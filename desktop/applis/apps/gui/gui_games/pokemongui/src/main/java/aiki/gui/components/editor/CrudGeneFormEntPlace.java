package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.places.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEntPlace extends AbsCrudGeneForm implements AbsCrudGeneFormTrCstOpen {
    private final CrudGeneFormSubContent crudGeneFormSubContent;
    private GeneComponentModelPlace gene;
    private ContentComponentModelRoad road;
    private int selectedPlace = -1;
    private final CustList<AbsButton> allButtonsMerge = new CustList<AbsButton>();
    private final CustList<AbsButton> allButtons = new CustList<AbsButton>();
    private final CustList<CrudGeneFormLevelCave> levelsCaves = new CustList<CrudGeneFormLevelCave>();
    private final CustList<CrudGeneFormLevel> levels = new CustList<CrudGeneFormLevel>();

    public CrudGeneFormEntPlace(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact);
        setFrame(_fr);
        crudGeneFormSubContent = new CrudGeneFormSubContent(_facade, _sub, this, _fr);
    }

    @Override
    public void initFormAll() {
        initForm();
        getCrudGeneFormSubContent().clearSub();
        gene = new GeneComponentModelPlace(getFactory(), getFrame());
        refresh();
    }


    public void selectPlace(int _i) {
        selectedPlace = _i;
        getElement().removeAll();
        getElement().add(gene.gene(false));
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        CustList<Place> places_ = facadeGame_.getData().getMap().getPlaces();
        Place place_ = places_.get(_i);
        gene.setEdited(place_);
        getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(null);
        if (place_ instanceof Road) {
            road = new ContentComponentModelRoad();
            road.form(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame());
            road.setupGridDims((short) _i,(byte) 0,place_,((Road) place_).getLevelRoad());
            getElement().add(road.getLevelWithWild().getSplitter());
            getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(road.getLevel());
        }
        selectOrAdd();
        enable(false);
    }
    @Override
    public void refresh() {
        refPlaces();
    }

    public void refPlaces() {
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        CustList<Place> places_ = facadeGame_.getData().getMap().getPlaces();
        getElements().removeAll();
        getAllButtons().clear();
        getAllButtonsMerge().clear();
        int len_ = places_.size();
        levelsCaves.clear();
        levels.clear();
        for (int i = 0; i < len_; i++) {
            Place place_ = places_.get(i);
            AbsButton but_ = getFactory().getCompoFactory().newPlainButton(i+":"+ place_.getName());
            but_.addActionListener(new SelectCrudPlaceFormEvent(this, i));
            but_.setEnabled(isEnabledButtons());
            getElements().add(but_);
            getAllButtons().add(but_);
            getAllButtonsMerge().add(but_);
            if (place_ instanceof Cave) {
                CrudGeneFormLevelCave c_ = new CrudGeneFormLevelCave(getFactory(), getCrudGeneFormSubContent().getFacadeGame(), getCrudGeneFormSubContent().getSubscription(), getFrame(), this);
                c_.initForm();
                c_.setCave((Cave)place_);
                getAllButtonsMerge().addAllElts(c_.refLevels());
                c_.setSelectedPlace(i);
                c_.setEnabledButtons(isEnabledButtons());
                levelsCaves.add(c_);
                levels.add(c_);
                getElements().add(c_.getGroup());
            }
        }
    }

    @Override
    public void formAdd() {
        selectedPlace = -1;
        getElement().removeAll();
        getElement().add(gene.gene(true));
        selectOrAdd();
        getValidRemove().setEnabled(false);
    }

    @Override
    public void validAddEdit() {
        Place value_ = gene.value();
        getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(null);
        if (selectedPlace < 0) {
            getCrudGeneFormSubContent().getFacadeGame().getData().getMap().addPlace(value_);
        } else {
            if (value_ instanceof Road) {
                road.getLevelWithWild().buildEntity();
            }
            getCrudGeneFormSubContent().getFacadeGame().getData().getMap().getPlaces().set(selectedPlace, value_);
        }
        getCrudGeneFormSubContent().removeOpenSub();
        afterModif();
        refresh();
    }

    @Override
    public void validRemove() {
        if (getCrudGeneFormSubContent().getFacadeGame().getData().getMap().deletePlace(selectedPlace) == null) {
            return;
        }
        getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(null);
        getCrudGeneFormSubContent().getSubscription().getRemovingPlacePhase().setPlace(selectedPlace);
        getCrudGeneFormSubContent().getSubscription().update();
        getCrudGeneFormSubContent().removeOpenSub();
        afterModif();
        refresh();
        selectedPlace = -1;
    }
    @Override
    public void cancel() {
        super.cancel();
        enable(true);
    }
    public void enable(boolean _e) {
        for (AbsButton b: getAllButtonsMerge()) {
            b.setEnabled(_e);
        }
    }
    public CrudGeneFormSubContent getCrudGeneFormSubContent() {
        return crudGeneFormSubContent;
    }

    public GeneComponentModelPlace getGene() {
        return gene;
    }

    public CustList<AbsButton> getAllButtons() {
        return allButtons;
    }

    public CustList<AbsButton> getAllButtonsMerge() {
        return allButtonsMerge;
    }

    public CustList<CrudGeneFormLevelCave> getLevelsCaves() {
        return levelsCaves;
    }

    public ContentComponentModelRoad getRoad() {
        return road;
    }
}
