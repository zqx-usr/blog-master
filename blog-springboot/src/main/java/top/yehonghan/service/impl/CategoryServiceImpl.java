package top.yehonghan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.yehonghan.dto.CategoryBackDTO;
import top.yehonghan.dto.CategoryDTO;
import top.yehonghan.dto.CategoryOptionDTO;
import top.yehonghan.entity.Article;
import top.yehonghan.entity.Category;
import top.yehonghan.exception.BizException;
import top.yehonghan.mapper.ArticleMapper;
import top.yehonghan.service.CategoryService;
import top.yehonghan.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import top.yehonghan.util.BeanCopyUtils;
import top.yehonghan.util.PageUtils;
import top.yehonghan.vo.CategoryVO;
import top.yehonghan.vo.ConditionVO;
import top.yehonghan.vo.PageResult;

import java.util.List;
import java.util.Objects;

/**
* @author 86152
* @description 针对表【tb_category】的数据库操作Service实现
* @createDate 2023-01-05 11:02:01
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageResult<CategoryDTO> listCategories() {
        return new PageResult<>(categoryMapper.listCategoryDTO(), Math.toIntExact(categoryMapper.selectCount(null)));
    }

    @Override
    public PageResult<CategoryBackDTO> listBackCategories(ConditionVO condition) {
        // 查询分类数量
        Integer count = Math.toIntExact(categoryMapper.selectCount(new LambdaQueryWrapper<Category>()
                .like(StringUtils.isNotBlank(condition.getKeywords()), Category::getCategoryName, condition.getKeywords())));
        if (count == 0) {
            return new PageResult<>();
        }
        // 分页查询分类列表
        List<CategoryBackDTO> categoryList = categoryMapper.listCategoryBackDTO(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        return new PageResult<>(categoryList, count);
    }

    @Override
    public List<CategoryOptionDTO> listCategoriesBySearch(ConditionVO condition) {
        // 搜索分类
        List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .like(StringUtils.isNotBlank(condition.getKeywords()), Category::getCategoryName, condition.getKeywords())
                .orderByDesc(Category::getId));
        return BeanCopyUtils.copyList(categoryList, CategoryOptionDTO.class);
    }

    @Override
    public void deleteCategory(List<Integer> categoryIdList) {
        // 查询分类id下是否有文章
        Integer count = Math.toIntExact(articleMapper.selectCount(new LambdaQueryWrapper<Article>()
                .in(Article::getCategoryId, categoryIdList)));
        if (count > 0) {
            throw new BizException("删除失败，该分类下存在文章");
        }
        categoryMapper.deleteBatchIds(categoryIdList);
    }

    @Override
    public void saveOrUpdateCategory(CategoryVO categoryVO) {
        // 判断分类名重复
        Category existCategory = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .select(Category::getId)
                .eq(Category::getCategoryName, categoryVO.getCategoryName()));
        if (Objects.nonNull(existCategory) && !existCategory.getId().equals(categoryVO.getId())) {
            throw new BizException("分类名已存在");
        }
        Category category = Category.builder()
                .id(categoryVO.getId())
                .categoryName(categoryVO.getCategoryName())
                .build();
        this.saveOrUpdate(category);
    }
}




