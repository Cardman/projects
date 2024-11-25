package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeStatisticByte implements AbsGeneComponentModelSubscribe<IdMap<Statistic,Byte>> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private CrudGeneFormSimpleFormSub<Statistic,Byte> crud;
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbsCommonFrame frame;

    public GeneComponentModelSubscribeStatisticByte(AbstractProgramInfos _core, FacadeGame _f, SubscribedTranslationList _subscription, AbsCommonFrame _fr) {
        programInfos = _core;
        facade = _f;
        subscribedTranslationList = _subscription;
        frame = _fr;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        crud = new CrudGeneFormSimpleFormSub<Statistic, Byte>(programInfos,facade,subscribedTranslationList,frame);
        crud.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Byte>(subscribedTranslationList.getFactoryStat(),programInfos,facade, new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(programInfos, subscribedTranslationList.getFactoryStat(), facade), new GeneComponentModelSubscribeFactoryDirect<Byte>(new GeneComponentModelSubscribeByte(programInfos)));
        form_.add(crud.getGroup());
        return form_;
    }


    @Override
    public IdMap<Statistic,Byte> tryRet() {
        return ConverterCommonMapUtil.buildIdMapStatisticByte(crud.getList());
    }

    @Override
    public void setupValue(IdMap<Statistic,Byte> _value) {
        crud.setupValues(new MapToEntriesListUtil<Statistic,Byte>().build(_value));
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(crud.subscribeButtons());
        return ids_;
    }

}
