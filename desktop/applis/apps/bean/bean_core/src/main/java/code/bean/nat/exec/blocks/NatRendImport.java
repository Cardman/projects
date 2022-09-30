package code.bean.nat.exec.blocks;

import code.bean.nat.AbstractNatImpLgNames;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.BeanStruct;
import code.bean.nat.exec.*;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.stacks.RendReadWrite;
import code.util.CustList;

public final class NatRendImport extends NatParentBlock {

    private final NatExecTextPart textPart;

    private final AbstractNatImpLgNames natImpLgNames;

    private final CustList<CustList<NatExecOperationNode>> fields;
    public NatRendImport(NatExecTextPart _textPart, AbstractNatImpLgNames _natImpLgNames, CustList<CustList<NatExecOperationNode>> _f) {
        this.textPart = _textPart;
        natImpLgNames = _natImpLgNames;
        fields = _f;
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        if (ip_.matchStatement(this)) {
            RendBlockHelp.processBlockAndRemove(_rendStack, this);
            return;
        }
        String pageName_ = NatRenderingText.renderNat(textPart, _rendStack);
        NatDocumentBlock val_ = ((BeanNatCommonLgNames)natImpLgNames).getRenders().getVal(pageName_);
        String beanName_ = val_.getBeanName();
        Struct newBean_ = ((BeanNatCommonLgNames)natImpLgNames).getBeansStruct().getVal(beanName_);
        Struct mainBean_ = _rendStack.getMainBean();
        natImpLgNames.setBeanForms(mainBean_,
                beanName_);
        for (CustList<NatExecOperationNode> l:fields) {
            ip_.setInternGlobal(newBean_);
            BeanNatCommonLgNames.getAllArgs(l, _rendStack);
            ip_.setInternGlobal(null);
        }
        beforeDisp(newBean_);
        NatImportingPage newIp_ = newImportingPage(ip_, val_, beanName_, _rendStack.getFormParts());
        newIp_.setGlobalArgumentStruct(newBean_);
        NatIfStack if_ = new NatIfStack();
        if_.setLastBlock(this);
        if_.setBlock(this);
        if_.setCurrentVisitedBlock(this);
        ip_.addBlock(if_);
        if_.setEntered(true);
        _rendStack.addPage(newIp_);
    }

    public static NatImportingPage newImportingPage(NatImportingPage _ip, NatDocumentBlock _val, String _beanName, NatFormParts _formParts) {
        NatImportingPage newIp_ = new NatImportingPage();
        newIp_.setBeanName(_beanName);
        NatRendReadWrite rwLoc_ = new NatRendReadWrite();
        rwLoc_.setConf(_formParts);
        RendReadWrite rw_ = _ip.getRendReadWrite();
        rwLoc_.setDocument(rw_.getDocument());
        rwLoc_.setWrite(rw_.getWrite());
        rwLoc_.setRead(_val.getBody().getFirstChild());
        newIp_.setRendReadWrite(rwLoc_);
        return newIp_;
    }
    public static void beforeDisp(Struct _arg) {
        if (!(_arg instanceof BeanStruct)) {
            return;
        }
        ((BeanStruct)_arg).beforeDisplaying();
    }
}
