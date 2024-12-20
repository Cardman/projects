package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeWildPk implements AbsGeneComponentModelSubscribe<WildPk> {
    private final FormWildPk formWildPk;
    public GeneComponentModelSubscribeWildPk(AbstractProgramInfos _ed, FacadeGame _facade, SubscribedTranslationList _sub) {
        formWildPk = new FormWildPk(_ed, _facade, _sub);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        formWildPk.feedForm();
        return formWildPk.getForm();
    }

    @Override
    public WildPk tryRet() {
        return formWildPk.buildEntity();
    }

    @Override
    public void setupValue(WildPk _value) {
        formWildPk.feedForm(_value);
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> subs_ = new IdList<SubscribedTranslation>();
        formWildPk.feedSubs(subs_);
        return subs_;
    }

    public FormWildPk getFormWildPk() {
        return formWildPk;
    }
}
