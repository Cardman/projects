package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.DefFormParts;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.DefRendReadWrite;
import code.formathtml.exec.stacks.RendIfStack;
import code.formathtml.exec.stacks.RendReadWrite;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.IdMap;

public final class RendImport extends RendParentBlock implements RendWithEl {
    private final Element elt;

    private final RendOperationNodeListOff rendExp;

    public RendImport(Element _elt, RendOperationNodeListOff _re) {
        this.elt = _elt;
        rendExp = _re;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        ip_.setOffset(rendExp.getOffset());
        ip_.setOpOffset(0);
        IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(rendExp.getList(), _ctx, _rendStack);
        String pageName_ = RendInput.idRad(args_,_ctx,_rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        RendDocumentBlock val_ = ((BeanCustLgNames)_stds).getRendExecutingBlocks().getRenders().getVal(pageName_);
        if (val_ == null || val_.getBodies().size() != 1) {
            processBlock(_cont, _stds, _ctx, _rendStack);
            return;
        }
        String beanName_ = val_.getBeanName();
        Struct newBean_ = ((BeanCustLgNames)_stds).getBuiltBeans().getVal(beanName_);
        boolean keepField_ = elt.hasAttribute(_cont.getRendKeyWords().getAttrKeepFields());
        Struct mainBean_ = _rendStack.getMainBean();
        if (!((BeanCustLgNames)_stds).setBeanForms(mainBean_, this, keepField_,
                beanName_, _ctx, _rendStack)) {
            return;
        }
        if (newBean_ != null) {
            for (RendBlock p: getDirectChildren(this)) {
                for (RendBlock c: getDirectChildren(p)) {
                    if (exitInitFields(_ctx,_rendStack,newBean_,c)) {
                        return;
                    }
                }
            }
        }
        endImp((BeanCustLgNames) _stds, _ctx, _rendStack, val_, newBean_);
    }

    private void endImp(BeanCustLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, RendDocumentBlock _val, Struct _newBean) {
        ImportingPage ip_ = _rendStack.getLastPage();
        ip_.setOffset(rendExp.getOffset());
        ip_.setOpOffset(0);
        befDisp(_stds, _ctx, _rendStack, _newBean);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        String beanName_ = _val.getBeanName();
        ImportingPage newIp_ = newImportingPage(ip_, _val, beanName_, _rendStack.getFormParts());
        if (_newBean != null) {
            newIp_.setGlobalArgumentStruct(_newBean,_ctx);
        }
        RendIfStack if_ = new RendIfStack();
        if_.setLabel("");
        if_.setLastBlock(this);
        if_.setBlock(this);
        if_.setCurrentVisitedBlock(this);
        ip_.addBlock(if_);
        if_.setEntered(true);
        _rendStack.addPage(newIp_);
    }

    private boolean exitInitFields(ContextEl _ctx, RendStackCall _rendStack,Struct _newBean,RendBlock _c) {
        ImportingPage ip_ = _rendStack.getLastPage();
        if (!(_c instanceof RendClass)) {
            return false;
        }
        RendClass cl_ = (RendClass) _c;
        String className_ = _newBean.getClassName(_ctx);
        if (!ExecInherits.isCorrectExecute(className_,cl_.getFullName(), _ctx)) {
            return false;
        }
        for (RendBlock f: getDirectChildren(_c)) {
            if (f instanceof RendField) {
                ip_.setOffset(((RendField) f).getPrepareOffset());
                ip_.setOpOffset(0);
                CustList<RendDynOperationNode> exps_ = ((RendField) f).getExps();
                ip_.setInternGlobal(_newBean);
                RenderExpUtil.getAllArgs(exps_, _ctx, _rendStack).lastValue();
                if (_ctx.callsOrException(_rendStack.getStackCall())) {
                    return true;
                }
                ip_.setInternGlobal(null);
            }
        }
        return false;
    }

    public static void befDisp(BeanCustLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, Struct _newBean) {
        if (_newBean != null) {
            _stds.beforeDisplaying(_newBean, _ctx, _rendStack);
        }
    }

    public static ImportingPage newImportingPage(ImportingPage _ip, RendDocumentBlock _val, String _beanName, DefFormParts _formParts) {
        ImportingPage newIp_ = new ImportingPage();
        newIp_.setOffset(0);
        newIp_.doc(_val);
//        newIp_.setReadUrl(_link);
        newIp_.setBeanName(_beanName);
        DefRendReadWrite rwLoc_ = new DefRendReadWrite();
        rwLoc_.setConf(_formParts);
        RendReadWrite rw_ = _ip.getRendReadWrite();
        rwLoc_.setDocument(rw_.getDocument());
        rwLoc_.setWrite(rw_.getWrite());
        rwLoc_.setRead(_val.getBodies().first().getFirstChild());
        newIp_.setRendReadWrite(rwLoc_);
        return newIp_;
    }
}
