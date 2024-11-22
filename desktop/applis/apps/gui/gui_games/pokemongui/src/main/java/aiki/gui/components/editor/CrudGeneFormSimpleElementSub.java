package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormSimpleElementSub<K> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbsCommonFrame commonFrame;
    private CrudGeneFormSimpleElement<K> crud;

    public CrudGeneFormSimpleElementSub(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        this.programInfos = _core;
        this.facadeGame = _fac;
        this.subscribedTranslationList = _fact;
        this.commonFrame = _f;
    }

    public void initForm(DisplayEntryCustSubElement<K> _d, AbsGeneComponentModelSubscribeFactory<K> _k) {
        crud = new CrudGeneFormSimpleElement<K>(programInfos, facadeGame, subscribedTranslationList, commonFrame);
        crud.initForm();
        crud.initForm(_d, programInfos, _k);
    }

    public AbsPanel getGroup() {
        return getCrud().getGroup();
    }

    public CustList<K> getList() {
        return getCrud().getList();
    }

    public void setupValues(CustList<K> _v) {
        getCrud().setupValues(_v);
    }
    public CrudGeneFormSubContent<K> getCrudGeneFormSubContent() {
        return getCrud().getCrudGeneFormSubContent();
    }
    public IdList<SubscribedTranslation> subscribeButtons() {
        return getCrud().subscribeButtons();
    }
    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public CrudGeneFormSimpleElement<K> getCrud() {
        return crud;
    }


    public AbstractProgramInfos getFactory() {
        return programInfos;
    }
}
