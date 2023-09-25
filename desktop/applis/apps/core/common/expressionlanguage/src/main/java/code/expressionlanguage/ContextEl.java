package code.expressionlanguage;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.types.ExecPartTypeUtil;
import code.expressionlanguage.exec.util.ClassMethodIdOverride;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public abstract class ContextEl {

    private final CommonExecutionInfos executionInfos;
    private AbstractExiting exiting = new AfterInitExiting(this);
    private final AbstractAtomicBoolean interrupt;

    protected ContextEl(AbstractAtomicBoolean _i,CommonExecutionInfos _executionInfos) {
        interrupt = _i;
        executionInfos = _executionInfos;
    }

    public AbstractAtomicBoolean getInterrupt() {
        return interrupt;
    }

    public AbstractTypePairHash getChecker() {
        return executionInfos.getClasses().getChecker();
    }
    public CustList<MethodPointBlockPairRootBlock> getPairs(ExecBlock _id, ExecFormattedRootBlock _gl, ContextEl _context, Struct _instance, boolean _exit, Parameters _p) {
        if (_p.getError() != null) {
            return new CustList<MethodPointBlockPairRootBlock>();
        }
        return getPairs(_id, _gl, _context, _instance, _exit);
    }
    public CustList<MethodPointBlockPairRootBlock> getPairs(ExecBlock _id, ExecFormattedRootBlock _gl, ContextEl _context, Struct _instance, boolean _exit) {
        String argClassName_ = _instance.getClassName(_context);
        String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        CustList<MethodPointBlockPairRootBlock> out_ = new CustList<MethodPointBlockPairRootBlock>();
        String id_ = BreakPointBlockList.id(_id);
        for (MethodPointBlockPair b: metList().elts()) {
            MemberCallingsBlock i_ = b.getMp().getId();
            int nb_ = nb(i_);
            int pr_ = b.getValue().result(_exit).pref(base_);
            if (_context.getClasses().getRedirections().isValidIndex(nb_)) {
                ClassMethodIdOverride v_ = _context.getClasses().getRedirections().get(nb_).getVal(MemberCallingsBlock.clName(i_));
                if (v_ != null) {
                    ExecOverrideInfo ov_ = v_.getVal(base_);
                    if (ov_ != null && ov_.getPair().getFct() == _id) {
                        out_.add(new MethodPointBlockPairRootBlock(b, pr_,ExecFormattedRootBlock.getFullObject(argClassName_,new ExecFormattedRootBlock(v_.getRoot()),_context)));
                        continue;
                    }
                }
            }
            if (StringUtil.quickEq(MemberCallingsBlock.clName(i_),id_)) {
                out_.add(new MethodPointBlockPairRootBlock(b, pr_,_gl));
            }
        }
        out_.sortElts(new CmpMethodPair());
        return out_;
    }

    private int nb(MemberCallingsBlock _i) {
        int nb_;
        if (_i.getParent() instanceof RootBlock){
            nb_ = ((RootBlock) _i.getParent()).getNumberAll();
        } else {
            nb_ = -1;
        }
        return nb_;
    }
    public void toggleBreakPoint(StandardType _t, StandardNamedFunction _i) {
        StdMethodPointBlockPair pair_ = std(_t, _i);
        for (StdMethodPointBlockPair b: stdList().elts()) {
            if (b.getSm().match(pair_.getSm())) {
                stdList().remove(b);
                return;
            }
        }
        stdList().add(pair_);
    }

    public AbsCollection<StdMethodPointBlockPair> stdList() {
        return getClasses().getDebugMapping().getBreakPointsBlock().getStdMethPointList();
    }
    public StdMethodPointBlockPair std(StandardType _t, StandardNamedFunction _i) {
        return getClasses().getDebugMapping().getBreakPointsBlock().std(_t, _i);
    }
    public void toggleBreakPointEnabled(StandardType _t, StandardNamedFunction _i) {
        StdMethodPointBlockPair pair_ = std(_t, _i);
        for (StdMethodPointBlockPair b: stdList().elts()) {
            if (b.getSm().match(pair_.getSm())) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        stdList().add(pair_);
    }
    public void toggleBreakPoint(AbsPairPoint _p) {
        if (_p instanceof WatchPointBlockPair) {
            toggleWatch((WatchPointBlockPair)_p);
            return;
        }
        if (_p instanceof MethodPointBlockPair) {
            toggle((MethodPointBlockPair)_p);
            return;
        }
        if (_p instanceof BreakPointBlockPair) {
            toggleBp((BreakPointBlockPair)_p);
        }
    }

    private void toggle(MethodPointBlockPair _pair) {
        for (MethodPointBlockPair b: metList().elts()) {
            if (b.getMp().match(_pair.getMp())) {
                metList().remove(b);
                return;
            }
        }
        metList().add(_pair);
    }

    public void toggleWatch(boolean _trField, SynthFieldInfo _field) {
        WatchPointBlockPair pair_ = watch(_trField, _field);
        toggleWatch(pair_);
    }

    private void toggleWatch(WatchPointBlockPair _pair) {
        for (WatchPointBlockPair b: watchList().elts()) {
            if (b.getWp().match(_pair.getWp())) {
                watchList().remove(b);
                return;
            }
        }
        watchList().add(_pair);
    }

    private void toggleBp(BreakPointBlockPair _pair) {
        for (BreakPointBlockPair b: bpList().elts()) {
            if (b.getBp().match(_pair.getBp())) {
                bpList().remove(b);
                return;
            }
        }
        bpList().add(_pair);
    }

    public OperNatPointBlockPair operNatDisabled() {
        return getClasses().getDebugMapping().getBreakPointsBlock().operNatDisabled();
    }

    public OperNatPointBlockPair operNat(String _k, String _symbol,String _f, String _s) {
        return getClasses().getDebugMapping().getBreakPointsBlock().operNat(_k,_symbol, _f, _s);
    }

    public MethodPointBlockPair method(DisplayedStrings _d, MemberCallingsBlock _id) {
        return getClasses().getDebugMapping().getBreakPointsBlock().method(_d, _id);
    }
    public AbsCollection<MethodPointBlockPair> metList() {
        return getClasses().getDebugMapping().getBreakPointsBlock().getMethPointList();
    }
    public WatchPointBlockPair watch(boolean _trField, SynthFieldInfo _field) {
        return getClasses().getDebugMapping().getBreakPointsBlock().watch(_trField, _field);
    }
    public BreakPointBlockPair bp(ExecFileBlock _file, int _nf, int _offset, boolean _enType) {
        return getClasses().getDebugMapping().getBreakPointsBlock().bp(_file, _nf, _offset, _enType);
    }
    public AbsCollection<WatchPointBlockPair> watchList() {
        return getClasses().getDebugMapping().getBreakPointsBlock().getWatchList();
    }
    public AbsCollection<BreakPointBlockPair> bpList() {
        return getClasses().getDebugMapping().getBreakPointsBlock().getList();
    }

    public void toggleEnabled(ExecFileBlock _file, int _nf, int _offset, boolean _enType) {
        BreakPointBlockPair pair_ = bp(_file, _nf, _offset, _enType);
        for (BreakPointBlockPair b: bpList().elts()) {
            if (b.getBp().match(pair_.getBp())) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        bpList().add(pair_);
    }
    public void toggleArrPoint(String _clName, boolean _exact) {
        ArrPointBlockPair exc_ = buildArr(_exact, _clName);
        if (exc_ == null) {
            return;
        }
        toggleArr(exc_);
    }
    public void toggleExcPoint(String _clName, boolean _exact) {
        ExcPointBlockPair exc_ = build(_exact, _clName);
        if (exc_ == null) {
            return;
        }
        toggleExc(exc_);
    }

    public AbsCollection<ArrPointBlockPair> arrList() {
        return getClasses().getDebugMapping().getBreakPointsBlock().getArrPointList();
    }

    public AbsCollection<ExcPointBlockPair> excList() {
        return getClasses().getDebugMapping().getBreakPointsBlock().getExcPointList();
    }

    public AbsCollection<ParPointBlockPair> parList() {
        return getClasses().getDebugMapping().getBreakPointsBlock().getParPointList();
    }

    public AbsCollection<OperNatPointBlockPair> operNatList() {
        return getClasses().getDebugMapping().getBreakPointsBlock().getOperNatPointList();
    }
    public ArrPointBlockPair buildArr(boolean _exact, String _clName) {
        String solved_ = ExecPartTypeUtil.correctClassPartsDynamic(_clName, this);
        if (koArr(solved_, _clName)) {
            return null;
        }
        return notNullBuildArr(_exact, solved_);
    }

    public ArrPointBlockPair notNullBuildArr(boolean _exact, String _clName) {
        return getClasses().getDebugMapping().getBreakPointsBlock().buildArr(_exact, _clName);
    }
    public ExcPointBlockPair build(boolean _exact, String _clName) {
        String solved_ = ExecPartTypeUtil.correctClassPartsDynamic(_clName, this);
        if (koExc(solved_, _clName)) {
            return null;
        }
        return notNullBuild(_exact, solved_);
    }

    public ExcPointBlockPair notNullBuild(boolean _exact, String _clName) {
        return getClasses().getDebugMapping().getBreakPointsBlock().build(_exact, _clName);
    }

    public ParPointBlockPair notNullBuildPar(boolean _exact, String _clName, RootBlock _de) {
        return getClasses().getDebugMapping().getBreakPointsBlock().buildPar(_exact, _clName, _de);
    }

    public BreakPointBlockList getBreakPointsBlock() {
        return getClasses().getDebugMapping().getBreakPointsBlock();
    }

    private static boolean koArr(String _solved, String _clName) {
        return !_solved.startsWith(StringExpUtil.ARR_CLASS) && !_clName.trim().isEmpty();
    }

    private static boolean koExc(String _solved, String _clName) {
        return _solved.isEmpty() && !_clName.trim().isEmpty();
    }

    public void toggleArrPointEnabled(String _clName, boolean _exact) {
        ArrPointBlockPair e_ = buildArr(_exact, _clName);
        if (e_ == null) {
            return;
        }
        toggleEnabledArr(e_);
    }

    public void toggleEnabledArr(ArrPointBlockPair _b) {
        for (ArrPointBlockPair b: arrList().elts()) {
            if (b.getEp().match(_b.getEp())) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        arrList().add(_b);
    }

    public void toggleArr(ArrPointBlockPair _b) {
        for (ArrPointBlockPair b: arrList().elts()) {
            if (b.getEp().match(_b.getEp())) {
                arrList().remove(b);
                return;
            }
        }
        arrList().add(_b);
    }
    public void toggleExcPointEnabled(String _clName, boolean _exact) {
        ExcPointBlockPair e_ = build(_exact, _clName);
        if (e_ == null) {
            return;
        }
        toggleEnabledExc(e_);
    }

    public void toggleEnabledExc(ExcPointBlockPair _b) {
        for (ExcPointBlockPair b: excList().elts()) {
            if (b.getEp().match(_b.getEp())) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        excList().add(_b);
    }

    public void toggleExc(ExcPointBlockPair _b) {
        for (ExcPointBlockPair b: excList().elts()) {
            if (b.getEp().match(_b.getEp())) {
                excList().remove(b);
                return;
            }
        }
        excList().add(_b);
    }

    public void toggleEnabledPar(ParPointBlockPair _b) {
        for (ParPointBlockPair b: parList().elts()) {
            if (b.getPp().match(_b.getPp())) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        parList().add(_b);
    }

    public void togglePar(ParPointBlockPair _b) {
        for (ParPointBlockPair b: parList().elts()) {
            if (b.getPp().match(_b.getPp())) {
                parList().remove(b);
                return;
            }
        }
        parList().add(_b);
    }

    public OperNatPointBlockPair toggleOperNat(OperNatPointBlockPair _b) {
        for (OperNatPointBlockPair b: operNatList().elts()) {
            if (b.getOn().match(_b.getOn())) {
                operNatList().remove(b);
                return operNatDisabled();
            }
        }
        operNatList().add(_b);
        return _b;
    }

    public OperNatPointBlockPair toggleEnableOperNat(OperNatPointBlockPair _b) {
        for (OperNatPointBlockPair b: operNatList().elts()) {
            if (b.getOn().match(_b.getOn())) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return b;
            }
        }
        operNatList().add(_b);
        return _b;
    }
    public void toggleEnabled(DisplayedStrings _d, MemberCallingsBlock _id) {
        MethodPointBlockPair pair_ = method(_d, _id);
        for (MethodPointBlockPair b: metList().elts()) {
            if (b.getMp().match(pair_.getMp())) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        metList().add(pair_);
    }
    public void toggleEnabledWatch(boolean _trField,SynthFieldInfo _field) {
        WatchPointBlockPair pair_ = watch(_trField, _field);
        for (WatchPointBlockPair b: watchList().elts()) {
            if (b.getWp().match(pair_.getWp())) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        watchList().add(pair_);
    }
    public IdMap<FileBlock, ExecFileBlock> getFiles() {
        return getClasses().getDebugMapping().getFiles();
    }
    public IdMap<ExecFileBlock, FileBlock> getRevFiles() {
        return getClasses().getDebugMapping().getRevFiles();
    }

    public boolean isWatch(boolean _trField,int _root, String _field) {
        return getNotNullWatch(_trField,_root, _field).isEnabled();
    }
    public boolean is(ExecFileBlock _file, int _offset) {
        return getNotNull(_file, _offset).isEnabled();
    }
    public boolean is(String _id) {
        return getNotNull(_id).isEnabled();
    }

    public boolean is(StandardNamedFunction _id) {
        return getNotNull(_id).isEnabled();
    }
    public boolean isArr(String _field, boolean _exact) {
        return getNotNullArr(_field, _exact).isEnabled();
    }
    public boolean isExc(String _field, boolean _exact) {
        return getNotNullExc(_field, _exact).isEnabled();
    }
    public boolean isPar(String _field, boolean _exact) {
        return getNotNullPar(_field, _exact).isEnabled();
    }
    public BreakPoint getNotNull(ExecFileBlock _file, int _offset) {
        return getNotNullPair(_file, _offset).getValue();
    }

    public BreakPointBlockPair getNotNullPair(ExecFileBlock _file, int _offset) {
        BreakPointBlockPair b_ = getPair(_file, _offset);
        return getClasses().getDebugMapping().getBreakPointsBlock().notNull(b_);
    }
    public MethodPoint getNotNull(StandardNamedFunction _id) {
        return getNotNullPair(_id).getValue();
    }
    public StdMethodPointBlockPair getNotNullPair(StandardNamedFunction _id) {
        StdMethodPointBlockPair b_ = getPair(_id);
        return getClasses().getDebugMapping().getBreakPointsBlock().notNull(b_);
    }
    public ArrPoint getNotNullArr(String _field, boolean _exact) {
        ArrPointBlockPair b_ = getNotNullArrPair(_field,_exact);
        return b_.getValue();
    }
    public ArrPointBlockPair getNotNullArrPair(String _field, boolean _exact) {
        ArrPointBlockPair b_ = getPairArr(_field,_exact);
        return getClasses().getDebugMapping().getBreakPointsBlock().notNullExp(b_);
    }
    public ExcPoint getNotNullExc(String _field, boolean _exact) {
        ExcPointBlockPair b_ = getNotNullExcPair(_field,_exact);
        return b_.getValue();
    }
    public ExcPointBlockPair getNotNullExcPair(String _field, boolean _exact) {
        ExcPointBlockPair b_ = getPairExc(_field,_exact);
        return getClasses().getDebugMapping().getBreakPointsBlock().notNullExp(b_);
    }
    public ParPoint getNotNullPar(String _field, boolean _exact) {
        ParPointBlockPair b_ = getNotNullPair(_field,_exact);
        return b_.getValue();
    }
    public ParPointBlockPair getNotNullPair(String _field, boolean _exact) {
        ParPointBlockPair b_ = getPairPar(_field,_exact);
        return getClasses().getDebugMapping().getBreakPointsBlock().notNullExp(b_);
    }

    public WatchPoint getNotNullWatch(boolean _trField, int _root, String _field) {
        return getNotNullWatchPair(_trField, _root, _field).getValue();
    }

    public WatchPointBlockPair getNotNullWatchPair(boolean _trField,int _root, String _field) {
        WatchPointBlockPair b_ = getPairWatch(_trField,_root, _field);
        return getClasses().getDebugMapping().getBreakPointsBlock().notNullWatch(b_);
    }
    public MethodPoint getNotNull(String _id) {
        return getNotNullPair(_id).getValue();
    }
    public MethodPointBlockPair getNotNullPair(String _id) {
        MethodPointBlockPair b_ = getPair(_id);
        return getClasses().getDebugMapping().getBreakPointsBlock().notNull(b_);
    }

    public BreakPointBlockPair getPair(ExecFileBlock _file, int _offset) {
        for (BreakPointBlockPair b: bpList().elts()) {
            if (b.getBp().match(_file, _offset)) {
                return b;
            }
        }
        return null;
    }
    public StdMethodPointBlockPair getPair(StandardNamedFunction _id) {
        for (StdMethodPointBlockPair b: stdList().elts()) {
            if (b.getSm().match(_id)) {
                return b;
            }
        }
        return null;
    }
    public MethodPointBlockPair getPair(String _id) {
        for (MethodPointBlockPair b: metList().elts()) {
            if (b.getMp().match(_id)) {
                return b;
            }
        }
        return null;
    }

    public ArrPointBlockPair getPairArr(String _field, boolean _exact) {
        for (ArrPointBlockPair b: arrList().elts()) {
            if (b.getEp().match(_field, _exact)) {
                return b;
            }
        }
        return null;
    }

    public ExcPointBlockPair getPairExc(String _field, boolean _exact) {
        for (ExcPointBlockPair b: excList().elts()) {
            if (b.getEp().match(_field, _exact)) {
                return b;
            }
        }
        return null;
    }

    public ParPointBlockPair getPairPar(String _field, boolean _exact) {
        for (ParPointBlockPair b: parList().elts()) {
            if (b.getPp().match(_field, _exact)) {
                return b;
            }
        }
        return null;
    }

    public OperNatPointBlockPair getPairOperNat(String _str) {
        for (OperNatPointBlockPair b: operNatList().elts()) {
            if (b.getOn().match(_str)) {
                return b;
            }
        }
        return operNatDisabled();
    }
    public WatchPointBlockPair getPairWatch(boolean _trField, int _root, String _field) {
        for (WatchPointBlockPair b: watchList().elts()) {
            if (b.getWp().match(_trField, _root, _field)) {
                return b;
            }
        }
        return null;
    }

    public boolean isTmp(ExecFileBlock _file, int _offset) {
        for (BreakPointBlockKey l: tmpList().elts()) {
            if (l.match(_file, _offset)) {
                return true;
            }
        }
        return false;
    }

    public AbstractAtomicBoolean pausedLoop() {
        return getClasses().getDebugMapping().getBreakPointsBlock().getPausedLoop();
    }

    public AbsCollection<BreakPointBlockKey> tmpList() {
        return getClasses().getDebugMapping().getBreakPointsBlock().getListTmp();
    }
    public void resetList() {
        getClasses().getDebugMapping().getBreakPointsBlock().resetList();
    }
    public GeneType getClassBody(String _type) {
        ExecRootBlock c_ = getExecutionInfos().getClasses().getClassBody(_type);
        if (c_ != null) {
            return c_;
        }
        return getExecutionInfos().getStandards().getStandards().getVal(_type);
    }
    public Initializer getInit() {
        return getExecutionInfos().getInitializer();
    }
    public int getStackOverFlow() {
        return getExecutionInfos().getStackOverFlow();
    }

    public int getTabWidth() {
        return getExecutionInfos().getTabWidth();
    }


    public AbstractMethodCriteria getDefCriteria() {
        return getExecutionInfos().getDefCriteria();
    }

    public AbstractMethodCriteria getStaticCriteria() {
        return getExecutionInfos().getStaticCriteria();
    }
    public Classes getClasses() {
        return getExecutionInfos().getClasses();
    }

    public AbstractInterceptorStdCaller getCaller() {
        return getExecutionInfos().getCaller();
    }

    public Coverage getCoverage() {
        return getExecutionInfos().getCoverage();
    }

    public LgNames getStandards() {
        return getExecutionInfos().getStandards();
    }

    public DefaultLockingClass getLocks() {
        return getExecutionInfos().getLocks();
    }
    public CommonExecutionInfos getExecutionInfos() {
        return executionInfos;
    }

    public void forwardAndClear() {
        ExecClassesUtil.buildIterable(getClasses(), this);
    }

    public boolean stopped() {
        return getInterrupt().get();
    }
    public boolean callsOrException(StackCall _stack) {
        return stopped() || _stack.callsOrException();
    }


    public AbstractExiting getExiting() {
        return exiting;
    }

    public void setExiting(AbstractExiting _exiting) {
        exiting = _exiting;
    }
}
