package aiki.gui.components.editor;


import aiki.db.*;
import aiki.facade.*;
import aiki.instances.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelCity {
    private AbsCustCheckBox gym;
    private AbsButton createBuilding;
    private final ContentComponentModelLevel contentLevelOutdoor = new ContentComponentModelLevel();
    private final ContentComponentModelLevel contentLevelBuilding = new ContentComponentModelLevel();
    private AbsCustComponent splitter;
    private City edited;
    private Building editedBuilding;
    private Points<Building> editedBuildings;
    private NullablePoint exitBuilding = new NullablePoint();
    private NullablePoint storage = new NullablePoint();
    private NullablePoint gymLeaderCoords = new NullablePoint();
    private AbsScrollPane scrollPane;
    private GeneComponentModelImgSelect imageFileName;
    private final ContentComponentModelGymTrainer gymTrainer = new ContentComponentModelGymTrainer();
    private final ContentComponentModelGymLeader gymLeader = new ContentComponentModelGymLeader();
    private final ContentComponentModelGerant gerant = new ContentComponentModelGerant();
    private final ContentComponentModelSeller seller = new ContentComponentModelSeller();
    private int nbPlace;

    public AbsCustComponent setupGridDims(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f,Coords _coords, City _pl) {
        AbsCustComponent form_ = form(_core, _fac, _fact, _f);
        setupGridDims(_coords.getNumberPlace(), _pl);
        return form_;
    }
    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        scrollPane = _core.getCompoFactory().newAbsScrollPane();
        AbsScrollPane map_ = scrollPane;
        contentLevelOutdoor.setLevel(new FormLevelGrid(_core, _fac, _f, _fact));
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        splitter = _core.getCompoFactory().newHorizontalSplitPane(map_,_core.getCompoFactory().newAbsScrollPane(form_));
        contentLevelOutdoor.getLevel().getTranslationList().setFormLevelGridUniq(null);
        return splitter;
    }
    public void setupGridDims(int _nbPlace, City _pl) {
        edited = _pl;
        editedBuildings = ConverterCommonMapUtil.copyPointsBuilding(_pl.getBuildings());
        nbPlace = _nbPlace;
        Coords coords_ = AbsContentComponentModelLevelLinks.coords(_nbPlace, 0, null);
        contentLevelOutdoor.setAccessCondition(ConverterCommonMapUtil.copyCoordsLists(contentLevelOutdoor.getLevel().getFacadeGame().getMap().getAccessCondition()));
        contentLevelOutdoor.setupGridDims(coords_,_pl,_pl.getLevelOutdoor());
        contentLevelOutdoor.getLevel().getGrid().addMouseListener(new BuildingKindEvent(this));
        scrollPane.setViewportView(contentLevelOutdoor.getLevel().getForm());
    }
    public void buildEntity() {
        edited.getLevelOutdoor().setBlocks(getLevel().getEdited());
        edited.setBuildings(editedBuildings);
        contentLevelOutdoor.getLevel().getFacadeGame().getMap().setAccessCondition(contentLevelOutdoor.getAccessCondition());
    }

    public AbsCustComponent getSplitter() {
        return splitter;
    }

    public void viewForeground(int _x, int _y) {
        Point pt_ = contentLevelOutdoor.viewForeground(_x, _y);
        getLevel().getFormBlockTile().getMatch().addActionListener(new ApplyBuildingTileEvent(this));
        if (editedBuildings.contains(pt_)) {
            editedBuilding = editedBuildings.getVal(pt_);
            choose(false);
        } else {
            AbsCompoFactory compoFactory_ = contentLevelOutdoor.getLevel().getApi().getCompoFactory();
            AbsPanel form_ = compoFactory_.newPageBox();
            gym = compoFactory_.newCustCheckBox();
            form_.add(SubscribedTranslationList.line(contentLevelOutdoor.getLevel().getApi(),MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(contentLevelOutdoor.getLevel().getApi().currentLg())),MessagesEditorSelect.GYM_CHECK,gym));
            createBuilding = compoFactory_.newPlainButton(MessagesPkEditor.getMessagesEditorSelectButtonsTr(MessagesPkEditor.getAppliTr(contentLevelOutdoor.getLevel().getApi().currentLg())).getMapping().getVal(MessagesEditorSelect.CREATE_BUILDING));
            createBuilding.addActionListener(new BuildingKindChoiceEvent(this));
            form_.add(createBuilding);
            contentLevelOutdoor.getFore().setViewportView(form_);
            editedBuilding = null;
        }
        if (contentLevelBuilding.getFore() != null) {
            contentLevelBuilding.getFore().setNullViewportView();
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
            contentLevelOutdoor.getLevel().getForegroundEdited().put(contentLevelOutdoor.getSelected(),new int[0][]);
        }

        AbsCompoFactory compoFactory_ = contentLevelOutdoor.getLevel().getApi().getCompoFactory();
        contentLevelBuilding.setLevel(new FormLevelGrid(contentLevelOutdoor.getLevel().getApi(), contentLevelOutdoor.getLevel().getFacadeGame(), contentLevelOutdoor.getLevel().getFrame(), contentLevelOutdoor.getLevel().getTranslationList()));
        Coords coords_ = AbsContentComponentModelLevelLinks.coords(nbPlace, 0, contentLevelOutdoor.getSelected());
        IdList<SubscribedTranslation> next_ = contentLevelBuilding.setupTranslationsGrid(coords_,edited,editedBuilding.getLevel());
        contentLevelBuilding.setAccessCondition(ConverterCommonMapUtil.copyCoordsLists(contentLevelOutdoor.getAccessCondition()));
        contentLevelBuilding.getLevel().getGrid().addMouseListener(new BuildingTileKindEvent(this));
        AbsPanel form_ = compoFactory_.newLineBox();
        imageFileName = new GeneComponentModelImgSelect(contentLevelOutdoor.getLevel().getApi(), contentLevelOutdoor.getLevel().getFacadeGame(), contentLevelOutdoor.getLevel().getTranslationList().getImgRetrieverLinksSub());
        form_.add(SubscribedTranslationList.line(contentLevelOutdoor.getLevel().getApi(),MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_MINI,imageFileName.gene()));
        FormDataMap.baseSelectImage(imageFileName);
        imageFileName.getName().getSelectUniq().getSelect().addListener(new ChangeItemBuildingTileEvent(this));
        imageFileName.updateValue(editedBuilding.getImageFileName());
        if (editedBuilding instanceof Gym) {
            form_.add(gymLeader.effectFormLeader(contentLevelOutdoor.getLevel().getApi(), contentLevelOutdoor.getLevel().getFacadeGame(),contentLevelOutdoor.getLevel().getTranslationList(),contentLevelOutdoor.getLevel().getFrame(),contentLevelBuilding.getLevel()));
            gymLeader.feedFormSub(((Gym)editedBuilding).getIndoor().getGymLeader());
            gymLeader.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemBuildingTileEvent(this));
            gymLeader.getTrainerImg().getMiniFileName().updateValue(((Gym)editedBuilding).getIndoor().getGymLeader().getImageMiniFileName());
            gymLeaderCoords = ConverterCommonMapUtil.copyNullablePoint(((Gym)editedBuilding).getIndoor().getGymLeaderCoords());
            exitBuilding = ConverterCommonMapUtil.copyNullablePoint(editedBuilding.getExitCity());
        }
        if (editedBuilding instanceof PokemonCenter) {
            storage = ConverterCommonMapUtil.copyNullablePoint(((PokemonCenter)editedBuilding).getIndoor().getStorageCoords());
            exitBuilding = ConverterCommonMapUtil.copyNullablePoint(editedBuilding.getExitCity());
        }
        contentLevelOutdoor.getTranslations().addAllElts(next_);
        contentLevelBuilding.setFore(contentLevelOutdoor.getLevel().getApi().getCompoFactory().newAbsScrollPane());
        contentLevelBuilding.getLevel().getForm().add(contentLevelBuilding.getFore());
        form_.add(SubscribedTranslationList.line(contentLevelOutdoor.getLevel().getApi(),MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(contentLevelOutdoor.getLevel().getApi().currentLg())),MessagesEditorSelect.DETAIL_TILE_BUILDING,contentLevelBuilding.getLevel().getForm()));
        contentLevelOutdoor.initRemove(form_);
        contentLevelOutdoor.getMoveTile().addActionListener(new MoveBuildingTileEvent(this));
        contentLevelOutdoor.getRemoveTile().addActionListener(new RemoveBuildingTileEvent(this));
        contentLevelOutdoor.getFore().setViewportView(form_);
        contentLevelBuilding.getFore().setNullViewportView();
    }

    public void viewForegroundBuilding(int _x, int _y) {
        Point pt_ = contentLevelBuilding.viewForeground(_x, _y);
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
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectTileKindGymTr(MessagesPkEditor.getAppliTr(contentLevelOutdoor.getLevel().getApi().currentLg())).getMapping();
        choices(messages_);
    }

    private void initFormChoicesPc() {
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectTileKindPcTr(MessagesPkEditor.getAppliTr(contentLevelOutdoor.getLevel().getApi().currentLg())).getMapping();
        choices(messages_);
    }

    private void choices(StringMap<String> _messages) {
        contentLevelBuilding.choose(DataBase.EMPTY_STRING);
        AbsCompoFactory compoFactory_ = contentLevelOutdoor.getLevel().getApi().getCompoFactory();
        AbsPanel form_ = compoFactory_.newPageBox();
        contentLevelOutdoor.getTiles().clear();
        for (EntryCust<String,String> e: _messages.entryList()) {
            AbsButton but_ = compoFactory_.newPlainButton(e.getValue());
            but_.addActionListener(new TileKindBuildingChoiceEvent(this,e.getKey()));
            form_.add(but_);
            contentLevelOutdoor.getTiles().addEntry(e.getKey(),but_);
        }
        contentLevelOutdoor.getFore().setViewportView(form_);
        contentLevelBuilding.getFore().setNullViewportView();
    }

    public void choose(String _k) {
        contentLevelBuilding.choose(_k);
        if (editedBuilding instanceof Gym) {
            if (StringUtil.quickEq(_k, MessagesEditorSelect.GYM_TILE_TRAINER)) {
                AbsCompoFactory compoFactory_ = contentLevelOutdoor.getLevel().getApi().getCompoFactory();
                AbsPanel form_ = compoFactory_.newLineBox();
                form_.add(gymTrainer.effectForm(contentLevelOutdoor.getLevel().getApi(), contentLevelOutdoor.getLevel().getFacadeGame(), contentLevelOutdoor.getLevel().getTranslationList(), contentLevelOutdoor.getLevel().getFrame(),contentLevelBuilding.getLevel()));
                gymTrainer.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemBuildingTileEvent(this));
                contentLevelBuilding.initRemove(form_);
                contentLevelBuilding.getMoveTile().addActionListener(new MoveBuildingEltTileEvent(this));
                contentLevelBuilding.getRemoveTile().addActionListener(new RemoveBuildingEltTileEvent(this));
                contentLevelBuilding.getFore().setViewportView(form_);
            }
            if (StringUtil.quickEq(_k, MessagesEditorSelect.GYM_TILE_LEADER) || StringUtil.quickEq(_k, MessagesEditorSelect.GYM_TILE_EXIT)) {
                AbsCompoFactory compoFactory_ = contentLevelOutdoor.getLevel().getApi().getCompoFactory();
                AbsPanel form_ = compoFactory_.newLineBox();
                contentLevelBuilding.initRemove(form_);
                contentLevelBuilding.getMoveTile().addActionListener(new MoveBuildingEltTileEvent(this));
                contentLevelBuilding.getRemoveTile().addActionListener(new RemoveBuildingEltTileEvent(this));
                contentLevelBuilding.getFore().setViewportView(form_);
            }
        }
        if (editedBuilding instanceof PokemonCenter) {
            if (StringUtil.quickEq(_k, MessagesEditorSelect.PC_TILE_GERANT)) {
                AbsCompoFactory compoFactory_ = contentLevelOutdoor.getLevel().getApi().getCompoFactory();
                AbsPanel form_ = compoFactory_.newLineBox();
                form_.add(gerant.effectForm(contentLevelOutdoor.getLevel().getApi(), contentLevelOutdoor.getLevel().getFacadeGame(), contentLevelOutdoor.getLevel().getTranslationList(), contentLevelBuilding.getLevel()));
                gerant.getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemBuildingTileEvent(this));
                contentLevelBuilding.initRemove(form_);
                contentLevelBuilding.getMoveTile().addActionListener(new MoveBuildingEltTileEvent(this));
                contentLevelBuilding.getRemoveTile().addActionListener(new RemoveBuildingEltTileEvent(this));
                contentLevelBuilding.getFore().setViewportView(form_);
            }
            if (StringUtil.quickEq(_k, MessagesEditorSelect.PC_TILE_SELLER)) {
                AbsCompoFactory compoFactory_ = contentLevelOutdoor.getLevel().getApi().getCompoFactory();
                AbsPanel form_ = compoFactory_.newLineBox();
                form_.add(seller.effectForm(contentLevelOutdoor.getLevel().getApi(), contentLevelOutdoor.getLevel().getFacadeGame(), contentLevelOutdoor.getLevel().getTranslationList(), contentLevelBuilding.getLevel()));
                seller.getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemBuildingTileEvent(this));
                contentLevelBuilding.initRemove(form_);
                contentLevelBuilding.getMoveTile().addActionListener(new MoveBuildingEltTileEvent(this));
                contentLevelBuilding.getRemoveTile().addActionListener(new RemoveBuildingEltTileEvent(this));
                contentLevelBuilding.getFore().setViewportView(form_);
            }
            if (StringUtil.quickEq(_k, MessagesEditorSelect.PC_TILE_STORAGE) || StringUtil.quickEq(_k, MessagesEditorSelect.PC_TILE_EXIT)) {
                AbsCompoFactory compoFactory_ = contentLevelOutdoor.getLevel().getApi().getCompoFactory();
                AbsPanel form_ = compoFactory_.newLineBox();
                contentLevelBuilding.initRemove(form_);
                contentLevelBuilding.getMoveTile().addActionListener(new MoveBuildingEltTileEvent(this));
                contentLevelBuilding.getRemoveTile().addActionListener(new RemoveBuildingEltTileEvent(this));
                contentLevelBuilding.getFore().setViewportView(form_);
            }
        }
    }

    public void events() {
        eventsGym();
        eventsPc();
    }

    private void eventsPc() {
        if (editedBuilding instanceof PokemonCenter) {
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_GERANT)) {
                gerant.getMiniFileName().getName().getSelectUniq().getSelect().events(null);
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_SELLER)) {
                seller.getMiniFileName().getName().getSelectUniq().getSelect().events(null);
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_EXIT)) {
                imageFileName.getName().getSelectUniq().getSelect().events(null);
            }
        }
    }

    private void eventsGym() {
        if (editedBuilding instanceof Gym) {
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_TRAINER)) {
                gymTrainer.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_LEADER)) {
                gymLeader.getTrainerImg().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_EXIT)) {
                imageFileName.getName().getSelectUniq().getSelect().events(null);
            }
        }
    }

    public void applyTile() {
        if (editedBuilding instanceof Gym) {
            ((Gym)editedBuilding).getIndoor().setGymLeader(gymLeader.buildEntity());
            editedBuilding.getLevel().setBlocks(contentLevelBuilding.getLevel().getEdited());
            editedBuildings.put(contentLevelOutdoor.getSelected(),editedBuilding);
        }
        if (editedBuilding instanceof PokemonCenter) {
            editedBuilding.getLevel().setBlocks(contentLevelBuilding.getLevel().getEdited());
            editedBuildings.put(contentLevelOutdoor.getSelected(),editedBuilding);
        }
        contentLevelOutdoor.setAccessCondition(contentLevelBuilding.getAccessCondition());
        contentLevelOutdoor.getLevel().getForeground().put(contentLevelOutdoor.getSelected(),new int[0][]);
        contentLevelOutdoor.getLevel().getForegroundEdited().removeKey(contentLevelOutdoor.getSelected());
        contentLevelOutdoor.getLevel().refreshImg(contentLevelOutdoor.getLevel().getFormBlockTile().getEdited().getWidth(), contentLevelOutdoor.getLevel().getFormBlockTile().getEdited().getHeight());
    }

    public void removeTile() {
        editedBuildings.removeKey(contentLevelOutdoor.getSelected());
        Coords prev_ = new Coords(contentLevelOutdoor.getLevel().getSelectedPlace());
        prev_.setInsideBuilding(contentLevelOutdoor.getSelected());
        if (editedBuilding instanceof Gym) {
            removeValues(((Gym)editedBuilding).getIndoor().getGymLeaderCoords(), prev_);
        }
        contentLevelOutdoor.setAccessCondition(contentLevelBuilding.getAccessCondition());
        contentLevelOutdoor.getLevel().getForeground().removeKey(contentLevelOutdoor.getSelected());
        contentLevelOutdoor.getLevel().getForegroundEdited().removeKey(contentLevelOutdoor.getSelected());
        contentLevelOutdoor.removeTile();
        editedBuilding = null;
        contentLevelBuilding.getFore().setNullViewportView();
    }

    public void moveTile() {
        editedBuildings.move(contentLevelOutdoor.getSelected(),contentLevelOutdoor.nextPoint());
        if (editedBuilding instanceof Gym) {
            Coords prev_ = new Coords(contentLevelOutdoor.getLevel().getSelectedPlace());
            prev_.setInsideBuilding(contentLevelOutdoor.getSelected());
            updatePt(((Gym)editedBuilding).getIndoor().getGymLeaderCoords(),prev_);
            Coords next_ = new Coords(prev_);
            next_.setInsideBuilding(contentLevelOutdoor.nextPoint());
            replaceValues(((Gym)editedBuilding).getIndoor().getGymLeaderCoords(),prev_,next_);
        }
        contentLevelOutdoor.setAccessCondition(contentLevelBuilding.getAccessCondition());
        contentLevelOutdoor.getLevel().getForeground().put(contentLevelOutdoor.nextPoint(),contentLevelOutdoor.getLevel().getForegroundEdited().getVal(contentLevelOutdoor.nextPoint()));
        contentLevelOutdoor.getLevel().getForeground().removeKey(contentLevelOutdoor.getSelected());
        contentLevelOutdoor.removeFore();
        contentLevelOutdoor.applyTile();
        editedBuilding = null;
        contentLevelBuilding.getFore().setNullViewportView();
    }

    public void applySelectItem() {
        applySelectItemGym();
        applySelectItemPc();
        contentLevelBuilding.applySelectItem();
    }

    private void applySelectItemPc() {
        if (editedBuilding instanceof PokemonCenter) {
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_GERANT)) {
                ContentComponentModelLevelWithWild.trySet(contentLevelOutdoor.getLevel().getFacadeGame().getData().getLinks().getVal(gerant.getMiniFileName().getName().tryRet()),contentLevelBuilding.getLevel().getForegroundEdited(),contentLevelBuilding.getSelected());
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_SELLER)) {
                ContentComponentModelLevelWithWild.trySet(contentLevelOutdoor.getLevel().getFacadeGame().getData().getLinks().getVal(seller.getMiniFileName().getName().tryRet()),contentLevelBuilding.getLevel().getForegroundEdited(),contentLevelBuilding.getSelected());
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_EXIT)) {
                ContentComponentModelLevelWithWild.trySet(contentLevelOutdoor.getLevel().getFacadeGame().getData().getLinks().getVal(imageFileName.getName().tryRet()),contentLevelBuilding.getLevel().getForegroundEdited(),contentLevelBuilding.getSelected());
            }
        }
    }

    private void applySelectItemGym() {
        if (editedBuilding instanceof Gym) {
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_TRAINER)) {
                ContentComponentModelLevelWithWild.trySet(contentLevelOutdoor.getLevel().getFacadeGame().getData().getPeople().getVal(gymTrainer.getTrainerImg().getMiniFileName().getName().tryRet()), contentLevelBuilding.getLevel().getForegroundEdited(), contentLevelBuilding.getSelected());
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_LEADER)) {
                ContentComponentModelLevelWithWild.trySet(contentLevelOutdoor.getLevel().getFacadeGame().getData().getPeople().getVal(gymLeader.getTrainerImg().getMiniFileName().getName().tryRet()), contentLevelBuilding.getLevel().getForegroundEdited(), contentLevelBuilding.getSelected());
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_EXIT)) {
                ContentComponentModelLevelWithWild.trySet(contentLevelOutdoor.getLevel().getFacadeGame().getData().getLinks().getVal(imageFileName.getName().tryRet()), contentLevelBuilding.getLevel().getForegroundEdited(), contentLevelBuilding.getSelected());
            }
        }
    }

    public void applyBuildingTile() {
        applyBuildingTileGym();
        applyBuildingTilePc();
        contentLevelBuilding.getLevel().refreshImg(contentLevelBuilding.getLevel().getFormBlockTile().getEdited().getWidth(), contentLevelBuilding.getLevel().getFormBlockTile().getEdited().getHeight());
    }

    private void applyBuildingTilePc() {
        if (editedBuilding instanceof PokemonCenter) {
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_GERANT)) {
                ((PokemonCenter)editedBuilding).getIndoor().getGerants().put(contentLevelBuilding.getSelected(),gerant.buildEntity());
                validate();
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_SELLER)) {
                ((PokemonCenter)editedBuilding).getIndoor().getGerants().put(contentLevelBuilding.getSelected(),seller.buildEntity());
                validate();
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_EXIT)) {
                exitBuilding.setPoint(contentLevelBuilding.getSelected());
                editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
                validate();
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_STORAGE)) {
                storage.setPoint(contentLevelBuilding.getSelected());
                ((PokemonCenter)editedBuilding).getIndoor().setStorageCoords(ConverterCommonMapUtil.copyNullablePoint(storage));
                validate();
            }
            contentLevelBuilding.choose(DataBase.EMPTY_STRING);
        }
    }

    private void applyBuildingTileGym() {
        if (editedBuilding instanceof Gym) {
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_TRAINER)) {
                ((Gym)editedBuilding).getIndoor().getGymTrainers().put(contentLevelBuilding.getSelected(),gymTrainer.buildEntity());
                validate();
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_LEADER)) {
                NullablePoint prevCoords_ = ConverterCommonMapUtil.copyNullablePoint(gymLeaderCoords);
                gymLeaderCoords.setPoint(contentLevelBuilding.getSelected());
                ((Gym)editedBuilding).getIndoor().setGymLeaderCoords(ConverterCommonMapUtil.copyNullablePoint(gymLeaderCoords));
                replaceValues(prevCoords_, contentLevelBuilding.getSelected());
                validate();
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_EXIT)) {
                exitBuilding.setPoint(contentLevelBuilding.getSelected());
                editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
                validate();
            }
            contentLevelBuilding.choose(DataBase.EMPTY_STRING);
        }
    }

    public void removeBuildingTile() {
        removeBuildingTileGym();
        removeBuildingTilePc();
        contentLevelBuilding.removeTile();
    }

    private void removeBuildingTilePc() {
        if (editedBuilding instanceof PokemonCenter) {
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_GERANT) || StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_SELLER)) {
                ((PokemonCenter)editedBuilding).getIndoor().getGerants().removeKey(contentLevelBuilding.getSelected());
                removeFore();
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_EXIT)) {
                exitBuilding.setPoint(null);
                editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
                removeFore();
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.PC_TILE_STORAGE)) {
                storage.setPoint(null);
                ((PokemonCenter)editedBuilding).getIndoor().setStorageCoords(ConverterCommonMapUtil.copyNullablePoint(storage));
                removeFore();
            }
            contentLevelBuilding.choose(DataBase.EMPTY_STRING);
            initFormChoicesPc();
        }
    }

    private void removeBuildingTileGym() {
        if (editedBuilding instanceof Gym) {
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_TRAINER)) {
                ((Gym)editedBuilding).getIndoor().getGymTrainers().removeKey(contentLevelBuilding.getSelected());
                removeFore();
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_LEADER)) {
                NullablePoint prevCoords_ = ConverterCommonMapUtil.copyNullablePoint(gymLeaderCoords);
                gymLeaderCoords.setPoint(null);
                ((Gym)editedBuilding).getIndoor().setGymLeaderCoords(ConverterCommonMapUtil.copyNullablePoint(gymLeaderCoords));
                Coords prev_ = new Coords(contentLevelBuilding.getLevel().getSelectedPlace());
                removeValues(prevCoords_, prev_);
                removeFore();
            }
            if (StringUtil.quickEq(contentLevelBuilding.getKey(), MessagesEditorSelect.GYM_TILE_EXIT)) {
                exitBuilding.setPoint(null);
                editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
                removeFore();
            }
            contentLevelBuilding.choose(DataBase.EMPTY_STRING);
            initFormChoicesGym();
        }
    }

    private void removeValues(NullablePoint _pt, Coords _prev) {
        if (_pt.isDefined()) {
            _prev.getLevel().setPoint(_pt.getPoint());
            ConverterCommonMapUtil.removeValues(contentLevelBuilding.getAccessCondition(), _prev);
        }
    }

    public void moveBuildingTile() {
        if (editedBuilding instanceof PokemonCenter) {
            ((PokemonCenter)editedBuilding).getIndoor().getGerants().move(contentLevelBuilding.getSelected(),contentLevelBuilding.nextPoint());
            AbsMoveForeTileEvent.moveNullablePoint(exitBuilding,contentLevelBuilding.getSelected(),contentLevelBuilding.nextPoint());
            AbsMoveForeTileEvent.moveNullablePoint(storage,contentLevelBuilding.getSelected(),contentLevelBuilding.nextPoint());
            editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
            ((PokemonCenter)editedBuilding).getIndoor().setStorageCoords(ConverterCommonMapUtil.copyNullablePoint(storage));
        }
        if (editedBuilding instanceof Gym) {
            NullablePoint prevCoords_ = ConverterCommonMapUtil.copyNullablePoint(gymLeaderCoords);
            ((Gym)editedBuilding).getIndoor().getGymTrainers().move(contentLevelBuilding.getSelected(),contentLevelBuilding.nextPoint());
            AbsMoveForeTileEvent.moveNullablePoint(exitBuilding,contentLevelBuilding.getSelected(),contentLevelBuilding.nextPoint());
            AbsMoveForeTileEvent.moveNullablePoint(gymLeaderCoords,contentLevelBuilding.getSelected(),contentLevelBuilding.nextPoint());
            editedBuilding.setExitCity(ConverterCommonMapUtil.copyNullablePoint(exitBuilding));
            ((Gym)editedBuilding).getIndoor().setGymLeaderCoords(ConverterCommonMapUtil.copyNullablePoint(gymLeaderCoords));
            replaceValues(prevCoords_, contentLevelBuilding.nextPoint());
        }
        contentLevelBuilding.getLevel().getForeground().put(contentLevelBuilding.nextPoint(),contentLevelBuilding.getLevel().getForegroundEdited().getVal(contentLevelBuilding.nextPoint()));
        contentLevelBuilding.removeFore();
        contentLevelBuilding.applyTile();
        if (editedBuilding instanceof Gym) {
            contentLevelBuilding.choose(DataBase.EMPTY_STRING);
            initFormChoicesGym();
        }
        if (editedBuilding instanceof PokemonCenter) {
            contentLevelBuilding.choose(DataBase.EMPTY_STRING);
            initFormChoicesPc();
        }
    }

    private void replaceValues(NullablePoint _r, Point _n) {
        Coords prev_ = new Coords(contentLevelBuilding.getLevel().getSelectedPlace());
        updatePt(_r,prev_);
        Coords next_ = new Coords(prev_);
        next_.getLevel().setPoint(_n);
        replaceValues(_r, prev_, next_);
    }

    private void replaceValues(NullablePoint _r, Coords _prev, Coords _next) {
        if (_r.isDefined()) {
            ConverterCommonMapUtil.replaceValues(contentLevelBuilding.getAccessCondition(), _prev, _next);
        }
    }

    private void updatePt(NullablePoint _r, Coords _prev) {
        if (_r.isDefined()) {
            _prev.getLevel().setPoint(_r.getPoint());
        }
    }

    private void removeFore() {
        contentLevelBuilding.removeFore();
    }
    private void validate() {
        contentLevelBuilding.validate();
    }
    public AbsButton getMoveTile() {
        return contentLevelOutdoor.getMoveTile();
    }
    public AbsButton getRemoveTile() {
        return contentLevelOutdoor.getRemoveTile();
    }

    public AbsCustCheckBox getGym() {
        return gym;
    }

    public ContentComponentModelLevel getContentLevelOutdoor() {
        return contentLevelOutdoor;
    }

    public ContentComponentModelLevel getContentLevelBuilding() {
        return contentLevelBuilding;
    }

    public FormLevelGrid getLevel() {
        return contentLevelOutdoor.getLevel();
    }

    public FormLevelGrid getLevelBuilding() {
        return contentLevelBuilding.getLevel();
    }

    public AbsButton getCreateBuilding() {
        return createBuilding;
    }

    public AbsButton getMoveTileBuilding() {
        return contentLevelBuilding.getMoveTile();
    }

    public AbsButton getRemoveTileBuilding() {
        return contentLevelBuilding.getRemoveTile();
    }

    public StringMap<AbsButton> getTiles() {
        return contentLevelOutdoor.getTiles();
    }
}
