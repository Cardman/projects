package aiki.beans.help;

import aiki.beans.CommonBean;
import aiki.beans.PokemonStandards;
import aiki.beans.facade.comparators.ComparatorStringList;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
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
    private StringList abilitiesSentBeginWeather;
    private StringList abilitiesSentBeginOther;
    private StringList abilitiesSentStatis;
    private StringList itemsSentBeginWeather;
    private StringList itemsSentBeginOther;
    private StringList changingTypesAbilities;
    private StringList copyAbilities;
    private StringList privatingMoves;
    private StringList movesHealingSubstitute;
    private StringList substitutingMoves;
    private StringList abilitiesPrio;
    private StringList slowAbilities;
    private StringList slowItems;
    private StringList reverseSpeedMoves;
    private StringList berrySpeed;
    private StringList itemSpeed;
    private StringList abilitiesSwitch;
    private StringList deletedStatusSwitch;
    private StringList entryHazard;
    private StringList beginRoundStatus;
    private StringList deleteStatusMove;
    private StringList immuStatusAbility;
    private AbsMap<String,StatusBeginRoundAutoDamage> autoDamage;
    private String damgeFormula;
    private NatStringTreeMap<String> mapAutoDamage;
    private StringList prepaRoundMoves;
    private StringList disappearingRoundMoves;
    private StringList speedPreparingItems;
    private StringList rechargeMoves;
    private StringList immuRecharging;
    private StringList movesInvoking;
    private StringList movesThieving;
    private StringList movesAttracting;
    private StringList movesMirror;
    private StringList copyMoveTypesAb;
    private StringList beginRoundStatusFoe;
    private StringList movesSecEffItems;
    private StringList pressureAbilities;
    private StringList protectAbilities;
    private StringList protectItems;
    private StringList protectMoves;
    private StringList effMoves;
    private StringList abilitiesPartStatis;
    private StringList movesTeam;
    private StringList abilitiesRateStatis;
    private StringList abilitiesFighterStatis;
    private StringList itemsFighterStatis;
    private StringList abilitiesFighterStatisVar;
    private StringList successfulStatus;
    private StringList globalMovesStatus;
    private StringList abilitiesPartStatus;
    private StringList abilitiesFighterStatus;
    private StringList itemsFighterStatus;
    private StringList movesProtAgainstKo;
    private StringList itemsProtAgainstKo;
    private StringList movesCannotKo;
    private StringList itemsAbs;
    private StringList abilitiesRevAbs;
    private StringList abilitiesDamageStatis;
    private StringList abilitiesChangingTypesDamage;
    private StringList abilitiesTakingItem;
    private StringList abilitiesStatisVarUser;
    private StringList abilitiesStatus;
    private StringList abilitiesCopyAb;
    private StringList recoilItems;
    private StringList recoilAbilities;
    private StringList abilitiesKoTarget;
    private StringList movesKoTarget;
    private StringList berryUser;
    private StringList berryTarget;
    private StringList abilitiesEndRound;
    private StringList berryEndRound;
    private StringList movesChangingAttOrder;
    private StringList damagingMoves;
    private StringList itemsUserPower;
    private StringList movesUserPower;
    private StringList movesTargetPower;
    private StringList abilitiesUserPower;
    private NatStringTreeMap<String> mapVar;
    private StringList abilitiesTargetDamage;
    private StringList movesTargetTeamDamage;
    private StringList abilitiesGlobal;
    private StringList movesGlobal;
    private StringList itemsUserDamage;
    private StringList abilitiesUserDamage;
    private StringList movesInvokDamage;
    private StringList itemsTargetDamage;
    private StringList movesGlobalPrepaDamage;
    private StringList statusDamage;
    private StringList abilitiesUserTargetDamage;
    private StringList abilitiesUserStabDamage;
    private StringList movesUserAllyDamage;
    private StringList abilitiesUserIgnTargetTeam;
    private StringList movesIgnLowAtt;
    private StringList movesIgnIncDef;
    private StringList itemsCancelImmu;
    private StringList movesProtectingTypes;
    private StringList movesUnprotectingTypes;
    private StringList movesGlobalBreakImmu;
    private StringList movesGlobalBreakImmuAb;
    private StringList abilitiesBreakable;
    private StringList abilitiesImmuTypes;
    private StringList itemsImmuTypes;
    private StringList abilitiesImmuAllies;
    private StringList abilitiesImmuAlliesDam;
    private StringList abilitiesImmu;
    private StringList itemsImmu;
    private StringList abilitiesImmuSecEffOther;
    private StringList abilitiesImmuSecEffOwner;
    private StringList abilitiesAchieveTarget;
    private StringList abilitiesBreakProtectMoves;
    private StringList movesProtecting;
    private StringList movesIgnAcc;
    private StringList movesIgnEva;
    private StringList movesGlobalAcc;
    private StringList abilitiesBoostingStat;
    private StringList itemsBoostingStat;
    private StringList abilitiesMultStat;
    private StringList itemsMultStat;
    private StringList movesGlobalMultStat;
    private StringList movesTeamMultStat;
    private StringList movesFoeTeamMultStat;
    private StringList abilitiesAllyMultStat;
    private StringList statusMultStat;
    private StringList abilitiesImmuMultStat;
    private CustList<StringList> comboMultStat;
    private CustList<StringList> comboEvtStat;
    private StringList movesTypesDefItem;
    private StringList movesTypesDefWeather;
    private StringList abilitiesTypeDefMoves;
    private StringList abilitiesChangeTypeMoves;
    private StringList movesTypeDefMoves;
    private StringList movesChangeTypeMoves;
    private StringList abilitiesBreakImmu;
    private StringList abilitiesImmuCh;
    private StringList movesBoostCh;
    private StringList abilitesMultEvtCh;
    private StringList abilitesMultRateCh;
    private String rateFormula;
    private String rateFormulaCh;
    private StringList itemsTypesDef;
    private final LongTreeMap<Rate> boosts = new LongTreeMap<Rate>();
    private final LongTreeMap<Rate> boostsCh = new LongTreeMap<Rate>();
    private DictionaryComparator<TypesDuo,Rate> efficiency;
    private StringList types;
    private Rate minHpNotKo;
    private Rate wonHappinessPointsLevel;
    private long defaultBoostValue;
    private String defaultMove;
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
        defaultMove = data_.getDefMove();
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
        movesHealingSubstitute = new StringList(data_.getMovesFullHeal());
        movesHealingSubstitute.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        initSubstitutingMoves();
        initSpeedElements();
        initSwitchingMembers();
        initBeginRoundStatusMembers();
        initBeginRoundPreparingMembers();
        initInvokingMembers();
        initCopyMoveTypesAb();
        copyMoveTypesAb.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initBeginRoundStatusFoe();
        initMovesSecEffItems();
        initPressureAbilities();
        initProtectingMembers();
        initEffMoves();
        initAbilitiesPartStatis();
        movesTeam = new StringList(data_.getMovesEffectTeam());
        movesTeam.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        movesTypesDefWeather = movesTypesDefWeatherInit(data_);
        movesTypesDefWeather.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        abilitiesTypeDefMoves = abilitiesTypeDefMovesInit(data_);
        abilitiesTypeDefMoves.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesChangeTypeMoves = abilitiesChangeTypeMovesInit(data_);
        abilitiesChangeTypeMoves.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initMovesEffectSwitchMoveTypes();
        abilitiesBreakImmu = abilitiesBreakImmuInit(data_);
        abilitiesBreakImmu.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        efficiency = efficiencyInit(data_,getLanguage());
        types = typesInit(data_,getLanguage());
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
    static DictionaryComparator<TypesDuo, Rate> efficiencyInit(DataBase _db, String _lg) {
        DictionaryComparator<TypesDuo, Rate> efficiency_ = DictionaryComparatorUtil.buildTypesDuoRate(_db, _lg, true, true);
        StringMap<String> translatedTypes_ = _db.getTranslatedTypes().getVal(_lg);
        for (TypesDuo t: _db.getTableTypes().getKeys()) {
            TypesDuo t_ = new TypesDuo();
            t_.setPokemonType(translatedTypes_.getVal(t.getPokemonType()));
            t_.setDamageType(translatedTypes_.getVal(t.getDamageType()));
            efficiency_.put(t_, _db.getTableTypes().getVal(t));
        }
        return efficiency_;
    }
    static StringList typesInit(DataBase _db, String _lg) {
        StringMap<String> translatedTypes_ = _db.getTranslatedTypes().getVal(_lg);
        StringList types_ = new StringList();
        for (TypesDuo t: _db.getTableTypes().getKeys()) {
            types_.add(translatedTypes_.getVal(t.getDamageType()));
        }
        types_.removeDuplicates();
        types_.sort();
        return types_;
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
        movesTypesDefItem = movesTypesDefItemInit(data_);
        movesTypesDefItem.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        itemsTypesDef = itemsTypesDefInit(movesTypesDefItem,data_);
        itemsTypesDef.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
    }

    private void initAbilitiesRateStatis() {
        DataBase data_ = getDataBase();
        abilitiesRateStatis = abilitiesRateStatisInit(data_);
        abilitiesRateStatis.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        abilitiesPartStatis = abilitiesPartStatisInit(data_);
        abilitiesPartStatis.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initEffMoves() {
        DataBase data_ = getDataBase();
        effMoves = effMovesInit(data_);
        effMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        pressureAbilities = pressureAbilitiesInit(data_);
        pressureAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        movesSecEffItems = movesSecEffItemsInit(data_);
        movesSecEffItems.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        beginRoundStatusFoe = beginRoundStatusFoeInit(data_);
        beginRoundStatusFoe.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
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
        copyMoveTypesAb = copyMoveTypesAbInit(data_);
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
        substitutingMoves = substitutingMovesInit(data_);
        substitutingMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        movesTypeDefMoves = movesTypeDefMovesInit(data_);
        movesTypeDefMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesChangeTypeMoves = movesChangeTypeMovesInit(data_);
        movesChangeTypeMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        privatingMoves = privatingMovesInit(data_);
        privatingMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        movesIgnAcc = movesIgnAccInit(data_);
        movesIgnAcc.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesIgnEva = movesIgnEvaInit(data_);
        movesIgnEva.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesGlobalAcc = movesGlobalAccInit(data_);
        movesGlobalAcc.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        successfulStatus = successfulStatusInit(data_);
        successfulStatus.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        initGlobalMovesStatus();
        abilitiesPartStatus = abilitiesPartStatusInit(data_);
        abilitiesPartStatus.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesFighterStatus = abilitiesFighterStatusInit(data_);
        abilitiesFighterStatus.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        itemsFighterStatus = itemsFighterStatusInit(data_);
        itemsFighterStatus.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
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
        globalMovesStatus = globalMovesStatusInit(data_);
        globalMovesStatus.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        abilitiesImmuCh = abilitiesImmuChInit(data_);
        abilitiesImmuCh.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initMovesBoostCh();
        abilitesMultEvtCh = abilitesMultEvtChInit(data_);
        abilitesMultEvtCh.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitesMultRateCh = abilitesMultRateChInit(data_);
        abilitesMultRateCh.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        movesBoostCh = movesBoostChInit(data_);
        movesBoostCh.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        abilitiesBoostingStat = abilitiesBoostingStatInit(data_);
        abilitiesBoostingStat.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initItemsBoostingStat();
        initAbItMultStat();
        initMultStatTeamGlobal();
        abilitiesAllyMultStat = abilitiesAllyMultStatInit(data_);
        abilitiesAllyMultStat.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        statusMultStat = statusMultStatInit(data_);
        statusMultStat.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        abilitiesImmuMultStat = abilitiesImmuMultStatInit(data_);
        abilitiesImmuMultStat.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        comboMultStat.sortElts(new ComparatorStringList(data_, getLanguage(), false));
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
        comboEvtStat.sortElts(new ComparatorStringList(data_, getLanguage(), false));
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
        abilitiesMultStat = abilitiesMultStatInit(data_);
        abilitiesMultStat.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        itemsMultStat = itemsMultStatInit(data_);
        itemsMultStat.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        movesGlobalMultStat = movesGlobalMultStatInit(data_);
        movesGlobalMultStat.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        movesTeamMultStat = movesTeamMultStatInit(data_);
        movesTeamMultStat.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesFoeTeamMultStat = movesFoeTeamMultStatInit(data_);
        movesFoeTeamMultStat.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        DataBase data_ = getDataBase();
        comboMultStat = comboMultStatInit(data_,getLanguage());
    }
    static CustList<StringList> comboMultStatInit(DataBase _db, String _lg) {
        CustList<StringList> comboMultStat_ = new CustList<StringList>();
        for (StringList g: _db.getCombos().getEffects().getKeys()) {
            EffectCombo effect_ = _db.getCombos().getEffects().getVal(g);
            if (effect_.estActifEquipe()) {
                comboMultStat_.add(new StringList(g));
            }
        }
        for (StringList v: comboMultStat_) {
            v.sortElts(DictionaryComparatorUtil.cmpMoves(_db,_lg));
        }
        return comboMultStat_;
    }

    private void initComboEvtStat() {
        DataBase data_ = getDataBase();
        comboEvtStat = comboEvtStatInit(data_,getLanguage());
    }
    static CustList<StringList> comboEvtStatInit(DataBase _db, String _lg) {
        CustList<StringList> comboEvtStat_ = new CustList<StringList>();
        for (StringList g: _db.getCombos().getEffects().getKeys()) {
            EffectCombo effect_ = _db.getCombos().getEffects().getVal(g);
            if (!effect_.getMultEvtRateSecEff().isZero()) {
                comboEvtStat_.add(new StringList(g));
            }
        }
        for (StringList v: comboEvtStat_) {
            v.sortElts(DictionaryComparatorUtil.cmpMoves(_db,_lg));
        }
        return comboEvtStat_;
    }

    private void initItemsBoostingStat() {
        DataBase data_ = getDataBase();
        itemsBoostingStat = itemsBoostingStatInit(data_);
        itemsBoostingStat.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
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
        movesProtectingTypes = new StringList(data_.getMovesEffectProt());
        movesProtectingTypes.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesUnprotectingTypes = new StringList(data_.getMovesEffectUnprot());
        movesUnprotectingTypes.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        initMovesBrImmu();
        initImmuTypes();
        initAbilitiesImmu();
        initItImmu();
        abilitiesImmuSecEffOther = abilitiesImmuSecEffOtherInit(data_);
        abilitiesImmuSecEffOther.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesImmuSecEffOwner = abilitiesImmuSecEffOwnerInit(data_);
        abilitiesImmuSecEffOwner.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesAchieveTarget = abilitiesAchieveTargetInit(data_);
        abilitiesAchieveTarget.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesBreakProtectMoves = abilitiesBreakProtectMovesInit(data_);
        abilitiesBreakProtectMoves.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        movesProtecting = new StringList();
        movesProtecting.addAllElts(data_.getMovesProtAgainstDamageMoves());
        movesProtecting.addAllElts(data_.getMovesProtAgainstStatusMoves());
        movesProtecting.addAllElts(data_.getMovesProtAgainstMultiTarget());
        movesProtecting.addAllElts(data_.getMovesProtAgainstPrio());
        movesProtecting.addAllElts(data_.getMovesProtSingleTarget());
        movesProtecting.removeDuplicates();
        movesProtecting.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        itemsCancelImmu = itemsCancelImmuInit(data_);
        itemsCancelImmu.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
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
        abilitiesImmuAllies = abilitiesImmuAlliesInit(data_);
        abilitiesImmuAllies.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesImmuAlliesDam = abilitiesImmuAlliesDamInit(data_);
        abilitiesImmuAlliesDam.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesImmu = abilitiesImmuInit(data_);
        abilitiesImmu.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        itemsImmu = itemsImmuInit(data_);
        itemsImmu.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
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
        abilitiesImmuTypes = abilitiesImmuTypesInit(data_);
        abilitiesImmuTypes.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        itemsImmuTypes = itemsImmuTypesInit(data_);
        itemsImmuTypes.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
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
        movesGlobalBreakImmu = movesGlobalBreakImmuInit(data_);
        movesGlobalBreakImmu.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesGlobalBreakImmuAb = movesGlobalBreakImmuAbInit(data_);
        abilitiesBreakable = abilitiesBreakableInit(movesGlobalBreakImmuAb,data_);
        movesGlobalBreakImmuAb.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        abilitiesBreakable.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        abilitiesTargetDamage = abilitiesTargetDamageInit(data_);
        abilitiesTargetDamage.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initMovesTargetTeamDamage();
        abilitiesUserIgnTargetTeam = abilitiesUserIgnTargetTeamInit(data_);
        abilitiesUserIgnTargetTeam.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesGlobal = abilitiesGlobalInit(data_);
        abilitiesGlobal.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        itemsUserDamage = itemsUserDamageInit(data_);
        itemsUserDamage.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        abilitiesUserDamage = abilitiesUserDamageInit(data_);
        abilitiesUserDamage.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initMovesInvokDamage();
        itemsTargetDamage = itemsTargetDamageInit(data_);
        itemsTargetDamage.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        initMovesGlobalPrepaDamage();
        statusDamage = statusDamageInit(data_);
        statusDamage.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
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
        movesUserAllyDamage = movesUserAllyDamageInit(data_);
        movesUserAllyDamage.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        movesTargetTeamDamage = movesTargetTeamDamageInit(data_);
        movesTargetTeamDamage.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        movesGlobal = movesGlobalInit(data_);
        movesGlobal.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        movesInvokDamage = movesInvokDamageInit(data_);
        movesInvokDamage.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        movesGlobalPrepaDamage = movesGlobalPrepaDamageInit(data_);
        movesGlobalPrepaDamage.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        abilitiesUserTargetDamage = abilitiesUserTargetDamageInit(data_);
        abilitiesUserTargetDamage.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesUserStabDamage = abilitiesUserStabDamageInit(data_);
        abilitiesUserStabDamage.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        movesIgnLowAtt = movesIgnLowAttInit(data_);
        movesIgnLowAtt.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesIgnIncDef = movesIgnIncDefInit(data_);
        movesIgnIncDef.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        damagingMoves = damagingMovesInit(data_);
        damagingMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        itemsUserPower = itemsUserPowerInit(data_);
        itemsUserPower.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        initMovePowerUserTarget();
        abilitiesUserPower = abilitiesUserPowerInit(data_);
        abilitiesUserPower.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        movesUserPower = movesUserPowerInit(data_);
        movesUserPower.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesTargetPower = movesTargetPowerInit(data_);
        movesTargetPower.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        movesProtAgainstKo = new StringList(data_.getMovesProtSingleTargetAgainstKo());
        movesProtAgainstKo.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        initItemsProtAgainstKo();
        movesCannotKo = movesCannotKoInit(data_);
        minHpNotKo = data_.getMinHp();
        movesCannotKo.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        itemsAbs = itemsAbsInit(data_);
        itemsAbs.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        initWhileDamageAbilities();
        initRecoilMembers();
        abilitiesKoTarget = abilitiesKoTargetInit(data_);
        abilitiesKoTarget.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        movesKoTarget = movesKoTargetInit(data_);
        movesKoTarget.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        itemsProtAgainstKo = itemsProtAgainstKoInit(data_);
        itemsProtAgainstKo.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
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
        abilitiesRevAbs = abilitiesRevAbsInit(data_);
        abilitiesRevAbs.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initAbilitiesDamageStatis();
        initAbilitiesChangingTypesDamage();
        abilitiesTakingItem = abilitiesTakingItemInit(data_);
        abilitiesTakingItem.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesStatisVarUser = abilitiesStatisVarUserInit(data_);
        abilitiesStatisVarUser.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesStatus = abilitiesStatusInit(data_);
        abilitiesStatus.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesCopyAb = abilitiesCopyAbInit(data_);
        abilitiesCopyAb.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        abilitiesChangingTypesDamage = abilitiesChangingTypesDamageInit(data_);
        abilitiesChangingTypesDamage.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        abilitiesDamageStatis = abilitiesDamageStatisInit(data_);
        abilitiesDamageStatis.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        abilitiesEndRound = abilitiesEndRoundInit(data_);
        abilitiesEndRound.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        berryEndRound = berryEndRoundInit(data_);
        berryEndRound.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        movesChangingAttOrder = movesChangingAttOrderInit(data_);
        movesChangingAttOrder.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        berryUser = berryUserInit(data_);
        berryTarget = berryTargetInit(data_);
        berryTarget.removeDuplicates();
        berryUser.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        berryTarget.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
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
        return berryTarget_;
    }

    private void initRecoilMembers() {
        DataBase data_ = getDataBase();
        recoilItems = recoilItemsInit(data_);
        recoilItems.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        recoilAbilities = recoilAbilitiesInit(data_);
        recoilAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        abilitiesFighterStatis = abilitiesFighterStatisInit(data_);
        abilitiesFighterStatis.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        itemsFighterStatis = itemsFighterStatisInit(data_);
        itemsFighterStatis.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        abilitiesFighterStatisVar = abilitiesFighterStatisVarInit(data_);
        abilitiesFighterStatisVar.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        protectItems = protectItemsInit(data_);
        protectItems.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        protectAbilities = protectAbilitiesInit(data_);
        protectAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        protectMoves = new StringList(data_.getMovesCountering());
        protectMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        movesInvoking = new StringList(data_.getMovesInvoking());
        movesInvoking.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesThieving = movesThievingInit(data_);
        movesAttracting = movesAttractingInit(data_);
        movesMirror = movesMirrorInit(data_);
        movesThieving.removeDuplicates();
        movesThieving.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesAttracting.removeDuplicates();
        movesAttracting.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesMirror.removeDuplicates();
        movesMirror.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        return movesThieving_;
    }

    private void initBeginRoundPreparingMembers() {
        DataBase data_ = getDataBase();
        prepaRoundMoves = prepaRoundMovesInit(data_);
        disappearingRoundMoves = disappearingRoundMovesInit(data_);
        prepaRoundMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        rechargeMoves = rechargeMovesInit(data_);
        rechargeMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        disappearingRoundMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        speedPreparingItems = speedPreparingItemsInit(data_);
        speedPreparingItems.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        immuRecharging = immuRechargingInit(data_);
        immuRecharging.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        beginRoundStatus = beginRoundStatusInit(data_);
        beginRoundStatus.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        deleteStatusMove = deleteStatusMoveInit(data_);
        deleteStatusMove.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        immuStatusAbility = immuStatusAbilityInit(data_);
        immuStatusAbility.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        autoDamage = autoDamageInit(data_,getLanguage());
        damgeFormula = data_.getFormula(data_.getDamageFormula(), getLanguage());
        mapVar = new NatStringTreeMap<String>();
        mapVar.putAllMap(data_.getDescriptions(data_.getDamageFormula(), getLanguage()));
        mapAutoDamage = mapAutoDamageInit(data_,getLanguage(), autoDamage);
    }

    static NatStringTreeMap<String> mapAutoDamageInit(DataBase _data, String _lg, AbsMap<String, StatusBeginRoundAutoDamage> _a) {
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
    static AbsMap<String,StatusBeginRoundAutoDamage> autoDamageInit(DataBase _db, String _lg) {
        AbsMap<String,StatusBeginRoundAutoDamage> autoDamage_ = DictionaryComparatorUtil.buildStatusAutoData(_db,_lg);
        for (String s: _db.getStatus().getKeys()) {
            Status st_ = _db.getStatus(s);
            if (st_ instanceof StatusBeginRoundAutoDamage) {
                autoDamage_.addEntry(s, (StatusBeginRoundAutoDamage) st_);
            }
        }
        return autoDamage_;
    }

    private void initSwitchingMembers() {
        DataBase data_ = getDataBase();
        abilitiesSwitch = abilitiesSwitchInit(data_);
        abilitiesSwitch.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        deletedStatusSwitch = deletedStatusSwitchInit(data_);
        deletedStatusSwitch.removeDuplicates();
        deletedStatusSwitch.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        entryHazard = new StringList(data_.getMovesEffectWhileSending());
        entryHazard.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        return deletedStatusSwitch_;
    }

    private void initSpeedElements() {
        DataBase data_ = getDataBase();
        abilitiesPrio = abilitiesPrioInit(data_);
        abilitiesPrio.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        slowAbilities = slowAbilitiesInit(data_);
        slowAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        slowItems = slowItemsInit(data_);
        slowItems.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
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
        berrySpeed = berrySpeedInit(data_);
        berrySpeed.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        itemSpeed = itemSpeedInit(data_);
        itemSpeed.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
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
        reverseSpeedMoves = reverseSpeedMovesInit(data_);
        reverseSpeedMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        changingTypesAbilities = changingTypesAbilitiesInit(data_);
        changingTypesAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        itemsSentBeginWeather = itemsSentBeginWeatherInit(data_);
        itemsSentBeginOther = itemsSentBeginOtherInit(data_);
        itemsSentBeginOther.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        itemsSentBeginWeather.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
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
        abilitiesSentBeginWeather = new StringList();
        abilitiesSentBeginOther = new StringList();
        StringMap<AbilityData> s_ = abilitiesSentStatisInit(data_);
        abilitiesSentStatis = new StringList(s_.getKeys());
        copyAbilities = new StringList();
        feed(s_,copyAbilities,abilitiesSentBeginOther,abilitiesSentBeginWeather);
        abilitiesSentStatis.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesSentBeginOther.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesSentBeginWeather.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        copyAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSentBeginWeather.get(_index));
    }
    public String clickAbilitiesSentBegin(int _index) {
        return tryRedirectAb(abilitiesSentBeginWeather.get(_index));
    }
    public String getTrAbilitiesSentBeginOth(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSentBeginOther.get(_index));
    }
    public String clickAbilitiesSentBeginOth(int _index) {
        return tryRedirectAb(abilitiesSentBeginOther.get(_index));
    }
    public String getTrItemsSentBegin(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsSentBeginWeather.get(_index));
    }
    public String clickItemsSentBegin(int _index) {
        return tryRedirectIt(itemsSentBeginWeather.get(_index));
    }
    public String getTrItemsSentBeginOth(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsSentBeginOther.get(_index));
    }
    public String clickItemsSentBeginOth(int _index) {
        return tryRedirectIt(itemsSentBeginOther.get(_index));
    }
    public String getTrSlowItems(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(slowItems.get(_index));
    }
    public String clickSlowItems(int _index) {
        return tryRedirectIt(slowItems.get(_index));
    }
    public String getTrItemSpeed(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemSpeed.get(_index));
    }
    public String clickItemSpeed(int _index) {
        return tryRedirectIt(itemSpeed.get(_index));
    }
    public String getTrSpeedPreparingItems(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(speedPreparingItems.get(_index));
    }
    public String clickSpeedPreparingItems(int _index) {
        return tryRedirectIt(speedPreparingItems.get(_index));
    }
    public String getTrProtectItems(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(protectItems.get(_index));
    }
    public String clickProtectItems(int _index) {
        return tryRedirectIt(protectItems.get(_index));
    }
    public String getTrItemsFighterStatis(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsFighterStatis.get(_index));
    }
    public String clickItemsFighterStatis(int _index) {
        return tryRedirectIt(itemsFighterStatis.get(_index));
    }
    public String getTrItemsFighterStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsFighterStatus.get(_index));
    }
    public String clickItemsFighterStatus(int _index) {
        return tryRedirectIt(itemsFighterStatus.get(_index));
    }
    public String getTrItemsProtAgainstKo(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsProtAgainstKo.get(_index));
    }
    public String clickItemsProtAgainstKo(int _index) {
        return tryRedirectIt(itemsProtAgainstKo.get(_index));
    }
    public String getTrItemsAbs(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsAbs.get(_index));
    }
    public String clickItemsAbs(int _index) {
        return tryRedirectIt(itemsAbs.get(_index));
    }
    public String getTrRecoilItems(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(recoilItems.get(_index));
    }
    public String clickRecoilItems(int _index) {
        String it_ = recoilItems.get(_index);
        return tryRedirectIt(it_);
    }
    public String getTrBerryUser(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berryUser.get(_index));
    }
    public String clickBerryUser(int _index) {
        String it_ = berryUser.get(_index);
        return tryRedirectIt(it_);
    }
    public String getTrBerryTarget(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berryTarget.get(_index));
    }
    public String clickBerryTarget(int _index) {
        String it_ = berryTarget.get(_index);
        return tryRedirectIt(it_);
    }
    public String getTrBerryEndRound(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berryEndRound.get(_index));
    }
    public String clickBerryEndRound(int _index) {
        String it_ = berryEndRound.get(_index);
        return tryRedirectIt(it_);
    }
    public String getTrItemsUserPower(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsUserPower.get(_index));
    }
    public String clickItemsUserPower(int _index) {
        String it_ = itemsUserPower.get(_index);
        return tryRedirectIt(it_);
    }
    public String getTrItemsUserDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsUserDamage.get(_index));
    }
    public String clickItemsUserDamage(int _index) {
        String it_ = itemsUserDamage.get(_index);
        return tryRedirectIt(it_);
    }
    public String getTrItemsTargetDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsTargetDamage.get(_index));
    }
    public String clickItemsTargetDamage(int _index) {
        String it_ = itemsTargetDamage.get(_index);
        return tryRedirectIt(it_);
    }
    public String getTrItemsCancelImmu(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsCancelImmu.get(_index));
    }
    public String clickItemsCancelImmu(int _index) {
        String it_ = itemsCancelImmu.get(_index);
        return tryRedirectIt(it_);
    }
    public String getTrItemsImmuTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsImmuTypes.get(_index));
    }
    public String clickItemsImmuTypes(int _index) {
        String it_ = itemsImmuTypes.get(_index);
        return tryRedirectIt(it_);
    }
    public String getTrItemsImmu(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsImmu.get(_index));
    }
    public String clickItemsImmu(int _index) {
        String it_ = itemsImmu.get(_index);
        return tryRedirectIt(it_);
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
        String ab_ = itemsBoostingStat.get(_index);
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
        String ab_ = itemsBoostingStat.get(_index);
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
        String ab_ = itemsBoostingStat.get(_index);
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
        String ab_ = itemsBoostingStat.get(_index);
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
        String ab_ = itemsBoostingStat.get(_index);
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsBoostingStat.get(_index));
    }
    public String clickItemsBoostingStat(int _index) {
        String it_ = itemsBoostingStat.get(_index);
        return tryRedirectIt(it_);
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
        String ab_ = itemsMultStat.get(_index);
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
        String ab_ = itemsMultStat.get(_index);
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
        String ab_ = itemsMultStat.get(_index);
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
        String ab_ = itemsMultStat.get(_index);
        DataBase data_ = getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        return i_.getMultStat().contains(Statistic.ACCURACY);
    }
    public String getTrItemsMultStat(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsMultStat.get(_index));
    }
    public String clickItemsMultStat(int _index) {
        String it_ = itemsMultStat.get(_index);
        return tryRedirectIt(it_);
    }
    public String getTrBerrySpeed(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berrySpeed.get(_index));
    }
    public String clickBerrySpeed(int _index) {
        return tryRedirectIt(berrySpeed.get(_index));
    }
    public String getTrChangingTypesAbilities(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(changingTypesAbilities.get(_index));
    }
    public String clickChangingTypesAbilities(int _index) {
        return tryRedirectAb(changingTypesAbilities.get(_index));
    }
    public String getTrAbilitiesSentStatis(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSentStatis.get(_index));
    }
    public String clickAbilitiesSentStatis(int _index) {
        return tryRedirectAb(abilitiesSentStatis.get(_index));
    }
    public String getTrCopyAbilities(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(copyAbilities.get(_index));
    }
    public String clickCopyAbilities(int _index) {
        return tryRedirectAb(copyAbilities.get(_index));
    }
    public String getTrAbilitiesPrio(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPrio.get(_index));
    }
    public String clickAbilitiesPrio(int _index) {
        return tryRedirectAb(abilitiesPrio.get(_index));
    }
    public String getTrSlowAbilities(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(slowAbilities.get(_index));
    }
    public String clickSlowAbilities(int _index) {
        return tryRedirectAb(slowAbilities.get(_index));
    }
    public String getTrAbilitiesSwitch(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSwitch.get(_index));
    }
    public String clickAbilitiesSwitch(int _index) {
        return tryRedirectAb(abilitiesSwitch.get(_index));
    }
    public String getTrImmuStatusAbility(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(immuStatusAbility.get(_index));
    }
    public String clickImmuStatusAbility(int _index) {
        return tryRedirectAb(immuStatusAbility.get(_index));
    }
    public String getTrImmuRecharging(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(immuRecharging.get(_index));
    }
    public String clickImmuRecharging(int _index) {
        return tryRedirectAb(immuRecharging.get(_index));
    }
    public String getTrCopyMoveTypesAb(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(copyMoveTypesAb.get(_index));
    }
    public String clickCopyMoveTypesAb(int _index) {
        return tryRedirectAb(copyMoveTypesAb.get(_index));
    }
    public String getTrPressureAbilities(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(pressureAbilities.get(_index));
    }
    public String clickPressureAbilities(int _index) {
        return tryRedirectAb(pressureAbilities.get(_index));
    }
    public String getTrProtectAbilities(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(protectAbilities.get(_index));
    }
    public String clickProtectAbilities(int _index) {
        return tryRedirectAb(protectAbilities.get(_index));
    }
    public String getTrAbilitiesPartStatis(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPartStatis.get(_index));
    }
    public String clickAbilitiesPartStatis(int _index) {
        return tryRedirectAb(abilitiesPartStatis.get(_index));
    }
    public String getTrAbilitiesRateStatis(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesRateStatis.get(_index));
    }
    public String clickAbilitiesRateStatis(int _index) {
        return tryRedirectAb(abilitiesRateStatis.get(_index));
    }
    public String getTrAbilitiesFighterStatis(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatis.get(_index));
    }
    public String clickAbilitiesFighterStatis(int _index) {
        return tryRedirectAb(abilitiesFighterStatis.get(_index));
    }
    public String getTrAbilitiesFighterStatisVar(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatisVar.get(_index));
    }
    public String clickAbilitiesFighterStatisVar(int _index) {
        return tryRedirectAb(abilitiesFighterStatisVar.get(_index));
    }
    public String getTrAbilitiesPartStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPartStatus.get(_index));
    }
    public String clickAbilitiesPartStatus(int _index) {
        return tryRedirectAb(abilitiesPartStatus.get(_index));
    }
    public String getTrAbilitiesFighterStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatus.get(_index));
    }
    public String clickAbilitiesFighterStatus(int _index) {
        return tryRedirectAb(abilitiesFighterStatus.get(_index));
    }
    public String getTrAbilitiesRevAbs(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesRevAbs.get(_index));
    }
    public String clickAbilitiesRevAbs(int _index) {
        return tryRedirectAb(abilitiesRevAbs.get(_index));
    }
    public String getTrAbilitiesDamageStatis(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesDamageStatis.get(_index));
    }
    public String clickAbilitiesDamageStatis(int _index) {
        return tryRedirectAb(abilitiesDamageStatis.get(_index));
    }
    public String getTrAbilitiesChangingTypesDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesChangingTypesDamage.get(_index));
    }
    public String clickAbilitiesChangingTypesDamage(int _index) {
        return tryRedirectAb(abilitiesChangingTypesDamage.get(_index));
    }
    public String getTrAbilitiesTakingItem(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTakingItem.get(_index));
    }
    public String clickAbilitiesTakingItem(int _index) {
        return tryRedirectAb(abilitiesTakingItem.get(_index));
    }
    public String getTrAbilitiesStatisVarUser(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesStatisVarUser.get(_index));
    }
    public String clickAbilitiesStatisVarUser(int _index) {
        return tryRedirectAb(abilitiesStatisVarUser.get(_index));
    }
    public String getTrAbilitiesStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesStatus.get(_index));
    }
    public String clickAbilitiesStatus(int _index) {
        return tryRedirectAb(abilitiesStatus.get(_index));
    }
    public String getTrAbilitiesCopyAb(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesCopyAb.get(_index));
    }
    public String clickAbilitiesCopyAb(int _index) {
        return tryRedirectAb(abilitiesCopyAb.get(_index));
    }
    public String getTrRecoilAbilities(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(recoilAbilities.get(_index));
    }
    public String clickRecoilAbilities(int _index) {
        return tryRedirectAb(recoilAbilities.get(_index));
    }
    public String getTrAbilitiesKoTarget(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesKoTarget.get(_index));
    }
    public String clickAbilitiesKoTarget(int _index) {
        return tryRedirectAb(abilitiesKoTarget.get(_index));
    }
    public String getTrAbilitiesEndRound(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesEndRound.get(_index));
    }
    public String clickAbilitiesEndRound(int _index) {
        return tryRedirectAb(abilitiesEndRound.get(_index));
    }
    public String getTrAbilitiesUserPower(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserPower.get(_index));
    }
    public String clickAbilitiesUserPower(int _index) {
        return tryRedirectAb(abilitiesUserPower.get(_index));
    }
    public String getTrAbilitiesTargetDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTargetDamage.get(_index));
    }
    public String clickAbilitiesTargetDamage(int _index) {
        return tryRedirectAb(abilitiesTargetDamage.get(_index));
    }
    public String getTrAbilitiesGlobal(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesGlobal.get(_index));
    }
    public String clickAbilitiesGlobal(int _index) {
        return tryRedirectAb(abilitiesGlobal.get(_index));
    }
    public String getTrAbilitiesUserDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserDamage.get(_index));
    }
    public String clickAbilitiesUserDamage(int _index) {
        return tryRedirectAb(abilitiesUserDamage.get(_index));
    }
    public String getTrAbilitiesUserTargetDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserTargetDamage.get(_index));
    }
    public String clickAbilitiesUserTargetDamage(int _index) {
        return tryRedirectAb(abilitiesUserTargetDamage.get(_index));
    }
    public String getTrAbilitiesUserStabDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserStabDamage.get(_index));
    }
    public String clickAbilitiesUserStabDamage(int _index) {
        return tryRedirectAb(abilitiesUserStabDamage.get(_index));
    }
    public String getTrAbilitiesUserIgnTargetTeam(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserIgnTargetTeam.get(_index));
    }
    public String clickAbilitiesUserIgnTargetTeam(int _index) {
        return tryRedirectAb(abilitiesUserIgnTargetTeam.get(_index));
    }
    public String getTrAbilitiesBreakable(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakable.get(_index));
    }
    public String clickAbilitiesBreakable(int _index) {
        return tryRedirectAb(abilitiesBreakable.get(_index));
    }
    public String getTrAbilitiesImmuTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuTypes.get(_index));
    }
    public String clickAbilitiesImmuTypes(int _index) {
        return tryRedirectAb(abilitiesImmuTypes.get(_index));
    }
    public String getTrAbilitiesImmuAllies(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuAllies.get(_index));
    }
    public String clickAbilitiesImmuAllies(int _index) {
        return tryRedirectAb(abilitiesImmuAllies.get(_index));
    }
    public String getTrAbilitiesImmuAlliesDam(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuAlliesDam.get(_index));
    }
    public String clickAbilitiesImmuAlliesDam(int _index) {
        return tryRedirectAb(abilitiesImmuAlliesDam.get(_index));
    }
    public String getTrAbilitiesImmu(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmu.get(_index));
    }
    public String clickAbilitiesImmu(int _index) {
        return tryRedirectAb(abilitiesImmu.get(_index));
    }
    public String getTrAbilitiesImmuSecEffOther(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuSecEffOther.get(_index));
    }
    public String clickAbilitiesImmuSecEffOther(int _index) {
        return tryRedirectAb(abilitiesImmuSecEffOther.get(_index));
    }
    public String getTrAbilitiesImmuSecEffOwner(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuSecEffOwner.get(_index));
    }
    public String clickAbilitiesImmuSecEffOwner(int _index) {
        return tryRedirectAb(abilitiesImmuSecEffOwner.get(_index));
    }
    public String getTrAbilitiesAchieveTarget(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesAchieveTarget.get(_index));
    }
    public String clickAbilitiesAchieveTarget(int _index) {
        return tryRedirectAb(abilitiesAchieveTarget.get(_index));
    }
    public String getTrAbilitiesBreakProtectMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakProtectMoves.get(_index));
    }
    public String clickAbilitiesBreakProtectMoves(int _index) {
        return tryRedirectAb(abilitiesBreakProtectMoves.get(_index));
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
        String ab_ = abilitiesBoostingStat.get(_index);
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
        String ab_ = abilitiesBoostingStat.get(_index);
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
        String ab_ = abilitiesBoostingStat.get(_index);
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
        String ab_ = abilitiesBoostingStat.get(_index);
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
        String ab_ = abilitiesBoostingStat.get(_index);
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.ACCURACY);
    }
    public String getTrAbilitiesBoostingStat(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBoostingStat.get(_index));
    }
    public String clickAbilitiesBoostingStat(int _index) {
        return tryRedirectAb(abilitiesBoostingStat.get(_index));
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
        String ab_ = abilitiesMultStat.get(_index);
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
        String ab_ = abilitiesMultStat.get(_index);
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
        String ab_ = abilitiesMultStat.get(_index);
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
        String ab_ = abilitiesMultStat.get(_index);
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesMultStat.get(_index));
    }
    public String clickAbilitiesMultStat(int _index) {
        return tryRedirectAb(abilitiesMultStat.get(_index));
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
        String ab_ = abilitiesAllyMultStat.get(_index);
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
        String ab_ = abilitiesAllyMultStat.get(_index);
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
        String ab_ = abilitiesAllyMultStat.get(_index);
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
        String ab_ = abilitiesAllyMultStat.get(_index);
        DataBase data_ = getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getMultStatAlly().contains(Statistic.ACCURACY);
    }
    public String getTrAbilitiesAllyMultStat(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesAllyMultStat.get(_index));
    }
    public String clickAbilitiesAllyMultStat(int _index) {
        return tryRedirectAb(abilitiesAllyMultStat.get(_index));
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
        String ab_ = abilitiesImmuMultStat.get(_index);
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
        String ab_ = abilitiesImmuMultStat.get(_index);
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
        String ab_ = abilitiesImmuMultStat.get(_index);
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
        String ab_ = abilitiesImmuMultStat.get(_index);
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuMultStat.get(_index));
    }
    public String clickAbilitiesImmuMultStat(int _index) {
        return tryRedirectAb(abilitiesImmuMultStat.get(_index));
    }
    public String getTrAbilitiesTypeDefMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTypeDefMoves.get(_index));
    }
    public String clickAbilitiesTypeDefMoves(int _index) {
        return tryRedirectAb(abilitiesTypeDefMoves.get(_index));
    }
    public String getTrAbilitiesChangeTypeMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesChangeTypeMoves.get(_index));
    }
    public String clickAbilitiesChangeTypeMoves(int _index) {
        return tryRedirectAb(abilitiesChangeTypeMoves.get(_index));
    }
    public String getTrAbilitiesBreakImmu(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakImmu.get(_index));
    }
    public String clickAbilitiesBreakImmu(int _index) {
        return tryRedirectAb(abilitiesBreakImmu.get(_index));
    }
    public String getTrAbilitiesImmuCh(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuCh.get(_index));
    }
    public String clickAbilitiesImmuCh(int _index) {
        return tryRedirectAb(abilitiesImmuCh.get(_index));
    }
    public String getTrAbilitiesMultEvtCh(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitesMultEvtCh.get(_index));
    }
    public String clickAbilitiesMultEvtCh(int _index) {
        return tryRedirectAb(abilitesMultEvtCh.get(_index));
    }
    public String getTrAbilitiesMultRateCh(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitesMultRateCh.get(_index));
    }
    public String clickAbilitiesMultRateCh(int _index) {
        return tryRedirectAb(abilitesMultRateCh.get(_index));
    }
    public String getTrPrivatingMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(privatingMoves.get(_index));
    }
    public String clickPrivatingMoves(int _index) {
        return tryRedirectMv(privatingMoves.get(_index));
    }
    public String getTrMovesHealingSubstitute(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesHealingSubstitute.get(_index));
    }
    public String clickMovesHealingSubstitute(int _index) {
        return tryRedirectMv(movesHealingSubstitute.get(_index));
    }
    public String getTrSubstitutingMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(substitutingMoves.get(_index));
    }
    public String clickSubstitutingMoves(int _index) {
        return tryRedirectMv(substitutingMoves.get(_index));
    }
    public String getTrReverseSpeedMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(reverseSpeedMoves.get(_index));
    }
    public String clickReverseSpeedMoves(int _index) {
        return tryRedirectMv(reverseSpeedMoves.get(_index));
    }
    public String getTrEntryHazard(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(entryHazard.get(_index));
    }
    public String clickEntryHazard(int _index) {
        return tryRedirectMv(entryHazard.get(_index));
    }
    public String getTrDeleteStatusMove(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(deleteStatusMove.get(_index));
    }
    public String clickDeleteStatusMove(int _index) {
        return tryRedirectMv(deleteStatusMove.get(_index));
    }
    public boolean isDisappearingUser(int _index) {
        DataBase data_ = getDataBase();
        String m_ = prepaRoundMoves.get(_index);
        MoveData move_ = data_.getMove(m_);
        return move_.getDisappearBeforeUse();
    }
    public String getTrPrepaRoundMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(prepaRoundMoves.get(_index));
    }
    public String clickPrepaRoundMoves(int _index) {
        return tryRedirectMv(prepaRoundMoves.get(_index));
    }
    public String getTrRechargeMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(rechargeMoves.get(_index));
    }
    public String clickRechargeMoves(int _index) {
        return tryRedirectMv(rechargeMoves.get(_index));
    }
    public String getTrMovesInvoking(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesInvoking.get(_index));
    }
    public String clickMovesInvoking(int _index) {
        return tryRedirectMv(movesInvoking.get(_index));
    }
    public String getTrMovesThieving(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesThieving.get(_index));
    }
    public String clickMovesThieving(int _index) {
        return tryRedirectMv(movesThieving.get(_index));
    }
    public String getTrMovesAttracting(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesAttracting.get(_index));
    }
    public String clickMovesAttracting(int _index) {
        return tryRedirectMv(movesAttracting.get(_index));
    }
    public String getTrMovesMirror(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesMirror.get(_index));
    }
    public String clickMovesMirror(int _index) {
        return tryRedirectMv(movesMirror.get(_index));
    }
    public String getTrMovesSecEffItems(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesSecEffItems.get(_index));
    }
    public String clickMovesSecEffItems(int _index) {
        return tryRedirectMv(movesSecEffItems.get(_index));
    }
    public String getTrProtectMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(protectMoves.get(_index));
    }
    public String clickProtectMoves(int _index) {
        return tryRedirectMv(protectMoves.get(_index));
    }
    public String getTrEffMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(effMoves.get(_index));
    }
    public String clickEffMoves(int _index) {
        return tryRedirectMv(effMoves.get(_index));
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
        String m_ = movesTeam.get(_index);
        return data_.getMove(m_);
    }
    public String getTrMovesTeam(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTeam.get(_index));
    }
    public String clickMovesTeam(int _index) {
        return tryRedirectMv(movesTeam.get(_index));
    }
    public String getTrGlobalMovesStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(globalMovesStatus.get(_index));
    }
    public String clickGlobalMovesStatus(int _index) {
        return tryRedirectMv(globalMovesStatus.get(_index));
    }
    public String getTrMovesProtAgainstKo(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtAgainstKo.get(_index));
    }
    public String clickMovesProtAgainstKo(int _index) {
        return tryRedirectMv(movesProtAgainstKo.get(_index));
    }
    public String getTrMovesCannotKo(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesCannotKo.get(_index));
    }
    public String clickMovesCannotKo(int _index) {
        return tryRedirectMv(movesCannotKo.get(_index));
    }
    public String getTrMovesKoTarget(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesKoTarget.get(_index));
    }
    public String clickMovesKoTarget(int _index) {
        return tryRedirectMv(movesKoTarget.get(_index));
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
        String m_ = movesChangingAttOrder.get(_index);
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesChangingAttOrder.get(_index));
    }
    public String clickMovesChangingAttOrder(int _index) {
        return tryRedirectMv(movesChangingAttOrder.get(_index));
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
        MoveData move_ = data_.getMove(damagingMoves.get(_index));
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
        MoveData move_ = data_.getMove(damagingMoves.get(_index));
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
        MoveData move_ = data_.getMove(damagingMoves.get(_index));
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        return !eff_.getMultDamageAgainst().isEmpty();
    }
    public String getTrDamagingMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(damagingMoves.get(_index));
    }
    public String clickDamagingMoves(int _index) {
        return tryRedirectMv(damagingMoves.get(_index));
    }
    public String getTrMovesUserPower(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUserPower.get(_index));
    }
    public String clickMovesUserPower(int _index) {
        return tryRedirectMv(movesUserPower.get(_index));
    }
    public String getTrMovesTargetPower(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTargetPower.get(_index));
    }
    public String clickMovesTargetPower(int _index) {
        return tryRedirectMv(movesTargetPower.get(_index));
    }
    public String getTrMovesTargetTeamDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTargetTeamDamage.get(_index));
    }
    public String clickMovesTargetTeamDamage(int _index) {
        return tryRedirectMv(movesTargetTeamDamage.get(_index));
    }
    public String getTrMovesGlobal(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobal.get(_index));
    }
    public String clickMovesGlobal(int _index) {
        return tryRedirectMv(movesGlobal.get(_index));
    }
    public String getTrMovesInvokDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesInvokDamage.get(_index));
    }
    public String clickMovesInvokDamage(int _index) {
        return tryRedirectMv(movesInvokDamage.get(_index));
    }
    public String getTrMovesGlobalPrepaDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalPrepaDamage.get(_index));
    }
    public String clickMovesGlobalPrepaDamage(int _index) {
        return tryRedirectMv(movesGlobalPrepaDamage.get(_index));
    }
    public String getTrMovesUserAllyDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUserAllyDamage.get(_index));
    }
    public String clickMovesUserAllyaDamage(int _index) {
        return tryRedirectMv(movesUserAllyDamage.get(_index));
    }
    public String getTrMovesIgnLowAtt(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnLowAtt.get(_index));
    }
    public String clickMovesIgnLowAtt(int _index) {
        return tryRedirectMv(movesIgnLowAtt.get(_index));
    }
    public String getTrMovesIgnIncDef(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnIncDef.get(_index));
    }
    public String clickMovesIgnIncDef(int _index) {
        return tryRedirectMv(movesIgnIncDef.get(_index));
    }
    public String getTrMovesProtectingTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtectingTypes.get(_index));
    }
    public String clickMovesProtectingTypes(int _index) {
        return tryRedirectMv(movesProtectingTypes.get(_index));
    }
    public String getTrMovesUnprotectingTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUnprotectingTypes.get(_index));
    }
    public String clickMovesUnprotectingTypes(int _index) {
        return tryRedirectMv(movesUnprotectingTypes.get(_index));
    }
    public String getTrMovesGlobalBreakImmu(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalBreakImmu.get(_index));
    }
    public String clickMovesGlobalBreakImmu(int _index) {
        return tryRedirectMv(movesGlobalBreakImmu.get(_index));
    }
    public String getTrMovesGlobalBreakImmuAb(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalBreakImmuAb.get(_index));
    }
    public String clickMovesGlobalBreakImmuAb(int _index) {
        return tryRedirectMv(movesGlobalBreakImmuAb.get(_index));
    }
    public String getTrMovesProtecting(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtecting.get(_index));
    }
    public String clickMovesProtecting(int _index) {
        return tryRedirectMv(movesProtecting.get(_index));
    }
    public String getTrMovesIgnAcc(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnAcc.get(_index));
    }
    public String clickMovesIgnAcc(int _index) {
        return tryRedirectMv(movesIgnAcc.get(_index));
    }
    public String getTrMovesIgnEva(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnEva.get(_index));
    }
    public String clickMovesIgnEva(int _index) {
        return tryRedirectMv(movesIgnEva.get(_index));
    }
    public String getTrMovesGlobalAcc(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalAcc.get(_index));
    }
    public String clickMovesGlobalAcc(int _index) {
        return tryRedirectMv(movesGlobalAcc.get(_index));
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
        String move_ = movesGlobalMultStat.get(_index);
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
        String move_ = movesGlobalMultStat.get(_index);
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
        String move_ = movesGlobalMultStat.get(_index);
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
        String move_ = movesGlobalMultStat.get(_index);
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalMultStat.get(_index));
    }
    public String clickMovesGlobalMultStat(int _index) {
        return tryRedirectMv(movesGlobalMultStat.get(_index));
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
        String move_ = movesTeamMultStat.get(_index);
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
        String move_ = movesTeamMultStat.get(_index);
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
        String move_ = movesTeamMultStat.get(_index);
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
        String move_ = movesTeamMultStat.get(_index);
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTeamMultStat.get(_index));
    }
    public String clickMovesTeamMultStat(int _index) {
        return tryRedirectMv(movesTeamMultStat.get(_index));
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
        String move_ = movesFoeTeamMultStat.get(_index);
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
        String move_ = movesFoeTeamMultStat.get(_index);
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
        String move_ = movesFoeTeamMultStat.get(_index);
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
        String move_ = movesFoeTeamMultStat.get(_index);
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesFoeTeamMultStat.get(_index));
    }
    public String clickMovesFoeTeamMultStat(int _index) {
        return tryRedirectMv(movesFoeTeamMultStat.get(_index));
    }
    public String getTrMovesTypesDefItem(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTypesDefItem.get(_index));
    }
    public String clickMovesTypesDefItem(int _index) {
        return tryRedirectMv(movesTypesDefItem.get(_index));
    }
    public String getTrItemsTypesDef(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsTypesDef.get(_index));
    }
    public String clickItemsTypesDef(int _index) {
        return tryRedirectIt(itemsTypesDef.get(_index));
    }
    public String getTrMovesTypesDefWeather(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTypesDefWeather.get(_index));
    }
    public String clickMovesTypesDefWeather(int _index) {
        return tryRedirectMv(movesTypesDefWeather.get(_index));
    }
    public String getTrMovesTypeDefMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTypeDefMoves.get(_index));
    }
    public String clickMovesTypeDefMoves(int _index) {
        return tryRedirectMv(movesTypeDefMoves.get(_index));
    }
    public String getTrMovesChangeTypeMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesChangeTypeMoves.get(_index));
    }
    public String clickMovesChangeTypeMoves(int _index) {
        return tryRedirectMv(movesChangeTypeMoves.get(_index));
    }
    public String getTrMovesBoostCh(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesBoostCh.get(_index));
    }
    public String clickMovesBoostCh(int _index) {
        return tryRedirectMv(movesBoostCh.get(_index));
    }
    public String getTrDeletedStatusSwitch(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(deletedStatusSwitch.get(_index));
    }
    public String clickDeletedStatusSwitch(int _index) {
        return tryRedirectSt(deletedStatusSwitch.get(_index));
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
        String status_ = beginRoundStatus.get(_index);
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
        String status_ = beginRoundStatus.get(_index);
        DataBase data_ = getDataBase();
        Status st_ = data_.getStatus(status_);
        StatusBeginRound s_ = (StatusBeginRound) st_;
        return !s_.getLawForFullHealIfMove().isEmpty();
    }
    public String getTrBeginRoundStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(beginRoundStatus.get(_index));
    }
    public String clickBeginRoundStatus(int _index) {
        return tryRedirectSt(beginRoundStatus.get(_index));
    }
    public String getTrAutoDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(autoDamage.getKey(_index));
    }
    public String clickAutoDamage(int _index) {
        return tryRedirectSt(autoDamage.getKey(_index));
    }
    public String getTrBeginRoundStatusFoe(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(beginRoundStatusFoe.get(_index));
    }
    public String clickBeginRoundStatusFoe(int _index) {
        return tryRedirectSt(beginRoundStatusFoe.get(_index));
    }
    public String getTrSuccessfulStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(successfulStatus.get(_index));
    }
    public String clickSuccessfulStatus(int _index) {
        return tryRedirectSt(successfulStatus.get(_index));
    }
    public String getTrStatusDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(statusDamage.get(_index));
    }
    public String clickStatusDamage(int _index) {
        return tryRedirectSt(statusDamage.get(_index));
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
        String status_ = statusMultStat.get(_index);
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
        String status_ = statusMultStat.get(_index);
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
        String status_ = statusMultStat.get(_index);
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
        String status_ = statusMultStat.get(_index);
        DataBase data_ = getDataBase();
        Status s_ = data_.getStatus(status_);
        return s_.getMultStat().contains(Statistic.ACCURACY);
    }
    public String getTrStatusMultStat(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(statusMultStat.get(_index));
    }
    public String clickStatusMultStat(int _index) {
        return tryRedirectSt(statusMultStat.get(_index));
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
        StringList combo_ = comboMultStat.get(_index);
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
        StringList combo_ = comboMultStat.get(_index);
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
        StringList combo_ = comboMultStat.get(_index);
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
        StringList combo_ = comboMultStat.get(_index);
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
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_ = new StringList();
        for (String m: comboMultStat.get(_index)) {
            moves_.add(translatedMoves_.getVal(m));
        }
        return StringUtil.join(moves_, CST_SEP_DASH);
    }
    public String clickComboMultStat(int _index) {
        getForms().put(CST_COMBO, comboMultStat.get(_index));
        return PkScriptPages.REN_ADD_WEB_HTML_COMBO_COMBOS_HTML;
    }
    public String getTrComboEvtStat(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_ = new StringList();
        for (String m: comboEvtStat.get(_index)) {
            moves_.add(translatedMoves_.getVal(m));
        }
        return StringUtil.join(moves_, CST_SEP_DASH);
    }
    public String clickComboEvtStat(int _index) {
        getForms().put(CST_COMBO, comboEvtStat.get(_index));
        return PkScriptPages.REN_ADD_WEB_HTML_COMBO_COMBOS_HTML;
    }
    public boolean nextRowAfter(int _index) {
        if (_index == IndexConstants.FIRST_INDEX) {
            return true;
        }
        TypesDuo types_ = efficiency.getKey(_index);
        TypesDuo typesNext_ = efficiency.getKey(_index - 1);
        return !StringUtil.quickEq(types_.getPokemonType(),typesNext_.getPokemonType());
    }
    public String getEfficiency(int _def, int _off) {
        int i_ = _def * types.size() + _off;
        return efficiency.getValue(i_).toNumberString();
    }
    public String getTrDefaultMove() {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(defaultMove);
    }
    public String clickDefaultMove() {
        return tryRedirectMv(defaultMove);
    }

    public long getDefaultBoostValue() {
        return defaultBoostValue;
    }

    public StringList getPrivatingMoves() {
        return privatingMoves;
    }

    public StringList getMovesHealingSubstitute() {
        return movesHealingSubstitute;
    }

    public StringList getAbilitiesSentBeginOther() {
        return abilitiesSentBeginOther;
    }

    public StringList getAbilitiesSentBeginWeather() {
        return abilitiesSentBeginWeather;
    }

    public StringList getItemsSentBeginWeather() {
        return itemsSentBeginWeather;
    }

    public StringList getItemsSentBeginOther() {
        return itemsSentBeginOther;
    }

    public StringList getChangingTypesAbilities() {
        return changingTypesAbilities;
    }

    public StringList getCopyAbilities() {
        return copyAbilities;
    }

    public StringList getAbilitiesSentStatis() {
        return abilitiesSentStatis;
    }

    public StringList getSubstitutingMoves() {
        return substitutingMoves;
    }

    public StringList getAbilitiesPrio() {
        return abilitiesPrio;
    }

    public StringList getSlowAbilities() {
        return slowAbilities;
    }

    public StringList getSlowItems() {
        return slowItems;
    }

    public StringList getReverseSpeedMoves() {
        return reverseSpeedMoves;
    }

    public StringList getBerrySpeed() {
        return berrySpeed;
    }

    public StringList getItemSpeed() {
        return itemSpeed;
    }

    public StringList getAbilitiesSwitch() {
        return abilitiesSwitch;
    }

    public StringList getDeletedStatusSwitch() {
        return deletedStatusSwitch;
    }

    public StringList getEntryHazard() {
        return entryHazard;
    }

    public StringList getBeginRoundStatus() {
        return beginRoundStatus;
    }

    public StringList getDeleteStatusMove() {
        return deleteStatusMove;
    }

    public StringList getImmuStatusAbility() {
        return immuStatusAbility;
    }

    public CustList<String> getAutoDamage() {
        return autoDamage.getKeys();
    }

    public NatStringTreeMap<String> getMapAutoDamage() {
        return mapAutoDamage;
    }

    public StringList getPrepaRoundMoves() {
        return prepaRoundMoves;
    }

    public StringList getSpeedPreparingItems() {
        return speedPreparingItems;
    }

    public StringList getDisappearingRoundMoves() {
        return disappearingRoundMoves;
    }

    public StringList getRechargeMoves() {
        return rechargeMoves;
    }

    public StringList getImmuRecharging() {
        return immuRecharging;
    }

    public StringList getMovesInvoking() {
        return movesInvoking;
    }

    public StringList getCopyMoveTypesAb() {
        return copyMoveTypesAb;
    }

    public StringList getMovesThieving() {
        return movesThieving;
    }

    public StringList getMovesSecEffItems() {
        return movesSecEffItems;
    }

    public StringList getMovesAttracting() {
        return movesAttracting;
    }

    public StringList getBeginRoundStatusFoe() {
        return beginRoundStatusFoe;
    }

    public StringList getPressureAbilities() {
        return pressureAbilities;
    }

    public StringList getProtectAbilities() {
        return protectAbilities;
    }

    public StringList getProtectItems() {
        return protectItems;
    }

    public StringList getProtectMoves() {
        return protectMoves;
    }

    public StringList getEffMoves() {
        return effMoves;
    }

    public StringList getMovesMirror() {
        return movesMirror;
    }

    public StringList getAbilitiesPartStatis() {
        return abilitiesPartStatis;
    }

    public StringList getMovesTeam() {
        return movesTeam;
    }

    public StringList getAbilitiesFighterStatisVar() {
        return abilitiesFighterStatisVar;
    }

    public StringList getAbilitiesRateStatis() {
        return abilitiesRateStatis;
    }

    public CustList<StringList> getComboEvtStat() {
        return comboEvtStat;
    }

    public StringList getAbilitiesFighterStatis() {
        return abilitiesFighterStatis;
    }

    public StringList getItemsFighterStatis() {
        return itemsFighterStatis;
    }

    public StringList getSuccessfulStatus() {
        return successfulStatus;
    }

    public StringList getGlobalMovesStatus() {
        return globalMovesStatus;
    }

    public StringList getAbilitiesPartStatus() {
        return abilitiesPartStatus;
    }

    public StringList getAbilitiesFighterStatus() {
        return abilitiesFighterStatus;
    }

    public StringList getItemsFighterStatus() {
        return itemsFighterStatus;
    }

    public StringMap<AbsBasicTreeMap<Rate,Rate>> getLawsRates() {
        return lawsRates;
    }

    public StringList getMovesProtAgainstKo() {
        return movesProtAgainstKo;
    }

    public Rate getMinHpNotKo() {
        return minHpNotKo;
    }

    public StringList getItemsProtAgainstKo() {
        return itemsProtAgainstKo;
    }

    public StringList getMovesCannotKo() {
        return movesCannotKo;
    }

    public StringList getItemsAbs() {
        return itemsAbs;
    }

    public StringList getAbilitiesRevAbs() {
        return abilitiesRevAbs;
    }

    public StringList getAbilitiesDamageStatis() {
        return abilitiesDamageStatis;
    }

    public StringList getAbilitiesChangingTypesDamage() {
        return abilitiesChangingTypesDamage;
    }

    public StringList getAbilitiesTakingItem() {
        return abilitiesTakingItem;
    }

    public StringList getAbilitiesStatisVarUser() {
        return abilitiesStatisVarUser;
    }

    public StringList getAbilitiesStatus() {
        return abilitiesStatus;
    }

    public StringList getAbilitiesCopyAb() {
        return abilitiesCopyAb;
    }

    public StringList getRecoilItems() {
        return recoilItems;
    }

    public StringList getRecoilAbilities() {
        return recoilAbilities;
    }

    public StringList getAbilitiesKoTarget() {
        return abilitiesKoTarget;
    }

    public StringList getMovesKoTarget() {
        return movesKoTarget;
    }

    public StringList getBerryUser() {
        return berryUser;
    }

    public StringList getBerryTarget() {
        return berryTarget;
    }

    public StringList getAbilitiesEndRound() {
        return abilitiesEndRound;
    }

    public StringList getBerryEndRound() {
        return berryEndRound;
    }

    public StringList getMovesChangingAttOrder() {
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

    public StringList getDamagingMoves() {
        return damagingMoves;
    }

    public StringList getItemsUserPower() {
        return itemsUserPower;
    }

    public StringList getMovesUserPower() {
        return movesUserPower;
    }

    public StringList getMovesTargetPower() {
        return movesTargetPower;
    }

    public StringList getAbilitiesUserPower() {
        return abilitiesUserPower;
    }

    public StringList getMovesUserAllyDamage() {
        return movesUserAllyDamage;
    }

    public StringList getAbilitiesTargetDamage() {
        return abilitiesTargetDamage;
    }

    public StringList getMovesTargetTeamDamage() {
        return movesTargetTeamDamage;
    }

    public StringList getAbilitiesUserIgnTargetTeam() {
        return abilitiesUserIgnTargetTeam;
    }

    public StringList getAbilitiesGlobal() {
        return abilitiesGlobal;
    }

    public StringList getMovesGlobal() {
        return movesGlobal;
    }

    public StringList getItemsUserDamage() {
        return itemsUserDamage;
    }

    public StringList getAbilitiesUserDamage() {
        return abilitiesUserDamage;
    }

    public StringList getMovesInvokDamage() {
        return movesInvokDamage;
    }

    public StringList getItemsTargetDamage() {
        return itemsTargetDamage;
    }

    public StringList getMovesGlobalPrepaDamage() {
        return movesGlobalPrepaDamage;
    }

    public StringList getStatusDamage() {
        return statusDamage;
    }

    public StringList getAbilitiesUserTargetDamage() {
        return abilitiesUserTargetDamage;
    }

    public StringList getAbilitiesUserStabDamage() {
        return abilitiesUserStabDamage;
    }

    public StringList getMovesTypesDefItem() {
        return movesTypesDefItem;
    }

    public StringList getItemsTypesDef() {
        return itemsTypesDef;
    }

    public StringList getMovesTypesDefWeather() {
        return movesTypesDefWeather;
    }

    public StringList getAbilitiesTypeDefMoves() {
        return abilitiesTypeDefMoves;
    }

    public StringList getMovesTypeDefMoves() {
        return movesTypeDefMoves;
    }

    public StringList getMovesChangeTypeMoves() {
        return movesChangeTypeMoves;
    }

    public StringList getMovesGlobalBreakImmu() {
        return movesGlobalBreakImmu;
    }

    public StringList getMovesUnprotectingTypes() {
        return movesUnprotectingTypes;
    }

    public StringList getAbilitiesBreakImmu() {
        return abilitiesBreakImmu;
    }

    public StringList getItemsCancelImmu() {
        return itemsCancelImmu;
    }

    public StringList getTypes() {
        return types;
    }

    public DictionaryComparator<TypesDuo,Rate> getEfficiency() {
        return efficiency;
    }

    public StringList getMovesIgnLowAtt() {
        return movesIgnLowAtt;
    }

    public StringList getMovesIgnIncDef() {
        return movesIgnIncDef;
    }

    public StringList getAbilitiesBoostingStat() {
        return abilitiesBoostingStat;
    }

    public StringList getItemsBoostingStat() {
        return itemsBoostingStat;
    }

    public StringList getItemsMultStat() {
        return itemsMultStat;
    }

    public StringList getAbilitiesMultStat() {
        return abilitiesMultStat;
    }

    public StringList getMovesGlobalMultStat() {
        return movesGlobalMultStat;
    }

    public StringList getMovesTeamMultStat() {
        return movesTeamMultStat;
    }

    public StringList getAbilitiesAllyMultStat() {
        return abilitiesAllyMultStat;
    }

    public StringList getMovesFoeTeamMultStat() {
        return movesFoeTeamMultStat;
    }

    public StringList getStatusMultStat() {
        return statusMultStat;
    }

    public StringList getAbilitiesImmuMultStat() {
        return abilitiesImmuMultStat;
    }

    public CustList<StringList> getComboMultStat() {
        return comboMultStat;
    }

    public StringList getAbilitiesBreakProtectMoves() {
        return abilitiesBreakProtectMoves;
    }

    public StringList getMovesIgnAcc() {
        return movesIgnAcc;
    }

    public StringList getMovesIgnEva() {
        return movesIgnEva;
    }

    public StringList getMovesGlobalAcc() {
        return movesGlobalAcc;
    }

    public StringList getAbilitiesImmuCh() {
        return abilitiesImmuCh;
    }

    public StringList getMovesBoostCh() {
        return movesBoostCh;
    }

    public StringList getAbilitesMultEvtCh() {
        return abilitesMultEvtCh;
    }

    public StringList getAbilitesMultRateCh() {
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

    public StringList getMovesProtectingTypes() {
        return movesProtectingTypes;
    }

    public StringList getMovesGlobalBreakImmuAb() {
        return movesGlobalBreakImmuAb;
    }

    public StringList getAbilitiesBreakable() {
        return abilitiesBreakable;
    }

    public StringList getAbilitiesImmuTypes() {
        return abilitiesImmuTypes;
    }

    public StringList getItemsImmuTypes() {
        return itemsImmuTypes;
    }

    public StringList getAbilitiesImmuAllies() {
        return abilitiesImmuAllies;
    }

    public StringList getAbilitiesImmuAlliesDam() {
        return abilitiesImmuAlliesDam;
    }

    public StringList getAbilitiesImmu() {
        return abilitiesImmu;
    }

    public StringList getItemsImmu() {
        return itemsImmu;
    }

    public StringList getAbilitiesImmuSecEffOther() {
        return abilitiesImmuSecEffOther;
    }

    public StringList getAbilitiesImmuSecEffOwner() {
        return abilitiesImmuSecEffOwner;
    }

    public StringList getAbilitiesAchieveTarget() {
        return abilitiesAchieveTarget;
    }

    public StringList getMovesProtecting() {
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