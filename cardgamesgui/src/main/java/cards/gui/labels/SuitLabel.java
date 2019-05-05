package cards.gui.labels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import cards.belote.BidBeloteSuit;
import cards.consts.Suit;
import cards.facade.Games;
import code.gui.LabelButtonUtil;
import code.gui.PaintableLabel;
import code.util.Numbers;

public class SuitLabel extends PaintableLabel {

    private static final String EMPTY_STRING = "";

    private BidBeloteSuit bid;

    private boolean selected;

    private String text;

    public void setSuit(BidBeloteSuit _bid, String _lg) {
        bid = _bid;
        if (!bid.getCouleurDominante()) {
            setText(Games.toString(bid.getEnchere(),_lg));
            setBackground(Color.WHITE);
            setForeground(new Color(0, 0, 127));
            int h_ = getFontMetrics(getFont()).getHeight();
            int w_ = getFontMetrics(getFont()).stringWidth(getText());
            setPreferredSize(new Dimension(w_, h_));
        } else {
            setText(EMPTY_STRING);
            setPreferredSize(new Dimension(20,20));
        }
    }

    public void setText(String _emptyString) {
        text = _emptyString;
    }

    public void setSelected(BidBeloteSuit _bid) {
        if (bid.getCouleur() != _bid.getCouleur()) {
            selected = false;
        } else if (bid.getEnchere() != _bid.getEnchere()) {
            selected = false;
        } else {
            selected = true;
        }
    }

    @Override
    public void paintComponent(Graphics _g) {
        if (!getText().isEmpty()) {
            Font font_ = getFont();
            FontMetrics fontMetrics_ = getFontMetrics(font_);
            int h_ = fontMetrics_.getHeight();
            int w_ = fontMetrics_.stringWidth(text);
            LabelButtonUtil.paintDefaultLabel(_g, text, w_, getWidth(), h_, getForeground(), getBackground());
        } else {
            _g.setColor(Color.WHITE);
            _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            dessinerGrandSymbole(_g, 0, 0);
        }
        _g.setColor(Color.BLACK);
        _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    private String getText() {
        return text;
    }

    private void dessinerGrandSymbole(Graphics _g,int _x,int _y) {
        if(bid.getCouleur() == Suit.HEART) {
            _g.setColor(Color.RED);
            _g.fillOval(_x,_y,10,10);
            _g.fillOval(_x+10,_y,10,10);
            _g.fillRect(_x+5,_y+5,10,10);
            _g.setColor(Color.WHITE);
            _g.fillOval(_x,_y+10,10,10);
            _g.fillOval(_x+10,_y+10,10,10);
        } else if(bid.getCouleur() == Suit.SPADE) {
            _g.setColor(Color.BLACK);
            _g.fillOval(_x,_y+5,10,10);
            _g.fillOval(_x+10,_y+5,10,10);
            _g.fillPolygon(Numbers.wrapIntArray(10+_x,13+_x,10+_x,7+_x),Numbers.wrapIntArray(10+_y,20+_y,17+_y,20+_y),4);
            _g.fillRect(_x+5,_y,10,10);
            _g.setColor(Color.WHITE);
            _g.fillOval(_x,_y-5,10,10);
            _g.fillOval(_x+10,_y-5,10,10);
        } else if(bid.getCouleur() == Suit.DIAMOND) {
            _g.setColor(Color.RED);
            _g.fillPolygon(Numbers.wrapIntArray(_x,10+_x,20+_x,10+_x),Numbers.wrapIntArray(10+_y,_y,10+_y,20+_y),4);
        } else {
            _g.setColor(Color.BLACK);
            _g.fillOval(_x,_y+6,8,8);
            _g.fillOval(_x+12,_y+6,8,8);
            _g.fillOval(_x+6,_y,8,8);
            _g.fillPolygon(Numbers.wrapIntArray(7+_x,10+_x,7+_x),Numbers.wrapIntArray(8+_y,10+_y,12+_y),3);
            _g.fillPolygon(Numbers.wrapIntArray(13+_x,10+_x,13+_x),Numbers.wrapIntArray(8+_y,10+_y,12+_y),3);
            _g.fillPolygon(Numbers.wrapIntArray(8+_x,10+_x,12+_x),Numbers.wrapIntArray(7+_y,10+_y,7+_y),3);
            _g.fillPolygon(Numbers.wrapIntArray(7+_x,10+_x,13+_x,10+_x),Numbers.wrapIntArray(20+_y,10+_y,20+_y,17+_y),4);
        }
    }
}
