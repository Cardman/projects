package aiki.beans.facade.comparators;
import code.util.ints.Comparing;

import aiki.DataBase;
import aiki.beans.help.LanguageElementKey;
import code.util.CustList;
import code.util.EnumMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.pagination.SelectedBoolean;

public final class ComparatorLanguageSelectedBoolean implements Comparing<LanguageElementKey> {

    private EnumMap<SelectedBoolean,String> translatorCurrentLanguage;

    private StringList languages = new StringList();

    public ComparatorLanguageSelectedBoolean(StringMap<EnumMap<SelectedBoolean,String>> _translatorCurrentLanguage, String _language) {
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
        SelectedBoolean keyOne_ = (SelectedBoolean) _o1.getKey();
        SelectedBoolean keyTwo_ = (SelectedBoolean) _o2.getKey();
        int res_ = compare(translatorCurrentLanguage, keyOne_, keyTwo_);
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        String langOne_ = _o1.getLanguage();
        String langTwo_ = _o2.getLanguage();
        return Numbers.compare(languages.indexOfObj(langOne_), languages.indexOfObj(langTwo_));
    }

    private static int compare(EnumMap<SelectedBoolean,String> _translator, SelectedBoolean _e1, SelectedBoolean _e2) {
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