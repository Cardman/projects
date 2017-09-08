package aiki.beans.fight;
import code.bean.Accessible;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.facade.comparators.ComparatorMoveTeamPosition;
import aiki.beans.facade.comparators.ComparatorStatisticInfo;
import aiki.beans.facade.fight.MultPowerMoves;
import aiki.beans.facade.fight.StatisticInfo;
import aiki.beans.facade.fight.SufferedDamageCategory;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.PokemonData;
import aiki.game.UsesOfMove;
import aiki.game.fight.ActivityOfMove;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.MoveTeamPosition;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.map.pokemon.enums.Gender;

public class FighterBean extends CommonFightBean {

    @Accessible
    private String name;

    private String keyName;

    @Accessible
    private String nickname;

    @Accessible
    private String gender;

    @Accessible
    private Rate weight;

    @Accessible
    private Rate height;

    @Accessible
    private String weightStr;

    @Accessible
    private String heightStr;

    @Accessible
    private String currentName;

    @Accessible
    private String currentGender;

    @Accessible
    private String item;

    @Accessible
    private String expItem;

    @Accessible
    private String ability;

    @Accessible
    private String currentAbility;

    @Accessible
    private NatTreeMap<String,Short> status;

    @Accessible
    private TreeMap<MoveTeamPosition,Short> statusRelat;

    @Accessible
    private StringList types;

    @Accessible
    private NatTreeMap<String,UsesOfMove> moves;

    @Accessible
    private NatTreeMap<String,UsesOfMove> currentMoves;

    @Accessible
    private CustList<StatisticInfo> statistics;

    @Accessible
    private Rate remainingHp;

    @Accessible
    private String remainingHpStr;

    @Accessible
    private String remainingHpStrPerCent;

    @Accessible
    private Rate clone;

    @Accessible
    private String cloneStr;

    @Accessible
    private StringList protectedAgainstMoveTypes;

    @Accessible
    private NatTreeMap<String,ActivityOfMove> enabledMoves;

    @Accessible
    private NatTreeMap<String,Boolean> enabledMovesForAlly;

    @Accessible
    private NatTreeMap<String,MultPowerMoves> damageRateByType;

    @Accessible
    private byte groundPlace;

    @Accessible
    private Rate wonExpSinceLastLevel;

    @Accessible
    private Rate necessaryPointsNextLevel;

    @Accessible
    private short level;

    @Accessible
    private short happiness;

    @Accessible
    private TreeMap<MoveTeamPosition,Boolean> incrUserAccuracy;

    @Accessible
    private NatTreeMap<String,Integer> nbUsesMoves;

    @Accessible
    private short nbPrepaRound;

    @Accessible
    private boolean needingToRecharge;

    @Accessible
    private TreeMap<MoveTeamPosition,AffectedMove> trackingMoves;

    @Accessible
    private TreeMap<MoveTeamPosition,ActivityOfMove> trappingMoves;

    @Accessible
    private NatTreeMap<String,SufferedDamageCategory> damageSufferedCateg;

    @Accessible
    private NatTreeMap<String,CopiedMove> copiedMoves;

    @Accessible
    private LgInt nbRepeatingSuccessfulMoves;

    @Accessible
    private TreeMap<MoveTeamPosition,String> privateMoves;

    @Accessible
    private boolean belongingToPlayer;

    /***/
    @Accessible
    private String lastUsedItem;

    /***/
    @Accessible
    private LgInt nbRounds;

    /***/
    @Accessible
    private boolean acted;

    /***/
    @Accessible
    private byte groundPlaceSubst;

    /***/
    @Accessible
    private Rate wonExp;

    /***/
    @Accessible
    private String usedBallCatching;

    /***/
    @Accessible
    private boolean disappeared;

    /***/
    @Accessible
    private String lastSufferedMove;

    /***/
    @Accessible
    private StringList lastSufferedMoveTypes;

    /***/
    @Accessible
    private String lastUsedMove;

    /***/
    @Accessible
    private String usedMoveLastRound;

    /***/
    @Accessible
    private StringList alreadyInvokedMovesRound;

    /***/
    @Accessible
    private String lastSuccessfulMove;

    /***/
    @Accessible
    private boolean usingItem;

    /***/
    @Accessible
    private boolean successfulMove;

    /***/
    @Accessible
    private boolean changed;

    /***/
    @Accessible
    private NatTreeMap<String,Boolean> enabledImmuAbilities;

