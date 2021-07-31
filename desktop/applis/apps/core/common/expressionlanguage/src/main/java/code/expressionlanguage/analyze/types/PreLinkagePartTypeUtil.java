package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;

public final class PreLinkagePartTypeUtil {
    private PreLinkagePartTypeUtil() {
    }

    public static AnaResultPartType processAccessOkRootAnalyze(String _input, String _solved, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        String inputTr_ = _input.trim();
        AnaPartType named_ = AnaPartType.createRootAnalyzedNamedPartType(inputTr_, _solved);
        if (_page.isGettingParts()) {
            named_.setLoc(_loc);
            named_.setLength(inputTr_.length());
            lookForTypes(_rooted,_page,named_);
        }
        return new AnaResultPartType(_input, _loc, "", named_, _rooted);
    }

    public static AnaResultPartType processAccessKoRootAnalyze(FoundErrorInterpret _err, String _input, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        String inputTr_ = _input.trim();
        AnaPartType named_ = AnaPartType.createRootAnalyzedNamedPartType(inputTr_);
        if (_page.isGettingParts()) {
            named_.setLoc(_loc);
            named_.setLength(inputTr_.length());
        }
        named_.getErrs().add(_err.getBuiltError());
        return new AnaResultPartType(_input, _loc, "", named_, _rooted);
    }

    private static void processAfterAnalyzeLoop(String _input, AnaPartType _root, AnalysisMessages _analysisMessages) {
        AnaPartType current_ = _root;
        while (current_ != null) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            processInaccessibleOffsets(current_, _analysisMessages);
            while (current_ != null) {
                processAfterAnalyze(_input, current_, _analysisMessages);
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    processAfterAnalyze(_input, par_, _analysisMessages);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
    }

    private static void processAfterAnalyze(String _input, AnaPartType _current, AnalysisMessages _analysisMessages) {
        if (_current.getAnalyzedType().isEmpty()) {
            processInexist(_input, _current, _analysisMessages);
        } else {
            checkIndivCt(_current, _analysisMessages);
            processErrorParamCount(_current, _analysisMessages);
        }
    }

    private static void processInexist(String _input, AnaPartType _current, AnalysisMessages _analysisMessages) {
        if (_current instanceof AnaEmptyPartType) {
            AnaParentPartType par_ = _current.getParent();
            if (par_ != null) {
                return;
            }
        }
        _current.processInexistType(_input, _analysisMessages);
    }

    static void processAnalyzeConstraintsRepErrs(AnaResultPartType _className, AnalysisMessages _analysisMessages, AnalyzedPageEl _page) {
        if (!_page.isGettingParts()) {
            return;
        }
        AnaPartType root_ = _className.getPartType();
        if (root_ == null) {
            String input_ = _className.getInput();
            if (input_.isEmpty()) {
                return;
            }
            _className.getErrs().add(FoundErrorInterpret.buildARError(_analysisMessages.getUnknownType(), input_));
            return;
        }
        String input_ = _className.getInput();
        if (input_.isEmpty()) {
            _className.getErrs().add(FoundErrorInterpret.buildARError(_analysisMessages.getEmptyType()));
            return;
        }
        processAfterAnalyzeLoop(_className.getInput(), root_, _analysisMessages);
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
        ((AnaNamePartType)_current).processFound(_page);
        processLeafOffsets(_rooted, _current);
    }

    private static void processErrorParamCount(AnaPartType _current, AnalysisMessages _analysisMessages) {
        if (!_current.isErrorNbParam()) {
            return;
        }
        AnaPartType ch_ = _current.getFirstChild();
        if (ch_ != null&&_current instanceof AnaTemplatePartType) {
            String err_ = FoundErrorInterpret.buildARError(_analysisMessages.getBadParamerizedType(), ch_.getAnalyzedType());
            _current.getErrs().add(err_);
            return;
        }
        AnaPartType l_ = ch_;
        while (ch_ != null) {
            l_ = ch_;
            ch_ = ch_.getNextSibling();
        }
        if (l_ != null) {
            //inner
            String err_ = FoundErrorInterpret.buildARError(_analysisMessages.getBadParamerizedType(), l_.getAnalyzedType());
            _current.getErrs().add(err_);
            return;
        }
        _current.processBadFormedOffsets(_analysisMessages);
    }

    private static void checkIndivCt(AnaPartType _current, AnalysisMessages _analysisMessages) {
        if (koTempErr(_current)) {
            ((AnaTemplatePartType) _current).buildBadConstraintsOffsetList(_analysisMessages);
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

    private static void processInaccessibleOffsets(AnaPartType _current, AnalysisMessages _analysisMessages) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType) _current).processInaccessibleOffsets(_analysisMessages);
        }
    }
}
