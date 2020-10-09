package code.formathtml.classes;

import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class ComparatorEnumNumber implements Comparing<EnumNumber> {
    @Override
    public int compare(EnumNumber _one, EnumNumber _two) {
        return NumberUtil.compareLg(_one.ordinal(),_two.ordinal());
    }
}