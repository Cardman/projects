package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.exec.blocks.ExecFieldBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;

import code.expressionlanguage.common.ClassField;

import code.expressionlanguage.structs.*;
import code.util.CustList;

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
        ExecFormattedRootBlock base_ = new ExecFormattedRootBlock(class_,class_.getGenericString());
        CustList<ExecFormattedRootBlock> allClasses_ = new CustList<ExecFormattedRootBlock>(base_);
        allClasses_.addAllElts(class_.getAllGenericSuperTypes());
        CustList<ClassFieldStruct> fields_;
        fields_ = new CustList<ClassFieldStruct>();
        for (ExecFormattedRootBlock c: allClasses_) {
            String preFormatted_ = c.getFormatted();
            String id_ = StringExpUtil.getIdFromAllTypes(preFormatted_);
            String formatted_ = ExecTemplates.quickFormat(_className, preFormatted_,_context);
            for (ExecFieldBlock b: c.getRootBlock().getInstanceFields()) {
                String fieldDeclClass_ = b.getImportedClassName();
                fieldDeclClass_ = ExecTemplates.quickFormat(formatted_,fieldDeclClass_,_context);
                for (String f: b.getFieldName()) {
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
        CustList<ClassFieldStruct> fields_;
        fields_ = new CustList<ClassFieldStruct>();
        for (ExecAnnotationMethodBlock b: class_.getAnnotationsFields()) {
            Struct str_ = b.getDefaultArgument();
            String fieldName_ = b.getName();
            String fieldDeclClass_ = b.getImportedReturnType();
            ClassField key_ = new ClassField(baseClass_, fieldName_);
            if (str_ != null) {
                fields_.add(new ClassFieldStruct(key_, str_));
            } else {
                fields_.add(new ClassFieldStruct(key_, PrimitiveTypeUtil.defaultClass(fieldDeclClass_, _context)));
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