    @Override
    public void beforeDisplaying() {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        DataBase data_ = dataBaseFight_.getData();
        Number noTeam_ = (Number) getForms().getVal(NO_TEAM);
        Number noFighter_ = (Number) getForms().getVal(NO_FIGHTER);
        Fighter fighter_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_.byteValue()).getMembers().getVal(noFighter_.byteValue());
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        EnumMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        EnumMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        keyName = fighter_.getName();
        name = translationsPokemon_.getVal(fighter_.getName());
        currentName = translationsPokemon_.getVal(fighter_.getCurrentName());
        belongingToPlayer = fighter_.isBelongingToPlayer();
        nickname = fighter_.getNickname();
        gender = translationsGenders_.getVal(fighter_.getGender());
        currentGender = translationsGenders_.getVal(fighter_.getCurrentGender());
        weight = fighter_.getWeight();
        height = fighter_.getHeight();
        weightStr = weight.evaluate(2);
        heightStr = height.evaluate(2);
        level = fighter_.getLevel();
        happiness = fighter_.getHappiness();
        if (fighter_.getItem().isEmpty()) {
            item = DataBase.EMPTY_STRING;
        } else {
            item = translationsItems_.getVal(fighter_.getItem());
        }
        if (fighter_.getExpItem().isEmpty()) {
            expItem = DataBase.EMPTY_STRING;
        } else {
            expItem = translationsItems_.getVal(fighter_.getExpItem());
        }
        if (fighter_.getLastUsedItem().isEmpty()) {
            lastUsedItem = DataBase.EMPTY_STRING;
        } else {
            lastUsedItem = translationsItems_.getVal(fighter_.getLastUsedItem());
        }
        nbRounds = fighter_.getNbRounds();
        acted = fighter_.isActed();
        ability = translationsAbilities_.getVal(fighter_.getAbility());
        if (fighter_.getCurrentAbility().isEmpty()) {
            currentAbility = DataBase.EMPTY_STRING;
        } else {
            currentAbility = translationsAbilities_.getVal(fighter_.getCurrentAbility());
        }
        wonExpSinceLastLevel = fighter_.getWonExpSinceLastLevel();
        necessaryPointsNextLevel = numberNecessaryPointsForGrowingLevel();
        nbPrepaRound = fighter_.getNbPrepaRound();
        needingToRecharge = fighter_.isNeedingToRecharge();
        remainingHp = fighter_.getRemainingHp();
        remainingHpStr = remainingHp.evaluate(2);
        remainingHpStrPerCent = Rate.multiply(Rate.divide(remainingHp, fighter_.pvMax()), new Rate(100)).evaluate(2);
        clone = fighter_.getClone();
        cloneStr = clone.evaluate(2);
        types = new StringList();
        for (String t: fighter_.getTypes()) {
            types.add(translationsTypes_.getVal(t));
        }
        types.sort();
        protectedAgainstMoveTypes = new StringList();
        for (String t: fighter_.getProtectedAgainstMoveTypes()) {
            protectedAgainstMoveTypes.add(translationsTypes_.getVal(t));
        }
        protectedAgainstMoveTypes.sort();
//        TreeMap<String,Boolean> enabledImmuAbilities_;
//        enabledImmuAbilities_ = new TreeMap<new>(new);
//        for (String a: fighter_.getEnabledImmuAbilities().getKeys()) {
//            enabledImmuAbilities_.put(translationsAbilities_.getVal(a), fighter_.getEnabledImmuAbilities().getVal(a));
//        }
        enabledImmuAbilities = new NatTreeMap<String,Boolean>();
        if (fighter_.getUsedBallCatching().isEmpty()) {
            usedBallCatching = DataBase.EMPTY_STRING;
        } else {
            usedBallCatching = translationsItems_.getVal(fighter_.getUsedBallCatching());
        }
        CustList<StatisticInfo> statistics_;
        statistics_ = new CustList<StatisticInfo>();
        for (Statistic s: Statistic.values()) {
            if (s == Statistic.PV_RESTANTS) {
                continue;
            }
            StatisticInfo stat_;
            stat_ = new StatisticInfo();
            stat_.setStatistic(s);
            if (s.isBoost()) {
                stat_.setStatisBoost(fighter_.getStatisBoost().getVal(s));
            }
            if (s.isWithBaseStatistic()) {
                stat_.setStatisBase(fighter_.getStatisBase().getVal(s));
                stat_.setEv(fighter_.getEv().getVal(s));
                stat_.setIv(fighter_.getIv().getVal(s));
            }
            stat_.setDisplayStatistic(translationsStatistics_.getVal(s));
            statistics_.add(stat_);
        }
        statistics_.sortElts(new ComparatorStatisticInfo());
        statistics = statistics_;
        NatTreeMap<String,ActivityOfMove> enabledMoves_;
        enabledMoves_ = new NatTreeMap<String,ActivityOfMove>();
        for (String m: fighter_.getEnabledMoves().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), fighter_.getEnabledMoves().getVal(m));
        }
        for (String m: fighter_.getEnabledMovesConstChoices().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), fighter_.getEnabledMovesConstChoices().getVal(m));
        }
        for (String m: fighter_.getEnabledCounteringMoves().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), fighter_.getEnabledCounteringMoves().getVal(m));
        }
        for (String m: fighter_.getEnabledChangingTypesMoves().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), fighter_.getEnabledChangingTypesMoves().getVal(m));
        }
        for (String m: fighter_.getEnabledMovesEndRound().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), fighter_.getEnabledMovesEndRound().getVal(m));
        }
        for (String m: fighter_.getEnabledMovesProt().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), fighter_.getEnabledMovesProt().getVal(m));
        }
        for (String m: fighter_.getEnabledMovesUnprot().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), fighter_.getEnabledMovesUnprot().getVal(m));
        }
        enabledMoves = enabledMoves_;
        NatTreeMap<String,UsesOfMove> moves_;
        moves_ = new NatTreeMap<String,UsesOfMove>();
        for (String m: fighter_.getMovesSet()) {
            moves_.put(translationsMoves_.getVal(m), fighter_.getMove(m));
        }
        moves = moves_;
        NatTreeMap<String,UsesOfMove> currentMoves_;
        currentMoves_ = new NatTreeMap<String,UsesOfMove>();
        for (String m: fighter_.getCurrentMovesSet()) {
            currentMoves_.put(translationsMoves_.getVal(m), fighter_.getCurrentMove(m));
        }
        currentMoves = currentMoves_;
        NatTreeMap<String,Integer> nbUsesMoves_;
        nbUsesMoves_ = new NatTreeMap<String,Integer>();
        for (String m: fighter_.getNbUsesMoves().getKeys()) {
            nbUsesMoves_.put(translationsMoves_.getVal(m), fighter_.getNbUsesMoves().getVal(m));
        }
        nbUsesMoves = nbUsesMoves_;
        NatTreeMap<String,Short> status_;
        status_ = new NatTreeMap<String,Short>();
        for (String m: fighter_.getStatusSet()) {
            status_.put(translationsStatus_.getVal(m), fighter_.getStatusNbRound(m));
        }
        status = status_;
        TreeMap<MoveTeamPosition,Short> statusRelat_;
