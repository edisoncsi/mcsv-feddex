package eco.com.spring.mcsv.feddex.services;

import eco.com.spring.mcsv.feddex.dtos.FindData;
import eco.com.spring.mcsv.feddex.models.entity.Price;
import eco.com.spring.mcsv.feddex.repositories.EcommerceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Service
public class EcommerceServiceImpl implements EcommerceService {

    @Autowired
    private EcommerceRepository ecommerceRepository;
    @Override
    public List<Price> listar() {
        return (List<Price>) ecommerceRepository.findAll();
    }

    @Override
    public List<Price> listarProduct(FindData data) {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("gye/UTC"));
        String strDate = sdf.format(data.getStartDate());

        return ecommerceRepository.findPriceByStartDateAndProductIdAndBrandId(strDate,data.getProductId(),data.getBrandId());
    }


}
