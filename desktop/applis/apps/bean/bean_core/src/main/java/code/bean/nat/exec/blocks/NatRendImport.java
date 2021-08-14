package code.bean.nat.exec.blocks;

import code.bean.nat.AbstractNatImpLgNames;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.*;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.util.CustList;

public final class NatRendImport extends RendParentBlock implements RendWithEl {
    private final Element elt;

    private final ExecTextPart textPart;

    private final int pageOffset;
    private final AbstractNatImpLgNames natImpLgNames;
    public NatRendImport(Element _elt, ExecTextPart _textPart, int _pageOffset, AbstractNatImpLgNames _natImpLgNames) {
        this.elt = _elt;
        this.textPart = _textPart;
        this.pageOffset = _pageOffset;
        natImpLgNames = _natImpLgNames;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        if (ip_.matchStatement(this)) {
            RendBlockHelp.processBlockAndRemove(_ctx, _rendStack, this);
            return;
        }
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrPage());
        ip_.setOffset(pageOffset);
        ip_.setOpOffset(0);
        String lg_ = _cont.getCurrentLanguage();
        String pageName_ = NatRenderingText.render(textPart, _stds, _ctx, _rendStack);
        String link_ = Configuration.getRealFilePath(lg_,pageName_);
        RendDocumentBlock val_ = _cont.getRenders().getVal(link_);
        String beanName_ = val_.getBeanName();
        Struct newBean_ = _cont.getBuiltBeans().getVal(beanName_);
        boolean keepField_ = elt.hasAttribute(_cont.getRendKeyWords().getAttrKeepFields());
        Struct mainBean_ = _rendStack.getMainBean();
        natImpLgNames.setBeanForms(_cont, mainBean_, this, keepField_,
                beanName_, _ctx, _rendStack);
        for (RendBlock p: getDirectChildren(this)) {
            for (RendBlock c: getDirectChildren(p)) {
                if (!(c instanceof NatRendClass)) {
                    continue;
                }
                for (RendBlock f: getDirectChildren(c)) {
                    if (f instanceof NatRendField) {
                        ip_.setOffset(((NatRendField) f).getPrepareOffset());
                        ip_.setOpOffset(0);
                        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrPrepare());
                        CustList<RendDynOperationNode> exps_ = ((NatRendField) f).getExps();
                        ip_.setInternGlobal(newBean_);
                        RenderExpUtil.calculateReuse(exps_, _stds, _ctx, _rendStack);
                        ip_.setInternGlobal(null);
                    }
                }
            }
        }
        ip_.setOffset(pageOffset);
        ip_.setOpOffset(0);
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrPage());
        beforeDisplaying(newBean_,_cont, _stds, _ctx, _rendStack);
        ImportingPage newIp_ = new ImportingPage();
        newIp_.setTabWidth(_cont.getTabWidth());
        newIp_.setOffset(0);
        newIp_.setFile(val_.getFile());
        newIp_.setReadUrl(link_);
        newIp_.setBeanName(beanName_);
        RendReadWrite rwLoc_ = new RendReadWrite();
        rwLoc_.setConf(_rendStack.getFormParts());
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rwLoc_.setDocument(rw_.getDocument());
        rwLoc_.setWrite(rw_.getWrite());
        rwLoc_.setRead(val_.getBodies().first().getFirstChild());
        newIp_.setRendReadWrite(rwLoc_);
        newIp_.setGlobalArgumentStruct(newBean_);
        RendIfStack if_ = new RendIfStack();
        if_.setLabel("");
        if_.setLastBlock(this);
        if_.setBlock(this);
        if_.setCurrentVisitedBlock(this);
        ip_.addBlock(if_);
        if_.setEntered(true);
        _rendStack.addPage(newIp_);
    }
}
