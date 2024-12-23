package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
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

    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        AbsScrollPane map_ = _core.getCompoFactory().newAbsScrollPane();
        level = new FormLevelGrid(_core,_fac,map_,_f,_fact);
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        areas = new CrudGeneFormAbsAreaApparition(_core, _fac, _fact, _f);
        areas.initForm(_core,new GeneComponentModelSubscribeFactoryDirect<AbsAreaApparition>(new GeneComponentModelSubscribeArea(_f, _core, _fac, _fact)));
        form_.add(areas.getGroup());
        getAreas().setFormBlockTile(level.getFormBlockTile());
        splitter = _core.getCompoFactory().newHorizontalSplitPane(map_,_core.getCompoFactory().newAbsScrollPane(form_));
        splitter.setVisible(false);
        level.getTranslationList().setFormLevelGridUniq(null);
        return splitter;
    }
    public void setupGridDims(Points<Block> _bk, short _nbPlace, byte _nbLevel, Place _pl, LevelWithWildPokemon _wild) {
        edited = _wild;
        Coords coords_ = coords(_nbPlace, _nbLevel, null);
        Points<int[][]> frontTiles_ = Level.getLevelForegroundImage(level.getFacadeGame().getData(), coords_, _pl,_wild);
        level.setupGridDims(_bk, frontTiles_);
        IdList<SubscribedTranslation> subs_ = level.getTranslationList().getSubscribedTranslations().getVal(level.getFrame());
        subs_.removeAllElements(translationsGrid);
        IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>();
        next_.add(new RefreshGridSubscription(level.getFacadeGame(),level,coords_,_pl,_wild));
        subs_.addAllElts(next_);
        translationsGrid.addAllElts(next_);
        fore = level.getApi().getCompoFactory().newAbsScrollPane();
        level.getForm().add(fore);
        level.getGrid().addMouseListener(new TileKindEvent(this));
        level.getContainer().setViewportView(level.getForm());
    }
    public void display(String _res) {
        if (StringUtil.quickEq(_res, MessagesEditorSelect.PLACE_ROAD)) {
            level.getTranslationList().setFormLevelGridUniq(level);
            splitter.setVisible(true);
        } else {
            level.getTranslationList().setFormLevelGridUniq(null);
            splitter.setVisible(false);
        }
    }
    public static Coords coords(short _nbPlace, byte _nbLevel, Point _inside) {
        Coords curCoords_ = new Coords();
        curCoords_.setNumberPlace(_nbPlace);
        curCoords_.setInsideBuilding(_inside);
        curCoords_.getLevel().setLevelIndex(_nbLevel);
        return curCoords_;
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
        } else {
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
        getLevel().getFrame().pack();
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
    }
    public void applySelectItem() {
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_ITEMS)) {
            trySet(level.getFacadeGame().getData().getMiniItems().getVal(items.tryRet()));
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_TM) || StringUtil.quickEq(key, MessagesEditorSelect.TILE_HM)) {
            trySet(level.getFacadeGame().getData().getImageTmHm());
        }
        if (StringUtil.quickEq(key, MessagesEditorSelect.TILE_LEG_PK)) {
            trySet(level.getFacadeGame().getData().getMiniPk().getVal(legendaryPks.getName().tryRet()));
        }
        level.refreshImg(level.getFormBlockTile().getEdited().getWidth(), level.getFormBlockTile().getEdited().getHeight());
    }

    private void trySet(ImageArrayBaseSixtyFour _img) {
        if (_img == null) {
            level.getForegroundEdited().put(selected,new int[0][]);
        } else {
            level.getForegroundEdited().put(selected, _img.getImage());
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
        level.refreshImg(level.getFormBlockTile().getEdited().getWidth(), level.getFormBlockTile().getEdited().getHeight());
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
}
