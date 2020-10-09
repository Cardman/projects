package code.formathtml;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.FormInputCoords;
import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.*;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class HtmlPage {

    private LongMap<LongTreeMap<NodeContainer>> containers = new LongMap<LongTreeMap<NodeContainer>>();

    private ObjectMap<FormInputCoords,StringList> selects = new ObjectMap<FormInputCoords,StringList>();
    private CustList<CustList<RendDynOperationNode>> callsExps = new CustList<CustList<RendDynOperationNode>>();
    private CustList<StringList> anchorsArgs = new CustList<StringList>();
    private CustList<StringList> anchorsVars = new CustList<StringList>();

    private LongMap<StringList> formatIdMap = new LongMap<StringList>();

    private CustList<CustList<RendDynOperationNode>> callsFormExps = new CustList<CustList<RendDynOperationNode>>();
    private CustList<StringList> formsArgs = new CustList<StringList>();
    private CustList<StringList> formsVars = new CustList<StringList>();

    private long url = IndexConstants.INDEX_NOT_FOUND_ELT;

    private boolean form;

    public void set(FormParts _form) {
        setContainers(_form.getContainersMap());
        setFormatIdMap(_form.getFormatIdMap());
        setCallsExps(_form.getCallsExps());
        setAnchorsArgs(_form.getAnchorsArgs());
        setAnchorsVars(_form.getAnchorsVars());
        setCallsFormExps(_form.getCallsFormExps());
        setFormsArgs(_form.getFormsArgs());
        setFormsVars(_form.getFormsVars());
    }

    public LongMap<LongTreeMap<NodeContainer>> getContainers() {
        return containers;
    }

    public void setContainers(LongMap<LongTreeMap<NodeContainer>> _containers) {
        containers = _containers;
    }

    public ObjectMap<FormInputCoords,StringList> getSelects() {
        return selects;
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

    public CustList<CustList<RendDynOperationNode>> getCallsExps() {
        return callsExps;
    }

    public void setCallsExps(CustList<CustList<RendDynOperationNode>> _callsExps) {
        callsExps = _callsExps;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsFormExps() {
        return callsFormExps;
    }

    public void setCallsFormExps(CustList<CustList<RendDynOperationNode>> _callsFormExps) {
        callsFormExps = _callsFormExps;
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

}
