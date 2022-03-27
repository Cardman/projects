package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.util.CustList;

public final class RendImport extends RendParentBlock implements RendWithEl {
    private final Element elt;

    private final ExecTextPart textPart;

    private final int pageOffset;

    public RendImport(Element _elt, ExecTextPart _textPart, int _pageOffset) {
        this.elt = _elt;
        this.textPart = _textPart;
        this.pageOffset = _pageOffset;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        ip_.setOffset(pageOffset);
        ip_.setOpOffset(0);
        String pageName_ = RenderingText.render(textPart, _stds, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        RendDocumentBlock val_ = _cont.getRenders().getVal(pageName_);
        if (val_ == null) {
            processBlock(_cont, _stds, _ctx, _rendStack);
            return;
        }
        if (val_.getBodies().size() != 1) {
            processBlock(_cont, _stds, _ctx, _rendStack);
            return;
        }
        String beanName_ = val_.getBeanName();
        Struct newBean_ = _cont.getBuiltBeans().getVal(beanName_);
        boolean keepField_ = elt.hasAttribute(_cont.getRendKeyWords().getAttrKeepFields());
        Struct mainBean_ = _rendStack.getMainBean();
        if (!((BeanCustLgNames)_stds).setBeanForms(_cont, mainBean_, this, keepField_,
                beanName_, _ctx, _rendStack)) {
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
                    if (!ExecInherits.isCorrectExecute(className_,cl_.getFullName(), _ctx)) {
                        continue;
                    }
                    for (RendBlock f: getDirectChildren(c)) {
                        if (f instanceof RendField) {
                            ip_.setOffset(((RendField) f).getPrepareOffset());
                            ip_.setOpOffset(0);
                            CustList<RendDynOperationNode> exps_ = ((RendField) f).getExps();
                            ip_.setInternGlobal(newBean_);
                            RenderExpUtil.calculateReuse(exps_, _stds, _ctx, _rendStack);
                            if (_ctx.callsOrException(_rendStack.getStackCall())) {
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
        befDisp((BeanCustLgNames) _stds, _ctx, _rendStack, newBean_);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        ImportingPage newIp_ = newImportingPage(_rendStack, ip_, val_, beanName_);
        if (newBean_ != null) {
            newIp_.setGlobalArgumentStruct(newBean_,_ctx);
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

    public static void befDisp(BeanCustLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, Struct _newBean) {
        if (_newBean != null) {
            _stds.beforeDisplaying(_newBean, _ctx, _rendStack);
        }
    }

    public static ImportingPage newImportingPage(RendStackCall _rendStack, ImportingPage _ip, RendDocumentBlock _val, String _beanName) {
        ImportingPage newIp_ = new ImportingPage();
        newIp_.setOffset(0);
        newIp_.doc(_val);
//        newIp_.setReadUrl(_link);
        newIp_.setBeanName(_beanName);
        RendReadWrite rwLoc_ = new RendReadWrite();
        rwLoc_.setConf(_rendStack.getFormParts());
        RendReadWrite rw_ = _ip.getRendReadWrite();
        rwLoc_.setDocument(rw_.getDocument());
        rwLoc_.setWrite(rw_.getWrite());
        rwLoc_.setRead(_val.getBodies().first().getFirstChild());
        newIp_.setRendReadWrite(rwLoc_);
        return newIp_;
    }
}
