package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.AbsTextPane;
import code.gui.initialize.AbstractProgramInfos;

public final class SubscribedTranslationRenamingMid extends AbsSubscribedTranslationRenamingMid {
    private final AbsTextPane textPane;

    public SubscribedTranslationRenamingMid(AbsTextPane _t) {
        this.textPane = _t;
    }

    @Override
    public void expression(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        textPane.setText(_facade.getData().renameMid(textPane.getText(),_phase.getOldMid(),_phase.getNewMid()));
    }
}
