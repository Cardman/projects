package aiki.gui.components.labels;
import java.awt.Color;
import java.awt.Dimension;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.MoveData;
import aiki.util.SortingMove;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;
import code.images.ConverterBufferedImage;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class TmLabel extends SelectableLabel {

    private static final String SPACE = " ";

    //private static final String TAB = "\t";

    private static final int FIRST_LINE = HEIGTH_CHARS;

    private static final int SECOND_LINE = FIRST_LINE + HEIGTH_CHARS;

    private static final int TYPE_WIDTH = 20;

    private SortingMove move;

    private String moveName;

    private String types;

    private String priority;

    private String pp;

    private String target;

    private String price;

    private int xMoveName;

    private int xTypes;

    private int xPriority;

    private int xPp;

    private int xTarget;

//    private int xPrice;

    private NatStringTreeMap<Integer> colorsTypes;

    public TmLabel(SortingMove _move, FacadeGame _facade, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        colorsTypes = new NatStringTreeMap<Integer>();
        move = _move;
        moveName = _move.getName();
        MoveData move_ = _facade.getData().getMove(move.getKeyName());
        StringList types_ = new StringList();
        for (String t: move_.getTypes()) {
            String type_ = _facade.translateType(t);
            String rgb_ = _facade.getData().getTypesColors().getVal(t);
            int c_ = ConverterBufferedImage.getIntColor(rgb_, DataBase.SEPARATOR_RGB);
            colorsTypes.put(type_, c_);
            types_.add(type_);
        }
        types_.sort();
        String target_ = _facade.translatedTargets(_move.getTargetChoice());
        types = StringUtil.concat(SPACE, StringUtil.join(types_, SPACE));
        priority = StringUtil.concatNbs(SPACE,move_.getPriority());
        pp = StringUtil.concatNbs(SPACE,move_.getPp());
        target = StringUtil.concat(SPACE,target_);
        price = StringUtil.concatNbs(SPACE,_move.getPrice());
        //setText(moveName+types+priority+pp+target+price);
        //setOpaque(true);
        //int width_ = getFontMetrics(getFont()).stringWidth(getText());
        //setPreferredSize(new Dimension(width_, SECOND_LINE));
    }

    public int getNameWidth() {
        return stringWidth(moveName);
    }

    public int getTypesWidth() {
        return stringWidth(StringUtil.concat(SPACE,types));
    }

    public int getPriorityWidth() {
        return stringWidth(StringUtil.concat(SPACE,priority));
    }

    public int getPpWidth() {
        return stringWidth(StringUtil.concat(SPACE,pp));
    }

    public int getTargetWidth() {
        return stringWidth(StringUtil.concat(SPACE,target));
    }

    public int getPriceWidth() {
        return stringWidth(StringUtil.concat(SPACE,price));
    }

    public void setPreferredSize(int _width) {
        setPreferredSize(new Dimension(_width, SECOND_LINE));
    }

    public void setxMoveName(int _xMoveName) {
        xMoveName = _xMoveName;
    }

    public void setxTypes(int _xTypes) {
        xTypes = _xTypes;
    }

    public void setxPriority(int _xPriority) {
        xPriority = _xPriority;
    }

    public void setxPp(int _xPp) {
        xPp = _xPp;
    }

    public void setxTarget(int _xTarget) {
        xTarget = _xTarget;
    }

//    public void setxPrice(int _xPrice) {
//        xPrice = _xPrice;
//    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(Color.BLACK);
        _g.drawString(moveName, 0, FIRST_LINE);
        int w_;
        w_ = xMoveName;
        _g.drawString(types, w_, FIRST_LINE);
        w_ += xTypes;
        _g.drawString(priority, w_, FIRST_LINE);
        w_ += xPriority;
        _g.drawString(pp, w_, FIRST_LINE);
        w_ += xPp;
        _g.drawString(target, w_, FIRST_LINE);
        w_ += xTarget;
        _g.drawString(price, w_, FIRST_LINE);
        //_g.drawString(getText(), 0, FIRST_LINE);
        int x_ = IndexConstants.SIZE_EMPTY;
        for (String t: colorsTypes.getKeys()) {
            _g.setColor(colorsTypes.getVal(t));
            int xLeft_ = x_;
            _g.fillRect(xLeft_, FIRST_LINE, TYPE_WIDTH, FIRST_LINE);
            x_ += TYPE_WIDTH;
        }
        super.paintComponent(_g);
    }
}
