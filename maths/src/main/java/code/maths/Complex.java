package code.maths;
import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.FromAndToString;


public final class Complex implements Equallable<Complex> {

    private static final String SEPARATOR = ":";

    private Rate real;

    private Rate imag;

    public Complex() {
        this(Rate.zero());
    }
    public Complex(Rate _real) {
        this(_real, Rate.zero());
    }
    public Complex(Rate _real, Rate _imag) {
        real = _real;
        imag = _imag;
    }
    public Complex(Complex _c) {
        real = new Rate(_c.real);
        imag = new Rate(_c.imag);
    }

    @FromAndToString
    public static Complex newFract(String _arg) {
        StringList args_ = StringList.splitStrings(_arg, SEPARATOR);
        return new Complex(Rate.newRate(args_.first()), Rate.newRate(args_.last()));
    }

    public Complex conjug() {
        return new Complex(real, imag.opposNb());
    }
    public Rate squareMod() {
        Rate r_ = Rate.plus(Rate.multiply(real, real), Rate.multiply(imag, imag));
        return r_;
    }
    public Complex divide(Complex _o) {
        Rate mod_= _o.squareMod();
        return multiply(new Complex(Rate.divide(_o.real,mod_),Rate.divide(_o.imag,mod_).opposNb()));
    }
    public Complex multiply(Complex _o) {
        Rate r_ = Rate.minus(Rate.multiply(real, _o.real), Rate.multiply(imag, _o.imag));
        Rate i_  = Rate.minus(Rate.multiply(real, _o.imag), Rate.multiply(imag, _o.real));
        return new Complex(r_, i_);
    }
    public Complex minus(Complex _complex) {
        return add(_complex.minus());
    }
    public Complex minus() {
        return new Complex(real.opposNb(), imag.opposNb());
    }
    public Complex add(Complex _complex) {
        return new Complex(Rate.plus(real, _complex.real), Rate.plus(imag, _complex.imag));
    }
    public Rate getReal() {
        return real;
    }

    public Rate getImag() {
        return imag;
    }
    public static boolean eq(Complex _tx1,Complex _tx2) {
        if (_tx1 == null) {
            return _tx2 == null;
        }
        return _tx1.isEqualTo(_tx2);
    }
    @Override
    public boolean eq(Complex _o) {
        if (!Rate.eq(_o.real, real)) {
            return false;
        }
        if (!Rate.eq(_o.imag, imag)) {
            return false;
        }
        return true;
    }

    public boolean isEqualTo(Complex _o) {
        if (!Rate.eq(_o.real, real)) {
            return false;
        }
        if (!Rate.eq(_o.imag, imag)) {
            return false;
        }
        return true;
    }
    @FromAndToString
    @Override
    public String toString() {
        return real+SEPARATOR+imag;
    }
}
