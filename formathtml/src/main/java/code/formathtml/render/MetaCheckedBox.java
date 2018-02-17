package code.formathtml.render;

public final class MetaCheckedBox extends MetaInput {

    private final boolean checked;

    public MetaCheckedBox(MetaContainer _parent, int _group, boolean _checked) {
        super(_parent, _group);
        checked = _checked;
    }

    public boolean isChecked() {
        return checked;
    }

}
