package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.tree.util.*;
import aiki.map.util.*;
import aiki.util.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class FormLevelGrid {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final AbsScrollPane container;
    private final AbsCommonFrame frame;
    private final SubscribedTranslationList translationList;
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
    private Points<Block> edited = new PointsBlock();
    private final Point topLeftRel = new Point((short) 0,(short) 0);
    private final Points<int[][]> foreground = new PointsArr();
    private final Points<int[][]> foregroundEdited = new PointsArr();
    private Point screen;

    public FormLevelGrid(AbstractProgramInfos _a, FacadeGame _f, AbsScrollPane _c, AbsCommonFrame _fr, SubscribedTranslationList _i) {
        api = _a;
        facadeGame = _f;
        container = _c;
        frame = _fr;
        translationList = _i;
    }
    public void setupGridDims(Points<Block> _bk, Points<int[][]> _f) {
        setupForeground(_f);
        edited = _bk;
        Limits limits_ = Level.limits(_bk);
        Point topLeft_ = limits_.getTopLeft();
        Point bottomRight_ = limits_.getBottomRight();
        rowsCount = NumberUtil.max(1,bottomRight_.gety() - topLeft_.gety());
        colsCount = NumberUtil.max(1,bottomRight_.getx() - topLeft_.getx());
        topLeftRel.sety((short) 0);
        topLeftRel.setx((short) 0);
        setupGrid();
    }

    public void setupForeground(Points<int[][]> _f) {
        foreground.clear();
        foreground.addAllEntries(_f);
    }

    public void prepend() {
        int deltaRows_ = rows.getValue();
        rowsCount += deltaRows_;
        topLeftRel.sety((short) (topLeftRel.gety()-deltaRows_));
        int deltaCols_ = cols.getValue();
        colsCount += deltaCols_;
        topLeftRel.setx((short) (topLeftRel.getx()-deltaCols_));
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
        topLeftRel.sety((short) (topLeftRel.gety()+deltaRows_));
        topLeftRel.setx((short) (topLeftRel.getx()+deltaCols_));
    }
    public void setupGrid() {
        AbsCompoFactory c_ = api.getCompoFactory();
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
        foregroundEdited.clear();
        EntryCust<Point, Block> e_ = Level.getEntryBlockByPoint(screen, edited);
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
        frame.pack();
    }
    public Point toPt(int _x, int _y) {
        Limits limits_ = Level.limits(edited);
        Point topLeft_ = limits_.getTopLeft();
        int max_ = facadeGame.getData().getMap().getSideLength();
        int i_ = NumberUtil.quot(_x, max_) + topLeft_.getx() + topLeftRel.getx();
        int j_ = NumberUtil.quot(_y, max_) + topLeft_.gety() + topLeftRel.gety();
        return new Point((short) i_, (short) j_);
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
                EntryCust<Point, Block> e_ = Level.getEntryBlockByPoint(new Point((short) i, (short) j), edited);
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
        int side_ = facadeGame.getMap().getSideLength();
        Limits limits_ = Level.limits(edited);
        Point topLeft_ = limits_.getTopLeft();
        AbstractImageFactory imgFact_ = api.getImageFactory();
        AbstractImage img_ = ConverterCommonMapUtil.buildImg(api,facadeGame,edited,topLeftRel,rowsCount,colsCount);
        if (NumberUtil.signum(_w) + NumberUtil.signum(_h) == 2) {
            ImageArrayBaseSixtyFour imgArr_ = translationList.getImgRetrieverBlocks().all(facadeGame).getVal(formBlockTile.getTileFileName().getName().tryRet());
            int[][] pixels_;
            if (imgArr_ != null) {
                pixels_ = imgArr_.getImage();
            } else {
                pixels_ = new int[0][];
            }
            if (pixels_.length > 0) {
                int height_ = _h * side_;
                int width_ = _w * side_;
                img_.drawImage(ConverterGraphicBufferedImage.decodeToImage(api.getImageFactory(), pixels_),(screen.getx() - topLeft_.getx() - topLeftRel.getx()) * side_+NumberUtil.quot(width_ - pixels_[0].length, 2),(screen.gety() - topLeft_.gety() - topLeftRel.gety()) * side_+NumberUtil.quot(height_ - pixels_.length, 2));
            }
            img_.drawImage(ConverterCommonMapUtil.buildImgFore(api,facadeGame,limits_,foreground,topLeftRel,rowsCount,colsCount),-topLeftRel.getx() * side_, -topLeftRel.gety() * side_);
            img_.drawImage(ConverterCommonMapUtil.buildImgFore(api,facadeGame,limits_,foregroundEdited,topLeftRel,rowsCount,colsCount),-topLeftRel.getx() * side_, -topLeftRel.gety() * side_);
            img_.setColor(GuiConstants.BLACK);
            img_.drawRect((screen.getx() - topLeft_.getx() - topLeftRel.getx()) * side_,(screen.gety() - topLeft_.gety() - topLeftRel.gety()) * side_, _w *side_, _h *side_);
        } else {
            img_.drawImage(ConverterCommonMapUtil.buildImgFore(api,facadeGame,limits_,foreground,topLeftRel,rowsCount,colsCount),-topLeftRel.getx() * side_, -topLeftRel.gety() * side_);
            img_.drawImage(ConverterCommonMapUtil.buildImgFore(api,facadeGame,limits_,foregroundEdited,topLeftRel,rowsCount,colsCount),-topLeftRel.getx() * side_, -topLeftRel.gety() * side_);
        }
        grid.setIcon(imgFact_,img_);
    }
    private boolean other(EntryCust<Point, Block> _e, Point _id) {
        return _e != null && !Point.eq(_e.getKey(), _id);
    }

    private void common() {
        FormDataMap.baseSelectImage(formBlockTile.getTileFileName());
        formBlockTile.getTileFileName().getName().getSelectUniq().getSelect().addListener(new RefreshBlockTileSelection(this));
        IdList<SubscribedTranslation> subs_ = translationList.getSubscribedTranslations().getVal(frame);
        subs_.removeAllElements(translations);
        IdList<SubscribedTranslation> next_ = formBlockTile.getTileFileName().subs();
        subs_.addAllElts(next_);
        translations.addAllElts(next_);
    }

    public AbstractProgramInfos getApi() {
        return api;
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public FacadeGame getFacadeGame() {
        return facadeGame;
    }

    public SubscribedTranslationList getTranslationList() {
        return translationList;
    }

    public FormBlockTile getFormBlockTile() {
        return formBlockTile;
    }

    public AbsPanel getForm() {
        return form;
    }

    public AbsScrollPane getContainer() {
        return container;
    }

    public Points<int[][]> getForeground() {
        return foreground;
    }

    public Points<int[][]> getForegroundEdited() {
        return foregroundEdited;
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

    public Points<Block> getEdited() {
        return edited;
    }

}
