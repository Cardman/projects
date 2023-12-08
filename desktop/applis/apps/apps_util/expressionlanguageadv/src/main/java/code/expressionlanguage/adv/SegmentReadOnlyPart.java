package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.blocks.NamedCalledFunctionBlock;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.files.AbsSegmentColorPart;
import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.util.CustList;

public final class SegmentReadOnlyPart extends AbsSegmentColorPart {
    private final SyntaxRefEnum kind;
    public SegmentReadOnlyPart(int _begin, int _end, SyntaxRefEnum _k) {
        super(_begin, _end);
        this.kind = _k;
    }
    public CustList<SegmentReadOnlyPart> parts(MemberCallingsBlock _fct) {
        CustList<ResultExpression> all_ = new CustList<ResultExpression>();
        if (_fct instanceof NamedFunctionBlock) {
            feed(all_, ((NamedFunctionBlock)_fct).getAnnotations().getAnnotations());
            for (ResultParsedAnnots r: ((NamedFunctionBlock)_fct).getAnnotationsParams()) {
                feed(all_, r.getAnnotations());
            }
        }
        if (_fct instanceof NamedCalledFunctionBlock) {
            feed(all_, ((NamedCalledFunctionBlock)_fct).getAnnotationsSupp().getAnnotations());
        }
        CustList<SegmentReadOnlyPart> out_ = new CustList<SegmentReadOnlyPart>();
        int b_ = getBegin();
        for (ResultExpression r: all_) {
            int e_ = r.getSumOffset();
            filter(out_, b_, e_);
            b_ = e_ + r.getAnalyzedString().length();
        }
        filter(out_, b_,getEnd());
        return out_;
    }

    private void filter(CustList<SegmentReadOnlyPart> _out, int _b, int _e) {
        if (_b >= _e) {
            return;
        }
        _out.add(new SegmentReadOnlyPart(_b,_e,kind));
    }

    private void feed(CustList<ResultExpression> _all, CustList<ResultParsedAnnot> _ls) {
        for (ResultParsedAnnot r: _ls) {
            _all.add(r.getRes());
        }
    }

    public SyntaxRefEnum getKind() {
        return kind;
    }
}
