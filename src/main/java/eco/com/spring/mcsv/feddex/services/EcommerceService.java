package eco.com.spring.mcsv.feddex.services;

import eco.com.spring.mcsv.feddex.dtos.FindData;
import eco.com.spring.mcsv.feddex.models.entity.Price;

import java.util.List;

public interface EcommerceService {

    List<Price> listar();

    List<Price> listarProduct(FindData data);
}
