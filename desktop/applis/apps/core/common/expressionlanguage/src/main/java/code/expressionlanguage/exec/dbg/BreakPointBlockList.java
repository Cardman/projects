package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.BracedBlock;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.SynthFieldInfo;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecReturnableWithSignature;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.Ints;
import code.util.core.StringUtil;

public final class BreakPointBlockList {
    private final AbsCollection<BreakPointBlockPair> list;
    private final AbsCollection<BreakPointBlockKey> listTmp;
    private final AbstractInterceptorStdCaller interceptor;
    private final AbstractAtomicBoolean pausedLoop;
    private final AbsCollection<WatchPointBlockPair> watchList;
    private final AbsCollection<ExcPointBlockPair> excPointList;
    private final AbsCollection<MethodPointBlockPair> methPointList;
    private final AbsCollection<StdMethodPointBlockPair> stdMethPointList;

    public BreakPointBlockList(AbstractInterceptorStdCaller _i) {
        interceptor = _i;
        listTmp = _i.newBreakPointKeyIdStringCollection();
        list = _i.newBreakPointKeyStringCollection();
        watchList = _i.newWatchPointKeyStringCollection();
        excPointList = _i.newExcPointKeyStringCollection();
        methPointList = _i.newMethodPointKeyStringCollection();
        stdMethPointList = _i.newStdMethodPointKeyStringCollection();
        pausedLoop = _i.newAtBool();
    }

    public static int pref(AbsCollection<MethodPointBlockPair> _p, boolean _exit) {
        Ints values_ = new Ints();
        for (MethodPointBlockPair m: _p.elts()) {
            values_.add(m.getValue().result(_exit).getPref().get());
        }
        return pref(values_);
    }

    public static int pref(AbsCollection<MethodPointBlockPair> _p, boolean _exit, String _cl) {
        Ints values_ = new Ints();
        for (MethodPointBlockPair m: _p.elts()) {
            for (EntryCust<String,Integer> e: m.getValue().result(_exit).getPrefs().elts()) {
                if (StringUtil.quickEq(e.getKey(),_cl)) {
                    values_.add(e.getValue());
                }
            }
        }
        return pref(values_);
    }

