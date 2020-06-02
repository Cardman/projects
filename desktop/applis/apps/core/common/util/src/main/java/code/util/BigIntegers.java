package code.util;
import java.math.BigInteger;

import code.util.comparators.BigIntegerComparator;

public final class BigIntegers extends CustList<BigInteger> {

    public static boolean eq(BigInteger _one, BigInteger _two) {
        return _one.equals(_two);
    }
    public void sort() {
        sortElts(new BigIntegerComparator());
    }

    public BigInteger sumElts() {
        BigInteger b_ = BigInteger.ZERO;
        for (BigInteger e: this) {
            b_ = b_.add(e);
        }
        return b_;
    }

    public BigInteger prodElts() {
        BigInteger b_ = BigInteger.ONE;
        for (BigInteger e: this) {
            b_ = b_.multiply(e);
        }
        return b_;
    }

    public boolean eq(BigIntegers _g) {
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            BigInteger e_ = get(i);
            BigInteger f_ = _g.get(i);
            if (!eq(e_, f_)) {
                return false;
            }
        }
        return true;
    }
}
