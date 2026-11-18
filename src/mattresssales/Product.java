package mattresssales;

public class Product implements IProduct {
    private int[][] salesData; 

    public Product(int[][] salesData) {
        this.salesData = salesData;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int i = 0; i < salesData.length; i++) {
            for (int j = 0; j < salesData[i].length; j++) {
                total += salesData[i][j];
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        int total = GetTotalProductsSold();
        if (salesData.length == 0) return 0;
        return (double) total / salesData.length;
    }

    @Override
    public String GetMostPopularProduct() {
        String[] productNames = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int maxSales = 0;
        int maxIndex = 0;

        for (int i = 0; i < salesData.length; i++) {
            int productTotal = 0;
            for (int j = 0; j < salesData[i].length; j++) {
                productTotal += salesData[i][j];
            }
            if (productTotal > maxSales) {
                maxSales = productTotal;
                maxIndex = i;
            }
        }
        return productNames[maxIndex] + "(" + maxSales + ")";
    }
}