package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.maths.LgInt;
import code.maths.montecarlo.AbMonteCarlo;
import code.util.CustList;
import code.util.ints.Comparing;

public final class CrudGeneFormMonteCarlo<E> extends AbsCrudGeneFormList<EditedCrudPair<E, LgInt>> {
    public CrudGeneFormMonteCarlo(AbstractProgramInfos _fact, Comparing<EditedCrudPair<E, LgInt>> _c) {
        super(_fact, _c);
    }
    public void initForm(DisplayEntryCust<Integer, EditedCrudPair<E, LgInt>> _disp, GeneComponentModel<EditedCrudPair<E, LgInt>> _k, AbMonteCarlo<E> _map, Comparing<EditedCrudPair<E, LgInt>> _c) {
        CustList<EditedCrudPair<E, LgInt>> ls_ = new MapToEntriesListUtil<E, LgInt>().build(_map);
        initForm(_disp,_k,ls_,_c,new ValidateElementPair<E, LgInt>(_c));
    }
}
