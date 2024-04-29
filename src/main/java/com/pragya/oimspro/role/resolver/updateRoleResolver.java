package com.pragya.oimspro.role.resolver;


import com.pragya.oimspro.role.dto.RoleUpdateRequest;
import com.pragya.oimspro.role.service.RoleUpdationService;
import com.pragya.oimspro.user.entity.User;
import com.pragya.oimspro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
public class updateRoleResolver {
    @Autowired
    private RoleUpdationService roleUpdationService;
    @PostMapping("/updateRole")
    public String updateRole(@RequestBody RoleUpdateRequest roleUpdateRequest) {
        return roleUpdationService.updateRole(roleUpdateRequest);
    }
}
