package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.sml.Element;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ResultText {

    private static final String CALL_METHOD = "$";
    private static final char ESCAPED = '\\';
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private CustList<OperationNode> opExpRoot;
    private ClassMethodIdReturn resultAnc;

    private StringList texts = new StringList();
    private String sgn = "";
    private final ResultExpression resultExpression = new ResultExpression();
    private final StringMap<ResultExpression> results = new StringMap<ResultExpression>();

    public static String check(AnalyzedPageEl _page, int rowsGrId_, ClassMethodIdReturn classMethodIdReturn_) {
        if (classMethodIdReturn_ == null) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(rowsGrId_);
            badEl_.buildError("");
            AnalyzingDoc.addError(badEl_, _page);
            return _page.getAliasObject();
        }
        return classMethodIdReturn_.getReturnType();
    }

    public void buildIdAna(String _expression, int _begin, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(_begin);
        _page.zeroOffset();
        opExpRoot = new CustList<OperationNode>();
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        boolean escaped_ = false;
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _expression.charAt(i_);
            if (escaped_) {
                if (cur_ == ESCAPED) {
                    escaped_ = false;
                    str_.append(ESCAPED);
                    i_++;
                    continue;
                }
                if (cur_ == LEFT_EL) {
                    escaped_ = false;
                    str_.append(LEFT_EL);
                    i_++;
                    continue;
                }
                if (cur_ == RIGHT_EL) {
                    escaped_ = false;
                    str_.append(RIGHT_EL);
                    i_++;
                    continue;
                }
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(_begin);
                badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                        Character.toString(cur_),
                        Long.toString(i_),
                        _expression);
                AnalyzingDoc.addError(badEl_, _page);
                return;
            }
            if (cur_ == ESCAPED) {
                escaped_ = true;
                i_++;
                continue;
            }
            if (cur_ == LEFT_EL) {
                texts.add(str_.toString());
                str_.delete(0,str_.length());
                i_++;
                if (i_ >= length_ || _expression.charAt(i_) == RIGHT_EL) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(_begin);
                    badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                            " ",
                            Long.toString(i_),
                            _expression);
                    AnalyzingDoc.addError(badEl_, _page);
                    return;
                }
//                _conf.getLastPage().setOffset(i_);
                OperationNode opsLoc_ = RenderAnalysis.getRootAnalyzedOperationsDel(_expression, i_, _anaDoc, _page,resultExpression);
                opExpRoot.add(opsLoc_);
                i_ = _anaDoc.getNextIndex();
                continue;
            }
            if (cur_ == RIGHT_EL){
//                _conf.getLastPage().setOffset(i_);
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(_begin);
                badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                        " ",
                        Long.toString(i_),
                        _expression);
                AnalyzingDoc.addError(badEl_, _page);
                return;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }

    public static void buildAnchor(AnaRendBlock _r, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page, ResultText _res) {
        String href_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        _res.opExpRoot = new CustList<OperationNode>();
        _res.texts = new StringList();
        if (href_.startsWith(CALL_METHOD)) {
            String lk_ = href_.substring(1);
            int colsGrId_ = _r.getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
            for (EntryCust<String,ResultExpression> e: _res.getResults().entryList()) {
                int param_ = _r.getAttributeDelimiter(e.getKey());
                _page.zeroOffset();
                _page.setGlobalOffset(param_);
                String attribute_ = _read.getAttribute(e.getKey());
                OperationNode res_ = RenderAnalysis.getRootAnalyzedOperations(attribute_, 0, _anaDoc, _page, e.getValue());
                Mapping m_ = new Mapping();
                m_.setArg(res_.getResultClass());
                m_.setParam(_page.getAliasLong());
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(param_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(res_.getResultClass().getNames(),AnaRendBlock.AND_ERR),
                            _page.getAliasLong());
                    AnalyzingDoc.addError(badEl_, _page);
                }
            }
            if (StringExpUtil.isDollarWord(lk_)) {
                StringList argCla_ = new StringList();
                for (EntryCust<String,ResultExpression> e: _res.getResults().entryList()) {
                    String singleNameOrEmpty_ = e.getValue().getRoot().getResultClass().getSingleNameOrEmpty();
                    argCla_.add(singleNameOrEmpty_);
                }
                _page.zeroOffset();
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
}
