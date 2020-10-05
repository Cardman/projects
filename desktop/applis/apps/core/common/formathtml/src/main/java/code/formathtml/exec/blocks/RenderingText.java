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
        CustList<CustList<RendDynOperationNode>> _opExp = _texts.getOpExp();
        StringList _texts1 = _texts.getTexts();
        int s_ = _opExp.size();
        StringBuilder str_ = new StringBuilder();
        for (int i = 0; i < s_; i++) {
            str_.append(_texts1.get(i));
            CustList<RendDynOperationNode> exp_ = _opExp.get(i);
            String st_ = tryCalculate(exp_, _cont, _advStandards, _ctx);
            if (st_ == null) {
                return str_.toString();
            }
            str_.append(st_);
        }
        str_.append(_texts1.last());
        return str_.toString();
    }

    public static StringList renderAltList(ExecTextPart _textPart, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx) {
        CustList<CustList<RendDynOperationNode>> _opExp = _textPart.getOpExp();
        StringList _texts = _textPart.getTexts();
        int s_ = _opExp.size();
        StringList str_ = new StringList();
        for (int i = 0; i < s_; i++) {
            str_.add(_texts.get(i));
            CustList<RendDynOperationNode> exp_ = _opExp.get(i);
            String st_ = tryCalculate(exp_, _cont, _advStandards, _ctx);
            if (st_ == null) {
                return str_;
            }
            str_.add(st_);
        }
        str_.add(_texts.last());
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
