package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.core.StringUtil;

public final class FctClassGetDeclaredFields1 extends FctReflection {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct name_ = argumentWrappers_.get(0).getValue().getStruct();
        CustList<FieldMetaInfo> fields_ = ((ClassMetaInfo)_instance).getFieldsInfos();
        if (!(name_ instanceof StringStruct)) {
            ArrayStruct str_ = buildArrFields(_cont,fields_);
            return new ArgumentWrapper(str_);
        }
        String fieldName_ = ((StringStruct) name_).getInstance();
        CustList<FieldMetaInfo> filteredFields_ = new CustList<FieldMetaInfo>();
        for (FieldMetaInfo e: fields_) {
            if (StringUtil.quickEq(fieldName_, e.getName())) {
                filteredFields_.add(e);
                break;
            }
        }
        ArrayStruct str_ = buildArrFields(_cont,filteredFields_);
        return new ArgumentWrapper(str_);
    }
}
