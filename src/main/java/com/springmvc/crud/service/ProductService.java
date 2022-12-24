package com.springmvc.crud.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.springmvc.crud.model.Category;
import com.springmvc.crud.model.Product;
import com.springmvc.crud.repo.CategoryRepo;
import com.springmvc.crud.repo.ProductRepo;
import com.springmvc.crud.utils.Utils;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Service
public class ProductService {
    public final ProductRepo productRepo;
    public final CategoryRepo categoryRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public String getAllProductPage(HttpSession session, ModelMap modelMap) {
        return "admin/product/list_product";
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

        boolean permited = Arrays.asList(
                ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_WEBP.getMimeType()
        ).contains(productPhoto.getContentType());

        if (productPhoto != null && productPhoto.getSize() <= 3145728 && permited) {
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "huyennhat",
                    "api_key", "836136537452954",
                    "api_secret", "qsXAaQH1f5b5zcLCtXu7-p0NTto",
                    "secure", true));
            Map uploadResult = cloudinary.uploader().upload(productPhoto.getBytes(), ObjectUtils.emptyMap());
            String imgUrl = (String) uploadResult.get("url");

            if (!imgUrl.isEmpty()) {
                Product pro = new Product();
                pro.setProductName(product.getProductName().trim());
                pro.setSlug(Utils.toSlug(product.getProductName()));
                pro.setCategoryID(product.getCategoryID());
                pro.setProductPhoto(imgUrl);
                pro.setDescription(product.getDescription());
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
}
