package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.CustList;
import code.util.IdList;

public final class CrudGeneFormMonteCarloStrSub extends AbsCrudGeneFormMonteCarloSub<String> {
    private final AbstractProgramInfos api;
    private final CrudGeneFormSimpleElementSub<EditedCrudPair<String, LgInt>> crud;

    public CrudGeneFormMonteCarloStrSub(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _f) {
        api = _core;
        crud = new CrudGeneFormSimpleElementSub<EditedCrudPair<String, LgInt>>(_core, _fac, _fact, _f);
    }

    public void initFormKeys() {
        DisplayEntryCustSubElementEventString dis_ = new DisplayEntryCustSubElementEventString(crud);
        display(dis_);
        crud.initForm(dis_,new GeneComponentModelSubscribeFactoryStringLgInt(api));
    }

    @Override
    protected IdList<SubscribedTranslation> geneLaw() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationRenamingIdCrud<LgInt>(crud));
        ids_.add(new SubscribedTranslationRenamingMidCrud<LgInt>(crud));
        ids_.add(new SubscribedTranslationRenamingPrefCrud<LgInt>(crud));
        return ids_;
    }
    public AbsButton getAdd() {
        return crud.getCrud().getAdd();
    }
    public AbsButton getCancel() {
        return crud.getCrud().getCancel();
    }
    public AbsButton getValidAddEdit() {
        return crud.getCrud().getValidAddEdit();
    }
    public CustList<AbsButton> getAllButtons() {
        return crud.getCrud().getAllButtons();
    }

    public void setupValues(CustList<EditedCrudPair<String,LgInt>> _value) {
        crud.getCrud().setupValues(_value);
    }

    public void setupValue(EditedCrudPair<String,LgInt> _value) {
        crud.getCrud().getGenePair().value(_value);
    }
    public CrudGeneFormSimpleElementSub<EditedCrudPair<String, LgInt>> getCrud() {
        return crud;
    }

}
