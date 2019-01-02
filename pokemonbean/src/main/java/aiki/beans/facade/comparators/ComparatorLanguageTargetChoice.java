package aiki.beans.facade.comparators;
import aiki.beans.help.LanguageElementKey;
import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import code.util.CustList;
import code.util.EnumMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.ints.Comparing;

public final class ComparatorLanguageTargetChoice implements Comparing<LanguageElementKey> {

    private EnumMap<TargetChoice,String> translatorCurrentLanguage;

    private StringList languages = new StringList();

    public ComparatorLanguageTargetChoice(StringMap<EnumMap<TargetChoice,String>> _translatorCurrentLanguage, String _language) {
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
        TargetChoice keyOne_ = (TargetChoice) _o1.getKey();
        TargetChoice keyTwo_ = (TargetChoice) _o2.getKey();
        int res_ = compare(translatorCurrentLanguage, keyOne_, keyTwo_);
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        String langOne_ = _o1.getLanguage();
        String langTwo_ = _o2.getLanguage();
        return Numbers.compare(languages.indexOfObj(langOne_), languages.indexOfObj(langTwo_));
    }

    private static int compare(EnumMap<TargetChoice,String> _translator, TargetChoice _e1, TargetChoice _e2) {
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