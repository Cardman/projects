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

}
