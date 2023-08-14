package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.SynthFieldInfo;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.blocks.ExecReturnableWithSignature;
import code.expressionlanguage.exec.types.ExecPartTypeUtil;
import code.expressionlanguage.exec.util.ClassMethodIdOverride;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.core.StringUtil;

public final class BreakPointBlockList {
    private final AbsCollection<BreakPointBlockPair> list;
    private final AbsCollection<BreakPointBlockPair> listTmp;
    private final AbstractInterceptorStdCaller interceptor;
    private final AbstractAtomicBoolean pausedLoop;
    private final AbsCollection<WatchPointBlockPair> watchList;
    private final AbsCollection<ExcPointBlockPair> excPointList;
    private final AbsCollection<MethodPointBlockPair> methPointList;

    public BreakPointBlockList(AbstractInterceptorStdCaller _i) {
        interceptor = _i;
        listTmp = _i.newBreakPointKeyStringCollection();
        list = _i.newBreakPointKeyStringCollection();
        watchList = _i.newWatchPointKeyStringCollection();
        excPointList = _i.newExcPointKeyStringCollection();
        methPointList = _i.newMethodPointKeyStringCollection();
        pausedLoop = _i.newAtBool();
    }

    public void toggleBreakPoint(String _file, int _offset, ResultContext _f) {
        toggleBreakPoint(_f.getPageEl().getPreviousFilesBodies().getVal(_file),_offset,_f.getForwards().dbg());
    }
    public void toggleBreakPoint(FileBlock _file, int _offset, DebugMapping _d) {
        int o_ = ResultExpressionOperationNode.beginPart(_offset, _file);
        if (o_ < 0) {
            return;
        }
        MemberCallingsBlock id_ = ResultExpressionOperationNode.keyMethodBp(_offset, _file);
        if (id_ != null) {
            toggle(id_);
            return;
        }
        ExecFileBlock f_ = _d.getFiles().getVal(_file);
        toggle(f_,o_, ResultExpressionOperationNode.enabledTypeBp(_offset, _file));
    }
    public void toggle(MemberCallingsBlock _id) {
        MethodPoint v_ = new MethodPoint(interceptor);
        v_.setEnabled(true);
        MethodPointBlockPair pair_ = new MethodPointBlockPair(_id, v_);
        int i_ = 0;
        for (MethodPointBlockPair b: methPointList.elts()) {
            if (b.match(pair_)) {
                methPointList.remove(i_,b);
                return;
            }
            i_++;
        }
        methPointList.add(pair_);
    }
    public void toggle(ExecFileBlock _file, int _offset, boolean _enType) {
        BreakPoint v_ = new BreakPoint(interceptor);
        v_.setEnabled(true);
        v_.setEnabledChgtType(_enType);
        BreakPointBlockPair pair_ = new BreakPointBlockPair(_file, _offset, v_);
        int i_ = 0;
        for (BreakPointBlockPair b: list.elts()) {
            if (b.match(pair_)) {
                list.remove(i_,b);
                return;
            }
            i_++;
        }
        list.add(pair_);
    }
    public void toggleBreakPointEnabled(String _file, int _offset, ResultContext _f) {
        toggleBreakPointEnabled(_f.getPageEl().getPreviousFilesBodies().getVal(_file),_offset,_f.getForwards().dbg());
    }
    public void toggleBreakPointEnabled(FileBlock _file, int _offset, DebugMapping _d) {
        int o_ = ResultExpressionOperationNode.beginPart(_offset, _file);
        if (o_ < 0) {
            return;
        }
        MemberCallingsBlock id_ = ResultExpressionOperationNode.keyMethodBp(_offset, _file);
        if (id_ != null) {
            toggleEnabled(id_);
            return;
        }
        ExecFileBlock f_ = _d.getFiles().getVal(_file);
        toggleEnabled(f_,o_, ResultExpressionOperationNode.enabledTypeBp(_offset, _file));
    }

