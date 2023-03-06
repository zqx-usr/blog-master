package top.yehonghan.strategy.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.yehonghan.exception.BizException;
import top.yehonghan.service.ArticleService;
import top.yehonghan.strategy.ArticleImportStrategy;
import top.yehonghan.vo.ArticleVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static top.yehonghan.enums.ArticleStatusEnum.DRAFT;

/**
 * ClassName: NormalArticleImportStrategyImpl
 * Description: 普通文章导入策略
 *
 * @Author: yehonghan
 * @Create: 2023/1/6 12:44
 * @Version: V1.0
 */
@Slf4j
@Service("normalArticleImportStrategyImpl")
public class NormalArticleImportStrategyImpl implements ArticleImportStrategy {

    @Autowired
    private ArticleService articleService;

    @Override
    public void importArticles(MultipartFile file) {
        // 获取文件名作为文章标题
        String filename = file.getOriginalFilename();
        if (StringUtils.isBlank(filename)) {
            throw new BizException("文件名不能为空");
        }
        String[] arr = filename.split("\\.");
        String articleTitle = arr[arr.length - 1];
        // 获取文章内容
        StringBuilder articleContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            while (reader.ready()) {
                articleContent.append((char) reader.read());
            }
        } catch (IOException e) {
            log.error(StrUtil.format("导入文章失败, 堆栈:{}", ExceptionUtil.stacktraceToString(e)));
            throw new BizException("导入文章失败");
        }
        // 保存文章
        ArticleVO articleVO = ArticleVO.builder()
                .articleTitle(articleTitle)
                .articleContent(articleContent.toString())
                .status(DRAFT.getStatus())
                .build();
        articleService.saveOrUpdateArticle(articleVO);
    }
}
