package aiki.instances;

import aiki.db.DataBase;
import aiki.fight.Combos;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.Boost;
import aiki.fight.items.EvolvingItem;
import aiki.fight.items.EvolvingStone;
import aiki.fight.items.Fossil;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingSimpleItem;
import aiki.fight.items.HealingSimpleStatus;
import aiki.fight.items.ItemForBattle;
import aiki.fight.items.Repel;
import aiki.fight.items.SellingItem;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.ConstValuesType;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.effects.enums.MoveItemType;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionHappiness;
import aiki.fight.pokemon.evolution.EvolutionItem;
import aiki.fight.pokemon.evolution.EvolutionLevelGender;
import aiki.fight.pokemon.evolution.EvolutionLevelSimple;
import aiki.fight.pokemon.evolution.EvolutionMove;
import aiki.fight.pokemon.evolution.EvolutionMoveType;
import aiki.fight.pokemon.evolution.EvolutionStoneGender;
import aiki.fight.pokemon.evolution.EvolutionStoneSimple;
import aiki.fight.pokemon.evolution.EvolutionTeam;
import aiki.fight.status.StatusBeginRoundAutoDamage;
import aiki.fight.status.StatusBeginRoundSimple;
import aiki.fight.status.StatusSimple;
import aiki.fight.status.StatusType;
import aiki.fight.status.effects.EffectPartnerStatus;
import aiki.fight.util.*;
import aiki.game.Game;
import aiki.game.HostPokemonDuo;
import aiki.game.UsesOfMove;
import aiki.game.fight.*;
import aiki.game.fight.actions.ActionHealMove;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSimpleHeal;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.util.*;
import aiki.game.params.Difficulty;
import aiki.game.player.Inventory;
import aiki.game.player.Player;
import aiki.map.Condition;
import aiki.map.DataMap;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.Ally;
import aiki.map.characters.DealerItem;
import aiki.map.characters.DualFight;
import aiki.map.characters.GerantPokemon;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.Seller;
import aiki.map.characters.TempTrainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.characters.enums.GeranceType;
import aiki.map.characters.enums.SellType;
import aiki.map.enums.Direction;
import aiki.map.levels.*;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.PokemonTeam;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.*;
import aiki.util.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloEnum;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;
import code.util.Ints;

import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;

public final class Instances {
    private Instances() {
    }
    public static Combos newCombos() {
        Combos object_ = new Combos();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setEffects(new ListEffectCombos(cap_));
        return object_;
    }

    public static AbilityData newAbilityData() {
        AbilityData object_ = new AbilityData();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setBreakFoeImmune(new CustList<TypesDuo>(cap_));
        object_.setChgtTypeByWeather(new StringMap<String>(cap_));
        object_.setRecoilDamageFoe(Rate.zero());
        object_.setDivideStatusRound(new StringMap<Rate>(cap_));
        object_.setHealHpByWeather(new StringMap<Rate>(cap_));
        object_.setIgnAbility(new StringList(cap_));
        object_.setIgnFoeTeamMove(new StringList(cap_));
        object_.setImmuMove(new StringList(cap_));
        object_.setImmuLowStat(new IdList<Statistic>(cap_));
        object_.setImmuLowStatIfStatus(new CustList<StatisticStatus>(cap_));
        object_.setImmuWeather(new StringList(cap_));
        object_.setImmuAbility(new StringList(cap_));
        object_.setImmuStatusBeginRound(new StringList(cap_));
        object_.setImmuStatus(new StringMap<StringList>(cap_));
        object_.setMultDamageFoe(new StringMap<Rate>(cap_));
        object_.setMultDamageCh(Rate.zero());
        object_.setMultAllyDamage(Rate.zero());
        object_.setMultSufferedDamageSuperEff(Rate.zero());
        object_.setMultEvtRateCh(Rate.zero());
        object_.setMultEvtRateSecEffectOwner(Rate.zero());
        object_.setMultPower(DataBase.EMPTY_STRING);
        object_.setMultDamage(DataBase.EMPTY_STRING);
        object_.setMultStab(Rate.zero());
        object_.setBonusStatRank(new IdMap<Statistic,Long>(cap_));
        object_.setBoostStatRankProtected(new IdMap<Statistic,Long>(cap_));
        object_.setBoostStatRankEndRound(new IdMap<Statistic,Long>(cap_));
        object_.setMultStatAlly(new IdMap<Statistic,Rate>(cap_));
        object_.setMultStatIfKoFoe(new IdMap<Statistic,Long>(cap_));
        object_.setMultStatIfLowStat(new IdMap<Statistic,Long>(cap_));
        object_.setMultStatIfCat(new StatisticCategoryRate(cap_));
        object_.setMultStatIfStatutRank(new StatisticStatusList(cap_));
        object_.setMultStatIfDamageCat(new StatisticCategoryByte(cap_));
        object_.setMultStatIfDamgeType(new StatisticTypeByte(cap_));
        object_.setMultStat(new IdMap<Statistic,String>(cap_));
        object_.setMultVarBoost(Rate.zero());
        object_.setHealedHpRateBySwitch(Rate.zero());
        object_.setIncreasedPrio(new StringMap<Long>(cap_));
        object_.setIncreasedPrioTypes(new StringMap<Long>(cap_));
        object_.setMaxStatisticsIfCh(new IdList<Statistic>(cap_));
        object_.setSingleStatus(new MonteCarloString(cap_));
        object_.setForwardStatus(new StringMap<String>(cap_));
        object_.setFailStatus(new StringMap<String>(cap_));
        object_.setTypeForMoves(DataBase.EMPTY_STRING);
        object_.setMaxHpForUsingBerry(Rate.zero());
        object_.setHealHpByTypeIfWeather(new WeatherTypes(cap_));
        object_.setImmuMoveTypesByWeather(new StringMap<StringList>(cap_));
        object_.setEffectEndRound(new CustList<EffectEndRound>(cap_));
        object_.setEffectSending(new CustList<EffectWhileSendingWithStatistic>(cap_));
        object_.setChangingBoostTypes(new StringMap<TypeDamageBoost>(cap_));
        object_.setImmuAllyFromMoves(new StringList(cap_));
        object_.setImmuStatusTypes(new StringMap<StringList>(cap_));
        object_.setImmuLowStatisTypes(new StringMap<IdList<Statistic>>(cap_));
        object_.setLowStatFoeHit(new IdMap<Statistic,Long>(cap_));
        object_.setMultPowerMovesTypesGlobal(new StringMap<Rate>(cap_));
        object_.setHealHpWhileUsingBerry(Rate.zero());
        return object_;
    }

