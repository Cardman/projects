package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.opers.util.ParamReturn;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.symbol.*;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
import code.util.core.*;

public final class SymbolFactoryUtil {
    private SymbolFactoryUtil(){
    }
    public static ResultOperand generateOperand(String _symbol, AnaClassArgumentMatching _unary, AnalyzedPageEl _page){
        if (StringExpUtil.isUnNum(_symbol)) {
            if (AnaTypeUtil.isPureNumberClass(_unary, _page)) {
                ResultOperand r_ = new ResultOperand();
                AnaClassArgumentMatching cl_ = AnaTypeUtil.toPrimitive(_unary, _page);
                if (AnaTypeUtil.isIntOrderClass(cl_, _page)) {
                    int res_ = AnaTypeUtil.getIntOrderClass(cl_, _page);
                    cl_ = goToAtLeastInt(_page,cl_,res_);
                }
                _unary.setUnwrapObject(cl_, _page.getPrimitiveTypes());
                r_.setResult(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
                r_.setSymbol(unary(_symbol));
                return r_;
            }
            ResultOperand res_ = _page.getAbstractSymbolFactory().generateOperand(_symbol, _unary, _page);
            update(res_,unary(_symbol));
            return res_;
        }
        if (StringUtil.quickEq("!",_symbol)) {
            if (_unary.isBoolType(_page)) {
                String booleanPrimType_ = _page.getAliasPrimBoolean();
                _unary.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
                ResultOperand r_ = new ResultOperand();
                r_.setResult(new AnaClassArgumentMatching(booleanPrimType_,PrimitiveTypes.BOOL_WRAP));
                r_.setSymbol(new CommonOperNegBool());
                return r_;
            }
            ResultOperand res_ = _page.getAbstractSymbolFactory().generateOperand(_symbol, _unary, _page);
            update(res_,new CommonOperNegBool());
            return res_;
        }
        if (StringExpUtil.isIncr(_symbol)) {
            if (AnaTypeUtil.isPureNumberClass(_unary, _page)) {
                ResultOperand r_ = new ResultOperand();
                AnaClassArgumentMatching cl_ = AnaTypeUtil.toPrimitive(_unary, _page);
                _unary.setUnwrapObject(cl_, _page.getPrimitiveTypes());
                r_.setResult(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
                r_.setSymbol(semi(_symbol));
                return r_;
            }
            ResultOperand res_ = _page.getAbstractSymbolFactory().generateOperand(_symbol, _unary, _page);
            update(res_,semi(_symbol));
            return res_;
        }
        if (AnaTypeUtil.getIntOrderClass(_unary, _page) != 0) {
            ResultOperand r_ = new ResultOperand();
            int order_ = AnaTypeUtil.getIntOrderClass(_unary, _page);
            AnaClassArgumentMatching cl_ = AnaTypeUtil.toPrimitive(_unary, _page);
            cl_ = goToAtLeastInt(_page,cl_,order_);
            _unary.setUnwrapObject(cl_, _page.getPrimitiveTypes());
            r_.setResult(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
            r_.setSymbol(new CommonOperNegNum());
            return r_;
        }
        ResultOperand res_ = _page.getAbstractSymbolFactory().generateOperand(_symbol, _unary, _page);
        update(res_,new CommonOperNegNum());
        return res_;
    }
    public static ResultOperand generateOperand(String _symbol, AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page){
        if (StringUtil.quickEq("+",_symbol) && strConvert(_left, _right, _page)) {
            ResultOperand r_ = new ResultOperand();
            r_.setDefConcat(true);
            r_.setResult(new AnaClassArgumentMatching(_page.getAliasString()));
            return r_;
        }
        if (StringExpUtil.isLogical(_symbol)) {
            ResultOperand res_ = logical(_symbol, _left, _right, _page);
            update(res_,logOp(_symbol));
            return res_;
        }
        if (StringExpUtil.isBinNum(_symbol)) {
            ResultOperand res_ = binNum(_symbol, _left, _right, _page);
            update(res_,operBin(_symbol));
            if (StringUtil.quickEq("+",_symbol)) {
                res_.setDefConcat(strOrNull(_left,_page)||strOrNull(_right,_page));
            }
            return res_;
        }
        if (StringExpUtil.isBitwise(_symbol)) {
            ResultOperand res_ = bitwise(_symbol, _left, _right, _page);
            update(res_,operBit(_symbol));
            return res_;
        }
        if (StringExpUtil.isShiftOper(_symbol)) {
            ResultOperand res_ = shiftRot(_symbol, _left, _right, _page);
            update(res_,shiftRotate(_symbol));
            return res_;
        }
        if (StringExpUtil.isCmp(_symbol)) {
            ResultOperand res_ = cmpOp(_symbol, _left, _right, _page);
            update(res_,cmpNb(_symbol));
            return res_;
        }
        if (StringExpUtil.isEq(_symbol)) {
            ResultOperand res_ = eqCall(_symbol, _left, _right, _page);
            update(res_,eq(_symbol));
            return res_;
        }
        ResultOperand r_ = new ResultOperand();
        r_.setSymbol(new CommonOperNullSafe());
        r_.setResult(new AnaClassArgumentMatching(""));
        return r_;
    }

    public static CustList<CustList<ParamReturn>> binaries(String _symbol, AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> res_;
        if (StringExpUtil.isLogical(_symbol)) {
            res_ =groupBinLogical(_page);
        } else if (StringExpUtil.isBinNum(_symbol)) {
            res_ = groupBinNum(_page);
        } else if (StringExpUtil.isBitwise(_symbol)) {
            res_ = groupBinBitwise(_page);
        } else if (StringExpUtil.isShiftOper(_symbol)) {
            res_ = groupBinShift(_page);
        } else if (StringExpUtil.isCmp(_symbol)) {
            res_ = groupBinCmp(_page);
        } else {
            res_ = new CustList<CustList<ParamReturn>>();
        }
        res_.addAllElts(_page.getAbstractSymbolFactory().binaries(_symbol, _page));
        return res_;
    }

    public static CustList<CustList<ParamReturn>> unaries(String _symbol, AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> res_;
        if (StringExpUtil.isUnNum(_symbol) || StringExpUtil.isIncr(_symbol)) {
            res_ = groupUnNum(_page);
        } else if (StringUtil.quickEq("!",_symbol)) {
            res_ = groupBool(_page);
        } else {
            res_ = groupUnBin(_page);
        }
        res_.addAllElts(_page.getAbstractSymbolFactory().unaries(_symbol, _page));
        return res_;
    }

    private static CustList<CustList<ParamReturn>> groupBinCmp(AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> groups_ = new CustList<CustList<ParamReturn>>();
        CustList<ParamReturn> group_ = new CustList<ParamReturn>();
        group_.add(withCmp(_page.getAliasPrimInteger(), _page.getAliasPrimBoolean()));
        group_.add(withCmp(_page.getAliasPrimLong(), _page.getAliasPrimBoolean()));
        groups_.add(group_);
        group_ = new CustList<ParamReturn>();
        group_.add(withCmp(_page.getAliasPrimFloat(), _page.getAliasPrimBoolean()));
        group_.add(withCmp(_page.getAliasPrimDouble(), _page.getAliasPrimBoolean()));
        groups_.add(group_);
        return groups_;
    }
    private static ParamReturn withCmp(String _in, String _out) {
        return new ParamReturn(_in,_out).with("<",new CommonOperNbLt()).with("<=",new CommonOperNbLe()).with(">",new CommonOperNbGt()).with(">=",new CommonOperNbGe());
    }

    private static CustList<CustList<ParamReturn>> groupBinShift(AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> groups_ = new CustList<CustList<ParamReturn>>();
        CustList<ParamReturn> group_ = new CustList<ParamReturn>();
        group_.add(withBinShift(_page.getAliasPrimInteger(), _page.getAliasPrimInteger()));
        group_.add(withBinShift(_page.getAliasPrimLong(), _page.getAliasPrimLong()));
        groups_.add(group_);
        return groups_;
    }
    private static ParamReturn withBinShift(String _in, String _out) {
        return new ParamReturn(_in,_out).with("<<",new CommonOperShiftLeft()).with(">>",new CommonOperShiftRight()).with("<<<",new CommonOperBitShiftLeft()).with(">>>",new CommonOperBitShiftRight()).with("<<<<",new CommonOperRotateLeft()).with(">>>>",new CommonOperRotateRight());
    }

    private static CustList<CustList<ParamReturn>> groupBinBitwise(AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> groups_ = new CustList<CustList<ParamReturn>>();
        CustList<ParamReturn> group_ = new CustList<ParamReturn>();
        group_.add(withBinBitwise(_page.getAliasPrimBoolean(), _page.getAliasPrimBoolean()));
        group_.add(withBinBitwise(_page.getAliasPrimInteger(), _page.getAliasPrimInteger()));
        group_.add(withBinBitwise(_page.getAliasPrimLong(), _page.getAliasPrimLong()));
        groups_.add(group_);
        return groups_;
    }
    private static ParamReturn withBinBitwise(String _in, String _out) {
        return new ParamReturn(_in,_out).with("&",new CommonOperBitAnd()).with("|",new CommonOperBitOr()).with("^",new CommonOperBitXor());
    }

    private static CustList<CustList<ParamReturn>> groupBinNum(AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> groups_ = new CustList<CustList<ParamReturn>>();
        CustList<ParamReturn> group_ = new CustList<ParamReturn>();
        group_.add(withBinNum(_page.getAliasPrimInteger(), _page.getAliasPrimInteger()));
        group_.add(withBinNum(_page.getAliasPrimLong(), _page.getAliasPrimLong()));
        groups_.add(group_);
        group_ = new CustList<ParamReturn>();
        group_.add(withBinNum(_page.getAliasPrimFloat(), _page.getAliasPrimFloat()));
        group_.add(withBinNum(_page.getAliasPrimDouble(), _page.getAliasPrimDouble()));
        groups_.add(group_);
        return groups_;
    }
    private static ParamReturn withBinNum(String _in, String _out) {
        return new ParamReturn(_in,_out).with("+",new CommonOperSum()).with("-",new CommonOperDiff()).with("*",new CommonOperMult()).with("/",new CommonOperDiv()).with("%",new CommonOperMod());
    }

    private static CustList<CustList<ParamReturn>> groupBinLogical(AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> groups_ = new CustList<CustList<ParamReturn>>();
        CustList<ParamReturn> group_ = new CustList<ParamReturn>();
        group_.add(withBinLogical(_page.getAliasPrimBoolean(), _page.getAliasPrimBoolean()));
        groups_.add(group_);
        return groups_;
    }
    private static ParamReturn withBinLogical(String _in, String _out) {
        return new ParamReturn(_in,_out).with("&&",new CommonOperAnd()).with("||",new CommonOperOr());
    }
    private static CustList<CustList<ParamReturn>> groupBool(AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> groups_ = new CustList<CustList<ParamReturn>>();
        CustList<ParamReturn> group_ = new CustList<ParamReturn>();
        group_.add(withNeg(_page.getAliasPrimBoolean(),_page.getAliasPrimBoolean()));
        groups_.add(group_);
        return groups_;
    }
    private static ParamReturn withNeg(String _in, String _out) {
        return new ParamReturn(_in,_out).with("!",new CommonOperNegBool());
    }
    private static CustList<CustList<ParamReturn>> groupUnBin(AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> groups_ = new CustList<CustList<ParamReturn>>();
        CustList<ParamReturn> group_ = new CustList<ParamReturn>();
        group_.add(withNegNum(_page.getAliasPrimInteger(),_page.getAliasPrimInteger()));
        group_.add(withNegNum(_page.getAliasPrimLong(),_page.getAliasPrimLong()));
        groups_.add(group_);
        return groups_;
    }
    private static ParamReturn withNegNum(String _in, String _out) {
        return new ParamReturn(_in,_out).with("~",new CommonOperNegNum());
    }
    private static CustList<CustList<ParamReturn>> groupUnNum(AnalyzedPageEl _page) {
        CustList<CustList<ParamReturn>> groups_ = new CustList<CustList<ParamReturn>>();
        CustList<ParamReturn> group_ = new CustList<ParamReturn>();
        group_.add(withUnNum(_page.getAliasPrimInteger(),_page.getAliasPrimInteger()));
        group_.add(withUnNum(_page.getAliasPrimLong(),_page.getAliasPrimLong()));
        groups_.add(group_);
        group_ = new CustList<ParamReturn>();
        group_.add(withUnNum(_page.getAliasPrimFloat(),_page.getAliasPrimFloat()));
        group_.add(withUnNum(_page.getAliasPrimDouble(),_page.getAliasPrimDouble()));
        groups_.add(group_);
        return groups_;
    }

    private static ParamReturn withUnNum(String _in, String _out) {
        return new ParamReturn(_in,_out).with("+",new CommonOperIdOp()).with("-",new CommonOperOpposite()).with("++",new CommonOperPlusOne()).with("--",new CommonOperMinusOne());
    }
    private static void update(ResultOperand _res, CommonOperSymbol _common) {
        if (_res.getSymbol() == null) {
            _res.setSymbol(_common);
        }
    }
    private static CommonOperSymbol semi(String _symbol) {
        if (StringUtil.quickEq("++",_symbol)) {
            return new CommonOperPlusOne();
        }
        return new CommonOperMinusOne();
    }

    private static CommonOperSymbol unary(String _symbol) {
        if (StringUtil.quickEq("-",_symbol)) {
            return new CommonOperOpposite();
        }
        return new CommonOperIdOp();
    }
    private static ResultOperand eqCall(String _symbol, AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        if (eq(_left, _right, _page)) {
            ResultOperand r_ = new ResultOperand();
            r_.setResult(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            r_.setSymbol(eq(_symbol));
            return r_;
        }
        return _page.getAbstractSymbolFactory().generateOperand(_symbol, _left, _right, _page);
    }

    private static ResultOperand cmpOp(String _symbol, AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        if (AnaTypeUtil.isIntOrderClass(_left, _right, _page) || AnaTypeUtil.isFloatOrderClass(_left, _right, _page)) {
            AnaClassArgumentMatching classFirst_ = AnaTypeUtil.toPrimitive(_left, _page);
            AnaClassArgumentMatching classSecond_ = AnaTypeUtil.toPrimitive(_right, _page);
            _left.setUnwrapObject(classFirst_, _page.getPrimitiveTypes());
            _right.setUnwrapObject(classSecond_, _page.getPrimitiveTypes());
            ResultOperand r_ = new ResultOperand();
            r_.setResult(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            r_.setSymbol(cmpNb(_symbol));
            return r_;
        }
        if (_left.matchClass(_page.getAliasString())
                && _right.matchClass(_page.getAliasString())){
            _left.setCheckOnlyNullPe(true);
            _right.setCheckOnlyNullPe(true);
            ResultOperand r_ = new ResultOperand();
            r_.setResult(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            r_.setSymbol(cmpStr(_symbol));
            return r_;
        }
        return _page.getAbstractSymbolFactory().generateOperand(_symbol, _left, _right, _page);
    }

    private static ResultOperand shiftRot(String _symbol, AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        if (AnaTypeUtil.isIntOrderClass(_left, _right, _page)) {
            ResultOperand r_ = new ResultOperand();
            r_.setResult(getIntResultClass(_left, _right, _page));
            r_.setSymbol(shiftRotate(_symbol));
            _left.setUnwrapObject(r_.getResult(), _page.getPrimitiveTypes());
            _right.setUnwrapObject(r_.getResult(), _page.getPrimitiveTypes());
            return r_;
        }
        return _page.getAbstractSymbolFactory().generateOperand(_symbol, _left, _right, _page);
    }

    private static ResultOperand bitwise(String _symbol, AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        if (allBool(_left, _right, _page)) {
            ResultOperand r_ = new ResultOperand();
            String bool_ = _page.getAliasPrimBoolean();
            _left.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
            _right.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
            r_.setResult(new AnaClassArgumentMatching(bool_,PrimitiveTypes.BOOL_WRAP));
            r_.setSymbol(operBit(_symbol));
            return r_;
        }
        if (AnaTypeUtil.isIntOrderClass(_left, _right, _page)) {
            ResultOperand r_ = new ResultOperand();
            r_.setResult(getIntResultClass(_left, _right, _page));
            r_.setSymbol(operBit(_symbol));
            _left.setUnwrapObject(r_.getResult(), _page.getPrimitiveTypes());
            _right.setUnwrapObject(r_.getResult(), _page.getPrimitiveTypes());
            return r_;
        }
        return _page.getAbstractSymbolFactory().generateOperand(_symbol, _left, _right, _page);
    }

    private static ResultOperand binNum(String _symbol, AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        if (AnaTypeUtil.isIntOrderClass(_left, _right, _page)) {
            ResultOperand r_ = new ResultOperand();
            r_.setResult(getIntResultClass(_left, _right, _page));
            r_.setSymbol(operBin(_symbol));
            _left.setUnwrapObject(r_.getResult(), _page.getPrimitiveTypes());
            _right.setUnwrapObject(r_.getResult(), _page.getPrimitiveTypes());
            return r_;
        }
        if (AnaTypeUtil.isFloatOrderClass(_left, _right, _page)){
            ResultOperand r_ = new ResultOperand();
            r_.setResult(getFloatResultClass(_left, _right, _page));
            r_.setSymbol(operBin(_symbol));
            _left.setUnwrapObject(r_.getResult(), _page.getPrimitiveTypes());
            _right.setUnwrapObject(r_.getResult(), _page.getPrimitiveTypes());
            return r_;
        }
        return _page.getAbstractSymbolFactory().generateOperand(_symbol, _left, _right, _page);
    }

    private static ResultOperand logical(String _symbol, AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        if (allBool(_left, _right, _page)) {
            ResultOperand r_ = new ResultOperand();
            String bool_ = _page.getAliasPrimBoolean();
            _left.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
            _right.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
            r_.setResult(new AnaClassArgumentMatching(bool_,PrimitiveTypes.BOOL_WRAP));
            r_.setSymbol(logOp(_symbol));
            return r_;
        }
        return _page.getAbstractSymbolFactory().generateOperand(_symbol, _left, _right, _page);
    }
    private static CommonOperSymbol logOp(String _symbol) {
        if (StringUtil.quickEq(SymbolConstants.AND_SHORT, _symbol)) {
            return new CommonOperAnd();
        }
        return new CommonOperOr();
    }

    private static boolean allBool(AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        return _left.isBoolType(_page) && _right.isBoolType(_page);
    }

    private static CommonOperSymbol eq(String _symbol) {
        if (StringUtil.quickEq("==", _symbol)) {
            return new CommonOperEq();
        }
        return new CommonOperNonEq();
    }
    private static CommonOperSymbol cmpStr(String _symbol) {
        if (StringUtil.quickEq("<",_symbol)) {
            return new CommonOperStrLt();
        }
        if (StringUtil.quickEq(">",_symbol)) {
            return new CommonOperStrGt();
        }
        if (StringUtil.quickEq("<=",_symbol)) {
            return new CommonOperStrLe();
        }
        return new CommonOperStrGe();
    }

    private static CommonOperSymbol cmpNb(String _symbol) {
        if (StringUtil.quickEq("<",_symbol)) {
            return new CommonOperNbLt();
        }
        if (StringUtil.quickEq(">",_symbol)) {
            return new CommonOperNbGt();
        }
        if (StringUtil.quickEq("<=",_symbol)) {
            return new CommonOperNbLe();
        }
        return new CommonOperNbGe();
    }
    private static CommonOperSymbol shiftRotate(String _symbol) {
        if (StringUtil.quickEq("<<",_symbol)) {
            return new CommonOperShiftLeft();
        }
        if (StringUtil.quickEq(">>",_symbol)) {
            return new CommonOperShiftRight();
        }
        if (StringUtil.quickEq("<<<",_symbol)) {
            return new CommonOperBitShiftLeft();
        }
        if (StringUtil.quickEq(">>>",_symbol)) {
            return new CommonOperBitShiftRight();
        }
        if (StringUtil.quickEq("<<<<",_symbol)) {
            return new CommonOperRotateLeft();
        }
        return new CommonOperRotateRight();
    }
    private static CommonOperSymbol operBin(String _symbol) {
        if (StringUtil.quickEq("+",_symbol)) {
            return new CommonOperSum();
        }
        if (StringUtil.quickEq("-",_symbol)) {
            return new CommonOperDiff();
        }
        if (StringUtil.quickEq("*",_symbol)) {
            return new CommonOperMult();
        }
        if (StringUtil.quickEq("/",_symbol)) {
            return new CommonOperDiv();
        }
        return new CommonOperMod();
    }

    private static CommonOperSymbol operBit(String _symbol) {
        if (StringUtil.quickEq("&",_symbol)) {
            return new CommonOperBitAnd();
        }
        if (StringUtil.quickEq("|",_symbol)) {
            return new CommonOperBitOr();
        }
        return new CommonOperBitXor();
    }
    private static AnaClassArgumentMatching getIntResultClass(AnaClassArgumentMatching _a, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        int oa_ = AnaTypeUtil.getIntOrderClass(_a, _page);
        int ob_ = AnaTypeUtil.getIntOrderClass(_b, _page);
        int max_ = NumberUtil.max(oa_, ob_);
        AnaClassArgumentMatching arg_ = getMaxWrap(_a, oa_, _b, ob_);
        return AnaTypeUtil.toPrimitive(goToAtLeastInt(_page,arg_,max_), _page);
    }
    private static AnaClassArgumentMatching goToAtLeastInt(AnalyzedPageEl _page, AnaClassArgumentMatching _before, int _value) {
        AnaClassArgumentMatching after_ = _before;
        int intOrder_ = AnaTypeUtil.getIntOrderClass(_page.getAliasPrimInteger(), _page);
        if (_value < intOrder_) {
            after_ = new AnaClassArgumentMatching(_page.getAliasPrimInteger(), PrimitiveTypes.INT_WRAP);
        }
        return after_;
    }
    private static AnaClassArgumentMatching getFloatResultClass(AnaClassArgumentMatching _a, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        int oa_ = AnaTypeUtil.getFloatOrderClass(_a, _page);
        int ob_ = AnaTypeUtil.getFloatOrderClass(_b, _page);
        AnaClassArgumentMatching arg_ = getMaxWrap(_a, oa_, _b, ob_);
        return AnaTypeUtil.toPrimitive(arg_, _page);
    }

    private static AnaClassArgumentMatching getMaxWrap(AnaClassArgumentMatching _a, int _oa, AnaClassArgumentMatching _b, int _ob) {
        AnaClassArgumentMatching arg_;
        if (_oa > _ob) {
            arg_ = _a;
        } else {
            arg_ = _b;
        }
        return arg_;
    }
    private static boolean eq(AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        if (AnaTypeUtil.isIntOrderClass(_left, _right, _page)) {
            return true;
        }
        if (AnaTypeUtil.isFloatOrderClass(_left, _right, _page)) {
            return true;
        }
        if (_left.matchClass(_page.getAliasNumber())
                && _right.matchClass(_page.getAliasNumber())) {
            return true;
        }
        if (_left.isBoolType(_page)&& _right.isBoolType(_page)) {
            return true;
        }
        if (_left.matchClass(_page.getAliasString())
                && _right.matchClass(_page.getAliasString())) {
            return true;
        }
        return _left.matchClass(_page.getAliasObject())
                && _right.matchClass(_page.getAliasObject());
    }

    private static boolean strConvert(AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        if (_left.matchClass(_page.getAliasString())) {
            return strPlusLeft(_right, _page);
        }
        if (_right.matchClass(_page.getAliasString())) {
            return strPlusRight(_left, _page);
        }
        return false;
    }
    private static boolean strOrNull(AnaClassArgumentMatching _un, AnalyzedPageEl _page) {
        return _un.matchClass(_page.getAliasString()) || _un.isVariable();
    }

    private static boolean strPlusRight(AnaClassArgumentMatching _left, AnalyzedPageEl _page) {
        if (_left.matchClass(_page.getAliasNumber())) {
            return true;
        }
        if (AnaTypeUtil.isPureNumberClass(_left, _page)) {
            return true;
        }
        if (_left.isBoolType(_page)) {
            return true;
        }
        return _left.matchClass(_page.getAliasObject());
    }

    private static boolean strPlusLeft(AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        if (_right.matchClass(_page.getAliasNumber())) {
            return true;
        }
        if (AnaTypeUtil.isPureNumberClass(_right, _page)) {
            return true;
        }
        if (_right.isBoolType(_page)) {
            return true;
        }
        if (_right.matchClass(_page.getAliasString())) {
            return true;
        }
        if (_right.matchClass(_page.getAliasStringBuilder())) {
            return true;
        }
        return _right.matchClass(_page.getAliasObject());
    }
}
