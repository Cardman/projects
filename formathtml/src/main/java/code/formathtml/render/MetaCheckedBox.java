package code.formathtml.render;

public final class MetaCheckedBox extends MetaInput implements IntCheckBox {

    private final long formNb;
    private boolean checked;

    public MetaCheckedBox(MetaContainer _parent, int _group, boolean _checked, long _formNb) {
        super(_parent, _group);
        checked = _checked;
        formNb = _formNb;
    }

    public boolean isChecked() {
        return checked;
    }

    @Override
    public long getFormNb() {
        return formNb;
    }

    @Override
    public String getValue() {
        if (checked) {
            return "on";
        }
        return "off";
    }

}
