package cards.president.comparators;

import cards.president.enumerations.CardPresident;
import code.util.AbsComparerTreeMap;
import code.util.ints.Comparing;

public final class HandPresidentRepartition extends AbsComparerTreeMap<CardPresident,Byte> {
    public HandPresidentRepartition(boolean _reversed) {
        super(new GameStrengthCardPresidentComparator(_reversed, true));
    }
    public HandPresidentRepartition(Comparing<CardPresident> _cmp) {
        super(_cmp);
    }
}
