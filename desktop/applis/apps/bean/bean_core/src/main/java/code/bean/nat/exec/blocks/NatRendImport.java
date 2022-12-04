package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.BeanStruct;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.*;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.bean.nat.*;
import code.sml.RendReadWrite;
import code.util.CustList;

public final class NatRendImport extends NatParentBlock {

    private final CustList<NatExecOperationNode> textPart;

    private final BeanNatCommonLgNames natImpLgNames;

    private final CustList<CustList<NatExecOperationNode>> fields;
    private final AbstractNatBlockBuilder bu;
    public NatRendImport(CustList<NatExecOperationNode> _textPart, BeanNatCommonLgNames _natImpLgNames, CustList<CustList<NatExecOperationNode>> _f, AbstractNatBlockBuilder _builder) {
        this.textPart = _textPart;
        natImpLgNames = _natImpLgNames;
        fields = _f;
        bu = _builder;
    }

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = _rendStack.getLastPage();
        if (ip_.matchStatement(this)) {
            RendBlockHelp.processBlockAndRemove(_rendStack, this);
            return;
        }
        String pageName_ = BeanNatCommonLgNames.processString(BeanNatCommonLgNames.getAllArgs(textPart,_rendStack).lastValue().getArgument());
        NatDocumentBlock val_ = natImpLgNames.getRenders().getVal(pageName_);
        String beanName_ = val_.getBeanName();
        NaSt newBean_ = natImpLgNames.getBeansStruct().getVal(beanName_);
        NaSt mainBean_ = _rendStack.getMainBean();
        natImpLgNames.setBeanForms(mainBean_,
                newBean_);
        for (CustList<NatExecOperationNode> l:fields) {
            ip_.setInternGlobal(newBean_);
            BeanNatCommonLgNames.getAllArgs(l, _rendStack);
            ip_.setInternGlobal(null);
        }
        beforeDisp(newBean_);
        NatImportingPageAbs newIp_ = newImportingPage(ip_, val_, beanName_,_rendStack, bu);
        newIp_.setGlobalArgumentStruct(newBean_);
        NatIfStack if_ = new NatIfStack();
        if_.setLastBlock(this);
        if_.setBlock(this);
        if_.setCurrentVisitedBlock(this);
        ip_.addBlock(if_);
        if_.setEntered(true);
        _rendStack.addPage(newIp_);
    }

    public static NatImportingPageAbs newImportingPage(NatImportingPageAbs _ip, NatDocumentBlock _val, String _beanName, NatRendStackCall _rendStack, AbstractNatBlockBuilder _bu) {
        NatImportingPageAbs newIp_ = _bu.fwd();
        newIp_.setBeanName(_beanName);
        NatRendReadWrite rwLoc_ = _ip.newNatRendReadWrite(_rendStack);
        RendReadWrite rw_ = _ip.getRendReadWrite();
        rwLoc_.setDocument(rw_.getDocument());
        rwLoc_.setWrite(rw_.getWrite());
        rwLoc_.setRead(_val.getBody().getFirstChild());
        newIp_.setRendReadWrite(rwLoc_);
        return newIp_;
    }
    public static void beforeDisp(NaSt _arg) {
        if (!(_arg instanceof BeanStruct)) {
            return;
        }
        ((BeanStruct)_arg).beforeDisplaying();
    }
}
