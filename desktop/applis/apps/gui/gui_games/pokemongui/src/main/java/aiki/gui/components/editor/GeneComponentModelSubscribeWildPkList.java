package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeWildPkList implements AbsGeneComponentModelSubscribe<CustList<WildPk>> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbsCommonFrame frame;
    private final CrudGeneFormSimpleElementSub<WildPk> form;
    public GeneComponentModelSubscribeWildPkList(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        form = new CrudGeneFormSimpleElementSub<WildPk>(_core, _fac, _fact, _f);
        programInfos = _core;
        facadeGame = _fac;
        subscribedTranslationList = _fact;
        frame = _f;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        form.initForm(new DisplayEntryCustSubElementEffect<WildPk>(),new GeneComponentModelSubscribeFactoryDirect<WildPk>(new GeneComponentModelSubscribeWildPk(programInfos,facadeGame,subscribedTranslationList,frame)));
        return form.getGroup();
    }

    @Override
    public CustList<WildPk> tryRet() {
        return form.getList();
    }

    @Override
    public void setupValue(CustList<WildPk> _value) {
        form.setupValues(ConverterCommonMapUtil.copyListWildPk(_value));
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

    public CrudGeneFormSimpleElementSub<WildPk> getForm() {
        return form;
    }
}
