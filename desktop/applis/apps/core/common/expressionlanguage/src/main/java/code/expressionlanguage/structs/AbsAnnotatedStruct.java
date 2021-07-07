package code.expressionlanguage.structs;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;

public abstract class AbsAnnotatedStruct extends WithoutParentStruct implements AnnotatedStruct {
    private AccessEnum access;
    private String fileName;

    protected AbsAnnotatedStruct(AccessEnum _access, String _fileName) {
        this.access = _access;
        fileName = _fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        this.fileName = _fileName;
    }

    protected AccessEnum getAccess() {
        return access;
    }

    protected void setAccess(AccessEnum _access) {
        access = _access;
    }

    public boolean isPublic() {
        return access == AccessEnum.PUBLIC;
    }

    public boolean isProtected() {
        return access == AccessEnum.PROTECTED;
    }

    public boolean isPrivate() {
        return access == AccessEnum.PRIVATE;
    }
    public boolean isPackage() {
        return access == AccessEnum.PACKAGE;
    }

    protected static MethodId tryFormatId(ExecFormattedRootBlock _name, ContextEl _context, MethodId _id) {
        MethodId fid_;
        if (ExecInherits.correctNbParameters(_name.getFormatted(), _context)) {
            fid_ = _id.reflectFormat(ExecInherits.getVarTypes(_name));
        } else {
            fid_ = _id;
        }
        return fid_;
    }

    protected static ConstructorId tryFormatId(ExecFormattedRootBlock _name, ContextEl _context, ConstructorId _id) {
        ConstructorId fid_;
        if (ExecInherits.correctNbParameters(_name.getFormatted(), _context)) {
            fid_ = reflectFormat(_name, _id);
        } else {
            fid_ = _id;
        }
        return fid_;
    }

    protected static ConstructorId tryFormatId(ExecFormattedRootBlock _name, ConstructorId _id) {
        return reflectFormat(_name, _id);
    }

    private static ConstructorId reflectFormat(ExecFormattedRootBlock _name, ConstructorId _id) {
        return _id.reflectFormat(ExecInherits.getVarTypes(_name), _name.getFormatted());
    }

    protected static MethodId tryFormatId(ExecFormattedRootBlock _name, MethodId _id) {
        return _id.reflectFormat(ExecInherits.getVarTypes(_name));
    }

    protected static String tryFormatType(ContextEl _cont, ExecFormattedRootBlock _owner, String _type) {
        String type_ = _type;
        if (ExecInherits.correctNbParameters(_owner.getFormatted(),_cont)) {
            type_ = ExecInherits.reflectFormat(_owner, type_);
        }
        return type_;
    }

}
