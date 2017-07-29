package code.util;
import java.math.BigInteger;

import code.util.annot.CapacityInit;
import code.util.comparators.NaturalComparator;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class BigIntegers extends CustList<BigInteger> implements Equallable<BigIntegers> {

    public BigIntegers() {
    }

    public BigIntegers(BigInteger... _array) {
        super(_array);
    }

    public BigIntegers(Listable<BigInteger> _list) {
        super(_list);
    }

    @CapacityInit
    public BigIntegers(CollCapacity _capacity) {
        super(_capacity);
    }

    public static boolean eq(BigInteger _one, BigInteger _two) {
        return _one.equals(_two);
    }

    public void sortBigIntegers() {
        sortElts(new NaturalComparator<BigInteger>());
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

    @Override
    public boolean eq(BigIntegers _g) {
        if (_g == null) {
            return false;
        }
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            BigInteger e_ = get(i);
            BigInteger f_ = _g.get(i);
            if (e_ == null) {
                if (f_ != null) {
                    return false;
                }
                continue;
            }
            if (f_ == null) {
                return false;
            }
            if (!eq(e_, f_)) {
                return false;
            }
        }
        return true;
    }
}
