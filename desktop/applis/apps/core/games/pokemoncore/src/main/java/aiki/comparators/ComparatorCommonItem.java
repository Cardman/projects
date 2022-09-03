package aiki.comparators;

import aiki.facade.LgIntFieldComparator;
import aiki.facade.LongFieldComparator;
import aiki.facade.StringFieldComparator;

public class ComparatorCommonItem extends ComparatorCommon {

    private final LongFieldComparator cmpPrice = new LongFieldComparator();

    private final StringFieldComparator cmpDescription = new StringFieldComparator();

    private final LgIntFieldComparator cmpNumber = new LgIntFieldComparator();

    public StringFieldComparator getCmpDescription() {
        return cmpDescription;
    }

    public LongFieldComparator getCmpPrice() {
        return cmpPrice;
    }

    public LgIntFieldComparator getCmpNumber() {
        return cmpNumber;
    }
}
