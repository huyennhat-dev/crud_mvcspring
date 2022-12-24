package com.springmvc.crud.service;

import com.cloudinary.utils.ObjectUtils;
import com.springmvc.crud.model.Category;
import com.springmvc.crud.model.Product;
import com.springmvc.crud.repo.CategoryRepo;
import com.springmvc.crud.repo.ProductRepo;
import com.springmvc.crud.utils.Utils;
import org.apache.http.entity.ContentType;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    public final ProductRepo productRepo;
    public final CategoryRepo categoryRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public String getAllProductPage(HttpSession session, ModelMap modelMap) {
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail != null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if (adminStatusCode >= 1) {
                Iterable<Product> products = productRepo.findAll();
                Iterable<Category> categories = categoryRepo.findAll();
                modelMap.addAttribute("products", products);
                modelMap.addAttribute("categories", categories);
                return "admin/product/list_product";
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";

    }

    public String getAddProductPage(HttpSession session, ModelMap modelMap) {
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail != null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if (adminStatusCode >= 1) {
                Iterable<Category> categories = categoryRepo.findAll();
                modelMap.addAttribute("categories", categories);
                modelMap.addAttribute("product", new Product());
                return "admin/product/add";
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";
    }

    public String addProduct(
            ModelMap modelMap,
            MultipartFile productPhoto,
            @NotNull Product product,
            @NotNull BindingResult bindingResult)
            throws IOException {

        if (bindingResult.hasErrors()) {
            Iterable<Category> categories = categoryRepo.findAll();
            modelMap.addAttribute("categories", categories);
            modelMap.addAttribute("product", new Product());
            return "admin/products/add";
        }

        boolean permit = Arrays.asList(
                ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_WEBP.getMimeType()
        ).contains(productPhoto.getContentType());

        if (productPhoto != null && productPhoto.getSize() <= 3145728 && permit) {

            Map params = ObjectUtils.asMap(
                    "public_id", "java/images/IMG_" + LocalTime.now(),
                    "overwrite", true,
                    "resource_type", "image"
            );
            Map uploadResult = Utils.cloudinary.uploader().upload(productPhoto.getBytes(), params);
            String imgUrl = (String) uploadResult.get("url");
            String imgId = (String) uploadResult.get("public_id");

            if (!imgUrl.isEmpty()) {
                Product pro = new Product();
                pro.setProductName(product.getProductName().trim());
                pro.setSlug(Utils.toSlug(product.getProductName()));
                pro.setCategoryID(product.getCategoryID());
                pro.setProductPhoto(imgUrl);
                pro.setPhotoID(imgId);
                pro.setPrice(product.getPrice());
                pro.setQuantity(product.getQuantity());
                pro.setQuantityPurchased(0);
                pro.setDescription(product.getDescription());
                pro.setUploadTime(LocalDateTime.now().toString());
                pro.setUpdateTime(LocalDateTime.now().toString());
                productRepo.save(pro);
            }
            return "redirect:/admin/products";
        }

        Iterable<Category> categories = categoryRepo.findAll();
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("error", "Kích thước ảnh qúa lớn!");
        return "admin/products/add";
    }

    public String delete(@NotNull HttpSession session, long id) throws IOException {
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail != null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if (adminStatusCode == 2) {
                Optional<Product> pro = productRepo.findById(id);
                Utils.cloudinary.uploader().destroy(pro.get().getPhotoID(), ObjectUtils.asMap());
                productRepo.deleteById(id);
                return "redirect:/admin/products";
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";
    }
}
