package code.util.comparators;

import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

import static code.util.EquallableExUtil.assertEq;

public final class ComparatorMapValueTest {
    @Test
    public void sortTest() {
        StringMap<String> map_ = new StringMap<String>();
        map_.put("ONE","TWO");
        map_.put("TWO","ONE");
        ComparatorMapValue<String> cmp_ = new ComparatorMapValue<String>(map_);
        StringList l_ = new StringList();
        l_.add("ONE");
        l_.add("TWO");
        l_.sortElts(cmp_);
        assertEq(2,l_.size());
        assertEq("TWO",l_.first());
        assertEq("ONE",l_.last());
    }
}
