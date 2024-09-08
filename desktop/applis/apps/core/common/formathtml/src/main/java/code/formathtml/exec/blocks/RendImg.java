package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.CustList;
import code.util.StringMap;

public final class RendImg extends RendElement {

    private final RendOperationNodeListOff rendExp;

    public RendImg(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText, RendOperationNodeListOff _r) {
        super(_read, _execAttributes, _execAttributesText);
        rendExp = _r;
    }

    @Override
    protected Struct processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage last_ = _rendStack.getLastPage();
        last_.setOffset(rendExp.getOffset());
        String pageName_ = RendInput.idRad(rendExp.getList(),_ctx,_rendStack);
        if (pageName_ == null) {
            return null;
        }
        if (!NavigationCore.prImg(_cont.getRend(), _cont.getRendKeyWords().group().getKeyWordsAttrs(), (Element) _nextWrite, pageName_)){
            ((Element) _nextWrite).setAttribute(_cont.getRendKeyWords().getAttrSrc(),"");
        }
        return NullStruct.NULL_VALUE;
    }

}
