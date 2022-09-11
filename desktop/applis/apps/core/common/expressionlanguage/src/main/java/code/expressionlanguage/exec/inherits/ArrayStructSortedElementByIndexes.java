package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.structs.Struct;
import code.util.AbsComparerTreeMap;
import code.util.Ints;
import code.util.ints.Comparing;

final class ArrayStructSortedElementByIndexes extends AbsComparerTreeMap<Ints, Struct> {
    ArrayStructSortedElementByIndexes(Comparing<Ints> _cmp) {
        super(_cmp);
    }
}
