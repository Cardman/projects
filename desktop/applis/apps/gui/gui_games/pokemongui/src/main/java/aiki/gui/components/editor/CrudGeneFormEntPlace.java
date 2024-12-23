package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.places.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEntPlace extends CrudGeneFormListSub<Place> implements AbsCrudGeneFormTrCstOpen {

    public CrudGeneFormEntPlace(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr, null);
    }

    @Override
    public void initFormAll() {
        initForm();
        getCrudGeneFormSubContent().clearSub();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        GeneComponentModel<Place> key_ = new GeneComponentModelPlace(this,getFactory(),facadeGame_,getCrudGeneFormSubContent().getSubscription(), getFrame());
        initForm(new DisplayEntryCustPlace(), key_);
        setupValues(facadeGame_.getData().getMap().getPlaces());
    }

    @Override
    protected void afterModif(int _index, Place _value) {
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        if (_index > -1) {
            if (facadeGame_.getData().getMap().deletePlace(_index) == null) {
                return;
            }
            getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(null);
            getList().remove(_index);
            getCrudGeneFormSubContent().getSubscription().getRemovingPlacePhase().setPlace(_index);
            getCrudGeneFormSubContent().getSubscription().update();
            getCrudGeneFormSubContent().removeOpenSub();
            afterModif();
            return;
        }
        if (getSelectedIndex() < 0) {
            getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(null);
            facadeGame_.getData().getMap().addPlace(_value);
            getCrudGeneFormSubContent().removeOpenSub();
            afterModif();
            return;
        }
        getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(null);
        facadeGame_.getData().getMap().getPlaces().set(getSelectedIndex(), _value);
        getCrudGeneFormSubContent().removeOpenSub();
        afterModif();
    }

    @Override
    public void cancel() {
        getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(null);
        getCrudGeneFormSubContent().removeOpenSub();
        cancelBase();
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        return new IdList<SubscribedTranslation>();
    }
}
