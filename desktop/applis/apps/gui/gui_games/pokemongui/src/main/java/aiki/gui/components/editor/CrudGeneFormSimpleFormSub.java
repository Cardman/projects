package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.ints.Comparing;

public final class CrudGeneFormSimpleFormSub<K, V> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscribedTranslationList;
    private final AbsCommonFrame commonFrame;
    private CrudGeneFormSimpleForm<K,V> crud;

    public CrudGeneFormSimpleFormSub(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        this.programInfos = _core;
        this.facadeGame = _fac;
        this.subscribedTranslationList = _fact;
        this.commonFrame = _f;
    }

    public void initForm(DisplayEntryCustSub<K> _d, AbsMap<K, String> _disp, AbsGeneComponentModelSubscribeFactory<K> _k, AbsGeneComponentModelSubscribeFactory<V> _v) {
        crud = new CrudGeneFormSimpleForm<K,V>(programInfos, facadeGame, subscribedTranslationList, commonFrame);
        crud.initForm();
        crud.initForm(_d, _disp, programInfos, _k, _v);
    }

    public void initForm(DisplayEntryCust<Integer,EditedCrudPair<K, V>> _d, Comparing<EditedCrudPair<K, V>> _cmp, AbsGeneComponentModelSubscribeFactory<K> _k, AbsGeneComponentModelSubscribeFactory<V> _v) {
        crud = new CrudGeneFormSimpleForm<K,V>(programInfos, facadeGame, subscribedTranslationList, commonFrame);
        crud.initForm();
        crud.initForm(_d, _cmp, programInfos, _k, _v);
    }

    public AbsPanel getGroup() {
        return getCrud().getGroup();
    }

    public CustList<EditedCrudPair<K, V>> getList() {
        return getCrud().getList();
    }

    public IdList<SubscribedTranslation> subscribeButtons() {
        return getCrud().subscribeButtons();
    }
    public void setupValues(CustList<EditedCrudPair<K, V>> _v) {
        getCrud().setupValues(_v);
    }

    public AbsCommonFrame getCommonFrame() {
        return commonFrame;
    }

    public CrudGeneFormSimpleForm<K, V> getCrud() {
        return crud;
    }

}
