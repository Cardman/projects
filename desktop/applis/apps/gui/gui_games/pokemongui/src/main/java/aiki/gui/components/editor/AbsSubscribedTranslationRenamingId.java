package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.core.*;

public abstract class AbsSubscribedTranslationRenamingId implements SubscribedTranslation {

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _current) {
        RenamingIdPhase c_ = _current.getRenamingIdPhase();
        if (!StringUtil.quickEq(c_.getNewId(), c_.getOldId())) {
            suffix(_api, _facade, c_);
        }
        if (!StringUtil.quickEq(c_.getNewMid(), c_.getOldMid())) {
            middle(_api, _facade, c_);
        }
        if (!StringUtil.quickEq(c_.getNewPref(), c_.getOldPref())) {
            prefix(_api, _facade, c_);
        }
    }

    protected abstract void prefix(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase);

    protected abstract void middle(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase);

    protected abstract void suffix(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase);
}
