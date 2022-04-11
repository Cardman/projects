package code.expressionlanguage.analyze.files;

import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.BoolVal;

public abstract class ParsedFctHeaderAbs {
    private final Ints offestsTypes;
    private final Ints offestsParams;
    private final CustList<BoolVal> parametersRef;
    private final StringList parametersType;
    private final StringList parametersName;
    private String returnType = "";
    private boolean retRef;
    private int returnOffest;

    protected ParsedFctHeaderAbs() {
        offestsTypes = new Ints();
        offestsParams = new Ints();
        parametersRef = new CustList<BoolVal>();
        parametersType = new StringList();
        parametersName = new StringList();
    }
    protected ParsedFctHeaderAbs(ParsedFctHeader _header) {
        offestsTypes = _header.getOffestsTypes();
        offestsParams = _header.getOffestsParams();
        parametersRef = _header.getParametersRef();
        parametersType = _header.getParametersType();
        parametersName = _header.getParametersName();
        returnType = _header.getReturnType();
        returnOffest = _header.getReturnOffest();
        retRef = _header.isRetRef();
    }

    public CustList<BoolVal> getParametersRef() {
        return parametersRef;
    }

    public int getReturnOffest() {
        return returnOffest;
    }

    public Ints getOffestsParams() {
        return offestsParams;
    }

    public Ints getOffestsTypes() {
        return offestsTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public StringList getParametersName() {
        return parametersName;
    }

    public boolean isRetRef() {
        return retRef;
    }

    public StringList getParametersType() {
        return parametersType;
    }

    public void setRetRef(boolean _ret) {
        this.retRef = _ret;
    }

    public void setReturnOffest(int _retOff) {
        this.returnOffest = _retOff;
    }

    public void setReturnType(String _retType) {
        this.returnType = _retType;
    }
}
