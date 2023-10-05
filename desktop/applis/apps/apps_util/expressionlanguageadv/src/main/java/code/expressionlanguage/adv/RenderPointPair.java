package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.CoreCheckedExecOperationNodeInfos;
import code.expressionlanguage.exec.dbg.ExcPointBlockKey;
import code.expressionlanguage.exec.dbg.ExcPointBlockPair;
import code.expressionlanguage.exec.dbg.StrResultContextLambda;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.Struct;
import code.util.AbsMap;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

//not thread safe class
public final class RenderPointPair {
    private final ExcPointBlockPair excPointBlockPair;
    private final StrResultContextLambda render = new StrResultContextLambda();
    private final StrResultContextLambda expand = new StrResultContextLambda();
    private boolean globalEnabled;
    private int pref;
    private final StringMap<Integer> prefs = new StringMap<Integer>();
    private boolean enableExpand;
    private boolean expandFirst;

    public RenderPointPair(int _ex, String _cl, AbstractInterceptorStdCaller _v, boolean _en, boolean _exp) {
        excPointBlockPair = new ExcPointBlockPair(_ex,_cl,_v, _en);
        enableExpand = _exp;
    }

    public void analyze(String _exp, String _it, ResultContext _curr, AbsLightContextGenerator _gene) {
        render.result(ResultContextLambda.dynamicAnalyzeExc(_exp, excPointBlockPair, _curr, _curr.getPageEl().getAliasString(), _gene), _exp);
        String it_ = _curr.getPageEl().getAliasIterableTable();
        String b_ = it_ +"<"+_curr.getPageEl().getAliasString()+",?>";
        expand.result(ResultContextLambda.dynamicAnalyzeExc(_it, excPointBlockPair, _curr, b_, _gene), _it);
    }

    public StrResultContextLambda getRender() {
        return render;
    }

    public StrResultContextLambda getExpand() {
        return expand;
    }

    public ExcPointBlockPair getExcPointBlockPair() {
        return excPointBlockPair;
    }

    static RenderPointInfosPreference stopExc(CustList<RenderPointPair> _d, DbgNodeStruct _node) {
        return stopExc(_d,_node.getResult(),_node.value());
    }
    private static RenderPointInfosPreference stopExc(CustList<RenderPointPair> _d, ContextEl _context, Struct _str) {
        if (_str == null) {
            return null;
        }
        String clName_ = _str.getClassName(_context);
        CoreCheckedExecOperationNodeInfos core_ = new CoreCheckedExecOperationNodeInfos(ExecFormattedRootBlock.build(_str.getClassName(_context), _context.getClasses()), _str);
        RenderPointPair r_ = render(_d, clName_, ExcPointBlockKey.SAME);
        if (r_ != null) {
            return new RenderPointInfosPreference(r_,core_,"",-1, core_.getDeclaring());
        }
        if (!clName_.isEmpty()) {
            RenderPointPair inex_ = render(_d, StringExpUtil.getIdFromAllTypes(clName_), ExcPointBlockKey.SAME_FAMILY);
            if (inex_ != null) {
                return new RenderPointInfosPreference(inex_,core_,"",-1, core_.getDeclaring());
            }
        }
        for (RenderPointInfosPreference m: rends(_d, _context,_str)) {
            if (m.getBreakPointCondition().isGlobalEnabled()) {
                return m;
            }
        }
        RenderPointPair any_ = render(_d, "", ExcPointBlockKey.SAME_FAMILY);
        if (any_ != null) {
            return new RenderPointInfosPreference(any_,core_,"",-1, core_.getDeclaring());
        }
        return null;
    }

    public static CustList<RenderPointInfosPreference> rends(CustList<RenderPointPair> _d, ContextEl _context, Struct _str) {
        String argClassName_ = _str.getClassName(_context);
        CoreCheckedExecOperationNodeInfos core_ = new CoreCheckedExecOperationNodeInfos(ExecFormattedRootBlock.build(_str.getClassName(_context), _context.getClasses()), _str);
        String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        CustList<RenderPointInfosPreference> out_ = new CustList<RenderPointInfosPreference>();
        for (RenderPointPair b: _d) {
            ExcPointBlockKey k_ = b.getExcPointBlockPair().getEp();
            if (k_.isExact() != ExcPointBlockKey.INHERIT) {
                continue;
            }
            tryAdd(_context,core_, argClassName_, base_, out_, k_, b);
        }
        out_.sortElts(new CmpRendPair());
        return out_;
    }
    private static void tryAdd(ContextEl _context, CoreCheckedExecOperationNodeInfos _core, String _argClassName, String _base, CustList<RenderPointInfosPreference> _out, ExcPointBlockKey _k, RenderPointPair _bpc) {
        int pr_ = _bpc.pref(_base);
        String param_ = _k.getClName();
        String formatted_ = ExecInherits.getQuickFullTypeByBases(_argClassName, param_, _context);
        if (!formatted_.isEmpty()) {
            _out.add(new RenderPointInfosPreference(_bpc, _core, param_, pr_, ExecFormattedRootBlock.build(formatted_, _context.getClasses())));
        }
    }
    private static RenderPointPair render(CustList<RenderPointPair> _d, String _cl, int _exact) {
        int s_ = _d.size();
        for (int i = 0; i < s_; i++) {
            if (_d.get(i).isGlobalEnabled() && _d.get(i).getExcPointBlockPair().getEp().match(_cl,_exact)) {
                return _d.get(i);
            }
        }
        return null;
    }

    public int pref(String _cl) {
        for (EntryCust<String, Integer> e: prefs.entryList()) {
            if (StringUtil.quickEq(_cl,e.getKey())) {
                return e.getValue();
            }
        }
        return pref;
    }
    public boolean isGlobalEnabled() {
        return globalEnabled;
    }

    public void setGlobalEnabled(boolean _gl) {
        this.globalEnabled = _gl;
    }

    public boolean isEnableExpand() {
        return enableExpand;
    }

    public void setEnableExpand(boolean _e) {
        this.enableExpand = _e;
    }

    public boolean isExpandFirst() {
        return expandFirst;
    }

    public void setExpandFirst(boolean _e) {
        this.expandFirst = _e;
    }

    public int getPref() {
        return pref;
    }

    public void setPref(int _p) {
        this.pref = _p;
    }

    public void prefsMap(AbsMap<String,Integer> _elts) {
        prefs.clear();
        prefs.addAllEntries(_elts);
    }
    public StringMap<Integer> getPrefs() {
        return prefs;
    }
}
