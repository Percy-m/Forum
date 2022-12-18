package com.enterprise.forum.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@Entity
@Table(name = "post")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post implements Serializable {

    @Id
    @Column(name = "id",
            columnDefinition = "uuid",
            nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "owner_id",
            columnDefinition = "uuid",
            nullable = false,
            foreignKey = @ForeignKey(foreignKeyDefinition =
                    "foreign key (owner_id) references account(id)" +
                            " on update cascade on delete cascade"))
    private Account account;

    @ManyToOne
    @JoinColumn(name = "topic_id",
            columnDefinition = "uuid",
            nullable = false,
            foreignKey = @ForeignKey(foreignKeyDefinition =
                    "foreign key (topic_id) references topic(id)" +
                            " on update cascade on delete cascade"))
    private Topic topic;

    @Column(name = "content",
            columnDefinition = "character varying (200)",
            nullable = false)
    private String content;

    @Column(name = "time",
            columnDefinition = "timestamp",
            nullable = false)
    private LocalDateTime time;

    @Serial
    private static final long serialVersionUID = 1L;

    public static Post of(Account account, Topic topic, String content, LocalDateTime time) {

        return new Post(
                UUID.randomUUID(),
                account,
                topic,
                content,
                time);
    }

}
