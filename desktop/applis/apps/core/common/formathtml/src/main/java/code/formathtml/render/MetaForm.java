package code.formathtml.render;

public final class MetaForm extends MetaContainer implements IntForm {

    private long number;
    public MetaForm(MetaContainer _parent, long _number) {
        super(_parent, MetaLayout.BOX);
        number = _number;
    }

    @Override
    public long getNumber() {
        return number;
    }
}
