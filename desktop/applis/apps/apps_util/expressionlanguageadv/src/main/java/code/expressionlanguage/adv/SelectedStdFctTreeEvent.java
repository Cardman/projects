package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.gui.*;

public final class SelectedStdFctTreeEvent implements AbsShortListTree {

    private final AbsDebuggerGui window;
    private final FrameStdMpForm frameStdMpForm;
    private final MutableTreeNodeNav<AbsMetaStdType> root;
    private final FramePoints framePoints;
    private final ResultContext currentResult;

    public SelectedStdFctTreeEvent(AbsDebuggerGui _w, MutableTreeNodeNav<AbsMetaStdType> _r, FrameStdMpForm _fr, FramePoints _p, ResultContext _cur) {
        this.window = _w;
        this.frameStdMpForm = _fr;
        this.root = _r;
        this.framePoints = _p;
        currentResult = _cur;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore<String> _node) {
        AbstractMutableTreeNodeCore<AbsMetaStdType> e_ = root.simular(_node);
        if (e_ != null && e_.info() instanceof MetaStdFunction) {
            StandardNamedFunction fct_ = ((MetaStdFunction) e_.info()).getFunction();
            OkStdMpFormEvent.act(frameStdMpForm, e_.info().getStandardType(), fct_, window, framePoints, currentResult);
        } else if (e_ != null && e_.info() instanceof MetaStdType) {
            OkStdMpFormEvent.act(frameStdMpForm, e_.info().getStandardType(), null, window, framePoints, currentResult);
        } else {
            OkStdMpFormEvent.act(frameStdMpForm, null, null, window, framePoints, currentResult);
        }
    }

    public ResultContext getCurrentResult() {
        return currentResult;
    }
}
