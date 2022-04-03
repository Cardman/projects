package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanCustLgNames;
import code.util.CustList;
import code.util.StringList;

public final class RenderingText {
    private RenderingText() {
    }

    public static String render(DefExecTextPart _texts, ContextEl _ctx, RendStackCall _rendStackCall) {
        CustList<CustList<RendDynOperationNode>> opExp_ = _texts.getOpExp();
        StringList texts_ = _texts.getTexts();
        int s_ = opExp_.size();
        StringBuilder str_ = new StringBuilder();
        for (int i = 0; i < s_; i++) {
            str_.append(texts_.get(i));
            CustList<RendDynOperationNode> exp_ = opExp_.get(i);
            String st_ = tryCalculate(exp_, _ctx, _rendStackCall);
            if (st_ == null) {
                return str_.toString();
            }
            str_.append(st_);
        }
        str_.append(texts_.last());
        return str_.toString();
    }

    public static StringList renderAltList(DefExecTextPart _textPart, ContextEl _ctx, RendStackCall _rendStackCall) {
        CustList<CustList<RendDynOperationNode>> opExp_ = _textPart.getOpExp();
        StringList texts_ = _textPart.getTexts();
        int s_ = opExp_.size();
        StringList str_ = new StringList();
        for (int i = 0; i < s_; i++) {
            str_.add(texts_.get(i));
            CustList<RendDynOperationNode> exp_ = opExp_.get(i);
            String st_ = tryCalculate(exp_, _ctx, _rendStackCall);
            if (st_ == null) {
                return str_;
            }
            str_.add(st_);
        }
        str_.add(texts_.last());
        return str_;
    }

    private static String tryCalculate(CustList<RendDynOperationNode> _e, ContextEl _ctx, RendStackCall _rendStackCall) {
        Argument argument_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(_e, _ctx, _rendStackCall).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return null;
        }
        String string_ = BeanCustLgNames.processStr(argument_, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return null;
        }
        return string_;
    }
}
