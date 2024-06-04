package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.EnabledMenu;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;

public final class ChangeTabulationsEvent implements AbsActionListener {
    private final WindowWithTreeImpl windowCdmEditor;
    private final EnabledMenu tabulationsMenu;
    public ChangeTabulationsEvent(WindowWithTreeImpl _w, EnabledMenu _m) {
        this.windowCdmEditor = _w;
        tabulationsMenu = _m;
    }

    @Override
    public void action() {
        if (windowCdmEditor.getTabulationsFrames().isEmpty()) {
            AbsCommonFrame fr_ = windowCdmEditor.getCommonFrame();
            AbstractProgramInfos frs_ = fr_.getFrames();
            AbsCommonFrame frame_ = frs_.getFrameFactory().newCommonFrame(frs_, null);
            frame_.addWindowListener(new CloseFrame(frame_,tabulationsMenu));
            OutputDialogTab w_ = new OutputDialogTab(windowCdmEditor, frame_, tabulationsMenu);
            windowCdmEditor.getTabulationsFrames().add(w_);
        } else {
            windowCdmEditor.getTabulationsFrames().last().reinit(windowCdmEditor);
        }
    }

}
