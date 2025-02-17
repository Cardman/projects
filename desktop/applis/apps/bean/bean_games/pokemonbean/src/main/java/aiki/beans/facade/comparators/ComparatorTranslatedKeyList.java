package aiki.beans.facade.comparators;
import aiki.beans.TranslatedKey;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ComparatorTranslatedKeyList implements Comparing<CustList<TranslatedKey>> {

    @Override
    public int compare(CustList<TranslatedKey> _o1, CustList<TranslatedKey> _o2) {
//        if (translate) {
//            int min_ = NumberUtil.min(_o1.size(), _o2.size());
//            for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
//                int res_ = StringUtil.compareStrings(_o1.get(i),_o2.get(i));
//                if (res_ != 0) {
//                    return res_;
//                }
//            }
//            return _o1.size() - _o2.size();
//        }
//        StringMap<String> translatedMovesCmp_ = data.getTranslatedMoves().getVal(language);
        int lenOne_ = _o1.size();
        int lenTwo_ = _o2.size();
        int minLen_ = NumberUtil.min(lenOne_, lenTwo_);
//        int diff_ = lenOne_ - lenTwo_;
//        if (diff_ != 0) {
//            return diff_;
//        }
        for (int i = IndexConstants.FIRST_INDEX; i < minLen_; i++) {
//            int res_ = ComparatorTrStrings.compare(translatedMovesCmp_, _o1.get(i), _o2.get(i));
            int res_ = StringUtil.compareStrings(_o1.get(i).getTranslation(), _o2.get(i).getTranslation());
            if (res_ != 0) {
                return res_;
            }
        }
        return _o1.size() - _o2.size();
        //return 0;
    }

}