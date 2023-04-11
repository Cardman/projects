package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.util.StringList;

public final class AnaResultPartTypeKoDto implements AnaResultPartTypeDtoInt {
    private final String input;
    private final FoundErrorInterpret type;
    private final boolean solved;
    private final FileBlock rooted;
    private final int loc;
    private final int indexInType;
    private final AnalysisMessages messages;
    public AnaResultPartTypeKoDto(String _input, FoundErrorInterpret _type, boolean _solved, FileBlock _rooted, int _loc, int _indexInType, AnalysisMessages _messages) {
        input = _input;
        type = _type;
        solved = _solved;
        rooted = _rooted;
        loc = _loc;
        indexInType = _indexInType;
        messages = _messages;
    }
    @Override
    public AnaResultPartType build() {
        String inputTr_ = input.trim();
        AnaNamePartType named_ = new AnaNamePartType(null, 0, indexInType, inputTr_.trim(), ".", messages);
        named_.setLoc(loc);
        named_.setVoidType(solved);
        named_.setLength(inputTr_.length());
        named_.errs(new StringList(type.getBuiltError()));
        return new AnaResultPartType(input, loc, named_, rooted);
    }
}
