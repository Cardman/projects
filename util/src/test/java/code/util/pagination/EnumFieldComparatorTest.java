package code.util.pagination;
import static code.util.EquallableExUtil.assertEq;

import org.junit.Test;

import code.util.EnumMap;
import code.util.classestest.EnumDigitExample;
import code.util.pagination.EnumFieldComparator;
import code.util.pagination.SelectedBoolean;

@SuppressWarnings("static-method")
public class EnumFieldComparatorTest {

    @Test
    public void compare1Test() {
        EnumFieldComparator<EnumDigitExample> cmp_;
        cmp_ = new EnumFieldComparator<EnumDigitExample>();
        EnumMap<EnumDigitExample, String> translation_;
        translation_ = new EnumMap<EnumDigitExample, String>();
        translation_.put(EnumDigitExample.ONE, EnumDigitExample.ONE.name());
        translation_.put(EnumDigitExample.TWO, EnumDigitExample.TWO.name());
        cmp_.setTranslations(translation_);
        assertEq(0, cmp_.compare(EnumDigitExample.ONE, EnumDigitExample.TWO));
        assertEq(0, cmp_.compare(EnumDigitExample.ONE, EnumDigitExample.ONE));
        assertEq(0, cmp_.compare(EnumDigitExample.TWO, EnumDigitExample.ONE));
        assertEq(0, cmp_.compare(EnumDigitExample.TWO, EnumDigitExample.TWO));
    }

    @Test
    public void compare2Test() {
        EnumFieldComparator<EnumDigitExample> cmp_;
        cmp_ = new EnumFieldComparator<EnumDigitExample>();
        EnumMap<EnumDigitExample, String> translation_;
        translation_ = new EnumMap<EnumDigitExample, String>();
        translation_.put(EnumDigitExample.ONE, EnumDigitExample.ONE.name());
        translation_.put(EnumDigitExample.TWO, EnumDigitExample.TWO.name());
        cmp_.setTranslations(translation_);
        cmp_.setIncreasing(SelectedBoolean.YES_AND_NO);
        assertEq(0, cmp_.compare(EnumDigitExample.ONE, EnumDigitExample.TWO));
        assertEq(0, cmp_.compare(EnumDigitExample.ONE, EnumDigitExample.ONE));
        assertEq(0, cmp_.compare(EnumDigitExample.TWO, EnumDigitExample.ONE));
        assertEq(0, cmp_.compare(EnumDigitExample.TWO, EnumDigitExample.TWO));
    }

    @Test
    public void compare3Test() {
        EnumFieldComparator<EnumDigitExample> cmp_;
        cmp_ = new EnumFieldComparator<EnumDigitExample>();
        EnumMap<EnumDigitExample, String> translation_;
        translation_ = new EnumMap<EnumDigitExample, String>();
        translation_.put(EnumDigitExample.ONE, EnumDigitExample.ONE.name());
        translation_.put(EnumDigitExample.TWO, EnumDigitExample.TWO.name());
        cmp_.setTranslations(translation_);
        cmp_.setIncreasing(SelectedBoolean.YES);
        assertEq(-5, cmp_.compare(EnumDigitExample.ONE, EnumDigitExample.TWO));
        assertEq(0, cmp_.compare(EnumDigitExample.ONE, EnumDigitExample.ONE));
        assertEq(5, cmp_.compare(EnumDigitExample.TWO, EnumDigitExample.ONE));
        assertEq(0, cmp_.compare(EnumDigitExample.TWO, EnumDigitExample.TWO));
    }

    @Test
    public void compare4Test() {
        EnumFieldComparator<EnumDigitExample> cmp_;
        cmp_ = new EnumFieldComparator<EnumDigitExample>();
        EnumMap<EnumDigitExample, String> translation_;
        translation_ = new EnumMap<EnumDigitExample, String>();
        translation_.put(EnumDigitExample.ONE, EnumDigitExample.ONE.name());
        translation_.put(EnumDigitExample.TWO, EnumDigitExample.TWO.name());
        cmp_.setTranslations(translation_);
        cmp_.setIncreasing(SelectedBoolean.NO);
        assertEq(5, cmp_.compare(EnumDigitExample.ONE, EnumDigitExample.TWO));
        assertEq(0, cmp_.compare(EnumDigitExample.ONE, EnumDigitExample.ONE));
        assertEq(-5, cmp_.compare(EnumDigitExample.TWO, EnumDigitExample.ONE));
        assertEq(0, cmp_.compare(EnumDigitExample.TWO, EnumDigitExample.TWO));
    }
}
