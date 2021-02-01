package cards.president.beans;
import cards.president.GamePresident;
import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import code.bean.Bean;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import code.util.core.IndexConstants;

final class PresidentBean extends Bean {

    private GamePresident game;

    private StringList nicknames;

    private CustList<Longs> scores;

    private int user;

    private String loc;

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
        setGame(res_.getGame());
        setNicknames(res_.getNicknames());
        setScores(res_.getScores());
        setUser(res_.getUser());
        setLoc(res_.getLoc());
        byte nombreJoueurs_ = getGame().getNombreDeJoueurs();
        linesDeal = new CustList<LineDeal>();
        int nbDeals_ = getScores().size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbDeals_; i++) {
            LineDeal l_ = new LineDeal();
            l_.setNumber(i);
            Longs scores_ = new Longs();
            for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                scores_.add(getScores().get(i).get(joueur_));
            }
            l_.setScores(scores_);
            linesDeal.add(l_);
        }
    }

    GamePresident getGame() {
        return game;
    }

    void setGame(GamePresident _game) {
        game = _game;
    }

    StringList getNicknames() {
        return nicknames;
    }

    void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
    }

    CustList<Longs> getScores() {
        return scores;
    }

    void setScores(CustList<Longs> _scores) {
        scores = _scores;
    }

    int getUser() {
        return user;
    }

    void setUser(int _user) {
        user = _user;
    }

    String getLoc() {
        return loc;
    }

    void setLoc(String _loc) {
        loc = _loc;
    }

    CustList<LineDeal> getLinesDeal() {
        return linesDeal;
    }

    ResultsPresident getResults() {
        return db();
    }
}
