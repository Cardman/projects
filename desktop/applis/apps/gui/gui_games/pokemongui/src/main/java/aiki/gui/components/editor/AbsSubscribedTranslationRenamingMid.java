package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.core.StringUtil;

public abstract class AbsSubscribedTranslationRenamingMid implements SubscribedTranslation {

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        if (StringUtil.quickEq(_phase.getNewMid(), _phase.getOldMid())) {
            return;
        }
        expression(_api,_facade,_phase);
    }

    protected abstract void expression(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase);
}
