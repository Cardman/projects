package aiki.beans.facade.comparators;

import aiki.beans.abilities.*;
import code.util.core.*;
import code.util.ints.*;

public final class ComparatorTranslatedKeyPair implements Comparing<TranslatedKeyPair> {

    @Override
    public int compare(TranslatedKeyPair _o1, TranslatedKeyPair _o2) {
        int res_ = StringUtil.compareStrings(_o1.getFirst().getTranslation(),_o2.getFirst().getTranslation());
        if (res_ != 0) {
            return res_;
        }
        return StringUtil.compareStrings(_o1.getSecond().getTranslation(),_o2.getSecond().getTranslation());
    }

}