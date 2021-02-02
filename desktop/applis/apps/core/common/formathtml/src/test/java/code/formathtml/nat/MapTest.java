package code.formathtml.nat;

import code.formathtml.EquallableExUtil;
import code.util.CustList;
import org.junit.Test;

public final class MapTest extends EquallableExUtil {

    @Test
    public void putAllMap13Test() {
        StringMapObject map_ = new StringMapObject();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        StringMapObject mapToPut_ = new StringMapObject();
        mapToPut_.put("THREE", 3);
        mapToPut_.put("FOUR", 4);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        assertTrue(map_.contains("THREE"));
        assertTrue(map_.contains("FOUR"));
        assertEq(1, (Integer)map_.getVal("ONE"));
        assertEq(2, (Integer)map_.getVal("TWO"));
        assertEq(3, (Integer)map_.getVal("THREE"));
        assertEq(4, (Integer)map_.getVal("FOUR"));
    }

    @Test
    public void putAllMap14Test() {
        StringMapObject map_ = new StringMapObject();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        StringMapObject mapToPut_ = new StringMapObject();
        mapToPut_.put("TWO", 3);
        mapToPut_.put("THREE", 4);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        assertTrue(map_.contains("THREE"));
        assertEq(1, (Integer)map_.getVal("ONE"));
        assertEq(3, (Integer)map_.getVal("TWO"));
        assertEq(4, (Integer)map_.getVal("THREE"));
    }
    @Test
    public void getKeysStrObjTest() {
        StringMapObject map_ = new StringMapObject();
        map_.put("", 0);
        CustList<String> elts_ = map_.getKeys();
        assertEq(1,elts_.size());
        assertEq("",elts_.first());
        map_.isEmpty();
        map_.removeKey("");
        map_.clear();
    }

}
