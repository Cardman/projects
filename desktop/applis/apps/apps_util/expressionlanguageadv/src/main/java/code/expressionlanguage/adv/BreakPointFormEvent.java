package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.options.ResultContext;
import code.gui.events.AbsActionListener;

public final class BreakPointFormEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ReadOnlyTabEditor tabEditor;
    private ResultContext result;

    public BreakPointFormEvent(AbsDebuggerGui _dbg,ReadOnlyTabEditor _t) {
        window = _dbg;
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        ResultContext r_ = getResult();
        if (r_ == null) {
            return;
        }
        FileBlock af_ = r_.getPageEl().getPreviousFilesBodies().getVal(tabEditor.getFullPath());
        ExecFileBlock f_ = r_.getForwards().dbg().getFiles().getVal(af_);
        int o_ = ResultExpressionOperationNode.beginPart(tabEditor.getCenter().getCaretPosition(), r_.getPageEl().getPreviousFilesBodies().getVal(tabEditor.getFullPath()));
        BreakPointBlockPair bp_ = r_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().getPair(f_, o_);
        if (bp_ == null) {
            return;
        }
        window.setSelectedPb(bp_);
        window.getEnabledBp().setSelected(bp_.getValue().isEnabled());
        window.getInstanceType().setEnabled(bp_.getValue().isEnabledChgtType());
        window.getInstanceType().setSelected(bp_.getValue().isInstanceType());
        window.getStaticType().setEnabled(bp_.getValue().isEnabledChgtType());
        window.getStaticType().setSelected(bp_.getValue().isStaticType());
        window.getBpForm().setVisible(true);
    }

    public ResultContext getResult() {
        return result;
    }

    public void setResult(ResultContext _r) {
        this.result = _r;
    }
}
