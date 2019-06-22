package cards.tarot;
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

    public void initialize(StringList _pseudos,
            CustList<Longs> _scores) {
        res.setScores(_scores);
        nicknames = _pseudos;
        Shorts scoresDeal_ = new Shorts();
        short basePoints_=0;
        short doubledScoreTaker_=0;
        short differenceScoreTaker_=0;
        EnumMap<Status,Rate> repartitionRate_=new EnumMap<Status,Rate>();
        short additionnalBonusesAttack_=0;
        short additionnalBonusesDefense_=0;
        short scoreTakerWithoutDeclaring_=0;
        short needlyScoresTaker_=0;
        Shorts additionnalBonuses_ =new Shorts();
        Shorts coefficients_ =new Shorts();
        short maxDoubledDifference_=0;
        byte nombreJoueurs_=game.getNombreDeJoueurs();
        BidTarot contrat_=game.getContrat();
        CustList<TreeMap<Miseres,Short>> miseresTaker_ = new CustList<TreeMap<Miseres,Short>>();
        CustList<TreeMap<Handfuls,Short>> handfulsTaker_ = new CustList<TreeMap<Handfuls,Short>>();
        Shorts positions_ = new Shorts();
        Shorts positions1_ = new Shorts();
        Shorts positions2_ = new Shorts();
        Shorts positions3_ = new Shorts();
        Shorts positions4_ = new Shorts();
        Shorts doubledScoresPlayersTricks_ = new Shorts();
        Shorts needlyScoresPlayers_ = new Shorts();
        Shorts doublesDifferencesPlayers_ = new Shorts();
        if(contrat_.isJouerDonne()) {
            EndTarotGame end_ = game.getEndTarotGame();
            miseresTaker_ = end_.getMiseresPointsForTaker();
            doubledScoreTaker_=end_.scorePreneurPlisDouble(contrat_);
            needlyScoresTaker_=end_.scoreNecessairePreneur(contrat_);
            short scorePreneurPlis_=end_.scorePreneurPlis(doubledScoreTaker_, needlyScoresTaker_);
            differenceScoreTaker_=(short) (scorePreneurPlis_-needlyScoresTaker_);
            basePoints_=end_.base(doubledScoreTaker_,differenceScoreTaker_);
            scoreTakerWithoutDeclaring_=end_.scorePreneurSansAnnonces(differenceScoreTaker_,basePoints_);
            handfulsTaker_ = end_.getHandfulsPointsForTaker(scoreTakerWithoutDeclaring_);
            additionnalBonusesAttack_ = end_.additionnalBonusesAttack(contrat_);
            additionnalBonusesDefense_ = end_.additionnalBonusesDefense(contrat_);
            short sommeTemporaire_=end_.temporarySum(contrat_,scoreTakerWithoutDeclaring_, miseresTaker_, handfulsTaker_, additionnalBonusesAttack_, additionnalBonusesDefense_);
            repartitionRate_=end_.coefficientsRepartition();
            game.setScores(end_.calculateScores(repartitionRate_, sommeTemporaire_, scoreTakerWithoutDeclaring_));
            scoresDeal_=game.getScores();
        } else if(!game.unionPlis().isEmpty()) {
            EndTarotGame end_ = game.getEndTarotGame();
            boolean pasJeuMisere_=game.pasJeuMisere();
            if(pasJeuMisere_) {
                for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                    doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble( joueur_));
                    needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                    doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDouble(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                    maxDoubledDifference_=(short)Math.max(maxDoubledDifference_,doublesDifferencesPlayers_.last());
                    additionnalBonuses_.add(end_.primeSupplementaire(joueur_));
                }
                positions_=EndTarotGame.positionsDifference(doublesDifferencesPlayers_);
                positions1_ = end_.changePositionsOne(positions_,pasJeuMisere_);
                positions2_ = end_.changePositionsTwo(positions1_,pasJeuMisere_);
                positions3_ = end_.changePositionsThree(positions2_,pasJeuMisere_);
                positions4_ = end_.changePositionsFour(positions3_, pasJeuMisere_);
                coefficients_=end_.coefficients(positions4_);
                game.setScores(end_.calculerScoresJoueurs(coefficients_, maxDoubledDifference_, additionnalBonuses_));
                scoresDeal_=game.getScores();
            } else {
                for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                    doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble(joueur_));
                    needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                    doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                    maxDoubledDifference_=(short)Math.max(maxDoubledDifference_,doublesDifferencesPlayers_.last());
                }
                positions_=EndTarotGame.positionsDifference(doublesDifferencesPlayers_);
                positions1_ = end_.changePositionsOne(positions_,pasJeuMisere_);
                positions2_ = end_.changePositionsTwo(positions1_,pasJeuMisere_);
                positions3_ = end_.changePositionsThree(positions2_,pasJeuMisere_);
                positions4_ = end_.changePositionsFour(positions3_, pasJeuMisere_);
                coefficients_=end_.coefficientsMisere(positions4_);
                game.setScores(end_.calculerScoresJoueurs(coefficients_,maxDoubledDifference_));
                scoresDeal_=game.getScores();
            }
        } else {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                scoresDeal_.add((short)0);
            }
        }
        if(game.getType()==GameType.RANDOM&&game.getNumber()==0|| game.getType() == GameType.EDIT && game.getNumber() <= game.getRegles().getNbDeals()) {
            long variance9_=0;
            long esperance_=0;
            res.getScores().add(new Longs());
            if(res.getScores().size()==1) {
                for(short score_:scoresDeal_) {
                    res.getScores().last().add((long) score_);
                }
            } else {
                byte indice_=0;
                for(short score_:scoresDeal_) {
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
                variance9_+=score_*score_*9*nombreJoueurs_;
            }
            /*variance9_ vaut_ neuf_ fois_ la_ variance_ des_ scores fois_ le_ carre_ du_ nombre_ de_ joueurs_*/
            res.getSigmas().add(new Rate(variance9_,nombreJoueurs_*nombreJoueurs_).rootAbs(new LgInt(2)));
            res.getSums().add(esperance_);
        }
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
}
