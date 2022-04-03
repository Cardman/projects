package code.formathtml;

import code.formathtml.exec.DefFormParts;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.DefNodeContainer;
import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.LongMap;
import code.util.LongTreeMap;

public final class DefHtmlPage extends HtmlPage{
    private LongMap<LongTreeMap<DefNodeContainer>> containers = new LongMap<LongTreeMap<DefNodeContainer>>();

    private CustList<CustList<RendDynOperationNode>> callsExps = new CustList<CustList<RendDynOperationNode>>();

    private CustList<CustList<RendDynOperationNode>> callsFormExps = new CustList<CustList<RendDynOperationNode>>();

    public void set(DefFormParts _form) {
        setBase(_form);
        setContainers(_form.getContainersMap());
        setCallsExps(_form.getCallsExps());
        setCallsFormExps(_form.getCallsFormExps());
    }

    public void setContainers(LongMap<LongTreeMap<DefNodeContainer>> _cont) {
        this.containers = _cont;
    }

    public void setCallsExps(CustList<CustList<RendDynOperationNode>> _cal) {
        this.callsExps = _cal;
    }

    public void setCallsFormExps(CustList<CustList<RendDynOperationNode>> _cal) {
        this.callsFormExps = _cal;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsFormExps() {
        return callsFormExps;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsExps() {
        return callsExps;
    }

    public LongMap<LongTreeMap<DefNodeContainer>> getContainers() {
        return containers;
    }

    @Override
    public NodeContainer getContainer(long _formNb, long _nbId) {
        return containers.getVal(_formNb).getVal(_nbId);
    }
}
