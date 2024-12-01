package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class SubscribedTranslationRenamingId extends AbsSubscribedTranslationRenamingId {
    private final AbsTextPane textPane;

    public SubscribedTranslationRenamingId(AbsTextPane _t) {
        this.textPane = _t;
    }

    @Override
    public void expression(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        textPane.setText(_facade.getData().rename(textPane.getText(),_phase.getMids(),_phase.getOldId(),_phase.getNewId()));
    }
}
