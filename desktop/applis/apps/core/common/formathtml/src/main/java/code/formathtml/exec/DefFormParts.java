package code.formathtml.exec;

import code.formathtml.FormParts;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.DefNodeContainer;
import code.util.CustList;
import code.util.LongMap;
import code.util.LongTreeMap;

public final class DefFormParts extends FormParts {
    private LongMap<LongTreeMap<DefNodeContainer>> containersMap;
    private CustList<LongTreeMap<DefNodeContainer>> containersMapStack;
    private CustList<CustList<RendDynOperationNode>> callsExps = new CustList<CustList<RendDynOperationNode>>();
    private CustList<CustList<RendDynOperationNode>> callsFormExps = new CustList<CustList<RendDynOperationNode>>();


    public void initFormsSpec() {
        initForms();
        callsExps = new CustList<CustList<RendDynOperationNode>>();
        containersMap = new LongMap<LongTreeMap<DefNodeContainer>>();
        containersMapStack = new CustList<LongTreeMap<DefNodeContainer>>();
        callsFormExps = new CustList<CustList<RendDynOperationNode>>();
    }
    public LongMap<LongTreeMap<DefNodeContainer>> getContainersMap() {
        return containersMap;
    }

    public CustList<LongTreeMap<DefNodeContainer>> getContainersMapStack() {
        return containersMapStack;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsExps() {
        return callsExps;
    }

    public CustList<CustList<RendDynOperationNode>> getCallsFormExps() {
        return callsFormExps;
    }

}
