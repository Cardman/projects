package cards.gui.labels.selection;

import java.awt.Dimension;

import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.tarot.enumerations.CardTarot;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.util.core.NumberUtil;

/**
 */
public class CardTarotCellRenderer extends CustCellRender<CardTarot>{
    private CardTarot card;
    private boolean selectionne;
    private final WindowCards window;
    public CardTarotCellRenderer(WindowCards _window) {
        window = _window;
    }

    @Override
    protected AbstractImageFactory getImageFactory() {
        return window.getImageFactory();
    }
    @Override
    public void getListCellRendererComponent(AbsPreparedLabel _currentLab,
                                             int _index, boolean _isSelected, boolean _cellHasFocus) {
        card= get(_index);
        selectionne=_isSelected;
        _currentLab.setPreferredSize(new MetaDimension(50,10));
    }
    @Override
    public void paintComponent(AbstractImage _g) {
        if(!selectionne) {
            _g.setColor(GuiConstants.WHITE);
        } else {
            _g.setColor(GuiConstants.BLUE);
        }
        _g.fillRect(0,0,50,10);
        Suit couleur_=card.couleur();
        if(couleur_ == Suit.TRUMP || couleur_ == CardTarot.EXCUSE.couleur()) {
            if(!selectionne) {
                _g.setColor(GuiConstants.BLUE);
            } else {
                _g.setColor(GuiConstants.BLACK);
            }
            _g.drawLine(0,0,5,10);
            _g.drawLine(2,5,8,5);
            _g.drawLine(10,0,5,10);
        } else if(couleur_ == Suit.HEART) {
            _g.setColor(GuiConstants.RED);
            _g.fillOval(0,0,5,5);
            _g.fillOval(5,0,5,5);
            _g.fillRect(3,3,5,5);
            if(!selectionne) {
                _g.setColor(GuiConstants.WHITE);
            } else {
                _g.setColor(GuiConstants.BLUE);
            }
            _g.fillOval(0,5,5,5);
            _g.fillOval(5,5,5,5);
        } else if(couleur_ == Suit.SPADE) {
            _g.setColor(GuiConstants.BLACK);
            _g.fillOval(0,2,5,5);
            _g.fillOval(5,2,5,5);
            _g.fillPolygon(NumberUtil.wrapIntArray(5,8,5,2), NumberUtil.wrapIntArray(5,12,8,12),4);
            _g.fillRect(3,-2,5,7);
            if(!selectionne) {
                _g.setColor(GuiConstants.WHITE);
            } else {
                _g.setColor(GuiConstants.BLUE);
            }
            _g.fillOval(0,-3,5,5);
            _g.fillOval(5,-3,5,5);
        } else if(couleur_ == Suit.DIAMOND) {
            _g.setColor(GuiConstants.RED);
            _g.fillPolygon(NumberUtil.wrapIntArray(0,5,10,5), NumberUtil.wrapIntArray(5,0,5,10),4);
        } else {
            _g.setColor(GuiConstants.BLACK);
            _g.fillOval(0,3,4,4);
            _g.fillOval(6,3,4,4);
            _g.fillOval(3,0,4,4);
            _g.fillPolygon(NumberUtil.wrapIntArray(3,5,3), NumberUtil.wrapIntArray(4,5,6),3);
            _g.fillPolygon(NumberUtil.wrapIntArray(6,5,6), NumberUtil.wrapIntArray(4,5,6),3);
            _g.fillPolygon(NumberUtil.wrapIntArray(4,5,6), NumberUtil.wrapIntArray(3,5,3),3);
            _g.fillPolygon(NumberUtil.wrapIntArray(3,5,6,5), NumberUtil.wrapIntArray(10,5,10,8),4);
        }
        if(!selectionne) {
            _g.setColor(GuiConstants.BLACK);
        } else {
            _g.setColor(GuiConstants.RED);
        }
        String lg_ = window.getLanguageKey();
        _g.drawString(Games.getSymbol(card,lg_),10,10);
    }
    @Override
    public int getHeight() {
        return 10;
    }
    @Override
    public int getWidth() {
        return 50;
    }
}
