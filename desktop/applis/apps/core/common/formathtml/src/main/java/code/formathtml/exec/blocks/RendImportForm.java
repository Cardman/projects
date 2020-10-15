package code.formathtml.exec.blocks;

public final class RendImportForm extends RendParentBlock {
    private String name;

    public RendImportForm(int _offsetTrim, String _name) {
        super(_offsetTrim);
        this.name = _name;
    }

    public String getName() {
        return name;
    }
}
