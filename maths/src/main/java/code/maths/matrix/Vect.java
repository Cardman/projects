package code.maths.matrix;
import code.maths.Rate;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.ints.Equallable;
import code.xml.FromAndToString;

public final class Vect implements Equallable<Vect> {

    private static final String SEPARATOR = " ";
    private EqList<Rate> numbers = new EqList<Rate>();

    public Vect() {
    }

    public Vect(Vect _v) {
        affect(_v);
    }

    @FromAndToString
    public static Vect newVect(String _arg) {
        Vect v_ = new Vect();
        for (String s: StringList.splitStrings(_arg, SEPARATOR)) {
            v_.add(new Rate(s));
        }
        return v_;
    }

    public void affect(Vect _o) {
        numbers.clear();
        for (Rate n: _o.numbers) {
            numbers.add(new Rate(n));
        }
    }

    public int size() {
        return numbers.size();
    }

    public Rate get(int _index) {
        return numbers.get(_index);
    }

    public void setNb(int _index, Rate _nb) {
        set(_index, new Rate(_nb));
    }

    public void set(int _index, Rate _nb) {
        numbers.set(_index, _nb);
    }

    public void addNb(Rate _nb) {
        add(new Rate(_nb));
    }

    public void add(Rate _nb) {
        numbers.add(_nb);
    }

    public Rate scale(Vect _v) {
        Rate r_ = Rate.zero();
        int index_ = CustList.FIRST_INDEX;
        for (Rate n: numbers) {
            r_.addNb(Rate.multiply(n, _v.get(index_)));
            index_ ++;
        }
        return r_;
    }

    public Rate square() {
        Rate r_ = Rate.zero();
        for (Rate n: numbers) {
            r_.addNb(Rate.multiply(n, n));
        }
        return r_;
    }

    public boolean isZero() {
        for (Rate n: numbers) {
            if (!n.isZero()) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq(Vect _tx1,Vect _tx2) {
        if (_tx1 == null) {
            return _tx2 == null;
        }
        return _tx1.isEqualTo(_tx2);
    }
    public boolean isEqualTo(Vect _o) {
        return numbers.eq(_o.numbers);
    }

    @Override
    public boolean eq(Vect _o) {
        return numbers.eq(_o.numbers);
    }

    @Override
    @FromAndToString
    public String toString() {
        return numbers.join(SEPARATOR);
    }
}
