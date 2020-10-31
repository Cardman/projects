package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.exec.blocks.ExecFieldBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.ForwardPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

import code.expressionlanguage.common.ClassField;

import code.expressionlanguage.structs.*;
import code.util.CustList;

public class DefaultInitializer implements Initializer {

    @Override
    public final Struct processInit(ContextEl _context, Struct _parent,
                                    String _className, ExecRootBlock _rootBlock,String _fieldName, int _ordinal) {
        CustList<ClassFieldStruct> fields_ = feedFields(_context, _className,_rootBlock);
        return init(_context, _parent, _className, _fieldName, _ordinal, fields_);
    }

    public final CustList<ClassFieldStruct> feedFields(ContextEl _context, String _className,ExecRootBlock _rootBlock) {
        ExecFormattedRootBlock base_ = new ExecFormattedRootBlock(_rootBlock,_rootBlock.getGenericString());
        CustList<ExecFormattedRootBlock> allClasses_ = new CustList<ExecFormattedRootBlock>(base_);
        allClasses_.addAllElts(_rootBlock.getAllGenericSuperTypes());
        CustList<ClassFieldStruct> fields_;
        fields_ = new CustList<ClassFieldStruct>();
        for (ExecFormattedRootBlock c: allClasses_) {
            String preFormatted_ = c.getFormatted();
            String id_ = StringExpUtil.getIdFromAllTypes(preFormatted_);
            String formatted_ = ExecTemplates.quickFormat(_rootBlock,_className, preFormatted_);
            for (ExecFieldBlock b: c.getRootBlock().getInstanceFields()) {
                String fieldDeclClass_ = b.getImportedClassName();
                fieldDeclClass_ = ExecTemplates.quickFormat(c.getRootBlock(),formatted_,fieldDeclClass_);
                for (String f: b.getFieldName()) {
                    ClassField key_ = new ClassField(id_, f);
                    fields_.add(new ClassFieldStruct(key_, ExecClassArgumentMatching.defaultValue(fieldDeclClass_, _context)));
                }
            }
        }
        return fields_;
    }

    @Override
    public final Struct processInitAnnot(ContextEl _context,
            String _className,ExecRootBlock _rootBlock) {
        String baseClass_ = StringExpUtil.getIdFromAllTypes(_className);
        CustList<ClassFieldStruct> fields_;
        fields_ = new CustList<ClassFieldStruct>();
        for (ExecAnnotationMethodBlock b: _rootBlock.getAnnotationsFields()) {
            Struct str_ = b.getDefaultArgument();
            String fieldName_ = b.getName();
            String fieldDeclClass_ = b.getImportedReturnType();
            ClassField key_ = new ClassField(baseClass_, fieldName_);
            if (str_ != null) {
                fields_.add(new ClassFieldStruct(key_, str_));
            } else {
                fields_.add(new ClassFieldStruct(key_, ExecClassArgumentMatching.defaultValue(fieldDeclClass_, _context)));
            }
        }
        return new AnnotationStruct(_className, fields_);
    }
    @Override
    public final void loopCalling(ContextEl _owner) {
        while (true) {
            AbstractPageEl p_ = _owner.getLastPage();
            ReadWrite rw_ = p_.getReadWrite();
            if (rw_ == null) {
                if (p_ instanceof StaticInitPageEl) {
                    ((StaticInitPageEl)p_).sucessClass(_owner);
                }
                _owner.removeLastPage();
                if (_owner.nbPages() == 0) {
                    break;
                }
                AbstractPageEl b_ = _owner.getLastPage();
                tryForward(_owner, p_, b_);
                rw_ = b_.getReadWrite();
            }
            if (_owner.callsOrException()) {
                rw_ = null;
            }
            if (rw_ != null) {
                ExecutingUtil.processTagsBase(_owner);
            }
            if (exitAfterCall(_owner)) {
                break;
            }
        }
    }

    private static void tryForward(ContextEl _owner, AbstractPageEl _p, AbstractPageEl _b) {
        if (_p instanceof ForwardPageEl) {
            ((ForwardPageEl)_p).forwardTo(_b, _owner);
        } else if (_p instanceof StaticInitPageEl) {
            StaticInitPageEl s_ = (StaticInitPageEl) _p;
            Argument fwd_ = s_.getFwd();
            if (fwd_ != null) {
                _b.receive(fwd_, _owner);
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
