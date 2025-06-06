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

public final class ContentComponentModelPlaceCaveLinks extends AbsContentComponentModelLevelLinks {
    private final CustList<FormLevelGridLink> levelsPlace = new CustList<FormLevelGridLink>();
    private final CustList<FormLevelGridLink> levelsCave = new CustList<FormLevelGridLink>();

    @Override
    protected AbsCustComponent viewLeft(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr) {
        levelsPlace.clear();
        CustList<FormLevelGridLink> parts_ = place(this, _core, _fac, _fact, _fr, true);
        levelsPlace.addAllElts(parts_);
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        form_.setTitledBorder(MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(_core.currentLg())).getMapping().getVal(MessagesEditorSelect.UNIQ_PLACE));
        appendPlace(parts_, form_);
        return form_;
    }


    public static CustList<FormLevelGridLink> place(AbsContentComponentModelLevelLinks _curr, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr, boolean _left){
        CustList<FormLevelGridLink> list_ = new CustList<FormLevelGridLink>();
        CustList<Place> pl_ = _fac.getMap().getPlaces();
        int len_ = pl_.size();
        for (int i = 0; i < len_; i++) {
            Place place_ = pl_.get(i);
            if (place_ instanceof InitializedPlace) {
                FormLevelGridLink g_ = build(_core, _fac, _fact, _fr, place_, coords(i, 0, null), _curr.getTranslationsGrid());
                list_.add(g_);
                g_.getGrid().addMouseListener(new LinkTileEvent(_curr, g_, _left));
            }
        }
        return list_;
    }

    @Override
    protected void buildParts(AbsPanel _form, AbstractProgramInfos _c, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr) {
        levelsCave.clear();
        _form.setTitledBorder(MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(_c.currentLg())).getMapping().getVal(MessagesEditorSelect.CAVE));
        CustList<FormLevelGridLink> parts_ = cave(this, _c, _fac, _fact, _fr, false);
        levelsCave.addAllElts(parts_);
        appendPlace(parts_,_form);
    }

    public static void appendPlace(CustList<FormLevelGridLink> _parts, AbsPanel _form) {
        int len_ = _parts.size();
        for (int i = 0; i < len_; i++) {
            _parts.get(i).getForm().setTitledBorder(i+"/"+(len_-1)+":"+_parts.get(i).getKey().getNumberPlace()+"-"+_parts.get(i).getKey().getLevel().getLevelIndex());
            _form.add(_parts.get(i).getForm());
        }
    }
    public static CustList<FormLevelGridLink> cave(AbsContentComponentModelLevelLinks _curr, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr, boolean _left){
        CustList<FormLevelGridLink> list_ = new CustList<FormLevelGridLink>();
        CustList<Place> pl_ = _fac.getMap().getPlaces();
        int len_ = pl_.size();
        for (int i = 0; i < len_; i++) {
            Place place_ = pl_.get(i);
            if (place_ instanceof Cave) {
                Cave c_ = (Cave)place_;
                CustList<LevelCave> levels_ = c_.getLevels();
                int lenLev_ = levels_.size();
                for (int j = 0; j < lenLev_; j++) {
                    FormLevelGridLink g_ = build(_core, _fac, _fact, _fr, place_, coords(i, j, null), _curr.getTranslationsGrid());
                    list_.add(g_);
                    g_.getGrid().addMouseListener(new LinkTileEvent(_curr, g_, _left));
                }
            }
        }
        return list_;
    }

    @Override
    protected void clearLeft() {
        clearList(levelsPlace);
    }

    @Override
    protected void clearRight() {
        clearList(levelsCave);
    }

    public static void clearList(CustList<FormLevelGridLink> _ls) {
        for (FormLevelGridLink f: _ls) {
            f.getForegroundEdited().clear();
        }
    }

    @Override
    protected String fileLeft(Point _r) {
        return ((InitializedPlace)getFacadeGame().getMap().getPlace(getSelectedFirstLevel().getKey().getNumberPlace())).getLinksWithCaves().getVal(_r).getFileName();
    }

    @Override
    protected boolean containsLeft(Point _r) {
        return ((InitializedPlace)getFacadeGame().getMap().getPlace(getSelectedFirstLevel().getKey().getNumberPlace())).getLinksWithCaves().contains(_r);
    }

    @Override
    protected String fileRight(Point _r) {
        return ((Cave)getFacadeGame().getMap().getPlace(getSelectedSecondLevel().getKey().getNumberPlace())).getLinksWithOtherPlaces().getVal(getSelectedSecondLevel().build(_r).getLevel()).getFileName();
    }

    @Override
    protected boolean containsRight(Point _r) {
        return ((Cave)getFacadeGame().getMap().getPlace(getSelectedSecondLevel().getKey().getNumberPlace())).getLinksWithOtherPlaces().contains(getSelectedSecondLevel().build(_r).getLevel());
    }

    @Override
    protected boolean partialFillForAdding(Point _first, Point _second) {
        return DataMap.partialFillForAdding(getFacadeGame().getMap(),getSelectedFirstLevel().build(_first), getSelectedSecondLevel().build(_second));
    }

    protected void refreshLevels() {
        refreshAll(levelsPlace, levelsCave);
    }

    public static void refreshAll(CustList<FormLevelGridLink> _levelsPlace, CustList<FormLevelGridLink> _levelsCave) {
        refreshList(_levelsPlace);
        refreshList(_levelsCave);
    }

    public static void refreshList(CustList<FormLevelGridLink> _levelsCave) {
        int lenRight_ = _levelsCave.size();
        for (int i = 0; i < lenRight_; i++) {
            _levelsCave.get(i).refreshImg();
        }
    }

    @Override
    protected void trySetLeft(ImageArrayBaseSixtyFour _img, Point _pt) {
        ContentComponentModelLevelWithWild.trySet(_img, levelsPlace.get(getSelectedFirstLevel().getKey().getLevel().getLevelIndex()).getForegroundEdited(),_pt);
    }

    @Override
    protected void trySetRight(ImageArrayBaseSixtyFour _img, Point _pt) {
        ContentComponentModelLevelWithWild.trySet(_img, levelsCave.get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()).getForegroundEdited(),_pt);
    }

    @Override
    protected String addTileLeftLink() {
        return MessagesEditorSelect.BUTTON_LINK_PLACE_CAVE_ONE;
    }

    @Override
    protected String addTileRightLink() {
        return MessagesEditorSelect.BUTTON_LINK_CAVE_PLACE_ONE;
    }

    @Override
    protected String addTileBothLink() {
        return MessagesEditorSelect.BUTTON_LINK_PLACE_CAVE_BOTH;
    }

    @Override
    protected String removeTileLeftLink() {
        return MessagesEditorSelect.BUTTON_UNLINK_PLACE_CAVE_ONE;
    }

    @Override
    protected String removeTileRightLink() {
        return MessagesEditorSelect.BUTTON_UNLINK_CAVE_PLACE_ONE;
    }

    @Override
    protected String removeTileBothLink() {
        return MessagesEditorSelect.BUTTON_UNLINK_PLACE_CAVE_BOTH;
    }

    @Override
    protected void appendBoth() {
        FacadeGame facadeGame_ = getFacadeGame();
        Coords coordsFirst_ = getSelectedFirstLevel().build(getSelectedFirst().value());
        Coords coordsSecond_ = getSelectedSecondLevel().build(getSelectedSecond().value());
        DataMap.joinCavePlace(facadeGame_.getMap(),coordsSecond_,coordsFirst_, getLinkFileNameFirst().getName().tryRet(), getLinkFileNameSecond().getName().tryRet());
    }

    @Override
    protected void appendRight() {
        FacadeGame facadeGame_ = getFacadeGame();
        Coords coordsFirst_ = getSelectedFirstLevel().build(getSelectedFirst().value());
        Coords coordsSecond_ = getSelectedSecondLevel().build(getSelectedSecond().value());
        DataMap.joinCave(facadeGame_.getMap(),coordsSecond_, coordsFirst_, getLinkFileNameSecond().getName().tryRet());
    }

    @Override
    protected void appendLeft() {
        FacadeGame facadeGame_ = getFacadeGame();
        Coords coordsFirst_ = getSelectedFirstLevel().build(getSelectedFirst().value());
        Coords coordsSecond_ = getSelectedSecondLevel().build(getSelectedSecond().value());
        DataMap.joinPlace(facadeGame_.getMap(),coordsSecond_, coordsFirst_, getLinkFileNameFirst().getName().tryRet());
    }

    private void validate(NullablePoint _n, FormLevelGridLink _level) {
        int[][] val_ = _level.getForegroundEdited().getVal(_n.value());
        _level.getForegroundEdited().removeKey(_n.value());
        _level.getForeground().put(_n.value(),val_);
    }

    @Override
    protected void removeLeft(Point _p) {
        ((InitializedPlace)getFacadeGame().getMap().getPlace(getSelectedFirstLevel().getKey().getNumberPlace())).getLinksWithCaves().removeKey(_p);
    }

    @Override
    protected void removeRight(Point _p) {
        ((Cave)getFacadeGame().getMap().getPlace(getSelectedSecondLevel().getKey().getNumberPlace())).getLinksWithOtherPlaces().removeKey(getSelectedSecondLevel().build(_p).getLevel());
    }

    @Override
    protected void tryChangeLinkPlace(FormLevelGridLink _l, Point _p, String _f) {
        trCh(_l, _p, _f, getFacadeGame());
    }

    public static void trCh(FormLevelGridLink _l, Point _p, String _f, FacadeGame _fac) {
        Place pl_ = _fac.getMap().getPlace(_l.getKey().getNumberPlace());
        if (pl_ instanceof InitializedPlace) {
            ((InitializedPlace)pl_).getLinksWithCaves().getVal(_p).setFileName(_f);
        }
        if (pl_ instanceof Cave) {
            ((Cave)pl_).getLinksWithOtherPlaces().getVal(_l.build(_p).getLevel()).setFileName(_f);
        }
    }

    @Override
    protected void validateLinks(NullablePoint _l, NullablePoint _r) {
        validate(_l, levelsPlace.get(getSelectedFirstLevel().getKey().getLevel().getLevelIndex()));
        validate(_r, levelsCave.get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()));
    }

    @Override
    protected void removeFore(Point _l, Point _r) {
        removeFore(_l, levelsPlace.get(getSelectedFirstLevel().getKey().getLevel().getLevelIndex()));
        removeFore(_r, levelsCave.get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()));
    }

    private void removeFore(Point _n, FormLevelGridLink _level) {
        _level.getForegroundEdited().removeKey(_n);
        _level.getForeground().removeKey(_n);
    }

    public CustList<FormLevelGridLink> getLevelsPlace() {
        return levelsPlace;
    }

    public CustList<FormLevelGridLink> getLevelsCave() {
        return levelsCave;
    }

}
