package aiki.gui.components.editor;


import aiki.facade.*;
import aiki.instances.*;
import aiki.map.buildings.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelCity {
    private AbsCustCheckBox gym;
    private AbsButton createBuilding;
    private FormLevelGrid level;
    private FormLevelGrid levelBuilding;
    private AbsCustComponent splitter;
    private AbsScrollPane fore;
    private AbsScrollPane foreBuilding;
    private City edited;
    private Building editedBuilding;
    private Points<Building> editedBuildings;
    private final IdList<SubscribedTranslation> translations = new IdList<SubscribedTranslation>();
    private final IdList<SubscribedTranslation> translationsGrid = new IdList<SubscribedTranslation>();
    private final IdList<SubscribedTranslation> translationsGridSec = new IdList<SubscribedTranslation>();
    private Point selected = new Point((short) 0,(short) 0);
    private Point selectedBuilding = new Point((short) 0,(short) 0);
    private final NullablePoint exitBuilding = new NullablePoint();
    private final NullablePoint gymLeaderCoords = new NullablePoint();
    private final StringMap<AbsButton> tiles = new StringMap<AbsButton>();
    private AbsButton removeTile;
    private AbsButton removeTileBuilding;
    private AbsScrollPane scrollPane;
    private GeneComponentModelImgSelect imageFileName;
    private final ContentComponentModelGymTrainer gymTrainer = new ContentComponentModelGymTrainer();
    private final ContentComponentModelGymLeader gymLeader = new ContentComponentModelGymLeader();
    private int nbPlace;
    private String key = "";

    public AbsCustComponent setupGridDims(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f,Coords _coords, City _pl) {
        AbsCustComponent form_ = form(_core, _fac, _fact, _f);
        setupGridDims(_coords.getNumberPlace(), _pl);
        return form_;
    }
    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        scrollPane = _core.getCompoFactory().newAbsScrollPane();
        AbsScrollPane map_ = scrollPane;
        level = new FormLevelGrid(_core,_fac, _f,_fact);
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        splitter = _core.getCompoFactory().newHorizontalSplitPane(map_,_core.getCompoFactory().newAbsScrollPane(form_));
        level.getTranslationList().setFormLevelGridUniq(null);
        return splitter;
    }
    public void setupGridDims(int _nbPlace, City _pl) {
        Points<Block> blocks_ = ConverterCommonMapUtil.copyPointsBlock(_pl.getLevelOutdoor().getBlocks());
        edited = _pl;
        editedBuildings = new PointsBuilding();
        for (EntryCust<Point,Building> f:_pl.getBuildings().entryList()) {
            if (f.getValue() instanceof Gym) {
                editedBuildings.addEntry(new Point(f.getKey()),ConverterCommonMapUtil.copyGym((Gym) f.getValue()));
            } else {
                PokemonCenter pc_ = new PokemonCenter();
                pc_.setLevel(Instances.newLevelIndoorPokemonCenter());
                pc_.setExitCity(ConverterCommonMapUtil.copyNullablePoint(f.getValue().getExitCity()));
                pc_.setImageFileName(f.getValue().getImageFileName());
                pc_.getLevel().setBlocks(ConverterCommonMapUtil.copyPointsBlock(f.getValue().getLevel().getBlocks()));
                editedBuildings.addEntry(new Point(f.getKey()),pc_);
            }
        }
        nbPlace = _nbPlace;
        Coords coords_ = ContentComponentModelLevelCaveLinks.coords(_nbPlace, 0, null);
        Points<int[][]> frontTiles_ = Level.getLevelForegroundImage(level.getFacadeGame().getData(), coords_, _pl,_pl.getLevelOutdoor());
        level.setupGridDims(blocks_, frontTiles_);
        IdList<SubscribedTranslation> subs_ = level.getTranslationList().getSubscribedTranslations().getVal(level.getFrame());
        subs_.removeAllElements(translationsGrid);
        IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>();
        next_.add(new RefreshGridSubscription(level.getFacadeGame(),level,coords_,_pl,_pl.getLevelOutdoor()));
        subs_.addAllElts(next_);
        translationsGrid.addAllElts(next_);
        fore = level.getApi().getCompoFactory().newAbsScrollPane();
        level.getForm().add(fore);
        level.getGrid().addMouseListener(new BuildingKindEvent(this));
        scrollPane.setViewportView(level.getForm());
    }
    public void buildEntity() {
        edited.getLevelOutdoor().setBlocks(getLevel().getEdited());
        edited.setBuildings(editedBuildings);
    }

    public AbsCustComponent getSplitter() {
        return splitter;
    }

    public void viewForeground(int _x, int _y) {
        Point pt_ = level.toPt(_x, _y);
        selected = pt_;
        getLevel().getFormBlockTile().getMatch().addActionListener(new ApplyBuildingTileEvent(this));
        if (editedBuildings.contains(pt_)) {
            editedBuilding = editedBuildings.getVal(pt_);
            choose(false);
        } else {
            AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
            AbsPanel form_ = compoFactory_.newPageBox();
            gym = compoFactory_.newCustCheckBox();
            form_.add(gym);
            createBuilding = compoFactory_.newPlainButton("+");
            createBuilding.addActionListener(new BuildingKindChoiceEvent(this));
            form_.add(createBuilding);
            fore.setViewportView(form_);
            editedBuilding = null;
        }
        if (foreBuilding != null) {
            foreBuilding.setNullViewportView();
        }
        getLevel().getFrame().pack();
    }

    public void choose(boolean _build) {
        if (_build) {
            if (gym.isSelected()) {
                editedBuilding = Instances.newGym();
            } else {
                editedBuilding = Instances.newPokemonCenter();
            }
        }

        AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
        levelBuilding = new FormLevelGrid(level.getApi(), level.getFacadeGame(), level.getFrame(), level.getTranslationList());
        Points<Block> blocks_ = ConverterCommonMapUtil.copyPointsBlock(editedBuilding.getLevel().getBlocks());
        Coords coords_ = ContentComponentModelLevelCaveLinks.coords(nbPlace, 0, selected);
        Points<int[][]> frontTiles_ = Level.getLevelForegroundImage(level.getFacadeGame().getData(), coords_, edited,editedBuilding.getLevel());
        levelBuilding.setupGridDims(blocks_, frontTiles_);
        levelBuilding.getGrid().addMouseListener(new BuildingTileKindEvent(this));
        IdList<SubscribedTranslation> subs_ = level.getTranslationList().getSubscribedTranslations().getVal(level.getFrame());
        subs_.removeAllElements(translationsGridSec);
        IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>();
        next_.add(new RefreshGridSubscription(level.getFacadeGame(),levelBuilding,coords_,edited,editedBuilding.getLevel()));
        subs_.addAllElts(next_);
        translationsGridSec.addAllElts(next_);
        AbsPanel form_ = compoFactory_.newLineBox();
        imageFileName = new GeneComponentModelImgSelect(level.getApi(), level.getFacadeGame(), level.getTranslationList().getImgRetrieverLinksSub());
        form_.add(imageFileName.gene());
        FormDataMap.baseSelectImage(imageFileName);
        imageFileName.getName().getSelectUniq().getSelect().addListener(new ChangeItemBuildingTileEvent(this));
        imageFileName.updateValue(editedBuilding.getImageFileName());
        if (editedBuilding instanceof Gym) {
            form_.add(gymLeader.effectFormLeader(level.getApi(), level.getFacadeGame(),level.getTranslationList(),level.getFrame(),levelBuilding));
            gymLeader.feedFormSub(((Gym)editedBuilding).getIndoor().getGymLeader());
            gymLeader.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemBuildingTileEvent(this));
            gymLeader.getTrainerImg().getMiniFileName().updateValue(((Gym)editedBuilding).getIndoor().getGymLeader().getImageMiniFileName());
        }
        translations.addAllElts(next_);
        removeTile = compoFactory_.newPlainButton("-");
        removeTile.addActionListener(new RemoveBuildingTileEvent(this));
        foreBuilding = level.getApi().getCompoFactory().newAbsScrollPane();
        levelBuilding.getForm().add(foreBuilding);
        form_.add(levelBuilding.getForm());
        form_.add(removeTile);
        fore.setViewportView(form_);
        foreBuilding.setNullViewportView();
    }

    public void viewForegroundBuilding(int _x, int _y) {
        Point pt_ = level.toPt(_x, _y);
        selectedBuilding = pt_;
        getLevelBuilding().getFormBlockTile().getMatch().addActionListener(new ApplyBuildingEltTileEvent(this));
        if (editedBuilding instanceof Gym) {
            Gym g_ = (Gym) editedBuilding;
            if (g_.getIndoor().getGymTrainers().contains(pt_)) {
                choose(MessagesEditorSelect.GYM_TILE_TRAINER);
                gymTrainer.feedFormSub(g_.getIndoor().getGymTrainers().getVal(pt_));
            } else if (Point.eq(g_.getIndoor().getGymLeaderCoords(), pt_)){
                choose(MessagesEditorSelect.GYM_TILE_LEADER);
            } else if (Point.eq(g_.getExitCity(), pt_)){
                choose(MessagesEditorSelect.GYM_TILE_EXIT);
            } else {
                initFormChoices();
            }
        }
        if (editedBuilding instanceof PokemonCenter) {
            AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
            AbsPanel form_ = compoFactory_.newPageBox();
            removeTileBuilding = compoFactory_.newPlainButton("-");
            removeTileBuilding.addActionListener(new RemoveBuildingEltTileEvent(this));
            form_.add(removeTileBuilding);
            foreBuilding.setViewportView(form_);
        }
        getLevel().getFrame().pack();
    }

    private void initFormChoices() {
        key = "";
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectTileKindGymTr(MessagesPkEditor.getAppliTr(level.getApi().currentLg())).getMapping();
        AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
        AbsPanel form_ = compoFactory_.newPageBox();
        tiles.clear();
        for (EntryCust<String,String> e: messages_.entryList()) {
            AbsButton but_ = compoFactory_.newPlainButton(e.getValue());
            but_.addActionListener(new TileKindBuildingChoiceEvent(this,e.getKey()));
            form_.add(but_);
            tiles.addEntry(e.getKey(),but_);
        }
        fore.setViewportView(form_);
        foreBuilding.setNullViewportView();
    }
    public void choose(String _k) {
        key = _k;
        if (StringUtil.quickEq(_k, MessagesEditorSelect.GYM_TILE_TRAINER)) {
            AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
            AbsPanel form_ = compoFactory_.newLineBox();
            form_.add(gymTrainer.effectForm(level.getApi(), level.getFacadeGame(), level.getTranslationList(), level.getFrame(),levelBuilding));
            gymTrainer.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemBuildingTileEvent(this));
            removeTileBuilding = compoFactory_.newPlainButton("-");
            removeTileBuilding.addActionListener(new RemoveBuildingEltTileEvent(this));
            form_.add(removeTileBuilding);
            foreBuilding.setViewportView(form_);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.GYM_TILE_LEADER) || StringUtil.quickEq(_k, MessagesEditorSelect.GYM_TILE_EXIT)) {
            AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
            AbsPanel form_ = compoFactory_.newLineBox();
            removeTileBuilding = compoFactory_.newPlainButton("-");
            removeTileBuilding.addActionListener(new RemoveBuildingEltTileEvent(this));
            form_.add(removeTileBuilding);
            foreBuilding.setViewportView(form_);
        }
    }

    public void events() {
//        if (editedBuilding instanceof Gym) {
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_TRAINER)) {
                gymTrainer.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_LEADER)) {
                gymLeader.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_EXIT)) {
                imageFileName.getName().getSelectUniq().getSelect().events(null);
            }
