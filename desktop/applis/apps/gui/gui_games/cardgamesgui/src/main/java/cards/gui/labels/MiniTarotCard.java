package cards.gui.labels;



import javax.swing.SwingConstants;

import cards.consts.Suit;
import cards.facade.Games;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsMetaLabel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.core.NumberUtil;

public final class MiniTarotCard extends AbsMetaLabel {

    static final String DEFAULT="Default";

    private CardTarot card;
    private String lg;

    public MiniTarotCard(String _lg, CardTarot _card, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        card = _card;
        lg = _lg;
        setHorizontalAlignment(SwingConstants.RIGHT);
        setVerticalAlignment(SwingConstants.TOP);
        setPreferredSize(new MetaDimension(20, 40));
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0,0,75+getWidth(),getHeight());
        _g.setColor(GuiConstants.BLACK);
        _g.drawRect(0,0,getWidth()-1,getHeight()-1);
        _g.drawString(Games.getSymbol(card,lg),0,20);
        if(card != CardTarot.EXCUSE) {
            dessinerGrandSymbole(_g, 0, 20);
        }
    }

    private void dessinerGrandSymbole(AbstractImage _g,int _x,int _y) {
        Suit couleur_=card.couleur();
        if(couleur_ == Suit.TRUMP) {
            _g.setColor(GuiConstants.BLUE);
            _g.drawLine(_x,_y,_x+10,_y+10);
            _g.setColor(GuiConstants.BLACK);
            _g.fillPolygon(NumberUtil.wrapIntArray(10+_x,12+_x,15+_x,15+_x,12+_x,10+_x), NumberUtil.wrapIntArray(10+_y,10+_y,12+_y,15+_y,15+_y,12+_y),6);
        } else if(couleur_ == Suit.HEART) {
            _g.setColor(GuiConstants.RED);
            _g.fillOval(_x,_y,10,10);
            _g.fillOval(_x+10,_y,10,10);
            _g.fillRect(_x+5,_y+5,10,10);
            _g.setColor(GuiConstants.WHITE);
            _g.fillOval(_x,_y+10,10,10);
            _g.fillOval(_x+10,_y+10,10,10);
        } else if(couleur_ == Suit.SPADE) {
            _g.setColor(GuiConstants.BLACK);
            _g.fillOval(_x,_y+5,10,10);
            _g.fillOval(_x+10,_y+5,10,10);
            _g.fillPolygon(NumberUtil.wrapIntArray(10+_x,13+_x,10+_x,7+_x), NumberUtil.wrapIntArray(10+_y,20+_y,17+_y,20+_y),4);
            _g.fillRect(_x+5,_y,10,10);
            _g.setColor(GuiConstants.WHITE);
            _g.fillOval(_x,_y-5,10,10);
            _g.fillOval(_x+10,_y-5,10,10);
        } else if(couleur_ == Suit.DIAMOND) {
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
