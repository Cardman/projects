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
import aiki.game.fight.Fight;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.images.BaseSixtyFourUtil;
import code.maths.Rate;
import code.maths.litteralcom.MathExpUtil;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
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
    private int defaultBoostValue;
    private String defaultMove;
    private String catchingFormula;
    private NatStringTreeMap<String> varCatchingFormula;
    private String fleeingFormula;
    private NatStringTreeMap<String> varFleeingFormula;
    private int happinessPoints;
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
        StringMap<String> replace_ = new StringMap<String>();
        rateFormula = data_.getRateBoost();
        replace_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.BOOST), VAR_BOOST);
//        rateFormula = rateFormula.replaceAll(StringList.BOUNDS+DataBase.VAR_PREFIX+Fight.BOOST+StringList.BOUNDS, VAR_BOOST);
        rateFormula = MathExpUtil.replaceWordsJoin(rateFormula, replace_);
        replace_ = new StringMap<String>();
        rateFormulaCh = data_.getRateBoostCriticalHit();
        replace_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.BOOST), VAR_BOOST);
//        rateFormulaCh = rateFormulaCh.replaceAll(StringList.BOUNDS+DataBase.VAR_PREFIX+Fight.BOOST+StringList.BOUNDS, VAR_BOOST);
        rateFormulaCh = MathExpUtil.replaceWordsJoin(rateFormulaCh, replace_);
        long minBoost_ = data_.getMinBoost();
        long maxBoost_ = data_.getMaxBoost();
        initBoosts(minBoost_, maxBoost_);
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
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
        movesTypesDefWeather = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (!move_.getTypesByWeather().isEmpty()) {
                movesTypesDefWeather.add(m);
            }
        }
        movesTypesDefWeather.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        abilitiesTypeDefMoves = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getTypeForMoves().isEmpty()) {
                abilitiesTypeDefMoves.add(a);
            }
        }
        abilitiesTypeDefMoves.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesChangeTypeMoves = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getChangingBoostTypes().isEmpty()) {
                abilitiesChangeTypeMoves.add(a);
            }
        }
        abilitiesChangeTypeMoves.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initMovesEffectSwitchMoveTypes();
        abilitiesBreakImmu = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getBreakFoeImmune().isEmpty()) {
                abilitiesBreakImmu.add(a);
            }
        }
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
        efficiency = DictionaryComparatorUtil.buildTypesDuoRate(data_, getLanguage(), true, true);
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

    private void initTypeDef() {
        DataBase data_ = getDataBase();
        movesTypesDefItem = new StringList();
        itemsTypesDef = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (!move_.getTypesByOwnedItem().isEmpty()) {
                itemsTypesDef.addAllElts(move_.getTypesByOwnedItem().getKeys());
                movesTypesDefItem.add(m);
            }
        }
        movesTypesDefItem.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        itemsTypesDef.removeDuplicates();
        StringUtil.removeObj(itemsTypesDef, DataBase.EMPTY_STRING);
        itemsTypesDef.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
    }

    private void initAbilitiesRateStatis() {
        DataBase data_ = getDataBase();
        abilitiesRateStatis = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultEvtRateSecEffectOwner().isZero()) {
                abilitiesRateStatis.add(a);
            }
        }
        abilitiesRateStatis.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initAbilitiesPartStatis() {
        DataBase data_ = getDataBase();
        abilitiesPartStatis = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getImmuLowStatisTypes().isEmpty()) {
                abilitiesPartStatis.add(a);
            }
        }
        abilitiesPartStatis.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initEffMoves() {
        DataBase data_ = getDataBase();
        effMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_.getSecEffectIfNoDamage()) {
                effMoves.add(m);
            }
        }
        effMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initPressureAbilities() {
        DataBase data_ = getDataBase();
        pressureAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.getNbUsedPp() > 0) {
                pressureAbilities.add(a);
            }
        }
        pressureAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initMovesSecEffItems() {
        DataBase data_ = getDataBase();
        movesSecEffItems = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (!move_.getSecEffectsByItem().isEmpty()) {
                movesSecEffItems.add(m);
            }
        }
        movesSecEffItems.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initBeginRoundStatusFoe() {
        DataBase data_ = getDataBase();
        beginRoundStatusFoe = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_.getStatusType() != StatusType.INDIVIDUEL && st_ instanceof StatusBeginRound) {
                beginRoundStatusFoe.add(s);
            }
        }
        beginRoundStatusFoe.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
    }

    private void initCopyMoveTypesAb() {
        DataBase data_ = getDataBase();
        copyMoveTypesAb = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isCopyMovesTypes()) {
                copyMoveTypesAb.add(a);
            }
        }
    }

    private void initSubstitutingMoves() {
        DataBase data_ = getDataBase();
        substitutingMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_.getSwitchType() == SwitchType.LANCEUR) {
                substitutingMoves.add(m);
            }
        }
        substitutingMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initMovesEffectSwitchMoveTypes() {
        DataBase data_ = getDataBase();
        movesTypeDefMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectSwitchMoveTypes && !((EffectSwitchMoveTypes) e).getReplacingTypes().isEmpty()) {
                    movesTypeDefMoves.add(m);
                    break;
                }
            }
        }
        movesTypeDefMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesChangeTypeMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectSwitchMoveTypes && !((EffectSwitchMoveTypes) e).getChangeTypes().isEmpty()) {
                    movesChangeTypeMoves.add(m);
                    break;
                }
            }
        }
        movesChangeTypeMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initPrivateMoves() {
        DataBase data_ = getDataBase();
        privatingMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectRestriction && ((EffectRestriction) e).getChoiceRestriction() != MoveChoiceRestrictionType.NOTHING) {
                    privatingMoves.add(m);
                    break;
                }
            }
        }
        privatingMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initAccuracyEvasinessElements() {
        DataBase data_ = getDataBase();
        movesIgnAcc = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_.getIgnVarAccurUserNeg() && move_.getTargetChoice() != TargetChoice.LANCEUR) {
                movesIgnAcc.add(m);
            }
        }
        movesIgnAcc.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesIgnEva = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_.getIgnVarEvasTargetPos() && move_.getTargetChoice() != TargetChoice.LANCEUR) {
                movesIgnEva.add(m);
            }
        }
        movesIgnEva.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesGlobalAcc = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getMultAccuracy().isZero()) {
                    movesGlobalAcc.add(m);
                    break;
                }
            }
        }
        movesGlobalAcc.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initStatusElements() {
        DataBase data_ = getDataBase();
        successfulStatus = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (!st_.getEffectsPartner().isEmpty() && st_.getEffectsPartner().first().getWeddingAlly()) {
                successfulStatus.add(s);
            }
        }
        successfulStatus.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        initGlobalMovesStatus();
        abilitiesPartStatus = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getImmuStatusTypes().isEmpty()) {
                abilitiesPartStatus.add(a);
            }
        }
        abilitiesPartStatus.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesFighterStatus = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getImmuStatus().isEmpty()) {
                abilitiesFighterStatus.add(a);
            }
        }
        abilitiesFighterStatus.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        itemsFighterStatus = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getImmuStatus().isEmpty()) {
                itemsFighterStatus.add(i);
            }
        }
        itemsFighterStatus.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
    }

    private void initGlobalMovesStatus() {
        DataBase data_ = getDataBase();
        globalMovesStatus = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getPreventStatus().isEmpty()) {
                    globalMovesStatus.add(m);
                }
            }
        }
        globalMovesStatus.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initCriticalHitElements() {
        DataBase data_ = getDataBase();
        abilitiesImmuCh = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isImmuCh()) {
                abilitiesImmuCh.add(a);
            }
        }
        abilitiesImmuCh.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initMovesBoostCh();
        abilitesMultEvtCh = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultEvtRateCh().isZero()) {
                abilitesMultEvtCh.add(a);
            }
        }
        abilitesMultEvtCh.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitesMultRateCh = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultDamageCh().isZero()) {
                abilitesMultRateCh.add(a);
            }
        }
        abilitesMultRateCh.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initMovesBoostCh() {
        DataBase data_ = getDataBase();
        movesBoostCh = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectDamage && ((EffectDamage) e).getChRate() > 0) {
                    movesBoostCh.add(m);
                    break;
                }
            }
        }
        movesBoostCh.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initStatisticsCalculationElements() {
        DataBase data_ = getDataBase();
        abilitiesBoostingStat = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getBonusStatRank().isEmpty()) {
                abilitiesBoostingStat.add(a);
            }
        }
        abilitiesBoostingStat.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initItemsBoostingStat();
        initAbItMultStat();
        initMultStatTeamGlobal();
        abilitiesAllyMultStat = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultStatAlly().isEmpty()) {
                abilitiesAllyMultStat.add(a);
            }
        }
        abilitiesAllyMultStat.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        statusMultStat = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_.getStatusType() != StatusType.RELATION_UNIQUE && !st_.getMultStat().isEmpty()) {
                statusMultStat.add(s);
            }
        }
        statusMultStat.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        abilitiesImmuMultStat = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getImmuLowStatIfStatus().isEmpty()) {
                abilitiesImmuMultStat.add(a);
            }
        }
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

    private void initAbItMultStat() {
        DataBase data_ = getDataBase();
        abilitiesMultStat = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultStat().isEmpty() || !ab_.getMultStatIfCat().isEmpty()) {
                abilitiesMultStat.add(a);
            }
        }
        abilitiesMultStat.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        itemsMultStat = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getMultStat().isEmpty()) {
                itemsMultStat.add(i);
            }
        }
        itemsMultStat.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initMultStatTeamGlobal() {
        DataBase data_ = getDataBase();
        movesGlobalMultStat = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getMultStatIfContainsType().isEmpty()) {
                    movesGlobalMultStat.add(m);
                    break;
                }
            }
        }
        movesGlobalMultStat.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        initMultStatTeam();
    }

    private void initMultStatTeam() {
        DataBase data_ = getDataBase();
        movesTeamMultStat = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectTeam && !((EffectTeam) e).getMultStatistic().isEmpty()) {
                    movesTeamMultStat.add(m);
                    break;
                }
            }
        }
        movesTeamMultStat.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesFoeTeamMultStat = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectTeam && !((EffectTeam) e).getMultStatisticFoe().isEmpty()) {
                    movesFoeTeamMultStat.add(m);
                    break;
                }
            }
        }
        movesFoeTeamMultStat.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initComboMultStat() {
        DataBase data_ = getDataBase();
        comboMultStat = new CustList<StringList>();
        for (StringList g: data_.getCombos().getEffects().getKeys()) {
            EffectCombo effect_ = data_.getCombos().getEffects().getVal(g);
            if (effect_.estActifEquipe()) {
                comboMultStat.add(new StringList(g));
            }
        }
        for (StringList v: comboMultStat) {
            v.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        }
    }

    private void initComboEvtStat() {
        DataBase data_ = getDataBase();
        comboEvtStat = new CustList<StringList>();
        for (StringList g: data_.getCombos().getEffects().getKeys()) {
            EffectCombo effect_ = data_.getCombos().getEffects().getVal(g);
            if (!effect_.getMultEvtRateSecEff().isZero()) {
                comboEvtStat.add(new StringList(g));
            }
        }
        for (StringList v: comboEvtStat) {
            v.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        }
    }

    private void initItemsBoostingStat() {
        DataBase data_ = getDataBase();
        itemsBoostingStat = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && (!((ItemForBattle) it_).getMultStatRank().isEmpty() || !((ItemForBattle) it_).getMultStatPokemonRank().isEmpty())) {
                itemsBoostingStat.add(i);
            }
        }
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getMultStat().isEmpty()) {
                itemsBoostingStat.add(i);
            }
        }
        itemsBoostingStat.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
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
        abilitiesImmuSecEffOther = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isCancelSecEffectOther()) {
                abilitiesImmuSecEffOther.add(a);
            }
        }
        abilitiesImmuSecEffOther.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesImmuSecEffOwner = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isCancelSecEffectOwner()) {
                abilitiesImmuSecEffOwner.add(a);
            }
        }
        abilitiesImmuSecEffOwner.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesAchieveTarget = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isAchievedDisappearedPk()) {
                abilitiesAchieveTarget.add(a);
            }
        }
        abilitiesAchieveTarget.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesBreakProtectMoves = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isBreakProtection()) {
                abilitiesBreakProtectMoves.add(a);
            }
        }
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

    private void initItemsCancelImmu() {
        DataBase data_ = getDataBase();
        itemsCancelImmu = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && ((ItemForBattle) it_).getCancelImmuType()) {
                itemsCancelImmu.add(i);
            }
        }
        itemsCancelImmu.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
    }

    private void initAbilitiesImmu() {
        DataBase data_ = getDataBase();
        abilitiesImmuAllies = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getImmuAllyFromMoves().isEmpty()) {
                abilitiesImmuAllies.add(a);
            }
        }
        abilitiesImmuAllies.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesImmuAlliesDam = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isImmuDamageAllyMoves()) {
                abilitiesImmuAlliesDam.add(a);
            }
        }
        abilitiesImmuAlliesDam.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesImmu = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getImmuMove().isEmpty() || ab_.isImmuSufferedDamageLowEff()) {
                abilitiesImmu.add(a);
            }
        }
        abilitiesImmu.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initItImmu() {
        DataBase data_ = getDataBase();
        itemsImmu = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getImmuMoves().isEmpty()) {
                itemsImmu.add(i);
            }
        }
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getHealHpBySuperEffMove().isZero()) {
                itemsImmu.add(i);
            }
        }
        itemsImmu.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
    }

    private void initImmuTypes() {
        DataBase data_ = getDataBase();
        abilitiesImmuTypes = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getImmuMoveTypesByWeather().isEmpty()) {
                abilitiesImmuTypes.add(a);
            }
        }
        abilitiesImmuTypes.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        itemsImmuTypes = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getImmuTypes().isEmpty()) {
                itemsImmuTypes.add(i);
            }
        }
        itemsImmuTypes.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
    }

    private void initMovesBrImmu() {
        DataBase data_ = getDataBase();
        movesGlobalBreakImmu = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getEfficiencyMoves().isEmpty()) {
                    movesGlobalBreakImmu.add(m);
                    break;
                }
            }
        }
        movesGlobalBreakImmu.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesGlobalBreakImmuAb = new StringList();
        abilitiesBreakable = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getCancelProtectingAbilities().isEmpty()) {
                    abilitiesBreakable.addAllElts(((EffectGlobal) e).getCancelProtectingAbilities());
                    movesGlobalBreakImmuAb.add(m);
                    break;
                }
            }
        }
        movesGlobalBreakImmuAb.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        abilitiesBreakable.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initDamageCalculationElements() {
        DataBase data_ = getDataBase();
        initMovesUserAllyDamage();
        abilitiesTargetDamage = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultDamageFoe().isEmpty() || !ab_.getMultSufferedDamageSuperEff().isZero()) {
                abilitiesTargetDamage.add(a);
            }
        }
        abilitiesTargetDamage.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initMovesTargetTeamDamage();
        abilitiesUserIgnTargetTeam = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getIgnFoeTeamMove().isEmpty()) {
                abilitiesUserIgnTargetTeam.add(a);
            }
        }
        abilitiesUserIgnTargetTeam.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesGlobal = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultPowerMovesTypesGlobal().isEmpty() || ab_.isReverseEffectsPowerMovesTypesGlobal()) {
                abilitiesGlobal.add(a);
            }
        }
        abilitiesGlobal.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initMovesGlobal();
        initDamageDefElement();
        initAbilitiesUserDamage();
        movesIgn();
    }

    private void initDamageDefElement() {
        DataBase data_ = getDataBase();
        itemsUserDamage = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getMultDamage().isEmpty()) {
                itemsUserDamage.add(i);
            }
        }
        itemsUserDamage.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        abilitiesUserDamage = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultDamage().isEmpty()) {
                abilitiesUserDamage.add(a);
            }
        }
        abilitiesUserDamage.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initMovesInvokDamage();
        itemsTargetDamage = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getMultFoesDamage().isEmpty()) {
                itemsTargetDamage.add(i);
            }
        }
        itemsTargetDamage.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        initMovesGlobalPrepaDamage();
        statusDamage = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_.getStatusType() != StatusType.INDIVIDUEL && st_.estActifPartenaire() && st_.getEffectsPartner().first().getWeddingAlly()) {
                statusDamage.add(s);
            }
        }
        statusDamage.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
    }

    private void initMovesUserAllyDamage() {
        DataBase data_ = getDataBase();
        movesUserAllyDamage = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectAlly && !((EffectAlly) e).getMultAllyDamage().isZero()) {
                    movesUserAllyDamage.add(m);
                    break;
                }
            }
        }
        movesUserAllyDamage.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initMovesTargetTeamDamage() {
        DataBase data_ = getDataBase();
        movesTargetTeamDamage = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectTeam && !((EffectTeam) e).getMultDamage().isEmpty()) {
                    movesTargetTeamDamage.add(m);
                    break;
                }
            }
        }
        movesTargetTeamDamage.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initMovesGlobal() {
        DataBase data_ = getDataBase();
        movesGlobal = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && (!((EffectGlobal) e).getMultPowerMoves().isEmpty() || !((EffectGlobal) e).getMultDamageTypesMoves().isEmpty())) {
                    movesGlobal.add(m);
                    break;
                }
            }
        }
        movesGlobal.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initMovesInvokDamage() {
        DataBase data_ = getDataBase();
        movesInvokDamage = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectInvoke && !((EffectInvoke) e).getRateInvokationMove().isZero()) {
                    movesInvokDamage.add(m);
                    break;
                }
            }
        }
        movesInvokDamage.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initMovesGlobalPrepaDamage() {
        DataBase data_ = getDataBase();
        movesGlobalPrepaDamage = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && !((EffectGlobal) e).getMovesUsedByTargetedFighters().isEmpty() && !((EffectGlobal) e).getMultDamagePrepaRound().isEmpty()) {
                    movesGlobalPrepaDamage.add(m);
                    break;
                }
            }
        }
        movesGlobalPrepaDamage.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initAbilitiesUserDamage() {
        DataBase data_ = getDataBase();
        abilitiesUserTargetDamage = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultAllyDamage().isZero()) {
                abilitiesUserTargetDamage.add(a);
            }
        }
        abilitiesUserTargetDamage.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesUserStabDamage = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultStab().isZero()) {
                abilitiesUserStabDamage.add(a);
            }
        }
        abilitiesUserStabDamage.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void movesIgn() {
        DataBase data_ = getDataBase();
        movesIgnLowAtt = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectDamage && !((EffectDamage) e).getIgnVarStatUserNeg().isEmpty()) {
                    movesIgnLowAtt.add(m);
                    break;
                }
            }
        }
        movesIgnLowAtt.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesIgnIncDef = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectDamage && !((EffectDamage) e).getIgnVarStatTargetPos().isEmpty()) {
                    movesIgnIncDef.add(m);
                    break;
                }
            }
        }
        movesIgnIncDef.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initPowerElements() {
        DataBase data_ = getDataBase();
        damagingMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectDamage) {
                    damagingMoves.add(m);
                }
            }
        }
        damagingMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        itemsUserPower = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getMultPower().isEmpty()) {
                itemsUserPower.add(i);
            }
        }
        itemsUserPower.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        initMovePowerUserTarget();
        abilitiesUserPower = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getChangingBoostTypes().isEmpty()) {
                abilitiesUserPower.add(a);
            }
        }
        abilitiesUserPower.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initMovePowerUserTarget() {
        DataBase data_ = getDataBase();
        movesUserPower = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectMultUsedMovePower) {
                    movesUserPower.add(m);
                    break;
                }
            }
        }
        movesUserPower.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesTargetPower = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectMultSufferedMovePower) {
                    movesTargetPower.add(m);
                    break;
                }
            }
        }
        movesTargetPower.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initWhileDamageElements() {
        DataBase data_ = getDataBase();
        movesProtAgainstKo = new StringList(data_.getMovesProtSingleTargetAgainstKo());
        movesProtAgainstKo.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        initItemsProtAgainstKo();
        movesCannotKo = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_ instanceof DamagingMoveData && ((DamagingMoveData) move_).getCannotKo()) {
                movesCannotKo.add(m);
            }
        }
        minHpNotKo = data_.getMinHp();
        movesCannotKo.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        itemsAbs = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getDrainedHpByDamageRate().isZero()) {
                itemsAbs.add(i);
            }
        }
        itemsAbs.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        initWhileDamageAbilities();
        initRecoilMembers();
        abilitiesKoTarget = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultStatIfKoFoe().isEmpty()) {
                abilitiesKoTarget.add(a);
            }
        }
        abilitiesKoTarget.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initMovesKoTarget();
    }

    private void initMovesKoTarget() {
        DataBase data_ = getDataBase();
        movesKoTarget = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectDamage && !((EffectDamage) e).getBoostStatisOnceKoFoe().isEmpty()) {
                    movesKoTarget.add(m);
                }
            }
        }
        movesKoTarget.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initItemsProtAgainstKo() {
        DataBase data_ = getDataBase();
        itemsProtAgainstKo = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && (!((ItemForBattle) it_).getProtectAgainstKo().isZero() || !((ItemForBattle) it_).getProtectAgainstKoIfFullHp().isZero())) {
                itemsProtAgainstKo.add(i);
            }
        }
        itemsProtAgainstKo.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
    }

    private void initWhileDamageAbilities() {
        DataBase data_ = getDataBase();
        abilitiesRevAbs = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isInflictingDamageInsteadOfSuffering()) {
                abilitiesRevAbs.add(a);
            }
        }
        abilitiesRevAbs.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        initAbilitiesDamageStatis();
        initAbilitiesChangingTypesDamage();
        abilitiesTakingItem = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isTakeItemByDamagingMove()) {
                abilitiesTakingItem.add(a);
            }
        }
        abilitiesTakingItem.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesStatisVarUser = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getLowStatFoeHit().isEmpty()) {
                abilitiesStatisVarUser.add(a);
            }
        }
        abilitiesStatisVarUser.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesStatus = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getSingleStatus().events().isEmpty()) {
                abilitiesStatus.add(a);
            }
        }
        abilitiesStatus.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesCopyAb = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isMumy()) {
                abilitiesCopyAb.add(a);
            }
        }
        abilitiesCopyAb.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initAbilitiesChangingTypesDamage() {
        DataBase data_ = getDataBase();
        abilitiesChangingTypesDamage = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isChgtTypeByDamage()) {
                abilitiesChangingTypesDamage.add(a);
            }
        }
        abilitiesChangingTypesDamage.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initAbilitiesDamageStatis() {
        DataBase data_ = getDataBase();
        abilitiesDamageStatis = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMaxStatisticsIfCh().isEmpty() || !ab_.getMultStatIfDamgeType().isEmpty() || !ab_.getMultStatIfDamageCat().isEmpty()) {
                abilitiesDamageStatis.add(a);
            }
        }
        abilitiesDamageStatis.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initEndRoundUserMembers() {
        DataBase data_ = getDataBase();
        abilitiesEndRound = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getBoostStatRankEndRound().isEmpty()) {
                abilitiesEndRound.add(a);
            }
        }
        abilitiesEndRound.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        berryEndRound = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof Berry && ((Berry) it_).getHealPp() > 0) {
                berryEndRound.add(i);
            }
        }
        berryEndRound.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        movesChangingAttOrder = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectOrder) {
                    movesChangingAttOrder.add(m);
                }
            }
        }
        movesChangingAttOrder.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initBerryEndEffectMembers() {
        DataBase data_ = getDataBase();
        berryUser = new StringList();
        berryTarget = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof Berry && (!((Berry) it_).getHealHp().isZero() || !((Berry) it_).getHealHpRate().isZero())) {
                berryUser.add(i);
                berryTarget.add(i);
            }
        }
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getCategoryBoosting().isEmpty()) {
                berryTarget.add(i);
            }
        }
        berryTarget.removeDuplicates();
        berryUser.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        berryTarget.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
    }

    private void initRecoilMembers() {
        DataBase data_ = getDataBase();
        recoilItems = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getDamageRecoil().isZero()) {
                recoilItems.add(i);
            }
        }
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getDamageRateRecoilFoe().isEmpty()) {
                recoilItems.add(i);
            }
        }
        recoilItems.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        recoilAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getRecoilDamageFoe().isZero()) {
                recoilAbilities.add(a);
            }
        }
        recoilAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initStatisticsImmuElements() {
        DataBase data_ = getDataBase();
        abilitiesFighterStatis = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getImmuLowStat().isEmpty() || !ab_.getImmuLowStatIfStatus().isEmpty()) {
                abilitiesFighterStatis.add(a);
            }
        }
        abilitiesFighterStatis.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        itemsFighterStatis = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && ((ItemForBattle) it_).getImmuLowStatis()) {
                itemsFighterStatis.add(i);
            }
        }
        itemsFighterStatis.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        abilitiesFighterStatisVar = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getMultVarBoost().isZero() || !ab_.getMultStatIfLowStat().isEmpty()) {
                abilitiesFighterStatisVar.add(a);
            }
        }
        abilitiesFighterStatisVar.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initProtectingMembers() {
        DataBase data_ = getDataBase();
        protectItems = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof Berry && !((Berry) it_).getHealHpBySuperEffMove().isZero()) {
                protectItems.add(i);
            }
        }
        protectItems.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        protectAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getBoostStatRankProtected().isEmpty() || !ab_.getHealHpByTypeIfWeather().isEmpty()) {
                protectAbilities.add(a);
            }
        }
        protectAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        protectMoves = new StringList(data_.getMovesCountering());
        protectMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initInvokingMembers() {
        DataBase data_ = getDataBase();
        movesInvoking = new StringList(data_.getMovesInvoking());
        movesInvoking.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
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
        movesThieving.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesAttracting.removeDuplicates();
        movesAttracting.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesMirror.removeDuplicates();
        movesMirror.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initBeginRoundPreparingMembers() {
        DataBase data_ = getDataBase();
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
        prepaRoundMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        rechargeMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (move_.getRechargeRound()) {
                rechargeMoves.add(m);
            }
        }
        rechargeMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        disappearingRoundMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        speedPreparingItems = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && ((ItemForBattle) it_).getAttacksSoon()) {
                speedPreparingItems.add(i);
            }
        }
        speedPreparingItems.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        immuRecharging = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isImmuRechargeRound()) {
                immuRecharging.add(a);
            }
        }
        immuRecharging.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initBeginRoundStatusMembers() {
        DataBase data_ = getDataBase();
        beginRoundStatus = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_.getStatusType() != StatusType.RELATION_UNIQUE && st_ instanceof StatusBeginRound) {
                beginRoundStatus.add(s);
            }
        }
        beginRoundStatus.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        deleteStatusMove = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            if (!move_.getDeletedStatus().isEmpty()) {
                deleteStatusMove.add(m);
            }
        }
        deleteStatusMove.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        immuStatusAbility = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getImmuStatusBeginRound().isEmpty()) {
                immuStatusAbility.add(a);
            }
        }
        immuStatusAbility.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        autoDamage = new StringList();
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_ instanceof StatusBeginRoundAutoDamage) {
                autoDamage.add(s);
            }
        }
        autoDamage.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        damgeFormula = data_.getFormula(data_.getDamageFormula(), getLanguage());
        mapVar = new NatStringTreeMap<String>();
        mapVar.putAllMap(data_.getDescriptions(data_.getDamageFormula(), getLanguage()));
        NatStringTreeMap<String> mapAutoDamage_ = new NatStringTreeMap<String>();
        int len_;
        len_ = autoDamage.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            mapAutoDamage_.putAllMap(data_.getDescriptions(getNumericString(i), getLanguage()));
        }
        mapAutoDamage = mapAutoDamage_;
    }

    private void initSwitchingMembers() {
        DataBase data_ = getDataBase();
        abilitiesSwitch = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getHealedHpRateBySwitch().isZero() || ab_.isHealedStatusBySwitch()) {
                abilitiesSwitch.add(a);
            }
        }
        abilitiesSwitch.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        deletedStatusSwitch = new StringList();
        for (String a: data_.getStatus().getKeys()) {
            Status ab_ = data_.getStatus(a);
            if (ab_.getDisabledEffIfSwitch()) {
                deletedStatusSwitch.add(a);
            }
        }
        for (String s: data_.getStatus().getKeys()) {
            Status st_ = data_.getStatus(s);
            if (st_.getStatusType() != StatusType.INDIVIDUEL) {
                deletedStatusSwitch.add(s);
            }
        }
        deletedStatusSwitch.removeDuplicates();
        deletedStatusSwitch.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        entryHazard = new StringList(data_.getMovesEffectWhileSending());
        entryHazard.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initSpeedElements() {
        DataBase data_ = getDataBase();
        abilitiesPrio = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.getIncreasedPrio().isEmpty() || !ab_.getIncreasedPrioTypes().isEmpty()) {
                abilitiesPrio.add(a);
            }
        }
        abilitiesPrio.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        slowAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isSlowing()) {
                slowAbilities.add(a);
            }
        }
        slowAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        slowItems = new StringList();
        for (String a: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(a);
            if (it_ instanceof ItemForBattle && ((ItemForBattle) it_).getAttackLast()) {
                slowItems.add(a);
            }
        }
        slowItems.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        initReverseSpeedMoves();
        initItSpeed();
    }

    private void initItSpeed() {
        DataBase data_ = getDataBase();
        berrySpeed = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof Berry && ((Berry) it_).getLawForAttackFirst()) {
                berrySpeed.add(i);
            }
        }
        berrySpeed.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        itemSpeed = new StringList();
        for (String i: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(i);
            if (it_ instanceof ItemForBattle && !((ItemForBattle) it_).getLawForAttackFirst().events().isEmpty()) {
                itemSpeed.add(i);
            }
        }
        itemSpeed.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
    }

    private void initReverseSpeedMoves() {
        DataBase data_ = getDataBase();
        reverseSpeedMoves = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (e instanceof EffectGlobal && ((EffectGlobal) e).getReverseOrderOfSortBySpeed()) {
                    reverseSpeedMoves.add(m);
                    break;
                }
            }
        }
        reverseSpeedMoves.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
    }

    private void initSendingMembers() {
        DataBase data_ = getDataBase();
        initSendingAbilities();
        initSendingItems();
        changingTypesAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (ab_.isPlate()) {
                changingTypesAbilities.add(a);
            }
        }
        changingTypesAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initSendingItems() {
        DataBase data_ = getDataBase();
        itemsSentBeginWeather = new StringList();
        itemsSentBeginOther = new StringList();
        for (String a: data_.getItems().getKeys()) {
            Item it_ = data_.getItem(a);
            if (!(it_ instanceof ItemForBattle) || !((ItemForBattle) it_).enabledSending()) {
                continue;
            }
            if (((ItemForBattle) it_).getEffectSending().first().getEnabledWeather().isEmpty() && !((ItemForBattle) it_).getEffectSending().first().getDisableWeather()) {
                itemsSentBeginOther.add(a);
            } else {
                itemsSentBeginWeather.add(a);
            }
        }
        itemsSentBeginOther.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
        itemsSentBeginWeather.sortElts(DictionaryComparatorUtil.cmpItems(data_,getLanguage()));
    }

    private void initSendingAbilities() {
        DataBase data_ = getDataBase();
        abilitiesSentBeginWeather = new StringList();
        abilitiesSentBeginOther = new StringList();
        abilitiesSentStatis = new StringList();
        copyAbilities = new StringList();
        for (String a: data_.getAbilities().getKeys()) {
            AbilityData ab_ = data_.getAbility(a);
            if (!ab_.enabledSending()) {
                continue;
            }
            abilitiesSentStatis.add(a);
//            if (ab_.getEffectSending().first() instanceof EffectWhileSendingWithStatistic) {
//                abilitiesSentStatis.add(a);
//                continue;
//            }
            if (ab_.getEffectSending().first().getCopyingAbility()) {
                copyAbilities.add(a);
            } else if (ab_.getEffectSending().first().getEnabledWeather().isEmpty() && !ab_.getEffectSending().first().getDisableWeather()) {
                abilitiesSentBeginOther.add(a);
            } else {
                abilitiesSentBeginWeather.add(a);
            }
        }
        abilitiesSentStatis.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesSentBeginOther.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        abilitiesSentBeginWeather.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        copyAbilities.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
    }

    private void initBoosts(long _minBoost, long _maxBoost) {
        DataBase data_ = getDataBase();
        for (long b = _minBoost; b <= _maxBoost; b++) {
            String rateBoost_ = data_.getRateBoost();
//            NumericString chNum_=new NumericString(rateBoost_);
            StringMap<String> variables_ = new StringMap<String>();
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.BOOST), Long.toString(b));
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
            variables_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.BOOST), Long.toString(b));
