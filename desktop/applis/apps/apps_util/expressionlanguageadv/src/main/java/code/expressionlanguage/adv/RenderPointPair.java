package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.expressionlanguage.exec.dbg.StrResultContextLambda;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

//not thread safe class
public final class RenderPointPair {
    private final ExcPointBlockPair excPointBlockPair;
    private final StrResultContextLambda render = new StrResultContextLambda();
    private boolean globalEnabled;
    public RenderPointPair(boolean _ex, String _cl, AbstractInterceptorStdCaller _v, boolean _en) {
        excPointBlockPair = new ExcPointBlockPair(_ex,_cl,_v, _en);
    }

    public void analyze(String _exp, ResultContext _curr, AbsLightContextGenerator _gene) {
        render.result(ResultContextLambda.dynamicAnalyzeExc(_exp, excPointBlockPair, _curr, _curr.getPageEl().getAliasString(), _gene), _exp);
    }

    public StrResultContextLambda getRender() {
        return render;
    }

    public ExcPointBlockPair getExcPointBlockPair() {
        return excPointBlockPair;
    }

    static RenderPointPair stopExc(CustList<RenderPointPair> _d, DbgNodeStruct _node) {
        return stopExc(_d,_node.getResult(),_node.value());
    }
    private static RenderPointPair stopExc(CustList<RenderPointPair> _d, ContextEl _context, Struct _str) {
        if (_str == null) {
            return null;
        }
        String clName_ = _str.getClassName(_context);
        RenderPointPair r_ = render(_d, clName_, true);
        if (r_ != null) {
            return r_;
        }
        if (!clName_.isEmpty()) {
            RenderPointPair inex_ = render(_d, StringExpUtil.getIdFromAllTypes(clName_), false);
            if (inex_ != null) {
                return inex_;
            }
        }
        return render(_d, "",false);
    }

    private static RenderPointPair render(CustList<RenderPointPair> _d, String _cl, boolean _exact) {
        int s_ = _d.size();
        for (int i = 0; i < s_; i++) {
            if (_d.get(i).isGlobalEnabled() && _d.get(i).getExcPointBlockPair().getEp().match(_cl,_exact)) {
                return _d.get(i);
            }
        }
        return null;
    }

    public boolean isGlobalEnabled() {
        return globalEnabled;
    }

    public void setGlobalEnabled(boolean _gl) {
        this.globalEnabled = _gl;
    }
}
