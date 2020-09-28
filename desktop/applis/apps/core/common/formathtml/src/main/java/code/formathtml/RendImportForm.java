package code.formathtml;

public final class RendImportForm extends RendParentBlock {
    private String name;

    public RendImportForm(int _offsetTrim, String name) {
        super(_offsetTrim);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
