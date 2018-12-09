package aiki.beans.facade.comparators;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.util.TypesDuo;
import code.util.StringMap;
import code.util.ints.Comparing;

public final class ComparatorTypesDuo implements Comparing<TypesDuo> {

    private boolean translate;

    private DataBase data;

    private String language;

    private boolean reverse;

    public ComparatorTypesDuo(DataBase _data, String _language, boolean _translate) {
        data = _data;
        language = _language;
        translate = _translate;
    }

    public ComparatorTypesDuo(DataBase _data, String _language, boolean _translate, boolean _reverse) {
        this(_data, _language, _translate);
        reverse = _reverse;
    }

    @Override
    public int compare(TypesDuo _o1, TypesDuo _o2) {
        if (translate) {
            if (reverse) {
                int cmp_ = _o1.getPokemonType().compareTo(_o2.getPokemonType());
                if (cmp_ != 0) {
                    return cmp_;
                }
                return _o1.getDamageType().compareTo(_o2.getDamageType());
            }
            int cmp_ = _o1.getDamageType().compareTo(_o2.getDamageType());
            if (cmp_ != 0) {
                return cmp_;
            }
            return _o1.getPokemonType().compareTo(_o2.getPokemonType());
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
        int res_ = ComparatorTrStrings.compare(translatedTypesCmp_, _o1.getDamageType(), _o2.getDamageType());
        if (res_ != 0) {
            return res_;
        }
        return ComparatorTrStrings.compare(translatedTypesCmp_, _o1.getPokemonType(), _o2.getPokemonType());
    }

}