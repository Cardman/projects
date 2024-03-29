package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractAtomicRef;
import code.util.AbsMap;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class BreakPointCondition {
    private final AbsPairPoint superKeyPoint;
    private final AbsKeyPoint keyPoint;
    private final int kindPoint;
    private final int phasePoint;
    private final AbstractAtomicBoolean disableWhenHit;
    private final AbstractAtomicBoolean enabled;
    private final AbstractAtomicBoolean hit;
    private final AbstractAtomicBoolean suspend;
    private final AbstractAtomicBoolean disableAgain;
    private final AbstractAtomicBoolean stackLog;
    private final AbstractAtomicBoolean stackErrLog;
    private final AbstractAtomicBoolean stackResErrLog;
    private final AbsCollection<BreakPointCondition> others;
    private final AbstractAtomicRef<StrResultContextLambda> lda;
    private final AbstractAtomicRef<StrResultContextLambda> logs;
    private final AbstractAtomicRef<StrResultContextLambda> watches;
    private final AbstractAtomicInteger countModulo;
    private final AbstractAtomicInteger count;
    private final AbstractAtomicInteger pref;
    private final AbsCollection<AbsCallContraints> exclude;
    private final AbsCollection<AbsCallContraints> include;
    private final AbsCollection<EntryCust<String,Integer>> prefs;
    public BreakPointCondition(AbstractInterceptorStdCaller _i, AbsPairPoint _superKey, AbsKeyPoint _key, int _kind, int _phase) {
        this.superKeyPoint = _superKey;
        this.keyPoint = _key;
        this.kindPoint = _kind;
        this.phasePoint = _phase;
        exclude = _i.newExecFileBlockTraceIndexCollection();
        include = _i.newExecFileBlockTraceIndexCollection();
        others = _i.newBreakPointConditionCollection();
        lda = _i.newAtLda();
        logs = _i.newAtLda();
        watches = _i.newAtLda();
        disableWhenHit = _i.newAtBool();
        enabled = _i.newAtBool();
        enabled.set(true);
        hit = _i.newAtBool();
        suspend = _i.newAtBool();
        suspend.set(true);
        disableAgain = _i.newAtBool();
        disableAgain.set(true);
        stackErrLog = _i.newAtBool();
        stackResErrLog = _i.newAtBool();
        stackLog = _i.newAtBool();
        countModulo = _i.newAtInt();
        count = _i.newAtInt();
        pref = _i.newAtInt();
        prefs = _i.newStringNumberCollection();
    }

    public BreakPointCondition stopBpc(boolean _en) {
        if (_en) {
            return this;
        }
        return null;
    }
    public void analyze(BreakPointBlockPair _mp, String _exp, String _log, String _watch, ResultContext _curr, AbsLightContextGenerator _gene) {
        result(ResultContextLambda.dynamicAnalyze(_exp, _mp, _curr, _curr.getPageEl().getAliasPrimBoolean(), _gene, phasePoint), _exp);
        resultLogs(ResultContextLambda.dynamicAnalyze(_log, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene, phasePoint), _log);
        resultWatches(ResultContextLambda.dynamicAnalyze(_watch, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene, phasePoint), _watch);
    }
    public void analyze(TypePointBlockPair _mp, String _exp, String _log, String _watch, ResultContext _curr, AbsLightContextGenerator _gene) {
        result(ResultContextLambda.dynamicAnalyze(_exp, _mp, _curr, _curr.getPageEl().getAliasPrimBoolean(), _gene, phasePoint), _exp);
        resultLogs(ResultContextLambda.dynamicAnalyze(_log, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene, phasePoint), _log);
        resultWatches(ResultContextLambda.dynamicAnalyze(_watch, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene, phasePoint), _watch);
    }

    public void analyze(StdMethodPointBlockPair _mp, String _exp, String _log, String _watch, ResultContext _curr, AbsLightContextGenerator _gene) {
        result(ResultContextLambda.dynamicAnalyze(_exp, _mp, _curr, _curr.getPageEl().getAliasPrimBoolean(), _gene), _exp);
        resultLogs(ResultContextLambda.dynamicAnalyze(_log, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene), _log);
        resultWatches(ResultContextLambda.dynamicAnalyze(_watch, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene), _watch);
    }

    public void analyze(OperNatPointBlockPair _mp, String _exp, String _log, String _watch, ResultContext _curr, AbsLightContextGenerator _gene) {
        result(ResultContextLambda.dynamicAnalyze(_exp, _mp, _curr, _curr.getPageEl().getAliasPrimBoolean(), _gene), _exp);
        resultLogs(ResultContextLambda.dynamicAnalyze(_log, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene), _log);
        resultWatches(ResultContextLambda.dynamicAnalyze(_watch, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene), _watch);
    }

    public void analyze(MethodPointBlockPair _mp, String _exp, String _log, String _watch, ResultContext _curr, AbsLightContextGenerator _gene) {
        result(ResultContextLambda.dynamicAnalyze(_exp, _mp, _curr, _curr.getPageEl().getAliasPrimBoolean(), _gene), _exp);
        resultLogs(ResultContextLambda.dynamicAnalyze(_log, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene), _log);
        resultWatches(ResultContextLambda.dynamicAnalyze(_watch, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene), _watch);
    }

    public void analyze(WatchPointBlockPair _mp, String _exp, String _log, String _watch, ResultContext _curr, AbsLightContextGenerator _gene) {
        result(ResultContextLambda.dynamicAnalyzeField(_exp, _mp, _curr, _curr.getPageEl().getAliasPrimBoolean(), _gene, phasePoint), _exp);
        resultLogs(ResultContextLambda.dynamicAnalyzeField(_log, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene, phasePoint), _log);
        resultWatches(ResultContextLambda.dynamicAnalyzeField(_watch, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene, phasePoint), _watch);
    }

    public void analyze(ArrPointBlockPair _mp, String _exp, String _log, String _watch, ResultContext _curr, AbsLightContextGenerator _gene) {
        result(ResultContextLambda.dynamicAnalyzeArr(_exp, _mp, _curr, _curr.getPageEl().getAliasPrimBoolean(), _gene, phasePoint), _exp);
        resultLogs(ResultContextLambda.dynamicAnalyzeArr(_log, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene, phasePoint), _log);
        resultWatches(ResultContextLambda.dynamicAnalyzeArr(_watch, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene, phasePoint), _watch);
    }

    public void analyze(ExcPointBlockPair _mp, String _exp, String _log, String _watch, ResultContext _curr, AbsLightContextGenerator _gene) {
        result(ResultContextLambda.dynamicAnalyzeExc(_exp, _mp, _curr, _curr.getPageEl().getAliasPrimBoolean(), _gene), _exp);
        resultLogs(ResultContextLambda.dynamicAnalyzeExc(_log, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene), _log);
        resultWatches(ResultContextLambda.dynamicAnalyzeExc(_watch, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene), _watch);
    }

    public void analyze(ParPointBlockPair _mp, String _exp, String _log, String _watch, ResultContext _curr, AbsLightContextGenerator _gene) {
        result(ResultContextLambda.dynamicAnalyzePar(_exp, _mp, _curr, _curr.getPageEl().getAliasPrimBoolean(), _gene), _exp);
        resultLogs(ResultContextLambda.dynamicAnalyzePar(_log, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene), _log);
        resultWatches(ResultContextLambda.dynamicAnalyzePar(_watch, _mp, _curr, _curr.getPageEl().getAliasObject(), _gene), _watch);
    }
    public int pref(String _cl) {
        for (EntryCust<String, Integer> e: prefs.elts()) {
            if (StringUtil.quickEq(_cl,e.getKey())) {
                return e.getValue();
            }
        }
        return pref.get();
    }
    public AbstractAtomicInteger getPref() {
        return pref;
    }

    public AbsCollection<EntryCust<String, Integer>> getPrefs() {
        return prefs;
    }
    public StringMap<Integer> mapPrefs() {
        StringMap<Integer> sm_ = new StringMap<Integer>();
        for (EntryCust<String,Integer> e: prefs.elts()) {
            sm_.addEntry(e.getKey(),e.getValue());
        }
        return sm_;
    }

    public void prefsMap(AbsMap<String,Integer> _elts) {
        prefsMap(prefs,_elts);
    }

    public void setAll(CustList<BreakPointCondition> _elts) {
        setAll(others,_elts);
    }
    public static void prefsMap(AbsCollection<EntryCust<String,Integer>> _collection, AbsMap<String,Integer> _elts) {
        AbsCollection<EntryCust<String,Integer>> conv_ = _collection.intercept().newStringNumberCollection();
        int s_ = _elts.size();
        for (int i = 0; i < s_; i++) {
            conv_.add(_elts.getEntry(i));
        }
        _collection.setAll(conv_);
    }

    public static void setAll(AbsCollection<BreakPointCondition> _collection, CustList<BreakPointCondition> _elts) {
        AbsCollection<BreakPointCondition> conv_ = _collection.intercept().newBreakPointConditionCollection();
        int s_ = _elts.size();
        for (int i = 0; i < s_; i++) {
            conv_.add(_elts.get(i));
        }
        _collection.setAll(conv_);
    }

    public String keyStr() {
        return pad(getKindPoint())+getKeyPoint().keyStr()+"\\"+getPhasePoint();
    }

    public int getKindPoint() {
        return kindPoint;
    }

    public AbsPairPoint getSuperKeyPoint() {
        return superKeyPoint;
    }

    public AbsKeyPoint getKeyPoint() {
        return keyPoint;
    }

    public int getPhasePoint() {
        return phasePoint;
    }

    public static String pad(int _i) {
        if (_i < 10) {
            return "0"+_i;
        }
        return Integer.toString(_i);
    }

    public void resetCount() {
        getEnabled().set(true);
        getHit().set(false);
        getCount().set(0);
    }
    public int incr() {
        return getCount().incrementAndGet();
    }
    public AbsCollection<AbsCallContraints> getExclude() {
        return exclude;
    }

    public AbsCollection<AbsCallContraints> getInclude() {
        return include;
    }
    public void result(ResultContextLambda _p, String _str) {
        StrResultContextLambda s_ = new StrResultContextLambda();
        s_.result(_p, _str);
        lda.set(s_);
    }
    public void resultLogs(ResultContextLambda _p, String _str) {
        StrResultContextLambda s_ = new StrResultContextLambda();
        s_.result(_p, _str);
        logs.set(s_);
    }
    public void resultWatches(ResultContextLambda _p, String _str) {
        StrResultContextLambda s_ = new StrResultContextLambda();
        s_.result(_p, _str);
        watches.set(s_);
    }

    public ResultContextLambda getResult() {
        return lda().getResult();
    }
    public AbstractAtomicBoolean getStackErrLog() {
        return stackErrLog;
    }

    public AbstractAtomicBoolean getStackResErrLog() {
        return stackResErrLog;
    }

    public String getResultStr() {
        return lda().getResultStr();
    }

    public ResultContextLambda getLogs() {
        return logs().getResult();
    }

    public String getLogsStr() {
        return logs().getResultStr();
    }

    public ResultContextLambda getWatches() {
        return watches().getResult();
    }
    public String getWatchesStr() {
        return watches().getResultStr();
    }

    public StrResultContextLambda lda() {
        return lda.get();
    }

    public StrResultContextLambda logs() {
        return logs.get();
    }

    public StrResultContextLambda watches() {
        return watches.get();
    }
    public AbstractAtomicInteger getCountModulo() {
        return countModulo;
    }

    public AbstractAtomicInteger getCount() {
        return count;
    }

    public AbstractAtomicBoolean getDisableWhenHit() {
        return disableWhenHit;
    }

    public AbsCollection<BreakPointCondition> getOthers() {
        return others;
    }

    public AbstractAtomicBoolean getEnabled() {
        return enabled;
    }

    public AbstractAtomicBoolean getDisableAgain() {
        return disableAgain;
    }

    public AbstractAtomicBoolean getHit() {
        return hit;
    }

    public AbstractAtomicBoolean getSuspend() {
        return suspend;
    }

    public AbstractAtomicBoolean getStackLog() {
        return stackLog;
    }
}
