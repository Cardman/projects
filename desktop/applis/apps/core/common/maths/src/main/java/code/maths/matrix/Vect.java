package code.maths.matrix;
import code.maths.Rate;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import code.util.ints.Displayable;

public final class Vect implements Displayable {

    private static final String SEPARATOR = " ";
    private final CustList<Rate> numbers = new CustList<Rate>();

    public Vect() {
    }

    public Vect(Vect _v) {
        affect(_v);
    }

    public Vect(CustList<Rate> _nbs) {
        numbers.addAllElts(_nbs);
    }

    
    public static Vect newVect(String _arg) {
        Vect v_ = new Vect();
        for (String s: StringUtil.splitStrings(_arg, SEPARATOR)) {
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
    public void swapIndexes(int _i, int _j) {
        numbers.swapIndexes(_i,_j);
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
        int index_ = IndexConstants.FIRST_INDEX;
        for (Rate n: numbers) {
            r_.addNb(Rate.multiply(n, _v.get(index_)));
            index_++;
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

    public boolean eq(Vect _o) {
        return Rate.eq(numbers,_o.numbers);
    }

    
    @Override
    public String display() {
        if (numbers.isEmpty()) {
            return "";
        }
        StringBuilder return_ = new StringBuilder(numbers.first().toNumberString());
        int size_ = numbers.size();
        for (int i=1;i<size_;i++) {
            return_.append(SEPARATOR);
            return_.append(numbers.get(i).toNumberString());
        }
        return return_.toString();
    }
}
