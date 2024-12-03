package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationRenamingValCrud<K> extends AbsSubscribedTranslationRenamingId {
    private final CrudGeneFormListSub<EditedCrudPair<K, String>> law;

    public SubscribedTranslationRenamingValCrud(CrudGeneFormListSub<EditedCrudPair<K, String>> _t) {
        this.law = _t;
    }

    @Override
    protected void prefix(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        common(_facade,new PrefixRenamingDataBase(_phase.getOldPref(), _phase.getNewPref()));
    }

    @Override
    protected void middle(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        common(_facade,new MidRenamingDataBase(_phase.getOldMid(), _phase.getNewMid()));
    }

    @Override
    public void suffix(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        common(_facade, new IdRenamingDataBase(_phase.getMids(), _phase.getOldId(), _phase.getNewId()));
    }

    private void common(FacadeGame _facade, AbsRenamingDataBase _abs) {
        CustList<EditedCrudPair<K, String>> ls_ = law.getList();
        int len_ = ls_.size();
        for (int i = 0; i < len_; i++) {
            ls_.set(i, new EditedCrudPair<K, String>(ls_.get(i).getKey(), _abs.rename(_facade.getData(),ls_.get(i).getValue())));
        }
    }
}
