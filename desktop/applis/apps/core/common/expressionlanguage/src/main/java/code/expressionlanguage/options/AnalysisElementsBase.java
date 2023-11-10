package code.expressionlanguage.options;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.CustList;

public final class AnalysisElementsBase {
    private final Forwards fwd;
    private final AnalysisMessages mess;
    private final KeyWords definedKw;
    private final CustList<CommentDelimiters> comments;
    private final Options options;
    private final LgNamesContent content;
    private final AnalyzedPageEl page;
    private AbsBuildLightResultContextNext lightResultContextNext;

    public AnalysisElementsBase(Forwards _f, AnalysisMessages _m, KeyWords _k, CustList<CommentDelimiters> _c, Options _o, LgNamesContent _n, AnalyzedPageEl _p) {
        this.fwd = _f;
        this.mess = _m;
        this.definedKw = _k;
        this.comments = _c;
        this.options = _o;
        this.content = _n;
        this.page = _p;
        setLightResultContextNext(new DefBuildLightResultContextNext());
    }

    public AbsBuildLightResultContextNext getLightResultContextNext() {
        return lightResultContextNext;
    }

    public void setLightResultContextNext(AbsBuildLightResultContextNext _l) {
        this.lightResultContextNext = _l;
    }

    public AnalyzedPageEl getPage() {
        return page;
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public Options getOptions() {
        return options;
    }

    public Forwards getFwd() {
        return fwd;
    }

    public AnalysisMessages getMess() {
        return mess;
    }

    public KeyWords getDefinedKw() {
        return definedKw;
    }

    public LgNamesContent getContent() {
        return content;
    }
}
