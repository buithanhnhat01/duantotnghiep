package com.fptpoly.main.Controller;


import com.fptpoly.main.Dao.*;
import com.fptpoly.main.Entity.Billaccessories;
import com.fptpoly.main.Util._MailService;
import com.fptpoly.main.Entity.Brand;
import com.fptpoly.main.Entity.Car;
import com.fptpoly.main.Entity.Accessories;
import com.fptpoly.main.Entity.Account;
import com.fptpoly.main.service.AccessoriesService;
import com.fptpoly.main.service.AccountService;
import com.fptpoly.main.service.CarService;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebInputException;

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


    @Autowired
    CarService carService;

    @Autowired
    AccessoriesService accessoriesService;

    @Autowired
    AccountService accountService;

    @Autowired
    _MailService mailService;

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
            if(btn.equals("PENDING")){
                mailService.sendEmail("longzu102@gmail.com","XÁC NHẬN ĐƠN HÀNG","Đơn Hàng Mã #"+mahds[i]+" Đã Được Xác Nhận");
            }
        }

        return "redirect:/Admin?status=PENDING";
    }


    @GetMapping("Admin/orders-accessories-detail")
    public String orders_detail(Model model,@RequestParam("madh")String madh) {
        model.addAttribute("bill",billaccessoriesRepository.findAllByMahd(madh));
        return "admin/pages/E-commerce/orders/orders-accessories-details";
    }

    // Product

    @GetMapping("Admin/product-car")
    public String addProduct(Model model) {
    	model.addAttribute("listCars", carService.getAllCars());
        return "admin/pages/E-commerce/products/product-car";
    }

    @GetMapping("/Admin/add-car")
    @RequestMapping("Admin/product-car")
    public String product_car(Model model) {
    	Car car =  new Car();
    	model.addAttribute("addBrand", brandRepository.findAll());
    	model.addAttribute("addType", typecarRepository.findAll());
    	model.addAttribute("addCars", car);
        return "admin/pages/E-commerce/products/add-car";
    }

    @PostMapping("/saveCar")
    public String saveCar(@Validated @ModelAttribute("addCars") Car car,BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){
    		return "admin/pages/E-commerce/products/add-car";
    	}
    	carService.saveCars(car);
    	return "redirect:Admin/product-car";
    }

    @GetMapping("/showFormUpdateCar/{id}")
    public String showFormUpdateCar(@PathVariable (value = "id") String id, Model model) {
    	// lấy car from service
    	Car car = carService.getCarById(id);

    	// set car as a model
    	model.addAttribute("addBrand", brandRepository.findAll());
    	model.addAttribute("addType", typecarRepository.findAll());
    	model.addAttribute("car", car);
        model.addAttribute("listcar",carRepository.findAll());
        System.out.println(carRepository.findAll().size());
        return "admin/pages/E-commerce/products/product-car";
    }

    @RequestMapping(value = "/deleteCar", method = RequestMethod.GET)
    public String deleteCar(@RequestParam("id") String id, Model model) {
      carService.deleteCar(id);
      return "redirect:/Admin/product-car";
    }

    //------------------- Accessories ---------------------

    @GetMapping("admin/orders-car")
    public String orders_car(Model model) {

        return "admin/pages/E-commerce/orders/orders-car";
    }

    @RequestMapping("admin/product-phukien")
    @GetMapping("Admin/product-phukien")
    public String product_phukien(Model model) {
    	model.addAttribute("Accessories", accessoriesService.getAllAccessories());
        return "admin/pages/E-commerce/products/product-phukien";
    }

    @GetMapping("/Admin/add-accessories")
    public String Accessories(Model model) {
    	Accessories accessories = new Accessories();

    	model.addAttribute("Cars", carService.getAllCars());
    	model.addAttribute("Accessories", accessories);
        return "admin/pages/E-commerce/products/add-access";
    }

    @PostMapping("/saveAccessories")
    public String saveAccessories(@ModelAttribute("Accessories") Accessories accessories) {
//    	if(bindingResult.hasErrors()){
//    		return "admin/pages/E-commerce/products/add-car";
//    	}
    	accessoriesService.saveAccessories(accessories);
    	return "redirect:Admin/product-phukien";
    }


    @GetMapping("/showUpdateAccessories/{malk}")
    public String showUpdateAccessories(@PathVariable (value = "malk") String malk, Model model) {
    	// lấy accessories from service

    	Accessories accessories = accessoriesService.getAccessoriesByMaLk(malk);

    	// set accessories as a model
    	model.addAttribute("Cars", carService.getAllCars());
    	model.addAttribute("Accessories", accessories);
    	return "admin/pages/E-commerce/products/UpdateAccessories";

    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteAccessories(@RequestParam("malk") String malk, Model model) {
      accessoriesService.deleteAccessories(malk) ;
      return "redirect:/Admin/product-phukien";
    }


    // Oders


    @RequestMapping("admin/orders-accessories")
    public String orders_access(Model model) {
        return "admin/pages/E-commerce/orders/orders-accessories";
    }

    //--------------- Employee----------------
    @GetMapping("Admin/users")
    public String users(Model model) {

    	model.addAttribute("Employees", accountService.getAllAccount());
    	return "admin/pages/users/users";
    }

    @GetMapping("Admin/users-add")
    public String users_add(Model model) {
    	Account account = new Account();

    	model.addAttribute("role", accountService.getAllAccount());
    	model.addAttribute("Employee", account);

        return "admin/pages/users/users-add-user";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("Employee") Account account) {
    	accountService.saveAccount(account);

    	return "redirect:Admin/users";
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
