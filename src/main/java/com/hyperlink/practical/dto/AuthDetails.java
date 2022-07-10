package com.hyperlink.practical.dto;

import com.hyperlink.practical.enums.ERole;

import java.util.List;

public class AuthDetails {

    private Long userId;
    private List<ERole> roleList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ERole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<ERole> roleList) {
        this.roleList = roleList;
    }
}
