package code.expressionlanguage.linkage;

public final class VariablesOffsets {
    private String currentFileName = "";
    private boolean possibleDeclareLoopVars;

    public boolean isPossibleDeclareLoopVars() {
        return possibleDeclareLoopVars;
    }

    public void setPossibleDeclareLoopVars(boolean _possibleDeclareLoopVars) {
        possibleDeclareLoopVars = _possibleDeclareLoopVars;
    }

    public String getCurrentFileName() {
        return currentFileName;
    }

    public void setCurrentFileName(String _currentFileName) {
        currentFileName = _currentFileName;
    }

}