    public static EffectWhileSendingWithStatistic newEffectWhileSendingSimple() {
        EffectWhileSendingWithStatistic object_ = new EffectWhileSendingWithStatistic();
        object_.setEffect(newEffectStatistic());
        object_.setEnabledWeather(DataBase.EMPTY_STRING);
        object_.setMultWeight(Rate.zero());
        return object_;
    }

    public static EffectWhileSendingWithStatistic newEffectWhileSendingWithStatistic() {
        EffectWhileSendingWithStatistic object_ = new EffectWhileSendingWithStatistic();
        object_.setEffect(newEffectStatistic());
        object_.setWithEffect(true);
        object_.setEnabledWeather(DataBase.EMPTY_STRING);
        object_.setMultWeight(Rate.zero());
        return object_;
    }

    public static Ball newBall() {
        Ball object_ = new Ball();
        object_.setCatchingRate(DataBase.EMPTY_STRING);
        return object_;
    }

    public static Berry newBerry() {
        Berry object_ = new Berry();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setHealHpBySuperEffMove(Rate.zero());
        object_.setMultFoesDamage(new StringMap<EfficiencyRate>(cap_));
        object_.setMultStat(new IdMap<Statistic,BoostHpRate>(cap_));
        object_.setHealHp(Rate.zero());
        object_.setMaxHpHealingHp(Rate.zero());
        object_.setHealStatus(new StringList(cap_));
        object_.setHealHpRate(Rate.zero());
        object_.setMaxHpHealingHpRate(Rate.zero());
        object_.setDamageRateRecoilFoe(new StringMap<Rate>(cap_));
        object_.setCategoryBoosting(DataBase.EMPTY_STRING);
        object_.setBoostStatis(new IdMap<Statistic,Long>(cap_));
        return object_;
    }

    public static Boost newBoost() {
        Boost object_ = new Boost();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setWinPp(Rate.zero());
        object_.setHappiness(new StringMap<Long>(cap_));
        object_.setEvs(new IdMap<Statistic,Long>(cap_));
        return object_;
    }

    public static EvolvingItem newEvolvingItem() {
        return new EvolvingItem();
    }

    public static EvolvingStone newEvolvingStone() {
        return new EvolvingStone();
    }

    public static Fossil newFossil() {
        Fossil object_ = new Fossil();
        object_.setPokemon(DataBase.EMPTY_STRING);
        return object_;
    }

    public static HealingHp newHealingHp() {
        HealingHp object_ = new HealingHp();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setHp(Rate.zero());
        object_.setHappiness(new StringMap<Long>(cap_));
        return object_;
    }

    public static HealingHpStatus newHealingHpStatus() {
        HealingHpStatus object_ = new HealingHpStatus();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setHealedHpRate(Rate.zero());
        object_.setStatus(new StringList(cap_));
        object_.setHappiness(new StringMap<Long>(cap_));
        return object_;
    }

    public static HealingPp newHealingPp() {
        HealingPp object_ = new HealingPp();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setHappiness(new StringMap<Long>(cap_));
        return object_;
    }

    public static HealingSimpleItem newHealingSimpleItem() {
        HealingSimpleItem object_ = new HealingSimpleItem();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setHappiness(new StringMap<Long>(cap_));
        return object_;
    }

    public static HealingSimpleStatus newHealingSimpleStatus() {
        HealingSimpleStatus object_ = new HealingSimpleStatus();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setStatus(new StringList(cap_));
        object_.setHappiness(new StringMap<Long>(cap_));
        return object_;
    }

    public static ItemForBattle newItemForBattle() {
        ItemForBattle object_ = new ItemForBattle();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTypesPk(new StringList(cap_));
        object_.setImmuStatus(new StringList(cap_));
        object_.setIncreasingMaxNbRoundTrap(new StringMap<Long>(cap_));
        object_.setSynchroStatus(new StringList(cap_));
        object_.setFailStatus(new StringMap<String>(cap_));
        object_.setProtectAgainstKo(Rate.zero());
        object_.setProtectAgainstKoIfFullHp(Rate.zero());
        object_.setDrainedHpByDamageRate(Rate.zero());
        object_.setWinEvFight(new IdMap<Statistic,Long>(cap_));
        object_.setLawForAttackFirst(new MonteCarloBoolean(cap_));
        object_.setMultTrappingDamage(Rate.zero());
        object_.setMultWinningHappiness(Rate.zero());
        object_.setMultWinningEv(Rate.zero());
        object_.setMultWinningExp(Rate.zero());
        object_.setMultPower(DataBase.EMPTY_STRING);
        object_.setMultDamage(DataBase.EMPTY_STRING);
        object_.setMultDrainedHp(Rate.zero());
        object_.setDamageRecoil(Rate.zero());
        object_.setMultStatRank(new IdMap<Statistic,Long>(cap_));
        object_.setMultStatPokemonRank(new StatisticPokemons(cap_));
        object_.setMultStat(new IdMap<Statistic,String>(cap_));
        object_.setIncreasingMaxNbRoundGlobalMove(new StringMap<Long>(cap_));
        object_.setIncreasingMaxNbRoundTeamMove(new StringMap<Long>(cap_));
        object_.setImmuMoves(new StringList(cap_));
        object_.setHatching(new StringList(cap_));
        object_.setImmuTypes(new StringList(cap_));
        object_.setImmuWeather(new StringList(cap_));
        object_.setBoostStatisSuperEff(new IdMap<Statistic,Long>(cap_));
        object_.setBoostStatisTypes(new StringMap<IdMap<Statistic,Long>>(cap_));
        object_.setEffectEndRound(new CustList<EffectEndRound>(cap_));
        object_.setEffectSending(new CustList<EffectWhileSendingWithStatistic>(cap_));
        return object_;
    }

    public static Repel newRepel() {
        return new Repel();
    }

    public static SellingItem newSellingItem() {
        return new SellingItem();
    }

    public static DamagingMoveData newDamagingMoveData() {
        DamagingMoveData object_ = new DamagingMoveData();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setCategory(DataBase.EMPTY_STRING);
        feed(object_, cap_);
        return object_;
    }

    public static StatusMoveData newStatusMoveData() {
        StatusMoveData object_ = new StatusMoveData();
        CollCapacity cap_ = new CollCapacity(0);
        feed(object_, cap_);
        return object_;
    }

    private static void feed(MoveData _object, CollCapacity _cap) {
        _object.setTypes(new StringList(_cap));
        _object.setBoostedTypes(new StringList(_cap));
        _object.setAccuracy(DataBase.EMPTY_STRING);
        _object.setEffects(new CustList<Effect>(_cap));
        _object.setRepeatRoundLaw(new MonteCarloNumber(_cap));
        _object.setSecEffectsByItem(new StringMap<Ints>(_cap));
        _object.setAchieveDisappearedPkUsingMove(new StringList(_cap));
        _object.setSwitchType(SwitchType.NOTHING);
        _object.setTypesByOwnedItem(new StringMap<String>(_cap));
        _object.setTypesByWeather(new StringMap<String>(_cap));
        _object.setTargetChoice(TargetChoice.NOTHING);
        _object.setDeletedStatus(new StringList(_cap));
        _object.setRequiredStatus(new StringList(_cap));
    }

