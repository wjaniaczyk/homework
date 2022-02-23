package pl.wj.teai.homework.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.wj.teai.homework.model.Product;
import pl.wj.teai.homework.util.BigDecimalGenerator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Repository
@Primary
public class ProductRepoImpl implements IProductRepo{

    private Set<Product> products;
    private BigDecimalGenerator bigDecimalGenerator;

    @Autowired
    public ProductRepoImpl(BigDecimalGenerator bigDecimalGenerator) {
        this.bigDecimalGenerator = bigDecimalGenerator;
        this.products = new HashSet<>();
        create5Products();
    }

    public Set<Product> getAllProducts() {
        return products;
    }

    private void create5Products(){
        BigDecimal[] prices = {
                new BigDecimal("110.17"),
                new BigDecimal("201.06"),
                new BigDecimal("259.17"),
                new BigDecimal("187.06"),
                new BigDecimal("59.26")};
        Product product;
        for (int i = 1; i < 6; i++) {
            product = new Product(i, "product_" + i, prices[i-1]);
            addProduct(product);
        }
    }

    public void addProduct(Product product){
        products.add(product);
    }


}
