package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.*;
import code.util.Ints;
import code.util.StringList;

public final class PartTypeUtil {

    private PartTypeUtil() {}

    public static boolean checkParametersCount(String _className, StringMap<StringList> _inherit, ContextEl _context) {
        Ints indexes_ = ParserType.getQuickIndexes(_className);
        AnalyzingType loc_ = ParserType.analyzeQuickLocal(0, _className, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        PartType root_ = PartType.createQuickPartType(null, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            PartType child_ = createQuickFirstChild(current_, loc_, dels_);
            if (child_ != null) {
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.setAnalyzedType(dels_, _inherit);
                PartType next_ = createQuickNextSibling(current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.setAnalyzedType(dels_, _inherit);
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                dels_.removeLast();
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        current_ = root_;
        while (true) {
            PartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                if (isNotCorrectParam(_context, current_)) {
                    return false;
                }
                PartType next_ = current_.getNextSibling();
                ParentPartType par_ = current_.getParent();
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
                dels_.removeLast();
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return true;
    }

    private static boolean isNotCorrectParam(ContextEl _context, PartType _current) {
        return !skip(_current) && !Templates.correctNbParameters(_current.getAnalyzedType(), _context);
    }

    private static boolean skip(PartType _current) {
        if (_current.getParent() instanceof InnerPartType) {
            return true;
        }
        if (_current.getParent() instanceof TemplatePartType && _current.getIndex() == 0) {
            return true;
        }
        return skipByClass(_current);
    }

    private static boolean skipByClass(PartType _current) {
        if (_current instanceof VariablePartType) {
            return true;
        }
        if (_current instanceof ArraryPartType) {
            return true;
        }
        if (_current instanceof WildCardPartType) {
            return true;
        }
        if (_current instanceof EmptyWildCardPart) {
            return true;
        }
        return false;
    }

    private static PartType createQuickFirstChild(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        if (!(_parent instanceof ParentPartType)) {
            return null;
        }
        ParentPartType par_ = (ParentPartType) _parent;
        int indexPar_ = 0;
        int off_ = 0;
        PartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        IntTreeMap< String> last_ = _dels.last();
        String v_ = last_.firstValue();
        AnalyzingType an_ = ParserType.analyzeQuickLocal(off_, v_, _analyze.getIndexes());
        PartType p_ = PartType.createQuickPartType(par_, 0, an_, last_);
        addValues(p_, _dels, an_);
        return p_;
    }

    private static PartType createQuickNextSibling(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        ParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof BinaryType)) {
            return null;
        }
        BinaryType b_ = (BinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        IntTreeMap< String> last_ = _dels.last();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int indexPar_ = indexNext_;
        int off_ = 0;
        PartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        String v_ = last_.getValue(indexNext_);
        AnalyzingType an_ = ParserType.analyzeQuickLocal(off_, v_, _analyze.getIndexes());
        PartType p_ = PartType.createQuickPartType(b_,indexNext_, an_, last_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }

    private static void addValues(PartType _p, CustList<IntTreeMap< String>> _dels, AnalyzingType _an) {
        if (!(_p instanceof ParentPartType)) {
            return;
        }
        if (_p instanceof TemplatePartType) {
            IntTreeMap<String> values_;
            values_ = new IntTreeMap< String>();
            values_.putAllMap(_an.getValues());
            values_.removeKey(values_.lastKey());
            _dels.add(values_);
        } else if (_p instanceof InnerPartType) {
            IntTreeMap<String> values_;
            values_ = new IntTreeMap< String>();
            values_.putAllMap(_an.getValues());
            _dels.add(values_);
        } else {
            _dels.add(_an.getValues());
        }
    }
}
