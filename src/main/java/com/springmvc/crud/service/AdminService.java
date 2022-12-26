package com.springmvc.crud.service;

import com.springmvc.crud.model.Account;
import com.springmvc.crud.repo.AccountRepo;
import com.springmvc.crud.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class AdminService {
    public final AccountRepo accountRepo;

    public AdminService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }


    public String getAdminHomePage(@NotNull ModelMap modelMap, @NotNull HttpSession session) {
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail != null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if (adminStatusCode >= 1) {
                modelMap.addAttribute("uStatus", adminStatusCode);
                return "admin/home";
            }
            return "redirect:/";

        }
        return "redirect:/auth/login";
    }

    public String getAccountManagerPage(@NotNull ModelMap modelMap, @NotNull HttpSession session) {
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail != null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if (adminStatusCode >= 1) {
                modelMap.addAttribute("uStatus", adminStatusCode);
                return "admin/account/manager";
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";
    }

    public String getAdminManagerPage(@NotNull ModelMap modelMap, @NotNull HttpSession session) {
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail != null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if (adminStatusCode == 2) {
                Iterable<Account> accounts = accountRepo.findAllAccountStatusByOne();
                modelMap.addAttribute("uStatus", adminStatusCode);
                modelMap.addAttribute("accounts", accounts);
                return "admin/account/admin";
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";
    }

    public String adminDown(@NotNull ModelMap modelMap, @NotNull HttpSession session, long id) {
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail != null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if ( adminStatusCode ==2) {
               Account account = accountRepo.findById(id).get();
                account.setStatus(0);
                accountRepo.save(account);
                return "redirect:/admin/accounts/admin";
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";
    }

    public String getUserManagerPage(@NotNull ModelMap modelMap, @NotNull HttpSession session) {
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail != null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if (adminStatusCode == 1 || adminStatusCode ==2) {
                Iterable<Account> accounts = accountRepo.findAllAccountStatusByZero();
                modelMap.addAttribute("uStatus", adminStatusCode);
                modelMap.addAttribute("accounts", accounts);
                return "admin/account/user";
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";
    }
    public String userUp(@NotNull ModelMap modelMap, @NotNull HttpSession session, long id) {
        String adminEmail = (String) session.getAttribute("email");
        if (adminEmail != null) {
            int adminStatusCode = (int) session.getAttribute("uStatus");
            if ( adminStatusCode ==1 || adminStatusCode ==2) {
                Account account = accountRepo.findById(id).get();
                account.setStatus(1);
                accountRepo.save(account);
                return "redirect:/admin/accounts/user";
            }
            return "redirect:/";
        }
        return "redirect:/auth/login";
    }
}
