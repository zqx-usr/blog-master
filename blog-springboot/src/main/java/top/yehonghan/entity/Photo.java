package top.yehonghan.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 照片
 * @TableName tb_photo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="tb_photo")
public class Photo implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 相册id
     */
    @TableField(value = "album_id")
    private Integer albumId;

    /**
     * 照片名
     */
    @TableField(value = "photo_name")
    private String photoName;

    /**
     * 照片描述
     */
    @TableField(value = "photo_desc")
    private String photoDesc;

    /**
     * 照片地址
     */
    @TableField(value = "photo_src")
    private String photoSrc;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
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
        Photo other = (Photo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlbumId() == null ? other.getAlbumId() == null : this.getAlbumId().equals(other.getAlbumId()))
            && (this.getPhotoName() == null ? other.getPhotoName() == null : this.getPhotoName().equals(other.getPhotoName()))
            && (this.getPhotoDesc() == null ? other.getPhotoDesc() == null : this.getPhotoDesc().equals(other.getPhotoDesc()))
            && (this.getPhotoSrc() == null ? other.getPhotoSrc() == null : this.getPhotoSrc().equals(other.getPhotoSrc()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlbumId() == null) ? 0 : getAlbumId().hashCode());
        result = prime * result + ((getPhotoName() == null) ? 0 : getPhotoName().hashCode());
        result = prime * result + ((getPhotoDesc() == null) ? 0 : getPhotoDesc().hashCode());
        result = prime * result + ((getPhotoSrc() == null) ? 0 : getPhotoSrc().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
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
        sb.append(", albumId=").append(albumId);
        sb.append(", photoName=").append(photoName);
        sb.append(", photoDesc=").append(photoDesc);
        sb.append(", photoSrc=").append(photoSrc);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
