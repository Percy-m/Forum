/*
    DBMS: postgres
    encoding: utf-8
*/

DROP TABLE IF EXISTS "account" CASCADE;
DROP TABLE IF EXISTS "topic" CASCADE;
DROP TABLE IF EXISTS "post" CASCADE;

/*
    "account": 用户
*/
CREATE TABLE "account" (
    "id"            UUID                        NOT NULL,   -- 用户id
    "username"      CHARACTER VARYING (20)      NOT NULL,   -- 用户昵称
    "password"      CHARACTER (60)              NOT NULL,   -- 用户密码，bcrypt加密
    "role"          SMALLINT                    NOT NULL,   -- 角色：普通用户(0)，管理员(1)
    "is_locked"     BOOLEAN                     NOT NULL,   -- 是否被管理员锁定

    PRIMARY KEY ("id"),
    UNIQUE ("username"),
    CHECK ("role" IN (0, 1))
);

/*
    "topic": 主题帖
*/
CREATE TABLE "topic" (
    "id"            UUID                        NOT NULL,   -- 主题帖id
    "title"         CHARACTER VARYING (20)      NOT NULL,   -- 主题帖标题
    "owner_id"      UUID                        NOT NULL,   -- 发帖人
    "content"       CHARACTER VARYING (500)     NOT NULL,   -- 内容
    "clicks"        INTEGER                     NOT NULL,   -- 点击量
    "time"          TIMESTAMP                   NOT NULL,   -- 发帖时间
    "is_topped"     BOOLEAN                     NOT NULL,   -- 是否被置顶

    PRIMARY KEY ("id"),
    FOREIGN KEY ("owner_id") REFERENCES "account"("id")
        ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE ("title", "owner_id")
);

/*
    "post": 回复贴
*/
CREATE TABLE "post" (
    "id"            UUID                        NOT NULL,   -- 回复贴id
    "owner_id"      UUID                        NOT NULL,   -- 回复人
    "topic_id"      UUID                        NOT NULL,   -- 所属主题帖
    "content"       CHARACTER VARYING (200)     NOT NULL,   -- 内容
    "time"          TIMESTAMP                   NOT NULL,   -- 回复时间

    PRIMARY KEY ("id"),
    FOREIGN KEY ("owner_id") REFERENCES "account"("id")
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY ("topic_id") REFERENCES "topic"("id")
        ON DELETE CASCADE ON UPDATE CASCADE
);