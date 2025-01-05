package code.expressionlanguage.guicompos;

import code.gui.*;
import code.gui.initialize.*;

public final class TextPaneStruct extends TxtComponentStruct {
    private final AbsTextPane textPane;
    public TextPaneStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        textPane = _compo.newTextPane();
    }

    @Override
    protected AbsTxtComponent component() {
        return textPane;
    }
}
