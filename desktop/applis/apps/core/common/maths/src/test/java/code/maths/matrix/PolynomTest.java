package code.maths.matrix;

import code.maths.EquallableMathUtil;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import org.junit.Test;

public class PolynomTest extends EquallableMathUtil {
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
        Polynom p_ = binome(1, 2);
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
        Polynom p_ = binome(1, 2);
        assertEq(1, p_.dg());
    }
    @Test
    public void add1Test() {
        Polynom a_ = binome(1, 2);
        Polynom b_ = binome(5, 3);
        Polynom p_ = a_.addPolynom(b_);
        assertEq(2, p_.size());
        assertEq(new Rate(6), p_.get(0));
        assertEq(new Rate(5), p_.get(1));
    }
    @Test
    public void add2Test() {
        Polynom a_ = binome(1, 2);
        Polynom b_ = trinome(5, 3, 1);
        Polynom p_ = a_.addPolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(5), p_.get(0));
        assertEq(new Rate(4), p_.get(1));
        assertEq(new Rate(3), p_.get(2));
    }
    @Test
    public void add3Test() {
        Polynom a_ = trinome(6, 2, 7);
        Polynom b_ = binome(5, 3);
        Polynom p_ = a_.addPolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(6), p_.get(0));
        assertEq(new Rate(7), p_.get(1));
        assertEq(new Rate(10), p_.get(2));
    }
    @Test
    public void add4Test() {
        Polynom a_ = trinome(6, 2, 7);
        Polynom b_ = trinome(-6, 5, 3);
        Polynom p_ = a_.addPolynom(b_);
        assertEq(2, p_.size());
        assertEq(new Rate(7), p_.get(0));
        assertEq(new Rate(10), p_.get(1));
    }
    @Test
    public void add5Test() {
        Polynom a_ = trinome(6, 2, 7);
        Polynom b_ = trinome(-6, -2, 3);
        Polynom p_ = a_.addPolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(10), p_.get(0));
    }
    @Test
    public void add6Test() {
        Polynom a_ = trinome(6, 2, 7);
        Polynom b_ = trinome(-6, -2, -7);
        Polynom p_ = a_.addPolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void add7Test() {
        Polynom a_ = trinome(6, 2, 7);
        Polynom b_ = new Polynom();
        Polynom p_ = a_.addPolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(6), p_.get(0));
        assertEq(new Rate(2), p_.get(1));
        assertEq(new Rate(7), p_.get(2));
    }
    @Test
    public void add8Test() {
        Polynom a_ = trinome(6, 2, 7);
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
        Polynom a_ = binome(6, 2);
        Polynom p_ = a_.minusPolynom();
        assertEq(2, p_.size());
        assertEq(new Rate(-6), p_.get(0));
        assertEq(new Rate(-2), p_.get(1));
    }
    @Test
    public void minusPolynom2Test() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = binome(5, 3);
        Polynom p_ = a_.minusPolynom(b_);
        assertEq(2, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(new Rate(-1), p_.get(1));
    }
    @Test
    public void multMonomTest() {
        Polynom a_ = binome(6, 2);
        Polynom p_ = a_.prodMonom(new Rate(3),2);
        assertEq(4, p_.size());
        assertEq(new Rate(18), p_.get(0));
        assertEq(new Rate(6), p_.get(1));
        assertEq(new Rate(0), p_.get(2));
        assertEq(new Rate(0), p_.get(3));
    }
    @Test
    public void addPolTest() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = binome(5, 3);
        b_.addPol(a_);
        assertEq(2, b_.size());
        assertEq(new Rate(11), b_.get(0));
        assertEq(new Rate(5), b_.get(1));
    }
    @Test
    public void removeNbTest() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = binome(5, 3);
        a_.removeNb(b_);
        assertEq(2, a_.size());
        assertEq(new Rate(1), a_.get(0));
        assertEq(new Rate(-1), a_.get(1));
    }
    @Test
    public void multTest() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = binome(5, 3);
        Polynom p_ = a_.multiplyPolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(30), p_.get(0));
        assertEq(new Rate(28), p_.get(1));
        assertEq(new Rate(6), p_.get(2));
    }
    @Test
    public void mult2Test() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = binome(5, 0);
        Polynom p_ = a_.multiplyPolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(30), p_.get(0));
        assertEq(new Rate(10), p_.get(1));
        assertEq(new Rate(0), p_.get(2));
    }

    @Test
    public void mult3Test() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = new Polynom();
        Polynom p_ = a_.multiplyPolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void mult4Test() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = new Polynom();
        Polynom p_ = b_.multiplyPolynom(a_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void dividePolynom1Test() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = monom(2);
        Polynom p_ = a_.dividePolynom(b_);
        assertEq(2, p_.size());
        assertEq(new Rate(3), p_.get(0));
        assertEq(new Rate(1), p_.get(1));
    }
    @Test
    public void dividePolynom2Test() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = binome(3, 1);
        Polynom p_ = a_.dividePolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(2), p_.get(0));
    }
    @Test
    public void dividePolynom3Test() {
        Polynom a_ = trinome(6, 0, 2);
        Polynom b_ = monom(2);
        Polynom p_ = a_.dividePolynom(b_);
        assertEq(3, p_.size());
        assertEq(new Rate(3), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
        assertEq(new Rate(1), p_.get(2));
    }
    @Test
    public void dividePolynom4Test() {
        Polynom a_ = trinome(6, 0, 2);
        Polynom b_ = binome(2, 0);
        Polynom p_ = a_.dividePolynom(b_);
        assertEq(2, p_.size());
        assertEq(new Rate(3), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
    }
    @Test
    public void dividePolynom5Test() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = new Polynom();
        Polynom p_ = a_.dividePolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void dividePolynom6Test() {
        Polynom a_ = quintinom(1, 0, 0, 0, -1);
        Polynom b_ = quintinom(1, 0, 3, 0, -4);
        Polynom p_ = a_.dividePolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(1), p_.get(0));
    }
    @Test
    public void remainPolynom1Test() {
        Polynom a_ = trinome(6, 0, 2);
        Polynom b_ = binome(2, 0);
        Polynom p_ = a_.remainPolynom(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(2), p_.get(0));
    }
    @Test
    public void derivee1Test() {
        Polynom a_ = trinome(6, 0, 2);
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
        Polynom a_ = trinome(6, 0, 0);
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
        Polynom a_ = trinome(6, 0, 0);
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
        Polynom a_ = binome(6, 2);
        Polynom b_ = monom(3);
        a_.multiplyBy(b_);
        assertEq(2, a_.size());
        assertEq(new Rate(18), a_.get(0));
        assertEq(new Rate(6), a_.get(1));
    }
    @Test
    public void multBy2Test() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = new Polynom();
        b_.multiplyBy(a_);
        assertEq(1, b_.size());
        assertEq(new Rate(0), b_.get(0));
    }
    @Test
    public void multBy3Test() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = new Polynom();
        a_.multiplyBy(b_);
        assertEq(1, a_.size());
        assertEq(new Rate(0), a_.get(0));
    }
    @Test
    public void dividePolynomByTest() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = monom(2);
        a_.divideBy(b_);
        assertEq(2, a_.size());
        assertEq(new Rate(3), a_.get(0));
        assertEq(new Rate(1), a_.get(1));
    }
    @Test
    public void remByTest() {
        Polynom a_ = binome(6, 2);
        Polynom b_ = binome(2, 0);
        a_.remainBy(b_);
        assertEq(1, a_.size());
        assertEq(new Rate(2), a_.get(0));
    }
    @Test
    public void compo1Test() {
        Polynom a_ = trinome(1, 0, 1);
        Polynom b_ = trinome(1, 0, 0);
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
        Polynom a_ = trinome(1, 0, 0);
        Polynom b_ = trinome(1, 0, 1);
        Polynom p_ = a_.comp(b_);
        assertEq(5, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
        assertEq(new Rate(2), p_.get(2));
        assertEq(new Rate(0), p_.get(3));
        assertEq(new Rate(1), p_.get(4));
    }
    @Test
    public void compo3Test() {
        Polynom a_ = monom(2);
        Polynom b_ = new Polynom();
        Polynom p_ = a_.comp(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(2), p_.get(0));
    }
    @Test
    public void compo4Test() {
        Polynom a_ = new Polynom();
        Polynom b_ = new Polynom();
        Polynom p_ = a_.comp(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void compo5Test() {
        Polynom a_ = new Polynom();
        Polynom b_ = monom(2);
        Polynom p_ = a_.comp(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void compo6Test() {
        Polynom a_ = binome(2,0);
        Polynom b_ = new Polynom();
        Polynom p_ = a_.comp(b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }

    @Test
    public void image1Test() {
        Polynom a_ = trinome(2, 0, 0);
        Rate r_ = a_.image(new Rate(3));
        assertEq(new Rate(18), r_);
    }
    @Test
    public void image2Test() {
        Polynom a_ = trinome(2, 0, 1);
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
        Polynom a_ = trinome(1, 2, 1);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
    }
    @Test
    public void racines2Test() {
        Polynom a_ = trinome(1, 0, -1);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(1), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines3Test() {
        Polynom a_ = quadrinom(1, 3, 3, 1);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(3, roots_.first().getDegree());
    }
    @Test
    public void racines4Test() {
        Polynom a_ = quadrinom(1, 1, -1, -1);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
        assertEq(new Rate(1), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines5Test() {
        Polynom a_ = quintinom(1, 0, -2, 0, 1);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
        assertEq(new Rate(1), roots_.last().getValue());
        assertEq(2, roots_.last().getDegree());
    }
    @Test
    public void racines6Test() {
        Polynom a_ = quintinom(1, 0, 2, 0, 1);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(0, roots_.size());
    }
    @Test
    public void racines7Test() {
        Polynom a_ = quadrinom(1, -1, -1, 1);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(1), roots_.last().getValue());
        assertEq(2, roots_.last().getDegree());
    }
    @Test
    public void racines8Test() {
        Polynom a_ = trinome(1, -2, 1);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(1), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
    }
    @Test
    public void racines9Test() {
        Polynom a_ = quintinom(1, 0, 0, 0, -1);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(1), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines10Test() {
        Polynom a_ = binome(1, -1);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(1), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
    }
    @Test
    public void racines11Test() {
        Polynom a_ = binome(1, 1);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
    }
    @Test
    public void racines12Test() {
        Polynom a_ = trinome(1, 1, 0);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(0), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(-1), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines13Test() {
        Polynom a_ = quadrinom(1, 1, 0, 0);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(0), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
        assertEq(new Rate(-1), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines14Test() {
        Polynom a_ = quintinom(1, 0, -1, 0, 0);
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
    @Test
    public void racines16Test() {
        Polynom a_ = trinome(1, 4, 4);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(-2), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
    }
    @Test
    public void racines17Test() {
        Polynom a_ = trinome(1, 0, -4);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-2), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(2), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines18Test() {
        Polynom a_ = quadrinom(1, 6, 12, 8);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(-2), roots_.first().getValue());
        assertEq(3, roots_.first().getDegree());
    }

    @Test
    public void racines19Test() {
        Polynom a_ = quadrinom(1, 2, -4, -8);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-2), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
        assertEq(new Rate(2), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines20Test() {
        Polynom a_ = quintinom(1, 0, -8, 0, 16);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-2), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
        assertEq(new Rate(2), roots_.last().getValue());
        assertEq(2, roots_.last().getDegree());
    }
    @Test
    public void racines21Test() {
        Polynom a_ = quintinom(1, 0, 4, 0, 16);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(0, roots_.size());
    }
    @Test
    public void racines22Test() {
        Polynom a_ = quadrinom(1, -2, -4, 8);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-2), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(2), roots_.last().getValue());
        assertEq(2, roots_.last().getDegree());
    }
    @Test
    public void racines23Test() {
        Polynom a_ = trinome(1, -4, 4);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(2), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
    }
    @Test
    public void racines24Test() {
        Polynom a_ = quintinom(1, 0, 0, 0, -16);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(-2), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(2), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines25Test() {
        Polynom a_ = binome(1, -2);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(2), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
    }
    @Test
    public void racines26Test() {
        Polynom a_ = binome(1, 2);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(-2), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
    }
    @Test
    public void racines27Test() {
        Polynom a_ = trinome(1, 2, 0);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(0), roots_.first().getValue());
        assertEq(1, roots_.first().getDegree());
        assertEq(new Rate(-2), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines28Test() {
        Polynom a_ = quadrinom(1, 2, 0, 0);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(2, roots_.size());
        assertEq(new Rate(0), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
        assertEq(new Rate(-2), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines29Test() {
        Polynom a_ = quintinom(1, 0, -4, 0, 0);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(3, roots_.size());
        assertEq(new Rate(0), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
        assertEq(new Rate(-2), roots_.get(1).getValue());
        assertEq(1, roots_.get(1).getDegree());
        assertEq(new Rate(2), roots_.last().getValue());
        assertEq(1, roots_.last().getDegree());
    }
    @Test
    public void racines30Test() {
        Polynom a_ = trinome(2, 4, 2);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(-1), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
    }
    @Test
    public void racines31Test() {
        Polynom a_ = trinome(2, -4, 2);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(1), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
    }
    @Test
    public void racines32Test() {
        Polynom a_ = trinome(2, 8, 8);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(-2), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
    }
    @Test
    public void racines33Test() {
        Polynom a_ = trinome(2, -8, 8);
        CustList<RootPol> roots_ = a_.racines();
        assertEq(1, roots_.size());
        assertEq(new Rate(2), roots_.first().getValue());
        assertEq(2, roots_.first().getDegree());
    }

    @Test
    public void interpolation1Test() {
        CustList<RateImage> l_ = new CustList<RateImage>();
        l_.add(new RateImage(new Rate(-1), new Rate(0)));
        l_.add(new RateImage(new Rate(0), new Rate(-1)));
        l_.add(new RateImage(new Rate(1), new Rate(0)));
        Polynom p_ = Polynom.interpolation(l_);
        assertEq(3, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
        assertEq(new Rate(-1), p_.get(2));
    }
    @Test
    public void interpolation2Test() {
        CustList<RateImage> l_ = new CustList<RateImage>();
        Polynom p_ = Polynom.interpolation(l_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void factor1Test() {
        Polynom a_ = trinome(-1, 0, 1);
        CustList<Polynom> p_ = a_.factor();
        assertEq(2, p_.size());
        assertEq(2, p_.get(0).size());
        assertEq(new Rate(1), p_.get(0).get(0));
        assertEq(new Rate(1), p_.get(0).get(1));
        assertEq(2, p_.get(1).size());
        assertEq(new Rate(1), p_.get(1).get(0));
        assertEq(new Rate(-1), p_.get(1).get(1));
    }
    @Test
    public void factor2Test() {
        Polynom a_ = trinome(1, 0, 1);
        CustList<Polynom> p_ = a_.factor();
        assertEq(1, p_.size());
        assertEq(3, p_.get(0).size());
        assertEq(new Rate(1), p_.get(0).get(0));
        assertEq(new Rate(0), p_.get(0).get(1));
        assertEq(new Rate(1), p_.get(0).get(2));
    }
    @Test
    public void factor3Test() {
        Polynom a_ = new Polynom();
        CustList<Polynom> p_ = a_.factor();
        assertEq(0, p_.size());
    }
    @Test
    public void factor4Test() {
        Polynom a_ = quintinom(1, 0, 0, 0, -1);
        CustList<Polynom> p_ = a_.factor();
        assertEq(3, p_.size());
        assertEq(2, p_.get(0).size());
        assertEq(new Rate(1), p_.get(0).get(0));
        assertEq(new Rate(1), p_.get(0).get(1));
        assertEq(2, p_.get(1).size());
        assertEq(new Rate(1), p_.get(1).get(0));
        assertEq(new Rate(-1), p_.get(1).get(1));
        assertEq(3, p_.get(2).size());
        assertEq(new Rate(1), p_.get(2).get(0));
        assertEq(new Rate(0), p_.get(2).get(1));
        assertEq(new Rate(1), p_.get(2).get(2));
    }
    @Test
    public void pgcd1Test() {
        Polynom a_ = binome(1, 1);
        Polynom b_ = binome(1, -1);
        Polynom p_ = Polynom.pgcd(a_,b_);
        assertEq(1, p_.size());
        assertEq(new Rate(2), p_.get(0));
    }
    @Test
    public void pgcd2Test() {
        Polynom a_ = binome(2, 2);
        Polynom b_ = binome(1, 1);
        Polynom p_ = Polynom.pgcd(a_,b_);
        assertEq(2, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(new Rate(1), p_.get(1));
    }
    @Test
    public void pgcd3Test() {
        Polynom a_ = binome(2, 1);
        Polynom b_ = monom(0);
        Polynom p_ = Polynom.pgcd(a_,b_);
        assertEq(2, p_.size());
        assertEq(new Rate(2), p_.get(0));
        assertEq(new Rate(1), p_.get(1));
    }
    @Test
    public void ppcm1Test() {
        Polynom a_ = binome(1, 1);
        Polynom b_ = binome(1, -1);
        Polynom p_ = Polynom.ppcm(a_,b_);
        assertEq(3, p_.size());
        assertEq(new Rate(1,2), p_.get(0));
        assertEq(new Rate(0), p_.get(1));
        assertEq(new Rate(-1,2), p_.get(2));
    }
    @Test
    public void ppcm2Test() {
        Polynom a_ = binome(2, 2);
        Polynom b_ = binome(1, 1);
        Polynom p_ = Polynom.ppcm(a_,b_);
        assertEq(2, p_.size());
        assertEq(new Rate(2), p_.get(0));
        assertEq(new Rate(2), p_.get(1));
    }
    @Test
    public void ppcm3Test() {
        Polynom a_ = binome(2, 1);
        Polynom b_ = monom(0);
        Polynom p_ = Polynom.ppcm(a_,b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void ppcm4Test() {
        Polynom a_ = monom(0);
        Polynom b_ = monom(0);
        Polynom p_ = Polynom.ppcm(a_,b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void ppcm5Test() {
        Polynom a_ = monom(0);
        Polynom b_ = binome(2, 1);
        Polynom p_ = Polynom.ppcm(a_,b_);
        assertEq(1, p_.size());
        assertEq(new Rate(0), p_.get(0));
    }
    @Test
    public void idBezoutPgcdPpcm1Test() {
        Polynom a_ = binome(1, -1);
        Polynom b_ = binome(2, 1);
        Polynom p_ = Polynom.idBezoutPgcdPpcm(a_,b_).getFirst();
        Polynom q_ = Polynom.idBezoutPgcdPpcm(a_,b_).getSecond();
        assertEq(1, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(1, q_.size());
        assertEq(new Rate(-1,2), q_.get(0));
        Polynom s_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPgcd();
        Polynom t_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPpcm();
        assertEq(1, s_.size());
        assertEq(new Rate(-3,2), s_.get(0));
        assertEq(3, t_.size());
        assertEq(new Rate(-4,3), t_.get(0));
        assertEq(new Rate(2,3), t_.get(1));
        assertEq(new Rate(2,3), t_.get(2));
    }
    @Test
    public void idBezoutPgcdPpcm2Test() {
        Polynom a_ = trinome(1, 0, -1);
        Polynom b_ = trinome(1, -2, 1);
        Polynom p_ = Polynom.idBezoutPgcdPpcm(a_,b_).getFirst();
        Polynom q_ = Polynom.idBezoutPgcdPpcm(a_,b_).getSecond();
        assertEq(1, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(1, q_.size());
        assertEq(new Rate(-1), q_.get(0));
        Polynom s_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPgcd();
        Polynom t_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPpcm();
        assertEq(2, s_.size());
        assertEq(new Rate(2), s_.get(0));
        assertEq(new Rate(-2), s_.get(1));
        assertEq(4, t_.size());
        assertEq(new Rate(1,2), t_.get(0));
        assertEq(new Rate(-1,2), t_.get(1));
        assertEq(new Rate(-1,2), t_.get(2));
        assertEq(new Rate(1,2), t_.get(3));
    }
    @Test
    public void idBezoutPgcdPpcm3Test() {
        Polynom a_ = trinome(1, 0, -1);
        Polynom b_ = trinome(1, 2, 1);
        Polynom p_ = Polynom.idBezoutPgcdPpcm(a_,b_).getFirst();
        Polynom q_ = Polynom.idBezoutPgcdPpcm(a_,b_).getSecond();
        assertEq(1, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(1, q_.size());
        assertEq(new Rate(-1), q_.get(0));
        Polynom s_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPgcd();
        Polynom t_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPpcm();
        assertEq(2, s_.size());
        assertEq(new Rate(-2), s_.get(0));
        assertEq(new Rate(-2), s_.get(1));
        assertEq(4, t_.size());
        assertEq(new Rate(-1,2), t_.get(0));
        assertEq(new Rate(-1,2), t_.get(1));
        assertEq(new Rate(1,2), t_.get(2));
        assertEq(new Rate(1,2), t_.get(3));
    }
    @Test
    public void idBezoutPgcdPpcm4Test() {
        Polynom a_ = quintinom(1, 0, 0, 0, -1);
        Polynom b_ = quintinom(1, 0, 3, 0, -4);
        Polynom p_ = Polynom.idBezoutPgcdPpcm(a_,b_).getFirst();
        Polynom q_ = Polynom.idBezoutPgcdPpcm(a_,b_).getSecond();
        assertEq(1, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(1, q_.size());
        assertEq(new Rate(-1), q_.get(0));
        Polynom s_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPgcd();
        Polynom t_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPpcm();
        assertEq(3, s_.size());
        assertEq(new Rate(-3), s_.get(0));
        assertEq(new Rate(0), s_.get(1));
        assertEq(new Rate(3), s_.get(2));
        assertEq(7, t_.size());
        assertEq(new Rate(-1,3), t_.get(0));
        assertEq(new Rate(0), t_.get(1));
        assertEq(new Rate(-4,3), t_.get(2));
        assertEq(new Rate(0), t_.get(3));
        assertEq(new Rate(1,3), t_.get(4));
        assertEq(new Rate(0), t_.get(5));
        assertEq(new Rate(4,3), t_.get(6));
    }
    @Test
    public void idBezoutPgcdPpcm5Test() {
        Polynom a_ = monom(0);
        Polynom b_ = quintinom(1, 0, 3, 0, -4);
        Polynom p_ = Polynom.idBezoutPgcdPpcm(a_,b_).getFirst();
        Polynom q_ = Polynom.idBezoutPgcdPpcm(a_,b_).getSecond();
        assertEq(1, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(1, q_.size());
        assertEq(new Rate(1), q_.get(0));
        Polynom s_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPgcd();
        Polynom t_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPpcm();
        assertEq(5, s_.size());
        assertEq(new Rate(1), s_.get(0));
        assertEq(new Rate(0), s_.get(1));
        assertEq(new Rate(3), s_.get(2));
        assertEq(new Rate(0), s_.get(3));
        assertEq(new Rate(-4), s_.get(4));
        assertEq(1, t_.size());
        assertEq(new Rate(0), t_.get(0));
    }
    @Test
    public void idBezoutPgcdPpcm6Test() {
        Polynom a_ = quintinom(1, 0, 3, 0, -4);
        Polynom b_ = monom(0);
        Polynom p_ = Polynom.idBezoutPgcdPpcm(a_,b_).getFirst();
        Polynom q_ = Polynom.idBezoutPgcdPpcm(a_,b_).getSecond();
        assertEq(1, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(1, q_.size());
        assertEq(new Rate(1), q_.get(0));
        Polynom s_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPgcd();
        Polynom t_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPpcm();
        assertEq(5, s_.size());
        assertEq(new Rate(1), s_.get(0));
        assertEq(new Rate(0), s_.get(1));
        assertEq(new Rate(3), s_.get(2));
        assertEq(new Rate(0), s_.get(3));
        assertEq(new Rate(-4), s_.get(4));
        assertEq(1, t_.size());
        assertEq(new Rate(0), t_.get(0));
    }
    @Test
    public void idBezoutPgcdPpcm7Test() {
        Polynom a_ = monom(0);
        Polynom b_ = monom(0);
        Polynom p_ = Polynom.idBezoutPgcdPpcm(a_,b_).getFirst();
        Polynom q_ = Polynom.idBezoutPgcdPpcm(a_,b_).getSecond();
        assertEq(1, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(1, q_.size());
        assertEq(new Rate(1), q_.get(0));
        Polynom s_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPgcd();
        Polynom t_ = Polynom.idBezoutPgcdPpcm(a_,b_).getPpcm();
        assertEq(1, s_.size());
        assertEq(new Rate(0), s_.get(0));
        assertEq(1, t_.size());
        assertEq(new Rate(0), t_.get(0));
    }
    @Test
    public void imageMat1Test() {
        Polynom a_ = binome(1, 2);
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        Matrix m_ = a_.image(mat_);
        assertEq(2, m_.nbLines());
        assertEq(2, m_.nbCols());
        assertEq(new Rate(3), m_.cell(0, 0));
        assertEq(Rate.zero(), m_.cell(0, 1));
        assertEq(Rate.zero(), m_.cell(1, 0));
        assertEq(new Rate(3), m_.cell(1, 1));
    }
    @Test
    public void imageMat2Test() {
        Polynom a_ = new Polynom();
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        Matrix m_ = a_.image(mat_);
        assertEq(2, m_.nbLines());
        assertEq(2, m_.nbCols());
        assertEq(Rate.zero(), m_.cell(0, 0));
        assertEq(Rate.zero(), m_.cell(0, 1));
        assertEq(Rate.zero(), m_.cell(1, 0));
        assertEq(Rate.zero(), m_.cell(1, 1));
    }
    @Test
    public void displayTest() {
        Polynom a_ = quintinom(1, 0, 3, 0, -4);
        assertEq("1 0 3 0 -4",a_.display());
    }
    @Test
    public void eq1Test() {
        Polynom a_ = quintinom(1, 0, 3, 0, -4);
        assertTrue(a_.eq(a_));
        Polynom b_ = quintinom(1, 0, -3, 0, -4);
        assertTrue(!a_.eq(b_));
        assertTrue(!b_.eq(a_));
        assertTrue(Polynom.eq(a_,a_));
        assertTrue(!Polynom.eq(b_,a_));
        assertTrue(!Polynom.eq(a_,b_));
        assertTrue(a_.isEqualTo(a_));
        assertTrue(!a_.isEqualTo(b_));
        assertTrue(!b_.isEqualTo(a_));
    }

    private static Polynom quadrinom(int _one, int _two, int _three, int _four) {
        return new Polynom(new CustList<Rate>(new Rate(_one),new Rate(_two),new Rate(_three),new Rate(_four)));
    }

    private static Polynom trinome(int _one, int _two, int _three) {
        return new Polynom(new CustList<Rate>(new Rate(_one),new Rate(_two),new Rate(_three)));
    }

    private static Polynom binome(int _one, int _two) {
        return new Polynom(new CustList<Rate>(new Rate(_one),new Rate(_two)));
    }

    private static Polynom monom(int _one) {
        return new Polynom(new CustList<Rate>(new Rate(_one)));
    }

    private static Polynom quintinom(int _one, int _two, int _three, int _four, int _five) {
        return new Polynom(new CustList<Rate>(new Rate(_one), new Rate(_two), new Rate(_three), new Rate(_four),new Rate(_five)));
    }



}
