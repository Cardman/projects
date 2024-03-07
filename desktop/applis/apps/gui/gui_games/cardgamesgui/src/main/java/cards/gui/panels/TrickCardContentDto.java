package cards.gui.panels;

import code.util.CustList;

public final class TrickCardContentDto<T> {
    private final CustList<T> cards;
    private final int starter;

    public TrickCardContentDto(CustList<T> _c, int _s) {
        this.cards = _c;
        this.starter = _s;
    }

    public CustList<T> getCards() {
        return cards;
    }

    public int getStarter() {
        return starter;
    }
}
