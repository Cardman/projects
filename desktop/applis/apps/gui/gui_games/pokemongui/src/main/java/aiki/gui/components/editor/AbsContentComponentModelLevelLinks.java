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

public abstract class AbsContentComponentModelLevelLinks {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int BOTH = 2;
    private GeneComponentModelImgSelect linkFileNameFirst;
    private GeneComponentModelImgSelect linkFileNameSecond;
    private final IdList<SubscribedTranslation> translations = new IdList<SubscribedTranslation>();
    private final IdList<SubscribedTranslation> translationsGrid = new IdList<SubscribedTranslation>();
    private final NullablePoint selectedFirst = new NullablePoint();
    private final NullablePoint selectedSecond = new NullablePoint();
    private FormLevelGridLink selectedFirstLevel;
    private FormLevelGridLink selectedSecondLevel;
    private AbsButton addTileLeft;
    private AbsButton addTileRight;
    private AbsButton addTileBoth;
    private AbsButton removeTileLeft;
    private AbsButton removeTileRight;
    private AbsButton removeTileBoth;
    private AbsButton matchLinkLeft;
    private AbsButton matchLinkRight;
    private AbsButton matchLinkBoth;
    private AbsButton close;
    private FacadeGame facadeGame;

    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        facadeGame = _fac;
        AbsScrollPane map_ = _core.getCompoFactory().newAbsScrollPane();
        map_.setViewportView(viewLeft(_core, _fac, _fact, _f));
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        linkFileNameFirst = new GeneComponentModelImgSelect(_core,_fac,_fact.getImgRetrieverLinksSub());
        form_.add(SubscribedTranslationList.line(_core,MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_MINI_LEFT,linkFileNameFirst.gene()));
        linkFileNameSecond = new GeneComponentModelImgSelect(_core,_fac,_fact.getImgRetrieverLinksSub());
        form_.add(SubscribedTranslationList.line(_core,MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_MINI_RIGHT,linkFileNameSecond.gene()));
        StringMap<String> tf_ = MessagesPkEditor.getMessagesEditorSelectButtonsTr(MessagesPkEditor.getAppliTr(_core.currentLg())).getMapping();
        addTileLeft = _core.getCompoFactory().newPlainButton(tf_.getVal(addTileLeftLink()));
        addTileLeft.setForeground(GuiConstants.GREEN);
        addTileRight = _core.getCompoFactory().newPlainButton(tf_.getVal(addTileRightLink()));
        addTileRight.setForeground(GuiConstants.GREEN);
        addTileBoth = _core.getCompoFactory().newPlainButton(tf_.getVal(addTileBothLink()));
        addTileBoth.setForeground(GuiConstants.GREEN);
        removeTileLeft = _core.getCompoFactory().newPlainButton(tf_.getVal(removeTileLeftLink()));
        removeTileLeft.setForeground(GuiConstants.RED);
        removeTileRight = _core.getCompoFactory().newPlainButton(tf_.getVal(removeTileRightLink()));
        removeTileRight.setForeground(GuiConstants.RED);
        removeTileBoth = _core.getCompoFactory().newPlainButton(tf_.getVal(removeTileBothLink()));
        removeTileBoth.setForeground(GuiConstants.RED);
        matchLinkLeft = _core.getCompoFactory().newPlainButton(tf_.getVal(MessagesEditorSelect.BUTTON_IMG_LINK_LEFT));
        matchLinkRight = _core.getCompoFactory().newPlainButton(tf_.getVal(MessagesEditorSelect.BUTTON_IMG_LINK_RIGHT));
        matchLinkBoth = _core.getCompoFactory().newPlainButton(tf_.getVal(MessagesEditorSelect.BUTTON_IMG_LINK_BOTH));
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
        common(_f,_fact,linkFileNameFirst);
        common(_f,_fact,linkFileNameSecond);
        buildParts(form_,_core, _fac, _fact, _f);
        linkFileNameFirst.getName().getSelectUniq().getSelect().events(null);
        linkFileNameSecond.getName().getSelectUniq().getSelect().events(null);
        return _core.getCompoFactory().newHorizontalSplitPane(map_,_core.getCompoFactory().newAbsScrollPane(form_));
    }

    protected abstract AbsCustComponent viewLeft(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr);

    protected abstract void buildParts(AbsPanel _pan, AbstractProgramInfos _c, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr);

    public AbsButton getClose() {
        return close;
    }

    private void common(AbsCommonFrame _f, SubscribedTranslationList _fact, GeneComponentModelImgSelect _sel) {
        IdList<SubscribedTranslation> subs_ = _fact.getSubscribedTranslations().getVal(_f);
        subs_.removeAllElements(translations);
        IdList<SubscribedTranslation> next_ = _sel.subs();
        subs_.addAllElts(next_);
        translations.addAllElts(next_);
    }
    protected static FormLevelGridLink build(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f, Place _cave, Coords _coords, IdList<SubscribedTranslation> _translationsGrid) {
        FormLevelGridLink level_ = new FormLevelGridLink(_core,_fac, _f,_fact);
        level_.setKey(_coords);
        Level levelCave_ = _cave.getLevelsList().get(_coords.getLevel().getLevelIndex());
        Points<Block> blocks_ = levelCave_.getBlocks();
        level_.getTranslationList().setFormLevelGridUniq(null);
        level_.setupGridDims(blocks_, _coords, _cave,levelCave_);
        IdList<SubscribedTranslation> subs_ = level_.getTranslationList().getSubscribedTranslations().getVal(level_.getFrame());
        subs_.removeAllElements(_translationsGrid);
        IdList<SubscribedTranslation> next_ = new IdList<SubscribedTranslation>();
        next_.add(new RefreshGridLinkSubscription(level_.getFacadeGame(),level_, _coords,_cave,levelCave_));
        subs_.addAllElts(next_);
        _translationsGrid.addAllElts(next_);
        return level_;
    }

    public IdList<SubscribedTranslation> getTranslationsGrid() {
        return translationsGrid;
    }

    public static Coords coords(int _nbPlace, int _nbLevel, Point _inside) {
        Coords curCoords_ = new Coords();
        curCoords_.setNumberPlace(_nbPlace);
        curCoords_.setInsideBuilding(_inside);
        curCoords_.getLevel().setLevelIndex(_nbLevel);
        return curCoords_;
    }

    public static Coords coords(int _nbPlace, int _nbLevel, Point _inside, Point _pt) {
        Coords curCoords_ = new Coords();
        curCoords_.setNumberPlace(_nbPlace);
        curCoords_.setInsideBuilding(_inside);
        curCoords_.getLevel().setLevelIndex(_nbLevel);
        curCoords_.getLevel().setPoint(_pt);
        return curCoords_;
    }

    public void viewForeground(FormLevelGridLink _selectedLevelGrid, boolean _left, int _x, int _y) {
        if (_left) {
            selectedFirstLevel = _selectedLevelGrid;
            selectedFirst.setPoint(_selectedLevelGrid.toPt(_x, _y));
            clearLeft();
        } else {
            selectedSecondLevel = _selectedLevelGrid;
            selectedSecond.setPoint(_selectedLevelGrid.toPt(_x, _y));
            clearRight();
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
            if (partialFillForAdding(selectedFirst.getPoint(),selectedSecond.getPoint())) {
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
        if (containsRight(selectedSecond.getPoint())) {
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

    public FormLevelGridLink getSelectedFirstLevel() {
        return selectedFirstLevel;
    }

    public FormLevelGridLink getSelectedSecondLevel() {
        return selectedSecondLevel;
    }

    protected abstract boolean containsLeft(Point _r);
    protected abstract boolean containsRight(Point _r);
    protected abstract String fileLeft(Point _r);
    protected abstract String fileRight(Point _r);

    protected abstract void clearRight();

    protected abstract void clearLeft();

    protected abstract boolean partialFillForAdding(Point _first, Point _second);

    private int count(Point _first, Point _second) {
        int count_ = 0;
        if (containsRight(_second)) {
            linkFileNameSecond.updateValue(fileRight(_second));
            count_++;
        } else if (facadeGame.getMap().isEmptyForAdding(selectedSecondLevel.build(selectedSecond.getPoint()))){
            trySet(facadeGame.getData().getLinks().getVal(linkFileNameSecond.getName().tryRet()),false,selectedSecond);
        }
        if (containsLeft(_first)){
            linkFileNameFirst.updateValue(fileLeft(_first));
            count_++;
        } else if (facadeGame.getMap().isEmptyForAdding(selectedFirstLevel.build(selectedFirst.getPoint()))){
            trySet(facadeGame.getData().getLinks().getVal(linkFileNameFirst.getName().tryRet()),true,selectedFirst);
        }
        return count_;
    }

    public void applySelectItem(NullablePoint _point, GeneComponentModelImgSelect _select, boolean _left) {
        trySet(facadeGame.getData().getLinks().getVal(_select.getName().tryRet()),_left,_point);
        refreshLevels();
    }

    protected abstract void refreshLevels();

    private void trySet(ImageArrayBaseSixtyFour _img, boolean _left, NullablePoint _n) {
        if (_n.isDefined()) {
            if (_left) {
                trySetLeft(_img,_n.getPoint());
            } else {
                trySetRight(_img,_n.getPoint());
            }
        }
    }

    protected abstract void trySetRight(ImageArrayBaseSixtyFour _img, Point _pt);

    protected abstract void trySetLeft(ImageArrayBaseSixtyFour _img, Point _pt);

    public void applyTile(int _option) {
        if (_option == LEFT) {
            appendLeft();
        } else if (_option == RIGHT) {
            appendRight();
        } else {
            appendBoth();
        }
        trySet(facadeGame.getData().getLinks().getVal(linkFileNameFirst.getName().tryRet()),true,selectedFirst);
        trySet(facadeGame.getData().getLinks().getVal(linkFileNameSecond.getName().tryRet()),false,selectedSecond);
        validate();
    }

    public FacadeGame getFacadeGame() {
        return facadeGame;
    }

    public NullablePoint getSelectedFirst() {
        return selectedFirst;
    }

    public NullablePoint getSelectedSecond() {
        return selectedSecond;
    }
    protected abstract String addTileLeftLink();
    protected abstract String addTileRightLink();
    protected abstract String addTileBothLink();
    protected abstract String removeTileLeftLink();
    protected abstract String removeTileRightLink();
    protected abstract String removeTileBothLink();
    protected abstract void appendBoth();

    protected abstract void appendRight();

    protected abstract void appendLeft();

    public void removeTile(int _option) {
        if (_option == LEFT) {
            removeLeft(selectedFirst.value());
        } else if (_option == RIGHT) {
            removeRight(selectedSecond.value());
        } else {
            removeLeft(selectedFirst.value());
            removeRight(selectedSecond.value());
        }
        removeFore(selectedFirst.value(),selectedSecond.value());
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

    protected abstract void removeFore(Point _l, Point _r);

    protected abstract void removeRight(Point _p);

    protected abstract void removeLeft(Point _p);

    public void applyLinks(int _option) {
        if (_option == LEFT) {
            tryChangeLink(selectedFirst, selectedFirstLevel, linkFileNameFirst);
        } else if (_option == RIGHT) {
            tryChangeLink(selectedSecond, selectedSecondLevel, linkFileNameSecond);
        } else {
            tryChangeLink(selectedFirst, selectedFirstLevel, linkFileNameFirst);
            tryChangeLink(selectedSecond, selectedSecondLevel, linkFileNameSecond);
        }
        validate();
    }

    private void validate() {
        validateLinks(selectedFirst, selectedSecond);
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
    protected abstract void validateLinks(NullablePoint _l, NullablePoint _r);

    private void tryChangeLink(NullablePoint _pt, FormLevelGridLink _level, GeneComponentModelImgSelect _sel) {
        if (_pt.isDefined()) {
            tryChangeLinkPlace(_level,_pt.getPoint(),_sel.getName().tryRet());
        }
    }

    protected abstract void tryChangeLinkPlace(FormLevelGridLink _l, Point _p, String _f);

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

    public GeneComponentModelImgSelect getLinkFileNameFirst() {
        return linkFileNameFirst;
    }

    public GeneComponentModelImgSelect getLinkFileNameSecond() {
        return linkFileNameSecond;
    }

}
