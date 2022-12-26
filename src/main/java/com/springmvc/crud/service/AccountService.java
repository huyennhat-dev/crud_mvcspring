package com.springmvc.crud.service;

import com.springmvc.crud.model.Account;
import com.springmvc.crud.repo.AccountRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }
    public String save(ModelMap modelMap, @NotNull Account account, @NotNull BindingResult bindingResult, HttpSession session){
        String username = account.getUsername();
        String email = account.getEmail();
        String password = account.getPassword();
        if(bindingResult.hasErrors()) {
            modelMap.addAttribute("error", "Thông tin của bạn không hợp lệ!");
            return "register";
        }

        if(!email.trim().isEmpty() && !username.trim().isEmpty() && !password.trim().isEmpty() ){
           if( !accountRepo.findByEmail(email).isPresent()){
               accountRepo.save(account);
               Optional<Account> acc=  accountRepo.findOneByEmailIgnoreCaseAndPassword(email, password);
               session.setAttribute("email", email);
               session.setAttribute("uId", acc.get().getId());
               session.setAttribute("username", username);
               session.setAttribute("uStatus", acc.get().getStatus());
               return "redirect:/";
           }
            modelMap.addAttribute("error", "Email đã được sử dụng");
            return "register";
        }
        modelMap.addAttribute("error", "Thông tin của bạn không hợp lệ!");
        return "register";
    }

    public String getAccount(ModelMap modelMap, @Valid @NotNull Account account, BindingResult bindingResult, HttpSession session){
        String email = account.getEmail();
        String password = account.getPassword();

        if(!email.trim().isEmpty() && !password.trim().isEmpty() ){
            Optional<Account> acc=  accountRepo.findOneByEmailIgnoreCaseAndPassword(email, password);
            if(acc.isPresent()){
                session.setAttribute("email", email);
                session.setAttribute("uId", acc.get().getId());
                session.setAttribute("username", acc.get().getUsername());
                session.setAttribute("uStatus", acc.get().getStatus());
                return "redirect:/";
            }
            modelMap.addAttribute("error", "Email hoặc mật khẩu không đúng!");
            return "login";
        }
        modelMap.addAttribute("error", "Email hoặc mật khẩu không hợp lệ!");
        return "login";
    }


}
