package code.formathtml.render;

import code.formathtml.util.BeanLgNames;

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
            return BeanLgNames.ON;
        }
        return BeanLgNames.OFF;
    }

    public void setChecked(boolean _checked) {
        checked = _checked;
    }
}
