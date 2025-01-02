package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.levels.*;
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
