package code.expressionlanguage;

import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.FieldBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.AnnotationStruct;
import code.expressionlanguage.structs.CustStruct;
import code.expressionlanguage.structs.EnumStruct;
import code.expressionlanguage.structs.InnerCustStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.ObjectMap;
import code.util.StringList;

public class DefaultInitializer implements Initializer {

    @Override
    public Struct processInit(ContextEl _context, Struct _parent,
            String _className, String _fieldName, int _ordinal) {
        Classes classes_ = _context.getClasses();
        String baseClass_ = Templates.getIdFromAllTypes(_className);
        RootBlock class_ = classes_.getClassBody(baseClass_);
        StringList allClasses_ = new StringList(baseClass_);
        allClasses_.addAllElts(class_.getAllSuperTypes());
        ObjectMap<ClassField,Struct> fields_;
        fields_ = new ObjectMap<ClassField,Struct>();
        for (String c: allClasses_) {
            RootBlock clMetaLoc_ = classes_.getClassBody(c);
            if (clMetaLoc_ == null) {
                continue;
            }
            for (Block b: Classes.getDirectChildren(clMetaLoc_)) {
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                if (f_.isStaticField()) {
                    continue;
                }
                Struct str_ = f_.getDefaultValue();
                String fieldDeclClass_ = f_.getImportedClassName();
                for (String f: f_.getFieldName()) {
                    ClassField key_ = new ClassField(c, f);
                    if (str_ != null) {
                        fields_.put(key_, str_);
                    } else {
                        fields_.put(key_, PrimitiveTypeUtil.defaultClass(fieldDeclClass_, _context));
                    }
                }
            }
        }
        return init(_context, _parent, _className, _fieldName, _ordinal, fields_);
    }

    @Override
    public Struct processInitAnnot(ContextEl _context,
            String _className) {
        Classes classes_ = _context.getClasses();
        String baseClass_ = Templates.getIdFromAllTypes(_className);
        RootBlock class_ = classes_.getClassBody(baseClass_);
        StringList allClasses_ = new StringList(baseClass_);
        allClasses_.addAllElts(class_.getAllSuperTypes());
        ObjectMap<ClassField,Struct> fields_;
        fields_ = new ObjectMap<ClassField,Struct>();
        for (String c: allClasses_) {
            RootBlock clMetaLoc_ = classes_.getClassBody(c);
            if (clMetaLoc_ == null) {
                continue;
            }
            for (Block b: Classes.getDirectChildren(clMetaLoc_)) {
                if (!(b instanceof AnnotationMethodBlock)) {
                    continue;
                }
                AnnotationMethodBlock f_ = (AnnotationMethodBlock) b;
                Struct str_ = f_.getDefaultArgument();
                String fieldName_ = f_.getName();
                String fieldDeclClass_ = f_.getImportedReturnType();
                ClassField key_ = new ClassField(c, fieldName_);
                if (str_ != null) {
                    fields_.put(key_, str_);
                } else {
                    fields_.put(key_, PrimitiveTypeUtil.defaultClass(fieldDeclClass_, _context));
                }
            }
        }
        return new AnnotationStruct(_className, fields_);
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
        if (_owner.hasExceptionOrFailInit()) {
            return false;
        }
        return true;
    }
    void addPage(ContextEl _conf, AbstractPageEl _page) {
        _conf.addPage(_page);
        if (_conf.hasException()) {
            _conf.getThrowing().removeBlockFinally(_conf);
        }
    }
    protected Struct init(ContextEl _context, Struct _parent,
            String _className, String _fieldName, int _ordinal, ObjectMap<ClassField,Struct> _fields) {
        if (_fieldName.isEmpty()) {
            if (_parent != NullStruct.NULL_VALUE) {
                return new InnerCustStruct(_className, _fields, _parent);
            }
            return new CustStruct(_className, _fields);
        }
        return new EnumStruct(_className, _fields, _ordinal, _fieldName);
    }
}
