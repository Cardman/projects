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
    private final ContentComponentModelLevel contentLevel = new ContentComponentModelLevel();
    private FormWildPk legendaryPks;
    private CrudGeneFormAbsAreaApparition areas;
    private AbsCustComponent splitter;
    private LevelWithWildPokemon edited;
    private int nbPlace;
    private int nbLevel;
    private AbsScrollPane scrollPane;

    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        scrollPane = _core.getCompoFactory().newAbsScrollPane();
        contentLevel.setLevel(new FormLevelGrid(_core, _fac, _f, _fact));
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        areas = new CrudGeneFormAbsAreaApparition(_core, _fac, _fact, _f);
        areas.initForm(_core,new GeneComponentModelSubscribeFactoryDirect<AbsAreaApparition>(new GeneComponentModelSubscribeArea(_f, _core, _fac, _fact)));
        form_.add(areas.getGroup());
        getAreas().setFormBlockTile(contentLevel.getLevel().getFormBlockTile());
        splitter = _core.getCompoFactory().newHorizontalSplitPane(scrollPane,_core.getCompoFactory().newAbsScrollPane(form_));
        contentLevel.getLevel().getTranslationList().setFormLevelGridUniq(null);
        return splitter;
    }
    public void setupGridDims(Coords _coords, Place _pl, LevelWithWildPokemon _wild) {
        getAreas().setupValues(_wild.getWildPokemonAreas());
        edited = _wild;
        nbPlace = _coords.getNumberPlace();
        nbLevel = _coords.getLevel().getLevelIndex();
        contentLevel.setupGridDims(_coords, _pl, _wild);
        contentLevel.getLevel().getGrid().addMouseListener(new TileKindEvent(this));
        scrollPane.setViewportView(contentLevel.getLevel().getForm());
        getAreas().setBlocks(contentLevel.getLevel().getEdited());
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
        Point pt_ = contentLevel.viewForeground(_x, _y);
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
        } else if (contentLevel.getLevel().getFacadeGame().getMap().isEmptyForAdding(AbsContentComponentModelLevelLinks.coords(nbPlace,nbLevel,null,pt_))){
            initFormChoices();
        } else {
            contentLevel.choose("");
            contentLevel.getFore().setNullViewportView();
        }
        getLevel().getFrame().pack();
    }

    private void initFormChoices() {
        contentLevel.choose("");
        StringMap<String> messages_ = MessagesPkEditor.getMessagesEditorSelectTileKindWildTr(MessagesPkEditor.getAppliTr(contentLevel.getLevel().getApi().currentLg())).getMapping();
        AbsCompoFactory compoFactory_ = contentLevel.getLevel().getApi().getCompoFactory();
        AbsPanel form_ = compoFactory_.newPageBox();
        contentLevel.getTiles().clear();
        for (EntryCust<String,String> e: messages_.entryList()) {
            AbsButton but_ = compoFactory_.newPlainButton(e.getValue());
            but_.addActionListener(new TileKindChoiceEvent(this,e.getKey()));
            form_.add(but_);
            contentLevel.getTiles().addEntry(e.getKey(),but_);
        }
        contentLevel.getFore().setViewportView(form_);
    }

    public void choose(String _k) {
        contentLevel.choose(_k);
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_ITEMS)) {
            AbsCompoFactory compoFactory_ = contentLevel.getLevel().getApi().getCompoFactory();
            items = ConverterCommonMapUtil.buildItFull(contentLevel.getLevel().getApi(), contentLevel.getLevel().getFacadeGame(), contentLevel.getLevel().getTranslationList());
            AbsPanel form_ = compoFactory_.newLineBox();
            form_.add(items.geneEnum());
            contentLevel.refreshSubs(items.getSubs(),items.getSelectUniq().getSelect());
            items.getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
            initRemove(form_);
            contentLevel.getFore().setViewportView(form_);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_TM)) {
            tm = ConverterCommonMapUtil.buildTm(contentLevel.getLevel().getApi(), contentLevel.getLevel().getFacadeGame(), contentLevel.getLevel().getTranslationList());
            techHidden(tm);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_HM)) {
            hm = ConverterCommonMapUtil.buildHm(contentLevel.getLevel().getApi(), contentLevel.getLevel().getFacadeGame(), contentLevel.getLevel().getTranslationList());
            techHidden(hm);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_LEG_PK)) {
            AbsCompoFactory compoFactory_ = contentLevel.getLevel().getApi().getCompoFactory();
            legendaryPks = new FormWildPk(contentLevel.getLevel().getApi(), contentLevel.getLevel().getFacadeGame(), contentLevel.getLevel().getTranslationList(), contentLevel.getLevel().getFrame());
            legendaryPks.feedForm();
            AbsPanel form_ = compoFactory_.newLineBox();
            form_.add(legendaryPks.getForm());
            IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>();
            legendaryPks.feedSubs(next_);
            contentLevel.refreshSubs(next_,legendaryPks.getName().getSelectUniq().getSelect());
            legendaryPks.getName().getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
            initRemove(form_);
            contentLevel.getFore().setViewportView(form_);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_TRAINER)) {
            AbsCompoFactory compoFactory_ = contentLevel.getLevel().getApi().getCompoFactory();
            AbsPanel form_ = compoFactory_.newLineBox();
            form_.add(trainerMultiFights.effectForm(contentLevel.getLevel().getApi(), contentLevel.getLevel().getFacadeGame(), contentLevel.getLevel().getTranslationList(), contentLevel.getLevel().getFrame(),contentLevel.getLevel()));
            trainerMultiFights.getTrainer().getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
            initRemove(form_);
            contentLevel.getFore().setViewportView(form_);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_DEALER)) {
            AbsCompoFactory compoFactory_ = contentLevel.getLevel().getApi().getCompoFactory();
            AbsPanel form_ = compoFactory_.newLineBox();
            form_.add(dealerItem.effectForm(contentLevel.getLevel().getApi(), contentLevel.getLevel().getFacadeGame(), contentLevel.getLevel().getTranslationList(), contentLevel.getLevel()));
            dealerItem.getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
            initRemove(form_);
            contentLevel.getFore().setViewportView(form_);
        }
        if (StringUtil.quickEq(_k, MessagesEditorSelect.TILE_DUAL)) {
            AbsCompoFactory compoFactory_ = contentLevel.getLevel().getApi().getCompoFactory();
            AbsPanel form_ = compoFactory_.newLineBox();
            form_.add(dualFight.effectForm(contentLevel.getLevel().getApi(), contentLevel.getLevel().getFacadeGame(), contentLevel.getLevel().getTranslationList(), contentLevel.getLevel().getFrame(),contentLevel.getLevel()));
            dualFight.getTrainer().getMiniFileName().getName().getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
            initRemove(form_);
            contentLevel.getFore().setViewportView(form_);
        }
    }

    private void techHidden(GeneComponentModelEltEnumSub<Short> _sel) {
        AbsCompoFactory compoFactory_ = contentLevel.getLevel().getApi().getCompoFactory();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(_sel.geneEnum());
        contentLevel.refreshSubs(_sel.getSubs(),_sel.getSelectUniq().getSelect());
        _sel.getSelectUniq().getSelect().addListener(new ChangeItemTileEvent(this));
        initRemove(form_);
        contentLevel.getFore().setViewportView(form_);
    }

    private void initRemove(AbsPanel _form) {
        contentLevel.initRemove(_form);
        contentLevel.getRemoveTile().addActionListener(new RemoveForeTileEvent(this));
    }

    public void events() {
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_ITEMS)) {
            items.getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_TM)) {
            tm.getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_HM)) {
            hm.getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_LEG_PK)) {
            legendaryPks.getName().getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_TRAINER)) {
            trainerMultiFights.getTrainer().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_DEALER)) {
            dealerItem.getMiniFileName().getName().getSelectUniq().getSelect().events(null);
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_DUAL)) {
            dualFight.getTrainer().getMiniFileName().getName().getSelectUniq().getSelect().events(null);
        }
    }
    public void applySelectItem() {
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_ITEMS)) {
            trySet(contentLevel.getLevel().getFacadeGame().getData().getMiniItems().getVal(items.tryRet()), contentLevel.getLevel().getForegroundEdited(), contentLevel.getSelected());
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_TM) || StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_HM)) {
            trySet(contentLevel.getLevel().getFacadeGame().getData().getImageTmHm(), contentLevel.getLevel().getForegroundEdited(), contentLevel.getSelected());
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_LEG_PK)) {
            trySet(contentLevel.getLevel().getFacadeGame().getData().getMiniPk().getVal(legendaryPks.getName().tryRet()), contentLevel.getLevel().getForegroundEdited(), contentLevel.getSelected());
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_TRAINER)) {
            trySet(contentLevel.getLevel().getFacadeGame().getData().getPeople().getVal(trainerMultiFights.getTrainer().getMiniFileName().getName().tryRet()), contentLevel.getLevel().getForegroundEdited(), contentLevel.getSelected());
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_DEALER)) {
            trySet(contentLevel.getLevel().getFacadeGame().getData().getPeople().getVal(dealerItem.getMiniFileName().getName().tryRet()), contentLevel.getLevel().getForegroundEdited(), contentLevel.getSelected());
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_DUAL)) {
            trySet(contentLevel.getLevel().getFacadeGame().getData().getPeople().getVal(dualFight.getTrainer().getMiniFileName().getName().tryRet()), contentLevel.getLevel().getForegroundEdited(), contentLevel.getSelected());
            trySet(contentLevel.getLevel().getFacadeGame().getData().getPeople().getVal(dualFight.getTrainer().getMiniFileName().getName().tryRet()), dualFight.getSecondPt().getForegroundEdited(), contentLevel.getSelected());
            dualFight.getSecondPt().refreshImg();
        }
        contentLevel.applySelectItem();
    }

    public static void trySet(ImageArrayBaseSixtyFour _img, Points<int[][]> _level, Point _selected) {
        ContentComponentModelLevel.trySet(_img, _level, _selected);
    }

    public void applyTile() {
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_ITEMS)) {
            edited.getItems().put(contentLevel.getSelected(),items.tryRet());
            validate();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_TM)) {
            edited.getTm().put(contentLevel.getSelected(),tm.tryRet());
            validate();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_HM)) {
            edited.getHm().put(contentLevel.getSelected(),hm.tryRet());
            validate();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_LEG_PK)) {
            edited.getLegendaryPks().put(contentLevel.getSelected(),legendaryPks.buildEntity());
            validate();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_TRAINER)) {
            edited.getCharacters().put(contentLevel.getSelected(),trainerMultiFights.buildEntity());
            validate();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_DEALER)) {
            edited.getCharacters().put(contentLevel.getSelected(),dealerItem.buildEntity());
            validate();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_DUAL)) {
            DualFight e_ = dualFight.buildEntity();
            edited.getDualFights().put(contentLevel.getSelected(), e_);
            validate();
            NullablePoint sec_ = e_.getPt();
            if (sec_.isDefined()) {
                int[][] val_ = dualFight.getSecondPt().getForegroundEdited().getVal(sec_.getPoint());
                contentLevel.getLevel().getForeground().put(sec_.getPoint(), val_);
            }
        }
        contentLevel.applyTile();
    }

    private void validate() {
        contentLevel.validate();
    }

    public void removeTile() {
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_ITEMS)) {
            edited.getItems().removeKey(contentLevel.getSelected());
            removeFore();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_TM)) {
            edited.getTm().removeKey(contentLevel.getSelected());
            removeFore();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_HM)) {
            edited.getHm().removeKey(contentLevel.getSelected());
            removeFore();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_LEG_PK)) {
            edited.getLegendaryPks().removeKey(contentLevel.getSelected());
            removeFore();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_TRAINER) || StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_DEALER)) {
            edited.getCharacters().removeKey(contentLevel.getSelected());
            removeFore();
        }
        if (StringUtil.quickEq(contentLevel.getKey(), MessagesEditorSelect.TILE_DUAL)) {
            edited.getDualFights().removeKey(contentLevel.getSelected());
            removeFore();
        }
        contentLevel.removeTile();
        initFormChoices();
    }

    private void removeFore() {
        contentLevel.removeFore();
    }

    public StringMap<AbsButton> getTiles() {
        return contentLevel.getTiles();
    }

    public AbsButton getRemoveTile() {
        return contentLevel.getRemoveTile();
    }

    public FormLevelGrid getLevel() {
        return contentLevel.getLevel();
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
