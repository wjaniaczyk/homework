package pl.wj.teai.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.wj.teai.homework.repository.IProductRepo;
import pl.wj.teai.homework.repository.ProductRepoImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Profile("pro")
public class ShopPro extends ShopPlus {

    @Value("${shop-info.discount}")
    private int discount;

    @Autowired
    public ShopPro(IProductRepo iProductRepo) {
        super(iProductRepo);
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Sum with " + discount + "% discount: " + calculateSumWithDiscount());
    }

    private BigDecimal calculateSumWithDiscount() {
        BigDecimal discountInPercent = new BigDecimal(discount).divide(new BigDecimal(100),  2, RoundingMode.HALF_EVEN);
        BigDecimal sum = sum();
        BigDecimal countedDiscount = sum.multiply(new BigDecimal(1).subtract(discountInPercent));
        return countedDiscount.setScale(2, RoundingMode.HALF_EVEN);
    }
}
