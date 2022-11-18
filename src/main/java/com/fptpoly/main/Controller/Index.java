package com.fptpoly.main.Controller;


import com.fptpoly.main.Dao.*;
import com.fptpoly.main.Entity.Appointment;
import com.fptpoly.main.Entity.Cartaccessories;
import com.fptpoly.main.Util._CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/")
public class Index {

    WebMainController controller = new WebMainController();


    @Autowired
    _CookieService cookieService;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ImagesaccessoriesRepository imagesaccessoriesRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    AccessoriesRepository accessoriesRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    TypecarRepository typecarRepository;
    @Autowired
    CartaccessoriesRepository cartaccessoriesRepository;
    @Autowired
    AppointmentRepository appointmentRepository;

    // hiển thị tất cả xe
    @GetMapping("CarsAll")
    public List Allcar() {
        /*System.out.println("Số lượng mãng:"+carRepository.findAll().size());*/
        return carRepository.findAll();
    }

    // hiện thị danh sách giỏ hàng theo từng thành viên
    @GetMapping("Inforcart")
    public List mmycart(Principal principal) {
        return cartaccessoriesRepository.findAll();
    }

    // chức năng giảm số lượng sản phầm trong giỏ hàng
    @GetMapping("Down/{stt}")
    public void down(@PathVariable("stt") int stt) {
        Cartaccessories cartaccessories = cartaccessoriesRepository.findAllByStt(stt);
        cartaccessories.setSoluong(cartaccessories.getSoluong() - 1);
        cartaccessoriesRepository.save(cartaccessories);
    }

    // chức năng tăng số lượng sản phầm trong giỏ hàng
    @GetMapping("Up/{stt}")
    public void up(@PathVariable("stt") int stt) {
        Cartaccessories cartaccessories = cartaccessoriesRepository.findAllByStt(stt);
        cartaccessories.setSoluong(cartaccessories.getSoluong() + 1);
        cartaccessoriesRepository.save(cartaccessories);
    }

    // chức năng xoá sản phầm trong giỏ hàng
    @GetMapping("Delete/{stt}")
    public void delete(@PathVariable("stt") int stt) {
        Cartaccessories cartaccessories = cartaccessoriesRepository.findAllByStt(stt);
        cartaccessoriesRepository.delete(cartaccessories);
    }

    // chức năng thêm sản phẩm vào giỏ hàng
    @GetMapping("Add")
    public void addlk(@RequestParam("idlk") String id, Principal principal) {
        System.out.println("Mã Sinh Kiện: " + id);
        boolean check = false;
        List<Cartaccessories> carts = cartaccessoriesRepository.findAllByAccount_Matv(principal.getName());  // kiểm tra sảm phẩm đã thêm chưa
        for (int i = 0; i < carts.size(); ++i) {
            Cartaccessories cart = carts.get(i);
            if (cart.getAccessoriesByMalk().getMalk().equals(id)) {      // sản phẩm đã có trong giỏ thì tăng 1
                cart.setSoluong(cart.getSoluong() + 1);
                cartaccessoriesRepository.save(cart);
                check = true;
                break;
            }
        }
        if (!check) {
            Cartaccessories cart = new Cartaccessories();  // sản phẩm chưa thêm thì thêm sản phẩm vào giỏ hàng
            cart.setAccessoriesByMalk(accessoriesRepository.findAllByMalk(id));
            cart.setAccount(accountRepository.findAllByMatv(principal.getName()));
            cart.setSoluong(1);
            cart.setGia(accessoriesRepository.findAllByMalk(id).getGia());
            cartaccessoriesRepository.save(cart);
        }
    }

    // hiển thị thông tin đặt lịch
    @GetMapping("/Appointment")
    public List book(@RequestParam("ngay") Optional<Date> ngay, Model model) {
        System.out.println(ngay);
        return appointmentRepository.findNgayhenAndCarByIdcar(ngay.orElse(new Date()), controller.idcar);
    }

    @GetMapping("/Appointment/Add")
    public void book(@RequestParam("ngayhen")Date ngayhen, @RequestParam("khunggio")String khunggio, Principal principal) throws ParseException {
        try {
            System.out.println("Ngày Hẹn: "+ngayhen+"  KHung Giờ: "+khunggio);
            Appointment create = new Appointment();
            SimpleDateFormat x = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(x.format(ngayhen));
            create.setLoai("Xem Xe");
            create.setNgayhen(x.parse(x.format(ngayhen)));
            create.setKhunggio(khunggio);
            System.out.println(principal.getName());
            create.setAccountByMatv(accountRepository.findAllByMatv(principal.getName()));
            create.setAccountByManv(accountRepository.findAllByRole("ADMIN"));
            create.setCarByIdcar(carRepository.findCarsByIdcar(controller.idcar));
            create.setGhichu("Không Có Chi");
            appointmentRepository.save(create);
            System.out.println("Đặt thành công");
        }catch (Exception ex){
            System.out.println("Lỗi:"+ex);
        }
        /*return "Đặt lịch thành công";*/
    }

    @GetMapping("TotalPage")
    public  List totalpage() {
        return carRepository.findAll();
    }

    // danh sách tất cả linh kiên có trong cửa hàng
    @GetMapping("LinhKien")
    public List accessories() {
        return accessoriesRepository.findAll();
    }
// Nhật làm
    // danh sách tất cả lich hen
    @GetMapping("LichHen")
    public List lichs() {
        return appointmentRepository.findAll();
    }

    // danh sách xe giảm giá
    @GetMapping("CarsSale")
    public List carsale() {
        return carRepository.findAll();
    }

    // danh sách các hãng xe
    @GetMapping("Brands")
    public List Allbrand() {
        return brandRepository.findAll();
    }

    // danh sách các thể loại xe
    @GetMapping("Types")
    public List types() {
        return typecarRepository.findAll();
    }

    // lọc sản phẩm theo loại xe
    @GetMapping("Cars/Loai")
    public List filltype(@RequestParam("type") String type) {
        return carRepository.findAllByTypecarByLoaixe(type);
    }

    @GetMapping(value = "Fill")
    public List fill() {
        return carRepository.findAllByNamsx(Integer.parseInt(controller.fill.getNam()));
    }
}
