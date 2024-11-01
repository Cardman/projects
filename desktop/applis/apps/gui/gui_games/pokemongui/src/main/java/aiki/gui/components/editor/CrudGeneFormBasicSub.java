package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;

public abstract class CrudGeneFormBasicSub<K,V> extends CrudGeneFormSub<K, V> {

    protected CrudGeneFormBasicSub(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_fact, _facade, _sub);

    }
    @Override
    protected void afterModif(int _index, K _key, V _value) {
        if (_index > -1) {
            getList().remove(_index);
            afterChange();
            return;
        }
        afterChange();
    }

}
