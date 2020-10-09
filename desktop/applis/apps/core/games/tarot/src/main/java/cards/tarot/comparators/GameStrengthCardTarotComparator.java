package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class GameStrengthCardTarotComparator implements Comparing<CardTarot> {

    private Suit demandedSuit;
    private boolean decreasing;

    public GameStrengthCardTarotComparator(
            Suit _couleurDemandee,
            boolean _decroissant){
        demandedSuit = _couleurDemandee;
        decreasing = _decroissant;
    }

//    public GameStrengthCardTarotComparator(
//            Suit _couleurDemandee){
//        demandedSuit = _couleurDemandee;
//        decreasing = true;
//    }

//    public boolean plusFortQue(CardTarot _o1, CardTarot _o2) {
//        return _o1.strength(demandedSuit) > _o2.strength(demandedSuit);
//    }
//    public boolean aussiFortQue(CardTarot _o1, CardTarot _o2) {
//        return _o1.strength(demandedSuit) == _o2.strength(demandedSuit);
//    }
    @Override
    public int compare(CardTarot _o1, CardTarot _o2) {
        int mult_ = 1;
        if(decreasing){
            mult_ = -1;
        }
        if(demandedSuit == Suit.UNDEFINED) {
            if(_o1.strength(_o1.couleur()) > _o2.strength(_o2.couleur())){
                return mult_;
            }
            if(_o1.strength(_o1.couleur()) < _o2.strength(_o2.couleur())){
                return -mult_;
            }
            return SortConstants.EQ_CMP;
        }
        if(_o1.strength(demandedSuit) > _o2.strength(demandedSuit)){
            return mult_;
        }
        if(_o1.strength(demandedSuit) < _o2.strength(demandedSuit)){
            return -mult_;
        }
        return SortConstants.EQ_CMP;
    }

}
