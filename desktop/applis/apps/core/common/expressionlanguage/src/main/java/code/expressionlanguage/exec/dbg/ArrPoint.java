package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class ArrPoint {
    public static final int AP=5;
    public static final int BPC_LENGTH = 0;
    public static final int BPC_INT_GET = WatchPoint.BPC_READ+1;
    public static final int BPC_INT_SET = WatchPoint.BPC_WRITE+1;
    public static final int BPC_INT_COMPOUND_GET = WatchPoint.BPC_COMPOUND_READ+1;
    public static final int BPC_INT_COMPOUND_SET = WatchPoint.BPC_COMPOUND_WRITE+1;
    public static final int BPC_INT_COMPOUND_SET_ERR = WatchPoint.BPC_COMPOUND_WRITE_ERR+1;
    public static final int BPC_RANGE_GET = WatchPoint.BPC_READ+6;
    public static final int BPC_RANGE_SET = WatchPoint.BPC_WRITE+6;
    public static final int BPC_RANGE_COMPOUND_GET = WatchPoint.BPC_COMPOUND_READ+6;
    public static final int BPC_RANGE_COMPOUND_SET = WatchPoint.BPC_COMPOUND_WRITE+6;
    public static final int BPC_INT_GET_SET = 10;
    public static final int BPC_INIT = 11;
    public static final int BPC_CLONE = 12;
    private boolean enabled;
    private boolean length;
    private boolean intGet;
    private boolean intSet;
    private boolean intCompoundGet;
    private boolean intCompoundSet;
    private boolean intCompoundSetErr;
    private boolean rangeGet;
    private boolean rangeSet;
    private boolean rangeCompoundGet;
    private boolean rangeCompoundSet;
    private boolean intGetSet;
    private boolean initArray;
    private boolean clone;
    private final BreakPointCondition resultLength;
    private final BreakPointCondition resultIntGet;
    private final BreakPointCondition resultIntSet;
    private final BreakPointCondition resultIntCompoundGet;
    private final BreakPointCondition resultIntCompoundSet;
    private final BreakPointCondition resultIntCompoundSetErr;
    private final BreakPointCondition resultRangeGet;
    private final BreakPointCondition resultRangeSet;
    private final BreakPointCondition resultRangeCompoundGet;
    private final BreakPointCondition resultRangeCompoundSet;
    private final BreakPointCondition resultIntGetSet;
    private final BreakPointCondition resultInitArray;
    private final BreakPointCondition resultClone;
    public ArrPoint(AbstractInterceptorStdCaller _i, AbsKeyPoint _key){
        resultLength = new BreakPointCondition(_i,_key,AP, BPC_LENGTH);
        resultIntGet = new BreakPointCondition(_i,_key,AP, BPC_INT_GET);
        resultIntSet = new BreakPointCondition(_i,_key,AP, BPC_INT_SET);
        resultIntCompoundGet = new BreakPointCondition(_i,_key,AP, BPC_INT_COMPOUND_GET);
        resultIntCompoundSet = new BreakPointCondition(_i,_key,AP, BPC_INT_COMPOUND_SET);
        resultIntCompoundSetErr = new BreakPointCondition(_i,_key,AP, BPC_INT_COMPOUND_SET_ERR);
        resultRangeGet = new BreakPointCondition(_i,_key,AP, BPC_RANGE_GET);
        resultRangeSet = new BreakPointCondition(_i,_key,AP, BPC_RANGE_SET);
        resultRangeCompoundGet = new BreakPointCondition(_i,_key,AP, BPC_RANGE_COMPOUND_GET);
        resultRangeCompoundSet = new BreakPointCondition(_i,_key,AP, BPC_RANGE_COMPOUND_SET);
        resultIntGetSet = new BreakPointCondition(_i,_key,AP, BPC_INT_GET_SET);
        resultInitArray = new BreakPointCondition(_i,_key,AP, BPC_INIT);
        resultClone = new BreakPointCondition(_i,_key,AP, BPC_CLONE);
        setLength(true);
        setIntGet(true);
        setIntSet(true);
        setIntCompoundGet(true);
        setIntCompoundSet(true);
        setIntCompoundSetErr(true);
        setRangeGet(true);
        setRangeSet(true);
        setRangeCompoundGet(true);
        setRangeCompoundSet(true);
        setIntGetSet(true);
        setClone(true);
    }

    public void resetCount() {
        resultLength.resetCount();
        resultIntGet.resetCount();
        resultIntSet.resetCount();
        resultIntCompoundGet.resetCount();
        resultIntCompoundSet.resetCount();
        resultIntCompoundSetErr.resetCount();
        resultRangeGet.resetCount();
        resultRangeSet.resetCount();
        resultRangeCompoundGet.resetCount();
        resultRangeCompoundSet.resetCount();
        resultIntGetSet.resetCount();
        resultInitArray.resetCount();
        resultClone.resetCount();
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public boolean isLength() {
        return length;
    }

    public void setLength(boolean _l) {
        this.length = _l;
    }

    public boolean isIntGet() {
        return intGet;
    }

    public void setIntGet(boolean _i) {
        this.intGet = _i;
    }

    public boolean isIntSet() {
        return intSet;
    }

    public void setIntSet(boolean _i) {
        this.intSet = _i;
    }

    public boolean isIntCompoundGet() {
        return intCompoundGet;
    }

    public void setIntCompoundGet(boolean _i) {
        this.intCompoundGet = _i;
    }

    public boolean isIntCompoundSet() {
        return intCompoundSet;
    }

    public void setIntCompoundSet(boolean _i) {
        this.intCompoundSet = _i;
    }

    public boolean isIntCompoundSetErr() {
        return intCompoundSetErr;
    }

    public void setIntCompoundSetErr(boolean _i) {
        this.intCompoundSetErr = _i;
    }

    public boolean isRangeGet() {
        return rangeGet;
    }

    public void setRangeGet(boolean _r) {
        this.rangeGet = _r;
    }

    public boolean isRangeSet() {
        return rangeSet;
    }

    public void setRangeSet(boolean _r) {
        this.rangeSet = _r;
    }

    public boolean isRangeCompoundGet() {
        return rangeCompoundGet;
    }

    public void setRangeCompoundGet(boolean _r) {
        this.rangeCompoundGet = _r;
    }

    public boolean isRangeCompoundSet() {
        return rangeCompoundSet;
    }

    public void setRangeCompoundSet(boolean _r) {
        this.rangeCompoundSet = _r;
    }

    public boolean isIntGetSet() {
        return intGetSet;
    }

    public void setIntGetSet(boolean _i) {
        this.intGetSet = _i;
    }

    public boolean isInitArray() {
        return initArray;
    }

    public void setInitArray(boolean _i) {
        this.initArray = _i;
    }

    public boolean isClone() {
        return clone;
    }

    public void setClone(boolean _i) {
        this.clone = _i;
    }

    public BreakPointCondition getResultLength() {
        return resultLength;
    }


    public BreakPointCondition getResultIntGet() {
        return resultIntGet;
    }

    public BreakPointCondition getResultIntSet() {
        return resultIntSet;
    }

    public BreakPointCondition getResultIntCompoundGet() {
        return resultIntCompoundGet;
    }

    public BreakPointCondition getResultIntCompoundSet() {
        return resultIntCompoundSet;
    }

    public BreakPointCondition getResultIntCompoundSetErr() {
        return resultIntCompoundSetErr;
    }

    public BreakPointCondition getResultRangeGet() {
        return resultRangeGet;
    }

    public BreakPointCondition getResultRangeSet() {
        return resultRangeSet;
    }

    public BreakPointCondition getResultRangeCompoundGet() {
        return resultRangeCompoundGet;
    }

    public BreakPointCondition getResultRangeCompoundSet() {
        return resultRangeCompoundSet;
    }

    public BreakPointCondition getResultIntGetSet() {
        return resultIntGetSet;
    }

    public BreakPointCondition getResultInitArray() {
        return resultInitArray;
    }

    public BreakPointCondition getResultClone() {
        return resultClone;
    }
}
