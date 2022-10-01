package cards.president.beans;

import cards.consts.LineDeal;
import cards.president.ResultsPresident;
import code.bean.Bean;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class PresidentBean extends Bean {

    private StringList nicknames;

    private CustList<LineDeal> history;
    private CustList<LineDeal> linesDeal;

    private ResultsPresident dataBase;
    public ResultsPresident db() {
        return dataBase;
    }

    public void setDataBase(ResultsPresident _dataBase) {
        dataBase = _dataBase;
    }

    @Override
    public void beforeDisplaying() {
        ResultsPresident res_ = getResults();
        setNicknames(res_.getRes().getNicknames());
        setHistory(res_.getRes().getHistory());
        linesDeal = new CustList<LineDeal>();
        int nbDeals_ = getHistory().size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbDeals_; i++) {
            LineDeal l_ = new LineDeal();
            l_.setNumber(getHistory().get(i).getNumber());
            Longs scores_ = new Longs();
            int nombreJoueurs_ = getHistory().get(i).getScores().size();
            for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                scores_.add(getHistory().get(i).getScores().get(joueur_));
            }
            l_.setScores(scores_);
            linesDeal.add(l_);
        }
    }

    public StringList getNicknames() {
        return nicknames;
    }

    public void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
    }

    public CustList<LineDeal> getHistory() {
        return history;
    }

    public void setHistory(CustList<LineDeal> _h) {
        this.history = _h;
    }

    public CustList<LineDeal> getLinesDeal() {
        return linesDeal;
    }

    public ResultsPresident getResults() {
        return db();
    }
}
