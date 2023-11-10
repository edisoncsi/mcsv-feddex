package eco.com.spring.mcsv.feddex.controllers;

import eco.com.spring.mcsv.feddex.dtos.FindData;
import eco.com.spring.mcsv.feddex.models.entity.Brand;
import eco.com.spring.mcsv.feddex.models.entity.Price;
import eco.com.spring.mcsv.feddex.repositories.EcommerceRepository;
import eco.com.spring.mcsv.feddex.services.EcommerceService;
import eco.com.spring.mcsv.feddex.services.EcommerceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author edisoncsi on 2/11/23
 * @project mcsv-feddex
 */

class EcommerceControllerTest {

    @Mock
    private EcommerceService ecommerceService ;

    @Mock
    private EcommerceRepository ecommerceRepository;


    @InjectMocks
    private EcommerceController ecommerceController;

    @InjectMocks
    private EcommerceServiceImpl ecommerceServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        // Mocking the service method to return a list of Prices
        List<Price> mockPrices = Arrays.asList(
                createMockPrice(1L, new Brand(), new Date(), new Date(), 1L, 1L, 1L, 9.99, "USD"),
                createMockPrice(2L, new Brand(), new Date(), new Date(), 2L, 2L, 2L, 19.99, "EUR")
                // Add more sample Price objects as needed for testing different scenarios
        );
        when(ecommerceService.listar()).thenReturn(mockPrices);

        // Calling the endpoint to get ResponseEntity
        ResponseEntity<?> responseEntity = ecommerceController.listar();

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue()); // Assuming 200 for OK status
        assertEquals(mockPrices, responseEntity.getBody()); // Check if the returned list matches the mock data
    }


    @Test
    public void testListarTest() {
        // Create sample data for the test
        List<Price> mockPrices = Arrays.asList(
                createMockPrice(1L, new Brand(), new Date(), new Date(), 1L, 1L, 1L, 9.99, "USD"),
                createMockPrice(2L, new Brand(), new Date(), new Date(), 2L, 2L, 2L, 19.99, "EUR")
                // Add more sample Price objects as needed for testing different scenarios
        );

        // Mock the behavior of the repository
        when(ecommerceRepository.findAll()).thenReturn(mockPrices);

        // Call the method to test
        List<Price> result = ecommerceServiceImpl.listar();
        assertEquals(2,result.size());
    }

    @Test
    void testListProducts() {
        // Prepare sample FindData
        FindData findData = new FindData(new Date(), 1L, 1L);

        // Mocking the service to return a list of prices
        Price mockedPrice = new Price();
        Brand mockedBrand = new Brand(); // Create a mock Brand object
        mockedBrand.setId(1L); // Set an ID for the Brand object
        mockedPrice.setBrandId(mockedBrand); // Set the Brand object in the Price object
        List<Price> mockPriceList = Collections.singletonList(mockedPrice);

        when(ecommerceService.listarProduct(any(FindData.class))).thenReturn(mockPriceList);

        // Mock BindingResult
        BindingResult mockBindingResult = org.mockito.Mockito.mock(BindingResult.class);

        // Call the endpoint with the mocked BindingResult
        ResponseEntity<?> response = ecommerceController.listProducts(findData, mockBindingResult);

        // Assert that the response is OK and contains the expected list of EcommerceResponse objects
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPriceList.size(), ((List<?>) response.getBody()).size()); // Assuming conversion to EcommerceResponse
    }


    @Test
    public void testValidator() {
        // Mocking BindingResult
        BindingResult bindingResult = mock(BindingResult.class);

        // Mock field error
        FieldError fieldError = new FieldError("objectName", "fieldName", "error message");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        // Call the validator method
        ResponseEntity<Map<String, String>> responseEntity = ecommerceController.validator(bindingResult);

        // Validate the response
        assertEquals(400, responseEntity.getStatusCodeValue()); // 400 is the status code for bad request
        assertEquals("El campo fieldName error message", responseEntity.getBody().get("fieldName"));
    }


    @Test
    public void testListProductsWithErrors() {
        // Mocking BindingResult
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(new FieldError("objectName", "fieldName", "Error message")));

        FindData findData = new FindData(new Date(), 1L, 1L);
        // Call the endpoint with the mocked BindingResult
        ResponseEntity<?> response = ecommerceController.listProducts(findData, bindingResult);

        // Call the validator method
        ResponseEntity<Map<String, String>> responseEntity = ecommerceController.validator(bindingResult);

        // Validate the response
        Map<String, String> expectedErrors = Collections.singletonMap("fieldName", "El campo fieldName Error message");
        assertEquals(expectedErrors, responseEntity.getBody());
        assertEquals(400, response.getStatusCodeValue()); // 404 is the status code for bad request

    }


    // Helper method to create a mock Price object
    private Price createMockPrice(long id, Brand brandId, Date startDate, Date endDate, Long priceList,
                                  Long productId, Long priority, Double price, String curr) {
        Price prices = new Price();
        prices.setId(id);
        prices.setBrandId(brandId);
        prices.setStartDate(startDate);
        prices.setEndDate(endDate);
        prices.setPriceList(priceList);
        prices.setProductId(productId);
        prices.setPriority(priority);
        prices.setPrice(price);
        prices.setCurr(curr);
        return prices;
    }

}