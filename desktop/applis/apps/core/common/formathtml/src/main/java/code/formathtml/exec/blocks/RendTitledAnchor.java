package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class RendTitledAnchor extends RendElement {
    private CustList<RendDynOperationNode> opExpAnch;
    private StringList varNames = new StringList();

    private StringMap<ExecTextPart> opExpTitle;

    private StringMap<String> preformatted;
    private ExecTextPart textPart;

    public RendTitledAnchor(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText, CustList<RendDynOperationNode> opExpAnch, StringList varNames, StringMap<ExecTextPart> opExpTitle, StringMap<String> preformatted, ExecTextPart textPart) {
        super(_offsetTrim, read, execAttributes, execAttributesText);
        this.opExpAnch = opExpAnch;
        this.varNames = varNames;
        this.opExpTitle = opExpTitle;
        this.preformatted = preformatted;
        this.textPart = textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
//        ImportingPage ip_ = _cont.getLastPage();
//        ip_.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
//        ip_.setOffset(var_.length()+1);
//        ip_.setLookForAttrValue(true);
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrValue());
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrEscapedAmp());
        StringList objects_ = new StringList();
        for (EntryCust<String, ExecTextPart> e:opExpTitle.entryList()) {
            ExecTextPart r_ = e.getValue();
            objects_.add(RenderingText.render(r_,_cont, _stds, _ctx));
            if (_ctx.callsOrException()) {
                incrAncNb(_cont, (Element) _nextWrite);
                return;
            }
            curWr_.removeAttribute(e.getKey());
        }
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrTitle(), StringList.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_));
        ownerDocument_.renameNode(curWr_, _cont.getRendKeyWords().getKeyWordAnchor());
        processLink(_cont,curWr_,_read,varNames,textPart, opExpAnch, _stds, _ctx);
    }

}
