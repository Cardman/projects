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

    public static void disable(CrudGeneFormEntPlace _a) {
        _a.setEnabledButtons(false);
        for (CrudGeneFormLevel c: _a.getLevels()) {
            disableForm(c);
        }
        disableForm(_a);
        _a.enable(false);
    }

    public static void disableForm(AbsCrudGeneForm _a) {
        _a.getValidAddEdit().setEnabled(false);
        _a.getValidRemove().setEnabled(false);
        _a.getCancel().setEnabled(false);
        _a.getAdd().setEnabled(false);
    }

    public void selectLevel(int _i) {
        selectedLevel = _i;
        getElement().removeAll();
        getElement().add(gene());
        selectOrAdd();
        parent.enable(false);
    }
    @Override
    public void refresh() {
        getGenePair().retGeneWild().removeSubs();
        parent.refPlaces();
    }

    public CustList<AbsButton> refLevels() {
        CustList<Level> levels_ = getPlace().getLevelsList();
        getElements().removeAll();
        getAllButtons().clear();
        int len_ = levels_.size();
        CustList<AbsButton> buts_ = new CustList<AbsButton>();
        for (int i = 0; i < len_; i++) {
            AbsPanel line_ = getFactory().getCompoFactory().newLineBox();
            AbsButton but_ = getFactory().getCompoFactory().newPlainButton(Long.toString(i));
            but_.addActionListener(new SelectCrudLevelFormEvent(this, i));
            but_.setEnabled(parent.isEnabledButtons());
            line_.add(but_);
            getAllButtons().add(but_);
            buts_.add(but_);
            AbsButton link_ = getFactory().getCompoFactory().newPlainButton(MessagesPkEditor.getMessagesEditorSelectButtonsTr(MessagesPkEditor.getAppliTr(getFactory().currentLg())).getMapping().getVal(MessagesEditorSelect.LK_FORM));
            link_.addActionListener(new DisplayLinksCaveEvent(this,i));
            link_.setEnabled(parent.isEnabledButtons());
            line_.add(link_);
            getAllButtons().add(link_);
            buts_.add(link_);
            getElements().add(line_);
        }
        return buts_;
    }

    @Override
    public void formAdd() {
        addLevel();
        afterModif();
        refresh();
//        selectedLevel = -1;
//        getElement().removeAll();
//        getElement().add(gene(true));
//        selectOrAdd();
//        getValidRemove().setEnabled(false);
    }

    public abstract AbsCustComponent gene();

    @Override
    public void validAddEdit() {
        getCrudGeneFormSubContent().getSubscription().setFormLevelGridUniq(null);
//        if (selectedLevel < 0) {
//            addLevel();
//        } else {
//            setupLevel(selectedLevel);
//        }
        setupLevel(selectedLevel);
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
        parent.getAdd().setEnabled(true);
    }
    @Override
    public void cancel() {
        getGenePair().retGeneWild().removeSubs();
        parent.setEnabledButtons(true);
        super.cancel();
        parent.enable(true);
        parent.getAdd().setEnabled(true);
    }

    public void displayGrid(int _index) {
        setupPlace(_index);
        getElement().removeAll();
        getElement().add(getLinks().form(getFactory(),getCrudGeneFormSubContent().getFacadeGame(),getCrudGeneFormSubContent().getSubscription(),getFrame()));
        getLinks().getClose().addActionListener(new CloseLinksFormEvent(getParent(), getCrudGeneFormSubContent().getSubscription(), getLinks().getTranslationsGrid(), getFrame()));
        disable(getParent());
        getFrame().pack();
    }

    public CrudGeneFormEntPlace getParent() {
        return parent;
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

    public abstract void setupPlace(int _index);
    public abstract AbsSubLevelLinks getLinks();
    public abstract AbsGeneComponentModelSubscribeLevel getGenePair();
}
