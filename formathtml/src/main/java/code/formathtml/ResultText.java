package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.formathtml.exec.RendDynOperationNode;
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

    private StringList texts = new StringList();
    private Ints expOffsets = new Ints();
    private Ints expEnds = new Ints();
    public void build(String _expression,Configuration _conf, int _off, RendDocumentBlock _doc) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
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
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_off);
                    badEl_.buildError(_conf.getContext().getAnalysisMessages().getBadExpression(),
                            Character.toString(RIGHT_EL),
                            Integer.toString(i_),
                            _expression);
                    _conf.addError(badEl_);
                    return;
                }
//                _conf.getLastPage().setOffset(i_);
                CustList<RendDynOperationNode> opsLoc_ = RenderExpUtil.getAnalyzedOperations(_expression, _conf,_off, i_);
                opExp.add(opsLoc_);
                i_ = _conf.getNextIndex();
                expEnds.add(i_);
                continue;
            }
            if (cur_ == RIGHT_EL){
//                _conf.getLastPage().setOffset(i_);
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_off);
                badEl_.buildError(_conf.getContext().getAnalysisMessages().getBadExpression(),
                        Character.toString(RIGHT_EL),
                        Integer.toString(i_),
                        _expression);
                _conf.addError(badEl_);
                return;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }
    public void buildId(String _expression,Configuration _conf, int _begin,RendDocumentBlock _doc) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
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
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_begin);
                badEl_.buildError(_conf.getContext().getAnalysisMessages().getBadExpression(),
                        Character.toString(cur_),
                        Integer.toString(i_),
                        _expression);
                _conf.addError(badEl_);
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
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_begin);
                    badEl_.buildError(_conf.getContext().getAnalysisMessages().getBadExpression(),
                            ElUtil.possibleChar(i_,_expression),
                            Integer.toString(i_),
                            _expression);
                    _conf.addError(badEl_);
                    return;
                }
//                _conf.getLastPage().setOffset(i_);
                CustList<RendDynOperationNode> opsLoc_ = RenderExpUtil.getAnalyzedOperations(_expression, _conf, _begin,i_);
                opExp.add(opsLoc_);
                i_ = _conf.getNextIndex();
                expEnds.add(i_);
                continue;
            }
            if (cur_ == RIGHT_EL){
//                _conf.getLastPage().setOffset(i_);
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_begin);
                badEl_.buildError(_conf.getContext().getAnalysisMessages().getBadExpression(),
                        ElUtil.possibleChar(i_,_expression),
                        Integer.toString(i_),
                        _expression);
                _conf.addError(badEl_);
                return;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }
    public static ResultText buildAnchor(Configuration _cont, RendBlock _r, RendDocumentBlock _doc, Element _read, StringList _list) {
        _list.removeAllString(_cont.getRendKeyWords().getAttrHref());
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        ResultText r_ = new ResultText();
        r_.opExp = new CustList<CustList<RendDynOperationNode>>();
        r_.texts = new StringList();
        if (href_.startsWith(CALL_METHOD)) {
            String lk_ = href_.substring(1);
            int colsGrId_ = _r.getAttributeDelimiter(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
            r_.build(lk_,_cont,colsGrId_,_doc);
            CustList<CustList<RendDynOperationNode>> opExp_ = r_.getOpExp();
            for (CustList<RendDynOperationNode> e: opExp_) {
                Mapping m_ = new Mapping();
                m_.setArg(e.last().getResultClass());
                m_.setParam(_cont.getStandards().getAliasNumber());
                if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(colsGrId_);
                    badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(e.last().getResultClass().getNames(),RendBlock.AND_ERR),
                            _cont.getStandards().getAliasNumber());
                    _cont.addError(badEl_);
                }
            }
            StringList argList_ = new StringList();
            for (CustList<RendDynOperationNode> e: opExp_) {
                String cl_ = e.last().getResultClass().getSingleNameOrEmpty();
                if (cl_.isEmpty()) {
                    argList_.add(_cont.getKeyWords().getKeyWordNull());
                } else {
                    String cast_ = _cont.getKeyWords().getKeyWordCast();
                    cast_ = StringList.concat(cast_,RendBlock.LEFT_PAR,cl_,RendBlock.RIGHT_PAR);
                    argList_.add(StringList.concat(cast_,RendBlock.ZERO));
                }
            }
            String pref_ = r_.quickRender(lk_, argList_);
            if (pref_.indexOf('(') < 0) {
                pref_ = StringList.concat(pref_,RendBlock.LEFT_PAR,RendBlock.RIGHT_PAR);
            }
            RenderExpUtil.getAnalyzedOperations(pref_,colsGrId_,0,_cont);
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
        BeanLgNames standards_ = _cont.getAdvStandards();
        for (int i = 0; i < s_; i++) {
            str_.append(_texts.get(i));
            CustList<RendDynOperationNode> exp_ = _opExp.get(i);
            Argument argument_ = RenderExpUtil.calculateReuse(exp_, _cont);
            if (_cont.getContext().hasException()) {
                return str_.toString();
            }
            String string_ = standards_.processString(argument_, _cont);
            if (_cont.getContext().hasException()) {
                return str_.toString();
            }
            str_.append(string_);
        }
        str_.append(_texts.last());
        return str_.toString();
    }
    public StringList getTexts() {
        return texts;
    }

    public CustList<CustList<RendDynOperationNode>> getOpExp() {
        return opExp;
    }
}
