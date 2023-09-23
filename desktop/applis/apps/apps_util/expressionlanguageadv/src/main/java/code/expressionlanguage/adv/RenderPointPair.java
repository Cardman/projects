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

    static ResultContextLambda stopExc(CustList<RenderPointPair> _d, DbgNodeStruct _node) {
        return stopExc(_d,_node.getResult(),_node.value());
    }
    private static ResultContextLambda stopExc(CustList<RenderPointPair> _d, ContextEl _context, Struct _str) {
        String clName_ = _str.getClassName(_context);
        ResultContextLambda r_ = checkExc(render(_d, _context, clName_, true));
        if (r_ != null) {
            return r_;
        }
        if (!clName_.isEmpty()) {
            ResultContextLambda inex_ = checkExc(render(_d, _context, StringExpUtil.getIdFromAllTypes(clName_), false));
            if (inex_ != null) {
                return inex_;
            }
        }
        return checkExc(render(_d,_context,"",false));
    }

    private static ResultContextLambda checkExc(RenderPointPair _bp) {
        StrResultContextLambda bpc_ = stopExcValue(_bp);
        return stopCurrent(bpc_);
    }

    private static ResultContextLambda stopCurrent(StrResultContextLambda _condition) {
        if (_condition == null) {
            return null;
        }
        return _condition.getResult();
    }

    private static StrResultContextLambda stopExcValue(RenderPointPair _ex) {
        if (!_ex.getExcPointBlockPair().getValue().isEnabled()) {
            return null;
        }
        return _ex.getRender();
    }
    private static RenderPointPair render(CustList<RenderPointPair> _d, ContextEl _context, String _cl, boolean _exact) {
        int s_ = _d.size();
        for (int i = 0; i < s_; i++) {
            if (_d.get(i).getExcPointBlockPair().getEp().match(_cl,_exact)) {
                return _d.get(i);
            }
        }
        return new RenderPointPair(false,"",_context.getCaller(), false);
    }
}
