package cards.tarot.beans;

import cards.consts.Role;
import cards.tarot.EndTarotGame;
import cards.tarot.GameTarot;
import cards.tarot.GameTarotTeamsRelation;
import cards.tarot.ResultsTarot;
import cards.tarot.comparators.SortedHandfuls;
import cards.tarot.comparators.SortedMiseres;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.maths.Rate;
import code.scripts.pages.cards.MessagesTarotPage;
import code.util.*;
import code.util.core.IndexConstants;


public final class DetailsResultsTarotBean extends TarotBean {

    private static final String EMPTY_STRING = "";

    private long differenceScoreTaker;

    private long basePoints;

    private long rate;

    private String small;

    private String playerSmall = EMPTY_STRING;

    private long multipliedTmp;

    private long sumPlayers;

    private CustList<TarotSumDeclaringPlayer> linesDeclaring;

    private CustList<ScoresPlayers> playersScores;

    private long additionnalBonusesAttack;

    private long additionnalBonusesDefense;

    private long diffAttackDefenseBonuses;

    private CustList<RankingPlayerVariantGame> orderedPlayers;

    private CustList<PointsPlayerVariantGame> pointsPlayers;

    private CustList<BonusesPlayers> bonuses;

    public void build() {
        beforeDisplaying();
        if (playClassicGame()) {
            classicGame();
        }
        if (playVariantModeGame()) {
            variantModeGame();
        }
    }

