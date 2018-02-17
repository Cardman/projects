package code.formathtml.render;

import code.sml.Element;

public final class MetaButton extends MetaInput {

    private final Element form;

    private final String value;

    public MetaButton(MetaContainer _parent, int _group, Element _form, String _value) {
        super(_parent, _group);
        form = _form;
        value = _value;
    }

    public Element getForm() {
        return form;
    }
    public String getValue() {
        return value;
    }
}
