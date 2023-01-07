package com.enterprise.forum.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiayi Zhu
 * 1/1/2023
 */
@Entity
@Table(name = "refresh-token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    @Id
    @Column(name = "id",
            columnDefinition = "bigint",
            nullable = false)
    private Long id;

    @Column(name = "token",
            columnDefinition = "varchar(255)",
            nullable = false)
    private String token;

    public static RefreshToken of(long id, String token) {

        return new RefreshToken(id, token);
    }
}
