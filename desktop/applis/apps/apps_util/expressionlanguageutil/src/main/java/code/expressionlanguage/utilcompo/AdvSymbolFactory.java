package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.AbstractSymbolFactory;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefSymbolFactory;
import code.expressionlanguage.analyze.opers.util.ParamReturn;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.utilcompo.common.*;
import code.util.CustList;
import code.util.core.StringUtil;

public final class AdvSymbolFactory implements AbstractSymbolFactory {
    private final MathAdvAliases stds;

    public AdvSymbolFactory(MathAdvAliases _s) {
        this.stds = _s;
    }

    @Override
    public ResultOperand generateOperand(String _symbol, AnaClassArgumentMatching _unary, AnalyzedPageEl _page) {
        if (StringUtil.quickEq("-", _symbol) && matchLg(_unary)) {
            ResultOperand res_ = new ResultOperand();
            _unary.setCheckOnlyNullPe(true);
            res_.setSymbol(new CommonOperOppositeRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_unary)));
            res_.setFirst(stds.getAliasRate());
            return res_;
        }
        if (StringUtil.quickEq("+", _symbol) && matchLg(_unary)) {
            ResultOperand res_ = new ResultOperand();
            _unary.setCheckOnlyNullPe(true);
            res_.setSymbol(new CommonOperSameRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_unary)));
            res_.setFirst(stds.getAliasRate());
            return res_;
        }
        if (StringUtil.quickEq("--", _symbol) && matchLg(_unary)) {
            ResultOperand res_ = new ResultOperand();
            _unary.setCheckOnlyNullPe(true);
            res_.setSymbol(new CommonOperMinusOneRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_unary)));
            res_.setFirst(stds.getAliasRate());
            return res_;
        }
        if (StringUtil.quickEq("++", _symbol) && matchLg(_unary)) {
            ResultOperand res_ = new ResultOperand();
            _unary.setCheckOnlyNullPe(true);
            res_.setSymbol(new CommonOperPlusOneRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_unary)));
            res_.setFirst(stds.getAliasRate());
            return res_;
        }
        return DefSymbolFactory.defResult();
    }

    @Override
    public ResultOperand generateOperand(String _symbol, AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        if (StringUtil.quickEq("+", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperSumRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_left, _right)));
            res_.setFirst(stds.getAliasRate());
            res_.setSecond(stds.getAliasRate());
            return res_;
        }
        if (StringUtil.quickEq("-", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperDiffRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_left, _right)));
            res_.setFirst(stds.getAliasRate());
            res_.setSecond(stds.getAliasRate());
            return res_;
        }
        if (StringUtil.quickEq("*", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperMultRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_left, _right)));
            res_.setFirst(stds.getAliasRate());
            res_.setSecond(stds.getAliasRate());
            return res_;
        }
        if (StringUtil.quickEq("/", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperDivRate(matchLgInt(_left, _right)));
            res_.setResult(new AnaClassArgumentMatching(alias(_left, _right)));
            choose(_left, _right, res_);
            return res_;
        }
        if (StringUtil.quickEq("%", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperModRate(matchLgInt(_left, _right)));
            res_.setResult(new AnaClassArgumentMatching(alias(_left, _right)));
            choose(_left, _right, res_);
            return res_;
        }
        return cmp(_symbol, _left, _right, _page);
    }

    private void choose(AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, ResultOperand _res) {
        if (matchLgInt(_left, _right)) {
            _res.setFirst(stds.getAliasLgInt());
            _res.setSecond(stds.getAliasLgInt());
        } else {
            _res.setFirst(stds.getAliasRate());
            _res.setSecond(stds.getAliasRate());
        }
    }

    private ResultOperand cmp(String _symbol, AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        if (StringUtil.quickEq("<", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperNbLtRate());
            return resCmp(_left, _right, _page, res_);
        }
        if (StringUtil.quickEq("<=", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperNbLeRate());
            return resCmp(_left, _right, _page, res_);
        }
        if (StringUtil.quickEq(">", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperNbGtRate());
            return resCmp(_left, _right, _page, res_);
        }
        if (StringUtil.quickEq(">=", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperNbGeRate());
            return resCmp(_left, _right, _page, res_);
        }
        return DefSymbolFactory.defResult();
    }

    private ResultOperand resCmp(AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page, ResultOperand _res) {
        String bool_ = _page.getAliasPrimBoolean();
        _left.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        _right.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        _res.setResult(new AnaClassArgumentMatching(bool_,PrimitiveTypes.BOOL_WRAP));
        _res.setFirst(stds.getAliasRate());
        _res.setSecond(stds.getAliasRate());
        return _res;
    }

    private boolean matchLg(AnaClassArgumentMatching _left, AnaClassArgumentMatching _right) {
        return matchLg(_left) && matchLg(_right);
    }

    private String alias(AnaClassArgumentMatching _l) {
        if (_l.matchClass(stds.getAliasLgInt())) {
            return stds.getAliasLgInt();
        }
        return stds.getAliasRate();
    }

    private String alias(AnaClassArgumentMatching _l, AnaClassArgumentMatching _r) {
        if (matchLgInt(_l,_r)) {
            return stds.getAliasLgInt();
        }
        return stds.getAliasRate();
    }
    private boolean matchLg(AnaClassArgumentMatching _opr) {
        return _opr.matchClass(stds.getAliasRate()) || _opr.matchClass(stds.getAliasLgInt());
    }
    private boolean matchLgInt(AnaClassArgumentMatching _l, AnaClassArgumentMatching _r) {
        return _l.matchClass(stds.getAliasLgInt()) && _r.matchClass(stds.getAliasLgInt());
    }

    @Override
    public CustList<CustList<ParamReturn>> binaries(String _symbol, AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> bin_ = new CustList<CustList<ParamReturn>>();
        if (StringUtil.quickEq("+", _symbol) || StringUtil.quickEq("-", _symbol)||StringUtil.quickEq("*", _symbol) || StringUtil.quickEq("/", _symbol)||StringUtil.quickEq("%", _symbol)){
            CustList<ParamReturn> gr_ = new CustList<ParamReturn>();
            gr_.add(withBinNum(stds.getAliasLgInt(),stds.getAliasLgInt(),true));
            gr_.add(withBinNum(stds.getAliasRate(),stds.getAliasRate(),false));
            bin_.add(gr_);
        }
        if (StringUtil.quickEq("<", _symbol) || StringUtil.quickEq(">", _symbol) || StringUtil.quickEq("<=", _symbol) || StringUtil.quickEq(">=", _symbol)) {
            CustList<ParamReturn> gr_ = new CustList<ParamReturn>();
            String bool_ = _page.getAliasPrimBoolean();
            gr_.add(withCmpNum(stds.getAliasLgInt(),bool_));
            gr_.add(withCmpNum(stds.getAliasRate(),bool_));
            bin_.add(gr_);
        }
        return bin_;
    }

    private ParamReturn withBinNum(String _in, String _out, boolean _all) {
        return new ParamReturn(_in,_out).with("+",new CommonOperSumRate()).with("-",new CommonOperDiffRate()).with("*",new CommonOperMultRate()).with("/",new CommonOperDivRate(_all)).with("%",new CommonOperModRate(_all));
    }

    private static ParamReturn withCmpNum(String _in, String _out) {
        return new ParamReturn(_in,_out).with("<",new CommonOperNbLtRate()).with("<=",new CommonOperNbLeRate()).with(">",new CommonOperNbGtRate()).with(">=",new CommonOperNbGeRate());
    }
    @Override
    public CustList<CustList<ParamReturn>> unaries(String _symbol, AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> un_ = new CustList<CustList<ParamReturn>>();
        if (StringUtil.quickEq("-", _symbol)){
            CustList<ParamReturn> gr_ = new CustList<ParamReturn>();
            gr_.add(withUnNum(stds.getAliasLgInt(),stds.getAliasLgInt()));
            gr_.add(withUnNum(stds.getAliasRate(),stds.getAliasRate()));
            un_.add(gr_);
        }
        if (StringUtil.quickEq("+", _symbol)){
            CustList<ParamReturn> gr_ = new CustList<ParamReturn>();
            gr_.add(withUnNumId(stds.getAliasLgInt(),stds.getAliasLgInt()));
            gr_.add(withUnNumId(stds.getAliasRate(),stds.getAliasRate()));
            un_.add(gr_);
        }
        if (StringUtil.quickEq("--", _symbol)){
            CustList<ParamReturn> gr_ = new CustList<ParamReturn>();
            gr_.add(withUnDec(stds.getAliasLgInt(),stds.getAliasLgInt()));
            gr_.add(withUnDec(stds.getAliasRate(),stds.getAliasRate()));
            un_.add(gr_);
        }
        if (StringUtil.quickEq("++", _symbol)){
            CustList<ParamReturn> gr_ = new CustList<ParamReturn>();
            gr_.add(withUnInc(stds.getAliasLgInt(),stds.getAliasLgInt()));
            gr_.add(withUnInc(stds.getAliasRate(),stds.getAliasRate()));
            un_.add(gr_);
        }
        return un_;
    }

    private ParamReturn withUnNum(String _in, String _out) {
        return new ParamReturn(_in,_out).with("-",new CommonOperOppositeRate());
    }

    private ParamReturn withUnNumId(String _in, String _out) {
        return new ParamReturn(_in,_out).with("+",new CommonOperSameRate());
    }

    private ParamReturn withUnDec(String _in, String _out) {
        return new ParamReturn(_in,_out).with("--",new CommonOperMinusOneRate());
    }

    private ParamReturn withUnInc(String _in, String _out) {
        return new ParamReturn(_in,_out).with("++",new CommonOperPlusOneRate());
    }
}
