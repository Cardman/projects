package code.expressionlanguage.common;

public final class OptionsReport {
    private boolean displayImplicit;
    private boolean displayImplicitLabel;
    private boolean encodeHeader = true;
    private String callsFile;

    public boolean isDisplayImplicit() {
        return displayImplicit;
    }

    public void setDisplayImplicit(boolean _d) {
        this.displayImplicit = _d;
    }

    public boolean isDisplayImplicitLabel() {
        return displayImplicitLabel;
    }

    public void setDisplayImplicitLabel(boolean _d) {
        this.displayImplicitLabel = _d;
    }

    public boolean isEncodeHeader() {
        return encodeHeader;
    }

    public void setEncodeHeader(boolean _e) {
        encodeHeader = _e;
    }

    public String getCallsFile() {
        return callsFile;
    }

    public void setCallsFile(String _c) {
        this.callsFile = _c;
    }

}
