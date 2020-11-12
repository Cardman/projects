package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.StandardType;

public final class ConstrustorIdVarArg {

    private ConstructorId realId;

    private ConstructorId constId;

    private StandardType standardType;

    private boolean varArgToCall;
    private String fileName;
    private int rootNumber=-1;
    private int memberNumber=-1;
    public ConstructorId getRealId() {
        return realId;
    }

    public void setRealId(ConstructorId _realId) {
        realId = _realId;
    }

    public ConstructorId getConstId() {
        return constId;
    }

    public void setConstId(ConstructorId _constId) {
        constId = _constId;
    }

    public StandardType getStandardType() {
        return standardType;
    }

    public void setStandardType(StandardType _standardType) {
        standardType = _standardType;
    }

    public boolean isVarArgToCall() {
        return varArgToCall;
    }

    public void setVarArgToCall(boolean _varArgToCall) {
        varArgToCall = _varArgToCall;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        this.fileName = _fileName;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int _memberNumber) {
        this.memberNumber = _memberNumber;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int _rootNumber) {
        this.rootNumber = _rootNumber;
    }
}
