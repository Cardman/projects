package code.formathtml.render;

public final class MetaPointLabel extends MetaLabel {

    private final MetaPointForm form;

    public MetaPointLabel(MetaContainer _parent, MetaPointForm _form) {
        super(_parent);
        form = _form;
    }
    public MetaPointForm getForm() {
        return form;
    }
}
