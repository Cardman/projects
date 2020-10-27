package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.sml.Element;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ResultText {

    private static final String CALL_METHOD = "$";
    private static final char ESCAPED = '\\';
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private static final char QUOTE = 39;
    private CustList<OperationNode> opExpRoot;
    private OperationNode opExpAnchorRoot;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    private Ints expOffsets = new Ints();
    private Ints expEnds = new Ints();

    public void buildAna(String _expression, int _off, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        opExpRoot = new CustList<OperationNode>();
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        boolean escaped_ = false;
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _expression.charAt(i_);
            if (escaped_) {
                if (cur_ == QUOTE) {
                    if (i_ < length_ - 1) {
                        if (_expression.charAt(i_ + 1) == QUOTE) {
                            str_.append(QUOTE);
                            i_++;
                            i_++;
                            continue;
                        }
                    }
                    escaped_ = false;
                } else {
                    str_.append(cur_);
                }
                i_++;
                continue;
            }
            if (cur_ == QUOTE) {
                if (i_ < length_ - 1) {
                    if (_expression.charAt(i_ + 1) == QUOTE) {
                        str_.append(QUOTE);
                        i_++;
                        i_++;
                        continue;
                    }
                }
                escaped_ = true;
                i_++;
                continue;
            }
            if (cur_ == LEFT_EL) {
                texts.add(str_.toString());
                str_.delete(0,str_.length());
                expOffsets.add(i_);
                i_++;
                if (i_ >= length_ || _expression.charAt(i_) == RIGHT_EL) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(_off);
                    badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                            Character.toString(RIGHT_EL),
                            Long.toString(i_),
                            _expression);
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                    return;
                }
                OperationNode root_ = RenderAnalysis.getRootAnalyzedOperationsDel(_expression, i_, _anaDoc, _page);
                opExpRoot.add(root_);
                i_ = _anaDoc.getNextIndex();
                expEnds.add(i_);
                continue;
            }
            if (cur_ == RIGHT_EL){
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(_off);
                badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                        Character.toString(RIGHT_EL),
                        Long.toString(i_),
                        _expression);
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                return;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }

    public void buildIdAna(String _expression, int _begin, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
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
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(_begin);
                badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                        Character.toString(cur_),
                        Long.toString(i_),
                        _expression);
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
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
                expOffsets.add(i_);
                i_++;
                if (i_ >= length_ || _expression.charAt(i_) == RIGHT_EL) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(_begin);
                    badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                            " ",
                            Long.toString(i_),
                            _expression);
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                    return;
                }
//                _conf.getLastPage().setOffset(i_);
                OperationNode opsLoc_ = RenderAnalysis.getRootAnalyzedOperationsDel(_expression, i_, _anaDoc, _page);
                opExpRoot.add(opsLoc_);
                i_ = _anaDoc.getNextIndex();
                expEnds.add(i_);
                continue;
            }
            if (cur_ == RIGHT_EL){
//                _conf.getLastPage().setOffset(i_);
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(_begin);
                badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                        " ",
                        Long.toString(i_),
                        _expression);
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                return;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }

    public static ResultText buildAnchor(AnaRendBlock _r, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrHref());
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        String href_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        ResultText r_ = new ResultText();
        r_.opExpRoot = new CustList<OperationNode>();
        r_.texts = new StringList();
        if (href_.startsWith(CALL_METHOD)) {
            String lk_ = href_.substring(1);
            int colsGrId_ = _r.getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
            r_.buildAna(lk_, colsGrId_, _anaDoc, _page);
            CustList<OperationNode> opExpRoot_ = r_.getOpExpRoot();
            for (OperationNode e: opExpRoot_) {
                Mapping m_ = new Mapping();
                m_.setArg(e.getResultClass());
                m_.setParam(_page.getAliasLong());
                if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(colsGrId_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(e.getResultClass().getNames(),AnaRendBlock.AND_ERR),
                            _page.getAliasLong());
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
            }
            int l_ = opExpRoot_.size();
            StringList formArg_ = new StringList();
            StringList varNames_ = new StringList();
            for (int i = 0; i< l_; i++) {
                String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
            }
            r_.varNames = varNames_;
            int i_ = 0;
            for (String v:varNames_) {
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(opExpRoot_.get(i_).getResultClass().getSingleNameOrEmpty());
                _page.getInfosVars().addEntry(v,lv_);
                formArg_.add(StringUtil.concat(AnaRendBlock.LEFT_PAR, v,AnaRendBlock.RIGHT_PAR));
                i_++;
            }
            String pref_ = r_.quickRender(lk_, formArg_);
            if (pref_.indexOf('(') < 0) {
                pref_ = StringUtil.concat(pref_,AnaRendBlock.LEFT_PAR,AnaRendBlock.RIGHT_PAR);
            }
            r_.opExpAnchorRoot = RenderAnalysis.getRootAnalyzedOperations(pref_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
        }
        return r_;
    }
    public String quickRender(String _expression,StringList _args) {
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        int i_ = IndexConstants.FIRST_INDEX;
        int iExp_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            if (expOffsets.isValidIndex(iExp_)) {
                if (expOffsets.get(iExp_) == i_) {
                    if (_args.isValidIndex(iExp_)) {
                        str_.append(_args.get(iExp_));
                    }
                    if (expEnds.isValidIndex(iExp_)) {
                        i_ = expEnds.get(iExp_);
                    }
                    iExp_++;
                    continue;
                }
            }
            str_.append(_expression.charAt(i_));
            i_++;
        }
        return str_.toString();
    }

    public StringList getTexts() {
        return texts;
    }

    public CustList<OperationNode> getOpExpRoot() {
        return opExpRoot;
    }

    public OperationNode getOpExpAnchorRoot() {
        return opExpAnchorRoot;
    }

    public StringList getVarNames() {
        return varNames;
    }

}
