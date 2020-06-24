package code.expressionlanguage.linkage;

import code.util.StringMap;

public final class VariablesOffsets {
    private StringMap<Integer> localVars = new StringMap<Integer>();
    private StringMap<Integer> mutableVars = new StringMap<Integer>();
    private StringMap<Integer> loopVars = new StringMap<Integer>();
    private StringMap<Integer> catchVars = new StringMap<Integer>();
    private StringMap<Integer> paramVars = new StringMap<Integer>();
    private String currentFileName = "";
    private boolean possibleDeclareLoopVars;

    public StringMap<Integer> getLocalVars() {
        return localVars;
    }

    public StringMap<Integer> getMutableVars() {
        return mutableVars;
    }

    public StringMap<Integer> getLoopVars() {
        return loopVars;
    }

    public StringMap<Integer> getCatchVars() {
        return catchVars;
    }

    public StringMap<Integer> getParamVars() {
        return paramVars;
    }

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
