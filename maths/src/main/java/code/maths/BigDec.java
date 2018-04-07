package code.maths;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import code.sml.FromAndToString;
import code.util.CustList;
import code.util.GenericNumbers;
import code.util.ints.Cmp;
import code.util.ints.Displayable;

public final class BigDec implements Cmp<BigDec>, Displayable {

    private static final String DOTTED_TENTH = "0.1";
    private static final int ROUNDING_MODE = BigDecimal.ROUND_HALF_DOWN;
    private static final int DEFAULT_DIGITS = 64;
    private static final int SCALE = DEFAULT_DIGITS;

    private final BigDecimal number;

    public BigDec(BigDecimal _number) {
        number = _number;
    }

    public BigDec(Rate _r) {
        this(_r, DEFAULT_DIGITS);
    }

    public BigDec(Rate _r, int _numberDec) {
        number = new BigDecimal(_r.evaluatePoint(_numberDec), MathContext.UNLIMITED);
    }

    public BigDec(String _arg) {
        number = new BigDecimal(_arg, MathContext.UNLIMITED);
    }

    @FromAndToString
    public static BigDec newBigDec(String _arg) {
        return new BigDec(_arg);
    }

    @Override
    public int cmp(BigDec _o) {
        return number.compareTo(_o.number);
    }

    public byte byteValue() {
        return number.byteValue();
    }

    public short shortValue() {
        return number.shortValue();
    }

    public BigDec add(BigDec _augend) {
        return new BigDec(number.add(_augend.number));
    }

    public BigDec add(BigDec _augend, MathContext _mc) {
        return new BigDec(number.add(_augend.number, _mc));
    }

    public BigDec subtract(BigDec _subtrahend) {
        return new BigDec(number.subtract(_subtrahend.number));
    }

    public BigDec subtract(BigDec _subtrahend, MathContext _mc) {
        return new BigDec(number.subtract(_subtrahend.number, _mc));
    }

    public BigDec multiply(BigDec _multiplicand) {
        return new BigDec(number.multiply(_multiplicand.number));
    }

    public BigDec multiply(BigDec _multiplicand, MathContext _mc) {
        return new BigDec(number.multiply(_multiplicand.number, _mc));
    }

    public BigDec divide(BigDec _divisor, int _scale, int _roundingMode) {
        return new BigDec(number.divide(_divisor.number, _scale, _roundingMode));
    }

    public BigDec divide(BigDec _divisor, int _scale,
            RoundingMode _roundingMode) {
        return new BigDec(number.divide(_divisor.number, _scale, _roundingMode));
    }

    public BigDec divide(BigDec _divisor, int _roundingMode) {
        return new BigDec(number.divide(_divisor.number, _roundingMode));
    }

    public BigDec divide(BigDec _divisor, RoundingMode _roundingMode) {
        return new BigDec(number.divide(_divisor.number, _roundingMode));
    }

    public BigDec divide(BigDec _divisor) {
        return new BigDec(number.divide(_divisor.number));
    }

    public BigDec divide(BigDec _divisor, MathContext _mc) {
        return new BigDec(number.divide(_divisor.number, _mc));
    }

    public BigDec divideToIntegralValue(BigDec _divisor) {
        return new BigDec(number.divideToIntegralValue(_divisor.number));
    }

    public BigDec divideToIntegralValue(BigDec _divisor, MathContext _mc) {
        return new BigDec(number.divideToIntegralValue(_divisor.number, _mc));
    }

    public BigDec remainder(BigDec _divisor) {
        return new BigDec(number.remainder(_divisor.number));
    }

    public BigDec remainder(BigDec _divisor, MathContext _mc) {
        return new BigDec(number.remainder(_divisor.number, _mc));
    }

