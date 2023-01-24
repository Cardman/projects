package code.formathtml;

import code.formathtml.exec.AnchorCall;
import code.formathtml.exec.DefFormParts;
import code.formathtml.util.DefNodeContainer;
import code.sml.HtmlPage;
import code.sml.NodeContainer;
import code.util.CustList;
import code.util.EntryCust;
import code.util.LongMap;
import code.util.LongTreeMap;

public final class DefHtmlPage extends HtmlPage {
    private LongMap<LongTreeMap<DefNodeContainer>> containers = new LongMap<LongTreeMap<DefNodeContainer>>();

    private CustList<AnchorCall> callsExps = new CustList<AnchorCall>();

    private CustList<AnchorCall> callsFormExps = new CustList<AnchorCall>();

    public void set(DefFormParts _form) {
        setBase(_form);
        setContainers(_form.getContainersMap());
        setCallsExps(_form.getCallsExps());
        setCallsFormExps(_form.getCallsFormExps());
    }

    public void setContainers(LongMap<LongTreeMap<DefNodeContainer>> _cont) {
        getContainersBase().clear();
        for (EntryCust<Long,LongTreeMap<DefNodeContainer>> e: _cont.entryList()) {
            LongTreeMap<NodeContainer> l_ = new LongTreeMap<NodeContainer>();
            for (EntryCust<Long, DefNodeContainer> f: e.getValue().entryList()) {
                l_.addEntry(f.getKey(),f.getValue());
            }
            getContainersBase().addEntry(e.getKey(),l_);
        }
        this.containers = _cont;
    }

    public void setCallsExps(CustList<AnchorCall> _cal) {
        this.callsExps = _cal;
    }

    public void setCallsFormExps(CustList<AnchorCall> _cal) {
        this.callsFormExps = _cal;
    }

    public CustList<AnchorCall> getCallsFormExps() {
        return callsFormExps;
    }

    public CustList<AnchorCall> getCallsExps() {
        return callsExps;
    }

    public LongMap<LongTreeMap<DefNodeContainer>> getContainers() {
        return containers;
    }

}
