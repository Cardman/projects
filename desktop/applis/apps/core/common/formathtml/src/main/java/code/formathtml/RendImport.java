package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringList;

public final class RendImport extends RendParentBlock implements RendWithEl, RendReducableOperations,RendBuildableElMethod {
    private Element elt;

    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringList texts = new StringList();

    private int pageOffset;
    RendImport(Element _elt, OffsetStringInfo _page, OffsetsBlock _offset) {
        super(_offset);
        pageOffset = _page.getOffset();
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        ResultText res_ = new ResultText();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(pageOffset);
        page_.setOffset(0);
        String pageName_ = elt.getAttribute(_cont.getRendKeyWords().getAttrPage());
        int rowsGrId_ = getAttributeDelimiter(_cont.getRendKeyWords().getAttrPage());
        res_.build(pageName_,_cont,rowsGrId_,_doc);
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
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont);
            return;
        }
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrPage());
        ip_.setOffset(pageOffset);
        ip_.setOpOffset(0);
        String lg_ = _cont.getCurrentLanguage();
        String pageName_ = ResultText.render(opExp,texts,_cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        String link_ = RendExtractFromResources.getRealFilePath(lg_,pageName_);
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
        ImportingPage newIp_ = new ImportingPage();
        newIp_.setTabWidth(_cont.getTabWidth());
        newIp_.setOffset(0);
        newIp_.setFile(val_.getFile());
        newIp_.setReadUrl(link_);
        newIp_.setBeanName(beanName_);
        RendReadWrite rwLoc_ = new RendReadWrite();
        rwLoc_.setConf(_cont);
        Struct newBean_ = _cont.getBuiltBeans().getVal(beanName_);
        boolean keepField_ = elt.hasAttribute(_cont.getRendKeyWords().getAttrKeepFields());
        Struct mainBean_ = _cont.getMainBean();
        _cont.getAdvStandards().setBeanForms(_cont, mainBean_, this, keepField_,
                beanName_);
        if (_cont.getContext().hasException()) {
            return;
        }
        if (newBean_ != null) {
            String className_ = newBean_.getClassName(_cont.getContext());
            for (RendBlock p: getDirectChildren(this)) {
                for (RendBlock c: getDirectChildren(p)) {
                    if (!(c instanceof RendClass)) {
                        continue;
                    }
                    RendClass cl_ = (RendClass) c;
                    if (!ExecTemplates.isCorrectExecute(className_,cl_.getFullName(),_cont.getContext())) {
                        continue;
                    }
                    for (RendBlock f: getDirectChildren(c)) {
                        if (f instanceof RendField) {
                            ip_.setOffset(((RendField) f).getPrepareOffset());
                            ip_.setOpOffset(0);
                            ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrPrepare());
                            CustList<RendDynOperationNode> exps_ = ((RendField) f).getExps();
                            ip_.setInternGlobal(newBean_);
                            RenderExpUtil.calculateReuse(exps_,_cont);
                            if (_cont.getContext().hasException()) {
                                return;
                            }
                            ip_.setInternGlobal(null);
                        }
                    }
                }
            }
        }
        ip_.setOffset(pageOffset);
        ip_.setOpOffset(0);
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrPage());
        beforeDisplaying(newBean_,_cont);
        if (_cont.getContext().hasException()) {
            return;
        }
        rwLoc_.setDocument(rw_.getDocument());
        rwLoc_.setWrite(write_);
        rwLoc_.setRead(val_.getBodies().first().getFirstChild());
        newIp_.setRoot(val_.getBodies().first());
        newIp_.setRendReadWrite(rwLoc_);
        if (newBean_ != null) {
            newIp_.setGlobalArgumentStruct(newBean_, _cont);
        }
        RendIfStack if_ = new RendIfStack();
        if_.setLastBlock(this);
        if_.setBlock(this);
        if_.setCurrentVisitedBlock(this);
        ip_.addBlock(if_);
        if_.setEntered(true);
        _cont.addPage(newIp_);
    }
}
