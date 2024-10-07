package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.sml.Node;
import code.sml.RendKeyWordsGroup;
import code.util.CustList;
import code.util.StringMap;

public final class RendEscImg extends RendElement {

    public RendEscImg(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText) {
        super(_read, _execAttributes, _execAttributesText);
    }

    @Override
    protected Struct processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        RendKeyWordsGroup gr_ = _cont.getRendKeyWords().group();
        RendImg.buildAttr(gr_,(Element) _nextWrite,((Element) _nextWrite).getAttribute(gr_.getKeyWordsAttrs().getAttrSrc()));
        _nextWrite.getOwnerDocument().renameNode(_nextWrite,_cont.getRendKeyWords().getKeyWordImg());
        return NullStruct.NULL_VALUE;
    }
}
