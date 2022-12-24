package com.springmvc.crud.controller;

import com.springmvc.crud.model.Account;
import com.springmvc.crud.service.AccountService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "auth")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String getLoginPage(ModelMap modelMap, @NotNull HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null) return "redirect:/admin/home";
        modelMap.addAttribute("account", new Account());
        return "login";
    }

    @PostMapping("/login")
    public String loginAccount(
            ModelMap modelMap,
            @Valid @ModelAttribute("account") Account account,
            BindingResult bindingResult,
            HttpSession session) {
        return accountService.getAccount(modelMap, account, bindingResult, session);
    }

    @GetMapping("/register")
    public String getRegisterPage(ModelMap modelMap, @NotNull HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null) return "redirect:/admin/home";
        modelMap.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String registerAccount(
            ModelMap modelMap,
            @Valid @ModelAttribute("account") Account account,
            BindingResult bindingResult,
            HttpSession session) {
        return accountService.save(modelMap, account, bindingResult, session);
    }
}
