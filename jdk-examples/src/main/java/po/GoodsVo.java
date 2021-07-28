package po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsVo {

    //----------------------前端展示字段-------------
    /**
     * 产品id
     */
    private String productId;

    /**
     * 规格id
     */
    private String goodsSpecsId;

    private List<String> productIds;

    /**
     *
     */
    private String goodsSpecsTableId;

    /**
     * 商品Id
     */
    private String goodsId;
    /**
     * 商品编码+商品规格+批次在商城端显示
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品所属类别
     */
    private String categoryId;

    /**
     * 平台名称
     */
    private String platformName;

    /**
     * 业务实体名称
     */
    private String ouName;

    /**
     * 部门名字
     */
    private String deptName;

    /**
     *
     */
    private List<String> userGroupNameList;
    /**
     * 产品组编码
     */
    private String userGroupCode;

    /**
     *大类名称
     */
    private String maxCategoryName;
    /**
     *中类名称
     */
    private String categoryName;
    /**
     *小类名称
     */
    private String subCategoryName;

    /**
     *小类名称
     */
    private String subCategoryCode;
    /**
     *小类名称
     */
    private String maxCategoryCode;
    /**
     *小类名称
     */
    private String categoryCode;
    /**
     * 商品规格
     */
    private String goodsSpecs;



    /**
     * 库存
     */
    private Long stockNum;

    /**
     * 可用库存
     */
    private Long availableNum;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 上下架状态：0：待上架，1：已上架，2：已下架
     */
    private Integer state;

    //----------------------商品编辑时额外展示的字段----------------

    /**
     * 图片库
     */
    private String picAddr;


    /**
     * 图片集合
     */
    private String fileUrlList;
    /**
     * 批次
     */
    private String goodsBatch;

    /**
     * 批次集合
     */
    private String goodsBatchList;

    /**
     * 可用库存
     */
    private String availableStockNum;
    /**
     * 所在产品组的列表
     */
    private List<String> userGroupList;

    /**
     * 规格名集合
     */
    private List<String> goodsSpecsList;


    /**
     * 共享产品组编码列表
     */
    private List<String> sharedUserGroupCodeList;



    /**
     * 保质期
     */

    private String validityTime;




    /**
     * 商品仓库编码
     */

    private String wareHouseCode;
    /**
     * 商品仓库名称
     */

    private String wareHouseName;
    /**
     * 业务实体编码
     */
    private String ouCode;


    /**
     * 生产日期
     */
    private String productionTime;


    /**
     * 产品组名称
     */
    private String userGroupName;


    /**
     * 平台代码
     */
    private String platformCode;

    private String imgAddr;

    private String img;






    /**
     * 最小单位名称
     */
    private String unitName;

    /**
     * 包装单位
     */
    private String cartonUnitName;
    /**
     * 描述
     */
    private String remark;

    /**
     * 图片地址
     */
    private String fileUrl;

    /**
     * pc_detail
     */
    private String pcDetail;

    /**
     * mobile_detail
     */
    private String mobileDetail;






}