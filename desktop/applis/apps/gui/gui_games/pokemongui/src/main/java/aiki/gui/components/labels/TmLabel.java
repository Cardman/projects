package aiki.gui.components.labels;



import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.SortingMove;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
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

    private final String moveName;

    private final String types;

    private final String priority;

    private final String pp;

    private final String target;

    private final String price;

    private int xMoveName;

    private int xTypes;

    private int xPriority;

    private int xPp;

    private int xTarget;

//    private int xPrice;

    private final NatStringTreeMap<Integer> colorsTypes;

    public TmLabel(SortingMove _move, FacadeGame _facade, AbsCompoFactory _compoFactory) {
        this(_move.getName(),_move.getKeyName(),_move.getTargetChoice(),_move.getPrice(),_facade,_compoFactory);
    }
    public TmLabel(String _name, String _key, TargetChoice _target, long _price, FacadeGame _facade, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        colorsTypes = new NatStringTreeMap<Integer>();
        moveName = _name;
        MoveData move_ = _facade.getData().getMove(_key);
        StringList types_ = new StringList();
        for (String t: move_.getTypes()) {
            String type_ = _facade.translateType(t);
            String rgb_ = _facade.getData().getTypesColors().getVal(t);
            int c_ = GuiConstants.newColor(ConverterBufferedImage.getIntColor(rgb_, DataBase.SEPARATOR_RGB));
            colorsTypes.put(type_, c_);
            types_.add(type_);
        }
        types_.sort();
        String target_ = _facade.translatedTargets(_target);
        types = StringUtil.concat(SPACE, StringUtil.join(types_, SPACE));
        priority = StringUtil.concatNbs(SPACE,move_.getPriority());
        pp = StringUtil.concatNbs(SPACE,move_.getPp());
        target = StringUtil.concat(SPACE,target_);
        price = StringUtil.concatNbs(SPACE,_price);
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
        setPreferredSize(new MetaDimension(_width, SECOND_LINE));
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
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(GuiConstants.BLACK);
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
            _g.setColor(GuiConstants.newColor(colorsTypes.getVal(t)));
            int xLeft_ = x_;
            _g.fillRect(xLeft_, FIRST_LINE, TYPE_WIDTH, FIRST_LINE);
            x_ += TYPE_WIDTH;
        }
        super.paintComponent(_g);
    }
}
