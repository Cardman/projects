package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.util.*;

public final class AnnotationBlock extends StaticRootBlock {

    public AnnotationBlock(int _idRowCol, String _name,
                           String _packageName, OffsetAccessInfo _access, String _templateDef,
                           IntMap<String> _directSuperTypes, OffsetsBlock _offset) {
        super(_idRowCol, _name, _packageName, _access, _templateDef, _directSuperTypes, _offset
        );
    }

}
