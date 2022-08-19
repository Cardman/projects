package cards.belote.comparators;
import cards.belote.BidBeloteSuit;
import cards.belote.DeclareHandBelote;
import cards.belote.HandBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Suit;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class DeclareHandBeloteComparator implements Comparing<DeclareHandBelote> {

    private BidBeloteSuit bid;
    public DeclareHandBeloteComparator(
            BidBeloteSuit _couleurAtout){
        bid = _couleurAtout;
    }
    @Override
    public int compare(DeclareHandBelote _o1, DeclareHandBelote _o2) {
        DeclaresBelote annonce1_ = _o1.getDeclare();
        DeclaresBelote annonce2_ = _o2.getDeclare();
        HandBelote main1_ = _o1.getHand();
        HandBelote main2_ = _o2.getHand();
        int cmp_ = NumberUtil.compareLg(annonce1_.getForce(), annonce2_.getForce());
        if (cmp_ != 0) {
            return -cmp_;
        }
        int minTaille_ = Math.min(main1_.total(), main2_.total());
        for (int i = IndexConstants.FIRST_INDEX; i<minTaille_; i++){
            cmp_ = NumberUtil.compareLg(main1_.carte(i).forceAnnonce(), main2_.carte(i).forceAnnonce());
            if (cmp_ != 0) {
                return -cmp_;
            }
        }
        Suit trumpSuit_ = bid.getSuit();
        if(trumpSuit_ != Suit.UNDEFINED){
            int first_ = main1_.couleurs(bid).getVal(trumpSuit_).total();
            int second_ = main2_.couleurs(bid).getVal(trumpSuit_).total();
            cmp_ = NumberUtil.compareLg(first_, second_);
            if (cmp_ != 0) {
                return -cmp_;
            }
        }
        return -NumberUtil.compareLg(main1_.total(), main2_.total());
    }

}
