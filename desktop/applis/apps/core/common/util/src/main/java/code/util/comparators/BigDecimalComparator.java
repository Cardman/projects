package code.util.comparators;
import java.math.BigDecimal;

import code.util.ints.Comparing;

public final class BigDecimalComparator implements Comparing<BigDecimal> {

    @Override
    public int compare(BigDecimal _o1, BigDecimal _o2) {
        return _o1.compareTo(_o2);
    }

}
