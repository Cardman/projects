package cards.gui.dialogs.help.beans;

import cards.belote.beans.DefaultStruct;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.DefaultInitialization;
import code.formathtml.util.BeanNatLgNames;
import code.util.StringList;

public final class GeneralHelpLgNames extends BeanNatLgNames {

    public GeneralHelpLgNames() {
        DefaultInitialization.basicStandards(this);
    }
    public Struct wrapStd(Object _element, ExecutableCode _ex){
        return NullStruct.NULL_VALUE;
    }
    @Override
    protected Struct newId(Object _obj, String _className) {
        return DefaultStruct.newInstance(_obj, _className);
    }

    public ResultErrorStd getOtherStructToBeValidated(StringList _values, String _className, ContextEl _context) {
        return new ResultErrorStd();
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
