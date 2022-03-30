package pl.wj.teai.homework.util;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BigDecimalGenerator {

    private static  final int MIN_PRICE = 50;
    private static final int MAX_PRICE = 300;

    public BigDecimal getRandomInGivenRange() {
        BigDecimal max = new BigDecimal(MAX_PRICE);
        BigDecimal min = new BigDecimal(MIN_PRICE);
        BigDecimal range = max.subtract(min);
        BigDecimal result = min.add(range.multiply(BigDecimal.valueOf(Math.random())));
        return result.setScale(2, RoundingMode.HALF_EVEN);
    }
}
