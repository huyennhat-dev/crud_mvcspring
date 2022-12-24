package com.springmvc.crud.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminService {
    public String getAdminHomePage(@NotNull HttpSession session) {
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail != null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if (adminStatusCode >= 1) {
                return "admin/home";
            }
            return "redirect:/";

        }
        return "redirect:/auth/login";
    }

}
