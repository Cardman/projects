package cards.gui.animations;

import cards.consts.DisplayingCommon;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.IdList;

public final class TarotSortingSummedTwoHands implements IntSortingSummedTwoHands<CardTarot> {
    @Override
    public IdList<CardTarot> sorted(IdList<CardTarot> _one, IdList<CardTarot> _two, DisplayingCommon _dis) {
        IdList<CardTarot> union_ = new IdList<CardTarot>();
        union_.addAllElts(_one);
        union_.addAllElts(_two);
        HandTarot hand_ = new HandTarot();
        hand_.setCards(union_);
        hand_.trier(_dis.getSuits(), _dis.isDecreasing());
        return hand_.getCards();
    }
}
