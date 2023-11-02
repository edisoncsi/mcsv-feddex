package eco.com.spring.mcsv.feddex.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "prices")
public class Price implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Brand brandId;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "price_list")
    private Long priceList;
    @Column(name = "product_id")
    private Long productId;

    private Long priority;

    private Double price;

    private String curr;

    public Date getStartDate() {
        return startDate;
    }

    public Long getPriceList() {
        return priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public Double getPrice() {
        return price;
    }

    public Brand getBrandId() {
        return brandId;
    }

}
