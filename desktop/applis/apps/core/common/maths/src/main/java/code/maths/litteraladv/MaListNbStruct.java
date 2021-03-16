package code.maths.litteraladv;

import code.maths.Rate;
import code.util.CustList;

public abstract class MaListNbStruct implements MaAddonStruct {
    @Override
    public boolean sameReferenceMath(MaStruct _other) {
        if (!(_other instanceof MaListNbStruct)) {
            return false;
        }
        MaListNbStruct oth_ = (MaListNbStruct) _other;
        return Rate.eq(getNumberList(),oth_.getNumberList());
    }

    public abstract CustList<Rate> getNumberList();
}
