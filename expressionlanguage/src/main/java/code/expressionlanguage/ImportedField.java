package code.expressionlanguage;

import code.expressionlanguage.common.GeneField;

public final class ImportedField {
    private int imported;
    private GeneField geneField;

    public ImportedField(int imported, GeneField geneField) {
        this.imported = imported;
        this.geneField = geneField;
    }

    public int getImported() {
        return imported;
    }

    public GeneField getReturnType() {
        return geneField;
    }
}
