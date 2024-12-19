package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class SubscribedTranslationRemovePlace implements SubscribedTranslation {
    private final AbsSpinner input;

    public SubscribedTranslationRemovePlace(AbsSpinner _c) {
        this.input = _c;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _current) {
        RemovingPlacePhase r_ = _current.getRemovingPlacePhase();
        if (input.getValue() > r_.getPlace()) {
            input.setValue(input.getValue()-1L);
        }
    }
}
