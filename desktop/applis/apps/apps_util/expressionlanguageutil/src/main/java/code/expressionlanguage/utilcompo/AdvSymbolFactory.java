package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.AbstractSymbolFactory;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefSymbolFactory;
import code.expressionlanguage.analyze.opers.util.ParamReturn;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.utilcompo.common.*;
import code.util.CustList;
import code.util.core.StringUtil;

public final class AdvSymbolFactory implements AbstractSymbolFactory {
    private final LgNamesWithNewAliases stds;

    public AdvSymbolFactory(LgNamesWithNewAliases _s) {
        this.stds = _s;
    }

    @Override
    public ResultOperand generateOperand(String _symbol, AnaClassArgumentMatching _unary, AnalyzedPageEl _page) {
        if (StringUtil.quickEq("-", _symbol) && matchLg(_unary)) {
            ResultOperand res_ = new ResultOperand();
            _unary.setCheckOnlyNullPe(true);
            res_.setSymbol(new CommonOperOppositeRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_unary)));
            return res_;
        }
        if (StringUtil.quickEq("+", _symbol) && matchLg(_unary)) {
            ResultOperand res_ = new ResultOperand();
            _unary.setCheckOnlyNullPe(true);
            res_.setSymbol(new CommonOperSameRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_unary)));
            return res_;
        }
        if (StringUtil.quickEq("--", _symbol) && matchLg(_unary)) {
            ResultOperand res_ = new ResultOperand();
            _unary.setCheckOnlyNullPe(true);
            res_.setSymbol(new CommonOperMinusOneRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_unary)));
            return res_;
        }
        if (StringUtil.quickEq("++", _symbol) && matchLg(_unary)) {
            ResultOperand res_ = new ResultOperand();
            _unary.setCheckOnlyNullPe(true);
            res_.setSymbol(new CommonOperPlusOneRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_unary)));
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
            return res_;
        }
        if (StringUtil.quickEq("-", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperDiffRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_left, _right)));
            return res_;
        }
        if (StringUtil.quickEq("*", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperMultRate());
            res_.setResult(new AnaClassArgumentMatching(alias(_left, _right)));
            return res_;
        }
        if (StringUtil.quickEq("/", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperDivRate(matchLgInt(_left, _right)));
            res_.setResult(new AnaClassArgumentMatching(alias(_left, _right)));
            return res_;
        }
        if (StringUtil.quickEq("%", _symbol) && matchLg(_left, _right)) {
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand res_ = new ResultOperand();
            res_.setSymbol(new CommonOperModRate(matchLgInt(_left, _right)));
            res_.setResult(new AnaClassArgumentMatching(alias(_left, _right)));
            return res_;
        }
        return DefSymbolFactory.defResult();
    }

    private boolean matchLg(AnaClassArgumentMatching _left, AnaClassArgumentMatching _right) {
        return matchLg(_left) && matchLg(_right);
    }

    private String alias(AnaClassArgumentMatching _l) {
        if (_l.matchClass(stds.getCustAliases().getAliasLgInt())) {
            return stds.getCustAliases().getAliasLgInt();
        }
        return stds.getCustAliases().getAliasRate();
    }

    private String alias(AnaClassArgumentMatching _l, AnaClassArgumentMatching _r) {
        if (matchLgInt(_l,_r)) {
            return stds.getCustAliases().getAliasLgInt();
        }
        return stds.getCustAliases().getAliasRate();
    }
    private boolean matchLg(AnaClassArgumentMatching _opr) {
        return _opr.matchClass(stds.getCustAliases().getAliasRate()) || _opr.matchClass(stds.getCustAliases().getAliasLgInt());
    }
    private boolean matchLgInt(AnaClassArgumentMatching _l, AnaClassArgumentMatching _r) {
        return _l.matchClass(stds.getCustAliases().getAliasLgInt()) && _r.matchClass(stds.getCustAliases().getAliasLgInt());
    }

    @Override
    public CustList<CustList<ParamReturn>> binaries(String _symbol, AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> bin_ = new CustList<CustList<ParamReturn>>();
        if (StringUtil.quickEq("+", _symbol) || StringUtil.quickEq("-", _symbol)||StringUtil.quickEq("*", _symbol) || StringUtil.quickEq("/", _symbol)||StringUtil.quickEq("%", _symbol)){
            CustList<ParamReturn> gr_ = new CustList<ParamReturn>();
            gr_.add(withBinNum(stds.getCustAliases().getAliasLgInt(),stds.getCustAliases().getAliasLgInt(),true));
            gr_.add(withBinNum(stds.getCustAliases().getAliasRate(),stds.getCustAliases().getAliasRate(),false));
            bin_.add(gr_);
        }
        return bin_;
    }

    private static ParamReturn withBinNum(String _in, String _out, boolean _all) {
        return new ParamReturn(_in,_out).with("+",new CommonOperSumRate()).with("-",new CommonOperDiffRate()).with("*",new CommonOperMultRate()).with("/",new CommonOperDivRate(_all)).with("%",new CommonOperModRate(_all));
    }
    @Override
    public CustList<CustList<ParamReturn>> unaries(String _symbol, AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> un_ = new CustList<CustList<ParamReturn>>();
        if (StringUtil.quickEq("-", _symbol)){
            CustList<ParamReturn> gr_ = new CustList<ParamReturn>();
            gr_.add(withUnNum(stds.getCustAliases().getAliasLgInt(),stds.getCustAliases().getAliasLgInt()));
            gr_.add(withUnNum(stds.getCustAliases().getAliasRate(),stds.getCustAliases().getAliasRate()));
            un_.add(gr_);
        }
        if (StringUtil.quickEq("+", _symbol)){
            CustList<ParamReturn> gr_ = new CustList<ParamReturn>();
            gr_.add(withUnNumId(stds.getCustAliases().getAliasLgInt(),stds.getCustAliases().getAliasLgInt()));
            gr_.add(withUnNumId(stds.getCustAliases().getAliasRate(),stds.getCustAliases().getAliasRate()));
            un_.add(gr_);
        }
        if (StringUtil.quickEq("--", _symbol)){
            CustList<ParamReturn> gr_ = new CustList<ParamReturn>();
            gr_.add(withUnDec(stds.getCustAliases().getAliasLgInt(),stds.getCustAliases().getAliasLgInt()));
            gr_.add(withUnDec(stds.getCustAliases().getAliasRate(),stds.getCustAliases().getAliasRate()));
            un_.add(gr_);
        }
        if (StringUtil.quickEq("++", _symbol)){
            CustList<ParamReturn> gr_ = new CustList<ParamReturn>();
            gr_.add(withUnInc(stds.getCustAliases().getAliasLgInt(),stds.getCustAliases().getAliasLgInt()));
            gr_.add(withUnInc(stds.getCustAliases().getAliasRate(),stds.getCustAliases().getAliasRate()));
            un_.add(gr_);
        }
        return un_;
    }

    private static ParamReturn withUnNum(String _in, String _out) {
        return new ParamReturn(_in,_out).with("-",new CommonOperOppositeRate());
    }

    private static ParamReturn withUnNumId(String _in, String _out) {
        return new ParamReturn(_in,_out).with("+",new CommonOperSameRate());
    }

    private static ParamReturn withUnDec(String _in, String _out) {
        return new ParamReturn(_in,_out).with("--",new CommonOperMinusOneRate());
    }

    private static ParamReturn withUnInc(String _in, String _out) {
        return new ParamReturn(_in,_out).with("++",new CommonOperPlusOneRate());
    }
}
