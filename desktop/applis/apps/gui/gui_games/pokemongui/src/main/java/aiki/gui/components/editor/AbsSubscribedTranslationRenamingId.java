package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.core.*;

public abstract class AbsSubscribedTranslationRenamingId implements SubscribedTranslation {

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase, RenamingImgNamePhase _renamingImgNamePhase) {
        if (!StringUtil.quickEq(_phase.getNewId(), _phase.getOldId())) {
            suffix(_api, _facade, _phase);
        }
        if (!StringUtil.quickEq(_phase.getNewMid(), _phase.getOldMid())) {
            middle(_api, _facade, _phase);
        }
        if (!StringUtil.quickEq(_phase.getNewPref(), _phase.getOldPref())) {
            prefix(_api, _facade, _phase);
        }
    }

    protected abstract void prefix(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase);

    protected abstract void middle(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase);

    protected abstract void suffix(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase);
}
