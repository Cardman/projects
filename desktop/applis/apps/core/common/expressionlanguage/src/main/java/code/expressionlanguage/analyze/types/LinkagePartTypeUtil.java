package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class LinkagePartTypeUtil {
    private LinkagePartTypeUtil() {
    }

    public static void processAnalyzeConstraintsRepParts(AnaResultPartType _className, CustList<PartOffset> _parts) {
        lookForTypes(_className);
        processAnalyzeConstraintsRepErrs(_className);
        if (_className.isGenerated()) {
            int loc_ = _className.getLoc();
            String input_ = _className.getInput();
            if (input_.isEmpty()) {
                return;
            }
            String pref_ = ExportCst.anchorErr(StringUtil.join(_className.getErrs(),ExportCst.JOIN_ERR));
            _parts.add(new PartOffset(pref_, loc_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, loc_ + input_.length()));
            return;
        }
        int loc_ = _className.getLoc();
        String input_ = _className.getInput();
        if (input_.isEmpty()) {
            String pref_ = ExportCst.anchorErr(StringUtil.join(_className.getErrs(),ExportCst.JOIN_ERR));
            _parts.add(new PartOffset(pref_, loc_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, loc_ + 1));
            return;
        }
        AnaPartType root_ = _className.getPartType();
        appendParts(root_, _parts);
    }

    private static String buildOffsetPartDefault(AnaPartType _current) {
        StringList errs_ = _current.getErrs();
        if (_current instanceof AnaNamePartType) {
            AnaNamePartType current_ = (AnaNamePartType) _current;
            if (!current_.isBuildRef()) {
                return buildOffsetPart(errs_,"", "");
            }
            FileBlock currentFile_ = current_.getCurrentFile();
            FileBlock refFileName_ = current_.getRefFileName();
            int value_ = current_.getValue();
            return buildOffsetPart(errs_,ExportCst.href(currentFile_,refFileName_,value_), current_.getTitleRef());
        }
        if (_current instanceof AnaVariablePartType) {
            AnaVariablePartType current_ = (AnaVariablePartType) _current;
            FileBlock refFileName_ = current_.getRefFileName();
            int value_ = current_.getValue();
            if (refFileName_ != null) {
                return buildOffsetPart(errs_, ExportCst.href(current_.getCurrentFile(), refFileName_,value_), "");
            } else {
                return buildOffsetPart(errs_, ExportCst.href(value_), "");
            }
        }
        return buildOffsetPart(errs_,"", "");
    }

    private static void appendParts(AnaPartType _root, CustList<PartOffset> _offs) {
        AnaPartType current_ = _root;
        while (current_ != null) {
            appendOffsetBegin(_offs, current_);
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            String candidate_ = buildOffsetPartDefault(current_);
            appendOffset(candidate_,_offs, current_,current_.getFull(),current_.getLength());
            while (current_ != null) {
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    appendInternNext(_offs, current_, next_, par_);
                    current_ = next_;
                    break;
                }
                appendEnd(_offs, par_);
                current_ = AnaPartTypeUtil.goUp(par_,_root);
            }
        }
    }

    private static void appendInternNext(CustList<PartOffset> _offs, AnaPartType _current, AnaPartType _next, AnaParentPartType _par) {
        if (error(_par)) {
            appendOffsetNext(_offs, _current, _next, _par);
            return;
        }
        int index_ = _current.getIndex();
        if (_par instanceof AnaTemplatePartType && _par.getErrsList().isValidIndex(index_)) {
            int begin_ = _par.getFullBegin(index_);
            _offs.add(new PartOffset(ExportCst.anchorErr(_par.getErrsList().get(index_)), begin_));
            int len_ = _par.getOpLen(index_);
            _offs.add(new PartOffset(ExportCst.END_ANCHOR, begin_ + len_));
        }
        if (_par instanceof AnaInnerPartType && _next.getNextSibling() == null) {
            String cand_ = buildOffsetPart(_par.getErrs(), "", "");
            appendIntern(cand_, _offs, _par.buildErrorInexistBegin(), _par.buildErrorInexistEnd());
        }
    }

    private static void appendEnd(CustList<PartOffset> _offs, AnaParentPartType _par) {
        if (error(_par)) {
            appendOffsetEnd(_offs, _par);
            return;
        }
        if (_par instanceof AnaTemplatePartType) {
            StringList errLen_ = _par.getErrs();
            if (!errLen_.isEmpty()) {
                int begin_ = _par.getFullBegin();
                int len_ = _par.getOpLen();
                _offs.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errLen_,ExportCst.JOIN_ERR)),begin_));
                _offs.add(new PartOffset(ExportCst.END_ANCHOR,begin_+len_));
            }
        }
    }

    private static boolean error(AnaPartType _par) {
        return _par != null && _par.getAnalyzedType().isEmpty();
    }

    private static void appendOffsetEnd(CustList<PartOffset> _offs, AnaParentPartType _par) {
        if (_par instanceof AnaArraryPartType) {
            appendOffset(_offs, _par);
        }
    }

    private static void appendOffsetBegin(CustList<PartOffset> _offs, AnaPartType _current) {
        if (_current instanceof AnaWildCardPartType|| _current instanceof AnaRefPartType) {
            appendOffset(_offs, (AnaParentPartType) _current);
        }
    }

    private static void appendOffsetNext(CustList<PartOffset> _offs, AnaPartType _current, AnaPartType _next, AnaParentPartType _par) {
        if (_par instanceof AnaTemplatePartType&& _current.getIndex() == 0) {
            appendOffset(_offs, _par);
        }
        if (_par instanceof AnaInnerPartType&& _next.getNextSibling() == null) {
            appendOffset(_offs, _par);
        }
    }

    private static void appendOffset(CustList<PartOffset> _offs, AnaParentPartType _par) {
        String cand_ = buildOffsetPart(_par.getErrs(), "", "");
        appendOffset(cand_,_offs, _par,_par.buildErrorInexistBegin(),_par.buildErrorInexistEnd());
    }

    private static void appendOffset(String _candidate, CustList<PartOffset> _offs, AnaPartType _l, int _begin, int _len) {
        boolean hasEmpty_ = false;
        boolean alreadyError_ = false;
        AnaPartType f_ = _l.getFirstChild();
        AnaPartType ch_ = f_;
        while (ch_ != null) {
            if (ch_ instanceof AnaEmptyPartType) {
                hasEmpty_ = true;
            }
            if (!ch_.getErrs().isEmpty()) {
                alreadyError_ = true;
            }
            ch_ = ch_.getNextSibling();
        }
        if (_l instanceof AnaNamePartType) {
            AnaPartType prev_ = ((AnaNamePartType) _l).getPreviousPartType();
            if (prev_ == null || prev_.getErrs().isEmpty()) {
                appendIntern(_candidate,_offs, _begin,_len);
            }
            return;
        }
        if (f_ == null || hasEmpty_ || !alreadyError_) {
            appendIntern(_candidate,_offs, _begin,_len);
        }
    }

    private static void appendIntern(String _candidate, CustList<PartOffset> _offs, int _begin, int _len) {
        if (_candidate.isEmpty()) {
            return;
        }
        _offs.add(new PartOffset(_candidate,_begin));
        _offs.add(new PartOffset(ExportCst.END_ANCHOR,_begin+_len));
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
        if (_current instanceof AnaNamePartType && ((AnaNamePartType)_current).isVoidType()) {
            _errs.add(FoundErrorInterpret.buildARError(_current.getMessages().getVoidType(),_input.trim()));
        } else {
            _errs.add(FoundErrorInterpret.buildARError(_current.getMessages().getUnknownType(),_input));
        }
    }

    private static void processAnalyzeConstraintsRepErrs(AnaResultPartType _className) {
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

    private static void lookForTypes(AnaResultPartType _className){
        AnaPartType root_ = _className.getPartType();
        AnaPartType current_ = root_;
        while (current_ != null) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                processLeafOffsets(_className.getRooted(), current_);
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    processLeafOffsets(_className.getRooted(), root_);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
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
        _errs.add(FoundErrorInterpret.buildARError(_current.getMessages().getBadParamerizedType(), _current.getAnalyzedType()));
    }

    private static void checkIndivCt(AnaPartType _current) {
        if (koTempErr(_current)) {
            ((AnaTemplatePartType) _current).getErrsList().clear();
            for (int i = 0; i < ((AnaTemplatePartType) _current).getCountParam(); i++) {
                ((AnaTemplatePartType) _current).getErrsList().add("");
            }
            for (int i: ((AnaTemplatePartType) _current).getIndexesChildConstraints()) {
                String err_ = FoundErrorInterpret.buildARError(_current.getMessages().getBadParamerizedType(), _current.getAnalyzedType());
                ((AnaTemplatePartType) _current).getErrsList().set(i,err_);
            }
        }
    }

    private static boolean koTempErr(AnaPartType _current) {
        return _current instanceof AnaTemplatePartType && ((AnaTemplatePartType) _current).isKoConstraints();
    }

    private static void processLeafOffsets(FileBlock _rooted, AnaPartType _current) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType)_current).processOffsets(_rooted);
        }
        if (_current instanceof AnaVariablePartType) {
            ((AnaVariablePartType)_current).processOffsets(_rooted);
        }
    }

    private static void processInaccessibleOffsets(AnaPartType _current, StringList _errs) {
        if (_current instanceof AnaNamePartType) {
            for (InaccessibleType i: _current.getInaccessibleTypes()) {
                _errs.add(FoundErrorInterpret.buildARError(_current.getMessages().getInaccessibleType(),
                        i.getType(), ((AnaNamePartType) _current).getGl()));
            }
        }
    }

    private static String buildOffsetPart(StringList _errs,String _href, String _titleRef) {
        StringBuilder pref_ = new StringBuilder(ExportCst.BEGIN_ANCHOR);
        boolean add_ = false;
        if (!_errs.isEmpty()||!_titleRef.isEmpty()) {
            pref_.append(ExportCst.SEP_ATTR).append(ExportCst.title(_errs, _titleRef));
        }
        if (!_href.isEmpty()) {
            pref_.append(ExportCst.SEP_ATTR).append(_href);
        }
        if (!_errs.isEmpty()) {
            pref_.append(ExportCst.SEP_ATTR_CLASS_ERR+ExportCst.END);
        } else {
            pref_.append(ExportCst.END);
        }
        if (!_errs.isEmpty()) {
            add_ = true;
        }
        if (!_titleRef.isEmpty()) {
            add_ = true;
        }
        if (!_href.isEmpty()) {
            add_ = true;
        }
        if (!add_) {
            return "";
        }
        return pref_.toString();
    }
}
