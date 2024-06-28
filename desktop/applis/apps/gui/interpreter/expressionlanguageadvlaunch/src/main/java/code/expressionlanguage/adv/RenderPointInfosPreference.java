package code.expressionlanguage.adv;

import code.expressionlanguage.exec.CheckedMethodInfos;
import code.expressionlanguage.exec.CoreCheckedExecOperationNodeInfos;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public class RenderPointInfosPreference {
    private final RenderPointPair breakPointCondition;
    private final CoreCheckedExecOperationNodeInfos infos;
    private final String sgn;
    private final int preference;
    private final ExecFormattedRootBlock value;

    public RenderPointInfosPreference(RenderPointPair _bpc, CoreCheckedExecOperationNodeInfos _i, String _s, int _p, ExecFormattedRootBlock _v) {
        this.breakPointCondition = _bpc;
        this.infos = _i;
        this.sgn = _s;
        this.preference = _p;
        this.value = _v;
    }

    public RenderPointPair getBreakPointCondition() {
        return breakPointCondition;
    }

    public int getPreference() {
        return preference;
    }

    public String getSgn() {
        return sgn;
    }

    public CheckedMethodInfos build() {
        return new CheckedMethodInfos(value,infos.getInstance(),CheckedMethodInfos.params(infos));
    }
}
