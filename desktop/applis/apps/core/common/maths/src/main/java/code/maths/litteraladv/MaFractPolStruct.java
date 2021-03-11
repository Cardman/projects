package code.maths.litteraladv;

import code.maths.matrix.FractPol;
import code.util.CollCapacity;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaFractPolStruct implements MaStruct {
    private final FractPol fractPol;

    public MaFractPolStruct(FractPol _fractPol) {
        fractPol = _fractPol;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        return eqFractPol(this, _other);
    }

    @Override
    public String displayRsult() {
        if (fractPol.isInteger()) {
            return MaPolynomStruct.displayRsult(fractPol.getNumerator());
        }
        StringList list_ = new StringList(new CollCapacity(2));
        list_.add(MaPolynomStruct.displayRsult(fractPol.getNumerator()));
        list_.add(MaPolynomStruct.displayRsult(fractPol.getDenominator()));
        return StringUtil.join(list_,":");
    }

    public static boolean eqFractPol(MaFractPolStruct _this, MaStruct _other) {
        MaFractPolStruct wrap_ = wrapOrNull(_other);
        if (wrap_ == null) {
            return false;
        }
        return _this.fractPol.eq(wrap_.fractPol);
    }
    static MaFractPolStruct wrapOrNull(MaStruct _pol) {
        if (_pol instanceof MaPolynomStruct) {
            return wrap((MaPolynomStruct) _pol);
        }
        if (_pol instanceof MaFractPolStruct) {
            return (MaFractPolStruct) _pol;
        }
        return null;
    }

    static MaFractPolStruct wrap(MaPolynomStruct _pol) {
        return new MaFractPolStruct(new FractPol(_pol.getPolynom()));
    }

    public FractPol getFractPol() {
        return fractPol;
    }
}
