package cards.gui.labels.selection;


import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCardsInt;
import code.gui.AbsCustCellRenderGene;
import code.gui.ColorsGroupList;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.util.core.NumberUtil;

/**
 */
public class CardBeloteCellRenderer implements AbsCustCellRenderGene<CardBelote> {
    private CardBelote card;
    private boolean selectionne;
    private final WindowCardsInt window;
    public CardBeloteCellRenderer(WindowCardsInt _window) {
        window = _window;
    }
    @Override
    public AbstractImage getListCellRendererComponent(int _index, CardBelote _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        card = _info;
        selectionne=_isSelected;
        AbstractImage img_ = window.getImageFactory().newImageRgb(50, 10);
        img_.setFont(_lab);
        paintComponent(img_);
        return img_;
    }


    public AbstractImageFactory getImageFactory() {
        return window.getImageFactory();
    }

    public void paintComponent(AbstractImage _g) {
        if(!selectionne) {
            _g.setColor(GuiConstants.WHITE);
        } else {
            _g.setColor(GuiConstants.BLUE);
        }
        _g.fillRect(0,0,50,10);
        Suit suit_= card.getId().getCouleur();
        if(suit_ == Suit.HEART) {
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
        } else if(suit_ == Suit.SPADE) {
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
        } else if(suit_ == Suit.DIAMOND) {
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
}
