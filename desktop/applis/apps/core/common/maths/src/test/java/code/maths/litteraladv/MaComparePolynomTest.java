package code.maths.litteraladv;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import code.maths.matrix.Polynom;
import code.util.CustList;
import org.junit.Test;

public class MaComparePolynomTest extends EquallableMathUtil {
    @Test
    public void compare1() {
        Polynom one_ = new Polynom(new CustList<Rate>(Rate.one(),Rate.one()));
        Polynom two_ = new Polynom(new CustList<Rate>(Rate.one()));
        assertTrue(MaComparePolynom.compare(one_,two_)>0);
    }
    @Test
    public void compare2() {
        Polynom one_ = new Polynom(new CustList<Rate>(Rate.one()));
        Polynom two_ = new Polynom(new CustList<Rate>(Rate.one(),Rate.one()));
        assertTrue(MaComparePolynom.compare(one_,two_)<0);
    }
    @Test
    public void compare3() {
        Polynom one_ = new Polynom(new CustList<Rate>(new Rate(2)));
        Polynom two_ = new Polynom(new CustList<Rate>(Rate.one()));
        assertTrue(MaComparePolynom.compare(one_,two_)>0);
    }
    @Test
    public void compare4() {
        Polynom one_ = new Polynom(new CustList<Rate>(Rate.one()));
        Polynom two_ = new Polynom(new CustList<Rate>(new Rate(2)));
        assertTrue(MaComparePolynom.compare(one_,two_)<0);
    }
    @Test
    public void compare5() {
        Polynom one_ = new Polynom(new CustList<Rate>(Rate.one()));
        Polynom two_ = new Polynom(new CustList<Rate>(Rate.one()));
        assertEq(0,MaComparePolynom.compare(one_,two_));
    }
    @Test
    public void sorted1() {
        Polynom one_ = new Polynom(new CustList<Rate>(Rate.one(),Rate.one()));
        Polynom two_ = new Polynom(new CustList<Rate>(Rate.one()));
        CustList<Polynom> sorted_ = MaComparePolynom.sorted(new CustList<Polynom>(one_,two_));
        assertEq(2, sorted_.size());
        assertTrue(two_.eq(sorted_.get(0)));
        assertTrue(one_.eq(sorted_.get(1)));
    }
    @Test
    public void sorted2() {
        Polynom one_ = new Polynom(new CustList<Rate>(Rate.one()));
        Polynom two_ = new Polynom(new CustList<Rate>(Rate.one(),Rate.one()));
        CustList<Polynom> sorted_ = MaComparePolynom.sorted(new CustList<Polynom>(one_,two_));
        assertEq(2, sorted_.size());
        assertTrue(one_.eq(sorted_.get(0)));
        assertTrue(two_.eq(sorted_.get(1)));
    }
    @Test
    public void sorted3() {
        Polynom one_ = new Polynom(new CustList<Rate>(Rate.one(),Rate.one()));
        Polynom two_ = new Polynom(new CustList<Rate>(Rate.one()));
        Polynom three_ = new Polynom(new CustList<Rate>(new Rate(2)));
        CustList<Polynom> sorted_ = MaComparePolynom.sorted(new CustList<Polynom>(one_,two_,three_));
        assertEq(3, sorted_.size());
        assertTrue(two_.eq(sorted_.get(0)));
        assertTrue(three_.eq(sorted_.get(1)));
        assertTrue(one_.eq(sorted_.get(2)));
    }
    @Test
    public void sorted4() {
        Polynom one_ = new Polynom(new CustList<Rate>(Rate.one(),Rate.one()));
        Polynom two_ = new Polynom(new CustList<Rate>(new Rate(2)));
        Polynom three_ = new Polynom(new CustList<Rate>(Rate.one()));
        CustList<Polynom> sorted_ = MaComparePolynom.sorted(new CustList<Polynom>(one_,two_,three_));
        assertEq(3, sorted_.size());
        assertTrue(three_.eq(sorted_.get(0)));
        assertTrue(two_.eq(sorted_.get(1)));
        assertTrue(one_.eq(sorted_.get(2)));
    }
    @Test
    public void sorted5() {
        Polynom one_ = new Polynom(new CustList<Rate>(Rate.one(),Rate.one()));
        Polynom two_ = new Polynom(new CustList<Rate>(Rate.one()));
        Polynom three_ = new Polynom(new CustList<Rate>(Rate.one()));
        CustList<Polynom> sorted_ = MaComparePolynom.sorted(new CustList<Polynom>(one_,two_,three_));
        assertEq(3, sorted_.size());
        assertTrue(two_.eq(sorted_.get(0)));
        assertTrue(three_.eq(sorted_.get(1)));
        assertTrue(one_.eq(sorted_.get(2)));
    }
    @Test
    public void sorted6() {
        Polynom one_ = new Polynom(new CustList<Rate>(Rate.one()));
        Polynom two_ = new Polynom(new CustList<Rate>(Rate.one()));
        Polynom three_ = new Polynom(new CustList<Rate>(Rate.one(),Rate.one()));
        CustList<Polynom> sorted_ = MaComparePolynom.sorted(new CustList<Polynom>(one_,two_,three_));
        assertEq(3, sorted_.size());
        assertTrue(one_.eq(sorted_.get(0)));
        assertTrue(two_.eq(sorted_.get(1)));
        assertTrue(three_.eq(sorted_.get(2)));
    }
}
