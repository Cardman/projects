package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.effects.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelItem extends GeneComponentModelEntity<Item> implements ChangeableFormType {
    private final GeneComponentModelInt price;
    private GeneComponentModelString catchingRate;
    private GeneComponentModelLong steps;
    private CrudGeneFormSimpleFormSub<String,IdMap<Statistic, Byte>> boostStatisTypes;
    private GeneComponentModelString multPower;
    private GeneComponentModelString multDamage;
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
    private Item element;
    private GeneComponentModelEltEnumSub<String> effectKind;
    private AbsPanel ballForm;
    private AbsPanel itemForBattleForm;
    private AbsPanel repelForm;

    public GeneComponentModelItem(AbsCommonFrame _frame, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_frame,_core, _facade, _sub);
        price = new GeneComponentModelInt(_core);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        effectKind = new GeneComponentModelEltEnumSub<String>(new GeneComponentModelElt<String>(getCompoFactory(), getFacade().getData().getTranslatedClassesDescriptions().getVal(getCompoFactory().getLanguage()), new EmptyDefValue()));
        SubscribedTranslationMessagesFactoryIt factoryIt_ = getSubscribedTranslationList().getFactoryIt();
        buildKey(_select,factoryIt_,factoryIt_.all(getFacade()).getKeys());
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(geneComponentModelSelectKey());
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(getEffectKind().geneEnum());
        form_.add(price.geneInt());
        ballForm = compoFactory_.newLineBox();
        catchingRate = new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText());
        ballForm.add(catchingRate.geneString());
        ballForm.setVisible(false);
        form_.add(ballForm);

        itemForBattleForm = compoFactory_.newLineBox();
        boostStatisTypes = new CrudGeneFormSimpleFormSub<String, IdMap<Statistic, Byte>>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        boostStatisTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String, IdMap<Statistic, Byte>>(getSubscribedTranslationList().getFactoryTy(), getCompoFactory(),getFacade(),new StringMap<String>()),buildPart(getCompoFactory(), getFacade(), getSubscribedTranslationList().getFactoryTy(), new StringMap<String>()),new GeneComponentModelSubscribeFactoryDirect<IdMap<Statistic, Byte>>(new GeneComponentModelSubscribeStatisticByte(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame())));
        itemForBattleForm.add(boostStatisTypes.getGroup());
        multPower = new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText());
        itemForBattleForm.add(multPower.geneString());
        multDamage = new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText());
        itemForBattleForm.add(multDamage.geneString());
        damageRecoil=new GeneComponentModelRate(getCompoFactory());
        itemForBattleForm.add(damageRecoil.geneRate(Rate.zero()));
        drainedHpByDamageRate=new GeneComponentModelRate(getCompoFactory());
        itemForBattleForm.add(drainedHpByDamageRate.geneRate(Rate.zero()));
        multDrainedHp=new GeneComponentModelRate(getCompoFactory());
        itemForBattleForm.add(multDrainedHp.geneRate(Rate.zero()));
        multTrappingDamage=new GeneComponentModelRate(getCompoFactory());
        itemForBattleForm.add(multTrappingDamage.geneRate(Rate.zero()));
        multWinningEv=new GeneComponentModelRate(getCompoFactory());
        itemForBattleForm.add(multWinningEv.geneRate(Rate.zero()));
        multWinningExp=new GeneComponentModelRate(getCompoFactory());
        itemForBattleForm.add(multWinningExp.geneRate(Rate.zero()));
        multWinningHappiness=new GeneComponentModelRate(getCompoFactory());
        itemForBattleForm.add(multWinningHappiness.geneRate(Rate.zero()));
        protectAgainstKo=new GeneComponentModelRate(getCompoFactory());
        itemForBattleForm.add(protectAgainstKo.geneRate(Rate.zero()));
        protectAgainstKoIfFullHp=new GeneComponentModelRate(getCompoFactory());
        itemForBattleForm.add(protectAgainstKoIfFullHp.geneRate(Rate.zero()));
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
        effectSending = new CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectSending.initForm(new DisplayEntryCustSubElementEffect<EffectWhileSendingWithStatistic>(),new GeneComponentModelSubscribeFactoryDirect<EffectWhileSendingWithStatistic>(new GeneComponentModelSubscribeEffectWhileSending(new GeneComponentModelEffectWhileSending(getFrame(),getCompoFactory(),getFacade(),getSubscribedTranslationList()))));
        itemForBattleForm.add(effectSending.getGroup());
        effectEndRound = new CrudGeneFormSimpleElementSub<EffectEndRound>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectEndRound.initForm(new DisplayEntryCustSubElementEffect<EffectEndRound>(),new GeneComponentModelSubscribeFactoryDirect<EffectEndRound>(new GeneComponentModelSubscribeEffectEndRound(new GeneComponentModelEffectEndRound(getFrame(),getCompoFactory(),getFacade(),getSubscribedTranslationList()))));
        itemForBattleForm.add(effectEndRound.getGroup());
        itemForBattleForm.setVisible(false);
        form_.add(itemForBattleForm);

        repelForm = compoFactory_.newLineBox();
        steps = new GeneComponentModelLong(getCompoFactory());
        repelForm.add(steps.gene(0L));
        repelForm.setVisible(false);
        form_.add(repelForm);
        sc_.setViewportView(form_);
        page_.add(sc_);
        effectKind.getSelectUniq().getSelect().addListener(new ChangingTypeEvent(this));
        ConverterCommonMapUtil.trigger(effectKind.getSelectUniq(),Item.BALL);
        return page_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    @Override
    public void applyChange() {
        String eff_ = getEffectKind().tryRet();
        display(eff_);
        if (StringUtil.quickEq(eff_, Item.BALL)) {
            element = Instances.newBall();
        }
        if (StringUtil.quickEq(eff_, Item.ITEM_FOR_BATTLE)) {
            element = Instances.newItemForBattle();
        }
        if (StringUtil.quickEq(eff_, Item.REPEL)) {
            element = Instances.newRepel();
        }
        getEffectKind().getSelectUniq().getSelect().repaint();
        getFrame().pack();
    }


    @Override
    public EditedCrudPair<String,Item> value() {
        element.setPrice(price.valueInt());
        if (element instanceof Ball) {
            ((Ball)element).setCatchingRate(catchingRate.valueString());
        }
        if (element instanceof ItemForBattle) {
            ((ItemForBattle)element).setBoostStatisTypes(ConverterCommonMapUtil.buildStringMapIdMapStatisticByte(boostStatisTypes.getList()));
            ((ItemForBattle)element).setMultPower(multPower.valueString());
            ((ItemForBattle)element).setMultDamage(multDamage.valueString());
            ((ItemForBattle)element).setDamageRecoil(damageRecoil.valueRate());
            ((ItemForBattle)element).setDrainedHpByDamageRate(drainedHpByDamageRate.valueRate());
            ((ItemForBattle)element).setMultDrainedHp(multDrainedHp.valueRate());
            ((ItemForBattle)element).setMultTrappingDamage(multTrappingDamage.valueRate());
            ((ItemForBattle)element).setMultWinningEv(multWinningEv.valueRate());
            ((ItemForBattle)element).setMultWinningExp(multWinningExp.valueRate());
            ((ItemForBattle)element).setMultWinningHappiness(multWinningHappiness.valueRate());
            ((ItemForBattle)element).setProtectAgainstKo(protectAgainstKo.valueRate());
            ((ItemForBattle)element).setProtectAgainstKoIfFullHp(protectAgainstKoIfFullHp.valueRate());
            ((ItemForBattle)element).setAgainstEvo(againstEvo.isSelected());
            ((ItemForBattle)element).setAttackLast(attackLast.isSelected());
            ((ItemForBattle)element).setAttacksSoon(attacksSoon.isSelected());
            ((ItemForBattle)element).setBoostExp(boostExp.isSelected());
            ((ItemForBattle)element).setCancelImmuType(cancelImmuType.isSelected());
            ((ItemForBattle)element).setImmuLowStatis(immuLowStatis.isSelected());
            ((ItemForBattle)element).setEffectSending(effectSending.getList());
            ((ItemForBattle)element).setEffectEndRound(effectEndRound.getList());
        }
        if (element instanceof Repel) {
            ((Repel)element).setSteps(steps.valueLong());
        }
        return new EditedCrudPair<String,Item>(getGeneComponentModelSelectKey().tryRet(),element);
    }

    @Override
    public void value(EditedCrudPair<String,Item> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        Item item_ = _v.getValue();
        price.valueInt(item_.getPrice());
        if (item_ instanceof Ball) {
            catchingRate.valueString(((Ball)item_).getCatchingRate());
        }
        if (item_ instanceof ItemForBattle) {
            boostStatisTypes.setupValues(new MapToEntriesListUtil<String, IdMap<Statistic, Byte>>().build(((ItemForBattle)item_).getBoostStatisTypes()));
            multPower.valueString(((ItemForBattle)item_).getMultPower());
            multDamage.valueString(((ItemForBattle)item_).getMultDamage());
            damageRecoil.valueRate(((ItemForBattle)item_).getDamageRecoil());
            drainedHpByDamageRate.valueRate(((ItemForBattle)item_).getDrainedHpByDamageRate());
            multDrainedHp.valueRate(((ItemForBattle)item_).getMultDrainedHp());
            multTrappingDamage.valueRate(((ItemForBattle)item_).getMultTrappingDamage());
            multWinningEv.valueRate(((ItemForBattle)item_).getMultWinningEv());
            multWinningExp.valueRate(((ItemForBattle)item_).getMultWinningExp());
            multWinningHappiness.valueRate(((ItemForBattle)item_).getMultWinningHappiness());
            protectAgainstKo.valueRate(((ItemForBattle)item_).getProtectAgainstKo());
            protectAgainstKoIfFullHp.valueRate(((ItemForBattle)item_).getProtectAgainstKoIfFullHp());
            againstEvo.setSelected(((ItemForBattle)item_).getAgainstEvo());
            attackLast.setSelected(((ItemForBattle)item_).getAttackLast());
            attacksSoon.setSelected(((ItemForBattle)item_).getAttacksSoon());
            boostExp.setSelected(((ItemForBattle)item_).getBoostExp());
            cancelImmuType.setSelected(((ItemForBattle)item_).getCancelImmuType());
            immuLowStatis.setSelected(((ItemForBattle)item_).getImmuLowStatis());
            effectSending.setupValues(((ItemForBattle)item_).getEffectSending());
            effectEndRound.setupValues(((ItemForBattle)item_).getEffectEndRound());
        }
        if (item_ instanceof Repel) {
            steps.valueLong(((Repel)item_).getSteps());
        }
        element = item_;
        String type_ = item_.getItemType();
        display(type_);
        getEffectKind().setupValue(type_);
        getEffectKind().getSelectUniq().getSelect().repaint();
    }

    private void display(String _eff) {
        ballForm.setVisible(StringUtil.quickEq(_eff, Item.BALL));
        itemForBattleForm.setVisible(StringUtil.quickEq(_eff, Item.ITEM_FOR_BATTLE));
        repelForm.setVisible(StringUtil.quickEq(_eff, Item.REPEL));
    }
    public GeneComponentModelInt getPrice() {
        return price;
    }

    public GeneComponentModelEltEnumSub<String> getEffectKind() {
        return effectKind;
    }

    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getGeneComponentModelSelectKey().getSubs());
        ids_.addAllElts(getEffectKind().getSubs());
        ids_.addAllElts(getBoostStatisTypes().subscribeButtons());
        return ids_;
    }

    public GeneComponentModelLong getSteps() {
        return steps;
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

    public CrudGeneFormSimpleFormSub<String, IdMap<Statistic, Byte>> getBoostStatisTypes() {
        return boostStatisTypes;
    }
}
