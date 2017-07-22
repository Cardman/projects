package code.util;
import static code.util.opers.EquallableUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import code.util.Numbers;

@SuppressWarnings("static-method")
@RunWith(JUnitParamsRunner.class)
public class NumbersTest {

    Object[] equalsNumbers() {
        return $($(new Long(1),new Integer(1)),
                $(new Long(1),new Short((short) 1)),
                $(new Long(1),new Byte((byte) 1)),
                $(new Short((short) 1),new Integer(1)),
                $(new Byte((byte) 1),new Integer(1)),
                $(new Byte((byte) 1),new Short((short) 1)));
    }

    @Test
    @Parameters(method="equalsNumbers")
    public void eq1Test(Number _nb1,Number _nb2) {
        assertTrue(Numbers.eq(_nb1, _nb2));
    }

    @Test
    public void containsIndexOf1Test() {
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(3);
        assertTrue(nbs_.contains(1));
        assertTrue(!nbs_.contains(2));
        assertTrue(nbs_.contains(3));
        assertTrue(nbs_.contains((byte)1));
        assertEq(0,nbs_.indexOf((byte)1));
        assertEq(1,nbs_.indexOf((byte)3));
        assertEq(-1,nbs_.indexOf((byte)2));
    }

    @Test
    public void removeDuplicates1Test() {
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(3);
        nbs_.removeDuplicates();
        assertEq(2,nbs_.size());
        assertEq(1,nbs_.get(0).intValue());
        assertEq(3,nbs_.get(1).intValue());
    }

    @Test
    public void removeDuplicates2Test() {
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(1);
        nbs_.removeDuplicates();
        assertEq(1,nbs_.size());
        assertEq(1,nbs_.get(0).intValue());
    }

    @Test
    public void removeDuplicates3Test() {
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(3);
        nbs_.add(1);
        nbs_.removeDuplicates();
        assertEq(2,nbs_.size());
        assertEq(1,nbs_.get(0).intValue());
        assertEq(3,nbs_.get(1).intValue());
    }

    @Test
    public void removeDuplicates4Test() {
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(3);
        nbs_.add(1);
        nbs_.add(3);
        nbs_.removeDuplicates();
        assertEq(2,nbs_.size());
        assertEq(1,nbs_.get(0).intValue());
        assertEq(3,nbs_.get(1).intValue());
    }

    @Test
    public void removeDuplicates5Test() {
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(1);
        nbs_.add(3);
        nbs_.removeDuplicates();
        assertEq(2,nbs_.size());
        assertEq(1,nbs_.get(0).intValue());
        assertEq(3,nbs_.get(1).intValue());
    }

    @Test
    public void removeDuplicates6Test() {
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(1);
        nbs_.add(3);
        nbs_.add(3);
        nbs_.removeDuplicates();
        assertEq(2,nbs_.size());
        assertEq(1,nbs_.get(0).intValue());
        assertEq(3,nbs_.get(1).intValue());
    }

    @Test
    public void min1Test() {
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(3);
        assertEq((long)1,(long)nbs_.getMinimum());
        nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        assertEq((long)1,(long)nbs_.getMinimum());
        nbs_ = new Numbers<Integer>();
        assertNull(nbs_.getMinimum());
    }

    @Test
    public void max1Test() {
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(1);
        nbs_.add(3);
        assertEq((long)3,(long)nbs_.getMaximum());
        nbs_ = new Numbers<Integer>();
        nbs_.add(3);
        assertEq((long)3,(long)nbs_.getMaximum());
        nbs_ = new Numbers<Integer>();
        assertNull(nbs_.getMaximum());
    }

    @Test
    public void sort1Test() {
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        nbs_.add(5);
        nbs_.add(1);
        nbs_.add(4);
        nbs_.add(10);
        nbs_.add(8);
        nbs_.sort();
        assertEq(5,nbs_.size());
        assertEq(1,nbs_.first().intValue());
        assertEq(4,nbs_.get(1).intValue());
        assertEq(5,nbs_.get(2).intValue());
        assertEq(8,nbs_.get(3).intValue());
        assertEq(10,nbs_.get(4).intValue());
    }

    @Test
    public void getAllIndexes1Test() {
    	Numbers<Integer> nbs_ = new Numbers<Integer>();
    	assertEq(0,nbs_.getAllIndexes().size());
    }

