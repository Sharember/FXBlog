package cn.fanhub.fxblogui.util;

import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

/**
 * @author chengfan
 * @date 2018-2-18 11:14:24
 */
public class PageUtil {
    public static List<Long> getPageArticleList(List<Long> articles, PageRequest pageRequest, int total) {
        int page = pageRequest.getPageNumber();
        int size = pageRequest.getPageSize();

        int start = page * size;
        int end =  (page + 1) * size + 1;

        if (start > total) {
            return Collections.emptyList();
        }

        if (end > total) {
            return articles.subList(start, total);
        }

        return articles.subList(start, end - 1);
    }
    //
    //public static void main(String[] args) {
    //    for (Long aLong : PageUtil.getPageArticleList(
    //            Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L),
    //            new PageRequest(1, 10), 21)) {
    //        System.out.println(aLong);
    //
    //    }
    //}
}