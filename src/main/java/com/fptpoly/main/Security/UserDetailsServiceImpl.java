package com.fptpoly.main.Security;

import com.fptpoly.main.Dao.AccountRepository;
import com.fptpoly.main.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAllByMatv(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();// xet quyen`
        GrantedAuthority authority = new SimpleGrantedAuthority(account.getRole());
        grantList.add(authority);
        System.out.println("Role: " + authority.getAuthority());
        UserDetails userDetails = (UserDetails) new User(account.getMatv(), account.getPassword(), grantList);// quan
                                                                                                              // trong
        return userDetails;
    }
}
// định nghĩa cách kiểm tra username , password và quyền của user có hợp lệ hay
// không Khi
// user login vào hệ thống ta sẽ query xuống database để kiểm tra user có đúng
// trong database không và quyền là gì ?