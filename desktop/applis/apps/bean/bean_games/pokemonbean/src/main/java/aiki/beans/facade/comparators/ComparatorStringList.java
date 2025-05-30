package aiki.beans.facade.comparators;
import code.util.*;
import code.util.core.*;
import code.util.ints.*;

public final class ComparatorStringList implements Comparing<StringList> {

    @Override
    public int compare(StringList _o1, StringList _o2) {
        int min_ = NumberUtil.min(_o1.size(), _o2.size());
        for (int i = IndexConstants.FIRST_INDEX; i < min_; i++) {
            int res_ = StringUtil.compareStrings(_o1.get(i),_o2.get(i));
            if (res_ != 0) {
                return res_;
            }
        }
        return _o1.size() - _o2.size();
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
//        int lenOne_ = _o1.size();
//        int lenTwo_ = _o2.size();
//        int minLen_ = NumberUtil.min(lenOne_, lenTwo_);
////        int diff_ = lenOne_ - lenTwo_;
////        if (diff_ != 0) {
////            return diff_;
////        }
//        for (int i = IndexConstants.FIRST_INDEX; i < minLen_; i++) {
//            int res_ = ComparatorTrStrings.compare(translatedMovesCmp_, _o1.get(i), _o2.get(i));
//            if (res_ != 0) {
//                return res_;
//            }
//        }
//        return _o1.size() - _o2.size();
        //return 0;
    }

}