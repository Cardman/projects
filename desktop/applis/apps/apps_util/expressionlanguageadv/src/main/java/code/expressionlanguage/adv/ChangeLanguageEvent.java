package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.EnabledMenu;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;

public final class ChangeLanguageEvent implements AbsActionListener {
    private final WindowWithTreeImpl windowCdmEditor;
    private final EnabledMenu languageMenu;

    public ChangeLanguageEvent(WindowWithTreeImpl _w, EnabledMenu _m) {
        this.windowCdmEditor = _w;
        languageMenu = _m;
    }

    @Override
    public void action() {
        if (windowCdmEditor.getLanguageFrames().isEmpty()) {
            AbsCommonFrame fr_ = windowCdmEditor.getCommonFrame();
            AbstractProgramInfos frs_ = fr_.getFrames();
            AbsCommonFrame frame_ = frs_.getFrameFactory().newCommonFrame(fr_.getLanguageKey(), frs_, null);
            frame_.addWindowListener(new CloseFrame(frame_,languageMenu));
            OutputDialogLanguage w_ = new OutputDialogLanguage(windowCdmEditor, frame_, languageMenu);
            windowCdmEditor.getLanguageFrames().add(w_);
        } else {
            windowCdmEditor.getLanguageFrames().last().reinit(windowCdmEditor);
        }
    }

}
