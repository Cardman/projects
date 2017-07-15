package cards.tarot.comparators;
import java.util.Comparator;

import code.util.Numbers;
import cards.tarot.enumerations.CardTarot;

public final class CharactersTarotGreaterPointsComparator implements Comparator<CardTarot> {

    @Override
    public int compare(CardTarot _card1, CardTarot _card2) {
        int pointsOne_ = _card1.points();
        int pointsTwo_ = _card2.points();
        return Numbers.compare(pointsTwo_, pointsOne_);
    }

}
