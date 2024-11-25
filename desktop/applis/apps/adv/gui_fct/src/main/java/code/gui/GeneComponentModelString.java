package code.gui;

import code.gui.events.AfterValidateText;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class GeneComponentModelString {
    private final AbstractProgramInfos compoFactory;
    private final StringList dico;
    private final AfterValidateText validateText;
    private AbsTextField textField;

    public GeneComponentModelString(AbstractProgramInfos _c, StringList _aDictionary, AfterValidateText _after) {
        this.compoFactory = _c;
        this.dico = _aDictionary;
        this.validateText = _after;
    }

    public AbsTextField geneString() {
        textField = compoFactory.getCompoFactory().newTextField();
        textField.addAutoComplete(new AutoCompleteDocument(textField,dico,compoFactory,validateText));
        return textField;
    }

    public String value() {
        return valueString();
    }

    public String valueString() {
        return textField.getText();
    }

    public void value(String _v) {
        valueString(_v);
    }

    public String valueString(String _v) {
        String p_ = textField.getText();
        textField.setText(_v);
        return p_;
    }

    public AbsTextField getTextField() {
        return textField;
    }
}
