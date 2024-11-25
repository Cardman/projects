package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;

public final class ContentComponentModelBerry {
    private CrudGeneFormSimpleFormSub<Statistic, Byte> boostStatis;
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
        boostStatis=new CrudGeneFormSimpleFormSub<Statistic,Byte>(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList(), _parent.getFrame());
        boostStatis.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(_parent.getSubscribedTranslationList().getFactoryStat(),_parent.getCompoFactory(),_parent.getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(_parent.getCompoFactory(), _parent.getSubscribedTranslationList().getFactoryStat(), _parent.getFacade()), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(_parent.getCompoFactory())));
        berryForm.add(boostStatis.getGroup());
        damageRateRecoilFoe = new CrudGeneFormSimpleFormSub<String, Rate>(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), _parent.getFrame());
        damageRateRecoilFoe.initFormWithVal(new DisplayEntryCustSubElementImpl<String,Rate>(_parent.getSubscribedTranslationList().getFactoryCa(),_parent.getCompoFactory(),_parent.getFacade(), new StringMap<String>()),buildPart(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList().getFactoryCa(),new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_parent.getCompoFactory())));
        berryForm.add(damageRateRecoilFoe.getGroup());
        healStatus=ConverterCommonMapUtil.buildStatusList(_parent.getCompoFactory(),_parent.getFacade(),_parent.getSubscribedTranslationList());
        berryForm.add(healStatus.geneEnum());
        categoryBoosting = ConverterCommonMapUtil.buildCatElt(_parent.getCompoFactory(), _parent.getFacade(), _parent.getSubscribedTranslationList(), ConverterCommonMapUtil.defKeyEmpty(" "));
        berryForm.add(categoryBoosting.geneEnum());
        healHp=new GeneComponentModelRate(_parent.getCompoFactory());
        berryForm.add(healHp.geneRate(Rate.zero()));
        healHpBySuperEffMove=new GeneComponentModelRate(_parent.getCompoFactory());
        berryForm.add(healHpBySuperEffMove.geneRate(Rate.zero()));
        healHpRate=new GeneComponentModelRate(_parent.getCompoFactory());
        berryForm.add(healHpRate.geneRate(Rate.zero()));
        maxHpHealingHp=new GeneComponentModelRate(_parent.getCompoFactory());
        berryForm.add(maxHpHealingHp.geneRate(Rate.zero()));
        maxHpHealingHpRate=new GeneComponentModelRate(_parent.getCompoFactory());
        berryForm.add(maxHpHealingHpRate.geneRate(Rate.zero()));
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
        _item.setBoostStatis(ConverterCommonMapUtil.buildIdMapStatisticByte(boostStatis.getList()));
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
        boostStatis.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(_item.getBoostStatis()));
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
        ids_.addAllElts(boostStatis.subscribeButtons());
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

    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
}
