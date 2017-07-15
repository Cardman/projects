package cards.gui.labels.selection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import code.gui.CommonCellRenderer;
import code.util.consts.Constants;
import cards.consts.Suit;
import cards.president.enumerations.CardPresident;
/**
 */
public class CardPresidentCellRenderer extends CommonCellRenderer {
    private CardPresident card;
    private boolean selectionne;
    @Override
    public Component getListCellRendererComponent(Object _value,
            int _index, boolean _isSelected, boolean _cellHasFocus) {
        card=(CardPresident)_value;
        selectionne=_isSelected;
        setPreferredSize(new Dimension(50,10));
        return this;
    }
    @Override
    protected void paintComponent(Graphics _g) {
        if(!selectionne) {
            _g.setColor(Color.WHITE);
        } else {
            _g.setColor(Color.BLUE);
        }
        _g.fillRect(0,0,50,10);
        Suit suit_=card.couleur();
        if(suit_ == Suit.HEART) {
            _g.setColor(Color.RED);
            _g.fillOval(0,0,5,5);
            _g.fillOval(5,0,5,5);
            _g.fillRect(3,3,5,5);
            if(!selectionne) {
                _g.setColor(Color.WHITE);
            } else {
                _g.setColor(Color.BLUE);
            }
            _g.fillOval(0,5,5,5);
            _g.fillOval(5,5,5,5);
        } else if(suit_ == Suit.SPADE) {
            _g.setColor(Color.BLACK);
            _g.fillOval(0,2,5,5);
            _g.fillOval(5,2,5,5);
            _g.fillPolygon(new int[]{5,8,5,2},new int[]{5,12,8,12},4);
            _g.fillRect(3,-2,5,7);
            if(!selectionne) {
                _g.setColor(Color.WHITE);
            } else {
                _g.setColor(Color.BLUE);
            }
            _g.fillOval(0,-3,5,5);
            _g.fillOval(5,-3,5,5);
        } else if(suit_ == Suit.CLUB) {
            _g.setColor(Color.RED);
            _g.fillPolygon(new int[]{0,5,10,5},new int[]{5,0,5,10},4);
        } else {
            _g.setColor(Color.BLACK);
            _g.fillOval(0,3,4,4);
            _g.fillOval(6,3,4,4);
            _g.fillOval(3,0,4,4);
            _g.fillPolygon(new int[]{3,5,3},new int[]{4,5,6},3);
            _g.fillPolygon(new int[]{6,5,6},new int[]{4,5,6},3);
            _g.fillPolygon(new int[]{4,5,6},new int[]{3,5,3},3);
            _g.fillPolygon(new int[]{3,5,6,5},new int[]{10,5,10,8},4);
        }
        if(!selectionne) {
            _g.setColor(Color.BLACK);
        } else {
            _g.setColor(Color.RED);
        }
        _g.drawString(card.getSymbol(Constants.getLanguage()),10,10);
    }
}
