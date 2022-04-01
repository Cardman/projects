package code.bean.nat.exec.blocks;

import code.bean.nat.AbstractNatImpLgNames;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.FormParts;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.RendIfStack;
import code.formathtml.exec.stacks.RendReadWrite;
import code.util.CustList;

public final class NatRendImport extends RendParentBlock implements NatRendWithEl {

    private final ExecTextPart textPart;

    private final AbstractNatImpLgNames natImpLgNames;
    public NatRendImport(ExecTextPart _textPart, AbstractNatImpLgNames _natImpLgNames) {
        this.textPart = _textPart;
        natImpLgNames = _natImpLgNames;
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        if (ip_.matchStatement(this)) {
            RendBlockHelp.processBlockAndRemove(_rendStack, this);
            return;
        }
        String pageName_ = NatRenderingText.renderNat(textPart, _rendStack);
        RendDocumentBlock val_ = ((BeanNatCommonLgNames)natImpLgNames).getRenders().getVal(pageName_);
        String beanName_ = val_.getBeanName();
        Struct newBean_ = ((BeanNatCommonLgNames)natImpLgNames).getBeansStruct().getVal(beanName_);
        Struct mainBean_ = _rendStack.getMainBean();
        natImpLgNames.setBeanForms(mainBean_,
                beanName_);
        for (RendBlock p: getDirectChildren(this)) {
            for (RendBlock c: getDirectChildren(p)) {
                if (!(c instanceof NatRendClass)) {
                    continue;
                }
                for (RendBlock f: getDirectChildren(c)) {
                    if (f instanceof NatRendField) {
                        CustList<RendDynOperationNode> exps_ = ((NatRendField) f).getExps();
                        ip_.setInternGlobal(newBean_);
                        BeanNatCommonLgNames.getAllArgs(exps_, _rendStack).lastValue();
                        ip_.setInternGlobal(null);
                    }
                }
            }
        }
        beforeDisp(newBean_, (BeanNatCommonLgNames) natImpLgNames);
        NatImportingPage newIp_ = newImportingPage(ip_, val_, beanName_, _rendStack.getFormParts());
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

    public static NatImportingPage newImportingPage(NatImportingPage _ip, RendDocumentBlock _val, String _beanName, FormParts _formParts) {
        NatImportingPage newIp_ = new NatImportingPage();
        newIp_.setBeanName(_beanName);
        RendReadWrite rwLoc_ = new RendReadWrite();
        rwLoc_.setConf(_formParts);
        RendReadWrite rw_ = _ip.getRendReadWrite();
        rwLoc_.setDocument(rw_.getDocument());
        rwLoc_.setWrite(rw_.getWrite());
        rwLoc_.setRead(_val.getBodies().first().getFirstChild());
        newIp_.setRendReadWrite(rwLoc_);
        return newIp_;
    }
    public static void beforeDisp(Struct _arg, BeanNatCommonLgNames _advStandards) {
        if (_arg == null) {
            return;
        }
        _advStandards.beforeDisplaying(_arg);
    }
}
