package cards.president;
import cards.consts.GameType;
import cards.consts.ResultsGame;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;


public final class ResultsPresident {
    private ResultsGame res = new ResultsGame();
    private GamePresident game;

    private StringList nicknames;

    private byte user;

    private String loc;

    public void initialize(StringList _pseudos, CustList<Longs> _scores, Bytes _r) {
        res.setScores(_scores);
        nicknames = _pseudos;
        Shorts scoresDeal_ = new Shorts();
        for (byte b: _r) {
            scoresDeal_.add((short) b);
        }
        res.getScores().add(new Longs());
        for(short score_:scoresDeal_) {
            res.getScores().last().add((long)score_);
        }
//        if(getScores().size()==1) {
//            for(short score_:scoresDeal_) {
//                getScores().last().add((long)score_);
//            }
//        } else {
//            byte indice_=0;
//            for(short score_:scoresDeal_) {
//                getScores().last().add(score_+getScores().get(getScores().size()-2).get(indice_));
//                indice_++;
//            }
//        }
    }

    public void initialize(StringList _pseudos, CustList<Longs> _scores) {
        res.setScores(_scores);
        nicknames = _pseudos;
        Shorts scoresDeal_ = new Shorts();
        for (byte b: game.getNewRanks()) {
            scoresDeal_.add((short) b);
        }
        GameType type_ = game.getType();
        long number_ = game.getNombre();
        int nbDeals_ = game.getRegles().getNbDeals();
        calculateScores(scoresDeal_, type_, number_, nbDeals_);
    }

    void calculateScores(Shorts _scoresDeal, GameType _type, long _number, int _nbDeals) {
        if(hasToCalculateScores(_type, _number, _nbDeals)) {
            long variance9_=0;
            long esperance_=0;
            int nbPl_ = _scoresDeal.size();
            res.getScores().add(new Longs());
            for(short score_: _scoresDeal) {
                res.getScores().last().add((long)score_);
            }
//            if(getScores().size()==1) {
//                for(short score_:scoresDeal_) {
//                    getScores().last().add((long)score_);
//                }
//            } else {
//                byte indice_=0;
//                for(short score_:scoresDeal_) {
//                    getScores().last().add(score_+getScores().get(getScores().size()-2).get(indice_));
//                    indice_++;
//                }
//            }
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
                variance9_+=score_*score_*9*nbPl_;
            }
            /*variance9_ vaut_ neuf_ fois_ la_ variance_ des_ scores fois_ le_ carre_ du_ nombre_ de_ joueurs_*/
            res.getSigmas().add(new Rate(variance9_,nbPl_*nbPl_).rootAbs(new LgInt(2)));
            res.getSums().add(esperance_);
        }
    }

    static boolean hasToCalculateScores(GameType _type, long _number, int _nbDeals) {
        return _type ==GameType.RANDOM&& _number ==0|| _type == GameType.EDIT && _number <= _nbDeals;
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
    public byte getUser() {
        return user;
    }
    public void setUser(byte _user) {
        user = _user;
    }
    public String getLoc() {
        return loc;
    }

    public void setNicknames(StringList _nicknames) {
        nicknames = _nicknames;
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

    public CustList<Rate> getSigmas() {
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
}
