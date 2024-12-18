package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class FormDataMap {
    private final WindowPkEditor window;
    private final FacadeGame facadeGame;
    private final AbsSpinner screenWidth;
    private final AbsSpinner screenHeight;
    private final AbsSpinner spaceBetweenLeftAndHeros;
    private final AbsSpinner spaceBetweenTopAndHeros;
    private final AbsSpinner sideLength;
    private final AbsScrollPane wildPkContent;
    private final AbsScrollPane unlockedCityContent;
    private final AbsScrollPane placesContent;
    private final AbsButton applyMapModif;
    private final AbsPanel form;
    private final IdList<SubscribedTranslation> translations;
    private final FormWildPk formWildPk;
    private final GeneComponentModelImgSelect unlockedCity;
    private final CrudGeneFormEntPlace crudPlace;

    public FormDataMap(WindowPkEditor _ed, FacadeGame _facade, SubscribedTranslationList _subscriptions, IdList<SubscribedTranslation> _trs) {
        formWildPk = new FormWildPk(_ed, _facade, _subscriptions);
        translations = _trs;
        window = _ed;
        facadeGame = _facade;
        AbstractProgramInfos frames_ = getWindow().getFrames();
        screenWidth = frames_.getCompoFactory().newSpinner(1,1,Integer.MAX_VALUE, 1);
        screenHeight = frames_.getCompoFactory().newSpinner(1,1,Integer.MAX_VALUE, 1);
        spaceBetweenLeftAndHeros = frames_.getCompoFactory().newSpinner(1,1,Integer.MAX_VALUE, 1);
        spaceBetweenTopAndHeros = frames_.getCompoFactory().newSpinner(1,1,Integer.MAX_VALUE, 1);
        sideLength = frames_.getCompoFactory().newSpinner(1,1,Integer.MAX_VALUE, 1);
        applyMapModif = frames_.getCompoFactory().newPlainButton("_");
        applyMapModif.addActionListener(new ApplyModifDataMapDimsEvent(this));
        crudPlace = new CrudGeneFormEntPlace(_ed.getFrames(),_facade,_subscriptions,window.getCommonFrame());
        form = frames_.getCompoFactory().newPageBox();
        form.add(screenWidth);
        form.add(screenHeight);
        form.add(spaceBetweenLeftAndHeros);
        form.add(spaceBetweenTopAndHeros);
        form.add(sideLength);
        form.add(applyMapModif);
        wildPkContent = frames_.getCompoFactory().newAbsScrollPane();
        form.add(wildPkContent);
        unlockedCityContent = frames_.getCompoFactory().newAbsScrollPane();
        form.add(unlockedCityContent);
        placesContent = frames_.getCompoFactory().newAbsScrollPane();
        form.add(placesContent);
        unlockedCity = new GeneComponentModelImgSelect(frames_,_facade,_subscriptions.getImgRetrieverMiniMapSub());
    }

    public void updateValues() {
        DataMap dm_ = getFacadeGame().getData().getMap();
        screenWidth.setValue(dm_.getScreenWidth());
        screenHeight.setValue(dm_.getScreenHeight());
        spaceBetweenLeftAndHeros.setValue(dm_.getSpaceBetweenLeftAndHeros());
        spaceBetweenTopAndHeros.setValue(dm_.getSpaceBetweenTopAndHeros());
        sideLength.setValue(dm_.getSideLength());
        formWildPk.feedForm(dm_.getFirstPokemon());
        formWildPk.feedSubs(translations);
        wildPkContent.setViewportView(formWildPk.getForm());
        unlockedCityContent.setViewportView(unlockedCity.gene());
        unlockedCity.getName().getSelectUniq().getSelect().addListener(new GeneComponentModelImgSelectEvent(unlockedCity));
        unlockedCity.updateValue(dm_.getUnlockedCity());
        translations.addAllElts(unlockedCity.subs());
        crudPlace.initFormAll();
        placesContent.setViewportView(crudPlace.getGroup());
    }
    public void update() {
        DataMap dm_ = getFacadeGame().getData().getMap();
        dm_.setScreenWidth(screenWidth.getValue());
        dm_.setScreenHeight(screenHeight.getValue());
        dm_.setSpaceBetweenLeftAndHeros(spaceBetweenLeftAndHeros.getValue());
        dm_.setSpaceBetweenTopAndHeros(spaceBetweenTopAndHeros.getValue());
        dm_.setSideLength(sideLength.getValue());
        dm_.setFirstPokemon(formWildPk.buildEntity());
        dm_.setUnlockedCity(unlockedCity.getName().tryRet());
        dm_.setPlaces(crudPlace.getList());
    }
    public AbsSpinner getScreenWidth() {
        return screenWidth;
    }

    public AbsSpinner getScreenHeight() {
        return screenHeight;
    }

    public AbsSpinner getSpaceBetweenLeftAndHeros() {
        return spaceBetweenLeftAndHeros;
    }

    public AbsSpinner getSpaceBetweenTopAndHeros() {
        return spaceBetweenTopAndHeros;
    }

    public AbsSpinner getSideLength() {
        return sideLength;
    }

    public FormWildPk getFormWildPk() {
        return formWildPk;
    }

    public GeneComponentModelImgSelect getUnlockedCity() {
        return unlockedCity;
    }

    public CrudGeneFormEntPlace getCrudPlace() {
        return crudPlace;
    }

    public AbsButton getApplyMapModif() {
        return applyMapModif;
    }

    public AbsPanel getForm() {
        return form;
    }

    public WindowPkEditor getWindow() {
        return window;
    }

    public FacadeGame getFacadeGame() {
        return facadeGame;
    }

}
