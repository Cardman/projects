package cards.tarot;
import cards.consts.EndGameState;
import cards.consts.GameType;
import cards.consts.Status;
import cards.consts.ResultsGame;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;


public final class ResultsTarot {

    private static final int FACTOR = 3;
    private ResultsGame res = new ResultsGame();

    private GameTarot game;

    private StringList nicknames;

    private byte user;

    private String loc;

    private EndGameState endTarotGame = EndGameState.EQUALLITY;

    private short finalUserPosition;

    private short maxDifference;

    private Shorts positionsDiff = new Shorts();
    private Shorts positionsOne = new Shorts();
    private Shorts positionsTwo = new Shorts();
    private Shorts positionsThree = new Shorts();
    private Shorts positionsFour = new Shorts();
    private Shorts coefficients = new Shorts();
    private String scoreSmallBound = "";
    private String playerSmallBound = "";

    public void initialize(StringList _pseudos,
            CustList<Longs> _scores) {
        res.setScores(_scores);
        nicknames = _pseudos;
        Shorts scoresDeal_ = new Shorts();
        short basePoints_;
        short doubledScoreTaker_;
        short differenceScoreTaker_;
        EnumMap<Status,Rate> repartitionRate_;
        short additionnalBonusesAttack_;
        short additionnalBonusesDefense_;
        short scoreTakerWithoutDeclaring_;
        short needlyScoresTaker_;
        Shorts additionnalBonuses_ =new Shorts();
        short maxDoubledDifference_=0;
        byte nombreJoueurs_=game.getNombreDeJoueurs();
        BidTarot contrat_=game.getContrat();
        CustList<TreeMap<Miseres,Short>> miseresTaker_;
        CustList<TreeMap<Handfuls,Short>> handfulsTaker_;
        Shorts doubledScoresPlayersTricks_ = new Shorts();
        Shorts needlyScoresPlayers_ = new Shorts();
        Shorts doublesDifferencesPlayers_ = new Shorts();
        if(!game.getTricks().isEmpty()) {
            if(contrat_.isJouerDonne()) {
                EndTarotGame end_ = game.getEndTarotGame();
                end_.setupSlams();
                miseresTaker_ = end_.getMiseresPointsForTaker();
                doubledScoreTaker_=end_.scorePreneurPlisDouble(contrat_);
                needlyScoresTaker_=end_.scoreNecessairePreneur(contrat_);
                short scorePreneurPlis_=end_.scorePreneurPlis(doubledScoreTaker_, needlyScoresTaker_);
                differenceScoreTaker_=(short) (scorePreneurPlis_-needlyScoresTaker_);
                playerSmallBound= end_.joueurPetitAuBout(_pseudos);
                scoreSmallBound = end_.scoreSmallBound();
                endTarotGame = end_.getUserState(differenceScoreTaker_,user);
                basePoints_=end_.base(doubledScoreTaker_,differenceScoreTaker_);
                scoreTakerWithoutDeclaring_=end_.scorePreneurSansAnnonces(differenceScoreTaker_,basePoints_);
                handfulsTaker_ = end_.getHandfulsPointsForTaker(scoreTakerWithoutDeclaring_);
                additionnalBonusesAttack_ = end_.additionnalBonusesAttack(contrat_);
                additionnalBonusesDefense_ = end_.additionnalBonusesDefense();
                short sommeTemporaire_= EndTarotGame.temporarySum(contrat_,scoreTakerWithoutDeclaring_, miseresTaker_, handfulsTaker_, additionnalBonusesAttack_, additionnalBonusesDefense_);
                repartitionRate_=end_.coefficientsRepartition();
                game.setScores(end_.calculateScores(repartitionRate_, sommeTemporaire_, scoreTakerWithoutDeclaring_));
                scoresDeal_=game.getScores();
            } else {
                EndTarotGame end_ = game.getEndTarotGame();
                end_.setupPlayersWonTricks();
                boolean pasJeuMisere_=game.pasJeuMisere();
                if(pasJeuMisere_) {
                    for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                        short ptsDb_ = end_.scoreJoueurPlisDouble(joueur_);
                        doubledScoresPlayersTricks_.add(ptsDb_);
                        short ptsNeed_ = end_.scoreNecessaireJoueur(joueur_);
                        needlyScoresPlayers_.add(ptsNeed_);
                        short diffDb_ = EndTarotGame.differenceJoueurDouble(ptsNeed_, ptsDb_);
                        doublesDifferencesPlayers_.add(diffDb_);
                        maxDoubledDifference_=(short)Math.max(maxDoubledDifference_,diffDb_);
                        additionnalBonuses_.add(end_.primeSupplementaire(joueur_));
                    }
                    maxDifference=end_.differenceMax(maxDoubledDifference_);
                    positionsDiff=EndTarotGame.positionsDifference(doublesDifferencesPlayers_);
                    positionsOne = end_.changePositionsOne(positionsDiff, true);
                    positionsTwo = end_.changePositionsTwo(positionsOne, true);
                    positionsThree = end_.changePositionsThree(positionsTwo, true);
                    positionsFour = end_.changePositionsFour(positionsThree, true);
                    coefficients=end_.coefficients(positionsFour);
                    game.setScores(end_.calculerScoresJoueurs(coefficients, maxDoubledDifference_, additionnalBonuses_));
                    scoresDeal_=game.getScores();
                } else {
                    for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                        doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble(joueur_));
                        needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                        doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                        maxDoubledDifference_=(short)Math.max(maxDoubledDifference_,doublesDifferencesPlayers_.last());
                    }
                    maxDifference=end_.differenceMax(maxDoubledDifference_);
                    positionsDiff=EndTarotGame.positionsDifference(doublesDifferencesPlayers_);
                    positionsOne = end_.changePositionsOne(positionsDiff, false);
                    positionsTwo = end_.changePositionsTwo(positionsOne, false);
                    positionsThree = end_.changePositionsThree(positionsTwo, false);
                    positionsFour = end_.changePositionsFour(positionsThree, false);
                    coefficients=end_.coefficientsMisere(positionsFour);
                    game.setScores(end_.calculerScoresJoueurs(coefficients,maxDoubledDifference_));
                    scoresDeal_=game.getScores();
                }
                finalUserPosition = positionsFour.get(user);
            }
        } else {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                scoresDeal_.add((short)0);
            }
        }
        GameType type_ = game.getType();
        long number_ = game.getNumber();
        int nbDeals_ = game.getRegles().getNbDeals();
        calculateScores(scoresDeal_, type_, number_, nbDeals_);
    }

    void calculateScores(Shorts _scoresDeal, GameType _type, long _number, int _nbDeals) {
        if(hasToCalculateScores(_type, _number, _nbDeals)) {
            int nbPl_ = _scoresDeal.size();
            long variance9_=0;
            long esperance_=0;
            res.getScores().add(new Longs());
            if(res.getScores().size()==1) {
                for(short score_: _scoresDeal) {
                    res.getScores().last().add((long) score_);
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
            variance9_+=FACTOR*esperance_;
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

    public GameTarot getGame() {
        return game;
    }

    public void setGame(GameTarot _game) {
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

    public short getFinalUserPosition() {
        return finalUserPosition;
    }

    public Shorts getPositionsDiff() {
        return positionsDiff;
    }

    public short getMaxDifference() {
        return maxDifference;
    }

    public Shorts getPositionsOne() {
        return positionsOne;
    }

    public Shorts getPositionsTwo() {
        return positionsTwo;
    }

    public Shorts getPositionsThree() {
        return positionsThree;
    }

    public Shorts getPositionsFour() {
        return positionsFour;
    }

    public Shorts getCoefficients() {
        return coefficients;
    }

    public String getPlayerSmallBound() {
        return playerSmallBound;
    }

    public String getScoreSmallBound() {
        return scoreSmallBound;
    }
}
