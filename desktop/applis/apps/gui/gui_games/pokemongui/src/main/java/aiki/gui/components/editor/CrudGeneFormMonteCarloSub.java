package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class CrudGeneFormMonteCarloSub<E> extends AbsCrudGeneFormMonteCarloSub<E> {
    private GeneComponentModelEventEnum<E> compo;
    private final CrudGeneFormMonteCarlo<E> law;

    public CrudGeneFormMonteCarloSub(AbsCommonFrame _f, AbstractProgramInfos _core) {
        law = new CrudGeneFormMonteCarlo<E>(_f, _core, null);
    }

    public void initFormKeys(GeneComponentModel<EditedCrudPair<E, LgInt>> _sub, DisplayEntryCustSubElement<EditedCrudPair<E, LgInt>> _subsLoc) {
        display(_subsLoc);
        law.initFormKeys(_subsLoc.buildDisplay(), _sub, _subsLoc.buildCmp());
    }
    public void initFormKeys(GeneComponentModelEltEnumSub<E> _sub, DisplayEntryCustSubElement<EditedCrudPair<E, LgInt>> _subsLoc) {
        GeneComponentModelEventEnum<E> compo_ = new GeneComponentModelEventEnum<E>(getLaw().getFactory(), _sub);
        initFormKeys(compo_,_subsLoc);
        this.compo = compo_;
    }

    @Override
    protected IdList<SubscribedTranslation> geneLaw() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationPkKey<EditedCrudPair<E, LgInt>>(law));
        return ids_;
    }

    public GeneComponentModelEventEnum<E> getCompo() {
        return compo;
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
}
