package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.items.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class GeneComponentModelItem extends GeneComponentModelEntity<Item> implements ChangeableFormType {
    private final GeneComponentModelInt price;
    private GeneComponentModelString catchingRate;
    private GeneComponentModelLong steps;
    private Item element;
    private GeneComponentModelEltEnumSub<String> effectKind;
    private AbsPanel ballForm;
    private final ContentComponentModelBerry berryForm = new ContentComponentModelBerry();
    private final ContentComponentModelItemForBattle itemForBattleForm = new ContentComponentModelItemForBattle();
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
        form_.add(berryForm.form(this));
        form_.add(itemForBattleForm.form(this));
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
        if (element instanceof Berry) {
            berryForm.buildEntity((Berry)element);
        }
        if (element instanceof ItemForBattle) {
            itemForBattleForm.buildEntity((ItemForBattle)element);
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
        if (item_ instanceof Berry) {
            berryForm.feedForm((Berry) item_);
        }
        if (item_ instanceof ItemForBattle) {
            itemForBattleForm.feedForm((ItemForBattle) item_);
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
        berryForm.display(_eff);
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
        ids_.addAllElts(berryForm.all());
        ids_.addAllElts(itemForBattleForm.all());
        return ids_;
    }

    public GeneComponentModelLong getSteps() {
        return steps;
    }

    public ContentComponentModelBerry getBerryForm() {
        return berryForm;
    }

    public ContentComponentModelItemForBattle getItemForBattleForm() {
        return itemForBattleForm;
    }

}
