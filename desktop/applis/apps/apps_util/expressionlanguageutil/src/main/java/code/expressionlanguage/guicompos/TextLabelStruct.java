package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsPlainLabel;
import code.gui.initialize.AbsCompoFactory;

public final class TextLabelStruct extends CustComponentStruct {
    private AbsPlainLabel textLabel;
    public TextLabelStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        textLabel = _compo.newPlainLabel("");
    }
    public TextLabelStruct(Struct _txt,String _className, AbsCompoFactory _compo) {
        super(_className);
        textLabel = _compo.newPlainLabel("");
        setText(_txt);
    }

    public Struct getText() {
        String txt_ = textLabel.getText();
        if (txt_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(txt_);
    }

    public void setText(Struct _text) {
        if (!(_text instanceof StringStruct)) {
            textLabel.setText("");
        } else {
            textLabel.setText(((StringStruct) _text).getInstance());
        }
    }

    @Override
    protected AbsCustComponent getComponent() {
        return textLabel;
    }
}
