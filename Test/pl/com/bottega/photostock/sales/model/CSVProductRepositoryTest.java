package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.infrastructure.repositories.*;
import pl.com.bottega.photostock.sales.model.repositories.ProductRepository;

public class CSVProductRepositoryTest {

    public static void main(String[] args) {
        ProductRepository productRepository =
                new CSVProductRepository("C:\\Users\\Lenovo\\Desktop\\Bottega\\Photostock\\src\\pl\\com" +
                        "\\bottega\\photostock\\sales\\infrastructure\\repositories\\products.csv",
        new inMemoryClientRepository());
                Product product = productRepository.get(3L);

        System.out.println(product.getNumber());



    }
}
