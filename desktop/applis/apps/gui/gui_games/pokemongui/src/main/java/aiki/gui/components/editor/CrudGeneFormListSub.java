package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.IdList;
import code.util.ints.*;

public abstract class CrudGeneFormListSub<E> extends AbsCrudGeneFormList<E> {
    private final CrudGeneFormSubContent crudGeneFormSubContent;

    protected CrudGeneFormListSub(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, Comparing<E> _c) {
        super(_fact, _c);
        crudGeneFormSubContent = new CrudGeneFormSubContent(_facade, _sub);
    }

    public CrudGeneFormSubContent getCrudGeneFormSubContent() {
        return crudGeneFormSubContent;
    }

    protected abstract IdList<SubscribedTranslation> all();

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

    protected abstract void afterChange();

    protected void subscribeAll() {
        getCrudGeneFormSubContent().addAllSub(subscribe());
    }
    protected abstract IdList<SubscribedTranslation> subscribe();
}
