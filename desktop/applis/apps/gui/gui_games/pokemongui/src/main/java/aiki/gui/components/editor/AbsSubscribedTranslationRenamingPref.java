package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.core.*;

public abstract class AbsSubscribedTranslationRenamingPref implements SubscribedTranslation {

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        if (StringUtil.quickEq(_phase.getOldPref(), _phase.getNewPref())) {
            return;
        }
        expression(_api,_facade,_phase);
    }

    protected abstract void expression(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase);
}
