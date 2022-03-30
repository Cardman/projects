package code.formathtml.render;

import code.sml.Element;

public final class MetaForm extends MetaContainer implements IntForm {

    private long number;
    private Element elt;
    public MetaForm(MetaContainer _parent, long _number, Element _elt) {
        super(_parent, MetaLayout.BOX);
        number = _number;
        elt = _elt;
    }

    @Override
    public Element getElt() {
        return elt;
    }

    @Override
    public long getNumber() {
        return number;
    }
}
