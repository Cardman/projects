package cards.belote;
import cards.consts.EndGameState;
import cards.consts.GameType;
import cards.consts.ResultsGame;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;


public final class ResultsBelote {
    private ResultsGame res = new ResultsGame();
    private GameBelote game;

    private StringList nicknames;

    private byte user;

    private String loc;

    private EndGameState endTarotGame = EndGameState.EQUALLITY;

    private int differenceScoreTaker;

    public void initialize(StringList _pseudos, CustList<Longs> _scores) {
        res.setScores(_scores);
        nicknames = _pseudos;
        Shorts scoresDeal_ = new Shorts();
        BidBeloteSuit bid_ = game.getContrat();
        if(bid_.jouerDonne()) {
            int pointsAttaqueTemporaire_;
            int pointsAttaqueDefinitif_;
            int pointsDefenseTemporaire_;
            int pointsDefenseDefinitif_;
            EndBeloteGame end_ = game.getEndBeloteGame();
            pointsAttaqueTemporaire_ = end_.pointsAttackWithBonus();
            pointsDefenseTemporaire_ = end_.pointsDefenseWithBonus();
            pointsAttaqueDefinitif_=end_.scoreDefinitifAttaque(pointsAttaqueTemporaire_, pointsDefenseTemporaire_);
            pointsDefenseDefinitif_=end_.scoreDefinitifDefense(pointsAttaqueDefinitif_,pointsDefenseTemporaire_);
            game.setScores(end_.scores(pointsAttaqueDefinitif_, pointsDefenseDefinitif_));
            scoresDeal_=game.getScores();
            differenceScoreTaker = end_.getDiffAttackPointsMinusDefensePoints(scoresDeal_);
            endTarotGame = end_.getUserState(user,scoresDeal_);
        } else {
            int nbPl_ = game.getNombreDeJoueurs();
            for (int i = 0; i < nbPl_; i++) {
                scoresDeal_.add((short)0);
            }
        }
        GameType type_ = game.getType();
        long number_ = game.getNombre();
        int nbDeals_ = game.getRegles().getNombreParties();
        calculateScores(scoresDeal_, type_, number_, nbDeals_);
    }

    void calculateScores(Shorts _scoresDeal, GameType _type, long _number, int _nbDeals) {
        if(hasToCalculateScores(_type, _number, _nbDeals)) {
            long variance9_=0;
            long esperance_=0;
            res.getScores().add(new Longs());
            int nbPl_ = _scoresDeal.size();
            if(res.getScores().size()==1) {
                for(short score_: _scoresDeal) {
                    res.getScores().last().add((long)score_);
                }
            } else {
                byte indice_=0;
                for(short score_: _scoresDeal) {
                    res.getScores().last().add(score_+res.getScores().get(res.getScores().size()-2).get(indice_));
                    indice_++;
                }
            }
            for(long score_:res.getScores().last()) {
                esperance_+=score_;
            }
            /*Somme des_ scores*/
            variance9_+=3*esperance_;
            /*Somme des_ scores fois_ trois_*/
            variance9_*=variance9_;
            /*Carre de_ la_ somme_ des_ scores fois_ trois_ (Le carre_ comprend_ le_ fois_ trois_)*/
            variance9_=-variance9_;
            /*Oppose du_ carre_ de_ la_ somme_ des_ scores fois_ trois_*/
            for(long score_:res.getScores().last()) {
                variance9_+=score_*score_*9* nbPl_;
            }
            /*variance9_ vaut_ neuf_ fois_ la_ variance_ des_ scores fois_ le_ carre_ du_ nombre_ de_ joueurs_*/
            res.getSigmas().add(new Rate(variance9_, nbPl_ * nbPl_).rootAbs(new LgInt(2)));
            res.getSums().add(esperance_);
        }
    }

    static boolean hasToCalculateScores(GameType _type, long _number, int _nbDeals) {
        return _type ==GameType.RANDOM&& _number ==0|| _type == GameType.EDIT && _number <= _nbDeals;
    }
    public GameBelote getGame() {
        return game;
    }
    public void setGame(GameBelote _game) {
        game = _game;
    }
    public StringList getNicknames() {
        return nicknames;
    }
    public void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
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

    public String getGlobalResultsPageTitle() {
        return res.getGlobalResultsPageTitle();
    }

    public void setGlobalResultsPageTitle(String _globalResultsPageTitle) {
        res.setGlobalResultsPageTitle(_globalResultsPageTitle);
    }

    public String getDetailResultsTitle() {
        return res.getDetailResultsTitle();
    }

    public void setDetailResultsTitle(String _detailResultsTitle) {
        res.setDetailResultsTitle(_detailResultsTitle);
    }

    public StringMap<String> getRenderedPages() {
        return res.getRenderedPages();
    }

    public EqList<Rate> getSigmas() {
        return res.getSigmas();
    }

    public Longs getSums() {
        return res.getSums();
    }

    public CustList<Longs> getScores() {
        return res.getScores();
    }

    public void setScores(CustList<Longs> _scores) {
        res.setScores(_scores);
    }

    public void setRenderedPages(StringMap<String> _renderedPages) {
        res.setRenderedPages(_renderedPages);
    }

    public void setSigmas(EqList<Rate> _sigmas) {
        res.setSigmas(_sigmas);
    }

    public void setSums(Longs _sums) {
        res.setSums(_sums);
    }

    public EndGameState getEndTarotGame() {
        return endTarotGame;
    }

    public int getDifferenceScoreTaker() {
        return differenceScoreTaker;
    }
}
