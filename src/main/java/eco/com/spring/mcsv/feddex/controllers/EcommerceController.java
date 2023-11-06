package eco.com.spring.mcsv.feddex.controllers;

import eco.com.spring.mcsv.feddex.dtos.FindData;
import eco.com.spring.mcsv.feddex.models.entity.Price;
import eco.com.spring.mcsv.feddex.response.EcommerceResponse;
import eco.com.spring.mcsv.feddex.services.EcommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EcommerceController {

    @Autowired
    private EcommerceService ecommerceService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(ecommerceService.listar());
    }

    @PostMapping
    public ResponseEntity<?> listProducts(@Valid @RequestBody FindData findData, BindingResult result) {

        if (result.hasErrors()) {
            return validator(result);
        }

        List<Price> listPrice = ecommerceService.listarProduct(findData);
        if (listPrice.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<EcommerceResponse> newList = listPrice.stream()
                .map(list -> new EcommerceResponse(
                        list.getProductId(),
                        list.getBrandId().getId(),
                        list.getStartDate(),
                        list.getPriceList(),
                        list.getPrice()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(newList);
    }

    private static ResponseEntity<Map<String, String>> validator(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
