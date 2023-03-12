package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.AbsMenuItem;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;

public final class ChangeAliasesEvent implements AbsActionListener {
    private final WindowWithTreeImpl windowCdmEditor;
    private final AbsMenuItem aliasesMenu;

    public ChangeAliasesEvent(WindowWithTreeImpl _w, AbsMenuItem _m) {
        this.windowCdmEditor = _w;
        aliasesMenu = _m;
    }

    @Override
    public void action() {
        if (windowCdmEditor.getAliasesFrames().isEmpty()) {
            AbsCommonFrame fr_ = windowCdmEditor.getCommonFrame();
            AbstractProgramInfos frs_ = fr_.getFrames();
            AbsCommonFrame frame_ = frs_.getFrameFactory().newCommonFrame(fr_.getLanguageKey(), frs_, null);
            frame_.addWindowListener(new CloseFrame(frame_,aliasesMenu));
            OutputDialogAliases w_ = new OutputDialogAliases(windowCdmEditor, frame_, aliasesMenu);
            windowCdmEditor.getAliasesFrames().add(w_);
        } else {
            windowCdmEditor.getAliasesFrames().last().reinit(windowCdmEditor);
        }
    }

}
