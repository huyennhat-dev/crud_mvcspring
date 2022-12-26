package com.springmvc.crud.service;

import com.cloudinary.utils.ObjectUtils;
import com.springmvc.crud.model.Category;
import com.springmvc.crud.model.Product;
import com.springmvc.crud.repo.CategoryRepo;
import com.springmvc.crud.repo.ProductRepo;
import com.springmvc.crud.utils.Utils;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.apache.http.entity.ContentType;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

  public final ProductRepo productRepo;
  public final CategoryRepo categoryRepo;

  public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
    this.productRepo = productRepo;
    this.categoryRepo = categoryRepo;
  }

  public String getAllProductPage(
    @NotNull HttpSession session,
    ModelMap modelMap
  ) {
    String adminEmail = (String) session.getAttribute("email");
    if (adminEmail != null) {
      int adminStatusCode = (int) session.getAttribute("uStatus");
      if (adminStatusCode >= 1) {
        Iterable<Product> products = productRepo.findAll();
        Iterable<Category> categories = categoryRepo.findAllByStatus(0);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categories);
        return "admin/product/list_product";
      }
      return "redirect:/";
    }
    return "redirect:/auth/login";
  }

  public String getProduct(
    @NotNull HttpSession session,
    ModelMap modelMap,
    long id
  ) {
    String adminEmail = (String) session.getAttribute("email");
    if (adminEmail != null) {
      int adminStatusCode = (int) session.getAttribute("uStatus");
      if (adminStatusCode >= 1) {
        Optional<Product> product = productRepo.findById(id);
        Optional<Category> category = categoryRepo.findById(
          product.get().getCategoryID()
        );
        modelMap.addAttribute("product", product.get());
        modelMap.addAttribute("category", category.get());
        return "admin/product/detail";
      }
      return "redirect:/";
    }
    return "redirect:/auth/login";
  }

  public String getAddProductPage(
    @NotNull HttpSession session,
    ModelMap modelMap
  ) {
    String adminEmail = (String) session.getAttribute("email");
    if (adminEmail != null) {
      int adminStatusCode = (int) session.getAttribute("uStatus");
      if (adminStatusCode >= 1) {
        Iterable<Category> categories = categoryRepo.findAllByStatus(0);
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
    @NotNull BindingResult bindingResult
  ) throws IOException {
    if (bindingResult.hasErrors()) {
      Iterable<Category> categories = categoryRepo.findAll();
      modelMap.addAttribute("categories", categories);
      modelMap.addAttribute("product", new Product());
      return "admin/products/add";
    }

    boolean permit = Arrays
      .asList(
        ContentType.IMAGE_JPEG.getMimeType(),
        ContentType.IMAGE_PNG.getMimeType(),
        ContentType.IMAGE_WEBP.getMimeType()
      )
      .contains(productPhoto.getContentType());

    if (productPhoto != null && productPhoto.getSize() <= 3145728 && permit) {
      String dateNow = LocalDateTime.now().toString();

      Map params = ObjectUtils.asMap(
        "public_id",
        "java/images/IMG_" + dateNow,
        "overwrite",
        true,
        "resource_type",
        "image"
      );
      Map uploadResult = Utils.cloudinary
        .uploader()
        .upload(productPhoto.getBytes(), params);
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
        pro.setUploadTime(dateNow);
        pro.setUpdateTime(dateNow);
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

  public String delete(@NotNull HttpSession session, long id)
    throws IOException {
    String adminEmail = (String) session.getAttribute("email");
    if (adminEmail != null) {
      int adminStatusCode = (int) session.getAttribute("uStatus");
      if (adminStatusCode == 2) {
        Optional<Product> pro = productRepo.findById(id);
        Utils.cloudinary
          .uploader()
          .destroy(pro.get().getPhotoID(), ObjectUtils.asMap());
        productRepo.deleteById(id);
        return "redirect:/admin/products";
      }
      return "redirect:/";
    }
    return "redirect:/auth/login";
  }

  public String getEditProductPage(
    @NotNull HttpSession session,
    @NotNull ModelMap modelMap,
    long id
  ) {
    Optional<Product> product = productRepo.findById(id);
    Iterable<Category> categories = categoryRepo.findAllByStatus(0);
    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("product", product);
    return "admin/product/edit";
  }

  public String updateProduct(
    ModelMap modelMap,
    @NotNull MultipartFile productPhoto,
    @NotNull Product product,
    @NotNull BindingResult bindingResult,
    long id
  ) throws IOException {
    String productName = product.getProductName().trim();
    long price = product.getPrice();
    int quantity = product.getQuantity();
    long categoryID = product.getCategoryID();
    String desc = product.getDescription().trim();

    boolean permit = Arrays
      .asList(
        ContentType.IMAGE_JPEG.getMimeType(),
        ContentType.IMAGE_PNG.getMimeType(),
        ContentType.IMAGE_WEBP.getMimeType()
      )
      .contains(productPhoto.getContentType());

    if (bindingResult.hasErrors()) {
      return "admin/product/edit";
    }
    String dateNow = LocalDateTime.now().toString();
    Optional<Product> pro = productRepo.findById(id);
    if (pro.isPresent()) {
      Product foundPro = pro.get();
      if (!productName.isEmpty()) {
        String slug = Utils.toSlug(productName);
        foundPro.setProductName(productName);
        foundPro.setSlug(slug);
      }
      if (productPhoto != null && productPhoto.getSize() <= 3145728 && permit) {
        Map params = ObjectUtils.asMap(
          "public_id",
          "java/images/IMG_" + dateNow,
          "overwrite",
          true,
          "resource_type",
          "image"
        );
        Map uploadResult = Utils.cloudinary
          .uploader()
          .upload(productPhoto.getBytes(), params);
        String imgUrl = (String) uploadResult.get("url");
        String imgId = (String) uploadResult.get("public_id");
        if (!imgUrl.isEmpty()) {
          Utils.cloudinary
            .uploader()
            .destroy(pro.get().getPhotoID(), ObjectUtils.asMap());
          foundPro.setProductPhoto(imgUrl);
          foundPro.setPhotoID(imgId);
        }
      }
      if (foundPro.getStatus() != product.getStatus()) {
        foundPro.setStatus(product.getStatus());
      }
      if (price != foundPro.getPrice()) foundPro.setPrice(price);
      if (quantity != foundPro.getQuantity()) foundPro.setQuantity(quantity);
      if (categoryID != foundPro.getCategoryID()) foundPro.setCategoryID(
        categoryID
      );
      if (!desc.isEmpty()) foundPro.setDescription(desc);
      foundPro.setUpdateTime(dateNow);
      productRepo.save(foundPro);
    }
    return "redirect:/admin/products";
  }
}
