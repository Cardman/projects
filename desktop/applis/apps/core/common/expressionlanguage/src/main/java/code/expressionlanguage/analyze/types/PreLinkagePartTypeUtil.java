package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.common.AnaGeneType;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;

public final class PreLinkagePartTypeUtil {
    private PreLinkagePartTypeUtil() {
    }

    public static AnaResultPartType processAccessOkRootAnalyze(String _input, AnaGeneType _type, String _solved, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        return processAccessOkRootAnalyze(_input, _type, _solved, _rooted, _loc,0, _page);
    }
    public static AnaResultPartType processAccessOkRootAnalyze(String _input, AnaGeneType _type, String _solved, AccessedBlock _rooted, int _loc, int _indexInType, AnalyzedPageEl _page) {
        if (_page.isGettingParts()) {
            String inputTr_ = _input.trim();
            AnaPartType named_ = new AnaNamePartType(null, 0, _indexInType, inputTr_.trim(), "", _page.getAnalysisMessages());
            named_.setAnalyzedType(_solved);
            named_.setLoc(_loc);
            named_.setLength(inputTr_.length());
            named_.setFoundType(_type);
            return new AnaResultPartType(_input, _loc, named_, _rooted);
        }
        return new AnaResultPartType();
    }
    public static AnaResultPartType processAccessInnerRootAnalyze(String _input, CustList<AnaResultPartType> _solved, StrTypes _operators, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        if (_page.isGettingParts()) {
            if (_solved.isEmpty()) {
                return new AnaResultPartType();
            }
            AnaResultPartType first_ = _solved.first();
            if (_solved.size() == 1) {
                return first_;
            }
            AnaPartType partTypeFirst_ = first_.getPartType();
            AnaInnerPartType inner_ = new AnaInnerPartType(null, 0, 0, _operators, _page.getAnalysisMessages(), new StrTypes());
            String inputTr_ = _input.trim();
            inner_.setLoc(_loc);
            inner_.setLength(inputTr_.length());
            AnaNamePartType adj_ = new AnaNamePartType(inner_, 0, partTypeFirst_.getIndexInType(), first_.getInput().trim(), ".", _page.getAnalysisMessages());
            adj_.setLoc(_loc);
            adj_.setAnalyzedType(first_.getResult());
            adj_.setFoundType(partTypeFirst_.getFoundType());
            int lengthFirst_ = partTypeFirst_.getLength();
            adj_.setLength(lengthFirst_);
            adj_.errs(partTypeFirst_);
            inner_.appendChild(adj_);
            AnaPartType previous_ = adj_;
            int length_ = _solved.size();
            for (int i = 1; i < length_; i++) {
                AnaResultPartType solvedPart_ = _solved.get(i);
                String operator_ = _operators.getValue(i - 1);
                AnaPartType partTypeNext_ = solvedPart_.getPartType();
                AnaNamePartType adjNext_ = new AnaNamePartType(inner_, i, partTypeNext_.getIndexInType(), solvedPart_.getInput().trim(), operator_, _page.getAnalysisMessages());
                adjNext_.setLoc(_loc);
                adjNext_.setAnalyzedType(solvedPart_.getResult());
                adjNext_.setFoundType(partTypeNext_.getFoundType());
                int lengthNext_ = partTypeNext_.getLength();
                adjNext_.setLength(lengthNext_);
                adjNext_.errs(partTypeNext_);
                adjNext_.setPreviousSibling(previous_);
                inner_.appendChild(adjNext_);
                previous_ = adjNext_;
            }
            inner_.setAnalyzedType(_solved.last().getResult());
            inner_.setFoundType(_solved.last().getPartType().getFoundType());
            return new AnaResultPartType(_input, _loc, inner_, _rooted);
        }
        return new AnaResultPartType();
    }

    public static AnaResultPartType processAccessKoRootAnalyze(boolean _voidType, FoundErrorInterpret _err, String _input, int _loc, int _indexInType, AnalyzedPageEl _page) {
        if (_page.isGettingParts()) {
            AccessedBlock r_ = _page.getImporting();
            String inputTr_ = _input.trim();
            AnaNamePartType named_ = new AnaNamePartType(null, 0, _indexInType, inputTr_.trim(), ".", _page.getAnalysisMessages());
            named_.setLoc(_loc);
            named_.setVoidType(_voidType);
            named_.setLength(inputTr_.length());
            named_.errs(new StringList(_err.getBuiltError()));
            return new AnaResultPartType(_input, _loc, named_, r_);
        }
        return new AnaResultPartType();
    }

}
