package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.map.enums.Direction;
import aiki.map.levels.*;
import aiki.map.places.InitializedPlace;
import aiki.map.places.Place;
import aiki.map.tree.util.*;
import aiki.map.util.*;
import aiki.util.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class FormLevelGrid extends FormLevelGridCommon {
    private final FormBlockTile formBlockTile = new FormBlockTile();
    private AbsSpinner rows;
    private AbsSpinner cols;
    private AbsPaintableLabel grid;
    private AbsScrollPane element;
    private AbsButton applyPrepend;
    private AbsButton applyAppend;
    private AbsPanel form;
    private int rowsCount;
    private int colsCount;
    private final IdList<SubscribedTranslation> translations = new IdList<SubscribedTranslation>();
    private Point screen;
    private final CustList<EditedCrudPair<Coords,EditedCrudPair<InitializedPlace,PlaceInterConnects>>> links = new CustList<EditedCrudPair<Coords, EditedCrudPair<InitializedPlace,PlaceInterConnects>>>();
    private Coords selectedPlace = new Coords();

    public FormLevelGrid(AbstractProgramInfos _a, FacadeGame _f, AbsCommonFrame _fr, SubscribedTranslationList _i) {
        super(_a, _f, _fr, _i);
    }
    public void setupGridDims(Points<Block> _bk, Points<int[][]> _f) {
        links.clear();
        CustList<Place> psl_ = getFacadeGame().getMap().getPlaces();
        int nbPl_ = psl_.size();
        for (int i = 0; i < nbPl_; i++) {
            Place pl_ = psl_.get(i);
            if (pl_ instanceof InitializedPlace) {
                links.add(new EditedCrudPair<Coords, EditedCrudPair<InitializedPlace,PlaceInterConnects>>(AbsContentComponentModelLevelLinks.coords(i,0,null),new EditedCrudPair<InitializedPlace, PlaceInterConnects>((InitializedPlace) pl_,ConverterCommonMapUtil.copyPlaceInterConnects(((InitializedPlace) pl_).getSavedlinks()))));
            }
        }
        setupForeground(_f);
        setEdited(_bk);
        Limits limits_ = Level.limits(_bk);
        Point topLeft_ = limits_.getTopLeft();
        Point bottomRight_ = limits_.getBottomRight();
        rowsCount = NumberUtil.max(1,bottomRight_.gety() - topLeft_.gety());
        colsCount = NumberUtil.max(1,bottomRight_.getx() - topLeft_.getx());
        getTopLeftRel().sety((short) 0);
        getTopLeftRel().setx((short) 0);
        setupGrid();
    }

    public void prepend() {
        int deltaRows_ = rows.getValue();
        rowsCount += deltaRows_;
        getTopLeftRel().sety((short) (getTopLeftRel().gety()-deltaRows_));
        int deltaCols_ = cols.getValue();
        colsCount += deltaCols_;
        getTopLeftRel().setx((short) (getTopLeftRel().getx()-deltaCols_));
        refreshImg(getFormBlockTile().getEdited().getWidth(), getFormBlockTile().getEdited().getHeight());
    }
    public void append() {
        rowsCount += rows.getValue();
        colsCount += cols.getValue();
        refreshImg(getFormBlockTile().getEdited().getWidth(), getFormBlockTile().getEdited().getHeight());
    }
    public void readjust(Limits _previous, Limits _next) {
        int deltaRows_ = _previous.getTopLeft().gety() - _next.getTopLeft().gety();
        int deltaCols_ = _previous.getTopLeft().getx() - _next.getTopLeft().getx();
        getTopLeftRel().sety((short) (getTopLeftRel().gety()+deltaRows_));
        getTopLeftRel().setx((short) (getTopLeftRel().getx()+deltaCols_));
        Coords target_ = new Coords(getSelectedPlace());
        target_.getLevel().setPoint(new Point((short) 0,(short) 0));
        for (EditedCrudPair<Coords, EditedCrudPair<InitializedPlace, PlaceInterConnects>> e: links) {
            if (Coords.eq(e.getKey(),getSelectedPlace())) {
                for (PlaceInterConnectCoords p:e.getValue().getValue().getList()) {
                    move(_previous,_next, p.getPlaceInterConnect().getSource(), p.getPlaceInterConnect().getDir());
                }
            } else {
                for (PlaceInterConnectCoords p:e.getValue().getValue().getList()) {
                    Coords v_ = new Coords(p.getCoords());
                    v_.getLevel().setPoint(new Point((short) 0,(short) 0));
                    if (Coords.eq(target_,v_)) {
                        move(_previous,_next, p.getCoords().getLevel().getPoint(), p.getPlaceInterConnect().getDir().getOpposite());
                    }
                }
            }
        }
    }

    private void move(Limits _previous, Limits _next, Point _src, Direction _dir) {
        if (_previous.getTopLeft().getx() == _previous.getBottomRight().getx()) {
            if (_dir == Direction.LEFT) {
                _src.setx(_next.getTopLeft().getx());
            }
            if (_dir == Direction.RIGHT) {
                _src.setx(_next.getBottomRight().getx());
            }
        } else if (_src.getx() == _previous.getTopLeft().getx()) {
            _src.setx(_next.getTopLeft().getx());
        } else if (_src.getx() == _previous.getBottomRight().getx()) {
            _src.setx(_next.getBottomRight().getx());
        }
        if (_previous.getTopLeft().gety() == _previous.getBottomRight().gety()) {
            if (_dir == Direction.UP) {
                _src.sety(_next.getTopLeft().gety());
            }
            if (_dir == Direction.DOWN) {
                _src.sety(_next.getBottomRight().gety());
            }
        } else if (_src.gety() == _previous.getTopLeft().gety()) {
            _src.sety(_next.getTopLeft().gety());
        } else if (_src.gety() == _previous.getBottomRight().gety()) {
            _src.sety(_next.getBottomRight().gety());
        }
    }

    public void setupGrid() {
        AbsCompoFactory c_ = getApi().getCompoFactory();
        AbsPanel form_ = c_.newPageBox();
        rows = c_.newSpinner(0,0,Integer.MAX_VALUE,1);
        form_.add(rows);
        cols = c_.newSpinner(0,0,Integer.MAX_VALUE,1);
        form_.add(cols);
        applyPrepend = c_.newPlainButton("<-");
        applyPrepend.addActionListener(new PrependTileBlockEvent(this));
        form_.add(applyPrepend);
        applyAppend = c_.newPlainButton("->");
        applyAppend.addActionListener(new AppendTileBlockEvent(this));
        form_.add(applyAppend);
        grid = c_.newAbsPaintableLabel();

        grid.addMouseListener(new FormBlockTileEvent(this));
        refreshImg(0, 0);
        grid.setLineBorder(GuiConstants.BLACK);
        form_.add(getGrid());
        element = c_.newAbsScrollPane();
        form_.add(element);
        form = form_;
    }


    public void view(int _x, int _y) {
        screen = toPt(_x, _y);
        getForegroundEdited().clear();
        EntryCust<Point, Block> e_ = Level.getEntryBlockByPoint(screen, getEdited());
        if (e_ == null) {
            formBlockTile.build(this, screen);
            common();
            formBlockTile.feedForm();
        } else {
            screen = e_.getKey();
            formBlockTile.build(this, screen);
            common();
            Block bk_ = e_.getValue();
            formBlockTile.feedForm(bk_);
        }
        element.setViewportView(formBlockTile.getForm());
        getFrame().pack();
    }

    public void checkDims() {
        String txt_ = formBlockTile.getDims().getText();
        StringList parts_ = StringUtil.splitChar(txt_, ':');
        int parseWidth_ = NumberUtil.parseInt(parts_.first());
        int parseHeight_ = NumberUtil.parseInt(parts_.last());
        if (NumberUtil.signum(parseWidth_) + NumberUtil.signum(parseHeight_) < 2) {
            return;
        }
        Dims ds_ = new Dims(parseWidth_, parseHeight_);
        int x_ = screen.getx();
        int y_ = screen.gety();
        int xb_ = x_ + ds_.getWidth();
        int yb_ = y_ + ds_.getHeight();
        for (int i = x_; i < xb_; i++) {
            for (int j = y_; j < yb_; j++) {
                EntryCust<Point, Block> e_ = Level.getEntryBlockByPoint(new Point((short) i, (short) j), getEdited());
                if (other(e_, screen)) {
                    return;
                }
            }
        }
        formBlockTile.getEdited().setHeight(ds_.getHeight());
        formBlockTile.getEdited().setWidth(ds_.getWidth());
        refreshImg(getFormBlockTile().getEdited().getWidth(), getFormBlockTile().getEdited().getHeight());
    }

    public void refreshImg(int _w, int _h) {
        int side_ = getFacadeGame().getMap().getSideLength();
        Limits limits_ = Level.limits(getEdited());
        Point topLeft_ = limits_.getTopLeft();
        AbstractImageFactory imgFact_ = getApi().getImageFactory();
        AbstractImage img_ = ConverterCommonMapUtil.buildImg(getApi(), getFacadeGame(), getEdited(),getTopLeftRel(),rowsCount,colsCount);
        if (NumberUtil.signum(_w) + NumberUtil.signum(_h) == 2) {
            ImageArrayBaseSixtyFour imgArr_ = getTranslationList().getImgRetrieverBlocks().all(getFacadeGame()).getVal(formBlockTile.getTileFileName().getName().tryRet());
            int[][] pixels_;
            if (imgArr_ != null) {
                pixels_ = imgArr_.getImage();
            } else {
                pixels_ = new int[0][];
            }
            if (pixels_.length > 0) {
                int height_ = _h * side_;
                int width_ = _w * side_;
                img_.drawImage(ConverterGraphicBufferedImage.decodeToImage(getApi().getImageFactory(), pixels_),(screen.getx() - topLeft_.getx() - getTopLeftRel().getx()) * side_+NumberUtil.quot(width_ - pixels_[0].length, 2),(screen.gety() - topLeft_.gety() - getTopLeftRel().gety()) * side_+NumberUtil.quot(height_ - pixels_.length, 2));
            }
            img_.drawImage(ConverterCommonMapUtil.buildImgFore(getApi(), getFacadeGame(),limits_, getForeground(),getTopLeftRel(),rowsCount,colsCount),-getTopLeftRel().getx() * side_, -getTopLeftRel().gety() * side_);
            img_.drawImage(ConverterCommonMapUtil.buildImgFore(getApi(), getFacadeGame(),limits_, getForegroundEdited(),getTopLeftRel(),rowsCount,colsCount),-getTopLeftRel().getx() * side_, -getTopLeftRel().gety() * side_);
            img_.setColor(GuiConstants.BLACK);
            img_.drawRect((screen.getx() - topLeft_.getx() - getTopLeftRel().getx()) * side_,(screen.gety() - topLeft_.gety() - getTopLeftRel().gety()) * side_, _w *side_, _h *side_);
        } else {
            img_.drawImage(ConverterCommonMapUtil.buildImgFore(getApi(), getFacadeGame(),limits_, getForeground(),getTopLeftRel(),rowsCount,colsCount),-getTopLeftRel().getx() * side_, -getTopLeftRel().gety() * side_);
            img_.drawImage(ConverterCommonMapUtil.buildImgFore(getApi(), getFacadeGame(),limits_, getForegroundEdited(),getTopLeftRel(),rowsCount,colsCount),-getTopLeftRel().getx() * side_, -getTopLeftRel().gety() * side_);
        }
        grid.setIcon(imgFact_,img_);
    }
    private boolean other(EntryCust<Point, Block> _e, Point _id) {
        return _e != null && !Point.eq(_e.getKey(), _id);
    }

    private void common() {
        FormDataMap.baseSelectImage(formBlockTile.getTileFileName());
        formBlockTile.getTileFileName().getName().getSelectUniq().getSelect().addListener(new RefreshBlockTileSelection(this));
        subs(formBlockTile.getTileFileName().subs());
    }

    public void subs(IdList<SubscribedTranslation> _ls) {
        IdList<SubscribedTranslation> subs_ = getTranslationList().getSubscribedTranslations().getVal(getFrame());
        subs_.removeAllElements(translations);
        subs_.addAllElts(_ls);
        translations.addAllElts(_ls);
    }

    public FormBlockTile getFormBlockTile() {
        return formBlockTile;
    }

    public AbsPanel getForm() {
        return form;
    }

    public AbsSpinner getCols() {
        return cols;
    }

    public AbsSpinner getRows() {
        return rows;
    }

    public AbsButton getApplyAppend() {
        return applyAppend;
    }

    public AbsButton getApplyPrepend() {
        return applyPrepend;
    }

    public AbsPaintableLabel getGrid() {
        return grid;
    }

    public Coords getSelectedPlace() {
        return selectedPlace;
    }

    public void setSelectedPlace(Coords _s) {
        this.selectedPlace = _s;
    }

    public CustList<EditedCrudPair<Coords, EditedCrudPair<InitializedPlace, PlaceInterConnects>>> getLinks() {
        return links;
    }
}
