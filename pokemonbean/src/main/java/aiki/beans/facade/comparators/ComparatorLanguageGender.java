package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.DataBase;
import aiki.beans.help.LanguageElementKey;
import aiki.map.pokemon.enums.Gender;
import code.util.CustList;
import code.util.EnumMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public final class ComparatorLanguageGender implements Comparator<LanguageElementKey> {

    private EnumMap<Gender,String> translatorCurrentLanguage;

    private StringList languages = new StringList();

    public ComparatorLanguageGender(StringMap<EnumMap<Gender,String>> _translatorCurrentLanguage, String _language) {
        translatorCurrentLanguage = _translatorCurrentLanguage.getVal(_language);
        languages.add(_language);
        for (String l: Constants.getAvailableLanguages()) {
            if (StringList.quickEq(l,_language)) {
                continue;
            }
            languages.add(l);
        }
    }

    @Override
    public int compare(LanguageElementKey _o1, LanguageElementKey _o2) {
        Gender keyOne_ = (Gender) _o1.getKey();
        Gender keyTwo_ = (Gender) _o2.getKey();
        int res_ = compare(translatorCurrentLanguage, keyOne_, keyTwo_);
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        String langOne_ = _o1.getLanguage();
        String langTwo_ = _o2.getLanguage();
        return Numbers.compare(languages.indexOfObj(langOne_), languages.indexOfObj(langTwo_));
    }

    private static int compare(EnumMap<Gender,String> _translator, Gender _e1, Gender _e2) {
        String trOne_;
        if (_translator.contains(_e1)) {
            trOne_ = _translator.getVal(_e1);
        } else {
            trOne_ = DataBase.EMPTY_STRING;
        }
        String trTwo_;
        if (_translator.contains(_e2)) {
            trTwo_ = _translator.getVal(_e2);
        } else {
            trTwo_ = DataBase.EMPTY_STRING;
        }
        return trOne_.compareTo(trTwo_);
    }
}