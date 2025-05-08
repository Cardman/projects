package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class FormDataMap {
    private final WindowPkEditor window;
    private final SubscribedTranslationList translationList;
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
    private final FormMiniMapGrid miniMapGrid;
    private final CrudGeneFormEntPlace crudPlace;

    public FormDataMap(WindowPkEditor _ed, FacadeGame _facade, SubscribedTranslationList _subscriptions, IdList<SubscribedTranslation> _trs) {
        formWildPk = new FormWildPk(_ed.getFrames(), _facade, _subscriptions,_ed.getCommonFrame());
        translationList = _subscriptions;
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
        form.add(SubscribedTranslationList.line(frames_,MessagesPkEditor.getMessagesEditorSelectDataMapGeneTr(MessagesPkEditor.getAppliTr(frames_.currentLg())),MessagesEditorSelect.SCREEN_WIDTH,screenWidth));
        form.add(SubscribedTranslationList.line(frames_,MessagesPkEditor.getMessagesEditorSelectDataMapGeneTr(MessagesPkEditor.getAppliTr(frames_.currentLg())),MessagesEditorSelect.SCREEN_HEIGHT,screenHeight));
        form.add(SubscribedTranslationList.line(frames_,MessagesPkEditor.getMessagesEditorSelectDataMapGeneTr(MessagesPkEditor.getAppliTr(frames_.currentLg())),MessagesEditorSelect.SPACE_LEFT,spaceBetweenLeftAndHeros));
        form.add(SubscribedTranslationList.line(frames_,MessagesPkEditor.getMessagesEditorSelectDataMapGeneTr(MessagesPkEditor.getAppliTr(frames_.currentLg())),MessagesEditorSelect.SPACE_TOP,spaceBetweenTopAndHeros));
        form.add(SubscribedTranslationList.line(frames_,MessagesPkEditor.getMessagesEditorSelectDataMapGeneTr(MessagesPkEditor.getAppliTr(frames_.currentLg())),MessagesEditorSelect.SIDE_LENGTH,sideLength));
        form.add(applyMapModif);
        wildPkContent = frames_.getCompoFactory().newAbsScrollPane();
        form.add(SubscribedTranslationList.line(frames_,MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_0,wildPkContent));
        unlockedCityContent = frames_.getCompoFactory().newAbsScrollPane();
        form.add(SubscribedTranslationList.line(frames_,MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_GOAL_3,unlockedCityContent));
        placesContent = frames_.getCompoFactory().newAbsScrollPane();
        form.add(placesContent);
        AbsScrollPane miniMapGridContent_ = frames_.getCompoFactory().newAbsScrollPane();
        form.add(miniMapGridContent_);
        unlockedCity = new GeneComponentModelImgSelect(frames_,_facade,_subscriptions.getImgRetrieverMiniMapSub());
        miniMapGrid = new FormMiniMapGrid(frames_,_facade, miniMapGridContent_, _ed.getCommonFrame(),_subscriptions);
    }

    public void updateValues() {
        crudPlace.initFormAll();
        placesContent.setViewportView(crudPlace.getGroup());
        displayFirstPk();
    }
    public void displayFirstPk() {
        DataMap dm_ = getFacadeGame().getData().getMap();
        screenWidth.setValue(dm_.getScreenWidth());
        screenHeight.setValue(dm_.getScreenHeight());
        spaceBetweenLeftAndHeros.setValue(dm_.getSpaceBetweenLeftAndHeros());
        spaceBetweenTopAndHeros.setValue(dm_.getSpaceBetweenTopAndHeros());
        sideLength.setValue(dm_.getSideLength());
        formWildPk.feedForm();
        formWildPk.feedForm(dm_.getFirstPokemon());
        formWildPk.feedSubs(translations);
        wildPkContent.setViewportView(formWildPk.getForm());
        crudPlace.refresh();
        unlockedCityContent.setViewportView(SubscribedTranslationList.line(getWindow().getFrames(),MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_MINI,unlockedCity.gene()));
        baseSelectImage(unlockedCity);
        unlockedCity.updateValue(dm_.getUnlockedCity());
        translations.addAllElts(unlockedCity.subs());
        miniMapGrid.setupGridDims();
        miniMapGrid.setupGrid(false);
    }

    static void baseSelectImage(GeneComponentModelImgSelect _sel) {
        _sel.getName().getSelectUniq().getSelect().addListener(new GeneComponentModelImgSelectEvent(_sel));
    }

    public void update() {
        DataMap dm_ = getFacadeGame().getData().getMap();
        dm_.setScreenWidth(screenWidth.getValue());
        dm_.setScreenHeight(screenHeight.getValue());
        dm_.setSpaceBetweenLeftAndHeros(spaceBetweenLeftAndHeros.getValue());
        dm_.setSpaceBetweenTopAndHeros(spaceBetweenTopAndHeros.getValue());
        int old_ = dm_.getSideLength();
        dm_.setSideLength(sideLength.getValue());
        dm_.setFirstPokemon(formWildPk.buildEntity());
        dm_.setUnlockedCity(unlockedCity.getName().tryRet());
        if (old_ != dm_.getSideLength()) {
            FormLevelGrid formLevelGrid_ = translationList.getFormLevelGridUniq();
            if (formLevelGrid_ != null) {
                formLevelGrid_.refreshImg(formLevelGrid_.getFormBlockTile().getEdited().getWidth(), formLevelGrid_.getFormBlockTile().getEdited().getHeight());
            }
            miniMapGrid.setupGrid(true);
        }
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

    public FormMiniMapGrid getMiniMapGrid() {
        return miniMapGrid;
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
