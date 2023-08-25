package code.expressionlanguage.adv;

import code.expressionlanguage.stds.StandardNamedFunction;
import code.gui.*;

public final class SelectedStdFctTreeEvent implements AbsShortListTree {

    private final AbsDebuggerGui window;
    private final FrameStdMpForm frameStdMpForm;
    private final MutableTreeNodeNav root;
    private final FramePoints framePoints;

    public SelectedStdFctTreeEvent(AbsDebuggerGui _w, MutableTreeNodeNav _r, FrameStdMpForm _fr, FramePoints _p) {
        this.window = _w;
        this.frameStdMpForm = _fr;
        this.root = _r;
        this.framePoints = _p;
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _node) {
        AbstractMutableTreeNodeCore e_ = MutableTreeNodeUtil.simular(root, (AbstractMutableTreeNode) _node);
        if (e_ instanceof MetaStdFunction) {
            StandardNamedFunction fct_ = ((MetaStdFunction) e_).getFunction();
            OkStdMpFormEvent.act(frameStdMpForm, ((MetaStdFunction) e_).getPar(), fct_, window, framePoints);
        } else if (e_ instanceof MetaStdType) {
            OkStdMpFormEvent.act(frameStdMpForm, ((MetaStdType) e_).getStandardType(), null, window, framePoints);
        } else {
            OkStdMpFormEvent.act(frameStdMpForm, null, null, window, framePoints);
        }
    }

}
