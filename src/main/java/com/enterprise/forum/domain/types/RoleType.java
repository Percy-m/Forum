package com.enterprise.forum.domain.types;

import jakarta.persistence.Enumerated;
import jakarta.persistence.MapKeyEnumerated;

/**
 * @author Jiayi Zhu
 * 2022/12/14
 */
public enum RoleType {

    User(0),

    Admin(1);


    private final Integer value;

    RoleType(Integer value) {

        this.value = value;
    }
}
