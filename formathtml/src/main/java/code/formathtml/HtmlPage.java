package code.formathtml;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.FormInputCoords;
import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.*;
import code.util.*;
import code.util.ObjectMap;
import code.util.StringList;

public class HtmlPage {

    private LongMap<LongTreeMap<NodeContainer>> containers = new LongMap<LongTreeMap<NodeContainer>>();

    private ObjectMap<FormInputCoords,StringList> selects = new ObjectMap<FormInputCoords,StringList>();
    private CustList<CustList<RendDynOperationNode>> callsExps = new CustList<CustList<RendDynOperationNode>>();
    private CustList<StringList> anchorsArgs = new CustList<StringList>();
    private CustList<StringList> anchorsVars = new CustList<StringList>();
    private BooleanList constAnchors = new BooleanList();
    private StringList anchorsNames = new StringList();

    private CustList<CustList<RendDynOperationNode>> callsFormExps = new CustList<CustList<RendDynOperationNode>>();
    private CustList<StringList> formsArgs = new CustList<StringList>();
    private CustList<StringList> formsVars = new CustList<StringList>();
    private StringList formsNames = new StringList();
    private LongMap<CustList<IdFormat>> formatIdMap = new LongMap<CustList<IdFormat>>();
    private CustList<IdFormat> formatId = new CustList<IdFormat>();

    private long url = CustList.INDEX_NOT_FOUND_ELT;

    private boolean form;

    private String usedFieldUrl;

    public LongMap<LongTreeMap<NodeContainer>> getContainers() {
        return containers;
    }

    public void setContainers(LongMap<LongTreeMap<NodeContainer>> _containers) {
        containers = _containers;
    }

    public ObjectMap<FormInputCoords,StringList> getSelects() {
        return selects;
    }

    public void setSelects(ObjectMap<FormInputCoords,StringList> _selects) {
        selects = _selects;
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

    public String getUsedFieldUrl() {
        return usedFieldUrl;
    }

    public void setUsedFieldUrl(String _usedFieldUrl) {
        usedFieldUrl = _usedFieldUrl;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsExps() {
        return callsExps;
    }

    public void setCallsExps(CustList<CustList<RendDynOperationNode>> _callsExps) {
        callsExps = _callsExps;
    }

    public CustList<StringList> getAnchorsArgs() {
        return anchorsArgs;
    }

    public void setAnchorsArgs(CustList<StringList> _anchorsArgs) {
        anchorsArgs = _anchorsArgs;
    }

    public CustList<StringList> getAnchorsVars() {
        return anchorsVars;
    }

    public void setAnchorsVars(CustList<StringList> _anchorsVars) {
        anchorsVars = _anchorsVars;
    }

    public BooleanList getConstAnchors() {
        return constAnchors;
    }

    public void setConstAnchors(BooleanList _constAnchors) {
        constAnchors = _constAnchors;
    }

    public StringList getAnchorsNames() {
        return anchorsNames;
    }

    public void setAnchorsNames(StringList _anchorsNames) {
        anchorsNames = _anchorsNames;
    }

    public CustList<StringList> getFormsArgs() {
        return formsArgs;
    }

    public void setFormsArgs(CustList<StringList> _formsArgs) {
        formsArgs = _formsArgs;
    }

    public CustList<StringList> getFormsVars() {
        return formsVars;
    }

    public void setFormsVars(CustList<StringList> _formsVars) {
        formsVars = _formsVars;
    }

    public StringList getFormsNames() {
        return formsNames;
    }

    public void setFormsNames(StringList _formsNames) {
        formsNames = _formsNames;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsFormExps() {
        return callsFormExps;
    }

    public void setCallsFormExps(CustList<CustList<RendDynOperationNode>> _callsFormExps) {
        callsFormExps = _callsFormExps;
    }

    public LongMap<CustList<IdFormat>> getFormatIdMap() {
        return formatIdMap;
    }

    public void setFormatIdMap(LongMap<CustList<IdFormat>> _formatIdMap) {
        formatIdMap = _formatIdMap;
    }

    public CustList<IdFormat> getFormatId() {
        return formatId;
    }

    public void setFormatId(CustList<IdFormat> _formatId) {
        formatId = _formatId;
    }
}
