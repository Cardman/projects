package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.places.*;
import aiki.map.util.*;
import aiki.util.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEntPlace extends AbsCrudGeneForm implements AbsCrudGeneFormTrCstOpen {
    private final CrudGeneFormSubContent crudGeneFormSubContent;
    private final ContentComponentModelUniqLevelLinks joinPlaces = new ContentComponentModelUniqLevelLinks();
    private final ContentComponentModelPlaceCaveLinks links = new ContentComponentModelPlaceCaveLinks();
    private final ContentComponentModelCavePlaceLinks linksRev = new ContentComponentModelCavePlaceLinks();
    private final ContentComponentModelLeagueLinks leagueLink = new ContentComponentModelLeagueLinks();
    private final ContentComponentModelAccessCondition accessCondition = new ContentComponentModelAccessCondition(false);
    private final ContentComponentModelAccessCondition beginGame = new ContentComponentModelAccessCondition(true);
    private GeneComponentModelPlace gene;
    private ContentComponentModelRoad road;
    private ContentComponentModelCity city;
    private int selectedPlace = -1;
    private final CustList<AbsButton> allButtonsMerge = new CustList<AbsButton>();
    private final CustList<AbsButton> allButtons = new CustList<AbsButton>();
    private final CustList<AbsButton> allHeaders = new CustList<AbsButton>();
    private final CustList<CrudGeneFormLevel> levels = new CustList<CrudGeneFormLevel>();
    private boolean enabledButtons;
    private AbsButton accessConditionButton;
    private AbsButton beginGameButton;
    private AbsButton joinPlacesButton;
    private AbsButton joinPlaceCaveButton;
    private AbsButton joinCavePlaceButton;

    public CrudGeneFormEntPlace(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact);
        setFrame(_fr);
        crudGeneFormSubContent = new CrudGeneFormSubContent(_facade, _sub, this, _fr);
    }

    @Override
    public void initFormAll() {
        initForm();
        setEnabledButtons(true);
        getCrudGeneFormSubContent().clearSub();
        gene = new GeneComponentModelPlace(getFactory(), getFrame());
        allHeaders.clear();
        accessConditionButton = getFactory().getCompoFactory().newPlainButton("?");
        eventAdd(accessConditionButton, new SelectCrudAccessConditionFormEvent(this, false));
        beginGameButton = getFactory().getCompoFactory().newPlainButton("******");
        eventAdd(beginGameButton, new SelectCrudAccessConditionFormEvent(this, true));
        joinPlacesButton = getFactory().getCompoFactory().newPlainButton("<->");
        eventAdd(joinPlacesButton, new SelectCrudJoinFormEvent(this));
        joinPlaceCaveButton = getFactory().getCompoFactory().newPlainButton("<->");
        eventAdd(joinPlaceCaveButton, new SelectCrudPlaceCaveFormEvent(this));
        joinCavePlaceButton = getFactory().getCompoFactory().newPlainButton("<->");
        eventAdd(joinCavePlaceButton, new SelectCrudCavePlaceFormEvent(this));
    }

    private void eventAdd(AbsButton _but, AbsActionListener _evt) {
        _but.addActionListener(_evt);
        _but.setEnabled(isEnabledButtons());
        getButtons().add(_but);
        getAllButtons().add(_but);
        getAllButtonsMerge().add(_but);
        allHeaders.add(_but);
    }

    public void displayAccessCondition(boolean _begin) {
        getElement().removeAll();
        if (_begin) {
            getElement().add(beginGame.form(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame(),this));
        } else {
            getElement().add(accessCondition.form(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame(),this));
        }
        CrudGeneFormLevelCave.disable(this);
        getFrame().pack();
    }
    public void displayJoinPlaces() {
        getElement().removeAll();
        getElement().add(joinPlaces.form(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame(),this));
        CrudGeneFormLevelCave.disable(this);
        getFrame().pack();
    }
    public void displayAllLinksPlaceCave() {
        getElement().removeAll();
        getElement().add(links.form(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame()));
        links.getClose().addActionListener(new CloseLinksFormEvent(this));
        CrudGeneFormLevelCave.disable(this);
        getFrame().pack();
    }
    public void displayAllLinksCavePlace() {
        getElement().removeAll();
        getElement().add(linksRev.form(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame()));
        linksRev.getClose().addActionListener(new CloseLinksFormEvent(this));
        CrudGeneFormLevelCave.disable(this);
        getFrame().pack();
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
            road.setupGridDims(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame(),AbsContentComponentModelLevelLinks.coords(_i, 0, null), place_,ConverterCommonMapUtil.copyLevelRoad(((Road) place_).getLevelRoad()));
            getElement().add(road.getLevelWithWild().getSplitter());
            getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(road.getLevel());
        }
        if (place_ instanceof City) {
            city = new ContentComponentModelCity();
            city.setupGridDims(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame(),AbsContentComponentModelLevelLinks.coords(_i, 0, null), (City)place_);
            getElement().add(city.getSplitter());
            getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(city.getLevel());
        }
        if (place_ instanceof League) {
            leagueLink.selectIndexes((League) place_, _i);
            getElement().add(leagueLink.form(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame(), this));
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
                levels.add(c_);
                getElements().add(c_.getGroup());
            }
            if (place_ instanceof League) {
                CrudGeneFormLevelLeague c_ = new CrudGeneFormLevelLeague(getFactory(), getCrudGeneFormSubContent().getFacadeGame(), getCrudGeneFormSubContent().getSubscription(), getFrame(), this);
                c_.initForm();
                c_.setCave((League)place_);
                getAllButtonsMerge().addAllElts(c_.refLevels());
                c_.setSelectedPlace(i);
                levels.add(c_);
                getElements().add(c_.getGroup());
            }
        }
        getAllButtonsMerge().addAllElts(allHeaders);
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
            CustList<EditedCrudPair<Coords, EditedCrudPair<InitializedPlace, PlaceInterConnects>>> ls_ = new CustList<EditedCrudPair<Coords, EditedCrudPair<InitializedPlace, PlaceInterConnects>>>();
            if (value_ instanceof Road) {
                road.getLevelWithWild().buildEntity(((Road)value_).getLevelRoad());
                ls_ = road.getLevelWithWild().getLevel().getLinks();
            }
            if (value_ instanceof City) {
                city.buildEntity();
                ls_ = city.getLevel().getLinks();
            }
            for (EditedCrudPair<Coords, EditedCrudPair<InitializedPlace, PlaceInterConnects>> e:ls_) {
                e.getValue().getKey().setSavedlinks(e.getValue().getValue());
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
    public void selectOrAdd() {
        setEnabledButtons(false);
        super.selectOrAdd();
    }

    @Override
    public void afterModif() {
        setEnabledButtons(true);
        super.afterModif();
    }

    @Override
    public void cancel() {
        setEnabledButtons(true);
        super.cancel();
        enable(true);
    }
    public void enable(boolean _e) {
        for (AbsButton b: getAllButtonsMerge()) {
            b.setEnabled(_e);
        }
    }

    public CustList<CrudGeneFormLevel> getLevels() {
        return levels;
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

    public ContentComponentModelRoad getRoad() {
        return road;
    }

    public ContentComponentModelCity getCity() {
        return city;
    }

    public boolean isEnabledButtons() {
        return enabledButtons;
    }

    public void setEnabledButtons(boolean _e) {
        this.enabledButtons = _e;
    }

    public ContentComponentModelUniqLevelLinks getJoinPlaces() {
        return joinPlaces;
    }

    public ContentComponentModelPlaceCaveLinks getLinks() {
        return links;
    }

    public ContentComponentModelCavePlaceLinks getLinksRev() {
        return linksRev;
    }

    public ContentComponentModelLeagueLinks getLeagueLink() {
        return leagueLink;
    }

    public ContentComponentModelAccessCondition getAccessCondition() {
        return accessCondition;
    }

    public AbsButton getAccessConditionButton() {
        return accessConditionButton;
    }

    public ContentComponentModelAccessCondition getBeginGame() {
        return beginGame;
    }

    public AbsButton getBeginGameButton() {
        return beginGameButton;
    }

    public AbsButton getJoinPlacesButton() {
        return joinPlacesButton;
    }

    public AbsButton getJoinPlaceCaveButton() {
        return joinPlaceCaveButton;
    }

    public AbsButton getJoinCavePlaceButton() {
        return joinCavePlaceButton;
    }
}
