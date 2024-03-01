package cards.gui.labels;

import cards.consts.CouleurValeur;
import cards.facade.enumerations.GameEnum;
import cards.tarot.enumerations.CardTarot;

public final class TarotCardConverter implements IntCardConverter<CardTarot> {
    @Override
    public CouleurValeur convert(CardTarot _t) {
        return _t.getId();
    }

    @Override
    public GameEnum kind() {
        return GameEnum.TAROT;
    }
}
