package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelLevelLeague {

    private NullablePoint trainerCoords = new NullablePoint();

    private NullablePoint accessPoint = new NullablePoint();
    private GeneComponentModelImgSelect imageFileName;
    private final ContentComponentModelTrainerLeague trainerLeague = new ContentComponentModelTrainerLeague();
    private final StringMap<AbsButton> tiles = new StringMap<AbsButton>();
    private FormLevelGrid level;
    private AbsScrollPane fore;
    private LevelLeague edited;
    private final IdList<SubscribedTranslation> translationsGrid = new IdList<SubscribedTranslation>();
    private Point selected = new Point((short) 0,(short) 0);
    private String key = "";
    private AbsButton removeTile;
    private int nbPlace;
    private int nbLevel;
    private AbsScrollPane scrollPane;

    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        scrollPane = _core.getCompoFactory().newAbsScrollPane();
        level = new FormLevelGrid(_core,_fac, _f,_fact);
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        AbsCustComponent splitter_ = _core.getCompoFactory().newHorizontalSplitPane(scrollPane, _core.getCompoFactory().newAbsScrollPane(form_));
        level.getTranslationList().setFormLevelGridUniq(null);
        return splitter_;
    }
    public void setupGridDims(Coords _coords, Place _pl, LevelLeague _wild) {
        Points<Block> blocks_ = _wild.getBlocks();
        edited = _wild;
        nbPlace = _coords.getNumberPlace();
        nbLevel = _coords.getLevel().getLevelIndex();
        Points<int[][]> frontTiles_ = Level.getLevelForegroundImage(level.getFacadeGame().getData(), _coords, _pl,_wild);
        level.setupGridDims(blocks_, frontTiles_);
        level.setSelectedPlace(_coords);
        IdList<SubscribedTranslation> subs_ = level.getTranslationList().getSubscribedTranslations().getVal(level.getFrame());
        subs_.removeAllElements(translationsGrid);
        IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>();
        next_.add(new RefreshGridSubscription(level.getFacadeGame(),level,_coords,_pl,_wild));
        subs_.addAllElts(next_);
        translationsGrid.addAllElts(next_);
        fore = level.getApi().getCompoFactory().newAbsScrollPane();
        imageFileName = new GeneComponentModelImgSelect(level.getApi(), level.getFacadeGame(), level.getTranslationList().getImgRetrieverLinksSub());
        level.getForm().add(imageFileName.gene());
        FormDataMap.baseSelectImage(imageFileName);
        imageFileName.getName().getSelectUniq().getSelect().addListener(new ChangeItemTileLeagueEvent(this));
        level.getForm().add(trainerLeague.effectFormLeader(level.getApi(), level.getFacadeGame(), level.getTranslationList(), level.getFrame(), level));
        trainerLeague.feedFormSub(_wild.getTrainer());
        trainerLeague.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemTileLeagueEvent(this));
        level.getForm().add(fore);
        level.getGrid().addMouseListener(new TileKindLeagueEvent(this));
        imageFileName.updateValue(_wild.getFileName());
        scrollPane.setViewportView(level.getForm());
        trainerCoords = _wild.getTrainerCoords();
        accessPoint = _wild.getAccessPoint();
    }
    public void buildEntity(LevelLeague _lev) {
        _lev.setBlocks(getLevel().getEdited());
        _lev.setTrainer(trainerLeague.buildEntity());
        _lev.setAccessPoint(edited.getAccessPoint());
        _lev.setTrainerCoords(edited.getTrainerCoords());
        _lev.setFileName(imageFileName.getName().tryRet());
    }

    public void viewForeground(int _x, int _y) {
        Point pt_ = level.toPt(_x, _y);
        selected = pt_;
        getLevel().getFormBlockTile().getMatch().addActionListener(new ApplyForeLeagueTileEvent(this));
        if (Point.eq(trainerCoords,pt_)) {
            choose(MessagesEditorSelect.LEAGUE_TRAINER);
        } else if (Point.eq(accessPoint,pt_)) {
            choose(MessagesEditorSelect.LEAGUE_ACCESS);
        } else if (level.getFacadeGame().getMap().isEmptyForAdding(AbsContentComponentModelLevelLinks.coords(nbPlace,nbLevel,null,pt_))){
            initFormChoices();
        } else {
            key = "";
            fore.setNullViewportView();
        }
        getLevel().getFrame().pack();
    }

    private void initFormChoices() {
        key = "";
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectTileKindLeagueTr(MessagesPkEditor.getAppliTr(level.getApi().currentLg())).getMapping();
        AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
        AbsPanel form_ = compoFactory_.newPageBox();
        tiles.clear();
        for (EntryCust<String,String> e: messages_.entryList()) {
            AbsButton but_ = compoFactory_.newPlainButton(e.getValue());
            but_.addActionListener(new TileKindLeagueChoiceEvent(this,e.getKey()));
            form_.add(but_);
            tiles.addEntry(e.getKey(),but_);
        }
        fore.setViewportView(form_);
    }

    public void choose(String _k) {
        key = _k;
        //ChangeItemTileLeagueEvent
        AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        removeTile = compoFactory_.newPlainButton("-");
        removeTile.addActionListener(new RemoveLeagueTileEvent(this));
        form_.add(removeTile);
        fore.setViewportView(form_);
    }

    public void events() {
        if (StringUtil.quickEq(key, MessagesEditorSelect.LEAGUE_TRAINER)) {
            trainerLeague.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.LEAGUE_ACCESS)) {
            imageFileName.getName().getSelectUniq().getSelect().events(null);
        }
    }
    public void applySelectItem() {
        if (StringUtil.quickEq(key, MessagesEditorSelect.LEAGUE_TRAINER)) {
            ContentComponentModelLevelWithWild.trySet(level.getFacadeGame().getData().getPeople().getVal(trainerLeague.getTrainerImg().getMiniFileName().getName().tryRet()), level.getForegroundEdited(), selected);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.LEAGUE_ACCESS)) {
            ContentComponentModelLevelWithWild.trySet(level.getFacadeGame().getData().getLinks().getVal(imageFileName.getName().tryRet()), level.getForegroundEdited(), selected);
        }
        level.refreshImg(level.getFormBlockTile().getEdited().getWidth(), level.getFormBlockTile().getEdited().getHeight());
    }

    public void applyTile() {
        if (StringUtil.quickEq(key, MessagesEditorSelect.LEAGUE_TRAINER)) {
            trainerCoords.setPoint(selected);
            edited.setTrainerCoords(ConverterCommonMapUtil.copyNullablePoint(trainerCoords));
            validate();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.LEAGUE_ACCESS)) {
            accessPoint.setPoint(selected);
            edited.setAccessPoint(ConverterCommonMapUtil.copyNullablePoint(accessPoint));
            validate();
        }
        key = "";
        level.refreshImg(level.getFormBlockTile().getEdited().getWidth(), level.getFormBlockTile().getEdited().getHeight());
    }

    private void validate() {
        int[][] val_ = level.getForegroundEdited().getVal(selected);
        level.getForegroundEdited().removeKey(selected);
        level.getForeground().put(selected,val_);
    }

    public void removeTile() {
        if (StringUtil.quickEq(key, MessagesEditorSelect.LEAGUE_TRAINER)) {
            trainerCoords.setPoint(null);
            edited.setTrainerCoords(ConverterCommonMapUtil.copyNullablePoint(trainerCoords));
            removeFore();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.LEAGUE_ACCESS)) {
            accessPoint.setPoint(null);
            edited.setAccessPoint(ConverterCommonMapUtil.copyNullablePoint(accessPoint));
            removeFore();
        }
        key = "";
        level.refreshImg(level.getFormBlockTile().getEdited().getWidth(), level.getFormBlockTile().getEdited().getHeight());
        initFormChoices();
    }

    private void removeFore() {
        level.getForegroundEdited().removeKey(selected);
        level.getForeground().removeKey(selected);
    }

    public StringMap<AbsButton> getTiles() {
        return tiles;
    }

    public AbsButton getRemoveTile() {
        return removeTile;
    }

    public FormLevelGrid getLevel() {
        return level;
    }

}
