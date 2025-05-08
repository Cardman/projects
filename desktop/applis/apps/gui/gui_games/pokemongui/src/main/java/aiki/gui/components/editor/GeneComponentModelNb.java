package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class GeneComponentModelNb implements GeneComponentModel<EditedCrudPair<Integer, String>> {
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbstractProgramInfos compoFactory;
    private final FacadeGame facade;
    private final GeneComponentModelInt key;
    private GeneComponentModelEltEnumSub<String> value;
    public GeneComponentModelNb(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        compoFactory = _core;
        facade = _facade;
        subscribedTranslationList = _sub;
        key = new GeneComponentModelInt(_core);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        value = ConverterCommonMapUtil.buildMvFull(compoFactory, facade, subscribedTranslationList);
        AbsCompoFactory compoFactory_ = compoFactory.getCompoFactory();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(SubscribedTranslationList.line(compoFactory,MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_TM_HM_NUMBER,key.geneInt()));
        page_.add(SubscribedTranslationList.line(compoFactory,MessagesPkBean.GENERAL,MessagesDataGeneral.M_P_14_HELP_TM_HM_MOVE,value.geneEnum()));
        return page_;
    }

    @Override
    public EditedCrudPair<Integer, String> value() {
        return new EditedCrudPair<Integer, String>(key.valueInt(), value.tryRet());
    }

    @Override
    public void value(EditedCrudPair<Integer, String> _v) {
        key.valueInt(_v.getKey());
        key.getSpinner().setEnabled(false);
        value.setupValue(_v.getValue());
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(value.getSubs());
        return ids_;
    }

    public GeneComponentModelInt getKey() {
        return key;
    }

    public GeneComponentModelEltEnumSub<String> getValue() {
        return value;
    }
}
