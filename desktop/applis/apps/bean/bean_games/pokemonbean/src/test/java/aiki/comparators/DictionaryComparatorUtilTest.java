package aiki.comparators;

import aiki.beans.InitDbBean;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import org.junit.Test;

public final class DictionaryComparatorUtilTest extends InitDbBean {
    private static final String EV1="3";
    private static final String EV2="2";
    private static final String EV3="1";
    private static final String EV4="4";
    private static final String FR1="2";
    private static final String FR2="6";
    private static final String FR3="8";
    private static final String FR4="5";
    private static final String FRC1="2/21";
    private static final String FRC2="2/7";
    private static final String FRC3="8/21";
    private static final String FRC4="5/21";
    @Test
    public void group1() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(Rate.newRate(EV3), LgInt.newLgInt(FR3));
        law_.addQuickEvent(Rate.newRate(EV2), LgInt.newLgInt(FR2));
        law_.addQuickEvent(Rate.newRate(EV1), LgInt.newLgInt(FR1));
        law_.addQuickEvent(Rate.newRate(EV4), LgInt.newLgInt(FR4));
        DictionaryComparator<Rate, Rate> map_ = DictionaryComparatorUtil.feedRateRate(law_);
        assertEq(4, map_.size());
        assertEq(Rate.newRate(EV3),map_.getKey(0));
        assertEq(Rate.newRate(FRC3),map_.getValue(0));
        assertEq(Rate.newRate(EV2),map_.getKey(1));
        assertEq(Rate.newRate(FRC2),map_.getValue(1));
        assertEq(Rate.newRate(EV1),map_.getKey(2));
        assertEq(Rate.newRate(FRC1),map_.getValue(2));
        assertEq(Rate.newRate(EV4),map_.getKey(3));
        assertEq(Rate.newRate(FRC4),map_.getValue(3));
    }
    @Test
    public void group2() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(Rate.newRate(EV3), LgInt.newLgInt(FR3));
        law_.addQuickEvent(Rate.newRate(EV3), LgInt.newLgInt(FR2));
        DictionaryComparator<Rate, Rate> map_ = DictionaryComparatorUtil.feedRateRate(law_);
        assertEq(1, map_.size());
        assertEq(Rate.newRate(EV3),map_.getKey(0));
        assertEq(Rate.one(),map_.getValue(0));
    }
    @Test
    public void group3() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(Rate.newRate(EV3), LgInt.newLgInt(FR3));
        law_.addQuickEvent(Rate.newRate(EV2), LgInt.newLgInt(FR2));
        law_.addQuickEvent(Rate.newRate(EV1), LgInt.newLgInt(FR1));
        law_.addQuickEvent(Rate.newRate(EV4), LgInt.newLgInt(FR4));
        DictionaryComparator<LgInt, Rate> map_ = DictionaryComparatorUtil.buildIntRate(law_);
        assertEq(4, map_.size());
        assertEq(LgInt.newLgInt(EV3),map_.getKey(0));
        assertEq(Rate.newRate(FRC3),map_.getValue(0));
        assertEq(LgInt.newLgInt(EV2),map_.getKey(1));
        assertEq(Rate.newRate(FRC2),map_.getValue(1));
        assertEq(LgInt.newLgInt(EV1),map_.getKey(2));
        assertEq(Rate.newRate(FRC1),map_.getValue(2));
        assertEq(LgInt.newLgInt(EV4),map_.getKey(3));
        assertEq(Rate.newRate(FRC4),map_.getValue(3));
    }
    @Test
    public void group4() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(Rate.newRate(EV3), LgInt.newLgInt(FR3));
        law_.addQuickEvent(Rate.newRate(EV3), LgInt.newLgInt(FR2));
        DictionaryComparator<LgInt, Rate> map_ = DictionaryComparatorUtil.buildIntRate(law_);
        assertEq(1, map_.size());
        assertEq(LgInt.newLgInt(EV3),map_.getKey(0));
        assertEq(Rate.one(),map_.getValue(0));
    }
}
