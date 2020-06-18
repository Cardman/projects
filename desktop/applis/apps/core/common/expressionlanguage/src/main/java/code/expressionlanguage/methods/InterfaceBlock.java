package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecInterfaceBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.types.ResolvingSuperTypes;
import code.expressionlanguage.inherits.Templates;
import code.util.*;
import code.util.StringList;

public final class InterfaceBlock extends RootBlock {

    private StringList importedDirectSuperInterfaces = new StringList();
    private final boolean staticType;

    public InterfaceBlock(int _idRowCol, int _categoryOffset, String _name, String _packageName, OffsetAccessInfo _access,
                          String _templateDef, IntMap< String> _directSuperTypes, boolean _staticType, OffsetsBlock _offset) {
        super(_idRowCol, _categoryOffset, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset);
        staticType = _staticType;
    }

    @Override
    public boolean isStaticType() {
        return staticType;
    }
    @Override
    public void setupBasicOverrides(ContextEl _context,ExecRootBlock _exec) {
        useSuperTypesOverrides(_context);
    }

    @Override
    public boolean mustImplement() {
        return false;
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
            _classes.addErrorIfNoMatch(s_,c_,this,index_);
            i_++;
            String base_ = Templates.getIdFromAllTypes(s_);
            ExecRootBlock r_ = _classes.getClasses().getClassBody(base_);
            if (!(r_ instanceof ExecInterfaceBlock)) {
                continue;
            }
            importedDirectSuperInterfaces.add(s_);
        }
    }

    @Override
    public void buildErrorDirectGenericSuperTypes(ContextEl _classes) {
        importedDirectSuperInterfaces.clear();
    }

    @Override
    public StringList getImportedDirectSuperTypes() {
        return new StringList(importedDirectSuperInterfaces);
    }
}
