package cards.gui.panels;

import cards.solitaire.*;
import code.util.core.*;
import code.util.ints.*;

public final class CardSolitaireCmp implements Comparing<CardSolitaire> {

    @Override
    public int compare(CardSolitaire _one, CardSolitaire _two) {
        return SortConstants.NO_SWAP_SORT;
    }
}