//            chNum_.replaceVars(variables_);
//            chNum_.evaluateExp();
//            Rate res_ = chNum_.toRate();
//            boostsCh.put(b, res_);
            boostsCh.put(b, data_.evaluateNumericable(rateBoost_, variables_, Rate.one()));
        }
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
        rates = new StringMap<String>();
        for (DifficultyWinPointsFight d: data_.getRates().getKeys()) {
            rates.put(d.getWinName(), data_.getFormula(data_.getRates().getVal(d), getLanguage()));
        }
        varRates = new NatStringTreeMap<String>();
        for (DifficultyWinPointsFight d: data_.getRates().getKeys()) {
            varRates.putAllMap(data_.getDescriptions(data_.getRates().getVal(d), getLanguage()));
        }
        lawsRates = new StringMap<AbsBasicTreeMap<Rate, Rate>>();
        for (DifficultyModelLaw d: data_.getLawsDamageRate().getKeys()) {
            DictionaryComparator<Rate,Rate> tree_ = DictionaryComparatorUtil.buildRateRate();
            MonteCarloNumber law_ = data_.getLawsDamageRate().getVal(d).getLaw();
            for (Rate e: law_.events()) {
                tree_.put(e, law_.normalizedRate(e));
            }
            lawsRates.put(d.getModelName(), tree_);
        }
        statisticAnim = Statistic.getStatisticsWithBoost();
    }

    public String getTrStatistic(int _index) {
        Statistic d_ = statisticAnim.get(_index);
        DataBase data_ = getDataBase();
        return data_.getTranslatedStatistics().getVal(getLanguage()).getVal(d_);
    }
    public String getAnimStatistic(int _index) {
        Statistic d_ = statisticAnim.get(_index);
        DataBase data_ = getDataBase();
        return BaseSixtyFourUtil.getStringByImage(data_.getAnimStatis().getVal(d_.getStatName()));
    }
    public String getAnimAbsorb() {
        DataBase data_ = getDataBase();
        return BaseSixtyFourUtil.getStringByImage(data_.getAnimAbsorb());
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
        return data_.getFormula(getNumericString(_index), getLanguage());
    }

    private String getNumericString(int _index) {
        String auto_ = autoDamage.get(_index);
        DataBase data_ = getDataBase();
        StatusBeginRoundAutoDamage st_ = (StatusBeginRoundAutoDamage) data_.getStatus(auto_);
        String str_ = data_.getDamageFormula();
        StringMap<String> replace_ = new StringMap<String>();
        replace_.put(StringUtil.concat(DataBase.VAR_PREFIX,Fight.POWER), st_.getPower().toNumberString());
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
        getForms().put(CST_ABILITY, abilitiesSentBeginWeather.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesSentBeginOth(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSentBeginOther.get(_index));
    }
    public String clickAbilitiesSentBeginOth(int _index) {
        getForms().put(CST_ABILITY, abilitiesSentBeginOther.get(_index));
        return CST_ABILITY;
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
        getForms().put(CST_ABILITY, changingTypesAbilities.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesSentStatis(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSentStatis.get(_index));
    }
    public String clickAbilitiesSentStatis(int _index) {
        getForms().put(CST_ABILITY, abilitiesSentStatis.get(_index));
        return CST_ABILITY;
    }
    public String getTrCopyAbilities(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(copyAbilities.get(_index));
    }
    public String clickCopyAbilities(int _index) {
        getForms().put(CST_ABILITY, copyAbilities.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesPrio(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPrio.get(_index));
    }
    public String clickAbilitiesPrio(int _index) {
        getForms().put(CST_ABILITY, abilitiesPrio.get(_index));
        return CST_ABILITY;
    }
    public String getTrSlowAbilities(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(slowAbilities.get(_index));
    }
    public String clickSlowAbilities(int _index) {
        getForms().put(CST_ABILITY, slowAbilities.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesSwitch(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesSwitch.get(_index));
    }
    public String clickAbilitiesSwitch(int _index) {
        getForms().put(CST_ABILITY, abilitiesSwitch.get(_index));
        return CST_ABILITY;
    }
    public String getTrImmuStatusAbility(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(immuStatusAbility.get(_index));
    }
    public String clickImmuStatusAbility(int _index) {
        getForms().put(CST_ABILITY, immuStatusAbility.get(_index));
        return CST_ABILITY;
    }
    public String getTrImmuRecharging(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(immuRecharging.get(_index));
    }
    public String clickImmuRecharging(int _index) {
        getForms().put(CST_ABILITY, immuRecharging.get(_index));
        return CST_ABILITY;
    }
    public String getTrCopyMoveTypesAb(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(copyMoveTypesAb.get(_index));
    }
    public String clickCopyMoveTypesAb(int _index) {
        getForms().put(CST_ABILITY, copyMoveTypesAb.get(_index));
        return CST_ABILITY;
    }
    public String getTrPressureAbilities(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(pressureAbilities.get(_index));
    }
    public String clickPressureAbilities(int _index) {
        getForms().put(CST_ABILITY, pressureAbilities.get(_index));
        return CST_ABILITY;
    }
    public String getTrProtectAbilities(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(protectAbilities.get(_index));
    }
    public String clickProtectAbilities(int _index) {
        getForms().put(CST_ABILITY, protectAbilities.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesPartStatis(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPartStatis.get(_index));
    }
    public String clickAbilitiesPartStatis(int _index) {
        getForms().put(CST_ABILITY, abilitiesPartStatis.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesRateStatis(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesRateStatis.get(_index));
    }
    public String clickAbilitiesRateStatis(int _index) {
        getForms().put(CST_ABILITY, abilitiesRateStatis.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesFighterStatis(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatis.get(_index));
    }
    public String clickAbilitiesFighterStatis(int _index) {
        getForms().put(CST_ABILITY, abilitiesFighterStatis.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesFighterStatisVar(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatisVar.get(_index));
    }
    public String clickAbilitiesFighterStatisVar(int _index) {
        getForms().put(CST_ABILITY, abilitiesFighterStatisVar.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesPartStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesPartStatus.get(_index));
    }
    public String clickAbilitiesPartStatus(int _index) {
        getForms().put(CST_ABILITY, abilitiesPartStatus.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesFighterStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesFighterStatus.get(_index));
    }
    public String clickAbilitiesFighterStatus(int _index) {
        getForms().put(CST_ABILITY, abilitiesFighterStatus.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesRevAbs(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesRevAbs.get(_index));
    }
    public String clickAbilitiesRevAbs(int _index) {
        getForms().put(CST_ABILITY, abilitiesRevAbs.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesDamageStatis(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesDamageStatis.get(_index));
    }
    public String clickAbilitiesDamageStatis(int _index) {
        getForms().put(CST_ABILITY, abilitiesDamageStatis.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesChangingTypesDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesChangingTypesDamage.get(_index));
    }
    public String clickAbilitiesChangingTypesDamage(int _index) {
        getForms().put(CST_ABILITY, abilitiesChangingTypesDamage.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesTakingItem(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTakingItem.get(_index));
    }
    public String clickAbilitiesTakingItem(int _index) {
        getForms().put(CST_ABILITY, abilitiesTakingItem.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesStatisVarUser(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesStatisVarUser.get(_index));
    }
    public String clickAbilitiesStatisVarUser(int _index) {
        getForms().put(CST_ABILITY, abilitiesStatisVarUser.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesStatus.get(_index));
    }
    public String clickAbilitiesStatus(int _index) {
        getForms().put(CST_ABILITY, abilitiesStatus.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesCopyAb(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesCopyAb.get(_index));
    }
    public String clickAbilitiesCopyAb(int _index) {
        getForms().put(CST_ABILITY, abilitiesCopyAb.get(_index));
        return CST_ABILITY;
    }
    public String getTrRecoilAbilities(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(recoilAbilities.get(_index));
    }
    public String clickRecoilAbilities(int _index) {
        getForms().put(CST_ABILITY, recoilAbilities.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesKoTarget(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesKoTarget.get(_index));
    }
    public String clickAbilitiesKoTarget(int _index) {
        getForms().put(CST_ABILITY, abilitiesKoTarget.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesEndRound(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesEndRound.get(_index));
    }
    public String clickAbilitiesEndRound(int _index) {
        getForms().put(CST_ABILITY, abilitiesEndRound.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesUserPower(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserPower.get(_index));
    }
    public String clickAbilitiesUserPower(int _index) {
        getForms().put(CST_ABILITY, abilitiesUserPower.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesTargetDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTargetDamage.get(_index));
    }
    public String clickAbilitiesTargetDamage(int _index) {
        getForms().put(CST_ABILITY, abilitiesTargetDamage.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesGlobal(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesGlobal.get(_index));
    }
    public String clickAbilitiesGlobal(int _index) {
        getForms().put(CST_ABILITY, abilitiesGlobal.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesUserDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserDamage.get(_index));
    }
    public String clickAbilitiesUserDamage(int _index) {
        getForms().put(CST_ABILITY, abilitiesUserDamage.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesUserTargetDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserTargetDamage.get(_index));
    }
    public String clickAbilitiesUserTargetDamage(int _index) {
        getForms().put(CST_ABILITY, abilitiesUserTargetDamage.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesUserStabDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserStabDamage.get(_index));
    }
    public String clickAbilitiesUserStabDamage(int _index) {
        getForms().put(CST_ABILITY, abilitiesUserStabDamage.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesUserIgnTargetTeam(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesUserIgnTargetTeam.get(_index));
    }
    public String clickAbilitiesUserIgnTargetTeam(int _index) {
        getForms().put(CST_ABILITY, abilitiesUserIgnTargetTeam.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesBreakable(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakable.get(_index));
    }
    public String clickAbilitiesBreakable(int _index) {
        getForms().put(CST_ABILITY, abilitiesBreakable.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesImmuTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuTypes.get(_index));
    }
    public String clickAbilitiesImmuTypes(int _index) {
        getForms().put(CST_ABILITY, abilitiesImmuTypes.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesImmuAllies(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuAllies.get(_index));
    }
    public String clickAbilitiesImmuAllies(int _index) {
        getForms().put(CST_ABILITY, abilitiesImmuAllies.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesImmuAlliesDam(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuAlliesDam.get(_index));
    }
    public String clickAbilitiesImmuAlliesDam(int _index) {
        getForms().put(CST_ABILITY, abilitiesImmuAlliesDam.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesImmu(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmu.get(_index));
    }
    public String clickAbilitiesImmu(int _index) {
        getForms().put(CST_ABILITY, abilitiesImmu.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesImmuSecEffOther(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuSecEffOther.get(_index));
    }
    public String clickAbilitiesImmuSecEffOther(int _index) {
        getForms().put(CST_ABILITY, abilitiesImmuSecEffOther.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesImmuSecEffOwner(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuSecEffOwner.get(_index));
    }
    public String clickAbilitiesImmuSecEffOwner(int _index) {
        getForms().put(CST_ABILITY, abilitiesImmuSecEffOwner.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesAchieveTarget(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesAchieveTarget.get(_index));
    }
    public String clickAbilitiesAchieveTarget(int _index) {
        getForms().put(CST_ABILITY, abilitiesAchieveTarget.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesBreakProtectMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakProtectMoves.get(_index));
    }
    public String clickAbilitiesBreakProtectMoves(int _index) {
        getForms().put(CST_ABILITY, abilitiesBreakProtectMoves.get(_index));
        return CST_ABILITY;
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
        getForms().put(CST_ABILITY, abilitiesBoostingStat.get(_index));
        return CST_ABILITY;
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
        getForms().put(CST_ABILITY, abilitiesMultStat.get(_index));
        return CST_ABILITY;
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
        getForms().put(CST_ABILITY, abilitiesAllyMultStat.get(_index));
        return CST_ABILITY;
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
        getForms().put(CST_ABILITY, abilitiesImmuMultStat.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesTypeDefMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesTypeDefMoves.get(_index));
    }
    public String clickAbilitiesTypeDefMoves(int _index) {
        getForms().put(CST_ABILITY, abilitiesTypeDefMoves.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesChangeTypeMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesChangeTypeMoves.get(_index));
    }
    public String clickAbilitiesChangeTypeMoves(int _index) {
        getForms().put(CST_ABILITY, abilitiesChangeTypeMoves.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesBreakImmu(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesBreakImmu.get(_index));
    }
    public String clickAbilitiesBreakImmu(int _index) {
        getForms().put(CST_ABILITY, abilitiesBreakImmu.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesImmuCh(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitiesImmuCh.get(_index));
    }
    public String clickAbilitiesImmuCh(int _index) {
        getForms().put(CST_ABILITY, abilitiesImmuCh.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesMultEvtCh(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitesMultEvtCh.get(_index));
    }
    public String clickAbilitiesMultEvtCh(int _index) {
        getForms().put(CST_ABILITY, abilitesMultEvtCh.get(_index));
        return CST_ABILITY;
    }
    public String getTrAbilitiesMultRateCh(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(abilitesMultRateCh.get(_index));
    }
    public String clickAbilitiesMultRateCh(int _index) {
        getForms().put(CST_ABILITY, abilitesMultRateCh.get(_index));
        return CST_ABILITY;
    }
    public String getTrPrivatingMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(privatingMoves.get(_index));
    }
    public String clickPrivatingMoves(int _index) {
        getForms().put(CST_MOVE, privatingMoves.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesHealingSubstitute(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesHealingSubstitute.get(_index));
    }
    public String clickMovesHealingSubstitute(int _index) {
        getForms().put(CST_MOVE, movesHealingSubstitute.get(_index));
        return CST_MOVE;
    }
    public String getTrSubstitutingMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(substitutingMoves.get(_index));
    }
    public String clickSubstitutingMoves(int _index) {
        getForms().put(CST_MOVE, substitutingMoves.get(_index));
        return CST_MOVE;
    }
    public String getTrReverseSpeedMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(reverseSpeedMoves.get(_index));
    }
    public String clickReverseSpeedMoves(int _index) {
        getForms().put(CST_MOVE, reverseSpeedMoves.get(_index));
        return CST_MOVE;
    }
    public String getTrEntryHazard(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(entryHazard.get(_index));
    }
    public String clickEntryHazard(int _index) {
        getForms().put(CST_MOVE, entryHazard.get(_index));
        return CST_MOVE;
    }
    public String getTrDeleteStatusMove(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(deleteStatusMove.get(_index));
    }
    public String clickDeleteStatusMove(int _index) {
        getForms().put(CST_MOVE, deleteStatusMove.get(_index));
        return CST_MOVE;
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
        getForms().put(CST_MOVE, prepaRoundMoves.get(_index));
        return CST_MOVE;
    }
    public String getTrRechargeMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(rechargeMoves.get(_index));
    }
    public String clickRechargeMoves(int _index) {
        getForms().put(CST_MOVE, rechargeMoves.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesInvoking(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesInvoking.get(_index));
    }
    public String clickMovesInvoking(int _index) {
        getForms().put(CST_MOVE, movesInvoking.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesThieving(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesThieving.get(_index));
    }
    public String clickMovesThieving(int _index) {
        getForms().put(CST_MOVE, movesThieving.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesAttracting(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesAttracting.get(_index));
    }
    public String clickMovesAttracting(int _index) {
        getForms().put(CST_MOVE, movesAttracting.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesMirror(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesMirror.get(_index));
    }
    public String clickMovesMirror(int _index) {
        getForms().put(CST_MOVE, movesMirror.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesSecEffItems(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesSecEffItems.get(_index));
    }
    public String clickMovesSecEffItems(int _index) {
        getForms().put(CST_MOVE, movesSecEffItems.get(_index));
        return CST_MOVE;
    }
    public String getTrProtectMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(protectMoves.get(_index));
    }
    public String clickProtectMoves(int _index) {
        getForms().put(CST_MOVE, protectMoves.get(_index));
        return CST_MOVE;
    }
    public String getTrEffMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(effMoves.get(_index));
    }
    public String clickEffMoves(int _index) {
        getForms().put(CST_MOVE, effMoves.get(_index));
        return CST_MOVE;
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
        getForms().put(CST_MOVE, movesTeam.get(_index));
        return CST_MOVE;
    }
    public String getTrGlobalMovesStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(globalMovesStatus.get(_index));
    }
    public String clickGlobalMovesStatus(int _index) {
        getForms().put(CST_MOVE, globalMovesStatus.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesProtAgainstKo(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtAgainstKo.get(_index));
    }
    public String clickMovesProtAgainstKo(int _index) {
        getForms().put(CST_MOVE, movesProtAgainstKo.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesCannotKo(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesCannotKo.get(_index));
    }
    public String clickMovesCannotKo(int _index) {
        getForms().put(CST_MOVE, movesCannotKo.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesKoTarget(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesKoTarget.get(_index));
    }
    public String clickMovesKoTarget(int _index) {
        getForms().put(CST_MOVE, movesKoTarget.get(_index));
        return CST_MOVE;
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
        getForms().put(CST_MOVE, movesChangingAttOrder.get(_index));
        return CST_MOVE;
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
        return !eff_.getDamageLaw().events().isEmpty();
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
        getForms().put(CST_MOVE, damagingMoves.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesUserPower(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUserPower.get(_index));
    }
    public String clickMovesUserPower(int _index) {
        getForms().put(CST_MOVE, movesUserPower.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesTargetPower(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTargetPower.get(_index));
    }
    public String clickMovesTargetPower(int _index) {
        getForms().put(CST_MOVE, movesTargetPower.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesTargetTeamDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTargetTeamDamage.get(_index));
    }
    public String clickMovesTargetTeamDamage(int _index) {
        getForms().put(CST_MOVE, movesTargetTeamDamage.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesGlobal(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobal.get(_index));
    }
    public String clickMovesGlobal(int _index) {
        getForms().put(CST_MOVE, movesGlobal.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesInvokDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesInvokDamage.get(_index));
    }
    public String clickMovesInvokDamage(int _index) {
        getForms().put(CST_MOVE, movesInvokDamage.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesGlobalPrepaDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalPrepaDamage.get(_index));
    }
    public String clickMovesGlobalPrepaDamage(int _index) {
        getForms().put(CST_MOVE, movesGlobalPrepaDamage.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesUserAllyDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUserAllyDamage.get(_index));
    }
    public String clickMovesUserAllyaDamage(int _index) {
        getForms().put(CST_MOVE, movesUserAllyDamage.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesIgnLowAtt(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnLowAtt.get(_index));
    }
    public String clickMovesIgnLowAtt(int _index) {
        getForms().put(CST_MOVE, movesIgnLowAtt.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesIgnIncDef(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnIncDef.get(_index));
    }
    public String clickMovesIgnIncDef(int _index) {
        getForms().put(CST_MOVE, movesIgnIncDef.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesProtectingTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtectingTypes.get(_index));
    }
    public String clickMovesProtectingTypes(int _index) {
        getForms().put(CST_MOVE, movesProtectingTypes.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesUnprotectingTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesUnprotectingTypes.get(_index));
    }
    public String clickMovesUnprotectingTypes(int _index) {
        getForms().put(CST_MOVE, movesUnprotectingTypes.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesGlobalBreakImmu(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalBreakImmu.get(_index));
    }
    public String clickMovesGlobalBreakImmu(int _index) {
        getForms().put(CST_MOVE, movesGlobalBreakImmu.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesGlobalBreakImmuAb(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalBreakImmuAb.get(_index));
    }
    public String clickMovesGlobalBreakImmuAb(int _index) {
        getForms().put(CST_MOVE, movesGlobalBreakImmuAb.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesProtecting(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesProtecting.get(_index));
    }
    public String clickMovesProtecting(int _index) {
        getForms().put(CST_MOVE, movesProtecting.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesIgnAcc(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnAcc.get(_index));
    }
    public String clickMovesIgnAcc(int _index) {
        getForms().put(CST_MOVE, movesIgnAcc.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesIgnEva(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesIgnEva.get(_index));
    }
    public String clickMovesIgnEva(int _index) {
        getForms().put(CST_MOVE, movesIgnEva.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesGlobalAcc(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesGlobalAcc.get(_index));
    }
    public String clickMovesGlobalAcc(int _index) {
        getForms().put(CST_MOVE, movesGlobalAcc.get(_index));
        return CST_MOVE;
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
        getForms().put(CST_MOVE, movesGlobalMultStat.get(_index));
        return CST_MOVE;
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
        getForms().put(CST_MOVE, movesTeamMultStat.get(_index));
        return CST_MOVE;
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
        getForms().put(CST_MOVE, movesFoeTeamMultStat.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesTypesDefItem(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTypesDefItem.get(_index));
    }
    public String clickMovesTypesDefItem(int _index) {
        getForms().put(CST_MOVE, movesTypesDefItem.get(_index));
        return CST_MOVE;
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
        getForms().put(CST_MOVE, movesTypesDefWeather.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesTypeDefMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesTypeDefMoves.get(_index));
    }
    public String clickMovesTypeDefMoves(int _index) {
        getForms().put(CST_MOVE, movesTypeDefMoves.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesChangeTypeMoves(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesChangeTypeMoves.get(_index));
    }
    public String clickMovesChangeTypeMoves(int _index) {
        getForms().put(CST_MOVE, movesChangeTypeMoves.get(_index));
        return CST_MOVE;
    }
    public String getTrMovesBoostCh(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(movesBoostCh.get(_index));
    }
    public String clickMovesBoostCh(int _index) {
        getForms().put(CST_MOVE, movesBoostCh.get(_index));
        return CST_MOVE;
    }
    public String getTrDeletedStatusSwitch(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(deletedStatusSwitch.get(_index));
    }
    public String clickDeletedStatusSwitch(int _index) {
        getForms().put(CST_STATUS, deletedStatusSwitch.get(_index));
        return CST_STATUS;
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
        if (s_.getLawForUsingAMove().events().isEmpty()) {
            return !s_.getLawForUsingAMoveIfFoe().events().isEmpty();
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
        return !s_.getLawForFullHealIfMove().events().isEmpty();
    }
    public String getTrBeginRoundStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(beginRoundStatus.get(_index));
    }
    public String clickBeginRoundStatus(int _index) {
        getForms().put(CST_STATUS, beginRoundStatus.get(_index));
        return CST_STATUS;
    }
    public String getTrAutoDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(autoDamage.get(_index));
    }
    public String clickAutoDamage(int _index) {
        getForms().put(CST_STATUS, autoDamage.get(_index));
        return CST_STATUS;
    }
    public String getTrBeginRoundStatusFoe(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(beginRoundStatusFoe.get(_index));
    }
    public String clickBeginRoundStatusFoe(int _index) {
        getForms().put(CST_STATUS, beginRoundStatusFoe.get(_index));
        return CST_STATUS;
    }
    public String getTrSuccessfulStatus(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(successfulStatus.get(_index));
    }
    public String clickSuccessfulStatus(int _index) {
        getForms().put(CST_STATUS, successfulStatus.get(_index));
        return CST_STATUS;
    }
    public String getTrStatusDamage(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(statusDamage.get(_index));
    }
    public String clickStatusDamage(int _index) {
        getForms().put(CST_STATUS, statusDamage.get(_index));
        return CST_STATUS;
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
        getForms().put(CST_STATUS, statusMultStat.get(_index));
        return CST_STATUS;
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
        for (StringList s: data_.getCombos().getEffects().getKeys()) {
            StringList tmp_ = new StringList(s);
            tmp_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
            if (!StringUtil.eqStrings(tmp_, combo_)) {
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
        for (StringList s: data_.getCombos().getEffects().getKeys()) {
            StringList tmp_ = new StringList(s);
            tmp_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
            if (!StringUtil.eqStrings(tmp_, combo_)) {
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
        for (StringList s: data_.getCombos().getEffects().getKeys()) {
            StringList tmp_ = new StringList(s);
            tmp_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
            if (!StringUtil.eqStrings(tmp_, combo_)) {
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
        for (StringList s: data_.getCombos().getEffects().getKeys()) {
            StringList tmp_ = new StringList(s);
            tmp_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
            if (!StringUtil.eqStrings(tmp_, combo_)) {
                continue;
            }
            EffectTeam eff_ = data_.getCombos().getEffects().getVal(s).getTeamMove().first();
            return eff_.getMultStatisticFoe().contains(Statistic.ACCURACY);
        }
        return false;
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
        return CST_COMBO;
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
        return CST_COMBO;
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
    public String getTypes(int _index) {
        return types.get(_index);
    }
    public String getTrDefaultMove() {
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(defaultMove);
    }
    public String clickDefaultMove() {
        getForms().put(CST_MOVE, defaultMove);
        return CST_MOVE;
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