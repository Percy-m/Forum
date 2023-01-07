package com.enterprise.forum.component.filter;

import com.enterprise.forum.exception.ForumException;
import com.enterprise.forum.utils.LogUtils;
import com.enterprise.forum.vo.CommonVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Jiayi Zhu
 * 2023/1/7
 */
@Slf4j
@Component
public class ExceptionFilter extends OncePerRequestFilter {

    private ObjectMapper mapper;

    @Autowired
    public void setMapper(ObjectMapper mapper) {

        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        }
        catch (ForumException e) {

            LogUtils.logError(log, e);
            String content = mapper.writeValueAsString(CommonVO.error(e.getStatus(), e.getMessage()));
            response.setContentType("application/json");
            response.getWriter().write(content);
        }
    }

}
