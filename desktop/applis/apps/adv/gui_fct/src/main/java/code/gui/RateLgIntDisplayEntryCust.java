package code.gui;

import code.maths.*;

public final class RateLgIntDisplayEntryCust implements DisplayEntryCust<Integer,EditedCrudPair<Rate, LgInt>> {
    @Override
    public String display(Integer _k, EditedCrudPair<Rate,LgInt> _v) {
        return _v.getKey().toNumberString()+":"+_v.getValue().toNumberString();
    }
}
