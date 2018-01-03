package code.expressionlanguage.opers.util;

import code.util.CustList;

public abstract class CharSequenceStruct implements Struct {

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof CharSequenceStruct)) {
            return false;
        }
        CharSequenceStruct other_ = (CharSequenceStruct) _other;
        CharSequence first_ = getInstance();
        CharSequence second_ = other_.getInstance();
        if (first_ == null) {
            if (second_ != null) {
                return false;
            }
            return true;
        }
        if (second_ == null) {
            return false;
        }
        int len_ = first_.length();
        if (len_ != second_.length()) {
            return false;
        }
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (first_.charAt(i) != second_.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public abstract CharSequence getInstance();
}
