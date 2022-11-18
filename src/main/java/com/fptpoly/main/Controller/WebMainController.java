package com.fptpoly.main.Controller;

import com.fptpoly.main.Dao.*;
import com.fptpoly.main.Entity.*;
import com.fptpoly.main.Util._CookieService;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/")
public class WebMainController {

    public static Logger logger = LoggerFactory.getLogger(WebMainController.class);

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
    public static String hangxe = null;
    public static fillCar fill;
    public static String idcar;
    public static boolean thongbao = false;


    // trang mặc định
    @GetMapping("home")
    public String main(Model model) {
        hangxe = null;
        return "site/layouts/index-2";
    }
    // Phụ kiện

    // Danh sách xe
    @GetMapping("home/product-detail")
    public String productdetail(Model model) {
        return "site/products/product-detail";
    }

    // danh sách tất cả các xe
    @GetMapping("home/listcar")
    public String listcar(Model model,@RequestParam("page")int indexPage,@RequestParam("search")Optional<String> search) {
        /*_CookieService cookieService = new _CookieService();
        cookieService.add("page",indexPage+"",1);*/
        String check = search.orElse(null);
        Page<Car> page = null;
        if(check==null || check.isEmpty()){
             page = carRepository.findAll(PageRequest.of(indexPage-1, 10));
        }else {
            Pageable pageable = PageRequest.of(0,Integer.MAX_VALUE);
            page = carRepository.searchAllByTencarLike("%"+search.get()+"%",pageable);
        }
        if (indexPage==0){
            indexPage=1;
        }if (indexPage>=page.getTotalPages()){
            indexPage = page.getTotalPages();
        }
        model.addAttribute("AllCar",page);
        model.addAttribute("IndexPage",indexPage);
        model.addAttribute("Brands", brandRepository.findAll());
        model.addAttribute("Types", typecarRepository.findAll());
        model.addAttribute("fill", new fillCar());
        return "site/products/luoicar";

    }

    @GetMapping("home/listcar/sreach")
    public String search(Model model,@RequestParam("search")String search){
        int totalpage = 0;
        if(carRepository.findAll().size()%10>0){
            totalpage = carRepository.findAll().size()/10+1;
        }else{
            totalpage =carRepository.findAll().size()/10;
        }
        model.addAttribute("totalPage",totalpage);
        model.addAttribute("Brands", brandRepository.findAll());
        model.addAttribute("Types", typecarRepository.findAll());
        return "site/products/luoicar";
    }


