package code.bean.nat;

import code.bean.nat.exec.NatFormParts;
import code.bean.nat.exec.NatNodeContainer;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.expressionlanguage.structs.Struct;
import code.sml.HtmlPage;
import code.sml.HtmlPageInt;
import code.sml.NodeContainer;
import code.util.CustList;
import code.util.LongMap;
import code.util.LongTreeMap;
import code.util.StringList;

public final class NatHtmlPage extends HtmlPage implements HtmlPageInt {
    private LongMap<LongTreeMap<NatNodeContainer>> containers = new LongMap<LongTreeMap<NatNodeContainer>>();

    private CustList<CustList<NatExecOperationNode>> callsExps = new CustList<CustList<NatExecOperationNode>>();

    private CustList<CustList<NatExecOperationNode>> callsFormExps = new CustList<CustList<NatExecOperationNode>>();
    private CustList<StringList> anchorsArgs = new CustList<StringList>();

    private CustList<StringList> anchorsVars = new CustList<StringList>();

    private CustList<StringList> formsArgs = new CustList<StringList>();
    private CustList<StringList> formsVars = new CustList<StringList>();
    private CustList<Struct> structsAnc = new CustList<Struct>();
    private CustList<Struct> structsForm = new CustList<Struct>();

    public void set(NatFormParts _form) {
        setBase(_form);
        setAnchorsArgs(_form.getAnchorsArgs());
        setAnchorsVars(_form.getAnchorsVars());
        setStructsAnc(_form.getStructsAnc());
        setStructsForm(_form.getStructsForm());
        setContainers(_form.getContainersMap());
        setCallsExps(_form.getCallsExps());
        setCallsFormExps(_form.getCallsFormExps());
        setFormsArgs(_form.getFormsArgs());
        setFormsVars(_form.getFormsVars());
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

    public CustList<Struct> getStructsForm() {
        return structsForm;
    }

    public void setStructsForm(CustList<Struct> _s) {
        this.structsForm = _s;
    }

    public CustList<Struct> getStructsAnc() {
        return structsAnc;
    }

    public void setStructsAnc(CustList<Struct> _s) {
        this.structsAnc = _s;
    }

    public CustList<CustList<NatExecOperationNode>> getCallsExps() {
        return callsExps;
    }

    public CustList<CustList<NatExecOperationNode>> getCallsFormExps() {
        return callsFormExps;
    }

    public LongMap<LongTreeMap<NatNodeContainer>> getContainers() {
        return containers;
    }

    public void setContainers(LongMap<LongTreeMap<NatNodeContainer>> _cont) {
        this.containers = _cont;
    }

    public void setCallsExps(CustList<CustList<NatExecOperationNode>> _cal) {
        this.callsExps = _cal;
    }

    public void setCallsFormExps(CustList<CustList<NatExecOperationNode>> _cal) {
        this.callsFormExps = _cal;
    }

    @Override
    public NodeContainer getContainer(long _formNb, long _nbId) {
        return containers.getVal(_formNb).getVal(_nbId);
    }
}
