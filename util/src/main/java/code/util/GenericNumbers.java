package code.util;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import code.util.comparators.BigDecimalComparator;
import code.util.ints.Equallable;
import code.util.ints.Listable;

public final class GenericNumbers extends CustList<BigDecimal> implements Equallable<GenericNumbers> {

    public GenericNumbers() {
    }

    public GenericNumbers(Listable<BigDecimal> _list) {
        super(_list);
    }

    public GenericNumbers(BigDecimal... _element) {
        super(_element);
    }

    public GenericNumbers(CollCapacity _capacity) {
        super(_capacity);
    }

    public void sort() {
        sortElts(new BigDecimalComparator());
    }

    public static boolean eq(BigDecimal _one, BigDecimal _two) {
        return _one.equals(_two);
    }

    public void addBigIntCopy(BigInteger _bigInt) {
        add(new BigDecimal(_bigInt, MathContext.UNLIMITED));
    }

    public void addBigIntCopy(int _i, BigInteger _bigInt) {
        add(_i, new BigDecimal(_bigInt, MathContext.UNLIMITED));
    }

    public void setBigIntCopy(int _i,BigInteger _bigInt) {
        set(_i, new BigDecimal(_bigInt, MathContext.UNLIMITED));
    }

    public BigDecimal sumElts() {
        BigDecimal b_ = new BigDecimal(0L, MathContext.UNLIMITED);
        for (BigDecimal e: this) {
            b_ = b_.add(e);
        }
        return b_;
    }

    public BigDecimal prodElts() {
        BigDecimal b_ = new BigDecimal(0L, MathContext.UNLIMITED);
        for (BigDecimal e: this) {
            b_ = b_.multiply(e);
        }
        return b_;
    }

    @Override
    public boolean eq(GenericNumbers _g) {
        if (_g == null) {
            return false;
        }
        int len_ = size();
        if (_g.size() != len_) {
            return false;
        }
        for (int i = FIRST_INDEX; i < len_; i++) {
            BigDecimal e_ = get(i);
            BigDecimal f_ = _g.get(i);
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
