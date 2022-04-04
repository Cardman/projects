package code.bean.nat.exec;

import code.bean.nat.exec.opers.NatExecOperationNode;
import code.formathtml.FormParts;
import code.util.CustList;
import code.util.LongMap;
import code.util.LongTreeMap;

public final class NatFormParts extends FormParts {
    private LongMap<LongTreeMap<NatNodeContainer>> containersMap;
    private CustList<LongTreeMap<NatNodeContainer>> containersMapStack;
    private CustList<CustList<NatExecOperationNode>> callsExps = new CustList<CustList<NatExecOperationNode>>();
    private CustList<CustList<NatExecOperationNode>> callsFormExps = new CustList<CustList<NatExecOperationNode>>();

    public void initFormsSpec() {
        initForms();
        callsExps = new CustList<CustList<NatExecOperationNode>>();
        containersMap = new LongMap<LongTreeMap<NatNodeContainer>>();
        containersMapStack = new CustList<LongTreeMap<NatNodeContainer>>();
        callsFormExps = new CustList<CustList<NatExecOperationNode>>();
    }
    public LongMap<LongTreeMap<NatNodeContainer>> getContainersMap() {
        return containersMap;
    }

    public CustList<LongTreeMap<NatNodeContainer>> getContainersMapStack() {
        return containersMapStack;
    }

    public CustList<CustList<NatExecOperationNode>> getCallsExps() {
        return callsExps;
    }

    public CustList<CustList<NatExecOperationNode>> getCallsFormExps() {
        return callsFormExps;
    }
}
