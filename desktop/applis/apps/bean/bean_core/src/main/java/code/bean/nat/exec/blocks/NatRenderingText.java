package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.Argument;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class NatRenderingText {
    private NatRenderingText() {
    }

    public static String renderNat(ExecTextPart _texts, NatRendStackCall _rendStackCall) {
        CustList<CustList<RendDynOperationNode>> opExp_ = _texts.getOpExp();
        StringList texts_ = _texts.getTexts();
        int s_ = opExp_.size();
        StringBuilder str_ = new StringBuilder();
        for (int i = 0; i < s_; i++) {
            str_.append(texts_.get(i));
            CustList<RendDynOperationNode> exp_ = opExp_.get(i);
            String st_ = calculate(exp_, _rendStackCall);
            str_.append(st_);
        }
        str_.append(texts_.last());
        return str_.toString();
    }

    public static StringList renderAltListNat(ExecTextPart _textPart, NatRendStackCall _rendStackCall) {
        CustList<CustList<RendDynOperationNode>> opExp_ = _textPart.getOpExp();
        StringList texts_ = _textPart.getTexts();
        int s_ = opExp_.size();
        StringList str_ = new StringList();
        for (int i = 0; i < s_; i++) {
            str_.add(texts_.get(i));
            CustList<RendDynOperationNode> exp_ = opExp_.get(i);
            String st_ = calculate(exp_, _rendStackCall);
            str_.add(st_);
        }
        str_.add(texts_.last());
        return str_;
    }

    private static String calculate(CustList<RendDynOperationNode> _e, NatRendStackCall _rendStackCall) {
        Argument argument_ = Argument.getNullableValue(BeanNatCommonLgNames.getAllArgs(_e, _rendStackCall).lastValue().getArgument());
        return BeanNatCommonLgNames.processString(argument_);
    }
}