//        statusRelat_ = new TreeMap<new>(new NaturalComparator<MoveTeamPosition>(){
//            @Override
//            public int compare(MoveTeamPosition _o1, MoveTeamPosition _o2) {
//                int res_ = _o1.getMove().compareTo(_o2.getMove());
//                if (res_ != 0) {
//                    return res_;
//                }
//                res_ = Integer.compare(_o1.getTeamPosition().getTeam(), _o2.getTeamPosition().getTeam());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return Integer.compare(_o1.getTeamPosition().getPosition(), _o2.getTeamPosition().getPosition());
//            }
//        });
        statusRelat_ = new TreeMap<MoveTeamPosition, Short>(new ComparatorMoveTeamPosition());
        for (MoveTeamPosition m: fighter_.getStatusRelatSet()) {
            String move_ = translationsStatus_.getVal(m.getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, m.getTeamPosition());
            statusRelat_.put(m_, fighter_.getStatusRelatNbRound(m));
        }
        statusRelat = statusRelat_;
        nbRepeatingSuccessfulMoves = fighter_.getNbRepeatingSuccessfulMoves();
        NatTreeMap<String,CopiedMove> copiedMoves_;
        copiedMoves_ = new NatTreeMap<String,CopiedMove>();
        for (String c: fighter_.getCopiedMoves().getKeys()) {
            CopiedMove copied_;
            copied_ = new CopiedMove();
            String move_ = fighter_.getCopiedMoves().getVal(c).getMove();
            if (!move_.isEmpty()) {
                move_ = translationsMoves_.getVal(fighter_.getCopiedMoves().getVal(c).getMove());
            }
            copied_.setMove(move_);
            copied_.setPp(fighter_.getCopiedMoves().getVal(c).getPp());
            copiedMoves_.put(translationsMoves_.getVal(c), copied_);
        }
        copiedMoves = copiedMoves_;
        groundPlace = fighter_.getGroundPlace();
        groundPlaceSubst = fighter_.getGroundPlaceSubst();
        wonExp = fighter_.getWonExp();
        disappeared = fighter_.isDisappeared();
        if (fighter_.getLastSufferedMove().isEmpty()) {
            lastSufferedMove = DataBase.EMPTY_STRING;
        } else {
            lastSufferedMove = translationsMoves_.getVal(fighter_.getLastSufferedMove());
        }
        if (fighter_.getLastUsedMove().isEmpty()) {
            lastUsedMove = DataBase.EMPTY_STRING;
        } else {
            lastUsedMove = translationsMoves_.getVal(fighter_.getLastUsedMove());
        }
        if (fighter_.getLastSuccessfulMove().isEmpty()) {
            lastSuccessfulMove = DataBase.EMPTY_STRING;
        } else {
            lastSuccessfulMove = translationsMoves_.getVal(fighter_.getLastSuccessfulMove());
        }
        if (fighter_.getUsedMoveLastRound().isEmpty()) {
            usedMoveLastRound = DataBase.EMPTY_STRING;
        } else {
            usedMoveLastRound = translationsMoves_.getVal(fighter_.getUsedMoveLastRound());
        }
        successfulMove = fighter_.isSuccessfulMove();
        StringList lastSufferedMoveTypes_ = new StringList();
        for (String t: fighter_.getLastSufferedMoveTypes()) {
            lastSufferedMoveTypes_.add(translationsTypes_.getVal(t));
        }
        lastSufferedMoveTypes_.sort();
        lastSufferedMoveTypes = lastSufferedMoveTypes_;
        StringList alreadyInvokedMovesRound_ = new StringList();
        for (String t: fighter_.getAlreadyInvokedMovesRound()) {
            alreadyInvokedMovesRound_.add(translationsMoves_.getVal(t));
        }
        alreadyInvokedMovesRound_.sort();
        alreadyInvokedMovesRound = alreadyInvokedMovesRound_;
        usingItem = fighter_.isUsingItem();
        changed = fighter_.isChanged();
        NatTreeMap<String,MultPowerMoves> damageRateByType_;
        damageRateByType_ = new NatTreeMap<String,MultPowerMoves>();
        for (String c: data_.getTypes()) {
            MultPowerMoves mult_ = new MultPowerMoves();
            mult_.setMultInflicted(fighter_.getDamageRateInflictedByType().getVal(c));
            mult_.setMultSuffering(fighter_.getDamageRateSufferedByType().getVal(c));
            damageRateByType_.put(translationsTypes_.getVal(c), mult_);
        }
        damageRateByType = damageRateByType_;
        NatTreeMap<String,SufferedDamageCategory> damageSufferedCateg_;
        damageSufferedCateg_ = new NatTreeMap<String,SufferedDamageCategory>();
        for (String c: data_.getCategories()) {
            SufferedDamageCategory suff_;
            suff_ = new SufferedDamageCategory();
            suff_.setRound(fighter_.getDamageSufferedCategRound().getVal(c));
            suff_.setUsing(fighter_.getDamageSufferedCateg().getVal(c));
            damageSufferedCateg_.put(translationsCategories_.getVal(c), suff_);
        }
        damageSufferedCateg = damageSufferedCateg_;
        NatTreeMap<String,Boolean> enabledMovesForAlly_;
        enabledMovesForAlly_ = new NatTreeMap<String,Boolean>();
        for (String c: fighter_.getEnabledMovesForAlly().getKeys()) {
            enabledMovesForAlly_.put(translationsMoves_.getVal(c), fighter_.getEnabledMovesForAlly().getVal(c));
        }
        enabledMovesForAlly = enabledMovesForAlly_;
        TreeMap<MoveTeamPosition,String> privateMoves_;
