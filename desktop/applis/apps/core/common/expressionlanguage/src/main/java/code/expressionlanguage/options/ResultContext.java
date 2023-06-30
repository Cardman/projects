package code.expressionlanguage.options;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.exec.AbsStackStopper;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.DefStackStopper;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.util.EntryCust;
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
        copy_.getFoundTypes().addAllElts(_base.getFoundTypes());
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
