package com.dh.dentalClinicMVCE.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");
    private final String roleName;
}
