package aiki.beans.help;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorStringList;
import aiki.beans.facade.comparators.ComparatorTypesDuo;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectAlly;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectMultSufferedMovePower;
import aiki.fight.moves.effects.EffectMultUsedMovePower;
import aiki.fight.moves.effects.EffectOrder;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import aiki.fight.moves.effects.EffectSwitchPointView;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.status.Status;
import aiki.fight.status.StatusBeginRound;
import aiki.fight.status.StatusBeginRoundAutoDamage;
import aiki.fight.status.StatusType;
import aiki.fight.status.effects.EffectPartnerStatus;
import aiki.fight.util.StatisticCategory;
import aiki.fight.util.StatisticPokemon;
import aiki.fight.util.StatisticStatus;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypesDuo;
import aiki.game.fight.Fight;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.bean.Accessible;
import code.images.ConverterBufferedImage;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.EnumList;
import code.util.EqList;
import code.util.NatCmpTreeMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.ComparatorEnum;
import code.util.ints.Listable;

public class FightHelpBean extends CommonBean {

    private final String varBoost="b";

    @Accessible
    private StringList abilitiesSentBeginWeather;

    @Accessible
    private StringList abilitiesSentBeginOther;

    @Accessible
    private StringList abilitiesSentCopying;

    @Accessible
    private StringList abilitiesSentStatis;

    @Accessible
    private StringList itemsSentBeginWeather;

    @Accessible
    private StringList itemsSentBeginOther;

    @Accessible
    private StringList changingTypesAbilities;

    @Accessible
    private StringList copyAbilities;

    @Accessible
    private StringList privatingMoves;

    @Accessible
    private StringList movesHealingSubstitute;

    @Accessible
    private StringList substitutingMoves;

    @Accessible
    private StringList abilitiesPrio;

    @Accessible
    private StringList slowAbilities;

    @Accessible
    private StringList slowItems;

    @Accessible
    private StringList reverseSpeedMoves;

    @Accessible
    private StringList berrySpeed;

    @Accessible
    private StringList itemSpeed;

    @Accessible
    private StringList abilitiesSwitch;

    @Accessible
    private StringList deletedStatusSwitch;

    @Accessible
    private StringList entryHazard;

    @Accessible
    private StringList beginRoundStatus;

    @Accessible
    private StringList deleteStatusMove;

    @Accessible
    private StringList immuStatusAbility;

    @Accessible
    private StringList autoDamage;

    @Accessible
    private String damgeFormula;

    @Accessible
    private NatTreeMap<String,String> mapAutoDamage;

    @Accessible
    private StringList prepaRoundMoves;

    @Accessible
    private StringList disappearingRoundMoves;

    @Accessible
    private StringList speedPreparingItems;

    @Accessible
    private StringList rechargeMoves;

    @Accessible
    private StringList immuRecharging;

    @Accessible
    private StringList movesInvoking;

    @Accessible
    private StringList movesThieving;

    @Accessible
    private StringList movesAttracting;

    @Accessible
    private StringList movesMirror;

    @Accessible
    private StringList copyMoveTypesAb;

    @Accessible
    private StringList beginRoundStatusFoe;

    @Accessible
    private StringList movesSecEffItems;

    @Accessible
    private StringList pressureAbilities;

    @Accessible
    private StringList protectAbilities;

    @Accessible
    private StringList protectItems;

    @Accessible
    private StringList protectMoves;

    @Accessible
    private StringList effMoves;

    @Accessible
    private StringList abilitiesPartStatis;

    @Accessible
    private StringList movesTeam;

    @Accessible
    private StringList abilitiesRateStatis;

    @Accessible
    private StringList abilitiesFighterStatis;

    @Accessible
    private StringList itemsFighterStatis;

    @Accessible
    private StringList abilitiesFighterStatisVar;

    @Accessible
    private StringList successfulStatus;

    @Accessible
    private StringList globalMovesStatus;

    @Accessible
    private StringList abilitiesPartStatus;

    @Accessible
    private StringList abilitiesFighterStatus;

    @Accessible
    private StringList itemsFighterStatus;

    @Accessible
    private StringList movesProtAgainstKo;

    @Accessible
    private StringList itemsProtAgainstKo;

    @Accessible
    private StringList movesCannotKo;

    @Accessible
    private StringList itemsAbs;

    @Accessible
    private StringList abilitiesRevAbs;

    @Accessible
    private StringList abilitiesDamageStatis;

    @Accessible
    private StringList abilitiesChangingTypesDamage;

    @Accessible
    private StringList abilitiesTakingItem;

    @Accessible
    private StringList abilitiesStatisVarUser;

    @Accessible
    private StringList abilitiesStatus;

    @Accessible
    private StringList abilitiesCopyAb;

    @Accessible
    private StringList recoilItems;

    @Accessible
    private StringList recoilAbilities;

    @Accessible
    private StringList abilitiesKoTarget;

    @Accessible
    private StringList movesKoTarget;

    @Accessible
    private StringList berryUser;

    @Accessible
    private StringList berryTarget;

    @Accessible
    private StringList abilitiesEndRound;

    @Accessible
    private StringList berryEndRound;

    @Accessible
    private StringList movesChangingAttOrder;

    @Accessible
    private StringList damagingMoves;

    @Accessible
    private StringList itemsUserPower;

    @Accessible
    private StringList movesUserPower;

    @Accessible
    private StringList movesTargetPower;

    @Accessible
    private StringList abilitiesUserPower;

    @Accessible
    private NatTreeMap<String,String> mapVar;

    @Accessible
    private StringList abilitiesTargetDamage;

    @Accessible
    private StringList movesTargetTeamDamage;

    @Accessible
    private StringList abilitiesGlobal;

    @Accessible
    private StringList movesGlobal;

    @Accessible
    private StringList itemsUserDamage;

    @Accessible
    private StringList abilitiesUserDamage;

    @Accessible
    private StringList movesInvokDamage;

    @Accessible
    private StringList itemsTargetDamage;

    @Accessible
    private StringList movesGlobalPrepaDamage;

    @Accessible
    private StringList statusDamage;

    @Accessible
    private StringList abilitiesUserTargetDamage;

    @Accessible
    private StringList abilitiesUserStabDamage;

    @Accessible
    private StringList movesUserAllyDamage;

    @Accessible
    private StringList abilitiesUserIgnTargetTeam;

    @Accessible
    private StringList movesIgnLowAtt;

    @Accessible
    private StringList movesIgnIncDef;

    @Accessible
    private StringList itemsCancelImmu;

    @Accessible
    private StringList movesProtectingTypes;

    @Accessible
    private StringList movesUnprotectingTypes;

    @Accessible
    private StringList movesGlobalBreakImmu;

    @Accessible
    private StringList movesGlobalBreakImmuAb;

    @Accessible
    private StringList abilitiesBreakable;

    @Accessible
    private StringList abilitiesImmuTypes;

    @Accessible
    private StringList itemsImmuTypes;

    @Accessible
    private StringList abilitiesImmuAllies;

    @Accessible
    private StringList abilitiesImmuAlliesDam;

    @Accessible
    private StringList abilitiesImmu;

    @Accessible
    private StringList itemsImmu;

    @Accessible
    private StringList abilitiesImmuSecEffOther;

    @Accessible
    private StringList abilitiesImmuSecEffOwner;

    @Accessible
    private StringList abilitiesAchieveTarget;

    @Accessible
    private StringList abilitiesBreakProtectMoves;

    @Accessible
    private StringList movesProtecting;

    @Accessible
    private StringList movesIgnAcc;

    @Accessible
    private StringList movesIgnEva;

    @Accessible
    private StringList movesGlobalAcc;

    @Accessible
    private StringList abilitiesBoostingStat;

    @Accessible
    private StringList itemsBoostingStat;

    @Accessible
    private StringList abilitiesMultStat;

    @Accessible
    private StringList itemsMultStat;

    @Accessible
    private StringList movesGlobalMultStat;

    @Accessible
    private StringList movesTeamMultStat;

    @Accessible
    private StringList movesFoeTeamMultStat;

    @Accessible
    private StringList abilitiesAllyMultStat;

    @Accessible
    private StringList statusMultStat;

    @Accessible
    private StringList abilitiesImmuMultStat;

    @Accessible
    private EqList<StringList> comboMultStat;

    @Accessible
    private EqList<StringList> comboEvtStat;

    @Accessible
    private StringList movesTypesDefItem;

    @Accessible
    private StringList movesTypesDefWeather;

    @Accessible
    private StringList abilitiesTypeDefMoves;

    @Accessible
    private StringList abilitiesChangeTypeMoves;

    @Accessible
    private StringList movesTypeDefMoves;

    @Accessible
    private StringList movesChangeTypeMoves;

    @Accessible
    private StringList abilitiesBreakImmu;

    @Accessible
    private StringList abilitiesImmuCh;

    @Accessible
    private StringList movesBoostCh;

    @Accessible
    private StringList abilitesMultEvtCh;

    @Accessible
    private StringList abilitesMultRateCh;

    @Accessible
    private String rateFormula;

    @Accessible
    private String rateFormulaCh;

    @Accessible
    private StringList itemsTypesDef;

    @Accessible
    private NatTreeMap<Long,Rate> boosts = new NatTreeMap<Long,Rate>();


    @Accessible
    private NatTreeMap<Long,Rate> boostsCh = new NatTreeMap<Long,Rate>();

    @Accessible
    private TreeMap<TypesDuo,Rate> efficiency;

    @Accessible
    private StringList types;

    @Accessible
    private int maxLevel;

    @Accessible
    private int maxNbMoves;

    @Accessible
    private Rate minHpNotKo;

    @Accessible
    private int maxNbFighters;

    @Accessible
    private Rate wonHappinessPointsLevel;

    @Accessible
    private int defaultBoostValue;

    @Accessible
    private String defaultMove;

    @Accessible
    private String catchingFormula;

    @Accessible
    private NatTreeMap<String,String> varCatchingFormula;

    @Accessible
    private String fleeingFormula;

    @Accessible
    private NatTreeMap<String,String> varFleeingFormula;

    @Accessible
    private int happinessPoints;

    @Accessible
    private Rate strongMove;

    @Accessible
    private TreeMap<DifficultyWinPointsFight, String> rates;

    @Accessible
    private NatTreeMap<String, String> varRates;

    @Accessible
    private TreeMap<DifficultyModelLaw,NatCmpTreeMap<Rate,Rate>> lawsRates;

