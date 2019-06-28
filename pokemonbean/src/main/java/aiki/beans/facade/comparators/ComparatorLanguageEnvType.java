package aiki.beans.facade.comparators;
import aiki.beans.help.LanguageElementKey;
import aiki.db.DataBase;
import aiki.map.levels.enums.EnvironmentType;
import code.util.CustList;
import code.util.EnumMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Comparing;

public final class ComparatorLanguageEnvType implements Comparing<LanguageElementKey> {

    private EnumMap<EnvironmentType,String> translatorCurrentLanguage;

    private StringList languages;

    public ComparatorLanguageEnvType(StringMap<EnumMap<EnvironmentType, String>> _translatorCurrentLanguage, String _language, StringList _sortedLg) {
        translatorCurrentLanguage = _translatorCurrentLanguage.getVal(_language);
        languages = _sortedLg;
    }

    @Override
    public int compare(LanguageElementKey _o1, LanguageElementKey _o2) {
        EnvironmentType keyOne_ = (EnvironmentType) _o1.getKey();
        EnvironmentType keyTwo_ = (EnvironmentType) _o2.getKey();
        int res_ = compare(translatorCurrentLanguage, keyOne_, keyTwo_);
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        String langOne_ = _o1.getLanguage();
        String langTwo_ = _o2.getLanguage();
        return Numbers.compareLg(StringList.indexOf(languages,langOne_), StringList.indexOf(languages,langTwo_));
    }

    private static int compare(EnumMap<EnvironmentType,String> _translator, EnvironmentType _e1, EnvironmentType _e2) {
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