package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeCategoryMult implements AbsGeneComponentModelSubscribe<CategoryMult> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private GeneComponentModelEltEnumSub<String> category;
    private final GeneComponentModelInt mult;
    private final SubscribedTranslationList subscribedTranslationList;

    public GeneComponentModelSubscribeCategoryMult(AbstractProgramInfos _core, FacadeGame _f, SubscribedTranslationList _subscription) {
        programInfos = _core;
        facade = _f;
        subscribedTranslationList = _subscription;
        mult = new GeneComponentModelInt(_core);
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        form_.add(mult.geneInt());
        category = ConverterCommonMapUtil.buildCatElt(programInfos, facade,subscribedTranslationList);
        form_.add(category.geneEnum());
        if (GeneComponentModelEltStrCom.disable(_select, _value)) {
            mult.getSpinner().setEnabled(false);
            category.getSelectUniq().getSelect().setEnabled(false);
        }
        return form_;
    }


    @Override
    public CategoryMult tryRet() {
        CategoryMult lv_ = new CategoryMult();
        lv_.setCategory(category.tryRet());
        lv_.setMult(mult.valueInt());
        return lv_;
    }

    @Override
    public void setupValue(CategoryMult _value) {
        category.setupValue(_value.getCategory());
        mult.valueInt(_value.getMult());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(category.getSubs());
        return ids_;
    }

}
