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
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(account.getRole());
        grantList.add(authority);
        System.out.println("Role: "+authority.getAuthority());
        UserDetails userDetails = (UserDetails) new User(account.getMatv(),account.getPassword(),grantList);
        return userDetails;
    }
}
