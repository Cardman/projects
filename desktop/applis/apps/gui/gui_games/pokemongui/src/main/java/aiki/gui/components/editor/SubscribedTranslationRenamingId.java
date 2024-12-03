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
    protected void prefix(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        textPane.setText(_facade.getData().renamePref(textPane.getText(),_phase.getOldPref(),_phase.getNewPref()));
    }

    @Override
    protected void middle(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        textPane.setText(_facade.getData().renameMid(textPane.getText(),_phase.getOldMid(),_phase.getNewMid()));
    }

    @Override
    public void suffix(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        textPane.setText(_facade.getData().rename(textPane.getText(),_phase.getMids(),_phase.getOldId(),_phase.getNewId()));
    }
}
