package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.util.*;

public final class ContentComponentModelEffectSwitchMoveTypes {

    private GeneComponentModelLsStrSub<String,StringList> replacingTypes;
    private CrudGeneFormSimpleFormSub<String,String> changeTypes;

    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        replacingTypes = ConverterCommonMapUtil.buildTypeList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(replacingTypes.geneEnum());
        changeTypes = new CrudGeneFormSimpleFormSub<String,String>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        changeTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(_core.getFactory().getFactoryTy(), _core.getProgramInfos(),_core.getFacadeGame(),new StringMap<String>()),buildPart(_core, _core.getFactory().getFactoryTy(), new StringMap<String>()),buildPart(_core, _core.getFactory().getFactoryTy(), new StringMap<String>()));
        selected_.add(changeTypes.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbsGeneComponentModelEffect _core, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core.getProgramInfos(), _core.getFacadeGame(), _facto, _abs);
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
