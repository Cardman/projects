package cards.tarot.comparators;
import cards.tarot.enumerations.CardTarot;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class CharactersTarotGreaterPointsComparator implements Comparing<CardTarot> {

    @Override
    public int compare(CardTarot _card1, CardTarot _card2) {
        int pointsOne_ = _card1.points();
        int pointsTwo_ = _card2.points();
        return Numbers.compare(pointsTwo_, pointsOne_);
    }

}
