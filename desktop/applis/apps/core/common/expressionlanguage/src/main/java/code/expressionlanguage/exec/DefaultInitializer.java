package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecFieldBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;

import code.expressionlanguage.common.ClassField;

import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;

public class DefaultInitializer implements Initializer {

    @Override
    public final Struct processInit(ContextEl _context, Struct _parent,
                                    String _className, String _fieldName, int _ordinal) {
        CustList<ClassFieldStruct> fields_ = feedFields(_context, _className);
        return init(_context, _parent, _className, _fieldName, _ordinal, fields_);
    }

    public CustList<ClassFieldStruct> feedFields(ContextEl _context, String _className) {
        Classes classes_ = _context.getClasses();
        String baseClass_ = StringExpUtil.getIdFromAllTypes(_className);
        ExecRootBlock class_ = classes_.getClassBody(baseClass_);
        StringList allClasses_ = new StringList(class_.getGenericString());
        allClasses_.addAllElts(class_.getAllGenericSuperTypes());
        CustList<ClassFieldStruct> fields_;
        fields_ = new CustList<ClassFieldStruct>();
        for (String c: allClasses_) {
            String id_ = StringExpUtil.getIdFromAllTypes(c);
            String formatted_ = Templates.quickFormat(_className,c,_context);
            ExecRootBlock clMetaLoc_ = classes_.getClassBody(id_);
            for (ExecBlock b: ExecBlock.getDirectChildren(clMetaLoc_)) {
                if (!(b instanceof ExecFieldBlock)) {
                    continue;
                }
                ExecFieldBlock f_ = (ExecFieldBlock) b;
                if (f_.isStaticField()) {
                    continue;
                }
                String fieldDeclClass_ = f_.getImportedClassName();
                fieldDeclClass_ = Templates.quickFormat(formatted_,fieldDeclClass_,_context);
                for (String f: f_.getFieldName()) {
                    ClassField key_ = new ClassField(id_, f);
                    fields_.add(new ClassFieldStruct(key_, PrimitiveTypeUtil.defaultClass(fieldDeclClass_, _context)));
                }
            }
        }
        return fields_;
    }

    @Override
    public final Struct processInitAnnot(ContextEl _context,
            String _className) {
        Classes classes_ = _context.getClasses();
        String baseClass_ = StringExpUtil.getIdFromAllTypes(_className);
        ExecRootBlock class_ = classes_.getClassBody(baseClass_);
        StringList allClasses_ = new StringList(baseClass_);
        allClasses_.addAllElts(class_.getAllSuperTypes());
        CustList<ClassFieldStruct> fields_;
        fields_ = new CustList<ClassFieldStruct>();
        for (String c: allClasses_) {
            ExecRootBlock clMetaLoc_ = classes_.getClassBody(c);
            for (ExecBlock b: ExecBlock.getDirectChildren(clMetaLoc_)) {
                if (!(b instanceof ExecAnnotationMethodBlock)) {
                    continue;
                }
                ExecAnnotationMethodBlock f_ = (ExecAnnotationMethodBlock) b;
                Struct str_ = f_.getDefaultArgument();
                String fieldName_ = f_.getName();
                String fieldDeclClass_ = f_.getImportedReturnType();
                ClassField key_ = new ClassField(c, fieldName_);
                if (str_ != null) {
                    fields_.add(new ClassFieldStruct(key_, str_));
                } else {
                    fields_.add(new ClassFieldStruct(key_, PrimitiveTypeUtil.defaultClass(fieldDeclClass_, _context)));
                }
            }
        }
        return new AnnotationStruct(_className, fields_);
    }
    @Override
    public final void loopCalling(ContextEl _owner) {
        while (true) {
            EndCallValue res_ = ExecutingUtil.removeCallBase(_owner);
            if (res_ == EndCallValue.EXIT) {
                break;
            }
            if (res_ == EndCallValue.FORWARD) {
                continue;
            }
            if (!_owner.callsOrException()) {
                ExecutingUtil.processTagsBase(_owner);
            }
            if (exitAfterCall(_owner)) {
                break;
            }
        }
    }

    protected boolean exitAfterCall(ContextEl _owner) {
        AbstractPageEl abs_ = ExecutingUtil.processAfterOperation(_owner);
        if (abs_ != null) {
            ExecutingUtil.addPage(_owner,abs_);
        }
        ExecutingUtil.processException(_owner);
        return _owner.callsOrException();
    }

    protected Struct init(ContextEl _context, Struct _parent,
            String _className, String _fieldName, int _ordinal, CustList<ClassFieldStruct> _fields) {
        if (_fieldName.isEmpty()) {
            if (_parent != NullStruct.NULL_VALUE) {
                return new InnerCustStruct(_className, _fields, _parent, _parent.getClassName(_context));
            }
            return new CustStruct(_className, _fields);
        }
        return new EnumStruct(_className, _fields, _ordinal, _fieldName);
    }
}
