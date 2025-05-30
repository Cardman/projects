package code.maths.litteraladv;

import code.maths.litteralcom.*;
import code.maths.montecarlo.AbsDoubleToStrConverter;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.CustomSeedGene;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class MaParser {
    private MaParser() {
    }

    public static String processEl(AbstractGenerator _gene, CustList<String> _rands, CustomSeedGene _cust, MaUserInput _input, AbsDoubleToStrConverter _conv) {
        String exp_ = StringUtil.nullToEmpty(_input.getEl());
        if (!_input.isOk()) {
            return "#####"+exp_;
        }
        MaParameters aliases_ = new MaParameters(_gene,_cust,_conv);
        String outStr_ = checkedAliases(exp_, _input.getRepl());
        if (!outStr_.isEmpty()) {
            return outStr_;
        }
        String outVars_ = checkedVariables(_input.getRepl());
        if (!outVars_.isEmpty()) {
            return outVars_;
        }
        StringMap<MaStruct> values_ = new StringMap<MaStruct>();
        StringList varNames_ = new StringList();
        StringMap<String> repl_ = new StringMap<String>();
        for (Replacement r: _input.getRepl()) {
//            if (!r.getOldString().startsWith("#")) {
//                String user_ = r.getOldString();
//                values_.addEntry(user_,null);
//                varNames_.add(user_);
//                repl_.addEntry(user_,r.getNewString());
//            }
            String user_ = r.getOldString();
            values_.addEntry(user_,MaNullStruct.NULL_VALUE);
            varNames_.add(user_);
            repl_.addEntry(user_,r.getNewString());
        }
        String strValues_ = feedVars(aliases_, repl_, values_, varNames_,_rands);
        if (!strValues_.isEmpty()) {
            return strValues_;
        }
        MaError err_ = new MaError();
        MaStruct out_ = analyzeCalculate(exp_, values_, aliases_, err_, false, varNames_,_rands);
        return MaNumParsers.toStrNb(out_,err_);
    }

    private static String feedVars(MaParameters _aliases, StringMap<String> _repl, StringMap<MaStruct> _values, StringList _varNames, CustList<String> _rands) {
        String message_;
        while (true) {
            Res res_ = res(_aliases, _repl, _values, _varNames,_rands);
            if (!res_.isCalculated()) {
                message_ = res_.getMessage();
                break;
            }
        }
        return message_;
    }
    private static Res res(MaParameters _aliases, StringMap<String> _repl, StringMap<MaStruct> _values, StringList _varNames, CustList<String> _rands) {
        boolean calculatedValue_ = false;
        int index_ = 0;
        for (EntryCust<String,String> e: _repl.entryList()) {
            if (_values.getValue(index_) == MaNullStruct.NULL_VALUE) {
                String cf_ = StringUtil.nullToEmpty(e.getValue());
                MaError err_ = new MaError();
                MaStruct val_ = analyzeCalculate(cf_, _values, _aliases, err_, true, _varNames,_rands);
                if (val_ != MaNullStruct.NULL_VALUE) {
                    _values.setValue(index_, val_);
                    calculatedValue_ = true;
                    break;
                }
                if (err_.getOffset() > -1) {
                    return new Res(false, "#" + e.getKey()+"#"+err_.getOffset());
                }
            }
            index_++;
        }
        return new Res(calculatedValue_,"");
    }
    private static String checkedVariables(CustList<Replacement> _conf) {
        StringList allVars_ = new StringList();
        for (Replacement r: _conf) {
//            if (!r.getOldString().startsWith("#")) {
//                String user_ = r.getOldString();
//                if (!MathExpUtil.isWord(user_)) {
//                    return "#" + _el;
//                }
//                allVars_.add(user_);
//            }
            String user_ = r.getOldString();
            if (!MathExpUtil.isWord(user_)) {
                return "###"+user_+"#"+r.getNewString();
            }
            if (StringUtil.contains(allVars_,user_)) {
                return "##"+user_+"#"+r.getNewString();
            }
            allVars_.add(user_);
        }
        return "";
    }
    private static String checkedAliases(String _el, CustList<Replacement> _conf) {
        for (Replacement r: _conf) {
            if (koCoreRepl(r)) {
                return "####" + _el;
            }
        }
        return "";
    }
//    private static String checkedAliases(String _el, StringMap<String> _aliases, CustList<Replacement> _conf) {
////        StringList allNew_ = new StringList();
////        for (String f: fcts()) {
////            _aliases.addEntry(f,f);
////        }
//        for (Replacement r: _conf) {
//            if (koCoreRepl(r)) {
//                return "#" + _el;
//            }
////            if (r.getOldString().startsWith("#")) {
////                String user_ = r.getOldString().substring(1);
////                String ref_ = r.getNewString();
////                if (!MathExpUtil.isWord(user_) || !okKey(ref_)) {
////                    return "#" + _el;
////                }
////                allNew_.add(user_);
////                _aliases.put(user_, ref_);
////            }
//        }
////        if (allNew_.hasDuplicates()) {
////            return "#"+_el;
////        }
//        return "";
////        return notAll(_el, _aliases, fcts());
//    }

//    static String notAll(String _el, StringMap<String> _aliases, StringList _fcts) {
//        if (!StringUtil.containsAllObj(_aliases.values(),_fcts)) {
//            return "#"+ _el;
//        }
//        return "";
//    }

//    static StringList fcts() {
//        return new StringList(MaOperationNode.TRUE_STRING,MaOperationNode.FALSE_STRING,
//                MaOperationNode.PUIS,
//                MaOperationNode.QUOT,
//                MaOperationNode.MOD,MaOperationNode.MODTAUX,
//                MaOperationNode.BEZOUT,
//                MaOperationNode.PARMI,MaOperationNode.REP,
//                MaOperationNode.SGN,MaOperationNode.ABS,
//                MaOperationNode.ENT,MaOperationNode.TRONC,
//                MaOperationNode.NUM,MaOperationNode.DEN,
//                MaOperationNode.PREM,MaOperationNode.DIVS,MaOperationNode.DECOMP,
//                MaOperationNode.CARAC_FERME,MaOperationNode.CARAC_OUVERT,
//                MaOperationNode.CARAC_SEMI_OUVERT_G,MaOperationNode.CARAC_SEMI_OUVERT_D,
//                MaOperationNode.CARAC_DROITE_OUVERT,MaOperationNode.CARAC_DROITE_FERME,
//                MaOperationNode.CARAC_GAUCHE_OUVERT,MaOperationNode.CARAC_GAUCHE_FERME,
//                MaOperationNode.ALEA,MaOperationNode.STAT);
//    }
    private static boolean koCoreRepl(Replacement _r) {
        return StringUtil.nullToEmpty(_r.getOldString()).isEmpty() || StringUtil.nullToEmpty(_r.getNewString()).isEmpty();
    }

//    private static boolean okKey(String _key) {
//        return StringUtil.contains(fcts(),_key);
//    }

    private static MaStruct analyzeCalculate(String _el, StringMap<MaStruct> _conf, MaParameters _mapping, MaError _err, boolean _procVar, StringList _varNames, CustList<String> _rands) {
        MaDelimiters d_ = checkSyntax(_el, _err,_varNames);
//        if (_err.getOffset() > -1) {
//            return MaNullStruct.NULL_VALUE;
//        }
//        MaOperationsSequence opTwo_ = getOperationsSequence(IndexConstants.FIRST_INDEX, _el, d_);
//        MaOperationNode op_ = MaOperationNode.createOperationNodeAndChild(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, null, opTwo_, _mapping);
//        if (op_ == null) {
//            _err.setOffset(0);
//            return MaNullStruct.NULL_VALUE;
//        }
        CustList<MaOperationNode> all_ = getSortedDescNodes(_el, _err, d_, _mapping);
        if (all_.isEmpty()) {
            return MaNullStruct.NULL_VALUE;
        }
        calculate(all_, _conf, _err,d_, _procVar,_rands);
        if (_err.getOffset() > -1) {
            return MaNullStruct.NULL_VALUE;
        }
        return MaNullStruct.def(all_.last().getStruct());
    }

    public static CustList<MaOperationNode> getSortedDescNodes(String _el, MaError _error, MaDelimiters _del, MaParameters _mapping) {
        if (_error.getOffset() > -1) {
            return new CustList<MaOperationNode>();
        }
        MaOperationsSequence opTwo_ = getOperationsSequence(IndexConstants.FIRST_INDEX, _el, _del);
        MaOperationNode op_ = MaOperationNode.createOperationNodeAndChild(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX, null, opTwo_, _mapping);
        if (op_ == null) {
            _error.setOffset(0);
            return new CustList<MaOperationNode>();
        }
        CustList<MaOperationNode> list_ = new CustList<MaOperationNode>();
        MaOperationNode c_ = op_;
        while (c_ != null) {
            c_ = getAnalyzedNext(c_, op_, list_, _error, _del, _mapping);
        }
        return list_;
    }

    public static MaOperationNode getAnalyzedNext(MaOperationNode _current, MaOperationNode _root, CustList<MaOperationNode> _sortedNodes, MaError _error, MaDelimiters _del, MaParameters _mapping) {
        MaOperationNode next_ = create(_error, _del, _mapping, _current, 0);
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
            next_ = create(_error, _del, _mapping, current_.getPar(), current_.getChildIndex() + 1);
            if (_error.getOffset() > -1) {
                return null;
            }
            if (next_ != null) {
                next_.getPar().append(next_);
                return next_;
            }
            MaOperationNode par_ = current_.getPar();
            if (par_ == _root) {
                par_.setOrder(_sortedNodes.size());
                _sortedNodes.add(par_);
                return null;
            }
            if (par_ == null) {
                return null;
            }
            current_ = par_;
        }
    }

    private static MaOperationNode create(MaError _error, MaDelimiters _delimiter, MaParameters _mapping, MaOperationNode _p, int _nextIndex) {
        if (!(_p instanceof MethodMaOperation)) {
            return null;
        }
        MethodMaOperation p_ = (MethodMaOperation) _p;
        StrTypes children_ = p_.getChs();
        if (_nextIndex >= children_.size()) {
            return null;
        }
        String value_ = children_.getValue(_nextIndex);
        int curKey_ = children_.getKey(_nextIndex);
        int offset_ = p_.getIndexExp()+curKey_;
        MaOperationsSequence r_ = getOperationsSequence(offset_, value_, _delimiter);
        MaOperationNode op_ = MaOperationNode.createOperationNodeAndChild(offset_, _nextIndex, p_, r_, _mapping);
        if (op_ == null) {
            _error.setOffset(offset_);
            return null;
        }
        return op_;
    }

    static void calculate(CustList<MaOperationNode> _nodes, StringMap<MaStruct> _context, MaError _error, MaDelimiters _del, boolean _procVar, CustList<String> _rands) {
        int fr_ = 0;
        int len_ = _nodes.size();
        while (fr_ < len_) {
            MaOperationNode o = _nodes.get(fr_);
            if (_procVar && o instanceof VariableMaOperation) {
                String varName_ = ((VariableMaOperation) o).getVarName();
                if (MaNullStruct.def(_context.getVal(varName_)) == MaNullStruct.NULL_VALUE) {
                    return;
                }
            }
            o.calculate(_context, _error,_del, _rands);
            if (_error.getOffset() > -1) {
                return;
            }
            MaStruct res_ = o.getStruct();
            fr_ = MaOperationNode.getNextIndex(o, res_,fr_+1);
        }
    }
    private static MaDelimiters checkSyntax(String _string, MaError _error, StringList _varNames) {
        MaDelimiters d_ = new MaDelimiters();
        int len_ = _string.length();
        int i_ = skipWhite(_string,len_,0);
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
        processLeftDel(_m, curChar_, s_, MatCommonCst.BRA_LEFT);
        processRightDel(_m, curChar_,_len,_error,MatCommonCst.PAR_LEFT,MatCommonCst.PAR_RIGHT);
        processRightDel(_m, curChar_,_len,_error,MatCommonCst.ARR_LEFT,MatCommonCst.ARR_RIGHT);
        processRightDel(_m, curChar_,_len,_error,MatCommonCst.BRA_LEFT,MatCommonCst.BRA_RIGHT);
        if (curChar_ == MatCommonCst.SEP_ARG && s_.empty()) {
            _error.setOffset(_m.getIndex());
            _m.setIndex(_len);
        }
        if (_error.getOffset() > -1) {
            return;
        }
        char def_;
        if (!s_.empty()) {
            def_ = s_.oper();
        } else {
            def_ = ' ';
        }
        _m.setIndex(procOper(_d, _m.getIndex(), curChar_, def_));
    }

    private static void processLeftDel(MathState _m, char _curChar, MaStackOperators _s, char _left) {
        if (_curChar == _left) {
            _s.add(_m.getIndex(), _left);
        }
    }

    private static void processRightDel(MathState _m, char _curChar, int _len, MaError _error, char _leftChar, char _rightChar) {
        MaStackOperators s_ = _m.getStack();
        if (_curChar == _rightChar) {
            if (misMatchDel(s_, _leftChar)) {
                _error.setOffset(_m.getIndex());
                _m.setIndex(_len);
                return;
            }
            s_.remove();
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
        MatVariableResult res_ = MatVariableResult.build(_string, _len, _from, new DefaultWordSplit());
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
            if (!MathExpUtil.isWordChar(ch_)) {
                break;
            } else {
                i_++;
            }
        }
        int endWord_ = i_;
        i_ = skipWhite(_string, _len, i_);
        if (MathExpUtil.charIs(_string, _len, i_, '/') || MathExpUtil.charIs(_string, _len, i_, '.')) {
            return true;
        }
        if (MathExpUtil.charIs(_string,_len,i_,'(')) {
            return false;
        }
        return !StringUtil.contains(_varNames,_string.substring(_from,endWord_));
    }
    public static int skipWhite(String _string, int _len,int _from) {
        int to_ = _from;
        while (to_ < _len) {
            char ch_ = _string.charAt(to_);
            if (!StringUtil.isWhitespace(ch_)) {
                break;
            } else {
                to_++;
            }
        }
        return to_;
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
            } else if (isWhitePart(_string, len_, i_, cur_) || followFrac(frac_, cur_)) {
                nbInfo_.append(cur_);
                i_++;
            } else if (digOrDot(cur_)) {
                nbInfo_.append(cur_);
                i_++;
                frac_ = false;
            } else {
                break;
            }
        }
        return new MatNumberResult(nbInfo_,i_);
    }

    private static boolean isWhitePart(String _string, int _len, int _i, char _cur) {
        return StringUtil.isWhitespace(_cur) && nbPart(_string, _len, _i);
    }

    private static boolean nbPart(String _string, int _len, int _current) {
        int next_ = skipWhite(_string, _len, _current);
        if (next_ >= _len) {
            return false;
        }
        char ch_ = _string.charAt(next_);
        return nbPart(ch_);
    }

    private static boolean nbPart(char _cur) {
        return _cur == MatNumberResult.SEP_RATE||digOrDot(_cur);
    }

    private static boolean digOrDot(char _cur) {
        return MathExpUtil.isDigit(_cur) || _cur == MatNumberResult.DOT;
    }

    private static boolean followFrac(boolean _frac, char _cur) {
        return _cur == '-' && _frac;
    }

    private static int procOper(MaDelimiters _d, int _i, char _curChar, char _ass) {
        if (MathExpUtil.allOp(_curChar)) {
            _d.getOperatorsIndexes().add(new MaIndexStackOperator(_i,_ass));
        }
        return _i+1;
    }
    static MaOperationsSequence getOperationsSequence(int _offset, String _string,
                                                      MaDelimiters _d) {
        int i_ = StringUtil.getFirstPrintableCharIndex(_string);
        if (i_ < 0) {
            MaOperationsSequence op_ = new MaOperationsSequence();
            op_.setOpers(new StrTypes());
            op_.setupValue(_string,0);
            return op_;
        }
        int lastPrintChar_ = StringUtil.getLastPrintableCharIndex(_string);
        int len_ = lastPrintChar_ + 1;
        int sumOff_ = _offset + i_;
        for (MatVariableInfo v: _d.getVarParts()) {
            if (v.getFirstChar() == sumOff_) {
                int iVar_ = v.getLastChar();
                if (iVar_ != _offset + lastPrintChar_ + 1) {
                    break;
                }
                MaOperationsSequence op_ = new MaOperationsSequence();
                op_.setType(MatConstType.LOC_VAR);
                op_.setOpers(new StrTypes());
                op_.setupValue(v.getName(),i_);
                return op_;
            }
        }
        int begin_ = _d.getNumbers().indexOfNb(sumOff_);
        int end_ = _d.getNumbers().indexOfNb(1L + _offset + lastPrintChar_);
        if (delimits(begin_, end_)) {
            MaOperationsSequence op_ = new MaOperationsSequence();
            op_.setCst(begin_/2);
            op_.setType(MatConstType.NUMBER);
            op_.setOpers(new StrTypes());
            op_.setupValue(_string,i_);
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
        op_.adj();
        return op_;
    }

    private static boolean delimits(int _begin, int _end) {
        return _begin > IndexConstants.INDEX_NOT_FOUND_ELT && _begin + 1 == _end;
    }

}
