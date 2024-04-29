package com.pragya.oimspro.role.service;

import com.pragya.oimspro.role.dto.RoleUpdateRequest;
import com.pragya.oimspro.user.entity.User;

public interface RoleUpdationService {
    String updateRole(RoleUpdateRequest roleUpdaterequest);
}
