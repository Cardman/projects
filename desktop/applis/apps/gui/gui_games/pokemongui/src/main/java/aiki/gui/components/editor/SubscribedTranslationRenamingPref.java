package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class SubscribedTranslationRenamingPref extends AbsSubscribedTranslationRenamingPref {
    private final AbsTextPane textPane;

    public SubscribedTranslationRenamingPref(AbsTextPane _t) {
        this.textPane = _t;
    }

    @Override
    public void expression(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        textPane.setText(_facade.getData().renamePref(textPane.getText(), _phase.getOldPref(), _phase.getNewPref()));
    }
}
