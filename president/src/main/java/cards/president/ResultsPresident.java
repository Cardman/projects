package cards.president;
import cards.consts.GameType;
import cards.gameresults.ResultsGame;
import code.maths.LgInt;
import code.maths.Rate;
import code.sml.util.ExtractFromFiles;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;


public final class ResultsPresident extends ResultsGame {

    private GamePresident game;

    private StringList nicknames;

    private byte user;

    private String loc;

    public void initialize(StringList _pseudos, CustList<Numbers<Long>> _scores, Numbers<Byte> _r) {
        setScores(_scores);
        nicknames = _pseudos;
        Numbers<Short> scoresDeal_ = new Numbers<Short>();
        for (byte b: _r) {
            scoresDeal_.add((short) b);
        }
        getScores().add(new Numbers<Long>());
        for(short score_:scoresDeal_) {
            getScores().last().add((long)score_);
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

    @Override
    public void initialize(StringList _pseudos, CustList<Numbers<Long>> _scores) {
        setScores(_scores);
        nicknames = _pseudos;
        Numbers<Short> scoresDeal_ = new Numbers<Short>();
        for (byte b: game.getNewRanks()) {
            scoresDeal_.add((short) b);
        }
        byte nombreJoueurs_=game.getNombreDeJoueurs();
        if(game.getType()==GameType.RANDOM&&game.getNombre()==0|| game.getType() == GameType.EDIT && game.getNombre() <= game.getRegles().getNbDeals()) {
            long variance9_=0;
            long esperance_=0;
            getScores().add(new Numbers<Long>());
            for(short score_:scoresDeal_) {
                getScores().last().add((long)score_);
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
            for(long score_:getScores().last()) {
                esperance_+=score_;
            }
            /*Somme des_ scores*/
            variance9_+=3*esperance_;
            /*Somme des_ scores fois_ trois_*/
            variance9_*=variance9_;
            /*Carre de_ la_ somme_ des_ scores fois_ trois_ (Le carre_ comprend_ le_ fois_ trois_)*/
            variance9_=-variance9_;
            /*Oppose du_ carre_ de_ la_ somme_ des_ scores fois_ trois_*/
            for(long score_:getScores().last()) {
                variance9_+=score_*score_*9*nombreJoueurs_;
            }
            /*variance9_ vaut_ neuf_ fois_ la_ variance_ des_ scores fois_ le_ carre_ du_ nombre_ de_ joueurs_*/
            getSigmas().add(new Rate(variance9_,nombreJoueurs_*nombreJoueurs_).rootAbs(new LgInt(2)));
            getSums().add(esperance_);
        }
    }

    public void setMessages(String _loc) {
        loc = _loc;
        StringMap<String> messages_ = new StringMap<String>();
        messages_ = ExtractFromFiles.getMessagesFromLocaleClass(RESOURCES_CLASS_PATH, _loc, RESULTS_PRESIDENT);
        setGlobalResultsPageTitle(messages_.getVal(RESULTS_PAGE));
        setDetailResultsTitle(messages_.getVal(DETAIL_RESULTS_PAGE));
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
}
