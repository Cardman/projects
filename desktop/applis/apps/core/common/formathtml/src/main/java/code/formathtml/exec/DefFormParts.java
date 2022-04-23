package code.formathtml.exec;

import code.formathtml.FormParts;
import code.formathtml.util.DefNodeContainer;
import code.util.CustList;
import code.util.LongMap;
import code.util.LongTreeMap;

public final class DefFormParts extends FormParts {
    private LongMap<LongTreeMap<DefNodeContainer>> containersMap;
    private CustList<LongTreeMap<DefNodeContainer>> containersMapStack;
    private CustList<AnchorCall> callsExps = new CustList<AnchorCall>();
    private CustList<AnchorCall> callsFormExps = new CustList<AnchorCall>();


    public void initFormsSpec() {
        initForms();
        callsExps = new CustList<AnchorCall>();
        containersMap = new LongMap<LongTreeMap<DefNodeContainer>>();
        containersMapStack = new CustList<LongTreeMap<DefNodeContainer>>();
        callsFormExps = new CustList<AnchorCall>();
    }
    public LongMap<LongTreeMap<DefNodeContainer>> getContainersMap() {
        return containersMap;
    }

    public CustList<LongTreeMap<DefNodeContainer>> getContainersMapStack() {
        return containersMapStack;
    }

    public CustList<AnchorCall> getCallsExps() {
        return callsExps;
    }

    public CustList<AnchorCall> getCallsFormExps() {
        return callsFormExps;
    }

}
