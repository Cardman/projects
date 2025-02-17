package aiki.beans.help;

import aiki.beans.CommonBean;
import aiki.beans.PokemonStandards;
import aiki.beans.TranslatedKey;
import aiki.beans.WithFilterBean;
import aiki.beans.abilities.TranslatedKeyPair;
import aiki.beans.facade.comparators.ComparatorTranslatedKeyList;
import aiki.comparators.ComparingTranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.status.Status;
import aiki.fight.status.StatusBeginRound;
import aiki.fight.status.StatusBeginRoundAutoDamage;
import aiki.fight.status.StatusType;
import aiki.fight.util.*;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.instances.Instances;
import code.maths.Rate;
import code.maths.litteralcom.MathExpUtil;
import code.maths.montecarlo.MonteCarloNumber;
import code.scripts.confs.PkScriptPages;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import code.util.ints.Listable;

public class FightHelpBean extends CommonBean {

//    private static final String VAR_BOOST ="b";
    private CustList<TranslatedKey> abilitiesSentBeginWeather;
    private CustList<TranslatedKey> abilitiesSentBeginOther;
    private CustList<TranslatedKey> abilitiesSentStatis;
    private CustList<TranslatedKey> itemsSentBeginWeather;
    private CustList<TranslatedKey> itemsSentBeginOther;
    private CustList<TranslatedKey> changingTypesAbilities;
    private CustList<TranslatedKey> copyAbilities;
    private CustList<TranslatedKey> privatingMoves;
    private CustList<TranslatedKey> movesHealingSubstitute;
    private CustList<TranslatedKey> substitutingMoves;
    private CustList<TranslatedKey> abilitiesPrio;
    private CustList<TranslatedKey> slowAbilities;
    private CustList<TranslatedKey> slowItems;
    private CustList<TranslatedKey> reverseSpeedMoves;
    private CustList<TranslatedKey> berrySpeed;
    private CustList<TranslatedKey> itemSpeed;
    private CustList<TranslatedKey> abilitiesSwitch;
    private CustList<TranslatedKey> deletedStatusSwitch;
    private CustList<TranslatedKey> entryHazard;
    private CustList<TranslatedKey> beginRoundStatus;
    private CustList<TranslatedKey> deleteStatusMove;
    private CustList<TranslatedKey> immuStatusAbility;
    private AbsMap<TranslatedKey,StatusBeginRoundAutoDamage> autoDamage;
    private String damgeFormula;
    private NatStringTreeMap<String> mapAutoDamage;
    private CustList<TranslatedKey> prepaRoundMoves;
    private CustList<TranslatedKey> disappearingRoundMoves;
    private CustList<TranslatedKey> speedPreparingItems;
    private CustList<TranslatedKey> rechargeMoves;
    private CustList<TranslatedKey> immuRecharging;
    private CustList<TranslatedKey> movesInvoking;
    private CustList<TranslatedKey> movesThieving;
    private CustList<TranslatedKey> movesAttracting;
    private CustList<TranslatedKey> movesMirror;
    private CustList<TranslatedKey> copyMoveTypesAb;
    private CustList<TranslatedKey> beginRoundStatusFoe;
    private CustList<TranslatedKey> movesSecEffItems;
    private CustList<TranslatedKey> pressureAbilities;
    private CustList<TranslatedKey> protectAbilities;
    private CustList<TranslatedKey> protectItems;
    private CustList<TranslatedKey> protectMoves;
    private CustList<TranslatedKey> effMoves;
    private CustList<TranslatedKey> abilitiesPartStatis;
    private CustList<TranslatedKey> movesTeam;
    private CustList<TranslatedKey> abilitiesRateStatis;
    private CustList<TranslatedKey> abilitiesFighterStatis;
    private CustList<TranslatedKey> itemsFighterStatis;
    private CustList<TranslatedKey> abilitiesFighterStatisVar;
    private CustList<TranslatedKey> successfulStatus;
    private CustList<TranslatedKey> globalMovesStatus;
    private CustList<TranslatedKey> abilitiesPartStatus;
    private CustList<TranslatedKey> abilitiesFighterStatus;
    private CustList<TranslatedKey> itemsFighterStatus;
    private CustList<TranslatedKey> movesProtAgainstKo;
    private CustList<TranslatedKey> itemsProtAgainstKo;
    private CustList<TranslatedKey> movesCannotKo;
    private CustList<TranslatedKey> itemsAbs;
    private CustList<TranslatedKey> abilitiesRevAbs;
    private CustList<TranslatedKey> abilitiesDamageStatis;
    private CustList<TranslatedKey> abilitiesChangingTypesDamage;
    private CustList<TranslatedKey> abilitiesTakingItem;
    private CustList<TranslatedKey> abilitiesStatisVarUser;
    private CustList<TranslatedKey> abilitiesStatus;
    private CustList<TranslatedKey> abilitiesCopyAb;
    private CustList<TranslatedKey> recoilItems;
    private CustList<TranslatedKey> recoilAbilities;
    private CustList<TranslatedKey> abilitiesKoTarget;
    private CustList<TranslatedKey> movesKoTarget;
    private CustList<TranslatedKey> berryUser;
    private CustList<TranslatedKey> berryTarget;
    private CustList<TranslatedKey> abilitiesEndRound;
    private CustList<TranslatedKey> berryEndRound;
    private CustList<TranslatedKey> movesChangingAttOrder;
    private CustList<TranslatedKey> damagingMoves;
    private CustList<TranslatedKey> itemsUserPower;
    private CustList<TranslatedKey> movesUserPower;
    private CustList<TranslatedKey> movesTargetPower;
    private CustList<TranslatedKey> abilitiesUserPower;
    private NatStringTreeMap<String> mapVar;
    private CustList<TranslatedKey> abilitiesTargetDamage;
    private CustList<TranslatedKey> movesTargetTeamDamage;
    private CustList<TranslatedKey> abilitiesGlobal;
    private CustList<TranslatedKey> movesGlobal;
    private CustList<TranslatedKey> itemsUserDamage;
    private CustList<TranslatedKey> abilitiesUserDamage;
    private CustList<TranslatedKey> movesInvokDamage;
    private CustList<TranslatedKey> itemsTargetDamage;
    private CustList<TranslatedKey> movesGlobalPrepaDamage;
    private CustList<TranslatedKey> statusDamage;
    private CustList<TranslatedKey> abilitiesUserTargetDamage;
    private CustList<TranslatedKey> abilitiesUserStabDamage;
    private CustList<TranslatedKey> movesUserAllyDamage;
    private CustList<TranslatedKey> abilitiesUserIgnTargetTeam;
    private CustList<TranslatedKey> movesIgnLowAtt;
    private CustList<TranslatedKey> movesIgnIncDef;
    private CustList<TranslatedKey> itemsCancelImmu;
    private CustList<TranslatedKey> movesProtectingTypes;
    private CustList<TranslatedKey> movesUnprotectingTypes;
    private CustList<TranslatedKey> movesGlobalBreakImmu;
    private CustList<TranslatedKey> movesGlobalBreakImmuAb;
    private CustList<TranslatedKey> abilitiesBreakable;
    private CustList<TranslatedKey> abilitiesImmuTypes;
    private CustList<TranslatedKey> itemsImmuTypes;
    private CustList<TranslatedKey> abilitiesImmuAllies;
    private CustList<TranslatedKey> abilitiesImmuAlliesDam;
    private CustList<TranslatedKey> abilitiesImmu;
    private CustList<TranslatedKey> itemsImmu;
    private CustList<TranslatedKey> abilitiesImmuSecEffOther;
    private CustList<TranslatedKey> abilitiesImmuSecEffOwner;
    private CustList<TranslatedKey> abilitiesAchieveTarget;
    private CustList<TranslatedKey> abilitiesBreakProtectMoves;
    private CustList<TranslatedKey> movesProtecting;
    private CustList<TranslatedKey> movesIgnAcc;
    private CustList<TranslatedKey> movesIgnEva;
    private CustList<TranslatedKey> movesGlobalAcc;
    private CustList<TranslatedKey> abilitiesBoostingStat;
    private CustList<TranslatedKey> itemsBoostingStat;
    private CustList<TranslatedKey> abilitiesMultStat;
    private CustList<TranslatedKey> itemsMultStat;
    private CustList<TranslatedKey> movesGlobalMultStat;
    private CustList<TranslatedKey> movesTeamMultStat;
    private CustList<TranslatedKey> movesFoeTeamMultStat;
    private CustList<TranslatedKey> abilitiesAllyMultStat;
    private CustList<TranslatedKey> statusMultStat;
    private CustList<TranslatedKey> abilitiesImmuMultStat;
    private CustList<CustList<TranslatedKey>> comboMultStat;
    private CustList<CustList<TranslatedKey>> comboEvtStat;
    private CustList<TranslatedKey> movesTypesDefItem;
    private CustList<TranslatedKey> movesTypesDefWeather;
    private CustList<TranslatedKey> abilitiesTypeDefMoves;
    private CustList<TranslatedKey> abilitiesChangeTypeMoves;
    private CustList<TranslatedKey> movesTypeDefMoves;
    private CustList<TranslatedKey> movesChangeTypeMoves;
    private CustList<TranslatedKey> abilitiesBreakImmu;
    private CustList<TranslatedKey> abilitiesImmuCh;
    private CustList<TranslatedKey> movesBoostCh;
    private CustList<TranslatedKey> abilitesMultEvtCh;
    private CustList<TranslatedKey> abilitesMultRateCh;
    private String rateFormula;
    private String rateFormulaCh;
    private CustList<TranslatedKey> itemsTypesDef;
    private final LongTreeMap<Rate> boosts = new LongTreeMap<Rate>();
    private final LongTreeMap<Rate> boostsCh = new LongTreeMap<Rate>();
    private DictionaryComparator<TranslatedKeyPair,Rate> efficiency;
    private CustList<TranslatedKey> types;
    private Rate minHpNotKo;
    private Rate wonHappinessPointsLevel;
    private long defaultBoostValue;
    private TranslatedKey defaultMove;
    private String catchingFormula;
    private NatStringTreeMap<String> varCatchingFormula;
    private String fleeingFormula;
    private NatStringTreeMap<String> varFleeingFormula;
    private long happinessPoints;
    private Rate strongMove;
    private StringMap<String> rates;
    private NatStringTreeMap< String> varRates;
    private StringMap<AbsBasicTreeMap<Rate,Rate>> lawsRates;
    private IdList<Statistic> statisticAnim;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        wonHappinessPointsLevel = data_.getWonHappinessByGrowLevel();
        happinessPoints = data_.getHappinessEvo();
        defaultMove = buildMv(getFacade(),data_.getDefMove());
        defaultBoostValue = data_.getDefaultBoost();
        strongMove = data_.getStrongMovePower();
//        StringMap<String> replace_ = new StringMap<String>();
//        rateFormula = data_.getRateBoost();
//        String pref_ = data_.prefixVar();
//        replace_.put(StringUtil.concat(pref_,DataBase.SEP_BETWEEN_KEYS,data_.boost()), VAR_BOOST);
//        rateFormula = rateFormula.replaceAll(StringList.BOUNDS+DataBase.VAR_PREFIX+Fight.BOOST+StringList.BOUNDS, VAR_BOOST);
//        rateFormula = MathExpUtil.replaceWordsJoin(rateFormula, replace_);
        rateFormula = data_.getFormula(data_.getRateBoost(),getLanguage());
//        replace_ = new StringMap<String>();
//        rateFormulaCh = data_.getRateBoostCriticalHit();
//        replace_.put(StringUtil.concat(pref_,DataBase.SEP_BETWEEN_KEYS,data_.boost()), VAR_BOOST);
//        rateFormulaCh = rateFormulaCh.replaceAll(StringList.BOUNDS+DataBase.VAR_PREFIX+Fight.BOOST+StringList.BOUNDS, VAR_BOOST);
//        rateFormulaCh = MathExpUtil.replaceWordsJoin(rateFormulaCh, replace_);
        rateFormulaCh = data_.getFormula(data_.getRateBoostCriticalHit(),getLanguage());
        long minBoost_ = data_.getMinBoost();
        long maxBoost_ = data_.getMaxBoost();
        initBoosts(minBoost_, maxBoost_);
        initSendingMembers();
        initPrivateMoves();
        movesHealingSubstitute = listTrStringsMv(data_.getMovesFullHeal(),getFacade());
        initSubstitutingMoves();
        initSpeedElements();
        initSwitchingMembers();
        initBeginRoundStatusMembers();
        initBeginRoundPreparingMembers();
        initInvokingMembers();
        initCopyMoveTypesAb();
        initBeginRoundStatusFoe();
        initMovesSecEffItems();
        initPressureAbilities();
        initProtectingMembers();
        initEffMoves();
        initAbilitiesPartStatis();
        movesTeam = listTrStringsMv(data_.getMovesEffectTeam(),getFacade());
        initAbilitiesRateStatis();
        initStatisticsImmuElements();
        initStatusElements();
        initWhileDamageElements();
        initBerryEndEffectMembers();
        initEndRoundUserMembers();
        initPowerElements();
        initDamageCalculationElements();
        initSuccessEffectsElements();
        initAccuracyEvasinessElements();
        initStatisticsCalculationElements();
        initTypeDef();
        movesTypesDefWeather = listTrStringsMv(movesTypesDefWeatherInit(data_),getFacade());
        abilitiesTypeDefMoves = listTrStringsAb(abilitiesTypeDefMovesInit(data_),getFacade());
        abilitiesChangeTypeMoves = listTrStringsAb(abilitiesChangeTypeMovesInit(data_),getFacade());
        initMovesEffectSwitchMoveTypes();
        abilitiesBreakImmu = listTrStringsAb(abilitiesBreakImmuInit(data_),getFacade());
        initCriticalHitElements();
        //getMultEvtRateCh
        //getMultDamageCh
//        efficiency = new TreeMap<>(new NaturalComparator<TypesDuo>() {
//            public int compare(TypesDuo _o1, TypesDuo _o2) {
//                int res_ = _o1.getPokemonType().compareTo(_o2.getPokemonType());
//                if (res_ != Constants.EQ_CMP) {
//                    return res_;
//                }
//                return _o1.getDamageType().compareTo(_o2.getDamageType());
//            }
//        });
        efficiency = efficiencyInit(getFacade());
        types = typesInit(getFacade());
        initFormulaElements();
    }
    static StringList movesTypesDefWeatherInit(DataBase _db) {
        StringList movesTypesDefWeather_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (!move_.getTypesByWeather().isEmpty()) {
                movesTypesDefWeather_.add(m);
            }
        }
        return movesTypesDefWeather_;
    }
    static StringList abilitiesTypeDefMovesInit(DataBase _db) {
        StringList abilitiesTypeDefMoves_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getTypeForMoves().isEmpty()) {
                abilitiesTypeDefMoves_.add(a);
            }
        }
        return abilitiesTypeDefMoves_;
    }
    static StringList abilitiesChangeTypeMovesInit(DataBase _db) {
        StringList abilitiesChangeTypeMoves_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getChangingBoostTypes().isEmpty()) {
                abilitiesChangeTypeMoves_.add(a);
            }
        }
        return abilitiesChangeTypeMoves_;
    }
    static StringList abilitiesBreakImmuInit(DataBase _db) {
        StringList abilitiesBreakImmu_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getBreakFoeImmune().isEmpty()) {
                abilitiesBreakImmu_.add(a);
            }
        }
        return abilitiesBreakImmu_;
    }
    static DictionaryComparator<TranslatedKeyPair, Rate> efficiencyInit(FacadeGame _db) {
        DictionaryComparator<TranslatedKeyPair, Rate> efficiency_ = DictionaryComparatorUtil.buildTypesDuoRate();
        for (TypesDuo t: _db.getData().getTableTypes().getKeys()) {
            efficiency_.put(new TranslatedKeyPair(buildTy(_db,t.getPokemonType()),buildTy(_db,t.getDamageType())), _db.getData().getTableTypes().getVal(t));
        }
        return efficiency_;
    }
    static CustList<TranslatedKey> typesInit(FacadeGame _db) {
        StringList types_ = new StringList();
        for (TypesDuo t: _db.getData().getTableTypes().getKeys()) {
            types_.add(t.getDamageType());
        }
        types_.removeDuplicates();
        return listTrStringsTy(types_,_db);
    }
    static StringList movesTypesDefItemInit(DataBase _db) {
        StringList movesTypesDefItem_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (!move_.getTypesByOwnedItem().isEmpty()) {
                movesTypesDefItem_.add(m);
            }
        }
        return movesTypesDefItem_;
    }
    static StringList itemsTypesDefInit(StringList _moves,DataBase _db) {
        StringList itemsTypesDef_ = new StringList();
        for (String m: _moves) {
            MoveData move_ = _db.getMove(m);
            itemsTypesDef_.addAllElts(move_.getTypesByOwnedItem().getKeys());
        }
        itemsTypesDef_.removeDuplicates();
        StringUtil.removeObj(itemsTypesDef_, DataBase.EMPTY_STRING);
        return itemsTypesDef_;
    }
    static StringList abilitiesRateStatisInit(DataBase _db) {
        StringList abilitiesRateStatis_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultEvtRateSecEffectOwner().isZero()) {
                abilitiesRateStatis_.add(a);
            }
        }
        return abilitiesRateStatis_;
    }

    private void initTypeDef() {
        DataBase data_ = getDataBase();
        movesTypesDefItem = listTrStringsMv(movesTypesDefItemInit(data_),getFacade());
        itemsTypesDef = listTrStringsIt(itemsTypesDefInit(WithFilterBean.keys(movesTypesDefItem),data_),getFacade());
    }

    private void initAbilitiesRateStatis() {
        DataBase data_ = getDataBase();
        abilitiesRateStatis = listTrStringsAb(abilitiesRateStatisInit(data_),getFacade());
    }

    static StringList abilitiesPartStatisInit(DataBase _db) {
        StringList abilitiesPartStatis_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getImmuLowStatisTypes().isEmpty()) {
                abilitiesPartStatis_.add(a);
            }
        }
        return abilitiesPartStatis_;
    }
    private void initAbilitiesPartStatis() {
        DataBase data_ = getDataBase();
        abilitiesPartStatis = listTrStringsAb(abilitiesPartStatisInit(data_),getFacade());
    }

    private void initEffMoves() {
        DataBase data_ = getDataBase();
        effMoves = listTrStringsMv(effMovesInit(data_),getFacade());
    }
    static StringList effMovesInit(DataBase _db) {
        StringList effMoves_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (move_.getSecEffectIfNoDamage()) {
                effMoves_.add(m);
            }
        }
        return effMoves_;
    }

    private void initPressureAbilities() {
        DataBase data_ = getDataBase();
        pressureAbilities = listTrStringsAb(pressureAbilitiesInit(data_),getFacade());
    }
    static StringList pressureAbilitiesInit(DataBase _db) {
        StringList pressureAbilities_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.getNbUsedPp() > 0) {
                pressureAbilities_.add(a);
            }
        }
        return pressureAbilities_;
    }

    private void initMovesSecEffItems() {
        DataBase data_ = getDataBase();
        movesSecEffItems = listTrStringsMv(movesSecEffItemsInit(data_),getFacade());
    }
    static StringList movesSecEffItemsInit(DataBase _db) {
        StringList movesSecEffItems_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (!move_.getSecEffectsByItem().isEmpty()) {
                movesSecEffItems_.add(m);
            }
        }
        return movesSecEffItems_;
    }

    private void initBeginRoundStatusFoe() {
        DataBase data_ = getDataBase();
        beginRoundStatusFoe = listTrStringsSt(beginRoundStatusFoeInit(data_),getFacade());
    }
    static StringList beginRoundStatusFoeInit(DataBase _db) {
        StringList beginRoundStatusFoe_ = new StringList();
        for (String s: _db.getStatus().getKeys()) {
            Status st_ = _db.getStatus(s);
            if (st_.getStatusType() != StatusType.INDIVIDUEL && st_ instanceof StatusBeginRound) {
                beginRoundStatusFoe_.add(s);
            }
        }
        return beginRoundStatusFoe_;
    }

    private void initCopyMoveTypesAb() {
        DataBase data_ = getDataBase();
        copyMoveTypesAb = listTrStringsAb(copyMoveTypesAbInit(data_),getFacade());
    }
    static StringList copyMoveTypesAbInit(DataBase _db) {
        StringList copyMoveTypesAb_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isCopyMovesTypes()) {
                copyMoveTypesAb_.add(a);
            }
        }
        return copyMoveTypesAb_;
    }

    private void initSubstitutingMoves() {
        DataBase data_ = getDataBase();
        substitutingMoves = listTrStringsMv(substitutingMovesInit(data_),getFacade());
    }
    static StringList substitutingMovesInit(DataBase _db) {
        StringList substitutingMoves_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (move_.getSwitchType() == SwitchType.LANCEUR) {
                substitutingMoves_.add(m);
            }
        }
        return substitutingMoves_;
    }

    private void initMovesEffectSwitchMoveTypes() {
        DataBase data_ = getDataBase();
        movesTypeDefMoves = listTrStringsMv(movesTypeDefMovesInit(data_),getFacade());
        movesChangeTypeMoves = listTrStringsMv(movesChangeTypeMovesInit(data_),getFacade());
    }
    static StringList movesTypeDefMovesInit(DataBase _db) {
        StringList movesTypeDefMoves_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectSwitchMoveTypes && !((EffectSwitchMoveTypes) e).getReplacingTypes().isEmpty()) {
                    movesTypeDefMoves_.add(m);
                    break;
                }
            }
        }
        return movesTypeDefMoves_;
    }
    static StringList movesChangeTypeMovesInit(DataBase _db) {
        StringList movesChangeTypeMoves_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectSwitchMoveTypes && !((EffectSwitchMoveTypes) e).getChangeTypes().isEmpty()) {
                    movesChangeTypeMoves_.add(m);
                    break;
                }
            }
        }
        return movesChangeTypeMoves_;
    }

    private void initPrivateMoves() {
        DataBase data_ = getDataBase();
        privatingMoves = listTrStringsMv(privatingMovesInit(data_),getFacade());
    }
    static StringList privatingMovesInit(DataBase _db) {
        StringList privatingMoves_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectRestriction && ((EffectRestriction) e).getChoiceRestriction() != MoveChoiceRestrictionType.NOTHING) {
                    privatingMoves_.add(m);
                    break;
                }
            }
        }
        return privatingMoves_;
    }

    private void initAccuracyEvasinessElements() {
        DataBase data_ = getDataBase();
        movesIgnAcc = listTrStringsMv(movesIgnAccInit(data_),getFacade());
        movesIgnEva = listTrStringsMv(movesIgnEvaInit(data_),getFacade());
        movesGlobalAcc = listTrStringsMv(movesGlobalAccInit(data_),getFacade());
    }
    static StringList movesIgnAccInit(DataBase _db) {
        StringList movesIgnAcc_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (move_.getIgnVarAccurUserNeg() && move_.getTargetChoice() != TargetChoice.LANCEUR) {
                movesIgnAcc_.add(m);
            }
        }
        return movesIgnAcc_;
    }
    static StringList movesIgnEvaInit(DataBase _db) {
        StringList movesIgnEva_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (move_.getIgnVarEvasTargetPos() && move_.getTargetChoice() != TargetChoice.LANCEUR) {
                movesIgnEva_.add(m);
            }
        }
        return movesIgnEva_;
    }
    static StringList movesGlobalAccInit(DataBase _db) {
        StringList movesGlobalAcc_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getMultAccuracy().isZero()) {
                    movesGlobalAcc_.add(m);
                    break;
                }
            }
        }
        return movesGlobalAcc_;
    }

    private void initStatusElements() {
        DataBase data_ = getDataBase();
        successfulStatus = listTrStringsSt(successfulStatusInit(data_),getFacade());
        initGlobalMovesStatus();
        abilitiesPartStatus = listTrStringsAb(abilitiesPartStatusInit(data_),getFacade());
        abilitiesFighterStatus = listTrStringsAb(abilitiesFighterStatusInit(data_),getFacade());
        itemsFighterStatus = listTrStringsIt(itemsFighterStatusInit(data_),getFacade());
    }
    static StringList successfulStatusInit(DataBase _db) {
        StringList successfulStatus_ = new StringList();
        for (String s: _db.getStatus().getKeys()) {
            Status st_ = _db.getStatus(s);
            if (!st_.getEffectsPartner().isEmpty() && st_.getEffectsPartner().first().getWeddingAlly()) {
                successfulStatus_.add(s);
            }
        }
        return successfulStatus_;
    }
    static StringList abilitiesPartStatusInit(DataBase _db) {
        StringList abilitiesPartStatus_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getImmuStatusTypes().isEmpty()) {
                abilitiesPartStatus_.add(a);
            }
        }
        return abilitiesPartStatus_;
    }
    static StringList abilitiesFighterStatusInit(DataBase _db) {
        StringList abilitiesFighterStatus_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getImmuStatus().isEmpty()) {
                abilitiesFighterStatus_.add(a);
            }
        }
        return abilitiesFighterStatus_;
    }
    static StringList itemsFighterStatusInit(DataBase _db) {
        StringList itemsFighterStatus_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getImmuStatus().isEmpty()) {
                itemsFighterStatus_.add(i);
            }
        }
        return itemsFighterStatus_;
    }

    private void initGlobalMovesStatus() {
        DataBase data_ = getDataBase();
        globalMovesStatus = listTrStringsMv(globalMovesStatusInit(data_),getFacade());
    }
    static StringList globalMovesStatusInit(DataBase _db) {
        StringList globalMovesStatus_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getPreventStatus().isEmpty()) {
                    globalMovesStatus_.add(m);
                }
            }
        }
        return globalMovesStatus_;
    }

    private void initCriticalHitElements() {
        DataBase data_ = getDataBase();
        abilitiesImmuCh = listTrStringsAb(abilitiesImmuChInit(data_),getFacade());
        initMovesBoostCh();
        abilitesMultEvtCh = listTrStringsAb(abilitesMultEvtChInit(data_),getFacade());
        abilitesMultRateCh = listTrStringsAb(abilitesMultRateChInit(data_),getFacade());
    }
    static StringList abilitiesImmuChInit(DataBase _db) {
        StringList abilitiesImmuCh_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isImmuCh()) {
                abilitiesImmuCh_.add(a);
            }
        }
        return abilitiesImmuCh_;
    }
    static StringList abilitesMultEvtChInit(DataBase _db) {
        StringList abilitesMultEvtCh_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultEvtRateCh().isZero()) {
                abilitesMultEvtCh_.add(a);
            }
        }
        return abilitesMultEvtCh_;
    }
    static StringList abilitesMultRateChInit(DataBase _db) {
        StringList abilitesMultRateCh_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultDamageCh().isZero()) {
                abilitesMultRateCh_.add(a);
            }
        }
        return abilitesMultRateCh_;
    }

    private void initMovesBoostCh() {
        DataBase data_ = getDataBase();
        movesBoostCh = listTrStringsMv(movesBoostChInit(data_),getFacade());
    }
    static StringList movesBoostChInit(DataBase _db) {
        StringList movesBoostCh_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectDamage && ((EffectDamage) e).getChRate() > 0) {
                    movesBoostCh_.add(m);
                    break;
                }
            }
        }
        return movesBoostCh_;
    }

    private void initStatisticsCalculationElements() {
        DataBase data_ = getDataBase();
        abilitiesBoostingStat = listTrStringsAb(abilitiesBoostingStatInit(data_),getFacade());
        initItemsBoostingStat();
        initAbItMultStat();
        initMultStatTeamGlobal();
        abilitiesAllyMultStat = listTrStringsAb(abilitiesAllyMultStatInit(data_),getFacade());
        statusMultStat = listTrStringsSt(statusMultStatInit(data_),getFacade());
        abilitiesImmuMultStat = listTrStringsAb(abilitiesImmuMultStatInit(data_),getFacade());
        initComboMultStat();
//        comboMultStat.sort(new NaturalComparator<StringList>(){
//            public int compare(StringList _key1, StringList _key2) {
//                DataBase dataCmp_ = (DataBase) getDataBase();
//                Map<String,String> translatedMovesCmp_ = dataCmp_.getTranslatedMoves().getVal(getLanguage());
//                int lenOne_ = _key1.size();
//                int lenTwo_ = _key2.size();
//                int minLen_ = Math.min(lenOne_, lenTwo_);
//                int diff_ = lenOne_ - lenTwo_;
//                if (diff_ != 0) {
//                    return diff_;
//                }
//                for (int i = CustList.FIRST_INDEX; i < minLen_; i++) {
//                    int res_ = ComparatorTrString.compare(translatedMovesCmp_, _key1.get(i), _key2.get(i));
//                    if (res_ != 0) {
//                        return res_;
//                    }
//                }
//                return 0;
//            }
//        });
        comboMultStat.sortElts(new ComparatorTranslatedKeyList());
        initComboEvtStat();
//        comboEvtStat.sort(new NaturalComparator<StringList>(){
//            public int compare(StringList _key1, StringList _key2) {
//                DataBase dataCmp_ = (DataBase) getDataBase();
//                Map<String,String> translatedMovesCmp_ = dataCmp_.getTranslatedMoves().getVal(getLanguage());
//                int lenOne_ = _key1.size();
//                int lenTwo_ = _key2.size();
//                int minLen_ = Math.min(lenOne_, lenTwo_);
//                int diff_ = lenOne_ - lenTwo_;
//                if (diff_ != 0) {
//                    return diff_;
//                }
//                for (int i = CustList.FIRST_INDEX; i < minLen_; i++) {
//                    int res_ = ComparatorTrString.compare(translatedMovesCmp_, _key1.get(i), _key2.get(i));
//                    if (res_ != 0) {
//                        return res_;
//                    }
//                }
//                return 0;
//            }
//        });
        comboEvtStat.sortElts(new ComparatorTranslatedKeyList());
    }
    static StringList abilitiesBoostingStatInit(DataBase _db) {
        StringList abilitiesBoostingStat_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getBonusStatRank().isEmpty()) {
                abilitiesBoostingStat_.add(a);
            }
        }
        return abilitiesBoostingStat_;
    }
    static StringList abilitiesAllyMultStatInit(DataBase _db) {
        StringList abilitiesAllyMultStat_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultStatAlly().isEmpty()) {
                abilitiesAllyMultStat_.add(a);
            }
        }
        return abilitiesAllyMultStat_;
    }
    static StringList statusMultStatInit(DataBase _db) {
        StringList statusMultStat_ = new StringList();
        for (String s: _db.getStatus().getKeys()) {
            Status st_ = _db.getStatus(s);
            if (st_.getStatusType() != StatusType.RELATION_UNIQUE && !st_.getMultStat().isEmpty()) {
                statusMultStat_.add(s);
            }
        }
        return statusMultStat_;
    }
    static StringList abilitiesImmuMultStatInit(DataBase _db) {
        StringList abilitiesImmuMultStat_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getImmuLowStatIfStatus().isEmpty()) {
                abilitiesImmuMultStat_.add(a);
            }
        }
        return abilitiesImmuMultStat_;
    }

    private void initAbItMultStat() {
        DataBase data_ = getDataBase();
        abilitiesMultStat = listTrStringsAb(abilitiesMultStatInit(data_),getFacade());
        itemsMultStat = listTrStringsIt(itemsMultStatInit(data_),getFacade());
    }
    static StringList abilitiesMultStatInit(DataBase _db) {
        StringList abilitiesMultStat_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultStat().isEmpty() || !ab_.getMultStatIfCat().isEmpty()) {
                abilitiesMultStat_.add(a);
            }
        }
        return abilitiesMultStat_;
    }
    static StringList itemsMultStatInit(DataBase _db) {
        StringList itemsMultStat_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getMultStat().isEmpty()) {
                itemsMultStat_.add(i);
            }
        }
        return itemsMultStat_;
    }

    private void initMultStatTeamGlobal() {
        DataBase data_ = getDataBase();
        movesGlobalMultStat = listTrStringsMv(movesGlobalMultStatInit(data_),getFacade());
        initMultStatTeam();
    }
    static StringList movesGlobalMultStatInit(DataBase _db) {
        StringList movesGlobalMultStat_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getMultStatIfContainsType().isEmpty()) {
                    movesGlobalMultStat_.add(m);
                    break;
                }
            }
        }
        return movesGlobalMultStat_;
    }

    private void initMultStatTeam() {
        DataBase data_ = getDataBase();
        movesTeamMultStat = listTrStringsMv(movesTeamMultStatInit(data_),getFacade());
        movesFoeTeamMultStat = listTrStringsMv(movesFoeTeamMultStatInit(data_),getFacade());
    }
    static StringList movesTeamMultStatInit(DataBase _db) {
        StringList movesTeamMultStat_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectTeam && !((EffectTeam) e).getMultStatistic().isEmpty()) {
                    movesTeamMultStat_.add(m);
                    break;
                }
            }
        }
        return movesTeamMultStat_;
    }
    static StringList movesFoeTeamMultStatInit(DataBase _db) {
        StringList movesFoeTeamMultStat_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectTeam && !((EffectTeam) e).getMultStatisticFoe().isEmpty()) {
                    movesFoeTeamMultStat_.add(m);
                    break;
                }
            }
        }
        return movesFoeTeamMultStat_;
    }

    private void initComboMultStat() {
        comboMultStat = comboMultStatInit(getFacade());
    }
    static CustList<CustList<TranslatedKey>> comboMultStatInit(FacadeGame _db) {
        CustList<CustList<TranslatedKey>> comboMultStat_ = new CustList<CustList<TranslatedKey>>();
        for (StringList g: _db.getData().getCombos().getEffects().getKeys()) {
            EffectCombo effect_ = _db.getData().getCombos().getEffects().getVal(g);
            if (effect_.estActifEquipe()) {
                comboMultStat_.add(listTrStringsMv(g,_db));
            }
        }
        for (CustList<TranslatedKey> v: comboMultStat_) {
            v.sortElts(new ComparingTranslatedKey());
        }
        return comboMultStat_;
    }

    private void initComboEvtStat() {
        comboEvtStat = comboEvtStatInit(getFacade());
    }
    static CustList<CustList<TranslatedKey>> comboEvtStatInit(FacadeGame _db) {
        CustList<CustList<TranslatedKey>> comboEvtStat_ = new CustList<CustList<TranslatedKey>>();
        for (StringList g: _db.getData().getCombos().getEffects().getKeys()) {
            EffectCombo effect_ = _db.getData().getCombos().getEffects().getVal(g);
            if (!effect_.getMultEvtRateSecEff().isZero()) {
                comboEvtStat_.add(listTrStringsMv(g,_db));
            }
        }
        for (CustList<TranslatedKey> v: comboEvtStat_) {
            v.sortElts(new ComparingTranslatedKey());
        }
        return comboEvtStat_;
    }

    private void initItemsBoostingStat() {
        DataBase data_ = getDataBase();
        itemsBoostingStat = listTrStringsIt(itemsBoostingStatInit(data_),getFacade());
    }
    static StringList itemsBoostingStatInit(DataBase _db) {
        StringList itemsBoostingStat_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && (!((ItemForBattle) it_).getMultStatRank().isEmpty() || !((ItemForBattle) it_).getMultStatPokemonRank().isEmpty())) {
                itemsBoostingStat_.add(i);
            }
        }
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getMultStat().isEmpty()) {
                itemsBoostingStat_.add(i);
            }
        }
        return itemsBoostingStat_;
    }

    private void initSuccessEffectsElements() {
        DataBase data_ = getDataBase();
        initItemsCancelImmu();
        movesProtectingTypes = listTrStringsMv(data_.getMovesEffectProt(),getFacade());
        movesUnprotectingTypes = listTrStringsMv(data_.getMovesEffectUnprot(),getFacade());
        initMovesBrImmu();
        initImmuTypes();
        initAbilitiesImmu();
        initItImmu();
        abilitiesImmuSecEffOther = listTrStringsAb(abilitiesImmuSecEffOtherInit(data_),getFacade());
        abilitiesImmuSecEffOwner = listTrStringsAb(abilitiesImmuSecEffOwnerInit(data_),getFacade());
        abilitiesAchieveTarget = listTrStringsAb(abilitiesAchieveTargetInit(data_),getFacade());
        abilitiesBreakProtectMoves = listTrStringsAb(abilitiesBreakProtectMovesInit(data_),getFacade());
        movesProtecting = listTrStringsMv(initProt(data_),getFacade());
    }
    private static StringList initProt(DataBase _db) {
        StringList movesProtecting_ = new StringList();
        movesProtecting_.addAllElts(_db.getMovesProtAgainstDamageMoves());
        movesProtecting_.addAllElts(_db.getMovesProtAgainstStatusMoves());
        movesProtecting_.addAllElts(_db.getMovesProtAgainstMultiTarget());
        movesProtecting_.addAllElts(_db.getMovesProtAgainstPrio());
        movesProtecting_.addAllElts(_db.getMovesProtSingleTarget());
        movesProtecting_.removeDuplicates();
        return movesProtecting_;
    }
    static StringList abilitiesImmuSecEffOtherInit(DataBase _db) {
        StringList abilitiesImmuSecEffOther_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isCancelSecEffectOther()) {
                abilitiesImmuSecEffOther_.add(a);
            }
        }
        return abilitiesImmuSecEffOther_;
    }
    static StringList abilitiesImmuSecEffOwnerInit(DataBase _db) {
        StringList abilitiesImmuSecEffOwner_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isCancelSecEffectOwner()) {
                abilitiesImmuSecEffOwner_.add(a);
            }
        }
        return abilitiesImmuSecEffOwner_;
    }

    static StringList abilitiesAchieveTargetInit(DataBase _db) {
        StringList abilitiesAchieveTarget_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isAchievedDisappearedPk()) {
                abilitiesAchieveTarget_.add(a);
            }
        }
        return abilitiesAchieveTarget_;
    }
    static StringList abilitiesBreakProtectMovesInit(DataBase _db) {
        StringList abilitiesBreakProtectMoves_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isBreakProtection()) {
                abilitiesBreakProtectMoves_.add(a);
            }
        }
        return abilitiesBreakProtectMoves_;
    }

    private void initItemsCancelImmu() {
        DataBase data_ = getDataBase();
        itemsCancelImmu = listTrStringsIt(itemsCancelImmuInit(data_),getFacade());
    }
    static StringList itemsCancelImmuInit(DataBase _db) {
        StringList itemsCancelImmu_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && ((ItemForBattle) it_).getCancelImmuType()) {
                itemsCancelImmu_.add(i);
            }
        }
        return itemsCancelImmu_;
    }

    private void initAbilitiesImmu() {
        DataBase data_ = getDataBase();
        abilitiesImmuAllies = listTrStringsAb(abilitiesImmuAlliesInit(data_),getFacade());
        abilitiesImmuAlliesDam = listTrStringsAb(abilitiesImmuAlliesDamInit(data_),getFacade());
        abilitiesImmu = listTrStringsAb(abilitiesImmuInit(data_),getFacade());
    }
    static StringList abilitiesImmuAlliesInit(DataBase _db) {
        StringList abilitiesImmuAllies_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getImmuAllyFromMoves().isEmpty()) {
                abilitiesImmuAllies_.add(a);
            }
        }
        return abilitiesImmuAllies_;
    }
    static StringList abilitiesImmuAlliesDamInit(DataBase _db) {
        StringList abilitiesImmuAlliesDam_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isImmuDamageAllyMoves()) {
                abilitiesImmuAlliesDam_.add(a);
            }
        }
        return abilitiesImmuAlliesDam_;
    }
    static StringList abilitiesImmuInit(DataBase _db) {
        StringList abilitiesImmu_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getImmuMove().isEmpty() || ab_.isImmuSufferedDamageLowEff()) {
                abilitiesImmu_.add(a);
            }
        }
        return abilitiesImmu_;
    }

    private void initItImmu() {
        DataBase data_ = getDataBase();
        itemsImmu = listTrStringsIt(itemsImmuInit(data_),getFacade());
    }
    static StringList itemsImmuInit(DataBase _db) {
        StringList itemsImmu_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getImmuMoves().isEmpty()) {
                itemsImmu_.add(i);
            }
        }
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getHealHpBySuperEffMove().isZero()) {
                itemsImmu_.add(i);
            }
        }
        return itemsImmu_;
    }

    private void initImmuTypes() {
        DataBase data_ = getDataBase();
        abilitiesImmuTypes = listTrStringsAb(abilitiesImmuTypesInit(data_),getFacade());
        itemsImmuTypes = listTrStringsIt(itemsImmuTypesInit(data_),getFacade());
    }
    static StringList abilitiesImmuTypesInit(DataBase _db) {
        StringList abilitiesImmuTypes_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getImmuMoveTypesByWeather().isEmpty()) {
                abilitiesImmuTypes_.add(a);
            }
        }
        return abilitiesImmuTypes_;
    }
    static StringList itemsImmuTypesInit(DataBase _db) {
        StringList itemsImmuTypes_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getImmuTypes().isEmpty()) {
                itemsImmuTypes_.add(i);
            }
        }
        return itemsImmuTypes_;
    }

    private void initMovesBrImmu() {
        DataBase data_ = getDataBase();
        movesGlobalBreakImmu = listTrStringsMv(movesGlobalBreakImmuInit(data_),getFacade());
        movesGlobalBreakImmuAb = listTrStringsMv(movesGlobalBreakImmuAbInit(data_),getFacade());
        abilitiesBreakable = listTrStringsAb(abilitiesBreakableInit(WithFilterBean.keys(movesGlobalBreakImmuAb),data_),getFacade());
    }
    static StringList movesGlobalBreakImmuInit(DataBase _db) {
        StringList movesGlobalBreakImmu_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getEfficiencyMoves().isEmpty()) {
                    movesGlobalBreakImmu_.add(m);
                    break;
                }
            }
        }
        return movesGlobalBreakImmu_;
    }
    static StringList movesGlobalBreakImmuAbInit(DataBase _db) {
        StringList movesGlobalBreakImmuAb_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getCancelProtectingAbilities().isEmpty()) {
                    movesGlobalBreakImmuAb_.add(m);
                    break;
                }
            }
        }
        return movesGlobalBreakImmuAb_;
    }
    static StringList abilitiesBreakableInit(StringList _moves,DataBase _db) {
        StringList abilitiesBreakable_ = new StringList();
        for (String m: _moves) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal) {
                    abilitiesBreakable_.addAllElts(((EffectGlobal) e).getCancelProtectingAbilities());
                }
            }
        }
        abilitiesBreakable_.removeDuplicates();
        return abilitiesBreakable_;
    }

    private void initDamageCalculationElements() {
        DataBase data_ = getDataBase();
        initMovesUserAllyDamage();
        abilitiesTargetDamage = listTrStringsAb(abilitiesTargetDamageInit(data_),getFacade());
        initMovesTargetTeamDamage();
        abilitiesUserIgnTargetTeam = listTrStringsAb(abilitiesUserIgnTargetTeamInit(data_),getFacade());
        abilitiesGlobal = listTrStringsAb(abilitiesGlobalInit(data_),getFacade());
        initMovesGlobal();
        initDamageDefElement();
        initAbilitiesUserDamage();
        movesIgn();
    }
    static StringList abilitiesTargetDamageInit(DataBase _db) {
        StringList abilitiesTargetDamage_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultDamageFoe().isEmpty() || !ab_.getMultSufferedDamageSuperEff().isZero()) {
                abilitiesTargetDamage_.add(a);
            }
        }
        return abilitiesTargetDamage_;
    }
    static StringList abilitiesUserIgnTargetTeamInit(DataBase _db) {
        StringList abilitiesUserIgnTargetTeam_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getIgnFoeTeamMove().isEmpty()) {
                abilitiesUserIgnTargetTeam_.add(a);
            }
        }
        return abilitiesUserIgnTargetTeam_;
    }
    static StringList abilitiesGlobalInit(DataBase _db) {
        StringList abilitiesGlobal_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultPowerMovesTypesGlobal().isEmpty() || ab_.isReverseEffectsPowerMovesTypesGlobal()) {
                abilitiesGlobal_.add(a);
            }
        }
        return abilitiesGlobal_;
    }
    private void initDamageDefElement() {
        DataBase data_ = getDataBase();
        itemsUserDamage = listTrStringsIt(itemsUserDamageInit(data_),getFacade());
        abilitiesUserDamage = listTrStringsAb(abilitiesUserDamageInit(data_),getFacade());
        initMovesInvokDamage();
        itemsTargetDamage = listTrStringsIt(itemsTargetDamageInit(data_),getFacade());
        initMovesGlobalPrepaDamage();
        statusDamage = listTrStringsSt(statusDamageInit(data_),getFacade());
    }
    static StringList itemsUserDamageInit(DataBase _db) {
        StringList itemsUserDamage_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getMultDamage().isEmpty()) {
                itemsUserDamage_.add(i);
            }
        }
        return itemsUserDamage_;
    }
    static StringList abilitiesUserDamageInit(DataBase _db) {
        StringList abilitiesUserDamage_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultDamage().isEmpty()) {
                abilitiesUserDamage_.add(a);
            }
        }
        return abilitiesUserDamage_;
    }
    static StringList itemsTargetDamageInit(DataBase _db) {
        StringList itemsTargetDamage_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getMultFoesDamage().isEmpty()) {
                itemsTargetDamage_.add(i);
            }
        }
        return itemsTargetDamage_;
    }
    static StringList statusDamageInit(DataBase _db) {
        StringList statusDamage_ = new StringList();
        for (String s: _db.getStatus().getKeys()) {
            Status st_ = _db.getStatus(s);
            if (st_.getStatusType() != StatusType.INDIVIDUEL && st_.estActifPartenaire() && st_.getEffectsPartner().first().getWeddingAlly()) {
                statusDamage_.add(s);
            }
        }
        return statusDamage_;
    }

    private void initMovesUserAllyDamage() {
        DataBase data_ = getDataBase();
        movesUserAllyDamage = listTrStringsMv(movesUserAllyDamageInit(data_),getFacade());
    }
    static StringList movesUserAllyDamageInit(DataBase _db) {
        StringList movesUserAllyDamage_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectAlly && !((EffectAlly) e).getMultAllyDamage().isZero()) {
                    movesUserAllyDamage_.add(m);
                    break;
                }
            }
        }
        return movesUserAllyDamage_;
    }

    private void initMovesTargetTeamDamage() {
        DataBase data_ = getDataBase();
        movesTargetTeamDamage = listTrStringsMv(movesTargetTeamDamageInit(data_),getFacade());
    }
    static StringList movesTargetTeamDamageInit(DataBase _db) {
        StringList movesTargetTeamDamage_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectTeam && !((EffectTeam) e).getMultDamage().isEmpty()) {
                    movesTargetTeamDamage_.add(m);
                    break;
                }
            }
        }
        return movesTargetTeamDamage_;
    }

    private void initMovesGlobal() {
        DataBase data_ = getDataBase();
        movesGlobal = listTrStringsMv(movesGlobalInit(data_),getFacade());
    }
    static StringList movesGlobalInit(DataBase _db) {
        StringList movesGlobal_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && (!((EffectGlobal) e).getMultPowerMoves().isEmpty() || !((EffectGlobal) e).getMultDamageTypesMoves().isEmpty())) {
                    movesGlobal_.add(m);
                    break;
                }
            }
        }
        return movesGlobal_;
    }

    private void initMovesInvokDamage() {
        DataBase data_ = getDataBase();
        movesInvokDamage = listTrStringsMv(movesInvokDamageInit(data_),getFacade());
    }
    static StringList movesInvokDamageInit(DataBase _db) {
        StringList movesInvokDamage_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectInvoke && !((EffectInvoke) e).getRateInvokationMove().isZero()) {
                    movesInvokDamage_.add(m);
                    break;
                }
            }
        }
        return movesInvokDamage_;
    }

    private void initMovesGlobalPrepaDamage() {
        DataBase data_ = getDataBase();
        movesGlobalPrepaDamage = listTrStringsMv(movesGlobalPrepaDamageInit(data_),getFacade());
    }
    static StringList movesGlobalPrepaDamageInit(DataBase _db) {
        StringList movesGlobalPrepaDamage_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getMovesUsedByTargetedFighters().isEmpty() && !((EffectGlobal) e).getMultDamagePrepaRound().isEmpty()) {
                    movesGlobalPrepaDamage_.add(m);
                    break;
                }
            }
        }
        return movesGlobalPrepaDamage_;
    }

    private void initAbilitiesUserDamage() {
        DataBase data_ = getDataBase();
        abilitiesUserTargetDamage = listTrStringsAb(abilitiesUserTargetDamageInit(data_),getFacade());
        abilitiesUserStabDamage = listTrStringsAb(abilitiesUserStabDamageInit(data_),getFacade());
    }
    static StringList abilitiesUserTargetDamageInit(DataBase _db) {
        StringList abilitiesUserTargetDamage_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultAllyDamage().isZero()) {
                abilitiesUserTargetDamage_.add(a);
            }
        }
        return abilitiesUserTargetDamage_;
    }
    static StringList abilitiesUserStabDamageInit(DataBase _db) {
        StringList abilitiesUserStabDamage_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultStab().isZero()) {
                abilitiesUserStabDamage_.add(a);
            }
        }
        return abilitiesUserStabDamage_;
    }

    private void movesIgn() {
        DataBase data_ = getDataBase();
        movesIgnLowAtt = listTrStringsMv(movesIgnLowAttInit(data_),getFacade());
        movesIgnIncDef = listTrStringsMv(movesIgnIncDefInit(data_),getFacade());
    }
    static StringList movesIgnLowAttInit(DataBase _db) {
        StringList movesIgnLowAtt_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectDamage && !((EffectDamage) e).getIgnVarStatUserNeg().isEmpty()) {
                    movesIgnLowAtt_.add(m);
                    break;
                }
            }
        }
        return movesIgnLowAtt_;
    }
    static StringList movesIgnIncDefInit(DataBase _db) {
        StringList movesIgnIncDef_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectDamage && !((EffectDamage) e).getIgnVarStatTargetPos().isEmpty()) {
                    movesIgnIncDef_.add(m);
                    break;
                }
            }
        }
        return movesIgnIncDef_;
    }

    private void initPowerElements() {
        DataBase data_ = getDataBase();
        damagingMoves = listTrStringsMv(damagingMovesInit(data_),getFacade());
        itemsUserPower = listTrStringsIt(itemsUserPowerInit(data_),getFacade());
        initMovePowerUserTarget();
        abilitiesUserPower = listTrStringsAb(abilitiesUserPowerInit(data_),getFacade());
    }
    static StringList damagingMovesInit(DataBase _db) {
        StringList damagingMoves_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectDamage) {
                    damagingMoves_.add(m);
                }
            }
        }
        return damagingMoves_;
    }
    static StringList itemsUserPowerInit(DataBase _db) {
        StringList itemsUserPower_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getMultPower().isEmpty()) {
                itemsUserPower_.add(i);
            }
        }
        return itemsUserPower_;
    }
    static StringList abilitiesUserPowerInit(DataBase _db) {
        StringList abilitiesUserPower_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getChangingBoostTypes().isEmpty()) {
                abilitiesUserPower_.add(a);
            }
        }
        return abilitiesUserPower_;
    }

    private void initMovePowerUserTarget() {
        DataBase data_ = getDataBase();
        movesUserPower = listTrStringsMv(movesUserPowerInit(data_),getFacade());
        movesTargetPower = listTrStringsMv(movesTargetPowerInit(data_),getFacade());
    }
    static StringList movesUserPowerInit(DataBase _db) {
        StringList movesUserPower_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectMultUsedMovePower) {
                    movesUserPower_.add(m);
                    break;
                }
            }
        }
        return movesUserPower_;
    }
    static StringList movesTargetPowerInit(DataBase _db) {
        StringList movesTargetPower_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectMultSufferedMovePower) {
                    movesTargetPower_.add(m);
                    break;
                }
            }
        }
        return movesTargetPower_;
    }

    private void initWhileDamageElements() {
        DataBase data_ = getDataBase();
        movesProtAgainstKo = listTrStringsMv(data_.getMovesProtSingleTargetAgainstKo(),getFacade());
        initItemsProtAgainstKo();
        movesCannotKo = listTrStringsMv(movesCannotKoInit(data_),getFacade());
        minHpNotKo = data_.getMinHp();
        itemsAbs = listTrStringsIt(itemsAbsInit(data_),getFacade());
        initWhileDamageAbilities();
        initRecoilMembers();
        abilitiesKoTarget = listTrStringsAb(abilitiesKoTargetInit(data_),getFacade());
        initMovesKoTarget();
    }
    static StringList movesCannotKoInit(DataBase _db) {
        StringList movesCannotKo_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (move_ instanceof DamagingMoveData && ((DamagingMoveData) move_).getCannotKo()) {
                movesCannotKo_.add(m);
            }
        }
        return movesCannotKo_;
    }
    static StringList itemsAbsInit(DataBase _db) {
        StringList itemsAbs_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getDrainedHpByDamageRate().isZero()) {
                itemsAbs_.add(i);
            }
        }
        return itemsAbs_;
    }
    static StringList abilitiesKoTargetInit(DataBase _db) {
        StringList abilitiesKoTarget_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultStatIfKoFoe().isEmpty()) {
                abilitiesKoTarget_.add(a);
            }
        }
        return abilitiesKoTarget_;
    }

    private void initMovesKoTarget() {
        DataBase data_ = getDataBase();
        movesKoTarget = listTrStringsMv(movesKoTargetInit(data_),getFacade());
    }
    static StringList movesKoTargetInit(DataBase _db) {
        StringList movesKoTarget_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectDamage && !((EffectDamage) e).getBoostStatisOnceKoFoe().isEmpty()) {
                    movesKoTarget_.add(m);
                }
            }
        }
        return movesKoTarget_;
    }

    private void initItemsProtAgainstKo() {
        DataBase data_ = getDataBase();
        itemsProtAgainstKo = listTrStringsIt(itemsProtAgainstKoInit(data_),getFacade());
    }
    static StringList itemsProtAgainstKoInit(DataBase _db) {
        StringList itemsProtAgainstKo_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && (!((ItemForBattle) it_).getProtectAgainstKo().isZero() || !((ItemForBattle) it_).getProtectAgainstKoIfFullHp().isZero())) {
                itemsProtAgainstKo_.add(i);
            }
        }
        return itemsProtAgainstKo_;
    }

    private void initWhileDamageAbilities() {
        DataBase data_ = getDataBase();
        abilitiesRevAbs = listTrStringsAb(abilitiesRevAbsInit(data_),getFacade());
        initAbilitiesDamageStatis();
        initAbilitiesChangingTypesDamage();
        abilitiesTakingItem = listTrStringsAb(abilitiesTakingItemInit(data_),getFacade());
        abilitiesStatisVarUser = listTrStringsAb(abilitiesStatisVarUserInit(data_),getFacade());
        abilitiesStatus = listTrStringsAb(abilitiesStatusInit(data_),getFacade());
        abilitiesCopyAb = listTrStringsAb(abilitiesCopyAbInit(data_),getFacade());
    }
    static StringList abilitiesRevAbsInit(DataBase _db) {
        StringList abilitiesRevAbs_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isInflictingDamageInsteadOfSuffering()) {
                abilitiesRevAbs_.add(a);
            }
        }
        return abilitiesRevAbs_;
    }
    static StringList abilitiesTakingItemInit(DataBase _db) {
        StringList abilitiesTakingItem_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isTakeItemByDamagingMove()) {
                abilitiesTakingItem_.add(a);
            }
        }
        return abilitiesTakingItem_;
    }
    static StringList abilitiesStatisVarUserInit(DataBase _db) {
        StringList abilitiesStatisVarUser_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getLowStatFoeHit().isEmpty()) {
                abilitiesStatisVarUser_.add(a);
            }
        }
        return abilitiesStatisVarUser_;
    }
    static StringList abilitiesStatusInit(DataBase _db) {
        StringList abilitiesStatus_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getSingleStatus().isEmpty()) {
                abilitiesStatus_.add(a);
            }
        }
        return abilitiesStatus_;
    }
    static StringList abilitiesCopyAbInit(DataBase _db) {
        StringList abilitiesCopyAb_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isMumy()) {
                abilitiesCopyAb_.add(a);
            }
        }
        return abilitiesCopyAb_;
    }

    private void initAbilitiesChangingTypesDamage() {
        DataBase data_ = getDataBase();
        abilitiesChangingTypesDamage = listTrStringsAb(abilitiesChangingTypesDamageInit(data_),getFacade());
    }
    static StringList abilitiesChangingTypesDamageInit(DataBase _db) {
        StringList abilitiesChangingTypesDamage_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isChgtTypeByDamage()) {
                abilitiesChangingTypesDamage_.add(a);
            }
        }
        return abilitiesChangingTypesDamage_;
    }

    private void initAbilitiesDamageStatis() {
        DataBase data_ = getDataBase();
        abilitiesDamageStatis = listTrStringsAb(abilitiesDamageStatisInit(data_),getFacade());
    }
    static StringList abilitiesDamageStatisInit(DataBase _db) {
        StringList abilitiesDamageStatis_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMaxStatisticsIfCh().isEmpty() || !ab_.getMultStatIfDamgeType().isEmpty() || !ab_.getMultStatIfDamageCat().isEmpty()) {
                abilitiesDamageStatis_.add(a);
            }
        }
        return abilitiesDamageStatis_;
    }

    private void initEndRoundUserMembers() {
        DataBase data_ = getDataBase();
        abilitiesEndRound = listTrStringsAb(abilitiesEndRoundInit(data_),getFacade());
        berryEndRound = listTrStringsIt(berryEndRoundInit(data_),getFacade());
        movesChangingAttOrder = listTrStringsMv(movesChangingAttOrderInit(data_),getFacade());
    }
    static StringList abilitiesEndRoundInit(DataBase _db) {
        StringList abilitiesEndRound_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getBoostStatRankEndRound().isEmpty()) {
                abilitiesEndRound_.add(a);
            }
        }
        return abilitiesEndRound_;
    }
    static StringList berryEndRoundInit(DataBase _db) {
        StringList berryEndRound_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof Berry && ((Berry) it_).getHealPp() > 0) {
                berryEndRound_.add(i);
            }
        }
        return berryEndRound_;
    }
    static StringList movesChangingAttOrderInit(DataBase _db) {
        StringList movesChangingAttOrder_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectOrder) {
                    movesChangingAttOrder_.add(m);
                }
            }
        }
        return movesChangingAttOrder_;
    }

    private void initBerryEndEffectMembers() {
        DataBase data_ = getDataBase();
        berryUser = listTrStringsIt(berryUserInit(data_),getFacade());
        berryTarget = listTrStringsIt(berryTargetInit(data_),getFacade());
    }
    static StringList berryUserInit(DataBase _db) {
        StringList berryUser_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof Berry && (!((Berry) it_).getHealHp().isZero() || !((Berry) it_).getHealHpRate().isZero())) {
                berryUser_.add(i);
            }
        }
        return berryUser_;
    }
    static StringList berryTargetInit(DataBase _db) {
        StringList berryTarget_ = berryUserInit(_db);
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getCategoryBoosting().isEmpty()) {
                berryTarget_.add(i);
            }
        }
        berryTarget_.removeDuplicates();
        return berryTarget_;
    }

    private void initRecoilMembers() {
        DataBase data_ = getDataBase();
        recoilItems = listTrStringsIt(recoilItemsInit(data_),getFacade());
        recoilAbilities = listTrStringsAb(recoilAbilitiesInit(data_),getFacade());
    }
    static StringList recoilItemsInit(DataBase _db) {
        StringList recoilItems_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getDamageRecoil().isZero()) {
                recoilItems_.add(i);
            }
        }
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getDamageRateRecoilFoe().isEmpty()) {
                recoilItems_.add(i);
            }
        }
        return recoilItems_;
    }
    static StringList recoilAbilitiesInit(DataBase _db) {
        StringList recoilAbilities_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getRecoilDamageFoe().isZero()) {
                recoilAbilities_.add(a);
            }
        }
        return recoilAbilities_;
    }

    private void initStatisticsImmuElements() {
        DataBase data_ = getDataBase();
        abilitiesFighterStatis = listTrStringsAb(abilitiesFighterStatisInit(data_),getFacade());
        itemsFighterStatis = listTrStringsIt(itemsFighterStatisInit(data_),getFacade());
        abilitiesFighterStatisVar = listTrStringsAb(abilitiesFighterStatisVarInit(data_),getFacade());
    }
    static StringList abilitiesFighterStatisInit(DataBase _db) {
        StringList abilitiesFighterStatis_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getImmuLowStat().isEmpty() || !ab_.getImmuLowStatIfStatus().isEmpty()) {
                abilitiesFighterStatis_.add(a);
            }
        }
        return abilitiesFighterStatis_;
    }
    static StringList itemsFighterStatisInit(DataBase _db) {
        StringList itemsFighterStatis_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && ((ItemForBattle) it_).getImmuLowStatis()) {
                itemsFighterStatis_.add(i);
            }
        }
        return itemsFighterStatis_;
    }
    static StringList abilitiesFighterStatisVarInit(DataBase _db) {
        StringList abilitiesFighterStatisVar_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getMultVarBoost().isZero() || !ab_.getMultStatIfLowStat().isEmpty()) {
                abilitiesFighterStatisVar_.add(a);
            }
        }
        return abilitiesFighterStatisVar_;
    }

    private void initProtectingMembers() {
        DataBase data_ = getDataBase();
        protectItems = listTrStringsIt(protectItemsInit(data_),getFacade());
        protectAbilities = listTrStringsAb(protectAbilitiesInit(data_),getFacade());
        protectMoves = listTrStringsMv(data_.getMovesCountering(),getFacade());
    }
    static StringList protectItemsInit(DataBase _db) {
        StringList protectItems_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getHealHpBySuperEffMove().isZero()) {
                protectItems_.add(i);
            }
        }
        return protectItems_;
    }
    static StringList protectAbilitiesInit(DataBase _db) {
        StringList protectAbilities_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getBoostStatRankProtected().isEmpty() || !ab_.getHealHpByTypeIfWeather().isEmpty()) {
                protectAbilities_.add(a);
            }
        }
        return protectAbilities_;
    }

    private void initInvokingMembers() {
        DataBase data_ = getDataBase();
        movesInvoking = listTrStringsMv(data_.getMovesInvoking(),getFacade());
        movesThieving = listTrStringsMv(movesThievingInit(data_),getFacade());
        movesAttracting = listTrStringsMv(movesAttractingInit(data_),getFacade());
        movesMirror = listTrStringsMv(movesMirrorInit(data_),getFacade());
    }
    static StringList movesThievingInit(DataBase _db) {
        StringList movesThieving_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectSwitchPointView)) {
                    continue;
                }
                EffectSwitchPointView eff_ = (EffectSwitchPointView) e;
                if (eff_.getPointViewChangement() == PointViewChangementType.THIEF_BONUSES) {
                    movesThieving_.add(m);
                }
            }
        }
        movesThieving_.removeDuplicates();
        return movesThieving_;
    }
    static StringList movesAttractingInit(DataBase _db) {
        StringList movesThieving_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectSwitchPointView)) {
                    continue;
                }
                EffectSwitchPointView eff_ = (EffectSwitchPointView) e;
                if (eff_.getPointViewChangement() == PointViewChangementType.ATTRACT_DAMAGES_MOVES) {
                    movesThieving_.add(m);
                }
            }
        }
        movesThieving_.removeDuplicates();
        return movesThieving_;
    }
    static StringList movesMirrorInit(DataBase _db) {
        StringList movesThieving_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectSwitchPointView)) {
                    continue;
                }
                EffectSwitchPointView eff_ = (EffectSwitchPointView) e;
                if (eff_.getPointViewChangement() == PointViewChangementType.MIRROR_AGAINST_THROWER) {
                    movesThieving_.add(m);
                }
            }
        }
        movesThieving_.removeDuplicates();
        return movesThieving_;
    }

    private void initBeginRoundPreparingMembers() {
        DataBase data_ = getDataBase();
        prepaRoundMoves = listTrStringsMv(prepaRoundMovesInit(data_),getFacade());
        disappearingRoundMoves = listTrStringsMv(disappearingRoundMovesInit(data_),getFacade());
        rechargeMoves = listTrStringsMv(rechargeMovesInit(data_),getFacade());
        speedPreparingItems = listTrStringsIt(speedPreparingItemsInit(data_),getFacade());
        immuRecharging = listTrStringsAb(immuRechargingInit(data_),getFacade());
    }
    static StringList prepaRoundMovesInit(DataBase _db) {
        StringList prepaRoundMoves_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (move_.getNbPrepaRound() <= 0) {
                continue;
            }
            prepaRoundMoves_.add(m);
        }
        return prepaRoundMoves_;
    }
    static StringList disappearingRoundMovesInit(DataBase _db) {
        StringList disappearingRoundMoves_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (move_.getNbPrepaRound() <= 0) {
                continue;
            }
            if (move_.getDisappearBeforeUse()) {
                disappearingRoundMoves_.add(m);
            }
        }
        return disappearingRoundMoves_;
    }
    static StringList rechargeMovesInit(DataBase _db) {
        StringList rechargeMoves_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (move_.getRechargeRound()) {
                rechargeMoves_.add(m);
            }
        }
        return rechargeMoves_;
    }
    static StringList speedPreparingItemsInit(DataBase _db) {
        StringList speedPreparingItems_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && ((ItemForBattle) it_).getAttacksSoon()) {
                speedPreparingItems_.add(i);
            }
        }
        return speedPreparingItems_;
    }
    static StringList immuRechargingInit(DataBase _db) {
        StringList immuRecharging_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isImmuRechargeRound()) {
                immuRecharging_.add(a);
            }
        }
        return immuRecharging_;
    }

    private void initBeginRoundStatusMembers() {
        DataBase data_ = getDataBase();
        beginRoundStatus = listTrStringsSt(beginRoundStatusInit(data_),getFacade());
        deleteStatusMove = listTrStringsMv(deleteStatusMoveInit(data_),getFacade());
        immuStatusAbility = listTrStringsAb(immuStatusAbilityInit(data_),getFacade());
        autoDamage = autoDamageInit(getFacade());
        damgeFormula = data_.getFormula(data_.getDamageFormula(), getLanguage());
        mapVar = new NatStringTreeMap<String>();
        mapVar.putAllMap(data_.getDescriptions(data_.getDamageFormula(), getLanguage()));
        mapAutoDamage = mapAutoDamageInit(data_,getLanguage(), autoDamage);
    }

    static NatStringTreeMap<String> mapAutoDamageInit(DataBase _data, String _lg, AbsMap<TranslatedKey, StatusBeginRoundAutoDamage> _a) {
        NatStringTreeMap<String> mapAutoDamage_ = new NatStringTreeMap<String>();
        int len_;
        len_ = _a.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            mapAutoDamage_.putAllMap(_data.getDescriptions(numString(_data, _a.getValue(i)), _lg));
        }
        return mapAutoDamage_;
    }

    static StringList beginRoundStatusInit(DataBase _db) {
        StringList beginRoundStatus_ = new StringList();
        for (String s: _db.getStatus().getKeys()) {
            Status st_ = _db.getStatus(s);
            if (st_.getStatusType() != StatusType.RELATION_UNIQUE && st_ instanceof StatusBeginRound) {
                beginRoundStatus_.add(s);
            }
        }
        return beginRoundStatus_;
    }
    static StringList deleteStatusMoveInit(DataBase _db) {
        StringList deleteStatusMove_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            if (!move_.getDeletedStatus().isEmpty()) {
                deleteStatusMove_.add(m);
            }
        }
        return deleteStatusMove_;
    }
    static StringList immuStatusAbilityInit(DataBase _db) {
        StringList immuStatusAbility_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getImmuStatusBeginRound().isEmpty()) {
                immuStatusAbility_.add(a);
            }
        }
        return immuStatusAbility_;
    }
    static AbsMap<TranslatedKey,StatusBeginRoundAutoDamage> autoDamageInit(FacadeGame _db) {
        AbsMap<TranslatedKey,StatusBeginRoundAutoDamage> autoDamage_ = DictionaryComparatorUtil.buildStatusAutoData();
        for (String s: _db.getData().getStatus().getKeys()) {
            Status st_ = _db.getData().getStatus(s);
            if (st_ instanceof StatusBeginRoundAutoDamage) {
                autoDamage_.addEntry(buildSt(_db,s), (StatusBeginRoundAutoDamage) st_);
            }
        }
        return autoDamage_;
    }

    private void initSwitchingMembers() {
        DataBase data_ = getDataBase();
        abilitiesSwitch = listTrStringsAb(abilitiesSwitchInit(data_),getFacade());
        deletedStatusSwitch = listTrStringsSt(deletedStatusSwitchInit(data_),getFacade());
        entryHazard = listTrStringsMv(data_.getMovesEffectWhileSending(),getFacade());
    }
    static StringList abilitiesSwitchInit(DataBase _db) {
        StringList abilitiesSwitch_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getHealedHpRateBySwitch().isZero() || ab_.isHealedStatusBySwitch()) {
                abilitiesSwitch_.add(a);
            }
        }
        return abilitiesSwitch_;
    }
    static StringList deletedStatusSwitchInit(DataBase _db) {
        StringList deletedStatusSwitch_ = new StringList();
        for (String a: _db.getStatus().getKeys()) {
            Status ab_ = _db.getStatus(a);
            if (ab_.getDisabledEffIfSwitch()) {
                deletedStatusSwitch_.add(a);
            }
        }
        for (String s: _db.getStatus().getKeys()) {
            Status st_ = _db.getStatus(s);
            if (st_.getStatusType() != StatusType.INDIVIDUEL) {
                deletedStatusSwitch_.add(s);
            }
        }
        deletedStatusSwitch_.removeDuplicates();
        return deletedStatusSwitch_;
    }

    private void initSpeedElements() {
        DataBase data_ = getDataBase();
        abilitiesPrio = listTrStringsAb(abilitiesPrioInit(data_),getFacade());
        slowAbilities = listTrStringsAb(slowAbilitiesInit(data_),getFacade());
        slowItems = listTrStringsIt(slowItemsInit(data_),getFacade());
        initReverseSpeedMoves();
        initItSpeed();
    }
    static StringList abilitiesPrioInit(DataBase _db) {
        StringList abilitiesPrio_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.getIncreasedPrio().isEmpty() || !ab_.getIncreasedPrioTypes().isEmpty()) {
                abilitiesPrio_.add(a);
            }
        }
        return abilitiesPrio_;
    }
    static StringList slowAbilitiesInit(DataBase _db) {
        StringList slowAbilities_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isSlowing()) {
                slowAbilities_.add(a);
            }
        }
        return slowAbilities_;
    }
    static StringList slowItemsInit(DataBase _db) {
        StringList slowItems_ = new StringList();
        for (String a: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(a);
            if (it_ instanceof ItemForBattle && ((ItemForBattle) it_).getAttackLast()) {
                slowItems_.add(a);
            }
        }
        return slowItems_;
    }

    private void initItSpeed() {
        DataBase data_ = getDataBase();
        berrySpeed = listTrStringsIt(berrySpeedInit(data_),getFacade());
        itemSpeed = listTrStringsIt(itemSpeedInit(data_),getFacade());
    }
    static StringList berrySpeedInit(DataBase _db) {
        StringList berrySpeed_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof Berry && ((Berry) it_).getLawForAttackFirst()) {
                berrySpeed_.add(i);
            }
        }
        return berrySpeed_;
    }
    static StringList itemSpeedInit(DataBase _db) {
        StringList itemSpeed_ = new StringList();
        for (String i: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getLawForAttackFirst().isEmpty()) {
                itemSpeed_.add(i);
            }
        }
        return itemSpeed_;
    }

    private void initReverseSpeedMoves() {
        DataBase data_ = getDataBase();
        reverseSpeedMoves = listTrStringsMv(reverseSpeedMovesInit(data_),getFacade());
    }
    static StringList reverseSpeedMovesInit(DataBase _db) {
        StringList reverseSpeedMoves_ = new StringList();
        for (String m: _db.getMoves().getKeys()) {
            MoveData move_ = _db.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && ((EffectGlobal) e).getReverseOrderOfSortBySpeed()) {
                    reverseSpeedMoves_.add(m);
                    break;
                }
            }
        }
        return reverseSpeedMoves_;
    }

    private void initSendingMembers() {
        DataBase data_ = getDataBase();
        initSendingAbilities();
        initSendingItems();
        changingTypesAbilities = listTrStringsAb(changingTypesAbilitiesInit(data_),getFacade());
    }
    static StringList changingTypesAbilitiesInit(DataBase _db) {
        StringList changingTypesAbilities_ = new StringList();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (ab_.isPlate()) {
                changingTypesAbilities_.add(a);
            }
        }
        return changingTypesAbilities_;
    }

    private void initSendingItems() {
        DataBase data_ = getDataBase();
        itemsSentBeginWeather = listTrStringsIt(itemsSentBeginWeatherInit(data_),getFacade());
        itemsSentBeginOther = listTrStringsIt(itemsSentBeginOtherInit(data_),getFacade());
    }
    static StringList itemsSentBeginWeatherInit(DataBase _db) {
        StringList itemsSentBeginWeather_ = new StringList();
        for (String a: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(a);
            if (!(it_ instanceof ItemForBattle) || !((ItemForBattle) it_).enabledSending()) {
                continue;
            }
            if (!otherItBattle((ItemForBattle) it_)) {
                itemsSentBeginWeather_.add(a);
            }
        }
        return itemsSentBeginWeather_;
    }
    static StringList itemsSentBeginOtherInit(DataBase _db) {
        StringList itemsSentBeginOther_ = new StringList();
        for (String a: _db.getItems().getKeys()) {
            Item it_ = _db.getItem(a);
            if (!(it_ instanceof ItemForBattle) || !((ItemForBattle) it_).enabledSending()) {
                continue;
            }
            if (otherItBattle((ItemForBattle) it_)) {
                itemsSentBeginOther_.add(a);
            }
        }
        return itemsSentBeginOther_;
    }

    private static boolean otherItBattle(ItemForBattle _it) {
        return _it.getEffectSending().first().getEnabledWeather().isEmpty() && !_it.getEffectSending().first().getDisableWeather();
    }

    private void initSendingAbilities() {
        DataBase data_ = getDataBase();
        StringList abilitiesSentBeginWeather_ = new StringList();
        StringList abilitiesSentBeginOther_ = new StringList();
        StringList copyAbilities_ = new StringList();
        StringMap<AbilityData> s_ = abilitiesSentStatisInit(data_);
        feed(s_,copyAbilities_,abilitiesSentBeginOther_,abilitiesSentBeginWeather_);
        abilitiesSentBeginWeather = listTrStringsAb(abilitiesSentBeginWeather_,getFacade());
        abilitiesSentBeginOther = listTrStringsAb(abilitiesSentBeginOther_,getFacade());
        copyAbilities = listTrStringsAb(copyAbilities_,getFacade());
        abilitiesSentStatis = listTrStringsAb(s_.getKeys(),getFacade());
    }
    static StringMap<AbilityData> abilitiesSentStatisInit(DataBase _db) {
        StringMap<AbilityData> abilitiesSentStatis_ = new StringMap<AbilityData>();
        for (String a: _db.getAbilities().getKeys()) {
            AbilityData ab_ = _db.getAbility(a);
            if (!ab_.enabledSending()) {
                continue;
            }
            abilitiesSentStatis_.addEntry(a,ab_);
        }
        return abilitiesSentStatis_;
    }
    static void feed(StringMap<AbilityData> _s, StringList _copyAbilities, StringList _abilitiesSentBeginOther, StringList _abilitiesSentBeginWeather) {
        for (EntryCust<String, AbilityData> a: _s.entryList()) {
            AbilityData ab_ = a.getValue();
//            if (ab_.getEffectSending().first() instanceof EffectWhileSendingWithStatistic) {
//                abilitiesSentStatis.add(a);
//                continue;
//            }
            if (ab_.getEffectSending().first().getCopyingAbility()) {
                _copyAbilities.add(a.getKey());
            } else if (ab_.getEffectSending().first().getEnabledWeather().isEmpty() && !ab_.getEffectSending().first().getDisableWeather()) {
                _abilitiesSentBeginOther.add(a.getKey());
            } else {
                _abilitiesSentBeginWeather.add(a.getKey());
            }
        }
    }

    private void initBoosts(long _minBoost, long _maxBoost) {
        boosts.addAllEntries(boostsInit(_minBoost,_maxBoost,getDataBase()));
        boostsCh.addAllEntries(boostsChInit(_minBoost,_maxBoost,getDataBase()));
    }
    static LongTreeMap<Rate> boostsInit(long _minBoost, long _maxBoost, DataBase _db) {
        String pref_ = _db.prefixBoost();
        LongTreeMap<Rate> boosts_ = new LongTreeMap<Rate>();
        for (long b = _minBoost; b <= _maxBoost; b++) {
            String rateBoost_ = _db.getRateBoost();
//            NumericString chNum_=new NumericString(rateBoost_);
            StringMap<String> variables_ = new StringMap<String>();
            variables_.put(pref_, Long.toString(b));
//            chNum_.replaceVars(variables_);
//            chNum_.evaluateExp();
//            Rate res_ = chNum_.toRate();
//            boosts.put(b, res_);
            boosts_.addEntry(b, _db.evaluateNumericable(rateBoost_, variables_, Rate.one()));
        }
        return boosts_;
    }
    static LongTreeMap<Rate> boostsChInit(long _minBoost, long _maxBoost, DataBase _db) {
        String pref_ = _db.prefixBoost();
        LongTreeMap<Rate> boostsCh_ = new LongTreeMap<Rate>();
        for (long b = _minBoost; b <= _maxBoost; b++) {
            String rateBoost_ = _db.getRateBoostCriticalHit();
//            NumericString chNum_=new NumericString(rateBoost_);
            StringMap<String> variables_ = new StringMap<String>();
            variables_.put(pref_, Long.toString(b));
//            chNum_.replaceVars(variables_);
//            chNum_.evaluateExp();
//            Rate res_ = chNum_.toRate();
//            boostsCh.put(b, res_);
            boostsCh_.addEntry(b, _db.evaluateNumericable(rateBoost_, variables_, Rate.one()));
        }
        return boostsCh_;
    }

    private void initFormulaElements() {
        DataBase data_ = getDataBase();

        String catchingFormulaCopy_ = data_.getRateCatching();
        catchingFormula = data_.getFormula(catchingFormulaCopy_, getLanguage());
        varCatchingFormula = new NatStringTreeMap<String>();
        varCatchingFormula.putAllMap(data_.getDescriptions(catchingFormulaCopy_, getLanguage()));

        fleeingFormula = data_.getFormula(data_.getRateFleeing(), getLanguage());
        varFleeingFormula = new NatStringTreeMap<String>();

        varFleeingFormula.putAllMap(data_.getDescriptions(data_.getRateFleeing(), getLanguage()));
        rates = ratesInit(data_,getLanguage());
        varRates = varRatesInit(data_,getLanguage());
        lawsRates = lawRatesInit(data_);
        statisticAnim = Statistic.getStatisticsWithBoost();
    }
    static StringMap<String> ratesInit(DataBase _db, String _lg) {
        StringMap<String> rates_ = new StringMap<String>();
        for (DifficultyWinPointsFight d: _db.getRates().getKeys()) {
            rates_.put(d.getWinName(), _db.getFormula(_db.getRates().getVal(d), _lg));
        }
        return rates_;
    }
    static NatStringTreeMap<String> varRatesInit(DataBase _db, String _lg) {
        NatStringTreeMap<String> rates_ = new NatStringTreeMap<String>();
        for (DifficultyWinPointsFight d: _db.getRates().getKeys()) {
            rates_.putAllMap(_db.getDescriptions(_db.getRates().getVal(d), _lg));
        }
        return rates_;
    }
    static StringMap<AbsBasicTreeMap<Rate, Rate>> lawRatesInit(DataBase _db) {
        StringMap<AbsBasicTreeMap<Rate, Rate>> lawsRates_ = new StringMap<AbsBasicTreeMap<Rate, Rate>>();
        for (DifficultyModelLaw d: _db.getLawsDamageRate().getKeys()) {
            DictionaryComparator<Rate,Rate> tree_ = DictionaryComparatorUtil.buildRateRate();
            MonteCarloNumber law_ = _db.getLawsDamageRate().getVal(d).getLaw();
            for (Rate e: law_.eventsDiff()) {
                tree_.put(e, law_.normalizedRate(e));
            }
            lawsRates_.put(d.getModelName(), tree_);
        }
        return lawsRates_;
    }

    public String getTrStatistic(int _index) {
        Statistic d_ = statisticAnim.get(_index);
        DataBase data_ = getDataBase();
        return data_.getTranslatedStatistics().getVal(getLanguage()).getVal(d_);
    }
    public int[][] getAnimStatistic(int _index) {
        Statistic d_ = statisticAnim.get(_index);
        DataBase data_ = getDataBase();
        return data_.getAnimStatis().getVal(d_.getStatName()).getImage();
    }
    public int[][] getAnimAbsorb() {
        DataBase data_ = getDataBase();
        return data_.getAnimAbsorb().getImage();
    }
    public String getTrLawRate(int _index) {
        DifficultyModelLaw d_ = PokemonStandards.getModelByName(lawsRates.getKey(_index));
        DataBase data_ = getDataBase();
        return data_.getTranslatedDiffModelLaw().getVal(getLanguage()).getVal(d_);
    }
    public String getTrDifficulty(int _index) {
        DifficultyWinPointsFight diff_ = PokemonStandards.getDiffWonPtsByName(rates.getKey(_index));
        DataBase data_ = getDataBase();
//        return XmlParser.transformSpecialChars(data_.getTranslatedDiffWinPts().getVal(getLanguage()).getVal(diff_));
        return data_.getTranslatedDiffWinPts().getVal(getLanguage()).getVal(diff_);
    }
    public String getStab() {
        DataBase data_ = getDataBase();
        return data_.getStab().toNumberString();
    }
    public String getFomula(int _index) {
        DataBase data_ = getDataBase();
        return data_.getFormula(numString(getDataBase(), autoDamage.getValue(_index)), getLanguage());
    }

    private static String numString(DataBase _data, StatusBeginRoundAutoDamage _st) {
        String str_ = _data.getDamageFormula();
        StringMap<String> replace_ = new StringMap<String>();
        replace_.put(_data.prefixPower(), _st.getPower().toNumberString());
        str_ = MathExpUtil.replaceWordsJoin(str_, replace_);
        return str_;
    }

    private static boolean hasNormalStat(Listable<Statistic> _list) {
        for (Statistic s: _list) {
            if (isNormalStat(s)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNormalStat(Statistic _st) {
        if (_st == Statistic.ATTACK) {
            return true;
        }
        if (_st == Statistic.SPECIAL_ATTACK) {
            return true;
        }
        if (_st == Statistic.DEFENSE) {
            return true;
        }
        return _st == Statistic.SPECIAL_DEFENSE;
    }
    public String getTrAbilitiesSentBegin(int _index) {
        return abilitiesSentBeginWeather.get(_index).getTranslation();
    }
    public String clickAbilitiesSentBegin(int _index) {
        return tryRedirect(abilitiesSentBeginWeather.get(_index));
    }
    public String getTrAbilitiesSentBeginOth(int _index) {
        return abilitiesSentBeginOther.get(_index).getTranslation();
    }
    public String clickAbilitiesSentBeginOth(int _index) {
        return tryRedirect(abilitiesSentBeginOther.get(_index));
    }
    public String getTrItemsSentBegin(int _index) {
        return itemsSentBeginWeather.get(_index).getTranslation();
    }
    public String clickItemsSentBegin(int _index) {
        return tryRedirect(itemsSentBeginWeather.get(_index));
    }
    public String getTrItemsSentBeginOth(int _index) {
        return itemsSentBeginOther.get(_index).getTranslation();
    }
    public String clickItemsSentBeginOth(int _index) {
        return tryRedirect(itemsSentBeginOther.get(_index));
    }
    public String getTrSlowItems(int _index) {
        return slowItems.get(_index).getTranslation();
    }
    public String clickSlowItems(int _index) {
        return tryRedirect(slowItems.get(_index));
    }
    public String getTrItemSpeed(int _index) {
        return itemSpeed.get(_index).getTranslation();
    }
    public String clickItemSpeed(int _index) {
        return tryRedirect(itemSpeed.get(_index));
    }
    public String getTrSpeedPreparingItems(int _index) {
        return speedPreparingItems.get(_index).getTranslation();
    }
    public String clickSpeedPreparingItems(int _index) {
        return tryRedirect(speedPreparingItems.get(_index));
    }
    public String getTrProtectItems(int _index) {
        return protectItems.get(_index).getTranslation();
    }
    public String clickProtectItems(int _index) {
        return tryRedirect(protectItems.get(_index));
    }
    public String getTrItemsFighterStatis(int _index) {
        return itemsFighterStatis.get(_index).getTranslation();
    }
    public String clickItemsFighterStatis(int _index) {
        return tryRedirect(itemsFighterStatis.get(_index));
    }
    public String getTrItemsFighterStatus(int _index) {
        return itemsFighterStatus.get(_index).getTranslation();
    }
    public String clickItemsFighterStatus(int _index) {
        return tryRedirect(itemsFighterStatus.get(_index));
    }
    public String getTrItemsProtAgainstKo(int _index) {
        return itemsProtAgainstKo.get(_index).getTranslation();
    }
    public String clickItemsProtAgainstKo(int _index) {
        return tryRedirect(itemsProtAgainstKo.get(_index));
    }
    public String getTrItemsAbs(int _index) {
        return itemsAbs.get(_index).getTranslation();
    }
    public String clickItemsAbs(int _index) {
        return tryRedirect(itemsAbs.get(_index));
    }
    public String getTrRecoilItems(int _index) {
        return recoilItems.get(_index).getTranslation();
    }
    public String clickRecoilItems(int _index) {
        return tryRedirect(recoilItems.get(_index));
    }
    public String getTrBerryUser(int _index) {
        return berryUser.get(_index).getTranslation();
    }
    public String clickBerryUser(int _index) {
        return tryRedirect(berryUser.get(_index));
    }
    public String getTrBerryTarget(int _index) {
        return berryTarget.get(_index).getTranslation();
    }
    public String clickBerryTarget(int _index) {
        return tryRedirect(berryTarget.get(_index));
    }
    public String getTrBerryEndRound(int _index) {
        return berryEndRound.get(_index).getTranslation();
    }
    public String clickBerryEndRound(int _index) {
        return tryRedirect(berryEndRound.get(_index));
    }
    public String getTrItemsUserPower(int _index) {
        return itemsUserPower.get(_index).getTranslation();
    }
    public String clickItemsUserPower(int _index) {
        return tryRedirect(itemsUserPower.get(_index));
    }
    public String getTrItemsUserDamage(int _index) {
        return itemsUserDamage.get(_index).getTranslation();
    }
    public String clickItemsUserDamage(int _index) {
        return tryRedirect(itemsUserDamage.get(_index));
    }
    public String getTrItemsTargetDamage(int _index) {
        return itemsTargetDamage.get(_index).getTranslation();
    }
    public String clickItemsTargetDamage(int _index) {
        return tryRedirect(itemsTargetDamage.get(_index));
    }
    public String getTrItemsCancelImmu(int _index) {
        return itemsCancelImmu.get(_index).getTranslation();
    }
    public String clickItemsCancelImmu(int _index) {
        return tryRedirect(itemsCancelImmu.get(_index));
    }
    public String getTrItemsImmuTypes(int _index) {
        return itemsImmuTypes.get(_index).getTranslation();
    }
    public String clickItemsImmuTypes(int _index) {
        return tryRedirect(itemsImmuTypes.get(_index));
    }
    public String getTrItemsImmu(int _index) {
        return itemsImmu.get(_index).getTranslation();
    }
    public String clickItemsImmu(int _index) {
        return tryRedirect(itemsImmu.get(_index));
    }
    public boolean itemBoostNormalAny() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (itemBoostNormal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostNormal(int _index) {
        String ab_ = itemsBoostingStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        if (data_.getItem(ab_) instanceof Berry) {
            Berry b_ = (Berry) data_.getItem(ab_);
            return hasNormalStat(b_.getMultStat().getKeys());
        }
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (hasNormalStat(i_.getMultStatRank().getKeys())) {
            return true;
        }
        for (StatisticPokemon s: i_.getMultStatPokemonRank().getKeys()) {
            if (isNormalStat(s.getStatistic())) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostSpeedAny() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (itemBoostSpeed(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostSpeed(int _index) {
        String ab_ = itemsBoostingStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        if (data_.getItem(ab_) instanceof Berry) {
            Berry b_ = (Berry) data_.getItem(ab_);
            return b_.getMultStat().contains(Statistic.SPEED);
        }
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (i_.getMultStatRank().contains(Statistic.SPEED)) {
            return true;
        }
        for (StatisticPokemon s: i_.getMultStatPokemonRank().getKeys()) {
            if (s.getStatistic() == Statistic.SPEED) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostChAny() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (itemBoostCh(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostCh(int _index) {
        String ab_ = itemsBoostingStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        if (data_.getItem(ab_) instanceof Berry) {
            Berry b_ = (Berry) data_.getItem(ab_);
            return b_.getMultStat().contains(Statistic.CRITICAL_HIT);
        }
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (i_.getMultStatRank().contains(Statistic.CRITICAL_HIT)) {
            return true;
        }
        for (StatisticPokemon s: i_.getMultStatPokemonRank().getKeys()) {
            if (s.getStatistic() == Statistic.CRITICAL_HIT) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostEvasinessAny() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (itemBoostEvasiness(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostEvasiness(int _index) {
        String ab_ = itemsBoostingStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        if (data_.getItem(ab_) instanceof Berry) {
            Berry b_ = (Berry) data_.getItem(ab_);
            return b_.getMultStat().contains(Statistic.EVASINESS);
        }
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (i_.getMultStatRank().contains(Statistic.EVASINESS)) {
            return true;
        }
        for (StatisticPokemon s: i_.getMultStatPokemonRank().getKeys()) {
            if (s.getStatistic() == Statistic.EVASINESS) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostAccuracyAny() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (itemBoostAccuracy(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostAccuracy(int _index) {
        String ab_ = itemsBoostingStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        if (data_.getItem(ab_) instanceof Berry) {
            Berry b_ = (Berry) data_.getItem(ab_);
            return b_.getMultStat().contains(Statistic.ACCURACY);
        }
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (i_.getMultStatRank().contains(Statistic.ACCURACY)) {
            return true;
        }
        for (StatisticPokemon s: i_.getMultStatPokemonRank().getKeys()) {
            if (s.getStatistic() == Statistic.ACCURACY) {
                return true;
            }
        }
        return false;
    }
    public String getTrItemsBoostingStat(int _index) {
        return itemsBoostingStat.get(_index).getTranslation();
    }
    public String clickItemsBoostingStat(int _index) {
        return tryRedirect(itemsBoostingStat.get(_index));
    }
    public boolean itemMultNormalAny() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (itemMultNormal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemMultNormal(int _index) {
        String ab_ = itemsMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        return hasNormalStat(i_.getMultStat().getKeys());
    }
    public boolean itemMultSpeedAny() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (itemMultSpeed(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemMultSpeed(int _index) {
        String ab_ = itemsMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        return i_.getMultStat().contains(Statistic.SPEED);
    }
    public boolean itemMultEvasinessAny() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (itemMultEvasiness(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemMultEvasiness(int _index) {
        String ab_ = itemsMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        return i_.getMultStat().contains(Statistic.EVASINESS);
    }
    public boolean itemMultAccuracyAny() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (itemMultAccuracy(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemMultAccuracy(int _index) {
        String ab_ = itemsMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        return i_.getMultStat().contains(Statistic.ACCURACY);
    }
    public String getTrItemsMultStat(int _index) {
        return itemsMultStat.get(_index).getTranslation();
    }
    public String clickItemsMultStat(int _index) {
        return tryRedirect(itemsMultStat.get(_index));
    }
    public String getTrBerrySpeed(int _index) {
        return berrySpeed.get(_index).getTranslation();
    }
    public String clickBerrySpeed(int _index) {
        return tryRedirect(berrySpeed.get(_index));
    }
    public String getTrChangingTypesAbilities(int _index) {
        return changingTypesAbilities.get(_index).getTranslation();
    }
    public String clickChangingTypesAbilities(int _index) {
        return tryRedirect(changingTypesAbilities.get(_index));
    }
    public String getTrAbilitiesSentStatis(int _index) {
        return abilitiesSentStatis.get(_index).getTranslation();
    }
    public String clickAbilitiesSentStatis(int _index) {
        return tryRedirect(abilitiesSentStatis.get(_index));
    }
    public String getTrCopyAbilities(int _index) {
        return copyAbilities.get(_index).getTranslation();
    }
    public String clickCopyAbilities(int _index) {
        return tryRedirect(copyAbilities.get(_index));
    }
    public String getTrAbilitiesPrio(int _index) {
        return abilitiesPrio.get(_index).getTranslation();
    }
    public String clickAbilitiesPrio(int _index) {
        return tryRedirect(abilitiesPrio.get(_index));
    }
    public String getTrSlowAbilities(int _index) {
        return slowAbilities.get(_index).getTranslation();
    }
    public String clickSlowAbilities(int _index) {
        return tryRedirect(slowAbilities.get(_index));
    }
    public String getTrAbilitiesSwitch(int _index) {
        return abilitiesSwitch.get(_index).getTranslation();
    }
    public String clickAbilitiesSwitch(int _index) {
        return tryRedirect(abilitiesSwitch.get(_index));
    }
    public String getTrImmuStatusAbility(int _index) {
        return immuStatusAbility.get(_index).getTranslation();
    }
    public String clickImmuStatusAbility(int _index) {
        return tryRedirect(immuStatusAbility.get(_index));
    }
    public String getTrImmuRecharging(int _index) {
        return immuRecharging.get(_index).getTranslation();
    }
    public String clickImmuRecharging(int _index) {
        return tryRedirect(immuRecharging.get(_index));
    }
    public String getTrCopyMoveTypesAb(int _index) {
        return copyMoveTypesAb.get(_index).getTranslation();
    }
    public String clickCopyMoveTypesAb(int _index) {
        return tryRedirect(copyMoveTypesAb.get(_index));
    }
    public String getTrPressureAbilities(int _index) {
        return pressureAbilities.get(_index).getTranslation();
    }
    public String clickPressureAbilities(int _index) {
        return tryRedirect(pressureAbilities.get(_index));
    }
    public String getTrProtectAbilities(int _index) {
        return protectAbilities.get(_index).getTranslation();
    }
    public String clickProtectAbilities(int _index) {
        return tryRedirect(protectAbilities.get(_index));
    }
    public String getTrAbilitiesPartStatis(int _index) {
        return abilitiesPartStatis.get(_index).getTranslation();
    }
    public String clickAbilitiesPartStatis(int _index) {
        return tryRedirect(abilitiesPartStatis.get(_index));
    }
    public String getTrAbilitiesRateStatis(int _index) {
        return abilitiesRateStatis.get(_index).getTranslation();
    }
    public String clickAbilitiesRateStatis(int _index) {
        return tryRedirect(abilitiesRateStatis.get(_index));
    }
    public String getTrAbilitiesFighterStatis(int _index) {
        return abilitiesFighterStatis.get(_index).getTranslation();
    }
    public String clickAbilitiesFighterStatis(int _index) {
        return tryRedirect(abilitiesFighterStatis.get(_index));
    }
    public String getTrAbilitiesFighterStatisVar(int _index) {
        return abilitiesFighterStatisVar.get(_index).getTranslation();
    }
    public String clickAbilitiesFighterStatisVar(int _index) {
        return tryRedirect(abilitiesFighterStatisVar.get(_index));
    }
    public String getTrAbilitiesPartStatus(int _index) {
        return abilitiesPartStatus.get(_index).getTranslation();
    }
    public String clickAbilitiesPartStatus(int _index) {
        return tryRedirect(abilitiesPartStatus.get(_index));
    }
    public String getTrAbilitiesFighterStatus(int _index) {
        return abilitiesFighterStatus.get(_index).getTranslation();
    }
    public String clickAbilitiesFighterStatus(int _index) {
        return tryRedirect(abilitiesFighterStatus.get(_index));
    }
    public String getTrAbilitiesRevAbs(int _index) {
        return abilitiesRevAbs.get(_index).getTranslation();
    }
    public String clickAbilitiesRevAbs(int _index) {
        return tryRedirect(abilitiesRevAbs.get(_index));
    }
    public String getTrAbilitiesDamageStatis(int _index) {
        return abilitiesDamageStatis.get(_index).getTranslation();
    }
    public String clickAbilitiesDamageStatis(int _index) {
        return tryRedirect(abilitiesDamageStatis.get(_index));
    }
    public String getTrAbilitiesChangingTypesDamage(int _index) {
        return abilitiesChangingTypesDamage.get(_index).getTranslation();
    }
    public String clickAbilitiesChangingTypesDamage(int _index) {
        return tryRedirect(abilitiesChangingTypesDamage.get(_index));
    }
    public String getTrAbilitiesTakingItem(int _index) {
        return abilitiesTakingItem.get(_index).getTranslation();
    }
    public String clickAbilitiesTakingItem(int _index) {
        return tryRedirect(abilitiesTakingItem.get(_index));
    }
    public String getTrAbilitiesStatisVarUser(int _index) {
        return abilitiesStatisVarUser.get(_index).getTranslation();
    }
    public String clickAbilitiesStatisVarUser(int _index) {
        return tryRedirect(abilitiesStatisVarUser.get(_index));
    }
    public String getTrAbilitiesStatus(int _index) {
        return abilitiesStatus.get(_index).getTranslation();
    }
    public String clickAbilitiesStatus(int _index) {
        return tryRedirect(abilitiesStatus.get(_index));
    }
    public String getTrAbilitiesCopyAb(int _index) {
        return abilitiesCopyAb.get(_index).getTranslation();
    }
    public String clickAbilitiesCopyAb(int _index) {
        return tryRedirect(abilitiesCopyAb.get(_index));
    }
    public String getTrRecoilAbilities(int _index) {
        return recoilAbilities.get(_index).getTranslation();
    }
    public String clickRecoilAbilities(int _index) {
        return tryRedirect(recoilAbilities.get(_index));
    }
    public String getTrAbilitiesKoTarget(int _index) {
        return abilitiesKoTarget.get(_index).getTranslation();
    }
    public String clickAbilitiesKoTarget(int _index) {
        return tryRedirect(abilitiesKoTarget.get(_index));
    }
    public String getTrAbilitiesEndRound(int _index) {
        return abilitiesEndRound.get(_index).getTranslation();
    }
    public String clickAbilitiesEndRound(int _index) {
        return tryRedirect(abilitiesEndRound.get(_index));
    }
    public String getTrAbilitiesUserPower(int _index) {
        return abilitiesUserPower.get(_index).getTranslation();
    }
    public String clickAbilitiesUserPower(int _index) {
        return tryRedirect(abilitiesUserPower.get(_index));
    }
    public String getTrAbilitiesTargetDamage(int _index) {
        return abilitiesTargetDamage.get(_index).getTranslation();
    }
    public String clickAbilitiesTargetDamage(int _index) {
        return tryRedirect(abilitiesTargetDamage.get(_index));
    }
    public String getTrAbilitiesGlobal(int _index) {
        return abilitiesGlobal.get(_index).getTranslation();
    }
    public String clickAbilitiesGlobal(int _index) {
        return tryRedirect(abilitiesGlobal.get(_index));
    }
    public String getTrAbilitiesUserDamage(int _index) {
        return abilitiesUserDamage.get(_index).getTranslation();
    }
    public String clickAbilitiesUserDamage(int _index) {
        return tryRedirect(abilitiesUserDamage.get(_index));
    }
    public String getTrAbilitiesUserTargetDamage(int _index) {
        return abilitiesUserTargetDamage.get(_index).getTranslation();
    }
    public String clickAbilitiesUserTargetDamage(int _index) {
        return tryRedirect(abilitiesUserTargetDamage.get(_index));
    }
    public String getTrAbilitiesUserStabDamage(int _index) {
        return abilitiesUserStabDamage.get(_index).getTranslation();
    }
    public String clickAbilitiesUserStabDamage(int _index) {
        return tryRedirect(abilitiesUserStabDamage.get(_index));
    }
    public String getTrAbilitiesUserIgnTargetTeam(int _index) {
        return abilitiesUserIgnTargetTeam.get(_index).getTranslation();
    }
    public String clickAbilitiesUserIgnTargetTeam(int _index) {
        return tryRedirect(abilitiesUserIgnTargetTeam.get(_index));
    }
    public String getTrAbilitiesBreakable(int _index) {
        return abilitiesBreakable.get(_index).getTranslation();
    }
    public String clickAbilitiesBreakable(int _index) {
        return tryRedirect(abilitiesBreakable.get(_index));
    }
    public String getTrAbilitiesImmuTypes(int _index) {
        return abilitiesImmuTypes.get(_index).getTranslation();
    }
    public String clickAbilitiesImmuTypes(int _index) {
        return tryRedirect(abilitiesImmuTypes.get(_index));
    }
    public String getTrAbilitiesImmuAllies(int _index) {
        return abilitiesImmuAllies.get(_index).getTranslation();
    }
    public String clickAbilitiesImmuAllies(int _index) {
        return tryRedirect(abilitiesImmuAllies.get(_index));
    }
    public String getTrAbilitiesImmuAlliesDam(int _index) {
        return abilitiesImmuAlliesDam.get(_index).getTranslation();
    }
    public String clickAbilitiesImmuAlliesDam(int _index) {
        return tryRedirect(abilitiesImmuAlliesDam.get(_index));
    }
    public String getTrAbilitiesImmu(int _index) {
        return abilitiesImmu.get(_index).getTranslation();
    }
    public String clickAbilitiesImmu(int _index) {
        return tryRedirect(abilitiesImmu.get(_index));
    }
    public String getTrAbilitiesImmuSecEffOther(int _index) {
        return abilitiesImmuSecEffOther.get(_index).getTranslation();
    }
    public String clickAbilitiesImmuSecEffOther(int _index) {
        return tryRedirect(abilitiesImmuSecEffOther.get(_index));
    }
    public String getTrAbilitiesImmuSecEffOwner(int _index) {
        return abilitiesImmuSecEffOwner.get(_index).getTranslation();
    }
    public String clickAbilitiesImmuSecEffOwner(int _index) {
        return tryRedirect(abilitiesImmuSecEffOwner.get(_index));
    }
    public String getTrAbilitiesAchieveTarget(int _index) {
        return abilitiesAchieveTarget.get(_index).getTranslation();
    }
    public String clickAbilitiesAchieveTarget(int _index) {
        return tryRedirect(abilitiesAchieveTarget.get(_index));
    }
    public String getTrAbilitiesBreakProtectMoves(int _index) {
        return abilitiesBreakProtectMoves.get(_index).getTranslation();
    }
    public String clickAbilitiesBreakProtectMoves(int _index) {
        return tryRedirect(abilitiesBreakProtectMoves.get(_index));
    }
    public boolean abilityBoostNormalAny() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostNormal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityBoostNormal(int _index) {
        String ab_ = abilitiesBoostingStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return hasNormalStat(a_.getBonusStatRank().getKeys());
    }
    public boolean abilityBoostSpeedAny() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostSpeed(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityBoostSpeed(int _index) {
        String ab_ = abilitiesBoostingStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.SPEED);
    }
    public boolean abilityBoostChAny() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostCh(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityBoostCh(int _index) {
        String ab_ = abilitiesBoostingStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.CRITICAL_HIT);
    }
    public boolean abilityBoostEvasinessAny() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostEvasiness(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityBoostEvasiness(int _index) {
        String ab_ = abilitiesBoostingStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.EVASINESS);
    }
    public boolean abilityBoostAccuracyAny() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostAccuracy(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityBoostAccuracy(int _index) {
        String ab_ = abilitiesBoostingStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.ACCURACY);
    }
    public String getTrAbilitiesBoostingStat(int _index) {
        return abilitiesBoostingStat.get(_index).getTranslation();
    }
    public String clickAbilitiesBoostingStat(int _index) {
        return tryRedirect(abilitiesBoostingStat.get(_index));
    }
    public boolean abilityMultNormalAny() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityMultNormal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityMultNormal(int _index) {
        String ab_ = abilitiesMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticCategory s: a_.getMultStatIfCat().getKeys()) {
            if (isNormalStat(s.getStatistic())) {
                return true;
            }
        }
        return hasNormalStat(a_.getMultStat().getKeys());
    }
    public boolean abilityMultSpeedAny() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityMultSpeed(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityMultSpeed(int _index) {
        String ab_ = abilitiesMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticCategory s: a_.getMultStatIfCat().getKeys()) {
            if (s.getStatistic() == Statistic.SPEED) {
                return true;
            }
        }
        return a_.getMultStat().contains(Statistic.SPEED);
    }
    public boolean abilityMultEvasinessAny() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityMultEvasiness(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityMultEvasiness(int _index) {
        String ab_ = abilitiesMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticCategory s: a_.getMultStatIfCat().getKeys()) {
            if (s.getStatistic() == Statistic.EVASINESS) {
                return true;
            }
        }
        return a_.getMultStat().contains(Statistic.EVASINESS);
    }
    public boolean abilityMultAccuracyAny() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityMultAccuracy(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityMultAccuracy(int _index) {
        String ab_ = abilitiesMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticCategory s: a_.getMultStatIfCat().getKeys()) {
            if (s.getStatistic() == Statistic.ACCURACY) {
                return true;
            }
        }
        return a_.getMultStat().contains(Statistic.ACCURACY);
    }
    public String getTrAbilitiesMultStat(int _index) {
        return abilitiesMultStat.get(_index).getTranslation();
    }
    public String clickAbilitiesMultStat(int _index) {
        return tryRedirect(abilitiesMultStat.get(_index));
    }
    public boolean abilityAllyMultNormalAny() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultNormal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityAllyMultNormal(int _index) {
        String ab_ = abilitiesAllyMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return hasNormalStat(a_.getMultStatAlly().getKeys());
    }
    public boolean abilityAllyMultEvasinessAny() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultEvasiness(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityAllyMultEvasiness(int _index) {
        String ab_ = abilitiesAllyMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getMultStatAlly().contains(Statistic.EVASINESS);
    }
    public boolean abilityAllyMultSpeedAny() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultSpeed(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityAllyMultSpeed(int _index) {
        String ab_ = abilitiesAllyMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getMultStatAlly().contains(Statistic.SPEED);
    }
    public boolean abilityAllyMultAccuracyAny() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultAccuracy(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityAllyMultAccuracy(int _index) {
        String ab_ = abilitiesAllyMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getMultStatAlly().contains(Statistic.ACCURACY);
    }
    public String getTrAbilitiesAllyMultStat(int _index) {
        return abilitiesAllyMultStat.get(_index).getTranslation();
    }
    public String clickAbilitiesAllyMultStat(int _index) {
        return tryRedirect(abilitiesAllyMultStat.get(_index));
    }
    public boolean abilityImmuMultNormalAny() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultNormal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityImmuMultNormal(int _index) {
        String ab_ = abilitiesImmuMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticStatus s: a_.getImmuLowStatIfStatus()) {
            if (isNormalStat(s.getStatistic())) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityImmuMultEvasinessAny() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultEvasiness(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityImmuMultEvasiness(int _index) {
        String ab_ = abilitiesImmuMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticStatus s: a_.getImmuLowStatIfStatus()) {
            if (s.getStatistic() == Statistic.EVASINESS) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityImmuMultSpeedAny() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultSpeed(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityImmuMultSpeed(int _index) {
        String ab_ = abilitiesImmuMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticStatus s: a_.getImmuLowStatIfStatus()) {
            if (s.getStatistic() == Statistic.SPEED) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityImmuMultAccuracyAny() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultAccuracy(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityImmuMultAccuracy(int _index) {
        String ab_ = abilitiesImmuMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticStatus s: a_.getImmuLowStatIfStatus()) {
            if (s.getStatistic() == Statistic.ACCURACY) {
                return true;
            }
        }
        return false;
    }
    public String getTrAbilitiesImmuMultStat(int _index) {
        return abilitiesImmuMultStat.get(_index).getTranslation();
    }
    public String clickAbilitiesImmuMultStat(int _index) {
        return tryRedirect(abilitiesImmuMultStat.get(_index));
    }
    public String getTrAbilitiesTypeDefMoves(int _index) {
        return abilitiesTypeDefMoves.get(_index).getTranslation();
    }
    public String clickAbilitiesTypeDefMoves(int _index) {
        return tryRedirect(abilitiesTypeDefMoves.get(_index));
    }
    public String getTrAbilitiesChangeTypeMoves(int _index) {
        return abilitiesChangeTypeMoves.get(_index).getTranslation();
    }
    public String clickAbilitiesChangeTypeMoves(int _index) {
        return tryRedirect(abilitiesChangeTypeMoves.get(_index));
    }
    public String getTrAbilitiesBreakImmu(int _index) {
        return abilitiesBreakImmu.get(_index).getTranslation();
    }
    public String clickAbilitiesBreakImmu(int _index) {
        return tryRedirect(abilitiesBreakImmu.get(_index));
    }
    public String getTrAbilitiesImmuCh(int _index) {
        return abilitiesImmuCh.get(_index).getTranslation();
    }
    public String clickAbilitiesImmuCh(int _index) {
        return tryRedirect(abilitiesImmuCh.get(_index));
    }
    public String getTrAbilitiesMultEvtCh(int _index) {
        return abilitesMultEvtCh.get(_index).getTranslation();
    }
    public String clickAbilitiesMultEvtCh(int _index) {
        return tryRedirect(abilitesMultEvtCh.get(_index));
    }
    public String getTrAbilitiesMultRateCh(int _index) {
        return abilitesMultRateCh.get(_index).getTranslation();
    }
    public String clickAbilitiesMultRateCh(int _index) {
        return tryRedirect(abilitesMultRateCh.get(_index));
    }
    public String getTrPrivatingMoves(int _index) {
        return privatingMoves.get(_index).getTranslation();
    }
    public String clickPrivatingMoves(int _index) {
        return tryRedirect(privatingMoves.get(_index));
    }
    public String getTrMovesHealingSubstitute(int _index) {
        return movesHealingSubstitute.get(_index).getTranslation();
    }
    public String clickMovesHealingSubstitute(int _index) {
        return tryRedirect(movesHealingSubstitute.get(_index));
    }
    public String getTrSubstitutingMoves(int _index) {
        return substitutingMoves.get(_index).getTranslation();
    }
    public String clickSubstitutingMoves(int _index) {
        return tryRedirect(substitutingMoves.get(_index));
    }
    public String getTrReverseSpeedMoves(int _index) {
        return reverseSpeedMoves.get(_index).getTranslation();
    }
    public String clickReverseSpeedMoves(int _index) {
        return tryRedirect(reverseSpeedMoves.get(_index));
    }
    public String getTrEntryHazard(int _index) {
        return entryHazard.get(_index).getTranslation();
    }
    public String clickEntryHazard(int _index) {
        return tryRedirect(entryHazard.get(_index));
    }
    public String getTrDeleteStatusMove(int _index) {
        return deleteStatusMove.get(_index).getTranslation();
    }
    public String clickDeleteStatusMove(int _index) {
        return tryRedirect(deleteStatusMove.get(_index));
    }
    public boolean isDisappearingUser(int _index) {
        DataBase data_ = getDataBase();
        String m_ = prepaRoundMoves.get(_index).getKey();
        MoveData move_ = data_.getMove(m_);
        return move_.getDisappearBeforeUse();
    }
    public String getTrPrepaRoundMoves(int _index) {
        return prepaRoundMoves.get(_index).getTranslation();
    }
    public String clickPrepaRoundMoves(int _index) {
        return tryRedirect(prepaRoundMoves.get(_index));
    }
    public String getTrRechargeMoves(int _index) {
        return rechargeMoves.get(_index).getTranslation();
    }
    public String clickRechargeMoves(int _index) {
        return tryRedirect(rechargeMoves.get(_index));
    }
    public String getTrMovesInvoking(int _index) {
        return movesInvoking.get(_index).getTranslation();
    }
    public String clickMovesInvoking(int _index) {
        return tryRedirect(movesInvoking.get(_index));
    }
    public String getTrMovesThieving(int _index) {
        return movesThieving.get(_index).getTranslation();
    }
    public String clickMovesThieving(int _index) {
        return tryRedirect(movesThieving.get(_index));
    }
    public String getTrMovesAttracting(int _index) {
        return movesAttracting.get(_index).getTranslation();
    }
    public String clickMovesAttracting(int _index) {
        return tryRedirect(movesAttracting.get(_index));
    }
    public String getTrMovesMirror(int _index) {
        return movesMirror.get(_index).getTranslation();
    }
    public String clickMovesMirror(int _index) {
        return tryRedirect(movesMirror.get(_index));
    }
    public String getTrMovesSecEffItems(int _index) {
        return movesSecEffItems.get(_index).getTranslation();
    }
    public String clickMovesSecEffItems(int _index) {
        return tryRedirect(movesSecEffItems.get(_index));
    }
    public String getTrProtectMoves(int _index) {
        return protectMoves.get(_index).getTranslation();
    }
    public String clickProtectMoves(int _index) {
        return tryRedirect(protectMoves.get(_index));
    }
    public String getTrEffMoves(int _index) {
        return effMoves.get(_index).getTranslation();
    }
    public String clickEffMoves(int _index) {
        return tryRedirect(effMoves.get(_index));
    }
    public boolean immuStatisTeamMoveAny() {
        int len_;
        len_ = movesTeam.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (immuStatisTeamMove(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean immuStatusTeamMoveAny() {
        int len_;
        len_ = movesTeam.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (immuStatusTeamMove(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean immuChTeamMoveAny() {
        int len_;
        len_ = movesTeam.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (immuChTeamMove(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean immuStatisTeamMove(int _index) {
        MoveData move_ = getStatisTeamMove(_index);
        for (Effect e: move_.getEffects()) {
            if (!(e instanceof EffectTeam)) {
                continue;
            }
            EffectTeam e_ = (EffectTeam) e;
            if (!e_.getProtectAgainstLowStat().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    public boolean immuStatusTeamMove(int _index) {
        MoveData move_ = getStatisTeamMove(_index);
        for (Effect e: move_.getEffects()) {
            if (!(e instanceof EffectTeam)) {
                continue;
            }
            EffectTeam e_ = (EffectTeam) e;
            if (!e_.getProtectAgainstStatus().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    public boolean immuChTeamMove(int _index) {
        MoveData move_ = getStatisTeamMove(_index);
        for (Effect e: move_.getEffects()) {
            if (!(e instanceof EffectTeam)) {
                continue;
            }
            EffectTeam e_ = (EffectTeam) e;
            if (e_.getProtectAgainstCh()) {
                return true;
            }
        }
        return false;
    }
    public MoveData getStatisTeamMove(int _index) {
        DataBase data_ = getDataBase();
        String m_ = movesTeam.get(_index).getKey();
        return data_.getMove(m_);
    }
    public String getTrMovesTeam(int _index) {
        return movesTeam.get(_index).getTranslation();
    }
    public String clickMovesTeam(int _index) {
        return tryRedirect(movesTeam.get(_index));
    }
    public String getTrGlobalMovesStatus(int _index) {
        return globalMovesStatus.get(_index).getTranslation();
    }
    public String clickGlobalMovesStatus(int _index) {
        return tryRedirect(globalMovesStatus.get(_index));
    }
    public String getTrMovesProtAgainstKo(int _index) {
        return movesProtAgainstKo.get(_index).getTranslation();
    }
    public String clickMovesProtAgainstKo(int _index) {
        return tryRedirect(movesProtAgainstKo.get(_index));
    }
    public String getTrMovesCannotKo(int _index) {
        return movesCannotKo.get(_index).getTranslation();
    }
    public String clickMovesCannotKo(int _index) {
        return tryRedirect(movesCannotKo.get(_index));
    }
    public String getTrMovesKoTarget(int _index) {
        return movesKoTarget.get(_index).getTranslation();
    }
    public String clickMovesKoTarget(int _index) {
        return tryRedirect(movesKoTarget.get(_index));
    }
    public boolean attackFirst() {
        int len_;
        len_ = movesChangingAttOrder.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (!attackLast(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean attackLastAny() {
        int len_;
        len_ = movesChangingAttOrder.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (attackLast(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean attackLast(int _index) {
        String m_ = movesChangingAttOrder.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData move_ = data_.getMove(m_);
        for(Effect e: move_.getEffects()){
            if(!(e instanceof EffectOrder)){
                continue;
            }
            EffectOrder effetOrdre_=(EffectOrder)e;
            if(!effetOrdre_.getTargetAttacksLast()){
                return false;
            }
        }
        return true;
    }
    public String getTrMovesChangingAttOrder(int _index) {
        return movesChangingAttOrder.get(_index).getTranslation();
    }
    public String clickMovesChangingAttOrder(int _index) {
        return tryRedirect(movesChangingAttOrder.get(_index));
    }
    public boolean withConstDamageAny() {
        int len_;
        len_ = damagingMoves.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (withConstDamage(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean withConstDamage(int _index) {
        DataBase data_ = getDataBase();
        MoveData move_ = data_.getMove(damagingMoves.get(_index).getKey());
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        return eff_.getConstDamage();
    }
    public boolean withRandDamageAny() {
        int len_;
        len_ = damagingMoves.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (withRandDamage(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean withRandDamage(int _index) {
        DataBase data_ = getDataBase();
        MoveData move_ = data_.getMove(damagingMoves.get(_index).getKey());
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        return !eff_.getDamageLaw().isEmpty();
    }
    public boolean withMultDamageAny() {
        int len_;
        len_ = damagingMoves.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (withMultDamage(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean withMultDamage(int _index) {
        DataBase data_ = getDataBase();
        MoveData move_ = data_.getMove(damagingMoves.get(_index).getKey());
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        return !eff_.getMultDamageAgainst().isEmpty();
    }
    public String getTrDamagingMoves(int _index) {
        return damagingMoves.get(_index).getTranslation();
    }
    public String clickDamagingMoves(int _index) {
        return tryRedirect(damagingMoves.get(_index));
    }
    public String getTrMovesUserPower(int _index) {
        return movesUserPower.get(_index).getTranslation();
    }
    public String clickMovesUserPower(int _index) {
        return tryRedirect(movesUserPower.get(_index));
    }
    public String getTrMovesTargetPower(int _index) {
        return movesTargetPower.get(_index).getTranslation();
    }
    public String clickMovesTargetPower(int _index) {
        return tryRedirect(movesTargetPower.get(_index));
    }
    public String getTrMovesTargetTeamDamage(int _index) {
        return movesTargetTeamDamage.get(_index).getTranslation();
    }
    public String clickMovesTargetTeamDamage(int _index) {
        return tryRedirect(movesTargetTeamDamage.get(_index));
    }
    public String getTrMovesGlobal(int _index) {
        return movesGlobal.get(_index).getTranslation();
    }
    public String clickMovesGlobal(int _index) {
        return tryRedirect(movesGlobal.get(_index));
    }
    public String getTrMovesInvokDamage(int _index) {
        return movesInvokDamage.get(_index).getTranslation();
    }
    public String clickMovesInvokDamage(int _index) {
        return tryRedirect(movesInvokDamage.get(_index));
    }
    public String getTrMovesGlobalPrepaDamage(int _index) {
        return movesGlobalPrepaDamage.get(_index).getTranslation();
    }
    public String clickMovesGlobalPrepaDamage(int _index) {
        return tryRedirect(movesGlobalPrepaDamage.get(_index));
    }
    public String getTrMovesUserAllyDamage(int _index) {
        return movesUserAllyDamage.get(_index).getTranslation();
    }
    public String clickMovesUserAllyaDamage(int _index) {
        return tryRedirect(movesUserAllyDamage.get(_index));
    }
    public String getTrMovesIgnLowAtt(int _index) {
        return movesIgnLowAtt.get(_index).getTranslation();
    }
    public String clickMovesIgnLowAtt(int _index) {
        return tryRedirect(movesIgnLowAtt.get(_index));
    }
    public String getTrMovesIgnIncDef(int _index) {
        return movesIgnIncDef.get(_index).getTranslation();
    }
    public String clickMovesIgnIncDef(int _index) {
        return tryRedirect(movesIgnIncDef.get(_index));
    }
    public String getTrMovesProtectingTypes(int _index) {
        return movesProtectingTypes.get(_index).getTranslation();
    }
    public String clickMovesProtectingTypes(int _index) {
        return tryRedirect(movesProtectingTypes.get(_index));
    }
    public String getTrMovesUnprotectingTypes(int _index) {
        return movesUnprotectingTypes.get(_index).getTranslation();
    }
    public String clickMovesUnprotectingTypes(int _index) {
        return tryRedirect(movesUnprotectingTypes.get(_index));
    }
    public String getTrMovesGlobalBreakImmu(int _index) {
        return movesGlobalBreakImmu.get(_index).getTranslation();
    }
    public String clickMovesGlobalBreakImmu(int _index) {
        return tryRedirect(movesGlobalBreakImmu.get(_index));
    }
    public String getTrMovesGlobalBreakImmuAb(int _index) {
        return movesGlobalBreakImmuAb.get(_index).getTranslation();
    }
    public String clickMovesGlobalBreakImmuAb(int _index) {
        return tryRedirect(movesGlobalBreakImmuAb.get(_index));
    }
    public String getTrMovesProtecting(int _index) {
        return movesProtecting.get(_index).getTranslation();
    }
    public String clickMovesProtecting(int _index) {
        return tryRedirect(movesProtecting.get(_index));
    }
    public String getTrMovesIgnAcc(int _index) {
        return movesIgnAcc.get(_index).getTranslation();
    }
    public String clickMovesIgnAcc(int _index) {
        return tryRedirect(movesIgnAcc.get(_index));
    }
    public String getTrMovesIgnEva(int _index) {
        return movesIgnEva.get(_index).getTranslation();
    }
    public String clickMovesIgnEva(int _index) {
        return tryRedirect(movesIgnEva.get(_index));
    }
    public String getTrMovesGlobalAcc(int _index) {
        return movesGlobalAcc.get(_index).getTranslation();
    }
    public String clickMovesGlobalAcc(int _index) {
        return tryRedirect(movesGlobalAcc.get(_index));
    }
    public boolean moveGlobalMultNormalAny() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultNormal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveGlobalMultNormal(int _index) {
        String move_ = movesGlobalMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectGlobal)) {
                continue;
            }
            EffectGlobal e_ = (EffectGlobal) e;
            for (StatisticType t: e_.getMultStatIfContainsType().getKeys()) {
                if (isNormalStat(t.getStatistic())) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean moveGlobalMultEvasinessAny() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultEvasiness(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveGlobalMultEvasiness(int _index) {
        String move_ = movesGlobalMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectGlobal)) {
                continue;
            }
            EffectGlobal e_ = (EffectGlobal) e;
            for (StatisticType t: e_.getMultStatIfContainsType().getKeys()) {
                if (t.getStatistic() == Statistic.EVASINESS) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean moveGlobalMultSpeedAny() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultSpeed(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveGlobalMultSpeed(int _index) {
        String move_ = movesGlobalMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectGlobal)) {
                continue;
            }
            EffectGlobal e_ = (EffectGlobal) e;
            for (StatisticType t: e_.getMultStatIfContainsType().getKeys()) {
                if (t.getStatistic() == Statistic.SPEED) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean moveGlobalMultAccuracyAny() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultAccuracy(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveGlobalMultAccuracy(int _index) {
        String move_ = movesGlobalMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectGlobal)) {
                continue;
            }
            EffectGlobal e_ = (EffectGlobal) e;
            for (StatisticType t: e_.getMultStatIfContainsType().getKeys()) {
                if (t.getStatistic() == Statistic.ACCURACY) {
                    return true;
                }
            }
        }
        return false;
    }
    public String getTrMovesGlobalMultStat(int _index) {
        return movesGlobalMultStat.get(_index).getTranslation();
    }
    public String clickMovesGlobalMultStat(int _index) {
        return tryRedirect(movesGlobalMultStat.get(_index));
    }
    public boolean moveTeamMultNormalAny() {
        int len_ = movesTeamMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultNormal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveTeamMultNormal(int _index) {
        String move_ = movesTeamMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectTeam)) {
                continue;
            }
            EffectTeam e_ = (EffectTeam) e;
            if (hasNormalStat(e_.getMultStatistic().getKeys())) {
                return true;
            }
        }
        return false;
    }
    public boolean moveTeamMultEvasinessAny() {
        int len_ = movesTeamMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultEvasiness(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveTeamMultEvasiness(int _index) {
        String move_ = movesTeamMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectTeam)) {
                continue;
            }
            EffectTeam e_ = (EffectTeam) e;
            if (e_.getMultStatistic().contains(Statistic.EVASINESS)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveTeamMultSpeedAny() {
        int len_ = movesTeamMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultSpeed(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveTeamMultSpeed(int _index) {
        String move_ = movesTeamMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectTeam)) {
                continue;
            }
            EffectTeam e_ = (EffectTeam) e;
            if (e_.getMultStatistic().contains(Statistic.SPEED)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveTeamMultAccuracyAny() {
        int len_ = movesTeamMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultAccuracy(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveTeamMultAccuracy(int _index) {
        String move_ = movesTeamMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectTeam)) {
                continue;
            }
            EffectTeam e_ = (EffectTeam) e;
            if (e_.getMultStatistic().contains(Statistic.ACCURACY)) {
                return true;
            }
        }
        return false;
    }
    public String getTrMovesTeamMultStat(int _index) {
        return movesTeamMultStat.get(_index).getTranslation();
    }
    public String clickMovesTeamMultStat(int _index) {
        return tryRedirect(movesTeamMultStat.get(_index));
    }
    public boolean moveFoeTeamMultNormalAny() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultNormal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveFoeTeamMultNormal(int _index) {
        String move_ = movesFoeTeamMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectTeam)) {
                continue;
            }
            EffectTeam e_ = (EffectTeam) e;
            if (hasNormalStat(e_.getMultStatisticFoe().getKeys())) {
                return true;
            }
        }
        return false;
    }
    public boolean moveFoeTeamMultEvasinessAny() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultEvasiness(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveFoeTeamMultEvasiness(int _index) {
        String move_ = movesFoeTeamMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectTeam)) {
                continue;
            }
            EffectTeam e_ = (EffectTeam) e;
            if (e_.getMultStatisticFoe().contains(Statistic.EVASINESS)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveFoeTeamMultSpeedAny() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultSpeed(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveFoeTeamMultSpeed(int _index) {
        String move_ = movesFoeTeamMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectTeam)) {
                continue;
            }
            EffectTeam e_ = (EffectTeam) e;
            if (e_.getMultStatisticFoe().contains(Statistic.SPEED)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveFoeTeamMultAccuracyAny() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultAccuracy(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveFoeTeamMultAccuracy(int _index) {
        String move_ = movesFoeTeamMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        MoveData m_ = data_.getMove(move_);
        for (Effect e: m_.getEffects()) {
            if (!(e instanceof EffectTeam)) {
                continue;
            }
            EffectTeam e_ = (EffectTeam) e;
            if (e_.getMultStatisticFoe().contains(Statistic.ACCURACY)) {
                return true;
            }
        }
        return false;
    }
    public String getTrMovesFoeTeamMultStat(int _index) {
        return movesFoeTeamMultStat.get(_index).getTranslation();
    }
    public String clickMovesFoeTeamMultStat(int _index) {
        return tryRedirect(movesFoeTeamMultStat.get(_index));
    }
    public String getTrMovesTypesDefItem(int _index) {
        return movesTypesDefItem.get(_index).getTranslation();
    }
    public String clickMovesTypesDefItem(int _index) {
        return tryRedirect(movesTypesDefItem.get(_index));
    }
    public String getTrItemsTypesDef(int _index) {
        return itemsTypesDef.get(_index).getTranslation();
    }
    public String clickItemsTypesDef(int _index) {
        return tryRedirect(itemsTypesDef.get(_index));
    }
    public String getTrMovesTypesDefWeather(int _index) {
        return movesTypesDefWeather.get(_index).getTranslation();
    }
    public String clickMovesTypesDefWeather(int _index) {
        return tryRedirect(movesTypesDefWeather.get(_index));
    }
    public String getTrMovesTypeDefMoves(int _index) {
        return movesTypeDefMoves.get(_index).getTranslation();
    }
    public String clickMovesTypeDefMoves(int _index) {
        return tryRedirect(movesTypeDefMoves.get(_index));
    }
    public String getTrMovesChangeTypeMoves(int _index) {
        return movesChangeTypeMoves.get(_index).getTranslation();
    }
    public String clickMovesChangeTypeMoves(int _index) {
        return tryRedirect(movesChangeTypeMoves.get(_index));
    }
    public String getTrMovesBoostCh(int _index) {
        return movesBoostCh.get(_index).getTranslation();
    }
    public String clickMovesBoostCh(int _index) {
        return tryRedirect(movesBoostCh.get(_index));
    }
    public String getTrDeletedStatusSwitch(int _index) {
        return deletedStatusSwitch.get(_index).getTranslation();
    }
    public String clickDeletedStatusSwitch(int _index) {
        return tryRedirect(deletedStatusSwitch.get(_index));
    }
    public boolean hasLawForAttackAny() {
        int len_;
        len_ = beginRoundStatus.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (hasLawForAttack(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasLawForAttack(int _index) {
        String status_ = beginRoundStatus.get(_index).getKey();
        DataBase data_ = getDataBase();
        Status st_ = data_.getStatus(status_);
        StatusBeginRound s_ = (StatusBeginRound) st_;
        if (s_.getLawForUsingAMove().isEmpty()) {
            return !s_.getLawForUsingAMoveIfFoe().isEmpty();
        }
        return true;
    }
    public boolean hasLawForHealAny() {
        int len_;
        len_ = beginRoundStatus.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (hasLawForHeal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasLawForHeal(int _index) {
        String status_ = beginRoundStatus.get(_index).getKey();
        DataBase data_ = getDataBase();
        Status st_ = data_.getStatus(status_);
        StatusBeginRound s_ = (StatusBeginRound) st_;
        return !s_.getLawForFullHealIfMove().isEmpty();
    }
    public String getTrBeginRoundStatus(int _index) {
        return beginRoundStatus.get(_index).getTranslation();
    }
    public String clickBeginRoundStatus(int _index) {
        return tryRedirect(beginRoundStatus.get(_index));
    }
    public String getTrAutoDamage(int _index) {
        return autoDamage.getKey(_index).getTranslation();
    }
    public String clickAutoDamage(int _index) {
        return tryRedirect(autoDamage.getKey(_index));
    }
    public String getTrBeginRoundStatusFoe(int _index) {
        return beginRoundStatusFoe.get(_index).getTranslation();
    }
    public String clickBeginRoundStatusFoe(int _index) {
        return tryRedirect(beginRoundStatusFoe.get(_index));
    }
    public String getTrSuccessfulStatus(int _index) {
        return successfulStatus.get(_index).getTranslation();
    }
    public String clickSuccessfulStatus(int _index) {
        return tryRedirect(successfulStatus.get(_index));
    }
    public String getTrStatusDamage(int _index) {
        return statusDamage.get(_index).getTranslation();
    }
    public String clickStatusDamage(int _index) {
        return tryRedirect(statusDamage.get(_index));
    }
    public boolean statusMultNormalAny() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (statusMultNormal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean statusMultNormal(int _index) {
        String status_ = statusMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        Status s_ = data_.getStatus(status_);
        return hasNormalStat(s_.getMultStat().getKeys());
    }
    public boolean statusMultEvasinessAny() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (statusMultEvasiness(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean statusMultEvasiness(int _index) {
        String status_ = statusMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        Status s_ = data_.getStatus(status_);
        return s_.getMultStat().contains(Statistic.EVASINESS);
    }
    public boolean statusMultSpeedAny() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (statusMultSpeed(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean statusMultSpeed(int _index) {
        String status_ = statusMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        Status s_ = data_.getStatus(status_);
        return s_.getMultStat().contains(Statistic.SPEED);
    }
    public boolean statusMultAccuracyAny() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (statusMultAccuracy(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean statusMultAccuracy(int _index) {
        String status_ = statusMultStat.get(_index).getKey();
        DataBase data_ = getDataBase();
        Status s_ = data_.getStatus(status_);
        return s_.getMultStat().contains(Statistic.ACCURACY);
    }
    public String getTrStatusMultStat(int _index) {
        return statusMultStat.get(_index).getTranslation();
    }
    public String clickStatusMultStat(int _index) {
        return tryRedirect(statusMultStat.get(_index));
    }
    public boolean comboMultNormalAny() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (comboMultNormal(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean comboMultNormal(int _index) {
        StringList combo_ = WithFilterBean.keys(comboMultStat.get(_index));
        DataBase data_ = getDataBase();
        EffectTeam eff_ = effectTeam(data_,combo_,getLanguage());
        return hasNormalStat(eff_.getMultStatisticFoe().getKeys());
    }
    public boolean comboMultEvasinessAny() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (comboMultEvasiness(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean comboMultEvasiness(int _index) {
        StringList combo_ = WithFilterBean.keys(comboMultStat.get(_index));
        DataBase data_ = getDataBase();
        EffectTeam eff_ = effectTeam(data_,combo_,getLanguage());
        return eff_.getMultStatisticFoe().contains(Statistic.EVASINESS);
    }
    public boolean comboMultSpeedAny() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (comboMultSpeed(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean comboMultSpeed(int _index) {
        StringList combo_ = WithFilterBean.keys(comboMultStat.get(_index));
        DataBase data_ = getDataBase();
        EffectTeam eff_ = effectTeam(data_,combo_,getLanguage());
        return eff_.getMultStatisticFoe().contains(Statistic.SPEED);
    }
    public boolean comboMultAccuracyAny() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (comboMultAccuracy(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean comboMultAccuracy(int _index) {
        StringList combo_ = WithFilterBean.keys(comboMultStat.get(_index));
        DataBase data_ = getDataBase();
        EffectTeam eff_ = effectTeam(data_,combo_,getLanguage());
        return eff_.getMultStatisticFoe().contains(Statistic.ACCURACY);
    }
    static EffectTeam effectTeam(DataBase _db, StringList _key, String _lg) {
        for (StringList s: _db.getCombos().getEffects().getKeys()) {
            StringList tmp_ = new StringList(s);
            tmp_.sortElts(DictionaryComparatorUtil.cmpMoves(_db, _lg));
            if (!StringUtil.eqStrings(tmp_, _key)) {
                continue;
            }
            EffectCombo ec_ = _db.getCombos().getEffects().getVal(s);
            if (!ec_.getTeamMove().isEmpty()) {
                return ec_.getTeamMove().first();
            }
        }
        return Instances.newEffectTeam();
    }
    public String getTrComboMultStat(int _index) {
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        StringList moves_ = new StringList();
//        for (String m: comboMultStat.get(_index)) {
//            moves_.add(translatedMoves_.getVal(m));
//        }
        return StringUtil.join(WithFilterBean.values(comboMultStat.get(_index)), CST_SEP_DASH);
    }
    public String clickComboMultStat(int _index) {
        getForms().put(CST_COMBO, WithFilterBean.keys(comboMultStat.get(_index)));
        return PkScriptPages.REN_ADD_WEB_HTML_COMBO_COMBOS_HTML;
    }
    public String getTrComboEvtStat(int _index) {
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        StringList moves_ = new StringList();
//        for (String m: comboEvtStat.get(_index)) {
//            moves_.add(translatedMoves_.getVal(m));
//        }
//        return StringUtil.join(moves_, CST_SEP_DASH);
        return StringUtil.join(WithFilterBean.values(comboEvtStat.get(_index)), CST_SEP_DASH);
    }
    public String clickComboEvtStat(int _index) {
        getForms().put(CST_COMBO, WithFilterBean.keys(comboEvtStat.get(_index)));
        return PkScriptPages.REN_ADD_WEB_HTML_COMBO_COMBOS_HTML;
    }
    public boolean nextRowAfter(int _index) {
        if (_index == IndexConstants.FIRST_INDEX) {
            return true;
        }
        String types_ = efficiency.getKey(_index).getFirst().getKey();
        String typesNext_ = efficiency.getKey(_index - 1).getFirst().getKey();
        return !StringUtil.quickEq(types_,typesNext_);
    }
    public String getEfficiency(int _def, int _off) {
        int i_ = _def * types.size() + _off;
        return efficiency.getValue(i_).toNumberString();
    }
    public String getTrDefaultMove() {
        return defaultMove.getTranslation();
    }
    public String clickDefaultMove() {
        return tryRedirect(defaultMove);
    }

    public long getDefaultBoostValue() {
        return defaultBoostValue;
    }

    public CustList<TranslatedKey> getPrivatingMoves() {
        return privatingMoves;
    }

    public CustList<TranslatedKey> getMovesHealingSubstitute() {
        return movesHealingSubstitute;
    }

    public CustList<TranslatedKey> getAbilitiesSentBeginOther() {
        return abilitiesSentBeginOther;
    }

    public CustList<TranslatedKey> getAbilitiesSentBeginWeather() {
        return abilitiesSentBeginWeather;
    }

    public CustList<TranslatedKey> getItemsSentBeginWeather() {
        return itemsSentBeginWeather;
    }

    public CustList<TranslatedKey> getItemsSentBeginOther() {
        return itemsSentBeginOther;
    }

    public CustList<TranslatedKey> getChangingTypesAbilities() {
        return changingTypesAbilities;
    }

    public CustList<TranslatedKey> getCopyAbilities() {
        return copyAbilities;
    }

    public CustList<TranslatedKey> getAbilitiesSentStatis() {
        return abilitiesSentStatis;
    }

    public CustList<TranslatedKey> getSubstitutingMoves() {
        return substitutingMoves;
    }

    public CustList<TranslatedKey> getAbilitiesPrio() {
        return abilitiesPrio;
    }

    public CustList<TranslatedKey> getSlowAbilities() {
        return slowAbilities;
    }

    public CustList<TranslatedKey> getSlowItems() {
        return slowItems;
    }

    public CustList<TranslatedKey> getReverseSpeedMoves() {
        return reverseSpeedMoves;
    }

    public CustList<TranslatedKey> getBerrySpeed() {
        return berrySpeed;
    }

    public CustList<TranslatedKey> getItemSpeed() {
        return itemSpeed;
    }

    public CustList<TranslatedKey> getAbilitiesSwitch() {
        return abilitiesSwitch;
    }

    public CustList<TranslatedKey> getDeletedStatusSwitch() {
        return deletedStatusSwitch;
    }

    public CustList<TranslatedKey> getEntryHazard() {
        return entryHazard;
    }

    public CustList<TranslatedKey> getBeginRoundStatus() {
        return beginRoundStatus;
    }

    public CustList<TranslatedKey> getDeleteStatusMove() {
        return deleteStatusMove;
    }

    public CustList<TranslatedKey> getImmuStatusAbility() {
        return immuStatusAbility;
    }

    public CustList<TranslatedKey> getAutoDamage() {
        return autoDamage.getKeys();
    }

    public NatStringTreeMap<String> getMapAutoDamage() {
        return mapAutoDamage;
    }

    public CustList<TranslatedKey> getPrepaRoundMoves() {
        return prepaRoundMoves;
    }

    public CustList<TranslatedKey> getSpeedPreparingItems() {
        return speedPreparingItems;
    }

    public CustList<TranslatedKey> getDisappearingRoundMoves() {
        return disappearingRoundMoves;
    }

    public CustList<TranslatedKey> getRechargeMoves() {
        return rechargeMoves;
    }

    public CustList<TranslatedKey> getImmuRecharging() {
        return immuRecharging;
    }

    public CustList<TranslatedKey> getMovesInvoking() {
        return movesInvoking;
    }

    public CustList<TranslatedKey> getCopyMoveTypesAb() {
        return copyMoveTypesAb;
    }

    public CustList<TranslatedKey> getMovesThieving() {
        return movesThieving;
    }

    public CustList<TranslatedKey> getMovesSecEffItems() {
        return movesSecEffItems;
    }

    public CustList<TranslatedKey> getMovesAttracting() {
        return movesAttracting;
    }

    public CustList<TranslatedKey> getBeginRoundStatusFoe() {
        return beginRoundStatusFoe;
    }

    public CustList<TranslatedKey> getPressureAbilities() {
        return pressureAbilities;
    }

    public CustList<TranslatedKey> getProtectAbilities() {
        return protectAbilities;
    }

    public CustList<TranslatedKey> getProtectItems() {
        return protectItems;
    }

    public CustList<TranslatedKey> getProtectMoves() {
        return protectMoves;
    }

    public CustList<TranslatedKey> getEffMoves() {
        return effMoves;
    }

    public CustList<TranslatedKey> getMovesMirror() {
        return movesMirror;
    }

    public CustList<TranslatedKey> getAbilitiesPartStatis() {
        return abilitiesPartStatis;
    }

    public CustList<TranslatedKey> getMovesTeam() {
        return movesTeam;
    }

    public CustList<TranslatedKey> getAbilitiesFighterStatisVar() {
        return abilitiesFighterStatisVar;
    }

    public CustList<TranslatedKey> getAbilitiesRateStatis() {
        return abilitiesRateStatis;
    }

    public CustList<CustList<TranslatedKey>> getComboEvtStat() {
        return comboEvtStat;
    }

    public CustList<TranslatedKey> getAbilitiesFighterStatis() {
        return abilitiesFighterStatis;
    }

    public CustList<TranslatedKey> getItemsFighterStatis() {
        return itemsFighterStatis;
    }

    public CustList<TranslatedKey> getSuccessfulStatus() {
        return successfulStatus;
    }

    public CustList<TranslatedKey> getGlobalMovesStatus() {
        return globalMovesStatus;
    }

    public CustList<TranslatedKey> getAbilitiesPartStatus() {
        return abilitiesPartStatus;
    }

    public CustList<TranslatedKey> getAbilitiesFighterStatus() {
        return abilitiesFighterStatus;
    }

    public CustList<TranslatedKey> getItemsFighterStatus() {
        return itemsFighterStatus;
    }

    public StringMap<AbsBasicTreeMap<Rate,Rate>> getLawsRates() {
        return lawsRates;
    }

    public CustList<TranslatedKey> getMovesProtAgainstKo() {
        return movesProtAgainstKo;
    }

    public Rate getMinHpNotKo() {
        return minHpNotKo;
    }

    public CustList<TranslatedKey> getItemsProtAgainstKo() {
        return itemsProtAgainstKo;
    }

    public CustList<TranslatedKey> getMovesCannotKo() {
        return movesCannotKo;
    }

    public CustList<TranslatedKey> getItemsAbs() {
        return itemsAbs;
    }

    public CustList<TranslatedKey> getAbilitiesRevAbs() {
        return abilitiesRevAbs;
    }

    public CustList<TranslatedKey> getAbilitiesDamageStatis() {
        return abilitiesDamageStatis;
    }

    public CustList<TranslatedKey> getAbilitiesChangingTypesDamage() {
        return abilitiesChangingTypesDamage;
    }

    public CustList<TranslatedKey> getAbilitiesTakingItem() {
        return abilitiesTakingItem;
    }

    public CustList<TranslatedKey> getAbilitiesStatisVarUser() {
        return abilitiesStatisVarUser;
    }

    public CustList<TranslatedKey> getAbilitiesStatus() {
        return abilitiesStatus;
    }

    public CustList<TranslatedKey> getAbilitiesCopyAb() {
        return abilitiesCopyAb;
    }

    public CustList<TranslatedKey> getRecoilItems() {
        return recoilItems;
    }

    public CustList<TranslatedKey> getRecoilAbilities() {
        return recoilAbilities;
    }

    public CustList<TranslatedKey> getAbilitiesKoTarget() {
        return abilitiesKoTarget;
    }

    public CustList<TranslatedKey> getMovesKoTarget() {
        return movesKoTarget;
    }

    public CustList<TranslatedKey> getBerryUser() {
        return berryUser;
    }

    public CustList<TranslatedKey> getBerryTarget() {
        return berryTarget;
    }

    public CustList<TranslatedKey> getAbilitiesEndRound() {
        return abilitiesEndRound;
    }

    public CustList<TranslatedKey> getBerryEndRound() {
        return berryEndRound;
    }

    public CustList<TranslatedKey> getMovesChangingAttOrder() {
        return movesChangingAttOrder;
    }

    public StringMap<String> getRates() {
        return rates;
    }

    public NatStringTreeMap<String> getVarRates() {
        return varRates;
    }

    public Rate getWonHappinessPointsLevel() {
        return wonHappinessPointsLevel;
    }

    public long getHappinessPoints() {
        return happinessPoints;
    }

    public String getDamgeFormula() {
        return damgeFormula;
    }

    public NatStringTreeMap<String> getMapVar() {
        return mapVar;
    }

    public Rate getStrongMove() {
        return strongMove;
    }

    public CustList<TranslatedKey> getDamagingMoves() {
        return damagingMoves;
    }

    public CustList<TranslatedKey> getItemsUserPower() {
        return itemsUserPower;
    }

    public CustList<TranslatedKey> getMovesUserPower() {
        return movesUserPower;
    }

    public CustList<TranslatedKey> getMovesTargetPower() {
        return movesTargetPower;
    }

    public CustList<TranslatedKey> getAbilitiesUserPower() {
        return abilitiesUserPower;
    }

    public CustList<TranslatedKey> getMovesUserAllyDamage() {
        return movesUserAllyDamage;
    }

    public CustList<TranslatedKey> getAbilitiesTargetDamage() {
        return abilitiesTargetDamage;
    }

    public CustList<TranslatedKey> getMovesTargetTeamDamage() {
        return movesTargetTeamDamage;
    }

    public CustList<TranslatedKey> getAbilitiesUserIgnTargetTeam() {
        return abilitiesUserIgnTargetTeam;
    }

    public CustList<TranslatedKey> getAbilitiesGlobal() {
        return abilitiesGlobal;
    }

    public CustList<TranslatedKey> getMovesGlobal() {
        return movesGlobal;
    }

    public CustList<TranslatedKey> getItemsUserDamage() {
        return itemsUserDamage;
    }

    public CustList<TranslatedKey> getAbilitiesUserDamage() {
        return abilitiesUserDamage;
    }

    public CustList<TranslatedKey> getMovesInvokDamage() {
        return movesInvokDamage;
    }

    public CustList<TranslatedKey> getItemsTargetDamage() {
        return itemsTargetDamage;
    }

    public CustList<TranslatedKey> getMovesGlobalPrepaDamage() {
        return movesGlobalPrepaDamage;
    }

    public CustList<TranslatedKey> getStatusDamage() {
        return statusDamage;
    }

    public CustList<TranslatedKey> getAbilitiesUserTargetDamage() {
        return abilitiesUserTargetDamage;
    }

    public CustList<TranslatedKey> getAbilitiesUserStabDamage() {
        return abilitiesUserStabDamage;
    }

    public CustList<TranslatedKey> getMovesTypesDefItem() {
        return movesTypesDefItem;
    }

    public CustList<TranslatedKey> getItemsTypesDef() {
        return itemsTypesDef;
    }

    public CustList<TranslatedKey> getMovesTypesDefWeather() {
        return movesTypesDefWeather;
    }

    public CustList<TranslatedKey> getAbilitiesTypeDefMoves() {
        return abilitiesTypeDefMoves;
    }

    public CustList<TranslatedKey> getMovesTypeDefMoves() {
        return movesTypeDefMoves;
    }

    public CustList<TranslatedKey> getMovesChangeTypeMoves() {
        return movesChangeTypeMoves;
    }

    public CustList<TranslatedKey> getMovesGlobalBreakImmu() {
        return movesGlobalBreakImmu;
    }

    public CustList<TranslatedKey> getMovesUnprotectingTypes() {
        return movesUnprotectingTypes;
    }

    public CustList<TranslatedKey> getAbilitiesBreakImmu() {
        return abilitiesBreakImmu;
    }

    public CustList<TranslatedKey> getItemsCancelImmu() {
        return itemsCancelImmu;
    }

    public CustList<TranslatedKey> getTypes() {
        return types;
    }

    public DictionaryComparator<TranslatedKeyPair,Rate> getEfficiency() {
        return efficiency;
    }

    public CustList<TranslatedKey> getMovesIgnLowAtt() {
        return movesIgnLowAtt;
    }

    public CustList<TranslatedKey> getMovesIgnIncDef() {
        return movesIgnIncDef;
    }

    public CustList<TranslatedKey> getAbilitiesBoostingStat() {
        return abilitiesBoostingStat;
    }

    public CustList<TranslatedKey> getItemsBoostingStat() {
        return itemsBoostingStat;
    }

    public CustList<TranslatedKey> getItemsMultStat() {
        return itemsMultStat;
    }

    public CustList<TranslatedKey> getAbilitiesMultStat() {
        return abilitiesMultStat;
    }

    public CustList<TranslatedKey> getMovesGlobalMultStat() {
        return movesGlobalMultStat;
    }

    public CustList<TranslatedKey> getMovesTeamMultStat() {
        return movesTeamMultStat;
    }

    public CustList<TranslatedKey> getAbilitiesAllyMultStat() {
        return abilitiesAllyMultStat;
    }

    public CustList<TranslatedKey> getMovesFoeTeamMultStat() {
        return movesFoeTeamMultStat;
    }

    public CustList<TranslatedKey> getStatusMultStat() {
        return statusMultStat;
    }

    public CustList<TranslatedKey> getAbilitiesImmuMultStat() {
        return abilitiesImmuMultStat;
    }

    public CustList<CustList<TranslatedKey>> getComboMultStat() {
        return comboMultStat;
    }

    public CustList<TranslatedKey> getAbilitiesBreakProtectMoves() {
        return abilitiesBreakProtectMoves;
    }

    public CustList<TranslatedKey> getMovesIgnAcc() {
        return movesIgnAcc;
    }

    public CustList<TranslatedKey> getMovesIgnEva() {
        return movesIgnEva;
    }

    public CustList<TranslatedKey> getMovesGlobalAcc() {
        return movesGlobalAcc;
    }

    public CustList<TranslatedKey> getAbilitiesImmuCh() {
        return abilitiesImmuCh;
    }

    public CustList<TranslatedKey> getMovesBoostCh() {
        return movesBoostCh;
    }

    public CustList<TranslatedKey> getAbilitesMultEvtCh() {
        return abilitesMultEvtCh;
    }

    public CustList<TranslatedKey> getAbilitesMultRateCh() {
        return abilitesMultRateCh;
    }

    public String getRateFormula() {
        return rateFormula;
    }

    public String getBoostVar() {
        DataBase data_ = getDataBase();
        return StringUtil.splitStrings(data_.getLitterals().getVal(getLanguage()).getVal(data_.boost()), "\t").get(1);
    }

    public LongTreeMap<Rate> getBoosts() {
        return boosts;
    }

    public String getRateFormulaCh() {
        return rateFormulaCh;
    }

    public LongTreeMap<Rate> getBoostsCh() {
        return boostsCh;
    }

    public CustList<TranslatedKey> getMovesProtectingTypes() {
        return movesProtectingTypes;
    }

    public CustList<TranslatedKey> getMovesGlobalBreakImmuAb() {
        return movesGlobalBreakImmuAb;
    }

    public CustList<TranslatedKey> getAbilitiesBreakable() {
        return abilitiesBreakable;
    }

    public CustList<TranslatedKey> getAbilitiesImmuTypes() {
        return abilitiesImmuTypes;
    }

    public CustList<TranslatedKey> getItemsImmuTypes() {
        return itemsImmuTypes;
    }

    public CustList<TranslatedKey> getAbilitiesImmuAllies() {
        return abilitiesImmuAllies;
    }

    public CustList<TranslatedKey> getAbilitiesImmuAlliesDam() {
        return abilitiesImmuAlliesDam;
    }

    public CustList<TranslatedKey> getAbilitiesImmu() {
        return abilitiesImmu;
    }

    public CustList<TranslatedKey> getItemsImmu() {
        return itemsImmu;
    }

    public CustList<TranslatedKey> getAbilitiesImmuSecEffOther() {
        return abilitiesImmuSecEffOther;
    }

    public CustList<TranslatedKey> getAbilitiesImmuSecEffOwner() {
        return abilitiesImmuSecEffOwner;
    }

    public CustList<TranslatedKey> getAbilitiesAchieveTarget() {
        return abilitiesAchieveTarget;
    }

    public CustList<TranslatedKey> getMovesProtecting() {
        return movesProtecting;
    }

    public String getCatchingFormula() {
        return catchingFormula;
    }

    public NatStringTreeMap<String> getVarCatchingFormula() {
        return varCatchingFormula;
    }

    public String getFleeingFormula() {
        return fleeingFormula;
    }

    public NatStringTreeMap<String> getVarFleeingFormula() {
        return varFleeingFormula;
    }

    public IdList<Statistic> getStatisticAnim() {
        return statisticAnim;
    }
}