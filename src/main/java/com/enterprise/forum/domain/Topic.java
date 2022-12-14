package com.enterprise.forum.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@Entity
@Table(name = "topic")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Topic implements Serializable {

    @Id
    @Column(name = "id",
            columnDefinition = "uuid",
            nullable = false)
    private Long id;

    @Column(name = "title",
            columnDefinition = "character varying (20)",
            nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "owner_id",
            columnDefinition = "uuid",
            nullable = false,
            foreignKey = @ForeignKey(foreignKeyDefinition =
                    "foreign key (owner_id) references account(id)" +
                            " on update cascade on delete cascade"))
    private Account account;

    @Column(name = "content",
            columnDefinition = "character varying (500)",
            nullable = false)
    private String content;

    @Column(name = "replies",
            columnDefinition = "integer",
            nullable = false)
    private Integer replies;

    @Column(name = "clicks",
            columnDefinition = "integer",
            nullable = false)
    private Integer clicks;

    @Column(name = "time",
            columnDefinition = "timestamp",
            nullable = false)
    private LocalDateTime time;

    @Column(name = "is_topped",
            columnDefinition = "boolean",
            nullable = false)
    private Boolean topped;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private List<Post> postList = new ArrayList<>();

    @Serial
    private static final long serialVersionUID = 1L;

    public static Topic of(long id, String title, Account account, String content, LocalDateTime time) {

        return new Topic(
                id,
                title,
                account,
                content,
                0,
                0,
                time,
                false,
                new ArrayList<>());
    }
}
