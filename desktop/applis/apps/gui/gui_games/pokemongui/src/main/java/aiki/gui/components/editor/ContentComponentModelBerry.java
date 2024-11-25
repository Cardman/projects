package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelBerry {
    private CrudGeneFormSimpleFormSub<Statistic, BoostHpRate> multStat;
    private CrudGeneFormSimpleFormSub<Statistic, Byte> boostStatis;
    private CrudGeneFormSimpleFormSub<String,EfficiencyRate> multFoesDamage;
    private CrudGeneFormSimpleFormSub<String,Rate> damageRateRecoilFoe;
    private GeneComponentModelLsStrSub<String,StringList> healStatus;
    private GeneComponentModelEltEnumSub<String> categoryBoosting;
    private GeneComponentModelRate healHp;
    private GeneComponentModelRate healHpBySuperEffMove;
    private GeneComponentModelRate healHpRate;
    private GeneComponentModelRate maxHpHealingHp;
    private GeneComponentModelRate maxHpHealingHpRate;
    private GeneComponentModelInt healPp;
    private AbsCustCheckBox lawForAttackFirst;
    private AbsCustCheckBox withoutFail;
    private AbsPanel berryForm;
    AbsPanel form(GeneComponentModelItem _parent) {
        AbsCompoFactory compoFactory_ = _parent.getCompoFactory().getCompoFactory();
        berryForm = compoFactory_.newLineBox();
        multStat=new CrudGeneFormSimpleFormSub<Statistic,BoostHpRate>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        multStat.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,BoostHpRate>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<BoostHpRate>(new GeneComponentModelSubscribeBoostHpRate(_parent.getCompoFactory())));
        berryForm.add(multStat.getGroup());
        boostStatis=new CrudGeneFormSimpleFormSub<Statistic,Byte>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        boostStatis.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(_parent.getCompoFactory())));
        berryForm.add(boostStatis.getGroup());
        multFoesDamage = new CrudGeneFormSimpleFormSub<String, EfficiencyRate>(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), _parent.getFrame());
        multFoesDamage.initFormWithVal(new DisplayEntryCustSubElementImpl<String,EfficiencyRate>(_parent.getSubscribedTranslationList().getFactoryTy(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()),buildPart(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList().getFactoryTy(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<EfficiencyRate>(new GeneComponentModelSubscribeEfficiencyRate(_parent.getCompoFactory())));
        berryForm.add(multFoesDamage.getGroup());
        damageRateRecoilFoe = new CrudGeneFormSimpleFormSub<String, Rate>(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), _parent.getFrame());
        damageRateRecoilFoe.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Rate>(_parent.getSubscribedTranslationList().getFactoryCa(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()),buildPart(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList().getFactoryCa(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_parent.getCompoFactory())));
        berryForm.add(damageRateRecoilFoe.getGroup());
        healStatus=ConverterCommonMapUtil.buildStatusList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        berryForm.add(healStatus.geneEnum());
        categoryBoosting = ConverterCommonMapUtil.buildCatElt(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), ConverterCommonMapUtil.defKeyEmpty(" "));
        berryForm.add(categoryBoosting.geneEnum());
        healHp=new GeneComponentModelRate(_parent.getCompoFactory());
        berryForm.add(healHp.geneRate());
        healHpBySuperEffMove=new GeneComponentModelRate(_parent.getCompoFactory());
        berryForm.add(healHpBySuperEffMove.geneRate());
        healHpRate=new GeneComponentModelRate(_parent.getCompoFactory());
        berryForm.add(healHpRate.geneRate());
        maxHpHealingHp=new GeneComponentModelRate(_parent.getCompoFactory());
        berryForm.add(maxHpHealingHp.geneRate());
        maxHpHealingHpRate=new GeneComponentModelRate(_parent.getCompoFactory());
        berryForm.add(maxHpHealingHpRate.geneRate());
        healPp=new GeneComponentModelInt(_parent.getCompoFactory());
        berryForm.add(healPp.geneInt());
        lawForAttackFirst=compoFactory_.newCustCheckBox();
        berryForm.add(lawForAttackFirst);
        withoutFail=compoFactory_.newCustCheckBox();
        berryForm.add(withoutFail);
        berryForm.setVisible(false);
        return berryForm;
    }
    void display(String _eff) {
        berryForm.setVisible(StringUtil.quickEq(_eff, Item.BERRY));
    }
    void buildEntity(Berry _item) {
        _item.setMultStat(ConverterCommonMapUtil.buildIdMapStatisticBoostHpRate(multStat.getList()));
        _item.setBoostStatis(ConverterCommonMapUtil.buildIdMapStatisticByte(boostStatis.getList()));
        _item.setMultFoesDamage(ConverterCommonMapUtil.buildStringMapEfficiencyRate(multFoesDamage.getList()));
        _item.setDamageRateRecoilFoe(ConverterCommonMapUtil.buildStringMapRate(damageRateRecoilFoe.getList()));
        _item.setHealStatus(healStatus.tryRet());
        _item.setCategoryBoosting(categoryBoosting.tryRet());
        _item.setHealHp(healHp.valueRate());
        _item.setHealHpBySuperEffMove(healHpBySuperEffMove.valueRate());
        _item.setHealHpRate(healHpRate.valueRate());
        _item.setMaxHpHealingHp(maxHpHealingHp.valueRate());
        _item.setMaxHpHealingHpRate(maxHpHealingHpRate.valueRate());
        _item.setHealPp(healPp.valueInt());
        _item.setLawForAttackFirst(lawForAttackFirst.isSelected());
        _item.setWithoutFail(withoutFail.isSelected());
    }
    void feedForm(Berry _item) {
        multStat.setupValues(new MapToEntriesListUtil<Statistic,BoostHpRate>().build(_item.getMultStat()));
        boostStatis.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(_item.getBoostStatis()));
        multFoesDamage.setupValues(new MapToEntriesListUtil<String,EfficiencyRate>().build(_item.getMultFoesDamage()));
        damageRateRecoilFoe.setupValues(new MapToEntriesListUtil<String,Rate>().build(_item.getDamageRateRecoilFoe()));
        healStatus.setupValue(_item.getHealStatus());
        categoryBoosting.setupValue(_item.getCategoryBoosting());
        healHp.valueRate(_item.getHealHp());
        healHpBySuperEffMove.valueRate(_item.getHealHpBySuperEffMove());
        healHpRate.valueRate(_item.getHealHpRate());
        maxHpHealingHp.valueRate(_item.getMaxHpHealingHp());
        maxHpHealingHpRate.valueRate(_item.getMaxHpHealingHpRate());
        healPp.valueInt(_item.getHealPp());
        lawForAttackFirst.setSelected(_item.getLawForAttackFirst());
        withoutFail.setSelected(_item.getWithoutFail());
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(multStat.subscribeButtons());
        ids_.addAllElts(boostStatis.subscribeButtons());
        ids_.addAllElts(multFoesDamage.subscribeButtons());
        ids_.addAllElts(damageRateRecoilFoe.subscribeButtons());
        ids_.addAllElts(healStatus.getSubs());
        ids_.addAllElts(categoryBoosting.getSubs());
        return ids_;
    }

    public AbsCustCheckBox getLawForAttackFirst(){
        return lawForAttackFirst;
    }

    public AbsCustCheckBox getWithoutFail(){
        return withoutFail;
    }

    public CrudGeneFormSimpleFormSub<Statistic, BoostHpRate> getMultStat() {
        return multStat;
    }

    public CrudGeneFormSimpleFormSub<String, EfficiencyRate> getMultFoesDamage() {
        return multFoesDamage;
    }

    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
}
