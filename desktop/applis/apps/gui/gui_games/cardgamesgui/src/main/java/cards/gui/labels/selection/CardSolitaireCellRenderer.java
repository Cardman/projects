package cards.gui.labels.selection;


import cards.gui.*;
import cards.solitaire.*;
import code.gui.*;
import code.gui.images.*;

/**
 */
public final class CardSolitaireCellRenderer implements AbsCustCellRenderGene<CardSolitaire> {
    private final WindowCardsInt window;
    public CardSolitaireCellRenderer(WindowCardsInt _window) {
        window = _window;
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, CardSolitaire _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        return CardTarotCellRenderer.paintComponent(window,_info.getId(),_isSelected);
    }

}
