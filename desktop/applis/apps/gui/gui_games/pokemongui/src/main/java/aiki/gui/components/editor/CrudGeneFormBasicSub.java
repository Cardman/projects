package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.AbsCommonFrame;
import code.gui.EditedCrudPair;
import code.gui.initialize.*;

public abstract class CrudGeneFormBasicSub<K,V> extends CrudGeneFormSub<K, V> {

    protected CrudGeneFormBasicSub(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr);

    }
    @Override
    protected void afterModif(int _index, EditedCrudPair<K, V> _value) {
        if (_index > -1) {
            getList().remove(_index);
            afterChange();
            return;
        }
        afterChange();
    }

}
