package code.sml;

import code.maths.*;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class DocumentMathUtilTest extends EquallableSerialUtil {
    @Test
    public void t1() {
        assertEq(LgInt.one(),saveLgInt(LgInt.one()));
    }
    @Test
    public void t2() {
        assertEq(Rate.one(),saveRate(Rate.one()));
    }
    @Test
    public void t3() {
        CustList<LgInt> ls_ = saveListLgInt(new CustList<LgInt>(LgInt.one()));
        assertEq(1,ls_.size());
        assertEq(LgInt.one(),ls_.get(0));
    }
    @Test
    public void t4() {
        CustList<Rate> ls_ = saveListRate(new CustList<Rate>(Rate.one()));
        assertEq(1,ls_.size());
        assertEq(Rate.one(),ls_.get(0));
    }
    @Test
    public void t5() {
        StringMap<LgInt> inSingle_ = new StringMap<LgInt>();
        inSingle_.addEntry("1",LgInt.minusOne());
        StringMap<LgInt> ls_ = saveStrMapLgInt(inSingle_);
        assertEq(1,ls_.size());
        assertEq("1",ls_.getKey(0));
        assertEq(LgInt.minusOne(),ls_.getValue(0));
    }
    @Test
    public void t6() {
        StringMap<Rate> inSingle_ = new StringMap<Rate>();
        inSingle_.addEntry("1",Rate.minusOne());
        StringMap<Rate> ls_ = saveStrMapRate(inSingle_);
        assertEq(1,ls_.size());
        assertEq("1",ls_.getKey(0));
        assertEq(Rate.minusOne(),ls_.getValue(0));
    }
    @Test
    public void t7() {
        MonteCarloNumber inSingle_ = new MonteCarloNumber();
        inSingle_.addQuickEvent(Rate.one(),new LgInt(2));
        inSingle_.addQuickEvent(Rate.minusOne(),new LgInt(3));
        MonteCarloNumber ls_ = saveMonteCarloNumber(inSingle_);
        assertEq(2,ls_.getEvents().size());
        assertEq(Rate.one(),ls_.getEvents().get(0).getEvent());
        assertEq(new LgInt(2),ls_.getEvents().get(0).getFreq());
        assertEq(Rate.minusOne(),ls_.getEvents().get(1).getEvent());
        assertEq(new LgInt(3),ls_.getEvents().get(1).getFreq());
    }
    @Test
    public void t8() {
        MonteCarloBoolean inSingle_ = new MonteCarloBoolean();
        inSingle_.addQuickEvent(BoolVal.FALSE,new LgInt(2));
        inSingle_.addQuickEvent(BoolVal.TRUE,new LgInt(3));
        MonteCarloBoolean ls_ = saveMonteCarloBool(inSingle_);
        assertEq(2,ls_.getEvents().size());
        assertEq(BoolVal.FALSE,ls_.getEvents().get(0).getEvent());
        assertEq(new LgInt(2),ls_.getEvents().get(0).getFreq());
        assertEq(BoolVal.TRUE,ls_.getEvents().get(1).getEvent());
        assertEq(new LgInt(3),ls_.getEvents().get(1).getFreq());
    }
    @Test
    public void t9() {
        MonteCarloString inSingle_ = new MonteCarloString();
        inSingle_.addQuickEvent("1",new LgInt(2));
        inSingle_.addQuickEvent("4",new LgInt(3));
        MonteCarloString ls_ = saveMonteCarloString(inSingle_);
        assertEq(2,ls_.getLaw().size());
        assertEq("1",ls_.getLaw().getKey(0));
        assertEq(new LgInt(2),ls_.getLaw().getValue(0));
        assertEq("4",ls_.getLaw().getKey(1));
        assertEq(new LgInt(3),ls_.getLaw().getValue(1));
    }
    @Test
    public void t10() {
        LongMap<Rate> inSingle_ = new LongMap<Rate>();
        inSingle_.addEntry(1L,new Rate(2));
        inSingle_.addEntry(4L,new Rate(3));
        LongMap<Rate> ls_ = saveMapLongRate(inSingle_);
        assertEq(2,ls_.size());
        assertEq(1,ls_.getKey(0));
        assertEq(new Rate(2),ls_.getValue(0));
        assertEq(4,ls_.getKey(1));
        assertEq(new Rate(3),ls_.getValue(1));
    }
}