    private static int pref(Ints _values) {
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

    public MethodPointBlockPair method(DisplayedStrings _d, MemberCallingsBlock _id) {
        return new MethodPointBlockPair(_id, interceptor,ResultExpressionOperationNode.clName(_d, _id),true, MemberCallingsBlock.clName(_id));
    }

    public BreakPointBlockPair bp(ExecFileBlock _file, int _nf, int _offset, boolean _enType) {
        BreakPointBlockPair pair_ = new BreakPointBlockPair(_file,_nf, _offset, interceptor,true);
        pair_.getValue().setEnabledChgtType(_enType);
        return pair_;
    }

    public static BracedBlock rootOfAnnot(AbsBk _id) {
        if (AbsBk.isAnnotBlock(_id)) {
            return _id.getParent();
        }
        return null;
    }

    public static void breakPointEnabled(String _file, int _offset, ResultContext _f, boolean _newValue) {
        breakPointUpdate(_file, _offset, _f, new BreakPointBooleanUpdaterEnabled(),_newValue);
    }
    public static void breakPointInstanceType(String _file, int _offset, ResultContext _f, boolean _newValue) {
        breakPointUpdate(_file, _offset, _f, new BreakPointBooleanUpdaterInstanceType(),_newValue);
    }
    public static void breakPointStaticType(String _file, int _offset, ResultContext _f, boolean _newValue) {
        breakPointUpdate(_file, _offset, _f, new BreakPointBooleanUpdaterStaticType(),_newValue);
    }
    public static void breakPointUpdate(String _file, int _offset, ResultContext _f, BreakPointBooleanUpdater _updater, boolean _newValue) {
        breakPointUpdate(_f.getPageEl().getPreviousFilesBodies().getVal(_file),_offset, _updater,_newValue, _f.getFiles(), _f.bpList());
    }
    public static void breakPointUpdate(FileBlock _file, int _offset, BreakPointBooleanUpdater _updater, boolean _newValue, IdMap<FileBlock, ExecFileBlock> _files, AbsCollection<BreakPointBlockPair> _ls) {
        int o_ = ResultExpressionOperationNode.beginPart(_offset, _file);
        if (o_ < 0) {
            return;
        }
        ExecFileBlock f_ = _files.getVal(_file);
        update(f_,o_, _updater,_newValue, _ls);
    }
    public static void breakPointFileIndexUpdaterExcludeStd(BreakPointBlockPair _bp,CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterExcludeStd().update(_bp.getValue(),_newValue);
    }
    public static void breakPointFileIndexUpdaterExcludeInstance(BreakPointBlockPair _bp, CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterExcludeInstance().update(_bp.getValue(),_newValue);
    }
    public static void breakPointFileIndexUpdaterExcludeStatic(BreakPointBlockPair _bp, CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterExcludeStatic().update(_bp.getValue(),_newValue);
    }
    public static void breakPointFileIndexUpdaterIncludeStd(BreakPointBlockPair _bp,CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterIncludeStd().update(_bp.getValue(),_newValue);
    }
    public static void breakPointFileIndexUpdaterIncludeInstance(BreakPointBlockPair _bp, CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterIncludeInstance().update(_bp.getValue(),_newValue);
    }
    public static void breakPointFileIndexUpdaterIncludeStatic(BreakPointBlockPair _bp, CustList<AbsCallContraints> _newValue) {
        new BreakPointFileIndexUpdaterIncludeStatic().update(_bp.getValue(),_newValue);
    }
    public static void breakPointCountStd(BreakPointBlockPair _bp,int _newValue) {
        new BreakPointCountUpdaterStd().update(_bp.getValue(),_newValue);
    }
    public static void breakPointCountInstance(BreakPointBlockPair _bp, int _newValue) {
        new BreakPointCountUpdaterInstance().update(_bp.getValue(),_newValue);
    }
    public static void breakPointCountStatic(BreakPointBlockPair _bp, int _newValue) {
        new BreakPointCountUpdaterStatic().update(_bp.getValue(),_newValue);
    }
    public static ReportedMessages breakPointCtxStd(BreakPointBlockPair _bp, ResultContext _f, AbsLightContextGenerator _gene, String _newValue) {
        return new BreakPointLambdaCtxUpdaterStd(_f,_gene).update(ExecFileBlock.name(_bp.getBp().getFile()),_bp.getBp().getOffset(),_bp.getValue(),_newValue);
    }
    public static ReportedMessages breakPointCtxInstance(BreakPointBlockPair _bp, ResultContext _f, AbsLightContextGenerator _gene, String _newValue) {
        return new BreakPointLambdaCtxUpdaterInstance(_f,_gene).update(ExecFileBlock.name(_bp.getBp().getFile()),_bp.getBp().getOffset(),_bp.getValue(),_newValue);
    }
    public static ReportedMessages breakPointCtxStatic(BreakPointBlockPair _bp, ResultContext _f, AbsLightContextGenerator _gene, String _newValue) {
        return new BreakPointLambdaCtxUpdaterStatic(_f,_gene).update(ExecFileBlock.name(_bp.getBp().getFile()),_bp.getBp().getOffset(),_bp.getValue(),_newValue);
    }
    public static void update(ExecFileBlock _file, int _offset, BreakPointBooleanUpdater _updater, boolean _newValue, AbsCollection<BreakPointBlockPair> _ls) {
        for (BreakPointBlockPair b: _ls.elts()) {
            if (b.getBp().match(_file, _offset)) {
                _updater.update(b.getValue(), _newValue);
                return;
            }
        }
    }

    public BreakPointBlockPair notNull(BreakPointBlockPair _b) {
        if (_b == null) {
            return new BreakPointBlockPair(null,-1,-1,interceptor,false);
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
        for (MethodPointBlockPair b: getMethPointList().elts()) {
            b.getValue().resetCount();
        }
        for (StdMethodPointBlockPair b: getStdMethPointList().elts()) {
            b.getValue().resetCount();
        }
        for (ExcPointBlockPair b: getExcPointList().elts()) {
            b.getValue().resetCount();
        }
        for (WatchPointBlockPair b: getWatchList().elts()) {
            b.getValue().resetCount();
        }
    }

    public ExcPointBlockPair build(boolean _exact, String _clName) {
        if (_exact) {
            return new ExcPointBlockPair(true, _clName, interceptor, true);
        }
        return new ExcPointBlockPair(false, StringExpUtil.getIdFromAllTypes(_clName), interceptor, true);
    }

    public ExcPointBlockPair notNullExp(ExcPointBlockPair _b) {
        if (_b == null) {
            return new ExcPointBlockPair(false,"",interceptor,false);
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

    public AbsCollection<ExcPointBlockPair> getExcPointList() {
        return excPointList;
    }

    public AbsCollection<BreakPointBlockPair> getList() {
        return list;
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

    public AbsCollection<BreakPointBlockKey> getListTmp() {
        return listTmp;
    }

    public AbstractAtomicBoolean getPausedLoop() {
        return pausedLoop;
    }
}
