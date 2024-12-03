package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationRenamingValMidCrud<K> extends AbsSubscribedTranslationRenamingMid {
    private final CrudGeneFormListSub<EditedCrudPair<K, String>> law;

    public SubscribedTranslationRenamingValMidCrud(CrudGeneFormListSub<EditedCrudPair<K, String>> _t) {
        this.law = _t;
    }

    @Override
    public void expression(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        CustList<EditedCrudPair<K, String>> ls_ = law.getList();
        int len_ = ls_.size();
        for (int i = 0; i < len_; i++) {
            ls_.set(i, new EditedCrudPair<K, String>(ls_.get(i).getKey(), _facade.getData().renameMid(ls_.get(i).getValue(),_phase.getOldMid(),_phase.getNewMid())));
        }
    }
}
