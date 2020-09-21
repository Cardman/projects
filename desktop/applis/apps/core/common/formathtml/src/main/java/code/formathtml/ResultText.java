package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AnalyzingDoc;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class ResultText {

    private static final String CALL_METHOD = "$";
    private static final char ESCAPED = '\\';
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private static final char QUOTE = 39;
    private CustList<CustList<RendDynOperationNode>> opExp;
    private CustList<OperationNode> opExpRoot;
    private CustList<RendDynOperationNode> opExpAnchor;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    private Ints expOffsets = new Ints();
    private Ints expEnds = new Ints();
    public void build(String _expression, int _off, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        opExpRoot = new CustList<OperationNode>();
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        boolean escaped_ = false;
        int i_ = CustList.FIRST_INDEX;
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
                            Integer.toString(i_),
                            _expression);
                    Configuration.addError(badEl_, _anaDoc, _page);
                    return;
                }
//                _conf.getLastPage().setOffset(i_);
                CustList<RendDynOperationNode> opsLoc_ = RenderExpUtil.getAnalyzedOperationsDel(_expression, i_, _anaDoc, _page);
                opExp.add(opsLoc_);
                opExpRoot.add(_page.getCurrentRoot());
                i_ = _anaDoc.getNextIndex();
                expEnds.add(i_);
                continue;
            }
            if (cur_ == RIGHT_EL){
//                _conf.getLastPage().setOffset(i_);
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(_off);
                badEl_.buildError(_page.getAnalysisMessages().getBadExpression(),
                        Character.toString(RIGHT_EL),
                        Integer.toString(i_),
                        _expression);
                Configuration.addError(badEl_, _anaDoc, _page);
                return;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }
    public void buildId(String _expression, int _begin, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        opExpRoot = new CustList<OperationNode>();
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        boolean escaped_ = false;
        int i_ = CustList.FIRST_INDEX;
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
                        Integer.toString(i_),
                        _expression);
                Configuration.addError(badEl_, _anaDoc, _page);
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
                            Integer.toString(i_),
                            _expression);
                    Configuration.addError(badEl_, _anaDoc, _page);
                    return;
                }
//                _conf.getLastPage().setOffset(i_);
                CustList<RendDynOperationNode> opsLoc_ = RenderExpUtil.getAnalyzedOperationsDel(_expression, i_, _anaDoc, _page);
                opExp.add(opsLoc_);
                opExpRoot.add(_page.getCurrentRoot());
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
                        Integer.toString(i_),
                        _expression);
                Configuration.addError(badEl_, _anaDoc, _page);
                return;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }
    public static ResultText buildAnchor(Configuration _cont, RendBlock _r, RendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(_cont.getRendKeyWords().getAttrHref());
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        ResultText r_ = new ResultText();
        r_.opExpAnchor = new CustList<RendDynOperationNode>();
        r_.opExp = new CustList<CustList<RendDynOperationNode>>();
        r_.opExpRoot = new CustList<OperationNode>();
        r_.texts = new StringList();
        if (href_.startsWith(CALL_METHOD)) {
            String lk_ = href_.substring(1);
            int colsGrId_ = _r.getAttributeDelimiter(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
            r_.build(lk_, colsGrId_,_doc, _anaDoc, _page);
            CustList<OperationNode> opExpRoot_ = r_.getOpExpRoot();
            CustList<CustList<RendDynOperationNode>> opExp_ = r_.getOpExp();
            for (OperationNode e: opExpRoot_) {
                Mapping m_ = new Mapping();
                m_.setArg(e.getResultClass());
                m_.setParam(_page.getStandards().getAliasLong());
                if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(colsGrId_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(e.getResultClass().getNames(),RendBlock.AND_ERR),
                            _page.getStandards().getAliasLong());
                    Configuration.addError(badEl_, _anaDoc, _page);
                }
            }
            int l_ = opExp_.size();
            StringList formArg_ = new StringList();
            StringList varNames_ = new StringList();
            for (int i = 0; i< l_; i++) {
                String varLoc_ = RendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
            }
            r_.varNames = varNames_;
            int i_ = 0;
            for (String v:varNames_) {
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(opExpRoot_.get(i_).getResultClass().getSingleNameOrEmpty());
                _page.getInfosVars().addEntry(v,lv_);
                formArg_.add(StringList.concat(RendBlock.LEFT_PAR, v,RendBlock.RIGHT_PAR));
                i_++;
            }
            String pref_ = r_.quickRender(lk_, formArg_);
            if (pref_.indexOf('(') < 0) {
                pref_ = StringList.concat(pref_,RendBlock.LEFT_PAR,RendBlock.RIGHT_PAR);
            }
            r_.opExpAnchor = RenderExpUtil.getAnalyzedOperations(pref_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
        }
        return r_;
    }
    public String quickRender(String _expression,StringList _args) {
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        int i_ = CustList.FIRST_INDEX;
        int iExp_ = CustList.FIRST_INDEX;
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
    public static void reduce(CustList<CustList<RendDynOperationNode>> _opExp) {
        int s_ = _opExp.size();
        for (int i = 0; i < s_; i++) {
            CustList<RendDynOperationNode> l_ = _opExp.get(i);
            if (l_.isEmpty()) {
                continue;
            }
            _opExp.set(i,RenderExpUtil.getReducedNodes(l_.last()));
        }
    }
    public static String render(CustList<CustList<RendDynOperationNode>> _opExp,StringList _texts,Configuration _cont) {
        int s_ = _opExp.size();
        StringBuilder str_ = new StringBuilder();
        for (int i = 0; i < s_; i++) {
            str_.append(_texts.get(i));
            CustList<RendDynOperationNode> exp_ = _opExp.get(i);
            String st_ = tryCalculate(exp_, _cont);
            if (st_ == null) {
                return str_.toString();
            }
            str_.append(st_);
        }
        str_.append(_texts.last());
        return str_.toString();
    }

    public static StringList renderAltList(CustList<CustList<RendDynOperationNode>> _opExp,StringList _texts,Configuration _cont) {
        int s_ = _opExp.size();
        StringList str_ = new StringList();
        for (int i = 0; i < s_; i++) {
            str_.add(_texts.get(i));
            CustList<RendDynOperationNode> exp_ = _opExp.get(i);
            String st_ = tryCalculate(exp_, _cont);
            if (st_ == null) {
                return str_;
            }
            str_.add(st_);
        }
        str_.add(_texts.last());
        return str_;
    }
    private static String tryCalculate(CustList<RendDynOperationNode> _e,Configuration _cont) {
        Argument argument_ = RenderExpUtil.calculateReuse(_e, _cont);
        BeanLgNames standards_ = _cont.getAdvStandards();
        if (_cont.getContext().hasException()) {
            return null;
        }
        String string_ = standards_.processString(argument_, _cont);
        if (_cont.getContext().hasException()) {
            return null;
        }
        return string_;
    }
    public StringList getTexts() {
        return texts;
    }

    public CustList<CustList<RendDynOperationNode>> getOpExp() {
        return opExp;
    }

    public CustList<OperationNode> getOpExpRoot() {
        return opExpRoot;
    }

    public CustList<RendDynOperationNode> getOpExpAnchor() {
        return opExpAnchor;
    }

    public StringList getVarNames() {
        return varNames;
    }
}
