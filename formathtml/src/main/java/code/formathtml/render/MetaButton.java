package code.formathtml.render;

import code.sml.Element;

public final class MetaButton extends MetaInput {

    private final Element form;

    private final String value;

    public MetaButton(MetaContainer _parent, int _group, Element _form, String _value) {
        super(_parent, _group, getNameOrEmpty(_form));
        form = _form;
        value = _value;
    }

    private static String getNameOrEmpty(Element _form) {
        if (_form == null) {
            return "";
        }
        return _form.getAttribute("name");
    }

    public Element getForm() {
        return form;
    }
    public String getValue() {
        return value;
    }
}
