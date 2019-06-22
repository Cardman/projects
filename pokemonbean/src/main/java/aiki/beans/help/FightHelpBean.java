package aiki.beans.help;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorDifficultyModelLaw;
import aiki.beans.facade.comparators.ComparatorDifficultyWinPointsFight;
import aiki.beans.facade.comparators.ComparatorStringList;
import aiki.beans.facade.comparators.ComparatorTypesDuo;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
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
import code.images.BaseSixtyFourUtil;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.EnumList;
import code.util.EqList;
import code.util.NatCmpTreeMap;
import code.util.NatStringTreeMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.ints.Listable;

public class FightHelpBean extends CommonBean {

    private static final String VAR_BOOST ="b";
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
    private StringList autoDamage;
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
    private EqList<StringList> comboMultStat;
    private EqList<StringList> comboEvtStat;
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
    private LongTreeMap<Rate> boosts = new LongTreeMap<Rate>();
    private LongTreeMap<Rate> boostsCh = new LongTreeMap<Rate>();
    private TreeMap<TypesDuo,Rate> efficiency;
    private StringList types;
    private Rate minHpNotKo;
    private Rate wonHappinessPointsLevel;
    private int defaultBoostValue;
    private String defaultMove;
    private String catchingFormula;
    private NatStringTreeMap<String> varCatchingFormula;
    private String fleeingFormula;
    private NatStringTreeMap<String> varFleeingFormula;
    private int happinessPoints;
    private Rate strongMove;
    private TreeMap<DifficultyWinPointsFight, String> rates;
    private NatStringTreeMap< String> varRates;
    private TreeMap<DifficultyModelLaw,NatCmpTreeMap<Rate,Rate>> lawsRates;
    private EnumList<Statistic> statisticAnim;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        wonHappinessPointsLevel = data_.getWonHappinessByGrowLevel();
        happinessPoints = data_.getHappinessEvo();
        defaultMove = data_.getDefaultMove();
        defaultBoostValue = data_.getDefaultBoost();
        strongMove = data_.getStrongMovePower();
        StringMap<String> replace_ = new StringMap<String>();
        rateFormula = data_.getRateBoost();
        replace_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.BOOST), VAR_BOOST);
//        rateFormula = rateFormula.replaceAll(StringList.BOUNDS+DataBase.VAR_PREFIX+Fight.BOOST+StringList.BOUNDS, VAR_BOOST);
        rateFormula = StringList.replaceWordsJoin(rateFormula, replace_);
        replace_ = new StringMap<String>();
        rateFormulaCh = data_.getRateBoostCriticalHit();
        replace_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.BOOST), VAR_BOOST);
