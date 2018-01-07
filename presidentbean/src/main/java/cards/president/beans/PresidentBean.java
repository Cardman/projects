package cards.president.beans;
import code.bean.Accessible;
import code.bean.Bean;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import cards.president.GamePresident;
import cards.president.ResultsPresident;

public final class PresidentBean extends Bean {

    @Accessible
    private GamePresident game;

    @Accessible
    private StringList nicknames;

    @Accessible
    private CustList<Numbers<Long>> scores;

    @Accessible
    private byte user;

    @Accessible
    private String loc;

    @Accessible
    private CustList<LineDeal> linesDeal;

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
        for(int i=CustList.FIRST_INDEX;i<nbDeals_;i++) {
            LineDeal l_ = new LineDeal();
            l_.setNumber(i);
            Numbers<Long> scores_ = new Numbers<Long>();
            for(byte joueur_=CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
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

    public CustList<Numbers<Long>> getScores() {
        return scores;
    }

    public void setScores(CustList<Numbers<Long>> _scores) {
        scores = _scores;
    }

    public byte getUser() {
        return user;
    }

    public void setUser(byte _user) {
        user = _user;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String _loc) {
        loc = _loc;
    }

    @Accessible
    protected ResultsPresident getResults() {
        return (ResultsPresident) getDataBase();
    }
}
