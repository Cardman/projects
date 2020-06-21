package code.expressionlanguage.analyze;

import code.expressionlanguage.common.GeneField;

public final class ImportedField {
    private int imported;
    private GeneField geneField;
    private String type;
    private boolean finalField;

    public ImportedField(int imported, GeneField geneField, String type, boolean finalField) {
        this.imported = imported;
        this.geneField = geneField;
        this.type = type;
        this.finalField = finalField;
    }

    public int getImported() {
        return imported;
    }

    public GeneField getReturnType() {
        return geneField;
    }

    public String getType() {
        return type;
    }

    public boolean isFinalField() {
        return finalField;
    }
}
