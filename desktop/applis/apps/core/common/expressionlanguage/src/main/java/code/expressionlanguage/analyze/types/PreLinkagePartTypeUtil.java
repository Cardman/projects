package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;

public final class PreLinkagePartTypeUtil {
    private PreLinkagePartTypeUtil() {
    }

    public static AnaResultPartType processAccessOkRootAnalyze(String _input, String _solved, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        return processAccessOkRootAnalyze(_input, _solved, _rooted, _loc,0, _page);
    }
    public static AnaResultPartType processAccessOkRootAnalyze(String _input, String _solved, AccessedBlock _rooted, int _loc, int _indexInType, AnalyzedPageEl _page) {
        if (_page.isGettingParts()) {
            String inputTr_ = _input.trim();
            AnaPartType named_ = new AnaNamePartType(null, 0, _indexInType, inputTr_.trim(), "", _page.getAnalysisMessages());
            named_.setAnalyzedType(_solved);
            named_.setLoc(_loc);
            named_.setLength(inputTr_.length());
            lookForTypes(_rooted,_page,named_);
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
            AnaInnerPartType inner_ = new AnaInnerPartType(null, 0, 0, _operators, _page.getAnalysisMessages());
            String inputTr_ = _input.trim();
            inner_.setLoc(_loc);
            inner_.setLength(inputTr_.length());
            AnaNamePartType adj_ = new AnaNamePartType(inner_, 0, partTypeFirst_.getIndexInType(), first_.getInput().trim(), ".", _page.getAnalysisMessages());
            adj_.setLoc(_loc);
            adj_.setAnalyzedType(first_.getResult());
            int lengthFirst_ = partTypeFirst_.getLength();
            adj_.setLength(lengthFirst_);
            adj_.errs(partTypeFirst_);
            lookForTypes(_rooted,adj_,partTypeFirst_);
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
                int lengthNext_ = partTypeNext_.getLength();
                adjNext_.setLength(lengthNext_);
                adjNext_.errs(partTypeNext_);
                lookForTypes(_rooted,adjNext_,partTypeNext_);
                adjNext_.setPreviousSibling(previous_);
                inner_.appendChild(adjNext_);
                previous_ = adjNext_;
            }
            inner_.setAnalyzedType(_solved.last().getResult());
            return new AnaResultPartType(_input, _loc, inner_, _rooted);
        }
        return new AnaResultPartType();
    }

    public static AnaResultPartType processAccessKoRootAnalyze(FoundErrorInterpret _err, String _input, int _loc, int _indexInType, AnalyzedPageEl _page) {
        return processAccessKoRootAnalyze(false,_err, _input, _loc, _indexInType, _page);
    }
    public static AnaResultPartType processAccessKoRootAnalyze(boolean _voidType, FoundErrorInterpret _err, String _input, int _loc, int _indexInType, AnalyzedPageEl _page) {
        if (_page.isGettingParts()) {
            AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
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

    private static void processAfterAnalyzeLoop(String _input, AnaPartType _root) {
        AnaPartType current_ = _root;
        while (current_ != null) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                processAfterAnalyze(_input, current_);
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    processAfterAnalyze(_input, par_);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
    }

    private static void processAfterAnalyze(String _input, AnaPartType _current) {
        StringList errs_ = new StringList();
        processInaccessibleOffsets(_current, errs_);
        if (_current.getAnalyzedType().isEmpty()) {
            processInexist(_input, _current, errs_);
        } else {
            checkIndivCt(_current);
            processErrorParamCount(_current, errs_);
        }
        _current.errs(errs_);
    }

    private static void processInexist(String _input, AnaPartType _current, StringList _errs) {
        if (_current instanceof AnaEmptyPartType) {
            AnaParentPartType par_ = _current.getParent();
            if (par_ != null) {
                return;
            }
        }
        _current.processInexistType(_input, _errs);
    }

    public static void processAnalyzeConstraintsRepErrs(AnaResultPartType _className) {
        AnaPartType root_ = _className.getPartType();
        if (_className.isGenerated()) {
            String input_ = _className.getInput();
            if (input_.trim().isEmpty()) {
                return;
            }
            _className.errs(new StringList(FoundErrorInterpret.buildARError(root_.getMessages().getUnknownType(), input_)));
            return;
        }
        String input_ = _className.getInput();
        if (input_.trim().isEmpty()) {
            _className.errs(new StringList(FoundErrorInterpret.buildARError(root_.getMessages().getEmptyType())));
            return;
        }
        processAfterAnalyzeLoop(input_, root_);
    }

    static void lookForTypes(AnaResultPartType _className, AnalyzedPageEl _page){
        if (!_page.isGettingParts()) {
            return;
        }
        AnaPartType root_ = _className.getPartType();
        AnaPartType current_ = root_;
        while (current_ != null) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                lookForTypes(_className.getRooted(),_page,current_);
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    lookForTypes(_className.getRooted(),_page,root_);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
    }

    private static void lookForTypes(AccessedBlock _rooted, AnalyzedPageEl _page, AnaPartType _current) {
        if (!(_current instanceof AnaNamePartType)) {
            processLeafOffsets(_rooted, _current);
            return;
        }
        _current.processFound(_page);
        processLeafOffsets(_rooted, _current);
    }

    private static void lookForTypes(AccessedBlock _rooted, AnaPartType _current, AnaPartType _origin) {
        _current.foundType(_origin);
        processLeafOffsets(_rooted, _current);
    }

    private static void processErrorParamCount(AnaPartType _current, StringList _errs) {
        if (!_current.isErrorNbParam()) {
            return;
        }
        AnalysisMessages messages_ = _current.getMessages();
        AnaPartType ch_ = _current.getFirstChild();
        if (ch_ != null&&_current instanceof AnaTemplatePartType) {
            String err_ = FoundErrorInterpret.buildARError(messages_.getBadParamerizedType(), ch_.getAnalyzedType());
            _errs.add(err_);
            return;
        }
        AnaPartType l_ = ch_;
        while (ch_ != null) {
            l_ = ch_;
            ch_ = ch_.getNextSibling();
        }
        if (l_ != null) {
            //inner
            String err_ = FoundErrorInterpret.buildARError(messages_.getBadParamerizedType(), l_.getAnalyzedType());
            _errs.add(err_);
            return;
        }
        _current.processBadFormedOffsets(_errs);
    }

    private static void checkIndivCt(AnaPartType _current) {
        if (koTempErr(_current)) {
            ((AnaTemplatePartType) _current).buildBadConstraintsOffsetList();
        }
    }

    private static boolean koTempErr(AnaPartType _current) {
        return _current instanceof AnaTemplatePartType && ((AnaTemplatePartType) _current).isKoConstraints();
    }

    private static void processLeafOffsets(AccessedBlock _rooted, AnaPartType _current) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType)_current).processOffsets(_rooted);
        }
        if (_current instanceof AnaVariablePartType) {
            ((AnaVariablePartType)_current).processOffsets(_rooted);
        }
    }

    private static void processInaccessibleOffsets(AnaPartType _current, StringList _errs) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType) _current).processInaccessibleOffsets(_errs);
        }
    }
}
