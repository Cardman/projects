package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringList;

public final class RenderingText {
    private RenderingText() {
    }

    public static String render(ExecTextPart _texts, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx) {
        CustList<CustList<RendDynOperationNode>> opExp_ = _texts.getOpExp();
        StringList texts_ = _texts.getTexts();
        int s_ = opExp_.size();
        StringBuilder str_ = new StringBuilder();
        for (int i = 0; i < s_; i++) {
            str_.append(texts_.get(i));
            CustList<RendDynOperationNode> exp_ = opExp_.get(i);
            String st_ = tryCalculate(exp_, _cont, _advStandards, _ctx);
            if (st_ == null) {
                return str_.toString();
            }
            str_.append(st_);
        }
        str_.append(texts_.last());
        return str_.toString();
    }

    public static StringList renderAltList(ExecTextPart _textPart, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx) {
        CustList<CustList<RendDynOperationNode>> opExp_ = _textPart.getOpExp();
        StringList texts_ = _textPart.getTexts();
        int s_ = opExp_.size();
        StringList str_ = new StringList();
        for (int i = 0; i < s_; i++) {
            str_.add(texts_.get(i));
            CustList<RendDynOperationNode> exp_ = opExp_.get(i);
            String st_ = tryCalculate(exp_, _cont, _advStandards, _ctx);
            if (st_ == null) {
                return str_;
            }
            str_.add(st_);
        }
        str_.add(texts_.last());
        return str_;
    }

    private static String tryCalculate(CustList<RendDynOperationNode> _e, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx) {
        Argument argument_ = RenderExpUtil.calculateReuse(_e, _cont, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return null;
        }
        String string_ = _advStandards.processString(argument_, _ctx);
        if (_ctx.callsOrException()) {
            return null;
        }
        return string_;
    }
}