//        privateMoves_ = new TreeMap<new>(new NaturalComparator<MoveTeamPosition>(){
//            @Override
//            public int compare(MoveTeamPosition _o1, MoveTeamPosition _o2) {
//                int res_ = _o1.getMove().compareTo(_o2.getMove());
//                if (res_ != 0) {
//                    return res_;
//                }
//                res_ = Integer.compare(_o1.getTeamPosition().getTeam(), _o2.getTeamPosition().getTeam());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return Integer.compare(_o1.getTeamPosition().getPosition(), _o2.getTeamPosition().getPosition());
//            }
//        });
        privateMoves_ = new TreeMap<MoveTeamPosition, String>(new ComparatorMoveTeamPosition());
        for (MoveTeamPosition m: fighter_.getPrivateMoves().getKeys()) {
            StringList movesPr_ = new StringList();
            for (String move_: fighter_.getPrivateMoves().getVal(m)) {
                movesPr_.add(translationsMoves_.getVal(move_));
            }
            movesPr_.sort();
            String move_ = translationsMoves_.getVal(m.getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, m.getTeamPosition());
            privateMoves_.put(m_, movesPr_.join(MOVES_SEPARATOR));
        }
        privateMoves = privateMoves_;
        TreeMap<MoveTeamPosition,ActivityOfMove> trappingMoves_;