    private void variantModeGame() {
        getBuilder().formatMessageDir(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_1));
        getBuilder().initGrid();
        getBuilder().colCount(6);
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_1_1));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_1_2));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_1_3));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_1_4));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_1_5));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_1_6));
        for (RankingPlayerVariantGame s:orderedPlayers) {
            getBuilder().formatMessageDirCts(s.getNickname());
            getBuilder().formatMessageDirCts(Long.toString(s.getPositionDiff()));
            getBuilder().formatMessageDirCts(Long.toString(s.getPositionOudlers()));
            getBuilder().formatMessageDirCts(Long.toString(s.getPositionCharacters()));
            getBuilder().formatMessageDirCts(Long.toString(s.getPositionStrengthCharacters()));
            getBuilder().formatMessageDirCts(Long.toString(s.getFinalPosition()));
        }
        getBuilder().feedParents();
        getBuilder().formatMessageDir(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_2));
        getBuilder().initGrid();
        getBuilder().colCount(6);
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_2_1));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_2_2));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_2_3));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_2_4));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_2_5));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_TABLE_2_6));
        for (PointsPlayerVariantGame s:pointsPlayers) {
            getBuilder().formatMessageDirCts(s.getNickname());
            getBuilder().formatMessageDirCts(s.getPointsTricks().toNumberString());
            getBuilder().formatMessageDirCts(Long.toString(s.getMinimumPoints()));
            getBuilder().formatMessageDirCts(s.getDifferenceScore().toNumberString());
            getBuilder().formatMessageDirCts(Long.toString(s.getRate()));
            getBuilder().formatMessageDirCts(Long.toString(s.getScore()));
        }
        getBuilder().feedParents();
        header(MessagesTarotPage.M_VARIANT_DECL);
        getBuilder().initPage();
        for (TarotSumDeclaringPlayer p:linesDeclaring) {
            getBuilder().initLine();
            getBuilder().paintMetaLabelDisk();
            getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_DECL_PL,p.getNickname());
            getBuilder().feedParents();
            getBuilder().setIndent(1);
            for (EntryCust<String,Long> d: p.getHandfuls().entryList()) {
                getBuilder().initLine();
                getBuilder().paintMetaLabelDisk();
                getBuilder().formatMessageDir(d.getKey());
                getBuilder().feedParents();
            }
            for (EntryCust<String,Long> d: p.getMiseres().entryList()) {
                getBuilder().initLine();
                getBuilder().paintMetaLabelDisk();
                getBuilder().formatMessageDir(d.getKey());
                getBuilder().feedParents();
            }
            getBuilder().setIndent(0);
        }
        getBuilder().feedParents();
        header(MessagesTarotPage.M_VARIANT_ADD);
        getBuilder().formatMessageDir(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_ADD_PL));
        getBuilder().initGrid();
        getBuilder().colCount(2);
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_ADD_PL_1));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_VARIANT_ADD_PL_2));
        for (BonusesPlayers s:bonuses) {
            getBuilder().formatMessageDirCts(s.getNickname());
            getBuilder().formatMessageDirCts(Long.toString(s.getBonus()));
        }
        getBuilder().feedParents();
    }

    private void classicGame() {
        header(MessagesTarotPage.M_CLASSIC_BID);
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_CLASSIC_BASE);
        getBuilder().formatMessageDir(Long.toString(basePoints));
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_CLASSIC_SMALL);
        getBuilder().formatMessageDir(playerSmall);
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_CLASSIC_DIFF);
        getBuilder().formatMessageDir(Long.toString(differenceScoreTaker));
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_CLASSIC_RATE);
        getBuilder().formatMessageDir(Long.toString(rate));
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_CLASSIC_SCORE_TAKER,Long.toString(basePoints),small,Long.toString(differenceScoreTaker),Long.toString(rate),Long.toString(multipliedTmp));
        header(MessagesTarotPage.M_CLASSIC_DECL);
        getBuilder().initPage();
        for (TarotSumDeclaringPlayer p:linesDeclaring) {
            getBuilder().initLine();
            getBuilder().paintMetaLabelDisk();
            getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_CLASSIC_DECL_PLAYER,p.getNickname(),p.getStatus());
            getBuilder().feedParents();
            getBuilder().setIndent(1);
            for (EntryCust<String,Long> d: p.getHandfuls().entryList()) {
                getBuilder().initLine();
                getBuilder().paintMetaLabelDisk();
                getBuilder().formatMessageDir(d.getKey()+" : "+d.getValue());
                getBuilder().feedParents();
            }
            for (EntryCust<String,Long> d: p.getMiseres().entryList()) {
                getBuilder().initLine();
                getBuilder().paintMetaLabelDisk();
                getBuilder().formatMessageDir(d.getKey()+" : "+d.getValue());
                getBuilder().feedParents();
            }
            getBuilder().initLine();
            getBuilder().paintMetaLabelDisk();
            getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_SUM,Long.toString(p.getSum()));
            getBuilder().feedParents();
            getBuilder().setIndent(0);
        }
        getBuilder().initLine();
        getBuilder().paintMetaLabelDisk();
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_CLASSIC_SUM_PLAYER,Long.toString(sumPlayers));
        getBuilder().feedParents();
        getBuilder().feedParents();
        header(MessagesTarotPage.M_CLASSIC_ADDON);
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_CLASSIC_ADDON_ATT);
        getBuilder().formatMessageDir(Long.toString(additionnalBonusesAttack));
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_CLASSIC_ADDON_DEF);
        getBuilder().formatMessageDir(Long.toString(additionnalBonusesDefense));
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_CLASSIC_ADDON_SUM);
        getBuilder().formatMessageDir(Long.toString(diffAttackDefenseBonuses));
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", MessagesTarotPage.M_CLASSIC_RATE_PL);
        getBuilder().initGrid();
        getBuilder().colCount(3);
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_PLAYER));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_RATE));
        getBuilder().formatMessageDirCts(getBuilder().formatMessageRend(MessagesTarotPage.APP_BEAN,"",MessagesTarotPage.M_SCORE));
        for (ScoresPlayers s:playersScores) {
            getBuilder().formatMessageDirCts(s.getNickname());
            getBuilder().formatMessageDirCts(s.getRate().toNumberString());
            getBuilder().formatMessageDirCts(Long.toString(s.getScore()));
        }
        getBuilder().feedParents();
    }

    private void header(String _key) {
        getBuilder().setHeader(1);
        getBuilder().formatMessage(MessagesTarotPage.APP_BEAN,"", _key);
        getBuilder().setHeader(0);
    }
    @Override
    public void beforeDisplaying() {
        ResultsTarot res_ = getResults();
        GameTarot game_ = res_.getGame();
        setGame(game_);
        setNicknames(res_.getRes().getNicknames());
        setHistory(res_.getRes().getHistory());
        setBid(getGame().getContrat());
        linesDeclaring = new CustList<TarotSumDeclaringPlayer>();
        orderedPlayers = new CustList<RankingPlayerVariantGame>();
        pointsPlayers = new CustList<PointsPlayerVariantGame>();
        bonuses = new CustList<BonusesPlayers>();
        playersScores = new CustList<ScoresPlayers>();
        if (!getGame().getTricks().isEmpty()) {
            if (getBid().isJouerDonne()) {
                bid(res_);
            }else {
                noBid(res_);
            }
        }
    }

    private void noBid(ResultsTarot _res) {
        int nombreJoueurs_ = getGame().getNombreDeJoueurs();
        EndTarotGame end_ = getGame().getEndTarotGame();
        end_.setupPlayersWonTricks();
        Longs doubledScoresPlayersTricks_ = new Longs();
        Longs needlyScoresPlayers_ = new Longs();
        Longs doublesDifferencesPlayers_ = new Longs();
        Longs additionnalBonuses_ =new Longs();
        boolean pasJeuMisere_=getGame().pasJeuMisere();
        if(pasJeuMisere_) {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_< nombreJoueurs_; joueur_++) {
                doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble( joueur_));
                needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDouble(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
                additionnalBonuses_.add(end_.primeSupplementaire(joueur_));
            }
        } else {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_< nombreJoueurs_; joueur_++) {
                doubledScoresPlayersTricks_.add(end_.scoreJoueurPlisDouble(joueur_));
                needlyScoresPlayers_.add(end_.scoreNecessaireJoueur(joueur_));
                doublesDifferencesPlayers_.add(EndTarotGame.differenceJoueurDoubleMisere(needlyScoresPlayers_.last(),doubledScoresPlayersTricks_.last()));
            }
        }
        Longs coefficients_ = rates(_res);
        for (int p = IndexConstants.FIRST_INDEX; p< nombreJoueurs_; p++){
            PointsPlayerVariantGame or_ = new PointsPlayerVariantGame();
            or_.setNickname(getNicknames().get(p));
            or_.setPointsTricks(new Rate(doubledScoresPlayersTricks_.get(p),2));
            or_.setMinimumPoints(needlyScoresPlayers_.get(p));
            or_.setDifferenceScore(new Rate(doublesDifferencesPlayers_.get(p),2));
            or_.setRate(coefficients_.get(p));
            or_.setScore(getGame().getScores().get(p));
            pointsPlayers.add(or_);
        }
        if (getGame().pasJeuMisere()) {
            bonusOneForOne(_res, end_, additionnalBonuses_);
        }
    }

    private void bid(ResultsTarot _res) {
        GameTarot game_ = _res.getGame();
        int nombreJoueurs_ = getGame().getNombreDeJoueurs();
        EndTarotGame end_ = getGame().getEndTarotGame();
        end_.setupSlams();
        long doubledScoreTaker_=end_.scorePreneurPlisDouble(getBid());
        long needlyScoresTaker_=end_.scoreNecessairePreneur(getBid());
        long scorePreneurPlis_=end_.scorePreneurPlis(doubledScoreTaker_, needlyScoresTaker_);
        differenceScoreTaker= scorePreneurPlis_-needlyScoresTaker_;
        basePoints=end_.base(doubledScoreTaker_,differenceScoreTaker);
        long scoreTakerWithoutDeclaring_=end_.scorePreneurSansAnnonces(differenceScoreTaker,basePoints);
        playerSmall = _res.getPlayerSmallBound();
        small = _res.getScoreSmallBound();
        rate = getGame().getContrat().getCoefficient();
        multipliedTmp = scoreTakerWithoutDeclaring_*getBid().getCoefficient();
        boolean existeAnnonce_ = existeAnnonce();
        if(existeAnnonce_) {
            declaring(_res, end_, scoreTakerWithoutDeclaring_);
        }
        IdMap<Role,Rate> repartitionRate_=end_.coefficientsRepartition();
        for (int p = IndexConstants.FIRST_INDEX; p< nombreJoueurs_; p++) {
            ScoresPlayers scoresPayer_ = new ScoresPlayers();
            scoresPayer_.setNickname(getNicknames().get(p));
            scoresPayer_.setRate(repartitionRate_.getVal(GameTarotTeamsRelation.statutDe(p, game_.getPreneur(), game_.getAppele())));
            scoresPayer_.setScore(getGame().getScores().get(p));
            playersScores.add(scoresPayer_);
        }
        additionnalBonusesAttack = end_.additionnalBonusesAttack(getBid());
        additionnalBonusesDefense = end_.additionnalBonusesDefense();
        diffAttackDefenseBonuses = additionnalBonusesAttack-additionnalBonusesDefense;
    }

    private void bonusOneForOne(ResultsTarot _res, EndTarotGame _end, Longs _additionnalBonuses) {
        int nombreJoueurs_ = getGame().getNombreDeJoueurs();
        for (int p = IndexConstants.FIRST_INDEX; p< nombreJoueurs_; p++){
            TarotSumDeclaringPlayer line_ = new TarotSumDeclaringPlayer();
            SortedHandfuls handfulsTakerLoc_ = new SortedHandfuls();
            for (Handfuls h: _end.calculHandfulsScorePlayer(p).get(p).getKeys()) {
                handfulsTakerLoc_.put(h, 0L);
            }
            line_.setNickname(getNicknames().get(p));
            StringMap<Long> hands_ = new StringMap<Long>();
            for (EntryCust<Handfuls,Long> e: handfulsTakerLoc_.entryList()) {
                Handfuls h_ = e.getKey();
                hands_.addEntry(toString(h_, _res.getRes().getSpecific()), e.getValue());
            }
            line_.setHandfuls(hands_);
            SortedMiseres miseres_ = new SortedMiseres();
            for (Miseres m: _end.calculMiseresScorePlayer(p).get(p).getKeys()) {
                miseres_.put(m, 0L);
            }
            StringMap<Long> mis_ = new StringMap<Long>();
            for (EntryCust<Miseres, Long> e: miseres_.entryList()) {
                Miseres m_ = e.getKey();
                mis_.addEntry(toString(m_, _res.getRes().getSpecific()), e.getValue());
            }
            line_.setMiseres(mis_);
            line_.setSum(0);
            linesDeclaring.add(line_);
        }
        for (int p = IndexConstants.FIRST_INDEX; p< nombreJoueurs_; p++) {
            BonusesPlayers bon_ = new BonusesPlayers();
            bon_.setNickname(getNicknames().get(p));
            bon_.setBonus(_additionnalBonuses.get(p));
            bonuses.add(bon_);
        }
    }

    private Longs rates(ResultsTarot _res) {
        int nombreJoueurs_ = getGame().getNombreDeJoueurs();
        Ints positions_ = _res.getPositionsDiff();
        Ints positions1_ = _res.getPositionsOne();
        Ints positions2_ = _res.getPositionsTwo();
        Ints positions3_ = _res.getPositionsThree();
        Ints positions4_ = _res.getPositionsFour();
        Longs coefficients_ = _res.getCoefficients();
        for (int p = IndexConstants.FIRST_INDEX; p< nombreJoueurs_; p++){
            RankingPlayerVariantGame or_ = new RankingPlayerVariantGame();
            or_.setNickname(getNicknames().get(p));
            or_.setPositionDiff(positions_.get(p));
            or_.setPositionOudlers(positions1_.get(p));
            or_.setPositionCharacters(positions2_.get(p));
            or_.setPositionStrengthCharacters(positions3_.get(p));
            or_.setFinalPosition(positions4_.get(p));
            orderedPlayers.add(or_);
        }
        return coefficients_;
    }

    private void declaring(ResultsTarot _res, EndTarotGame _end, long _scoreTakerWithoutDeclaring) {
        GameTarot game_ = _res.getGame();
        int nombreJoueurs_ = getGame().getNombreDeJoueurs();
        int sumPlayers_ = 0;
        CustList<SortedHandfuls> handfulsTaker_ = _end.getHandfulsPointsForTaker(_scoreTakerWithoutDeclaring);
        CustList<SortedMiseres> miseresTaker_ = _end.getMiseresPointsForTaker();
        for (int p = IndexConstants.FIRST_INDEX; p< nombreJoueurs_; p++){
            TarotSumDeclaringPlayer line_ = new TarotSumDeclaringPlayer();
            SortedHandfuls handfulsTakerLoc_ = handfulsTaker_.get(p);
            line_.setNickname(getNicknames().get(p));
            line_.setStatus(toString(GameTarotTeamsRelation.statutDe(p, game_.getPreneur(), game_.getAppele()), _res.getRes().getGeneral()));
            StringMap<Long> hands_ = new StringMap<Long>();
            for (EntryCust<Handfuls,Long> e: handfulsTakerLoc_.entryList()) {
                Handfuls h_ = e.getKey();
                hands_.addEntry(toString(h_, _res.getRes().getSpecific()), e.getValue());
            }
            line_.setHandfuls(hands_);
            int sum_ = 0;
            for (Handfuls h: getGame().getAnnoncesPoignees(p)) {
                sumPlayers_ += handfulsTakerLoc_.getVal(h);
                sum_ += handfulsTakerLoc_.getVal(h);
            }
            SortedMiseres miseresTakerLoc_ = miseresTaker_.get(p);
            StringMap<Long> mis_ = new StringMap<Long>();
            for (EntryCust<Miseres, Long> e: miseresTakerLoc_.entryList()) {
                Miseres m_ = e.getKey();
                mis_.addEntry(toString(m_, _res.getRes().getSpecific()), e.getValue());
            }
            line_.setMiseres(mis_);
            for (Miseres m: getGame().getAnnoncesMiseres(p)) {
                sumPlayers_ += miseresTakerLoc_.getVal(m);
                sum_ += miseresTakerLoc_.getVal(m);
            }
            line_.setSum(sum_);
            linesDeclaring.add(line_);
        }
        sumPlayers = sumPlayers_;
    }

    private boolean existeAnnonce() {
        int nombreJoueurs_ = getGame().getNombreDeJoueurs();
        boolean existeAnnonce_=false;
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_< nombreJoueurs_; joueur_++){
            if (!getGame().getAnnoncesMiseres(joueur_).isEmpty()) {
                existeAnnonce_ = true;
            }
            if (!getGame().getAnnoncesPoignees(joueur_).isEmpty()) {
                existeAnnonce_ = true;
            }
        }
        return existeAnnonce_;
    }

    public long getDifferenceScoreTaker() {
        return differenceScoreTaker;
    }

    public long getBasePoints() {
        return basePoints;
    }

    public long getRate() {
        return rate;
    }

    public String getSmall() {
        return small;
    }

    public String getPlayerSmall() {
        return playerSmall;
    }

    public long getMultipliedTmp() {
        return multipliedTmp;
    }

    public long getSumPlayers() {
        return sumPlayers;
    }

    public CustList<TarotSumDeclaringPlayer> getLinesDeclaring() {
        return linesDeclaring;
    }

    public CustList<ScoresPlayers> getPlayersScores() {
        return playersScores;
    }

    public long getAdditionnalBonusesAttack() {
        return additionnalBonusesAttack;
    }

    public long getAdditionnalBonusesDefense() {
        return additionnalBonusesDefense;
    }

    public long getDiffAttackDefenseBonuses() {
        return diffAttackDefenseBonuses;
    }

    public CustList<RankingPlayerVariantGame> getOrderedPlayers() {
        return orderedPlayers;
    }

    public CustList<PointsPlayerVariantGame> getPointsPlayers() {
        return pointsPlayers;
    }

    public CustList<BonusesPlayers> getBonuses() {
        return bonuses;
    }

}
