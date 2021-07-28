package po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 文件描述:
 * 物料类型Vo
 * @Author: wangqiang
 * @Date: 2020/8/27 19:21
 */
@Data
@EqualsAndHashCode
public class CategoryVo implements Serializable {

    private String id;

    /**
     * 类别编码
     */
    //@NotBlank(message="类别编码不能为空！")
    private String categoryCode;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 状态 0：有效，1：无效
     */
    private Integer state;

    /**
     * 上级
     */
    //@NotBlank(message="上级不能为空！")
    private String parId;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 物料类型 0：非药品， 1：药品类、保健品类
     */
    //@NotNull(message = "物料类型不能为空！")
    private Integer categoryType;

    /**
     * 是否必填保质期 0：是，1：否
     */
    private Integer validityTimeState;

    /**
     * 所属平台
     */
    private String platformCode;

    /**
     * 图片地址
     */
    private String picAddr;
}