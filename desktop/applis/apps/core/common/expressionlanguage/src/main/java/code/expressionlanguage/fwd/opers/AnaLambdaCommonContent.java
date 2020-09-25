package code.expressionlanguage.fwd.opers;

public final class AnaLambdaCommonContent {

    private boolean intermediate;
    private boolean safeInstance;
    private String returnFieldType;
    private String fileName;
    private boolean shiftArgument;
    private int ancestor;
    private String foundClass;

    public boolean isIntermediate() {
        return intermediate;
    }

    public void setIntermediate(boolean intermediate) {
        this.intermediate = intermediate;
    }

    public boolean isSafeInstance() {
        return safeInstance;
    }

    public void setSafeInstance(boolean safeInstance) {
        this.safeInstance = safeInstance;
    }

    public String getReturnFieldType() {
        return returnFieldType;
    }

    public void setReturnFieldType(String returnFieldType) {
        this.returnFieldType = returnFieldType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isShiftArgument() {
        return shiftArgument;
    }

    public void setShiftArgument(boolean shiftArgument) {
        this.shiftArgument = shiftArgument;
    }

    public int getAncestor() {
        return ancestor;
    }

    public void setAncestor(int ancestor) {
        this.ancestor = ancestor;
    }

    public String getFoundClass() {
        return foundClass;
    }

    public void setFoundClass(String foundClass) {
        this.foundClass = foundClass;
    }
}
