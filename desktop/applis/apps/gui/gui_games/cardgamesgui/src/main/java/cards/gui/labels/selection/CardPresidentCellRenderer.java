package cards.gui.labels.selection;


import cards.gui.WindowCardsInt;
import cards.president.enumerations.CardPresident;
import code.gui.AbsCustCellRenderGene;
import code.gui.ColorsGroupList;
import code.gui.images.AbstractImage;
import code.gui.images.MetaFont;

/**
 */
public class CardPresidentCellRenderer implements AbsCustCellRenderGene<CardPresident> {
    private final WindowCardsInt window;
    public CardPresidentCellRenderer(WindowCardsInt _window) {
        window = _window;
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, CardPresident _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        return CardTarotCellRenderer.paintComponent(window,_info.getId(),_isSelected);
    }

}