//        trappingMoves_ = new TreeMap<new>(new NaturalComparator<MoveTeamPosition>(){
//            @Override
//            public int compare(MoveTeamPosition _o1, MoveTeamPosition _o2) {
//                int res_ = _o1.getMove().compareTo(_o2.getMove());
//                if (res_ != 0) {
//                    return res_;
//                }
//                res_ = Integer.compare(_o1.getTeamPosition().getTeam(), _o2.getTeamPosition().getTeam());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return Integer.compare(_o1.getTeamPosition().getPosition(), _o2.getTeamPosition().getPosition());
//            }
//        });
        trappingMoves_ = new TreeMap<MoveTeamPosition, ActivityOfMove>(new ComparatorMoveTeamPosition());
        for (MoveTeamPosition m: fighter_.getTrappingMoves().getKeys()) {
            ActivityOfMove activity_;
            activity_ = new ActivityOfMove(fighter_.getTrappingMoves().getVal(m));
            String move_ = translationsMoves_.getVal(m.getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, m.getTeamPosition());
            trappingMoves_.put(m_, activity_);
        }
        trappingMoves = trappingMoves_;
        TreeMap<MoveTeamPosition,AffectedMove> trackingMoves_;
//        trackingMoves_ = new TreeMap<new>(new NaturalComparator<MoveTeamPosition>(){
//            @Override
//            public int compare(MoveTeamPosition _o1, MoveTeamPosition _o2) {
//                int res_ = _o1.getMove().compareTo(_o2.getMove());
//                if (res_ != 0) {
//                    return res_;
//                }
//                res_ = Integer.compare(_o1.getTeamPosition().getTeam(), _o2.getTeamPosition().getTeam());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return Integer.compare(_o1.getTeamPosition().getPosition(), _o2.getTeamPosition().getPosition());
//            }
//        });
        trackingMoves_ = new TreeMap<MoveTeamPosition, AffectedMove>(new ComparatorMoveTeamPosition());
        for (MoveTeamPosition m: fighter_.getTrackingMoves().getKeys()) {
            ActivityOfMove activity_;
            activity_ = new ActivityOfMove(fighter_.getTrackingMoves().getVal(m).getActivity());
            String move_ = translationsMoves_.getVal(m.getMove());
            String affectedMove_ = fighter_.getTrackingMoves().getVal(m).getMove();
            String affectedMoveTr_ = DataBase.EMPTY_STRING;
            if (!affectedMove_.isEmpty()) {
                affectedMoveTr_ = translationsMoves_.getVal(affectedMove_);
            }
            MoveTeamPosition m_ = new MoveTeamPosition(move_, m.getTeamPosition());
            trackingMoves_.put(m_, new AffectedMove(affectedMoveTr_, activity_));
        }
        trackingMoves = trackingMoves_;
        TreeMap<MoveTeamPosition,Boolean> incrUserAccuracy_;
