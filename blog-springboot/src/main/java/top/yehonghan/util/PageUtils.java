package top.yehonghan.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;

/**
 * ClassName: PageUtils
 * Description: 分页工具类
 *
 * @Author: yehonghan
 * @Create: 2023/1/4 10:01
 * @Version: V1.0
 */
public class PageUtils {

    private Integer page;

    private Integer size;

    public Integer getCurrentPage() {
        return this.page == null || this.page == 0 ? 1 : this.page;
    }

    public Integer getPageSize() {
        return this.size == null || this.size == 0 ? 10 : this.size;
    }

    public PageUtils() {}

    public PageUtils(Integer thisPage, Integer thisSize) {
        this.page = thisPage;
        this.size = thisSize;
    }

    private static final ThreadLocal<Page<?>> PAGE_HOLDER = new ThreadLocal<>();

    public static void setCurrentPage(Page<?> page) {
        PAGE_HOLDER.set(page);
    }

    public static Page<?> getPage() {
        Page<?> page = PAGE_HOLDER.get();
        if (Objects.isNull(page)) {
            setCurrentPage(new Page<>());
        }
        return PAGE_HOLDER.get();
    }

    public static Long getCurrent() {
        return getPage().getCurrent();
    }

    public static Long getSize() {
        return getPage().getSize();
    }

    public static Long getLimitCurrent() {
        return (getCurrent() - 1) * getSize();
    }

    public static void remove() {
        PAGE_HOLDER.remove();
    }

}
