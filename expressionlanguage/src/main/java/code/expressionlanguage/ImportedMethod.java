package code.expressionlanguage;

public final class ImportedMethod {
    private int imported;
    private String returnType;

    public ImportedMethod(int imported, String returnType) {
        this.imported = imported;
        this.returnType = returnType;
    }

    public int getImported() {
        return imported;
    }

    public String getReturnType() {
        return returnType;
    }
}
