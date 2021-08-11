package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatLgNames;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringList;

public final class NatRenderingText {
    private NatRenderingText() {
    }

    public static String render(ExecTextPart _texts, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        CustList<CustList<RendDynOperationNode>> opExp_ = _texts.getOpExp();
        StringList texts_ = _texts.getTexts();
        int s_ = opExp_.size();
        StringBuilder str_ = new StringBuilder();
        for (int i = 0; i < s_; i++) {
            str_.append(texts_.get(i));
            CustList<RendDynOperationNode> exp_ = opExp_.get(i);
            String st_ = tryCalculate(exp_, _advStandards, _ctx, _rendStackCall);
            str_.append(st_);
        }
        str_.append(texts_.last());
        return str_.toString();
    }

    public static StringList renderAltList(ExecTextPart _textPart, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        CustList<CustList<RendDynOperationNode>> opExp_ = _textPart.getOpExp();
        StringList texts_ = _textPart.getTexts();
        int s_ = opExp_.size();
        StringList str_ = new StringList();
        for (int i = 0; i < s_; i++) {
            str_.add(texts_.get(i));
            CustList<RendDynOperationNode> exp_ = opExp_.get(i);
            String st_ = tryCalculate(exp_, _advStandards, _ctx, _rendStackCall);
            str_.add(st_);
        }
        str_.add(texts_.last());
        return str_;
    }

    private static String tryCalculate(CustList<RendDynOperationNode> _e, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        Argument argument_ = RenderExpUtil.calculateReuse(_e, _advStandards, _ctx, _rendStackCall);
        return BeanNatLgNames.processString(argument_, _ctx);
    }
}
