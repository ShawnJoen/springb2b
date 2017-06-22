package com.spring.service.admin.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.vo.admin.AdminUserVO;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
	
    @Autowired
    private AdminUserService adminUserService;
     
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

    	AdminUserVO adminUser = adminUserService.getAdminUserByUsername(username);
        
        if(adminUser == null) {
        	
        	System.out.println("AdminUser not found");
            
            throw new UsernameNotFoundException("Login Account not found"); 
        }
        
        System.out.println("AdminUser:" + adminUser);

        return new org.springframework.security.core.userdetails.User(adminUser.getUsername(), adminUser.getPassword(), 
            		adminUser.getStatus() == 1, true, true, true, getGrantedAuthorities(adminUser));
    }
    
    private List<GrantedAuthority> getGrantedAuthorities(AdminUserVO adminUser) {
    	
    	final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        final List<String> roles = adminUserService.getAdminRoleAccessByGroupId(adminUser.getGroupId());
        
        if ("admin".equals(adminUser.getUsername())) {
        	
        	authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        
        if (roles.size() > 0) {
        	
        	Iterator<String> iroles = roles.iterator();
        	while(iroles.hasNext()) {

        		String role = iroles.next();
        		
        		System.out.println("AdminUserService:" + role);
        		
        		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        	}
        } else {
        	
        	authorities.add(new SimpleGrantedAuthority("ROLE_NO"));
        }
        
        return authorities;
    }
}