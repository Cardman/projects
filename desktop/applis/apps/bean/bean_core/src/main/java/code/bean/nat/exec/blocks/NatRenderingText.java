package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.bean.nat.*;
import code.util.CustList;
import code.util.StringList;

public final class NatRenderingText {
    private NatRenderingText() {
    }

    public static String renderNat(NatExecTextPart _texts, NatRendStackCall _rendStackCall) {
        CustList<CustList<NatExecOperationNode>> opExp_ = _texts.getOpExp();
        StringList texts_ = _texts.getTexts();
        int s_ = opExp_.size();
        StringBuilder str_ = new StringBuilder();
        for (int i = 0; i < s_; i++) {
            str_.append(texts_.get(i));
            CustList<NatExecOperationNode> exp_ = opExp_.get(i);
            String st_ = calculate(exp_, _rendStackCall);
            str_.append(st_);
        }
        str_.append(texts_.last());
        return str_.toString();
    }

    public static String calculate(CustList<NatExecOperationNode> _e, NatRendStackCall _rendStackCall) {
        NaSt argument_ = BeanNatCommonLgNames.getAllArgs(_e, _rendStackCall).lastValue().getArgument();
        return BeanNatCommonLgNames.processString(argument_);
    }
}