//        rateFormulaCh = rateFormulaCh.replaceAll(StringList.BOUNDS+DataBase.VAR_PREFIX+Fight.BOOST+StringList.BOUNDS, VAR_BOOST);
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
        StringList.removeObj(itemsTypesDef, DataBase.EMPTY_STRING);
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
        mapVar = new NatStringTreeMap<String>();
        mapVar.putAllMap(data_.getDescriptions(data_.getDamageFormula(), getLanguage()));
        NatStringTreeMap<String> mapAutoDamage_ = new NatStringTreeMap<String>();
        int len_;
        len_ = autoDamage.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            mapAutoDamage_.putAllMap(data_.getDescriptions(getNumericString((long) i), getLanguage()));
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
            variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.BOOST), Long.toString(b));
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
            variables_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.BOOST), Long.toString(b));
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
        StringBuilder str_ = new StringBuilder();
        int len_ = catchingFormulaCopy_.length();
        int i_ = 0;
        while (i_ < len_) {
            char cur_ = catchingFormulaCopy_.charAt(i_);
            if (StringList.isWordChar(cur_)) {
                boolean dig_ = cur_ >= '0' && cur_ <= '9';
                int j_ = i_;
                while (StringList.isWordChar(cur_)) {
                    j_++;
                    cur_ = catchingFormulaCopy_.charAt(j_);
                }
                String word_ = catchingFormulaCopy_.substring(i_, j_);
                if (dig_) {
                    str_.append(word_);
                    i_ = j_;
                    continue;
                }
                String next_ = catchingFormulaCopy_.substring(j_).trim();
                if (!next_.isEmpty() && next_.charAt(0) == LEFT_PAR) {
                    str_.append(word_);
                    i_ = j_;
                    continue;
                }
                str_.append(DataBase.VAR_PREFIX);
                str_.append(word_);
                i_ = j_;
                continue;
            }
            str_.append(cur_);
            i_++;
        }
        catchingFormulaCopy_ = str_.toString();
        catchingFormula = data_.getFormula(catchingFormulaCopy_, getLanguage());
        varCatchingFormula = new NatStringTreeMap<String>();
        varCatchingFormula.putAllMap(data_.getDescriptions(catchingFormulaCopy_, getLanguage()));
        fleeingFormula = data_.getFormula(data_.getFleeingFormula(), getLanguage());
        varFleeingFormula = new NatStringTreeMap<String>();
        varFleeingFormula.putAllMap(data_.getDescriptions(data_.getFleeingFormula(), getLanguage()));
        rates = new TreeMap<DifficultyWinPointsFight, String>(new ComparatorDifficultyWinPointsFight());
        for (DifficultyWinPointsFight d: data_.getRates().getKeys()) {
            rates.put(d, data_.getFormula(data_.getRates().getVal(d), getLanguage()));
        }
        varRates = new NatStringTreeMap<String>();
        for (DifficultyWinPointsFight d: data_.getRates().getKeys()) {
            varRates.putAllMap(data_.getDescriptions(data_.getRates().getVal(d), getLanguage()));
        }
        lawsRates = new TreeMap<DifficultyModelLaw, NatCmpTreeMap<Rate, Rate>>(new ComparatorDifficultyModelLaw());
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

    public String getTrStatistic(Long _index) {
        Statistic d_ = statisticAnim.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        return data_.getTranslatedStatistics().getVal(getLanguage()).getVal(d_);
    }
    public String getAnimStatistic(Long _index) {
        Statistic d_ = statisticAnim.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        return BaseSixtyFourUtil.getStringByImage(data_.getAnimStatis().getVal(d_.name()));
    }
    public String getAnimAbsorb() {
        DataBase data_ = (DataBase) getDataBase();
        return BaseSixtyFourUtil.getStringByImage(data_.getAnimAbsorb());
    }
    public String getTrLawRate(Long _index) {
        DifficultyModelLaw d_ = lawsRates.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        return data_.getTranslatedDiffModelLaw().getVal(getLanguage()).getVal(d_);
    }
    public String getTrDifficulty(Long _index) {
        DifficultyWinPointsFight diff_ = rates.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
//        return XmlParser.transformSpecialChars(data_.getTranslatedDiffWinPts().getVal(getLanguage()).getVal(diff_));
        return data_.getTranslatedDiffWinPts().getVal(getLanguage()).getVal(diff_);
    }
    public String getStab() {
        DataBase data_ = (DataBase) getDataBase();
        return data_.getStab().toNumberString();
    }
    public String getFomula(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        return data_.getFormula(getNumericString(_index), getLanguage());
    }

    private String getNumericString(Long _index) {
        String auto_ = autoDamage.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StatusBeginRoundAutoDamage st_ = (StatusBeginRoundAutoDamage) data_.getStatus(auto_);
        String str_ = data_.getDamageFormula();
        StringMap<String> replace_ = new StringMap<String>();
        replace_.put(StringList.concat(DataBase.VAR_PREFIX,Fight.POWER), st_.getPower().toNumberString());
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
    public String getTrAbilitiesSentBegin(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSentBeginWeather.get(_index.intValue()));
    }
    public String clickAbilitiesSentBegin(Long _index) {
        getForms().put(ABILITY, abilitiesSentBeginWeather.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesSentBeginOth(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSentBeginOther.get(_index.intValue()));
    }
    public String clickAbilitiesSentBeginOth(Long _index) {
        getForms().put(ABILITY, abilitiesSentBeginOther.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrItemsSentBegin(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsSentBeginWeather.get(_index.intValue()));
    }
    public String clickItemsSentBegin(Long _index) {
        getForms().put(ITEM, itemsSentBeginWeather.get(_index.intValue()));
        return ITEMFORBATTLE;
    }
    public String getTrItemsSentBeginOth(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsSentBeginOther.get(_index.intValue()));
    }
    public String clickItemsSentBeginOth(Long _index) {
        getForms().put(ITEM, itemsSentBeginOther.get(_index.intValue()));
        return ITEMFORBATTLE;
    }
    public String getTrSlowItems(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(slowItems.get(_index.intValue()));
    }
    public String clickSlowItems(Long _index) {
        getForms().put(ITEM, slowItems.get(_index.intValue()));
        return ITEMFORBATTLE;
    }
    public String getTrItemSpeed(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemSpeed.get(_index.intValue()));
    }
    public String clickItemSpeed(Long _index) {
        getForms().put(ITEM, itemSpeed.get(_index.intValue()));
        return ITEMFORBATTLE;
    }
    public String getTrSpeedPreparingItems(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(speedPreparingItems.get(_index.intValue()));
    }
    public String clickSpeedPreparingItems(Long _index) {
        getForms().put(ITEM, speedPreparingItems.get(_index.intValue()));
        return ITEMFORBATTLE;
    }
    public String getTrProtectItems(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(protectItems.get(_index.intValue()));
    }
    public String clickProtectItems(Long _index) {
        getForms().put(ITEM, protectItems.get(_index.intValue()));
        return BERRY;
    }
    public String getTrItemsFighterStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsFighterStatis.get(_index.intValue()));
    }
    public String clickItemsFighterStatis(Long _index) {
        getForms().put(ITEM, itemsFighterStatis.get(_index.intValue()));
        return ITEMFORBATTLE;
    }
    public String getTrItemsFighterStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsFighterStatus.get(_index.intValue()));
    }
    public String clickItemsFighterStatus(Long _index) {
        getForms().put(ITEM, itemsFighterStatus.get(_index.intValue()));
        return ITEMFORBATTLE;
    }
    public String getTrItemsProtAgainstKo(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsProtAgainstKo.get(_index.intValue()));
    }
    public String clickItemsProtAgainstKo(Long _index) {
        getForms().put(ITEM, itemsProtAgainstKo.get(_index.intValue()));
        return ITEMFORBATTLE;
    }
    public String getTrItemsAbs(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsAbs.get(_index.intValue()));
    }
    public String clickItemsAbs(Long _index) {
        getForms().put(ITEM, itemsAbs.get(_index.intValue()));
        return ITEMFORBATTLE;
    }
    public String getTrRecoilItems(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(recoilItems.get(_index.intValue()));
    }
    public String clickRecoilItems(Long _index) {
        String it_ = recoilItems.get(_index.intValue());
        getForms().put(ITEM, it_);
        DataBase data_ = (DataBase) getDataBase();
        if (data_.getItem(it_) instanceof Berry) {
            return BERRY;
        }
        return ITEMFORBATTLE;
    }
    public String getTrBerryUser(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berryUser.get(_index.intValue()));
    }
    public String clickBerryUser(Long _index) {
        String it_ = berryUser.get(_index.intValue());
        getForms().put(ITEM, it_);
        return BERRY;
    }
    public String getTrBerryTarget(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berryTarget.get(_index.intValue()));
    }
    public String clickBerryTarget(Long _index) {
        String it_ = berryTarget.get(_index.intValue());
        getForms().put(ITEM, it_);
        return BERRY;
    }
    public String getTrBerryEndRound(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berryEndRound.get(_index.intValue()));
    }
    public String clickBerryEndRound(Long _index) {
        String it_ = berryEndRound.get(_index.intValue());
        getForms().put(ITEM, it_);
        return BERRY;
    }
    public String getTrItemsUserPower(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsUserPower.get(_index.intValue()));
    }
    public String clickItemsUserPower(Long _index) {
        String it_ = itemsUserPower.get(_index.intValue());
        getForms().put(ITEM, it_);
        return BERRY;
    }
    public String getTrItemsUserDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsUserDamage.get(_index.intValue()));
    }
    public String clickItemsUserDamage(Long _index) {
        String it_ = itemsUserDamage.get(_index.intValue());
        getForms().put(ITEM, it_);
        return ITEMFORBATTLE;
    }
    public String getTrItemsTargetDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsTargetDamage.get(_index.intValue()));
    }
    public String clickItemsTargetDamage(Long _index) {
        String it_ = itemsTargetDamage.get(_index.intValue());
        getForms().put(ITEM, it_);
        return BERRY;
    }
    public String getTrItemsCancelImmu(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsCancelImmu.get(_index.intValue()));
    }
    public String clickItemsCancelImmu(Long _index) {
        String it_ = itemsCancelImmu.get(_index.intValue());
        getForms().put(ITEM, it_);
        return ITEMFORBATTLE;
    }
    public String getTrItemsImmuTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsImmuTypes.get(_index.intValue()));
    }
    public String clickItemsImmuTypes(Long _index) {
        String it_ = itemsImmuTypes.get(_index.intValue());
        getForms().put(ITEM, it_);
        return ITEMFORBATTLE;
    }
    public String getTrItemsImmu(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsImmu.get(_index.intValue()));
    }
    public String clickItemsImmu(Long _index) {
        String it_ = itemsImmu.get(_index.intValue());
        getForms().put(ITEM, it_);
        DataBase data_ = (DataBase) getDataBase();
        if (data_.getItem(it_) instanceof Berry) {
            return BERRY;
        }
        return ITEMFORBATTLE;
    }
    public boolean itemBoostNormalAny() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemBoostNormal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostNormal(Long _index) {
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
    public boolean itemBoostSpeedAny() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemBoostSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostSpeed(Long _index) {
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
    public boolean itemBoostChAny() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemBoostCh((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostCh(Long _index) {
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
    public boolean itemBoostEvasinessAny() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemBoostEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostEvasiness(Long _index) {
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
    public boolean itemBoostAccuracyAny() {
        int len_;
        len_ = itemsBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemBoostAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemBoostAccuracy(Long _index) {
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
    public String getTrItemsBoostingStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsBoostingStat.get(_index.intValue()));
    }
    public String clickItemsBoostingStat(Long _index) {
        String it_ = itemsBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        getForms().put(ITEM, it_);
        if (data_.getItem(it_) instanceof Berry) {
            return BERRY;
        }
        return ITEMFORBATTLE;
    }
    public boolean itemMultNormalAny() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemMultNormal(Long _index) {
        String ab_ = itemsMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (hasNormalStat(i_.getMultStat().getKeys())) {
            return true;
        }
        return false;
    }
    public boolean itemMultSpeedAny() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemMultSpeed(Long _index) {
        String ab_ = itemsMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (i_.getMultStat().contains(Statistic.SPEED)) {
            return true;
        }
        return false;
    }
    public boolean itemMultEvasinessAny() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemMultEvasiness(Long _index) {
        String ab_ = itemsMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (i_.getMultStat().contains(Statistic.EVASINESS)) {
            return true;
        }
        return false;
    }
    public boolean itemMultAccuracyAny() {
        int len_;
        len_ = itemsMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (itemMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean itemMultAccuracy(Long _index) {
        String ab_ = itemsMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        ItemForBattle i_ = (ItemForBattle) data_.getItem(ab_);
        if (i_.getMultStat().contains(Statistic.ACCURACY)) {
            return true;
        }
        return false;
    }
    public String getTrItemsMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsMultStat.get(_index.intValue()));
    }
    public String clickItemsMultStat(Long _index) {
        String it_ = itemsMultStat.get(_index.intValue());
        getForms().put(ITEM, it_);
        return ITEMFORBATTLE;
    }
    public String getTrBerrySpeed(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(berrySpeed.get(_index.intValue()));
    }
    public String clickBerrySpeed(Long _index) {
        getForms().put(ITEM, berrySpeed.get(_index.intValue()));
        return BERRY;
    }
    public String getTrChangingTypesAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(changingTypesAbilities.get(_index.intValue()));
    }
    public String clickChangingTypesAbilities(Long _index) {
        getForms().put(ABILITY, changingTypesAbilities.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesSentStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSentStatis.get(_index.intValue()));
    }
    public String clickAbilitiesSentStatis(Long _index) {
        getForms().put(ABILITY, abilitiesSentStatis.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrCopyAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(copyAbilities.get(_index.intValue()));
    }
    public String clickCopyAbilities(Long _index) {
        getForms().put(ABILITY, copyAbilities.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesPrio(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPrio.get(_index.intValue()));
    }
    public String clickAbilitiesPrio(Long _index) {
        getForms().put(ABILITY, abilitiesPrio.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrSlowAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(slowAbilities.get(_index.intValue()));
    }
    public String clickSlowAbilities(Long _index) {
        getForms().put(ABILITY, slowAbilities.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesSwitch(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSwitch.get(_index.intValue()));
    }
    public String clickAbilitiesSwitch(Long _index) {
        getForms().put(ABILITY, abilitiesSwitch.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrImmuStatusAbility(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(immuStatusAbility.get(_index.intValue()));
    }
    public String clickImmuStatusAbility(Long _index) {
        getForms().put(ABILITY, immuStatusAbility.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrImmuRecharging(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(immuRecharging.get(_index.intValue()));
    }
    public String clickImmuRecharging(Long _index) {
        getForms().put(ABILITY, immuRecharging.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrCopyMoveTypesAb(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(copyMoveTypesAb.get(_index.intValue()));
    }
    public String clickCopyMoveTypesAb(Long _index) {
        getForms().put(ABILITY, copyMoveTypesAb.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrPressureAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(pressureAbilities.get(_index.intValue()));
    }
    public String clickPressureAbilities(Long _index) {
        getForms().put(ABILITY, pressureAbilities.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrProtectAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(protectAbilities.get(_index.intValue()));
    }
    public String clickProtectAbilities(Long _index) {
        getForms().put(ABILITY, protectAbilities.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesPartStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPartStatis.get(_index.intValue()));
    }
    public String clickAbilitiesPartStatis(Long _index) {
        getForms().put(ABILITY, abilitiesPartStatis.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesRateStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesRateStatis.get(_index.intValue()));
    }
    public String clickAbilitiesRateStatis(Long _index) {
        getForms().put(ABILITY, abilitiesRateStatis.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesFighterStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatis.get(_index.intValue()));
    }
    public String clickAbilitiesFighterStatis(Long _index) {
        getForms().put(ABILITY, abilitiesFighterStatis.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesFighterStatisVar(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatisVar.get(_index.intValue()));
    }
    public String clickAbilitiesFighterStatisVar(Long _index) {
        getForms().put(ABILITY, abilitiesFighterStatisVar.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesPartStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPartStatus.get(_index.intValue()));
    }
    public String clickAbilitiesPartStatus(Long _index) {
        getForms().put(ABILITY, abilitiesPartStatus.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesFighterStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatus.get(_index.intValue()));
    }
    public String clickAbilitiesFighterStatus(Long _index) {
        getForms().put(ABILITY, abilitiesFighterStatus.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesRevAbs(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesRevAbs.get(_index.intValue()));
    }
    public String clickAbilitiesRevAbs(Long _index) {
        getForms().put(ABILITY, abilitiesRevAbs.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesDamageStatis(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesDamageStatis.get(_index.intValue()));
    }
    public String clickAbilitiesDamageStatis(Long _index) {
        getForms().put(ABILITY, abilitiesDamageStatis.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesChangingTypesDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesChangingTypesDamage.get(_index.intValue()));
    }
    public String clickAbilitiesChangingTypesDamage(Long _index) {
        getForms().put(ABILITY, abilitiesChangingTypesDamage.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesTakingItem(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTakingItem.get(_index.intValue()));
    }
    public String clickAbilitiesTakingItem(Long _index) {
        getForms().put(ABILITY, abilitiesTakingItem.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesStatisVarUser(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesStatisVarUser.get(_index.intValue()));
    }
    public String clickAbilitiesStatisVarUser(Long _index) {
        getForms().put(ABILITY, abilitiesStatisVarUser.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesStatus.get(_index.intValue()));
    }
    public String clickAbilitiesStatus(Long _index) {
        getForms().put(ABILITY, abilitiesStatus.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesCopyAb(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesCopyAb.get(_index.intValue()));
    }
    public String clickAbilitiesCopyAb(Long _index) {
        getForms().put(ABILITY, abilitiesCopyAb.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrRecoilAbilities(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(recoilAbilities.get(_index.intValue()));
    }
    public String clickRecoilAbilities(Long _index) {
        getForms().put(ABILITY, recoilAbilities.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesKoTarget(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesKoTarget.get(_index.intValue()));
    }
    public String clickAbilitiesKoTarget(Long _index) {
        getForms().put(ABILITY, abilitiesKoTarget.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesEndRound(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesEndRound.get(_index.intValue()));
    }
    public String clickAbilitiesEndRound(Long _index) {
        getForms().put(ABILITY, abilitiesEndRound.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesUserPower(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserPower.get(_index.intValue()));
    }
    public String clickAbilitiesUserPower(Long _index) {
        getForms().put(ABILITY, abilitiesUserPower.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesTargetDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTargetDamage.get(_index.intValue()));
    }
    public String clickAbilitiesTargetDamage(Long _index) {
        getForms().put(ABILITY, abilitiesTargetDamage.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesGlobal(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesGlobal.get(_index.intValue()));
    }
    public String clickAbilitiesGlobal(Long _index) {
        getForms().put(ABILITY, abilitiesGlobal.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesUserDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserDamage.get(_index.intValue()));
    }
    public String clickAbilitiesUserDamage(Long _index) {
        getForms().put(ABILITY, abilitiesUserDamage.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesUserTargetDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserTargetDamage.get(_index.intValue()));
    }
    public String clickAbilitiesUserTargetDamage(Long _index) {
        getForms().put(ABILITY, abilitiesUserTargetDamage.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesUserStabDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserStabDamage.get(_index.intValue()));
    }
    public String clickAbilitiesUserStabDamage(Long _index) {
        getForms().put(ABILITY, abilitiesUserStabDamage.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesUserIgnTargetTeam(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserIgnTargetTeam.get(_index.intValue()));
    }
    public String clickAbilitiesUserIgnTargetTeam(Long _index) {
        getForms().put(ABILITY, abilitiesUserIgnTargetTeam.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesBreakable(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakable.get(_index.intValue()));
    }
    public String clickAbilitiesBreakable(Long _index) {
        getForms().put(ABILITY, abilitiesBreakable.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesImmuTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuTypes.get(_index.intValue()));
    }
    public String clickAbilitiesImmuTypes(Long _index) {
        getForms().put(ABILITY, abilitiesImmuTypes.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesImmuAllies(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuAllies.get(_index.intValue()));
    }
    public String clickAbilitiesImmuAllies(Long _index) {
        getForms().put(ABILITY, abilitiesImmuAllies.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesImmuAlliesDam(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuAlliesDam.get(_index.intValue()));
    }
    public String clickAbilitiesImmuAlliesDam(Long _index) {
        getForms().put(ABILITY, abilitiesImmuAlliesDam.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesImmu(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmu.get(_index.intValue()));
    }
    public String clickAbilitiesImmu(Long _index) {
        getForms().put(ABILITY, abilitiesImmu.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesImmuSecEffOther(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuSecEffOther.get(_index.intValue()));
    }
    public String clickAbilitiesImmuSecEffOther(Long _index) {
        getForms().put(ABILITY, abilitiesImmuSecEffOther.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesImmuSecEffOwner(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuSecEffOwner.get(_index.intValue()));
    }
    public String clickAbilitiesImmuSecEffOwner(Long _index) {
        getForms().put(ABILITY, abilitiesImmuSecEffOwner.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesAchieveTarget(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesAchieveTarget.get(_index.intValue()));
    }
    public String clickAbilitiesAchieveTarget(Long _index) {
        getForms().put(ABILITY, abilitiesAchieveTarget.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesBreakProtectMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakProtectMoves.get(_index.intValue()));
    }
    public String clickAbilitiesBreakProtectMoves(Long _index) {
        getForms().put(ABILITY, abilitiesBreakProtectMoves.get(_index.intValue()));
        return ABILITY;
    }
    public boolean abilityBoostNormalAny() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostNormal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityBoostNormal(Long _index) {
        String ab_ = abilitiesBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return hasNormalStat(a_.getBonusStatRank().getKeys());
    }
    public boolean abilityBoostSpeedAny() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityBoostSpeed(Long _index) {
        String ab_ = abilitiesBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.SPEED);
    }
    public boolean abilityBoostChAny() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostCh((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityBoostCh(Long _index) {
        String ab_ = abilitiesBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.CRITICAL_HIT);
    }
    public boolean abilityBoostEvasinessAny() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityBoostEvasiness(Long _index) {
        String ab_ = abilitiesBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.EVASINESS);
    }
    public boolean abilityBoostAccuracyAny() {
        int len_;
        len_ = abilitiesBoostingStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityBoostAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityBoostAccuracy(Long _index) {
        String ab_ = abilitiesBoostingStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getBonusStatRank().contains(Statistic.ACCURACY);
    }
    public String getTrAbilitiesBoostingStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBoostingStat.get(_index.intValue()));
    }
    public String clickAbilitiesBoostingStat(Long _index) {
        getForms().put(ABILITY, abilitiesBoostingStat.get(_index.intValue()));
        return ABILITY;
    }
    public boolean abilityMultNormalAny() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityMultNormal(Long _index) {
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
    public boolean abilityMultSpeedAny() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityMultSpeed(Long _index) {
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
    public boolean abilityMultEvasinessAny() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityMultEvasiness(Long _index) {
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
    public boolean abilityMultAccuracyAny() {
        int len_;
        len_ = abilitiesMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityMultAccuracy(Long _index) {
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
    public String getTrAbilitiesMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesMultStat.get(_index.intValue()));
    }
    public String clickAbilitiesMultStat(Long _index) {
        getForms().put(ABILITY, abilitiesMultStat.get(_index.intValue()));
        return ABILITY;
    }
    public boolean abilityAllyMultNormalAny() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityAllyMultNormal(Long _index) {
        String ab_ = abilitiesAllyMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return hasNormalStat(a_.getMultStatAlly().getKeys());
    }
    public boolean abilityAllyMultEvasinessAny() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityAllyMultEvasiness(Long _index) {
        String ab_ = abilitiesAllyMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getMultStatAlly().contains(Statistic.EVASINESS);
    }
    public boolean abilityAllyMultSpeedAny() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityAllyMultSpeed(Long _index) {
        String ab_ = abilitiesAllyMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getMultStatAlly().contains(Statistic.SPEED);
    }
    public boolean abilityAllyMultAccuracyAny() {
        int len_;
        len_ = abilitiesAllyMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityAllyMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityAllyMultAccuracy(Long _index) {
        String ab_ = abilitiesAllyMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        AbilityData a_ = data_.getAbility(ab_);
        return a_.getMultStatAlly().contains(Statistic.ACCURACY);
    }
    public String getTrAbilitiesAllyMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesAllyMultStat.get(_index.intValue()));
    }
    public String clickAbilitiesAllyMultStat(Long _index) {
        getForms().put(ABILITY, abilitiesAllyMultStat.get(_index.intValue()));
        return ABILITY;
    }
    public boolean abilityImmuMultNormalAny() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityImmuMultNormal(Long _index) {
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
    public boolean abilityImmuMultEvasinessAny() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityImmuMultEvasiness(Long _index) {
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
    public boolean abilityImmuMultSpeedAny() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityImmuMultSpeed(Long _index) {
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
    public boolean abilityImmuMultAccuracyAny() {
        int len_;
        len_ = abilitiesImmuMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (abilityImmuMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean abilityImmuMultAccuracy(Long _index) {
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
    public String getTrAbilitiesImmuMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuMultStat.get(_index.intValue()));
    }
    public String clickAbilitiesImmuMultStat(Long _index) {
        getForms().put(ABILITY, abilitiesImmuMultStat.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesTypeDefMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTypeDefMoves.get(_index.intValue()));
    }
    public String clickAbilitiesTypeDefMoves(Long _index) {
        getForms().put(ABILITY, abilitiesTypeDefMoves.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesChangeTypeMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesChangeTypeMoves.get(_index.intValue()));
    }
    public String clickAbilitiesChangeTypeMoves(Long _index) {
        getForms().put(ABILITY, abilitiesChangeTypeMoves.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesBreakImmu(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakImmu.get(_index.intValue()));
    }
    public String clickAbilitiesBreakImmu(Long _index) {
        getForms().put(ABILITY, abilitiesBreakImmu.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesImmuCh(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuCh.get(_index.intValue()));
    }
    public String clickAbilitiesImmuCh(Long _index) {
        getForms().put(ABILITY, abilitiesImmuCh.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesMultEvtCh(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitesMultEvtCh.get(_index.intValue()));
    }
    public String clickAbilitiesMultEvtCh(Long _index) {
        getForms().put(ABILITY, abilitesMultEvtCh.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrAbilitiesMultRateCh(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitesMultRateCh.get(_index.intValue()));
    }
    public String clickAbilitiesMultRateCh(Long _index) {
        getForms().put(ABILITY, abilitesMultRateCh.get(_index.intValue()));
        return ABILITY;
    }
    public String getTrPrivatingMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(privatingMoves.get(_index.intValue()));
    }
    public String clickPrivatingMoves(Long _index) {
        getForms().put(MOVE, privatingMoves.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesHealingSubstitute(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesHealingSubstitute.get(_index.intValue()));
    }
    public String clickMovesHealingSubstitute(Long _index) {
        getForms().put(MOVE, movesHealingSubstitute.get(_index.intValue()));
        return MOVE;
    }
    public String getTrSubstitutingMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(substitutingMoves.get(_index.intValue()));
    }
    public String clickSubstitutingMoves(Long _index) {
        getForms().put(MOVE, substitutingMoves.get(_index.intValue()));
        return MOVE;
    }
    public String getTrReverseSpeedMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(reverseSpeedMoves.get(_index.intValue()));
    }
    public String clickReverseSpeedMoves(Long _index) {
        getForms().put(MOVE, reverseSpeedMoves.get(_index.intValue()));
        return MOVE;
    }
    public String getTrEntryHazard(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(entryHazard.get(_index.intValue()));
    }
    public String clickEntryHazard(Long _index) {
        getForms().put(MOVE, entryHazard.get(_index.intValue()));
        return MOVE;
    }
    public String getTrDeleteStatusMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(deleteStatusMove.get(_index.intValue()));
    }
    public String clickDeleteStatusMove(Long _index) {
        getForms().put(MOVE, deleteStatusMove.get(_index.intValue()));
        return MOVE;
    }
    public boolean isDisappearingUser(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        String m_ = prepaRoundMoves.get(_index.intValue());
        MoveData move_ = data_.getMove(m_);
        return move_.getDisappearBeforeUse();
    }
    public String getTrPrepaRoundMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(prepaRoundMoves.get(_index.intValue()));
    }
    public String clickPrepaRoundMoves(Long _index) {
        getForms().put(MOVE, prepaRoundMoves.get(_index.intValue()));
        return MOVE;
    }
    public String getTrRechargeMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(rechargeMoves.get(_index.intValue()));
    }
    public String clickRechargeMoves(Long _index) {
        getForms().put(MOVE, rechargeMoves.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesInvoking(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesInvoking.get(_index.intValue()));
    }
    public String clickMovesInvoking(Long _index) {
        getForms().put(MOVE, movesInvoking.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesThieving(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesThieving.get(_index.intValue()));
    }
    public String clickMovesThieving(Long _index) {
        getForms().put(MOVE, movesThieving.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesAttracting(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesAttracting.get(_index.intValue()));
    }
    public String clickMovesAttracting(Long _index) {
        getForms().put(MOVE, movesAttracting.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesMirror(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesMirror.get(_index.intValue()));
    }
    public String clickMovesMirror(Long _index) {
        getForms().put(MOVE, movesMirror.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesSecEffItems(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesSecEffItems.get(_index.intValue()));
    }
    public String clickMovesSecEffItems(Long _index) {
        getForms().put(MOVE, movesSecEffItems.get(_index.intValue()));
        return MOVE;
    }
    public String getTrProtectMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(protectMoves.get(_index.intValue()));
    }
    public String clickProtectMoves(Long _index) {
        getForms().put(MOVE, protectMoves.get(_index.intValue()));
        return MOVE;
    }
    public String getTrEffMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(effMoves.get(_index.intValue()));
    }
    public String clickEffMoves(Long _index) {
        getForms().put(MOVE, effMoves.get(_index.intValue()));
        return MOVE;
    }
    public boolean immuStatisTeamMoveAny() {
        int len_;
        len_ = movesTeam.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (immuStatisTeamMove((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean immuStatusTeamMoveAny() {
        int len_;
        len_ = movesTeam.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (immuStatusTeamMove((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean immuChTeamMoveAny() {
        int len_;
        len_ = movesTeam.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (immuChTeamMove((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean immuStatisTeamMove(Long _index) {
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
    public boolean immuStatusTeamMove(Long _index) {
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
    public boolean immuChTeamMove(Long _index) {
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
    public MoveData getStatisTeamMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        String m_ = movesTeam.get(_index.intValue());
        return data_.getMove(m_);
    }
    public String getTrMovesTeam(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTeam.get(_index.intValue()));
    }
    public String clickMovesTeam(Long _index) {
        getForms().put(MOVE, movesTeam.get(_index.intValue()));
        return MOVE;
    }
    public String getTrGlobalMovesStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(globalMovesStatus.get(_index.intValue()));
    }
    public String clickGlobalMovesStatus(Long _index) {
        getForms().put(MOVE, globalMovesStatus.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesProtAgainstKo(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtAgainstKo.get(_index.intValue()));
    }
    public String clickMovesProtAgainstKo(Long _index) {
        getForms().put(MOVE, movesProtAgainstKo.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesCannotKo(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesCannotKo.get(_index.intValue()));
    }
    public String clickMovesCannotKo(Long _index) {
        getForms().put(MOVE, movesCannotKo.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesKoTarget(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesKoTarget.get(_index.intValue()));
    }
    public String clickMovesKoTarget(Long _index) {
        getForms().put(MOVE, movesKoTarget.get(_index.intValue()));
        return MOVE;
    }
    public boolean attackFirst() {
        int len_;
        len_ = movesChangingAttOrder.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (!attackLast((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean attackLastAny() {
        int len_;
        len_ = movesChangingAttOrder.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (attackLast((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean attackLast(Long _index) {
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
    public String getTrMovesChangingAttOrder(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesChangingAttOrder.get(_index.intValue()));
    }
    public String clickMovesChangingAttOrder(Long _index) {
        getForms().put(MOVE, movesChangingAttOrder.get(_index.intValue()));
        return MOVE;
    }
    public boolean withConstDamageAny() {
        int len_;
        len_ = damagingMoves.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (withConstDamage((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean withConstDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        MoveData move_ = data_.getMove(damagingMoves.get(_index.intValue()));
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        return eff_.getConstDamage();
    }
    public boolean withRandDamageAny() {
        int len_;
        len_ = damagingMoves.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (withRandDamage((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean withRandDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        MoveData move_ = data_.getMove(damagingMoves.get(_index.intValue()));
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        return !eff_.getDamageLaw().events().isEmpty();
    }
    public boolean withMultDamageAny() {
        int len_;
        len_ = damagingMoves.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (withMultDamage((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean withMultDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        MoveData move_ = data_.getMove(damagingMoves.get(_index.intValue()));
        EffectDamage eff_ = (EffectDamage) move_.getEffet(move_.indexOfPrimaryEffect());
        return !eff_.getMultDamageAgainst().isEmpty();
    }
    public String getTrDamagingMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(damagingMoves.get(_index.intValue()));
    }
    public String clickDamagingMoves(Long _index) {
        getForms().put(MOVE, damagingMoves.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesUserPower(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUserPower.get(_index.intValue()));
    }
    public String clickMovesUserPower(Long _index) {
        getForms().put(MOVE, movesUserPower.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesTargetPower(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTargetPower.get(_index.intValue()));
    }
    public String clickMovesTargetPower(Long _index) {
        getForms().put(MOVE, movesTargetPower.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesTargetTeamDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTargetTeamDamage.get(_index.intValue()));
    }
    public String clickMovesTargetTeamDamage(Long _index) {
        getForms().put(MOVE, movesTargetTeamDamage.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesGlobal(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobal.get(_index.intValue()));
    }
    public String clickMovesGlobal(Long _index) {
        getForms().put(MOVE, movesGlobal.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesInvokDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesInvokDamage.get(_index.intValue()));
    }
    public String clickMovesInvokDamage(Long _index) {
        getForms().put(MOVE, movesInvokDamage.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesGlobalPrepaDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalPrepaDamage.get(_index.intValue()));
    }
    public String clickMovesGlobalPrepaDamage(Long _index) {
        getForms().put(MOVE, movesGlobalPrepaDamage.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesUserAllyDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUserAllyDamage.get(_index.intValue()));
    }
    public String clickMovesUserAllyaDamage(Long _index) {
        getForms().put(MOVE, movesUserAllyDamage.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesIgnLowAtt(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnLowAtt.get(_index.intValue()));
    }
    public String clickMovesIgnLowAtt(Long _index) {
        getForms().put(MOVE, movesIgnLowAtt.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesIgnIncDef(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnIncDef.get(_index.intValue()));
    }
    public String clickMovesIgnIncDef(Long _index) {
        getForms().put(MOVE, movesIgnIncDef.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesProtectingTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtectingTypes.get(_index.intValue()));
    }
    public String clickMovesProtectingTypes(Long _index) {
        getForms().put(MOVE, movesProtectingTypes.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesUnprotectingTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUnprotectingTypes.get(_index.intValue()));
    }
    public String clickMovesUnprotectingTypes(Long _index) {
        getForms().put(MOVE, movesUnprotectingTypes.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesGlobalBreakImmu(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalBreakImmu.get(_index.intValue()));
    }
    public String clickMovesGlobalBreakImmu(Long _index) {
        getForms().put(MOVE, movesGlobalBreakImmu.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesGlobalBreakImmuAb(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalBreakImmuAb.get(_index.intValue()));
    }
    public String clickMovesGlobalBreakImmuAb(Long _index) {
        getForms().put(MOVE, movesGlobalBreakImmuAb.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesProtecting(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtecting.get(_index.intValue()));
    }
    public String clickMovesProtecting(Long _index) {
        getForms().put(MOVE, movesProtecting.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesIgnAcc(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnAcc.get(_index.intValue()));
    }
    public String clickMovesIgnAcc(Long _index) {
        getForms().put(MOVE, movesIgnAcc.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesIgnEva(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnEva.get(_index.intValue()));
    }
    public String clickMovesIgnEva(Long _index) {
        getForms().put(MOVE, movesIgnEva.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesGlobalAcc(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalAcc.get(_index.intValue()));
    }
    public String clickMovesGlobalAcc(Long _index) {
        getForms().put(MOVE, movesGlobalAcc.get(_index.intValue()));
        return MOVE;
    }
    public boolean moveGlobalMultNormalAny() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveGlobalMultNormal(Long _index) {
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
    public boolean moveGlobalMultEvasinessAny() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveGlobalMultEvasiness(Long _index) {
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
    public boolean moveGlobalMultSpeedAny() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveGlobalMultSpeed(Long _index) {
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
    public boolean moveGlobalMultAccuracyAny() {
        int len_;
        len_ = movesGlobalMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveGlobalMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveGlobalMultAccuracy(Long _index) {
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
    public String getTrMovesGlobalMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalMultStat.get(_index.intValue()));
    }
    public String clickMovesGlobalMultStat(Long _index) {
        getForms().put(MOVE, movesGlobalMultStat.get(_index.intValue()));
        return MOVE;
    }
    public boolean moveTeamMultNormalAny() {
        int len_ = movesTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveTeamMultNormal(Long _index) {
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
    public boolean moveTeamMultEvasinessAny() {
        int len_ = movesTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveTeamMultEvasiness(Long _index) {
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
    public boolean moveTeamMultSpeedAny() {
        int len_ = movesTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveTeamMultSpeed(Long _index) {
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
    public boolean moveTeamMultAccuracyAny() {
        int len_ = movesTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveTeamMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveTeamMultAccuracy(Long _index) {
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
    public String getTrMovesTeamMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTeamMultStat.get(_index.intValue()));
    }
    public String clickMovesTeamMultStat(Long _index) {
        getForms().put(MOVE, movesTeamMultStat.get(_index.intValue()));
        return MOVE;
    }
    public boolean moveFoeTeamMultNormalAny() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveFoeTeamMultNormal(Long _index) {
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
    public boolean moveFoeTeamMultEvasinessAny() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveFoeTeamMultEvasiness(Long _index) {
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
    public boolean moveFoeTeamMultSpeedAny() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveFoeTeamMultSpeed(Long _index) {
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
    public boolean moveFoeTeamMultAccuracyAny() {
        int len_;
        len_ = movesFoeTeamMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (moveFoeTeamMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean moveFoeTeamMultAccuracy(Long _index) {
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
    public String getTrMovesFoeTeamMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesFoeTeamMultStat.get(_index.intValue()));
    }
    public String clickMovesFoeTeamMultStat(Long _index) {
        getForms().put(MOVE, movesFoeTeamMultStat.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesTypesDefItem(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTypesDefItem.get(_index.intValue()));
    }
    public String clickMovesTypesDefItem(Long _index) {
        getForms().put(MOVE, movesTypesDefItem.get(_index.intValue()));
        return MOVE;
    }
    public String getTrItemsTypesDef(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedItems_ = data_.getTranslatedItems().getVal(getLanguage());
        return translatedItems_.getVal(itemsTypesDef.get(_index.intValue()));
    }
    public String clickItemsTypesDef(Long _index) {
        getForms().put(ITEM, itemsTypesDef.get(_index.intValue()));
        return ITEM;
    }
    public String getTrMovesTypesDefWeather(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTypesDefWeather.get(_index.intValue()));
    }
    public String clickMovesTypesDefWeather(Long _index) {
        getForms().put(MOVE, movesTypesDefWeather.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesTypeDefMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTypeDefMoves.get(_index.intValue()));
    }
    public String clickMovesTypeDefMoves(Long _index) {
        getForms().put(MOVE, movesTypeDefMoves.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesChangeTypeMoves(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesChangeTypeMoves.get(_index.intValue()));
    }
    public String clickMovesChangeTypeMoves(Long _index) {
        getForms().put(MOVE, movesChangeTypeMoves.get(_index.intValue()));
        return MOVE;
    }
    public String getTrMovesBoostCh(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesBoostCh.get(_index.intValue()));
    }
    public String clickMovesBoostCh(Long _index) {
        getForms().put(MOVE, movesBoostCh.get(_index.intValue()));
        return MOVE;
    }
    public String getTrDeletedStatusSwitch(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(deletedStatusSwitch.get(_index.intValue()));
    }
    public String clickDeletedStatusSwitch(Long _index) {
        getForms().put(STATUS, deletedStatusSwitch.get(_index.intValue()));
        return STATUS;
    }
    public boolean hasLawForAttackAny() {
        int len_;
        len_ = beginRoundStatus.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (hasLawForAttack((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasLawForAttack(Long _index) {
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
    public boolean hasLawForHealAny() {
        int len_;
        len_ = beginRoundStatus.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (hasLawForHeal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasLawForHeal(Long _index) {
        String status_ = beginRoundStatus.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        Status st_ = data_.getStatus(status_);
        StatusBeginRound s_ = (StatusBeginRound) st_;
        if (s_.getLawForFullHealIfMove().events().isEmpty()) {
            return false;
        }
        return true;
    }
    public String getTrBeginRoundStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(beginRoundStatus.get(_index.intValue()));
    }
    public String clickBeginRoundStatus(Long _index) {
        getForms().put(STATUS, beginRoundStatus.get(_index.intValue()));
        return STATUS;
    }
    public String getTrAutoDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(autoDamage.get(_index.intValue()));
    }
    public String clickAutoDamage(Long _index) {
        getForms().put(STATUS, autoDamage.get(_index.intValue()));
        return STATUS;
    }
    public String getTrBeginRoundStatusFoe(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(beginRoundStatusFoe.get(_index.intValue()));
    }
    public String clickBeginRoundStatusFoe(Long _index) {
        getForms().put(STATUS, beginRoundStatusFoe.get(_index.intValue()));
        return STATUS;
    }
    public String getTrSuccessfulStatus(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(successfulStatus.get(_index.intValue()));
    }
    public String clickSuccessfulStatus(Long _index) {
        getForms().put(STATUS, successfulStatus.get(_index.intValue()));
        return STATUS;
    }
    public String getTrStatusDamage(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(statusDamage.get(_index.intValue()));
    }
    public String clickStatusDamage(Long _index) {
        getForms().put(STATUS, statusDamage.get(_index.intValue()));
        return STATUS;
    }
    public boolean statusMultNormalAny() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (statusMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean statusMultNormal(Long _index) {
        String status_ = statusMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        Status s_ = data_.getStatus(status_);
        return hasNormalStat(s_.getMultStat().getKeys());
    }
    public boolean statusMultEvasinessAny() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (statusMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean statusMultEvasiness(Long _index) {
        String status_ = statusMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        Status s_ = data_.getStatus(status_);
        return s_.getMultStat().contains(Statistic.EVASINESS);
    }
    public boolean statusMultSpeedAny() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (statusMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean statusMultSpeed(Long _index) {
        String status_ = statusMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        Status s_ = data_.getStatus(status_);
        return s_.getMultStat().contains(Statistic.SPEED);
    }
    public boolean statusMultAccuracyAny() {
        int len_;
        len_ = statusMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (statusMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean statusMultAccuracy(Long _index) {
        String status_ = statusMultStat.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        Status s_ = data_.getStatus(status_);
        return s_.getMultStat().contains(Statistic.ACCURACY);
    }
    public String getTrStatusMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(statusMultStat.get(_index.intValue()));
    }
    public String clickStatusMultStat(Long _index) {
        getForms().put(STATUS, statusMultStat.get(_index.intValue()));
        return STATUS;
    }
    public boolean comboMultNormalAny() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (comboMultNormal((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean comboMultNormal(Long _index) {
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
    public boolean comboMultEvasinessAny() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (comboMultEvasiness((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean comboMultEvasiness(Long _index) {
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
    public boolean comboMultSpeedAny() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (comboMultSpeed((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean comboMultSpeed(Long _index) {
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
    public boolean comboMultAccuracyAny() {
        int len_;
        len_ = comboMultStat.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (comboMultAccuracy((long) i)) {
                return true;
            }
        }
        return false;
    }
    public boolean comboMultAccuracy(Long _index) {
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
    public String getTrComboMultStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_ = new StringList();
        for (String m: comboMultStat.get(_index.intValue())) {
            moves_.add(translatedMoves_.getVal(m));
        }
        return moves_.join(SEP_DASH);
    }
    public String clickComboMultStat(Long _index) {
        getForms().put(COMBO, comboMultStat.get(_index.intValue()));
        return COMBO;
    }
    public String getTrComboEvtStat(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_ = new StringList();
        for (String m: comboEvtStat.get(_index.intValue())) {
            moves_.add(translatedMoves_.getVal(m));
        }
        return moves_.join(SEP_DASH);
    }
    public String clickComboEvtStat(Long _index) {
        getForms().put(COMBO, comboEvtStat.get(_index.intValue()));
        return COMBO;
    }
    public boolean nextRowAfter(Long _index) {
        if (_index == CustList.FIRST_INDEX) {
            return true;
        }
        TypesDuo types_ = efficiency.getKey(_index.intValue());
        TypesDuo typesNext_ = efficiency.getKey(_index.intValue() - 1);
        return !StringList.quickEq(types_.getPokemonType(),typesNext_.getPokemonType());
    }
    public String getTypes(Long _index) {
        return types.get(_index.intValue());
    }
    public String getTrDefaultMove() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(defaultMove);
    }
    public String clickDefaultMove() {
        getForms().put(MOVE, defaultMove);
        return MOVE;
    }

    public int getDefaultBoostValue() {
        return defaultBoostValue;
    }

    public StringList getPrivatingMoves() {
        return privatingMoves;
    }

    public StringList getMovesHealingSubstitute() {
        return movesHealingSubstitute;
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

    public StringList getAutoDamage() {
        return autoDamage;
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

    public EqList<StringList> getComboEvtStat() {
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

    public TreeMap<DifficultyModelLaw,NatCmpTreeMap<Rate,Rate>> getLawsRates() {
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

    public TreeMap<DifficultyWinPointsFight,String> getRates() {
        return rates;
    }

    public NatStringTreeMap<String> getVarRates() {
        return varRates;
    }

    public Rate getWonHappinessPointsLevel() {
        return wonHappinessPointsLevel;
    }

    public int getHappinessPoints() {
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

    public TreeMap<TypesDuo,Rate> getEfficiency() {
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

    public EqList<StringList> getComboMultStat() {
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

    public EnumList<Statistic> getStatisticAnim() {
        return statisticAnim;
    }
}