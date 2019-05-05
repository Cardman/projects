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
            CustList<Numbers<Long>> _scores) {
        res.setScores(_scores);
        nicknames = _pseudos;
        Numbers<Short> scoresDeal_ = new Numbers<Short>();
        short basePoints_=0;
        short doubledScoreTaker_=0;
        short differenceScoreTaker_=0;
        EnumMap<Status,Rate> repartitionRate_=new EnumMap<Status,Rate>();
        short additionnalBonusesAttack_=0;
        short additionnalBonusesDefense_=0;
        short scoreTakerWithoutDeclaring_=0;
        short needlyScoresTaker_=0;
        Numbers<Short> additionnalBonuses_ =new Numbers<Short>();
        Numbers<Short> coefficients_ =new Numbers<Short>();
        short maxDoubledDifference_=0;
        byte nombreJoueurs_=game.getNombreDeJoueurs();
        BidTarot contrat_=game.getContrat();
        CustList<TreeMap<Miseres,Short>> miseresTaker_ = new CustList<TreeMap<Miseres,Short>>();
        CustList<TreeMap<Handfuls,Short>> handfulsTaker_ = new CustList<TreeMap<Handfuls,Short>>();
        Numbers<Short> positions_ = new Numbers<Short>();
        Numbers<Short> positions1_ = new Numbers<Short>();
        Numbers<Short> positions2_ = new Numbers<Short>();
        Numbers<Short> positions3_ = new Numbers<Short>();
        Numbers<Short> positions4_ = new Numbers<Short>();
        Numbers<Short> doubledScoresPlayersTricks_ = new Numbers<Short>();
        Numbers<Short> needlyScoresPlayers_ = new Numbers<Short>();
        Numbers<Short> doublesDifferencesPlayers_ = new Numbers<Short>();
        if(contrat_.isJouerDonne()) {
            miseresTaker_ = game.getMiseresPointsForTaker();
            doubledScoreTaker_=game.scorePreneurPlisDouble();
            needlyScoresTaker_=game.scoreNecessairePreneur();
            short scorePreneurPlis_=game.scorePreneurPlis(doubledScoreTaker_, needlyScoresTaker_);
            differenceScoreTaker_=(short) (scorePreneurPlis_-needlyScoresTaker_);
            basePoints_=game.base(doubledScoreTaker_,differenceScoreTaker_);
            scoreTakerWithoutDeclaring_=game.scorePreneurSansAnnonces(differenceScoreTaker_,basePoints_);
            handfulsTaker_ = game.getHandfulsPointsForTaker(scoreTakerWithoutDeclaring_);
            additionnalBonusesAttack_ = game.additionnalBonusesAttack();
            additionnalBonusesDefense_ = game.additionnalBonusesDefense();
            short sommeTemporaire_=game.temporarySum(scoreTakerWithoutDeclaring_, miseresTaker_, handfulsTaker_, additionnalBonusesAttack_, additionnalBonusesDefense_);
            repartitionRate_=game.coefficientsRepartition();
            game.calculateScores(repartitionRate_, sommeTemporaire_, scoreTakerWithoutDeclaring_);
            scoresDeal_=game.getScores();
        } else if(!game.unionPlis(true).isEmpty()) {
            boolean pasJeuMisere_=game.pasJeuMisere();
            if(pasJeuMisere_) {
                for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                    doubledScoresPlayersTricks_.add(game.scoreJoueurPlisDouble( joueur_));
                    needlyScoresPlayers_.add(game.scoreNecessaireJoueur(joueur_));
                    doublesDifferencesPlayers_.add(GameTarot.differenceJoueurDouble(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                    maxDoubledDifference_=(short)Math.max(maxDoubledDifference_,doublesDifferencesPlayers_.last());
                    additionnalBonuses_.add(game.primeSupplementaire(joueur_));
                }
                positions_=GameTarot.positionsDifference(doublesDifferencesPlayers_);
                positions1_ = game.changePositionsOne(positions_,pasJeuMisere_);
                positions2_ = game.changePositionsTwo(positions1_,pasJeuMisere_);
                positions3_ = game.changePositionsThree(positions2_,pasJeuMisere_);
                positions4_ = game.changePositionsFour(positions3_, pasJeuMisere_);
                coefficients_=game.coefficients(positions4_);
                game.calculerScoresJoueurs(coefficients_, maxDoubledDifference_, additionnalBonuses_);
                scoresDeal_=game.getScores();
            } else {
                for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                    doubledScoresPlayersTricks_.add(game.scoreJoueurPlisDouble(joueur_));
                    needlyScoresPlayers_.add(game.scoreNecessaireJoueur(joueur_));
                    doublesDifferencesPlayers_.add(GameTarot.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                    maxDoubledDifference_=(short)Math.max(maxDoubledDifference_,doublesDifferencesPlayers_.last());
                }
                positions_=GameTarot.positionsDifference(doublesDifferencesPlayers_);
                positions1_ = game.changePositionsOne(positions_,pasJeuMisere_);
                positions2_ = game.changePositionsTwo(positions1_,pasJeuMisere_);
                positions3_ = game.changePositionsThree(positions2_,pasJeuMisere_);
                positions4_ = game.changePositionsFour(positions3_, pasJeuMisere_);
                coefficients_=game.coefficientsMisere(positions4_);
                game.calculerScoresJoueurs(coefficients_,maxDoubledDifference_);
                scoresDeal_=game.getScores();
            }
        } else {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                scoresDeal_.add((short)0);
            }
        }
        if(game.getType()==GameType.RANDOM&&game.getNumber()==0|| game.getType() == GameType.EDIT && game.getNumber() <= game.getRegles().getNombreParties()) {
            long variance9_=0;
            long esperance_=0;
            res.getScores().add(new Numbers<Long>());
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

    public Numbers<Long> getSums() {
        return res.getSums();
    }

    public CustList<Numbers<Long>> getScores() {
        return res.getScores();
    }

    public void setScores(CustList<Numbers<Long>> _scores) {
        res.setScores(_scores);
    }

    public void setRenderedPages(StringMap<String> _renderedPages) {
        res.setRenderedPages(_renderedPages);
    }

    public void setSigmas(EqList<Rate> _sigmas) {
        res.setSigmas(_sigmas);
    }

    public void setSums(Numbers<Long> _sums) {
        res.setSums(_sums);
    }
}
