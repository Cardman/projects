package code.formathtml.render;

public final class MetaSearchableContent {
    private final String text;

    private final int formGroup;
    private final int partGroup;

    private final int rowGroup;

    public MetaSearchableContent(String _t, int _p, int _r) {
        this(_t,0,_p,_r);
    }

    public MetaSearchableContent(String _t, int _f, int _p, int _r) {
        this.text = _t;
        this.formGroup = _f;
        this.partGroup = _p;
        this.rowGroup = _r;
    }

    public String getText() {
        return text;
    }

    public int getFormGroup() {
        return formGroup;
    }

    public int getPartGroup() {
        return partGroup;
    }

    public int getRowGroup() {
        return rowGroup;
    }
}
