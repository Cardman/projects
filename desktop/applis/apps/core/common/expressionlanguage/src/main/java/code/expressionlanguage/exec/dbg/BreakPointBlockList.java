package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.*;
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
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
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

    public static int pref(AbsCollection<MethodPointBlockPair> _p) {
        Ints values_ = new Ints();
        for (MethodPointBlockPair m: _p.elts()) {
            values_.add(m.getPref().get());
        }
        values_.sort();
        int s_ = values_.size();
        for (int i = 1; i < s_; i++) {
            int one_ = values_.get(i - 1);
            int two_ = values_.get(i);
            if (two_ - one_ > 1) {
                return one_ + 1;
            }
        }
        return (int) (values_.getMaximum(-1)+1);
    }
    public void toggleBreakPoint(StandardType _t, StandardNamedFunction _i) {
        String k_ = build(_t, _i);
        StdMethodPointBlockPair pair_ = new StdMethodPointBlockPair(_i, _t, k_, interceptor, true);
        int i_ = 0;
        for (StdMethodPointBlockPair b: stdMethPointList.elts()) {
            if (b.getSm().match(pair_.getSm())) {
                stdMethPointList.remove(i_,b);
                return;
            }
            i_++;
        }
        stdMethPointList.add(pair_);
    }
    public void toggleBreakPointEnabled(StandardType _t, StandardNamedFunction _i) {
        String k_ = build(_t, _i);
        StdMethodPointBlockPair pair_ = new StdMethodPointBlockPair(_i, _t, k_, interceptor, true);
        for (StdMethodPointBlockPair b: stdMethPointList.elts()) {
            if (b.getSm().match(pair_.getSm())) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        stdMethPointList.add(pair_);
    }

    private String build(StandardType _t, StandardNamedFunction _i) {
        return _t.getNumber()+buildSep(_i)+_i.getNumber();
    }

    private static String buildSep(StandardNamedFunction _i) {
        if (_i instanceof StandardMethod) {
            return "_";
        }
        return ".";
    }

    public void toggleBreakPoint(String _file, int _offset, ResultContext _f) {
        toggleBreakPoint(_f.getPageEl().getPreviousFilesBodies().getVal(_file),_offset,_f.getForwards().dbg(),_f.getPageEl().getDisplayedStrings());
    }
    public void toggleBreakPoint(FileBlock _file, int _offset, DebugMapping _d, DisplayedStrings _s) {
        int o_ = ResultExpressionOperationNode.beginPart(_offset, _file);
        if (o_ < 0) {
            return;
        }
        MemberCallingsBlock id_ = ResultExpressionOperationNode.keyMethodBp(_offset, _file);
        if (id_ != null) {
            BracedBlock r_ = rootOfAnnot(id_);
            if (r_ instanceof RootBlock) {
                toggleWatch(false,new SynthFieldInfo(new ClassField("",((NamedCalledFunctionBlock)id_).getName()),(RootBlock)r_,((RootBlock)r_).getNumberAll()));
                return;
            }
            toggle(_s,id_);
            return;
        }
        ExecFileBlock f_ = _d.getFiles().getVal(_file);
        toggle(f_,FileBlock.number(_file),o_, ResultExpressionOperationNode.enabledTypeBp(_offset, _file));
    }
    public void toggle(DisplayedStrings _d, MemberCallingsBlock _id) {
        MethodPointBlockPair pair_ = new MethodPointBlockPair(_id,interceptor,ResultExpressionOperationNode.clName(_d, _id), pref(methPointList), true);
        int i_ = 0;
        for (MethodPointBlockPair b: methPointList.elts()) {
            if (b.getMp().match(pair_.getMp())) {
                methPointList.remove(i_,b);
                return;
            }
            i_++;
        }
        methPointList.add(pair_);
    }
    public void toggle(ExecFileBlock _file, int _nf, int _offset, boolean _enType) {
        BreakPointBlockPair pair_ = new BreakPointBlockPair(_file,_nf, _offset, interceptor,true);
        pair_.getValue().setEnabledChgtType(_enType);
        int i_ = 0;
        for (BreakPointBlockPair b: list.elts()) {
            if (b.getBp().match(pair_.getBp())) {
                list.remove(i_,b);
                return;
            }
            i_++;
        }
        list.add(pair_);
    }
    public void toggleBreakPointEnabled(String _file, int _offset, ResultContext _f) {
        toggleBreakPointEnabled(_f.getPageEl().getPreviousFilesBodies().getVal(_file),_offset,_f.getForwards().dbg(),_f.getPageEl().getDisplayedStrings());
    }
    public void toggleBreakPointEnabled(FileBlock _file, int _offset, DebugMapping _d, DisplayedStrings _s) {
        int o_ = ResultExpressionOperationNode.beginPart(_offset, _file);
        if (o_ < 0) {
            return;
        }
        MemberCallingsBlock id_ = ResultExpressionOperationNode.keyMethodBp(_offset, _file);
        if (id_ != null) {
            BracedBlock r_ = rootOfAnnot(id_);
            if (r_ instanceof RootBlock) {
                toggleEnabledWatch(false,new SynthFieldInfo(new ClassField("",((NamedCalledFunctionBlock)id_).getName()),(RootBlock)r_,((RootBlock)r_).getNumberAll()));
                return;
            }
            toggleEnabled(_s,id_);
            return;
        }
        ExecFileBlock f_ = _d.getFiles().getVal(_file);
        toggleEnabled(f_,FileBlock.number(_file),o_, ResultExpressionOperationNode.enabledTypeBp(_offset, _file));
    }
    public static BracedBlock rootOfAnnot(AbsBk _id) {
        if (AbsBk.isAnnotBlock(_id)) {
            return _id.getParent();
        }
        return null;
    }

    public void toggleEnabled(DisplayedStrings _d, MemberCallingsBlock _id) {
        MethodPointBlockPair pair_ = new MethodPointBlockPair(_id, interceptor,ResultExpressionOperationNode.clName(_d, _id), pref(methPointList), true);
        for (MethodPointBlockPair b: methPointList.elts()) {
            if (b.getMp().match(pair_.getMp())) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        methPointList.add(pair_);
    }
    public void toggleEnabled(ExecFileBlock _file, int _nf, int _offset, boolean _enType) {
        BreakPointBlockPair pair_ = new BreakPointBlockPair(_file,_nf, _offset, interceptor, true);
        pair_.getValue().setEnabledChgtType(_enType);
        for (BreakPointBlockPair b: list.elts()) {
            if (b.getBp().match(pair_.getBp())) {
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
        return new BreakPointLambdaCtxUpdaterStd(_f,_gene).update(ExecFileBlock.name(_bp.getBp().getFile()),_bp.getBp().getOffset(),_bp.getValue(),_newValue);
    }
    public static ReportedMessages breakPointCtxInstance(BreakPointBlockPair _bp, ResultContext _f, AbsLightContextGenerator _gene, String _newValue) {
        return new BreakPointLambdaCtxUpdaterInstance(_f,_gene).update(ExecFileBlock.name(_bp.getBp().getFile()),_bp.getBp().getOffset(),_bp.getValue(),_newValue);
    }
    public static ReportedMessages breakPointCtxStatic(BreakPointBlockPair _bp, ResultContext _f, AbsLightContextGenerator _gene, String _newValue) {
        return new BreakPointLambdaCtxUpdaterStatic(_f,_gene).update(ExecFileBlock.name(_bp.getBp().getFile()),_bp.getBp().getOffset(),_bp.getValue(),_newValue);
    }
    public void update(ExecFileBlock _file, int _offset, BreakPointBooleanUpdater _updater, boolean _newValue) {
        for (BreakPointBlockPair b: list.elts()) {
            if (b.getBp().match(_file, _offset)) {
                _updater.update(b.getValue(), _newValue);
                return;
            }
        }
    }
    public boolean is(ExecFileBlock _file, int _offset) {
        return getNotNull(_file, _offset).isEnabled();
    }

    public boolean is(StandardNamedFunction _id) {
        return getNotNull(_id).isEnabled();
    }
    public boolean is(String _id) {
        return getNotNull(_id).isEnabled();
    }
    public BreakPoint getNotNull(ExecFileBlock _file, int _offset) {
        return getNotNullPair(_file, _offset).getValue();
    }

    public BreakPointBlockPair getNotNullPair(ExecFileBlock _file, int _offset) {
        BreakPointBlockPair b_ = getPair(_file, _offset);
        if (b_ == null) {
            return new BreakPointBlockPair(null,-1,-1,interceptor,false);
        }
        return b_;
    }
    public BreakPointBlockPair getPair(ExecFileBlock _file, int _offset) {
        for (BreakPointBlockPair b: list.elts()) {
            if (b.getBp().match(_file, _offset)) {
                return b;
            }
        }
        return null;
    }
    public MethodPoint getNotNull(StandardNamedFunction _id) {
        return getNotNullPair(_id).getValue();
    }
    public StdMethodPointBlockPair getNotNullPair(StandardNamedFunction _id) {
        StdMethodPointBlockPair b_ = getPair(_id);
        if (b_ == null) {
            return new StdMethodPointBlockPair(null,null,"",interceptor,false);
        }
        return b_;
    }

    public StdMethodPointBlockPair getPair(StandardNamedFunction _id) {
        for (StdMethodPointBlockPair b: stdMethPointList.elts()) {
            if (b.getSm().match(_id)) {
                return b;
            }
        }
        return null;
    }
    public MethodPoint getNotNull(String _id) {
        return getNotNullPair(_id).getValue();
    }
    public MethodPointBlockPair getNotNullPair(String _id) {
        MethodPointBlockPair b_ = getPair(_id);
        if (b_ == null) {
            return new MethodPointBlockPair(null,interceptor,"",-1,false);
        }
        return b_;
    }

    public MethodPointBlockPair getPair(String _id) {
        for (MethodPointBlockPair b: methPointList.elts()) {
            if (b.getMp().match(_id)) {
                return b;
            }
        }
        return null;
    }

    public CustList<StdMethodPointBlockPair> getStdPairs(StandardNamedFunction _id) {
        CustList<StdMethodPointBlockPair> out_ = new CustList<StdMethodPointBlockPair>();
        for (StdMethodPointBlockPair b: stdMethPointList.elts()) {
            StandardNamedFunction i_ = b.getSm().getId();
            if (i_ == _id) {
                out_.add(b);
            }
        }
        return out_;
    }
    public CustList<MethodPointBlockPairRootBlock> getPairs(ExecBlock _id, ExecFormattedRootBlock _gl, ContextEl _context, Struct _instance) {
        String argClassName_ = _instance.getClassName(_context);
        String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        CustList<MethodPointBlockPairRootBlock> out_ = new CustList<MethodPointBlockPairRootBlock>();
        String id_ = id(_id);
        for (MethodPointBlockPair b: methPointList.elts()) {
            MemberCallingsBlock i_ = b.getMp().getId();
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
            if (b.getBp().matchRow(_file, _ana, r_)) {
                list_.add(b);
            }
        }
        return list_;
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

    public boolean isTmp(ExecFileBlock _file, int _offset) {
        for (BreakPointBlockKey l: listTmp.elts()) {
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
            if (b.getEp().match(pair_.getEp())) {
                excPointList.remove(i_,b);
                return;
            }
            i_++;
        }
        excPointList.add(pair_);
    }

    private ExcPointBlockPair build(boolean _exact, String _clName) {
        if (_exact) {
            return new ExcPointBlockPair(true, _clName, interceptor, true);
        }
        return new ExcPointBlockPair(false, StringExpUtil.getIdFromAllTypes(_clName), interceptor, true);
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
            if (b.getEp().match(pair_.getEp())) {
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
        ExcPointBlockPair b_ = getNotNullExcPair(_field,_exact);
        return b_.getValue();
    }
    public ExcPointBlockPair getNotNullExcPair(String _field, boolean _exact) {
        ExcPointBlockPair b_ = getPairExc(_field,_exact);
        if (b_ == null) {
            return new ExcPointBlockPair(false,"",interceptor,false);
        }
        return b_;
    }


    public ExcPointBlockPair getPairExc(String _field, boolean _exact) {
        for (ExcPointBlockPair b: excPointList.elts()) {
            if (b.getEp().match(_field, _exact)) {
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
        toggleWatch(true,o_);
    }
    public void toggleWatch(boolean _trField,SynthFieldInfo _field) {
        WatchPointBlockPair pair_ = new WatchPointBlockPair(_trField,_field.getRootBlock(),_field.getRootBlockNb(),_field.getClassField().getFieldName(), interceptor,true);
        int i_ = 0;
        for (WatchPointBlockPair b: watchList.elts()) {
            if (b.getWp().match(pair_.getWp())) {
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
        toggleEnabledWatch(true,o_);
    }
    public void toggleEnabledWatch(boolean _trField,SynthFieldInfo _field) {
        WatchPointBlockPair pair_ = new WatchPointBlockPair(_trField,_field.getRootBlock(),_field.getRootBlockNb(),_field.getClassField().getFieldName(), interceptor,true);
        for (WatchPointBlockPair b: watchList.elts()) {
            if (b.getWp().match(pair_.getWp())) {
                b.getValue().setEnabled(!b.getValue().isEnabled());
                return;
            }
        }
        watchList.add(pair_);
    }
    public boolean isWatch(boolean _trField,int _root, String _field) {
        return getNotNullWatch(_trField,_root, _field).isEnabled();
    }

    public WatchPoint getNotNullWatch(boolean _trField,int _root, String _field) {
        return getNotNullWatchPair(_trField, _root, _field).getValue();
    }

    public WatchPointBlockPair getNotNullWatchPair(boolean _trField,int _root, String _field) {
        WatchPointBlockPair b_ = getPairWatch(_trField,_root, _field);
        if (b_ == null) {
            return new WatchPointBlockPair(false,null,-1,"",interceptor,false);
        }
        return b_;
    }
    public WatchPointBlockPair getPairWatch(boolean _trField,int _root, String _field) {
        for (WatchPointBlockPair b: watchList.elts()) {
            if (b.getWp().match(_trField, _root, _field)) {
                return b;
            }
        }
        return null;
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
