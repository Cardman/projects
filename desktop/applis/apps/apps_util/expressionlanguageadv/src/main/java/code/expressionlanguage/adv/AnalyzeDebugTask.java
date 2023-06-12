package code.expressionlanguage.adv;

import code.expressionlanguage.AdvContextGenerator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilimpl.CustContextFactory;
import code.expressionlanguage.utilimpl.RunningTest;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class AnalyzeDebugTask implements Runnable {
    private final ResultContext base;
    private final AbsDebuggerGui gui;
    private final StringMap<String> src;

    public AnalyzeDebugTask(ResultContext _b,AbsDebuggerGui _g, StringMap<String> _s) {
        this.base = _b;
        this.gui = _g;
        this.src = _s;
    }

    @Override
    public void run() {
        if (base.getPageEl().notAllEmptyErrors()) {
            return;
        }
        AbstractProgramInfos fr_ = gui.getCommonFrame().getFrames();
        LgNamesGui lg_ = (LgNamesGui) base.getForwards().getGenerator();
        ExecutingOptions exec_ = lg_.getExecContent().getExecutingOptions();
        AnalyzedPageEl ana_ = RunningTest.nextValidateQuickAna(base, exec_, lg_.getExecContent().getInfos(), src);
        if (ana_ == null) {
            return;
        }
        Forwards forwards_ = CustContextFactory.fwd(base.getForwards().getOptions(), lg_, base.getForwards().getFileBuilder());
        ResultContext cust_ = new ResultContext(ana_, forwards_, ana_.getMessages());
        Forwards f_ = cust_.getForwards();
        AnalyzedPageEl page_ = cust_.getPageEl();
        f_.getClasses().getCommon().setStaticFields(page_.getStaticFields());
        ForwardInfos.generalForward(page_,f_);
        ContextEl ctx_ = new AdvContextGenerator(fr_.getThreadFactory().newAtomicBoolean()).gene(f_);
        Classes.forwardAndClear(ctx_);
        cust_.setContext(ctx_);
        gui.update(cust_);
    }
}
