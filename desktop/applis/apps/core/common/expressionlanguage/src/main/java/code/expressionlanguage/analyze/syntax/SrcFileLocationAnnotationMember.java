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
    public String getFileName() {
        return getFile().getFileName();
    }

    @Override
    public int getIndex() {
        return expression.getIndex();
    }

    @Override
    public FileBlock getFile() {
        return file;
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.ANNOTATION,expression.getAnnotation(),getFileName(),getIndex());
    }
}
