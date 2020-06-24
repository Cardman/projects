package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.common.GeneField;

public final class ImportedField {
    private int imported;
    private Accessed geneField;
    private String type;
    private boolean finalField;

    public ImportedField(int imported, Accessed geneField, String type, boolean finalField) {
        this.imported = imported;
        this.geneField = geneField;
        this.type = type;
        this.finalField = finalField;
    }

    public int getImported() {
        return imported;
    }

    public Accessed getReturnType() {
        return geneField;
    }

    public String getType() {
        return type;
    }

    public boolean isFinalField() {
        return finalField;
    }
}
