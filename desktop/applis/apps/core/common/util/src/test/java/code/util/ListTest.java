package code.util;
import code.util.classestest.IntegerComparator;
import code.util.classestest.MyEnum;
import code.util.core.BoolVal;
import org.junit.Test;


public class ListTest extends EquallableExUtil {

    @Test
    public void getGroupsSameCompare1Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        IntegerComparator intCmp_ = new IntegerComparator();
        CustList<CustList<Integer>> groups_ = integers_.getBaseGroupsSameCompare(intCmp_);
        assertEq(1, groups_.size());
        assertEq(0, groups_.first().size());
    }

    @Test
    public void getGroupsSameCompare2Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(0);
        integers_.add(3);
        integers_.add(-2);
        integers_.add(5);
        integers_.add(1);
        IntegerComparator intCmp_ = new IntegerComparator();
        CustList<CustList<Integer>> groups_ = integers_.getBaseGroupsSameCompare(intCmp_);
        assertEq(5, groups_.size());
        assertEq(1, groups_.get(0).size());
        assertEq(1, groups_.get(1).size());
        assertEq(1, groups_.get(2).size());
        assertEq(1, groups_.get(3).size());
        assertEq(1, groups_.get(4).size());
        assertEq(-2, groups_.get(0).first());
        assertEq(0, groups_.get(1).first());
        assertEq(1, groups_.get(2).first());
        assertEq(3, groups_.get(3).first());
        assertEq(5, groups_.get(4).first());
    }

    @Test
    public void getGroupsSameCompare3Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(0);
        integers_.add(3);
        integers_.add(-2);
        integers_.add(5);
        integers_.add(1);
        integers_.add(3);
        IntegerComparator intCmp_ = new IntegerComparator();
        CustList<CustList<Integer>> groups_ = integers_.getBaseGroupsSameCompare(intCmp_);
        assertEq(5, groups_.size());
        assertEq(1, groups_.get(0).size());
        assertEq(1, groups_.get(1).size());
        assertEq(1, groups_.get(2).size());
        assertEq(2, groups_.get(3).size());
        assertEq(1, groups_.get(4).size());
        assertEq(-2, groups_.get(0).first());
        assertEq(0, groups_.get(1).first());
        assertEq(1, groups_.get(2).first());
        assertEq(3, groups_.get(3).first());
        assertEq(3, groups_.get(3).last());
        assertEq(5, groups_.get(4).first());
    }

    @Test
    public void getGroupsSameCompare4Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(0);
        integers_.add(3);
        integers_.add(-2);
        integers_.add(5);
        integers_.add(3);
        integers_.add(1);
        integers_.add(3);
        IntegerComparator intCmp_ = new IntegerComparator();
        CustList<CustList<Integer>> groups_ = integers_.getBaseGroupsSameCompare(intCmp_);
        assertEq(5, groups_.size());
        assertEq(1, groups_.get(0).size());
        assertEq(1, groups_.get(1).size());
        assertEq(1, groups_.get(2).size());
        assertEq(3, groups_.get(3).size());
        assertEq(1, groups_.get(4).size());
        assertEq(-2, groups_.get(0).first());
        assertEq(0, groups_.get(1).first());
        assertEq(1, groups_.get(2).first());
        assertEq(3, groups_.get(3).first());
        assertEq(3, groups_.get(3).get(1));
        assertEq(3, groups_.get(3).last());
        assertEq(5, groups_.get(4).first());
    }

    @Test
    public void getGroupsSameCompare5Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(3);
        integers_.add(3);
        integers_.add(3);
        integers_.add(3);
        IntegerComparator intCmp_ = new IntegerComparator();
        CustList<CustList<Integer>> groups_ = integers_.getBaseGroupsSameCompare(intCmp_);
        assertEq(1, groups_.size());
        assertEq(4, groups_.get(0).size());
        assertEq(3, groups_.get(0).first());
        assertEq(3, groups_.get(0).get(1));
        assertEq(3, groups_.get(0).get(2));
        assertEq(3, groups_.get(0).last());
    }

    @Test
    public void getReverse1Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(0);
        integers_.add(3);
        integers_.add(-2);
        integers_.add(5);
        CustList<Integer> res_ = integers_.getReverse();
        assertEq(4, res_.size());
        assertEq(5, res_.get(0));
        assertEq(-2, res_.get(1));
        assertEq(3, res_.get(2));
        assertEq(0, res_.get(3));
    }

    @Test
    public void getRevers2Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(0);
        integers_.add(3);
        integers_.add(-2);
        CustList<Integer> res_ = integers_.getReverse();
        assertEq(3, res_.size());
        assertEq(-2, res_.get(0));
        assertEq(3, res_.get(1));
        assertEq(0, res_.get(2));
    }

    @Test
    public void getRevers3Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(0);
        integers_.add(3);
        CustList<Integer> res_ = integers_.getReverse();
        assertEq(2, res_.size());
        assertEq(3, res_.get(0));
        assertEq(0, res_.get(1));
    }

    @Test
    public void getRevers4Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(0);
        CustList<Integer> res_ = integers_.getReverse();
        assertEq(1, res_.size());
        assertEq(0, res_.get(0));
    }

    @Test
    public void getRevers5Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        CustList<Integer> res_ = integers_.getReverse();
        assertEq(0, res_.size());
    }
    @Test
    public void left1Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.left(-1);
        assertEq(0, sub_.size());
    }
    @Test
    public void left2Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.left(2);
        assertEq(2, sub_.size());
        assertEq(5, sub_.get(0));
        assertEq(1, sub_.get(1));
    }
    @Test
    public void left3Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.left(1);
        assertEq(1, sub_.size());
        assertEq(5, sub_.get(0));
    }
    @Test
    public void leftMinusOne1Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.leftMinusOne(0);
        assertEq(0, sub_.size());
    }
    @Test
    public void leftMinusOne2Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.leftMinusOne(2);
        assertEq(1, sub_.size());
        assertEq(1, sub_.get(0));
    }
    @Test
    public void leftMinusOne3Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.leftMinusOne(1);
        assertEq(1, sub_.size());
        assertEq(1, sub_.get(0));
    }
    @Test
    public void leftMinusOne4Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        integers_.add(2);
        CustList<Integer> sub_ = integers_.leftMinusOne(1);
        assertEq(1, sub_.size());
        assertEq(1, sub_.get(0));
    }
    @Test
    public void leftMinusOne5Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        integers_.add(2);
        CustList<Integer> sub_ = integers_.leftMinusOne(3);
        assertEq(2, sub_.size());
        assertEq(1, sub_.get(0));
        assertEq(2, sub_.get(1));
    }
    @Test
    public void sub1Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.sub(1,2);
        assertEq(1, sub_.size());
        assertEq(1, sub_.get(0));
    }
    @Test
    public void sub2Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.sub(2,1);
        assertEq(0, sub_.size());
    }
    @Test
    public void mid1Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.mid(1,1);
        assertEq(1, sub_.size());
        assertEq(1, sub_.get(0));
    }
    @Test
    public void mid2Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.mid(1);
        assertEq(1, sub_.size());
        assertEq(1, sub_.get(0));
    }
    @Test
    public void mid3Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.mid(1,2);
        assertEq(1, sub_.size());
        assertEq(1, sub_.get(0));
    }
    @Test
    public void mid4Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.mid(2);
        assertEq(0, sub_.size());
    }
    @Test
    public void mid5Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.mid(0);
        assertEq(2, sub_.size());
        assertEq(5, sub_.get(0));
        assertEq(1, sub_.get(1));
    }
    @Test
    public void mid6Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.mid(3);
        assertEq(0, sub_.size());
    }
    @Test
    public void isValidIndexTest() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        assertTrue(!integers_.isValidIndex(-1));
        assertTrue(integers_.isValidIndex(0));
        assertTrue(!integers_.isValidIndex(1));
    }
    @Test
    public void removeLastTest() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.removeLast();
        integers_.removeLast();
        assertTrue(integers_.isEmpty());
    }
    @Test
    public void retainTest() {
        IdList<MyEnum> integers_ = new IdList<MyEnum>();
        integers_.add(MyEnum.THREE);
        integers_.add(MyEnum.ONE);
        IdList<MyEnum> integersTwo_ = new IdList<MyEnum>();
        integersTwo_.add(MyEnum.THREE);
        integersTwo_.add(MyEnum.TWO);
        integers_.retainAllElements(integersTwo_);
        assertEq(1, integers_.size());
        assertSame(MyEnum.THREE, integers_.get(0));
    }
    @Test
    public void retain2Test() {
        IdList<BoolVal> integers_ = new IdList<BoolVal>();
        integers_.add(BoolVal.TRUE);
        integers_.add(BoolVal.FALSE);
        IdList<BoolVal> integersTwo_ = new IdList<BoolVal>();
        integersTwo_.add(BoolVal.TRUE);
        integers_.retainAllElements(integersTwo_);
        assertEq(1, integers_.size());
        assertSame(BoolVal.TRUE, integers_.get(0));
    }
    @Test
    public void getPrevTest() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        assertEq(5, integers_.getPrev(1));
    }
    @Test
    public void clearTest() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        assertNotNull(integers_.get(0));
        integers_.clear();
        assertTrue(integers_.isEmpty());
        assertNotNull(integers_.getList());
        assertEq(0,integers_.list().toArray().length);
    }
    @Test
    public void simpleIterator1Test() {
        assertEq(0,CharList.wrapCharArray().length);
    }
}
