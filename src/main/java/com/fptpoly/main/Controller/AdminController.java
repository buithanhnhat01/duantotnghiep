package com.fptpoly.main.Controller;


import com.fptpoly.main.Dao.*;
import com.fptpoly.main.Entity.Billaccessories;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/")
public class AdminController {

    @Autowired
    CarRepository carRepository;
    @Autowired
    AccessoriesRepository accessoriesRepository;
    @Autowired
    CartaccessoriesRepository cartaccessoriesRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    TypecarRepository typecarRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    BillaccessoriesRepository billaccessoriesRepository;
    @Autowired
    BillaccessoriesdetailRepository billaccessoriesdetailRepository;

    /*@GetMapping("/Admin")
    public String admin(Model model){
        model.addAttribute("donhangs", billaccessoriesRepository.findAllByTrangthaiOrderByNgaymuaDesc("PENDING"));
        return "admin/layouts/index";
    }*/

    private int[] totalbll(){
        int[] total = new int[4];
        total[0] = billaccessoriesRepository.findAllByTrangthaiOrderByNgaymuaDesc("PENDING").size();
        total[1] = billaccessoriesRepository.findAllByTrangthaiOrderByNgaymuaDesc("PACKING").size();
        total[2] = billaccessoriesRepository.findAllByTrangthaiOrderByNgaymuaDesc("SUCCESS").size();
        total[3] = billaccessoriesRepository.findAllByTrangthaiOrderByNgaymuaDesc("CANCEL").size();
        return total;
    }


    @GetMapping("/Admin")
    public String status(Model model, @RequestParam("status")Optional<String> status){
        model.addAttribute("donhangs", billaccessoriesRepository.findAllByTrangthaiOrderByNgaymuaDesc(status.orElse("PENDING")));
        model.addAttribute("totalbill",totalbll());
        model.addAttribute("status",status.orElse("PENDING"));
        return "admin/layouts/index";
    }
    @PostMapping("Admin")
    public String packing(@RequestParam("madh")String[] mahds,@RequestParam("btn")String btn){
        String sta = null;
        switch (btn){
            case "PENDING":{
                sta = "PACKING";
                break;
            }
            case "PACKING":{
                sta = "SHIPING";
                break;
            }
        }
        for(int i=0;i<mahds.length;++i){
            Billaccessories billaccessories = billaccessoriesRepository.findAllByMahd(mahds[i]);
            billaccessories.setNgaynhan(new Date());
            billaccessories.setTrangthai(sta);
            billaccessoriesRepository.save(billaccessories);
        }

        return "redirect:/Admin?status=PENDING";
    }


    @GetMapping("Admin/orders-accessories-detail")
    public String orders_detail(Model model,@RequestParam("madh")String madh) {
        model.addAttribute("bill",billaccessoriesRepository.findAllByMahd(madh));
        return "admin/pages/E-commerce/orders/orders-accessories-details";
    }

    // Product
    @RequestMapping("admin/product-car")
    public String product_car(Model model) {
        return "admin/pages/E-commerce/products/product-car";
    }

    @RequestMapping("admin/product-phukien")
    public String product_phukien(Model model) {
        return "admin/pages/E-commerce/products/product-phukien";
    }

    @RequestMapping("admin/car-detail")
    public String car_detail(Model model) {
        return "admin/pages/E-commerce/products/car-details";
    }

    @RequestMapping("admin/access-detail")
    public String access_detail(Model model) {
        return "admin/pages/E-commerce/products/access-details";
    }

    @RequestMapping("admin/add-car")
    public String car_add(Model model) {
        return "admin/pages/E-commerce/products/add-car";
    }

    @RequestMapping("admin/add-access")
    public String access_add(Model model) {
        return "admin/pages/E-commerce/products/add-access";
    }

    // Oders
    @RequestMapping("admin/orders-car")
    public String orders_car(Model model) {
        return "admin/pages/E-commerce/orders/orders-car";
    }

    @RequestMapping("admin/orders-accessories")
    public String orders_access(Model model) {
        return "admin/pages/E-commerce/orders/orders-accessories";
    }

    // users
    @RequestMapping("admin/users-add")
    public String users_add(Model model) {
        return "admin/pages/users/users-add-user";
    }

    @RequestMapping("admin/users")
    public String users(Model model) {
        return "admin/pages/users/users";
    }

    @RequestMapping("admin/user-profile")
    public String user_profile(Model model) {
        return "admin/pages/users/user-profile";
    }

    @RequestMapping("admin/user-my-profile")
    public String user_my_profile(Model model) {
        return "admin/pages/users-profile/user-my-profile";
    }

    @RequestMapping("admin/user-edit")
    public String user_edit(Model model) {
        return "admin/pages/accounts/account-settings";
    }

    // welcome
    @RequestMapping("admin/welcome")
    public String welcome(Model model) {
        return "admin/welcome-page";
    }




}
