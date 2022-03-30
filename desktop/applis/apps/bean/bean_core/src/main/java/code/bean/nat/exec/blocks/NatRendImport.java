package code.bean.nat.exec.blocks;

import code.bean.nat.AbstractNatImpLgNames;
import code.bean.nat.BeanNatCommonLgNames;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.*;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class NatRendImport extends RendParentBlock implements NatRendWithEl {

    private final ExecTextPart textPart;

    private final int pageOffset;
    private final AbstractNatImpLgNames natImpLgNames;
    public NatRendImport(ExecTextPart _textPart, int _pageOffset, AbstractNatImpLgNames _natImpLgNames) {
        this.textPart = _textPart;
        this.pageOffset = _pageOffset;
        natImpLgNames = _natImpLgNames;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        if (ip_.matchStatement(this)) {
            RendBlockHelp.processBlockAndRemove(_rendStack, this);
            return;
        }
        ip_.setOffset(pageOffset);
        ip_.setOpOffset(0);
        String lg_ = _cont.getCurrentLanguage();
        String pageName_ = NatRenderingText.renderNat(textPart, _stds, _rendStack);
        String link_ = BeanNatCommonLgNames.getRealFilePath(lg_,pageName_);
        RendDocumentBlock val_ = ((BeanNatCommonLgNames)natImpLgNames).getRenders().getVal(link_);
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
                        ip_.setOffset(((NatRendField) f).getPrepareOffset());
                        ip_.setOpOffset(0);
                        CustList<RendDynOperationNode> exps_ = ((NatRendField) f).getExps();
                        ip_.setInternGlobal(newBean_);
                        ((BeanNatCommonLgNames)_stds).getAllArgs(exps_, _rendStack).lastValue();
                        ip_.setInternGlobal(null);
                    }
                }
            }
        }
        ip_.setOffset(pageOffset);
        ip_.setOpOffset(0);
        beforeDisp(newBean_, (BeanNatCommonLgNames) _stds);
        ImportingPage newIp_ = RendImport.newImportingPage(_rendStack, ip_, val_, beanName_);
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
    public static void beforeDisp(Struct _arg, BeanNatCommonLgNames _advStandards) {
        if (_arg == null) {
            return;
        }
        _advStandards.beforeDisplaying(_arg);
    }
}
