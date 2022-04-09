package code.expressionlanguage.analyze.files;

import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.BoolVal;

public final class ParsedFctHeaderResult {
    private final Ints offestsTypes;
    private final Ints offestsParams;
    private final CustList<BoolVal> parametersRef;
    private final StringList parametersType;
    private final StringList parametersName;
    private final String returnType;
    private final boolean retRef;
    private final int returnOffest;
    public ParsedFctHeaderResult(ParsedFctHeader _header) {
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

}
