package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.*;

public final class EnumBlock extends RootBlock implements UniqueRootedBlock {


    public EnumBlock(int _idRowCol, int _categoryOffset, String _name, String _packageName, OffsetAccessInfo _access,
                     String _templateDef, IntMap< String> _directSuperTypes, OffsetsBlock _offset) {
        super(_idRowCol, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset);
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
        for (Block b: ClassesUtil.getDirectChildren(this)) {
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
