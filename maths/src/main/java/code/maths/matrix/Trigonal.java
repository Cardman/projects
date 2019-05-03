package code.maths.matrix;
import code.maths.Rate;
import code.util.CustList;
import code.util.PairEq;
import code.util.PairNumber;

public final class Trigonal {

    private final CustList<PairEq<Rate,PairNumber<Integer,Integer>>> rates;
    private final Diagonal diagonal;
    public Trigonal(CustList<PairEq<Rate,PairNumber<Integer,Integer>>> _rates,
            Diagonal _diagonal) {
        rates = _rates;
        diagonal = _diagonal;
    }

    public CustList<PairEq<Rate,PairNumber<Integer,Integer>>> getRates() {
        return rates;
    }

    public Diagonal getDiagonal() {
        return diagonal;
    }
}