//        incrUserAccuracy_ = new TreeMap<new>(new NaturalComparator<MoveTeamPosition>(){
//            @Override
//            public int compare(MoveTeamPosition _o1, MoveTeamPosition _o2) {
//                int res_ = _o1.getMove().compareTo(_o2.getMove());
//                if (res_ != 0) {
//                    return res_;
//                }
//                res_ = Integer.compare(_o1.getTeamPosition().getTeam(), _o2.getTeamPosition().getTeam());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return Integer.compare(_o1.getTeamPosition().getPosition(), _o2.getTeamPosition().getPosition());
//            }
//        });
        incrUserAccuracy_ = new TreeMap<MoveTeamPosition, Boolean>(new ComparatorMoveTeamPosition());
        for (MoveTeamPosition m: fighter_.getIncrUserAccuracy().getKeys()) {
            String move_ = translationsMoves_.getVal(m.getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, m.getTeamPosition());
            incrUserAccuracy_.put(m_, fighter_.getIncrUserAccuracy().getVal(m));
        }
        incrUserAccuracy = incrUserAccuracy_;
    }

    Rate numberNecessaryPointsForGrowingLevel(){
        FacadeGame facadeGame_ = (FacadeGame) getDataBase();
        DataBase data_ = facadeGame_.getData();
        PokemonData fPk_=data_.getPokemon(keyName);
        String expLitt_=data_.getExpGrowth().getVal(fPk_.getExpEvo());
        StringMap<String> vars_ = new StringMap<String>();
        vars_.put(DataBase.VAR_PREFIX+Fighter.NIVEAU,Integer.toString(level + 1));
        Rate next_;
        next_ = data_.evaluateNumericable(expLitt_, vars_, Rate.one());
        Rate current_;
        vars_.put(DataBase.VAR_PREFIX+Fighter.NIVEAU,Integer.toString(level));
        current_ = data_.evaluateNumericable(expLitt_, vars_, Rate.one());
        vars_.clear();
        Rate diff_ = data_.evaluatePositiveExp(Rate.minus(next_, current_).toString(), vars_, Rate.one());
        diff_.removeNb(wonExpSinceLastLevel);
        return diff_;
    }

    @Accessible
    private boolean isBack() {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        Number noTeam_ = (Number) getForms().getVal(NO_TEAM);
        Number noFighter_ = (Number) getForms().getVal(NO_FIGHTER);
        Fighter fighter_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_.byteValue()).getMembers().getVal(noFighter_.byteValue());
        return fighter_.estArriere();
    }

    @Accessible
    private boolean isBackSubst() {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        Number noTeam_ = (Number) getForms().getVal(NO_TEAM);
        Number noFighter_ = (Number) getForms().getVal(NO_FIGHTER);
        Fighter fighter_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_.byteValue()).getMembers().getVal(noFighter_.byteValue());
        return fighter_.getGroundPlaceSubst() == Fighter.BACK;
    }

    @Accessible
    private boolean isFoeStatusRelatTeam(Long _index) {
        MoveTeamPosition mt_ = statusRelat.getKey(_index.intValue());
        return mt_.getTeamPosition().getTeam() == Fight.FOE;
    }

    @Accessible
    private String getStatusRelatTeam(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        MoveTeamPosition mt_ = statusRelat.getKey(_index.intValue());
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }

    @Accessible
    private boolean isEnabled(Long _index) {
        return statusRelat.getValue(_index.intValue()) > 0;
    }

    @Accessible
    private boolean isFoePrivateMovesTeam(Long _index) {
        MoveTeamPosition mt_ = privateMoves.getKey(_index.intValue());
        return mt_.getTeamPosition().getTeam() == Fight.FOE;
    }

    @Accessible
    private String getIncrPrivateMovesTeam(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        MoveTeamPosition mt_ = privateMoves.getKey(_index.intValue());
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }

    @Accessible
    private boolean isFoeTrappingMovesTeam(Long _index) {
        MoveTeamPosition mt_ = trappingMoves.getKey(_index.intValue());
        return mt_.getTeamPosition().getTeam() == Fight.FOE;
    }

    @Accessible
    private String getIncrTrappingMovesTeam(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        MoveTeamPosition mt_ = trappingMoves.getKey(_index.intValue());
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }

    @Accessible
    private boolean isFoeTrackingMovesTeam(Long _index) {
        MoveTeamPosition mt_ = trackingMoves.getKey(_index.intValue());
        return mt_.getTeamPosition().getTeam() == Fight.FOE;
    }

    @Accessible
    private String getIncrTrackingMovesTeam(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        MoveTeamPosition mt_ = trackingMoves.getKey(_index.intValue());
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }

    @Accessible
    private boolean isFoeIncrUserAccuracyTeam(Long _index) {
        MoveTeamPosition mt_ = incrUserAccuracy.getKey(_index.intValue());
        return mt_.getTeamPosition().getTeam() == Fight.FOE;
    }

    @Accessible
    private String getIncrUserAccuracyTeam(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        MoveTeamPosition mt_ = incrUserAccuracy.getKey(_index.intValue());
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }
}
