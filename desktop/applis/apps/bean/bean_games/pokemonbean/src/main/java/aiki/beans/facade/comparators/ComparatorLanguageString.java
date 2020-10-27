package aiki.beans.facade.comparators;
import aiki.beans.help.LanguageElementStringKey;
import aiki.db.DataBase;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorLanguageString implements Comparing<LanguageElementStringKey> {

    private StringMap<String> translatorCurrentLanguage;

    private StringList languages;

    public ComparatorLanguageString(StringMap<StringMap<String>> _translatorCurrentLanguage, String _language, StringList _sortedLg) {
        translatorCurrentLanguage = _translatorCurrentLanguage.getVal(_language);
        languages = _sortedLg;
    }

    @Override
    public int compare(LanguageElementStringKey _o1, LanguageElementStringKey _o2) {
        String keyOne_ = _o1.getKey();
        String keyTwo_ = _o2.getKey();
        int res_ = compare(translatorCurrentLanguage, keyOne_, keyTwo_);
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        String langOne_ = _o1.getLanguage();
        String langTwo_ = _o2.getLanguage();
        return NumberUtil.compareLg(StringUtil.indexOf(languages,langOne_), StringUtil.indexOf(languages,langTwo_));
    }

    private static int compare(StringMap<String> _translator, String _e1, String _e2) {
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
        return StringUtil.compareStrings(trOne_,trTwo_);
    }
}