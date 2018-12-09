package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.TrickTarot;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class GameTarotMostDemandedSuitComparator implements Comparing<Suit> {

    private CustList<TrickTarot> tricks;

    private Numbers<Byte> players;

    public GameTarotMostDemandedSuitComparator(CustList<TrickTarot> _tricks,
            Numbers<Byte> _players) {
        tricks = _tricks;
        players = _players;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        boolean aussiHaut_ = true;
        boolean permuter_ = false;
        byte nbEntamesCouleur1_ = CustList.SIZE_EMPTY;
        byte nbEntamesCouleur2_ = CustList.SIZE_EMPTY;
        for(TrickTarot pli_: tricks){
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            if (pli_.couleurDemandee() == _suit1 && players.containsObj(pli_.getEntameur())) {
                nbEntamesCouleur1_++;
            }
            if (pli_.couleurDemandee() == _suit2 && players.containsObj(pli_.getEntameur())) {
                nbEntamesCouleur2_++;
            }
        }
        if (nbEntamesCouleur1_ != nbEntamesCouleur2_) {
            aussiHaut_ = false;
            if (nbEntamesCouleur1_ < nbEntamesCouleur2_) {
                permuter_ = true;
            }
        }
        if (permuter_) {
            return CustList.SWAP_SORT;
        }
        if (aussiHaut_) {
            return CustList.EQ_CMP;
        }
        return CustList.NO_SWAP_SORT;
    }

}
