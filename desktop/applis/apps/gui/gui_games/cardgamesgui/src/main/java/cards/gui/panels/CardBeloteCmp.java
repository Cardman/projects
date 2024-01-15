package cards.gui.panels;

import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.IdList;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class CardBeloteCmp implements Comparing<CardBelote> {
    private final IdList<Suit> couleurs;
    private final Order order;
    private final boolean decroissant;

    public CardBeloteCmp(IdList<Suit> _c, Order _o, boolean _d) {
        this.couleurs = _c;
        this.order = _o;
        this.decroissant = _d;
    }

    @Override
    public int compare(CardBelote _one, CardBelote _two) {
        if (_one.vientAvant(_two, decroissant, order, couleurs)) {
            return SortConstants.NO_SWAP_SORT;
        }
        return SortConstants.SWAP_SORT;
    }
}
