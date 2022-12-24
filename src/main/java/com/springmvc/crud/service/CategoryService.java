package com.springmvc.crud.service;

import com.springmvc.crud.model.Category;
import com.springmvc.crud.repo.CategoryRepo;
import com.springmvc.crud.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class CategoryService {

    public final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public String getAdminCategoryPage(ModelMap modelMap, @NotNull HttpSession session){
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail!=null) {
            int adminStatusCode = (int)session.getAttribute("uStatus");
           if(adminStatusCode>=1){
               Iterable<Category> categories = categoryRepo.findAll();
               modelMap.addAttribute("categories", categories);
               modelMap.addAttribute("uStatus", adminStatusCode);
               return "admin/category/list_category";
           }
            return "redirect:/";
        }
        return "redirect:/auth/login";
    }

    public String getAddCategoryPage(@NotNull HttpSession session, ModelMap modelMap){
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail!=null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if (adminStatusCode >= 1) {
                modelMap.addAttribute("categories", new Category());
                return "admin/category/add";
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";
    }


    public  String add(@NotNull Category category, @NotNull BindingResult bindingResult){
        String categoryName = category.getCategoryName().trim();
        if (bindingResult.hasErrors()){
            return "admin/category/add";
        }
        if(!categoryName.isEmpty()){
            String slug = Utils.toSlug(categoryName);
            Category  cate = new Category();
            cate.setCategoryName(categoryName);
            cate.setSlug(slug);
            cate.setStatus(0);
            categoryRepo.save(cate);
            return "redirect:/admin/categories";

        }
        return "admin/category/add";
    }

    public String getEditCategoryPage(@NotNull HttpSession session, ModelMap modelMap, long id){
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail!=null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if (adminStatusCode >= 1) {
                Optional<Category> category = categoryRepo.findById(id);
                modelMap.addAttribute("category", category.get());
                return "admin/category/edit";
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";

    }

    public String updateCategoryPage(
            HttpSession session, ModelMap modelMap,
            Category category, @NotNull BindingResult bindingResult, long id)
    {
        if (bindingResult.hasErrors()) {
            return "admin/category/edit";
        }
        String categoryName = category.getCategoryName().trim();
        int status = category.getStatus();
        Optional<Category> cate = categoryRepo.findById(id);
        if(cate.isPresent()) {
            Category foundCategory = cate.get();
            if(!categoryName.isEmpty()) {
                String slug = Utils.toSlug(categoryName);

                foundCategory.setCategoryName(categoryName);
                foundCategory.setSlug(slug);
            }
            if(status!=cate.get().getStatus()){
                foundCategory.setStatus(status);
            }
            categoryRepo.save(foundCategory);
        }
        return "redirect:/admin/categories";
    }

    public String delete(@NotNull HttpSession session , long id){
        String adminEmail = (String)session.getAttribute("email");
        if (adminEmail!=null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if (adminStatusCode == 2) {
                categoryRepo.deleteById(id);
                return "redirect:/admin/categories";
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";
    }
}
