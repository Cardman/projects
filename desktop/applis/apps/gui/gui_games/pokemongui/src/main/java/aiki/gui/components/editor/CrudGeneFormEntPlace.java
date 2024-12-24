package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEntPlace extends AbsCrudGeneForm implements AbsCrudGeneFormTrCstOpen {
    private final CrudGeneFormSubContent crudGeneFormSubContent;
    private GeneComponentModelPlace gene;
    private ContentComponentModelRoad road;
    private int selectedPlace = -1;
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
            road.getLevelWithWild().getAreas().setupValues(ConverterCommonMapUtil.copyListArea(((Road)place_).getLevelRoad().getWildPokemonAreas()));
            Points<Block> blocks_ = ConverterCommonMapUtil.copyPointsBlock(((Road) place_).getLevelRoad().getBlocks());
            road.getLevelWithWild().setupGridDims(blocks_, (short) _i,(byte) 0,place_,((Road) place_).getLevelRoad());
            road.getLevelWithWild().getAreas().setBlocks(blocks_);
            getElement().add(road.getLevelWithWild().getSplitter());
            getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(road.getLevelWithWild().getLevel());
        }
        selectOrAdd();
        enable(false);
    }
    @Override
    public void refresh() {
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        CustList<Place> places_ = facadeGame_.getData().getMap().getPlaces();
        getElements().removeAll();
        getAllButtons().clear();
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
            if (place_ instanceof Cave) {
                CrudGeneFormLevelCave c_ = new CrudGeneFormLevelCave(getFactory(), getCrudGeneFormSubContent().getFacadeGame(), getCrudGeneFormSubContent().getSubscription(), getFrame());
                c_.initForm();
                c_.setCave((Cave)place_);
                c_.refLevels();
                c_.setSelectedPlace(i);
                c_.setEnabledButtons(isEnabledButtons());
                levelsCaves.add(c_);
                levels.add(c_);
                getElements().add(c_.getGroup());
                getAllButtons().addAllElts(c_.getAllButtons());
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
                ((Road)value_).getLevelRoad().setBlocks(road.getLevelWithWild().getLevel().getEdited());
                ((Road)value_).getLevelRoad().setWildPokemonAreas(road.getLevelWithWild().getAreas().getList());
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
    private void enable(boolean _e) {
        for (AbsButton b: getAllButtons()) {
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

    public CustList<CrudGeneFormLevelCave> getLevelsCaves() {
        return levelsCaves;
    }

    public ContentComponentModelRoad getRoad() {
        return road;
    }
}
