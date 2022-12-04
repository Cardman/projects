package code.bean.nat.exec;

import code.bean.nat.exec.opers.NatExecOperationNode;
import code.expressionlanguage.structs.Struct;
import code.sml.FormParts;
import code.util.CustList;
import code.util.LongMap;
import code.util.LongTreeMap;
import code.util.StringList;

public final class NatFormParts extends FormParts {
    private LongMap<LongTreeMap<NatNodeContainer>> containersMap;
    private CustList<LongTreeMap<NatNodeContainer>> containersMapStack;
    private CustList<CustList<NatExecOperationNode>> callsExps = new CustList<CustList<NatExecOperationNode>>();
    private CustList<CustList<NatExecOperationNode>> callsFormExps = new CustList<CustList<NatExecOperationNode>>();
    private CustList<StringList> anchorsArgs = new CustList<StringList>();
    private CustList<StringList> anchorsVars = new CustList<StringList>();
    private CustList<StringList> formsArgs = new CustList<StringList>();
    private CustList<StringList> formsVars = new CustList<StringList>();
    private CustList<Struct> structsAnc = new CustList<Struct>();
    private CustList<Struct> structsForm = new CustList<Struct>();

    public void initFormsSpec() {
        initForms();
        anchorsArgs = new CustList<StringList>();
        anchorsVars = new CustList<StringList>();
        formsArgs = new CustList<StringList>();
        formsVars = new CustList<StringList>();
        callsExps = new CustList<CustList<NatExecOperationNode>>();
        containersMap = new LongMap<LongTreeMap<NatNodeContainer>>();
        containersMapStack = new CustList<LongTreeMap<NatNodeContainer>>();
        callsFormExps = new CustList<CustList<NatExecOperationNode>>();
        structsAnc = new CustList<Struct>();
        structsForm = new CustList<Struct>();
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

    public CustList<StringList> getAnchorsArgs() {
        return anchorsArgs;
    }

    public CustList<StringList> getAnchorsVars() {
        return anchorsVars;
    }

    public CustList<StringList> getFormsArgs() {
        return formsArgs;
    }

    public CustList<StringList> getFormsVars() {
        return formsVars;
    }

    public CustList<Struct> getStructsAnc() {
        return structsAnc;
    }

    public CustList<Struct> getStructsForm() {
        return structsForm;
    }
}
