package cards.consts;

import code.util.CustList;
import code.util.Longs;
import code.util.core.IndexConstants;

public final class LineDeal {

    private int number;

    private Longs scores;

    public static CustList<LineDeal> copy(CustList<LineDeal> _copy) {
        CustList<LineDeal> ld_ = new CustList<LineDeal>();
        int nbDeals_ = _copy.size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbDeals_; i++) {
            LineDeal l_ = new LineDeal();
            l_.setNumber(_copy.get(i).getNumber());
            Longs scores_ = new Longs();
            int nombreJoueurs_ = _copy.get(i).getScores().size();
            for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                scores_.add(_copy.get(i).getScores().get(joueur_));
            }
            l_.setScores(scores_);
            ld_.add(l_);
        }
        return ld_;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int _number) {
        number = _number;
    }

    public Longs getScores() {
        return scores;
    }

    public void setScores(Longs _scores) {
        scores = _scores;
    }
}
