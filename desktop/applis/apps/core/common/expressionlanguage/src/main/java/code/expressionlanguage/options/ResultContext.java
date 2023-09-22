package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.SymbolFactoryUtil;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.*;
import code.expressionlanguage.common.symbol.CommonOperNullSafe;
import code.expressionlanguage.exec.AbsStackStopper;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.DefStackStopper;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.exec.types.ExecPartTypeUtil;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public final class ResultContext {
    private final AnalyzedPageEl pageEl;
    private ContextEl context;
    private final Forwards forwards;
    private final ReportedMessages reportedMessages;

    public ResultContext(AnalyzedPageEl _page, Forwards _fwd) {
        this(init(_page),_fwd, _page.getMessages());
    }
    public ResultContext(AnalyzedPageEl _page, Forwards _fwd, ReportedMessages _reportedMessages) {
        pageEl = _page;
        this.forwards = _fwd;
        this.reportedMessages = _reportedMessages;
    }
    private static AnalyzedPageEl init(AnalyzedPageEl _page) {
        ClassesUtil.buildCoreBracesBodies(_page);
        return _page;
    }

    public static ResultContext def(ResultContext _base, StringMap<String> _files, String _filter) {
        return def(_base, _files, _filter, new DefStackStopper());
    }
    public static ResultContext def(ResultContext _base, StringMap<String> _files, String _filter, AbsStackStopper _s) {
        AnalyzedPageEl resultAna_ = def(_base.getPageEl(), _files, _filter);
        return afterDef(_base, _files, resultAna_, _s);
    }

    public static ResultContext afterDef(ResultContext _base, StringMap<String> _files, AnalyzedPageEl _resultAna) {
        return afterDef(_base, _files, _resultAna, new DefStackStopper());
    }
    public static ResultContext afterDef(ResultContext _base, StringMap<String> _files, AnalyzedPageEl _resultAna, AbsStackStopper _s) {
        Forwards forwards_ = new Forwards(_base.getForwards(), _s);
        forwards_.getResources().addAllEntries(_files);
        forwards_.getClasses().getCommon().setStaticFields(_resultAna.getStaticFields());
        return new ResultContext(_resultAna, forwards_, _resultAna.getMessages());
    }

    public static AnalyzedPageEl def(AnalyzedPageEl _base, StringMap<String> _files, String _filter) {
        StringMap<String> srcFiles_ = ContextFactory.filter(_files, _filter);
        return defFilter(_base, _files, srcFiles_);
    }

    public static AnalyzedPageEl defFilter(AnalyzedPageEl _base, StringMap<String> _files, StringMap<String> _src) {
        AnalyzedPageEl resultAna_ = defFilterUser(_base, _files, _src);
        postValidate(resultAna_);
        return resultAna_;
    }

    public static AnalyzedPageEl defFilterUser(AnalyzedPageEl _base, StringMap<String> _files, StringMap<String> _src) {
        AnalyzedPageEl copy_ = AnalyzedPageEl.copy(_base);
        copy_.setResources(_files);
        return ClassesUtil.buildUserCode(_src, copy_);
    }

    public static void fwd(ResultContext _res, AbsLightContextGenerator _g) {
        if (!_res.getPageEl().getMessages().isAllEmptyErrors()) {
            return;
        }
        fwdWithoutCheck(_res, _g);
    }

    public static void fwdWithoutCheck(ResultContext _res, AbsLightContextGenerator _g) {
        ForwardInfos.generalForward(_res);
        ContextEl ctx_ = _g.gene(_res.getForwards());
        Classes.forwardAndClear(ctx_);
        _res.setContext(ctx_);
    }

    public static void postValidate(AnalyzedPageEl _page) {
        _page.setCustomAna(true);
        ClassesUtil.checkImpls(_page);
        if (_page.isGettingErrors()) {
            _page.getToStringOwners().add(_page.getAliasObject());
            _page.getRandCodeOwners().add(_page.getAliasObject());
            for (EntryCust<RootBlock, ClassMethodIdReturn> e: _page.getToStr().entryList()) {
                String fullName_ = e.getKey().getFullName();
                _page.getToStringOwners().add(fullName_);
            }
            for (EntryCust<RootBlock, ClassMethodIdReturn> e: _page.getRandCodes().entryList()) {
                String fullName_ = e.getKey().getFullName();
                _page.getRandCodeOwners().add(fullName_);
            }
            for (EntryCust<String, FileBlock> f: _page.getPreviousFilesBodies().entryList()) {
                FileBlock content_ = f.getValue();
                _page.getErrors().putFile(content_);
            }
            ReportedMessages messages_ = _page.getMessages();
            messages_.setErrors(FileBlock.errors(_page));
        }
    }
    public void toggleBreakPoint(StandardType _t, StandardNamedFunction _i) {
        getContext().toggleBreakPoint(_t, _i);
    }

    public void toggleBreakPointEnabled(StandardType _t, StandardNamedFunction _i) {
        getContext().toggleBreakPointEnabled(_t, _i);
    }
    public void toggleBreakPoint(String _file, int _offset) {
        AbsPairPoint p_ = tryGetPair(_file, _offset);
        getContext().toggleBreakPoint(p_);
    }
    public AbsPairPoint tryGetPair(String _file, int _offset) {
        FileBlock fb_ = getPageEl().getPreviousFilesBodies().getVal(_file);
        int o_ = ResultExpressionOperationNode.beginPart(_offset, fb_);
        if (o_ < 0) {
            return null;
        }
        MemberCallingsBlock id_ = ResultExpressionOperationNode.keyMethodBp(_offset, fb_);
        if (id_ != null) {
            BracedBlock r_ = BreakPointBlockList.rootOfAnnot(id_);
            if (r_ instanceof RootBlock) {
                return watch(false, build(((NamedCalledFunctionBlock)id_),(RootBlock)r_));
            }
            return method(getPageEl().getDisplayedStrings(), id_);
        }
        ExecFileBlock f_ = getFiles().getVal(fb_);
        return bp(f_, FileBlock.number(fb_), o_, ResultExpressionOperationNode.enabledTypeBp(_offset, fb_));
    }
    public static SynthFieldInfo build(NamedCalledFunctionBlock _id,RootBlock _r) {
        return new SynthFieldInfo(new ClassField("",_id.getName()),_r);
    }

    public void toggleWatch(boolean _trField, SynthFieldInfo _field) {
        getContext().toggleWatch(_trField, _field);
    }
    public OperNatPointBlockPair toggleOperNatPoint(String _symbol,String _first, String _second) {
        OperNatPointBlockPair o_ = resolve(_symbol, _first, _second);
        if (o_ == null) {
            return getContext().operNatDisabled();
        }
        return getContext().toggleOperNat(o_);
    }
    public OperNatPointBlockPair toggleEnableOperNatPoint(String _symbol,String _first, String _second) {
        OperNatPointBlockPair o_ = resolve(_symbol, _first, _second);
        if (o_ == null) {
            return getContext().operNatDisabled();
        }
        return getContext().toggleEnableOperNat(o_);
    }
    public OperNatPointBlockPair resolve(String _symbol,String _first, String _second) {
        if (koType(_first)) {
            return null;
        }
        ResultOperand res_;
        if (_second.trim().isEmpty()) {
            res_ = SymbolFactoryUtil.generateOperand(_symbol,new AnaClassArgumentMatching(_first),getPageEl());
        } else {
            if (koType(_second)) {
                return null;
            }
            res_ = SymbolFactoryUtil.generateOperand(_symbol,new AnaClassArgumentMatching(_first),new AnaClassArgumentMatching(_second),getPageEl());
        }
        String k_;
        if (res_.isDefConcat()) {
            k_ = "+";
        } else {
            k_ = res_.getSgn();
        }
        if (k_.isEmpty()) {
            return null;
        }
        OperNatPointBlockPair o_;
        if (_second.trim().isEmpty()) {
            o_ = operNat(k_, _symbol,res_.getFirst(), "");
        } else {
            o_ = operNat(k_, _symbol,res_.getFirst(), res_.getSecond());
        }
        if (!_second.trim().isEmpty()) {
            o_.getValue().setEnabledAffect(StringExpUtil.isLogical(_symbol) || StringExpUtil.isBinNum(_symbol) || StringExpUtil.isBitwise(_symbol) || StringExpUtil.isShiftOper(_symbol) || res_.getSymbol() instanceof CommonOperNullSafe);
        }
        return o_;
    }
    private boolean koType(String _second) {
        StandardType secondMain_ = getPageEl().getStandardsTypes().getVal(_second);
        PrimitiveType secondPr_ = getPageEl().getPrimitiveTypes().getVal(_second);
        return secondMain_ == null && secondPr_ == null;
    }
    public OperNatPointBlockPair operNat(String _k, String _symbol,String _f, String _s) {
        return getContext().operNat(_k, _symbol, _f, _s);
    }

    public MethodPointBlockPair method(DisplayedStrings _d, MemberCallingsBlock _id) {
        return getContext().method(_d, _id);
    }

    public WatchPointBlockPair watch(boolean _trField, SynthFieldInfo _field) {
        return getContext().watch(_trField, _field);
    }
    public BreakPointBlockPair bp(ExecFileBlock _file, int _nf, int _offset, boolean _enType) {
        return getContext().bp(_file, _nf, _offset, _enType);
    }

    public AbsCollection<BreakPointBlockPair> bpList() {
        return getContext().bpList();
    }
    public void toggleBreakPointEnabled(String _file, int _offset) {
        FileBlock fb_ = getPageEl().getPreviousFilesBodies().getVal(_file);
        int o_ = ResultExpressionOperationNode.beginPart(_offset, fb_);
        if (o_ < 0) {
            return;
        }
        MemberCallingsBlock id_ = ResultExpressionOperationNode.keyMethodBp(_offset, fb_);
        if (id_ != null) {
            BracedBlock r_ = BreakPointBlockList.rootOfAnnot(id_);
            if (r_ instanceof RootBlock) {
                toggleEnabledWatch(false,build(((NamedCalledFunctionBlock)id_),(RootBlock)r_));
                return;
            }
            toggleEnabled(getPageEl().getDisplayedStrings(),id_);
            return;
        }
        ExecFileBlock f_ = getFiles().getVal(fb_);
        toggleEnabled(f_,FileBlock.number(fb_),o_, ResultExpressionOperationNode.enabledTypeBp(_offset, fb_));
    }

    public void toggleEnabled(ExecFileBlock _file, int _nf, int _offset, boolean _enType) {
        getContext().toggleEnabled(_file, _nf, _offset, _enType);
    }
    public void toggleArrPoint(String _clName, boolean _exact) {
        getContext().toggleArrPoint(_clName, _exact);
    }

    public void toggleArrPointEnabled(String _clName, boolean _exact) {
        getContext().toggleArrPointEnabled(_clName, _exact);
    }
    public void toggleExcPoint(String _clName, boolean _exact) {
        getContext().toggleExcPoint(_clName, _exact);
    }

    public void toggleExcPointEnabled(String _clName, boolean _exact) {
        getContext().toggleExcPointEnabled(_clName, _exact);
    }
    public void toggleParPoint(String _clName, boolean _exact) {
        ParPointBlockPair p_ = tryBuild(_clName, _exact);
        if (p_ == null) {
            return;
        }
        getContext().togglePar(p_);
    }

    public void toggleParPointEnabled(String _clName, boolean _exact) {
        ParPointBlockPair p_ = tryBuild(_clName, _exact);
        if (p_ == null) {
            return;
        }
        getContext().toggleEnabledPar(p_);
    }
    public ParPointBlockPair tryBuild(String _clName, boolean _exact) {
        String solved_ = ExecPartTypeUtil.correctClassPartsDynamic(_clName, getContext());
        RootBlock r_ = getPageEl().getAnaClassBody(StringExpUtil.getIdFromAllTypes(solved_));
        if (koPar(r_, _clName)) {
            return null;
        }
        return getContext().notNullBuildPar(_exact,solved_,r_);
    }

    private static boolean koPar(RootBlock _solved, String _clName) {
        return _solved == null && !_clName.trim().isEmpty();
    }
    public void toggleWatchPoint(String _file, int _offset){
        SynthFieldInfo o_ = ResultExpressionOperationNode.vexerChamps(getPageEl(), _file, _offset);
        if (o_.getRootBlock() == null) {
            return;
        }
        toggleWatch(true,o_);
    }
    public void toggleWatchPointEnabled(String _file, int _offset){
        SynthFieldInfo o_ = ResultExpressionOperationNode.vexerChamps(getPageEl(), _file, _offset);
        if (o_.getRootBlock() == null) {
            return;
        }
        toggleEnabledWatch(true,o_);
    }

    public void toggleEnabled(DisplayedStrings _d, MemberCallingsBlock _id) {
        getContext().toggleEnabled(_d, _id);
    }
    public void toggleEnabledWatch(boolean _trField,SynthFieldInfo _field) {
        getContext().toggleEnabledWatch(_trField, _field);
    }
    public IdMap<FileBlock, ExecFileBlock> getFiles() {
        return getContext().getFiles();
    }

    public BreakPointBlockList getBreakPointsBlock() {
        return getContext().getBreakPointsBlock();
    }
    public boolean isWatch(boolean _trField,int _root, String _field) {
        return getContext().isWatch(_trField, _root, _field);
    }
    public WatchPointBlockPair getPairWatch(boolean _trField, int _root, String _field) {
        return getContext().getPairWatch(_trField, _root, _field);
    }
    public boolean is(ExecFileBlock _file, int _offset) {
        return getContext().is(_file, _offset);
    }
    public BreakPointBlockPair getPair(ExecFileBlock _file, int _offset) {
        return getContext().getPair(_file, _offset);
    }
    public boolean is(String _id) {
        return getContext().is(_id);
    }

    public boolean is(StandardNamedFunction _id) {
        return getContext().is(_id);
    }
    public StdMethodPointBlockPair getPair(StandardNamedFunction _id) {
        return getContext().getPair(_id);
    }
    public MethodPointBlockPair getPair(String _id) {
        return getContext().getPair(_id);
    }
    public boolean isArr(String _field, boolean _exact) {
        return getContext().isArr(_field, _exact);
    }
    public ArrPointBlockPair getPairArr(String _field, boolean _exact) {
        return getContext().getPairArr(_field, _exact);
    }
    public boolean isExc(String _field, boolean _exact) {
        return getContext().isExc(_field, _exact);
    }
    public ExcPointBlockPair getPairExc(String _field, boolean _exact) {
        return getContext().getPairExc(_field, _exact);
    }
    public boolean isPar(String _field, boolean _exact) {
        return getContext().isPar(_field, _exact);
    }
    public ParPointBlockPair getPairPar(String _field, boolean _exact) {
        return getContext().getPairPar(_field, _exact);
    }
    public void breakPointEnabled(String _file, int _offset, boolean _newValue) {
        BreakPointBlockList.breakPointEnabled(_file, _offset, this,_newValue);
    }
    public void breakPointInstanceType(String _file, int _offset, boolean _newValue) {
        BreakPointBlockList.breakPointInstanceType(_file, _offset, this, _newValue);
    }
    public void breakPointStaticType(String _file, int _offset, boolean _newValue) {
        BreakPointBlockList.breakPointStaticType(_file, _offset, this, _newValue);
    }

    public CustList<BreakPointBlockPair> bp(ExecFileBlock _file, FileMetrics _ana, int _off) {
        int r_ = _ana.getRowFile(_off);
        CustList<BreakPointBlockPair> list_ = new CustList<BreakPointBlockPair>();
        for (BreakPointBlockPair b: bpList().elts()) {
            if (b.getBp().matchRow(_file, _ana, r_)) {
                list_.add(b);
            }
        }
        return list_;
    }
    public void setContext(ContextEl _c) {
        this.context = _c;
    }

    public AnalyzedPageEl getPageEl() {
        return pageEl;
    }

    public Forwards getForwards() {
        return forwards;
    }

    public ContextEl getContext() {
        return context;
    }

    public ReportedMessages getReportedMessages() {
        return reportedMessages;
    }
}
