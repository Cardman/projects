package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AbstractReplacingType;
import code.expressionlanguage.common.ArrayResult;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.types.KindPartType;
import code.maths.litteralcom.StrTypes;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ExecPartTypeUtil {
    public static final int WILD_CARD_PRIO = 1;
    public static final int ARR_PRIO = 2;
    public static final int INT_PRIO = 3;
    public static final int TMP_PRIO = 4;
    public static final String INNER_TYPE = "..";

    private ExecPartTypeUtil(){
    }
    public static String processPrettyType(String _input) {
        return processPretty(_input, false);
    }

    private static ExecPartType nextPretty(StringBuilder _out, ExecPartType _root, ExecParentPartType _par) {
        ExecPartType current_;
        if (_par == _root) {
            _out.append(_par.getPrettyEnd());
            current_ = null;
        } else if (_par == null) {
            current_ = null;
        } else {
            _out.append(_par.getPrettyEnd());
            current_ = _par;
        }
        return current_;
    }

    public static String processPrettySingleType(String _input) {
        return processPretty(_input, true);
    }

    private static String processPretty(String _input, boolean _single) {
        StringBuilder out_ = new StringBuilder();
        ExecAnalyzingType loc_ = analyzeLocalExec(_input);
        ExecPartType root_ = ExecPartType.createPartTypeExec(null, 0, loc_, loc_.getValuesEx().getValue(0));
        ExecPartType current_ = root_;
        while (current_ != null) {
            prettyLeaf(out_, current_);
            ExecPartType crChild_ = create(null, current_, 0);
            ExecParentChildType parChild_ = wrCh(current_, crChild_);
            ExecPartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getPrettyBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                ExecPartType next_ = create(current_, current_.getParent(), current_.getIndex() + 1);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(next_.getPreviousOperator(_single));
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                current_ = nextPretty(out_, root_, par_);
            }
        }
        return out_.toString();
    }

    private static void prettyLeaf(StringBuilder _out, ExecPartType _current) {
        if (_current instanceof ExecLeafPartType) {
            String t_ = ((ExecLeafPartType) _current).getTypeName();
            _out.append(t_);
        }
    }

    /**Calls Templates.isCorrect*/
    public static String correctClassPartsDynamic(String _className, ContextEl _context) {
        ExecResultPartType className_ = processExec(_className, _context);
        String res_ = className_.getResult();
        if (res_.isEmpty()) {
            return "";
        }
        if (!checkParametersCount(className_, _context)){
            return "";
        }
        if (processAnalyzeConstraintsExec(className_, _context)) {
            return res_;
        }
        return "";
    }
    private static boolean processAnalyzeConstraintsExec(ExecResultPartType _root, ContextEl _context) {
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
                if (koTmp(_context, root_, par_)) {
                    return false;
                }
                current_ = next(root_, par_);
            }
        }
        return true;
    }

    private static boolean koTmp(ContextEl _context, ExecPartType _root, ExecParentPartType _par) {
        return _par == _root && koTmp(_context, _par);
    }

    private static boolean koTmp(ContextEl _context, ExecPartType _current) {
        return _current instanceof ExecTemplatePartType && !((ExecTemplatePartType) _current).okTmp(_context);
    }

    private static boolean checkParametersCount(ExecResultPartType _root, ContextEl _context) {
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
                if (isNotCorrectParam(_context, root_, par_)) {
                    return false;
                }
                current_ = next(root_, par_);
            }
        }
        return true;
    }

    private static boolean isNotCorrectParam(ContextEl _context, ExecPartType _root, ExecParentPartType _par) {
        return _par == _root && isNotCorrectParam(_context, _par);
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
    private static ExecResultPartType processExec(String _input,ContextEl _an) {
        StringBuilder out_ = new StringBuilder();
        if (!okDoubleDotIndexes(_input)) {
            return new ExecResultPartType("",null);
        }
        ExecAnalyzingType loc_ = analyzeLocalExec(_input);
        ExecPartType root_ = ExecPartType.createPartTypeExec(null, 0, loc_, loc_.getValuesEx().getValue(0));
        ExecPartType current_ = root_;
        while (current_ != null) {
//            if (current_ instanceof ExecLeafPartType) {
//                ((ExecLeafPartType)current_).checkDynExistence(_an);
//                String t_ = ((ExecLeafPartType)current_).exportHeader();
//                if (t_.trim().isEmpty()) {
//                    return new ExecResultPartType("",null);
//                }
//                out_.append(t_);
//            }
            ExecPartType crChild_ = create(null, current_, 0);
            ExecParentChildType parChild_ = wrCh(current_, crChild_);
            ExecPartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                if (koAnalyzeTree(out_,_an, current_)) {
                    return new ExecResultPartType("", null);
                }
                ExecPartType next_ = create(current_, current_.getParent(), current_.getIndex() + 1);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(next_.getPreviousOperator());
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (koAnalyzeTree(_an, root_, par_)) {
                    return new ExecResultPartType("", null);
                }
                current_ = next(root_, par_);
                appendEnd(out_, par_);
            }
        }
        return new ExecResultPartType(out_.toString(), root_);
    }

    private static ExecPartType next(ExecPartType _root, ExecParentPartType _par) {
        if (_par == _root) {
            return null;
        }
        return _par;
    }

    private static boolean koAnalyzeTree(ContextEl _an, ExecPartType _root, ExecParentPartType _par) {
        return _par == _root && !_par.analyzeTree(_an);
    }

    private static void appendEnd(StringBuilder _out, ExecParentPartType _par) {
        if (_par != null) {
            _out.append(_par.getEnd());
        }
    }

    private static boolean koAnalyzeTree(StringBuilder _build,ContextEl _an, ExecPartType _current) {
        if (_current instanceof ExecLeafPartType) {
            if (_current instanceof ExecEmptyWildCardPart) {
                ((ExecEmptyWildCardPart)_current).checkDynExistence();
            }
            if (_current instanceof ExecNamePartType) {
                ((ExecNamePartType)_current).checkDynExistence(_an);
            }
            String t_ = ((ExecLeafPartType)_current).exportHeader();
            if (t_.trim().isEmpty()) {
                return true;
            }
            _build.append(t_);
        }
        return _current instanceof ExecParentPartType && !((ExecParentPartType) _current).analyzeTree(_an);
    }

    private static ExecParentChildType wrCh(ExecPartType _cur, ExecPartType _child) {
        ExecParentChildType parChild_;
        if (!(_cur instanceof ExecParentPartType)) {
            parChild_ = new ExecParentChildType(null, _child);
        } else {
            parChild_ = new ExecParentChildType((ExecParentPartType) _cur, _child);
        }
        return parChild_;
    }

    private static ExecPartType create(ExecPartType _prev, ExecPartType _pa, int _next) {
        if (!(_pa instanceof ExecParentPartType)) {
            return null;
        }
        ExecParentPartType par_ = (ExecParentPartType) _pa;
        StrTypes last_ = par_.getStrTypes();
        if (last_.size() <= _next) {
            return null;
        }
        String v_ = last_.getValue(_next);
        ExecAnalyzingType an_ = analyzeLocalExec(v_);
        ExecPartType p_ = ExecPartType.createPartTypeExec(par_, _next, an_, v_);
        p_.setPreviousSibling(_prev);
        return p_;
    }

    private static ExecAnalyzingType analyzeLocalExec(String _string) {
        ExecAnalyzingType a_ = new ExecAnalyzingType();
        StrTypes values_ = a_.getValuesEx();
        if (_string.trim().isEmpty()) {
            values_.addEntry(IndexConstants.FIRST_INDEX, _string);
            a_.setErrorEx();
            return a_;
        }
        if (StringExpUtil.isTypeLeafExec(_string)) {
            a_.setKindEx(KindPartType.TYPE_NAME);
            a_.setupValueExec(_string);
            return a_;
        }
        if (StringUtil.quickEq(_string.trim(), StringExpUtil.SUB_TYPE)) {
            a_.setKindEx(KindPartType.EMPTY_WILD_CARD);
            a_.setupValueExec(_string);
            return a_;
        }
        if (_string.trim().startsWith(StringExpUtil.SUB_TYPE)) {
            a_.setPrioEx(WILD_CARD_PRIO);
            a_.setupUnaryValuesExec(_string, StringExpUtil.SUB_TYPE);
            return a_;
        }
        if (_string.trim().startsWith(StringExpUtil.SUP_TYPE)) {
            if (StringUtil.quickEq(_string.trim(), StringExpUtil.SUP_TYPE)) {
                a_.setErrorEx();
            }
            a_.setPrioEx(WILD_CARD_PRIO);
            a_.setupUnaryValuesExec(_string, StringExpUtil.SUP_TYPE);
            return a_;
        }
        if (_string.trim().startsWith("~")) {
            if (StringUtil.quickEq(_string.trim(), "~")) {
                a_.setErrorEx();
            }
            a_.setPrioEx(WILD_CARD_PRIO);
            a_.setupUnaryValuesExec(_string, "~");
            return a_;
        }
        StrTypes operators_ = a_.getOperatorsEx();
        ArrayResult res_ = StringExpUtil.tryGetArray(_string, values_, operators_);
        if (res_ != ArrayResult.NONE) {
            if (res_ == ArrayResult.ERROR) {
                values_.addEntry(IndexConstants.FIRST_INDEX, _string);
                a_.setErrorEx();
            } else {
                a_.setPrioEx(ARR_PRIO);
            }
            return a_;
        }
        if (_string.trim().startsWith(AbstractReplacingType.ARR_BEG_STRING)) {
            a_.setPrioEx(ARR_PRIO);
            a_.setupUnaryValuesExec(_string, AbstractReplacingType.ARR_BEG_STRING);
            return a_;
        }
        return loop(_string);
    }

    private static ExecAnalyzingType loop(String _string) {
        ExecAnalyzingType a_ = new ExecAnalyzingType();
        StrTypes operators_ = a_.getOperatorsEx();
        int count_ = 0;
        int len_ = _string.length();
        int i_ = 0;
        int prio_ = TMP_PRIO;
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (curChar_ == StringExpUtil.LT) {
                addBeginOp(operators_, count_, i_, prio_);
                count_++;
            }
            addCommaOp(operators_, count_, i_, prio_, curChar_);
            if (curChar_ == StringExpUtil.GT) {
                count_--;
                addEndOp(operators_, count_, i_, prio_);
            }
            if (inner(_string, count_, i_, curChar_)) {
                if (prio_ > INT_PRIO) {
                    operators_.clear();
                    prio_ = INT_PRIO;
                }
                if (curChar_ == StringExpUtil.SEP_CLASS_CHAR) {
                    operators_.addEntry(i_, INNER_TYPE);
                    i_++;
                } else {
                    operators_.addEntry(i_, "-");
                }
            }
            i_++;
        }
        a_.setPrioEx(prio_);
        a_.setupValuesExec(_string);
        return a_;
    }

    private static void addCommaOp(StrTypes _operators, int _count, int _index, int _prio, char _cur) {
        if (comma(_count, _prio, _cur)) {
            _operators.addEntry(_index, StringExpUtil.TEMPLATE_SEP);
        }
    }

    private static void addEndOp(StrTypes _operators, int _count, int _index, int _prio) {
        if (tmpPrio(_count, _prio)) {
            _operators.addEntry(_index, StringExpUtil.TEMPLATE_END);
        }
    }

    private static void addBeginOp(StrTypes _operators, int _count, int _index, int _prio) {
        if (tmpPrio(_count, _prio)) {
            _operators.clear();
            _operators.addEntry(_index,StringExpUtil.TEMPLATE_BEGIN);
        }
    }

    private static boolean inner(String _string, int _count, int _index, char _curChar) {
        return _count == 0 && (_string.startsWith(StringExpUtil.INNER_TYPE, _index) || _curChar == '-');
    }

    private static boolean comma(int _count, int _prio, char _curChar) {
        return _curChar == StringExpUtil.COMMA && _count == 1 && _prio == TMP_PRIO;
    }

    private static boolean tmpPrio(int _count, int _prio) {
        return _count== 0 && _prio == TMP_PRIO;
    }

    private static boolean okDoubleDotIndexes(String _input) {
        int count_ = 0;
        int len_ = _input.length();
        int i_ = 0;
        while (i_ < len_) {
            char curChar_ = _input.charAt(i_);
            if (curChar_ == StringExpUtil.LT) {
                count_++;
            }
            if (curChar_ == StringExpUtil.GT) {
                if (count_ == 0) {
                    return false;
                }
                count_--;
            }
            if (curChar_ == StringExpUtil.COMMA && count_ == 0) {
                return false;
            }
            i_++;
        }
        return count_ <= 0;
    }
}