    public static EffectAccuracy newEffectAccuracy() {
        EffectAccuracy object_ = new EffectAccuracy();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectAlly newEffectAlly() {
        EffectAlly object_ = new EffectAlly();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setMultAllyDamage(Rate.zero());
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectBatonPass newEffectBatonPass() {
        EffectBatonPass object_ = new EffectBatonPass();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectClone newEffectClone() {
        EffectClone object_ = new EffectClone();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setHpRateClone(Rate.zero());
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectCombo newEffectCombo() {
        EffectCombo object_ = new EffectCombo();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setMultEvtRateSecEff(Rate.zero());
        object_.setRepeatedRoundsLaw(new MonteCarloNumber(cap_));
        object_.setEffectEndRound(new CustList<EffectEndRoundFoe>(cap_));
        object_.setTeamMove(new CustList<EffectTeam>(cap_));
        return object_;
    }

    public static EffectCommonStatistics newEffectCommonStatistics() {
        EffectCommonStatistics object_ = new EffectCommonStatistics();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setCommonValue(new IdMap<Statistic,String>(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectCopyFighter newEffectCopyFighter() {
        EffectCopyFighter object_ = new EffectCopyFighter();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectCopyMove newEffectCopyMove() {
        EffectCopyMove object_ = new EffectCopyMove();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setMovesNotToBeCopied(new StringList(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectCounterAttack newEffectCounterAttack() {
        EffectCounterAttack object_ = new EffectCounterAttack();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setSufferingDamageTypes(new StringMap<Rate>(cap_));
        object_.setDroppedStatDirectMove(new IdMap<Statistic,Long>(cap_));
        object_.setSufferingDamageDirectMove(Rate.zero());
        object_.setProtectFail(DataBase.EMPTY_STRING);
        object_.setCounterFail(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectDamage newEffectDamage() {
        EffectDamage object_ = new EffectDamage();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setDamageLaw(new MonteCarloString(cap_));
        object_.setMultDamageAgainst(new StringMap<Rate>(cap_));
        object_.setChLaw(new MonteCarloNumber(cap_));
        object_.setHitsLaw(new MonteCarloNumber(cap_));
        object_.setPower(DataBase.EMPTY_STRING);
        object_.setIgnVarStatTargetPos(new IdList<Statistic>(cap_));
        object_.setIgnVarStatUserNeg(new IdList<Statistic>(cap_));
        object_.setStatisAtt(Statistic.ATTACK);
        object_.setStatisDef(Statistic.DEFENSE);
        object_.setBoostStatisOnceKoFoe(new IdMap<Statistic,Long>(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectDamageRate newEffectDamageRate() {
        EffectDamageRate object_ = new EffectDamageRate();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setRateDamage(Rate.zero());
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectEndRoundFoe newEffectEndRoundFoe() {
        EffectEndRoundFoe object_ = new EffectEndRoundFoe();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setInflictedRateHpTarget(Rate.zero());
        object_.setFailEndRound(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectEndRoundGlobal newEffectEndRoundGlobal() {
        EffectEndRoundGlobal object_ = new EffectEndRoundGlobal();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setFailEndRound(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectEndRoundIndividual newEffectEndRoundIndividual() {
        EffectEndRoundIndividual object_ = new EffectEndRoundIndividual();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setDeleteAllStatus(Rate.zero());
        object_.setRecoilDamage(Rate.zero());
        object_.setHealHp(Rate.zero());
        object_.setHealHpByOwnerTypes(new StringMap<Rate>(cap_));
        object_.setMultDamageStatus(new StringMap<Rate>(cap_));
        object_.setUserStatusEndRound(DataBase.EMPTY_STRING);
        object_.setFailEndRound(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectEndRoundMultiRelation newEffectEndRoundMultiRelation() {
        EffectEndRoundMultiRelation object_ = new EffectEndRoundMultiRelation();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setDamageByStatus(new StringMap<Rate>(cap_));
        object_.setFailEndRound(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectEndRoundPositionRelation newEffectEndRoundPositionRelation() {
        EffectEndRoundPositionRelation object_ = new EffectEndRoundPositionRelation();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setHealHp(Rate.zero());
        object_.setFailEndRound(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectEndRoundPositionTargetRelation newEffectEndRoundPositionTargetRelation() {
        EffectEndRoundPositionTargetRelation object_ = new EffectEndRoundPositionTargetRelation();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setFailEndRound(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectEndRoundSingleRelation newEffectEndRoundSingleRelation() {
        EffectEndRoundSingleRelation object_ = new EffectEndRoundSingleRelation();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setRateDamageFunctionOfNbRounds(new LongMap<Rate>(cap_));
        object_.setLawForEnablingEffect(new MonteCarloNumber(cap_));
        object_.setFailEndRound(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectEndRoundSingleStatus newEffectEndRoundSingleStatus() {
        EffectEndRoundSingleStatus object_ = new EffectEndRoundSingleStatus();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setInflictedRateHpTarget(Rate.zero());
        object_.setFailEndRound(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectEndRoundStatusRelation newEffectEndRoundStatusRelation() {
        EffectEndRoundStatusRelation object_ = new EffectEndRoundStatusRelation();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setThievedHpRateTargetToUser(Rate.zero());
        object_.setInflictedRateHpTarget(Rate.zero());
        object_.setFailEndRound(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectEndRoundTeam newEffectEndRoundTeam() {
        EffectEndRoundTeam object_ = new EffectEndRoundTeam();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setDeleteAllStatus(Rate.zero());
        object_.setDeleteAllStatusAlly(Rate.zero());
        object_.setFailEndRound(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectFullHpRate newEffectFullHpRate() {
        EffectFullHpRate object_ = new EffectFullHpRate();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setLeftUserHp(Rate.zero());
        object_.setRestoredHp(DataBase.EMPTY_STRING);
        object_.setClosestFoeDamageRateHp(Rate.zero());
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectGlobal newEffectGlobal() {
        EffectGlobal object_ = new EffectGlobal();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setMultAccuracy(Rate.zero());
        object_.setPreventStatus(new StringList(cap_));
        object_.setImmuneTypes(new StringList(cap_));
        object_.setDamageEndRound(Rate.zero());
        object_.setHealingEndRound(Rate.zero());
        object_.setHealingEndRoundGround(Rate.zero());
        object_.setEfficiencyMoves(new TypesDuos(cap_));
        object_.setDisableImmuAgainstTypes(new StringList(cap_));
        object_.setCancelProtectingAbilities(new StringList(cap_));
        object_.setUnusableMoves(new StringList(cap_));
        object_.setMultDamagePrepaRound(new StringMap<Rate>(cap_));
        object_.setMovesUsedByTargetedFighters(new StringList(cap_));
        object_.setMultEffectLovingAlly(Rate.zero());
        object_.setMultPowerMoves(new StringMap<Rate>(cap_));
        object_.setMultStatIfContainsType(new StatisticTypeRate(cap_));
        object_.setCancelEffects(new StringList(cap_));
        object_.setMultDamageTypesMoves(new StringMap<Rate>(cap_));
        object_.setCancelChgtStat(new IdList<Statistic>(cap_));
        object_.setInvokedMoveTerrain(DataBase.EMPTY_STRING);
        object_.setChangedTypesTerrain(new StringList(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectInvoke newEffectInvoke() {
        EffectInvoke object_ = new EffectInvoke();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setMoveFctEnv(new IdMap<EnvironmentType,String>(cap_));
        object_.setInvokingMoveByUserTypes(new StringMap<String>(cap_));
        object_.setMovesNotToBeInvoked(new StringList(cap_));
        object_.setRateInvokationMove(Rate.zero());
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectMultSufferedMovePower newEffectMultSufferedMovePower() {
        EffectMultSufferedMovePower object_ = new EffectMultSufferedMovePower();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setMultMovePowerFctType(new StringMap<Rate>(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectMultUsedMovePower newEffectMultUsedMovePower() {
        EffectMultUsedMovePower object_ = new EffectMultUsedMovePower();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setMultMovePowerFctType(new StringMap<Rate>(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectOrder newEffectOrder() {
        EffectOrder object_ = new EffectOrder();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectProtectFromTypes newEffectProtectFromTypes() {
        EffectProtectFromTypes object_ = new EffectProtectFromTypes();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setImmuAgainstTypes(new StringList(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectProtection newEffectProtection() {
        EffectProtection object_ = new EffectProtection();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setProtSingleAgainstKo(Rate.zero());
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectRemainedHpRate newEffectRemainedHpRate() {
        EffectRemainedHpRate object_ = new EffectRemainedHpRate();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setRateHp(Rate.zero());
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectRestriction newEffectRestriction() {
        EffectRestriction object_ = new EffectRestriction();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setChoiceRestriction(MoveChoiceRestrictionType.NOTHING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectStatistic newEffectStatistic() {
        EffectStatistic object_ = new EffectStatistic();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setStatisVarRank(new IdMap<Statistic,Long>(cap_));
        object_.setLocalFailStatis(new IdMap<Statistic,String>(cap_));
        object_.setEvtRate(Rate.zero());
        object_.setCopyBoost(new IdList<Statistic>(cap_));
        object_.setSwapBoostStatis(new IdList<Statistic>(cap_));
        object_.setLocalFailSwapBoostStatis(new IdMap<Statistic,String>(cap_));
        object_.setLawBoost(new MonteCarloEnum<Statistic>(cap_));
        object_.setCancelLowStat(new IdList<Statistic>(cap_));
        object_.setCancelChgtStat(new IdList<Statistic>(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectStatus newEffectStatus() {
        EffectStatus object_ = new EffectStatus();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setLawStatus(new MonteCarloString(cap_));
        object_.setDeletedStatus(new StringList(cap_));
        object_.setLocalFailStatus(new StringMap<String>(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectSwitchAbilities newEffectSwitchAbilities() {
        EffectSwitchAbilities object_ = new EffectSwitchAbilities();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setExchangeAbility(ExchangeType.NOTHING);
        object_.setConstAbility(DataBase.EMPTY_STRING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectSwitchItems newEffectSwitchItems() {
        EffectSwitchItems object_ = new EffectSwitchItems();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setMoveObject(MoveItemType.EXCHANGE_OBJECTS);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectSwitchMoveTypes newEffectSwitchMoveTypes() {
        EffectSwitchMoveTypes object_ = new EffectSwitchMoveTypes();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setChangeTypes(new StringMap<String>(cap_));
        object_.setReplacingTypes(new StringList(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectSwitchPointView newEffectSwitchPointView() {
        EffectSwitchPointView object_ = new EffectSwitchPointView();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setPointViewChangement(PointViewChangementType.NOTHING);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectSwitchPosition newEffectSwitchPosition() {
        EffectSwitchPosition object_ = new EffectSwitchPosition();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectSwitchTypes newEffectSwitchTypes() {
        EffectSwitchTypes object_ = new EffectSwitchTypes();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setChgtTypeByEnv(new IdMap<EnvironmentType,String>(cap_));
        object_.setConstValuesType(ConstValuesType.NOTHING);
        object_.setExchangeTypes(ExchangeType.NOTHING);
        object_.setConstTypes(new StringList(cap_));
        object_.setAddedTypes(new StringList(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectTeam newEffectTeam() {
        EffectTeam object_ = new EffectTeam();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setForbiddenBoost(new IdList<Statistic>(cap_));
        object_.setUnusableMoves(new StringList(cap_));
        object_.setCancelChgtStatFoeTeam(new IdList<Statistic>(cap_));
        object_.setCancelChgtStatTeam(new IdList<Statistic>(cap_));
        object_.setMultDamage(new CategoryMults(cap_));
        object_.setMultStatistic(new IdMap<Statistic,Rate>(cap_));
        object_.setMultStatisticFoe(new IdMap<Statistic,Rate>(cap_));
        object_.setProtectAgainstLowStat(new IdList<Statistic>(cap_));
        object_.setProtectAgainstStatus(new StringList(cap_));
        object_.setDisableFoeTeamEffects(new StringList(cap_));
        object_.setDisableFoeTeamStatus(new StringList(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectTeamWhileSendFoe newEffectTeamWhileSendFoe() {
        EffectTeamWhileSendFoe object_ = new EffectTeamWhileSendFoe();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setFailSending(DataBase.EMPTY_STRING);
        object_.setStatusByNbUses(new LongMap<String>(cap_));
        object_.setDeletedByFoeTypes(new StringList(cap_));
        object_.setDamageRateAgainstFoe(DataBase.EMPTY_STRING);
        object_.setStatistics(new IdMap<Statistic,Long>(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectUnprotectFromTypes newEffectUnprotectFromTypes() {
        EffectUnprotectFromTypes object_ = new EffectUnprotectFromTypes();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTypes(new CustList<TypesDuo>(cap_));
        object_.setDisableImmuAgainstTypes(new StringList(cap_));
        object_.setDisableImmuFromMoves(new StringList(cap_));
        object_.setAttackTargetWithTypes(new StringList(cap_));
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectVarPP newEffectVarPP() {
        EffectVarPP object_ = new EffectVarPP();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static EffectWinMoney newEffectWinMoney() {
        EffectWinMoney object_ = new EffectWinMoney();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setWinningRateBySumTargetUser(Rate.zero());
        object_.setTargetChoice(TargetChoice.NOTHING);
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setRequiredSuccessfulEffects(new Ints(cap_));
        return object_;
    }

    public static PokemonData newPokemonData() {
        PokemonData object_ = new PokemonData();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setWeight(Rate.zero());
        object_.setTypes(new StringList(cap_));
        object_.setStatistics(new IdMap<Statistic,StatBaseEv>(cap_));
        object_.setLevMoves(new CustList<LevelMove>(cap_));
        object_.setGenderRep(GenderRepartition.NO_GENDER);
        object_.setAbilities(new StringList(cap_));
        object_.setMoveTutors(new StringList(cap_));
        object_.setHiddenMoves(new Ints(cap_));
        object_.setTechnicalMoves(new Ints(cap_));
        object_.setBaseEvo(DataBase.EMPTY_STRING);
        object_.setEvolutions(new StringMap<Evolution>(cap_));
        object_.setHeight(Rate.zero());
        object_.setExpEvo(ExpType.P);
        object_.setEggGroups(new StringList(cap_));
        object_.setHatchingSteps(LgInt.zero());
        return object_;
    }

    public static EvolutionHappiness newEvolutionHappiness() {
        return new EvolutionHappiness();
    }

    public static EvolutionItem newEvolutionItem() {
        EvolutionItem object_ = new EvolutionItem();
        object_.setItem(DataBase.EMPTY_STRING);
        return object_;
    }

    public static EvolutionLevelGender newEvolutionLevelGender() {
        EvolutionLevelGender object_ = new EvolutionLevelGender();
        object_.setGender(Gender.NO_GENDER);
        return object_;
    }

    public static EvolutionLevelSimple newEvolutionLevelSimple() {
        return new EvolutionLevelSimple();
    }

    public static EvolutionMove newEvolutionMove() {
        EvolutionMove object_ = new EvolutionMove();
        object_.setMove(DataBase.EMPTY_STRING);
        return object_;
    }

    public static EvolutionMoveType newEvolutionMoveType() {
        EvolutionMoveType object_ = new EvolutionMoveType();
        object_.setType(DataBase.EMPTY_STRING);
        return object_;
    }

    public static EvolutionStoneGender newEvolutionStoneGender() {
        EvolutionStoneGender object_ = new EvolutionStoneGender();
        object_.setGender(Gender.NO_GENDER);
        object_.setStone(DataBase.EMPTY_STRING);
        return object_;
    }

    public static EvolutionStoneSimple newEvolutionStoneSimple() {
        EvolutionStoneSimple object_ = new EvolutionStoneSimple();
        object_.setStone(DataBase.EMPTY_STRING);
        return object_;
    }

    public static EvolutionTeam newEvolutionTeam() {
        EvolutionTeam object_ = new EvolutionTeam();
        object_.setPokemon(DataBase.EMPTY_STRING);
        return object_;
    }

    public static StatusBeginRoundAutoDamage newStatusBeginRoundAutoDamage() {
        StatusBeginRoundAutoDamage object_ = new StatusBeginRoundAutoDamage();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setPower(Rate.zero());
        object_.setAttack(Statistic.ATTACK);
        object_.setDefense(Statistic.DEFENSE);
        object_.setStatusType(StatusType.INDIVIDUEL);
        object_.setLawForUsingAMove(new MonteCarloBoolean(cap_));
        object_.setLawForUsingAMoveNbRound(new MonteCarloNumber(cap_));
        object_.setLawForUsingAMoveIfFoe(new MonteCarloBoolean(cap_));
        object_.setLawForFullHealIfMove(new MonteCarloBoolean(cap_));
        object_.setCatchingRate(Rate.zero());
        object_.setEffectEndRound(new CustList<EffectEndRoundStatus>(cap_));
        object_.setEffectsPartner(new CustList<EffectPartnerStatus>(cap_));
        object_.setMultStat(new IdMap<Statistic,Rate>(cap_));
        object_.setFail(DataBase.EMPTY_STRING);
        return object_;
    }

    public static StatusBeginRoundSimple newStatusBeginRoundSimple() {
        StatusBeginRoundSimple object_ = new StatusBeginRoundSimple();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setLawForUsingAMove(new MonteCarloBoolean(cap_));
        object_.setLawForUsingAMoveNbRound(new MonteCarloNumber(cap_));
        object_.setLawForUsingAMoveIfFoe(new MonteCarloBoolean(cap_));
        object_.setLawForFullHealIfMove(new MonteCarloBoolean(cap_));
        object_.setStatusType(StatusType.INDIVIDUEL);
        object_.setCatchingRate(Rate.zero());
        object_.setEffectEndRound(new CustList<EffectEndRoundStatus>(cap_));
        object_.setEffectsPartner(new CustList<EffectPartnerStatus>(cap_));
        object_.setMultStat(new IdMap<Statistic,Rate>(cap_));
        object_.setFail(DataBase.EMPTY_STRING);
        return object_;
    }

    public static StatusSimple newStatusSimple() {
        StatusSimple object_ = new StatusSimple();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setCatchingRate(Rate.zero());
        object_.setEffectEndRound(new CustList<EffectEndRoundStatus>(cap_));
        object_.setEffectsPartner(new CustList<EffectPartnerStatus>(cap_));
        object_.setMultStat(new IdMap<Statistic,Rate>(cap_));
        object_.setFail(DataBase.EMPTY_STRING);
        object_.setStatusType(StatusType.INDIVIDUEL);
        return object_;
    }

    public static EffectPartnerStatus newEffectPartnerStatus() {
        EffectPartnerStatus object_ = new EffectPartnerStatus();
        object_.setMultDamageAgainstFoe(Rate.zero());
        object_.setRestoredHpRateLovedAlly(Rate.zero());
        return object_;
    }

    public static Game newGame() {
        Game object_ = new Game();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setZippedRom(DataBase.EMPTY_STRING);
        object_.setPlayer(newPlayer());
        object_.setBeatGymTrainer(new IntMap<PointEqList>(cap_));
        object_.setBeatGymLeader(new CoordssBoolVal(cap_));
        object_.setBeatTrainer(new NbFightCoordss(cap_));
        object_.setTakenObjects(new CoordssBoolVal(cap_));
        object_.setTakenPokemon(new CoordssBoolVal(cap_));
        object_.setPlayerCoords(new Coords());
        object_.setPlayerOrientation(Direction.UP);
        object_.setHostedPk(new CoordssHostPokemonDuo(cap_));
        object_.setFight(newFight());
        object_.setDifficulty(newDifficulty());
        object_.setVisitedPlacesNb(new IntMap<BoolVal>());
        object_.setVisitedPlaces(new CoordssBoolVal(cap_));
        return object_;
    }

    public static HostPokemonDuo newHostPokemonDuo() {
        HostPokemonDuo object_ = new HostPokemonDuo();
        object_.setFirstPokemon(newPokemonPlayer());
        object_.setSecondPokemon(newPokemonPlayer());
        return object_;
    }

    public static ChoiceOfEvolutionAndMoves newChoiceOfEvolutionAndMoves() {
        ChoiceOfEvolutionAndMoves object_ = new ChoiceOfEvolutionAndMoves();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setName(DataBase.EMPTY_STRING);
        object_.setKeptMoves(new StringList(cap_));
        object_.setAbility(DataBase.EMPTY_STRING);
        return object_;
    }

    public static Fight newFight() {
        Fight object_ = new Fight();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setFightType(FightType.NOTHING);
        object_.setEnvType(EnvironmentType.ROAD);
        object_.setEnabledMoves(new StringMap<ActivityOfMove>(cap_));
        object_.setStillEnabledMoves(new StringMap<BoolVal>(cap_));
        object_.setTeams(new IntMap<Team>(cap_));
        object_.setNbRounds(LgInt.zero());
        object_.setWinningMoney(Rate.zero());
        object_.setCatchingBalls(new CustList<CatchingBallFoeAction>());
        object_.setCurrentUser(new TeamPosition());
        object_.setState(FightState.RIEN);
        object_.setUsedItemsWhileRound(new StringMap<Long>(cap_));
        object_.setFirstPositPlayerFighters(new IntMap<Integer>(cap_));
        object_.setFirstPositFoeFighters(new IntMap<Integer>(cap_));
        object_.setAllyChoice(new MoveTargets(cap_));
        object_.setLostObjects(new StringList(cap_));
        object_.setChoices(new IntMap<ChoiceOfEvolutionAndMoves>(cap_));
        object_.setCaughtEvolutions(new StringList(cap_));
        return object_;
    }

    public static CatchingBallFoeAction newCatchingBallFoeAction() {
        CatchingBallFoeAction c_ = new CatchingBallFoeAction();
        c_.setCatchingBall(DataBase.EMPTY_STRING);
        c_.setNickname(DataBase.EMPTY_STRING);
        return c_;
    }
    public static Fighter newFighter() {
        Fighter object_ = new Fighter();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setName(DataBase.EMPTY_STRING);
        object_.setNickname(DataBase.EMPTY_STRING);
        object_.setGender(Gender.NO_GENDER);
        object_.setWeight(Rate.zero());
        object_.setHeight(Rate.zero());
        object_.setCurrentName(DataBase.EMPTY_STRING);
        object_.setCurrentGender(Gender.NO_GENDER);
        object_.setLastUsedItem(DataBase.EMPTY_STRING);
        object_.setItem(DataBase.EMPTY_STRING);
        object_.setExpItem(DataBase.EMPTY_STRING);
        object_.setAbility(DataBase.EMPTY_STRING);
        object_.setCurrentAbility(DataBase.EMPTY_STRING);
        object_.setStatus(new StringMap<Long>(cap_));
        object_.setStatusRelat(new MoveTeamPositionsShort(cap_));
        object_.setNbRounds(LgInt.zero());
        object_.setTypes(new StringList(cap_));
        object_.setMoves(new StringMap<UsesOfMove>(cap_));
        object_.setCurrentMoves(new StringMap<UsesOfMove>(cap_));
        object_.setEv(new IdMap<Statistic,Long>(cap_));
        object_.setStatisBase(new IdMap<Statistic,Rate>(cap_));
        object_.setStatisBoost(new IdMap<Statistic,Long>(cap_));
        object_.setRemainingHp(Rate.zero());
        object_.setClone(Rate.zero());
        object_.setEnabledMoves(new StringMap<ActivityOfMove>(cap_));
        object_.setEnabledMovesProt(new StringMap<ActivityOfMove>(cap_));
        object_.setProtectedAgainstMoveTypes(new StringList(cap_));
        object_.setEnabledMovesUnprot(new StringMap<ActivityOfMove>(cap_));
        object_.setEnabledMovesEndRound(new StringMap<ActivityOfMove>(cap_));
        object_.setEnabledMovesConstChoices(new StringMap<ActivityOfMove>(cap_));
        object_.setEnabledChangingTypesMoves(new StringMap<ActivityOfMove>(cap_));
        object_.setEnabledCounteringMoves(new StringMap<ActivityOfMove>(cap_));
        object_.setEnabledMovesForAlly(new StringMap<BoolVal>(cap_));
        object_.setDamageRateInflictedByType(new StringMap<Rate>(cap_));
        object_.setDamageRateSufferedByType(new StringMap<Rate>(cap_));
        object_.setWonExp(Rate.zero());
        object_.setWonExpSinceLastLevel(Rate.zero());
        object_.setUsedBallCatching(DataBase.EMPTY_STRING);
        object_.setIncrUserAccuracy(new MoveTeamPositionsBoolVal(cap_));
        object_.setNbUsesMoves(new StringMap<Long>(cap_));
        object_.setTrackingMoves(new MoveTeamPositionsAffectedMove(cap_));
        object_.setTrappingMoves(new MoveTeamPositionsActivityOfMove(cap_));
        object_.setLastSufferedMove(DataBase.EMPTY_STRING);
        object_.setLastSufferedMoveTypes(new StringList(cap_));
        object_.setDamageSufferedCateg(new StringMap<Rate>(cap_));
        object_.setDamageSufferedCategRound(new StringMap<Rate>(cap_));
        object_.setLastUsedMove(DataBase.EMPTY_STRING);
        object_.setUsedMoveLastRound(DataBase.EMPTY_STRING);
        object_.setAlreadyInvokedMovesRound(new StringList(cap_));
        object_.setLastSuccessfulMove(DataBase.EMPTY_STRING);
        object_.setCopiedMoves(new StringMap<CopiedMove>(cap_));
        object_.setNbRepeatingSuccessfulMoves(LgInt.zero());
        object_.setPrivateMoves(new MoveTeamPositionsStringList(cap_));
        object_.setAction(null);
        object_.setMovesToBeLearnt(new StringList(cap_));
        object_.setMovesAbilitiesEvos(new StringMap<MovesAbilities>(cap_));
        return object_;
    }

    public static Team newTeam() {
        Team object_ = new Team();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setEnabledMovesByGroup(new ListActivityOfMoves(cap_));
        object_.setEnabledMoves(new StringMap<ActivityOfMove>(cap_));
        object_.setEnabledMovesWhileSendingFoe(new StringMap<BoolVal>(cap_));
        object_.setEnabledMovesWhileSendingFoeUses(new StringMap<LgInt>(cap_));
        object_.setNbUsesMoves(new StringMap<Long>(cap_));
        object_.setNbUsesMovesRound(new StringMap<Long>(cap_));
        object_.setHealAfter(new StringMap<IntMap<StacksOfUses>>(cap_));
        object_.setMovesAnticipation(new StringMap<IntMap<Anticipation>>(cap_));
        object_.setMembers(new IntMap<Fighter>(cap_));
        object_.setPlayerFightersAgainstFoe(new IntMap<CustList<Integer>>(cap_));
        object_.setSuccessfulMovesRound(new StringList(cap_));
        return object_;
    }

    public static ActionHealMove newActionHealMove() {
        ActionHealMove object_ = new ActionHealMove();
        object_.setFirstChosenMove(DataBase.EMPTY_STRING);
        object_.setChosenHealingItem(DataBase.EMPTY_STRING);
        return object_;
    }

    public static ActionMove newActionMove() {
        ActionMove object_ = new ActionMove();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setFirstChosenMove(DataBase.EMPTY_STRING);
        object_.setFinalChosenMove(DataBase.EMPTY_STRING);
        object_.setChosenTargets(new TargetCoordsList(cap_));
        return object_;
    }

    public static ActionSimpleHeal newActionSimpleHeal() {
        ActionSimpleHeal object_ = new ActionSimpleHeal();
        object_.setChosenHealingItem(DataBase.EMPTY_STRING);
        return object_;
    }

    public static ActionSwitch newActionSwitch() {
        return new ActionSwitch();
    }

    public static MovesAbilities newMovesAbilities() {
        MovesAbilities object_ = new MovesAbilities();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setMoves(new StringList(cap_));
        object_.setAbilities(new StringList(cap_));
        return object_;
    }

    public static Difficulty newDifficulty() {
        return new Difficulty();
    }

    public static Inventory newInventory() {
        Inventory object_ = new Inventory();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setItems(new StringMap<LgInt>(cap_));
        object_.setTm(new IntMap<BoolVal>(cap_));
        object_.setHm(new IntMap<BoolVal>(cap_));
        return object_;
    }

    public static Player newPlayer() {
        Player object_ = new Player();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setNickname(DataBase.EMPTY_STRING);
        object_.setTeam(new CustList<UsablePokemon>(cap_));
        object_.setBox(new CustList<UsablePokemon>(cap_));
        object_.setInventory(newInventory());
        object_.setCaughtPk(new StringMap<BoolVal>(cap_));
        object_.setMoney(LgInt.zero());
        return object_;
    }

    public static Condition newCondition() {
        return new Condition();
    }

    public static DataMap newDataMap() {
        DataMap object_ = new DataMap();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setPlaces(new CustList<Place>(cap_));
        object_.setAccessCondition(new CoordsLists(cap_));
        object_.setMiniMap(new MiniMapCoordsList(cap_));
        object_.setUnlockedCity(DataBase.EMPTY_STRING);
        object_.setBegin(new Coords());
        object_.setFirstPokemon(newWildPk());
        return object_;
    }

    public static Gym newGym() {
        Gym object_ = new Gym();
        object_.setLevel(newLevelIndoorGym());
        object_.setImageFileName(DataBase.EMPTY_STRING);
        object_.setExitCity(new NullablePoint());
        return object_;
    }

    public static PokemonCenter newPokemonCenter() {
        PokemonCenter object_ = new PokemonCenter();
        object_.setLevel(newLevelIndoorPokemonCenter());
        object_.setImageFileName(DataBase.EMPTY_STRING);
        object_.setExitCity(new NullablePoint());
        return object_;
    }

    public static Ally newAlly() {
        Ally object_ = new Ally();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTeam(new CustList<PkTrainer>(cap_));
        return object_;
    }

    public static DealerItem newDealerItem() {
        DealerItem object_ = new DealerItem();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setItems(new StringList(cap_));
        object_.setTechnicalMoves(new Ints(cap_));
        object_.setImageMiniFileName(DataBase.EMPTY_STRING);
        return object_;
    }

    public static DualFight newDualFight() {
        DualFight object_ = new DualFight();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setAlly(newAlly());
        object_.setFoeTrainer(newTempTrainer());
        object_.setNames(new StringList(cap_));
        object_.setPt(new NullablePoint());
        return object_;
    }

    public static GerantPokemon newGerantPokemon() {
        GerantPokemon object_ = new GerantPokemon();
        object_.setGerance(GeranceType.HEAL);
        object_.setImageMiniFileName(DataBase.EMPTY_STRING);
        return object_;
    }

    public static GymLeader newGymLeader() {
        GymLeader object_ = new GymLeader();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setName(DataBase.EMPTY_STRING);
        object_.setTeam(new CustList<PkTrainer>(cap_));
        object_.setImageMaxiFileName(DataBase.EMPTY_STRING);
        object_.setImageMiniFileName(DataBase.EMPTY_STRING);
        return object_;
    }

    public static GymTrainer newGymTrainer() {
        GymTrainer object_ = new GymTrainer();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTeam(new CustList<PkTrainer>(cap_));
        object_.setImageMaxiFileName(DataBase.EMPTY_STRING);
        object_.setImageMiniFileName(DataBase.EMPTY_STRING);
        return object_;
    }

    public static Seller newSeller() {
        Seller object_ = new Seller();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setSell(SellType.ITEM);
        object_.setItems(new StringList(cap_));
        object_.setTm(new Ints(cap_));
        object_.setImageMiniFileName(DataBase.EMPTY_STRING);
        return object_;
    }

    public static TempTrainer newTempTrainer() {
        TempTrainer object_ = new TempTrainer();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setImageMiniSecondTrainerFileName(DataBase.EMPTY_STRING);
        object_.setTeam(new CustList<PkTrainer>(cap_));
        object_.setImageMaxiFileName(DataBase.EMPTY_STRING);
        object_.setImageMiniFileName(DataBase.EMPTY_STRING);
        return object_;
    }

    public static TrainerLeague newTrainerLeague() {
        TrainerLeague object_ = new TrainerLeague();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setName(DataBase.EMPTY_STRING);
        object_.setTeam(new CustList<PkTrainer>(cap_));
        object_.setImageMaxiFileName(DataBase.EMPTY_STRING);
        object_.setImageMiniFileName(DataBase.EMPTY_STRING);
        return object_;
    }

    public static TrainerMultiFights newTrainerMultiFights() {
        TrainerMultiFights object_ = new TrainerMultiFights();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTeamsRewards(new CustList<PokemonTeam>(cap_));
        object_.setImageMaxiFileName(DataBase.EMPTY_STRING);
        object_.setImageMiniFileName(DataBase.EMPTY_STRING);
        return object_;
    }

    public static AreaApparition newAreaApparition() {
        AreaApparition object_ = new AreaApparition();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setWildPokemon(new CustList<WildPk>(cap_));
        object_.setWildPokemonFishing(new CustList<WildPk>(cap_));
        return object_;
    }

    public static Block newBlock() {
        Block object_ = new Block();
        object_.setTileFileName(DataBase.EMPTY_STRING);
        object_.setType(EnvironmentType.ROAD);
        return object_;
    }

    public static LevelCave newLevelCave() {
        LevelCave object_ = new LevelCave();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setLinksOtherLevels(new PointsLink(cap_));
        object_.setWildPokemonAreas(new CustList<AbsAreaApparition>(cap_));
        object_.setCharacters(new PointsCharacterInRoadCave(cap_));
        object_.setDualFights(new PointsDualFight(cap_));
        object_.setLegendaryPks(new PointsWildPk(cap_));
        object_.setItems(new PointsString(cap_));
        object_.setTm(new PointsShort(cap_));
        object_.setHm(new PointsShort(cap_));
        object_.setBlocks(new PointsBlock(cap_));
        return object_;
    }

    public static LevelIndoorGym newLevelIndoorGym() {
        LevelIndoorGym object_ = new LevelIndoorGym();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setGymTrainers(new PointsGymTrainer(cap_));
        object_.setGymLeaderCoords(new NullablePoint());
        object_.setGymLeader(newGymLeader());
        object_.setBlocks(new PointsBlock(cap_));
        return object_;
    }

    public static LevelIndoorPokemonCenter newLevelIndoorPokemonCenter() {
        LevelIndoorPokemonCenter object_ = new LevelIndoorPokemonCenter();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setGerants(new PointsPerson(cap_));
        object_.setStorageCoords(new NullablePoint());
        object_.setBlocks(new PointsBlock(cap_));
        return object_;
    }

    public static LevelLeague newLevelLeague() {
        LevelLeague object_ = new LevelLeague();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTrainerCoords(new NullablePoint());
        object_.setTrainer(newTrainerLeague());
        object_.setAccessPoint(new NullablePoint());
        object_.setNextLevelTarget(new NullablePoint());
        object_.setFileName(DataBase.EMPTY_STRING);
        object_.setBlocks(new PointsBlock(cap_));
        return object_;
    }

    public static LevelOutdoor newLevelOutdoor() {
        LevelOutdoor object_ = new LevelOutdoor();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setBlocks(new PointsBlock(cap_));
        return object_;
    }

    public static LevelRoad newLevelRoad() {
        LevelRoad object_ = new LevelRoad();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setWildPokemonAreas(new CustList<AbsAreaApparition>(cap_));
        object_.setCharacters(new PointsCharacterInRoadCave(cap_));
        object_.setDualFights(new PointsDualFight(cap_));
        object_.setLegendaryPks(new PointsWildPk(cap_));
        object_.setItems(new PointsString(cap_));
        object_.setTm(new PointsShort(cap_));
        object_.setHm(new PointsShort(cap_));
        object_.setBlocks(new PointsBlock(cap_));
        return object_;
    }

    public static Cave newCave() {
        Cave object_ = new Cave();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setName(DataBase.EMPTY_STRING);
        object_.setLevels(new CustList<LevelCave>(cap_));
        object_.setLinksWithOtherPlaces(new LevelPoints(cap_));
        return object_;
    }

    public static City newCity() {
        City object_ = new City();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setBuildings(new PointsBuilding(cap_));
        object_.setLevel(newLevelOutdoor());
        object_.setName(DataBase.EMPTY_STRING);
        object_.setSavedlinks(new PlaceInterConnects(cap_));
        object_.setLinksWithCaves(new PointsLink(cap_));
        return object_;
    }

    public static League newLeague() {
        League object_ = new League();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setName(DataBase.EMPTY_STRING);
        object_.setRooms(new CustList<LevelLeague>(cap_));
        object_.setAccessCoords(new Coords());
        object_.setFileName(DataBase.EMPTY_STRING);
        object_.setBegin(new NullablePoint());
        return object_;
    }

    public static Road newRoad() {
        Road object_ = new Road();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setName(DataBase.EMPTY_STRING);
        object_.setLevel(newLevelRoad());
        object_.setLinksWithCaves(new PointsLink(cap_));
        object_.setSavedlinks(new PlaceInterConnects(cap_));
        return object_;
    }

    public static PkTrainer newPkTrainer() {
        PkTrainer object_ = new PkTrainer();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setName(DataBase.EMPTY_STRING);
        object_.setGender(Gender.NO_GENDER);
        object_.setAbility(DataBase.EMPTY_STRING);
        object_.setItem(DataBase.EMPTY_STRING);
        object_.setMoves(new StringList(cap_));
        return object_;
    }

    public static PokemonPlayer newPokemonPlayer() {
        PokemonPlayer object_ = new PokemonPlayer();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setName(DataBase.EMPTY_STRING);
        object_.setGender(Gender.NO_GENDER);
        object_.setAbility(DataBase.EMPTY_STRING);
        object_.setItem(DataBase.EMPTY_STRING);
        object_.setRemainingHp(Rate.zero());
        object_.setStatus(new StringList(cap_));
        object_.setNickname(DataBase.EMPTY_STRING);
        object_.setMoves(new StringMap<UsesOfMove>(cap_));
        object_.setEv(new IdMap<Statistic,Long>(cap_));
        object_.setWonExpSinceLastLevel(Rate.zero());
        object_.setUsedBallCatching(DataBase.EMPTY_STRING);
        return object_;
    }

    public static PokemonTeam newPokemonTeam() {
        PokemonTeam object_ = new PokemonTeam();
        CollCapacity cap_ = new CollCapacity(0);
        object_.setTeam(new CustList<PkTrainer>(cap_));
        return object_;
    }

    public static WildPk newWildPk() {
        WildPk object_ = new WildPk();
        object_.setName(DataBase.EMPTY_STRING);
        object_.setGender(Gender.NO_GENDER);
        object_.setAbility(DataBase.EMPTY_STRING);
        object_.setItem(DataBase.EMPTY_STRING);
        return object_;
    }

    public static TileMiniMap newTileMiniMap() {
        TileMiniMap object_ = new TileMiniMap();
        object_.setFile(DataBase.EMPTY_STRING);
        return object_;
    }

}
