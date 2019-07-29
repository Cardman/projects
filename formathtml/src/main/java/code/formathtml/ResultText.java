package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.Calculation;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BadElRender;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class ResultText {
    private static final String ATTRIBUTE_COMMAND = "command";
    private static final String CALL_METHOD = "$";
    private static final char ESCAPED = '\\';
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private static final char QUOTE = 39;
    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringList texts = new StringList();
    private Ints expOffsets = new Ints();
    private Ints expEnds = new Ints();
    public void build(String _expression,Configuration _conf, RendDocumentBlock _doc) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        boolean st_ = _doc.isStaticContext();
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
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(badEl_);
                    return;
                }
//                _conf.getLastPage().setOffset(i_);
                CustList<RendDynOperationNode> opsLoc_ = RenderExpUtil.getAnalyzedOperations(_expression, _conf, i_, LEFT_EL, RIGHT_EL, Calculation.staticCalculation(st_));
                opExp.add(opsLoc_);
                i_ = _conf.getNextIndex();
                expEnds.add(i_);
                continue;
            }
            if (cur_ == RIGHT_EL){
//                _conf.getLastPage().setOffset(i_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(badEl_);
                return;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }
    public void buildId(String _expression,Configuration _conf, RendDocumentBlock _doc) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        boolean st_ = _doc.isStaticContext();
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
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().getClasses().addError(badEl_);
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
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(badEl_);
                    return;
                }
//                _conf.getLastPage().setOffset(i_);
                CustList<RendDynOperationNode> opsLoc_ = RenderExpUtil.getAnalyzedOperations(_expression, _conf, i_, LEFT_EL, RIGHT_EL, Calculation.staticCalculation(st_));
                opExp.add(opsLoc_);
                i_ = _conf.getNextIndex();
                expEnds.add(i_);
                continue;
            }
            if (cur_ == RIGHT_EL){
//                _conf.getLastPage().setOffset(i_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(badEl_);
                return;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }
    public static ResultText buildAnchor(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _all, StringList _list) {
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND));
        ResultText r_ = new ResultText();
        r_.opExp = new CustList<CustList<RendDynOperationNode>>();
        r_.texts = new StringList();
        if (href_.startsWith(CALL_METHOD)) {
            String lk_ = href_.substring(1);
            r_.build(lk_,_cont,_doc);
            CustList<CustList<RendDynOperationNode>> opExp_ = r_.getOpExp();
            for (CustList<RendDynOperationNode> e: opExp_) {
                Mapping m_ = new Mapping();
                m_.setArg(e.last().getResultClass());
                m_.setParam(_cont.getStandards().getAliasNumber());
                if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_cont.getClasses().getErrorsDet());
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                    _cont.getClasses().addError(badEl_);
                }
            }
            StringList argList_ = new StringList();
            for (CustList<RendDynOperationNode> e: opExp_) {
                String cl_ = e.last().getResultClass().getSingleNameOrEmpty();
                if (cl_.isEmpty()) {
                    argList_.add(_cont.getKeyWords().getKeyWordNull());
                } else {
                    String cast_ = _cont.getKeyWords().getKeyWordCast();
                    cast_ = StringList.concat(cast_,"(",cl_,")");
                    argList_.add(StringList.concat(cast_,"0"));
                }
            }
            String pref_ = r_.quickRender(lk_, argList_);
            if (pref_.indexOf('(') < 0) {
                pref_ = StringList.concat(pref_,"()");
            }
            boolean st_ = _doc.isStaticContext();
            RenderExpUtil.getAnalyzedOperations(pref_,0,_cont,Calculation.staticCalculation(st_));
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
            if (_cont.getContext().hasExceptionOrFailInit()) {
                return str_.toString();
            }
            str_.append(standards_.processString(argument_,_cont));
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
