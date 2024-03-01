package cards.gui.labels;

import cards.belote.enumerations.CardBelote;
import cards.consts.CouleurValeur;
import cards.facade.enumerations.GameEnum;

public final class BeloteCardConverter implements IntCardConverter<CardBelote> {
    @Override
    public CouleurValeur convert(CardBelote _t) {
        return _t.getId();
    }

    @Override
    public GameEnum kind() {
        return GameEnum.BELOTE;
    }
}
