package cards.tarot.comparators;
import cards.tarot.enumerations.CardTarot;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class CharactersTarotGreaterPointsComparator implements Comparing<CardTarot> {

    @Override
    public int compare(CardTarot _card1, CardTarot _card2) {
        int pointsOne_ = _card1.points();
        int pointsTwo_ = _card2.points();
        return NumberUtil.compareLg(pointsTwo_, pointsOne_);
    }

}
