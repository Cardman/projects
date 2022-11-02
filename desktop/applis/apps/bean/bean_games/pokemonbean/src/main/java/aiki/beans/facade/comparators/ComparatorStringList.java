package aiki.beans.facade.comparators;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorStringList implements Comparing<StringList> {

    private final boolean translate;

    private final DataBase data;

    private final String language;

    public ComparatorStringList(DataBase _data, String _language, boolean _translate) {
        data = _data;
        language = _language;
        translate = _translate;
    }

    @Override
    public int compare(StringList _o1, StringList _o2) {
        if (translate) {
            int min_ = NumberUtil.min(_o1.size(), _o2.size());
            for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
                int res_ = StringUtil.compareStrings(_o1.get(i),_o2.get(i));
                if (res_ != 0) {
                    return res_;
                }
            }
            return _o1.size() - _o2.size();
        }
        StringMap<String> translatedMovesCmp_ = data.getTranslatedMoves().getVal(language);
        int lenOne_ = _o1.size();
        int lenTwo_ = _o2.size();
        int minLen_ = NumberUtil.min(lenOne_, lenTwo_);
//        int diff_ = lenOne_ - lenTwo_;
//        if (diff_ != 0) {
//            return diff_;
//        }
        for (int i = IndexConstants.FIRST_INDEX; i < minLen_; i++) {
            int res_ = ComparatorTrStrings.compare(translatedMovesCmp_, _o1.get(i), _o2.get(i));
            if (res_ != 0) {
                return res_;
            }
        }
        return _o1.size() - _o2.size();
        //return 0;
    }

}