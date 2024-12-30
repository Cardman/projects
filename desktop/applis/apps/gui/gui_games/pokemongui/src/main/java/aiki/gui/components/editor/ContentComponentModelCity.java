package aiki.gui.components.editor;


import aiki.facade.*;
import aiki.instances.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
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
    private final NullablePoint storage = new NullablePoint();
    private final NullablePoint gymLeaderCoords = new NullablePoint();
    private final StringMap<AbsButton> tiles = new StringMap<AbsButton>();
    private AbsButton removeTile;
    private AbsButton removeTileBuilding;
    private AbsScrollPane scrollPane;
    private GeneComponentModelImgSelect imageFileName;
    private final ContentComponentModelGymTrainer gymTrainer = new ContentComponentModelGymTrainer();
    private final ContentComponentModelGymLeader gymLeader = new ContentComponentModelGymLeader();
    private final ContentComponentModelGerant gerant = new ContentComponentModelGerant();
    private final ContentComponentModelSeller seller = new ContentComponentModelSeller();
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
        editedBuildings = ConverterCommonMapUtil.copyPointsBuilding(_pl.getBuildings());
        nbPlace = _nbPlace;
        Coords coords_ = AbsContentComponentModelLevelLinks.coords(_nbPlace, 0, null);
        Points<int[][]> frontTiles_ = Level.getLevelForegroundImage(level.getFacadeGame().getData(), coords_, _pl,_pl.getLevelOutdoor());
        level.setupGridDims(blocks_, frontTiles_);
        level.setSelectedPlace(coords_);
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
        Coords coords_ = AbsContentComponentModelLevelLinks.coords(nbPlace, 0, selected);
        Points<int[][]> frontTiles_ = Level.getLevelForegroundImage(level.getFacadeGame().getData(), coords_, edited,editedBuilding.getLevel());
        levelBuilding.setupGridDims(blocks_, frontTiles_);
        levelBuilding.setSelectedPlace(coords_);
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
        viewForegroundGym(pt_);
        viewForegroundPc(pt_);
        getLevel().getFrame().pack();
    }

    private void viewForegroundPc(Point _pt) {
        if (editedBuilding instanceof PokemonCenter) {
            PokemonCenter pc_ = (PokemonCenter) editedBuilding;
            if (pc_.getIndoor().getGerants().contains(_pt)) {
                Person p_ = pc_.getIndoor().getGerants().getVal(_pt);
                if (p_ instanceof GerantPokemon) {
                    choose(MessagesEditorSelect.PC_TILE_GERANT);
                    gerant.feedForm((GerantPokemon) p_);
                }
                if (p_ instanceof Seller) {
                    choose(MessagesEditorSelect.PC_TILE_SELLER);
                    seller.feedForm((Seller) p_);
                }
            } else if (Point.eq(pc_.getIndoor().getStorageCoords(), _pt)){
                choose(MessagesEditorSelect.PC_TILE_STORAGE);
            } else if (Point.eq(pc_.getExitCity(), _pt)){
                choose(MessagesEditorSelect.PC_TILE_EXIT);
            } else {
                initFormChoicesPc();
            }
        }
    }

    private void viewForegroundGym(Point _pt) {
        if (editedBuilding instanceof Gym) {
            Gym g_ = (Gym) editedBuilding;
            if (g_.getIndoor().getGymTrainers().contains(_pt)) {
                choose(MessagesEditorSelect.GYM_TILE_TRAINER);
                gymTrainer.feedFormSub(g_.getIndoor().getGymTrainers().getVal(_pt));
            } else if (Point.eq(g_.getIndoor().getGymLeaderCoords(), _pt)){
                choose(MessagesEditorSelect.GYM_TILE_LEADER);
            } else if (Point.eq(g_.getExitCity(), _pt)){
                choose(MessagesEditorSelect.GYM_TILE_EXIT);
            } else {
                initFormChoicesGym();
            }
        }
    }

    private void initFormChoicesGym() {
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectTileKindGymTr(MessagesPkEditor.getAppliTr(level.getApi().currentLg())).getMapping();
        choices(messages_);
    }

    private void initFormChoicesPc() {
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectTileKindPcTr(MessagesPkEditor.getAppliTr(level.getApi().currentLg())).getMapping();
        choices(messages_);
    }

    private void choices(StringMap<String> _messages) {
        key = "";
        AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
        AbsPanel form_ = compoFactory_.newPageBox();
        tiles.clear();
        for (EntryCust<String,String> e: _messages.entryList()) {
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
        if (editedBuilding instanceof Gym) {
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
        if (editedBuilding instanceof PokemonCenter) {
            if (StringUtil.quickEq(_k, MessagesEditorSelect.PC_TILE_GERANT)) {
                AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
                AbsPanel form_ = compoFactory_.newLineBox();
                form_.add(gerant.effectForm(level.getApi(), level.getFacadeGame(), level.getTranslationList(), levelBuilding));
                gerant.getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemBuildingTileEvent(this));
                removeTileBuilding = compoFactory_.newPlainButton("-");
                removeTileBuilding.addActionListener(new RemoveBuildingEltTileEvent(this));
                form_.add(removeTileBuilding);
                foreBuilding.setViewportView(form_);
            }
            if (StringUtil.quickEq(_k, MessagesEditorSelect.PC_TILE_SELLER)) {
                AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
                AbsPanel form_ = compoFactory_.newLineBox();
                form_.add(seller.effectForm(level.getApi(), level.getFacadeGame(), level.getTranslationList(), levelBuilding));
                seller.getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemBuildingTileEvent(this));
                removeTileBuilding = compoFactory_.newPlainButton("-");
                removeTileBuilding.addActionListener(new RemoveBuildingEltTileEvent(this));
                form_.add(removeTileBuilding);
                foreBuilding.setViewportView(form_);
            }
            if (StringUtil.quickEq(_k, MessagesEditorSelect.PC_TILE_STORAGE) || StringUtil.quickEq(_k, MessagesEditorSelect.PC_TILE_EXIT)) {
                AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
                AbsPanel form_ = compoFactory_.newLineBox();
                removeTileBuilding = compoFactory_.newPlainButton("-");
                removeTileBuilding.addActionListener(new RemoveBuildingEltTileEvent(this));
                form_.add(removeTileBuilding);
                foreBuilding.setViewportView(form_);
            }
        }
    }

    public void events() {
        eventsGym();
        eventsPc();
    }

    private void eventsPc() {
        if (editedBuilding instanceof PokemonCenter) {
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_GERANT)) {
                gerant.getMiniFileName().getName().getSelectUniq().getSelect().events(null);
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_SELLER)) {
                seller.getMiniFileName().getName().getSelectUniq().getSelect().events(null);
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_EXIT)) {
                imageFileName.getName().getSelectUniq().getSelect().events(null);
            }
        }
    }

    private void eventsGym() {
        if (editedBuilding instanceof Gym) {
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_TRAINER)) {
                gymTrainer.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_LEADER)) {
                gymLeader.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.GYM_TILE_EXIT)) {
                imageFileName.getName().getSelectUniq().getSelect().events(null);
            }
        }
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
        applySelectItemGym();
        applySelectItemPc();
        levelBuilding.refreshImg(levelBuilding.getFormBlockTile().getEdited().getWidth(), levelBuilding.getFormBlockTile().getEdited().getHeight());
    }

    private void applySelectItemPc() {
        if (editedBuilding instanceof PokemonCenter) {
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_GERANT)) {
                ContentComponentModelLevelWithWild.trySet(level.getFacadeGame().getData().getLinks().getVal(gerant.getMiniFileName().getName().tryRet()),levelBuilding.getForegroundEdited(),selectedBuilding);
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_SELLER)) {
                ContentComponentModelLevelWithWild.trySet(level.getFacadeGame().getData().getLinks().getVal(seller.getMiniFileName().getName().tryRet()),levelBuilding.getForegroundEdited(),selectedBuilding);
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_EXIT)) {
                ContentComponentModelLevelWithWild.trySet(level.getFacadeGame().getData().getLinks().getVal(imageFileName.getName().tryRet()),levelBuilding.getForegroundEdited(),selectedBuilding);
            }
        }
    }

    private void applySelectItemGym() {
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
    }

    public void applyBuildingTile() {
        applyBuildingTileGym();
        applyBuildingTilePc();
        levelBuilding.refreshImg(levelBuilding.getFormBlockTile().getEdited().getWidth(), levelBuilding.getFormBlockTile().getEdited().getHeight());
    }

    private void applyBuildingTilePc() {
        if (editedBuilding instanceof PokemonCenter) {
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_GERANT)) {
                ((PokemonCenter)editedBuilding).getIndoor().getGerants().put(selectedBuilding,gerant.buildEntity());
                validate();
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_SELLER)) {
                ((PokemonCenter)editedBuilding).getIndoor().getGerants().put(selectedBuilding,seller.buildEntity());
                validate();
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_EXIT)) {
                exitBuilding.setPoint(selectedBuilding);
                editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
                validate();
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_STORAGE)) {
                storage.setPoint(selectedBuilding);
                ((PokemonCenter)editedBuilding).getIndoor().setStorageCoords(ConverterCommonMapUtil.copyNullablePoint(storage));
                validate();
            }
            key = "";
        }
    }

    private void applyBuildingTileGym() {
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
    }

    public void removeBuildingTile() {
        removeBuildingTileGym();
        removeBuildingTilePc();
        levelBuilding.refreshImg(levelBuilding.getFormBlockTile().getEdited().getWidth(), levelBuilding.getFormBlockTile().getEdited().getHeight());
    }

    private void removeBuildingTilePc() {
        if (editedBuilding instanceof PokemonCenter) {
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_GERANT) || StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_SELLER)) {
                ((PokemonCenter)editedBuilding).getIndoor().getGerants().removeKey(selectedBuilding);
                removeFore();
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_EXIT)) {
                exitBuilding.setPoint(null);
                editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
                removeFore();
            }
            if (StringUtil.quickEq(key, MessagesEditorSelect.PC_TILE_STORAGE)) {
                storage.setPoint(null);
                ((PokemonCenter)editedBuilding).getIndoor().setStorageCoords(ConverterCommonMapUtil.copyNullablePoint(storage));
                removeFore();
            }
            key = "";
            initFormChoicesPc();
        }
    }

    private void removeBuildingTileGym() {
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
            initFormChoicesGym();
        }
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
