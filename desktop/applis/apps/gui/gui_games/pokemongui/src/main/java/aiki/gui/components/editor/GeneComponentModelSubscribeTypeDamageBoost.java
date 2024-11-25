package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeTypeDamageBoost implements AbsGeneComponentModelSubscribe<TypeDamageBoost> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private GeneComponentModelEltEnumSub<String> type;
    private GeneComponentModelRate rate;
    private final SubscribedTranslationList subscribedTranslationList;

    public GeneComponentModelSubscribeTypeDamageBoost(AbstractProgramInfos _core, FacadeGame _f, SubscribedTranslationList _subscription) {
        programInfos = _core;
        facade = _f;
        subscribedTranslationList = _subscription;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        type = ConverterCommonMapUtil.buildTypeElt(programInfos, facade,subscribedTranslationList);
        form_.add(type.geneEnum());
        rate = new GeneComponentModelRate(programInfos);
        form_.add(rate.geneRate());
        return form_;
    }


    @Override
    public TypeDamageBoost tryRet() {
        return new TypeDamageBoost(type.tryRet(), rate.valueRate());
    }

    @Override
    public void setupValue(TypeDamageBoost _value) {
        type.setupValue(_value.getType());
        rate.valueRate(_value.getBoost());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(type.getSubs());
        return ids_;
    }

}
