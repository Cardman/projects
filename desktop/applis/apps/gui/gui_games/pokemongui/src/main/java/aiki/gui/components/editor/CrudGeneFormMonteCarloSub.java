package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.LgInt;
import code.util.CustList;
import code.util.ints.Comparing;

public final class CrudGeneFormMonteCarloSub<E> {
    private final CrudGeneFormMonteCarlo<E> law;
    private final GeneComponentModelEventEnum<E> compo;

    public CrudGeneFormMonteCarloSub(AbsCommonFrame _f, AbstractProgramInfos _core, Comparing<EditedCrudPair<E, LgInt>> _cmp, GeneComponentModelEltEnumSub<E> _sub, DisplayEntryCust<Integer, EditedCrudPair<E, LgInt>> _disp) {
        this.law = new CrudGeneFormMonteCarlo<E>(_core, _cmp);
        law.setFrame(_f);
        law.initForm();
        GeneComponentModelEventEnum<E> compo_ = new GeneComponentModelEventEnum<E>(_core, _sub);
        law.initFormKeys(_disp, compo_, _cmp);
        this.compo = compo_;
    }

    public AbsPanel getGroup() {
        return getLaw().getGroup();
    }

    public CustList<EditedCrudPair<E, LgInt>> getList() {
        return getLaw().getList();
    }
    public void setupValues(CustList<EditedCrudPair<E, LgInt>> _ls) {
        getLaw().setupValues(_ls);
    }
    public CrudGeneFormMonteCarlo<E> getLaw() {
        return law;
    }

    public GeneComponentModelEventEnum<E> getCompo() {
        return compo;
    }

}
