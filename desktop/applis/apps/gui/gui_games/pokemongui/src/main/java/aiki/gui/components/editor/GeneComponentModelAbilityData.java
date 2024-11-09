package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.abilities.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelAbilityData extends GeneComponentModelEntity<AbilityData> {
    private GeneComponentModelString multPower;
    private GeneComponentModelString multDamage;
//    private AbilityData element;

    public GeneComponentModelAbilityData(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_core, _facade, _sub);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        SubscribedTranslationMessagesFactoryAb factoryAb_ = getSubscribedTranslationList().getFactoryAb();
        buildKey(_select,factoryAb_,factoryAb_.all(getFacade()).getKeys());
        multPower = new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText());
        multDamage = new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText());
//        element = Instances.newAbilityData();
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(getGeneComponentModelSelectKey().geneEnum());
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(multPower.geneString());
        form_.add(multDamage.geneString());
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    @Override
    public EditedCrudPair<String,AbilityData> value() {
        AbilityData ent_ = Instances.newAbilityData();
        ent_.setMultPower(multPower.valueString());
        ent_.setMultDamage(multDamage.valueString());
        return new EditedCrudPair<String, AbilityData>(getGeneComponentModelSelectKey().tryRet(DataBase.EMPTY_STRING),ent_);
    }

    @Override
    public void value(EditedCrudPair<String,AbilityData> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        AbilityData ability_ = _v.getValue();
        multPower.valueString(ability_.getMultPower());
        multDamage.valueString(ability_.getMultDamage());
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
}
