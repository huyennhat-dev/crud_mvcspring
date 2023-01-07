package com.springmvc.crud.service;

import com.springmvc.crud.model.Account;
import com.springmvc.crud.model.Chariot;
import com.springmvc.crud.model.Oder;
import com.springmvc.crud.model.Product;
import com.springmvc.crud.repo.AccountRepo;
import com.springmvc.crud.repo.ChariotRepo;
import com.springmvc.crud.repo.OderRepo;
import javax.servlet.http.HttpSession;

import com.springmvc.crud.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OderService {
public final ChariotRepo chariotRepo;
  public final OderRepo oderRepo;
  public final HomeService homeService;
public final ProductRepo productRepo;
  public final AccountRepo accountRepo;

  public OderService(OderRepo oderRepo, ChariotRepo chariotRepo, HomeService homeService, AccountRepo accountRepo, ProductRepo productRepo) {
    this.oderRepo = oderRepo;
      this.chariotRepo = chariotRepo;
      this.homeService = homeService;
      this.accountRepo = accountRepo;
      this.productRepo = productRepo;
  }

  public String userCheckOut(
    ModelMap modelMap,
    HttpSession session,
    Oder oder
  ) {
    String uName = (String) session.getAttribute("username");
    if (uName != null) {
      long uId = (long) session.getAttribute("uId");

      Iterable<Chariot> cha = chariotRepo.findAllByUserID(uId);
      if(cha !=null){
          if(!oder.getAddress().isEmpty() && !oder.getNumberPhone().isEmpty()){
              for (Chariot c:cha) {
                  Oder oder1 = new Oder();
                  oder1.setUserId(uId);
                  oder1.setNumberPhone(oder.getNumberPhone());
                  oder1.setAddress(oder.getAddress());
                  oder1.setProductId(c.getProductID());
                  oder1.setQuantity(c.getQuantity());
                  oder1.setOderTime(LocalDateTime.now().toString());
                  oder1.setStatus(0);
                  oderRepo.save(oder1);

                  homeService.deleteChariot(modelMap, session,c.getId());

              }
              return "redirect:"; /**********/
          }
      }
    }
      return "redirect:/auth/login";

  }

  public String getAdminAllOder(ModelMap modelMap, HttpSession session){
      String adminEmail = (String) session.getAttribute("email");
      if (adminEmail != null) {
          int adminStatusCode = (int) session.getAttribute("uStatus");
          if (adminStatusCode >= 1) {
              Iterable<Oder> oders = oderRepo.findAll();
              List<Account> accs = new ArrayList<Account>();
              for (Oder o: oders) {
                  Account acc = accountRepo.findById(o.getUserId()).get();
                  accs.add(acc);
              }

              List<Product> products = new ArrayList<Product>();

              for (Oder o : oders) {
                  Product pro = productRepo.findById(o.getProductId()).get();
                  products.add(pro);
              }

              modelMap.addAttribute("oders" , oders);
              modelMap.addAttribute("accs" , accs);
              modelMap.addAttribute("products" , products);
              return "admin/oder/all_oder";
          }
          return "redirect:/";
      }
      return "redirect:/auth/login";
  }
}
