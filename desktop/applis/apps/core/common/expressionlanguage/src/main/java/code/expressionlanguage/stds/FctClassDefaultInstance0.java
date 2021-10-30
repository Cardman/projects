package code.expressionlanguage.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecInnerElementBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;

public final class FctClassDefaultInstance0 extends FctClassDefaultInstanceAbs {
    @Override
    public ArgumentWrapper spec(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        String className_ = ((ClassMetaInfo)_instance).getFormatted().getFormatted();
        String id_ = StringExpUtil.getIdFromAllTypes(className_);
        GeneType type_ = _cont.getClassBody(id_);
        ExecRootBlock root_ = (ExecRootBlock) type_;
        CustList<ExecRootBlock> needRoot_ = root_.getSelfAndParentTypes();
        ExecRootBlock firstType_ = needRoot_.first();
        ExecFormattedRootBlock formType_ = new ExecFormattedRootBlock(root_, className_);
        Struct parent_ = NullStruct.NULL_VALUE;
        int start_ = 0;
        if (root_.withoutInstance()) {
            if (_exit.hasToExit(_stackCall, firstType_)) {
                return new ArgumentWrapper(NullStruct.NULL_VALUE);
            }
        } else {
            if (firstType_ instanceof ExecInnerElementBlock) {
                if (_exit.hasToExit(_stackCall, firstType_.getParentType())) {
                    return new ArgumentWrapper(NullStruct.NULL_VALUE);
                }
                ExecInnerElementBlock i_ = (ExecInnerElementBlock) firstType_;
                String classFieldName_ = i_.getRealImportedClassName();
                String idCl_ = StringExpUtil.getIdFromAllTypes(classFieldName_);
                String fieldName_ = i_.getUniqueFieldName();
                StringMap<StringMap<Struct>> staticFields_ = _cont.getClasses().getStaticFields();
                Struct staticField_ = NumParsers.getStaticField(new ClassField(idCl_, fieldName_), staticFields_);
                parent_ = Argument.getNull(staticField_);
                start_ = 1;
            }
        }
        Initializer in_ = _cont.getInit();
        for (ExecRootBlock r : needRoot_.mid(start_)) {
            String genStr_ = r.getGenericString();
            String form_ = ExecInherits.quickFormat(formType_, genStr_);
            parent_ = in_.processInit(_cont, parent_, new ExecFormattedRootBlock(r, form_), "", 0);
        }
        return new ArgumentWrapper(parent_);
    }
}
