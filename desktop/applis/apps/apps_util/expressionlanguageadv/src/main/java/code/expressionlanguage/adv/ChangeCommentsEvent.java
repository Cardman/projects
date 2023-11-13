package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.EnabledMenu;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;

public final class ChangeCommentsEvent implements AbsActionListener {
    private final WindowWithTreeImpl windowCdmEditor;
    private final EnabledMenu commentsMenu;
    public ChangeCommentsEvent(WindowWithTreeImpl _w, EnabledMenu _c) {
        this.windowCdmEditor = _w;
        commentsMenu = _c;
    }

    @Override
    public void action() {
        if (windowCdmEditor.getCommentsFrames().isEmpty()) {
            AbsCommonFrame fr_ = windowCdmEditor.getCommonFrame();
            AbstractProgramInfos frs_ = fr_.getFrames();
            AbsCommonFrame frame_ = frs_.getFrameFactory().newCommonFrame(fr_.getLanguageKey(), frs_, null);
            frame_.addWindowListener(new CloseFrame(frame_,commentsMenu));
            OutputDialogComments w_ = new OutputDialogComments(windowCdmEditor, frame_, commentsMenu);
            windowCdmEditor.getCommentsFrames().add(w_);
        } else {
            windowCdmEditor.getCommentsFrames().last().reinit(windowCdmEditor);
        }
    }

}
