package code.util.comparators;

import code.threads.ConcreteInteger;
import code.util.EquallableExUtil;
import code.util.IdList;
import org.junit.Test;

public final class ComparatorIndexesTest extends EquallableExUtil {
    @Test
    public void sort() {
        IdList<ConcreteInteger> id_ = new IdList<ConcreteInteger>();
        ConcreteInteger zero_ = new ConcreteInteger(0);
        id_.add(zero_);
        ConcreteInteger one_ = new ConcreteInteger(1);
        id_.add(one_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        id_.add(two_);
        ConcreteInteger three_ = new ConcreteInteger(3);
        id_.add(three_);
        ComparatorIndexes<ConcreteInteger> cmp_ = new ComparatorIndexes<ConcreteInteger>(id_);
        IdList<ConcreteInteger> l_ = new IdList<ConcreteInteger>();
        l_.add(one_);
        l_.add(three_);
        l_.add(two_);
        l_.add(zero_);
        l_.sortElts(cmp_);
        assertEq(4, l_.size());
        assertSame(zero_,l_.get(0));
        assertSame(one_,l_.get(1));
        assertSame(two_,l_.get(2));
        assertSame(three_,l_.get(3));
    }
}
