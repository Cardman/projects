package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationRenamingIdCrud<V> extends AbsSubscribedTranslationRenamingId {
    private final CrudGeneFormSimpleElementSub<EditedCrudPair<String, V>> law;

    public SubscribedTranslationRenamingIdCrud(CrudGeneFormSimpleElementSub<EditedCrudPair<String, V>> _t) {
        this.law = _t;
    }

    @Override
    public void expression(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        CustList<EditedCrudPair<String, V>> ls_ = law.getList();
        int len_ = ls_.size();
        for (int i = 0; i < len_; i++) {
            ls_.set(i, new EditedCrudPair<String, V>(_facade.getData().rename(ls_.get(i).getKey(),_phase.getMids(),_phase.getOldId(),_phase.getNewId()),ls_.get(i).getValue()));
        }
    }
}
