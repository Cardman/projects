package cards.belote;
import code.maths.LgInt;
import code.maths.Rate;
import code.stream.ExtractFromFiles;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;
import cards.consts.GameType;
import cards.gameresults.ResultsGame;

@RwXml
public final class ResultsBelote extends ResultsGame {

    private GameBelote game;

    private StringList nicknames;

    private byte user;

    private String loc;

    @Override
    public void initialize(StringList _pseudos, CustList<Numbers<Long>> _scores) {
        setScores(_scores);
        nicknames = _pseudos;
        Numbers<Short> scoresDeal_ = new Numbers<Short>();
        byte nombreJoueurs_=game.getNombreDeJoueurs();
        BidBeloteSuit bid_ = game.getContrat();
        int pointsAttaqueSansPrime_=game.pointsAttaqueSansPrime();
        int pointsAttaqueTemporaire_=pointsAttaqueSansPrime_;
        int pointsAttaqueDefinitif_=0;
        int pointsDefenseSansPrime_=game.pointsDefenseSansPrime();
        int pointsDefenseTemporaire_=pointsDefenseSansPrime_;
        int pointsDefenseDefinitif_=0;
        if(bid_.jouerDonne()) {
            game.getPreneur();
            pointsAttaqueTemporaire_ = game.pointsAttackWithBonus();
            pointsDefenseTemporaire_ = game.pointsDefenseWithBonus();
            pointsAttaqueDefinitif_=game.scoreDefinitifAttaque(pointsAttaqueTemporaire_, pointsDefenseTemporaire_);
            pointsDefenseDefinitif_=game.scoreDefinitifDefense(pointsAttaqueDefinitif_,pointsDefenseTemporaire_);
            game.scores(pointsAttaqueDefinitif_, pointsDefenseDefinitif_);
            scoresDeal_=game.getScores();
        }
        if(game.getType()==GameType.RANDOM&&game.getNombre()==0 || game.getType() == GameType.EDIT && game.getNombre() <= game.getRegles().getNombreParties()) {
            long variance9_=0;
            long esperance_=0;
            getScores().add(new Numbers<Long>());
            if(getScores().size()==1) {
                for(short score_:scoresDeal_) {
                    getScores().last().add((long)score_);
                }
            } else {
                byte indice_=0;
                for(short score_:scoresDeal_) {
                    getScores().last().add(score_+getScores().get(getScores().size()-2).get(indice_));
                    indice_++;
                }
            }
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
        messages_ = ExtractFromFiles.getMessagesFromLocaleClass(RESOURCES_CLASS_PATH, _loc, RESULTS_BELOTE);
        setGlobalResultsPageTitle(messages_.getVal(RESULTS_PAGE));
        setDetailResultsTitle(messages_.getVal(DETAIL_RESULTS_PAGE));
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
    public byte getUser() {
        return user;
    }
    public void setUser(byte _user) {
        user = _user;
    }
    public String getLoc() {
        return loc;
    }
}
