package code.maths.matrix;

import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import org.junit.Test;

import static code.maths.EquallableMathUtil.assertEq;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public class PolynomTest {
    @Test
    public void new_Polynom_test() {
        Polynom p_ = new Polynom();
        assertEq(1, p_.size());
        assertEq(Rate.zero(), p_.get(0));
    }
    @Test
    public void new_Polynom_1_test() {
        Polynom p_ = new Polynom(Rate.one());
        assertEq(1, p_.size());
        assertEq(Rate.one(), p_.get(0));
    }
    @Test
    public void new_Polynom_2_test() {
        Polynom p_ = new Polynom(Rate.one(), 2);
        assertEq(2, p_.size());
        assertEq(Rate.one(), p_.get(0));
        assertEq(Rate.one(), p_.get(1));
        assertNotSame(p_.get(0),p_.get(1));
    }
    @Test
    public void new_Polynom_3_test() {
        Polynom p_ = new Polynom(Rate.one(), 2);
        Polynom c_ = new Polynom(p_);
        assertEq(2, c_.size());
        assertEq(Rate.one(), c_.get(0));
        assertEq(Rate.one(), c_.get(1));
        assertNotSame(c_.get(0),c_.get(1));
        assertNotSame(c_.get(0),p_.get(0));
        assertNotSame(c_.get(1),p_.get(1));
    }
    @Test
    public void new_Polynom_4_test() {
        Polynom p_ = Polynom.newPolynom("1 2");
        assertEq(2, p_.size());
        assertEq(Rate.one(), p_.get(0));
        assertEq(new Rate(2), p_.get(1));
    }
    @Test
    public void isZero1Test() {
        Polynom p_ = Polynom.one();
        assertTrue(!p_.isZero());
    }
    @Test
    public void isZero2Test() {
        Polynom p_ = Polynom.zero();
        assertTrue(p_.isZero());
    }
    @Test
    public void isZero3Test() {
        Polynom p_ = new Polynom();
        p_.add(new Rate(1));
        p_.add(new Rate(2));
        assertTrue(!p_.isZero());
    }
    @Test
    public void zeroTest() {
        Polynom p_ = Polynom.zero();
        assertEq(1, p_.size());
        assertEq(Rate.zero(), p_.get(0));
    }
    @Test
    public void oneTest() {
        Polynom p_ = Polynom.one();
        assertEq(1, p_.size());
        assertEq(Rate.one(), p_.get(0));
    }
    @Test
    public void zeroDgTest() {
        Polynom p_ = Polynom.zero();
        assertEq(-1, p_.dg());
    }
    @Test
    public void oneDgTest() {
        Polynom p_ = Polynom.one();
        assertEq(0, p_.dg());
    }
    @Test
    public void complexDgTest() {
        Polynom p_ = new Polynom();
        p_.add(new Rate(1));
        p_.add(new Rate(2));
        assertEq(1, p_.dg());
    }
    @Test
    public void add1Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(5));
        b_.add(new Rate(3));
        Polynom p_ = a_.addPolynom(b_);
        assertEq(2, p_.size());
        assertEq(new Rate(6), p_.get(0));
        assertEq(new Rate(5), p_.get(1));
    }
    @Test
    public void add2Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(5));
        b_.add(new Rate(3));
        b_.add(new Rate(1));
        Polynom p_ = a_.addPolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(5), p_.get(0));
        assertEq(new Rate(4), p_.get(1));
        assertEq(new Rate(3), p_.get(2));
    }
    @Test
    public void add3Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        a_.add(new Rate(7));
        Polynom b_ = new Polynom();
        b_.add(new Rate(5));
        b_.add(new Rate(3));
        Polynom p_ = a_.addPolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(6), p_.get(0));
        assertEq(new Rate(7), p_.get(1));
        assertEq(new Rate(10), p_.get(2));
    }
    @Test
    public void add4Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        a_.add(new Rate(7));
        Polynom b_ = new Polynom();
        b_.add(new Rate(-6));
        b_.add(new Rate(5));
        b_.add(new Rate(3));
        Polynom p_ = a_.addPolynom(b_);
        assertEq(2, p_.size());
        assertEq(new Rate(7), p_.get(0));
        assertEq(new Rate(10), p_.get(1));
    }
    @Test
    public void add5Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        a_.add(new Rate(7));
        Polynom b_ = new Polynom();
        b_.add(new Rate(-6));
        b_.add(new Rate(-2));
        b_.add(new Rate(3));
        Polynom p_ = a_.addPolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(10), p_.get(0));
    }
    @Test
    public void add6Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        a_.add(new Rate(7));
        Polynom b_ = new Polynom();
        b_.add(new Rate(-6));
        b_.add(new Rate(-2));
        b_.add(new Rate(-7));
        Polynom p_ = a_.addPolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void add7Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        a_.add(new Rate(7));
        Polynom b_ = new Polynom();
        Polynom p_ = a_.addPolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(6), p_.get(0));
        assertEq(new Rate(2), p_.get(1));
        assertEq(new Rate(7), p_.get(2));
    }
    @Test
    public void add8Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        a_.add(new Rate(7));
        Polynom b_ = new Polynom();
        Polynom p_ = b_.addPolynom(a_);
        assertEq(3, p_.size());
        assertEq(new Rate(6), p_.get(0));
        assertEq(new Rate(2), p_.get(1));
        assertEq(new Rate(7), p_.get(2));
    }
    @Test
    public void add9Test() {
        Polynom a_ = new Polynom();
        Polynom b_ = new Polynom();
        Polynom p_ = b_.addPolynom(a_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void minusPolynom1Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom p_ = a_.minusPolynom();
        assertEq(2, p_.size());
        assertEq(new Rate(-6), p_.get(0));
        assertEq(new Rate(-2), p_.get(1));
    }
    @Test
    public void minusPolynom2Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(5));
        b_.add(new Rate(3));
        Polynom p_ = a_.minusPolynom(b_);
        assertEq(2, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(new Rate(-1), p_.get(1));
    }
    @Test
    public void multMonomTest() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom p_ = a_.prodMonom(new Rate(3),2);
        assertEq(4, p_.size());
        assertEq(new Rate(18), p_.get(0));
        assertEq(new Rate(6), p_.get(1));
        assertEq(new Rate(0), p_.get(2));
        assertEq(new Rate(0), p_.get(3));
    }
    @Test
    public void addPolTest() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(5));
        b_.add(new Rate(3));
        b_.addPol(a_);
        assertEq(2, b_.size());
        assertEq(new Rate(11), b_.get(0));
        assertEq(new Rate(5), b_.get(1));
    }
    @Test
    public void removeNbTest() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(5));
        b_.add(new Rate(3));
        a_.removeNb(b_);
        assertEq(2, a_.size());
        assertEq(new Rate(1), a_.get(0));
        assertEq(new Rate(-1), a_.get(1));
    }
    @Test
    public void multTest() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(5));
        b_.add(new Rate(3));
        Polynom p_ = a_.multiplyPolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(30), p_.get(0));
        assertEq(new Rate(28), p_.get(1));
        assertEq(new Rate(6), p_.get(2));
    }
    @Test
    public void mult2Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(5));
        b_.add(Rate.zero());
        Polynom p_ = a_.multiplyPolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(30), p_.get(0));
        assertEq(new Rate(10), p_.get(1));
        assertEq(new Rate(0), p_.get(2));
    }
    @Test
    public void mult3Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        Polynom p_ = a_.multiplyPolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void mult4Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        Polynom p_ = b_.multiplyPolynom(a_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void dividePolynom1Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(2));
        Polynom p_ = a_.dividePolynom(b_);
        assertEq(2, p_.size());
        assertEq(new Rate(3), p_.get(0));
        assertEq(new Rate(1), p_.get(1));
    }
    @Test
    public void dividePolynom2Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(3));
        b_.add(new Rate(1));
        Polynom p_ = a_.dividePolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(2), p_.get(0));
    }
    @Test
    public void dividePolynom3Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(0));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(2));
        Polynom p_ = a_.dividePolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(3), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
        assertEq(new Rate(1), p_.get(2));
    }
    @Test
    public void dividePolynom4Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(0));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(2));
        b_.add(new Rate(0));
        Polynom p_ = a_.dividePolynom(b_);
        assertEq(2, p_.size());
        assertEq(new Rate(3), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
    }
    @Test
    public void dividePolynom5Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        Polynom p_ = a_.dividePolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void remainPolynom1Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(0));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(2));
        b_.add(new Rate(0));
        Polynom p_ = a_.remainPolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(2), p_.get(0));
    }
    @Test
    public void derivee1Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(0));
        a_.add(new Rate(2));
        Polynom p_ = a_.derivee();
        assertEq(2, p_.size());
        assertEq(new Rate(12), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
    }
    @Test
    public void derivee2Test() {
        Polynom a_ = new Polynom();
        Polynom p_ = a_.derivee();
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void powNbTest() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(0));
        a_.add(new Rate(0));
        Polynom p_ = Polynom.powNb(a_,new LgInt(2));
        assertEq(5, p_.size());
        assertEq(new Rate(36), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
        assertEq(new Rate(0), p_.get(2));
        assertEq(new Rate(0), p_.get(3));
        assertEq(new Rate(0), p_.get(4));
    }
    @Test
    public void powTest() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(0));
        a_.add(new Rate(0));
        Polynom p_ = a_.pow(2);
        assertEq(5, p_.size());
        assertEq(new Rate(36), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
        assertEq(new Rate(0), p_.get(2));
        assertEq(new Rate(0), p_.get(3));
        assertEq(new Rate(0), p_.get(4));
    }
    @Test
    public void multBy1Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(3));
        a_.multiplyBy(b_);
        assertEq(2, a_.size());
        assertEq(new Rate(18), a_.get(0));
        assertEq(new Rate(6), a_.get(1));
    }
    @Test
    public void multBy2Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.multiplyBy(a_);
        assertEq(1, b_.size());
        assertEq(new Rate(0), b_.get(0));
    }
    @Test
    public void multBy3Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        a_.multiplyBy(b_);
        assertEq(1, a_.size());
        assertEq(new Rate(0), a_.get(0));
    }
    @Test
    public void dividePolynomByTest() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(2));
        a_.divideBy(b_);
        assertEq(2, a_.size());
        assertEq(new Rate(3), a_.get(0));
        assertEq(new Rate(1), a_.get(1));
    }
    @Test
    public void remByTest() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(6));
        a_.add(new Rate(2));
        Polynom b_ = new Polynom();
        b_.add(new Rate(2));
        b_.add(new Rate(0));
        a_.remainBy(b_);
        assertEq(1, a_.size());
        assertEq(new Rate(2), a_.get(0));
    }
    @Test
    public void compo1Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(0));
        a_.add(new Rate(1));
        Polynom b_ = new Polynom();
        b_.add(new Rate(1));
        b_.add(new Rate(0));
        b_.add(new Rate(0));
        Polynom p_ = a_.comp(b_);
        assertEq(5, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
        assertEq(new Rate(0), p_.get(2));
        assertEq(new Rate(0), p_.get(3));
        assertEq(new Rate(1), p_.get(4));
    }
    @Test
    public void compo2Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(0));
        a_.add(new Rate(0));
        Polynom b_ = new Polynom();
        b_.add(new Rate(1));
        b_.add(new Rate(0));
        b_.add(new Rate(1));
        Polynom p_ = a_.comp(b_);
        assertEq(5, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
        assertEq(new Rate(2), p_.get(2));
        assertEq(new Rate(0), p_.get(3));
        assertEq(new Rate(1), p_.get(4));
    }
    @Test
    public void image1Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(2));
        a_.add(new Rate(0));
        a_.add(new Rate(0));
        Rate r_ = a_.image(new Rate(3));
        assertEq(new Rate(18), r_);
    }
    @Test
    public void image2Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(2));
        a_.add(new Rate(0));
        a_.add(new Rate(1));
        Rate r_ = a_.image(new Rate(3));
        assertEq(new Rate(19), r_);
    }
    @Test
    public void image3Test() {
        Polynom a_ = Polynom.zero();
        Rate r_ = a_.image(new Rate(3));
        assertEq(new Rate(0), r_);
    }
    @Test
    public void racines1Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(2));
        a_.add(new Rate(1));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
    }
    @Test
    public void racines2Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(0));
        a_.add(new Rate(-1));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(1), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines3Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(3));
        a_.add(new Rate(3));
        a_.add(new Rate(1));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(3, roots_.first().getDegree());
    }
    @Test
    public void racines4Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(1));
        a_.add(new Rate(-1));
        a_.add(new Rate(-1));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
        assertEq(new Rate(1), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines5Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(0));
        a_.add(new Rate(-2));
        a_.add(new Rate(0));
        a_.add(new Rate(1));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
        assertEq(new Rate(1), roots_.last().getValue());
        assertEq(2, roots_.last().getDegree());
    }
    @Test
    public void racines6Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(0));
        a_.add(new Rate(2));
        a_.add(new Rate(0));
        a_.add(new Rate(1));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(0, roots_.size());
    }
    @Test
    public void racines7Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(-1));
        a_.add(new Rate(-1));
        a_.add(new Rate(1));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(1), roots_.last().getValue());
        assertEq(2, roots_.last().getDegree());
    }
    @Test
    public void racines8Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(-2));
        a_.add(new Rate(1));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(1), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
    }
    @Test
    public void racines9Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(0));
        a_.add(new Rate(0));
        a_.add(new Rate(0));
        a_.add(new Rate(-1));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(1), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines10Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(-1));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(1), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
    }
    @Test
    public void racines11Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(1));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
    }
    @Test
    public void racines12Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(1));
        a_.add(new Rate(0));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(0), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(-1), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines13Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(1));
        a_.add(new Rate(0));
        a_.add(new Rate(0));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(0), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
        assertEq(new Rate(-1), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines14Test() {
        Polynom a_ = new Polynom();
        a_.add(new Rate(1));
        a_.add(new Rate(0));
        a_.add(new Rate(-1));
        a_.add(new Rate(0));
        a_.add(new Rate(0));
        CustList<RootPol> roots_ = a_.racines();
        assertEq(3, roots_.size());
        assertEq(new Rate(0), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
        assertEq(new Rate(-1), roots_.get(1).getValue());
        assertEq(1, roots_.get(1).getDegree());
        assertEq(new Rate(1), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines15Test() {
        Polynom a_ = new Polynom();
        CustList<RootPol> roots_ = a_.racines();
        assertEq(0, roots_.size());
    }
}
