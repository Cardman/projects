package code.util.comparators;
import java.math.BigInteger;

import code.util.ints.Comparing;

public final class BigIntegerComparator implements Comparing<BigInteger> {

    @Override
    public int compare(BigInteger _o1, BigInteger _o2) {
        return _o1.compareTo(_o2);
    }

}