//        }
//        if (editedBuilding instanceof PokemonCenter) {
//            imageFileName.getName().getSelectUniq().getSelect().events(null);
//        }
    }
    public void applyTile() {
        if (editedBuilding instanceof Gym) {
            ((Gym)editedBuilding).getIndoor().setGymLeader(gymLeader.buildEntity());
            editedBuilding.getLevel().setBlocks(levelBuilding.getEdited());
            editedBuildings.put(selected,editedBuilding);
        }
        if (editedBuilding instanceof PokemonCenter) {
            editedBuilding.getLevel().setBlocks(levelBuilding.getEdited());
            editedBuildings.put(selected,editedBuilding);
        }
        level.refreshImg(level.getFormBlockTile().getEdited().getWidth(), level.getFormBlockTile().getEdited().getHeight());
    }

    public void removeTile() {
        editedBuildings.removeKey(selected);
        level.refreshImg(level.getFormBlockTile().getEdited().getWidth(), level.getFormBlockTile().getEdited().getHeight());
        editedBuilding = null;
        foreBuilding.setNullViewportView();
    }

    public void applySelectItem() {
        if (editedBuilding instanceof Gym) {
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_TRAINER)) {
                ContentComponentModelLevelWithWild.trySet(level.getFacadeGame().getData().getPeople().getVal(gymTrainer.getTrainerImg().getMiniFileName().getName().tryRet()), levelBuilding.getForegroundEdited(), selectedBuilding);
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_LEADER)) {
                ContentComponentModelLevelWithWild.trySet(level.getFacadeGame().getData().getPeople().getVal(gymLeader.getTrainerImg().getMiniFileName().getName().tryRet()), levelBuilding.getForegroundEdited(), selectedBuilding);
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_EXIT)) {
                ContentComponentModelLevelWithWild.trySet(level.getFacadeGame().getData().getLinks().getVal(imageFileName.getName().tryRet()), levelBuilding.getForegroundEdited(), selectedBuilding);
            }
        }
        if (editedBuilding instanceof PokemonCenter) {
            ContentComponentModelLevelWithWild.trySet(level.getFacadeGame().getData().getLinks().getVal(imageFileName.getName().tryRet()),levelBuilding.getForegroundEdited(),selectedBuilding);
        }
        levelBuilding.refreshImg(levelBuilding.getFormBlockTile().getEdited().getWidth(), levelBuilding.getFormBlockTile().getEdited().getHeight());
    }
    public void applyBuildingTile() {
        if (editedBuilding instanceof Gym) {
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_TRAINER)) {
                ((Gym)editedBuilding).getIndoor().getGymTrainers().put(selectedBuilding,gymTrainer.buildEntity());
                validate();
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_LEADER)) {
                gymLeaderCoords.setPoint(selectedBuilding);
                ((Gym)editedBuilding).getIndoor().setGymLeaderCoords(ConverterCommonMapUtil.copyNullablePoint(gymLeaderCoords));
                validate();
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_EXIT)) {
                exitBuilding.setPoint(selectedBuilding);
                editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
                validate();
            }
            key = "";
        }
        if (editedBuilding instanceof PokemonCenter) {
            exitBuilding.setPoint(selectedBuilding);
            editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
            validate();
        }
        levelBuilding.refreshImg(levelBuilding.getFormBlockTile().getEdited().getWidth(), levelBuilding.getFormBlockTile().getEdited().getHeight());
    }

    public void removeBuildingTile() {
        if (editedBuilding instanceof Gym) {
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_TRAINER)) {
                ((Gym)editedBuilding).getIndoor().getGymTrainers().removeKey(selectedBuilding);
                removeFore();
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_LEADER)) {
                gymLeaderCoords.setPoint(null);
                ((Gym)editedBuilding).getIndoor().setGymLeaderCoords(ConverterCommonMapUtil.copyNullablePoint(gymLeaderCoords));
                removeFore();
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_EXIT)) {
                exitBuilding.setPoint(null);
                editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
                removeFore();
            }
            key = "";
        }
        if (editedBuilding instanceof PokemonCenter) {
            exitBuilding.setPoint(null);
            editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
            removeFore();
        }
        levelBuilding.refreshImg(levelBuilding.getFormBlockTile().getEdited().getWidth(), levelBuilding.getFormBlockTile().getEdited().getHeight());
        initFormChoices();
    }

    private void removeFore() {
        levelBuilding.getForegroundEdited().removeKey(selectedBuilding);
        levelBuilding.getForeground().removeKey(selectedBuilding);
    }
    private void validate() {
        int[][] val_ = levelBuilding.getForegroundEdited().getVal(selectedBuilding);
        levelBuilding.getForegroundEdited().removeKey(selectedBuilding);
        levelBuilding.getForeground().put(selectedBuilding,val_);
    }
    public AbsButton getRemoveTile() {
        return removeTile;
    }

    public AbsCustCheckBox getGym() {
        return gym;
    }

    public FormLevelGrid getLevel() {
        return level;
    }

    public FormLevelGrid getLevelBuilding() {
        return levelBuilding;
    }

    public AbsButton getCreateBuilding() {
        return createBuilding;
    }

    public AbsButton getRemoveTileBuilding() {
        return removeTileBuilding;
    }

    public StringMap<AbsButton> getTiles() {
        return tiles;
    }
}
