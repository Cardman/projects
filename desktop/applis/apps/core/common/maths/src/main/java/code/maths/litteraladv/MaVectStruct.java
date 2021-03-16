package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.matrix.Vect;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaVectStruct extends MaListNbStruct {
    private final Vect vect;

    public MaVectStruct(Vect _vect) {
        this.vect = _vect;
    }

    @Override
    public CustList<Rate> getNumberList() {
        return vect.getNumbers();
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaVectStruct)) {
            return false;
        }
        return vect.eq(((MaVectStruct)_other).vect);
    }

    public Vect getVect() {
        return vect;
    }

    @Override
    public String displayRsult() {
        return displayRsult(vect);
    }

    static String displayRsult(Vect _vect) {
        int size_ = _vect.size();
        StringList elts_ = new StringList(new CollCapacity(size_));
        for (int i = 0; i < size_; i++) {
            elts_.add(_vect.get(i).toNumberString());
        }
        return StringUtil.join(elts_,",");
    }
}
