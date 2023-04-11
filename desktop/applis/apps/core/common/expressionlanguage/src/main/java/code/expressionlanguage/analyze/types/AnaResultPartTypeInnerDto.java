package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;

public final class AnaResultPartTypeInnerDto implements AnaResultPartTypeDtoInt {
    private final String input;
    private final CustList<AnaResultPartTypeDtoInt> solved;
    private final StrTypes operators;
    private final FileBlock rooted;
    private final int loc;
    private final AnalysisMessages messages;
    public AnaResultPartTypeInnerDto(String _input, CustList<AnaResultPartTypeDtoInt> _solved, StrTypes _operators, FileBlock _rooted, int _loc, AnalysisMessages _messages) {
        input = _input;
        solved = _solved;
        operators = _operators;
        rooted = _rooted;
        loc = _loc;
        messages = _messages;
    }
    public AnaResultPartType build() {
        if (solved.isEmpty()) {
            return new AnaResultPartType();
        }
        AnaResultPartType first_ = solved.first().build();
        if (solved.size() == 1) {
            return first_;
        }
        AnaPartType partTypeFirst_ = first_.getPartType();
        AnaInnerPartType inner_ = new AnaInnerPartType(null, 0, 0, operators, messages, new StrTypes());
        String inputTr_ = input.trim();
        inner_.setLoc(loc);
        inner_.setLength(inputTr_.length());
        AnaNamePartType adj_ = new AnaNamePartType(inner_, 0, partTypeFirst_.getIndexInType(), first_.getInput().trim(), ".", messages);
        adj_.setLoc(loc);
        adj_.setAnalyzedType(first_.getResult());
        adj_.setFoundType(partTypeFirst_.getFoundType());
        int lengthFirst_ = partTypeFirst_.getLength();
        adj_.setLength(lengthFirst_);
        adj_.errs(partTypeFirst_);
        inner_.appendChild(adj_);
        AnaPartType previous_ = adj_;
        int length_ = solved.size();
        for (int i = 1; i < length_; i++) {
            AnaResultPartType solvedPart_ = solved.get(i).build();
            String operator_ = operators.getValue(i - 1);
            AnaPartType partTypeNext_ = solvedPart_.getPartType();
            AnaNamePartType adjNext_ = new AnaNamePartType(inner_, i, partTypeNext_.getIndexInType(), solvedPart_.getInput().trim(), operator_, messages);
            adjNext_.setLoc(loc);
            adjNext_.setAnalyzedType(solvedPart_.getResult());
            adjNext_.setFoundType(partTypeNext_.getFoundType());
            int lengthNext_ = partTypeNext_.getLength();
            adjNext_.setLength(lengthNext_);
            adjNext_.errs(partTypeNext_);
            adjNext_.setPreviousSibling(previous_);
            inner_.appendChild(adjNext_);
            previous_ = adjNext_;
        }
        inner_.setAnalyzedType(solved.last().build().getResult());
        inner_.setFoundType(solved.last().build().getPartType().getFoundType());
        return new AnaResultPartType(input, loc, inner_, rooted);
    }
}
