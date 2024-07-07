package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.files.ResultParsedAnnot;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationAnnotationMember extends AbsSrcFileLocation {
    private final FileBlock file;
    private final ResultParsedAnnot expression;

    public SrcFileLocationAnnotationMember(FileBlock _f, ResultParsedAnnot _r) {
        this.file = _f;
        this.expression = _r;
    }

    @Override
    public FileBlockCursor cursor() {
        return new FileBlockCursor(file,expression.getIndex());
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        FileBlockCursor cursor_ = cursor();
        return new RowSrcLocation(EnSrcLocation.ANNOTATION,expression.getAnnotation(),FileBlock.name(cursor_.getFile()), cursor_.getIndex());
    }
}
