package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;
import code.util.ints.*;

public final class CrudGeneFormTypes extends CrudGeneFormListSub<EditedCrudPair<TypesDuo, Rate>> {

    private GeneComponentModelSubscribeTypesDuoRate value;

    public CrudGeneFormTypes(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact,_facade,_sub, _fr, null);
    }
    public void initForm(AbstractProgramInfos _core) {
        initForm();
        getCrudGeneFormSubContent().clearSub();
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        SubscribedTranslationList subscription_ = getCrudGeneFormSubContent().getSubscription();
        value = new GeneComponentModelSubscribeTypesDuoRate(_core, facadeGame_, subscription_);
        DisplayEntryCustSubElementTypesDuo dis_ = new DisplayEntryCustSubElementTypesDuo(_core,facadeGame_,subscription_);
        Comparing<EditedCrudPair<TypesDuo, Rate>> cmp_ = dis_.buildCmp();
        initForm(dis_.buildDisplay(), value, cmp_, new ValidateElementPair<TypesDuo, Rate>(cmp_));
        CustList<EditedCrudPair<TypesDuo, Rate>> types_ = new CustList<EditedCrudPair<TypesDuo, Rate>>();
        for (EntryCust<TypesDuo, Rate> e: facadeGame_.getData().getTableTypes().entryList()) {
            types_.add(new EditedCrudPair<TypesDuo, Rate>(e.getKey(),e.getValue()));
        }
        setupValues(types_);
        getFrame().setContentPane(getGroup());
        getFrame().setVisible(true);
        getFrame().pack();
    }

    @Override
    protected void afterModif(int _index, EditedCrudPair<TypesDuo, Rate> _value) {
        FacadeGame facadeGame_ = getCrudGeneFormSubContent().getFacadeGame();
        if (_index > -1) {
            facadeGame_.getData().getTableTypes().remove(_index);
            getList().remove(_index);
            afterModif();
            return;
        }
        if (getSelectedIndex() < 0) {
            facadeGame_.getData().getTableTypes().addEntry(_value.getKey(), _value.getValue());
            afterModif();
            return;
        }
        facadeGame_.getData().getTableTypes().set(_value.getKey(), _value.getValue());
        afterModif();
    }

    @Override
    protected IdList<SubscribedTranslation> all() {
        return value.all();
    }

    public GeneComponentModelSubscribeTypesDuoRate getValue() {
        return value;
    }
}
