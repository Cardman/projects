package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.OperatorBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.util.CustList;
import code.util.StringList;

public final class VariablesOffsets {
    private String currentFileName = "";
    private boolean possibleDeclareLoopVars;
    private CustList<RootBlock> refFoundTypes;
    private CustList<OperatorBlock> refOperators;

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

    public CustList<RootBlock> getRefFoundTypes() {
        return refFoundTypes;
    }

    public static RootBlock getClassBody(String _type, CustList<RootBlock> _refFoundTypes) {
        for (RootBlock r: _refFoundTypes) {
            if (StringList.quickEq(r.getFullName(),_type)) {
                return r;
            }
        }
        return null;
    }

    public void setRefFoundTypes(CustList<RootBlock> _refFoundTypes) {
        refFoundTypes = _refFoundTypes;
    }

    public CustList<OperatorBlock> getRefOperators() {
        return refOperators;
    }

    public void setRefOperators(CustList<OperatorBlock> _refOperators) {
        refOperators = _refOperators;
    }
}
