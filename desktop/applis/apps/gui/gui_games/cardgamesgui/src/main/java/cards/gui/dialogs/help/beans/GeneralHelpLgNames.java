package cards.gui.dialogs.help.beans;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.bean.nat.DefaultInitialization;
import code.bean.nat.BeanNatLgNames;

public final class GeneralHelpLgNames extends BeanNatLgNames {

    public GeneralHelpLgNames() {
        DefaultInitialization.basicStandards(this);
    }
    public Struct wrapStd(Object _element){
        return NullStruct.NULL_VALUE;
    }
    @Override
    protected Struct newId(Object _obj, String _className) {
        return NullStruct.NULL_VALUE;
    }

    public ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Object... _args) {
        return new ResultErrorStd();
    }
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
                                             ClassMethodId _method, Object... _args) {
        return new ResultErrorStd();
    }

    public ResultErrorStd getOtherName(ContextEl _cont, Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        return new ResultErrorStd();
    }

    @Override
    public void buildOther() {
        //impl
    }
}
