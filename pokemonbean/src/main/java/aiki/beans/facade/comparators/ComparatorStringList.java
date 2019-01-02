package aiki.beans.facade.comparators;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Comparing;

public final class ComparatorStringList implements Comparing<StringList> {

    private boolean translate;

    private DataBase data;

    private String language;

    public ComparatorStringList(DataBase _data, String _language, boolean _translate) {
        data = _data;
        language = _language;
        translate = _translate;
    }

    @Override
    public int compare(StringList _o1, StringList _o2) {
        if (translate) {
            int min_ = Math.min(_o1.size(), _o2.size());
            for (int i = CustList.FIRST_INDEX; i < min_; i++) {
                int res_ = _o1.get(i).compareTo(_o2.get(i));
                if (res_ != 0) {
                    return res_;
                }
            }
            return _o1.size() - _o2.size();
        }
        StringMap<String> translatedMovesCmp_ = data.getTranslatedMoves().getVal(language);
        int lenOne_ = _o1.size();
        int lenTwo_ = _o2.size();
        int minLen_ = Math.min(lenOne_, lenTwo_);
//        int diff_ = lenOne_ - lenTwo_;
//        if (diff_ != 0) {
//            return diff_;
//        }
        for (int i = CustList.FIRST_INDEX; i < minLen_; i++) {
            int res_ = ComparatorTrStrings.compare(translatedMovesCmp_, _o1.get(i), _o2.get(i));
            if (res_ != 0) {
                return res_;
            }
        }
        return _o1.size() - _o2.size();
        //return 0;
    }

}