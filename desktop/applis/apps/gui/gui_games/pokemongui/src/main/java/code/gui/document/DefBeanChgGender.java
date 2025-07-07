package code.gui.document;

import aiki.beans.*;
import aiki.map.pokemon.enums.*;
import code.gui.*;

public final class DefBeanChgGender extends BeanChgGender {
    private final GeneComponentModelElt<Gender> gender;
    public DefBeanChgGender(GeneComponentModelElt<Gender> _ch) {
        gender = _ch;
    }

    @Override
    public Gender valGender() {
        return gender.tryRet();
    }

    @Override
    public void valGender(Gender _v) {
        gender.setupValue(_v);
    }

}
