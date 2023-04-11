package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.AnaGeneType;

public final class AnaResultPartTypeDto implements AnaResultPartTypeDtoInt {
    private final String input;
    private final AnaGeneType type;
    private final String solved;
    private final FileBlock rooted;
    private final int loc;
    private final int indexInType;
    private final AnalysisMessages messages;
    public AnaResultPartTypeDto(String _input, AnaGeneType _type, String _solved, FileBlock _rooted, int _loc, int _indexInType, AnalysisMessages _messages) {
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
        AnaPartType named_ = new AnaNamePartType(null, 0, indexInType, inputTr_.trim(), "", messages);
        named_.setAnalyzedType(solved);
        named_.setLoc(loc);
        named_.setLength(inputTr_.length());
        named_.setFoundType(type);
        return new AnaResultPartType(input, loc, named_, rooted);
    }
}
