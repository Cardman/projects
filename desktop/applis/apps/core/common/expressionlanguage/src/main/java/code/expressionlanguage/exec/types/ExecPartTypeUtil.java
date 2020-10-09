package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ArrayResult;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.types.KindPartType;
import code.util.*;
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
        while (true) {
            ExecPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
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
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                current_ = par_;
            }
            if (stop_) {
                break;
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
        while (true) {
            ExecPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
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
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return true;
    }

    private static boolean isNotCorrectParam(ContextEl _context, ExecPartType _current) {
        return !skip(_current) && !ExecTemplates.correctNbParameters(_current.getAnalyzedType(), _context);
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
        return _current instanceof ExecEmptyWildCardPart;
    }
    public static String processPrettyType(String _input) {
        StringBuilder out_ = new StringBuilder();
        Ints indexes_ = getIndexesExec(_input);
        ExecAnalyzingType loc_ = analyzeLocalExec(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        ExecPartType root_ = ExecPartType.createPartTypeExec(null, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        ExecPartType current_ = root_;
        while (true) {
            if (current_ instanceof ExecLeafPartType) {
                String t_ = ((ExecLeafPartType)current_).getTypeName();
                out_.append(t_);
            }
            ExecParentChildType parChild_ = createFirstChildExec(current_, loc_, dels_);
            ExecPartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getPrettyBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                ExecPartType next_ = createNextSiblingExec(current_, loc_, dels_);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(((ExecBinaryType) par_).getSeparator(current_.getIndex()));
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    out_.append(par_.getPrettyEnd());
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                out_.append(par_.getPrettyEnd());
                dels_.removeLast();
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return out_.toString();
    }
    public static String processPrettySingleType(String _input) {
        StringBuilder out_ = new StringBuilder();
        Ints indexes_ = getIndexesExec(_input);
        ExecAnalyzingType loc_ = analyzeLocalExec(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        ExecPartType root_ = ExecPartType.createPartTypeExec(null, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        ExecPartType current_ = root_;
        while (true) {
            if (current_ instanceof ExecLeafPartType) {
                String t_ = ((ExecLeafPartType)current_).getTypeName();
                out_.append(t_);
            }
            ExecParentChildType parChild_ = createFirstChildExec(current_, loc_, dels_);
            ExecPartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getPrettyBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                ExecPartType next_ = createNextSiblingExec(current_, loc_, dels_);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(((ExecBinaryType) par_).getSingleSeparator(current_.getIndex()));
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    out_.append(par_.getPrettyEnd());
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                out_.append(par_.getPrettyEnd());
                dels_.removeLast();
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return out_.toString();
    }
    public static ExecResultPartType processExec(String _input,ContextEl _an) {
        StringBuilder out_ = new StringBuilder();
        Ints indexes_ = getIndexesExec(_input);
        if (indexes_ == null) {
            return new ExecResultPartType("",null);
        }
        ExecAnalyzingType loc_ = analyzeLocalExec(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        ExecPartType root_ = ExecPartType.createPartTypeExec(null, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        ExecPartType current_ = root_;
        while (true) {
            if (current_ instanceof ExecLeafPartType) {
                ((ExecLeafPartType)current_).checkDynExistence(_an, dels_);
                String t_ = ((ExecLeafPartType)current_).exportHeader();
                if (t_.trim().isEmpty()) {
                    return new ExecResultPartType("",null);
                }
                out_.append(t_);
            }
            ExecParentChildType parChild_ = createFirstChildExec(current_, loc_, dels_);
            ExecPartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                if (current_ instanceof ExecParentPartType) {
                    if (!((ExecParentPartType)current_).analyzeTree(_an, dels_)) {
                        return new ExecResultPartType("",null);
                    }
                }
                ExecPartType next_ = createNextSiblingExec(current_, loc_, dels_);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(((ExecBinaryType) par_).getSeparator(current_.getIndex()));
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    if (!par_.analyzeTree(_an, dels_)) {
                        return new ExecResultPartType("",null);
                    }
                    out_.append(par_.getEnd());
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                out_.append(par_.getEnd());
                dels_.removeLast();
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return new ExecResultPartType(out_.toString(), root_);
    }
    private static ExecParentChildType createFirstChildExec(ExecPartType _parent, ExecAnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        if (!(_parent instanceof ExecParentPartType)) {
            return new ExecParentChildType(null,null);
        }
        ExecParentPartType par_ = (ExecParentPartType) _parent;
        int indexPar_ = 0;
        int off_ = 0;
        ExecPartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        IntTreeMap< String> last_ = _dels.last();
        String v_ = last_.firstValue();
        ExecAnalyzingType an_ = analyzeLocalExec(off_, v_, _analyze.getIndexes());
        ExecPartType p_ = ExecPartType.createPartTypeExec(par_, 0, an_, last_);
        addValues(p_, _dels, an_);
        return new ExecParentChildType(par_,p_);
    }

    private static ExecPartType createNextSiblingExec(ExecPartType _parent, ExecAnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        ExecParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof ExecBinaryType)) {
            return null;
        }
        ExecBinaryType b_ = (ExecBinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        IntTreeMap< String> last_ = _dels.last();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int indexPar_ = indexNext_;
        int off_ = 0;
        ExecPartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        String v_ = last_.getValue(indexNext_);
        ExecAnalyzingType an_ = analyzeLocalExec(off_, v_, _analyze.getIndexes());
        ExecPartType p_ = ExecPartType.createPartTypeExec(b_,indexNext_, an_, last_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static void addValues(ExecPartType _p, CustList<IntTreeMap< String>> _dels, ExecAnalyzingType _an) {
        if (!(_p instanceof ExecParentPartType)) {
            return;
        }
        if (_p instanceof ExecTemplatePartType) {
            IntTreeMap<String> values_;
            values_ = new IntTreeMap< String>();
            values_.putAllMap(_an.getValues());
            values_.removeKey(values_.lastKey());
            _dels.add(values_);
        } else if (_p instanceof ExecInnerPartType) {
            IntTreeMap<String> values_;
            values_ = new IntTreeMap< String>();
            values_.putAllMap(_an.getValues());
            _dels.add(values_);
        } else {
            _dels.add(_an.getValues());
        }
    }

    private static ExecAnalyzingType analyzeLocalExec(int _offset, String _string, Ints _indexes) {
        ExecAnalyzingType a_ = new ExecAnalyzingType();
        a_.getIndexes().addAllElts(_indexes);
        if (_string.trim().isEmpty()) {
            a_.getValues().put((int)IndexConstants.FIRST_INDEX, _string);
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
            a_.setupWildCardValues(Templates.SUB_TYPE, _string);
            return a_;
        }
        if (_string.trim().startsWith(Templates.SUP_TYPE)) {
            if (StringUtil.quickEq(_string.trim(), Templates.SUP_TYPE)) {
                a_.setError(true);
            }
            a_.setPrio(WILD_CARD_PRIO);
            a_.setupWildCardValues(Templates.SUP_TYPE, _string);
            return a_;
        }
        ArrayResult res_ = StringExpUtil.tryGetArray(_string, a_.getValues(), a_.getOperators());
        if (res_ != ArrayResult.NONE) {
            if (res_ == ArrayResult.ERROR) {
                a_.getValues().put((int) IndexConstants.FIRST_INDEX, _string);
                a_.setError(true);
            } else {
                a_.setPrio(ARR_PRIO);
            }
            return a_;
        }
        if (_string.trim().startsWith(Templates.ARR_BEG_STRING)) {
            a_.setPrio(ARR_PRIO);
            a_.setupArrayValuesExec(_string);
            return a_;
        }
        int count_ = 0;
        int len_ = _string.length();
        int i_ = 0;
        int prio_ = TMP_PRIO;
        IntTreeMap<String> operators_;
        operators_ = new IntTreeMap<String>();
        while (i_ < len_) {
            char curChar_ = _string.charAt(i_);
            if (!_indexes.containsObj((long)i_+_offset)) {
                i_++;
                continue;
            }
            if (curChar_ == Templates.LT) {
                if (count_== 0 && prio_ == TMP_PRIO) {
                    operators_.clear();
                    operators_.put(i_,Templates.TEMPLATE_BEGIN);
                }
                count_++;
            }
            if (curChar_ == Templates.COMMA && count_ == 1 && prio_ == TMP_PRIO) {
                operators_.put(i_, Templates.TEMPLATE_SEP);
            }
            if (curChar_ == Templates.GT) {
                count_--;
                if (count_ == 0 && prio_ == TMP_PRIO) {
                    operators_.put(i_,Templates.TEMPLATE_END);
                }
            }
            if (count_ == 0) {
                if (curChar_ == Templates.SEP_CLASS_CHAR || curChar_ == '-') {
                    if (prio_ > INT_PRIO) {
                        operators_.clear();
                        prio_ = INT_PRIO;
                    }
                    if (curChar_ == Templates.SEP_CLASS_CHAR){
                        operators_.put(i_,Templates.INNER_TYPE);
                    } else {
                        operators_.put(i_,"-");
                    }
                }
            }
            i_++;
        }
        a_.getOperators().putAllMap(operators_);
        a_.setPrio(prio_);
        a_.setupValuesExec(_string);
        return a_;
    }

    private static Ints getIndexesExec(String _input) {
        return getDoubleDotIndexes(_input);
    }

    private static Ints getDoubleDotIndexes(String _input) {
        int count_ = 0;
        int len_ = _input.length();
        int i_ = 0;
        Ints indexes_ = new Ints();
        while (i_ < len_) {
            char curChar_ = _input.charAt(i_);
            if (curChar_ == Templates.LT) {
                indexes_.add(i_);
                count_++;
            }
            if (curChar_ == Templates.GT) {
                if (count_ == 0) {
                    return null;
                }
                indexes_.add(i_);
                count_--;
            }
            if (curChar_ == Templates.COMMA) {
                if (count_ == 0) {
                    return null;
                }
                indexes_.add(i_);
            }
            if (curChar_ == Templates.SEP_CLASS_CHAR) {
                if (StringExpUtil.nextCharIs(_input, i_ + 1, len_, Templates.SEP_CLASS_CHAR)) {
                    indexes_.add(i_);
                    i_++;
                }
            } else if (curChar_ == '-') {
                indexes_.add(i_);
            }
            i_++;
        }
        if (count_ > 0) {
            return null;
        }
        return indexes_;
    }
}
