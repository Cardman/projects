package cards.gui.panels;

import cards.consts.Suit;
import cards.president.enumerations.CardPresident;
import code.util.IdList;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class CardPresidentCmp implements Comparing<CardPresident> {
    private final IdList<Suit> couleurs;
    private final boolean decroissant;

    public CardPresidentCmp(IdList<Suit> _c, boolean _d) {
        this.couleurs = _c;
        this.decroissant = _d;
    }

    @Override
    public int compare(CardPresident _one, CardPresident _two) {
        if (_one.vientAvant(_two, decroissant, couleurs)) {
            return SortConstants.NO_SWAP_SORT;
        }
        return SortConstants.SWAP_SORT;
    }
}
