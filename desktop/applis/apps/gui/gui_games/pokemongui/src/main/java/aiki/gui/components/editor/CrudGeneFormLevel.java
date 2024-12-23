package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class CrudGeneFormLevel extends AbsCrudGeneForm {

    private final CrudGeneFormSubContent crudGeneFormSubContent;
    private int selectedPlace = -1;
    private int selectedLevel = -1;
    private final CustList<AbsButton> allButtons = new CustList<AbsButton>();
    private final CrudGeneFormEntPlace parent;

    protected CrudGeneFormLevel(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, CrudGeneFormEntPlace _crud) {
        super(_fact);
        setFrame(_fr);
        crudGeneFormSubContent = new CrudGeneFormSubContent(_facade, _sub, this, _fr);
        parent = _crud;
    }

    public void selectLevel(int _i) {
        selectedLevel = _i;
        getElement().removeAll();
        getElement().add(gene(false));
        selectOrAdd();
        parent.enable(false);
    }
    @Override
    public void refresh() {
        parent.refPlaces();
    }

    public CustList<AbsButton> refLevels() {
        CustList<Level> levels_ = getPlace().getLevelsList();
        getElements().removeAll();
        getAllButtons().clear();
        int len_ = levels_.size();
        CustList<AbsButton> buts_ = new CustList<AbsButton>();
        for (int i = 0; i < len_; i++) {
            AbsButton but_ = getFactory().getCompoFactory().newPlainButton(Long.toString(i));
            but_.addActionListener(new SelectCrudLevelFormEvent(this, i));
            but_.setEnabled(parent.isEnabledButtons());
            getElements().add(but_);
            getAllButtons().add(but_);
            buts_.add(but_);
        }
        return buts_;
    }


    @Override
    public void formAdd() {
        selectedLevel = -1;
        getElement().removeAll();
        getElement().add(gene(true));
        selectOrAdd();
        getValidRemove().setEnabled(false);
    }

    public abstract AbsCustComponent gene(boolean _s);

    @Override
    public void validAddEdit() {
        getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(null);
        if (selectedLevel < 0) {
            addLevel();
        } else {
            setupLevel(selectedLevel);
        }
        getCrudGeneFormSubContent().removeOpenSub();
        afterModif();
        refresh();
    }

    public abstract void setupLevel(int _s);

    public abstract void addLevel();

    @Override
    public void validRemove() {
        if (getCrudGeneFormSubContent().getFacadeGame().getData().getMap().deleteLevelPlace(selectedPlace, selectedLevel) == null) {
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
        parent.setEnabledButtons(false);
        super.selectOrAdd();
    }

    @Override
    public void afterModif() {
        parent.setEnabledButtons(true);
        super.afterModif();
    }
    @Override
    public void cancel() {
        parent.setEnabledButtons(true);
        super.cancel();
        parent.enable(true);
    }

    public abstract Place getPlace();

    public int getSelectedPlace() {
        return selectedPlace;
    }

    public void setSelectedPlace(int _s) {
        this.selectedPlace = _s;
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }

    public CustList<AbsButton> getAllButtons() {
        return allButtons;
    }

    public CrudGeneFormSubContent getCrudGeneFormSubContent() {
        return crudGeneFormSubContent;
    }
}
