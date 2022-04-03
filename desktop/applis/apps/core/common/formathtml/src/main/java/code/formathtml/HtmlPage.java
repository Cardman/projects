package code.formathtml;

import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.LongMap;
import code.util.StringList;
import code.util.core.IndexConstants;

public abstract class HtmlPage {

    private CustList<StringList> anchorsArgs = new CustList<StringList>();
    private CustList<StringList> anchorsVars = new CustList<StringList>();

    private LongMap<StringList> formatIdMap = new LongMap<StringList>();

    private CustList<StringList> formsArgs = new CustList<StringList>();
    private CustList<StringList> formsVars = new CustList<StringList>();

    private long url = IndexConstants.INDEX_NOT_FOUND_ELT;

    private boolean form;
    protected HtmlPage() {
    }

    public void setBase(FormParts _form) {
        setFormatIdMap(_form.getFormatIdMap());
        setAnchorsArgs(_form.getAnchorsArgs());
        setAnchorsVars(_form.getAnchorsVars());
        setFormsArgs(_form.getFormsArgs());
        setFormsVars(_form.getFormsVars());
    }

    public long getUrl() {
        return url;
    }

    public void setUrl(long _url) {
        url = _url;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean _form) {
        form = _form;
    }

    public CustList<StringList> getAnchorsArgs() {
        return anchorsArgs;
    }

    public void setAnchorsArgs(CustList<StringList> _anchorsArgs) {
        anchorsArgs = _anchorsArgs;
    }

    public CustList<StringList> getFormsArgs() {
        return formsArgs;
    }

    public void setFormsArgs(CustList<StringList> _formsArgs) {
        formsArgs = _formsArgs;
    }

    public CustList<StringList> getAnchorsVars() {
        return anchorsVars;
    }

    public void setAnchorsVars(CustList<StringList> _anchorsVars) {
        anchorsVars = _anchorsVars;
    }

    public CustList<StringList> getFormsVars() {
        return formsVars;
    }

    public void setFormsVars(CustList<StringList> _formsVars) {
        formsVars = _formsVars;
    }

    public LongMap<StringList> getFormatIdMap() {
        return formatIdMap;
    }

    public void setFormatIdMap(LongMap<StringList> _formatIdMap) {
        formatIdMap = _formatIdMap;
    }

    public abstract NodeContainer getContainer(long _formNb, long _nbId);
}
