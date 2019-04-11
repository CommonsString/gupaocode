package com.module.security.service;

import com.module.security.units.JwtUser;
import com.module.security.utils.ValidUtils;
import com.module.system.entity.Permission;
import com.module.system.entity.Role;
import com.module.system.entity.User;
import com.module.system.repository.PermissionRepository;
import com.module.system.repository.RoleRepository;
import com.module.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private PermissionRepository permissDao;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if("".equals(username) || username == null) {
            // 异常处理
            return null;
        }
        // 邮箱验证
        User user = null;
        if(ValidUtils.isEmail(username)) {
            user = userDao.findByEmail(username);
        } else {
            user = userDao.findByUsername(username);
        }

        // 判空操作
        if(user == null) {
            throw new RuntimeException("user is null");
        } else {
            return createDetails(user);
        }
    }



    public UserDetails createDetails(User user) {
        JwtUser jwtUser = new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAvatar(),
                user.getEmail(),
                // 获取权限
                mapToGrantedAuthorities(roleDao.findByUsers_Id(user.getId()), permissDao),
                user.getEnabled(),
                user.getCreateTime(),
                user.getLastPasswordResetTime()
        );
        return jwtUser;
    }


    /**
     * 权限封装
     * @param roles
     * @param permissionRepository
     * @return
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> roles, PermissionRepository permissionRepository) {

        // 权限操作
        Set<Permission> permissions = new HashSet<>();
        for (Role role : roles) {
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            permissions.addAll(permissionRepository.findByRoles_Id(role.getId()));
        }

        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
    }






}
