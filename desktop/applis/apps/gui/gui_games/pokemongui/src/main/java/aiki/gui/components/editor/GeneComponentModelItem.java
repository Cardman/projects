package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.items.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelItem extends GeneComponentModelEntity<Item> implements ChangeableFormType {
    private final GeneComponentModelInt price;
    private GeneComponentModelSubscribeString catchingRate;
    private GeneComponentModelRate hp;
    private GeneComponentModelRate healedHpRate;
    private GeneComponentModelLong steps;
    private Item element;
    private GeneComponentModelEltEnumSub<String> effectKind;
    private AbsPanel ballForm;
    private final ContentComponentModelBerry berryForm = new ContentComponentModelBerry();
    private final ContentComponentModelBoost boostForm = new ContentComponentModelBoost();
    private final ContentComponentModelFossil fossilForm = new ContentComponentModelFossil();
    private final ContentComponentModelHealingItem healingItemForm = new ContentComponentModelHealingItem();
    private final ContentComponentModelHealingStatus healingStatusForm = new ContentComponentModelHealingStatus();
    private final ContentComponentModelHealingPp healingPpForm = new ContentComponentModelHealingPp();
    private final ContentComponentModelItemForBattle itemForBattleForm = new ContentComponentModelItemForBattle();
    private AbsPanel healHpForm;
    private AbsPanel healHpStatusForm;
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
        catchingRate = new GeneComponentModelSubscribeString(getCompoFactory());
        ballForm.add(catchingRate.geneEnum());
        ballForm.setVisible(false);
        form_.add(ballForm);
        form_.add(berryForm.form(this));
        form_.add(boostForm.form(this));
        form_.add(fossilForm.form(this));
        form_.add(healingItemForm.form(this));
        healHpForm = compoFactory_.newLineBox();
        hp = new GeneComponentModelRate(getCompoFactory());
        healHpForm.add(hp.geneRate());
        healHpForm.setVisible(false);
        form_.add(healHpForm);
        form_.add(healingStatusForm.form(this));
        healHpStatusForm = compoFactory_.newLineBox();
        healedHpRate = new GeneComponentModelRate(getCompoFactory());
        healHpStatusForm.add(healedHpRate.geneRate());
        healHpStatusForm.setVisible(false);
        form_.add(healHpStatusForm);
        form_.add(healingPpForm.form(this));
        form_.add(itemForBattleForm.form(this));
        repelForm = compoFactory_.newLineBox();
        steps = new GeneComponentModelLong(getCompoFactory());
        repelForm.add(steps.geneLong());
        repelForm.setVisible(false);
        form_.add(repelForm);
        sc_.setViewportView(form_);
        page_.add(sc_);
        effectKind.getSelectUniq().getSelect().addListener(new ChangingTypeEvent(this));
        ConverterCommonMapUtil.trigger(effectKind.getSelectUniq(),Item.BALL);
        return page_;
    }
    @Override
    public void applyChange() {
        String eff_ = getEffectKind().tryRet();
        display(eff_);
        if (StringUtil.quickEq(eff_, Item.BALL)) {
            element = Instances.newBall();
        }
        if (StringUtil.quickEq(eff_, Item.BERRY)) {
            element = Instances.newBerry();
        }
        if (StringUtil.quickEq(eff_, Item.BOOST)) {
            element = Instances.newBoost();
        }
        if (StringUtil.quickEq(eff_, Item.EVOLVING_ITEM)) {
            element = Instances.newEvolvingItem();
        }
        if (StringUtil.quickEq(eff_, Item.EVOLVING_STONE)) {
            element = Instances.newEvolvingStone();
        }
        if (StringUtil.quickEq(eff_, Item.FOSSIL)) {
            element = Instances.newFossil();
        }
        if (StringUtil.quickEq(eff_, Item.HEALING_HP)) {
            element = Instances.newHealingHp();
        }
        if (StringUtil.quickEq(eff_, Item.HEALING_HP_STATUS)) {
            element = Instances.newHealingHpStatus();
        }
        if (StringUtil.quickEq(eff_, Item.HEALING_ITEM)) {
            element = Instances.newHealingSimpleItem();
        }
        if (StringUtil.quickEq(eff_, Item.HEALING_PP)) {
            element = Instances.newHealingPp();
        }
        if (StringUtil.quickEq(eff_, Item.HEALING_STATUS)) {
            element = Instances.newHealingSimpleStatus();
        }
        if (StringUtil.quickEq(eff_, Item.ITEM_FOR_BATTLE)) {
            element = Instances.newItemForBattle();
        }
        if (StringUtil.quickEq(eff_, Item.REPEL)) {
            element = Instances.newRepel();
        }
        if (StringUtil.quickEq(eff_, Item.SELLING_ITEM)) {
            element = Instances.newSellingItem();
        }
        effectSub(element);
        getEffectKind().getSelectUniq().getSelect().repaint();
        getFrame().pack();
    }


    @Override
    public EditedCrudPair<String,Item> value() {
        element.setPrice(price.valueInt());
        if (element instanceof Ball) {
            ((Ball)element).setCatchingRate(catchingRate.tryRet());
        }
        if (element instanceof Berry) {
            berryForm.buildEntity((Berry)element);
        }
        if (element instanceof Boost) {
            boostForm.buildEntity((Boost)element);
        }
        if (element instanceof Fossil) {
            fossilForm.buildEntity((Fossil)element);
        }
        if (element instanceof HealingItem) {
            healingItemForm.buildEntity((HealingItem)element);
            valueHeal();
        }
        if (element instanceof ItemForBattle) {
            itemForBattleForm.buildEntity((ItemForBattle)element);
        }
        if (element instanceof Repel) {
            ((Repel)element).setSteps(steps.valueLong());
        }
        return new EditedCrudPair<String,Item>(getGeneComponentModelSelectKey().tryRet(),element);
    }

    private void valueHeal() {
        if (element instanceof HealingHp) {
            ((HealingHp)element).setHp(hp.valueRate());
        }
        if (element instanceof HealingPp) {
            healingPpForm.buildEntity((HealingPp)element);
        }
        if (element instanceof HealingStatus) {
            healingStatusForm.buildEntity((HealingStatus) element);
            if (element instanceof HealingHpStatus) {
                ((HealingHpStatus)element).setHealedHpRate(healedHpRate.valueRate());
            }
        }
    }

    @Override
    public void value(EditedCrudPair<String,Item> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        Item item_ = _v.getValue();
        price.valueInt(item_.getPrice());
        if (item_ instanceof Ball) {
            catchingRate.setupValue(((Ball)item_).getCatchingRate());
        }
        if (item_ instanceof Berry) {
            berryForm.feedForm((Berry) item_);
        }
        if (item_ instanceof Boost) {
            boostForm.feedForm((Boost) item_);
        }
        if (item_ instanceof Fossil) {
            fossilForm.feedForm((Fossil) item_);
        }
        if (item_ instanceof HealingItem) {
            healingItemForm.feedForm((HealingItem) item_);
            valueHeal(item_);
        }
        if (item_ instanceof ItemForBattle) {
            itemForBattleForm.feedForm((ItemForBattle) item_);
        }
        if (item_ instanceof Repel) {
            steps.valueLong(((Repel)item_).getSteps());
        }
        element = item_;
        effectSub(item_);
        String type_ = item_.getItemType();
        display(type_);
        getEffectKind().setupValue(type_);
        getEffectKind().getSelectUniq().getSelect().repaint();
    }

    private void valueHeal(Item _item) {
        if (_item instanceof HealingHp) {
            hp.valueRate(((HealingHp)_item).getHp());
        }
        if (_item instanceof HealingPp) {
            healingPpForm.feedForm((HealingPp)_item);
        }
        if (_item instanceof HealingStatus) {
            healingStatusForm.feedForm((HealingStatus) _item);
            if (_item instanceof HealingHpStatus) {
                healedHpRate.valueRate(((HealingHpStatus)_item).getHealedHpRate());
            }
        }
    }
    private void effectSub(Item _v) {
        getSubscribedTranslationList().getModifiedEntitiesRenameMid().setItemForBattle(_v);
        getSubscribedTranslationList().getModifiedEntitiesRenamePref().setItemForBattle(_v);
        getSubscribedTranslationList().getFactoryAb().setItemForBattle(_v);
        getSubscribedTranslationList().getFactoryCa().setItemForBattle(_v);
        getSubscribedTranslationList().getFactoryIt().setItemForBattle(_v);
        getSubscribedTranslationList().getFactoryMv().setItemForBattle(_v);
        getSubscribedTranslationList().getFactoryPk().setItemForBattle(_v);
        getSubscribedTranslationList().getFactorySt().setItemForBattle(_v);
        getSubscribedTranslationList().getFactoryTy().setItemForBattle(_v);
    }
    private void display(String _eff) {
        ballForm.setVisible(StringUtil.quickEq(_eff, Item.BALL));
        berryForm.display(_eff);
        boostForm.display(_eff);
        healingItemForm.display(_eff);
        healHpForm.setVisible(StringUtil.quickEq(_eff, Item.HEALING_HP));
        healingStatusForm.display(_eff);
        healHpStatusForm.setVisible(StringUtil.quickEq(_eff, Item.HEALING_HP_STATUS));
        healingPpForm.display(_eff);
        fossilForm.display(_eff);
        itemForBattleForm.display(_eff);
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
        ids_.addAllElts(catchingRate.getSubs());
        ids_.addAllElts(berryForm.all());
        ids_.addAllElts(boostForm.all());
        ids_.addAllElts(fossilForm.all());
        ids_.addAllElts(healingItemForm.all());
        ids_.addAllElts(healingStatusForm.all());
        ids_.addAllElts(itemForBattleForm.all());
        return ids_;
    }

    public GeneComponentModelLong getSteps() {
        return steps;
    }

    public ContentComponentModelBerry getBerryForm() {
        return berryForm;
    }

    public ContentComponentModelBoost getBoostForm() {
        return boostForm;
    }

    public ContentComponentModelFossil getFossilForm() {
        return fossilForm;
    }

    public ContentComponentModelHealingItem getHealingItemForm() {
        return healingItemForm;
    }

    public ContentComponentModelHealingPp getHealingPpForm() {
        return healingPpForm;
    }

    public ContentComponentModelHealingStatus getHealingStatusForm() {
        return healingStatusForm;
    }

    public ContentComponentModelItemForBattle getItemForBattleForm() {
        return itemForBattleForm;
    }

}
