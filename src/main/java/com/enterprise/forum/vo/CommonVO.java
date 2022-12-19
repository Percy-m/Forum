package com.enterprise.forum.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonVO implements Serializable {

    @JsonIgnore
    private Boolean success;

    private Integer status;

    private String message;

    private Object data;

    public static CommonVO success(Object data) {

        return new CommonVO(true, 200, "success", data);
    }
    public static CommonVO ok() {

        return CommonVO.success(null);
    }

    public static CommonVO error(Integer status, String message) {

        return new CommonVO(false, status, message, null);
    }

    public static CommonVO error(String message) {

        return CommonVO.error(-1, message);
    }

//    /**
//     * 由于被认为是客户端错误（例如，错误的请求语法、无效的请求消息帧或欺骗性的请求路由），服务器无法或不会处理请求。
//     *
//     * @param message error message
//     * @return common result view object
//     */
//    public static CommonVO badRequest(String message) {
//
//        return CommonVO.error(400, message);
//    }
//
//    /**
//     * 客户端必须对自身进行身份验证才能获得请求的响应。
//     *
//     * @param message error message
//     * @return common result view object
//     */
//    public static CommonVO unauthorized(String message) {
//
//        return CommonVO.error(401, message);
//    }
//
//    /**
//     * 客户端没有访问内容的权限；也就是说，它是未经授权的，因此服务器拒绝提供请求的资源。
//     *
//     * @param message error message
//     * @return common result view object
//     */
//    public static CommonVO forbidden(String message) {
//
//        return CommonVO.error(403, message);
//    }
//
//    /**
//     * 服务器找不到请求的资源。在浏览器中，这意味着无法识别 URL。在 API 中，这也可能意味着端点有效，但资源本身不存在。
//     *
//     * @param message error message
//     * @return common result view object
//     */
//    public static CommonVO notFound(String message) {
//
//        return CommonVO.error(404, message);
//    }

}
