package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.TrickTarot;
import code.util.CustList;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class GameTarotMostDemandedSuitComparator implements Comparing<Suit> {

    private final CustList<TrickTarot> tricks;

    private final Bytes players;

    public GameTarotMostDemandedSuitComparator(CustList<TrickTarot> _tricks,
            Bytes _players) {
        tricks = _tricks;
        players = _players;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        int res_ = SortConstants.EQ_CMP;
        byte nbEntamesCouleur1_ = IndexConstants.SIZE_EMPTY;
        byte nbEntamesCouleur2_ = IndexConstants.SIZE_EMPTY;
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
            if (nbEntamesCouleur1_ < nbEntamesCouleur2_) {
                res_ = SortConstants.SWAP_SORT;
            } else {
                res_ = SortConstants.NO_SWAP_SORT;
            }
        }
        return res_;
    }

}
