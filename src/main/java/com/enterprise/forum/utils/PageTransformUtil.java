package com.enterprise.forum.utils;

import com.enterprise.forum.dto.PageDTO;
import com.enterprise.forum.vo.PageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.function.Function;

/**
 * @author Jiayi Zhu
 * 12/27/2022
 */
public class PageTransformUtil {

    public static <T, R> PageVO<R> toViewPage(
            PageDTO pageDTO,
            Function<Pageable, Page<T>> function,
            Function<T, R> mapper
    ) {
        // Page<T> 's pageNumber starts from 0
        int page = pageDTO.getPage() - 1;
        int pageSize = pageDTO.getPageSize();

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<R> rPage = function.apply(pageable).map(mapper);

        return new PageVO<>(
                rPage.getContent(),
                rPage.getNumber() + 1,
                rPage.getSize(),
                rPage.getTotalPages(),
                rPage.hasNext(),
                rPage.hasPrevious(),
                rPage.isFirst(),
                rPage.isLast());
    }

}