    public void toggleEnabled(MemberCallingsBlock _id) {
        MethodPoint v_ = new MethodPoint(interceptor);
        v_.setEnabled(true);
        MethodPointBlockPair pair_ = new MethodPointBlockPair(_id, v_);
        for (MethodPointBlockPair b: methPointList.elts()) {
            if (b.match(pair_)) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        methPointList.add(pair_);
    }
    public void toggleEnabled(ExecFileBlock _file, int _offset, boolean _enType) {
        BreakPoint v_ = new BreakPoint(interceptor);
        v_.setEnabled(true);
        v_.setEnabledChgtType(_enType);
        BreakPointBlockPair pair_ = new BreakPointBlockPair(_file, _offset, v_);
        for (BreakPointBlockPair b: list.elts()) {
            if (b.match(pair_)) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        list.add(pair_);
    }
    public void breakPointEnabled(String _file, int _offset, ResultContext _f, boolean _newValue) {
        breakPointUpdate(_file, _offset, _f, new BreakPointBooleanUpdaterEnabled(),_newValue);
    }
    public void breakPointInstanceType(String _file, int _offset, ResultContext _f, boolean _newValue) {
        breakPointUpdate(_file, _offset, _f, new BreakPointBooleanUpdaterInstanceType(),_newValue);
    }
    public void breakPointStaticType(String _file, int _offset, ResultContext _f, boolean _newValue) {
        breakPointUpdate(_file, _offset, _f, new BreakPointBooleanUpdaterStaticType(),_newValue);
    }
    public void breakPointUpdate(String _file, int _offset, ResultContext _f, BreakPointBooleanUpdater _updater, boolean _newValue) {
        breakPointUpdate(_f.getPageEl().getPreviousFilesBodies().getVal(_file),_offset,_f.getForwards().dbg(), _updater,_newValue);
    }
    public void breakPointUpdate(FileBlock _file, int _offset, DebugMapping _d, BreakPointBooleanUpdater _updater, boolean _newValue) {
        int o_ = ResultExpressionOperationNode.beginPart(_offset, _file);
        if (o_ < 0) {
            return;
        }
        ExecFileBlock f_ = _d.getFiles().getVal(_file);
        update(f_,o_, _updater,_newValue);
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
        return new BreakPointLambdaCtxUpdaterStd(_f,_gene).update(ExecFileBlock.name(_bp.getFile()),_bp.getOffset(),_bp.getValue(),_newValue);
    }
    public static ReportedMessages breakPointCtxInstance(BreakPointBlockPair _bp, ResultContext _f, AbsLightContextGenerator _gene, String _newValue) {
        return new BreakPointLambdaCtxUpdaterInstance(_f,_gene).update(ExecFileBlock.name(_bp.getFile()),_bp.getOffset(),_bp.getValue(),_newValue);
    }
    public static ReportedMessages breakPointCtxStatic(BreakPointBlockPair _bp, ResultContext _f, AbsLightContextGenerator _gene, String _newValue) {
        return new BreakPointLambdaCtxUpdaterStatic(_f,_gene).update(ExecFileBlock.name(_bp.getFile()),_bp.getOffset(),_bp.getValue(),_newValue);
    }
    public void update(ExecFileBlock _file, int _offset, BreakPointBooleanUpdater _updater, boolean _newValue) {
        for (BreakPointBlockPair b: list.elts()) {
            if (b.match(_file, _offset)) {
                _updater.update(b.getValue(), _newValue);
                return;
            }
        }
    }
    public boolean is(ExecFileBlock _file, int _offset) {
        return getNotNull(_file, _offset).isEnabled();
    }

    public boolean is(String _id) {
        return getNotNull(_id).isEnabled();
    }
    public BreakPoint getNotNull(ExecFileBlock _file, int _offset) {
        BreakPointBlockPair b_ = getPair(_file, _offset);
        if (b_ == null) {
            BreakPoint bp_ = new BreakPoint(interceptor);
            bp_.setEnabled(false);
            return bp_;
        }
        return b_.getValue();
    }

    public BreakPointBlockPair getPair(ExecFileBlock _file, int _offset) {
        for (BreakPointBlockPair b: list.elts()) {
            if (b.match(_file, _offset)) {
                return b;
            }
        }
        return null;
    }
    public MethodPoint getNotNull(String _id) {
        MethodPointBlockPair b_ = getPair(_id);
        if (b_ == null) {
            MethodPoint bp_ = new MethodPoint(interceptor);
            bp_.setEnabled(false);
            return bp_;
        }
        return b_.getValue();
    }

    public MethodPointBlockPair getPair(String _id) {
        for (MethodPointBlockPair b: methPointList.elts()) {
            if (b.match(_id)) {
                return b;
            }
        }
        return null;
    }

    public CustList<MethodPointBlockPairRootBlock> getPairs(ExecBlock _id, ExecFormattedRootBlock _gl, ContextEl _context, Struct _instance) {
        String argClassName_ = _instance.getClassName(_context);
        String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        CustList<MethodPointBlockPairRootBlock> out_ = new CustList<MethodPointBlockPairRootBlock>();
        String id_ = id(_id);
        for (MethodPointBlockPair b: methPointList.elts()) {
            MemberCallingsBlock i_ = b.getId();
            int nb_ = nb(i_);
            if (_context.getClasses().getRedirections().isValidIndex(nb_)) {
                ClassMethodIdOverride v_ = _context.getClasses().getRedirections().get(nb_).getVal(MemberCallingsBlock.clName(i_));
                if (v_ != null) {
                    ExecOverrideInfo ov_ = v_.getVal(base_);
                    if (ov_ != null && ov_.getPair().getFct() == _id) {
                        out_.add(new MethodPointBlockPairRootBlock(b,ExecFormattedRootBlock.getFullObject(argClassName_,new ExecFormattedRootBlock(v_.getRoot()),_context)));
                        continue;
                    }
                }
            }
            if (StringUtil.quickEq(MemberCallingsBlock.clName(i_),id_)) {
                out_.add(new MethodPointBlockPairRootBlock(b,_gl));
            }
        }
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

    public static String id(ExecBlock _id) {
        if (_id instanceof ExecReturnableWithSignature) {
            return ((ExecReturnableWithSignature) _id).id();
        }
        return "";
    }
    public CustList<BreakPointBlockPair> bp(ExecFileBlock _file, FileMetrics _ana, int _off) {
        int r_ = _ana.getRowFile(_off);
        CustList<BreakPointBlockPair> list_ = new CustList<BreakPointBlockPair>();
        for (BreakPointBlockPair b: list.elts()) {
            if (b.matchRow(_file, _ana, r_)) {
                list_.add(b);
            }
        }
        return list_;
    }
    public void resetList() {
        for (BreakPointBlockPair b: list.elts()) {
            b.getValue().resetCount();
        }
        for (MethodPointBlockPair b: methPointList.elts()) {
            b.getValue().resetCount();
        }
        for (ExcPointBlockPair b: excPointList.elts()) {
            b.getValue().resetCount();
        }
        for (WatchPointBlockPair b: watchList.elts()) {
            b.getValue().resetCount();
        }
    }

    public boolean isTmp(ExecFileBlock _file, int _offset) {
        for (BreakPointBlockPair l: listTmp.elts()) {
            if (l.match(_file, _offset)) {
                return true;
            }
        }
        return false;
    }

    public void toggleExcPoint(String _clName, ResultContext _f, boolean _exact) {
        String solved_ = ExecPartTypeUtil.correctClassPartsDynamic(_clName, _f.getContext());
        if (koExc(solved_, _clName)) {
            return;
        }
        toggleExc(solved_,_exact);
    }
    public void toggleExc(String _clName, boolean _exact) {
        ExcPointBlockPair pair_ = build(_exact, _clName);
        int i_ = 0;
        for (ExcPointBlockPair b: excPointList.elts()) {
            if (b.match(pair_)) {
                excPointList.remove(i_,b);
                return;
            }
            i_++;
        }
        excPointList.add(pair_);
    }

    private ExcPointBlockPair build(boolean _exact, String _clName) {
        ExcPoint v_ = new ExcPoint(interceptor);
        v_.setEnabled(true);
        v_.setThrown(true);
        v_.setCaught(true);
        v_.setPropagated(true);
        if (_exact) {
            return new ExcPointBlockPair(true, _clName, v_);
        }
        return new ExcPointBlockPair(false, StringExpUtil.getIdFromAllTypes(_clName), v_);
    }

    public void toggleExcPointEnabled(String _clName, ResultContext _f, boolean _exact) {
        String solved_ = ExecPartTypeUtil.correctClassPartsDynamic(_clName, _f.getContext());
        if (koExc(solved_, _clName)) {
            return;
        }
        toggleEnabledExc(solved_,_exact);
    }

    private static boolean koExc(String _solved, String _clName) {
        return _solved.isEmpty() && !_clName.trim().isEmpty();
    }

    public void toggleEnabledExc(String _field, boolean _exact) {
        ExcPointBlockPair pair_ = build(_exact, _field);
        for (ExcPointBlockPair b: excPointList.elts()) {
            if (b.match(pair_)) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        excPointList.add(pair_);
    }

    public boolean isExc(String _field, boolean _exact) {
        return getNotNullExc(_field,_exact).isEnabled();
    }
    public ExcPoint getNotNullExc(String _field, boolean _exact) {
        ExcPointBlockPair b_ = getPairExc(_field,_exact);
        if (b_ == null) {
            ExcPoint bp_ = new ExcPoint(interceptor);
            bp_.setEnabled(false);
            return bp_;
        }
        return b_.getValue();
    }

    public ExcPointBlockPair getPairExc(String _field, boolean _exact) {
        for (ExcPointBlockPair b: excPointList.elts()) {
            if (b.match(_field,_exact)) {
                return b;
            }
        }
        return null;
    }
    public void toggleWatchPoint(String _file, int _offset, ResultContext _f) {
        SynthFieldInfo o_ = ResultExpressionOperationNode.vexerChamps(_f.getPageEl(), _file, _offset);
        if (o_.getRootBlock() == null) {
            return;
        }
        toggleWatch(o_);
    }
    public void toggleWatch(SynthFieldInfo _field) {
        WatchPoint v_ = new WatchPoint(interceptor);
        v_.setEnabled(true);
        v_.setRead(true);
        v_.setWrite(true);
        v_.setCompoundRead(true);
        v_.setCompoundWrite(true);
        v_.setCompoundWriteErr(true);
        WatchPointBlockPair pair_ = new WatchPointBlockPair(_field.getRootBlock(),_field.getRootBlock().getNumberAll(),_field.getClassField(), v_);
        int i_ = 0;
        for (WatchPointBlockPair b: watchList.elts()) {
            if (b.match(pair_)) {
                watchList.remove(i_,b);
                return;
            }
            i_++;
        }
        watchList.add(pair_);
    }
    public void toggleWatchPointEnabled(String _file, int _offset, ResultContext _f) {
        SynthFieldInfo o_ = ResultExpressionOperationNode.vexerChamps(_f.getPageEl(), _file, _offset);
        if (o_.getRootBlock() == null) {
            return;
        }
        toggleEnabledWatch(o_);
    }
    public void toggleEnabledWatch(SynthFieldInfo _field) {
        WatchPoint v_ = new WatchPoint(interceptor);
        v_.setEnabled(true);
        v_.setRead(true);
        v_.setWrite(true);
        v_.setCompoundRead(true);
        v_.setCompoundWrite(true);
        v_.setCompoundWriteErr(true);
        WatchPointBlockPair pair_ = new WatchPointBlockPair(_field.getRootBlock(),_field.getRootBlock().getNumberAll(),_field.getClassField(), v_);
        for (WatchPointBlockPair b: watchList.elts()) {
            if (b.match(pair_)) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        watchList.add(pair_);
    }
    public boolean isWatch(int _root, String _field) {
        return getNotNullWatch(_root, _field).isEnabled();
    }

    public WatchPoint getNotNullWatch(int _root, String _field) {
        WatchPointBlockPair b_ = getPairWatch(_root, _field);
        if (b_ == null) {
            WatchPoint bp_ = new WatchPoint(interceptor);
            bp_.setEnabled(false);
            return bp_;
        }
        return b_.getValue();
    }

    public WatchPointBlockPair getPairWatch(int _root, String _field) {
        for (WatchPointBlockPair b: watchList.elts()) {
            if (b.match(_root, _field)) {
                return b;
            }
        }
        return null;
    }
    public AbsCollection<BreakPointBlockPair> getListTmp() {
        return listTmp;
    }

    public AbstractAtomicBoolean getPausedLoop() {
        return pausedLoop;
    }
}
