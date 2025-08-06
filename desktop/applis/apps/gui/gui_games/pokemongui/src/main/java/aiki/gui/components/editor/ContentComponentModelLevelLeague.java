package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelLevelLeague implements ContentComponentModelLevelRemovableSubs {

    private NullablePoint trainerCoords = new NullablePoint();

    private NullablePoint accessPoint = new NullablePoint();
    private GeneComponentModelImgSelect imageFileName;
    private final ContentComponentModelTrainerLeague trainerLeague = new ContentComponentModelTrainerLeague();
    private final ContentComponentModelLevel contentLevel = new ContentComponentModelLevel();
    private LevelLeague edited;
    private int nbPlace;
    private int nbLevel;
    private AbsScrollPane scrollPane;

    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        scrollPane = _core.getCompoFactory().newAbsScrollPane();
        contentLevel.setLevel(new FormLevelGrid(_core, _fac, _f, _fact));
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        AbsCustComponent splitter_ = _core.getCompoFactory().newHorizontalSplitPane(scrollPane, _core.getCompoFactory().newAbsScrollPane(form_));
        contentLevel.getLevel().getTranslationList().setFormLevelGridUniq(null);
        return splitter_;
    }

    public void removeSubs() {
        contentLevel.removeSubs();
    }
    public void setupGridDims(Coords _coords, Place _pl, LevelLeague _wild) {
        edited = _wild;
        nbPlace = _coords.getNumberPlace();
        nbLevel = _coords.getLevel().getLevelIndex();
        contentLevel.setAccessCondition(ConverterCommonMapUtil.copyCoordsLists(contentLevel.getLevel().getFacadeGame().getMap().getAccessCondition()));
        contentLevel.setupGridDims(_coords,_pl,_wild);
        imageFileName = new GeneComponentModelImgSelect(contentLevel.getLevel().getApi(), contentLevel.getLevel().getFacadeGame(), contentLevel.getLevel().getTranslationList().getImgRetrieverLinksSub());
        contentLevel.getLevel().getForm().add(SubscribedTranslationList.line(contentLevel.getLevel().getApi(),MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_MINI,imageFileName.gene()));
        FormDataMap.baseSelectImage(imageFileName);
        imageFileName.getName().getSelectUniq().getSelect().addListener(new ChangeItemTileLeagueEvent(this));
        contentLevel.getLevel().getForm().add(trainerLeague.effectFormLeader(contentLevel.getLevel().getApi(), contentLevel.getLevel().getFacadeGame(), contentLevel.getLevel().getTranslationList(), contentLevel.getLevel().getFrame(), contentLevel.getLevel()));
        trainerLeague.feedFormSub(_wild.getTrainer());
        trainerLeague.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemTileLeagueEvent(this));
        contentLevel.getLevel().getGrid().addMouseListener(new TileKindLeagueEvent(this));
        imageFileName.updateValue(_wild.getFileName());
        scrollPane.setViewportView(contentLevel.getLevel().getForm());
        trainerCoords = _wild.getTrainerCoords();
        accessPoint = _wild.getAccessPoint();
    }
    public void buildEntity(LevelLeague _lev) {
        _lev.setBlocks(getLevel().getEdited());
        _lev.setTrainer(trainerLeague.buildEntity());
        _lev.setAccessPoint(edited.getAccessPoint());
        _lev.setTrainerCoords(edited.getTrainerCoords());
        _lev.setFileName(imageFileName.getName().tryRet());
        contentLevel.getLevel().getFacadeGame().getMap().setAccessCondition(contentLevel.getAccessCondition());
    }

    public void viewForeground(int _x, int _y) {
        Point pt_ = contentLevel.viewForeground(_x, _y);
        getLevel().getFormBlockTile().getMatch().addActionListener(new ApplyForeLeagueTileEvent(this));
        if (Point.eq(trainerCoords,pt_)) {
            choose(MessagesEditorSelect.LEAGUE_TRAINER);
        } else if (Point.eq(accessPoint,pt_)) {
            choose(MessagesEditorSelect.LEAGUE_ACCESS);
        } else if (contentLevel.getLevel().getFacadeGame().getMap().isEmptyForAdding(AbsContentComponentModelLevelLinks.coords(nbPlace,nbLevel,null,pt_))){
            initFormChoices();
        } else {
            contentLevel.choose(DataBase.EMPTY_STRING);
            contentLevel.getFore().setNullViewportView();
        }
        getLevel().getFrame().pack();
    }

    private void initFormChoices() {
        contentLevel.choose(DataBase.EMPTY_STRING);
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectTileKindLeagueTr(MessagesPkEditor.getAppliTr(contentLevel.getLevel().getApi().currentLg())).getMapping();
        AbsCompoFactory compoFactory_ = contentLevel.getLevel().getApi().getCompoFactory();
        AbsPanel form_ = compoFactory_.newPageBox();
        contentLevel.getTiles().clear();
        for (EntryCust<String,String> e: messages_.entryList()) {
            AbsButton but_ = compoFactory_.newPlainButton(e.getValue());
            but_.addActionListener(new TileKindLeagueChoiceEvent(this,e.getKey()));
            form_.add(but_);
            contentLevel.getTiles().addEntry(e.getKey(),but_);
        }
        contentLevel.getFore().setViewportView(form_);
    }

    public void choose(String _k) {
        contentLevel.choose(_k);
        AbsCompoFactory compoFactory_ = contentLevel.getLevel().getApi().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        contentLevel.initRemove(form_);
        contentLevel.getRemoveTile().addActionListener(new RemoveLeagueTileEvent(this));
        contentLevel.getMoveTile().addActionListener(new MoveLeagueTileEvent(this));
        contentLevel.getFore().setViewportView(form_);
    }

    public void events() {
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.LEAGUE_TRAINER)) {
            trainerLeague.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.LEAGUE_ACCESS)) {
            imageFileName.getName().getSelectUniq().getSelect().events(null);
        }
    }
    public void applySelectItem() {
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.LEAGUE_TRAINER)) {
            ContentComponentModelLevelWithWild.trySet(contentLevel.getLevel().getFacadeGame().getData().getPeople().getVal(trainerLeague.getTrainerImg().getMiniFileName().getName().tryRet()), contentLevel.getLevel().getForegroundEdited(), contentLevel.getSelected());
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.LEAGUE_ACCESS)) {
            ContentComponentModelLevelWithWild.trySet(contentLevel.getLevel().getFacadeGame().getData().getLinks().getVal(imageFileName.getName().tryRet()), contentLevel.getLevel().getForegroundEdited(), contentLevel.getSelected());
        }
        contentLevel.applySelectItem();
    }

    public void applyTile() {
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.LEAGUE_TRAINER)) {
            trainerCoords.setPoint(contentLevel.getSelected());
            edited.setTrainerCoords(ConverterCommonMapUtil.copyNullablePoint(trainerCoords));
            validate();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.LEAGUE_ACCESS)) {
            accessPoint.setPoint(contentLevel.getSelected());
            edited.setAccessPoint(ConverterCommonMapUtil.copyNullablePoint(accessPoint));
            validate();
        }
        contentLevel.applyTile();
    }
    public void moveTile() {
        AbsMoveForeTileEvent.moveNullablePoint(trainerCoords,contentLevel.getSelected(),contentLevel.nextPoint());
        AbsMoveForeTileEvent.moveNullablePoint(accessPoint,contentLevel.getSelected(),contentLevel.nextPoint());
        edited.setTrainerCoords(ConverterCommonMapUtil.copyNullablePoint(trainerCoords));
        edited.setAccessPoint(ConverterCommonMapUtil.copyNullablePoint(accessPoint));
        contentLevel.getLevel().getForeground().put(contentLevel.nextPoint(),contentLevel.getLevel().getForegroundEdited().getVal(contentLevel.nextPoint()));
        contentLevel.removeFore();
        contentLevel.applyTile();
    }

    private void validate() {
        contentLevel.validate();
    }

    public void removeTile() {
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.LEAGUE_TRAINER)) {
            trainerCoords.setPoint(null);
            edited.setTrainerCoords(ConverterCommonMapUtil.copyNullablePoint(trainerCoords));
            removeFore();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.LEAGUE_ACCESS)) {
            accessPoint.setPoint(null);
            edited.setAccessPoint(ConverterCommonMapUtil.copyNullablePoint(accessPoint));
            removeFore();
        }
        contentLevel.choose(DataBase.EMPTY_STRING);
        contentLevel.removeTile();
        initFormChoices();
    }

    public ContentComponentModelLevel getContentLevel() {
        return contentLevel;
    }

    private void removeFore() {
        contentLevel.removeFore();
    }

    public StringMap<AbsButton> getTiles() {
        return contentLevel.getTiles();
    }

    public AbsButton getMoveTile() {
        return contentLevel.getMoveTile();
    }

    public AbsButton getRemoveTile() {
        return contentLevel.getRemoveTile();
    }

    public FormLevelGrid getLevel() {
        return contentLevel.getLevel();
    }

}
