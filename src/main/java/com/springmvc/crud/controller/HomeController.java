package com.springmvc.crud.controller;

import com.springmvc.crud.model.Account;
import com.springmvc.crud.model.Oder;
import com.springmvc.crud.model.Product;
import com.springmvc.crud.service.HomeService;
import com.springmvc.crud.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "")
public class HomeController {

    public final HomeService homeService;
public final OderService oderService;
    public HomeController(HomeService homeService, OderService oderService) {
        this.homeService = homeService;
        this.oderService = oderService;
    }

    @GetMapping("")
    public String getHomePage(ModelMap modelMap, HttpSession session)
    {
        return homeService.getHomePage(modelMap, session);
    }

    @GetMapping("/product/{id}")
    public  String getProductPage(ModelMap modelMap, HttpSession session, @PathVariable long id){
        return homeService.getProductPage(modelMap, session, id);
    }

    @PostMapping("/product/{id}")
    public String addToCart(ModelMap modelMap, HttpSession session, @ModelAttribute("product")Product product){
        return homeService.addToCart(modelMap, session, product);
    }

    @GetMapping("/cart")
    public  String getCartPage(ModelMap modelMap, HttpSession session){
        return homeService.getCartPage(modelMap, session);
    }
    @GetMapping("/cart/delete/{id}")
    public  String deleteCart(ModelMap modelMap, HttpSession session, @PathVariable long id){
        return homeService.deleteChariot(modelMap, session, id);
    }
    @GetMapping("/cart/change/{key}/{id}")
    public  String changeCart(ModelMap modelMap, HttpSession session, @PathVariable int key, @PathVariable long id){
        return homeService.changeCart(modelMap, session, key, id);
    }

    @PostMapping("/cart")
    public String checkOut(ModelMap modelMap, HttpSession session, @ModelAttribute("oder")Oder oder){
        return oderService.userCheckOut(modelMap, session, oder);
    }
}
