package code.util;

import code.threads.ConcreteInteger;
import code.util.comparators.NaturalComparator;
import org.junit.Test;

public class AbEqListTest extends EquallableExUtil {
    @Test
    public void index1Test() {
        IdList<ConcreteInteger> e_ = new IdList<ConcreteInteger>(new IdList<ConcreteInteger>());
        ConcreteInteger one_ = new ConcreteInteger(1);
        e_.add(one_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        e_.add(two_);
        ConcreteInteger three_ = new ConcreteInteger(3);
        e_.add(three_);
        e_.add(one_);
        Ints indexes_ = e_.indexesOfObj(one_);
        assertEq(2, indexes_.size());
        assertEq(0, indexes_.get(0));
        assertEq(3, indexes_.get(1));
    }
    @Test
    public void removeDuplates1Test() {
        IdList<ConcreteInteger> e_ = new IdList<ConcreteInteger>(new ConcreteInteger[0]);
        ConcreteInteger one_ = new ConcreteInteger(1);
        e_.add(one_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        e_.add(two_);
        ConcreteInteger three_ = new ConcreteInteger(3);
        e_.add(three_);
        e_.add(one_);
        e_.removeDuplicates();
        assertEq(3, e_.size());
        assertSame(one_, e_.get(0));
        assertSame(two_, e_.get(1));
        assertSame(three_, e_.get(2));
    }
    @Test
    public void getGroupsSameCompare1Test() {
        IdList<String> e_ = new IdList<String>();
        e_.add("ONE");
        e_.add("TWO");
        e_.add("THREE");
        e_.add("ONE");
        CustList<IdList<String>> gr_ = e_.getGroupsSameCompare(new NaturalComparator());
        assertEq(3, gr_.size());
        assertEq(2, gr_.get(0).size());
        assertEq("ONE", gr_.get(0).get(0));
        assertEq("ONE", gr_.get(0).get(1));
        assertEq(1, gr_.get(1).size());
        assertEq("THREE", gr_.get(1).get(0));
        assertEq(1, gr_.get(2).size());
        assertEq("TWO", gr_.get(2).get(0));
    }
    @Test
    public void eqEnum1Test() {
        IdList<ConcreteInteger> e_ = new IdList<ConcreteInteger>(new CollCapacity(3));
        ConcreteInteger one_ = new ConcreteInteger(1);
        e_.add(one_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        e_.add(two_);
        ConcreteInteger three_ = new ConcreteInteger(3);
        e_.add(three_);
        e_.add(one_);
        IdList<ConcreteInteger> f_ = new IdList<ConcreteInteger>();
        f_.add(one_);
        f_.add(two_);
        f_.add(three_);
        f_.add(one_);
        assertTrue(e_.eq(f_));
    }
    @Test
    public void eqEnum2Test() {
        IdList<ConcreteInteger> e_ = new IdList<ConcreteInteger>();
        ConcreteInteger one_ = new ConcreteInteger(1);
        e_.add(one_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        e_.add(two_);
        ConcreteInteger three_ = new ConcreteInteger(3);
        e_.add(three_);
        IdList<ConcreteInteger> f_ = new IdList<ConcreteInteger>();
        f_.add(one_);
        f_.add(two_);
        f_.add(three_);
        f_.add(one_);
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void eqEnum3Test() {
        IdList<ConcreteInteger> e_ = new IdList<ConcreteInteger>();
        ConcreteInteger one_ = new ConcreteInteger(1);
        e_.add(one_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        e_.add(two_);
        ConcreteInteger three_ = new ConcreteInteger(3);
        e_.add(three_);
        e_.add(three_);
        IdList<ConcreteInteger> f_ = new IdList<ConcreteInteger>();
        f_.add(one_);
        f_.add(two_);
        f_.add(three_);
        f_.add(one_);
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void indexId1Test() {
        IdList<ConcreteInteger> e_ = new IdList<ConcreteInteger>(new IdList<ConcreteInteger>());
        ConcreteInteger one_ = new ConcreteInteger(1);
        e_.add(one_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        e_.add(two_);
        ConcreteInteger three_ = new ConcreteInteger(3);
        e_.add(three_);
        e_.add(one_);
        int index_ = e_.indexOfObj(one_,1);
        assertEq(3, index_);
    }
    @Test
    public void indexId2Test() {
        IdList<ConcreteInteger> e_ = new IdList<ConcreteInteger>(new ConcreteInteger[0]);
        ConcreteInteger one_ = new ConcreteInteger(1);
        e_.add(one_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        e_.add(two_);
        ConcreteInteger three_ = new ConcreteInteger(3);
        e_.add(three_);
        e_.add(one_);
        int index_ = e_.indexOfObj(two_,2);
        assertEq(-1, index_);
    }
    @Test
    public void eqId1Test() {
        IdList<ConcreteInteger> e_ = new IdList<ConcreteInteger>(new CollCapacity(4));
        ConcreteInteger one_ = new ConcreteInteger(1);
        e_.add(one_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        e_.add(two_);
        ConcreteInteger three_ = new ConcreteInteger(3);
        e_.add(three_);
        e_.add(one_);
        IdList<ConcreteInteger> f_ = new IdList<ConcreteInteger>();
        f_.add(one_);
        f_.add(two_);
        f_.add(three_);
        f_.add(one_);
        assertTrue(e_.eq(f_));
    }
    @Test
    public void eqId2Test() {
        IdList<ConcreteInteger> e_ = new IdList<ConcreteInteger>();
        ConcreteInteger one_ = new ConcreteInteger(1);
        e_.add(one_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        e_.add(two_);
        ConcreteInteger three_ = new ConcreteInteger(3);
        e_.add(three_);
        IdList<ConcreteInteger> f_ = new IdList<ConcreteInteger>();
        f_.add(one_);
        f_.add(two_);
        f_.add(three_);
        f_.add(one_);
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void eqId3Test() {
        IdList<ConcreteInteger> e_ = new IdList<ConcreteInteger>();
        ConcreteInteger one_ = new ConcreteInteger(1);
        e_.add(one_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        e_.add(two_);
        ConcreteInteger three_ = new ConcreteInteger(3);
        e_.add(three_);
        e_.add(three_);
        IdList<ConcreteInteger> f_ = new IdList<ConcreteInteger>();
        f_.add(one_);
        f_.add(two_);
        f_.add(three_);
        f_.add(one_);
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void indexEq1Test() {
        StringList e_ = new StringList(new StringList());
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("1");
        int index_ = e_.indexOfObj("1",1);
        assertEq(3, index_);
    }
    @Test
    public void indexEq2Test() {
        StringList e_ = new StringList(new StringList());
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("1");
        int index_ = e_.indexOfObj("2",2);
        assertEq(-1, index_);
    }
    @Test
    public void removeDuplatesEq1Test() {
        StringList e_ = new StringList(new String[0]);
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("1");
        e_.removeDuplicates();
        assertEq(3, e_.size());
        assertEq("1", e_.get(0));
        assertEq("2", e_.get(1));
        assertEq("3", e_.get(2));
    }
    @Test
    public void hasDuplicatesEq1Test() {
        StringList e_ = new StringList(new String[0]);
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("1");
        assertTrue(e_.hasDuplicates());
    }
    @Test
    public void hasDuplicatesEq2Test() {
        StringList e_ = new StringList(new String[0]);
        e_.add("1");
        e_.add("2");
        assertTrue(!e_.hasDuplicates());
    }
    @Test
    public void eqEq1Test() {
        StringList e_ = new StringList(new CollCapacity(3));
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("1");
        StringList f_ = new StringList();
        f_.add("1");
        f_.add("2");
        f_.add("3");
        f_.add("1");
        assertTrue(e_.eq(f_));
    }
    @Test
    public void eqEq2Test() {
        StringList e_ = new StringList();
        e_.add("1");
        e_.add("2");
        e_.add("3");
        StringList f_ = new StringList();
        f_.add("1");
        f_.add("2");
        f_.add("3");
        f_.add("1");
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void eqEq3Test() {
        StringList e_ = new StringList();
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("3");
        StringList f_ = new StringList();
        f_.add("1");
        f_.add("2");
        f_.add("3");
        f_.add("1");
        assertTrue(!e_.eq(f_));
    }
//    @Test
//    public void indexBool1Test() {
//        BooleanList e_ = new BooleanList(new BooleanList());
//        e_.add(true);
//        e_.add(false);
//        e_.add(false);
//        e_.add(true);
//        Ints indexes_ = e_.indexesOfBool(true);
//        assertEq(2, indexes_.size());
//        assertEq(0, indexes_.get(0));
//        assertEq(3, indexes_.get(1));
//        assertTrue(e_.containsObj(true));
//    }
//    @Test
//    public void indexBool2Test() {
//        BooleanList e_ = new BooleanList(new BooleanList());
//        e_.add(false);
//        e_.add(false);
//        Ints indexes_ = e_.indexesOfBool(true);
//        assertEq(0, indexes_.size());
//        assertTrue(!e_.containsObj(true));
//    }
//    @Test
//    public void eqBool1Test() {
//        BooleanList e_ = new BooleanList(new CollCapacity(3));
//        e_.add(true);
//        e_.add(false);
//        e_.add(false);
//        e_.add(true);
//        BooleanList f_ = new BooleanList();
//        f_.add(true);
//        f_.add(false);
//        f_.add(false);
//        f_.add(true);
//        assertTrue(e_.eq(f_));
//    }
//    @Test
//    public void eqBool2Test() {
//        BooleanList e_ = new BooleanList(new CollCapacity(3));
//        e_.add(true);
//        e_.add(false);
//        e_.add(false);
//        BooleanList f_ = new BooleanList(new Boolean[0]);
//        f_.add(true);
//        f_.add(false);
//        f_.add(false);
//        f_.add(true);
//        assertTrue(!e_.eq(f_));
//    }
//    @Test
//    public void eqBool3Test() {
//        BooleanList e_ = new BooleanList();
//        e_.add(true);
//        e_.add(false);
//        e_.add(false);
//        e_.add(false);
//        BooleanList f_ = new BooleanList();
//        f_.add(true);
//        f_.add(false);
//        f_.add(false);
//        f_.add(true);
//        assertTrue(!e_.eq(f_));
//    }
    @Test
    public void remove1Test() {
        StringList e_ = new StringList();
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("1");
        e_.removeAllObj("1");
        StringList f_ = new StringList();
        f_.add("2");
        f_.add("3");
        assertTrue(e_.eq(f_));
    }
    @Test
    public void remove2Test() {
        StringList e_ = new StringList();
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("1");
        e_.removeObj("4");
        StringList f_ = new StringList();
        f_.add("1");
        f_.add("2");
        f_.add("3");
        f_.add("1");
        assertTrue(e_.eq(f_));
    }
    @Test
    public void remove3Test() {
        StringList e_ = new StringList();
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("1");
        StringList g_ = new StringList();
        g_.add("2");
        g_.add("3");
        g_.add("5");
        e_.removeAllElements(g_);
        StringList f_ = new StringList();
        f_.add("1");
        f_.add("1");
        assertTrue(e_.eq(f_));
    }
    @Test
    public void remove4Test() {
        StringList e_ = new StringList();
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("1");
        e_.removeObj("1");
        StringList f_ = new StringList();
        f_.add("2");
        f_.add("3");
        f_.add("1");
        assertTrue(e_.eq(f_));
    }
    @Test
    public void containsAllObj1Test() {
        AbEqList<String> e_ = new StringList();
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("1");
        CustList<String> f_ = new StringList();
        f_.add("1");
        f_.add("1");
        assertTrue(e_.containsAllObj(f_));
    }
    @Test
    public void containsAllObj2Test() {
        AbEqList<String> e_ = new StringList();
        e_.add("1");
        e_.add("2");
        e_.add("3");
        e_.add("1");
        CustList<String> f_ = new StringList();
        f_.add("1");
        f_.add("4");
        assertTrue(!e_.containsAllObj(f_));
    }

}
