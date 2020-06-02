package code.util.comparators;

import code.util.IdList;
import code.util.classestest.MyEnum;
import org.junit.Test;

import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertSame;

public final class ComparatorIndexesTest {
    @Test
    public void sort() {
        IdList<MyEnum> id_ = new IdList<MyEnum>();
        id_.add(MyEnum.ZERO);
        id_.add(MyEnum.ONE);
        id_.add(MyEnum.TWO);
        id_.add(MyEnum.THREE);
        ComparatorIndexes<MyEnum> cmp_ = new ComparatorIndexes<MyEnum>(id_);
        IdList<MyEnum> l_ = new IdList<MyEnum>();
        l_.add(MyEnum.ONE);
        l_.add(MyEnum.THREE);
        l_.add(MyEnum.TWO);
        l_.add(MyEnum.ZERO);
        l_.sortElts(cmp_);
        assertEq(4, l_.size());
        assertSame(MyEnum.ZERO,l_.get(0));
        assertSame(MyEnum.ONE,l_.get(1));
        assertSame(MyEnum.TWO,l_.get(2));
        assertSame(MyEnum.THREE,l_.get(3));
    }
}
