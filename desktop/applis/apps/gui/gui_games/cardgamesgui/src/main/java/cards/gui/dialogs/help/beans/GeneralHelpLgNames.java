package cards.gui.dialogs.help.beans;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.Struct;
import code.bean.nat.DefaultInitialization;
import code.bean.nat.BeanNatLgNames;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;

public final class GeneralHelpLgNames extends BeanNatLgNames {

    public GeneralHelpLgNames() {
        DefaultInitialization.basicStandards(this);
    }
    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        _rendStack.setCurrentUrl(_dest);
        RendDocumentBlock rendDocumentBlock_ = _conf.getRenders().getVal(_dest);
        _rendStack.clearPages();
        return RendBlock.getRes(rendDocumentBlock_,_conf, this, _ctx, _stack, _rendStack);
    }

    public ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Struct... _args) {
        return new ResultErrorStd();
    }
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
                                             ClassMethodId _method, Struct... _args) {
        return new ResultErrorStd();
    }

    public ResultErrorStd getOtherName(ContextEl _cont, Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val) {
        return new ResultErrorStd();
    }

    @Override
    public void buildOther() {
        //impl
    }

    @Override
    public void beforeDisplaying(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        //impl
    }

    @Override
    protected Struct newSimpleBean(String _language, BeanInfo _bean, ContextEl _ctx, StackCall _stackCall) {
        return null;
    }
}
