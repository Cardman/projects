package cards.belote.comparators;
import cards.belote.BidBeloteSuit;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.ints.Comparing;

public final class GameStrengthCardBeloteComparator implements Comparing<CardBelote> {

    private Suit trumpSuit;
    private Suit demandedSuit;
    private BidBeloteSuit bid = new BidBeloteSuit();
    private boolean decreasing;

    public GameStrengthCardBeloteComparator(
            Suit _couleurAtout,
            Suit _couleurDemandee,
            boolean _decroissant){
        trumpSuit = _couleurAtout;
        demandedSuit = _couleurDemandee;
        decreasing = _decroissant;
    }

    public GameStrengthCardBeloteComparator(
            Suit _couleurDemandee,
            BidBeloteSuit _enchere,
            boolean _decroissant){
        trumpSuit = _enchere.getCouleur();
        demandedSuit = _couleurDemandee;
        bid = _enchere;
        decreasing = _decroissant;
    }

    public boolean bienClasse(CardBelote _arg0, CardBelote _arg1){
        return compare(_arg0,_arg1)<=0;
    }
    @Override
    public int compare(CardBelote _arg0, CardBelote _arg1) {
        int mult_ = 1;
        if(decreasing){
            mult_ = -1;
        }
        if(bid.getEnchere() == BidBelote.FOLD){
            if(_arg0.strength(trumpSuit, demandedSuit) > _arg1.strength(trumpSuit, demandedSuit)){
                return mult_;
            }
            if(_arg0.strength(trumpSuit, demandedSuit) < _arg1.strength(trumpSuit, demandedSuit)){
                return -mult_;
            }
            return 0;
        }
        if(_arg0.strength(demandedSuit, bid) > _arg1.strength(demandedSuit, bid)){
            return mult_;
        }
        if(_arg0.strength(demandedSuit, bid) < _arg1.strength(demandedSuit, bid)){
            return -mult_;
        }
        return 0;
    }

}
