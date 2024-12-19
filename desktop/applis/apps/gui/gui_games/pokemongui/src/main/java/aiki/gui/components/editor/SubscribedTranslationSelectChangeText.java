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
    public void update(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _current) {
        RenamingImgNamePhase i_ = _current.getRenamingImgNamePhase();
        if (!StringUtil.quickEq(i_.getOldId(), i_.getNewId())) {
            if (StringUtil.quickEq(input.tryRet(), i_.getOldId())) {
                input.updateVal(i_.getNewId());
            } else {
                input.reset();
            }
        } else {
            input.reset();
        }
        input.getSelect().events(null);
    }
}
