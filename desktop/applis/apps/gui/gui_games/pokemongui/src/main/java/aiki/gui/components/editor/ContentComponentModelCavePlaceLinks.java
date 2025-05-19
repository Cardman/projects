package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.map.*;
import aiki.map.places.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelCavePlaceLinks extends AbsContentComponentModelLevelLinks {
    private final CustList<FormLevelGridLink> levelsPlace = new CustList<FormLevelGridLink>();
    private final CustList<FormLevelGridLink> levelsCave = new CustList<FormLevelGridLink>();

    @Override
    protected AbsCustComponent viewLeft(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr) {
        levelsCave.clear();
        CustList<FormLevelGridLink> parts_ = ContentComponentModelPlaceCaveLinks.cave(this, _core, _fac, _fact, _fr, true);
        levelsCave.addAllElts(parts_);
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        form_.setTitledBorder(MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(_core.currentLg())).getMapping().getVal(MessagesEditorSelect.CAVE));
        ContentComponentModelPlaceCaveLinks.appendPlace(parts_,form_);
        return form_;
    }

    @Override
    protected void buildParts(AbsPanel _form, AbstractProgramInfos _c, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr) {
        _form.setTitledBorder(MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(_c.currentLg())).getMapping().getVal(MessagesEditorSelect.UNIQ_PLACE));
        levelsPlace.clear();
        CustList<FormLevelGridLink> parts_ = ContentComponentModelPlaceCaveLinks.place(this, _c, _fac, _fact, _fr, false);
        levelsPlace.addAllElts(parts_);
        ContentComponentModelPlaceCaveLinks.appendPlace(parts_, _form);
    }

    @Override
    protected void clearLeft() {
        ContentComponentModelPlaceCaveLinks.clearList(levelsCave);
    }

    @Override
    protected void clearRight() {
        ContentComponentModelPlaceCaveLinks.clearList(levelsPlace);
    }

    @Override
    protected String fileLeft(Point _r) {
        return ((Cave)getFacadeGame().getMap().getPlace(getSelectedFirstLevel().getKey().getNumberPlace())).getLinksWithOtherPlaces().getVal(getSelectedFirstLevel().build(_r).getLevel()).getFileName();
    }

    @Override
    protected boolean containsLeft(Point _r) {
        return ((Cave)getFacadeGame().getMap().getPlace(getSelectedFirstLevel().getKey().getNumberPlace())).getLinksWithOtherPlaces().contains(getSelectedFirstLevel().build(_r).getLevel());
    }

    @Override
    protected String fileRight(Point _r) {
        return ((InitializedPlace)getFacadeGame().getMap().getPlace(getSelectedSecondLevel().getKey().getNumberPlace())).getLinksWithCaves().getVal(_r).getFileName();
    }

    @Override
    protected boolean containsRight(Point _r) {
        return ((InitializedPlace)getFacadeGame().getMap().getPlace(getSelectedSecondLevel().getKey().getNumberPlace())).getLinksWithCaves().contains(_r);
    }

    @Override
    protected boolean partialFillForAdding(Point _first, Point _second) {
        return DataMap.partialFillForAdding(getFacadeGame().getMap(),getSelectedFirstLevel().build(_first), getSelectedSecondLevel().build(_second));
    }

    protected void refreshLevels() {
        ContentComponentModelPlaceCaveLinks.refreshAll(levelsPlace, levelsCave);
    }

    @Override
    protected void trySetLeft(ImageArrayBaseSixtyFour _img, Point _pt) {
        ContentComponentModelLevelWithWild.trySet(_img, levelsCave.get(getSelectedFirstLevel().getKey().getLevel().getLevelIndex()).getForegroundEdited(),_pt);
    }

    @Override
    protected void trySetRight(ImageArrayBaseSixtyFour _img, Point _pt) {
        ContentComponentModelLevelWithWild.trySet(_img, levelsPlace.get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()).getForegroundEdited(),_pt);
    }

    @Override
    protected void appendBoth() {
        FacadeGame facadeGame_ = getFacadeGame();
        Coords coordsFirst_ = getSelectedFirstLevel().build(getSelectedFirst().value());
        Coords coordsSecond_ = getSelectedSecondLevel().build(getSelectedSecond().value());
        DataMap.joinCavePlace(facadeGame_.getMap(),coordsFirst_,coordsSecond_, getLinkFileNameSecond().getName().tryRet(), getLinkFileNameFirst().getName().tryRet());
    }

    @Override
    protected void appendRight() {
        FacadeGame facadeGame_ = getFacadeGame();
        Coords coordsFirst_ = getSelectedFirstLevel().build(getSelectedFirst().value());
        Coords coordsSecond_ = getSelectedSecondLevel().build(getSelectedSecond().value());
        DataMap.joinPlace(facadeGame_.getMap(), coordsFirst_,coordsSecond_, getLinkFileNameFirst().getName().tryRet());
    }

    @Override
    protected void appendLeft() {
        FacadeGame facadeGame_ = getFacadeGame();
        Coords coordsFirst_ = getSelectedFirstLevel().build(getSelectedFirst().value());
        Coords coordsSecond_ = getSelectedSecondLevel().build(getSelectedSecond().value());
        DataMap.joinCave(facadeGame_.getMap(),coordsFirst_, coordsSecond_, getLinkFileNameSecond().getName().tryRet());
    }

    private void validate(NullablePoint _n, FormLevelGridLink _level) {
        int[][] val_ = _level.getForegroundEdited().getVal(_n.value());
        _level.getForegroundEdited().removeKey(_n.value());
        _level.getForeground().put(_n.value(),val_);
    }

    @Override
    protected void removeLeft(Point _p) {
        ((Cave)getFacadeGame().getMap().getPlace(getSelectedFirstLevel().getKey().getNumberPlace())).getLinksWithOtherPlaces().removeKey(getSelectedFirstLevel().build(_p).getLevel());
    }

    @Override
    protected void removeRight(Point _p) {
        ((InitializedPlace)getFacadeGame().getMap().getPlace(getSelectedSecondLevel().getKey().getNumberPlace())).getLinksWithCaves().removeKey(_p);
    }

    @Override
    protected void tryChangeLinkPlace(FormLevelGridLink _l, Point _p, String _f) {
        ContentComponentModelPlaceCaveLinks.trCh(_l,_p,_f,getFacadeGame());
    }

    @Override
    protected void validateLinks(NullablePoint _l, NullablePoint _r) {
        validate(_l, levelsPlace.get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()));
        validate(_r, levelsCave.get(getSelectedFirstLevel().getKey().getLevel().getLevelIndex()));
    }

    @Override
    protected void removeFore(Point _l, Point _r) {
        removeFore(_l, levelsPlace.get(getSelectedSecondLevel().getKey().getLevel().getLevelIndex()));
        removeFore(_r, levelsCave.get(getSelectedFirstLevel().getKey().getLevel().getLevelIndex()));
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
