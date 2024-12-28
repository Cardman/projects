package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.map.characters.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelLevelWithWild {
    private GeneComponentModelEltEnumSub<String> items;
    private GeneComponentModelEltEnumSub<Short> hm;
    private GeneComponentModelEltEnumSub<Short> tm;
    private final ContentComponentModelDealerItem dealerItem = new ContentComponentModelDealerItem();
    private final ContentComponentModelTrainerMultiFights trainerMultiFights = new ContentComponentModelTrainerMultiFights();
    private final ContentComponentModelDualFight dualFight = new ContentComponentModelDualFight();
    private FormWildPk legendaryPks;
    private final StringMap<AbsButton> tiles = new StringMap<AbsButton>();
    private FormLevelGrid level;
    private CrudGeneFormAbsAreaApparition areas;
    private AbsCustComponent splitter;
    private AbsScrollPane fore;
    private LevelWithWildPokemon edited;
    private final IdList<SubscribedTranslation> translations = new IdList<SubscribedTranslation>();
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
        areas = new CrudGeneFormAbsAreaApparition(_core, _fac, _fact, _f);
        areas.initForm(_core,new GeneComponentModelSubscribeFactoryDirect<AbsAreaApparition>(new GeneComponentModelSubscribeArea(_f, _core, _fac, _fact)));
        form_.add(areas.getGroup());
        getAreas().setFormBlockTile(level.getFormBlockTile());
        splitter = _core.getCompoFactory().newHorizontalSplitPane(scrollPane,_core.getCompoFactory().newAbsScrollPane(form_));
        level.getTranslationList().setFormLevelGridUniq(null);
        return splitter;
    }
    public void setupGridDims(Coords _coords, Place _pl, LevelWithWildPokemon _wild) {
        getAreas().setupValues(_wild.getWildPokemonAreas());
        Points<Block> blocks_ = _wild.getBlocks();
        edited = _wild;
        nbPlace = _coords.getNumberPlace();
        nbLevel = _coords.getLevel().getLevelIndex();
        Points<int[][]> frontTiles_ = Level.getLevelForegroundImage(level.getFacadeGame().getData(), _coords, _pl,_wild);
        level.setupGridDims(blocks_, frontTiles_);
        IdList<SubscribedTranslation> subs_ = level.getTranslationList().getSubscribedTranslations().getVal(level.getFrame());
        subs_.removeAllElements(translationsGrid);
        IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>();
        next_.add(new RefreshGridSubscription(level.getFacadeGame(),level,_coords,_pl,_wild));
        subs_.addAllElts(next_);
        translationsGrid.addAllElts(next_);
        fore = level.getApi().getCompoFactory().newAbsScrollPane();
        level.getForm().add(fore);
        level.getGrid().addMouseListener(new TileKindEvent(this));
        scrollPane.setViewportView(level.getForm());
        getAreas().setBlocks(blocks_);
    }
    public void buildEntity(LevelWithWildPokemon _lev) {
        _lev.setBlocks(getLevel().getEdited());
        _lev.setWildPokemonAreas(getAreas().getList());
        _lev.setItems(edited.getItems());
        _lev.setTm(edited.getTm());
        _lev.setHm(edited.getHm());
        _lev.setLegendaryPks(edited.getLegendaryPks());
        _lev.setDualFights(edited.getDualFights());
        _lev.setCharacters(edited.getCharacters());
    }

    public AbsCustComponent getSplitter() {
        return splitter;
    }

    public void viewForeground(int _x, int _y) {
        Point pt_ = level.toPt(_x, _y);
        selected = pt_;
        getLevel().getFormBlockTile().getMatch().addActionListener(new ApplyForeTileEvent(this));
        if (edited.getItems().contains(pt_)) {
            choose(MessagesEditorSelect.TILE_ITEMS);
            items.setupValue(edited.getItems().getVal(pt_));
        } else if (edited.getTm().contains(pt_)) {
            choose(MessagesEditorSelect.TILE_TM);
            tm.setupValue(edited.getTm().getVal(pt_));
        } else if (edited.getHm().contains(pt_)) {
            choose(MessagesEditorSelect.TILE_HM);
            hm.setupValue(edited.getHm().getVal(pt_));
        } else if (edited.getLegendaryPks().contains(pt_)) {
            choose(MessagesEditorSelect.TILE_LEG_PK);
            legendaryPks.feedForm(edited.getLegendaryPks().getVal(pt_));
        } else if (edited.getCharacters().contains(pt_)) {
            CharacterInRoadCave ch_ = edited.getCharacters().getVal(pt_);
            if (ch_ instanceof TrainerMultiFights) {
                choose(MessagesEditorSelect.TILE_TRAINER);
                trainerMultiFights.feedForm((TrainerMultiFights) ch_);
            } else {
                choose(MessagesEditorSelect.TILE_DEALER);
                dealerItem.feedForm((DealerItem) ch_);
            }
        } else if (edited.getDualFights().contains(pt_)) {
            choose(MessagesEditorSelect.TILE_DUAL);
            dualFight.feedForm(edited.getDualFights().getVal(pt_));
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
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectTileKindWildTr(MessagesPkEditor.getAppliTr(level.getApi().currentLg())).getMapping();
        AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
        AbsPanel form_ = compoFactory_.newPageBox();
        tiles.clear();
        for (EntryCust<String,String> e: messages_.entryList()) {
            AbsButton but_ = compoFactory_.newPlainButton(e.getValue());
            but_.addActionListener(new TileKindChoiceEvent(this,e.getKey()));
            form_.add(but_);
            tiles.addEntry(e.getKey(),but_);
        }
        fore.setViewportView(form_);
    }

    public void choose(String _k) {
        key = _k;
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_ITEMS)) {
            AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
            items = ConverterCommonMapUtil.buildItFull(level.getApi(), level.getFacadeGame(), level.getTranslationList());
            AbsPanel form_ = compoFactory_.newLineBox();
            form_.add(items.geneEnum());
            IdList<SubscribedTranslation> subs_ = level.getTranslationList().getSubscribedTranslations().getVal(level.getFrame());
            subs_.removeAllElements(translations);
            IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>(items.getSubs());
            next_.add(new SubscribedTranslationSelectChangeEvtsText<String>(items.getSelectUniq()));
            subs_.addAllElts(next_);
            translations.addAllElts(next_);
            items.getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
            removeTile = compoFactory_.newPlainButton("-");
            removeTile.addActionListener(new RemoveForeTileEvent(this));
            form_.add(removeTile);
            fore.setViewportView(form_);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_TM)) {
            tm = ConverterCommonMapUtil.buildTm(level.getApi(), level.getFacadeGame(), level.getTranslationList());
            techHidden(tm);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_HM)) {
            hm = ConverterCommonMapUtil.buildHm(level.getApi(), level.getFacadeGame(), level.getTranslationList());
            techHidden(hm);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_LEG_PK)) {
            AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
            legendaryPks = new FormWildPk(level.getApi(), level.getFacadeGame(), level.getTranslationList(), level.getFrame());
            legendaryPks.feedForm();
            AbsPanel form_ = compoFactory_.newLineBox();
            form_.add(legendaryPks.getForm());
            IdList<SubscribedTranslation> subs_ = level.getTranslationList().getSubscribedTranslations().getVal(level.getFrame());
            subs_.removeAllElements(translations);
            IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>();
            legendaryPks.feedSubs(next_);
            subs_.addAllElts(next_);
            translations.addAllElts(next_);
            legendaryPks.getName().getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
            removeTile = compoFactory_.newPlainButton("-");
            removeTile.addActionListener(new RemoveForeTileEvent(this));
            form_.add(removeTile);
            fore.setViewportView(form_);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_TRAINER)) {
            AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
            AbsPanel form_ = compoFactory_.newLineBox();
            form_.add(trainerMultiFights.effectForm(level.getApi(), level.getFacadeGame(), level.getTranslationList(), level.getFrame(),level));
            trainerMultiFights.getTrainer().getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
            removeTile = compoFactory_.newPlainButton("-");
            removeTile.addActionListener(new RemoveForeTileEvent(this));
            form_.add(removeTile);
            fore.setViewportView(form_);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_DEALER)) {
            AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
            AbsPanel form_ = compoFactory_.newLineBox();
            form_.add(dealerItem.effectForm(level.getApi(), level.getFacadeGame(), level.getTranslationList(), level));
            dealerItem.getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
            removeTile = compoFactory_.newPlainButton("-");
            removeTile.addActionListener(new RemoveForeTileEvent(this));
            form_.add(removeTile);
            fore.setViewportView(form_);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_DUAL)) {
            AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
            AbsPanel form_ = compoFactory_.newLineBox();
            form_.add(dualFight.effectForm(level.getApi(), level.getFacadeGame(), level.getTranslationList(), level.getFrame(),level));
            dualFight.getTrainer().getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
            removeTile = compoFactory_.newPlainButton("-");
            removeTile.addActionListener(new RemoveForeTileEvent(this));
            form_.add(removeTile);
            fore.setViewportView(form_);
        }
    }

    private void techHidden(GeneComponentModelEltEnumSub<Short> _sel) {
        AbsCompoFactory compoFactory_ = level.getApi().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(_sel.geneEnum());
        IdList<SubscribedTranslation> subs_ = level.getTranslationList().getSubscribedTranslations().getVal(level.getFrame());
        subs_.removeAllElements(translations);
        IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>(_sel.getSubs());
        next_.add(new SubscribedTranslationSelectChangeEvtsText<Short>(_sel.getSelectUniq()));
        subs_.addAllElts(next_);
        translations.addAllElts(next_);
        _sel.getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
        removeTile = compoFactory_.newPlainButton("-");
        removeTile.addActionListener(new RemoveForeTileEvent(this));
        form_.add(removeTile);
        fore.setViewportView(form_);
    }

    public void events() {
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_ITEMS)) {
            items.getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_TM)) {
            tm.getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_HM)) {
            hm.getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_LEG_PK)) {
            legendaryPks.getName().getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_TRAINER)) {
            trainerMultiFights.getTrainer().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_DEALER)) {
            dealerItem.getMiniFileName().getName().getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_DUAL)) {
            dualFight.getTrainer().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
        }
    }
    public void applySelectItem() {
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_ITEMS)) {
            trySet(level.getFacadeGame().getData().getMiniItems().getVal(items.tryRet()), level.getForegroundEdited(), selected);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_TM) || StringUtil.quickEq(key, MessagesEditorSelect.TILE_HM)) {
            trySet(level.getFacadeGame().getData().getImageTmHm(), level.getForegroundEdited(), selected);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_LEG_PK)) {
            trySet(level.getFacadeGame().getData().getMiniPk().getVal(legendaryPks.getName().tryRet()), level.getForegroundEdited(), selected);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_TRAINER)) {
            trySet(level.getFacadeGame().getData().getPeople().getVal(trainerMultiFights.getTrainer().getMiniFileName().getName().tryRet()), level.getForegroundEdited(), selected);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_DEALER)) {
            trySet(level.getFacadeGame().getData().getPeople().getVal(dealerItem.getMiniFileName().getName().tryRet()), level.getForegroundEdited(), selected);
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_DUAL)) {
            trySet(level.getFacadeGame().getData().getPeople().getVal(dualFight.getTrainer().getMiniFileName().getName().tryRet()), level.getForegroundEdited(), selected);
            trySet(level.getFacadeGame().getData().getPeople().getVal(dualFight.getTrainer().getMiniFileName().getName().tryRet()), dualFight.getSecondPt().getForegroundEdited(), selected);
            dualFight.getSecondPt().refreshImg();
        }
        level.refreshImg(level.getFormBlockTile().getEdited().getWidth(), level.getFormBlockTile().getEdited().getHeight());
    }

    public static void trySet(ImageArrayBaseSixtyFour _img, Points<int[][]> _level, Point _selected) {
        if (_img == null) {
            _level.put(_selected,new int[0][]);
        } else {
            _level.put(_selected, _img.getImage());
        }
    }

    public void applyTile() {
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_ITEMS)) {
            edited.getItems().put(selected,items.tryRet());
            validate();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_TM)) {
            edited.getTm().put(selected,tm.tryRet());
            validate();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_HM)) {
            edited.getHm().put(selected,hm.tryRet());
            validate();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_LEG_PK)) {
            edited.getLegendaryPks().put(selected,legendaryPks.buildEntity());
            validate();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_TRAINER)) {
            edited.getCharacters().put(selected,trainerMultiFights.buildEntity());
            validate();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_DEALER)) {
            edited.getCharacters().put(selected,dealerItem.buildEntity());
            validate();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_DUAL)) {
            DualFight e_ = dualFight.buildEntity();
            edited.getDualFights().put(selected, e_);
            validate();
            NullablePoint sec_ = e_.getPt();
            if (sec_.isDefined()) {
                int[][] val_ = dualFight.getSecondPt().getForegroundEdited().getVal(sec_.getPoint());
                level.getForeground().put(sec_.getPoint(), val_);
            }
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
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_ITEMS)) {
            edited.getItems().removeKey(selected);
            removeFore();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_TM)) {
            edited.getTm().removeKey(selected);
            removeFore();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_HM)) {
            edited.getHm().removeKey(selected);
            removeFore();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_LEG_PK)) {
            edited.getLegendaryPks().removeKey(selected);
            removeFore();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_TRAINER) || StringUtil.quickEq(key, MessagesEditorSelect.TILE_DEALER)) {
            edited.getCharacters().removeKey(selected);
            removeFore();
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_DUAL)) {
            edited.getDualFights().removeKey(selected);
            removeFore();
        }
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

    public LevelWithWildPokemon getEdited() {
        return edited;
    }

    public GeneComponentModelEltEnumSub<String> getItems() {
        return items;
    }

    public FormWildPk getLegendaryPks() {
        return legendaryPks;
    }

    public GeneComponentModelEltEnumSub<Short> getTm() {
        return tm;
    }

    public GeneComponentModelEltEnumSub<Short> getHm() {
        return hm;
    }

    public CrudGeneFormAbsAreaApparition getAreas() {
        return areas;
    }

    public ContentComponentModelDealerItem getDealerItem() {
        return dealerItem;
    }

    public ContentComponentModelTrainerMultiFights getTrainerMultiFights() {
        return trainerMultiFights;
    }

    public ContentComponentModelDualFight getDualFight() {
        return dualFight;
    }
}
