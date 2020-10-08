package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.util.CustList;

public final class RendImport extends RendParentBlock implements RendWithEl, RendReducableOperations {
    private Element elt;

    private ExecTextPart textPart;

    private int pageOffset;

    public RendImport(int _offsetTrim, Element elt, ExecTextPart textPart, int pageOffset) {
        super(_offsetTrim);
        this.elt = elt;
        this.textPart = textPart;
        this.pageOffset = pageOffset;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _cont.getLastPage();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont, _stds, _ctx);
            return;
        }
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrPage());
        ip_.setOffset(pageOffset);
        ip_.setOpOffset(0);
        String lg_ = _cont.getCurrentLanguage();
        String pageName_ = RenderingText.render(textPart,_cont, _stds, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        String link_ = Configuration.getRealFilePath(lg_,pageName_);
        RendDocumentBlock val_ = _cont.getRenders().getVal(link_);
        if (val_ == null) {
            processBlock(_cont, _stds, _ctx);
            return;
        }
        if (val_.getBodies().size() != 1) {
            processBlock(_cont, _stds, _ctx);
            return;
        }
        String beanName_ = val_.getBeanName();
        Struct newBean_ = _cont.getBuiltBeans().getVal(beanName_);
        boolean keepField_ = elt.hasAttribute(_cont.getRendKeyWords().getAttrKeepFields());
        Struct mainBean_ = _cont.getMainBean();
        _stds.setBeanForms(_cont, mainBean_, this, keepField_,
                beanName_, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        if (newBean_ != null) {
            String className_ = newBean_.getClassName(_ctx);
            for (RendBlock p: getDirectChildren(this)) {
                for (RendBlock c: getDirectChildren(p)) {
                    if (!(c instanceof RendClass)) {
                        continue;
                    }
                    RendClass cl_ = (RendClass) c;
                    if (!ExecTemplates.isCorrectExecute(className_,cl_.getFullName(), _ctx)) {
                        continue;
                    }
                    for (RendBlock f: getDirectChildren(c)) {
                        if (f instanceof RendField) {
                            ip_.setOffset(((RendField) f).getPrepareOffset());
                            ip_.setOpOffset(0);
                            ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrPrepare());
                            CustList<RendDynOperationNode> exps_ = ((RendField) f).getExps();
                            ip_.setInternGlobal(newBean_);
                            RenderExpUtil.calculateReuse(exps_,_cont, _stds, _ctx);
                            if (_ctx.callsOrException()) {
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
        beforeDisplaying(newBean_,_cont, _stds, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        ImportingPage newIp_ = new ImportingPage();
        newIp_.setTabWidth(_cont.getTabWidth());
        newIp_.setOffset(0);
        newIp_.setFile(val_.getFile());
        newIp_.setReadUrl(link_);
        newIp_.setBeanName(beanName_);
        RendReadWrite rwLoc_ = new RendReadWrite();
        rwLoc_.setConf(_cont.getFormParts());
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rwLoc_.setDocument(rw_.getDocument());
        rwLoc_.setWrite(rw_.getWrite());
        rwLoc_.setRead(val_.getBodies().first().getFirstChild());
        newIp_.setRendReadWrite(rwLoc_);
        if (newBean_ != null) {
            newIp_.setGlobalArgumentStruct(newBean_);
        }
        RendIfStack if_ = new RendIfStack();
        if_.setLabel("");
        if_.setLastBlock(this);
        if_.setBlock(this);
        if_.setCurrentVisitedBlock(this);
        ip_.addBlock(if_);
        ip_.setLastIf(if_);
        if_.setEntered(true);
        _cont.addPage(newIp_);
    }
}
