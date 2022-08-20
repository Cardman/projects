package cards.belote.comparators;
import cards.belote.BidBeloteSuit;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class GameStrengthCardBeloteComparator implements Comparing<CardBelote> {

    private final Suit trumpSuit;
    private final Suit demandedSuit;
    private final BidBeloteSuit bid;
    private final boolean decreasing;

    public GameStrengthCardBeloteComparator(
            Suit _couleurAtout,
            Suit _couleurDemandee,
            boolean _decroissant){
        trumpSuit = _couleurAtout;
        demandedSuit = _couleurDemandee;
        bid = new BidBeloteSuit();
        decreasing = _decroissant;
    }

    public GameStrengthCardBeloteComparator(
            Suit _couleurDemandee,
            BidBeloteSuit _enchere,
            boolean _decroissant){
        trumpSuit = _enchere.getSuit();
        demandedSuit = _couleurDemandee;
        bid = _enchere;
        decreasing = _decroissant;
    }

    @Override
    public int compare(CardBelote _arg0, CardBelote _arg1) {
        int mult_ = 1;
        if(decreasing){
            mult_ = -1;
        }
        if(bid.getBid() == BidBelote.FOLD){
            return mult_*NumberUtil.compareLg(_arg0.strength(trumpSuit, demandedSuit), _arg1.strength(trumpSuit, demandedSuit));
        }
        return mult_* NumberUtil.compareLg(_arg0.strength(demandedSuit, bid) , _arg1.strength(demandedSuit, bid));
    }

}
