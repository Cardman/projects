package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.opers.util.SearchingMemberStatus;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.FoundVariable;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class FullFieldRetriever implements FieldRetriever {

    private static final char PAR_LEFT = '(';
    private static final char DOT_VAR = '.';

    private final Delimiters delimiters;
    private final String string;
    private final AnalyzedPageEl context;
    private boolean add;
    private boolean ctorCallFirst;

    public FullFieldRetriever(Delimiters _delimiters, String _string, AnalyzedPageEl _context, int _minIndex) {
        this.delimiters = _delimiters;
        this.string = _string;
        this.context = _context;
        int pr_ = StringExpUtil.nextPrintChar(_minIndex, string.length(), string);
        for (String k:StringUtil.wrapStringArray(_context.getKeyWords().getKeyWordSuper(),
                _context.getKeyWords().getKeyWordThis(),
                _context.getKeyWords().getKeyWordInterfaces())) {
            if (StringExpUtil.startsWithKeyWord(string,pr_,k)) {
                int next_ = StringExpUtil.nextPrintChar(pr_ + k.length(), string.length(), string);
                if (StringExpUtil.nextCharIs(string,next_,string.length(),'(')){
                    ctorCallFirst = true;
                    break;
                }
            }
        }
    }

    @Override
    public int processFieldsStaticAccess(int _begin, String _word, int _to) {
        FoundVariable foundVar_ = new FoundVariable(_word, context);
        AnaLocalVariable val_ = foundVar_.getVal();
        if (val_ != null) {
            return variableOrWord(val_.getConstType(), _begin, _to, _word);
        }
        if (StringExpUtil.nextPrintCharIs(_to, string.length(), string, DOT_VAR) > -1) {
            return processFieldsStaticAccess(_begin, _word, _to, context);
        }
        VariableInfo info_ = word(_begin, _word, _to);
        delimiters.getVariables().add(info_);
        return _to;
    }

    static VariableInfo word(int _begin, String _word, int _to) {
        return buildVariableOrWord(ConstType.WORD,_begin,_to,_word);
    }

    @Override
    public int tryGetVarField(int _begin, String _word, int _to) {
        add = true;
        FoundVariable foundVar_ = new FoundVariable(_word, context);
        AnaLocalVariable val_ = foundVar_.getVal();
        if (val_ != null) {
            return variableOrWord(val_.getConstType(), _begin, _to, _word);
        }
        if (StringExpUtil.nextPrintCharIs(_to, string.length(), string, DOT_VAR) > -1) {
            int n_ = processFieldsStaticAccess(_begin, _word, _to, context);
            if (add) {
                return n_;
            }
            return _begin;
        }
        String glClass_ = context.getGlobalClass();
        boolean field_ = isField(glClass_, _word, context);
        if (field_) {
            return variableOrWord(ConstType.WORD, _begin, _to, _word);
        }
        return _begin;
    }

    private int variableOrWord(ConstType _val, int _begin, int _to, String _word) {
        VariableInfo info_ = buildVariableOrWord(_val, _begin, _to, _word);
        this.delimiters.getVariables().add(info_);
        return _to;
    }

    private static VariableInfo buildVariableOrWord(ConstType _val, int _begin, int _to, String _word) {
        VariableInfo info_ = new VariableInfo();
        info_.setKind(_val);
        info_.setFirstChar(_begin);
        info_.setLastChar(_to);
        info_.setName(_word);
        return info_;
    }

    private int processFieldsStaticAccess(int _from, String _word, int _to, AnalyzedPageEl _page) {
        String glClass_ = _page.getGlobalClass();
        boolean field_ = isField(glClass_, _word, _page);
        if (field_) {
            return variableOrWord(ConstType.WORD, _from, _to, _word);
        }
        Ints indexes_ = new Ints();
        StringList partsFields_ = new StringList();
        StringList partsSeps_ = new StringList();
        int k_ = partsFields(_from, indexes_, partsFields_, partsSeps_);
        StringList partsFieldsBisFields_ = partsFieldsSec(partsFields_, partsSeps_);
        CustList<AnaResultPartType> partOffsets_ = new CustList<AnaResultPartType>();
        String join_ = StringUtil.join(partsFieldsBisFields_, "");
        StringList inns_ = AnaInherits.getAllInnerTypes(join_, _page);
        String trim_ = inns_.first().trim();
        int nb_;
        String start_;
        FileBlock r_ = _page.getCurrentFile();
        AnaGeneType startType_ = _page.getAnaGeneType(trim_);
        int loc_ = _from + _page.getIndex();
        if (startType_ != null) {
            nb_ = count(trim_);
            start_ = trim_;
            partOffsets_.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(inns_.first(),startType_,start_,r_, loc_,0,_page));
        } else {
            nb_ = 0;
            AnaResultPartType resType_ = ResolvingTypes.resolveCorrectTypeWithoutErrors(_from + StringExpUtil.getOffset(inns_.first()), trim_, _page);
            start_ = resType_.getResult();
            if (!resType_.isOk()) {
                start_ = "";
            } else {
                partOffsets_.add(resType_);
            }
            startType_ = _page.getAnaGeneType(start_);
        }
        int rootOff_ = inns_.first().length() + 1;
        StrTypes operators_ = new StrTypes();
        int iPart_ = 2;
        int lenPart_ = inns_.size();
        while (iPart_ < lenPart_) {
            String fullPart_ = inns_.get(iPart_);
            int locOff_ = StringUtil.getFirstPrintableCharIndex(fullPart_);
            String p_ = fullPart_.trim();
            String curSep_ = inns_.get(iPart_ - 1);
            operators_.addEntry(rootOff_-1, curSep_);
            int indexInType_ = rootOff_ + curSep_.length() - 1 + locOff_;
            OwnerListResultInfo res_ = type(_page,curSep_,start_,p_);
            if (res_ == null) {
                break;
            }
            start_ = res_.firstOwnedName();
            startType_ = res_.firstOwned();
            partOffsets_.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(p_,startType_,start_,r_, loc_, indexInType_,_page));
            rootOff_ += fullPart_.length() + 1;
            nb_++;
            iPart_+=2;
        }
        int n_ = nextIndex(indexes_, k_, nb_);
        if (startType_ == null) {
            return variableOrWord(ConstType.WORD, _from, _to, _word);
        }
        if (string.substring(n_).trim().indexOf('.') != 0) {
            add = false;
            return n_;
        }
        delimiters.getDelKeyWordStatic().add(_from);
        delimiters.getDelKeyWordStatic().add(n_);
        delimiters.getDelKeyWordStaticExtract().add(start_);
        delimiters.getStaticAccessTypes().add(startType_);
        delimiters.getStaticParts().add(PreLinkagePartTypeUtil.processAccessInnerRootAnalyze(join_,partOffsets_,operators_,r_, loc_,_page));
        return n_;
    }
    private OwnerListResultInfo type(AnalyzedPageEl _page, String _curSep, String _start, String _p) {
        OwnerListResultInfo res_;
        if (StringUtil.quickEq("..", _curSep)) {
            res_ = AnaTypeUtil.getEnumOwners(_start, _p, _page);
        } else {
            boolean fieldLoc_ = isField(_start, _p, _page);
            if (fieldLoc_) {
                return null;
            }
            res_ = AnaTypeUtil.getInners(_start, _p, _page);
        }
        if (!res_.onlyOneElt()) {
            return null;
        }
        return res_;
    }

    private int nextIndex(Ints _indexes, int _k, int _nb) {
        int n_;
        if (_indexes.isValidIndex(_nb)) {
            n_ = _indexes.get(_nb);
        } else {
            n_ = _k;
        }
        return n_;
    }

    private static int count(String _trim) {
        int nb_ = 0;
        for (char c: _trim.toCharArray()) {
            if (c == '.') {
                nb_++;
            }
        }
        return nb_;
    }
    private static StringList partsFieldsSec(StringList _partsFields, StringList _partsSeps) {
        StringList partsFieldsBisFields_ = new StringList();
        int partFieldIndex_ = 0;
        for (String p: _partsFields) {
            int f_ = StringUtil.getFirstPrintableCharIndex(p);
            int l_= StringUtil.getLastPrintableCharIndex(p);
            int index_ = -1;
            while (f_ < l_) {
                char loc_ = p.charAt(f_);
                if (StringUtil.isWhitespace(loc_)) {
                    index_ = f_;
                    break;
                }
                f_++;
            }
            if (index_ > -1) {
                partsFieldsBisFields_.add(p.substring(0,index_));
                break;
            }
            partsFieldsBisFields_.add(p);
            if (_partsFields.isValidIndex(partFieldIndex_+1)) {
                partsFieldsBisFields_.add(_partsSeps.get(partFieldIndex_));
            }
            partFieldIndex_++;
        }
        return partsFieldsBisFields_;
    }

    private int partsFields(int _from, Ints _indexes, StringList _partsFields, StringList _partsSeps) {
        StringBuilder part_ = new StringBuilder();
        int len_ = string.length();
        int j_ = _from;
        int k_ = _from;
        while (j_ < len_) {
            char locChar_ = string.charAt(j_);
            if (StringExpUtil.isTypeLeafChar(locChar_) || StringUtil.isWhitespace(locChar_)) {
                part_.append(locChar_);
                j_++;
            } else {
                String partStr_ = part_.toString();
                if (locChar_ == DOT_VAR) {
                    _indexes.add(j_);
                    _partsFields.add(partStr_);
                    part_.delete(0, part_.length());
                    j_ = incrPartFieldSep(j_,_partsSeps);
                } else {
                    if (locChar_ != PAR_LEFT) {
                        k_ = j_;
                        _partsFields.add(partStr_);
                    }
                    break;
                }
            }
        }
        return k_;
    }
    private int incrPartFieldSep(int _j, StringList _partsSeps) {
        int len_ = string.length();
        int j_ = _j;
        j_++;
        if (StringExpUtil.nextCharIs(string, j_, len_, DOT_VAR)) {
            _partsSeps.add("..");
            j_++;
        } else {
            _partsSeps.add(".");
        }
        return j_;
    }

    private boolean isField(String _fromClass, String _word, AnalyzedPageEl _page) {
        boolean stCtx_ = _page.isStaticContext() || ctorCallFirst;
        AnaClassArgumentMatching clArg_ = new AnaClassArgumentMatching(_fromClass);
        ScopeFilter scope_ = new ScopeFilter(null, true, true, false, _page.getGlobalClass());
        FieldResult fr_ = OperationNode.resolveDeclaredCustField(stCtx_, clArg_, _word, true, false, _page, scope_);
        return fr_.getStatus() == SearchingMemberStatus.UNIQ;
    }
}
