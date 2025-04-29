package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeStatisticByte implements AbsGeneComponentModelSubscribe<IdMap<Statistic,Long>> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private CrudGeneFormSimpleFormSub<Statistic,Long> crud;
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbsCommonFrame frame;
    private final String file;
    private final String titleKey;
    private final String titleValue;

    public GeneComponentModelSubscribeStatisticByte(SubscribedTranslationList _subscription, AbsCommonFrame _fr, String _file, String _k,String _v) {
        programInfos = _subscription.getProgramInfos();
        facade = _subscription.getFacadeGame();
        subscribedTranslationList = _subscription;
        frame = _fr;
        file =_file;
        titleKey =_k;
        titleValue =_v;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        crud = new CrudGeneFormSimpleFormSub<Statistic, Long>(programInfos,facade,subscribedTranslationList,frame);
        crud.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Long>(subscribedTranslationList.getFactoryStat(),programInfos,facade, new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(programInfos, subscribedTranslationList.getFactoryStat(), facade), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(programInfos)),file,titleKey,titleValue);
        form_.add(crud.getGroup());
        return form_;
    }


    @Override
    public IdMap<Statistic,Long> tryRet() {
        return ConverterCommonMapUtil.buildIdMapStatisticInteger(crud.getList());
    }

    @Override
    public void setupValue(IdMap<Statistic,Long> _value) {
        crud.setupValues(new MapToEntriesListUtil<Statistic,Long>().build(_value));
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(crud.subscribeButtons());
        return ids_;
    }

}
