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
}
