package cards.gui.labels.selection;



import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCardsInt;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.util.core.NumberUtil;

/**
    */

public class SuitCellRenderer implements AbsCustCellRenderGene<Suit> {
    private Suit couleur;
    private boolean selectionne;
    private final WindowCardsInt window;
    public SuitCellRenderer(WindowCardsInt _window) {
        window = _window;
    }


    /**Donne la facon de presenter une couleur dans une liste avec un symbole et un nom*/
    @Override
    public AbstractImage getListCellRendererComponent(int _index, Suit _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        couleur= _info;
        selectionne=_isSelected;
        AbstractImage img_ = window.getImageFactory().newImageRgb(100,10);
        img_.setFont(_lab);
        paintComponent(img_);
        return img_;
    }
    public void paintComponent(AbstractImage _g) {
        if(!selectionne) {
            _g.setColor(GuiConstants.WHITE);
        } else {
            _g.setColor(GuiConstants.BLUE);
        }
        _g.fillRect(0,0,100,10);
        if(couleur==Suit.TRUMP) {
            _g.setColor(GuiConstants.BLUE);
            _g.drawLine(0,0,5,10);
            _g.drawLine(2,5,8,5);
            _g.drawLine(10,0,5,10);
        } else if(couleur==Suit.HEART) {
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
        } else if(couleur==Suit.SPADE) {
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
        } else if(couleur==Suit.DIAMOND) {
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
        if (couleur != null) {
            _g.drawString(Games.toString(couleur, lg_), 10, 10);
        }
    }

    public AbstractImageFactory getImageFactory() {
        return window.getImageFactory();
    }

}
