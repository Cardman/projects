package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.map.util.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelUniqLevelLinks {

    private AbstractProgramInfos api;
    private SubscribedTranslationList translationList;
    private AbsCommonFrame frame;
    private final IdList<SubscribedTranslation> translationsGrid = new IdList<SubscribedTranslation>();
    private NullablePoint firstSelected = new NullablePoint();
    private NullablePoint secondSelected = new NullablePoint();
    private AbsButton close;
    private AbsButton joinPlacesButton;
    private AbsButton unjoinPlacesButton;
    private AbsPanel buttonsLeft;
    private AbsPanel buttonsRight;
    private FacadeGame facadeGame;
    private FormLevelGridLink levelLeft;
    private FormLevelGridLink levelRight;
    private final CustList<CustList<IdMap<Direction,BoolVal>>> linked = new CustList<CustList<IdMap<Direction,BoolVal>>>();
    private GeneComponentModelLs<EditedCrudPair<Coords, InitializedPlace>> left;
    private GeneComponentModelLs<EditedCrudPair<Coords, InitializedPlace>> right;
    private AbsScrollPane leftScroll;
    private AbsScrollPane rightScroll;
    private final IdMap<EditedCrudPair<Coords, InitializedPlace>,String> messages = new IdMap<EditedCrudPair<Coords, InitializedPlace>, String>();

    private Direction firstDirection;
    private Direction secondDirection;
    private CustList<Direction> possibleLeft = new CustList<Direction>();
    private CustList<Direction> possibleRight = new CustList<Direction>();
    private final IdMap<Direction,AbsButton> selDirLeftButtons = new IdMap<Direction, AbsButton>();
    private final IdMap<Direction,AbsButton> selDirRightButtons = new IdMap<Direction, AbsButton>();
    private final IdMap<Direction,String> dirMessages = new IdMap<Direction, String>();

    public ContentComponentModelUniqLevelLinks() {
        dirMessages.addEntry(Direction.UP,"\u2191");
        dirMessages.addEntry(Direction.DOWN,"\u2193");
        dirMessages.addEntry(Direction.LEFT,"\u2190");
        dirMessages.addEntry(Direction.RIGHT,"\u2192");
    }
    public AbsCustComponent form(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f, CrudGeneFormEntPlace _par) {
        api = _core;
        AbsPanel form_ = _core.getCompoFactory().newPageBox();
        facadeGame = _fac;
        translationList = _fact;
        frame = _f;
        firstDirection = null;
        secondDirection = null;
        linked.clear();
        messages.clear();
        CustList<Place> places_ = _fac.getMap().getPlaces();
        int len_ = places_.size();
        int sub_ = 0;
        for (int i = 0; i < len_; i++) {
            Place pl_ = places_.get(i);
            if (pl_ instanceof InitializedPlace) {
                sub_++;
            }
        }
        int curr_ = 0;
        for (int i = 0; i < len_; i++) {
            CustList<IdMap<Direction,BoolVal>> s_ = new CustList<IdMap<Direction,BoolVal>>();
            Place pl_ = places_.get(i);
            if (pl_ instanceof InitializedPlace) {
                messages.addEntry(new EditedCrudPair<Coords, InitializedPlace>(AbsContentComponentModelLevelLinks.coords(i,0,null),(InitializedPlace) pl_), curr_+"/"+(sub_-1)+":"+i+":"+pl_.getName());
                for (int j = 0; j < len_; j++) {
                    IdMap<Direction, BoolVal> dirs_ = dirs((InitializedPlace) pl_, j);
                    s_.add(dirs_);
                }
                curr_++;
            } else {
                for (int j = 0; j < len_; j++) {
                    IdMap<Direction,BoolVal> dirs_ = new IdMap<Direction, BoolVal>();
                    dirs_.addEntry(Direction.UP,BoolVal.FALSE);
                    dirs_.addEntry(Direction.RIGHT,BoolVal.FALSE);
                    dirs_.addEntry(Direction.LEFT,BoolVal.FALSE);
                    dirs_.addEntry(Direction.DOWN,BoolVal.FALSE);
                    s_.add(dirs_);
                }
            }
            linked.add(s_);
        }
        left = new GeneComponentModelLs<EditedCrudPair<Coords, InitializedPlace>>(_core,messages);
        right = new GeneComponentModelLs<EditedCrudPair<Coords, InitializedPlace>>(_core,messages);
        AbsPanel lists_ = _core.getCompoFactory().newLineBox();
        lists_.add(SubscribedTranslationList.line(_core,MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(_core.currentLg())),MessagesEditorSelect.UNIQ_LEFT,left.buildLs()));
        lists_.add(SubscribedTranslationList.line(_core,MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(_core.currentLg())),MessagesEditorSelect.UNIQ_RIGHT,right.buildLs()));
        form_.add(lists_);
        StringMap<String> tf_ = MessagesPkEditor.getMessagesEditorSelectButtonsTr(MessagesPkEditor.getAppliTr(_core.currentLg())).getMapping();
        joinPlacesButton = _core.getCompoFactory().newPlainButton(tf_.getVal(MessagesEditorSelect.PLACES_JOIN));
        joinPlacesButton.addActionListener(new JoinPlacesEvent(this));
        form_.add(joinPlacesButton);
        joinPlacesButton.setEnabled(false);
        unjoinPlacesButton = _core.getCompoFactory().newPlainButton(tf_.getVal(MessagesEditorSelect.PLACES_UNJOIN));
        unjoinPlacesButton.addActionListener(new UnJoinPlacesEvent(this));
        form_.add(unjoinPlacesButton);
        left.getSelect().addListener(new ChangePlaceListEvent(this,true));
        right.getSelect().addListener(new ChangePlaceListEvent(this,false));
        close = _core.getCompoFactory().newPlainButton("\u23F9");
        close.addActionListener(new CloseLinksFormEvent(_par, _fact, translationsGrid, _f));
        form_.add(getClose());
        AbsPanel viewLeft_ = _core.getCompoFactory().newPageBox();
        AbsPanel viewRight_ = _core.getCompoFactory().newPageBox();
        AbsPanel views_ = _core.getCompoFactory().newLineBox();
        viewLeft_.add(_core.getCompoFactory().newPlainLabel(MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(_core.currentLg())).getMapping().getVal(MessagesEditorSelect.UNIQ_VIEW_LEFT)));
        viewRight_.add(_core.getCompoFactory().newPlainLabel(MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(_core.currentLg())).getMapping().getVal(MessagesEditorSelect.UNIQ_VIEW_RIGHT)));
        translationList.removeSubs(frame,getTranslationsGrid());
        leftScroll = _core.getCompoFactory().newAbsScrollPane();
        rightScroll = _core.getCompoFactory().newAbsScrollPane();
        viewLeft_.add(leftScroll);
        viewRight_.add(rightScroll);
        views_.add(viewLeft_);
        views_.add(viewRight_);
        form_.add(views_);
        AbsPanel buttons_ = _core.getCompoFactory().newLineBox();
        buttonsLeft = _core.getCompoFactory().newPageBox();
        buttonsRight = _core.getCompoFactory().newPageBox();
        buttons_.add(buttonsLeft);
        buttons_.add(buttonsRight);
        form_.add(buttons_);
        return _core.getCompoFactory().newAbsScrollPane(form_);
    }

    private IdMap<Direction, BoolVal> dirs(InitializedPlace _pl, int _j) {
        IdMap<Direction,BoolVal> dirs_ = new IdMap<Direction, BoolVal>();
        for (Direction d: Direction.all()) {
            if (linked(_pl, _j, d)) {
                dirs_.addEntry(d,BoolVal.TRUE);
            } else {
                dirs_.addEntry(d,BoolVal.FALSE);
            }
        }
        return dirs_;
    }

    private boolean linked(InitializedPlace _pl, int _j, Direction _dir) {
        boolean link_ = false;
        for (PlaceInterConnectCoords p: _pl.getSavedlinks().entryList()) {
            if (p.getCoords().getNumberPlace() == _j && p.getPlaceInterConnect().getDir() == _dir) {
                link_ = true;
                break;
            }
        }
        return link_;
    }
    public void placeChanged(boolean _left) {
        CustList<EditedCrudPair<Coords, InitializedPlace>> selLeft_ = left.tryRet();
        CustList<EditedCrudPair<Coords, InitializedPlace>> selRight_ = right.tryRet();
        CustList<EditedCrudPair<Coords, InitializedPlace>> sel_;
        CustList<EditedCrudPair<Coords, InitializedPlace>> selOther_;
        translationList.removeSubs(frame,getTranslationsGrid());
        if (_left) {
            sel_ = selLeft_;
            selOther_ = selRight_;
        } else {
            sel_ = selRight_;
            selOther_ = selLeft_;
        }
        if (sel_.size() != 1 || selOther_.size() != 1) {
            leftScroll.setNullViewportView();
            rightScroll.setNullViewportView();
            firstSelected = new NullablePoint();
            secondSelected = new NullablePoint();
            return;
        }
        levelLeft = AbsContentComponentModelLevelLinks.build(api, facadeGame, translationList, frame, (Place) selLeft_.get(0).getValue(), selLeft_.get(0).getKey(), getTranslationsGrid());
        levelLeft.getGrid().addMouseListener(new SelectPtJoinEvent(this, levelLeft, true));
        leftScroll.setViewportView(levelLeft.getForm());
        levelRight = AbsContentComponentModelLevelLinks.build(api, facadeGame, translationList, frame, (Place) selRight_.get(0).getValue(), selRight_.get(0).getKey(), getTranslationsGrid());
        levelRight.getGrid().addMouseListener(new SelectPtJoinEvent(this, levelRight, false));
        rightScroll.setViewportView(levelRight.getForm());
        firstSelected = new NullablePoint();
        secondSelected = new NullablePoint();
    }

    public void selectOrDeselectSide(FormLevelGridLink _g, boolean _left,int _x, int _y) {
        Point pt_ = _g.toPt(_x, _y);
        set(_left, pt_);
        CustList<Direction> possible_ = possible(_g, _x, _y, facadeGame.getMap().getSideLength());
        changeEnableLink(possible_,_left);
    }

    static CustList<Direction> possible(FormLevelGridLink _g, int _x, int _y, int _sideLength) {
        Limits limits_ = Level.limits(_g.getEdited());
        Point typed_ = convert(_x, _y);
        Point topLeft_ = new Point();
        Point bottomRight_ = convert(limits_.getBottomRight().getx() - limits_.getTopLeft().getx() + 1, limits_.getBottomRight().gety() - limits_.getTopLeft().gety() + 1);
        int xLeftScreen_ = topLeft_.getx() * _sideLength;
        int xRightScreen_ = bottomRight_.getx() * _sideLength -1;
        int yTopScreen_ = topLeft_.gety() * _sideLength;
        int yBottomScreen_ = bottomRight_.gety() * _sideLength -1;
        CustList<Direction> possible_ = new CustList<Direction>();
        if (Point.eq(typed_,convert(xLeftScreen_,yTopScreen_))) {
            possible_.add(Direction.UP);
            possible_.add(Direction.LEFT);
        } else if (Point.eq(typed_,convert(xLeftScreen_,yBottomScreen_))) {
            possible_.add(Direction.DOWN);
            possible_.add(Direction.LEFT);
        } else if (Point.eq(typed_,convert(xRightScreen_,yBottomScreen_))) {
            possible_.add(Direction.DOWN);
            possible_.add(Direction.RIGHT);
        } else if (Point.eq(typed_,convert(xRightScreen_,yTopScreen_))) {
            possible_.add(Direction.UP);
            possible_.add(Direction.RIGHT);
        } else if (_x == xLeftScreen_) {
            possible_.add(Direction.LEFT);
        } else if (_x == xRightScreen_) {
            possible_.add(Direction.RIGHT);
        } else if (_y == yTopScreen_) {
            possible_.add(Direction.UP);
        } else if (_y == yBottomScreen_) {
            possible_.add(Direction.DOWN);
        } else if (intersect(_x, _y, xLeftScreen_, xRightScreen_, yTopScreen_, yBottomScreen_)) {
            possible_.add(Direction.DOWN);
        } else if (intersect(_x, _y, xLeftScreen_, xRightScreen_, yBottomScreen_, yTopScreen_)) {
            possible_.add(Direction.UP);
        } else if (_x * 2 < xLeftScreen_ + xRightScreen_){
            possible_.add(Direction.LEFT);
            complete(_x, _y, xRightScreen_, yTopScreen_, yBottomScreen_, possible_, xLeftScreen_);
        } else if (_x * 2 > xLeftScreen_ + xRightScreen_){
            possible_.add(Direction.RIGHT);
            complete(_x, _y, xLeftScreen_, yTopScreen_, yBottomScreen_, possible_, xRightScreen_);
        } else {
            possible_.add(Direction.UP);
            possible_.add(Direction.LEFT);
            possible_.add(Direction.DOWN);
            possible_.add(Direction.RIGHT);
        }
        return possible_;
    }

    private static Point convert(int _x, int _y) {
        return new Point(_x, _y);
    }

    private void changeEnableLink(CustList<Direction> _poss, boolean _left) {
        if (_left) {
            possibleLeft = _poss;
        } else {
            possibleRight = _poss;
        }
        if (_poss.size() == 1) {
            if (_left) {
                firstDirection = _poss.get(0);
            } else {
                secondDirection = _poss.get(0);
            }
        }
        if (!firstSelected.isDefined() || !secondSelected.isDefined()) {
            joinPlacesButton.setEnabled(false);
            return;
        }
        if (possibleLeft.size() > 1) {
            setDirs(selDirLeftButtons,buttonsLeft, possibleLeft, true);
            firstDirection = null;
        }
        if (possibleRight.size() > 1) {
            setDirs(selDirRightButtons,buttonsRight, possibleRight, false);
            secondDirection = null;
        }
        if (atLeatNull(_left)) {
            joinPlacesButton.setEnabled(false);
            return;
        }
        changeJoinEnable();
    }

    private void setDirs(IdMap<Direction, AbsButton> _id, AbsPanel _form, CustList<Direction> _poss, boolean _l) {
        _form.removeAll();
        _id.clear();
        for (Direction p: _poss) {
            AbsButton but_ = api.getCompoFactory().newPlainButton(dirMessages.getVal(p));
            but_.addActionListener(new SelectDirJoinEvent(this,p, _l));
            _form.add(but_);
            _id.addEntry(p,but_);
        }
    }


    public void selectDir(Direction _dir, boolean _left) {
        if (_left) {
            firstDirection = _dir;
        } else {
            secondDirection = _dir;
        }
        if (atLeatNull(_left)) {
            joinPlacesButton.setEnabled(false);
            return;
        }
        changeJoinEnable();
    }

    private boolean atLeatNull(boolean _left) {
        if (firstDirection == null || secondDirection == null) {
            return true;
        }
        BoolVal lk_;
        if (_left) {
            lk_ = linked.get(levelLeft.getKey().getNumberPlace()).get(levelRight.getKey().getNumberPlace()).getVal(firstDirection);
        } else {
            lk_ = linked.get(levelRight.getKey().getNumberPlace()).get(levelLeft.getKey().getNumberPlace()).getVal(secondDirection);
        }
        return lk_ == BoolVal.TRUE;
    }

    private void changeJoinEnable() {
        joinPlacesButton.setEnabled(firstDirection == secondDirection.getOpposite());
    }
    public void joinPlaces() {
        EditedCrudPair<Coords, InitializedPlace> selLeft_ = left.tryRet().get(0);
        EditedCrudPair<Coords, InitializedPlace> selRight_ = right.tryRet().get(0);
        Limits limitsFirst_ = Level.limits(levelLeft.getEdited());
        Limits limitsSecond_ = Level.limits(levelRight.getEdited());
        if (firstDirection == Direction.UP) {
            selLeft_.getValue().getSavedlinks().put(new PlaceInterConnect(convert(firstSelected.getPoint().getx(), limitsFirst_.getTopLeft().gety()),firstDirection),levelRight.build(convert(secondSelected.getPoint().getx(), limitsSecond_.getBottomRight().gety())));
            selRight_.getValue().getSavedlinks().put(new PlaceInterConnect(convert(secondSelected.getPoint().getx(), limitsSecond_.getBottomRight().gety()),secondDirection),levelLeft.build(convert(firstSelected.getPoint().getx(), limitsFirst_.getTopLeft().gety())));
        }
        if (firstDirection == Direction.DOWN) {
            selLeft_.getValue().getSavedlinks().put(new PlaceInterConnect(convert(firstSelected.getPoint().getx(), limitsFirst_.getBottomRight().gety()),firstDirection),levelRight.build(convert(secondSelected.getPoint().getx(), limitsSecond_.getTopLeft().gety())));
            selRight_.getValue().getSavedlinks().put(new PlaceInterConnect(convert(secondSelected.getPoint().getx(), limitsSecond_.getTopLeft().gety()),secondDirection),levelLeft.build(convert(firstSelected.getPoint().getx(), limitsFirst_.getBottomRight().gety())));
        }
        if (firstDirection == Direction.LEFT) {
            selLeft_.getValue().getSavedlinks().put(new PlaceInterConnect(convert(limitsFirst_.getTopLeft().getx(), firstSelected.getPoint().gety()),firstDirection),levelRight.build(convert(limitsSecond_.getBottomRight().getx(), secondSelected.getPoint().gety())));
            selRight_.getValue().getSavedlinks().put(new PlaceInterConnect(convert(limitsSecond_.getBottomRight().getx(), secondSelected.getPoint().gety()),secondDirection),levelLeft.build(convert(limitsFirst_.getTopLeft().getx(), firstSelected.getPoint().gety())));
        }
        if (firstDirection == Direction.RIGHT) {
            selLeft_.getValue().getSavedlinks().put(new PlaceInterConnect(convert(limitsFirst_.getBottomRight().getx(), firstSelected.getPoint().gety()),firstDirection),levelRight.build(convert(limitsSecond_.getTopLeft().getx(), secondSelected.getPoint().gety())));
            selRight_.getValue().getSavedlinks().put(new PlaceInterConnect(convert(limitsSecond_.getTopLeft().getx(), secondSelected.getPoint().gety()),secondDirection),levelLeft.build(convert(limitsFirst_.getBottomRight().getx(), firstSelected.getPoint().gety())));
        }
        linked.get(selLeft_.getKey().getNumberPlace()).get(selRight_.getKey().getNumberPlace()).set(firstDirection,BoolVal.TRUE);
        linked.get(selRight_.getKey().getNumberPlace()).get(selLeft_.getKey().getNumberPlace()).set(secondDirection,BoolVal.TRUE);
        translationList.removeSubs(frame,getTranslationsGrid());
        leftScroll.setNullViewportView();
        rightScroll.setNullViewportView();
        firstSelected = new NullablePoint();
        secondSelected = new NullablePoint();
        firstDirection = null;
        secondDirection = null;
        joinPlacesButton.setEnabled(false);
    }
    public void unjoinPlaces() {
        CustList<EditedCrudPair<Coords, InitializedPlace>> selListLeft_ = left.tryRet();
        CustList<EditedCrudPair<Coords, InitializedPlace>> selListRight_ = right.tryRet();
        if (selListLeft_.size() != 1 || selListRight_.size() != 1) {
            return;
        }
        EditedCrudPair<Coords, InitializedPlace> selLeft_ = selListLeft_.get(0);
        EditedCrudPair<Coords, InitializedPlace> selRight_ = selListRight_.get(0);
        selLeft_.getValue().setSavedlinks(filter(selLeft_.getValue().getSavedlinks(),selRight_.getKey().getNumberPlace()));
        selRight_.getValue().setSavedlinks(filter(selRight_.getValue().getSavedlinks(),selLeft_.getKey().getNumberPlace()));
        for (EntryCust<Direction,BoolVal> e:linked.get(selLeft_.getKey().getNumberPlace()).get(selRight_.getKey().getNumberPlace()).entryList()) {
            e.setValue(BoolVal.FALSE);
        }
        for (EntryCust<Direction,BoolVal> e:linked.get(selRight_.getKey().getNumberPlace()).get(selLeft_.getKey().getNumberPlace()).entryList()) {
            e.setValue(BoolVal.FALSE);
        }
        translationList.removeSubs(frame,getTranslationsGrid());
        leftScroll.setNullViewportView();
        rightScroll.setNullViewportView();
        firstSelected = new NullablePoint();
        secondSelected = new NullablePoint();
        joinPlacesButton.setEnabled(false);
    }
    private PlaceInterConnects filter(PlaceInterConnects _f, int _exc) {
        PlaceInterConnects cp_ = new PlaceInterConnects();
        for (PlaceInterConnectCoords c: _f.getList()) {
            if (c.getCoords().getNumberPlace() != _exc) {
                cp_.addEntry(c.getPlaceInterConnect(),c.getCoords());
            }
        }
        return cp_;
    }

    private void set(boolean _left, Point _pt) {
        if (_left) {
            firstSelected.setPoint(_pt);
        } else {
            secondSelected.setPoint(_pt);
        }
    }

    private static void complete(int _x, int _y, int _xRightScreen, int _yTopScreen, int _yBottomScreen, CustList<Direction> _possible, int _xBound) {
        Rate xMid_ = Rate.divide(Rate.plus(new Rate(_xRightScreen), new Rate(_xBound)), new Rate(2));
        Rate yMid_ = Rate.divide(Rate.plus(new Rate(_yTopScreen), new Rate(_yBottomScreen)), new Rate(2));
        if (align(_x, _y, _xBound, _yTopScreen, xMid_, yMid_)) {
            _possible.add(Direction.UP);
        } else  if (align(_x, _y, _xBound, _yBottomScreen, xMid_, yMid_)) {
            _possible.add(Direction.DOWN);
        }
    }

    private static boolean align(int _x, int _y, int _xLeftScreen, int _yTopScreen, Rate _xMid, Rate _yMid) {
        Rate a_ = Rate.minus(new Rate(_xLeftScreen),new Rate(_x));
        Rate b_ = Rate.minus(new Rate(_yMid),new Rate(_y));
        Rate c_ = Rate.minus(new Rate(_xMid),new Rate(_x));
        Rate d_ = Rate.minus(new Rate(_yTopScreen),new Rate(_y));
        return Rate.eq(Rate.multiply(a_, b_), Rate.multiply(c_, d_));
    }

    private static boolean intersect(long _x, long _y, long _xLeftScreen, long _xRightScreen, long _yTopScreen, long _yBottomScreen) {
        Rate aOne_ = dir(_yTopScreen,_y,_xRightScreen,_x);
        Rate aTwo_ = dir(_yTopScreen,_y,_xLeftScreen,_x);
        Rate bOne_ = ord(_yTopScreen,aOne_,_xRightScreen);
        Rate bTwo_ = ord(_yTopScreen,aTwo_,_xLeftScreen);
        Rate xOne_ = Rate.divide(Rate.minus(new Rate(_yBottomScreen),bOne_),aOne_);
        Rate xTwo_ = Rate.divide(Rate.minus(new Rate(_yBottomScreen),bTwo_),aTwo_);
        return inRangStr(xOne_, _xLeftScreen, _xRightScreen) && inRangStr(xTwo_, _xLeftScreen, _xRightScreen);
    }

    private static boolean inRangStr(Rate _rate, long _xLeftScreen, long _xRightScreen) {
        return !Rate.eq(_rate,new Rate(_xLeftScreen))&&!Rate.eq(_rate,new Rate(_xRightScreen))&&_rate.inRange(new Rate(_xLeftScreen), new Rate(_xRightScreen));
    }

    private static Rate dir(long _yTop, long _y, long _xBound, long _x) {
        return new Rate(_yTop - _y, _xBound - _x);
    }
    private static Rate ord(long _yTop, Rate _d, long _xBound) {
        return Rate.minus(new Rate(_yTop),Rate.multiply(_d,new Rate(_xBound)));
    }

    public IdList<SubscribedTranslation> getTranslationsGrid() {
        return translationsGrid;
    }

    public AbsButton getClose() {
        return close;
    }

    public IdMap<Direction, AbsButton> getSelDirLeftButtons() {
        return selDirLeftButtons;
    }

    public GeneComponentModelLs<EditedCrudPair<Coords, InitializedPlace>> getLeft() {
        return left;
    }

    public IdMap<Direction, AbsButton> getSelDirRightButtons() {
        return selDirRightButtons;
    }

    public GeneComponentModelLs<EditedCrudPair<Coords, InitializedPlace>> getRight() {
        return right;
    }

    public FormLevelGridLink getLevelLeft() {
        return levelLeft;
    }

    public FormLevelGridLink getLevelRight() {
        return levelRight;
    }

    public AbsButton getJoinPlacesButton() {
        return joinPlacesButton;
    }

    public AbsButton getUnjoinPlacesButton() {
        return unjoinPlacesButton;
    }
}
