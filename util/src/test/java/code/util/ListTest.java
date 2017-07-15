package code.util;
import static code.util.opers.EquallableUtil.assertEq;

import org.junit.Test;

import code.util.CustList;
import code.util.classestest.IntegerComparator;

@SuppressWarnings("static-method")
public class ListTest {

//    @Test
//    public void removeDuplicates1Test() {
//        CustList<String> strings_ = new CustList<String>();
//        strings_.add("myStringOne");
//        strings_.add("myStringTwo");
//        strings_.removeDuplicates();
//        assertEq(2,strings_.size());
//        assertEq("myStringOne",strings_.first());
//        assertEq("myStringTwo",strings_.get(1));
//        strings_ = new CustList<String>();
//        strings_.add("myStringOne");
//        strings_.add("myStringTwo");
//        strings_.add("myStringOne");
//        strings_.removeDuplicates();
//        assertEq(2,strings_.size());
//        assertEq("myStringOne",strings_.first());
//        assertEq("myStringTwo",strings_.get(1));
//    }

    @Test
    public void getGroupsSameCompare1Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        IntegerComparator intCmp_ = new IntegerComparator();
        CustList<? extends CustList<Integer>> groups_ = integers_.getGroupsSameCompare(intCmp_);
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
        CustList<? extends CustList<Integer>> groups_ = integers_.getGroupsSameCompare(intCmp_);
        assertEq(5, groups_.size());
        assertEq(1, groups_.get(0).size());
        assertEq(1, groups_.get(1).size());
        assertEq(1, groups_.get(2).size());
        assertEq(1, groups_.get(3).size());
        assertEq(1, groups_.get(4).size());
        assertEq(-2, groups_.get(0).first().intValue());
        assertEq(0, groups_.get(1).first().intValue());
        assertEq(1, groups_.get(2).first().intValue());
        assertEq(3, groups_.get(3).first().intValue());
        assertEq(5, groups_.get(4).first().intValue());
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
        CustList<? extends CustList<Integer>> groups_ = integers_.getGroupsSameCompare(intCmp_);
        assertEq(5, groups_.size());
        assertEq(1, groups_.get(0).size());
        assertEq(1, groups_.get(1).size());
        assertEq(1, groups_.get(2).size());
        assertEq(2, groups_.get(3).size());
        assertEq(1, groups_.get(4).size());
        assertEq(-2, groups_.get(0).first().intValue());
        assertEq(0, groups_.get(1).first().intValue());
        assertEq(1, groups_.get(2).first().intValue());
        assertEq(3, groups_.get(3).first().intValue());
        assertEq(3, groups_.get(3).last().intValue());
        assertEq(5, groups_.get(4).first().intValue());
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
        CustList<? extends CustList<Integer>> groups_ = integers_.getGroupsSameCompare(intCmp_);
        assertEq(5, groups_.size());
        assertEq(1, groups_.get(0).size());
        assertEq(1, groups_.get(1).size());
        assertEq(1, groups_.get(2).size());
        assertEq(3, groups_.get(3).size());
        assertEq(1, groups_.get(4).size());
        assertEq(-2, groups_.get(0).first().intValue());
        assertEq(0, groups_.get(1).first().intValue());
        assertEq(1, groups_.get(2).first().intValue());
        assertEq(3, groups_.get(3).first().intValue());
        assertEq(3, groups_.get(3).get(1).intValue());
        assertEq(3, groups_.get(3).last().intValue());
        assertEq(5, groups_.get(4).first().intValue());
    }

    @Test
    public void getGroupsSameCompare5Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(3);
        integers_.add(3);
        integers_.add(3);
        integers_.add(3);
        IntegerComparator intCmp_ = new IntegerComparator();
        CustList<? extends CustList<Integer>> groups_ = integers_.getGroupsSameCompare(intCmp_);
        assertEq(1, groups_.size());
        assertEq(4, groups_.get(0).size());
        assertEq(3, groups_.get(0).first().intValue());
        assertEq(3, groups_.get(0).get(1).intValue());
        assertEq(3, groups_.get(0).get(2).intValue());
        assertEq(3, groups_.get(0).last().intValue());
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
        assertEq(5, res_.get(0).intValue());
        assertEq(-2, res_.get(1).intValue());
        assertEq(3, res_.get(2).intValue());
        assertEq(0, res_.get(3).intValue());
    }

    @Test
    public void getRevers2Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(0);
        integers_.add(3);
        integers_.add(-2);
        CustList<Integer> res_ = integers_.getReverse();
        assertEq(3, res_.size());
        assertEq(-2, res_.get(0).intValue());
        assertEq(3, res_.get(1).intValue());
        assertEq(0, res_.get(2).intValue());
    }

    @Test
    public void getRevers3Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(0);
        integers_.add(3);
        CustList<Integer> res_ = integers_.getReverse();
        assertEq(2, res_.size());
        assertEq(3, res_.get(0).intValue());
        assertEq(0, res_.get(1).intValue());
    }

    @Test
    public void getRevers4Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        integers_.add(0);
        CustList<Integer> res_ = integers_.getReverse();
        assertEq(1, res_.size());
        assertEq(0, res_.get(0).intValue());
    }

    @Test
    public void getRevers5Test() {
        CustList<Integer> integers_ = new CustList<Integer>();
        CustList<Integer> res_ = integers_.getReverse();
        assertEq(0, res_.size());
    }
}
