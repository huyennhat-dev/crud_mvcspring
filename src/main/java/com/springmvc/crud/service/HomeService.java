package com.springmvc.crud.service;

import com.springmvc.crud.model.Account;
import com.springmvc.crud.model.Category;
import com.springmvc.crud.repo.CategoryRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

@Service
public class HomeService {

    public final CategoryRepo categoryRepo;

    public HomeService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public String getHomePage(ModelMap modelMap, @NotNull HttpSession session){

        Account acc = new Account();
        String email = (String)session.getAttribute("email");

        if(email!=null){
            acc.setUsername((String)session.getAttribute("username"));
            acc.setEmail((String)session.getAttribute("email"));
            acc.setStatus((int)session.getAttribute("uStatus"));
            modelMap.addAttribute("account", acc);
        }

        Iterable<Category> categories = categoryRepo.findAll();
        modelMap.addAttribute("categories", categories);
        return  "home";

    }
}