    public BigDec[] divideAndRemainder(BigDec _divisor) {
        BigDecimal[] big_ = number.divideAndRemainder(_divisor.number);
        int len_ = big_.length;
        BigDec[] ret_ = new BigDec[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            ret_[i] = new BigDec(big_[i]);
        }
        return ret_;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public Rate toRate() {
        BigDecimal[] res_ = number.divideAndRemainder(BigDecimal.ONE, MathContext.UNLIMITED);
        Rate int_ = new Rate(res_[CustList.FIRST_INDEX].toString());
        Rate dec_ = new Rate(res_[CustList.SECOND_INDEX].toString());
        return Rate.plus(int_, dec_);
    }

    public BigDec[] divideAndRemainder(BigDec _divisor, MathContext _mc) {
        BigDecimal[] big_ = number.divideAndRemainder(_divisor.number, _mc);
        int len_ = big_.length;
        BigDec[] ret_ = new BigDec[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            ret_[i] = new BigDec(big_[i]);
        }
        return ret_;
    }

    public BigDec nthRoot(int _n) {
        return nthRoot(_n, new BigDecimal(DOTTED_TENTH).movePointLeft(SCALE));
    }

    public BigDec nthRoot(int _n, BigDecimal _p) {
        if (GenericNumbers.eq(number, BigDecimal.ZERO)) {
            return new BigDec(BigDecimal.ZERO);
        }
        BigDecimal xPrev_ = number;
        BigDecimal n_ = new BigDecimal(_n);
        BigDecimal x_ = number.divide(n_, MathContext.UNLIMITED);
        BigDecimal cst_ = BigDecimal.valueOf(_n - 1.0);
        // starting "guessed" value...
        while (x_.subtract(xPrev_).abs().compareTo(_p) > 0) {
            xPrev_ = x_;
            x_ = cst_.multiply(x_)
                    .add(number.divide(x_.pow(_n - 1), SCALE, ROUNDING_MODE))
                    .divide(n_, SCALE, ROUNDING_MODE);
        }
        return new BigDec(x_);
    }

    public BigDec powInv(int _n) {
        return new BigDec(BigDecimal.ONE.divide(number.pow(_n)));
    }

    public BigDec powInv(int _n, MathContext _mc) {
        return new BigDec(BigDecimal.ONE.divide(number.pow(_n, _mc)));
    }

    public BigDec pow(int _n) {
        return new BigDec(number.pow(_n));
    }

    public BigDec pow(int _n, MathContext _mc) {
        return new BigDec(number.pow(_n, _mc));
    }

    public BigDec abs() {
        return new BigDec(number.abs());
    }

    public BigDec abs(MathContext _mc) {
        return new BigDec(number.abs(_mc));
    }

    public BigDec negate() {
        return new BigDec(number.negate());
    }

    public BigDec negate(MathContext _mc) {
        return new BigDec(number.negate(_mc));
    }

    public BigDec plus() {
        return new BigDec(number.plus());
    }

    public BigDec plus(MathContext _mc) {
        return new BigDec(number.plus(_mc));
    }

    public int signum() {
        return number.signum();
    }

    public int scale() {
        return number.scale();
    }

    public int precision() {
        return number.precision();
    }

    public BigInteger unscaledValue() {
        return number.unscaledValue();
    }

    public BigDec round(MathContext _mc) {
        return new BigDec(number.round(_mc));
    }

    public BigDec setScale(int _newScale, RoundingMode _roundingMode) {
        return new BigDec(number.setScale(_newScale, _roundingMode));
    }

    public BigDec setScale(int _newScale, int _roundingMode) {
        return new BigDec(number.setScale(_newScale, _roundingMode));
    }

    public BigDec setScale(int _newScale) {
        return new BigDec(number.setScale(_newScale));
    }

    public BigDec movePointLeft(int _n) {
        return new BigDec(number.movePointLeft(_n));
    }

    public BigDec movePointRight(int _n) {
        return new BigDec(number.movePointRight(_n));
    }

    public BigDec scaleByPowerOfTen(int _n) {
        return new BigDec(number.scaleByPowerOfTen(_n));
    }

    public BigDec stripTrailingZeros() {
        return new BigDec(number.stripTrailingZeros());
    }

    @Override
    public boolean eq(BigDec _o) {
        return GenericNumbers.eq(number, _o.number);
    }

    public BigDec min(BigDec _val) {
        return new BigDec(number.min(_val.number));
    }

    public BigDec max(BigDec _val) {
        return new BigDec(number.max(_val.number));
    }

    @FromAndToString
    @Override
    public String display() {
        return number.toString();
    }

    public String toEngineeringString() {
        return number.toEngineeringString();
    }

    public String toPlainString() {
        return number.toPlainString();
    }

    public BigInteger toBigInteger() {
        return number.toBigInteger();
    }

    public BigInteger toBigIntegerExact() {
        return number.toBigIntegerExact();
    }

    public long longValue() {
        return number.longValue();
    }

    public long longValueExact() {
        return number.longValueExact();
    }

    public int intValue() {
        return number.intValue();
    }

    public int intValueExact() {
        return number.intValueExact();
    }

    public short shortValueExact() {
        return number.shortValueExact();
    }

    public byte byteValueExact() {
        return number.byteValueExact();
    }

    public float floatValue() {
        return number.floatValue();
    }

    public double doubleValue() {
        return number.doubleValue();
    }

    public BigDec ulp() {
        return new BigDec(number.ulp());
    }

}
