package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.sml.Element;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ResultText {

    public static final char ESCAPED = '\\';
    public static final char RIGHT_EL = '}';
    public static final char LEFT_EL = '{';
    private static final String CALL_METHOD = "$";
    private CustList<OperationNode> opExpRoot;
    private ClassMethodIdReturn resultAnc;

    private final StringList argTypes = new StringList();
    private StringList texts = new StringList();
    private String sgn = "";
    private final ResultExpression resultExpression = new ResultExpression();
    private final StringMap<ResultExpression> results = new StringMap<ResultExpression>();

    public static String check(AnalyzedPageEl _page, int _rowsGrId, ClassMethodIdReturn _classMethodIdReturn) {
        if (_classMethodIdReturn == null) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(_rowsGrId);
            badEl_.buildError("");
            AnalyzingDoc.addError(badEl_, _page);
            return _page.getAliasObject();
        }
        return _classMethodIdReturn.getReturnType();
    }

    public void buildIdAna(int _begin, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setSumOffset(resultExpression.getSumOffset());
        _page.zeroOffset();
        String exp_ = resultExpression.getAnalyzedString();
        opExpRoot = new CustList<OperationNode>();
        StringBuilder str_ = new StringBuilder();
        int length_ = exp_.length();
        boolean escaped_ = false;
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = exp_.charAt(i_);
            if (escaped_) {
                if (isDel(cur_)) {
                    escaped_ = false;
                    str_.append(cur_);
                    i_++;
                    continue;
                }
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(_begin);
                badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                        Character.toString(cur_),
                        Long.toString(i_),
                        exp_);
                AnalyzingDoc.addError(badEl_, _page);
                return;
            }
            if (cur_ == ESCAPED) {
                escaped_ = true;
                i_++;
            } else if (cur_ == LEFT_EL) {
                texts.add(str_.toString());
                str_.delete(0,str_.length());
                i_++;
                if (isRightBoundOrFar(exp_, i_)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(_begin);
                    badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                            " ",
                            Long.toString(i_),
                            exp_);
                    AnalyzingDoc.addError(badEl_, _page);
                    return;
                }
//                _conf.getLastPage().setOffset(i_);
                OperationNode opsLoc_ = RenderAnalysis.getRootAnalyzedOperationsDel(i_, _anaDoc, _page,resultExpression);
                opExpRoot.add(opsLoc_);
                i_ = _anaDoc.getNextIndex();
            } else if (cur_ == RIGHT_EL){
//                _conf.getLastPage().setOffset(i_);
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(_begin);
                badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                        " ",
                        Long.toString(i_),
                        exp_);
                AnalyzingDoc.addError(badEl_, _page);
                return;
            } else {
                str_.append(cur_);
                i_++;
            }
        }
        texts.add(str_.toString());
    }

    public static boolean isRightBoundOrFar(String _exp, int _index) {
        int length_ = _exp.length();
        return _index >= length_ || _exp.charAt(_index) == RIGHT_EL;
    }

    public static boolean isDel(char _cur) {
        return _cur == ESCAPED || _cur == LEFT_EL || _cur == RIGHT_EL;
    }

    public static void buildAnchor(AnaRendBlock _r, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page, ResultText _res) {
        String href_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        _res.opExpRoot = new CustList<OperationNode>();
        _res.texts = new StringList();
        if (href_.startsWith(CALL_METHOD)) {
            String lk_ = href_.substring(1);
            int colsGrId_ = _r.getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
            for (EntryCust<String,ResultExpression> e: _res.getResults().entryList()) {
                _page.zeroOffset();
                _page.setSumOffset(e.getValue().getSumOffset());
                RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page, e.getValue());
            }
            if (StringExpUtil.isDollarWord(lk_)) {
                StringList argCla_ = feedArgs(_res,_page);
                _page.zeroOffset();
                _page.setSumOffset(colsGrId_);
                ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), lk_, argCla_, _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()));
                _res.resultAnc = classMethodIdReturn_;
                check(_page,colsGrId_,classMethodIdReturn_);
                _res.sgn = AnaRendBlock.toSgn(classMethodIdReturn_,_page);
                _read.setAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrSgn()),_res.sgn);
                return;
            }
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(colsGrId_);
            badEl_.buildError("");
            AnalyzingDoc.addError(badEl_, _page);
        }
    }

    public static StringList feedArgs(ResultText _res, AnalyzedPageEl _page) {
        StringList argCla_ = new StringList();
        for (EntryCust<String,ResultExpression> e: _res.getResults().entryList()) {
            String singleNameOrEmpty_ = voidToObject(e.getValue().getRoot().getResultClass().getSingleNameOrEmpty(),_page);
            argCla_.add(singleNameOrEmpty_);
            _res.argTypes.add(singleNameOrEmpty_);
        }
        return argCla_;
    }

    public static String voidToObject(String _original, AnalyzedPageEl _page) {
        String v_ = StringUtil.nullToEmpty(_original);
        if (v_.isEmpty()) {
            return _page.getAliasObject();
        }
        return v_;
    }
    public ResultExpression getResultExpression() {
        return resultExpression;
    }

    public StringList getTexts() {
        return texts;
    }

    public CustList<OperationNode> getOpExpRoot() {
        return opExpRoot;
    }

    public StringMap<ResultExpression> getResults() {
        return results;
    }

    public ClassMethodIdReturn getResultAnc() {
        return resultAnc;
    }

    public void setResultAnc(ClassMethodIdReturn _res) {
        this.resultAnc = _res;
    }

    public StringList getArgTypes() {
        return argTypes;
    }
}
