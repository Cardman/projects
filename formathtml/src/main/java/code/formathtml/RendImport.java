package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.*;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringList;

public final class RendImport extends RendParentBlock implements RendWithEl, RendReducableOperations,RendBuildableElMethod {
    private static final String PAGE_ATTRIBUTE = "page";
    private Element elt;

    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringList texts = new StringList();

    RendImport(Element _elt, OffsetsBlock _offset) {
        super(_offset);
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        ResultText res_ = new ResultText();
        String pageName_ = elt.getAttribute(PAGE_ATTRIBUTE);
        res_.build(pageName_,_cont,_doc);
        opExp = res_.getOpExp();
        texts = res_.getTexts();
    }

    @Override
    public void reduce(Configuration _context) {
        ResultText.reduce(opExp);
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
        String pageName_ = ResultText.render(opExp,texts,_cont);
        if (_cont.getContext().getException() != null) {
            return;
        }
        String link_ = ExtractFromResources.getRealFilePath(lg_,pageName_);
        RendDocumentBlock val_ = _cont.getRenders().getVal(link_);
        if (val_ == null) {
            processBlock(_cont);
            return;
        }
        if (val_.getBodies().size() != 1) {
            processBlock(_cont);
            return;
        }
        String beanName_ = val_.getBeanName();
        ImportingPage newIp_ = new ImportingPage(false);
        newIp_.setTabWidth(_cont.getTabWidth());
//        newIp_.setHtml(newElt_.getHtml());
        newIp_.setOffset(0);
//        newIp_.setRoot(newElt_.getRoot());
//        newIp_.setProcessingNode(newElt_.getRoot().getFirstChild());
//        newIp_.setReadUrl(newElt_.getUrl());
        newIp_.setBeanName(beanName_);
        RendReadWrite rwLoc_ = new RendReadWrite();
        Struct newBean_ = FormatHtml.getBean(_cont, beanName_);
        if (newBean_ != null) {
            String className_ = newBean_.getClassName(_cont);
            for (RendBlock p: getDirectChildren(this)) {
                for (RendBlock c: getDirectChildren(p)) {
                    if (!(c instanceof RendClass)) {
                        continue;
                    }
                    RendClass cl_ = (RendClass) c;
                    if (!Templates.isCorrectExecute(className_,cl_.getFullName(),_cont)) {
                        continue;
                    }
                    for (RendBlock f: getDirectChildren(c)) {
                        if (f instanceof RendField) {
                            CustList<RendDynOperationNode> exps_ = ((RendField) f).getExps();
                            ip_.setInternGlobal(newBean_);
                            ElRenderUtil.calculateReuse(exps_,_cont);
                            if (_cont.getContext().getException() != null) {
                                return;
                            }
                            ip_.setInternGlobal(null);
                        }
                    }
                }
            }
        }
        beforeDisplaying(newBean_,_cont);
        if (_cont.getContext().getException() != null) {
            return;
        }
        rwLoc_.setDocument(rw_.getDocument());
        rwLoc_.setWrite(write_);
        rwLoc_.setRead(val_.getBodies().first().getFirstChild());
        newIp_.setRendReadWrite(rwLoc_);
        if (newBean_ != null) {
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
