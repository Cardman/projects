package code.maths.matrix;
import code.maths.Rate;
import code.util.EqList;
import code.util.PairEq;
import code.util.PairNumber;

public final class Trigonal {

    private final EqList<PairEq<Rate,PairNumber<Integer,Integer>>> rates;
    private final Diagonal diagonal;
    public Trigonal(EqList<PairEq<Rate,PairNumber<Integer,Integer>>> _rates,
            Diagonal _diagonal) {
        rates = _rates;
        diagonal = _diagonal;
    }

    public EqList<PairEq<Rate,PairNumber<Integer,Integer>>> getRates() {
        return rates;
    }

    public Diagonal getDiagonal() {
        return diagonal;
    }
}
