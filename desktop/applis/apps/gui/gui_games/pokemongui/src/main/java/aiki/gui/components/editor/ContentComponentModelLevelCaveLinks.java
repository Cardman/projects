package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.map.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelLevelCaveLinks {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int BOTH = 2;
    private GeneComponentModelImgSelect linkFileNameFirst;
    private GeneComponentModelImgSelect linkFileNameSecond;
    private FormLevelGridLink level;
    private final CustList<FormLevelGridLink> levels = new CustList<FormLevelGridLink>();
    private Cave cave;
    private final IdList<SubscribedTranslation> translations = new IdList<SubscribedTranslation>();
    private final IdList<SubscribedTranslation> translationsGrid = new IdList<SubscribedTranslation>();
    private final NullablePoint selectedFirst = new NullablePoint();
    private final NullablePoint selectedSecond = new NullablePoint();
    private AbsButton addTileLeft;
    private AbsButton addTileRight;
    private AbsButton addTileBoth;
    private AbsButton removeTileLeft;
    private AbsButton removeTileRight;
    private AbsButton removeTileBoth;
    private AbsButton matchLinkLeft;
    private AbsButton matchLinkRight;
    private AbsButton matchLinkBoth;
    private int selectedPlace;
    private int selectedLevel;
    private int selectedFirstLevel;
    private AbsButton close;

    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f, Cave _cave, int _nbPlace, int _nbLevel) {
        AbsScrollPane map_ = _core.getCompoFactory().newAbsScrollPane();
        levels.clear();
        selectedPlace = _nbPlace;
        selectedFirstLevel = _nbLevel;
        cave = _cave;
        level = build(_core, _fac, _fact, _f, _cave, _nbPlace, _nbLevel);
        level.getGrid().addMouseListener(new LinkTileEvent(this,-1));
        map_.setViewportView(level.getForm());
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        linkFileNameFirst = new GeneComponentModelImgSelect(_core,_fac,_fact.getImgRetrieverLinksSub());
        form_.add(linkFileNameFirst.gene());
        linkFileNameSecond = new GeneComponentModelImgSelect(_core,_fac,_fact.getImgRetrieverLinksSub());
        form_.add(linkFileNameSecond.gene());
        addTileLeft = _core.getCompoFactory().newPlainButton("<-");
        addTileLeft.setForeground(GuiConstants.GREEN);
        addTileRight = _core.getCompoFactory().newPlainButton("->");
        addTileRight.setForeground(GuiConstants.GREEN);
        addTileBoth = _core.getCompoFactory().newPlainButton("<->");
        addTileBoth.setForeground(GuiConstants.GREEN);
        removeTileLeft = _core.getCompoFactory().newPlainButton("<-");
        removeTileLeft.setForeground(GuiConstants.RED);
        removeTileRight = _core.getCompoFactory().newPlainButton("->");
        removeTileRight.setForeground(GuiConstants.RED);
        removeTileBoth = _core.getCompoFactory().newPlainButton("<->");
        removeTileBoth.setForeground(GuiConstants.RED);
        matchLinkLeft = _core.getCompoFactory().newPlainButton("<-");
        matchLinkRight = _core.getCompoFactory().newPlainButton("->");
        matchLinkBoth = _core.getCompoFactory().newPlainButton("<->");
        form_.add(addTileLeft);
        form_.add(addTileRight);
        form_.add(addTileBoth);
        form_.add(removeTileLeft);
        form_.add(removeTileRight);
        form_.add(removeTileBoth);
        form_.add(matchLinkLeft);
        form_.add(matchLinkRight);
        form_.add(matchLinkBoth);
        close = _core.getCompoFactory().newPlainButton("\u23F9");
        form_.add(close);
        addTileLeft.setEnabled(false);
        addTileRight.setEnabled(false);
        addTileBoth.setEnabled(false);
        removeTileLeft.setEnabled(false);
        removeTileRight.setEnabled(false);
        removeTileBoth.setEnabled(false);
        addTileLeft.addActionListener(new AddForeLinkTileEvent(this,LEFT));
        addTileRight.addActionListener(new AddForeLinkTileEvent(this,RIGHT));
        addTileBoth.addActionListener(new AddForeLinkTileEvent(this,BOTH));
        removeTileLeft.addActionListener(new RemoveForeLinkTileEvent(this,LEFT));
        removeTileRight.addActionListener(new RemoveForeLinkTileEvent(this,RIGHT));
        removeTileBoth.addActionListener(new RemoveForeLinkTileEvent(this,BOTH));
        matchLinkLeft.addActionListener(new ChangeForeLinkTileEvent(this,LEFT));
        matchLinkRight.addActionListener(new ChangeForeLinkTileEvent(this,RIGHT));
        matchLinkBoth.addActionListener(new ChangeForeLinkTileEvent(this,BOTH));
        FormDataMap.baseSelectImage(linkFileNameFirst);
        FormDataMap.baseSelectImage(linkFileNameSecond);
        linkFileNameFirst.getName().getSelectUniq().getSelect().addListener(new ChangeItemLinkTileEvent(this,linkFileNameFirst,selectedFirst, true));
        linkFileNameSecond.getName().getSelectUniq().getSelect().addListener(new ChangeItemLinkTileEvent(this,linkFileNameSecond,selectedSecond, false));
        common(linkFileNameFirst,level);
        common(linkFileNameSecond,level);
        int len_ = _cave.getLevels().size();
        for (int i = 0; i < len_; i++) {
            FormLevelGridLink g_ = build(_core, _fac, _fact, _f, _cave, _nbPlace, i);
            levels.add(g_);
            g_.getGrid().addMouseListener(new LinkTileEvent(this,i));
            form_.add(g_.getForm());
        }
        linkFileNameFirst.getName().getSelectUniq().getSelect().events(null);
        linkFileNameSecond.getName().getSelectUniq().getSelect().events(null);
        return _core.getCompoFactory().newHorizontalSplitPane(map_,_core.getCompoFactory().newAbsScrollPane(form_));
    }

    public AbsButton getClose() {
        return close;
    }

    private void common(GeneComponentModelImgSelect _sel, FormLevelGridLink _lev) {
        IdList<SubscribedTranslation> subs_ = _lev.getTranslationList().getSubscribedTranslations().getVal(_lev.getFrame());
        subs_.removeAllElements(translations);
        IdList<SubscribedTranslation> next_ = _sel.subs();
        subs_.addAllElts(next_);
        translations.addAllElts(next_);
    }
    private FormLevelGridLink build(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f, Cave _cave, int _nbPlace, int _nbLevel) {
        FormLevelGridLink level_ = new FormLevelGridLink(_core,_fac, _f,_fact);
        LevelCave levelCave_ = _cave.getLevels().get(_nbLevel);
        Points<Block> blocks_ = ConverterCommonMapUtil.copyPointsBlock(levelCave_.getBlocks());
        level_.getTranslationList().setFormLevelGridUniq(null);
        Coords coords_ = coords(_nbPlace, _nbLevel, null);
        Points<int[][]> frontTiles_ = Level.getLevelForegroundImage(level_.getFacadeGame().getData(), coords_, _cave,levelCave_);
        level_.setupGridDims(blocks_, frontTiles_);
        IdList<SubscribedTranslation> subs_ = level_.getTranslationList().getSubscribedTranslations().getVal(level_.getFrame());
        subs_.removeAllElements(translationsGrid);
        IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>();
        next_.add(new RefreshGridLinkSubscription(level_.getFacadeGame(),level_,coords_,_cave,levelCave_));
        subs_.addAllElts(next_);
        translationsGrid.addAllElts(next_);
        return level_;
    }

    public static Coords coords(int _nbPlace, int _nbLevel, Point _inside) {
        Coords curCoords_ = new Coords();
        curCoords_.setNumberPlace((short) _nbPlace);
        curCoords_.setInsideBuilding(_inside);
        curCoords_.getLevel().setLevelIndex((byte) _nbLevel);
        return curCoords_;
    }

    public static Coords coords(int _nbPlace, int _nbLevel, Point _inside, Point _pt) {
        Coords curCoords_ = new Coords();
        curCoords_.setNumberPlace((short) _nbPlace);
        curCoords_.setInsideBuilding(_inside);
        curCoords_.getLevel().setLevelIndex((byte) _nbLevel);
        curCoords_.getLevel().setPoint(_pt);
        return curCoords_;
    }

    public void viewForeground(int _index, int _x, int _y) {
        if (_index < 0) {
            selectedFirst.setPoint(level.toPt(_x, _y));
            level.getForegroundEdited().clear();
        } else {
            selectedLevel = _index;
            selectedSecond.setPoint(levels.get(_index).toPt(_x, _y));
            for (FormLevelGridLink f: levels) {
                f.getForegroundEdited().clear();
            }
        }
        if (!selectedFirst.isDefined() || !selectedSecond.isDefined()) {
            return;
        }
        int count_ = count(selectedFirst.getPoint(), selectedSecond.getPoint());
        refreshLevels();
        if (count_ == 0) {
            removeTileBoth.setEnabled(false);
            removeTileRight.setEnabled(false);
            removeTileLeft.setEnabled(false);
            if (DataMap.partialFillForAdding(getLevel().getFacadeGame().getMap(),coords(selectedPlace, selectedFirstLevel, null, selectedFirst.getPoint()),coords(selectedPlace, selectedLevel, null, selectedSecond.getPoint()))) {
                addTileBoth.setEnabled(false);
                addTileRight.setEnabled(false);
                addTileLeft.setEnabled(false);
                return;
            }
            addTileBoth.setEnabled(true);
            addTileRight.setEnabled(true);
            addTileLeft.setEnabled(true);
            return;
        }
        if (count_ == 2) {
            addTileBoth.setEnabled(false);
            addTileRight.setEnabled(false);
            addTileLeft.setEnabled(false);
            removeTileBoth.setEnabled(true);
            removeTileRight.setEnabled(true);
            removeTileLeft.setEnabled(true);
            return;
        }
        addTileBoth.setEnabled(false);
        removeTileBoth.setEnabled(false);
        if (cave.getLevels().get(selectedLevel).getLinksOtherLevels().contains(selectedSecond.getPoint())) {
            addTileLeft.setEnabled(true);
            removeTileRight.setEnabled(true);
            removeTileLeft.setEnabled(false);
            addTileRight.setEnabled(false);
        } else {
            addTileRight.setEnabled(true);
            removeTileRight.setEnabled(false);
            addTileLeft.setEnabled(false);
            removeTileLeft.setEnabled(true);
        }
    }

    private int count(Point _first, Point _second) {
        int count_ = 0;
        if (cave.getLevels().get(selectedLevel).getLinksOtherLevels().contains(_second)) {
            linkFileNameSecond.updateValue(cave.getLevels().get(selectedLevel).getLinksOtherLevels().getVal(_second).getFileName());
            count_++;
        } else if (getLevel().getFacadeGame().getMap().isEmptyForAdding(coords(selectedPlace, selectedLevel, null, selectedSecond.getPoint()))){
            trySet(level.getFacadeGame().getData().getLinks().getVal(linkFileNameSecond.getName().tryRet()),false,selectedSecond);
        }
        if (cave.getLevels().get(selectedFirstLevel).getLinksOtherLevels().contains(_first)){
            linkFileNameFirst.updateValue(cave.getLevels().get(selectedFirstLevel).getLinksOtherLevels().getVal(_first).getFileName());
            count_++;
        } else if (getLevel().getFacadeGame().getMap().isEmptyForAdding(coords(selectedPlace, selectedFirstLevel, null, selectedFirst.getPoint()))){
            trySet(level.getFacadeGame().getData().getLinks().getVal(linkFileNameFirst.getName().tryRet()),true,selectedFirst);
        }
        return count_;
    }

    public void applySelectItem(NullablePoint _point, GeneComponentModelImgSelect _select, boolean _left) {
        trySet(level.getFacadeGame().getData().getLinks().getVal(_select.getName().tryRet()),_left,_point);
        refreshLevels();
    }

    private void refreshLevels() {
        level.refreshImg();
        int len_ = cave.getLevels().size();
        for (int i = 0; i < len_; i++) {
            levels.get(i).refreshImg();
        }
    }

    private void trySet(ImageArrayBaseSixtyFour _img, boolean _left, NullablePoint _n) {
        if (_n.isDefined()) {
            if (_left) {
                ContentComponentModelLevelWithWild.trySet(_img,level.getForegroundEdited(),_n.getPoint());
                ContentComponentModelLevelWithWild.trySet(_img,levels.get(selectedFirstLevel).getForegroundEdited(),_n.getPoint());
            } else {
                ContentComponentModelLevelWithWild.trySet(_img,levels.get(selectedLevel).getForegroundEdited(),_n.getPoint());
            }
        }
    }

    public void applyTile(int _option) {
        FacadeGame facadeGame_ = level.getFacadeGame();
        Coords coordsFirst_ = coords(selectedPlace, selectedFirstLevel, null, selectedFirst.value());
        Coords coordsSecond_ = coords(selectedPlace, selectedLevel, null, selectedSecond.value());
        if (_option == LEFT) {
            DataMap.joinLevelCave(facadeGame_.getMap(),coordsFirst_.getNumberPlace(),coordsFirst_.getLevel(),coordsSecond_.getLevel(),linkFileNameFirst.getName().tryRet());
        } else if (_option == RIGHT) {
            DataMap.joinLevelCave(facadeGame_.getMap(),coordsFirst_.getNumberPlace(),coordsSecond_.getLevel(),coordsFirst_.getLevel(),linkFileNameSecond.getName().tryRet());
        } else {
            DataMap.joinLevelCave(facadeGame_.getMap(),coordsFirst_.getNumberPlace(),coordsFirst_.getLevel(),coordsSecond_.getLevel(),linkFileNameFirst.getName().tryRet(),linkFileNameSecond.getName().tryRet());
        }
        trySet(level.getFacadeGame().getData().getLinks().getVal(linkFileNameFirst.getName().tryRet()),true,selectedFirst);
        trySet(level.getFacadeGame().getData().getLinks().getVal(linkFileNameSecond.getName().tryRet()),false,selectedSecond);
        validate();
    }

    private void validate(NullablePoint _n, FormLevelGridLink _level) {
        int[][] val_ = _level.getForegroundEdited().getVal(_n.value());
        _level.getForegroundEdited().removeKey(_n.value());
        _level.getForeground().put(_n.value(),val_);
    }

    public void removeTile(int _option) {
        if (_option == LEFT) {
            cave.getLevels().get(selectedFirstLevel).getLinksOtherLevels().removeKey(selectedFirst.value());
        } else if (_option == RIGHT) {
            cave.getLevels().get(selectedLevel).getLinksOtherLevels().removeKey(selectedSecond.value());
        } else {
            cave.getLevels().get(selectedFirstLevel).getLinksOtherLevels().removeKey(selectedFirst.value());
            cave.getLevels().get(selectedLevel).getLinksOtherLevels().removeKey(selectedSecond.value());
        }
        removeFore(selectedFirst, level);
        removeFore(selectedFirst, levels.get(selectedFirstLevel));
        removeFore(selectedSecond, levels.get(selectedLevel));
        selectedFirst.setPoint(null);
        selectedSecond.setPoint(null);
        refreshLevels();
        addTileLeft.setEnabled(false);
        addTileRight.setEnabled(false);
        addTileBoth.setEnabled(false);
        removeTileLeft.setEnabled(false);
        removeTileRight.setEnabled(false);
        removeTileBoth.setEnabled(false);
    }
    public void applyLinks(int _option) {
        if (_option == LEFT) {
            tryChangeLink(selectedFirst, selectedFirstLevel, linkFileNameFirst);
        } else if (_option == RIGHT) {
            tryChangeLink(selectedSecond, selectedLevel, linkFileNameSecond);
        } else {
            tryChangeLink(selectedFirst, selectedFirstLevel, linkFileNameFirst);
            tryChangeLink(selectedSecond, selectedLevel, linkFileNameSecond);
        }
        validate();
    }

    private void validate() {
        validate(selectedFirst, level);
        validate(selectedFirst, levels.get(selectedFirstLevel));
        validate(selectedSecond, levels.get(selectedLevel));
        selectedFirst.setPoint(null);
        selectedSecond.setPoint(null);
        refreshLevels();
        addTileLeft.setEnabled(false);
        addTileRight.setEnabled(false);
        addTileBoth.setEnabled(false);
        removeTileLeft.setEnabled(false);
        removeTileRight.setEnabled(false);
        removeTileBoth.setEnabled(false);
    }

    private void tryChangeLink(NullablePoint _pt, int _level, GeneComponentModelImgSelect _sel) {
        if (_pt.isDefined()) {
            cave.getLevels().get(_level).getLinksOtherLevels().getVal(_pt.getPoint()).setFileName(_sel.getName().tryRet());
        }
    }

    private void removeFore(NullablePoint _n, FormLevelGridLink _level) {
        _level.getForegroundEdited().removeKey(_n.value());
        _level.getForeground().removeKey(_n.value());
    }

    public AbsButton getAddTileBoth() {
        return addTileBoth;
    }

    public AbsButton getAddTileLeft() {
        return addTileLeft;
    }

    public AbsButton getAddTileRight() {
        return addTileRight;
    }

    public AbsButton getRemoveTileBoth() {
        return removeTileBoth;
    }

    public AbsButton getRemoveTileLeft() {
        return removeTileLeft;
    }

    public AbsButton getRemoveTileRight() {
        return removeTileRight;
    }

    public AbsButton getMatchLinkBoth() {
        return matchLinkBoth;
    }

    public AbsButton getMatchLinkLeft() {
        return matchLinkLeft;
    }

    public AbsButton getMatchLinkRight() {
        return matchLinkRight;
    }

    public FormLevelGridLink getLevel() {
        return level;
    }

    public CustList<FormLevelGridLink> getLevels() {
        return levels;
    }

    public GeneComponentModelImgSelect getLinkFileNameFirst() {
        return linkFileNameFirst;
    }

    public GeneComponentModelImgSelect getLinkFileNameSecond() {
        return linkFileNameSecond;
    }

}
