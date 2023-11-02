package eco.com.spring.mcsv.feddex.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonSerialize
public class FindData {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("start_date")
    @NotNull
    private Date startDate;

    @NotNull
    @JsonProperty("product_id")
    private Long productId;

    @NotNull
    @JsonProperty("brand_id")
    private Long brandId;

    public FindData(Date startDate, Long productId, Long brandId) {
        this.startDate = startDate;
        this.productId = productId;
        this.brandId = brandId;
    }

    public Date getStartDate() {
        return startDate;
    }
    public Long getProductId() {
        return productId;
    }
    public Long getBrandId() {
        return brandId;
    }

}
