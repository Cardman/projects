package cards.gui.panels;


import cards.belote.DisplayingBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.WindowCardsInt;
import cards.gui.labels.selection.CardBeloteCellRenderer;
import cards.main.CardFactories;

public final class BeloteCardsScrollableList extends CardsScrollableList<CardBelote> {

    public BeloteCardsScrollableList(WindowCardsInt _parent, int _nb, int _pmax, String _titre, DisplayingBelote _dis) {
        super(_parent.getCompoFactory(),_nb,_pmax,_titre,CardFactories.belote(_parent.getCompoFactory(), _parent.getImageFactory(), new CardBeloteCellRenderer(_parent)),new CardBeloteCmp(_dis.getDisplaying().getSuits(), _dis.getOrderBeforeBids(), _dis.getDisplaying().isDecreasing()));
    }
}
