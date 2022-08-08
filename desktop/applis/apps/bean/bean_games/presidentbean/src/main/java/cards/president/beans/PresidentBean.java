package cards.president.beans;
import cards.president.GamePresident;
import cards.president.ResultsPresident;
import code.bean.Bean;
import code.util.CustList;
import code.util.Longs;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class PresidentBean extends Bean {

    private GamePresident game;

    private StringList nicknames;

    private CustList<Longs> scores;

    private int user;

    private String loc;

    private CustList<PresidentLineDeal> linesDeal;

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
        setNicknames(res_.getRes().getNicknames());
        setScores(res_.getRes().getScores());
        setUser(res_.getRes().getUser());
        setLoc(res_.getRes().getLoc());
        byte nombreJoueurs_ = getGame().getNombreDeJoueurs();
        linesDeal = new CustList<PresidentLineDeal>();
        int nbDeals_ = getScores().size();
        for(int i = IndexConstants.FIRST_INDEX; i<nbDeals_; i++) {
            PresidentLineDeal l_ = new PresidentLineDeal();
            l_.setNumber(i);
            Longs scores_ = new Longs();
            for(byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
                scores_.add(getScores().get(i).get(joueur_));
            }
            l_.setScores(scores_);
            linesDeal.add(l_);
        }
    }

    public GamePresident getGame() {
        return game;
    }

    public void setGame(GamePresident _game) {
        game = _game;
    }

    public StringList getNicknames() {
        return nicknames;
    }

    public void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
    }

    public CustList<Longs> getScores() {
        return scores;
    }

    public void setScores(CustList<Longs> _scores) {
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

    public CustList<PresidentLineDeal> getLinesDeal() {
        return linesDeal;
    }

    public ResultsPresident getResults() {
        return db();
    }
}
