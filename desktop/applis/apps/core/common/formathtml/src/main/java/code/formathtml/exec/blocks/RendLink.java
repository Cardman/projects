package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.DefRendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.*;
import code.util.core.StringUtil;

public final class RendLink extends RendElement {
    private final String content;
    private final StringMap<CustList<RendDynOperationNode>> execOpExpTitle;

    public RendLink(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText, String _content, StringMap<CustList<RendDynOperationNode>> _execOpExpTitle) {
        super(_read, _execAttributes, _execAttributesText,true);
        this.content = _content;
        this.execOpExpTitle = _execOpExpTitle;
    }

    @Override
    protected boolean processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        String fileContent_ = content;
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
        if (!execOpExpTitle.isEmpty()) {
            StringList objects_ = new StringList();
            for (EntryCust<String, CustList<RendDynOperationNode>> e:execOpExpTitle.entryList()) {
                IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
                String txt_ = RendInput.idRad(args_,_ctx,_rendStack);
                objects_.add(txt_);
                if (_ctx.callsOrException(_rendStack.getStackCall())) {
                    return true;
                }
                curWr_.removeAttribute(e.getKey());
            }
            fileContent_ = StringUtil.simpleStringsFormat(fileContent_, objects_);
        }
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        NavigationCore.simpleAppendChild(ownerDocument_, rw_, _nextWrite);
        RendBlock.procLink(_cont.getRendKeyWords().group().getKeyWordsTags(), fileContent_, ownerDocument_);
        return _ctx.callsOrException(_rendStack.getStackCall());
    }

}
