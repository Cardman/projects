package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.IdList;
import code.util.ints.*;

public abstract class CrudGeneFormListSub<E> extends AbsCrudGeneFormList<E> {
    private final CrudGeneFormSubContent crudGeneFormSubContent;

    protected CrudGeneFormListSub(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr, Comparing<E> _c) {
        super(_fact, _c);
        setFrame(_fr);
        crudGeneFormSubContent = new CrudGeneFormSubContent(_facade, _sub, this, _fr);
    }

    public SubscribedTranslationList subscription() {
        return getCrudGeneFormSubContent().getSubscription();
    }
    public CrudGeneFormSubContent getCrudGeneFormSubContent() {
        return crudGeneFormSubContent;
    }

    protected abstract IdList<SubscribedTranslation> all();

    @Override
    protected void afterModif(int _index, E _value) {
        if (_index > -1) {
            getList().remove(_index);
        }
        afterChange();
    }

    protected void afterChange() {
        getCrudGeneFormSubContent().afterChange();
    }

    @Override
    public void selectOrAdd() {
        getCrudGeneFormSubContent().selectOrAdd(all());
        super.selectOrAdd();
    }
    @Override
    public void cancel() {
        getCrudGeneFormSubContent().removeOpenSub();
        super.cancel();
    }

}
