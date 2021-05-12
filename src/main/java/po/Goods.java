package po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huchonglin
 * @date 2020/11/24 18:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private String goodsCode;
    private String goodsSpecs;
    private String goodsBatch;
}
