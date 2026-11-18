package mattresssales;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SalesCalculationsTest {

    @Test
    public void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        int[][] testSales = {{10}, {20}, {5}};
        Product product = new Product(testSales);
        int result = product.GetTotalProductsSold();
        assertEquals(35, result);
    }

    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        int[][] testSales = {{10}, {10}, {10}};
        Product product = new Product(testSales);
        double result = product.GetAverageProductSold();
        assertEquals(10.0, result, 0.01);
    }

    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calc = new ProductCalculations(3500.0);
        double result = calc.GetVat();
        assertEquals(525.0, result, 0.01); 
    }

    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calc = new ProductCalculations(3500.0);
        double result = calc.GetTotal();
        assertEquals(4200.0, result, 0.01);
    }
}