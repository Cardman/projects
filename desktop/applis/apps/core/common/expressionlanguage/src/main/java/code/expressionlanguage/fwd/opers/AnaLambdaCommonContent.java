package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;

public final class AnaLambdaCommonContent {

    private boolean intermediate;
    private boolean safeInstance;
    private String returnFieldType = "";
    private String fileName = "";
    private boolean shiftArgument;
    private int ancestor;
    private AnaFormattedRootBlock foundFormatted;
    private String result = "";

    public boolean isIntermediate() {
        return intermediate;
    }

    public void setIntermediate(boolean _intermediate) {
        this.intermediate = _intermediate;
    }

    public boolean isSafeInstance() {
        return safeInstance;
    }

    public void setSafeInstance(boolean _safeInstance) {
        this.safeInstance = _safeInstance;
    }

    public String getReturnFieldType() {
        return returnFieldType;
    }

    public void setReturnFieldType(String _returnFieldType) {
        this.returnFieldType = _returnFieldType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        this.fileName = _fileName;
    }

    public boolean isShiftArgument() {
        return shiftArgument;
    }

    public void setShiftArgument(boolean _shiftArgument) {
        this.shiftArgument = _shiftArgument;
    }

    public int getAncestor() {
        return ancestor;
    }

    public void setAncestor(int _ancestor) {
        this.ancestor = _ancestor;
    }

    public AnaFormattedRootBlock getFoundFormatted() {
        return foundFormatted;
    }

    public void setFoundFormatted(AnaFormattedRootBlock _foundFormatted) {
        this.foundFormatted = _foundFormatted;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String _result) {
        this.result = _result;
    }
}
