package code.util.comparators;

import code.util.EquallableExUtil;
import code.util.StringList;
import code.util.TreeMap;
import org.junit.Test;

public final class ComparatorTreeMapValueTest  extends EquallableExUtil {
    @Test
    public void sortTest() {
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator());
        map_.put("ONE",2);
        map_.put("TWO",1);
        ComparatorTreeMapValue<String> cmp_ = new ComparatorTreeMapValue<String>(map_);
        StringList l_ = new StringList();
        l_.add("ONE");
        l_.add("TWO");
        l_.sortElts(cmp_);
        assertEq(2,l_.size());
        assertEq("TWO",l_.first());
        assertEq("ONE",l_.last());
    }
}
