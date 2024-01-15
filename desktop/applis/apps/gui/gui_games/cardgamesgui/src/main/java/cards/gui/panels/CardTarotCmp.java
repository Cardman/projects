package cards.gui.panels;

import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.IdList;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class CardTarotCmp implements Comparing<CardTarot> {
    private final IdList<Suit> couleurs;
    private final boolean decroissant;

    public CardTarotCmp(IdList<Suit> _c, boolean _d) {
        this.couleurs = _c;
        this.decroissant = _d;
    }

    @Override
    public int compare(CardTarot _one, CardTarot _two) {
        if (_one.vientAvant(_two, decroissant, couleurs)) {
            return SortConstants.NO_SWAP_SORT;
        }
        return SortConstants.SWAP_SORT;
    }
}
