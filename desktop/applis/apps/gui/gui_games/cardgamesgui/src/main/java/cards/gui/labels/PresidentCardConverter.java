package cards.gui.labels;

import cards.consts.CouleurValeur;
import cards.facade.enumerations.GameEnum;
import cards.president.enumerations.CardPresident;

public final class PresidentCardConverter implements IntCardConverter<CardPresident> {
    @Override
    public CouleurValeur convert(CardPresident _t) {
        return _t.getId();
    }

    @Override
    public GameEnum kind() {
        return GameEnum.PRESIDENT;
    }
}
