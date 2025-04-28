package aiki.gui.components.editor;

import aiki.db.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import aiki.map.levels.enums.*;
import code.gui.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectSwitchTypes {

    private CrudGeneFormSimpleFormSub<EnvironmentType, String> chgtTypeByEnv;
    private GeneComponentModelElt<String> constValuesType;
    private GeneComponentModelElt<String> exchangeTypes;
    private GeneComponentModelLsStrSub<String,StringList> constTypes;
    private GeneComponentModelLsStrSub<String,StringList> addedTypes;

    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        constTypes = ConverterCommonMapUtil.buildTypeList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffswitchtypes.M_P_65_AFFECT_TYPES,constTypes.geneEnum()));
        addedTypes = ConverterCommonMapUtil.buildTypeList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffswitchtypes.M_P_65_ADDED_TYPES,addedTypes.geneEnum()));
        exchangeTypes = new GeneComponentModelElt<String>(_core.getProgramInfos(), MessagesPkEditor.getMessagesEditorSelectExchangeTypeTr(MessagesPkEditor.getAppliTr(_core.getProgramInfos().currentLg())).getMapping(),new EmptyDefValue());
        selected_.add(line(_core,MessagesDataEffswitchtypes.M_P_65_SWITCH_TYPES,exchangeTypes.geneEnum()));
        exchangeTypes.setupValue(DataBase.DEF_EXCHANGE_TYPE_NOTHING);
        constValuesType = new GeneComponentModelElt<String>(_core.getProgramInfos(), MessagesPkEditor.getMessagesEditorSelectConstValuesTypeTr(MessagesPkEditor.getAppliTr(_core.getProgramInfos().currentLg())).getMapping(),new EmptyDefValue());
        selected_.add(line(_core,MessagesDataEffswitchtypes.M_P_65_AFFECT_TYPES_CONST,constValuesType.geneEnum()));
        constValuesType.setupValue(DataBase.DEF_CONST_VALUES_TYPE_NOTHING);
        chgtTypeByEnv = new CrudGeneFormSimpleFormSub<EnvironmentType, String>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        chgtTypeByEnv.initFormWithVal(new DisplayEntryCustSubElementImpl<EnvironmentType, String>(_core.getFactory().getFactoryEnvironmentType(),_core.getProgramInfos(),_core.getFacadeGame(), new IdMap<EnvironmentType, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<EnvironmentType>(_core.getProgramInfos(),_core.getFactory().getFactoryEnvironmentType(),_core.getFacadeGame()), buildPart(_core, _core.getFactory().getFactoryTy(),new StringMap<String>()),MessagesPkBean.EFF_SWITCHTYPES, MessagesDataEffswitchtypes.M_P_65_ENVIR_ENV,MessagesDataEffswitchtypes.M_P_65_ENVIR_TYPE);
        selected_.add(line(_core,MessagesDataEffswitchtypes.M_P_65_ENVIR,chgtTypeByEnv.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_SWITCHTYPES, _key,_input);
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbsGeneComponentModelEffect _core, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core.getProgramInfos(), _core.getFacadeGame(), _facto, _abs);
    }
    void buildEntity(EffectSwitchTypes _edited) {
        _edited.setAddedTypes(addedTypes.tryRet());
        _edited.setConstTypes(constTypes.tryRet());
        _edited.setExchangeTypes(ExchangeType.getExchangeTypeByName(exchangeTypes.tryRet()));
        _edited.setConstValuesType(ConstValuesType.getConstValuesTypeByName(constValuesType.tryRet()));
        _edited.setChgtTypeByEnv(ConverterCommonMapUtil.buildIdMapEnvironmentTypeString(chgtTypeByEnv.getList()));
    }
    void feedForm(EffectSwitchTypes _edited) {
        addedTypes.setupValue(_edited.getAddedTypes());
        constTypes.setupValue(_edited.getConstTypes());
        exchangeTypes.setupValue(_edited.getExchangeTypes().getExcType());
        constValuesType.setupValue(_edited.getConstValuesType().getCstValType());
        chgtTypeByEnv.setupValues(new MapToEntriesListUtil<EnvironmentType,String>().build(_edited.getChgtTypeByEnv()));
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }


    public CrudGeneFormSimpleFormSub<EnvironmentType, String> getChgtTypeByEnv() {
        return chgtTypeByEnv;
    }

    public GeneComponentModelLsStrSub<String,StringList> getAddedTypes() {
        return addedTypes;
    }

    public GeneComponentModelLsStrSub<String,StringList> getConstTypes() {
        return constTypes;
    }
}
