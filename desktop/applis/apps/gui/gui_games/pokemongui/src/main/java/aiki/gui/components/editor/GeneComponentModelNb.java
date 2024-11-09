package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelNb implements GeneComponentModel<EditedCrudPair<Integer, String>> {
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbstractProgramInfos compoFactory;
    private final FacadeGame facade;
    private final GeneComponentModelInt key;
    private GeneComponentModelEltStrSub value;
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
        page_.add(key.geneInt());
        page_.add(value.geneEnum());
        return page_;
    }

    @Override
    public EditedCrudPair<Integer, String> value() {
        return new EditedCrudPair<Integer, String>(key.valueInt(), value.tryRet(DataBase.EMPTY_STRING));
    }

    @Override
    public void value(EditedCrudPair<Integer, String> _v) {
        key.valueInt(_v.getKey());
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

    public GeneComponentModelEltStrSub getValue() {
        return value;
    }
}
