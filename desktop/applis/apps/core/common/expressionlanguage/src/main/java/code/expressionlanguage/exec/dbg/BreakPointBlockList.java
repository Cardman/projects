package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.SynthFieldInfo;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecReturnableWithSignature;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.EntryCust;
import code.util.Ints;
import code.util.core.StringUtil;

public final class BreakPointBlockList {
    private final AbsCollection<BreakPointBlockPair> list;
    private final AbsCollection<BreakPointBlockKey> listTmp;
    private final AbstractInterceptorStdCaller interceptor;
    private final AbstractAtomicBoolean pausedLoop;
    private final AbsCollection<WatchPointBlockPair> watchList;
    private final AbsCollection<ArrPointBlockPair> arrPointList;
    private final AbsCollection<ExcPointBlockPair> excPointList;
    private final AbsCollection<MethodPointBlockPair> methPointList;
    private final AbsCollection<StdMethodPointBlockPair> stdMethPointList;
    private final AbsCollection<ParPointBlockPair> parPointList;
    private final AbsCollection<AbsOperNatPointBlockPair> operNatPointList;
    private final AbsCollection<AbsOperNatPointBlockPair> operCompoNatPointList;
    private final AbsCollection<AbsCallContraints> exclude;
    private final AbsCollection<AbsCallContraints> include;
    private final AbsCollection<TypePointBlockPair> typeList;

    public BreakPointBlockList(AbstractInterceptorStdCaller _i) {
        interceptor = _i;
        listTmp = _i.newBreakPointKeyIdStringCollection();
        list = _i.newBreakPointKeyStringCollection();
        typeList = _i.newTypePointKeyStringCollection();
        watchList = _i.newWatchPointKeyStringCollection();
        arrPointList = _i.newArrPointKeyStringCollection();
        excPointList = _i.newExcPointKeyStringCollection();
        parPointList = _i.newParPointKeyStringCollection();
        methPointList = _i.newMethodPointKeyStringCollection();
        stdMethPointList = _i.newStdMethodPointKeyStringCollection();
        operNatPointList = _i.newOperNatPointKeyStringCollection();
        operCompoNatPointList = _i.newOperNatPointKeyStringCollection();
        pausedLoop = _i.newAtBool();
        exclude = _i.newExecFileBlockTraceIndexCollection();
        include = _i.newExecFileBlockTraceIndexCollection();
    }

    public static int pref(AbsCollection<MethodPointBlockPair> _p, int _exit) {
        return prefIn(prefsMeths(_p, _exit));
    }

    public static int prefIn(CustList<BreakPointCondition> _ls) {
        return pref(retrieve(_ls));
    }

    private static Ints retrieve(CustList<BreakPointCondition> _ls) {
        Ints values_ = new Ints();
        for (BreakPointCondition m: _ls) {
            values_.add(m.getPref().get());
        }
        return values_;
    }

    public static int pref(AbsCollection<MethodPointBlockPair> _p, int _exit, String _cl) {
        return prefBpc(prefsMeths(_p, _exit),_cl);
    }

    public static CustList<BreakPointCondition> prefsArr(AbsCollection<ArrPointBlockPair> _p, int _exit) {
        CustList<BreakPointCondition> values_ = new CustList<BreakPointCondition>();
        for (ArrPointBlockPair m: _p.elts()) {
            values_.add(m.getValue().resultBpc(_exit));
        }
        return values_;
    }

    public static CustList<BreakPointCondition> prefsExc(AbsCollection<ExcPointBlockPair> _p, int _exit) {
        CustList<BreakPointCondition> values_ = new CustList<BreakPointCondition>();
        for (ExcPointBlockPair m: _p.elts()) {
            values_.add(m.getValue().resultBpc(_exit));
        }
        return values_;
    }

    public static CustList<BreakPointCondition> prefsPar(AbsCollection<ParPointBlockPair> _p) {
        CustList<BreakPointCondition> values_ = new CustList<BreakPointCondition>();
        for (ParPointBlockPair m: _p.elts()) {
            values_.add(m.getValue().getResultGet());
        }
        return values_;
    }
    public static CustList<BreakPointCondition> prefsMeths(AbsCollection<MethodPointBlockPair> _p, int _exit) {
        CustList<BreakPointCondition> values_ = new CustList<BreakPointCondition>();
        for (MethodPointBlockPair m: _p.elts()) {
            values_.add(m.getValue().result(_exit));
        }
        return values_;
    }

