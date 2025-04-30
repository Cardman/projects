package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.util.*;

public final class GeneComponentModelSubscribeCategoryMult implements AbsGeneComponentModelSubscribe<CategoryMult> {
    private final AbsGeneComponentModelEffect programInfos;
    private final FacadeGame facade;
    private final String file;
    private final String titleKey;
    private final String titleValue;
    private GeneComponentModelEltEnumSub<String> category;
    private final GeneComponentModelInt mult;
    private final SubscribedTranslationList subscribedTranslationList;

    public GeneComponentModelSubscribeCategoryMult(AbsGeneComponentModelEffect _core, String _f, String _k, String _v) {
        programInfos = _core;
        facade = _core.getFacadeGame();
        subscribedTranslationList = _core.getFactory();
        mult = new GeneComponentModelInt(_core.getProgramInfos());
        file = _f;
        titleKey = _k;
        titleValue = _v;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getProgramInfos().getCompoFactory().newLineBox();
        form_.add(SubscribedTranslationList.line(programInfos.getProgramInfos(), file, titleKey,mult.geneInt()));
        category = ConverterCommonMapUtil.buildCatElt(programInfos.getProgramInfos(), facade,subscribedTranslationList);
        form_.add(SubscribedTranslationList.line(programInfos.getProgramInfos(), file, titleValue,category.geneEnum()));
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
