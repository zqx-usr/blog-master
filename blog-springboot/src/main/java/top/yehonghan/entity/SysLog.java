package top.yehonghan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ClassName: SysLog
 * Package: top.yehonghan.entity
 * Description: 系统日志实体
 *
 * @Author: yehonghan
 * @Create: 2022/12/10 14:08
 * @Version: V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_log")
@ApiModel(value="SysLog对象", description="系统日志表")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "日志类型")
    private String logType;

    @ApiModelProperty(value = "创建用户编码")
    private String createUserCode;

    @ApiModelProperty(value = "创建用户名称")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "请求URI")
    private String requestUri;

    @ApiModelProperty(value = "请求方式")
    private String requestMethod;

    @ApiModelProperty(value = "请求参数")
    private String requestParams;

    @ApiModelProperty(value = "请求IP")
    private String requestIp;

    @ApiModelProperty(value = "请求服务器地址")
    private String serverAddress;

    @ApiModelProperty(value = "是否异常")
    private String isException;

    @ApiModelProperty(value = "异常信息")
    private String exceptionInfo;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "执行时间")
    private Integer executeTime;

    @ApiModelProperty(value = "用户代理")
    private String userAgent;

    @ApiModelProperty(value = "操作系统")
    private String deviceName;

    @ApiModelProperty(value = "浏览器名称")
    private String browserName;

}
