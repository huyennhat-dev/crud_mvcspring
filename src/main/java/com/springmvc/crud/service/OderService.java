package com.springmvc.crud.service;

import com.springmvc.crud.model.Chariot;
import com.springmvc.crud.model.Oder;
import com.springmvc.crud.repo.ChariotRepo;
import com.springmvc.crud.repo.OderRepo;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;

@Service
public class OderService {
public final ChariotRepo chariotRepo;
  public final OderRepo oderRepo;

  public OderService(OderRepo oderRepo, ChariotRepo chariotRepo) {
    this.oderRepo = oderRepo;
      this.chariotRepo = chariotRepo;
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
          Oder oder1 = new Oder();
          oder1.setUserId(uId);
          oder1.setNumberPhone(oder.getNumberPhone());
          oder1.setAddress(oder.getAddress());

          for (Chariot c:cha) {
              oder1.setProductId(c.getProductID());
              oder1.setQuantity(c.getQuantity());
          }
          oder1.setOderTime(LocalDateTime.now().toString());
          oder1.setStatus(0);
          oderRepo.save(oder1);
          return "redirect:"; /**********/

      }
    }
      return "redirect:/auth/login";

  }
}
