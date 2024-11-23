package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.abilities.*;
import aiki.fight.effects.*;
import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelAbilityData extends GeneComponentModelEntity<AbilityData> {
    private GeneComponentModelString multPower;
    private GeneComponentModelString multDamage;
    private CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic> effectSending;
    private CrudGeneFormSimpleElementSub<EffectEndRound> effectEndRound;

    public GeneComponentModelAbilityData(AbsCommonFrame _frame, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_frame,_core, _facade, _sub);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        SubscribedTranslationMessagesFactoryAb factoryAb_ = getSubscribedTranslationList().getFactoryAb();
        buildKey(_select,factoryAb_,factoryAb_.all(getFacade()).getKeys());
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(getGeneComponentModelSelectKey().geneEnum());
        AbsPanel form_ = compoFactory_.newLineBox();
        multPower = new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText());
        form_.add(multPower.geneString());
        multDamage = new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText());
        form_.add(multDamage.geneString());
        effectSending = new CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectSending.initForm(new DisplayEntryCustSubElementEffect<EffectWhileSendingWithStatistic>(),new GeneComponentModelSubscribeFactoryDirect<EffectWhileSendingWithStatistic>(new GeneComponentModelSubscribeEffectWhileSending(new GeneComponentModelEffectWhileSending(getFrame(),getCompoFactory(),getFacade(),getSubscribedTranslationList()))));
        form_.add(effectSending.getGroup());
        effectEndRound = new CrudGeneFormSimpleElementSub<EffectEndRound>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectEndRound.initForm(new DisplayEntryCustSubElementEffect<EffectEndRound>(),new GeneComponentModelSubscribeFactoryDirect<EffectEndRound>(new GeneComponentModelSubscribeEffectEndRound(new GeneComponentModelEffectEndRound(getFrame(),getCompoFactory(),getFacade(),getSubscribedTranslationList()))));
        form_.add(effectEndRound.getGroup());
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    @Override
    public EditedCrudPair<String,AbilityData> value() {
        AbilityData ent_ = Instances.newAbilityData();
        ent_.setMultPower(multPower.valueString());
        ent_.setMultDamage(multDamage.valueString());
        ent_.setEffectSending(effectSending.getList());
        ent_.setEffectEndRound(effectEndRound.getList());
        return new EditedCrudPair<String, AbilityData>(getGeneComponentModelSelectKey().tryRet(),ent_);
    }

    @Override
    public void value(EditedCrudPair<String,AbilityData> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        AbilityData ability_ = _v.getValue();
        multPower.valueString(ability_.getMultPower());
        multDamage.valueString(ability_.getMultDamage());
        effectSending.setupValues(ability_.getEffectSending());
        effectEndRound.setupValues(ability_.getEffectEndRound());
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(getGeneComponentModelSelectKey().getSubs());
        return ids_;
    }


    public GeneComponentModelString getMultDamage() {
        return multDamage;
    }

    public GeneComponentModelString getMultPower() {
        return multPower;
    }

    public CrudGeneFormSimpleElementSub<EffectWhileSendingWithStatistic> getEffectSending() {
        return effectSending;
    }

    public CrudGeneFormSimpleElementSub<EffectEndRound> getEffectEndRound() {
        return effectEndRound;
    }
}
