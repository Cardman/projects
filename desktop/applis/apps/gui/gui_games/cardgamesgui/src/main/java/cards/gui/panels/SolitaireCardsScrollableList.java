package cards.gui.panels;

import cards.gui.*;
import cards.gui.labels.selection.*;
import cards.main.*;
import cards.solitaire.*;

public final class SolitaireCardsScrollableList extends CardsScrollableList<CardSolitaire> {

    public SolitaireCardsScrollableList(WindowCardsInt _parent, int _nb, int _pmax, String _titre) {
        super(_parent.getFrames(),_nb,_pmax,_titre,CardFactories.solitaire(_parent.getCompoFactory(), _parent.getImageFactory(),new CardSolitaireCellRenderer(_parent)),new CardSolitaireCmp());
    }

}