    @Test
    public void getAllIndexes2Test() {
    	Numbers<Integer> nbs_ = new Numbers<Integer>();
    	nbs_.add(2);
    	nbs_.add(3);
    	nbs_.add(4);
    	EqList<Numbers<Integer>> indexes_;
    	indexes_ = nbs_.getAllIndexes();
    	assertEq(24,indexes_.size());
    	assertEq(3, indexes_.get(0).size());
    	assertEq(0, indexes_.get(0).get(0));
    	assertEq(0, indexes_.get(0).get(1));
    	assertEq(0, indexes_.get(0).get(2));
    	assertEq(3, indexes_.get(1).size());
    	assertEq(0, indexes_.get(1).get(0));
    	assertEq(0, indexes_.get(1).get(1));
    	assertEq(1, indexes_.get(1).get(2));
    	assertEq(3, indexes_.get(2).size());
    	assertEq(0, indexes_.get(2).get(0));
    	assertEq(0, indexes_.get(2).get(1));
    	assertEq(2, indexes_.get(2).get(2));
    	assertEq(3, indexes_.get(3).size());
    	assertEq(0, indexes_.get(3).get(0));
    	assertEq(0, indexes_.get(3).get(1));
    	assertEq(3, indexes_.get(3).get(2));
    	assertEq(3, indexes_.get(4).size());
    	assertEq(0, indexes_.get(4).get(0));
    	assertEq(1, indexes_.get(4).get(1));
    	assertEq(0, indexes_.get(4).get(2));
    	assertEq(3, indexes_.get(5).size());
    	assertEq(0, indexes_.get(5).get(0));
    	assertEq(1, indexes_.get(5).get(1));
    	assertEq(1, indexes_.get(5).get(2));
    	assertEq(3, indexes_.get(6).size());
    	assertEq(0, indexes_.get(6).get(0));
    	assertEq(1, indexes_.get(6).get(1));
    	assertEq(2, indexes_.get(6).get(2));
    	assertEq(3, indexes_.get(7).size());
    	assertEq(0, indexes_.get(7).get(0));
    	assertEq(1, indexes_.get(7).get(1));
    	assertEq(3, indexes_.get(7).get(2));
    	assertEq(3, indexes_.get(8).size());
    	assertEq(0, indexes_.get(8).get(0));
    	assertEq(2, indexes_.get(8).get(1));
    	assertEq(0, indexes_.get(8).get(2));
    	assertEq(3, indexes_.get(9).size());
    	assertEq(0, indexes_.get(9).get(0));
    	assertEq(2, indexes_.get(9).get(1));
    	assertEq(1, indexes_.get(9).get(2));
    	assertEq(3, indexes_.get(10).size());
    	assertEq(0, indexes_.get(10).get(0));
    	assertEq(2, indexes_.get(10).get(1));
    	assertEq(2, indexes_.get(10).get(2));
    	assertEq(3, indexes_.get(11).size());
    	assertEq(0, indexes_.get(11).get(0));
    	assertEq(2, indexes_.get(11).get(1));
    	assertEq(3, indexes_.get(11).get(2));
    	assertEq(3, indexes_.get(12).size());
    	assertEq(1, indexes_.get(12).get(0));
    	assertEq(0, indexes_.get(12).get(1));
    	assertEq(0, indexes_.get(12).get(2));
    	assertEq(3, indexes_.get(13).size());
    	assertEq(1, indexes_.get(13).get(0));
    	assertEq(0, indexes_.get(13).get(1));
    	assertEq(1, indexes_.get(13).get(2));
    	assertEq(3, indexes_.get(14).size());
    	assertEq(1, indexes_.get(14).get(0));
    	assertEq(0, indexes_.get(14).get(1));
    	assertEq(2, indexes_.get(14).get(2));
    	assertEq(3, indexes_.get(15).size());
    	assertEq(1, indexes_.get(15).get(0));
    	assertEq(0, indexes_.get(15).get(1));
    	assertEq(3, indexes_.get(15).get(2));
    	assertEq(3, indexes_.get(16).size());
    	assertEq(1, indexes_.get(16).get(0));
    	assertEq(1, indexes_.get(16).get(1));
    	assertEq(0, indexes_.get(16).get(2));
    	assertEq(3, indexes_.get(17).size());
    	assertEq(1, indexes_.get(17).get(0));
    	assertEq(1, indexes_.get(17).get(1));
    	assertEq(1, indexes_.get(17).get(2));
    	assertEq(3, indexes_.get(18).size());
    	assertEq(1, indexes_.get(18).get(0));
    	assertEq(1, indexes_.get(18).get(1));
    	assertEq(2, indexes_.get(18).get(2));
    	assertEq(3, indexes_.get(19).size());
    	assertEq(1, indexes_.get(19).get(0));
    	assertEq(1, indexes_.get(19).get(1));
    	assertEq(3, indexes_.get(19).get(2));
    	assertEq(3, indexes_.get(20).size());
    	assertEq(1, indexes_.get(20).get(0));
    	assertEq(2, indexes_.get(20).get(1));
    	assertEq(0, indexes_.get(20).get(2));
    	assertEq(3, indexes_.get(21).size());
    	assertEq(1, indexes_.get(21).get(0));
    	assertEq(2, indexes_.get(21).get(1));
    	assertEq(1, indexes_.get(21).get(2));
    	assertEq(3, indexes_.get(22).size());
    	assertEq(1, indexes_.get(22).get(0));
    	assertEq(2, indexes_.get(22).get(1));
    	assertEq(2, indexes_.get(22).get(2));
    	assertEq(3, indexes_.get(23).size());
    	assertEq(1, indexes_.get(23).get(0));
    	assertEq(2, indexes_.get(23).get(1));
    	assertEq(3, indexes_.get(23).get(2));
    }
}
