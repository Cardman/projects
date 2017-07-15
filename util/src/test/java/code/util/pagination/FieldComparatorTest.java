package code.util.pagination;
import static code.util.opers.EquallableUtil.assertEq;

import org.junit.Test;

import code.util.pagination.FieldComparator;
import code.util.pagination.SelectedBoolean;

@SuppressWarnings("static-method")
public class FieldComparatorTest {

    @Test
    public void compare1Test() {
        FieldComparator<Integer> cmp_;
        cmp_ = new FieldComparator<Integer>();
        assertEq(0, cmp_.compare(2, 1));
        assertEq(0, cmp_.compare(2, 2));
        assertEq(0, cmp_.compare(2, 3));
    }

    @Test
    public void compare2Test() {
        FieldComparator<Integer> cmp_;
        cmp_ = new FieldComparator<Integer>();
        cmp_.setIncreasing(SelectedBoolean.YES_AND_NO);
        assertEq(0, cmp_.compare(2, 1));
        assertEq(0, cmp_.compare(2, 2));
        assertEq(0, cmp_.compare(2, 3));
    }

    @Test
    public void compare3Test() {
        FieldComparator<Integer> cmp_;
        cmp_ = new FieldComparator<Integer>();
        cmp_.setIncreasing(SelectedBoolean.YES);
        assertEq(1, cmp_.compare(2, 1));
        assertEq(0, cmp_.compare(2, 2));
        assertEq(-1, cmp_.compare(2, 3));
    }

    @Test
    public void compare4Test() {
        FieldComparator<Integer> cmp_;
        cmp_ = new FieldComparator<Integer>();
        cmp_.setIncreasing(SelectedBoolean.NO);
        assertEq(-1, cmp_.compare(2, 1));
        assertEq(0, cmp_.compare(2, 2));
        assertEq(1, cmp_.compare(2, 3));
    }
}
