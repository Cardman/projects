package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.core.*;

public final class SubscribedTranslationSelectChangeText implements SubscribedTranslation {
    private final GeneComponentModelElt<String> input;

    public SubscribedTranslationSelectChangeText(GeneComponentModelElt<String> _c) {
        this.input = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase, RenamingImgNamePhase _renamingImgNamePhase) {
        if (!StringUtil.quickEq(_renamingImgNamePhase.getOldId(), _renamingImgNamePhase.getNewId())) {
            if (StringUtil.quickEq(input.tryRet(), _renamingImgNamePhase.getOldId())) {
                input.updateVal(_renamingImgNamePhase.getNewId());
            }
        } else {
            input.reset();
        }
        input.getSelect().events(null);
    }
}
