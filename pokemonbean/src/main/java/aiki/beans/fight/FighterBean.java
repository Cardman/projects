package aiki.beans.fight;
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
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public class FighterBean extends CommonFightBean {
    private String name;

    private String keyName;
    private String nickname;
    private String gender;
    private Rate weight;
    private Rate height;
    private String weightStr;
    private String heightStr;
    private String currentName;
    private String currentGender;
    private String item;
    private String expItem;
    private String ability;
    private String currentAbility;
    private NatTreeMap<String,Short> status;
    private TreeMap<MoveTeamPosition,Short> statusRelat;
    private StringList types;
    private NatTreeMap<String,UsesOfMove> moves;
    private NatTreeMap<String,UsesOfMove> currentMoves;
    private CustList<StatisticInfo> statistics;
    private Rate remainingHp;
    private String remainingHpStr;
    private String remainingHpStrPerCent;
    private Rate clone;
    private String cloneStr;
    private StringList protectedAgainstMoveTypes;
    private NatTreeMap<String,ActivityOfMove> enabledMoves;
    private NatTreeMap<String,Boolean> enabledMovesForAlly;
    private NatTreeMap<String,MultPowerMoves> damageRateByType;
    private byte groundPlace;
    private Rate wonExpSinceLastLevel;
    private Rate necessaryPointsNextLevel;
    private short level;
    private short happiness;
    private TreeMap<MoveTeamPosition,Boolean> incrUserAccuracy;
    private NatTreeMap<String,Integer> nbUsesMoves;
    private short nbPrepaRound;
    private boolean needingToRecharge;
    private TreeMap<MoveTeamPosition,AffectedMove> trackingMoves;
    private TreeMap<MoveTeamPosition,ActivityOfMove> trappingMoves;
    private NatTreeMap<String,SufferedDamageCategory> damageSufferedCateg;
    private NatTreeMap<String,CopiedMove> copiedMoves;
    private LgInt nbRepeatingSuccessfulMoves;
    private TreeMap<MoveTeamPosition,String> privateMoves;
    private boolean belongingToPlayer;
    private String lastUsedItem;
    private LgInt nbRounds;
    private boolean acted;
    private byte groundPlaceSubst;
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
        vars_.put(StringList.concat(DataBase.VAR_PREFIX,Fighter.NIVEAU),Integer.toString(level + 1));
        Rate next_;
        next_ = data_.evaluateNumericable(expLitt_, vars_, Rate.one());
        Rate current_;
        vars_.put(StringList.concat(DataBase.VAR_PREFIX,Fighter.NIVEAU),Integer.toString(level));
        current_ = data_.evaluateNumericable(expLitt_, vars_, Rate.one());
        vars_.clear();
        Rate diff_ = data_.evaluatePositiveExp(Rate.minus(next_, current_).toNumberString(), vars_, Rate.one());
        diff_.removeNb(wonExpSinceLastLevel);
        return diff_;
    }
    public boolean isBack() {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        Number noTeam_ = (Number) getForms().getVal(NO_TEAM);
        Number noFighter_ = (Number) getForms().getVal(NO_FIGHTER);
        Fighter fighter_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_.byteValue()).getMembers().getVal(noFighter_.byteValue());
        return fighter_.estArriere();
    }
    public boolean isBackSubst() {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        Number noTeam_ = (Number) getForms().getVal(NO_TEAM);
        Number noFighter_ = (Number) getForms().getVal(NO_FIGHTER);
        Fighter fighter_ = dataBaseFight_.getGame().getFight().getTeams().getVal(noTeam_.byteValue()).getMembers().getVal(noFighter_.byteValue());
        return fighter_.getGroundPlaceSubst() == Fighter.BACK;
    }
    public boolean isFoeStatusRelatTeam(Long _index) {
        MoveTeamPosition mt_ = statusRelat.getKey(_index.intValue());
        return mt_.getTeamPosition().getTeam() == Fight.FOE;
    }
    public String getStatusRelatTeam(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        MoveTeamPosition mt_ = statusRelat.getKey(_index.intValue());
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }
    public boolean isEnabled(Long _index) {
        return statusRelat.getValue(_index.intValue()) > 0;
    }
    public boolean isFoePrivateMovesTeam(Long _index) {
        MoveTeamPosition mt_ = privateMoves.getKey(_index.intValue());
        return mt_.getTeamPosition().getTeam() == Fight.FOE;
    }
    public String getIncrPrivateMovesTeam(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        MoveTeamPosition mt_ = privateMoves.getKey(_index.intValue());
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }
    public boolean isFoeTrappingMovesTeam(Long _index) {
        MoveTeamPosition mt_ = trappingMoves.getKey(_index.intValue());
        return mt_.getTeamPosition().getTeam() == Fight.FOE;
    }
    public String getIncrTrappingMovesTeam(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        MoveTeamPosition mt_ = trappingMoves.getKey(_index.intValue());
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }
    public boolean isFoeTrackingMovesTeam(Long _index) {
        MoveTeamPosition mt_ = trackingMoves.getKey(_index.intValue());
        return mt_.getTeamPosition().getTeam() == Fight.FOE;
    }
    public String getIncrTrackingMovesTeam(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        MoveTeamPosition mt_ = trackingMoves.getKey(_index.intValue());
        return getFighterAtPosition(dataBaseFight_, mt_.getTeamPosition());
    }
    public boolean isFoeIncrUserAccuracyTeam(Long _index) {
        MoveTeamPosition mt_ = incrUserAccuracy.getKey(_index.intValue());
        return mt_.getTeamPosition().getTeam() == Fight.FOE;
    }
    public String getIncrUserAccuracyTeam(Long _index) {
        FacadeGame dataBaseFight_ = (FacadeGame) getDataBase();
        MoveTeamPosition mt_ = incrUserAccuracy.getKey(_index.intValue());
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

    public byte getGroundPlaceSubst() {
        return groundPlaceSubst;
    }

    public byte getGroundPlace() {
        return groundPlace;
    }

    public String getNickname() {
        return nickname;
    }

    public short getHappiness() {
        return happiness;
    }

    public short getLevel() {
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
        return weightStr;
    }

    public Rate getHeight() {
        return height;
    }

    public String getHeightStr() {
        return heightStr;
    }

    public Rate getRemainingHp() {
        return remainingHp;
    }

    public String getRemainingHpStr() {
        return remainingHpStr;
    }

    public String getRemainingHpStrPerCent() {
        return remainingHpStrPerCent;
    }

    public Rate getClone() {
        return clone;
    }

    public String getCloneStr() {
        return cloneStr;
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

    public NatTreeMap<String,UsesOfMove> getMoves() {
        return moves;
    }

    public NatTreeMap<String,UsesOfMove> getCurrentMoves() {
        return currentMoves;
    }

    public LgInt getNbRounds() {
        return nbRounds;
    }

    public NatTreeMap<String,CopiedMove> getCopiedMoves() {
        return copiedMoves;
    }

    public CustList<StatisticInfo> getStatistics() {
        return statistics;
    }

    public NatTreeMap<String,MultPowerMoves> getDamageRateByType() {
        return damageRateByType;
    }

    public StringList getProtectedAgainstMoveTypes() {
        return protectedAgainstMoveTypes;
    }

    public NatTreeMap<String,SufferedDamageCategory> getDamageSufferedCateg() {
        return damageSufferedCateg;
    }

    public NatTreeMap<String,ActivityOfMove> getEnabledMoves() {
        return enabledMoves;
    }

    public NatTreeMap<String,Boolean> getEnabledMovesForAlly() {
        return enabledMovesForAlly;
    }

    public NatTreeMap<String,Integer> getNbUsesMoves() {
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

    public short getNbPrepaRound() {
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

    public NatTreeMap<String,Short> getStatus() {
        return status;
    }

    public TreeMap<MoveTeamPosition,Short> getStatusRelat() {
        return statusRelat;
    }

    public TreeMap<MoveTeamPosition,String> getPrivateMoves() {
        return privateMoves;
    }

    public TreeMap<MoveTeamPosition,ActivityOfMove> getTrappingMoves() {
        return trappingMoves;
    }

    public TreeMap<MoveTeamPosition,AffectedMove> getTrackingMoves() {
        return trackingMoves;
    }

    public TreeMap<MoveTeamPosition,Boolean> getIncrUserAccuracy() {
        return incrUserAccuracy;
    }
}