package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ArrayResult;
import code.maths.litteral.StrTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.types.KindPartType;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ExecPartTypeUtil {
    public static final int WILD_CARD_PRIO = 1;
    public static final int ARR_PRIO = 2;
    public static final int INT_PRIO = 3;
    public static final int TMP_PRIO = 4;

    private ExecPartTypeUtil(){
    }

    public static boolean processAnalyzeConstraintsExec(ExecResultPartType _root, ContextEl _context) {
        ExecPartType root_ = _root.getPartType();
        ExecPartType current_ = root_;
        while (current_ != null) {
            ExecPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                if (koTmp(_context, current_)) {
                    return false;
                }
                ExecPartType next_ = current_.getNextSibling();
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    if (koTmp(_context, par_)) {
                        return false;
                    }
                    current_ = null;
                    continue;
                }
                if (par_ == null) {
                    current_ = null;
                    continue;
                }
                current_ = par_;
            }
        }
        return true;
    }

    private static boolean koTmp(ContextEl _context, ExecPartType _current) {
        return _current instanceof ExecTemplatePartType && !((ExecTemplatePartType) _current).okTmp(_context);
    }

    public static boolean checkParametersCount(ExecResultPartType _root, ContextEl _context) {
        ExecPartType root_ = _root.getPartType();
        ExecPartType current_ = root_;
        while (current_ != null) {
            ExecPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                if (isNotCorrectParam(_context, current_)) {
                    return false;
                }
                ExecPartType next_ = current_.getNextSibling();
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    if (isNotCorrectParam(_context, par_)) {
                        return false;
                    }
                    current_ = null;
                    continue;
                }
                if (par_ == null) {
                    current_ = null;
                    continue;
                }
                current_ = par_;
            }
        }
        return true;
    }

    private static boolean isNotCorrectParam(ContextEl _context, ExecPartType _current) {
        return !skip(_current) && !ExecInherits.correctNbParameters(_current.getAnalyzedType(), _context);
    }

    private static boolean skip(ExecPartType _current) {
        if (_current.getParent() instanceof ExecInnerPartType) {
            return true;
        }
        if (_current.getParent() instanceof ExecTemplatePartType && _current.getIndex() == 0) {
            return true;
        }
        return skipByClass(_current);
    }

    private static boolean skipByClass(ExecPartType _current) {
        if (_current instanceof ExecArraryPartType) {
            return true;
        }
        if (_current instanceof ExecWildCardPartType) {
            return true;
        }
        if (_current instanceof ExecRefPartType) {
            return true;
        }
        return _current instanceof ExecEmptyWildCardPart;
    }
    public static String processPrettyType(String _input) {
        StringBuilder out_ = new StringBuilder();
        ExecAnalyzingType loc_ = analyzeLocalExec(_input);
        ExecPartType root_ = ExecPartType.createPartTypeExec(null, 0, loc_, loc_.getValues().getValue(0));
        addValues(root_, loc_);
        ExecPartType current_ = root_;
        while (current_ != null) {
            if (current_ instanceof ExecLeafPartType) {
                String t_ = ((ExecLeafPartType)current_).getTypeName();
                out_.append(t_);
            }
            ExecParentChildType parChild_ = createFirstChildExec(current_);
            ExecPartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getPrettyBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                ExecPartType next_ = createNextSiblingExec(current_);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(next_.getPreviousOperator());
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    out_.append(par_.getPrettyEnd());
                    current_ = null;
                    continue;
                }
                if (par_ == null) {
                    current_ = null;
                    continue;
                }
                out_.append(par_.getPrettyEnd());
                current_ = par_;
            }
        }
        return out_.toString();
    }
    public static String processPrettySingleType(String _input) {
        StringBuilder out_ = new StringBuilder();
        ExecAnalyzingType loc_ = analyzeLocalExec(_input);
        ExecPartType root_ = ExecPartType.createPartTypeExec(null, 0, loc_, loc_.getValues().getValue(0));
        addValues(root_, loc_);
        ExecPartType current_ = root_;
        while (current_ != null) {
            if (current_ instanceof ExecLeafPartType) {
                String t_ = ((ExecLeafPartType)current_).getTypeName();
                out_.append(t_);
            }
            ExecParentChildType parChild_ = createFirstChildExec(current_);
            ExecPartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getPrettyBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                ExecPartType next_ = createNextSiblingExec(current_);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(next_.getPreviousOperatorSingle());
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    out_.append(par_.getPrettyEnd());
                    current_ = null;
                    continue;
                }
                if (par_ == null) {
                    current_ = null;
                    continue;
                }
                out_.append(par_.getPrettyEnd());
                current_ = par_;
            }
        }
        return out_.toString();
    }
    public static ExecResultPartType processExec(String _input,ContextEl _an) {
        StringBuilder out_ = new StringBuilder();
        if (!okDoubleDotIndexes(_input)) {
            return new ExecResultPartType("",null);
        }
        ExecAnalyzingType loc_ = analyzeLocalExec(_input);
        ExecPartType root_ = ExecPartType.createPartTypeExec(null, 0, loc_, loc_.getValues().getValue(0));
        addValues(root_, loc_);
        ExecPartType current_ = root_;
        while (current_ != null) {
            if (current_ instanceof ExecLeafPartType) {
                ((ExecLeafPartType)current_).checkDynExistence(_an);
                String t_ = ((ExecLeafPartType)current_).exportHeader();
                if (t_.trim().isEmpty()) {
                    return new ExecResultPartType("",null);
                }
                out_.append(t_);
            }
            ExecParentChildType parChild_ = createFirstChildExec(current_);
            ExecPartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                if (current_ instanceof ExecParentPartType) {
                    if (!((ExecParentPartType)current_).analyzeTree(_an)) {
                        return new ExecResultPartType("",null);
                    }
                }
                ExecPartType next_ = createNextSiblingExec(current_);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(next_.getPreviousOperator());
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    if (!par_.analyzeTree(_an)) {
                        return new ExecResultPartType("",null);
                    }
                    out_.append(par_.getEnd());
                    current_ = null;
                    continue;
                }
                if (par_ == null) {
                    current_ = null;
                    continue;
                }
                out_.append(par_.getEnd());
                current_ = par_;
            }
        }
        return new ExecResultPartType(out_.toString(), root_);
    }
    private static ExecParentChildType createFirstChildExec(ExecPartType _parent) {
        if (!(_parent instanceof ExecParentPartType)) {
            return new ExecParentChildType(null,null);
        }
        ExecParentPartType par_ = (ExecParentPartType) _parent;
        StrTypes last_ = par_.getStrTypes();
        String v_ = last_.firstValue();
        ExecAnalyzingType an_ = analyzeLocalExec(v_);
        ExecPartType p_ = ExecPartType.createPartTypeExec(par_, 0, an_, v_);
        addValues(p_, an_);
        return new ExecParentChildType(par_,p_);
    }

    private static ExecPartType createNextSiblingExec(ExecPartType _parent) {
        ExecParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof ExecBinaryType)) {
            return null;
        }
        ExecBinaryType b_ = (ExecBinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        StrTypes last_ = par_.getStrTypes();
        if (last_.size() <= indexNext_) {
            return null;
        }
        String v_ = last_.getValue(indexNext_);
        ExecAnalyzingType an_ = analyzeLocalExec(v_);
        ExecPartType p_ = ExecPartType.createPartTypeExec(b_,indexNext_, an_, v_);
        p_.setPreviousSibling(_parent);
        addValues(p_, an_);
        return p_;
    }
    private static void addValues(ExecPartType _p, ExecAnalyzingType _an) {
        if (!(_p instanceof ExecParentPartType)) {
            return;
        }
        if (_p instanceof ExecTemplatePartType) {
            StrTypes values_;
            values_ = _an.getValues();
            values_.remove(values_.getValues().getLastIndex());
            ((ExecParentPartType)_p).getStrTypes().addAllEntries(values_);
        } else {
            ((ExecParentPartType)_p).getStrTypes().addAllEntries(_an.getValues());
        }
    }

    private static ExecAnalyzingType analyzeLocalExec(String _string) {
        ExecAnalyzingType a_ = new ExecAnalyzingType();
        StrTypes values_ = a_.getValues();
        if (_string.trim().isEmpty()) {
            values_.addEntry(IndexConstants.FIRST_INDEX, _string);
            a_.setError(true);
            return a_;
        }
        if (StringExpUtil.isTypeLeafExec(_string)) {
            a_.setKind(KindPartType.TYPE_NAME);
            a_.setupValueExec(_string);
            return a_;
        }
        if (StringUtil.quickEq(_string.trim(), Templates.SUB_TYPE)) {
            a_.setKind(KindPartType.EMPTY_WILD_CARD);
            a_.setupValueExec(_string);
            return a_;
        }
        if (_string.trim().startsWith(Templates.SUB_TYPE)) {
            a_.setPrio(WILD_CARD_PRIO);
            a_.setupUnaryValuesExec(_string, Templates.SUB_TYPE);
            return a_;
        }
        if (_string.trim().startsWith(Templates.SUP_TYPE)) {
            if (StringUtil.quickEq(_string.trim(), Templates.SUP_TYPE)) {
                a_.setError(true);
            }
            a_.setPrio(WILD_CARD_PRIO);
            a_.setupUnaryValuesExec(_string, Templates.SUP_TYPE);
            return a_;
        }
        if (_string.trim().startsWith("~")) {
            if (StringUtil.quickEq(_string.trim(), "~")) {
                a_.setError(true);
            }
            a_.setPrio(WILD_CARD_PRIO);
            a_.setupUnaryValuesExec(_string, "~");
            return a_;
        }
        StrTypes operators_ = a_.getOperators();
        ArrayResult res_ = StringExpUtil.tryGetArray(_string, values_, operators_);
        if (res_ != ArrayResult.NONE) {
            if (res_ == ArrayResult.ERROR) {
                values_.addEntry(IndexConstants.FIRST_INDEX, _string);
                a_.setError(true);
            } else {
                a_.setPrio(ARR_PRIO);
            }
            return a_;
        }
        if (_string.trim().startsWith(Templates.ARR_BEG_STRING)) {
            a_.setPrio(ARR_PRIO);
            a_.setupUnaryValuesExec(_string, Templates.ARR_BEG_STRING);
            return a_;
        }
        int count_ = 0;
        int len_ = _string.length();
        int i_ = 0;
        int prio_ = TMP_PRIO;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (curChar_ == Templates.LT) {
                if (count_== 0 && prio_ == TMP_PRIO) {
                    operators_.clear();
                    operators_.addEntry(i_,Templates.TEMPLATE_BEGIN);
                }
                count_++;
            }
            if (curChar_ == Templates.COMMA && count_ == 1 && prio_ == TMP_PRIO) {
                operators_.addEntry(i_, Templates.TEMPLATE_SEP);
            }
            if (curChar_ == Templates.GT) {
                count_--;
                if (count_ == 0 && prio_ == TMP_PRIO) {
                    operators_.addEntry(i_,Templates.TEMPLATE_END);
                }
            }
            if (count_ == 0) {
                if (_string.startsWith(Templates.INNER_TYPE,i_) || curChar_ == '-') {
                    if (prio_ > INT_PRIO) {
                        operators_.clear();
                        prio_ = INT_PRIO;
                    }
                    if (curChar_ == Templates.SEP_CLASS_CHAR){
                        operators_.addEntry(i_,Templates.INNER_TYPE);
                        i_++;
                    } else {
                        operators_.addEntry(i_,"-");
                    }
                }
            }
            i_++;
        }
        a_.setPrio(prio_);
        a_.setupValuesExec(_string);
        return a_;
    }

    private static boolean okDoubleDotIndexes(String _input) {
        int count_ = 0;
        int len_ = _input.length();
        int i_ = 0;
        while (i_ < len_) {
            char curChar_ = _input.charAt(i_);
            if (curChar_ == Templates.LT) {
                count_++;
            }
            if (curChar_ == Templates.GT) {
                if (count_ == 0) {
                    return false;
                }
                count_--;
            }
            if (curChar_ == Templates.COMMA) {
                if (count_ == 0) {
                    return false;
                }
            }
            i_++;
        }
        return count_ <= 0;
    }
}
