package com.springmvc.crud.controller;

import com.cloudinary.utils.ObjectUtils;
import com.springmvc.crud.model.Account;
import com.springmvc.crud.model.Category;
import com.springmvc.crud.model.Product;
import com.springmvc.crud.service.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.cloudinary.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

@Controller
@RequestMapping(path = "admin")
public class AdminController {

    public final AdminService adminService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final OderService oderService;

    public AdminController(AdminService adminService, CategoryService categoryService, ProductService productService, OderService oderService) {
        this.adminService = adminService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.oderService = oderService;
    }


    @GetMapping("")
    public String getHomePage(ModelMap modelMap, HttpSession session) {
        return adminService.getAdminHomePage(modelMap,session);
    }

    @GetMapping("/categories")
    public String getCategoryPage(ModelMap modelMap, HttpSession session) {
        return categoryService.getAdminCategoryPage(modelMap, session);
    }

    @GetMapping("categories/add")
    public String addCategoryPage(HttpSession session, ModelMap modelMap) {
        return categoryService.getAddCategoryPage(session, modelMap);
    }

    @PostMapping("categories/add")
    public String addCategory(@Valid @ModelAttribute("categories") Category category, BindingResult bindingResult) {
        return categoryService.add(category, bindingResult);
    }

    @GetMapping("categories/edit/{id}")
    public String editCategoryPage(HttpSession session, ModelMap modelMap, @PathVariable long id) {
        return categoryService.getEditCategoryPage(session, modelMap, id);
    }

    @PostMapping("categories/edit/{id}")
    public String updateCategory(
            HttpSession session,
            ModelMap modelMap,
            @Valid @ModelAttribute("category") Category category,
            BindingResult bindingResult,
            @PathVariable long id
    )
    {
        return categoryService.updateCategoryPage(session, modelMap, category, bindingResult, id);
    }

    @GetMapping("categories/delete/{id}")
    public String deleteCategory(HttpSession session ,@PathVariable long id){
        return categoryService.delete(session,id);
    }

    @GetMapping("products")
    public String getAllProducts(HttpSession session, ModelMap modelMap){
        return productService.getAllProductPage(session, modelMap);
    }

    @GetMapping("products/add")
    public String getAddProductsPage(HttpSession session, ModelMap modelMap){
        return productService.getAddProductPage(session, modelMap);
    }

    @PostMapping("products/add")
    public String addProduct(
            ModelMap modelMap,
            @RequestParam("image") MultipartFile image,
            @Valid @ModelAttribute("product") Product product,
            BindingResult bindingResult
            ) throws IOException {

        return productService.addProduct(modelMap,image, product, bindingResult);
    }

    @GetMapping("products/delete/{id}")
    public String delProduct(HttpSession session, @PathVariable long id) throws IOException {
        return productService.delete(session, id);
    }

    @GetMapping("products/{id}")
    public String getProductPage(HttpSession session, ModelMap modelMap, @PathVariable long id){
        return productService.getProduct(session, modelMap,id);
    }

    @GetMapping("products/edit/{id}")
    public String getEditProductPage(HttpSession session, ModelMap modelMap, @PathVariable long id){
        return productService.getEditProductPage(session, modelMap,id);
    }

    @PostMapping("products/edit/{id}")
    public String updateProduct(ModelMap modelMap,
                                @RequestParam("image") MultipartFile productPhoto,
                                @NotNull Product product,
                                @NotNull BindingResult bindingResult,
                                @PathVariable long id) throws IOException {
        return productService.updateProduct(modelMap, productPhoto, product, bindingResult, id);
    }

    @GetMapping("accounts")
    public String accountManager(ModelMap modelMap, HttpSession session){
        return adminService.getAccountManagerPage(modelMap, session);
    }
    @GetMapping("accounts/admin")
    public String adminManager(ModelMap modelMap, HttpSession session){
        return adminService.getAdminManagerPage(modelMap, session);
    }
    @GetMapping("accounts/admin/down/{id}")
    public String adminDown(ModelMap modelMap, HttpSession session, @PathVariable long id){
        return adminService.adminDown(modelMap, session, id);}
    @GetMapping("accounts/user")
    public String userManager(ModelMap modelMap, HttpSession session){
        return adminService.getUserManagerPage(modelMap, session);
    }

    @GetMapping("accounts/user/up/{id}")
    public String userUp(ModelMap modelMap, HttpSession session, @PathVariable long id){
        return adminService.userUp(modelMap, session, id);
    }

    @GetMapping("oders")
    public String getAllOders(ModelMap modelMap, HttpSession session){
return oderService.getAdminAllOder(modelMap, session);
    }

}
