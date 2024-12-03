package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.effects.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelItemForBattle {
    private CrudGeneFormSimpleFormSub<String, IdMap<Statistic, Byte>> boostStatisTypes;
    private CrudGeneFormSimpleFormSub<StatisticPokemon,Byte> multStatPokemonRank;
    private CrudGeneFormSimpleFormSub<Statistic, Byte> boostStatisSuperEff;
    private CrudGeneFormSimpleFormSub<Statistic, Byte> multStatRank;
    private CrudGeneFormSimpleFormSub<Statistic, Short> winEvFight;
    private CrudGeneFormSimpleFormSub<Statistic, String> multStat;
    private CrudGeneFormSimpleFormSub<String, String> failStatus;
    private CrudGeneFormSimpleFormSub<String,Short> increasingMaxNbRoundGlobalMove;
    private CrudGeneFormSimpleFormSub<String,Short> increasingMaxNbRoundTeamMove;
    private CrudGeneFormSimpleFormSub<String,Short> increasingMaxNbRoundTrap;
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
        boostStatisTypes = new CrudGeneFormSimpleFormSub<String, IdMap<Statistic, Byte>>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(),_parent.getFrame());
        boostStatisTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String, IdMap<Statistic, Byte>>(_parent.getSubscribedTranslationList().getFactoryTy(), _parent.getCompoFactory(),_parent.getFacade(),new StringMap<String>()),buildPart(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList().getFactoryTy(), new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<IdMap<Statistic, Byte>>(new GeneComponentModelSubscribeStatisticByte(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(),_parent.getFrame())));
        itemForBattleForm.add(boostStatisTypes.getGroup());
        multStatPokemonRank = new CrudGeneFormSimpleFormSub<StatisticPokemon, Byte>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(),_parent.getFrame());
        multStatPokemonRank.initFormWithVal(new DisplayEntryCustSubElementStatisticPokemon<Byte>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList()),new GeneComponentModelSubscribeFactoryDirect<StatisticPokemon>(new GeneComponentModelSubscribeStatisticPokemon(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList())),new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(_parent.getCompoFactory())));
        itemForBattleForm.add(multStatPokemonRank.getGroup());
        boostStatisSuperEff=new CrudGeneFormSimpleFormSub<Statistic,Byte>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        boostStatisSuperEff.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(_parent.getCompoFactory())));
        itemForBattleForm.add(boostStatisSuperEff.getGroup());
        multStatRank=new CrudGeneFormSimpleFormSub<Statistic,Byte>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        multStatRank.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(_parent.getCompoFactory())));
        itemForBattleForm.add(multStatRank.getGroup());
        winEvFight=new CrudGeneFormSimpleFormSub<Statistic,Short>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        winEvFight.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Short>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<Short>(new GeneComponentModelSubscribeShort(_parent.getCompoFactory())));
        itemForBattleForm.add(winEvFight.getGroup());
        multStat=new CrudGeneFormSimpleFormSub<Statistic,String>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        multStat.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,String>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<String>(new GeneComponentModelSubscribeString(_parent.getCompoFactory())));
        itemForBattleForm.add(multStat.getGroup());
        failStatus=new CrudGeneFormSimpleFormSub<String,String>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        failStatus.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(_parent.getSubscribedTranslationList().getFactorySt(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()), buildPart(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList().getFactorySt(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryString(_parent.getCompoFactory()));
        itemForBattleForm.add(failStatus.getGroup());
        increasingMaxNbRoundGlobalMove = new CrudGeneFormSimpleFormSub<String, Short>(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), _parent.getFrame());
        increasingMaxNbRoundGlobalMove.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Short>(_parent.getSubscribedTranslationList().getFactoryMv(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()),buildPart(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList().getFactoryMv(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Short>(new GeneComponentModelSubscribeShort(_parent.getCompoFactory())));
        itemForBattleForm.add(increasingMaxNbRoundGlobalMove.getGroup());
        increasingMaxNbRoundTeamMove = new CrudGeneFormSimpleFormSub<String, Short>(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), _parent.getFrame());
        increasingMaxNbRoundTeamMove.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Short>(_parent.getSubscribedTranslationList().getFactoryMv(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()),buildPart(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList().getFactoryMv(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Short>(new GeneComponentModelSubscribeShort(_parent.getCompoFactory())));
        itemForBattleForm.add(increasingMaxNbRoundTeamMove.getGroup());
        increasingMaxNbRoundTrap = new CrudGeneFormSimpleFormSub<String, Short>(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), _parent.getFrame());
        increasingMaxNbRoundTrap.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Short>(_parent.getSubscribedTranslationList().getFactoryMv(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()),buildPart(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList().getFactoryMv(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Short>(new GeneComponentModelSubscribeShort(_parent.getCompoFactory())));
        itemForBattleForm.add(increasingMaxNbRoundTrap.getGroup());
        hatching=ConverterCommonMapUtil.buildPkList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(hatching.geneEnum());
        immuMoves=ConverterCommonMapUtil.buildMoveList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(immuMoves.geneEnum());
        immuStatus=ConverterCommonMapUtil.buildStatusList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(immuStatus.geneEnum());
        immuTypes=ConverterCommonMapUtil.buildTypeList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(immuTypes.geneEnum());
        immuWeather=ConverterCommonMapUtil.buildMoveList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(immuWeather.geneEnum());
        synchroStatus=ConverterCommonMapUtil.buildStatusList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(synchroStatus.geneEnum());
        typesPk=ConverterCommonMapUtil.buildTypeList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        itemForBattleForm.add(typesPk.geneEnum());
        lawForAttackFirst = ConverterCommonMapUtil.buildMcBool(_parent.getFrame(),_parent.getCompoFactory());
        itemForBattleForm.add(lawForAttackFirst.getGroup());
        multPower = new GeneComponentModelSubscribeString(_parent.getCompoFactory());
        itemForBattleForm.add(multPower.geneEnum());
        multDamage = new GeneComponentModelSubscribeString(_parent.getCompoFactory());
        itemForBattleForm.add(multDamage.geneEnum());
        damageRecoil=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(damageRecoil.geneRate());
        drainedHpByDamageRate=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(drainedHpByDamageRate.geneRate());
        multDrainedHp=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(multDrainedHp.geneRate());
        multTrappingDamage=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(multTrappingDamage.geneRate());
        multWinningEv=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(multWinningEv.geneRate());
        multWinningExp=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(multWinningExp.geneRate());
        multWinningHappiness=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(multWinningHappiness.geneRate());
        protectAgainstKo=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(protectAgainstKo.geneRate());
        protectAgainstKoIfFullHp=new GeneComponentModelRate(_parent.getCompoFactory());
        itemForBattleForm.add(protectAgainstKoIfFullHp.geneRate());
        againstEvo=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(againstEvo);
        attackLast=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(attackLast);
        attacksSoon=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(attacksSoon);
        boostExp=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(boostExp);
        cancelImmuType=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(cancelImmuType);
        immuLowStatis=compoFactory_.newCustCheckBox();
        itemForBattleForm.add(immuLowStatis);
        effectSending = new CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(),_parent.getFrame());
        effectSending.initForm(new DisplayEntryCustSubElementEffect<EffectWhileSendingWithStatistic>(),new GeneComponentModelSubscribeFactoryDirect<EffectWhileSendingWithStatistic>(new GeneComponentModelSubscribeEffectWhileSending(new GeneComponentModelEffectWhileSending(_parent.getFrame(),_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), false))));
        itemForBattleForm.add(effectSending.getGroup());
        effectEndRound = new CrudGeneFormSimpleElementSub<EffectEndRound>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(),_parent.getFrame());
        effectEndRound.initForm(new DisplayEntryCustSubElementEffect<EffectEndRound>(),new GeneComponentModelSubscribeFactoryDirect<EffectEndRound>(new GeneComponentModelSubscribeEffectEndRound(new GeneComponentModelEffectEndRound(_parent.getFrame(),_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), false))));
        itemForBattleForm.add(effectEndRound.getGroup());
        itemForBattleForm.setVisible(false);
        return itemForBattleForm;
    }
    void display(String _eff) {
        itemForBattleForm.setVisible(StringUtil.quickEq(_eff, Item.ITEM_FOR_BATTLE));
    }
    void buildEntity(ItemForBattle _item) {
        _item.setBoostStatisTypes(ConverterCommonMapUtil.buildStringMapIdMapStatisticByte(boostStatisTypes.getList()));
        _item.setMultStatPokemonRank(ConverterCommonMapUtil.buildStatisticPokemons(multStatPokemonRank.getList()));
        _item.setBoostStatisSuperEff(ConverterCommonMapUtil.buildIdMapStatisticByte(boostStatisSuperEff.getList()));
        _item.setMultStatRank(ConverterCommonMapUtil.buildIdMapStatisticByte(multStatRank.getList()));
        _item.setWinEvFight(ConverterCommonMapUtil.buildIdMapStatisticShort(winEvFight.getList()));
        _item.setMultStat(ConverterCommonMapUtil.buildIdMapStatisticString(multStat.getList()));
        _item.setFailStatus(ConverterCommonMapUtil.buildStringMapString(failStatus.getList()));
        _item.setIncreasingMaxNbRoundGlobalMove(ConverterCommonMapUtil.buildStringMapShort(increasingMaxNbRoundGlobalMove.getList()));
        _item.setIncreasingMaxNbRoundTeamMove(ConverterCommonMapUtil.buildStringMapShort(increasingMaxNbRoundTeamMove.getList()));
        _item.setIncreasingMaxNbRoundTrap(ConverterCommonMapUtil.buildStringMapShort(increasingMaxNbRoundTrap.getList()));
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
        boostStatisTypes.setupValues(new MapToEntriesListUtil<String, IdMap<Statistic, Byte>>().build(_item.getBoostStatisTypes()));
        multStatPokemonRank.setupValues(new MapToEntriesListUtil<StatisticPokemon,Byte>().build(_item.getMultStatPokemonRank()));
        boostStatisSuperEff.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(_item.getBoostStatisSuperEff()));
        multStatRank.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(_item.getMultStatRank()));
        winEvFight.setupValues(new MapToEntriesListUtil<Statistic,Short>().build(_item.getWinEvFight()));
        multStat.setupValues(new MapToEntriesListUtil<Statistic,String>().build(_item.getMultStat()));
        failStatus.setupValues(new MapToEntriesListUtil<String,String>().build(_item.getFailStatus()));
        increasingMaxNbRoundGlobalMove.setupValues(new MapToEntriesListUtil<String,Short>().build(_item.getIncreasingMaxNbRoundGlobalMove()));
        increasingMaxNbRoundTeamMove.setupValues(new MapToEntriesListUtil<String,Short>().build(_item.getIncreasingMaxNbRoundTeamMove()));
        increasingMaxNbRoundTrap.setupValues(new MapToEntriesListUtil<String,Short>().build(_item.getIncreasingMaxNbRoundTrap()));
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
        ids_.add(new SubscribedTranslationRenamingValMidCrud<Statistic>(multStat.getCrud()));
        ids_.add(new SubscribedTranslationRenamingValPrefCrud<Statistic>(multStat.getCrud()));
        ids_.addAllElts(multStat.subscribeButtons());
        ids_.add(new SubscribedTranslationRenamingValCrud<String>(failStatus.getCrud()));
        ids_.add(new SubscribedTranslationRenamingValMidCrud<String>(failStatus.getCrud()));
        ids_.add(new SubscribedTranslationRenamingValPrefCrud<String>(failStatus.getCrud()));
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

    public CrudGeneFormSimpleFormSub<String, IdMap<Statistic, Byte>> getBoostStatisTypes() {
        return boostStatisTypes;
    }

    public CrudGeneFormSimpleElementSub<EffectEndRound> getEffectEndRound() {
        return effectEndRound;
    }

    public CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic> getEffectSending() {
        return effectSending;
    }

    public CrudGeneFormSimpleFormSub<StatisticPokemon, Byte> getMultStatPokemonRank() {
        return multStatPokemonRank;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
}
