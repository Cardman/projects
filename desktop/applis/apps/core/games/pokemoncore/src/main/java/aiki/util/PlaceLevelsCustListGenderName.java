package aiki.util;

import aiki.fight.pokemon.GenderName;
import code.util.CustList;
import code.util.core.StringUtil;

public final class PlaceLevelsCustListGenderName extends PlaceLevels<CustList<GenderName>> {
    public static CustList<GenderName> filter(CustList<GenderName> _list) {
        CustList<GenderName> g_ = new CustList<GenderName>();
        for (GenderName pk_: _list) {
            boolean cont_ = containsGender(g_, pk_);
            if (cont_) {
                continue;
            }
            g_.add(pk_);
        }
        return g_;
    }

    public static boolean containsGender(CustList<GenderName> _list, GenderName _gender) {
        boolean cont_ = false;
        for (GenderName s: _list) {
            if (match(_gender, s)) {
                cont_ = true;
                break;
            }
        }
        return cont_;
    }

    public static boolean match(GenderName _one, GenderName _two) {
        return StringUtil.quickEq(_two.getName(), _one.getName()) && _two.getGender() == _one.getGender();
    }

    @Override
    protected CustList<GenderName> def() {
        return new CustList<GenderName>();
    }
}
