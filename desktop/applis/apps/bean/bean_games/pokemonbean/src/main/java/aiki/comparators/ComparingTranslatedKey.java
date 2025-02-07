package aiki.comparators;

import aiki.beans.*;
import code.util.core.*;
import code.util.ints.*;

public final class ComparingTranslatedKey implements Comparing<TranslatedKey> {
    @Override
    public int compare(TranslatedKey _one, TranslatedKey _two) {
        return StringUtil.compareStrings(_one.getTranslation(),_two.getTranslation());
    }
}
