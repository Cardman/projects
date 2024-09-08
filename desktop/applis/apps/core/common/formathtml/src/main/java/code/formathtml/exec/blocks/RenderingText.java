package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
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
                return null;
            }
            str_.append(st_);
        }
        str_.append(texts_.last());
        return str_.toString();
    }

    private static String tryCalculate(CustList<RendDynOperationNode> _e, ContextEl _ctx, RendStackCall _rendStackCall) {
        Struct argument_ = RenderExpUtil.getFinalArg(_e, _ctx, _rendStackCall);
        if (argument_ == null) {
            return null;
        }
        Struct string_ = RendDynOperationNode.processString(argument_, _ctx, _rendStackCall);
        if (string_ == null) {
            return null;
        }
        return NumParsers.getString(string_).getInstance();
    }
}
