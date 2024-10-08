package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.EnabledMenu;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;

public final class ChangeSrcEvent implements AbsActionListener {
    private final WindowWithTreeImpl windowCdmEditor;
    private final EnabledMenu srcMenu;
    public ChangeSrcEvent(WindowWithTreeImpl _w, EnabledMenu _m) {
        this.windowCdmEditor = _w;
        srcMenu = _m;
    }

    @Override
    public void action() {
        if (windowCdmEditor.getSrcFrames().isEmpty()) {
            AbstractProgramInfos frs_ = windowCdmEditor.getFrames();
            AbsCommonFrame frame_ = frs_.getFrameFactory().newCommonFrame();
            frame_.addWindowListener(new CloseFrame(frame_, srcMenu));
            OutputDialogSrc w_ = new OutputDialogSrc(windowCdmEditor, frame_, srcMenu);
            windowCdmEditor.getSrcFrames().add(w_);
        } else {
            windowCdmEditor.getSrcFrames().last().reinit(windowCdmEditor);
        }
    }

}
