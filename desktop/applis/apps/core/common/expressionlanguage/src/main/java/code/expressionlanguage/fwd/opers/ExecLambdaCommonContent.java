package code.expressionlanguage.fwd.opers;

public final class ExecLambdaCommonContent {

    private final boolean intermediate;
    private final boolean safeInstance;
    private final String returnFieldType;
    private final String fileName;
    private final boolean shiftArgument;
    private final int ancestor;
    private final String foundClass;
    public ExecLambdaCommonContent(AnaLambdaCommonContent _cont) {
        intermediate = _cont.isIntermediate();
        safeInstance = _cont.isSafeInstance();
        returnFieldType = _cont.getReturnFieldType();
        fileName = _cont.getFileName();
        shiftArgument = _cont.isShiftArgument();
        ancestor = _cont.getAncestor();
        foundClass = _cont.getFoundClass();
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public boolean isShiftArgument() {
        return shiftArgument;
    }

    public boolean isSafeInstance() {
        return safeInstance;
    }

    public String getReturnFieldType() {
        return returnFieldType;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFoundClass() {
        return foundClass;
    }

    public int getAncestor() {
        return ancestor;
    }
}
