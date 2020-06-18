package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecClassBlock;
import code.expressionlanguage.exec.blocks.ExecInterfaceBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.types.ResolvingSuperTypes;
import code.expressionlanguage.inherits.Templates;
import code.util.*;
import code.util.StringList;

public final class EnumBlock extends RootBlock implements UniqueRootedBlock {

    private final StringList allSuperTypes = new StringList();

    private String importedDirectSuperClass = "";
    private StringList importedDirectSuperInterfaces = new StringList();

    public EnumBlock(int _idRowCol, int _categoryOffset, String _name, String _packageName, OffsetAccessInfo _access,
                     String _templateDef, IntMap< String> _directSuperTypes, OffsetsBlock _offset) {
        super(_idRowCol, _categoryOffset, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset);
    }

    @Override
    public boolean isStaticType() {
        return true;
    }
    @Override
    public void setupBasicOverrides(ContextEl _context,ExecRootBlock _exec) {
        checkAccess(_context,_exec);
    }

    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public boolean isFinalType() {
        return true;
    }

    @Override
    public boolean isAbstractType() {
        return true;
    }

    @Override
    public boolean mustImplement() {
        for (Block b: Classes.getDirectChildren(this)) {
            if (b instanceof ElementBlock) {
                return true;
            }
        }
        return false;
    }
    public String getOriginalName() {
        return formatName("..",".");
    }
    @Override
    public void buildDirectGenericSuperTypes(ContextEl _classes,ExecRootBlock _exec) {
        IntMap< String> rcs_;
        rcs_ = getRowColDirectSuperTypes();
        int i_ = 0;
        importedDirectSuperInterfaces.clear();
        for (String s: getDirectSuperTypes()) {
            int index_ = rcs_.getKey(i_);
            String s_ = ResolvingSuperTypes.resolveTypeInherits(_classes,s, _exec,index_);
            String c_ = getImportedDirectBaseSuperType(i_);
            i_++;
            String base_ = Templates.getIdFromAllTypes(s_);
            _classes.addErrorIfNoMatch(s_,c_,this,index_);
            ExecRootBlock r_ = _classes.getClasses().getExecClassBody(base_);
            if (!(r_ instanceof ExecClassBlock)) {
                if (r_ instanceof ExecInterfaceBlock) {
                    importedDirectSuperInterfaces.add(s_);
                }
            } else {
                importedDirectSuperClass = s_;
            }
        }
        if (importedDirectSuperClass.isEmpty()) {
            importedDirectSuperClass = _classes.getStandards().getAliasObject();
        }
    }

    @Override
    public void buildErrorDirectGenericSuperTypes(ContextEl _classes) {
        importedDirectSuperInterfaces.clear();
        importedDirectSuperClass = _classes.getStandards().getAliasObject();
    }
    @Override
    public String getImportedDirectGenericSuperClass() {
        return importedDirectSuperClass;
    }

    @Override
    public StringList getImportedDirectGenericSuperInterfaces() {
        return importedDirectSuperInterfaces;
    }

    @Override
    public StringList getImportedDirectSuperTypes() {
        StringList l_ = new StringList(importedDirectSuperClass);
        l_.addAllElts(importedDirectSuperInterfaces);
        return l_;
    }
}
