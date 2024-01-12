package cards.gui.labels.selection;


import cards.belote.enumerations.CardBelote;
import cards.gui.WindowCardsInt;
import code.gui.AbsCustCellRenderGene;
import code.gui.ColorsGroupList;
import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;

/**
 */
public class CardBeloteCellRenderer implements AbsCustCellRenderGene<CardBelote> {
    private final WindowCardsInt window;
    public CardBeloteCellRenderer(WindowCardsInt _window) {
        window = _window;
    }
    @Override
    public AbstractImage getListCellRendererComponent(int _index, CardBelote _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        return CardTarotCellRenderer.paintComponent(window,_info.getId(),_isSelected);
    }


}
