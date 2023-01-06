package com.enterprise.forum.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@Data
public class CommonVO implements Serializable {

    @JsonIgnore
    private Boolean success;

    private Integer status;

    private String message;

    private Object data;

    private CommonVO(boolean success, HttpStatus status, String message, Object data) {

        this.success = success;
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    private static CommonVO success(HttpStatus status, String message, Object data) {

        return new CommonVO(true, status, message, data);
    }

    private static CommonVO failure(HttpStatus status, String message) {

        return new CommonVO(false, status, message, null);
    }

    public static CommonVO ok(Object data) {

        return success(HttpStatus.OK, "success", data);
    }
    public static CommonVO ok() {

        return CommonVO.ok(null);
    }

    /**
     * 请求成功，并因此创建了一个新的资源。
     * 这通常是在<code>POST</code>请求或一些<code>PUT</code>请求之后发送的响应。
     *
     * @return common result view object
     */
    public static CommonVO created(Object data) {

        return success(HttpStatus.CREATED, "created", data);
    }

    public static CommonVO created() {

        return created(null);
    }

    public static CommonVO error(HttpStatus status, String message) {

        return failure(status, message);
    }

    public static CommonVO error(int status, String message) {

        return error(HttpStatus.valueOf(status), message);
    }

    /**
     * 由于被认为是客户端错误（例如，错误的请求语法、无效的请求消息帧或欺骗性的请求路由），服务器无法或不会处理请求。
     *
     * @param message error message
     * @return common result view object
     */
    public static CommonVO badRequest(String message) {

        return CommonVO.error(HttpStatus.BAD_REQUEST, message);
    }

    /**
     * 客户端必须对自身进行身份验证才能获得请求的响应。
     *
     * @param message error message
     * @return common result view object
     */
    public static CommonVO unauthorized(String message) {

        return CommonVO.error(HttpStatus.UNAUTHORIZED, message);
    }

    /**
     * 客户端没有访问内容的权限；也就是说，它是未经授权的，因此服务器拒绝提供请求的资源。
     *
     * @param message error message
     * @return common result view object
     */
    public static CommonVO forbidden(String message) {

        return CommonVO.error(HttpStatus.FORBIDDEN, message);
    }

    /**
     * 服务器找不到请求的资源。在浏览器中，这意味着无法识别 URL。在 API 中，这也可能意味着端点有效，但资源本身不存在。
     *
     * @param message error message
     * @return common result view object
     */
    public static CommonVO notFound(String message) {

        return CommonVO.error(HttpStatus.NOT_FOUND, message);
    }

}
