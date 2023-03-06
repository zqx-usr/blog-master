package top.yehonghan.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 页面
 *
 * @TableName tb_page
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_page")
public class Page implements Serializable {
    /**
     * 页面id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 页面名
     */
    @TableField(value = "page_name")
    private String pageName;

    /**
     * 页面标签
     */
    @TableField(value = "page_label")
    private String pageLabel;

    /**
     * 页面封面
     */
    @TableField(value = "page_cover")
    private String pageCover;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Page other = (Page) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getPageName() == null ? other.getPageName() == null : this.getPageName().equals(other.getPageName()))
                && (this.getPageLabel() == null ? other.getPageLabel() == null : this.getPageLabel().equals(other.getPageLabel()))
                && (this.getPageCover() == null ? other.getPageCover() == null : this.getPageCover().equals(other.getPageCover()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPageName() == null) ? 0 : getPageName().hashCode());
        result = prime * result + ((getPageLabel() == null) ? 0 : getPageLabel().hashCode());
        result = prime * result + ((getPageCover() == null) ? 0 : getPageCover().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pageName=").append(pageName);
        sb.append(", pageLabel=").append(pageLabel);
        sb.append(", pageCover=").append(pageCover);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
