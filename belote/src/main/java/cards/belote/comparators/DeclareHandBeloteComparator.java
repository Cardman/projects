package cards.belote.comparators;
import cards.belote.DeclareHandBelote;
import cards.belote.HandBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.Suit;
import code.util.CustList;
import code.util.ints.Comparing;

public final class DeclareHandBeloteComparator implements Comparing<DeclareHandBelote> {

    private Suit trumpSuit;
    public DeclareHandBeloteComparator(
            Suit _couleurAtout){
        trumpSuit = _couleurAtout;
    }
    @Override
    public int compare(DeclareHandBelote _o1, DeclareHandBelote _o2) {
        DeclaresBelote annonce1_ = _o1.getAnnonce();
        DeclaresBelote annonce2_ = _o2.getAnnonce();
        HandBelote main1_ = _o1.getMain();
        HandBelote main2_ = _o2.getMain();
        if(annonce1_.getForce() > annonce2_.getForce()){
            return -1;
        }
        if(annonce1_.getForce() < annonce2_.getForce()){
            return 1;
        }
        if(annonce1_.getPoints() > annonce2_.getPoints()){
            return -1;
        }
        if(annonce1_.getPoints() < annonce2_.getPoints()){
            return 1;
        }
        int minTaille_ = Math.min(main1_.total(), main2_.total());
        for (int i = CustList.FIRST_INDEX;i<minTaille_;i++){
            if(main1_.carte(i).forceAnnonce() > main2_.carte(i).forceAnnonce()){
                return -1;
            }
            if(main1_.carte(i).forceAnnonce() < main2_.carte(i).forceAnnonce()){
                return 1;
            }
        }
        if(trumpSuit != Suit.UNDEFINED){
            if (main1_.estVide()) {
                if (main2_.estVide()) {
                    return 0;
                }
                return -1;
            }
            if (main2_.estVide()) {
                return 1;
            }
            if(main1_.premiereCarte().couleur() == trumpSuit){
                if(main2_.premiereCarte().couleur() != trumpSuit){
                    return -1;
                }
            }
            if(main2_.premiereCarte().couleur() == trumpSuit){
                return 1;
            }
        }
        if(main1_.total() > main2_.total()){
            return -1;
        }
        if(main1_.total() < main2_.total()){
            return 1;
        }
        return 0;
    }

}
