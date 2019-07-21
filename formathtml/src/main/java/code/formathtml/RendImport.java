package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.*;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.StringMap;

public final class RendImport extends RendParentBlock implements RendWithEl, RendReducableOperations,RendBuildableElMethod {
    private static final String PAGE_ATTRIBUTE = "page";
    private Element elt;
    RendImport(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {

    }

    @Override
    public void reduce(Configuration _context) {

    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        Node write_ = rw_.getWrite();
        if (ip_.hasBlock()) {
            RendRemovableVars bl_ = ip_.getRendLastStack();
            if (bl_.getBlock() == this) {
                ip_.removeRendLastBlock();
                processBlock(_cont);
                return;
            }
        }
        AnalyzingDoc analyzingDoc_ = _cont.getAnalyzingDoc();
        String lg_ = analyzingDoc_.getLanguage();
//        StringMap<String> files_ = analyzingDoc_.getFiles();
//        String[] resourcesFolder_ = analyzingDoc_.getResourcesFolder();

        String pageName_ = elt.getAttribute(PAGE_ATTRIBUTE);
        if (pageName_.isEmpty()) {
            processBlock(_cont);
            return;
        }
        pageName_ = ExtractObject.formatNumVariables(pageName_, _cont, ip_);
        if (_cont.getContext().getException() != null) {
            return;
        }
        String link_ = ExtractFromResources.getRealFilePath(lg_,pageName_);
        RendDocumentBlock val_ = _cont.getRenders().getVal(link_);
        if (val_ == null) {
            processBlock(_cont);
            return;
        }
//        BeanElement newElt_ = FormatHtml.tryToOpenDocument(lg_,elt, ip_, _cont, files_, resourcesFolder_);
//        if (_cont.getContext().getException() != null) {
//            return;
//        }
//        if (newElt_ == null) {
//            processBlock(_cont);
//            return;
//        }
        String beanName_ = val_.getBeanName();
        ImportingPage newIp_ = new ImportingPage(false);
        newIp_.setTabWidth(_cont.getTabWidth());
//        newIp_.setHtml(newElt_.getHtml());
        newIp_.setOffset(0);
//        newIp_.setRoot(newElt_.getRoot());
//        newIp_.setProcessingNode(newElt_.getRoot().getFirstChild());
//        newIp_.setReadUrl(newElt_.getUrl());
        newIp_.setBeanName(beanName_);
//        newIp_.setPrefix(newElt_.getPrefix());
        RendReadWrite rwLoc_ = new RendReadWrite();
        Struct newBean_ = FormatHtml.getBean(_cont, beanName_);
        beforeDisplaying(newBean_,_cont);
        if (_cont.getContext().getException() != null) {
            return;
        }
        rwLoc_.setDocument(rw_.getDocument());
        rwLoc_.setWrite(write_);
        rwLoc_.setRead(val_.getFirstChild());
        newIp_.setRendReadWrite(rwLoc_);
        if (newBean_ != null && newBean_ != NullStruct.NULL_VALUE) {
            newIp_.setGlobalArgumentStruct(newBean_, _cont);
        }
        RendIfStack if_ = new RendIfStack();
        if_.setLastBlock(this);
        if_.setBlock(this);
        if_.setCurentVisitedBlock(this);
        ip_.addBlock(if_);
        if_.setEntered(true);
        _cont.addPage(newIp_);
    }
}
