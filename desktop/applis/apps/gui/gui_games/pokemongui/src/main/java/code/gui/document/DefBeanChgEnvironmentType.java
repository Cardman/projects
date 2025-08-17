package code.gui.document;

import aiki.beans.*;
import aiki.map.levels.enums.*;
import code.gui.*;

public final class DefBeanChgEnvironmentType extends BeanChgEnvironmentType {
    private final GeneComponentModelElt<EnvironmentType> spinner;
    public DefBeanChgEnvironmentType(GeneComponentModelElt<EnvironmentType> _c) {
        spinner = _c;
    }
    @Override
    public EnvironmentType valueEt() {
        return spinner.tryRet();
    }

    @Override
    public void valueEt(EnvironmentType _v) {
        spinner.setupValue(_v);
    }

}
