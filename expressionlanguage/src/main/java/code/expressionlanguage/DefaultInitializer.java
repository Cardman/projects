package code.expressionlanguage;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.CustStruct;
import code.expressionlanguage.opers.util.EnumStruct;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.StringList;

public class DefaultInitializer implements Initializer {

    @Override
    public Struct processInit(ContextEl _context, Struct _parent,
            String _className, String _fieldName, int _ordinal) {
        Classes classes_ = _context.getClasses();
        String baseClass_ = StringList.getAllTypes(_className).first();
        RootBlock class_ = classes_.getClassBody(baseClass_);
        StringList allClasses_ = new StringList(baseClass_);
        allClasses_.addAllElts(class_.getAllSuperTypes());
        ObjectMap<ClassField,Struct> fields_;
        fields_ = new ObjectMap<ClassField,Struct>();
        for (String c: allClasses_) {
            ClassMetaInfo clMetaLoc_ = classes_.getClassMetaInfo(c, _context);
            if (clMetaLoc_ == null) {
                continue;
            }
            for (EntryCust<String, FieldMetaInfo> e: clMetaLoc_.getFields().entryList()) {
                FieldMetaInfo fieldMeta_ = e.getValue();
                if (fieldMeta_.isStaticField()) {
                    continue;
                }
                String fieldDeclClass_ = fieldMeta_.getType();
                fields_.put(new ClassField(c, e.getKey()), StdStruct.defaultClass(fieldDeclClass_, _context));
            }
        }
        return init(_context, _parent, _className, _fieldName, _ordinal, fields_);
    }
    @Override
    public final void loopCalling(ContextEl _owner) {
        while (simpleCall(_owner)) {
            continue;
        }
    }
    protected boolean simpleCall(ContextEl _owner) {
        Boolean res_ = _owner.removeCall(0);
        if (res_ == null) {
            return false;
        }
        if (res_) {
            return true;
        }
        _owner.processTags();
        AbstractPageEl abs_ = _owner.processAfterOperation();
        if (abs_ != null) {
            addPage(_owner, abs_);
        }
        if (_owner.getException() != null) {
            return false;
        }
        return true;
    }
    void addPage(ContextEl _conf, AbstractPageEl _page) {
        _conf.addPage(_page);
        if (_conf.getException() != null) {
            _conf.getThrowing().removeBlockFinally(_conf);
        }
    }
    protected Struct init(ContextEl _context, Struct _parent,
            String _className, String _fieldName, int _ordinal, ObjectMap<ClassField,Struct> _fields) {
        if (_fieldName.isEmpty()) {
            return new CustStruct(_className, _fields, _parent);
        }
        return new EnumStruct(_className, _fields, _ordinal, _fieldName);
    }
    @Override
    public String getInterfaceTask(LgNames _stds) {
        return "";
    }
    @Override
    public String getRunTask(LgNames _stds) {
        return "";
    }
}
