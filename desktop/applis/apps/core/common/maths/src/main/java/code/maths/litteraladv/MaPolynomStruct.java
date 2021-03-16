package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.matrix.Polynom;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaPolynomStruct implements MaStruct {
    private final Polynom polynom;

    public MaPolynomStruct(Polynom _polynom) {
        polynom = _polynom;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqPolynom(this, _other);
    }

    @Override
    public String displayRsult() {
        return displayRsult(polynom);
    }

    static String displayRsult(Polynom _polynom) {
        CustList<Rate> rates_ = _polynom.getNumbers();
        StringList list_ = new StringList(new CollCapacity(rates_.size()+1));
        for (Rate r: rates_) {
            list_.add(r.toNumberString());
        }
        list_.add(";");
        return "("+ StringUtil.join(list_,",")+")";
    }

    public static boolean eqPolynom(MaPolynomStruct _this, MaStruct _other) {
        return MaFractPolStruct.eqFractPol(MaFractPolStruct.wrap(_this),_other);
    }

    public Polynom getPolynom() {
        return polynom;
    }
}
