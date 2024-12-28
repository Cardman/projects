package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.map.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelLevelCaveLinks extends AbsContentComponentModelLevelLinks {
    private FormLevelGridLink level;
    private final CustList<FormLevelGridLink> levels = new CustList<FormLevelGridLink>();
    private Cave cave;
    private int selectedPlace;
    private int selectedFirstLevelIndex;

    public void selectIndexes(Cave _cave, int _nbPlace, int _nbLevel) {
        selectedPlace = _nbPlace;
        selectedFirstLevelIndex = _nbLevel;
        cave = _cave;
    }

    @Override
    protected AbsCustComponent viewLeft(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr) {
        level = build(_core, _fac, _fact, _fr, cave, coords(selectedPlace, selectedFirstLevelIndex, null), getTranslationsGrid());
        level.getGrid().addMouseListener(new LinkTileEvent(this, level, true));
        return level.getForm();
    }

    @Override
    protected void buildParts(AbsPanel _form, AbstractProgramInfos _c, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr) {
        levels.clear();
        int len_ = cave.getLevels().size();
        for (int i = 0; i < len_; i++) {
            FormLevelGridLink g_ = build(_c, _fac, _fact, _fr, cave, coords(selectedPlace, i, null), getTranslationsGrid());
            levels.add(g_);
            g_.getGrid().addMouseListener(new LinkTileEvent(this, g_, false));
            _form.add(g_.getForm());
        }
    }

    @Override
    protected void clearLeft() {
        level.getForegroundEdited().clear();
    }

    @Override
    protected void clearRight() {
        for (FormLevelGridLink f: levels) {
            f.getForegroundEdited().clear();
        }
    }

    @Override
    protected String fileLeft(Point _r) {
        return cave.getLevels().get(getSelectedFirstLevel().getKey().getLevel().getLevelIndex()).getLinksOtherLevels().getVal(_r).getFileName();
    }

    @Override
    protected boolean containsLeft(Point _r) {
        return cave.getLevels().get(getSelectedFirstLevel().getKey().getLevel().getLevelIndex()).getLinksOtherLevels().contains(_r);
    }

    @Override
    protected String fileRight(Point _r) {
        return cave.getLevels().get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()).getLinksOtherLevels().getVal(_r).getFileName();
    }

    @Override
    protected boolean containsRight(Point _r) {
        return cave.getLevels().get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()).getLinksOtherLevels().contains(_r);
    }

    @Override
    protected boolean partialFillForAdding(Point _first, Point _second) {
        return DataMap.partialFillForAdding(getLevel().getFacadeGame().getMap(),level.build(_first),getSelectedSecondLevel().build(_second));
    }

    protected void refreshLevels() {
        level.refreshImg();
        ContentComponentModelPlaceCaveLinks.refreshList(levels);
    }

    @Override
    protected void trySetLeft(ImageArrayBaseSixtyFour _img, Point _pt) {
        ContentComponentModelLevelWithWild.trySet(_img,level.getForegroundEdited(),_pt);
        ContentComponentModelLevelWithWild.trySet(_img,levels.get(selectedFirstLevelIndex).getForegroundEdited(),_pt);
    }

    @Override
    protected void trySetRight(ImageArrayBaseSixtyFour _img, Point _pt) {
        ContentComponentModelLevelWithWild.trySet(_img,levels.get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()).getForegroundEdited(),_pt);
    }

    @Override
    protected void appendBoth() {
        FacadeGame facadeGame_ = getFacadeGame();
        Coords coordsFirst_ = getSelectedFirstLevel().build(getSelectedFirst().value());
        Coords coordsSecond_ = getSelectedSecondLevel().build(getSelectedSecond().value());
        DataMap.joinLevelCave(facadeGame_.getMap(),coordsFirst_.getNumberPlace(),coordsFirst_.getLevel(),coordsSecond_.getLevel(), getLinkFileNameFirst().getName().tryRet(), getLinkFileNameSecond().getName().tryRet());
    }

    @Override
    protected void appendRight() {
        FacadeGame facadeGame_ = getFacadeGame();
        Coords coordsFirst_ = getSelectedFirstLevel().build(getSelectedFirst().value());
        Coords coordsSecond_ = getSelectedSecondLevel().build(getSelectedSecond().value());
        DataMap.joinLevelCave(facadeGame_.getMap(),coordsFirst_.getNumberPlace(),coordsSecond_.getLevel(),coordsFirst_.getLevel(), getLinkFileNameSecond().getName().tryRet());
    }

    @Override
    protected void appendLeft() {
        FacadeGame facadeGame_ = getFacadeGame();
        Coords coordsFirst_ = getSelectedFirstLevel().build(getSelectedFirst().value());
        Coords coordsSecond_ = getSelectedSecondLevel().build(getSelectedSecond().value());
        DataMap.joinLevelCave(facadeGame_.getMap(),coordsFirst_.getNumberPlace(),coordsFirst_.getLevel(),coordsSecond_.getLevel(), getLinkFileNameFirst().getName().tryRet());
    }

    private void validate(NullablePoint _n, FormLevelGridLink _level) {
        int[][] val_ = _level.getForegroundEdited().getVal(_n.value());
        _level.getForegroundEdited().removeKey(_n.value());
        _level.getForeground().put(_n.value(),val_);
    }

    @Override
    protected void removeLeft(Point _p) {
        cave.getLevels().get(selectedFirstLevelIndex).getLinksOtherLevels().removeKey(_p);
    }

    @Override
    protected void removeRight(Point _p) {
        cave.getLevels().get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()).getLinksOtherLevels().removeKey(_p);
    }

    @Override
    protected void tryChangeLinkPlace(FormLevelGridLink _l, Point _p, String _f) {
        cave.getLevels().get(_l.getKey().getLevel().getLevelIndex()).getLinksOtherLevels().getVal(_p).setFileName(_f);
    }

    @Override
    protected void validateLinks(NullablePoint _l, NullablePoint _r) {
        validate(_l, level);
        validate(_l, levels.get(selectedFirstLevelIndex));
        validate(_r, levels.get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()));
    }

    @Override
    protected void removeFore(Point _l, Point _r) {
        removeFore(_l, level);
        removeFore(_l, levels.get(selectedFirstLevelIndex));
        removeFore(_r, levels.get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()));
    }

    private void removeFore(Point _n, FormLevelGridLink _level) {
        _level.getForegroundEdited().removeKey(_n);
        _level.getForeground().removeKey(_n);
    }

    public FormLevelGridLink getLevel() {
        return level;
    }

    public CustList<FormLevelGridLink> getLevels() {
        return levels;
    }

}
