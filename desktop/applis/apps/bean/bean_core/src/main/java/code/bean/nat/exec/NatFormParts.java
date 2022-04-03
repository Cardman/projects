package code.bean.nat.exec;

import code.formathtml.FormParts;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.LongMap;
import code.util.LongTreeMap;

public final class NatFormParts extends FormParts {
    private LongMap<LongTreeMap<NatNodeContainer>> containersMap;
    private CustList<LongTreeMap<NatNodeContainer>> containersMapStack;
    private CustList<CustList<RendDynOperationNode>> callsExps = new CustList<CustList<RendDynOperationNode>>();
    private CustList<CustList<RendDynOperationNode>> callsFormExps = new CustList<CustList<RendDynOperationNode>>();

    public void initFormsSpec() {
        initForms();
        callsExps = new CustList<CustList<RendDynOperationNode>>();
        containersMap = new LongMap<LongTreeMap<NatNodeContainer>>();
        containersMapStack = new CustList<LongTreeMap<NatNodeContainer>>();
        callsFormExps = new CustList<CustList<RendDynOperationNode>>();
    }
    public LongMap<LongTreeMap<NatNodeContainer>> getContainersMap() {
        return containersMap;
    }

    public CustList<LongTreeMap<NatNodeContainer>> getContainersMapStack() {
        return containersMapStack;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsExps() {
        return callsExps;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsFormExps() {
        return callsFormExps;
    }
}
