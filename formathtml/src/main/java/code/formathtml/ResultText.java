package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.Calculation;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BadElRender;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringList;

public final class ResultText {
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private static final char QUOTE = 39;
    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringList texts = new StringList();
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
                CustList<RendDynOperationNode> opsLoc_ = ElRenderUtil.getAnalyzedOperations(_expression, _conf, i_, LEFT_EL, RIGHT_EL, Calculation.staticCalculation(st_));
                opExp.add(opsLoc_);
                i_ = _conf.getNextIndex();
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

    public static void reduce(CustList<CustList<RendDynOperationNode>> _opExp) {
        int s_ = _opExp.size();
        for (int i = 0; i < s_; i++) {
            CustList<RendDynOperationNode> l_ = _opExp.get(i);
            if (l_.isEmpty()) {
                continue;
            }
            _opExp.set(i,ElRenderUtil.getReducedNodes(l_.last()));
        }
    }
    public static String render(CustList<CustList<RendDynOperationNode>> _opExp,StringList _texts,Configuration _cont) {
        int s_ = _opExp.size();
        StringBuilder str_ = new StringBuilder();
        BeanLgNames standards_ = _cont.getAdvStandards();
        for (int i = 0; i < s_; i++) {
            str_.append(_texts.get(i));
            CustList<RendDynOperationNode> exp_ = _opExp.get(i);
            Argument argument_ = ElRenderUtil.calculateReuse(exp_, _cont);
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
