package pl.wj.teai.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.wj.teai.homework.model.Product;
import pl.wj.teai.homework.repository.IProductRepo;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Set;

@Service
@Profile("start")
public class ShopStart implements IShop {

    private IProductRepo iProductRepo;

    @Autowired
    public ShopStart(IProductRepo iProductRepo) {
        this.iProductRepo = iProductRepo;
    }

    public BigDecimal sum(){
        Set<Product> products = iProductRepo.getAllProducts();
        BigDecimal sum = new BigDecimal(0);
        for (Product p : products){
            sum = sum.add(p.getPrice());
        }
        return sum;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public void showInfo() {
        iProductRepo.getAllProducts().stream()
                .sorted(Comparator.comparing(Product::getId))
                .forEach(System.out::println);
        System.out.println("Sum: " + sum());
    }
}
