package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendImg extends RendElement {

    private final RendOperationNodeListOff rendExp;

    public RendImg(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText, RendOperationNodeListOff _r) {
        super(_read, _execAttributes, _execAttributesText);
        rendExp = _r;
    }

    @Override
    protected boolean processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage last_ = _rendStack.getLastPage();
        last_.setOffset(rendExp.getOffset());
        IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(rendExp.getList(), _ctx, _rendStack);
        String pageName_ = RendInput.idRad(args_,_ctx,_rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return true;
        }
        if (!prImg(_cont, (Element) _nextWrite, pageName_)){
            ((Element) _nextWrite).setAttribute(_cont.getRendKeyWords().getAttrSrc(),"");
        }
        return _ctx.callsOrException(_rendStack.getStackCall());
    }

    public static boolean prImg(Configuration _cont, Element _nextWrite, String _link) {
        String file_ = StringUtil.nullToEmpty(_cont.getFiles().getVal(_link));
        if (file_.isEmpty()) {
            return false;
        }
        _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrSrc(),file_);
        return true;
    }
}
