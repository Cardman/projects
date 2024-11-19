package aiki.beans.facade.comparators;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.util.TypesDuo;
import code.util.AbsMap;
import code.util.StringMap;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorTypesDuo implements Comparing<TypesDuo> {

    private final boolean translate;

    private final DataBase data;

    private final String language;

    private final boolean reverse;

    public ComparatorTypesDuo(DataBase _data, String _language, boolean _translate, boolean _reverse) {
        data = _data;
        language = _language;
        translate = _translate;
        reverse = _reverse;
    }

    @Override
    public int compare(TypesDuo _o1, TypesDuo _o2) {
        if (translate) {
            if (reverse) {
                int cmp_ = StringUtil.compareStrings(_o1.getPokemonType(),_o2.getPokemonType());
                if (cmp_ != 0) {
                    return cmp_;
                }
                return StringUtil.compareStrings(_o1.getDamageType(),_o2.getDamageType());
            }
            int cmp_ = StringUtil.compareStrings(_o1.getDamageType(),_o2.getDamageType());
            if (cmp_ != 0) {
                return cmp_;
            }
            return StringUtil.compareStrings(_o1.getPokemonType(),_o2.getPokemonType());
        }
        if (reverse) {
            StringMap<String> translatedTypesCmp_ = data.getTranslatedTypes().getVal(language);
            int res_ = ComparatorTrStrings.compare(translatedTypesCmp_, _o1.getPokemonType(), _o2.getPokemonType());
            if (res_ != 0) {
                return res_;
            }
            return ComparatorTrStrings.compare(translatedTypesCmp_, _o1.getDamageType(), _o2.getDamageType());
        }
        StringMap<String> translatedTypesCmp_ = data.getTranslatedTypes().getVal(language);
        return compareTr(_o1, _o2, translatedTypesCmp_);
    }

    public static int compareTr(TypesDuo _o1, TypesDuo _o2, AbsMap<String,String> _types) {
        int res_ = ComparatorTrStrings.compare(_types, _o1.getDamageType(), _o2.getDamageType());
        if (res_ != 0) {
            return res_;
        }
        return ComparatorTrStrings.compare(_types, _o1.getPokemonType(), _o2.getPokemonType());
    }

}