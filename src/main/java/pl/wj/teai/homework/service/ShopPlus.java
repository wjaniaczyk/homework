package pl.wj.teai.homework.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.wj.teai.homework.repository.IProductRepo;
import pl.wj.teai.homework.repository.ProductRepoImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Profile("plus")
public class ShopPlus extends ShopStart implements IShop {

    @Value("${shop-info.vat}")
    private int vat;

    public ShopPlus(IProductRepo iProductRepo) {
        super(iProductRepo);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Sum with " + vat + "% vat: " + calculateVat());
    }

    private BigDecimal calculateVat(){
        BigDecimal sum = sum();
        BigDecimal vatBD = new BigDecimal(vat);
        BigDecimal sumMultipliedByVat = sum.multiply(vatBD.divide(new BigDecimal(100),2 , RoundingMode.HALF_EVEN));
        BigDecimal sumWithVat = sum.add(sumMultipliedByVat);
        return sumWithVat.setScale(2, RoundingMode.HALF_EVEN);
    }
}
