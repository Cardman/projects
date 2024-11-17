package code.gui;

import code.gui.initialize.*;
import code.maths.*;
import code.util.ints.*;

public final class CrudGeneFormMonteCarlo<E> extends AbsCrudGeneFormList<EditedCrudPair<E, LgInt>> {
    public CrudGeneFormMonteCarlo(AbstractProgramInfos _fact, Comparing<EditedCrudPair<E, LgInt>> _c) {
        super(_fact, _c);
    }

    public void initFormKeys(DisplayEntryCust<Integer, EditedCrudPair<E, LgInt>> _disp, GeneComponentModel<EditedCrudPair<E, LgInt>> _k, Comparing<EditedCrudPair<E, LgInt>> _c) {
        initForm(_disp,_k, _c,new ValidateElementPair<E, LgInt>(_c));
    }
}
