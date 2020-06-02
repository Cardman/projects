package code.maths.matrix;
import code.util.CustList;

public final class Trigonal {

    private final CustList<EigenValue> rates;
    private final Diagonal diagonal;
    public Trigonal(CustList<EigenValue> _rates,
            Diagonal _diagonal) {
        rates = _rates;
        diagonal = _diagonal;
    }

    public CustList<EigenValue> getRates() {
        return rates;
    }

    public Diagonal getDiagonal() {
        return diagonal;
    }
}
