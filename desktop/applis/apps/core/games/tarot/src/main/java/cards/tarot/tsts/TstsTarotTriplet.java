package cards.tarot.tsts;

import cards.tarot.HandTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.CustList;
import code.util.EnumList;

public final class TstsTarotTriplet {
    private final CustList<EnumList<Miseres>> miseres;
    private final CustList<EnumList<Handfuls>> handfuls;
    private final CustList<HandTarot> hands;
    public TstsTarotTriplet() {
        this(3);
    }
    public TstsTarotTriplet(int _nb) {
        miseres = new CustList<EnumList<Miseres>>();
        handfuls = new CustList<EnumList<Handfuls>>();
        hands = new CustList<HandTarot>();
        for (int i = 0; i < _nb; i++) {
            miseres.add(new EnumList<Miseres>());
            hands.add(new HandTarot());
            handfuls.add(new EnumList<Handfuls>());
        }
    }

    public CustList<EnumList<Handfuls>> getHandfuls() {
        return handfuls;
    }

    public CustList<EnumList<Miseres>> getMiseres() {
        return miseres;
    }

    public CustList<HandTarot> getHands() {
        return hands;
    }
}
