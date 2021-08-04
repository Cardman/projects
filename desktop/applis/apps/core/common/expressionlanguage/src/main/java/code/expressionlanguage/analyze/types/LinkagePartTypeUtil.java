package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class LinkagePartTypeUtil {
    private LinkagePartTypeUtil() {
    }

    private static String buildOffsetPartDefault(AnaPartType _current) {
        if (_current instanceof AnaNamePartType) {
            AnaNamePartType current_ = (AnaNamePartType) _current;
            if (!current_.isBuildRef()) {
                return _current.buildOffsetPart("", "");
            }
            FileBlock currentFile_ = current_.getCurrentFile();
            FileBlock refFileName_ = current_.getRefFileName();
            int value_ = current_.getValue();
            return _current.buildOffsetPart(ExportCst.href(currentFile_,refFileName_,value_), current_.getTitleRef());
        }
        if (_current instanceof AnaVariablePartType) {
            AnaVariablePartType current_ = (AnaVariablePartType) _current;
            FileBlock refFileName_ = current_.getRefFileName();
            int value_ = current_.getValue();
            if (refFileName_ != null) {
                return _current.buildOffsetPartDefault(ExportCst.href(current_.getCurrentFile(), refFileName_,value_));
            } else {
                return _current.buildOffsetPartDefault(ExportCst.href(value_));
            }
        }
        return _current.buildOffsetPart();
    }

    public static void processAnalyzeConstraintsRepParts(AnaResultPartType _className, CustList<PartOffset> _parts) {
        PreLinkagePartTypeUtil.processAnalyzeConstraintsRepErrs(_className);
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
            String cand_ = _par.buildOffsetPart();
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
        String cand_ = _par.buildOffsetPart();
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
}
