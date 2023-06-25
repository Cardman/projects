package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.PackingWindowAfter;
import code.gui.events.AbsActionListener;

public final class BreakPointFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ReadOnlyTabEditor tabEditor;

    public BreakPointFormEvent(AbsDebuggerGui _dbg,ReadOnlyTabEditor _t) {
        window = _dbg;
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        ResultContext r_ = tabEditor.getDebuggerGui().getCurrentResult();
        FileBlock af_ = r_.getPageEl().getPreviousFilesBodies().getVal(tabEditor.getFullPath());
        ExecFileBlock f_ = r_.getForwards().dbg().getFiles().getVal(af_);
        int o_ = ResultExpressionOperationNode.beginPart(tabEditor.getCenter().getCaretPosition(), r_.getPageEl().getPreviousFilesBodies().getVal(tabEditor.getFullPath()));
        BreakPointBlockPair bp_ = r_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(f_, o_);
        if (bp_ == null) {
            return;
        }
        window.getFrameBpForm().setSelectedPb(bp_);
        window.getFrameBpForm().getEnabledBp().setSelected(bp_.getValue().isEnabled());
        window.getFrameBpForm().getConditionalStd().setVisible(!bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getConditionalStd().setText(bp_.getValue().getResultStd().getResultStr());
        window.getFrameBpForm().getCountStd().setVisible(!bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getCountStd().setValue(bp_.getValue().getResultStd().getCountModulo());
        window.getFrameBpForm().getInstanceType().setEnabled(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getInstanceType().setSelected(bp_.getValue().isInstanceType());
        window.getFrameBpForm().getStaticType().setEnabled(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getStaticType().setSelected(bp_.getValue().isStaticType());
        window.getFrameBpForm().getConditionalStatic().setVisible(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getConditionalStatic().setText(bp_.getValue().getResultStatic().getResultStr());
        window.getFrameBpForm().getCountStatic().setVisible(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getCountStatic().setValue(bp_.getValue().getResultStatic().getCountModulo());
        window.getFrameBpForm().getConditionalInstance().setVisible(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getConditionalInstance().setText(bp_.getValue().getResultInstance().getResultStr());
        window.getFrameBpForm().getCountInstance().setVisible(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getCountInstance().setValue(bp_.getValue().getResultInstance().getCountModulo());
        window.getFrameBpForm().getGuiStdStackForm().getStaScIncExc().setVisible(!bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getGuiInsStackForm().getStaScIncExc().setVisible(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getGuiStaStackForm().getStaScIncExc().setVisible(bp_.getValue().isEnabledChgtType());
        window.getFrameBpForm().getCommonFrame().setVisible(true);
        PackingWindowAfter.pack(window.getFrameBpForm().getCommonFrame());
    }

}
