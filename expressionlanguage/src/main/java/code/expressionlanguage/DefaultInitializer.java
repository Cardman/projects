package code.expressionlanguage;

import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.*;
import code.util.ObjectMap;
import code.util.StringList;

public class DefaultInitializer implements Initializer {

    @Override
    public final Struct processInit(ContextEl _context, Struct _parent,
            String _className, String _fieldName, int _ordinal) {
        Classes classes_ = _context.getClasses();
        String baseClass_ = Templates.getIdFromAllTypes(_className);
        RootBlock class_ = classes_.getClassBody(baseClass_);
        StringList allClasses_ = new StringList(class_.getGenericString());
        allClasses_.addAllElts(class_.getAllGenericSuperTypes());
        ObjectMap<ClassField,Struct> fields_;
        fields_ = new ObjectMap<ClassField,Struct>();
        for (String c: allClasses_) {
            String id_ = Templates.getIdFromAllTypes(c);
            String formatted_ = Templates.quickFormat(_className,c,_context);
            RootBlock clMetaLoc_ = classes_.getClassBody(id_);
            for (Block b: Classes.getDirectChildren(clMetaLoc_)) {
                if (!(b instanceof FieldBlock)) {
                    continue;
                }
                FieldBlock f_ = (FieldBlock) b;
                if (f_.isStaticField()) {
                    continue;
                }
                String fieldDeclClass_ = f_.getImportedClassName();
                fieldDeclClass_ = Templates.quickFormat(formatted_,fieldDeclClass_,_context);
                for (String f: f_.getFieldName()) {
                    ClassField key_ = new ClassField(id_, f);
                    fields_.put(key_, PrimitiveTypeUtil.defaultClass(fieldDeclClass_, _context));
                }
            }
        }
        return init(_context, _parent, _className, _fieldName, _ordinal, fields_);
    }

    @Override
    public final Struct processInitAnnot(ContextEl _context,
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
        while (true) {
            EndCallValue res_ = _owner.removeCall();
            if (res_ == EndCallValue.EXIT) {
                break;
            }
            if (res_ == EndCallValue.FORWARD) {
                continue;
            }
            if (!_owner.callsOrException()) {
                _owner.processTags();
            }
            if (exitAfterCall(_owner)) {
                break;
            }
        }
    }

    protected boolean exitAfterCall(ContextEl _owner) {
        AbstractPageEl abs_ = _owner.processAfterOperation();
        if (abs_ != null) {
            _owner.addPage(abs_);
        }
        _owner.processException();
        return _owner.callsOrException();
    }

    protected Struct init(ContextEl _context, Struct _parent,
            String _className, String _fieldName, int _ordinal, ObjectMap<ClassField,Struct> _fields) {
        if (_fieldName.isEmpty()) {
            if (_parent != NullStruct.NULL_VALUE) {
                return new InnerCustStruct(_className, _fields, _parent, _parent.getClassName(_context));
            }
            return new CustStruct(_className, _fields);
        }
        return new EnumStruct(_className, _fields, _ordinal, _fieldName);
    }
}
