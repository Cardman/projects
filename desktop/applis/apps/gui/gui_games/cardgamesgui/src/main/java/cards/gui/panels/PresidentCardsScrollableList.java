package cards.gui.panels;


import cards.gui.WindowCardsInt;
import cards.gui.labels.selection.CardPresidentCellRenderer;
import cards.main.CardFactories;
import cards.president.DisplayingPresident;
import cards.president.enumerations.CardPresident;

public final class PresidentCardsScrollableList extends CardsScrollableList<CardPresident> {

    public PresidentCardsScrollableList(WindowCardsInt _parent, int _nb, int _pmax, String _titre, DisplayingPresident _dis) {
        super(_parent.getFrames(),_nb,_pmax,_titre,CardFactories.president(_parent.getCompoFactory(), _parent.getImageFactory(),new CardPresidentCellRenderer(_parent)),new CardPresidentCmp(_dis.getDisplaying().getSuits(), _dis.getDisplaying().isDecreasing()));
    }

}