    // chức năng đăng xuất
    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/home";
    }

    // chức năng lọc xem theo loại xe và hãng xe
    /*@GetMapping("home/listcar/{filler}")
    public String listcar(Model model, @PathVariable("filler") String fil) {
        try {
            if (fil.startsWith("LX")) {
                model.addAttribute("fillCar", carRepository.findAllByTypecarByLoaixe(fil));
            } else {
                model.addAttribute("fillCar", carRepository.findAllByBrandByMa(fil));
            }
        } catch (Exception e) {
            model.addAttribute("fillCar", carRepository.findAll());
        } finally {
            model.addAttribute("Brands", brandRepository.findAll());
            model.addAttribute("Types", typecarRepository.findAll());
            model.addAttribute("fill", new fillCar());
            return "site/products/listcar";
        }

    }*/
    /*@GetMapping("home/listcar/{filler}")
    public String listcar(Model model) {
        *//*model.addAttribute("namsx",x);*//*
        model.addAttribute("Brands", brandRepository.findAll());
        model.addAttribute("Types", typecarRepository.findAll());
        model.addAttribute("fill", new fillCar());
        return "site/products/luoicar";

    }*/

    // chức năng thêm sản phẩm vào giỏ hàng trực tiếp bằng thymeleaf
    @GetMapping("home/addcart")
    public String addcart(@RequestParam("idlk") String id, Principal principal) {
        boolean check = false;
        System.out.println(principal.getName());
        List<Cartaccessories> carts = cartaccessoriesRepository.findAllByAccount_Matv(principal.getName());
        for (int i = 0; i < carts.size(); ++i) {
            Cartaccessories cart = carts.get(i);
            if (cart.getAccessoriesByMalk().getMalk().equals(id)) {
                cart.setSoluong(cart.getSoluong() + 1);
                cartaccessoriesRepository.save(cart);
                check = true;
                break;
            }
        }
        if (!check) {
            Cartaccessories cart = new Cartaccessories();
            cart.setAccessoriesByMalk(accessoriesRepository.findAllByMalk(id));
            cart.setAccount(accountRepository.findAllByMatv(principal.getName()));
            cart.setSoluong(1);
            cart.setGia(accessoriesRepository.findAllByMalk(id).getGia());
            cartaccessoriesRepository.save(cart);
        }
        return "redirect:phukien-detail?idlk=" + id;
    }

    // cái ni đang định bỏ
    @PostMapping("home/listcar")
    public void listcar(Model model, @ModelAttribute("fill") fillCar fillcar) {
        System.out.println("Data " + fillcar.getNam());
        fill = fillcar;
        /*return "site/products/listcar";*/
    }

    @GetMapping("home/luoicar")
    public String luoicar(Model model) {
        return "site/products/luoicar";
    }

    @GetMapping(value = "home/car-detail")
    public String cardetail(Model model, @RequestParam("idcar") String id,Principal principal) {
        try {
            model.addAttribute("Account",accountRepository.findAllByMatv(principal.getName()));
        }catch (Exception ex){
            model.addAttribute("Account",new Account());
            logger.info("Không Sao Vẫn Ổn 😘👌");
        }
        Date x = new Date();
        idcar = id;
        System.out.println(appointmentRepository.findNgayhenAndCarByIdcar(x, id).size());
        /*carRepository.findCarsByIdcar(id).getImagescarsByIdcar().get(0).getHinh();*/
        model.addAttribute("thongtingio", appointmentRepository.findNgayhenAndCarByIdcar(x, id));
        model.addAttribute("Cardetail", carRepository.findCarsByIdcar(id));
        return "site/products/car-detail";
    }

    // Danh sách phụ kiện
    @GetMapping("home/listphukien")
    public String listphukien(Model model) {
        model.addAttribute("Alllk", accessoriesRepository.findAll());
        return "site/products/listphukien";
    }

    @GetMapping("home/luoipk")
    public String luoipk(Model model) {
        return "site/products/luoipk";
    }


    // hiển thị chi tiết sản phẩm
    @GetMapping("home/phukien-detail")
    public String pkdetail(Model model, @RequestParam("idlk") String id) {
        model.addAttribute("Lkdetail", accessoriesRepository.findAllByMalk(id));
        return "site/products/phukien-detail";
    }

    // Diễn đàn
    @GetMapping("home/blog")
    public String blog(Model model) {
        return "site/blogs/blog";
    }

    // So sánh ô tô

    // Error 403
    @GetMapping("403")
    public String error() {
        return "site/security/404error";
    }
    // Tùy chọn

    // Tài khoản của tôi

    // Pages
    @GetMapping("home/about")
    public String about(Model model) {
        return "site/about-us";
    }

    //Shop Cart
    @GetMapping("home/member/shopping-cart")
    public String giohang(Model model) {
          /*UserDetails userDetail =(UserDetails) authentication.getPrincipal();
          System.out.println("Tên Đăng Nhập: "+userDetail.getUsername()+"Pass "+userDetail.getAuthorities());
          model.addAttribute("Mycarts",cartaccessoriesRepository.findAllByMatv(userDetail.getUsername()));*/
        model.addAttribute("thongbao", thongbao);
        thongbao = false;
        return "site/shopping-cart/shopping-cart";
    }

    /*@GetMapping("home/o")
    public String quenmatkhau(Model model){
         return "site/security/quenmatkhau";
    }*/
    @GetMapping("home/checkout")
    public String checkout(Model model) {
        return "site/oders/check";
    }

    @GetMapping("home/Payorder")
    public String payorder(Model model, Principal principal) {
        try {
            Random rn = new Random();
            int HoadonNumber = rn.nextInt(99999999) + 10000000;
            String Mahoadon = "HD" + HoadonNumber;
            double tongtien = 0;
            // tạo và lưu hoá đơn
            Billaccessories billaccessories = new Billaccessories();
            billaccessories.setMahd(Mahoadon);
            billaccessories.setAccountByMatv(accountRepository.findAllByMatv(principal.getName()));
            /*billaccessories.setNgaynhan(null);*/
            for (Cartaccessories cartaccessories : cartaccessoriesRepository.findAllByAccount_Matv(principal.getName())) {
                tongtien += cartaccessories.getSoluong() * cartaccessories.getAccessoriesByMalk().getGia();
            }
            billaccessories.setTongtien(tongtien);
            billaccessories.setTrangthai("PENDING");
            billaccessoriesRepository.save(billaccessories);
            // tạo vào lưu hoá đơn chi tiết
            for (Cartaccessories cartaccessories : cartaccessoriesRepository.findAllByAccount_Matv(principal.getName())) {
                Billaccessoriesdetail billaccessoriesdetail = new Billaccessoriesdetail();
                billaccessoriesdetail.setBillaccessoriesByMahd(billaccessoriesRepository.findAllByMahd(Mahoadon));
                billaccessoriesdetail.setAccessoriesByMalk(cartaccessories.getAccessoriesByMalk());
                billaccessoriesdetail.setSoluong(cartaccessories.getSoluong());
                billaccessoriesdetail.setGia(cartaccessories.getAccessoriesByMalk().getGia());
                billaccessoriesdetail.setGhichu("Không Có Chiu Hết 😂");
                billaccessoriesdetailRepository.save(billaccessoriesdetail);
            }
            System.out.println("Tao Đơn Hàng Thành Công");
        } catch (Exception e) {
            System.err.println(e);
        }
        thongbao = true;

        return "redirect:/home/member/shopping-cart";
    }

    @GetMapping("home/{status}")
    public String pending(Model model,@PathVariable("status")String status,@RequestParam("idhd") String id) {
        try {
           /* List<Billaccessoriesdetail> ls = billaccessoriesdetailRepository.findAllByBillaccessoriesByMahd_Mahd(id);
            for (int i = 0; i < ls.size(); ++i) {
                Billaccessoriesdetail billaccessoriesdetail = ls.get(0);
                billaccessoriesdetailRepository.delete(ls.get(i));
            }*/
            Billaccessories billaccessories = billaccessoriesRepository.findAllByMahd(id);

            if (status.equals("PENDING") || status.equals("PACKING")){
                billaccessories.setTrangthai("CANCEL");
                billaccessories.setNgaynhan(new Date());
                logger.info("Huỷ Đơn Thành Công");
            }else if(status.equals("CANCEL")){
                billaccessories.setTrangthai("PENDING");
                billaccessories.setNgaymua(new Date());
                billaccessories.setNgaynhan(null);
                logger.info("Mua Lại Thành Công");
            }
            billaccessoriesRepository.save(billaccessories);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/home/dashboard?status="+status;
    }


    @GetMapping("home/hoadon")
    public String hoadon(Model model, @RequestParam("idhd") String idhd) {
        model.addAttribute("billdetail", billaccessoriesdetailRepository.findAllByBillaccessoriesByMahd_Mahd(idhd));
        return "site/user/hoadon-detail";
    }

    @GetMapping("home/LichHen")
    public String appointment(Model model,Principal principal) {
        System.out.println(appointmentRepository.fillappointment(principal.getName()).get(0).getLoai());
        model.addAttribute("appontments",appointmentRepository.fillappointment(principal.getName()));
        return "site/user/lichhen";
    }
    @GetMapping("home/dashboard")
    public String dashboard(Model model, Principal principal, @RequestParam("status") Optional<String> status) {
        String trangthai = status.orElse("PENDING");
        switch (trangthai) {
            case "PENDING": {
                model.addAttribute("donhangs", billaccessoriesRepository.findAllByTrangthaiAndAccountByMatv_MatvOrderByMahdDesc("PENDING", principal.getName()));
                break;
            }
            case "PACKING": {
                model.addAttribute("donhangs", billaccessoriesRepository.findAllByTrangthaiAndAccountByMatv_MatvOrderByMahdDesc("PACKING", principal.getName()));
                break;
            }
            case "SHIPING": {
                model.addAttribute("donhangs", billaccessoriesRepository.findAllByTrangthaiAndAccountByMatv_MatvOrderByMahdDesc("SHIPING", principal.getName()));
                break;
            }
            case "SUCCESS": {
                model.addAttribute("donhangs", billaccessoriesRepository.findAllByTrangthaiAndAccountByMatv_MatvOrderByMahdDesc("SUCCESS", principal.getName()));
                break;
            }
            default: {
                model.addAttribute("donhangs", billaccessoriesRepository.findAllByTrangthaiAndAccountByMatv_MatvOrderByMahdDesc("CANCEL", principal.getName()));
                break;
            }
        }
        model.addAttribute("checked",status.orElse("PENDING"));
        return "site/user/donhang";
    }

    @GetMapping("home/donhang")
    public String donhang(Model model) {
        return "site/user/donhang";
    }

    @GetMapping("home/user-info")
    public String user_info(Model model) {
        return "site/user/user-info";
    }

    @GetMapping("home/wishlist")
    public String wishlist(Model model) {
        return "site/wishlist";
    }

    @GetMapping("home/contact")
    public String contact(Model model) {
        return "site/contact-us";
    }

    // account
    @GetMapping("home/login")
    public String login(Model model) {
        return "site/security/login";
    }

    @GetMapping("home/dangky")
    public String dangky(Model model) {
        return "site/security/dangky";
    }

    @GetMapping("home/quenmatkhau")
    public String quenmatkhau(Model model) {
        return "site/security/quenmatkhau";
    }

}
