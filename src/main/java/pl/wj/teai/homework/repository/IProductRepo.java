package pl.wj.teai.homework.repository;

import pl.wj.teai.homework.model.Product;

import java.util.Set;

public interface IProductRepo {

    Set<Product> getAllProducts();

    void addProduct(Product product);

    void create5Products();
}
