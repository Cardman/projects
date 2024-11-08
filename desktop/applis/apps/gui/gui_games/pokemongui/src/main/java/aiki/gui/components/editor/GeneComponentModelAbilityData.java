package aiki.gui.components.editor;

import aiki.fight.abilities.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelAbilityData implements GeneComponentModel<AbilityData> {
    private final AbstractProgramInfos compoFactory;
    private GeneComponentModelString multPower;
    private GeneComponentModelString multDamage;
    private AbilityData element;

    public GeneComponentModelAbilityData(AbstractProgramInfos _core) {
        this.compoFactory = _core;
    }
    @Override
    public AbsCustComponent gene() {
        multPower = new GeneComponentModelString(compoFactory,new StringList(),new DefValidateText());
        multDamage = new GeneComponentModelString(compoFactory,new StringList(),new DefValidateText());
        element = Instances.newAbilityData();
        AbsCompoFactory compoFactory_ = compoFactory.getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel form_ = compoFactory_.newLineBox();
        form_.add(multPower.gene());
        form_.add(multDamage.gene());
        sc_.setViewportView(form_);
        return sc_;
    }

    @Override
    public AbilityData value() {
        AbilityData ent_ = Instances.newAbilityData();
        ent_.setMultPower(multPower.valueString());
        ent_.setMultDamage(multDamage.valueString());
        return ent_;
    }

    @Override
    public AbilityData value(AbilityData _v) {
        multPower.valueString(_v.getMultPower());
        multDamage.valueString(_v.getMultDamage());
        return element;
    }

    public GeneComponentModelString getMultDamage() {
        return multDamage;
    }

    public GeneComponentModelString getMultPower() {
        return multPower;
    }
}
