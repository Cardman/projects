package code.maths.litteraladv;

import code.maths.litteralcom.*;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class MaParser {
    private MaParser() {
    }

    static String processEl(String _el, CustList<Replacement> _conf) {
        if (_conf == null) {
            return "#"+_el;
        }
        StringMap<String> aliases_ = new StringMap<String>();
        String outStr_ = checkedAliases(_el, aliases_, _conf);
        if (!outStr_.isEmpty()) {
            return outStr_;
        }
        String outVars_ = checkedVariables(_el, _conf);
        if (!outVars_.isEmpty()) {
            return outVars_;
        }
        StringMap<MaStruct> values_ = new StringMap<MaStruct>();
        StringList varNames_ = new StringList();
        StringMap<String> repl_ = new StringMap<String>();
        for (Replacement r: _conf) {
            if (!r.getOldString().startsWith("#")) {
                String user_ = r.getOldString();
                values_.addEntry(user_,null);
                varNames_.add(user_);
                repl_.addEntry(user_,r.getNewString());
            }
        }
        String strValues_ = feedVars(aliases_, repl_, values_, varNames_);
        if (!strValues_.isEmpty()) {
            return strValues_;
        }
        MaError err_ = new MaError();
        MaStruct out_ = analyzeCalculate(_el, values_, aliases_, err_, false, varNames_);
        return MaNumParsers.toStrNb(out_,err_);
    }

    private static String feedVars(StringMap<String> _aliases, StringMap<String> _repl, StringMap<MaStruct> _values, StringList _varNames) {
        while (true) {
            boolean calculatedValue_ = false;
            for (EntryCust<String,String> e: _repl.entryList()) {
                String k_ = e.getKey();
                if (_values.getVal(k_) != null) {
                    continue;
                }
                String cf_ = e.getValue();
                MaError err_ = new MaError();
                MaStruct val_ = analyzeCalculate(cf_, _values, _aliases, err_, true, _varNames);
                if (val_ != null) {
                    _values.set(k_,val_);
                    calculatedValue_ = true;
                    break;
                }
                if (err_.getOffset() > -1) {
                    return "#" + cf_;
                }
            }
            if (!calculatedValue_) {
                break;
            }
        }
        return "";
    }
    private static String checkedVariables(String _el, CustList<Replacement> _conf) {
        StringList allVars_ = new StringList();
        for (Replacement r: _conf) {
            if (!r.getOldString().startsWith("#")) {
                String user_ = r.getOldString();
                if (!MathExpUtil.isWord(user_)) {
                    return "#" + _el;
                }
                allVars_.add(user_);
            }
        }
        if (allVars_.hasDuplicates()) {
            return "#"+_el;
        }
        return "";
    }
    private static String checkedAliases(String _el, StringMap<String> _aliases, CustList<Replacement> _conf) {
        StringList allNew_ = new StringList();
        for (String f: fcts()) {
            _aliases.addEntry(f,f);
        }
        for (Replacement r: _conf) {
            if (koCoreRepl(r)) {
                return "#" + _el;
            }
            if (r.getOldString().startsWith("#")) {
                String user_ = r.getOldString().substring(1);
                String ref_ = r.getNewString();
                if (!MathExpUtil.isWord(user_) || !okKey(ref_)) {
                    return "#" + _el;
                }
                allNew_.add(user_);
                _aliases.put(user_, ref_);
            }
        }
        if (allNew_.hasDuplicates()) {
            return "#"+_el;
        }
        return notAll(_el, _aliases, fcts());
    }

    static String notAll(String _el, StringMap<String> _aliases, StringList _fcts) {
        if (!StringUtil.containsAllObj(_aliases.values(),_fcts)) {
            return "#"+ _el;
        }
        return "";
    }

    static StringList fcts() {
        return new StringList(MaOperationNode.TRUE_STRING,MaOperationNode.FALSE_STRING,
                MaOperationNode.PUIS,
                MaOperationNode.QUOT,
                MaOperationNode.MOD,MaOperationNode.MODTAUX,
                MaOperationNode.BEZOUT,
                MaOperationNode.SGN,MaOperationNode.ABS,
                MaOperationNode.ENT,MaOperationNode.TRONC,
                MaOperationNode.NUM,MaOperationNode.DEN,
                MaOperationNode.LG);
    }
    private static boolean koCoreRepl(Replacement _r) {
        return _r == null || StringUtil.nullToEmpty(_r.getOldString()).isEmpty() || StringUtil.nullToEmpty(_r.getNewString()).isEmpty();
    }

    private static boolean okKey(String _key) {
        return StringUtil.contains(fcts(),_key);
    }

    private static MaStruct analyzeCalculate(String _el, StringMap<MaStruct> _conf, StringMap<String> _mapping, MaError _err, boolean _procVar, StringList _varNames) {
        MaDelimiters d_ = checkSyntax(_el, _err,_varNames);
        if (_err.getOffset() > -1) {
            return null;
        }
        MaOperationsSequence opTwo_ = getOperationsSequence(IndexConstants.FIRST_INDEX, _el, d_);
        MaOperationNode op_ = MaOperationNode.createOperationNodeAndChild(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, null, opTwo_, _mapping);
        if (op_ == null) {
            return null;
        }
        CustList<MaOperationNode> all_ = getSortedDescNodes(op_, _err, d_, _mapping);
        if (_err.getOffset() > -1) {
            return null;
        }
        calculate(all_, _conf, _err,d_, _procVar);
        if (_err.getOffset() > -1) {
            return null;
        }
        return op_.getStruct();
    }

    public static CustList<MaOperationNode> getSortedDescNodes(MaOperationNode _root, MaError _error, MaDelimiters _del, StringMap<String> _mapping) {
        CustList<MaOperationNode> list_ = new CustList<MaOperationNode>();
        MaOperationNode c_ = _root;
        while (c_ != null) {
            c_ = getAnalyzedNext(c_, _root, list_, _error, _del, _mapping);
        }
        return list_;
    }

    public static MaOperationNode getAnalyzedNext(MaOperationNode _current, MaOperationNode _root, CustList<MaOperationNode> _sortedNodes, MaError _error, MaDelimiters _del, StringMap<String> _mapping) {
        MaOperationNode next_ = createFirstChild(_current, _error, _del, _mapping);
        if (_error.getOffset() > -1) {
            return null;
        }
        if (next_ != null) {
            ((MethodMaOperation) _current).append(next_);
            return next_;
        }
        MaOperationNode current_ = _current;
        while (true) {
            current_.setOrder(_sortedNodes.size());
            _sortedNodes.add(current_);
            next_ = createNextSibling(current_, _error, _del, _mapping);
            if (_error.getOffset() > -1) {
                return null;
            }
            if (next_ != null) {
                next_.getPar().append(next_);
                return next_;
            }
            MaOperationNode par_ = current_.getPar();
            if (par_ == _root) {
                return processRoot(_sortedNodes, par_);
            }
            if (par_ == null) {
                return null;
            }
            current_ = par_;
        }
    }

    private static MaOperationNode processRoot(CustList<MaOperationNode> _sortedNodes, MaOperationNode _par) {
        _par.setOrder(_sortedNodes.size());
        _sortedNodes.add(_par);
        return null;
    }

    private static MaOperationNode createFirstChild(MaOperationNode _block, MaError _error, MaDelimiters _delimiter, StringMap<String> _mapping) {
        if (!(_block instanceof MethodMaOperation)) {
            return null;
        }
        MethodMaOperation block_ = (MethodMaOperation) _block;
        if (block_.getChs().isEmpty()) {
            return null;
        }
        String value_ = block_.getChs().getValue(0);
        int curKey_ = block_.getChs().getKey(0);
        int offset_ = block_.getIndexExp()+curKey_;
        MaOperationsSequence r_ = getOperationsSequence(offset_, value_, _delimiter);
        MaOperationNode op_ = MaOperationNode.createOperationNodeAndChild(offset_, IndexConstants.FIRST_INDEX, block_, r_, _mapping);
        if (op_ == null) {
            _error.setOffset(offset_);
            return null;
        }
        return op_;
    }

    private static MaOperationNode createNextSibling(MaOperationNode _block, MaError _error, MaDelimiters _delimiter, StringMap<String> _mapping) {
        MethodMaOperation p_ = _block.getPar();
        if (p_ == null) {
            return null;
        }
        StrTypes children_ = p_.getChs();
        int nextIndex_ = _block.getChildIndex() + 1;
        if (nextIndex_ >= children_.size()) {
            return null;
        }
        String value_ = children_.getValue(nextIndex_);
        int curKey_ = children_.getKey(nextIndex_);
        int offset_ = p_.getIndexExp()+curKey_;
        MaOperationsSequence r_ = getOperationsSequence(offset_, value_, _delimiter);
        MaOperationNode op_ = MaOperationNode.createOperationNodeAndChild(offset_, nextIndex_, p_, r_, _mapping);
        if (op_ == null) {
            _error.setOffset(offset_);
            return null;
        }
        return op_;
    }

    static void calculate(CustList<MaOperationNode> _nodes, StringMap<MaStruct> _context, MaError _error, MaDelimiters _del, boolean _procVar) {
        int fr_ = 0;
        int len_ = _nodes.size();
        while (fr_ < len_) {
            MaOperationNode o = _nodes.get(fr_);
            if (_procVar && o instanceof VariableMaOperation) {
                String varName_ = ((VariableMaOperation) o).getVarName();
                if (_context.getVal(varName_) == null) {
                    return;
                }
            }
            o.calculate(_context, _error,_del);
            if (_error.getOffset() > -1) {
                return;
            }
            MaStruct res_ = o.getStruct();
            fr_ = MaOperationNode.getNextIndex(o, res_);
        }
    }
    private static MaDelimiters checkSyntax(String _string, MaError _error, StringList _varNames) {
        MaDelimiters d_ = new MaDelimiters();
        int len_ = _string.length();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < len_) {
            if (!StringUtil.isWhitespace(_string.charAt(i_))) {
                break;
            }
            i_++;
        }
        if (i_ >= len_) {
            _error.setOffset(i_);
            return d_;
        }
        MathState m_ = new MathState();
        while (m_.getIndex() < len_) {
            loop(_string, _error, d_, len_, m_,_varNames);
        }
        return redirect(_error, m_,d_);
    }
    private static void loop(String _string, MaError _error, MaDelimiters _d, int _len, MathState _m, StringList _varNames) {
        char curChar_ = _string.charAt(_m.getIndex());
        if (MathExpUtil.isWordChar(curChar_)) {
            _m.setIndex(processWordChar(_string, _d, _len, _m.getIndex(), curChar_,_varNames));
            return;
        }
        if (curChar_ == MatCommonCst.DOT && charIsDig(_string,_m)) {
            int j_ = addNumberInfo(_d, _m.getIndex() + 1, _m.getIndex(), _string);
            _d.getNbParts().last().insert(0, MatCommonCst.DOT);
            _m.setIndex(j_);
            return;
        }
        MaStackOperators s_ = _m.getStack();
        processLeftDel(_m, curChar_, s_, MatCommonCst.PAR_LEFT);
        processLeftDel(_m, curChar_, s_, MatCommonCst.ARR_LEFT);
        if (curChar_ == MatCommonCst.PAR_RIGHT) {
            if (misMatchDel(s_, MatCommonCst.PAR_LEFT)) {
                _error.setOffset(_m.getIndex());
                _m.setIndex(_len);
                return;
            }
            s_.remove();
        }
        if (curChar_ == MatCommonCst.ARR_RIGHT) {
            if (misMatchDel(s_, MatCommonCst.ARR_LEFT)) {
                _error.setOffset(_m.getIndex());
                _m.setIndex(_len);
                return;
            }
            s_.remove();
        }
        if (curChar_ == MatCommonCst.SEP_ARG && s_.empty()) {
            _error.setOffset(_m.getIndex());
            _m.setIndex(_len);
            return;
        }
        _m.setIndex(procOper(_d, _m.getIndex(), curChar_));
    }

    private static void processLeftDel(MathState _m, char _curChar, MaStackOperators _s, char _left) {
        if (_curChar == _left) {
            _s.add(_m.getIndex(), _left);
        }
    }

    private static boolean misMatchDel(MaStackOperators _s, char _left) {
        return _s.empty() || _s.oper() != _left;
    }

    private static boolean charIsDig(String _string, MathState _m) {
        int index_ = _m.getIndex() + 1;
        return index_ < _string.length() && MathExpUtil.isDigit(_string.charAt(index_));
    }
    private static MaDelimiters redirect(MaError _error, MathState _state, MaDelimiters _d) {
        if (!_state.getStack().empty() && _error.getOffset() < 0) {
            _error.setOffset(_state.getIndex());
        }
        return _d;
    }
    private static int processWordChar(String _string, MaDelimiters _d, int _len, int _from, char _curChar, StringList _varNames) {
        if (isDigitFirst(_string, _len, _from, _curChar, _varNames)) {
            return addNumberInfo(_d, _from, _from, _string);
        }
        MatVariableInfo var_ = new MatVariableInfo();
        var_.setFirstChar(_from);
        MatVariableResult res_ = MatVariableResult.build(_string, _len, _from);
        int i_ = res_.getIndex();
        var_.setLastChar(i_);
        var_.setName(res_.getName());
        _d.getVarParts().add(var_);
        return i_;
    }

    private static boolean isDigitFirst(String _string, int _len, int _from, char _curChar, StringList _varNames) {
        if (!MathExpUtil.isDigit(_curChar)) {
            return false;
        }
        int i_ = _from+1;
        while (i_ < _len) {
            char ch_ = _string.charAt(i_);
            if (MathExpUtil.isWordChar(ch_)) {
                i_++;
                continue;
            }
            break;
        }
        int endWord_ = i_;
        i_ = skipWhite(_string, _len, i_);
        if (charIs(_string, _len, i_, '/') || charIs(_string, _len, i_, '.')) {
            return true;
        }
        if (charIs(_string,_len,i_,'(')) {
            return false;
        }
        return !StringUtil.contains(_varNames,_string.substring(_from,endWord_));
    }
    private static int skipWhite(String _string, int _len,int _from) {
        int to_ = _from;
        while (to_ < _len) {
            char ch_ = _string.charAt(to_);
            if (StringUtil.isWhitespace(ch_)) {
                to_++;
                continue;
            }
            break;
        }
        return to_;
    }
    private static boolean charIs(String _string, int _len,int _i, char _ch) {
        return _i < _len && _string.charAt(_i) == _ch;
    }
    private static int addNumberInfo(MaDelimiters _d, int _from, int _begin, String _string) {
        MatNumberResult res_ = build(_from, _string);
        int i_ = res_.getIndex();
        _d.getNumbers().add(_begin);
        _d.getNumbers().add(i_);
        _d.getNbParts().add(res_.getNbInfo());
        return i_;
    }

    public static MatNumberResult build(int _from, String _string) {
        StringBuilder nbInfo_ = new StringBuilder();
        int len_ = _string.length();
        int i_ = _from;
        boolean frac_ = false;
        while (i_ < len_) {
            char cur_ = _string.charAt(i_);
            if (cur_ == MatNumberResult.SEP_RATE) {
                nbInfo_.append(cur_);
                i_++;
                frac_ = true;
            } else if (StringUtil.isWhitespace(cur_) || ((cur_ == '-') && frac_)) {
                nbInfo_.append(cur_);
                i_++;
            } else if (MathExpUtil.isDigit(cur_) || cur_ == MatNumberResult.DOT) {
                nbInfo_.append(cur_);
                i_++;
                frac_ = false;
            } else {
                break;
            }
        }
        return new MatNumberResult(nbInfo_,i_);
    }

    private static int procOper(MaDelimiters _d, int _i, char _curChar) {
        if (MathExpUtil.allOp(_curChar)) {
            _d.getOperatorsIndexes().add(_i);
        }
        return _i+1;
    }
    static MaOperationsSequence getOperationsSequence(int _offset, String _string,
                                                      MaDelimiters _d) {
        int len_ = _string.length();
        int i_ = StringUtil.getFirstPrintableCharIndex(_string);
        if (i_ < 0) {
            MaOperationsSequence op_ = new MaOperationsSequence();
            op_.setOpers(new StrTypes());
            op_.setupValue(_string);
            return op_;
        }
        int lastPrintChar_ = len_ - 1;
        while (StringUtil.isWhitespace(_string.charAt(lastPrintChar_))) {
            lastPrintChar_--;
        }
        len_ = lastPrintChar_+1;
        for (MatVariableInfo v: _d.getVarParts()) {
            if (v.getFirstChar() == _offset + i_) {
                int iVar_ = v.getLastChar();
                if (iVar_ != _offset + lastPrintChar_ + 1) {
                    break;
                }
                MaOperationsSequence op_ = new MaOperationsSequence();
                op_.setType(MatConstType.LOC_VAR);
                op_.setOpers(new StrTypes());
                op_.setupValue(v.getName());
                return op_;
            }
        }
        int begin_ = _d.getNumbers().indexOfNb((long) _offset + i_);
        int end_ = _d.getNumbers().indexOfNb((long) _offset + lastPrintChar_ + 1L);
        if (delimits(begin_, end_)) {
            MaOperationsSequence op_ = new MaOperationsSequence();
            op_.setCst(begin_/2);
            op_.setType(MatConstType.NUMBER);
            op_.setOpers(new StrTypes());
            op_.setupValue(_string);
            return op_;
        }
        
        MathAdvAfUnaryParts mat_ = new MathAdvAfUnaryParts(_string,i_, lastPrintChar_);
        while (mat_.getCurrent() < len_) {
            mat_.loop(_offset, _string, _d);
        }
        MaOperationsSequence op_ = new MaOperationsSequence();
        op_.setPrio(mat_.getPrio());
        op_.setOpers(mat_.getOpers());
        op_.setFct(mat_.getFct());
        op_.setupValues(_string);
        return op_;
    }

    private static boolean delimits(int _begin, int _end) {
        return _begin > IndexConstants.INDEX_NOT_FOUND_ELT && _begin + 1 == _end;
    }

}
