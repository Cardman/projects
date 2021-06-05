package code.expressionlanguage.structs;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;

public abstract class AbsAnnotatedStruct extends WithoutParentStruct {
    private ExecRootBlock owner;

    protected static MethodId tryFormatId(String _name, ContextEl _context, MethodId _id) {
        MethodId fid_;
        if (ExecInherits.correctNbParameters(_name, _context)) {
            fid_ = _id.reflectFormat(_name, _context);
        } else {
            fid_ = _id;
        }
        return fid_;
    }

    protected static ConstructorId tryFormatId(String _name, ContextEl _context, ConstructorId _id) {
        ConstructorId fid_;
        if (ExecInherits.correctNbParameters(_name, _context)) {
            fid_ = _id.reflectFormat(_name, _context);
        } else {
            fid_ = _id;
        }
        return fid_;
    }

    protected static ConstructorId tryFormatId(ExecFormattedRootBlock _name, ConstructorId _id) {
        return _id.reflectFormat(_name);
    }

    protected static MethodId tryFormatId(ExecFormattedRootBlock _name, MethodId _id) {
        return _id.reflectFormat(_name);
    }

    protected static String tryFormatType(ContextEl _cont, String _owner, String _type) {
        String type_ = _type;
        if (ExecInherits.correctNbParameters(_owner,_cont)) {
            type_ = ExecInherits.reflectFormat(_owner, type_, _cont);
        }
        return type_;
    }

    public ExecRootBlock getOwner() {
        return owner;
    }
    public void setOwner(ExecRootBlock _owner) {
        owner = _owner;
    }
}
