package cards.gui.panels;


import cards.gui.WindowCardsInt;
import cards.gui.labels.selection.CardTarotCellRenderer;
import cards.main.CardFactories;
import cards.tarot.DisplayingTarot;
import cards.tarot.enumerations.CardTarot;

public final class TarotCardsScrollableList extends CardsScrollableList<CardTarot> {

    public TarotCardsScrollableList(WindowCardsInt _parent, int _nb, int _pmax, String _titre, DisplayingTarot _dis) {
        super(_parent.getFrames(), _nb,_pmax,_titre,CardFactories.tarot(_parent.getCompoFactory(), _parent.getImageFactory(),new CardTarotCellRenderer(_parent)),new CardTarotCmp(_dis.getDisplaying().getSuits(), _dis.getDisplaying().isDecreasing()));
    }
}