    @Accessible
    private EnumList<Statistic> statisticAnim;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        wonHappinessPointsLevel = data_.getWonHappinessByGrowLevel();
        happinessPoints = data_.getHappinessEvo();
        maxLevel = data_.getMaxLevel();
        defaultMove = data_.getDefaultMove();
        maxNbFighters = data_.getNbMaxTeam();
        maxNbMoves = data_.getNbMaxMoves();
        defaultBoostValue = data_.getDefaultBoost();
        strongMove = data_.getStrongMovePower();
        StringMap<String> replace_ = new StringMap<String>();
        rateFormula = data_.getRateBoost();
        replace_.put(DataBase.VAR_PREFIX+Fight.BOOST, varBoost);
//        rateFormula = rateFormula.replaceAll(StringList.BOUNDS+DataBase.VAR_PREFIX+Fight.BOOST+StringList.BOUNDS, varBoost);
        rateFormula = StringList.replaceWordsJoin(rateFormula, replace_);
        replace_ = new StringMap<String>();
        rateFormulaCh = data_.getRateBoostCriticalHit();
        replace_.put(DataBase.VAR_PREFIX+Fight.BOOST, varBoost);
//        rateFormulaCh = rateFormulaCh.replaceAll(StringList.BOUNDS+DataBase.VAR_PREFIX+Fight.BOOST+StringList.BOUNDS, varBoost);
        rateFormulaCh = StringList.replaceWordsJoin(rateFormulaCh, replace_);
        long minBoost_ = data_.getMinBoost();
        long maxBoost_ = data_.getMaxBoost();
        initBoosts(minBoost_, maxBoost_);
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        initSendingMembers();
        privatingMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectRestriction)) {
                    continue;
                }
                EffectRestriction e_ = (EffectRestriction) e;
                if (e_.getChoiceRestriction() != MoveChoiceRestrictionType.NOTHING) {
                    privatingMoves.add(m);
                    break;
                }
            }
        }
        privatingMoves.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesHealingSubstitute = new StringList(data_.getMovesFullHeal());
        movesHealingSubstitute.sortElts(new ComparatorTrStrings(translatedMoves_));
        substitutingMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_.getSwitchType() == SwitchType.LANCEUR) {
                substitutingMoves.add(m);
            }
        }
        substitutingMoves.sortElts(new ComparatorTrStrings(translatedMoves_));
        initSpeedElements();
        initSwitchingMembers();
        initBeginRoundStatusMembers();
        initBeginRoundPreparingMembers();
        initInvokingMembers();
        copyMoveTypesAb = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isCopyMovesTypes()) {
                continue;
            }
            copyMoveTypesAb.add(a);
        }
        copyMoveTypesAb.sortElts(new ComparatorTrStrings(translatedAbilities_));
        beginRoundStatusFoe = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_.getStatusType() == StatusType.INDIVIDUEL) {
                continue;
            }
            if (!(st_ instanceof StatusBeginRound)) {
                continue;
            }
            beginRoundStatusFoe.add(s);
        }
        beginRoundStatusFoe.sortElts(new ComparatorTrStrings(translatedStatus_));
        movesSecEffItems = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_.getSecEffectsByItem().isEmpty()) {
                continue;
            }
            movesSecEffItems.add(m);
        }
        movesSecEffItems.sortElts(new ComparatorTrStrings(translatedMoves_));
        pressureAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getNbUsedPp() <= 0) {
                continue;
            }
            pressureAbilities.add(a);
        }
        pressureAbilities.sortElts(new ComparatorTrStrings(translatedAbilities_));
        initProtectingMembers();
        effMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (!move_.getSecEffectIfNoDamage()) {
                continue;
            }
            effMoves.add(m);
        }
        effMoves.sortElts(new ComparatorTrStrings(translatedMoves_));
        abilitiesPartStatis = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getImmuLowStatisTypes().isEmpty()) {
                continue;
            }
            abilitiesPartStatis.add(a);
        }
        abilitiesPartStatis.sortElts(new ComparatorTrStrings(translatedAbilities_));
        movesTeam = new StringList(data_.getMovesEffectTeam());
        movesTeam.sortElts(new ComparatorTrStrings(translatedMoves_));
        abilitiesRateStatis = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultEvtRateSecEffectOwner().isZero()) {
                continue;
            }
            abilitiesRateStatis.add(a);
        }
        abilitiesRateStatis.sortElts(new ComparatorTrStrings(translatedAbilities_));
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
        movesTypesDefItem = new StringList();
        itemsTypesDef = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_.getTypesByOwnedItem().isEmpty()) {
                continue;
            }
            itemsTypesDef.addAllElts(move_.getTypesByOwnedItem().getKeys());
            movesTypesDefItem.add(m);
        }
        movesTypesDefItem.sortElts(new ComparatorTrStrings(translatedMoves_));
        itemsTypesDef.removeDuplicates();
        itemsTypesDef.removeObj(DataBase.EMPTY_STRING);
        itemsTypesDef.sortElts(new ComparatorTrStrings(translatedItems_));
        movesTypesDefWeather = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_.getTypesByWeather().isEmpty()) {
                continue;
            }
            movesTypesDefWeather.add(m);
        }
        movesTypesDefWeather.sortElts(new ComparatorTrStrings(translatedMoves_));
        abilitiesTypeDefMoves = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getTypeForMoves().isEmpty()) {
                continue;
            }
            abilitiesTypeDefMoves.add(a);
        }
        abilitiesTypeDefMoves.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesChangeTypeMoves = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getChangingBoostTypes().isEmpty()) {
                continue;
            }
            abilitiesChangeTypeMoves.add(a);
        }
        abilitiesChangeTypeMoves.sortElts(new ComparatorTrStrings(translatedAbilities_));
        movesTypeDefMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectSwitchMoveTypes)) {
                    continue;
                }
                EffectSwitchMoveTypes e_ = (EffectSwitchMoveTypes) e;
                if (e_.getReplacingTypes().isEmpty()) {
                    continue;
                }
                movesTypeDefMoves.add(m);
                break;
            }
        }
        movesTypeDefMoves.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesChangeTypeMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectSwitchMoveTypes)) {
                    continue;
                }
                EffectSwitchMoveTypes e_ = (EffectSwitchMoveTypes) e;
                if (e_.getChangeTypes().isEmpty()) {
                    continue;
                }
                movesChangeTypeMoves.add(m);
                break;
            }
        }
        movesChangeTypeMoves.sortElts(new ComparatorTrStrings(translatedMoves_));
        abilitiesBreakImmu = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getBreakFoeImmune().isEmpty()) {
                continue;
            }
            abilitiesBreakImmu.add(a);
        }
        abilitiesBreakImmu.sortElts(new ComparatorTrStrings(translatedAbilities_));
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
        efficiency = new TreeMap<TypesDuo, Rate>(new ComparatorTypesDuo(data_, getLanguage(), true, true));
        types = new StringList();
        for (TypesDuo t: data_.getTableTypes().getKeys()) {
            TypesDuo t_ = new TypesDuo();
            t_.setPokemonType(translatedTypes_.getVal(t.getPokemonType()));
            t_.setDamageType(translatedTypes_.getVal(t.getDamageType()));
            efficiency.put(t_, data_.getTableTypes().getVal(t));
            types.add(translatedTypes_.getVal(t.getDamageType()));
        }
        types.removeDuplicates();
        types.sort();
        initFormulaElements();
    }

    private void initAccuracyEvasinessElements() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        movesIgnAcc = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (!move_.getIgnVarAccurUserNeg()) {
                continue;
            }
            if (move_.getTargetChoice() == TargetChoice.LANCEUR) {
                continue;
            }
            movesIgnAcc.add(m);
        }
        movesIgnAcc.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesIgnEva = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (!move_.getIgnVarEvasTargetPos()) {
                continue;
            }
            if (move_.getTargetChoice() == TargetChoice.LANCEUR) {
                continue;
            }
            movesIgnEva.add(m);
        }
        movesIgnEva.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesGlobalAcc = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal e_ = (EffectGlobal) e;
                if (e_.getMultAccuracy().isZero()) {
                    continue;
                }
                movesGlobalAcc.add(m);
                break;
            }
        }
        movesGlobalAcc.sortElts(new ComparatorTrStrings(translatedMoves_));
    }

    private void initStatusElements() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        successfulStatus = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_.getEffectsPartner().isEmpty()) {
                continue;
            }
            if (!st_.getEffectsPartner().first().getWeddingAlly()) {
                continue;
            }
            successfulStatus.add(s);
        }
        successfulStatus.sortElts(new ComparatorTrStrings(translatedStatus_));
        globalMovesStatus = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal e_ = (EffectGlobal) e;
                if (!e_.getPreventStatus().isEmpty()) {
                    globalMovesStatus.add(m);
                }
            }
        }
        globalMovesStatus.sortElts(new ComparatorTrStrings(translatedMoves_));
        abilitiesPartStatus = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getImmuStatusTypes().isEmpty()) {
                continue;
            }
            abilitiesPartStatus.add(a);
        }
        abilitiesPartStatus.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesFighterStatus = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getImmuStatus().isEmpty()) {
                continue;
            }
            abilitiesFighterStatus.add(a);
        }
        abilitiesFighterStatus.sortElts(new ComparatorTrStrings(translatedAbilities_));
        itemsFighterStatus = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (i_.getImmuStatus().isEmpty()) {
                continue;
            }
            itemsFighterStatus.add(i);
        }
        itemsFighterStatus.sortElts(new ComparatorTrStrings(translatedItems_));
    }

    private void initCriticalHitElements() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        abilitiesImmuCh = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isImmuCh()) {
                continue;
            }
            abilitiesImmuCh.add(a);
        }
        abilitiesImmuCh.sortElts(new ComparatorTrStrings(translatedAbilities_));
        movesBoostCh = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectDamage)) {
                    continue;
                }
                EffectDamage e_ = (EffectDamage) e;
                if (e_.getChRate() <= 0) {
                    continue;
                }
                movesBoostCh.add(m);
                break;
            }
        }
        movesBoostCh.sortElts(new ComparatorTrStrings(translatedMoves_));
        abilitesMultEvtCh = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultEvtRateCh().isZero()) {
                continue;
            }
            abilitesMultEvtCh.add(a);
        }
        abilitesMultEvtCh.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitesMultRateCh = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultDamageCh().isZero()) {
                continue;
            }
            abilitesMultRateCh.add(a);
        }
        abilitesMultRateCh.sortElts(new ComparatorTrStrings(translatedAbilities_));
    }

    private void initStatisticsCalculationElements() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        abilitiesBoostingStat = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getBonusStatRank().isEmpty()) {
                continue;
            }
            abilitiesBoostingStat.add(a);
        }
        abilitiesBoostingStat.sortElts(new ComparatorTrStrings(translatedAbilities_));
        itemsBoostingStat = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (i_.getMultStatRank().isEmpty()) {
                if (i_.getMultStatPokemonRank().isEmpty()) {
                    continue;
                }
            }
            itemsBoostingStat.add(i);
        }
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof Berry)) {
                continue;
            }
            Berry i_ = (Berry) it_;
            if (i_.getMultStat().isEmpty()) {
                continue;
            }
            itemsBoostingStat.add(i);
        }
        itemsBoostingStat.sortElts(new ComparatorTrStrings(translatedItems_));
        abilitiesMultStat = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultStat().isEmpty()) {
                if (ab_.getMultStatIfCat().isEmpty()) {
                    continue;
                }
            }
            abilitiesMultStat.add(a);
        }
        abilitiesMultStat.sortElts(new ComparatorTrStrings(translatedAbilities_));
        itemsMultStat = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (i_.getMultStat().isEmpty()) {
                continue;
            }
            itemsMultStat.add(i);
        }
        itemsMultStat.sortElts(new ComparatorTrStrings(translatedAbilities_));
        movesGlobalMultStat = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal e_ = (EffectGlobal) e;
                if (e_.getMultStatIfContainsType().isEmpty()) {
                    continue;
                }
                movesGlobalMultStat.add(m);
                break;
            }
        }
        movesGlobalMultStat.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesTeamMultStat = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectTeam)) {
                    continue;
                }
                EffectTeam e_ = (EffectTeam) e;
                if (e_.getMultStatistic().isEmpty()) {
                    continue;
                }
                movesTeamMultStat.add(m);
                break;
            }
        }
        movesTeamMultStat.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesFoeTeamMultStat = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectTeam)) {
                    continue;
                }
                EffectTeam e_ = (EffectTeam) e;
                if (e_.getMultStatisticFoe().isEmpty()) {
                    continue;
                }
                movesFoeTeamMultStat.add(m);
                break;
            }
        }
        movesFoeTeamMultStat.sortElts(new ComparatorTrStrings(translatedMoves_));
        abilitiesAllyMultStat = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultStatAlly().isEmpty()) {
                continue;
            }
            abilitiesAllyMultStat.add(a);
        }
        abilitiesAllyMultStat.sortElts(new ComparatorTrStrings(translatedAbilities_));
        statusMultStat = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_.getStatusType() == StatusType.RELATION_UNIQUE) {
                continue;
            }
            if (st_.getMultStat().isEmpty()) {
                continue;
            }
            statusMultStat.add(s);
        }
        statusMultStat.sortElts(new ComparatorTrStrings(translatedStatus_));
        abilitiesImmuMultStat = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getImmuLowStatIfStatus().isEmpty()) {
                continue;
            }
            abilitiesImmuMultStat.add(a);
        }
        abilitiesImmuMultStat.sortElts(new ComparatorTrStrings(translatedAbilities_));
        comboMultStat = new EqList<StringList>();
        for (StringList g: data_.getCombos().getEffects().getKeys()) {
            EffectCombo effect_ = data_.getCombos().getEffects().getVal(g);
            if (!effect_.estActifEquipe()) {
                continue;
            }
            comboMultStat.add(new StringList(g));
        }
        for (StringList v: comboMultStat) {
            v.sortElts(new ComparatorTrStrings(translatedMoves_));
        }
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
        comboEvtStat = new EqList<StringList>();
        for (StringList g: data_.getCombos().getEffects().getKeys()) {
            EffectCombo effect_ = data_.getCombos().getEffects().getVal(g);
            if (effect_.getMultEvtRateSecEff().isZero()) {
                continue;
            }
            comboEvtStat.add(new StringList(g));
        }
        for (StringList v: comboEvtStat) {
            v.sortElts(new ComparatorTrStrings(translatedMoves_));
        }
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

    private void initSuccessEffectsElements() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        itemsCancelImmu = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (!i_.getCancelImmuType()) {
                continue;
            }
            itemsCancelImmu.add(i);
        }
        itemsCancelImmu.sortElts(new ComparatorTrStrings(translatedItems_));
        movesProtectingTypes = new StringList(data_.getMovesEffectProt());
        movesProtectingTypes.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesUnprotectingTypes = new StringList(data_.getMovesEffectUnprot());
        movesUnprotectingTypes.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesGlobalBreakImmu = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal e_ = (EffectGlobal) e;
                if (e_.getEfficiencyMoves().isEmpty()) {
                    continue;
                }
                movesGlobalBreakImmu.add(m);
                break;
            }
        }
        movesGlobalBreakImmu.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesGlobalBreakImmuAb = new StringList();
        abilitiesBreakable = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal e_ = (EffectGlobal) e;
                if (e_.getCancelProtectingAbilities().isEmpty()) {
                    continue;
                }
                abilitiesBreakable.addAllElts(e_.getCancelProtectingAbilities());
                movesGlobalBreakImmuAb.add(m);
                break;
            }
        }
        movesGlobalBreakImmuAb.sortElts(new ComparatorTrStrings(translatedMoves_));
        abilitiesBreakable.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesImmuTypes = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getImmuMoveTypesByWeather().isEmpty()) {
                continue;
            }
            abilitiesImmuTypes.add(a);
        }
        abilitiesImmuTypes.sortElts(new ComparatorTrStrings(translatedAbilities_));
        itemsImmuTypes = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (i_.getImmuTypes().isEmpty()) {
                continue;
            }
            itemsImmuTypes.add(i);
        }
        itemsImmuTypes.sortElts(new ComparatorTrStrings(translatedItems_));
        abilitiesImmuAllies = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getImmuAllyFromMoves().isEmpty()) {
                continue;
            }
            abilitiesImmuAllies.add(a);
        }
        abilitiesImmuAllies.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesImmuAlliesDam = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isImmuDamageAllyMoves()) {
                continue;
            }
            abilitiesImmuAlliesDam.add(a);
        }
        abilitiesImmuAlliesDam.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesImmu = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getImmuMove().isEmpty()) {
                if (!ab_.isImmuSufferedDamageLowEff()) {
                    continue;
                }
            }
            abilitiesImmu.add(a);
        }
        abilitiesImmu.sortElts(new ComparatorTrStrings(translatedAbilities_));
        itemsImmu = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (i_.getImmuMoves().isEmpty()) {
                continue;
            }
            itemsImmu.add(i);
        }
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof Berry)) {
                continue;
            }
            Berry b_ = (Berry) it_;
            if (b_.getHealHpBySuperEffMove().isZero()) {
                continue;
            }
            itemsImmu.add(i);
        }
        itemsImmu.sortElts(new ComparatorTrStrings(translatedItems_));
        abilitiesImmuSecEffOther = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isCancelSecEffectOther()) {
                continue;
            }
            abilitiesImmuSecEffOther.add(a);
        }
        abilitiesImmuSecEffOther.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesImmuSecEffOwner = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isCancelSecEffectOwner()) {
                continue;
            }
            abilitiesImmuSecEffOwner.add(a);
        }
        abilitiesImmuSecEffOwner.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesAchieveTarget = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isAchievedDisappearedPk()) {
                continue;
            }
            abilitiesAchieveTarget.add(a);
        }
        abilitiesAchieveTarget.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesBreakProtectMoves = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isBreakProtection()) {
                continue;
            }
            abilitiesBreakProtectMoves.add(a);
        }
        abilitiesBreakProtectMoves.sortElts(new ComparatorTrStrings(translatedAbilities_));
        movesProtecting = new StringList();
        movesProtecting.addAllElts(data_.getMovesProtAgainstDamageMoves());
        movesProtecting.addAllElts(data_.getMovesProtAgainstStatusMoves());
        movesProtecting.addAllElts(data_.getMovesProtAgainstMultiTarget());
        movesProtecting.addAllElts(data_.getMovesProtAgainstPrio());
        movesProtecting.addAllElts(data_.getMovesProtSingleTarget());
        movesProtecting.removeDuplicates();
        movesProtecting.sortElts(new ComparatorTrStrings(translatedMoves_));
    }

    private void initDamageCalculationElements() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        movesUserAllyDamage = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectAlly)) {
                    continue;
                }
                EffectAlly e_ = (EffectAlly) e;
                if (e_.getMultAllyDamage().isZero()) {
                    continue;
                }
                movesUserAllyDamage.add(m);
                break;
            }
        }
        movesUserAllyDamage.sortElts(new ComparatorTrStrings(translatedMoves_));
        abilitiesTargetDamage = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultDamageFoe().isEmpty()) {
                if (ab_.getMultSufferedDamageSuperEff().isZero()) {
                    continue;
                }
            }
            abilitiesTargetDamage.add(a);
        }
        abilitiesTargetDamage.sortElts(new ComparatorTrStrings(translatedAbilities_));
        movesTargetTeamDamage = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectTeam)) {
                    continue;
                }
                EffectTeam e_ = (EffectTeam) e;
                if (e_.getMultDamage().isEmpty()) {
                    continue;
                }
                movesTargetTeamDamage.add(m);
                break;
            }
        }
        movesTargetTeamDamage.sortElts(new ComparatorTrStrings(translatedMoves_));
        abilitiesUserIgnTargetTeam = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getIgnFoeTeamMove().isEmpty()) {
                continue;
            }
            abilitiesUserIgnTargetTeam.add(a);
        }
        abilitiesUserIgnTargetTeam.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesGlobal = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultPowerMovesTypesGlobal().isEmpty()) {
                if (!ab_.isReverseEffectsPowerMovesTypesGlobal()) {
                    continue;
                }
            }
            abilitiesGlobal.add(a);
        }
        abilitiesGlobal.sortElts(new ComparatorTrStrings(translatedAbilities_));
        movesGlobal = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal e_ = (EffectGlobal) e;
                if (e_.getMultPowerMoves().isEmpty()) {
                    if (e_.getMultDamageTypesMoves().isEmpty()) {
                        continue;
                    }
                }
                movesGlobal.add(m);
                break;
            }
        }
        movesGlobal.sortElts(new ComparatorTrStrings(translatedMoves_));
        itemsUserDamage = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (i_.getMultDamage().isEmpty()) {
                continue;
            }
            itemsUserDamage.add(i);
        }
        itemsUserDamage.sortElts(new ComparatorTrStrings(translatedItems_));
        abilitiesUserDamage = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultDamage().isEmpty()) {
                continue;
            }
            abilitiesUserDamage.add(a);
        }
        abilitiesUserDamage.sortElts(new ComparatorTrStrings(translatedAbilities_));
        movesInvokDamage = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectInvoke)) {
                    continue;
                }
                EffectInvoke e_ = (EffectInvoke) e;
                if (e_.getRateInvokationMove().isZero()) {
                    continue;
                }
                movesInvokDamage.add(m);
                break;
            }
        }
        movesInvokDamage.sortElts(new ComparatorTrStrings(translatedMoves_));
        itemsTargetDamage = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof Berry)) {
                continue;
            }
            Berry b_ = (Berry) it_;
            if (b_.getMultFoesDamage().isEmpty()) {
                continue;
            }
            itemsTargetDamage.add(i);
        }
        itemsTargetDamage.sortElts(new ComparatorTrStrings(translatedItems_));
        movesGlobalPrepaDamage = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal e_ = (EffectGlobal) e;
                if (e_.getMovesUsedByTargetedFighters().isEmpty()) {
                    continue;
                }
                if (e_.getMultDamagePrepaRound().isEmpty()) {
                    continue;
                }
                movesGlobalPrepaDamage.add(m);
                break;
            }
        }
        movesGlobalPrepaDamage.sortElts(new ComparatorTrStrings(translatedMoves_));
        statusDamage = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_.getStatusType() == StatusType.INDIVIDUEL) {
                continue;
            }
            if(!st_.estActifPartenaire()){
                continue;
            }
            EffectPartnerStatus effetPart_=st_.getEffectsPartner().first();
            if(!effetPart_.getWeddingAlly()){
                continue;
            }
            statusDamage.add(s);
        }
        statusDamage.sortElts(new ComparatorTrStrings(translatedStatus_));
        abilitiesUserTargetDamage = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultAllyDamage().isZero()) {
                continue;
            }
            abilitiesUserTargetDamage.add(a);
        }
        abilitiesUserTargetDamage.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesUserStabDamage = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultStab().isZero()) {
                continue;
            }
            abilitiesUserStabDamage.add(a);
        }
        abilitiesUserStabDamage.sortElts(new ComparatorTrStrings(translatedAbilities_));
        movesIgnLowAtt = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectDamage)) {
                    continue;
                }
                EffectDamage e_ = (EffectDamage) e;
                if (e_.getIgnVarStatUserNeg().isEmpty()) {
                    continue;
                }
                movesIgnLowAtt.add(m);
                break;
            }
        }
        movesIgnLowAtt.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesIgnIncDef = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectDamage)) {
                    continue;
                }
                EffectDamage e_ = (EffectDamage) e;
                if (e_.getIgnVarStatTargetPos().isEmpty()) {
                    continue;
                }
                movesIgnIncDef.add(m);
                break;
            }
        }
        movesIgnIncDef.sortElts(new ComparatorTrStrings(translatedMoves_));
    }

    private void initPowerElements() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        damagingMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectDamage)) {
                    continue;
                }
                damagingMoves.add(m);
            }
        }
        damagingMoves.sortElts(new ComparatorTrStrings(translatedMoves_));
        itemsUserPower = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (i_.getMultPower().isEmpty()) {
                continue;
            }
            itemsUserPower.add(i);
        }
        itemsUserPower.sortElts(new ComparatorTrStrings(translatedItems_));
        movesUserPower = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectMultUsedMovePower)) {
                    continue;
                }
                movesUserPower.add(m);
                break;
            }
        }
        movesUserPower.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesTargetPower = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectMultSufferedMovePower)) {
                    continue;
                }
                movesTargetPower.add(m);
                break;
            }
        }
        movesTargetPower.sortElts(new ComparatorTrStrings(translatedMoves_));
        abilitiesUserPower = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getChangingBoostTypes().isEmpty()) {
                continue;
            }
            abilitiesUserPower.add(a);
        }
        abilitiesUserPower.sortElts(new ComparatorTrStrings(translatedAbilities_));
    }

    private void initWhileDamageElements() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        movesProtAgainstKo = new StringList(data_.getMovesProtSingleTargetAgainstKo());
        movesProtAgainstKo.sortElts(new ComparatorTrStrings(translatedMoves_));
        itemsProtAgainstKo = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (i_.getProtectAgainstKo().isZero()) {
                if (i_.getProtectAgainstKoIfFullHp().isZero()) {
                    continue;
                }
            }
            itemsProtAgainstKo.add(i);
        }
        itemsProtAgainstKo.sortElts(new ComparatorTrStrings(translatedItems_));
        movesCannotKo = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (!(move_ instanceof DamagingMoveData)) {
                continue;
            }
            DamagingMoveData dam_ = (DamagingMoveData) move_;
            if (!dam_.getCannotKo()) {
                continue;
            }
            movesCannotKo.add(m);
        }
        minHpNotKo = data_.getMinHp();
        movesCannotKo.sortElts(new ComparatorTrStrings(translatedMoves_));
        itemsAbs = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (i_.getDrainedHpByDamageRate().isZero()) {
                continue;
            }
            itemsAbs.add(i);
        }
        itemsAbs.sortElts(new ComparatorTrStrings(translatedItems_));
        initWhileDamageAbilities();
        initRecoilMembers();
        abilitiesKoTarget = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultStatIfKoFoe().isEmpty()) {
                continue;
            }
            abilitiesKoTarget.add(a);
        }
        abilitiesKoTarget.sortElts(new ComparatorTrStrings(translatedAbilities_));
        movesKoTarget = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectDamage)) {
                    continue;
                }
                EffectDamage e_ = (EffectDamage) e;
                if (!e_.getBoostStatisOnceKoFoe().isEmpty()) {
                    movesKoTarget.add(m);
                }
            }
        }
        movesKoTarget.sortElts(new ComparatorTrStrings(translatedMoves_));
    }

    private void initWhileDamageAbilities() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        abilitiesRevAbs = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isInflictingDamageInsteadOfSuffering()) {
                continue;
            }
            abilitiesRevAbs.add(a);
        }
        abilitiesRevAbs.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesDamageStatis = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMaxStatisticsIfCh().isEmpty()) {
                if (ab_.getMultStatIfDamgeType().isEmpty()) {
                    if (ab_.getMultStatIfDamageCat().isEmpty()) {
                        continue;
                    }
                }
            }
            abilitiesDamageStatis.add(a);
        }
        abilitiesDamageStatis.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesChangingTypesDamage = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isChgtTypeByDamage()) {
                continue;
            }
            abilitiesChangingTypesDamage.add(a);
        }
        abilitiesChangingTypesDamage.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesTakingItem = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isTakeItemByDamagingMove()) {
                continue;
            }
            abilitiesTakingItem.add(a);
        }
        abilitiesTakingItem.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesStatisVarUser = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getLowStatFoeHit().isEmpty()) {
                continue;
            }
            abilitiesStatisVarUser.add(a);
        }
        abilitiesStatisVarUser.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesStatus = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getSingleStatus().events().isEmpty()) {
                continue;
            }
            abilitiesStatus.add(a);
        }
        abilitiesStatus.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesCopyAb = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isMumy()) {
                continue;
            }
            abilitiesCopyAb.add(a);
        }
        abilitiesCopyAb.sortElts(new ComparatorTrStrings(translatedAbilities_));
    }

    private void initEndRoundUserMembers() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        abilitiesEndRound = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getBoostStatRankEndRound().isEmpty()) {
                continue;
            }
            abilitiesEndRound.add(a);
        }
        abilitiesEndRound.sortElts(new ComparatorTrStrings(translatedAbilities_));
        berryEndRound = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof Berry)) {
                continue;
            }
            Berry b_ = (Berry) it_;
            if (b_.getHealPp() <= 0) {
                continue;
            }
            berryEndRound.add(i);
        }
        berryEndRound.sortElts(new ComparatorTrStrings(translatedAbilities_));
        movesChangingAttOrder = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectOrder)) {
                    continue;
                }
                movesChangingAttOrder.add(m);
            }
        }
        movesChangingAttOrder.sortElts(new ComparatorTrStrings(translatedMoves_));
    }

    private void initBerryEndEffectMembers() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        berryUser = new StringList();
        berryTarget = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof Berry)) {
                continue;
            }
            Berry b_ = (Berry) it_;
            if (b_.getHealHp().isZero()) {
                if (b_.getHealHpRate().isZero()) {
                    continue;
                }
            }
            berryUser.add(i);
            berryTarget.add(i);
        }
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof Berry)) {
                continue;
            }
            Berry b_ = (Berry) it_;
            if (b_.getCategoryBoosting().isEmpty()) {
                continue;
            }
            berryTarget.add(i);
        }
        berryTarget.removeDuplicates();
        berryUser.sortElts(new ComparatorTrStrings(translatedItems_));
        berryTarget.sortElts(new ComparatorTrStrings(translatedItems_));
    }

    private void initRecoilMembers() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        recoilItems = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (i_.getDamageRecoil().isZero()) {
                continue;
            }
            recoilItems.add(i);
        }
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof Berry)) {
                continue;
            }
            Berry b_ = (Berry) it_;
            if (b_.getDamageRateRecoilFoe().isEmpty()) {
                continue;
            }
            recoilItems.add(i);
        }
        recoilItems.sortElts(new ComparatorTrStrings(translatedItems_));
        recoilAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getRecoilDamageFoe().isZero()) {
                continue;
            }
            recoilAbilities.add(a);
        }
        recoilAbilities.sortElts(new ComparatorTrStrings(translatedAbilities_));
    }

    private void initStatisticsImmuElements() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        abilitiesFighterStatis = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getImmuLowStat().isEmpty()) {
                if (ab_.getImmuLowStatIfStatus().isEmpty()) {
                    continue;
                }
            }
            abilitiesFighterStatis.add(a);
        }
        abilitiesFighterStatis.sortElts(new ComparatorTrStrings(translatedAbilities_));
        itemsFighterStatis = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (!i_.getImmuLowStatis()) {
                continue;
            }
            itemsFighterStatis.add(i);
        }
        itemsFighterStatis.sortElts(new ComparatorTrStrings(translatedItems_));
        abilitiesFighterStatisVar = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getMultVarBoost().isZero()) {
                if (ab_.getMultStatIfLowStat().isEmpty()) {
                    continue;
                }
            }
            abilitiesFighterStatisVar.add(a);
        }
        abilitiesFighterStatisVar.sortElts(new ComparatorTrStrings(translatedAbilities_));
    }

    private void initProtectingMembers() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        protectItems = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof Berry)) {
                continue;
            }
            Berry b_ = (Berry) it_;
            if (b_.getHealHpBySuperEffMove().isZero()) {
                continue;
            }
            protectItems.add(i);
        }
        protectItems.sortElts(new ComparatorTrStrings(translatedItems_));
        protectAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getBoostStatRankProtected().isEmpty()) {
                if (ab_.getHealHpByTypeIfWeather().isEmpty()) {
                    continue;
                }
            }
            protectAbilities.add(a);
        }
        protectAbilities.sortElts(new ComparatorTrStrings(translatedAbilities_));
        protectMoves = new StringList(data_.getMovesCountering());
        protectMoves.sortElts(new ComparatorTrStrings(translatedMoves_));
    }

    private void initInvokingMembers() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        movesInvoking = new StringList(data_.getMovesInvoking());
        movesInvoking.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesThieving = new StringList();
        movesAttracting = new StringList();
        movesMirror = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectSwitchPointView)) {
                    continue;
                }
                EffectSwitchPointView eff_ = (EffectSwitchPointView) e;
                if (eff_.getPointViewChangement() == PointViewChangementType.THIEF_BONUSES) {
                    movesThieving.add(m);
                }
                if (eff_.getPointViewChangement() == PointViewChangementType.ATTRACT_DAMAGES_MOVES) {
                    movesAttracting.add(m);
                }
                if (eff_.getPointViewChangement() == PointViewChangementType.MIRROR_AGAINST_THROWER) {
                    movesMirror.add(m);
                }
            }
        }
        movesThieving.removeDuplicates();
        movesThieving.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesAttracting.removeDuplicates();
        movesAttracting.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesMirror.removeDuplicates();
        movesMirror.sortElts(new ComparatorTrStrings(translatedMoves_));
    }

    private void initBeginRoundPreparingMembers() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        prepaRoundMoves = new StringList();
        disappearingRoundMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_.getNbPrepaRound() <= 0) {
                continue;
            }
            if (move_.getDisappearBeforeUse()) {
                disappearingRoundMoves.add(m);
            }
            prepaRoundMoves.add(m);
        }
        prepaRoundMoves.sortElts(new ComparatorTrStrings(translatedMoves_));
        rechargeMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (!move_.getRechargeRound()) {
                continue;
            }
            rechargeMoves.add(m);
        }
        rechargeMoves.sortElts(new ComparatorTrStrings(translatedMoves_));
        disappearingRoundMoves.sortElts(new ComparatorTrStrings(translatedMoves_));
        speedPreparingItems = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (!i_.getAttacksSoon()) {
                continue;
            }
            speedPreparingItems.add(i);
        }
        speedPreparingItems.sortElts(new ComparatorTrStrings(translatedItems_));
        immuRecharging = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isImmuRechargeRound()) {
                continue;
            }
            immuRecharging.add(a);
        }
        immuRecharging.sortElts(new ComparatorTrStrings(translatedAbilities_));
    }

    private void initBeginRoundStatusMembers() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        beginRoundStatus = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_.getStatusType() == StatusType.RELATION_UNIQUE) {
                continue;
            }
            if (!(st_ instanceof StatusBeginRound)) {
                continue;
            }
            beginRoundStatus.add(s);
        }
        beginRoundStatus.sortElts(new ComparatorTrStrings(translatedStatus_));
        deleteStatusMove = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_.getDeletedStatus().isEmpty()) {
                continue;
            }
            deleteStatusMove.add(m);
        }
        deleteStatusMove.sortElts(new ComparatorTrStrings(translatedMoves_));
        immuStatusAbility = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getImmuStatusBeginRound().isEmpty()) {
                continue;
            }
            immuStatusAbility.add(a);
        }
        immuStatusAbility.sortElts(new ComparatorTrStrings(translatedAbilities_));
        autoDamage = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (!(st_ instanceof StatusBeginRoundAutoDamage)) {
                continue;
            }
            autoDamage.add(s);
        }
        autoDamage.sortElts(new ComparatorTrStrings(translatedStatus_));
        damgeFormula = data_.getFormula(data_.getDamageFormula(), getLanguage());
        mapVar = new NatTreeMap<String,String>();
        mapVar.putAllTreeMap(data_.getDescriptions(data_.getDamageFormula(), getLanguage()));
        NatTreeMap<String,String> mapAutoDamage_ = new NatTreeMap<String,String>();
        int len_;
        len_ = autoDamage.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            mapAutoDamage_.putAllTreeMap(data_.getDescriptions(getNumericString((long) i), getLanguage()));
        }
        mapAutoDamage = mapAutoDamage_;
    }

    private void initSwitchingMembers() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        abilitiesSwitch = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getHealedHpRateBySwitch().isZero()) {
                if (!ab_.isHealedStatusBySwitch()) {
                    continue;
                }
            }
            abilitiesSwitch.add(a);
        }
        abilitiesSwitch.sortElts(new ComparatorTrStrings(translatedAbilities_));
        deletedStatusSwitch = new StringList();
        for (String a: data_.getStatus().getKeys()) {
            Status ab_ = data_.getStatus(a);
            if (!ab_.getDisabledEffIfSwitch()) {
                continue;
            }
            deletedStatusSwitch.add(a);
        }
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_.getStatusType() == StatusType.INDIVIDUEL) {
                continue;
            }
            deletedStatusSwitch.add(s);
        }
        deletedStatusSwitch.removeDuplicates();
        deletedStatusSwitch.sortElts(new ComparatorTrStrings(translatedStatus_));
        entryHazard = new StringList(data_.getMovesEffectWhileSending());
        entryHazard.sortElts(new ComparatorTrStrings(translatedMoves_));
    }

    private void initSpeedElements() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        abilitiesPrio = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getIncreasedPrio().isEmpty()) {
                if (ab_.getIncreasedPrioTypes().isEmpty()) {
                    continue;
                }
            }
            abilitiesPrio.add(a);
        }
        abilitiesPrio.sortElts(new ComparatorTrStrings(translatedAbilities_));
        slowAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isSlowing()) {
                continue;
            }
            slowAbilities.add(a);
        }
        slowAbilities.sortElts(new ComparatorTrStrings(translatedAbilities_));
        slowItems = new StringList();
        for (String a: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(a);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (!i_.getAttackLast()) {
                continue;
            }
            slowItems.add(a);
        }
        slowItems.sortElts(new ComparatorTrStrings(translatedItems_));
        reverseSpeedMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectGlobal)) {
                    continue;
                }
                EffectGlobal eff_ = (EffectGlobal) e;
                if (eff_.getReverseOrderOfSortBySpeed()) {
                    reverseSpeedMoves.add(m);
                    break;
                }
            }
        }
        reverseSpeedMoves.sortElts(new ComparatorTrStrings(translatedMoves_));
        berrySpeed = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof Berry)) {
                continue;
            }
            Berry berry_ = (Berry) it_;
            if (!berry_.getLawForAttackFirst()) {
                continue;
            }
            berrySpeed.add(i);
        }
        berrySpeed.sortElts(new ComparatorTrStrings(translatedItems_));
        itemSpeed = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (i_.getLawForAttackFirst().events().isEmpty()) {
                continue;
            }
            itemSpeed.add(i);
        }
        itemSpeed.sortElts(new ComparatorTrStrings(translatedItems_));
    }

    private void initSendingMembers() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        abilitiesSentBeginWeather = new StringList();
        abilitiesSentBeginOther = new StringList();
        abilitiesSentStatis = new StringList();
        copyAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.enabledSending()) {
                continue;
            }
            if (ab_.getEffectSending().first() instanceof EffectWhileSendingWithStatistic) {
                abilitiesSentStatis.add(a);
                continue;
            }
            if (ab_.getEffectSending().first().getCopyingAbility()) {
                copyAbilities.add(a);
                continue;
            }
            if (ab_.getEffectSending().first().getEnabledWeather().isEmpty()) {
                if (!ab_.getEffectSending().first().getDisableWeather()) {
                    abilitiesSentBeginOther.add(a);
                    continue;
                }
            }
            abilitiesSentBeginWeather.add(a);
        }
        abilitiesSentStatis.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesSentBeginOther.sortElts(new ComparatorTrStrings(translatedAbilities_));
        abilitiesSentBeginWeather.sortElts(new ComparatorTrStrings(translatedAbilities_));
        copyAbilities.sortElts(new ComparatorTrStrings(translatedAbilities_));
        itemsSentBeginWeather = new StringList();
        itemsSentBeginOther = new StringList();
        for (String a: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(a);
            if (!(it_ instanceof ItemForBattle)) {
                continue;
            }
            ItemForBattle i_ = (ItemForBattle) it_;
            if (!i_.enabledSending()) {
                continue;
            }
            if (i_.getEffectSending().first().getEnabledWeather().isEmpty()) {
                if (!i_.getEffectSending().first().getDisableWeather()) {
                    itemsSentBeginOther.add(a);
                    continue;
                }
            }
            itemsSentBeginWeather.add(a);
        }
        itemsSentBeginOther.sortElts(new ComparatorTrStrings(translatedItems_));
        itemsSentBeginWeather.sortElts(new ComparatorTrStrings(translatedItems_));
        changingTypesAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.isPlate()) {
                continue;
            }
            changingTypesAbilities.add(a);
        }
        changingTypesAbilities.sortElts(new ComparatorTrStrings(translatedAbilities_));
    }

    private void initBoosts(long _minBoost, long _maxBoost) {
        DataBase data_ = (DataBase) getDataBase();
        for (long b = _minBoost; b <= _maxBoost; b++) {
            String rateBoost_ = data_.getRateBoost();
//            NumericString chNum_=new NumericString(rateBoost_);
            StringMap<String> variables_ = new StringMap<String>();
            variables_.put(DataBase.VAR_PREFIX+Fight.BOOST, Long.toString(b));
//            chNum_.replaceVars(variables_);
//            chNum_.evaluateExp();
//            Rate res_ = chNum_.toRate();
//            boosts.put(b, res_);
            boosts.put(b, data_.evaluateNumericable(rateBoost_, variables_, Rate.one()));
        }
        for (long b = _minBoost; b <= _maxBoost; b++) {
            String rateBoost_ = data_.getRateBoostCriticalHit();
//            NumericString chNum_=new NumericString(rateBoost_);
            StringMap<String> variables_ = new StringMap<String>();
            variables_.put(DataBase.VAR_PREFIX+Fight.BOOST, Long.toString(b));
//            chNum_.replaceVars(variables_);
//            chNum_.evaluateExp();
//            Rate res_ = chNum_.toRate();
//            boostsCh.put(b, res_);
            boostsCh.put(b, data_.evaluateNumericable(rateBoost_, variables_, Rate.one()));
        }
    }

    private void initFormulaElements() {
        DataBase data_ = (DataBase) getDataBase();
        String catchingFormulaCopy_ = data_.getCatchingFormula();
//        StringList tokens_ = StringList.getTokensSeparators(catchingFormulaCopy_, TOKENS_VAR_CATCHING);
        StringList tokens_ = getTokensTranslate(catchingFormulaCopy_);
        int len_ = tokens_.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (i % 2 == 0) {
                continue;
            }
            tokens_.set(i, DataBase.VAR_PREFIX+tokens_.get(i));
        }
        catchingFormulaCopy_ = tokens_.join(DataBase.EMPTY_STRING);
        catchingFormula = data_.getFormula(catchingFormulaCopy_, getLanguage());
        varCatchingFormula = new NatTreeMap<String,String>();
        varCatchingFormula.putAllMap(data_.getDescriptions(catchingFormulaCopy_, getLanguage()));
        fleeingFormula = data_.getFormula(data_.getFleeingFormula(), getLanguage());
        varFleeingFormula = new NatTreeMap<String,String>();
        varFleeingFormula.putAllMap(data_.getDescriptions(data_.getFleeingFormula(), getLanguage()));
        rates = new TreeMap<DifficultyWinPointsFight, String>(new ComparatorEnum<DifficultyWinPointsFight>());
        for (DifficultyWinPointsFight d: data_.getRates().getKeys()) {
            rates.put(d, data_.getFormula(data_.getRates().getVal(d), getLanguage()));
        }
        varRates = new NatTreeMap<String,String>();
        for (DifficultyWinPointsFight d: data_.getRates().getKeys()) {
            varRates.putAllTreeMap(data_.getDescriptions(data_.getRates().getVal(d), getLanguage()));
        }
        lawsRates = new TreeMap<DifficultyModelLaw, NatCmpTreeMap<Rate, Rate>>(new ComparatorEnum<DifficultyModelLaw>());
        for (DifficultyModelLaw d: data_.getLawsDamageRate().getKeys()) {
            NatCmpTreeMap<Rate,Rate> tree_ = new NatCmpTreeMap<Rate, Rate>();
            MonteCarloNumber law_ = data_.getLawsDamageRate().getVal(d).getLaw();
            for (Rate e: law_.events()) {
                tree_.put(e, law_.normalizedRate(e));
            }
            lawsRates.put(d, tree_);
        }
        statisticAnim = Statistic.getStatisticsWithBoost();
    }

    private static StringList getTokensTranslate(String _str) {
        StringList list_ = StringList.getWordsSeparators(_str);
        StringList newList_ = new StringList();
        int i_ = CustList.FIRST_INDEX;
        for (String t : list_) {
            if (i_ % 2 == 0) {
                if (newList_.isEmpty()) {
                    newList_.add(t);
                } else if (!isTokenTranslate(newList_.last())) {
                    newList_.setLast(newList_.last()+t);
                } else {
                    newList_.add(t);
                }
                i_++;
                continue;
            }
            if (isTokenTranslate(t)) {
                newList_.add(t);
                i_++;
                continue;
            }
            newList_.setLast(newList_.last()+t);
            i_++;
        }
        return newList_;
    }

    private static boolean isTokenTranslate(String _string) {
        if (Character.isUpperCase(_string.charAt(CustList.FIRST_INDEX))) {
            int len_ = _string.length();
            for (int i = CustList.SECOND_INDEX; i < len_; i++) {
                if (!Character.isUpperCase(_string.charAt(i))) {
                    if (!Character.isDigit(_string.charAt(i))) {
                        if (_string.charAt(i) != UNDERSCORE) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Accessible
    private String getTrStatistic(Long _index) {
        Statistic d_ = statisticAnim.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        return data_.getTranslatedStatistics().getVal(getLanguage()).getVal(d_);
    }

    @Accessible
    private String getAnimStatistic(Long _index) {
        Statistic d_ = statisticAnim.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        return ConverterBufferedImage.surroundImage(data_.getAnimStatis().getVal(d_.name()));
    }

    @Accessible
    private String getAnimAbsorb() {
        DataBase data_ = (DataBase) getDataBase();
        return ConverterBufferedImage.surroundImage(data_.getAnimAbsorb());
    }

    @Accessible
    private String getTrLawRate(Long _index) {
        DifficultyModelLaw d_ = lawsRates.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        return data_.getTranslatedDiffModelLaw().getVal(getLanguage()).getVal(d_);
    }

    @Accessible
    private String getTrDifficulty(Long _index) {
        DifficultyWinPointsFight diff_ = rates.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
//        return XmlParser.transformSpecialChars(data_.getTranslatedDiffWinPts().getVal(getLanguage()).getVal(diff_));
        return data_.getTranslatedDiffWinPts().getVal(getLanguage()).getVal(diff_);
    }

    @Accessible
    private String getStab() {
        DataBase data_ = (DataBase) getDataBase();
        return data_.getStab().toNumberString();
    }

    @Accessible
    private String getFomula(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        return data_.getFormula(getNumericString(_index), getLanguage());
    }

    private String getNumericString(Long _index) {
        String auto_ = autoDamage.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StatusBeginRoundAutoDamage st_ = (StatusBeginRoundAutoDamage) data_.getStatus(auto_);
        String str_ = data_.getDamageFormula();
        StringMap<String> replace_ = new StringMap<String>();
        replace_.put(DataBase.VAR_PREFIX+Fight.POWER, st_.getPower().toNumberString());
//        str_ = str_.replaceAll(DataBase.VAR_PREFIX+Fight.POWER, st_.getPower().toString());
        str_ = StringList.replaceWordsJoin(str_, replace_);
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
        if (_st == Statistic.SPECIAL_DEFENSE) {
            return true;
        }
        return false;
    }

    @Accessible
    private String getTrAbilitiesSentBegin(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSentBeginWeather.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesSentBegin(Long _index) {
        getForms().put(ABILITY, abilitiesSentBeginWeather.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesSentBeginOth(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSentBeginOther.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesSentBeginOth(Long _index) {
        getForms().put(ABILITY, abilitiesSentBeginOther.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrItemsSentBegin(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsSentBeginWeather.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsSentBegin(Long _index) {
        getForms().put(ITEM, itemsSentBeginWeather.get(_index.intValue()));
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrItemsSentBeginOth(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsSentBeginOther.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsSentBeginOth(Long _index) {
        getForms().put(ITEM, itemsSentBeginOther.get(_index.intValue()));
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrSlowItems(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(slowItems.get(_index.intValue()));
    }

    @Accessible
    private String clickSlowItems(Long _index) {
        getForms().put(ITEM, slowItems.get(_index.intValue()));
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrItemSpeed(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemSpeed.get(_index.intValue()));
    }

    @Accessible
    private String clickItemSpeed(Long _index) {
        getForms().put(ITEM, itemSpeed.get(_index.intValue()));
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrSpeedPreparingItems(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(speedPreparingItems.get(_index.intValue()));
    }

    @Accessible
    private String clickSpeedPreparingItems(Long _index) {
        getForms().put(ITEM, speedPreparingItems.get(_index.intValue()));
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrProtectItems(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(protectItems.get(_index.intValue()));
    }

    @Accessible
    private String clickProtectItems(Long _index) {
        getForms().put(ITEM, protectItems.get(_index.intValue()));
        return BERRY;
    }

    @Accessible
    private String getTrItemsFighterStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsFighterStatis.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsFighterStatis(Long _index) {
        getForms().put(ITEM, itemsFighterStatis.get(_index.intValue()));
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrItemsFighterStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsFighterStatus.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsFighterStatus(Long _index) {
        getForms().put(ITEM, itemsFighterStatus.get(_index.intValue()));
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrItemsProtAgainstKo(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsProtAgainstKo.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsProtAgainstKo(Long _index) {
        getForms().put(ITEM, itemsProtAgainstKo.get(_index.intValue()));
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrItemsAbs(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsAbs.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsAbs(Long _index) {
        getForms().put(ITEM, itemsAbs.get(_index.intValue()));
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrRecoilItems(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(recoilItems.get(_index.intValue()));
    }

    @Accessible
    private String clickRecoilItems(Long _index) {
        String it_ = recoilItems.get(_index.intValue());
        getForms().put(ITEM, it_);
        DataBase data_ = (DataBase) getDataBase();
        if (data_.getItem(it_) instanceof Berry) {
            return BERRY;
        }
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrBerryUser(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berryUser.get(_index.intValue()));
    }

    @Accessible
    private String clickBerryUser(Long _index) {
        String it_ = berryUser.get(_index.intValue());
        getForms().put(ITEM, it_);
        return BERRY;
    }

    @Accessible
    private String getTrBerryTarget(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berryTarget.get(_index.intValue()));
    }

    @Accessible
    private String clickBerryTarget(Long _index) {
        String it_ = berryTarget.get(_index.intValue());
        getForms().put(ITEM, it_);
        return BERRY;
    }

    @Accessible
    private String getTrBerryEndRound(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berryEndRound.get(_index.intValue()));
    }

    @Accessible
    private String clickBerryEndRound(Long _index) {
        String it_ = berryEndRound.get(_index.intValue());
        getForms().put(ITEM, it_);
        return BERRY;
    }

    @Accessible
    private String getTrItemsUserPower(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsUserPower.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsUserPower(Long _index) {
        String it_ = itemsUserPower.get(_index.intValue());
        getForms().put(ITEM, it_);
        return BERRY;
    }

    @Accessible
    private String getTrItemsUserDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsUserDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsUserDamage(Long _index) {
        String it_ = itemsUserDamage.get(_index.intValue());
        getForms().put(ITEM, it_);
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrItemsTargetDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsTargetDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsTargetDamage(Long _index) {
        String it_ = itemsTargetDamage.get(_index.intValue());
        getForms().put(ITEM, it_);
        return BERRY;
    }

    @Accessible
    private String getTrItemsCancelImmu(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsCancelImmu.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsCancelImmu(Long _index) {
        String it_ = itemsCancelImmu.get(_index.intValue());
        getForms().put(ITEM, it_);
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrItemsImmuTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsImmuTypes.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsImmuTypes(Long _index) {
        String it_ = itemsImmuTypes.get(_index.intValue());
        getForms().put(ITEM, it_);
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrItemsImmu(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsImmu.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsImmu(Long _index) {
        String it_ = itemsImmu.get(_index.intValue());
        getForms().put(ITEM, it_);
        DataBase data_ = (DataBase) getDataBase();
        if (data_.getItem(it_) instanceof Berry) {
            return BERRY;
        }
        return ITEMFORBATTLE;
    }

    @Accessible
    private boolean itemBoostNormal() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemBoostNormal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean itemBoostNormal(Long _index) {
        String ab_ = itemsBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean itemBoostSpeed() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemBoostSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean itemBoostSpeed(Long _index) {
        String ab_ = itemsBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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


    @Accessible
    private boolean itemBoostCh() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemBoostCh((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean itemBoostCh(Long _index) {
        String ab_ = itemsBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean itemBoostEvasiness() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemBoostEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean itemBoostEvasiness(Long _index) {
        String ab_ = itemsBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean itemBoostAccuracy() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemBoostAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean itemBoostAccuracy(Long _index) {
        String ab_ = itemsBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private String getTrItemsBoostingStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsBoostingStat.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsBoostingStat(Long _index) {
        String it_ = itemsBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        getForms().put(ITEM, it_);
        if (data_.getItem(it_) instanceof Berry) {
            return BERRY;
        }
        return ITEMFORBATTLE;
    }

    @Accessible
    private boolean itemMultNormal() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean itemMultNormal(Long _index) {
        String ab_ = itemsMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (hasNormalStat(i_.getMultStat().getKeys())) {
            return true;
        }
        return false;
    }

    @Accessible
    private boolean itemMultSpeed() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean itemMultSpeed(Long _index) {
        String ab_ = itemsMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (i_.getMultStat().contains(Statistic.SPEED)) {
            return true;
        }
        return false;
    }

    @Accessible
    private boolean itemMultEvasiness() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean itemMultEvasiness(Long _index) {
        String ab_ = itemsMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (i_.getMultStat().contains(Statistic.EVASINESS)) {
            return true;
        }
        return false;
    }

    @Accessible
    private boolean itemMultAccuracy() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean itemMultAccuracy(Long _index) {
        String ab_ = itemsMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (i_.getMultStat().contains(Statistic.ACCURACY)) {
            return true;
        }
        return false;
    }

    @Accessible
    private String getTrItemsMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsMultStat.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsMultStat(Long _index) {
        String it_ = itemsMultStat.get(_index.intValue());
        getForms().put(ITEM, it_);
        return ITEMFORBATTLE;
    }

    @Accessible
    private String getTrBerrySpeed(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berrySpeed.get(_index.intValue()));
    }

    @Accessible
    private String clickBerrySpeed(Long _index) {
        getForms().put(ITEM, berrySpeed.get(_index.intValue()));
        return BERRY;
    }

    @Accessible
    private String getTrChangingTypesAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(changingTypesAbilities.get(_index.intValue()));
    }

    @Accessible
    private String clickChangingTypesAbilities(Long _index) {
        getForms().put(ABILITY, changingTypesAbilities.get(_index.intValue()));
        return ABILITY;
    }
    @Accessible
    private String getTrAbilitiesSentStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSentStatis.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesSentStatis(Long _index) {
        getForms().put(ABILITY, abilitiesSentStatis.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrCopyAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(copyAbilities.get(_index.intValue()));
    }

    @Accessible
    private String clickCopyAbilities(Long _index) {
        getForms().put(ABILITY, copyAbilities.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesPrio(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPrio.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesPrio(Long _index) {
        getForms().put(ABILITY, abilitiesPrio.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrSlowAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(slowAbilities.get(_index.intValue()));
    }

    @Accessible
    private String clickSlowAbilities(Long _index) {
        getForms().put(ABILITY, slowAbilities.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesSwitch(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSwitch.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesSwitch(Long _index) {
        getForms().put(ABILITY, abilitiesSwitch.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrImmuStatusAbility(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(immuStatusAbility.get(_index.intValue()));
    }

    @Accessible
    private String clickImmuStatusAbility(Long _index) {
        getForms().put(ABILITY, immuStatusAbility.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrImmuRecharging(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(immuRecharging.get(_index.intValue()));
    }

    @Accessible
    private String clickImmuRecharging(Long _index) {
        getForms().put(ABILITY, immuRecharging.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrCopyMoveTypesAb(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(copyMoveTypesAb.get(_index.intValue()));
    }

    @Accessible
    private String clickCopyMoveTypesAb(Long _index) {
        getForms().put(ABILITY, copyMoveTypesAb.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrPressureAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(pressureAbilities.get(_index.intValue()));
    }

    @Accessible
    private String clickPressureAbilities(Long _index) {
        getForms().put(ABILITY, pressureAbilities.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrProtectAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(protectAbilities.get(_index.intValue()));
    }

    @Accessible
    private String clickProtectAbilities(Long _index) {
        getForms().put(ABILITY, protectAbilities.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesPartStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPartStatis.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesPartStatis(Long _index) {
        getForms().put(ABILITY, abilitiesPartStatis.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesRateStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesRateStatis.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesRateStatis(Long _index) {
        getForms().put(ABILITY, abilitiesRateStatis.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesFighterStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatis.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesFighterStatis(Long _index) {
        getForms().put(ABILITY, abilitiesFighterStatis.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesFighterStatisVar(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatisVar.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesFighterStatisVar(Long _index) {
        getForms().put(ABILITY, abilitiesFighterStatisVar.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesPartStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPartStatus.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesPartStatus(Long _index) {
        getForms().put(ABILITY, abilitiesPartStatus.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesFighterStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatus.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesFighterStatus(Long _index) {
        getForms().put(ABILITY, abilitiesFighterStatus.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesRevAbs(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesRevAbs.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesRevAbs(Long _index) {
        getForms().put(ABILITY, abilitiesRevAbs.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesDamageStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesDamageStatis.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesDamageStatis(Long _index) {
        getForms().put(ABILITY, abilitiesDamageStatis.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesChangingTypesDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesChangingTypesDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesChangingTypesDamage(Long _index) {
        getForms().put(ABILITY, abilitiesChangingTypesDamage.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesTakingItem(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTakingItem.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesTakingItem(Long _index) {
        getForms().put(ABILITY, abilitiesTakingItem.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesStatisVarUser(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesStatisVarUser.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesStatisVarUser(Long _index) {
        getForms().put(ABILITY, abilitiesStatisVarUser.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesStatus.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesStatus(Long _index) {
        getForms().put(ABILITY, abilitiesStatus.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesCopyAb(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesCopyAb.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesCopyAb(Long _index) {
        getForms().put(ABILITY, abilitiesCopyAb.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrRecoilAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(recoilAbilities.get(_index.intValue()));
    }

    @Accessible
    private String clickRecoilAbilities(Long _index) {
        getForms().put(ABILITY, recoilAbilities.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesKoTarget(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesKoTarget.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesKoTarget(Long _index) {
        getForms().put(ABILITY, abilitiesKoTarget.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesEndRound(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesEndRound.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesEndRound(Long _index) {
        getForms().put(ABILITY, abilitiesEndRound.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesUserPower(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserPower.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesUserPower(Long _index) {
        getForms().put(ABILITY, abilitiesUserPower.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesTargetDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTargetDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesTargetDamage(Long _index) {
        getForms().put(ABILITY, abilitiesTargetDamage.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesGlobal(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesGlobal.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesGlobal(Long _index) {
        getForms().put(ABILITY, abilitiesGlobal.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesUserDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesUserDamage(Long _index) {
        getForms().put(ABILITY, abilitiesUserDamage.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesUserTargetDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserTargetDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesUserTargetDamage(Long _index) {
        getForms().put(ABILITY, abilitiesUserTargetDamage.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesUserStabDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserStabDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesUserStabDamage(Long _index) {
        getForms().put(ABILITY, abilitiesUserStabDamage.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesUserIgnTargetTeam(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserIgnTargetTeam.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesUserIgnTargetTeam(Long _index) {
        getForms().put(ABILITY, abilitiesUserIgnTargetTeam.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesBreakable(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakable.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesBreakable(Long _index) {
        getForms().put(ABILITY, abilitiesBreakable.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesImmuTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuTypes.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesImmuTypes(Long _index) {
        getForms().put(ABILITY, abilitiesImmuTypes.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesImmuAllies(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuAllies.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesImmuAllies(Long _index) {
        getForms().put(ABILITY, abilitiesImmuAllies.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesImmuAlliesDam(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuAlliesDam.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesImmuAlliesDam(Long _index) {
        getForms().put(ABILITY, abilitiesImmuAlliesDam.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesImmu(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmu.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesImmu(Long _index) {
        getForms().put(ABILITY, abilitiesImmu.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesImmuSecEffOther(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuSecEffOther.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesImmuSecEffOther(Long _index) {
        getForms().put(ABILITY, abilitiesImmuSecEffOther.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesImmuSecEffOwner(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuSecEffOwner.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesImmuSecEffOwner(Long _index) {
        getForms().put(ABILITY, abilitiesImmuSecEffOwner.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesAchieveTarget(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesAchieveTarget.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesAchieveTarget(Long _index) {
        getForms().put(ABILITY, abilitiesAchieveTarget.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesBreakProtectMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakProtectMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesBreakProtectMoves(Long _index) {
        getForms().put(ABILITY, abilitiesBreakProtectMoves.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private boolean abilityBoostNormal() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostNormal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityBoostNormal(Long _index) {
        String ab_ = abilitiesBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return hasNormalStat(a_.getBonusStatRank().getKeys());
    }

    @Accessible
    private boolean abilityBoostSpeed() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityBoostSpeed(Long _index) {
        String ab_ = abilitiesBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.SPEED);
    }

    @Accessible
    private boolean abilityBoostCh() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostCh((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityBoostCh(Long _index) {
        String ab_ = abilitiesBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.CRITICAL_HIT);
    }

    @Accessible
    private boolean abilityBoostEvasiness() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityBoostEvasiness(Long _index) {
        String ab_ = abilitiesBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.EVASINESS);
    }

    @Accessible
    private boolean abilityBoostAccuracy() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityBoostAccuracy(Long _index) {
        String ab_ = abilitiesBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.ACCURACY);
    }

    @Accessible
    private String getTrAbilitiesBoostingStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBoostingStat.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesBoostingStat(Long _index) {
        getForms().put(ABILITY, abilitiesBoostingStat.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private boolean abilityMultNormal() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityMultNormal(Long _index) {
        String ab_ = abilitiesMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticCategory s: a_.getMultStatIfCat().getKeys()) {
            if (isNormalStat(s.getStatistic())) {
                return true;
            }
        }
        return hasNormalStat(a_.getMultStat().getKeys());
    }

    @Accessible
    private boolean abilityMultSpeed() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityMultSpeed(Long _index) {
        String ab_ = abilitiesMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticCategory s: a_.getMultStatIfCat().getKeys()) {
            if (s.getStatistic() == Statistic.SPEED) {
                return true;
            }
        }
        return a_.getMultStat().contains(Statistic.SPEED);
    }

    @Accessible
    private boolean abilityMultEvasiness() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityMultEvasiness(Long _index) {
        String ab_ = abilitiesMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticCategory s: a_.getMultStatIfCat().getKeys()) {
            if (s.getStatistic() == Statistic.EVASINESS) {
                return true;
            }
        }
        return a_.getMultStat().contains(Statistic.EVASINESS);
    }

    @Accessible
    private boolean abilityMultAccuracy() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityMultAccuracy(Long _index) {
        String ab_ = abilitiesMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticCategory s: a_.getMultStatIfCat().getKeys()) {
            if (s.getStatistic() == Statistic.ACCURACY) {
                return true;
            }
        }
        return a_.getMultStat().contains(Statistic.ACCURACY);
    }

    @Accessible
    private String getTrAbilitiesMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesMultStat.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesMultStat(Long _index) {
        getForms().put(ABILITY, abilitiesMultStat.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private boolean abilityAllyMultNormal() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityAllyMultNormal(Long _index) {
        String ab_ = abilitiesAllyMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return hasNormalStat(a_.getMultStatAlly().getKeys());
    }

    @Accessible
    private boolean abilityAllyMultEvasiness() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityAllyMultEvasiness(Long _index) {
        String ab_ = abilitiesAllyMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getMultStatAlly().contains(Statistic.EVASINESS);
    }

    @Accessible
    private boolean abilityAllyMultSpeed() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityAllyMultSpeed(Long _index) {
        String ab_ = abilitiesAllyMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getMultStatAlly().contains(Statistic.SPEED);
    }

    @Accessible
    private boolean abilityAllyMultAccuracy() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityAllyMultAccuracy(Long _index) {
        String ab_ = abilitiesAllyMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getMultStatAlly().contains(Statistic.ACCURACY);
    }

    @Accessible
    private String getTrAbilitiesAllyMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesAllyMultStat.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesAllyMultStat(Long _index) {
        getForms().put(ABILITY, abilitiesAllyMultStat.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private boolean abilityImmuMultNormal() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityImmuMultNormal(Long _index) {
        String ab_ = abilitiesImmuMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticStatus s: a_.getImmuLowStatIfStatus()) {
            if (isNormalStat(s.getStatistic())) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityImmuMultEvasiness() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityImmuMultEvasiness(Long _index) {
        String ab_ = abilitiesImmuMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticStatus s: a_.getImmuLowStatIfStatus()) {
            if (s.getStatistic() == Statistic.EVASINESS) {
                return true;
            }
        }
        return false;
    }


    @Accessible
    private boolean abilityImmuMultSpeed() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityImmuMultSpeed(Long _index) {
        String ab_ = abilitiesImmuMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticStatus s: a_.getImmuLowStatIfStatus()) {
            if (s.getStatistic() == Statistic.SPEED) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityImmuMultAccuracy() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean abilityImmuMultAccuracy(Long _index) {
        String ab_ = abilitiesImmuMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        for (StatisticStatus s: a_.getImmuLowStatIfStatus()) {
            if (s.getStatistic() == Statistic.ACCURACY) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private String getTrAbilitiesImmuMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuMultStat.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesImmuMultStat(Long _index) {
        getForms().put(ABILITY, abilitiesImmuMultStat.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesTypeDefMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTypeDefMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesTypeDefMoves(Long _index) {
        getForms().put(ABILITY, abilitiesTypeDefMoves.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesChangeTypeMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesChangeTypeMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesChangeTypeMoves(Long _index) {
        getForms().put(ABILITY, abilitiesChangeTypeMoves.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesBreakImmu(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakImmu.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesBreakImmu(Long _index) {
        getForms().put(ABILITY, abilitiesBreakImmu.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesImmuCh(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuCh.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesImmuCh(Long _index) {
        getForms().put(ABILITY, abilitiesImmuCh.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesMultEvtCh(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitesMultEvtCh.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesMultEvtCh(Long _index) {
        getForms().put(ABILITY, abilitesMultEvtCh.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrAbilitiesMultRateCh(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitesMultRateCh.get(_index.intValue()));
    }

    @Accessible
    private String clickAbilitiesMultRateCh(Long _index) {
        getForms().put(ABILITY, abilitesMultRateCh.get(_index.intValue()));
        return ABILITY;
    }

    @Accessible
    private String getTrPrivatingMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(privatingMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickPrivatingMoves(Long _index) {
        getForms().put(MOVE, privatingMoves.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesHealingSubstitute(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesHealingSubstitute.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesHealingSubstitute(Long _index) {
        getForms().put(MOVE, movesHealingSubstitute.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrSubstitutingMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(substitutingMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickSubstitutingMoves(Long _index) {
        getForms().put(MOVE, substitutingMoves.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrReverseSpeedMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(reverseSpeedMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickReverseSpeedMoves(Long _index) {
        getForms().put(MOVE, reverseSpeedMoves.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrEntryHazard(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(entryHazard.get(_index.intValue()));
    }

    @Accessible
    private String clickEntryHazard(Long _index) {
        getForms().put(MOVE, entryHazard.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrDeleteStatusMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(deleteStatusMove.get(_index.intValue()));
    }

    @Accessible
    private String clickDeleteStatusMove(Long _index) {
        getForms().put(MOVE, deleteStatusMove.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private boolean isDisappearingUser(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        String m_ = prepaRoundMoves.get(_index.intValue());
        MoveData move_ = data_.getMove(m_);
        return move_.getDisappearBeforeUse();
    }

    @Accessible
    private String getTrPrepaRoundMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(prepaRoundMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickPrepaRoundMoves(Long _index) {
        getForms().put(MOVE, prepaRoundMoves.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrRechargeMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(rechargeMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickRechargeMoves(Long _index) {
        getForms().put(MOVE, rechargeMoves.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesInvoking(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesInvoking.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesInvoking(Long _index) {
        getForms().put(MOVE, movesInvoking.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesThieving(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesThieving.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesThieving(Long _index) {
        getForms().put(MOVE, movesThieving.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesAttracting(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesAttracting.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesAttracting(Long _index) {
        getForms().put(MOVE, movesAttracting.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesMirror(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesMirror.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesMirror(Long _index) {
        getForms().put(MOVE, movesMirror.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesSecEffItems(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesSecEffItems.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesSecEffItems(Long _index) {
        getForms().put(MOVE, movesSecEffItems.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrProtectMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(protectMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickProtectMoves(Long _index) {
        getForms().put(MOVE, protectMoves.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrEffMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(effMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickEffMoves(Long _index) {
        getForms().put(MOVE, effMoves.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private boolean immuStatisTeamMove() {
        int len_;
        len_ = movesTeam.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (immuStatisTeamMove((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean immuStatusTeamMove() {
        int len_;
        len_ = movesTeam.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (immuStatusTeamMove((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean immuChTeamMove() {
        int len_;
        len_ = movesTeam.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (immuChTeamMove((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean immuStatisTeamMove(Long _index) {
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

    @Accessible
    private boolean immuStatusTeamMove(Long _index) {
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

    @Accessible
    private boolean immuChTeamMove(Long _index) {
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

    @Accessible
    private MoveData getStatisTeamMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        String m_ = movesTeam.get(_index.intValue());
        return data_.getMove(m_);
    }

    @Accessible
    private String getTrMovesTeam(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTeam.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesTeam(Long _index) {
        getForms().put(MOVE, movesTeam.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrGlobalMovesStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(globalMovesStatus.get(_index.intValue()));
    }

    @Accessible
    private String clickGlobalMovesStatus(Long _index) {
        getForms().put(MOVE, globalMovesStatus.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesProtAgainstKo(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtAgainstKo.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesProtAgainstKo(Long _index) {
        getForms().put(MOVE, movesProtAgainstKo.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesCannotKo(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesCannotKo.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesCannotKo(Long _index) {
        getForms().put(MOVE, movesCannotKo.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesKoTarget(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesKoTarget.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesKoTarget(Long _index) {
        getForms().put(MOVE, movesKoTarget.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private boolean attackFirst() {
        int len_;
        len_ = movesChangingAttOrder.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (!attackLast((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean attackLast() {
        int len_;
        len_ = movesChangingAttOrder.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (attackLast((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean attackLast(Long _index) {
        String m_ = movesChangingAttOrder.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private String getTrMovesChangingAttOrder(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesChangingAttOrder.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesChangingAttOrder(Long _index) {
        getForms().put(MOVE, movesChangingAttOrder.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private boolean withConstDamage() {
        int len_;
        len_ = damagingMoves.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (withConstDamage((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean withConstDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        MoveData move_ = data_.getMove(damagingMoves.get(_index.intValue()));
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        return eff_.getConstDamage();
    }

    @Accessible
    private boolean withRandDamage() {
        int len_;
        len_ = damagingMoves.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (withRandDamage((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean withRandDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        MoveData move_ = data_.getMove(damagingMoves.get(_index.intValue()));
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        return !eff_.getDamageLaw().events().isEmpty();
    }

    @Accessible
    private boolean withMultDamage() {
        int len_;
        len_ = damagingMoves.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (withMultDamage((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean withMultDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        MoveData move_ = data_.getMove(damagingMoves.get(_index.intValue()));
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        return !eff_.getMultDamageAgainst().isEmpty();
    }

    @Accessible
    private String getTrDamagingMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(damagingMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickDamagingMoves(Long _index) {
        getForms().put(MOVE, damagingMoves.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesUserPower(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUserPower.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesUserPower(Long _index) {
        getForms().put(MOVE, movesUserPower.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesTargetPower(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTargetPower.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesTargetPower(Long _index) {
        getForms().put(MOVE, movesTargetPower.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesTargetTeamDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTargetTeamDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesTargetTeamDamage(Long _index) {
        getForms().put(MOVE, movesTargetTeamDamage.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesGlobal(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobal.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesGlobal(Long _index) {
        getForms().put(MOVE, movesGlobal.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesInvokDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesInvokDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesInvokDamage(Long _index) {
        getForms().put(MOVE, movesInvokDamage.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesGlobalPrepaDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalPrepaDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesGlobalPrepaDamage(Long _index) {
        getForms().put(MOVE, movesGlobalPrepaDamage.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesUserAllyDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUserAllyDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesUserAllyaDamage(Long _index) {
        getForms().put(MOVE, movesUserAllyDamage.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesIgnLowAtt(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnLowAtt.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesIgnLowAtt(Long _index) {
        getForms().put(MOVE, movesIgnLowAtt.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesIgnIncDef(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnIncDef.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesIgnIncDef(Long _index) {
        getForms().put(MOVE, movesIgnIncDef.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesProtectingTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtectingTypes.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesProtectingTypes(Long _index) {
        getForms().put(MOVE, movesProtectingTypes.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesUnprotectingTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUnprotectingTypes.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesUnprotectingTypes(Long _index) {
        getForms().put(MOVE, movesUnprotectingTypes.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesGlobalBreakImmu(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalBreakImmu.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesGlobalBreakImmu(Long _index) {
        getForms().put(MOVE, movesGlobalBreakImmu.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesGlobalBreakImmuAb(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalBreakImmuAb.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesGlobalBreakImmuAb(Long _index) {
        getForms().put(MOVE, movesGlobalBreakImmuAb.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesProtecting(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtecting.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesProtecting(Long _index) {
        getForms().put(MOVE, movesProtecting.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesIgnAcc(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnAcc.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesIgnAcc(Long _index) {
        getForms().put(MOVE, movesIgnAcc.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesIgnEva(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnEva.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesIgnEva(Long _index) {
        getForms().put(MOVE, movesIgnEva.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesGlobalAcc(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalAcc.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesGlobalAcc(Long _index) {
        getForms().put(MOVE, movesGlobalAcc.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private boolean moveGlobalMultNormal() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveGlobalMultNormal(Long _index) {
        String move_ = movesGlobalMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean moveGlobalMultEvasiness() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveGlobalMultEvasiness(Long _index) {
        String move_ = movesGlobalMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean moveGlobalMultSpeed() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveGlobalMultSpeed(Long _index) {
        String move_ = movesGlobalMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean moveGlobalMultAccuracy() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveGlobalMultAccuracy(Long _index) {
        String move_ = movesGlobalMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private String getTrMovesGlobalMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalMultStat.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesGlobalMultStat(Long _index) {
        getForms().put(MOVE, movesGlobalMultStat.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private boolean moveTeamMultNormal() {
        int len_ = movesTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveTeamMultNormal(Long _index) {
        String move_ = movesTeamMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean moveTeamMultEvasiness() {
        int len_ = movesTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveTeamMultEvasiness(Long _index) {
        String move_ = movesTeamMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean moveTeamMultSpeed() {
        int len_ = movesTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveTeamMultSpeed(Long _index) {
        String move_ = movesTeamMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean moveTeamMultAccuracy() {
        int len_ = movesTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveTeamMultAccuracy(Long _index) {
        String move_ = movesTeamMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private String getTrMovesTeamMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTeamMultStat.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesTeamMultStat(Long _index) {
        getForms().put(MOVE, movesTeamMultStat.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private boolean moveFoeTeamMultNormal() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveFoeTeamMultNormal(Long _index) {
        String move_ = movesFoeTeamMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean moveFoeTeamMultEvasiness() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveFoeTeamMultEvasiness(Long _index) {
        String move_ = movesFoeTeamMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean moveFoeTeamMultSpeed() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveFoeTeamMultSpeed(Long _index) {
        String move_ = movesFoeTeamMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private boolean moveFoeTeamMultAccuracy() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean moveFoeTeamMultAccuracy(Long _index) {
        String move_ = movesFoeTeamMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
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

    @Accessible
    private String getTrMovesFoeTeamMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesFoeTeamMultStat.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesFoeTeamMultStat(Long _index) {
        getForms().put(MOVE, movesFoeTeamMultStat.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesTypesDefItem(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTypesDefItem.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesTypesDefItem(Long _index) {
        getForms().put(MOVE, movesTypesDefItem.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrItemsTypesDef(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsTypesDef.get(_index.intValue()));
    }

    @Accessible
    private String clickItemsTypesDef(Long _index) {
        getForms().put(ITEM, itemsTypesDef.get(_index.intValue()));
        return ITEM;
    }

    @Accessible
    private String getTrMovesTypesDefWeather(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTypesDefWeather.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesTypesDefWeather(Long _index) {
        getForms().put(MOVE, movesTypesDefWeather.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesTypeDefMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTypeDefMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesTypeDefMoves(Long _index) {
        getForms().put(MOVE, movesTypeDefMoves.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesChangeTypeMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesChangeTypeMoves.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesChangeTypeMoves(Long _index) {
        getForms().put(MOVE, movesChangeTypeMoves.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrMovesBoostCh(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesBoostCh.get(_index.intValue()));
    }

    @Accessible
    private String clickMovesBoostCh(Long _index) {
        getForms().put(MOVE, movesBoostCh.get(_index.intValue()));
        return MOVE;
    }

    @Accessible
    private String getTrDeletedStatusSwitch(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(deletedStatusSwitch.get(_index.intValue()));
    }

    @Accessible
    private String clickDeletedStatusSwitch(Long _index) {
        getForms().put(STATUS, deletedStatusSwitch.get(_index.intValue()));
        return STATUS;
    }

    @Accessible
    private boolean hasLawForAttack() {
        int len_;
        len_ = beginRoundStatus.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (hasLawForAttack((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean hasLawForAttack(Long _index) {
        String status_ = beginRoundStatus.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        Status st_ = data_.getStatus(status_);
        StatusBeginRound s_ = (StatusBeginRound) st_;
        if (s_.getLawForUsingAMove().events().isEmpty()) {
            if (s_.getLawForUsingAMoveIfFoe().events().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Accessible
    private boolean hasLawForHeal() {
        int len_;
        len_ = beginRoundStatus.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (hasLawForHeal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean hasLawForHeal(Long _index) {
        String status_ = beginRoundStatus.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        Status st_ = data_.getStatus(status_);
        StatusBeginRound s_ = (StatusBeginRound) st_;
        if (s_.getLawForFullHealIfMove().events().isEmpty()) {
            return false;
        }
        return true;
    }

    @Accessible
    private String getTrBeginRoundStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(beginRoundStatus.get(_index.intValue()));
    }

    @Accessible
    private String clickBeginRoundStatus(Long _index) {
        getForms().put(STATUS, beginRoundStatus.get(_index.intValue()));
        return STATUS;
    }

    @Accessible
    private String getTrAutoDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(autoDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickAutoDamage(Long _index) {
        getForms().put(STATUS, autoDamage.get(_index.intValue()));
        return STATUS;
    }

    @Accessible
    private String getTrBeginRoundStatusFoe(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(beginRoundStatusFoe.get(_index.intValue()));
    }

    @Accessible
    private String clickBeginRoundStatusFoe(Long _index) {
        getForms().put(STATUS, beginRoundStatusFoe.get(_index.intValue()));
        return STATUS;
    }

    @Accessible
    private String getTrSuccessfulStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(successfulStatus.get(_index.intValue()));
    }

    @Accessible
    private String clickSuccessfulStatus(Long _index) {
        getForms().put(STATUS, successfulStatus.get(_index.intValue()));
        return STATUS;
    }

    @Accessible
    private String getTrStatusDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(statusDamage.get(_index.intValue()));
    }

    @Accessible
    private String clickStatusDamage(Long _index) {
        getForms().put(STATUS, statusDamage.get(_index.intValue()));
        return STATUS;
    }

    @Accessible
    private boolean statusMultNormal() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (statusMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean statusMultNormal(Long _index) {
        String status_ = statusMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        Status s_ = data_.getStatus(status_);
        return hasNormalStat(s_.getMultStat().getKeys());
    }

    @Accessible
    private boolean statusMultEvasiness() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (statusMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean statusMultEvasiness(Long _index) {
        String status_ = statusMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        Status s_ = data_.getStatus(status_);
        return s_.getMultStat().contains(Statistic.EVASINESS);
    }


    @Accessible
    private boolean statusMultSpeed() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (statusMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean statusMultSpeed(Long _index) {
        String status_ = statusMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        Status s_ = data_.getStatus(status_);
        return s_.getMultStat().contains(Statistic.SPEED);
    }

    @Accessible
    private boolean statusMultAccuracy() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (statusMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean statusMultAccuracy(Long _index) {
        String status_ = statusMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        Status s_ = data_.getStatus(status_);
        return s_.getMultStat().contains(Statistic.ACCURACY);
    }

    @Accessible
    private String getTrStatusMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(statusMultStat.get(_index.intValue()));
    }

    @Accessible
    private String clickStatusMultStat(Long _index) {
        getForms().put(STATUS, statusMultStat.get(_index.intValue()));
        return STATUS;
    }

    @Accessible
    private boolean comboMultNormal() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (comboMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean comboMultNormal(Long _index) {
        StringList combo_ = comboMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        for (StringList s: data_.getCombos().getEffects().getKeys()) {
            StringList tmp_ = new StringList(s);
            tmp_.sortElts(new ComparatorTrStrings(translatedMoves_));
            if (!StringList.eqStrings(tmp_, combo_)) {
                continue;
            }
            EffectTeam eff_ = data_.getCombos().getEffects().getVal(s).getTeamMove().first();
            return hasNormalStat(eff_.getMultStatisticFoe().getKeys());
        }
        return false;
    }

    @Accessible
    private boolean comboMultEvasiness() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (comboMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean comboMultEvasiness(Long _index) {
        StringList combo_ = comboMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        for (StringList s: data_.getCombos().getEffects().getKeys()) {
            StringList tmp_ = new StringList(s);
            tmp_.sortElts(new ComparatorTrStrings(translatedMoves_));
            if (!StringList.eqStrings(tmp_, combo_)) {
                continue;
            }
            EffectTeam eff_ = data_.getCombos().getEffects().getVal(s).getTeamMove().first();
            return eff_.getMultStatisticFoe().contains(Statistic.EVASINESS);
        }
        return false;
    }

    @Accessible
    private boolean comboMultSpeed() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (comboMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean comboMultSpeed(Long _index) {
        StringList combo_ = comboMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        for (StringList s: data_.getCombos().getEffects().getKeys()) {
            StringList tmp_ = new StringList(s);
            tmp_.sortElts(new ComparatorTrStrings(translatedMoves_));
            if (!StringList.eqStrings(tmp_, combo_)) {
                continue;
            }
            EffectTeam eff_ = data_.getCombos().getEffects().getVal(s).getTeamMove().first();
            return eff_.getMultStatisticFoe().contains(Statistic.SPEED);
        }
        return false;
    }

    @Accessible
    private boolean comboMultAccuracy() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (comboMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }

    @Accessible
    private boolean comboMultAccuracy(Long _index) {
        StringList combo_ = comboMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        for (StringList s: data_.getCombos().getEffects().getKeys()) {
            StringList tmp_ = new StringList(s);
            tmp_.sortElts(new ComparatorTrStrings(translatedMoves_));
            if (!StringList.eqStrings(tmp_, combo_)) {
                continue;
            }
            EffectTeam eff_ = data_.getCombos().getEffects().getVal(s).getTeamMove().first();
            return eff_.getMultStatisticFoe().contains(Statistic.ACCURACY);
        }
        return false;
    }

    @Accessible
    private String getTrComboMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_ = new StringList();
        for (String m: comboMultStat.get(_index.intValue())) {
            moves_.add(translatedMoves_.getVal(m));
        }
        return moves_.join(SEP_DASH);
    }

    @Accessible
    private String clickComboMultStat(Long _index) {
        getForms().put(COMBO, comboMultStat.get(_index.intValue()));
        return COMBO;
    }

    @Accessible
    private String getTrComboEvtStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_ = new StringList();
        for (String m: comboEvtStat.get(_index.intValue())) {
            moves_.add(translatedMoves_.getVal(m));
        }
        return moves_.join(SEP_DASH);
    }

    @Accessible
    private String clickComboEvtStat(Long _index) {
        getForms().put(COMBO, comboEvtStat.get(_index.intValue()));
        return COMBO;
    }

    @Accessible
    private boolean nextRowAfter(Long _index) {
        if (_index == CustList.FIRST_INDEX) {
            return true;
        }
        TypesDuo types_ = efficiency.getKey(_index.intValue());
        TypesDuo typesNext_ = efficiency.getKey(_index.intValue() - 1);
        return !StringList.quickEq(types_.getPokemonType(),typesNext_.getPokemonType());
    }

    @Accessible
    private String getTypes(Long _index) {
        return types.get(_index.intValue());
    }

    @Accessible
    private String getTrDefaultMove() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(defaultMove);
    }

    @Accessible
    private String clickDefaultMove() {
        getForms().put(MOVE, defaultMove);
        return MOVE;
    }
}
