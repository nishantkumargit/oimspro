package com.pragya.oimspro.role.service.impl;

import com.pragya.oimspro.role.dto.RoleUpdateRequest;
import com.pragya.oimspro.role.service.RoleUpdationService;
import com.pragya.oimspro.user.entity.Role;
import com.pragya.oimspro.user.entity.User;
import com.pragya.oimspro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUpdationServiceImpl implements RoleUpdationService {
    @Autowired
    private UserService userService;
    @Override
    public String updateRole(RoleUpdateRequest roleUpdateRequest) {
        User user = userService.getUserByEmailId(roleUpdateRequest.getUserEmail());
        user.setRole(Role.valueOf(roleUpdateRequest.getRoleName()));
        userService.updateUser(user);
        return "Role for user " + user.getEmailId() + " updated successfully from "+ user.getRole().name() +"to"+   roleUpdateRequest.getRoleName() + " role.";
    }
}
