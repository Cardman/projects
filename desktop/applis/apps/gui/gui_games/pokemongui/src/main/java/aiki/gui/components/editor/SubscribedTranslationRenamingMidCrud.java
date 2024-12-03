package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationRenamingMidCrud<V> extends AbsSubscribedTranslationRenamingMid {
    private final CrudGeneFormSimpleElementSub<EditedCrudPair<String, V>> law;

    public SubscribedTranslationRenamingMidCrud(CrudGeneFormSimpleElementSub<EditedCrudPair<String, V>> _t) {
        this.law = _t;
    }

    @Override
    public void expression(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        CustList<EditedCrudPair<String, V>> ls_ = law.getList();
        int len_ = ls_.size();
        for (int i = 0; i < len_; i++) {
            ls_.set(i, new EditedCrudPair<String, V>(_facade.getData().renameMid(ls_.get(i).getKey(),_phase.getOldMid(),_phase.getNewMid()),ls_.get(i).getValue()));
        }
    }
}
