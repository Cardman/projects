package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsSeparator;
import code.gui.initialize.AbsCompoFactory;

public final class SeparatorStruct extends CustComponentStruct {

    private final AbsSeparator absSeparator;

    public SeparatorStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        absSeparator = _compo.newSep();
    }

    public Struct orientation() {
        return new IntStruct(absSeparator.orientation());
    }

    public void orientation(Struct _o) {
        absSeparator.orientation(((NumberStruct)_o).intStruct());
    }
    @Override
    protected AbsCustComponent getComponent() {
        return absSeparator;
    }
}
