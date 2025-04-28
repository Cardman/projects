package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectSwitchMoveTypes {

    private GeneComponentModelLsStrSub<String,StringList> replacingTypes;
    private CrudGeneFormSimpleFormSub<String,String> changeTypes;

    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        replacingTypes = ConverterCommonMapUtil.buildTypeList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffswitchmovestypes.M_P_62_REPLACING_TYPES,replacingTypes.geneEnum()));
        changeTypes = new CrudGeneFormSimpleFormSub<String,String>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        changeTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(_core.getFactory().getFactoryTy(), _core.getProgramInfos(),_core.getFacadeGame(),new StringMap<String>()),buildPart(_core, _core.getFactory().getFactoryTy(), new StringMap<String>()),buildPart(_core, _core.getFactory().getFactoryTy(), new StringMap<String>()),MessagesPkBean.EFF_SWITCHMOVESTYPES,MessagesDataEffswitchmovestypes.M_P_62_OLD_TYPE,MessagesDataEffswitchmovestypes.M_P_62_NEW_TYPE);
        selected_.add(line(_core,MessagesDataEffswitchmovestypes.M_P_62_CHANGING_TYPE,changeTypes.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_SWITCHMOVESTYPES, _key,_input);
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