    public static int prefBpc(CustList<BreakPointCondition> _p, String _cl) {
        Ints values_ = new Ints();
        for (BreakPointCondition m: _p) {
            for (EntryCust<String,Integer> e: m.getPrefs().elts()) {
                filter(_cl, values_, e);
            }
        }
        return pref(values_);
    }

    public static void filter(String _cl, Ints _values, EntryCust<String, Integer> _e) {
        if (StringUtil.quickEq(_e.getKey(), _cl)) {
            _values.add(_e.getValue());
        }
    }

    public static int pref(Ints _values) {
        _values.sort();
        int s_ = _values.size();
        if (s_ > 0 && _values.get(0) > 0) {
            return _values.get(0) - 1;
        }
        for (int i = 1; i < s_; i++) {
            int one_ = _values.get(i - 1);
            int two_ = _values.get(i);
            if (two_ - one_ > 1) {
                return one_ + 1;
            }
        }
        return (int) (_values.getMaximum(-1) + 1);
    }

    public StdMethodPointBlockPair std(StandardType _t, StandardNamedFunction _i) {
        String k_ = build(_t, _i);
        return new StdMethodPointBlockPair(_i, _t, k_, interceptor, true);
    }

    private static String build(StandardType _t, StandardNamedFunction _i) {
        return _t.getNumber()+buildSep(_i)+_i.getNumber();
    }

    private static String buildSep(StandardNamedFunction _i) {
        if (_i instanceof StandardMethod) {
            return "_";
        }
        return ".";
    }

    public OperNatPointBlockPair operNat(String _k, String _symbol, String _f, String _s) {
        return new OperNatPointBlockPair(_k, _symbol, interceptor,true, _f, _s);
    }

    public CompoOperNatPointBlockPair operNatComp(String _k, String _symbol, String _f, String _s) {
        return new CompoOperNatPointBlockPair(_k, _symbol, interceptor,true, _f, _s);
    }

    public OperNatPointBlockPair operNatDisabled() {
        return new OperNatPointBlockPair("","", interceptor,false, "", "");
    }

    public MethodPointBlockPair method(DisplayedStrings _d, MemberCallingsBlock _id) {
        return new MethodPointBlockPair(_id, interceptor,ResultExpressionOperationNode.clName(_d, _id),true, MemberCallingsBlock.clName(_id));
    }

    public BreakPointBlockPair bp(ExecFileBlock _file, int _nf, int _offset) {
        return new BreakPointBlockPair(_file,_nf, _offset, interceptor,true);
    }

    public TypePointBlockPair tp(ExecFileBlock _file, int _nf, int _offset) {
        return new TypePointBlockPair(_file,_nf, _offset, interceptor,true);
    }

    public static BracedBlock rootOfAnnot(AbsBk _id) {
        if (AbsBk.isAnnotBlock(_id)) {
            return _id.getParent();
        }
        return null;
    }

    public BreakPointBlockPair notNull(BreakPointBlockPair _b) {
        if (_b == null) {
            return new BreakPointBlockPair(null,-1,-1,interceptor,false);
        }
        return _b;
    }

    public TypePointBlockPair notNull(TypePointBlockPair _b) {
        if (_b == null) {
            return new TypePointBlockPair(null,-1,-1,interceptor,false);
        }
        return _b;
    }

    public StdMethodPointBlockPair notNull(StdMethodPointBlockPair _b) {
        if (_b == null) {
            return new StdMethodPointBlockPair(null,null,"",interceptor,false);
        }
        return _b;
    }

    public MethodPointBlockPair notNull(MethodPointBlockPair _b) {
        if (_b == null) {
            return new MethodPointBlockPair(null, interceptor, "", false, "");
        }
        return _b;
    }

