package aiki.beans.facade.comparators;
import aiki.beans.help.LanguageElementKey;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import code.util.CustList;
import code.util.EnumMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.ints.Comparing;

public final class ComparatorLanguageStatisic implements Comparing<LanguageElementKey> {

    private EnumMap<Statistic,String> translatorCurrentLanguage;

    private StringList languages = new StringList();

    public ComparatorLanguageStatisic(StringMap<EnumMap<Statistic,String>> _translatorCurrentLanguage, String _language) {
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
        Statistic keyOne_ = (Statistic) _o1.getKey();
        Statistic keyTwo_ = (Statistic) _o2.getKey();
        int res_ = compare(translatorCurrentLanguage, keyOne_, keyTwo_);
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        String langOne_ = _o1.getLanguage();
        String langTwo_ = _o2.getLanguage();
        return Numbers.compareLg(StringList.indexOf(languages,langOne_), StringList.indexOf(languages,langTwo_));
    }

    private static int compare(EnumMap<Statistic,String> _translator, Statistic _e1, Statistic _e2) {
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