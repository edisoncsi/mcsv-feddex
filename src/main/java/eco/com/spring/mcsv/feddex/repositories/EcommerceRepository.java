package eco.com.spring.mcsv.feddex.repositories;

import eco.com.spring.mcsv.feddex.models.entity.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EcommerceRepository extends CrudRepository<Price, Long> {

     @Query(value = "SELECT  * FROM Prices u WHERE  SUBSTRING(u.start_date , 0, 10) = SUBSTRING(:startDate , 0, 10) " +
             "and   u.start_date <= :startDate  " +
             "and   u.end_date  >=  :startDate " +
             "and u.product_id = :productId " +
             "and u.brand_id = :brandId " +
             "order by priority desc " +
             "limit 1",nativeQuery = true)
     List<Price> findPriceByStartDateAndProductIdAndBrandId(@Param("startDate") String startDate,
                                                            @Param("productId") Long productId,
                                                            @Param("brandId") Long brandId);
}