    public static String id(ExecBlock _id) {
        if (_id instanceof ExecReturnableWithSignature) {
            return ((ExecReturnableWithSignature) _id).id();
        }
        return "";
    }
    public void resetList() {
        for (BreakPointBlockPair b: getList().elts()) {
            b.getValue().resetCount();
        }
        for (TypePointBlockPair b: getTypeList().elts()) {
            b.getValue().resetCount();
        }
        for (MethodPointBlockPair b: getMethPointList().elts()) {
            b.getValue().resetCount();
        }
        for (StdMethodPointBlockPair b: getStdMethPointList().elts()) {
            b.getValue().resetCount();
        }
        for (ArrPointBlockPair b: getArrPointList().elts()) {
            b.getValue().resetCount();
        }
        for (ExcPointBlockPair b: getExcPointList().elts()) {
            b.getValue().resetCount();
        }
        for (ParPointBlockPair b: getParPointList().elts()) {
            b.getValue().resetCount();
        }
        for (WatchPointBlockPair b: getWatchList().elts()) {
            b.getValue().resetCount();
        }
        for (AbsOperNatPointBlockPair b: getOperNatPointList().elts()) {
            b.resetCount();
        }
        for (AbsOperNatPointBlockPair b: getOperCompoNatPointList().elts()) {
            b.resetCount();
        }
    }

    public ArrPointBlockPair buildArr(int _exact, String _clName) {
        if (_exact == ExcPointBlockKey.SAME) {
            return new ArrPointBlockPair(_exact, _clName, interceptor, true);
        }
        return new ArrPointBlockPair(_exact, StringExpUtil.getIdFromAllTypes(_clName), interceptor, true);
    }

    public ArrPointBlockPair notNullExp(ArrPointBlockPair _b) {
        if (_b == null) {
            return new ArrPointBlockPair(-1,"",interceptor,false);
        }
        return _b;
    }

    public ExcPointBlockPair build(int _exact, String _clName) {
        if (_exact == ExcPointBlockKey.SAME) {
            return new ExcPointBlockPair(_exact, _clName, interceptor, true);
        }
        return new ExcPointBlockPair(_exact, StringExpUtil.getIdFromAllTypes(_clName), interceptor, true);
    }

    public ExcPointBlockPair notNullExp(ExcPointBlockPair _b) {
        if (_b == null) {
            return new ExcPointBlockPair(-1,"",interceptor,false);
        }
        return _b;
    }


    public ParPointBlockPair buildPar(int _exact, String _clName, RootBlock _de) {
        if (_exact == ExcPointBlockKey.SAME) {
            return new ParPointBlockPair(_exact, _clName, interceptor, true,_de);
        }
        return new ParPointBlockPair(_exact, StringExpUtil.getIdFromAllTypes(_clName), interceptor, true,_de);
    }

    public ParPointBlockPair notNullExp(ParPointBlockPair _b) {
        if (_b == null) {
            return new ParPointBlockPair(-1,"",interceptor,false, null);
        }
        return _b;
    }
    public WatchPointBlockPair watch(boolean _trField, SynthFieldInfo _field) {
        return new WatchPointBlockPair(_trField, _field.getRootBlock(), _field.getRootBlockNb(), _field.getClassField().getFieldName(), interceptor, true);
    }

    public WatchPointBlockPair notNullWatch(WatchPointBlockPair _b) {
        if (_b == null) {
            return new WatchPointBlockPair(false,null,-1,"",interceptor,false);
        }
        return _b;
    }

    public AbsCollection<ArrPointBlockPair> getArrPointList() {
        return arrPointList;
    }

    public AbsCollection<ExcPointBlockPair> getExcPointList() {
        return excPointList;
    }

    public AbsCollection<ParPointBlockPair> getParPointList() {
        return parPointList;
    }

    public AbsCollection<BreakPointBlockPair> getList() {
        return list;
    }

    public AbsCollection<TypePointBlockPair> getTypeList() {
        return typeList;
    }

    public AbsCollection<StdMethodPointBlockPair> getStdMethPointList() {
        return stdMethPointList;
    }

    public AbsCollection<MethodPointBlockPair> getMethPointList() {
        return methPointList;
    }

    public AbsCollection<WatchPointBlockPair> getWatchList() {
        return watchList;
    }

    public AbsCollection<AbsOperNatPointBlockPair> getOperNatPointList() {
        return operNatPointList;
    }

    public AbsCollection<AbsOperNatPointBlockPair> getOperCompoNatPointList() {
        return operCompoNatPointList;
    }

    public AbsCollection<AbsCallContraints> getExclude() {
        return exclude;
    }

    public AbsCollection<AbsCallContraints> getInclude() {
        return include;
    }
    public AbsCollection<BreakPointBlockKey> getListTmp() {
        return listTmp;
    }

    public AbstractAtomicBoolean getPausedLoop() {
        return pausedLoop;
    }
}
