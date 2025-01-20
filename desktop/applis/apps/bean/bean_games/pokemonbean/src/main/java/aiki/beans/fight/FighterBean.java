package aiki.beans.fight;

import aiki.beans.facade.comparators.ComparatorStatisticInfo;
import aiki.beans.facade.fight.MultPowerMoves;
import aiki.beans.facade.fight.StatisticInfo;
import aiki.beans.facade.fight.SufferedDamageCategory;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.game.UsesOfMove;
import aiki.game.fight.*;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.map.pokemon.enums.Gender;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public class FighterBean extends CommonFightBean {
    private String name;

    private String keyName;
    private String nickname;
    private String gender;
    private Rate weight;
    private Rate height;
//    private String weightStr;
//    private String heightStr;
    private String currentName;
    private String currentGender;
    private String item;
    private String expItem;
    private String ability;
    private String currentAbility;
    private NatStringTreeMap<Long> status;
    private DictionaryComparator<MoveTeamPosition,Long> statusRelat;
    private StringList types;
    private NatStringTreeMap<UsesOfMove> moves;
    private NatStringTreeMap<UsesOfMove> currentMoves;
    private CustList<StatisticInfo> statistics;
    private Rate maxHp;
    private Rate remainingHp;
//    private String remainingHpStr;
//    private String remainingHpStrPerCent;
    private Rate clone;
//    private String cloneStr;
    private StringList protectedAgainstMoveTypes;
    private NatStringTreeMap<ActivityOfMove> enabledMoves;
    private NatStringTreeMap<BoolVal> enabledMovesForAlly;
    private NatStringTreeMap<MultPowerMoves> damageRateByType;
    private int groundPlace;
    private Rate wonExpSinceLastLevel;
    private Rate necessaryPointsNextLevel;
    private long level;
    private long happiness;
    private DictionaryComparator<MoveTeamPosition,BoolVal> incrUserAccuracy;
    private NatStringTreeMap<Long> nbUsesMoves;
    private long nbPrepaRound;
    private boolean needingToRecharge;
    private DictionaryComparator<MoveTeamPosition,AffectedMove> trackingMoves;
    private DictionaryComparator<MoveTeamPosition,ActivityOfMove> trappingMoves;
    private NatStringTreeMap<SufferedDamageCategory> damageSufferedCateg;
    private NatStringTreeMap<CopiedMove> copiedMoves;
    private LgInt nbRepeatingSuccessfulMoves;
    private DictionaryComparator<MoveTeamPosition,String> privateMoves;
    private boolean belongingToPlayer;
    private String lastUsedItem;
    private LgInt nbRounds;
    private boolean acted;
    private int groundPlaceSubst;
    private String usedBallCatching;
    private boolean disappeared;
    private String lastSufferedMove;
    private StringList lastSufferedMoveTypes;
    private String lastUsedMove;
    private String usedMoveLastRound;
    private StringList alreadyInvokedMovesRound;
    private String lastSuccessfulMove;
    private boolean usingItem;
    private boolean successfulMove;
    private boolean changed;

    @Override
    public void beforeDisplaying() {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        int noTeam_ = getForms().getValInt(NO_TEAM);
        int noFighter_ = getForms().getValInt(NO_FIGHTER);
        Fighter fighter_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_).getMembers().getVal(noFighter_);
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        StringMap<String> translationsItems_;
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translationsAbilities_;
        translationsAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        AbsMap<Gender,String> translationsGenders_;
        translationsGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        keyName = fighter_.getName();
        name = translationsPokemon_.getVal(fighter_.getName());
        currentName = translationsPokemon_.getVal(fighter_.getCurrentName());
        belongingToPlayer = fighter_.isBelongingToPlayer();
        nickname = fighter_.getNickname();
        gender = translationsGenders_.getVal(fighter_.getGender());
        currentGender = translationsGenders_.getVal(fighter_.getCurrentGender());
        weight = fighter_.getWeight();
        height = fighter_.getHeight();
//        weightStr = weight.evaluate(2);
//        heightStr = height.evaluate(2);
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
        maxHp = fighter_.pvMax();
//        remainingHpStr = remainingHp.evaluate(2);
//        remainingHpStrPerCent = Rate.multiply(Rate.divide(remainingHp, maxHp), new Rate(100)).evaluate(2);
        clone = fighter_.getClone();
//        cloneStr = clone.evaluate(2);
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
        if (fighter_.getUsedBallCatching().isEmpty()) {
            usedBallCatching = DataBase.EMPTY_STRING;
        } else {
            usedBallCatching = translationsItems_.getVal(fighter_.getUsedBallCatching());
        }
        statistics = initFighterStats(fighter_);
        fighterMoves(fighter_);
        status = status(fighter_);
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
        statusRelat = statusRelat(fighter_);
        nbRepeatingSuccessfulMoves = fighter_.getNbRepeatingSuccessfulMoves();
        copiedMoves = copiedMoves(fighter_);
        groundPlace = fighter_.getGroundPlace();
        groundPlaceSubst = fighter_.getGroundPlaceSubst();
        disappeared = fighter_.isDisappeared();
        pastMove(fighter_);
        successfulMove = fighter_.isSuccessfulMove();
        StringList lastSufferedMoveTypes_ = new StringList();
        for (String t: fighter_.getLastSufferedMoveTypes()) {
            lastSufferedMoveTypes_.add(translationsTypes_.getVal(t));
        }
        lastSufferedMoveTypes_.sort();
        lastSufferedMoveTypes = lastSufferedMoveTypes_;
        alreadyInvokedMovesRound = alreadyInvokedMovesRound(fighter_);
        usingItem = fighter_.isUsingItem();
        changed = fighter_.isChanged();
        NatStringTreeMap<MultPowerMoves> damageRateByType_;
        damageRateByType_ = new NatStringTreeMap<MultPowerMoves>();
        for (String c: data_.getTypes()) {
            MultPowerMoves mult_ = new MultPowerMoves();
            mult_.setMultInflicted(fighter_.getDamageRateInflictedByType().getVal(c));
            mult_.setMultSuffering(fighter_.getDamageRateSufferedByType().getVal(c));
            damageRateByType_.put(translationsTypes_.getVal(c), mult_);
        }
        damageRateByType = damageRateByType_;
        damageSufferedCateg = damageSufferedCateg(fighter_);
        enabledMovesForAlly = enabledMovesForAll(fighter_);
        relMoves(fighter_);
    }

    private DictionaryComparator<MoveTeamPosition, Long> statusRelat(Fighter _fighter) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        DictionaryComparator<MoveTeamPosition, Long> statusRelat_ = DictionaryComparatorUtil.buildMoveTeamPositionShort();
        for (MoveTeamPosition m: _fighter.getStatusRelatSet()) {
            String move_ = translationsStatus_.getVal(m.getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, m.getTeamPosition());
            statusRelat_.put(m_, _fighter.getStatusRelatNbRound(m));
        }
        return statusRelat_;
    }

    private NatStringTreeMap<Long> status(Fighter _fighter) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsStatus_;
        translationsStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        NatStringTreeMap<Long> status_;
        status_ = new NatStringTreeMap<Long>();
        for (String m: _fighter.getStatusSet()) {
            status_.put(translationsStatus_.getVal(m), _fighter.getStatusNbRound(m));
        }
        return status_;
    }

    private NatStringTreeMap<SufferedDamageCategory> damageSufferedCateg(Fighter _fighter) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        NatStringTreeMap<SufferedDamageCategory> damageSufferedCateg_;
        damageSufferedCateg_ = new NatStringTreeMap<SufferedDamageCategory>();
        for (String c: data_.getCategories()) {
            SufferedDamageCategory suff_;
            suff_ = new SufferedDamageCategory();
            suff_.setRound(_fighter.getDamageSufferedCategRound().getVal(c));
            suff_.setUsing(_fighter.getDamageSufferedCateg().getVal(c));
            damageSufferedCateg_.put(translationsCategories_.getVal(c), suff_);
        }
        return damageSufferedCateg_;
    }

    private NatStringTreeMap<CopiedMove> copiedMoves(Fighter _fighter) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        NatStringTreeMap<CopiedMove> copiedMoves_;
        copiedMoves_ = new NatStringTreeMap<CopiedMove>();
        for (String c: _fighter.getCopiedMoves().getKeys()) {
            CopiedMove copied_;
            copied_ = new CopiedMove();
            String move_ = _fighter.getCopiedMoves().getVal(c).getMove();
            if (!move_.isEmpty()) {
                move_ = translationsMoves_.getVal(_fighter.getCopiedMoves().getVal(c).getMove());
            }
            copied_.setMove(move_);
            copied_.setPp(_fighter.getCopiedMoves().getVal(c).getPp());
            copiedMoves_.put(translationsMoves_.getVal(c), copied_);
        }
        return copiedMoves_;
    }

    private NatStringTreeMap<BoolVal> enabledMovesForAll(Fighter _fighter) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        NatStringTreeMap<BoolVal> enabledMovesForAlly_;
        enabledMovesForAlly_ = new NatStringTreeMap<BoolVal>();
        for (String c: _fighter.getEnabledMovesForAlly().getKeys()) {
            enabledMovesForAlly_.put(translationsMoves_.getVal(c), _fighter.getEnabledMovesForAlly().getVal(c));
        }
        return enabledMovesForAlly_;
    }

    private StringList alreadyInvokedMovesRound(Fighter _fighter) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList alreadyInvokedMovesRound_ = new StringList();
        for (String t: _fighter.getAlreadyInvokedMovesRound()) {
            alreadyInvokedMovesRound_.add(translationsMoves_.getVal(t));
        }
        alreadyInvokedMovesRound_.sort();
        return alreadyInvokedMovesRound_;
    }

    private void fighterMoves(Fighter _fighter) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        enabledMoves = initEnabledMoves(_fighter);
        NatStringTreeMap<UsesOfMove> moves_;
        moves_ = new NatStringTreeMap<UsesOfMove>();
        for (String m: _fighter.getMovesSet()) {
            moves_.put(translationsMoves_.getVal(m), _fighter.getMove(m));
        }
        moves = moves_;
        NatStringTreeMap<UsesOfMove> currentMoves_;
        currentMoves_ = new NatStringTreeMap<UsesOfMove>();
        for (String m: _fighter.getCurrentMovesSet()) {
            currentMoves_.put(translationsMoves_.getVal(m), _fighter.getCurrentMove(m));
        }
        currentMoves = currentMoves_;
        NatStringTreeMap<Long> nbUsesMoves_;
        nbUsesMoves_ = new NatStringTreeMap<Long>();
        for (String m: _fighter.getNbUsesMoves().getKeys()) {
            nbUsesMoves_.put(translationsMoves_.getVal(m), _fighter.getNbUsesMoves().getVal(m));
        }
        nbUsesMoves = nbUsesMoves_;
    }

    private void relMoves(Fighter _fighter) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        DictionaryComparator<MoveTeamPosition,String> privateMoves_;
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
        privateMoves_ = DictionaryComparatorUtil.buildMoveTeamPositionString();
        for (MoveTeamPosition m: _fighter.getPrivateMoves().getKeys()) {
            StringList movesPr_ = new StringList();
            for (String move_: _fighter.getPrivateMoves().getVal(m)) {
                movesPr_.add(translationsMoves_.getVal(move_));
            }
            movesPr_.sort();
            String move_ = translationsMoves_.getVal(m.getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, m.getTeamPosition());
            privateMoves_.put(m_, StringUtil.join(movesPr_, MOVES_SEPARATOR));
        }
        privateMoves = privateMoves_;
        DictionaryComparator<MoveTeamPosition,ActivityOfMove> trappingMoves_;
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
        trappingMoves_ = DictionaryComparatorUtil.buildMoveTeamPositionActivityOfMove();
        for (MoveTeamPosition m: _fighter.getTrappingMoves().getKeys()) {
            ActivityOfMove activity_;
            activity_ = new ActivityOfMove(_fighter.getTrappingMoves().getVal(m));
            String move_ = translationsMoves_.getVal(m.getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, m.getTeamPosition());
            trappingMoves_.put(m_, activity_);
        }
        trappingMoves = trappingMoves_;
        DictionaryComparator<MoveTeamPosition,AffectedMove> trackingMoves_;
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
        trackingMoves_ =DictionaryComparatorUtil.buildMoveTeamPositionAffectedMove();
        for (MoveTeamPosition m: _fighter.getTrackingMoves().getKeys()) {
            ActivityOfMove activity_;
            activity_ = new ActivityOfMove(_fighter.getTrackingMoves().getVal(m).getActivity());
            String move_ = translationsMoves_.getVal(m.getMove());
            String affectedMove_ = _fighter.getTrackingMoves().getVal(m).getMove();
            String affectedMoveTr_ = DataBase.EMPTY_STRING;
            if (!affectedMove_.isEmpty()) {
                affectedMoveTr_ = translationsMoves_.getVal(affectedMove_);
            }
            MoveTeamPosition m_ = new MoveTeamPosition(move_, m.getTeamPosition());
            trackingMoves_.put(m_, new AffectedMove(affectedMoveTr_, activity_));
        }
        trackingMoves = trackingMoves_;
        DictionaryComparator<MoveTeamPosition,BoolVal> incrUserAccuracy_;
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
        incrUserAccuracy_ = DictionaryComparatorUtil.buildMoveTeamPositionBoolVal();
        for (MoveTeamPosition m: _fighter.getIncrUserAccuracy().getKeys()) {
            String move_ = translationsMoves_.getVal(m.getMove());
            MoveTeamPosition m_ = new MoveTeamPosition(move_, m.getTeamPosition());
            incrUserAccuracy_.put(m_, _fighter.getIncrUserAccuracy().getVal(m));
        }
        incrUserAccuracy = incrUserAccuracy_;
    }

    private void pastMove(Fighter _fighter) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        if (_fighter.getLastSufferedMove().isEmpty()) {
            lastSufferedMove = DataBase.EMPTY_STRING;
        } else {
            lastSufferedMove = translationsMoves_.getVal(_fighter.getLastSufferedMove());
        }
        if (_fighter.getLastUsedMove().isEmpty()) {
            lastUsedMove = DataBase.EMPTY_STRING;
        } else {
            lastUsedMove = translationsMoves_.getVal(_fighter.getLastUsedMove());
        }
        if (_fighter.getLastSuccessfulMove().isEmpty()) {
            lastSuccessfulMove = DataBase.EMPTY_STRING;
        } else {
            lastSuccessfulMove = translationsMoves_.getVal(_fighter.getLastSuccessfulMove());
        }
        if (_fighter.getUsedMoveLastRound().isEmpty()) {
            usedMoveLastRound = DataBase.EMPTY_STRING;
        } else {
            usedMoveLastRound = translationsMoves_.getVal(_fighter.getUsedMoveLastRound());
        }
    }

    private NatStringTreeMap<ActivityOfMove> initEnabledMoves(Fighter _fighter) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        NatStringTreeMap<ActivityOfMove> enabledMoves_;
        enabledMoves_ = new NatStringTreeMap<ActivityOfMove>();
        for (String m: _fighter.getEnabledMoves().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), _fighter.getEnabledMoves().getVal(m));
        }
        for (String m: _fighter.getEnabledMovesConstChoices().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), _fighter.getEnabledMovesConstChoices().getVal(m));
        }
        for (String m: _fighter.getEnabledCounteringMoves().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), _fighter.getEnabledCounteringMoves().getVal(m));
        }
        for (String m: _fighter.getEnabledChangingTypesMoves().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), _fighter.getEnabledChangingTypesMoves().getVal(m));
        }
        for (String m: _fighter.getEnabledMovesEndRound().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), _fighter.getEnabledMovesEndRound().getVal(m));
        }
        for (String m: _fighter.getEnabledMovesProt().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), _fighter.getEnabledMovesProt().getVal(m));
        }
        for (String m: _fighter.getEnabledMovesUnprot().getKeys()) {
            enabledMoves_.put(translationsMoves_.getVal(m), _fighter.getEnabledMovesUnprot().getVal(m));
        }
        return enabledMoves_;
    }

    private CustList<StatisticInfo> initFighterStats(Fighter _fighter) {
        FacadeGame dataBaseFight_ = facade();
        DataBase data_ = dataBaseFight_.getData();
        AbsMap<Statistic,String> translationsStatistics_;
        translationsStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        CustList<StatisticInfo> statistics_;
        statistics_ = new CustList<StatisticInfo>();
        for (Statistic s: Statistic.all()) {
            if (s == Statistic.PV_RESTANTS) {
                continue;
            }
            StatisticInfo stat_;
            stat_ = new StatisticInfo();
            stat_.setStatistic(s);
            if (StatisticInfo.isBoost(s)) {
                stat_.setStatisBoost(_fighter.getStatisBoost().getVal(s));
            }
            if (StatisticInfo.isBase(s)) {
                stat_.setStatisBase(_fighter.getStatisBase().getVal(s));
                stat_.setEv(_fighter.getEv().getVal(s));
                stat_.setIv(_fighter.getIv().getVal(s));
            }
            stat_.setDisplayStatistic(translationsStatistics_.getVal(s));
            statistics_.add(stat_);
        }
        statistics_.sortElts(new ComparatorStatisticInfo());
        return statistics_;
    }

    Rate numberNecessaryPointsForGrowingLevel(){
        FacadeGame facadeGame_ = facade();
        DataBase data_ = facadeGame_.getData();
        Rate diff_ = FightFacade.numberNecessaryPointsForGrowingLevel(keyName,level+1L,data_);
        diff_.removeNb(wonExpSinceLastLevel);
        return diff_;
    }
    public boolean isBack() {
        FacadeGame dataBaseFight_ = facade();
        int noTeam_ = getForms().getValInt(NO_TEAM);
        int noFighter_ = getForms().getValInt(NO_FIGHTER);
        Fighter fighter_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_).getMembers().getVal(noFighter_);
        return fighter_.estArriere();
    }
    public boolean isBackSubst() {
        FacadeGame dataBaseFight_ = facade();
        int noTeam_ = getForms().getValInt(NO_TEAM);
        int noFighter_ = getForms().getValInt(NO_FIGHTER);
        Fighter fighter_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_).getMembers().getVal(noFighter_);
        return fighter_.getGroundPlaceSubst() == Fighter.BACK;
    }
    public boolean isFoeStatusRelatTeam(int _index) {
        MoveTeamPosition mt_ = statusRelat.getKey(_index);
        return mt_.getTeamPosition().getTeam() == Fight.CST_FOE;
    }
    public String getStatusRelatTeam(int _index) {
        FacadeGame dataBaseFight_ = facade();
        MoveTeamPosition mt_ = statusRelat.getKey(_index);
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }
    public boolean isEnabled(int _index) {
        return statusRelat.getValue(_index) > 0;
    }
    public boolean isFoePrivateMovesTeam(int _index) {
        MoveTeamPosition mt_ = privateMoves.getKey(_index);
        return mt_.getTeamPosition().getTeam() == Fight.CST_FOE;
    }
    public String getIncrPrivateMovesTeam(int _index) {
        FacadeGame dataBaseFight_ = facade();
        MoveTeamPosition mt_ = privateMoves.getKey(_index);
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }
    public boolean isFoeTrappingMovesTeam(int _index) {
        MoveTeamPosition mt_ = trappingMoves.getKey(_index);
        return mt_.getTeamPosition().getTeam() == Fight.CST_FOE;
    }
    public String getIncrTrappingMovesTeam(int _index) {
        FacadeGame dataBaseFight_ = facade();
        MoveTeamPosition mt_ = trappingMoves.getKey(_index);
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }
    public boolean isFoeTrackingMovesTeam(int _index) {
        MoveTeamPosition mt_ = trackingMoves.getKey(_index);
        return mt_.getTeamPosition().getTeam() == Fight.CST_FOE;
    }
    public String getIncrTrackingMovesTeam(int _index) {
        FacadeGame dataBaseFight_ = facade();
        MoveTeamPosition mt_ = trackingMoves.getKey(_index);
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }
    public boolean isFoeIncrUserAccuracyTeam(int _index) {
        MoveTeamPosition mt_ = incrUserAccuracy.getKey(_index);
        return mt_.getTeamPosition().getTeam() == Fight.CST_FOE;
    }
    public String getIncrUserAccuracyTeam(int _index) {
        FacadeGame dataBaseFight_ = facade();
        MoveTeamPosition mt_ = incrUserAccuracy.getKey(_index);
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }

    public String getName() {
        return name;
    }

    public boolean getChanged() {
        return changed;
    }

    public String getCurrentName() {
        return currentName;
    }

    public String getCurrentGender() {
        return currentGender;
    }

    public String getUsedBallCatching() {
        return usedBallCatching;
    }

    public boolean getBelongingToPlayer() {
        return belongingToPlayer;
    }

    public int getGroundPlaceSubst() {
        return groundPlaceSubst;
    }

    public int getGroundPlace() {
        return groundPlace;
    }

    public String getNickname() {
        return nickname;
    }

    public long getHappiness() {
        return happiness;
    }

    public long getLevel() {
        return level;
    }

    public Rate getWonExpSinceLastLevel() {
        return wonExpSinceLastLevel;
    }

    public Rate getNecessaryPointsNextLevel() {
        return necessaryPointsNextLevel;
    }

    public String getGender() {
        return gender;
    }

    public Rate getWeight() {
        return weight;
    }

    public String getWeightStr() {
        return weight.evaluate(2);
    }

    public Rate getHeight() {
        return height;
    }

    public String getHeightStr() {
        return height.evaluate(2);
    }

    public Rate getRemainingHp() {
        return remainingHp;
    }

    public Rate getMaxHp() {
        return maxHp;
    }

    public String getRemainingHpStr() {
        return remainingHp.evaluate(2);
    }

    public String getRemainingHpStrPerCent() {
        return Rate.multiply(Rate.divide(remainingHp, maxHp), new Rate(100)).evaluate(2);
    }

    public Rate getClone() {
        return clone;
    }

    public String getCloneStr() {
        return clone.evaluate(2);
    }

    public String getAbility() {
        return ability;
    }

    public String getCurrentAbility() {
        return currentAbility;
    }

    public boolean getActed() {
        return acted;
    }

    public String getLastUsedItem() {
        return lastUsedItem;
    }

    public String getItem() {
        return item;
    }

    public String getExpItem() {
        return expItem;
    }

    public boolean getUsingItem() {
        return usingItem;
    }

    public StringList getTypes() {
        return types;
    }

    public NatStringTreeMap<UsesOfMove> getMoves() {
        return moves;
    }

    public NatStringTreeMap<UsesOfMove> getCurrentMoves() {
        return currentMoves;
    }

    public LgInt getNbRounds() {
        return nbRounds;
    }

    public NatStringTreeMap<CopiedMove> getCopiedMoves() {
        return copiedMoves;
    }

    public CustList<StatisticInfo> getStatistics() {
        return statistics;
    }

    public NatStringTreeMap<MultPowerMoves> getDamageRateByType() {
        return damageRateByType;
    }

    public StringList getProtectedAgainstMoveTypes() {
        return protectedAgainstMoveTypes;
    }

    public NatStringTreeMap<SufferedDamageCategory> getDamageSufferedCateg() {
        return damageSufferedCateg;
    }

    public NatStringTreeMap<ActivityOfMove> getEnabledMoves() {
        return enabledMoves;
    }

    public NatStringTreeMap<BoolVal> getEnabledMovesForAlly() {
        return enabledMovesForAlly;
    }

    public NatStringTreeMap<Long> getNbUsesMoves() {
        return nbUsesMoves;
    }

    public LgInt getNbRepeatingSuccessfulMoves() {
        return nbRepeatingSuccessfulMoves;
    }

    public boolean getSuccessfulMove() {
        return successfulMove;
    }

    public String getLastSuccessfulMove() {
        return lastSuccessfulMove;
    }

    public String getLastUsedMove() {
        return lastUsedMove;
    }

    public String getUsedMoveLastRound() {
        return usedMoveLastRound;
    }

    public long getNbPrepaRound() {
        return nbPrepaRound;
    }

    public StringList getAlreadyInvokedMovesRound() {
        return alreadyInvokedMovesRound;
    }

    public String getLastSufferedMove() {
        return lastSufferedMove;
    }

    public StringList getLastSufferedMoveTypes() {
        return lastSufferedMoveTypes;
    }

    public boolean getDisappeared() {
        return disappeared;
    }

    public boolean getNeedingToRecharge() {
        return needingToRecharge;
    }

    public NatStringTreeMap<Long> getStatus() {
        return status;
    }

    public DictionaryComparator<MoveTeamPosition,Long> getStatusRelat() {
        return statusRelat;
    }

    public DictionaryComparator<MoveTeamPosition,String> getPrivateMoves() {
        return privateMoves;
    }

    public DictionaryComparator<MoveTeamPosition,ActivityOfMove> getTrappingMoves() {
        return trappingMoves;
    }

    public DictionaryComparator<MoveTeamPosition,AffectedMove> getTrackingMoves() {
        return trackingMoves;
    }

    public DictionaryComparator<MoveTeamPosition,BoolVal> getIncrUserAccuracy() {
        return incrUserAccuracy;
    }
}