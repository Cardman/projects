package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class GeneComponentModelSubscribeCategoryMult implements AbsGeneComponentModelSubscribe<CategoryMult> {
    private final AbsGeneComponentModelEffect programInfos;
    private final FacadeGame facade;
    private GeneComponentModelEltEnumSub<String> category;
    private final GeneComponentModelInt mult;
    private final SubscribedTranslationList subscribedTranslationList;

    public GeneComponentModelSubscribeCategoryMult(AbsGeneComponentModelEffect _core) {
        programInfos = _core;
        facade = _core.getFacadeGame();
        subscribedTranslationList = _core.getFactory();
        mult = new GeneComponentModelInt(_core.getProgramInfos());
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getProgramInfos().getCompoFactory().newLineBox();
        form_.add(line(MessagesDataEffteam.M_P_66_MULT,mult.geneInt()));
        category = ConverterCommonMapUtil.buildCatElt(programInfos.getProgramInfos(), facade,subscribedTranslationList);
        form_.add(line(MessagesDataEffteam.M_P_66_CAT,category.geneEnum()));
        if (GeneComponentModelEltStrCom.disable(_select, _value)) {
            mult.getSpinner().setEnabled(false);
            category.getSelectUniq().getSelect().setEnabled(false);
        }
        return form_;
    }

    private AbsCustComponent line(String _key, AbsCustComponent _input) {
        return programInfos.line(MessagesPkBean.EFF_TEAM, _key,_input);
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
