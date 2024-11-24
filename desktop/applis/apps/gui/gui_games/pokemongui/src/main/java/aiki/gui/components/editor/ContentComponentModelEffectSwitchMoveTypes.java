package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelEffectSwitchMoveTypes {

    private GeneComponentModelLsStrSub<String,StringList> replacingTypes;
    private CrudGeneFormSimpleFormSub<String,String> changeTypes;

    private AbsPanel form;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        replacingTypes = ConverterCommonMapUtil.buildTypeList(_core,_fac,_fact);
        selected_.add(replacingTypes.geneEnum());
        changeTypes = new CrudGeneFormSimpleFormSub<String,String>(_core, _fac, _fact, _f);
        changeTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(_fact.getFactoryTy(), _core,_fac,new StringMap<String>()),buildPart(_core,_fac, _fact.getFactoryTy(), new StringMap<String>()),buildPart(_core,_fac, _fact.getFactoryTy(), new StringMap<String>()));
        selected_.add(changeTypes.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void buildEntity(EffectSwitchMoveTypes _edited) {
        _edited.setReplacingTypes(replacingTypes.tryRet());
        _edited.setChangeTypes(ConverterCommonMapUtil.buildStringMapString(changeTypes.getList()));
    }
    void feedForm(EffectSwitchMoveTypes _edited) {
        replacingTypes.setupValue(_edited.getReplacingTypes());
        changeTypes.setupValues(new MapToEntriesListUtil<String,String>().build(_edited.getChangeTypes()));
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }

    public GeneComponentModelLsStrSub<String,StringList> getReplacingTypes() {
        return replacingTypes;
    }

    public CrudGeneFormSimpleFormSub<String, String> getChangeTypes() {
        return changeTypes;
    }
}
