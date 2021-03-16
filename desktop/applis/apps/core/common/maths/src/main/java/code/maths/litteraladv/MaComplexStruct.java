package code.maths.litteraladv;

import code.maths.Complex;
import code.maths.Rate;

public final class MaComplexStruct implements MaStruct {
    private final Complex complex;

    public MaComplexStruct(Complex _complex) {
        complex = _complex;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqRate(this, _other);
    }

    @Override
    public String displayRsult() {
        Rate imag_ = complex.getImag();
        if (imag_.isZero()) {
            return complex.getReal().toNumberString();
        }
        return "("+complex.getReal().toNumberString()+","+ imag_.toNumberString()+",-)";
    }

    static MaComplexStruct wrapOrNull(MaStruct _cur) {
        if (_cur instanceof MaComplexStruct) {
            return (MaComplexStruct) _cur;
        }
        if (_cur instanceof MaRateStruct) {
            return new MaComplexStruct(new Complex(((MaRateStruct)_cur).getRate()));
        }
        return null;
    }
    static boolean eqRate(MaComplexStruct _this, MaStruct _other) {
        MaComplexStruct wr_ = wrapOrNull(_other);
        if (wr_ == null) {
            return false;
        }
        return Complex.eq(_this.complex, wr_.complex);
    }

    public Complex getComplex() {
        return complex;
    }
}
