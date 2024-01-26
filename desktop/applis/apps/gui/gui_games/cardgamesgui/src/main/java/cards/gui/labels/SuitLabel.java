package cards.gui.labels;



import cards.belote.BidBeloteSuit;
import cards.consts.Suit;
import cards.facade.Games;
import code.gui.GuiConstants;
import code.gui.LabelButtonUtil;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.sml.util.TranslationsLg;
import code.util.core.NumberUtil;

public final class SuitLabel extends AbsMetaLabelCard {

    private static final String EMPTY_STRING = "";

    private BidBeloteSuit bid;

    private boolean selected;

    private String text;

    public SuitLabel(AbsCompoFactory _compoFactory) {
        super(_compoFactory);
    }

    public void setSuit(BidBeloteSuit _bid, TranslationsLg _lg) {
        bid = _bid;
        if (!bid.getCouleurDominante()) {
            setText(Games.toString(bid.getBid(),_lg));
            getPaintableLabel().setBackground(GuiConstants.WHITE);
            getPaintableLabel().setForeground(GuiConstants.newColor(0, 0, 127));
            int h_ = getFact().heightFont(getPaintableLabel().getMetaFont());
            int w_ = getFact().stringWidth(getPaintableLabel().getMetaFont(),getText());
            setPreferredSize(new MetaDimension(w_, h_));
        } else {
            setText(EMPTY_STRING);
            setPreferredSize(new MetaDimension(20,20));
        }
    }

    public void setText(String _emptyString) {
        text = _emptyString;
    }

    public void setSelected(BidBeloteSuit _bid) {
        if (bid.getSuit() != _bid.getSuit()) {
            selected = false;
        } else {
            selected = bid.getBid() == _bid.getBid();
        }
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        if (!getText().isEmpty()) {
            int h_ = getFact().heightFont(getPaintableLabel().getMetaFont());
            int w_ = getFact().stringWidth(getPaintableLabel().getMetaFont(),text);
            LabelButtonUtil.paintDefaultLabel(_g, text, w_, getWidth(), h_, GuiConstants.newColor(0, 0, 127), GuiConstants.WHITE);
        } else {
            _g.setColor(GuiConstants.WHITE);
            _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            dessinerGrandSymbole(_g, 0, 0);
        }
        _g.setColor(GuiConstants.BLACK);
        _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    private String getText() {
        return text;
    }

    private void dessinerGrandSymbole(AbstractImage _g,int _x,int _y) {
        if(bid.getSuit() == Suit.HEART) {
            _g.setColor(GuiConstants.RED);
            _g.fillOval(_x,_y,10,10);
            _g.fillOval(_x+10,_y,10,10);
            _g.fillRect(_x+5,_y+5,10,10);
            _g.setColor(GuiConstants.WHITE);
            _g.fillOval(_x,_y+10,10,10);
            _g.fillOval(_x+10,_y+10,10,10);
        } else if(bid.getSuit() == Suit.SPADE) {
            _g.setColor(GuiConstants.BLACK);
            _g.fillOval(_x,_y+5,10,10);
            _g.fillOval(_x+10,_y+5,10,10);
            _g.fillPolygon(NumberUtil.wrapIntArray(10+_x,13+_x,10+_x,7+_x), NumberUtil.wrapIntArray(10+_y,20+_y,17+_y,20+_y),4);
            _g.fillRect(_x+5,_y,10,10);
            _g.setColor(GuiConstants.WHITE);
            _g.fillOval(_x,_y-5,10,10);
            _g.fillOval(_x+10,_y-5,10,10);
        } else if(bid.getSuit() == Suit.DIAMOND) {
            _g.setColor(GuiConstants.RED);
            _g.fillPolygon(NumberUtil.wrapIntArray(_x,10+_x,20+_x,10+_x), NumberUtil.wrapIntArray(10+_y,_y,10+_y,20+_y),4);
        } else {
            _g.setColor(GuiConstants.BLACK);
            _g.fillOval(_x,_y+6,8,8);
            _g.fillOval(_x+12,_y+6,8,8);
            _g.fillOval(_x+6,_y,8,8);
            _g.fillPolygon(NumberUtil.wrapIntArray(7+_x,10+_x,7+_x), NumberUtil.wrapIntArray(8+_y,10+_y,12+_y),3);
            _g.fillPolygon(NumberUtil.wrapIntArray(13+_x,10+_x,13+_x), NumberUtil.wrapIntArray(8+_y,10+_y,12+_y),3);
            _g.fillPolygon(NumberUtil.wrapIntArray(8+_x,10+_x,12+_x), NumberUtil.wrapIntArray(7+_y,10+_y,7+_y),3);
            _g.fillPolygon(NumberUtil.wrapIntArray(7+_x,10+_x,13+_x,10+_x), NumberUtil.wrapIntArray(20+_y,10+_y,20+_y,17+_y),4);
        }
    }
}
