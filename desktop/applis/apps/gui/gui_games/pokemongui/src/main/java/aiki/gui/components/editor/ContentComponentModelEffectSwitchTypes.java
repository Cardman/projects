package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import aiki.map.levels.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelEffectSwitchTypes {

    private CrudGeneFormSimpleFormSub<EnvironmentType, String> chgtTypeByEnv;
    private GeneComponentModelElt<String> constValuesType;
    private GeneComponentModelElt<String> exchangeTypes;
    private GeneComponentModelLsStrSub<String> constTypes;
    private GeneComponentModelLsStrSub<String> addedTypes;

    private AbsPanel form;
    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        constTypes = ConverterCommonMapUtil.buildTypeList(_core,_fac,_fact);
        selected_.add(constTypes.geneEnum());
        addedTypes = ConverterCommonMapUtil.buildTypeList(_core,_fac,_fact);
        selected_.add(addedTypes.geneEnum());
        exchangeTypes = new GeneComponentModelElt<String>(_core, MessagesPkEditor.getMessagesEditorSelectExchangeTypeTr(MessagesPkEditor.getAppliTr(_core.currentLg())).getMapping(),new EmptyDefValue());
        selected_.add(exchangeTypes.geneEnum());
        exchangeTypes.setupValue(DataBase.DEF_EXCHANGE_TYPE_NOTHING);
        constValuesType = new GeneComponentModelElt<String>(_core, MessagesPkEditor.getMessagesEditorSelectConstValuesTypeTr(MessagesPkEditor.getAppliTr(_core.currentLg())).getMapping(),new EmptyDefValue());
        selected_.add(constValuesType.geneEnum());
        constValuesType.setupValue(DataBase.DEF_CONST_VALUES_TYPE_NOTHING);
        chgtTypeByEnv = new CrudGeneFormSimpleFormSub<EnvironmentType, String>(_core, _fac, _fact, _f);
        chgtTypeByEnv.initFormWithVal(new DisplayEntryCustSubElementImpl<EnvironmentType, String>(_fact.getFactoryEnvironmentType(),_core,_fac, new IdMap<EnvironmentType, String>()),new GeneComponentModelSubscribeFactorySelEltEnum<EnvironmentType>(_core,_fact.getFactoryEnvironmentType(),_fac), buildPart(_core,_fac,_fact.getFactoryTy(),new StringMap<String>()));
        selected_.add(chgtTypeByEnv.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void buildEntity(EffectSwitchTypes _edited) {
        _edited.setAddedTypes(new StringList(addedTypes.tryRet()));
        _edited.setConstTypes(new StringList(constTypes.tryRet()));
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

    public GeneComponentModelLsStrSub<String> getAddedTypes() {
        return addedTypes;
    }

    public GeneComponentModelLsStrSub<String> getConstTypes() {
        return constTypes;
    }
}
