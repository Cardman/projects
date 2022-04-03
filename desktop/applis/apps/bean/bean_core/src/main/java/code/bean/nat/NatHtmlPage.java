package code.bean.nat;

import code.bean.nat.exec.NatFormParts;
import code.bean.nat.exec.NatNodeContainer;
import code.formathtml.HtmlPage;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.NodeContainer;
import code.util.CustList;
import code.util.LongMap;
import code.util.LongTreeMap;

public final class NatHtmlPage extends HtmlPage {
    private LongMap<LongTreeMap<NatNodeContainer>> containers = new LongMap<LongTreeMap<NatNodeContainer>>();

    private CustList<CustList<RendDynOperationNode>> callsExps = new CustList<CustList<RendDynOperationNode>>();

    private CustList<CustList<RendDynOperationNode>> callsFormExps = new CustList<CustList<RendDynOperationNode>>();

    public void set(NatFormParts _form) {
        setBase(_form);
        setContainers(_form.getContainersMap());
        setCallsExps(_form.getCallsExps());
        setCallsFormExps(_form.getCallsFormExps());
    }

    public CustList<CustList<RendDynOperationNode>> getCallsExps() {
        return callsExps;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsFormExps() {
        return callsFormExps;
    }

    public LongMap<LongTreeMap<NatNodeContainer>> getContainers() {
        return containers;
    }

    public void setContainers(LongMap<LongTreeMap<NatNodeContainer>> _cont) {
        this.containers = _cont;
    }

    public void setCallsExps(CustList<CustList<RendDynOperationNode>> _cal) {
        this.callsExps = _cal;
    }

    public void setCallsFormExps(CustList<CustList<RendDynOperationNode>> _cal) {
        this.callsFormExps = _cal;
    }

    @Override
    public NodeContainer getContainer(long _formNb, long _nbId) {
        return containers.getVal(_formNb).getVal(_nbId);
    }
}
