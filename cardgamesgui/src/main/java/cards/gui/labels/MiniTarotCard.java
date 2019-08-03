package cards.gui.labels;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import cards.consts.Suit;
import cards.facade.Games;
import cards.tarot.enumerations.CardTarot;
import code.gui.CustGraphics;
import code.gui.PaintableLabel;
import code.util.*;

public class MiniTarotCard extends PaintableLabel {

    static final String DEFAULT="Default";

    private CardTarot card;
    private String lg;

    public MiniTarotCard(String _lg, CardTarot _card) {
        card = _card;
        lg = _lg;
        setHorizontalAlignment(SwingConstants.RIGHT);
        setVerticalAlignment(SwingConstants.TOP);
        setPreferredSize(new Dimension(20, 40));
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0,0,75+getWidth(),getHeight());
        _g.setColor(Color.BLACK);
        _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        _g.drawString(Games.getSymbol(card,lg),0,20);
        if(card != CardTarot.EXCUSE) {
            dessinerGrandSymbole(_g, 0, 20);
        }
    }

    private void dessinerGrandSymbole(CustGraphics _g,int _x,int _y) {
        Suit couleur_=card.couleur();
        if(couleur_ == Suit.TRUMP) {
            _g.setColor(Color.BLUE);
            _g.drawLine(_x,_y,_x+10,_y+10);
            _g.setColor(Color.BLACK);
            _g.fillPolygon(Numbers.wrapIntArray(10+_x,12+_x,15+_x,15+_x,12+_x,10+_x),Numbers.wrapIntArray(10+_y,10+_y,12+_y,15+_y,15+_y,12+_y),6);
        } else if(couleur_ == Suit.HEART) {
            _g.setColor(Color.RED);
            _g.fillOval(_x,_y,10,10);
            _g.fillOval(_x+10,_y,10,10);
            _g.fillRect(_x+5,_y+5,10,10);
            _g.setColor(Color.WHITE);
            _g.fillOval(_x,_y+10,10,10);
            _g.fillOval(_x+10,_y+10,10,10);
        } else if(couleur_ == Suit.SPADE) {
            _g.setColor(Color.BLACK);
            _g.fillOval(_x,_y+5,10,10);
            _g.fillOval(_x+10,_y+5,10,10);
            _g.fillPolygon(Numbers.wrapIntArray(10+_x,13+_x,10+_x,7+_x),Numbers.wrapIntArray(10+_y,20+_y,17+_y,20+_y),4);
            _g.fillRect(_x+5,_y,10,10);
            _g.setColor(Color.WHITE);
            _g.fillOval(_x,_y-5,10,10);
            _g.fillOval(_x+10,_y-5,10,10);
        } else if(couleur_ == Suit.DIAMOND) {
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
