package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.TextLabel;

public final class TextLabelStruct extends CustComponentStruct {
    private TextLabel textLabel;
    protected TextLabelStruct(String _className) {
        super(_className);
        textLabel = new TextLabel("");
    }
    protected TextLabelStruct(Struct _txt,String _className) {
        super(_className);
        textLabel = new TextLabel("");
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
    protected CustComponent getComponent() {
        return textLabel;
    }
}
