@Test
    public void simpleIterator1Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        SimpleItr itr_ = integers_.simpleIterator();
        assertTrue(itr_.hasNext());
        assertEq(5, (Integer)itr_.next());
        assertTrue(itr_.hasNext());
        assertEq(1, (Integer)itr_.next());
        assertTrue(!itr_.hasNext());
        assertTrue(new CharList().isEmpty());
        assertEq(0,CharList.wrapCharArray().length);
    }
	assertNotNull(map_.entries());
	package code.util;

import org.junit.Test;

import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;

public final class EntryCustTest {
    @Test
    public void simpleTest() {
        EntryCust<Integer,Integer> e_ = new EntryCust<Integer, Integer>(1,2);
        assertEq(1, e_.getKey());
        assertEq(2, e_.getValue());
        assertNotNull(e_.getSimpleKey());
        assertNotNull(e_.getSimpleValue());
    }
}
