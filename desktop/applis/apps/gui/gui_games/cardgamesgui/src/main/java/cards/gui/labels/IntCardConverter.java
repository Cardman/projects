package cards.gui.labels;

import cards.consts.CouleurValeur;
import cards.facade.enumerations.GameEnum;

public interface IntCardConverter<T> {
    CouleurValeur convert(T _t);
    GameEnum kind();
}
