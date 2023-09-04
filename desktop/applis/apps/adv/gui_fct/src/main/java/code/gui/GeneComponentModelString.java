package code.gui;

import code.gui.events.AfterValidateText;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class GeneComponentModelString implements GeneComponentModel<String> {
    private final AbstractProgramInfos compoFactory;
    private final StringList dico;
    private final AfterValidateText validateText;
    private AbsTextField textField;

    public GeneComponentModelString(AbstractProgramInfos _c, StringList _aDictionary, AfterValidateText _after) {
        this.compoFactory = _c;
        this.dico = _aDictionary;
        this.validateText = _after;
    }

    @Override
    public AbsCustComponent gene() {
        textField = compoFactory.getCompoFactory().newTextField();
        textField.addAutoComplete(new AutoCompleteDocument(textField,dico,compoFactory,validateText));
        return textField;
    }

    @Override
    public AbsCustComponent gene(String _d) {
        textField = compoFactory.getCompoFactory().newTextField(_d);
        textField.addAutoComplete(new AutoCompleteDocument(textField,dico,compoFactory,validateText));
        return textField;
    }

    @Override
    public String value() {
        return textField.getText();
    }

    @Override
    public String value(String _v) {
        String p_ = textField.getText();
        textField.setText(_v);
        return p_;
    }
}
