package code.expressionlanguage;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.PlainButton;

public final class PlainButtonStruct extends CustComponentStruct {
    private PlainButton plainButton;
    public PlainButtonStruct(String _className) {
        super(_className);
        plainButton = new PlainButton();
    }
    public PlainButtonStruct(Struct _txt,String _className) {
        super(_className);
        plainButton = new PlainButton();
        setText(_txt);
    }

    public Struct getText() {
        String txt_ = plainButton.getText();
        if (txt_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(txt_);
    }

    public void setText(Struct text) {
        if (!(text instanceof StringStruct)) {
            plainButton.setText(null);
        } else {
            plainButton.setText(((StringStruct)text).getInstance());
        }
    }

    public Struct isEnabled() {
        return new BooleanStruct(plainButton.isEnabled());
    }
    public void setEnabled(Struct b) {
        plainButton.setEnabled(((BooleanStruct)b).getInstance());
    }
    public void addActionListener(Struct _list) {
        if (_list instanceof EventStruct) {
            plainButton.addActionListener((EventStruct)_list);
        }
    }
    @Override
    protected CustComponent getComponent() {
        return plainButton;
    }
}
