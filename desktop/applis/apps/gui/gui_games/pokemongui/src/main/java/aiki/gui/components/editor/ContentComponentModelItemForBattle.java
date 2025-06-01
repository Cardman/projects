package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.effects.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelItemForBattle {
    private CrudGeneFormSimpleFormSub<String, IdMap<Statistic,Long>> boostStatisTypes;
    private CrudGeneFormSimpleFormSub<StatisticPokemon,Long> multStatPokemonRank;
    private CrudGeneFormSimpleFormSub<Statistic, Long> boostStatisSuperEff;
    private CrudGeneFormSimpleFormSub<Statistic, Long> multStatRank;
    private CrudGeneFormSimpleFormSub<Statistic, Long> winEvFight;
    private CrudGeneFormSimpleFormSub<Statistic, String> multStat;
    private CrudGeneFormSimpleFormSub<String, String> failStatus;
    private CrudGeneFormSimpleFormSub<String,Long> increasingMaxNbRoundGlobalMove;
    private CrudGeneFormSimpleFormSub<String,Long> increasingMaxNbRoundTeamMove;
    private CrudGeneFormSimpleFormSub<String,Long> increasingMaxNbRoundTrap;
    private GeneComponentModelLsStrSub<String, StringList> hatching;
    private GeneComponentModelLsStrSub<String,StringList> immuMoves;
    private GeneComponentModelLsStrSub<String,StringList> immuStatus;
    private GeneComponentModelLsStrSub<String,StringList> immuTypes;
    private GeneComponentModelLsStrSub<String,StringList> immuWeather;
    private GeneComponentModelLsStrSub<String,StringList> synchroStatus;
    private GeneComponentModelLsStrSub<String,StringList> typesPk;
    private CrudGeneFormMonteCarlo<BoolVal> lawForAttackFirst;
    private GeneComponentModelSubscribeString multPower;
    private GeneComponentModelSubscribeString multDamage;
    private GeneComponentModelRate damageRecoil;
    private GeneComponentModelRate drainedHpByDamageRate;
    private GeneComponentModelRate multDrainedHp;
    private GeneComponentModelRate multTrappingDamage;
    private GeneComponentModelRate multWinningEv;
    private GeneComponentModelRate multWinningExp;
    private GeneComponentModelRate multWinningHappiness;
    private GeneComponentModelRate protectAgainstKo;
    private GeneComponentModelRate protectAgainstKoIfFullHp;
    private AbsCustCheckBox againstEvo;
    private AbsCustCheckBox attackLast;
    private AbsCustCheckBox attacksSoon;
    private AbsCustCheckBox boostExp;
    private AbsCustCheckBox cancelImmuType;
    private AbsCustCheckBox immuLowStatis;
    private CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic> effectSending;
    private CrudGeneFormSimpleElementSub<EffectEndRound> effectEndRound;
    private AbsPanel itemForBattleForm;
    AbsPanel form(GeneComponentModelItem _parent) {
        AbsCompoFactory compoFactory_ = _parent.getCompoFactory().getCompoFactory();
        itemForBattleForm = compoFactory_.newLineBox();
        boostStatisTypes = new CrudGeneFormSimpleFormSub<String, IdMap<Statistic,Long>>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(),_parent.getFrame());
        boostStatisTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String, IdMap<Statistic,Long>>(_parent.getSubscribedTranslationList().getFactoryTy(), _parent.getCompoFactory(),_parent.getFacade(),new StringMap<String>()),buildPart(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList().getFactoryTy(), new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<IdMap<Statistic,Long>>(new GeneComponentModelSubscribeStatisticByte(_parent.getSubscribedTranslationList(),_parent.getFrame(),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_BOOST_TYPES_STAT,MessagesDataItemsItemforbattle.M_P_28_BOOST_TYPES_BOOST)),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_BOOST_TYPES_TYPE,DataBase.EMPTY_STRING);
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_BOOST_TYPES,boostStatisTypes.getGroup()));
        multStatPokemonRank = new CrudGeneFormSimpleFormSub<StatisticPokemon, Long>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(),_parent.getFrame());
        multStatPokemonRank.initFormWithVal(new DisplayEntryCustSubElementStatisticPokemon<Long>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList()),new GeneComponentModelSubscribeFactoryDirect<StatisticPokemon>(new GeneComponentModelSubscribeStatisticPokemon(_parent.getSubscribedTranslationList(),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_POKEMON_RANK_STAT,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_POKEMON_RANK_NAME)),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_parent.getCompoFactory())),MessagesPkBean.IT_ITEMFORBATTLE,DataBase.EMPTY_STRING,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_POKEMON_RANK_VAR);
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_POKEMON_RANK,multStatPokemonRank.getGroup()));
        boostStatisSuperEff=new CrudGeneFormSimpleFormSub<Statistic,Long>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        boostStatisSuperEff.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_parent.getCompoFactory())),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_BOOST_SUPER_EFF_STAT,MessagesDataItemsItemforbattle.M_P_28_BOOST_SUPER_EFF_BOOST);
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_BOOST_SUPER_EFF,boostStatisSuperEff.getGroup()));
        multStatRank=new CrudGeneFormSimpleFormSub<Statistic,Long>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        multStatRank.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_parent.getCompoFactory())),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_RANK_KEY,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_RANK_BOOST);
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_RANK,multStatRank.getGroup()));
        winEvFight=new CrudGeneFormSimpleFormSub<Statistic,Long>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        winEvFight.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_parent.getCompoFactory())),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_WIN_EV_STAT,MessagesDataItemsItemforbattle.M_P_28_WIN_EV_VALUE);
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_WIN_EV,winEvFight.getGroup()));
        multStat=new CrudGeneFormSimpleFormSub<Statistic,String>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        multStat.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,String>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<String>(new GeneComponentModelSubscribeString(_parent.getCompoFactory(),_parent.getFacade())),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_KEY,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_RATE);
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT,multStat.getGroup()));
        failStatus=new CrudGeneFormSimpleFormSub<String,String>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        failStatus.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(_parent.getSubscribedTranslationList().getFactorySt(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()), buildPart(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList().getFactorySt(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryString(_parent.getCompoFactory(),_parent.getFacade()),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_FAIL_STATUS,MessagesDataItemsItemforbattle.M_P_28_FAIL_REASON);
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_FAIL,failStatus.getGroup()));
        increasingMaxNbRoundGlobalMove = new CrudGeneFormSimpleFormSub<String, Long>(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), _parent.getFrame());
        increasingMaxNbRoundGlobalMove.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Long>(_parent.getSubscribedTranslationList().getFactoryMv(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()),buildPart(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList().getFactoryMv(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_parent.getCompoFactory())),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_GLOBAL_MOVE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_GLOBAL_NB);
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_INCREASING_GLOBAL,increasingMaxNbRoundGlobalMove.getGroup()));
        increasingMaxNbRoundTeamMove = new CrudGeneFormSimpleFormSub<String, Long>(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), _parent.getFrame());
        increasingMaxNbRoundTeamMove.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Long>(_parent.getSubscribedTranslationList().getFactoryMv(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()),buildPart(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList().getFactoryMv(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_parent.getCompoFactory())),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TEAM_MOVE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TEAM_NB);
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TEAM,increasingMaxNbRoundTeamMove.getGroup()));
        increasingMaxNbRoundTrap = new CrudGeneFormSimpleFormSub<String, Long>(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), _parent.getFrame());
        increasingMaxNbRoundTrap.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Long>(_parent.getSubscribedTranslationList().getFactoryMv(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()),buildPart(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList().getFactoryMv(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_parent.getCompoFactory())),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TRAP_MOVE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TRAP_NB);
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TRAP,increasingMaxNbRoundTrap.getGroup()));
        hatching=ConverterCommonMapUtil.buildPkList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(hatching.geneEnum());
        immuMoves=ConverterCommonMapUtil.buildMoveList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_IMMU_MOVES,immuMoves.geneEnum()));
        immuStatus=ConverterCommonMapUtil.buildStatusList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_IMMU_STATUS,immuStatus.geneEnum()));
        immuTypes=ConverterCommonMapUtil.buildTypeList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_IMMU_TYPES,immuTypes.geneEnum()));
        immuWeather=ConverterCommonMapUtil.buildMoveList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_IMMU_WEATHERS,immuWeather.geneEnum()));
        synchroStatus=ConverterCommonMapUtil.buildStatusList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_SYNCHRO_STATUS,synchroStatus.geneEnum()));
        typesPk=ConverterCommonMapUtil.buildTypeList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_TYPES_PK_2,typesPk.geneEnum()));
        lawForAttackFirst = ConverterCommonMapUtil.buildMcBool(_parent.getFrame(),_parent.getCompoFactory(),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_ATTACK_FIRST_EVT,MessagesDataItemsItemforbattle.M_P_28_ATTACK_FIRST_RT);
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_ATTACK_FIRST,lawForAttackFirst.getGroup()));
        multPower = new GeneComponentModelSubscribeString(_parent.getCompoFactory(),_parent.getFacade());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_MULT_POWER_INTRO,multPower.geneEnum()));
        multPower.addComplete();
        multDamage = new GeneComponentModelSubscribeString(_parent.getCompoFactory(),_parent.getFacade());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_MULT_DAMAGE_INTRO,multDamage.geneEnum()));
        multDamage.addComplete();
        damageRecoil=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_DAMAGE_RECOIL_INTRO,damageRecoil.geneRate()));
        drainedHpByDamageRate=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_DRAINED_HP_INTRO,drainedHpByDamageRate.geneRate()));
        multDrainedHp=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_MULT_DRAINED_HP_INTRO,multDrainedHp.geneRate()));
        multTrappingDamage=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_MULT_TRAPPING_INTRO,multTrappingDamage.geneRate()));
        multWinningEv=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_MULT_WIN_EV_INTRO,multWinningEv.geneRate()));
        multWinningExp=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_MULT_WIN_EXP_INTRO,multWinningExp.geneRate()));
        multWinningHappiness=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_MULT_WIN_HAPPINESS_INTRO,multWinningHappiness.geneRate()));
        protectAgainstKo=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_PROTECT_KO_INTRO,protectAgainstKo.geneRate()));
        protectAgainstKoIfFullHp=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_PROTECT_KO_ALL_INTRO,protectAgainstKoIfFullHp.geneRate()));
        againstEvo=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_AGAINST_EVO,againstEvo));
        attackLast=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_ATTACK_LAST,attackLast));
        attacksSoon=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_ATTACK_SOON,attacksSoon));
        boostExp=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_BOOST_EXP,boostExp));
        cancelImmuType=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_CANCEL_IMMU_TYPE,cancelImmuType));
        immuLowStatis=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_IMMU_LOW_STATIS,immuLowStatis));
        effectSending = new CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(),_parent.getFrame());
        effectSending.initForm(new DisplayEntryCustSubElementEffect<EffectWhileSendingWithStatistic>(),new GeneComponentModelSubscribeFactoryDirect<EffectWhileSendingWithStatistic>(new GeneComponentModelSubscribeEffectWhileSending(new GeneComponentModelEffectWhileSending(_parent.getFrame(),_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), false))));
        itemForBattleForm.add(SubscribedTranslationList.line(_parent.getCompoFactory(),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_EFFECT,effectSending.getGroup()));
        effectEndRound = new CrudGeneFormSimpleElementSub<EffectEndRound>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(),_parent.getFrame());
        effectEndRound.initForm(new DisplayEntryCustSubElementEffect<EffectEndRound>(),new GeneComponentModelSubscribeFactoryDirect<EffectEndRound>(new GeneComponentModelSubscribeEffectEndRound(new GeneComponentModelEffectEndRound(_parent.getFrame(),_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), false))));
        itemForBattleForm.add(line(_parent,MessagesDataItemsItemforbattle.M_P_28_ENDROUND,effectEndRound.getGroup()));
        itemForBattleForm.setVisible(false);
        return itemForBattleForm;
    }
    private AbsCustComponent line(GeneComponentModelItem _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.IT_ITEMFORBATTLE, _key,_input);
    }
    void display(String _eff) {
        itemForBattleForm.setVisible(StringUtil.quickEq(_eff, Item.ITEM_FOR_BATTLE));
    }
    void buildEntity(ItemForBattle _item) {
        _item.setBoostStatisTypes(ConverterCommonMapUtil.buildStringMapIdMapStatisticByte(boostStatisTypes.getList()));
        _item.setMultStatPokemonRank(ConverterCommonMapUtil.buildStatisticPokemons(multStatPokemonRank.getList()));
        _item.setBoostStatisSuperEff(ConverterCommonMapUtil.buildIdMapStatisticInteger(boostStatisSuperEff.getList()));
        _item.setMultStatRank(ConverterCommonMapUtil.buildIdMapStatisticInteger(multStatRank.getList()));
        _item.setWinEvFight(ConverterCommonMapUtil.buildIdMapStatisticInteger(winEvFight.getList()));
        _item.setMultStat(ConverterCommonMapUtil.buildIdMapStatisticString(multStat.getList()));
        _item.setFailStatus(ConverterCommonMapUtil.buildStringMapString(failStatus.getList()));
        _item.setIncreasingMaxNbRoundGlobalMove(ConverterCommonMapUtil.buildStringMapInteger(increasingMaxNbRoundGlobalMove.getList()));
        _item.setIncreasingMaxNbRoundTeamMove(ConverterCommonMapUtil.buildStringMapInteger(increasingMaxNbRoundTeamMove.getList()));
        _item.setIncreasingMaxNbRoundTrap(ConverterCommonMapUtil.buildStringMapInteger(increasingMaxNbRoundTrap.getList()));
        _item.setHatching(hatching.tryRet());
        _item.setImmuMoves(immuMoves.tryRet());
        _item.setImmuStatus(immuStatus.tryRet());
        _item.setImmuTypes(immuTypes.tryRet());
        _item.setImmuWeather(immuWeather.tryRet());
        _item.setSynchroStatus(synchroStatus.tryRet());
        _item.setTypesPk(typesPk.tryRet());
        _item.setLawForAttackFirst(ConverterCommonMapUtil.buildMonteCarloBool(lawForAttackFirst.getList()));
        _item.setMultPower(multPower.tryRet());
        _item.setMultDamage(multDamage.tryRet());
        _item.setDamageRecoil(damageRecoil.valueRate());
        _item.setDrainedHpByDamageRate(drainedHpByDamageRate.valueRate());
        _item.setMultDrainedHp(multDrainedHp.valueRate());
        _item.setMultTrappingDamage(multTrappingDamage.valueRate());
        _item.setMultWinningEv(multWinningEv.valueRate());
        _item.setMultWinningExp(multWinningExp.valueRate());
        _item.setMultWinningHappiness(multWinningHappiness.valueRate());
        _item.setProtectAgainstKo(protectAgainstKo.valueRate());
        _item.setProtectAgainstKoIfFullHp(protectAgainstKoIfFullHp.valueRate());
        _item.setAgainstEvo(againstEvo.isSelected());
        _item.setAttackLast(attackLast.isSelected());
        _item.setAttacksSoon(attacksSoon.isSelected());
        _item.setBoostExp(boostExp.isSelected());
        _item.setCancelImmuType(cancelImmuType.isSelected());
        _item.setImmuLowStatis(immuLowStatis.isSelected());
        _item.setEffectSending(effectSending.getList());
        _item.setEffectEndRound(effectEndRound.getList());
    }
    void feedForm(ItemForBattle _item) {
        boostStatisTypes.setupValues(new MapToEntriesListUtil<String, IdMap<Statistic,Long>>().build(_item.getBoostStatisTypes()));
        multStatPokemonRank.setupValues(new MapToEntriesListUtil<StatisticPokemon,Long>().build(_item.getMultStatPokemonRank()));
        boostStatisSuperEff.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(_item.getBoostStatisSuperEff()));
        multStatRank.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(_item.getMultStatRank()));
        winEvFight.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(_item.getWinEvFight()));
        multStat.setupValues(new MapToEntriesListUtil<Statistic,String>().build(_item.getMultStat()));
        failStatus.setupValues(new MapToEntriesListUtil<String,String>().build(_item.getFailStatus()));
        increasingMaxNbRoundGlobalMove.setupValues(new MapToEntriesListUtil<String,Long>().build(_item.getIncreasingMaxNbRoundGlobalMove()));
        increasingMaxNbRoundTeamMove.setupValues(new MapToEntriesListUtil<String,Long>().build(_item.getIncreasingMaxNbRoundTeamMove()));
        increasingMaxNbRoundTrap.setupValues(new MapToEntriesListUtil<String,Long>().build(_item.getIncreasingMaxNbRoundTrap()));
        hatching.setupValue(_item.getHatching());
        immuMoves.setupValue(_item.getImmuMoves());
        immuStatus.setupValue(_item.getImmuStatus());
        immuTypes.setupValue(_item.getImmuTypes());
        immuWeather.setupValue(_item.getImmuWeather());
        synchroStatus.setupValue(_item.getSynchroStatus());
        typesPk.setupValue(_item.getTypesPk());
        lawForAttackFirst.setupValues(new MapToEntriesListUtil<BoolVal, LgInt>().build(_item.getLawForAttackFirst()));
        multPower.setupValue(_item.getMultPower());
        multDamage.setupValue(_item.getMultDamage());
        damageRecoil.valueRate(_item.getDamageRecoil());
        drainedHpByDamageRate.valueRate(_item.getDrainedHpByDamageRate());
        multDrainedHp.valueRate(_item.getMultDrainedHp());
        multTrappingDamage.valueRate(_item.getMultTrappingDamage());
        multWinningEv.valueRate(_item.getMultWinningEv());
        multWinningExp.valueRate(_item.getMultWinningExp());
        multWinningHappiness.valueRate(_item.getMultWinningHappiness());
        protectAgainstKo.valueRate(_item.getProtectAgainstKo());
        protectAgainstKoIfFullHp.valueRate(_item.getProtectAgainstKoIfFullHp());
        againstEvo.setSelected(_item.getAgainstEvo());
        attackLast.setSelected(_item.getAttackLast());
        attacksSoon.setSelected(_item.getAttacksSoon());
        boostExp.setSelected(_item.getBoostExp());
        cancelImmuType.setSelected(_item.getCancelImmuType());
        immuLowStatis.setSelected(_item.getImmuLowStatis());
        effectSending.setupValues(_item.getEffectSending());
        effectEndRound.setupValues(_item.getEffectEndRound());
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getBoostStatisTypes().subscribeButtons());
        ids_.addAllElts(getMultStatPokemonRank().subscribeButtons());
        ids_.addAllElts(boostStatisSuperEff.subscribeButtons());
        ids_.addAllElts(multStatRank.subscribeButtons());
        ids_.addAllElts(winEvFight.subscribeButtons());
        ids_.add(new SubscribedTranslationRenamingValCrud<Statistic>(multStat.getCrud()));
        ids_.addAllElts(multStat.subscribeButtons());
        ids_.add(new SubscribedTranslationRenamingValCrud<String>(failStatus.getCrud()));
        ids_.addAllElts(failStatus.subscribeButtons());
        ids_.addAllElts(increasingMaxNbRoundGlobalMove.subscribeButtons());
        ids_.addAllElts(increasingMaxNbRoundTeamMove.subscribeButtons());
        ids_.addAllElts(increasingMaxNbRoundTrap.subscribeButtons());
        ids_.addAllElts(hatching.getSubs());
        ids_.addAllElts(immuMoves.getSubs());
        ids_.addAllElts(immuStatus.getSubs());
        ids_.addAllElts(immuTypes.getSubs());
        ids_.addAllElts(immuWeather.getSubs());
        ids_.addAllElts(synchroStatus.getSubs());
        ids_.addAllElts(typesPk.getSubs());
        ids_.addAllElts(multDamage.getSubs());
        ids_.addAllElts(multPower.getSubs());
        return ids_;
    }

    public AbsCustCheckBox getAgainstEvo(){
        return againstEvo;
    }

    public AbsCustCheckBox getAttackLast(){
        return attackLast;
    }

    public AbsCustCheckBox getAttacksSoon(){
        return attacksSoon;
    }

    public AbsCustCheckBox getBoostExp(){
        return boostExp;
    }

    public AbsCustCheckBox getCancelImmuType(){
        return cancelImmuType;
    }

    public AbsCustCheckBox getImmuLowStatis(){
        return immuLowStatis;
    }

    public CrudGeneFormMonteCarlo<BoolVal> getLawForAttackFirst() {
        return lawForAttackFirst;
    }

    public CrudGeneFormSimpleFormSub<String, IdMap<Statistic,Long>> getBoostStatisTypes() {
        return boostStatisTypes;
    }

    public CrudGeneFormSimpleElementSub<EffectEndRound> getEffectEndRound() {
        return effectEndRound;
    }

    public CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic> getEffectSending() {
        return effectSending;
    }

    public CrudGeneFormSimpleFormSub<StatisticPokemon, Long> getMultStatPokemonRank() {
        return multStatPokemonRank;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
}
