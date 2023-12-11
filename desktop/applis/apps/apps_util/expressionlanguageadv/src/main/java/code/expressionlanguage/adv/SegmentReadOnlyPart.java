package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.AbsSegmentColorPart;
import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
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
            if (AbsBk.isAnonBlock(_fct)) {
                for (ResultParsedAnnots r: ((NamedFunctionBlock)_fct).getAnnotationsParams()) {
                    feed(all_, r.getAnnotations());
                }
                feed(all_, ((NamedFunctionBlock)_fct).getAnnotations().getAnnotations());
            } else {
                feed(all_, ((NamedFunctionBlock)_fct).getAnnotations().getAnnotations());
                for (ResultParsedAnnots r: ((NamedFunctionBlock)_fct).getAnnotationsParams()) {
                    feed(all_, r.getAnnotations());
                }
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
    public CustList<SegmentReadOnlyPart> parts(ResultExpression _res) {
        CustList<SegmentReadOnlyPart> o_ = new CustList<SegmentReadOnlyPart>();
        int b_ = _res.getSumOffset();
        OperationNode r_ = _res.getRoot();
        OperationNode c_ = r_;
        while (c_ != null) {
            b_ = nextBeginFirstOp(o_,b_,c_);
            OperationNode f_ = c_.getFirstChild();
            if (f_ != null) {
                c_ = f_;
                continue;
            }
            while (c_ != null) {
                OperationNode n_ = c_.getNextSibling();
                if (n_ != null) {
                    c_ = n_;
                    break;
                }
                MethodOperation p_ = c_.getParent();
                b_ = nextBeginLastOp(_res, o_, b_, c_, p_);
                if (p_ == r_) {
                    c_ = null;
                } else {
                    c_ = p_;
                }
            }
        }
        filter(o_,b_,_res.end());
        return o_;
    }

    private int nextBeginFirstOp(CustList<SegmentReadOnlyPart> _o, int _b, OperationNode _c) {
        int b_ = _b;
        if (_c instanceof AnonymousInstancingOperation) {
            CustList<ResultExpression> res_ = new CustList<ResultExpression>();
            feed(res_,((AnonymousInstancingOperation)_c).getBlock().getAnnotations().getAnnotations());
            for (ResultExpression r: res_) {
                int e_ = r.getSumOffset();
                filter(_o, b_, e_);
                b_ = e_ + r.getAnalyzedString().length();
            }
        }
        if (_c instanceof SwitchOperation) {
            CustList<ResultExpression> res_ = new CustList<ResultExpression>();
            feed(res_,((SwitchOperation)_c).getSwitchMethod().getAnnotations().getAnnotations());
            for (ResultParsedAnnots r: ((SwitchOperation)_c).getSwitchMethod().getAnnotationsParams()) {
                feed(res_, r.getAnnotations());
            }
            for (ResultExpression r: res_) {
                int e_ = r.getSumOffset();
                filter(_o, b_, e_);
                b_ = e_ + r.getAnalyzedString().length();
            }
        }
        if (_c instanceof AnonymousLambdaOperation) {
            NamedCalledFunctionBlock anon_ = ((AnonymousLambdaOperation) _c).getBlock();
            int head_ = anon_.getPlace();
            int arrow_ = anon_.getNameOffset();
            filter(_o, b_, head_);
            filter(_o, arrow_, arrow_ + 2);
            b_ = anon_.getIndexEnd();
        }
        return b_;
    }

    private int nextBeginLastOp(ResultExpression _res, CustList<SegmentReadOnlyPart> _o, int _b, OperationNode _c, MethodOperation _p) {
        int b_ = _b;
        if (_p instanceof AnonymousInstancingOperation) {
            filter(_o, b_,ResultExpressionOperationNode.end(_res, _c));
            b_ = ((AnonymousInstancingOperation) _p).getBlock().getIndexEnd();
        }
        if (_p instanceof SwitchOperation) {
            filter(_o, b_,ResultExpressionOperationNode.end(_res, _c));
            b_ = ((SwitchOperation) _p).getSwitchMethod().getIndexEnd();
        }
        return b_;
    }

    private void filter(CustList<SegmentReadOnlyPart> _out, int _b, int _e) {
        filter(_out, _b, _e, kind);
    }

    public static void filter(CustList<SegmentReadOnlyPart> _out, int _b, int _e, SyntaxRefEnum _kind) {
        if (_b >= _e) {
            return;
        }
        _out.add(new SegmentReadOnlyPart(_b, _e, _kind));
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
