package cards.tarot.tsts;

import cards.tarot.HandTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.CustList;
import code.util.IdList;

public final class TstsTarotTriplet {
    private final CustList<IdList<Miseres>> miseres;
    private final CustList<IdList<Handfuls>> handfuls;
    private final CustList<HandTarot> hands;
    public TstsTarotTriplet() {
        this(3);
    }
    public TstsTarotTriplet(int _nb) {
        miseres = new CustList<IdList<Miseres>>();
        handfuls = new CustList<IdList<Handfuls>>();
        hands = new CustList<HandTarot>();
        for (int i = 0; i < _nb; i++) {
            miseres.add(new IdList<Miseres>());
            hands.add(new HandTarot());
            handfuls.add(new IdList<Handfuls>());
        }
    }

    public CustList<IdList<Handfuls>> getHandfuls() {
        return handfuls;
    }

    public CustList<IdList<Miseres>> getMiseres() {
        return miseres;
    }

    public CustList<HandTarot> getHands() {
        return hands;
    }
}
