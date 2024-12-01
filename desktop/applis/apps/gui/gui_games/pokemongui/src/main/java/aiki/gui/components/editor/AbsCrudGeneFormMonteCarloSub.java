package aiki.gui.components.editor;

import code.gui.*;
import code.maths.*;
import code.util.*;

public abstract class AbsCrudGeneFormMonteCarloSub<E> {
    private DisplayEntryCustSubElement<EditedCrudPair<E, LgInt>> displayEntryCustSub;

    public void display(DisplayEntryCustSubElement<EditedCrudPair<E, LgInt>> _subsLoc) {
        displayEntryCustSub = _subsLoc;
    }

    public IdList<SubscribedTranslation> subscribeButtons() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(displayEntryCustSub.buildSub());
        ids_.add(geneLaw());
        return ids_;
    }
    protected abstract SubscribedTranslation geneLaw();

}
