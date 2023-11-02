package eco.com.spring.mcsv.feddex.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EcommerceResponse{

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("brand_id")
    private Long brandId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("price_list")
    private Long priceList;

    private Double price;

    public EcommerceResponse(Long productId, Long brandId, Date startDate, Long priceList, Double price) {
        this.productId = productId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.priceList = priceList;
        this.price = price;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
