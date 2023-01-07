package com.springmvc.crud.service;

import com.springmvc.crud.model.*;
import com.springmvc.crud.repo.CategoryRepo;
import com.springmvc.crud.repo.ChariotRepo;
import com.springmvc.crud.repo.ProductRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@Service
public class HomeService {

  public final CategoryRepo categoryRepo;
  public final ProductRepo productRepo;
  public final ChariotRepo chariotRepo;

  public HomeService(
    CategoryRepo categoryRepo,
    ProductRepo productRepo,
    ChariotRepo chariotRepo
  ) {
    this.categoryRepo = categoryRepo;
    this.productRepo = productRepo;
    this.chariotRepo = chariotRepo;
  }

  public String getHomePage(ModelMap modelMap, @NotNull HttpSession session) {
    Account acc = new Account();
    String email = (String) session.getAttribute("email");

    if (email != null) {
      acc.setUsername((String) session.getAttribute("username"));
      acc.setEmail((String) session.getAttribute("email"));
      acc.setStatus((int) session.getAttribute("uStatus"));
      modelMap.addAttribute("account", acc);
    }
    Iterable<Category> categories = categoryRepo.findAll();
    modelMap.addAttribute("categories", categories);
    Iterable<Product> products = productRepo.findAllByStatus(0);
    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("products", products);
    return "home";
  }

  public String getProductPage(
    ModelMap modelMap,
    HttpSession session,
    long id
  ) {
    Optional<Product> product = productRepo.findByIdAndStatus(id, 0);
    if (product.isPresent()) {
      Optional<Category> category = categoryRepo.findById(
        product.get().getCategoryID()
      );
      modelMap.addAttribute("product", product.get());
      modelMap.addAttribute("category", category.get().getCategoryName());
      return "show_product";
    }
    modelMap.addAttribute("error", "Sản phẩm này không tồn tại");
    return "show_product";
  }

  public String addToCart(
    ModelMap modelMap,
    HttpSession session,
    Product product
  ) {
    String adminEmail = (String) session.getAttribute("email");
    if (adminEmail != null) {
      int statusCode = (int) session.getAttribute("uStatus");
      long uId = (long) session.getAttribute("uId");
      if (statusCode >= 0) {
        Optional<Product> pro = productRepo.findById(product.getId());
        if (pro.isPresent()) {
          Optional<Chariot> chariot = chariotRepo.findByProductIDAndUserID(
            product.getId(),
            uId
          );
          if (chariot.isPresent()) {
            Chariot chariot2 = chariot.get();
            chariot2.setQuantity(chariot.get().getQuantity() + 1);
            chariotRepo.save(chariot2);
            modelMap.addAttribute("success", "Thêm vào giỏ hàng thành công");
            return getProductPage(modelMap, session, product.getId());
          }
          Chariot chariot1 = new Chariot();
          chariot1.setUserID(uId);
          chariot1.setProductID(product.getId());
          chariot1.setQuantity(1);

          chariotRepo.save(chariot1);
          modelMap.addAttribute("success", "Thêm vào giỏ hàng thành công");
          return getProductPage(modelMap, session, product.getId());
        }
      }
      modelMap.addAttribute("error", "Tài khoản của bạn đã bị khóa");
      return getProductPage(modelMap, session, product.getId());
    }
    return "redirect:/auth/login";
  }

  public String getCartPage(ModelMap modelMap, HttpSession session) {
    String uName = (String) session.getAttribute("username");
    if(uName !=null){
      long uId = (long) session.getAttribute("uId");
      if (uId >= 0) {
        Iterable<Chariot> chariots = chariotRepo.findAllByUserID(uId);

        List<Product> products = new ArrayList<Product>();

        long totalPrice = 0;

        for (Chariot c : chariots) {
          Product pro = productRepo.findById(c.getProductID()).get();
          products.add(pro);
          totalPrice = totalPrice + (pro.getPrice()*c.getQuantity());
        }

        modelMap.addAttribute("chariots", chariots);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("totalPrice", totalPrice);
        modelMap.addAttribute("username", uName);
        modelMap.addAttribute("oder", new Oder());
        return "chariot";
      }
    }
    return "redirect:/auth/login";
  }

  public String deleteChariot(ModelMap modelMap, HttpSession session, long id){
    String uName = (String) session.getAttribute("username");
    if(uName!=null){
      chariotRepo.deleteById(id);
      return "redirect:/cart";
    }
    return "redirect:/auth/login";

  }

  public String changeCart(ModelMap modelMap, HttpSession session, int key, long id){
    long uId = (long) session.getAttribute("uId");
    if (uId >= 0) {
      Chariot chariot = chariotRepo.findById(id).get();
      if(chariot.getQuantity()==1 && key==0) return deleteChariot(modelMap, session, id);
      if(key==1)  chariot.setQuantity(chariot.getQuantity() + 1);
      if(key==0 )  chariot.setQuantity(chariot.getQuantity() -1);
      chariotRepo.save(chariot);
      return "redirect:/cart";
    }
    return "redirect:/auth/login";
  }

  public String getSearchPage(ModelMap modelMap, @NotNull HttpSession session, String key) {
    Account acc = new Account();
    String email = (String) session.getAttribute("email");
    System.out.println(key);
    if (email != null) {
      acc.setUsername((String) session.getAttribute("username"));
      acc.setEmail((String) session.getAttribute("email"));
      acc.setStatus((int) session.getAttribute("uStatus"));
      modelMap.addAttribute("account", acc);
    }

    if(key != null){
      Iterable<Product> searchResult = productRepo.findAllProduct(key);
      modelMap.addAttribute("searchResult", searchResult);
    }




    Iterable<Category> categories = categoryRepo.findAll();
    modelMap.addAttribute("categories", categories);
    Iterable<Product> products = productRepo.findAllByStatus(0);
    modelMap.addAttribute("categories", categories);
    modelMap.addAttribute("products", products);
    return "search";
  }

}
