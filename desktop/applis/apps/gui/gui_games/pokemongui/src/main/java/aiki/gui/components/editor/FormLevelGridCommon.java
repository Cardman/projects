package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.map.util.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.core.*;

public abstract class FormLevelGridCommon {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final AbsCommonFrame frame;
    private final SubscribedTranslationList translationList;
    private Points<Block> edited = new PointsBlock();
    private final Point topLeftRel = new Point(0, 0);
    private final Points<int[][]> foreground = new PointsArr();
    private final Points<int[][]> foregroundEdited = new PointsArr();

    protected FormLevelGridCommon(AbstractProgramInfos _a, FacadeGame _f, AbsCommonFrame _fr, SubscribedTranslationList _i) {
        api = _a;
        facadeGame = _f;
        frame = _fr;
        translationList = _i;
    }

    static boolean edited(Point _pt, Points<int[][]> _foreground, Points<int[][]> _foregroundEdited) {
        return _foreground.contains(_pt) || _foregroundEdited.contains(_pt);
    }

    public void setupGridDims(Points<Block> _bk, Coords _coords, Place _pl, Level _lev) {
        Points<int[][]> frontTiles_ = Level.getLevelForegroundImage(getFacadeGame().getData(), _coords, _pl,_lev);
        if (_pl instanceof City && !_coords.isInside()) {
            for (Point b: ((City)_pl).getBuildings().getKeys()) {
                frontTiles_.put(new Point(b),new int[0][]);
            }
        }
        if (_pl instanceof League && ((League)_pl).getBegin().isDefined() && _coords.getLevel().getLevelIndex() == 0) {
            frontTiles_.put(new Point(((League)_pl).getBegin().getPoint()),new int[0][]);
        }
        LevelLeague prev_;
        if (_pl instanceof League && _coords.getLevel().getLevelIndex() > 0){
            prev_ = ((League)_pl).getRooms().get(_coords.getLevel().getLevelIndex()-1);
        } else {
            prev_ = null;
        }
        if (prev_ != null && prev_.getNextLevelTarget().isDefined()) {
            frontTiles_.put(new Point(prev_.getNextLevelTarget().getPoint()),new int[0][]);
        }
        setupGridDims(_bk, frontTiles_);
    }
    public abstract void setupGridDims(Points<Block> _bk, Points<int[][]> _f);
    public void setupForeground(Points<int[][]> _f) {
        foreground.clear();
        foreground.addAllEntries(_f);
    }

    public Point toPt(int _x, int _y) {
        Limits limits_ = Level.limits(edited);
        Point topLeft_ = limits_.getTopLeft();
        int max_ = facadeGame.getData().getMap().getSideLength();
        int i_ = NumberUtil.quot(_x, max_) + topLeft_.getx() + topLeftRel.getx();
        int j_ = NumberUtil.quot(_y, max_) + topLeft_.gety() + topLeftRel.gety();
        return new Point(i_, j_);
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


    public Points<int[][]> getForeground() {
        return foreground;
    }

    public Points<int[][]> getForegroundEdited() {
        return foregroundEdited;
    }

    public Points<Block> getEdited() {
        return edited;
    }

    public void setEdited(Points<Block> _e) {
        this.edited = _e;
    }

    public Point getTopLeftRel() {
        return topLeftRel;
    }
}
