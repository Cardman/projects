package aiki.gui.components.editor;

import aiki.db.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import code.gui.*;

public final class ContentComponentModelEffectSwitchAbilities {
    private GeneComponentModelElt<String> exchangeAbility;
    private GeneComponentModelEltEnumSub<String> constAbility;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        exchangeAbility = new GeneComponentModelElt<String>(_core.getProgramInfos(), MessagesPkEditor.getMessagesEditorSelectExchangeTypeTr(MessagesPkEditor.getAppliTr(_core.getProgramInfos().currentLg())).getMapping(),new EmptyDefValue());
        selected_.add(exchangeAbility.geneEnum());
        exchangeAbility.setupValue(DataBase.DEF_EXCHANGE_TYPE_NOTHING);
        constAbility = ConverterCommonMapUtil.buildAbFull(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), ConverterCommonMapUtil.defKeyEmpty(" "));
        selected_.add(constAbility.geneEnum());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectSwitchAbilities _edited) {
        _edited.setExchangeAbility(ExchangeType.getExchangeTypeByName(exchangeAbility.tryRet()));
        _edited.setConstAbility(constAbility.tryRet());
    }
    void feedForm(EffectSwitchAbilities _edited) {
        exchangeAbility.setupValue(_edited.getExchangeAbility().getExcType());
        constAbility.setupValue(_edited.getConstAbility());
    }

    public GeneComponentModelElt<String> getExchangeAbility() {
        return exchangeAbility;
    }

    public GeneComponentModelEltEnumSub<String> getConstAbility() {
        return constAbility;
    }
}
