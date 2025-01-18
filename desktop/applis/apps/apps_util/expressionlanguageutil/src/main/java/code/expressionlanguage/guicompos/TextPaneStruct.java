package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.initialize.*;

public final class TextPaneStruct extends TxtComponentStruct {
    private final AbsTextPane textPane;
    public TextPaneStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        textPane = _compo.newTextPane();
    }

    public void setCharacterAttributes(Struct _begin, Struct _length, Struct _attrs, Struct _replace) {
        if (_attrs instanceof AttrSetStruct) {
            textPane.setCharacterAttributes(((NumberStruct)_begin).intStruct(),((NumberStruct)_length).intStruct(),((AttrSetStruct)_attrs).getAttrSet(),BooleanStruct.isTrue(_replace));
        }
    }
    @Override
    protected AbsTxtComponent component() {
        return textPane;
    }
}
