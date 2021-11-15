package service.product;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> showAllProduct();
    void addProduct(Product product);
    Product selectProduct(int id);
    void updateProduct(int id, Product product);
    void deleteProduct(int id);
    Product findById(int id);
}
