package aiki.comparators;

import aiki.facade.StringFieldComparator;

public class ComparatorCommon {

    private final StringFieldComparator cmpName = new StringFieldComparator();

    public StringFieldComparator getCmpName() {
        return cmpName;
    }
}
