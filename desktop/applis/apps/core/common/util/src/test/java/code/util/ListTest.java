package code.util;
import code.threads.ConcreteInteger;
import code.util.classestest.IntegerComparator;
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
    public void right1Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.right(-1);
        assertEq(0, sub_.size());
    }
    @Test
    public void right2Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.right(2);
        assertEq(2, sub_.size());
        assertEq(5, sub_.get(0));
        assertEq(1, sub_.get(1));
    }
    @Test
    public void right3Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(5);
        integers_.add(1);
        CustList<Integer> sub_ = integers_.right(1);
        assertEq(1, sub_.size());
        assertEq(1, sub_.get(0));
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
        IdList<ConcreteInteger> integers_ = new IdList<ConcreteInteger>();
        ConcreteInteger three_ = new ConcreteInteger(3);
        integers_.add(three_);
        ConcreteInteger one_ = new ConcreteInteger(1);
        integers_.add(one_);
        IdList<ConcreteInteger> integersTwo_ = new IdList<ConcreteInteger>();
        integersTwo_.add(three_);
        ConcreteInteger two_ = new ConcreteInteger(2);
        integersTwo_.add(two_);
        integers_.retainAllElements(integersTwo_);
        assertEq(1, integers_.size());
        assertSame(three_, integers_.get(0));
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
        integers_.clear();
        assertTrue(integers_.isEmpty());
        integers_.getList().clear();
        assertEq(0,integers_.list().toArray().length);
    }
    @Test
    public void cartesian() {
        CustList<Integer> lsOne_ = new CustList<Integer>();
        lsOne_.add(2);
        lsOne_.add(3);
        CustList<Integer> lsTwo_ = new CustList<Integer>();
        lsTwo_.add(5);
        lsTwo_.add(7);
        lsTwo_.add(11);
        CustList<Integer> lsThree_ = new CustList<Integer>();
        lsThree_.add(13);
        lsThree_.add(17);
        lsThree_.add(19);
        lsThree_.add(23);
        CustList<CustList<Integer>> e_ = new CustList<CustList<Integer>>();
        e_.add(new CustList<Integer>());
        CustList<CustList<Integer>> resOne_ = lsOne_.cartesian(e_);
        CustList<CustList<Integer>> resTwo_ = lsTwo_.cartesian(resOne_);
        CustList<CustList<Integer>> res_ = lsThree_.cartesian(resTwo_);
        assertEq(24,res_.size());
        assertEq(3,res_.get(0).size());
        assertEq(2,res_.get(0).get(0));
        assertEq(5,res_.get(0).get(1));
        assertEq(13,res_.get(0).get(2));
        assertEq(3,res_.get(1).size());
        assertEq(2,res_.get(1).get(0));
        assertEq(5,res_.get(1).get(1));
        assertEq(17,res_.get(1).get(2));
        assertEq(3,res_.get(2).size());
        assertEq(2,res_.get(2).get(0));
        assertEq(5,res_.get(2).get(1));
        assertEq(19,res_.get(2).get(2));
        assertEq(3,res_.get(3).size());
        assertEq(2,res_.get(3).get(0));
        assertEq(5,res_.get(3).get(1));
        assertEq(23,res_.get(3).get(2));
        assertEq(3,res_.get(4).size());
        assertEq(2,res_.get(4).get(0));
        assertEq(7,res_.get(4).get(1));
        assertEq(13,res_.get(4).get(2));
        assertEq(3,res_.get(5).size());
        assertEq(2,res_.get(5).get(0));
        assertEq(7,res_.get(5).get(1));
        assertEq(17,res_.get(5).get(2));
        assertEq(3,res_.get(6).size());
        assertEq(2,res_.get(6).get(0));
        assertEq(7,res_.get(6).get(1));
        assertEq(19,res_.get(6).get(2));
        assertEq(3,res_.get(7).size());
        assertEq(2,res_.get(7).get(0));
        assertEq(7,res_.get(7).get(1));
        assertEq(23,res_.get(7).get(2));
        assertEq(3,res_.get(8).size());
        assertEq(2,res_.get(8).get(0));
        assertEq(11,res_.get(8).get(1));
        assertEq(13,res_.get(8).get(2));
        assertEq(3,res_.get(9).size());
        assertEq(2,res_.get(9).get(0));
        assertEq(11,res_.get(9).get(1));
        assertEq(17,res_.get(9).get(2));
        assertEq(3,res_.get(10).size());
        assertEq(2,res_.get(10).get(0));
        assertEq(11,res_.get(10).get(1));
        assertEq(19,res_.get(10).get(2));
        assertEq(3,res_.get(11).size());
        assertEq(2,res_.get(11).get(0));
        assertEq(11,res_.get(11).get(1));
        assertEq(23,res_.get(11).get(2));
        assertEq(3,res_.get(12).size());
        assertEq(3,res_.get(12).get(0));
        assertEq(5,res_.get(12).get(1));
        assertEq(13,res_.get(12).get(2));
        assertEq(3,res_.get(13).size());
        assertEq(3,res_.get(13).get(0));
        assertEq(5,res_.get(13).get(1));
        assertEq(17,res_.get(13).get(2));
        assertEq(3,res_.get(14).size());
        assertEq(3,res_.get(14).get(0));
        assertEq(5,res_.get(14).get(1));
        assertEq(19,res_.get(14).get(2));
        assertEq(3,res_.get(15).size());
        assertEq(3,res_.get(15).get(0));
        assertEq(5,res_.get(15).get(1));
        assertEq(23,res_.get(15).get(2));
        assertEq(3,res_.get(16).size());
        assertEq(3,res_.get(16).get(0));
        assertEq(7,res_.get(16).get(1));
        assertEq(13,res_.get(16).get(2));
        assertEq(3,res_.get(17).size());
        assertEq(3,res_.get(17).get(0));
        assertEq(7,res_.get(17).get(1));
        assertEq(17,res_.get(17).get(2));
        assertEq(3,res_.get(18).size());
        assertEq(3,res_.get(18).get(0));
        assertEq(7,res_.get(18).get(1));
        assertEq(19,res_.get(18).get(2));
        assertEq(3,res_.get(19).size());
        assertEq(3,res_.get(19).get(0));
        assertEq(7,res_.get(19).get(1));
        assertEq(23,res_.get(19).get(2));
        assertEq(3,res_.get(20).size());
        assertEq(3,res_.get(20).get(0));
        assertEq(11,res_.get(20).get(1));
        assertEq(13,res_.get(20).get(2));
        assertEq(3,res_.get(21).size());
        assertEq(3,res_.get(21).get(0));
        assertEq(11,res_.get(21).get(1));
        assertEq(17,res_.get(21).get(2));
        assertEq(3,res_.get(22).size());
        assertEq(3,res_.get(22).get(0));
        assertEq(11,res_.get(22).get(1));
        assertEq(19,res_.get(22).get(2));
        assertEq(3,res_.get(23).size());
        assertEq(3,res_.get(23).get(0));
        assertEq(11,res_.get(23).get(1));
        assertEq(23,res_.get(23).get(2));
    }
    @Test
    public void simpleIterator1Test() {
        assertEq(0,CharList.wrapCharArray().length);
    }
}
