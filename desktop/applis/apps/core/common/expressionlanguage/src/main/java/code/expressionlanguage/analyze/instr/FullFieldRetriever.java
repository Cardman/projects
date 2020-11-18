package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.VariableInfo;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.SearchingMemberStatus;
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

    public FullFieldRetriever(Delimiters _delimiters, String _string, AnalyzedPageEl _context) {
        this.delimiters = _delimiters;
        this.string = _string;
        this.context = _context;
    }

    @Override
    public int processFieldsStaticAccess(boolean _ctorCall, int _begin, String _word, int _to) {
        AnalyzedPageEl ana_ = context;
        AnaLocalVariable val_ = ana_.getInfosVars().getVal(_word);
        if (val_ == null) {
            String sh_ = StringExpUtil.skipPrefix(_word);
            AnaLocalVariable loc_ = ana_.getInfosVars().getVal(sh_);
            int deep_ = StringExpUtil.countPrefix(_word, '#');
            if (loc_ != null) {
                deep_--;
            }
            val_ = ana_.getCache().getLocalVar(sh_,deep_);
        }
        if (val_ != null) {
            ConstType type_;
            type_ = val_.getConstType();
            VariableInfo info_ = new VariableInfo();
            info_.setKind(type_);
            info_.setFirstChar(_begin);
            info_.setLastChar(_to);
            info_.setName(_word);
            delimiters.getVariables().add(info_);
            return _to;
        }
        String look_ = ana_.getLookLocalClass();
        if (!look_.isEmpty()) {
            VariableInfo info_ = new VariableInfo();
            ConstType type_;
            type_ = ConstType.WORD;
            info_.setKind(type_);
            info_.setFirstChar(_begin);
            info_.setLastChar(_to);
            info_.setName(_word);
            delimiters.getVariables().add(info_);
            return _to;
        }
        if (StringExpUtil.nextPrintCharIs(_to, string.length(), string, DOT_VAR) > -1) {
            return processFieldsStaticAccess(_ctorCall, string, _begin, _word, _to, delimiters, context);
        }
        VariableInfo info_ = new VariableInfo();
        ConstType type_;
        type_ = ConstType.WORD;
        info_.setKind(type_);
        info_.setFirstChar(_begin);
        info_.setLastChar(_to);
        info_.setName(_word);
        delimiters.getVariables().add(info_);
        return _to;
    }
    private static int processFieldsStaticAccess(boolean _ctor, String _string, int _from, String _word, int _to, Delimiters _d, AnalyzedPageEl _page) {
        int len_ = _string.length();
        String glClass_ = _page.getGlobalClass();
        boolean field_ = isField(glClass_, _ctor, _word, _page);
        if (field_) {
            ConstType type_ = ConstType.WORD;
            VariableInfo infoLoc_ = new VariableInfo();
            infoLoc_.setKind(type_);
            infoLoc_.setFirstChar(_from);
            infoLoc_.setLastChar(_to);
            infoLoc_.setName(_word);
            _d.getVariables().add(infoLoc_);
            return _to;
        }
        Ints indexes_ = new Ints();
        StringList partsFields_ = new StringList();
        StringList partsSeps_ = new StringList();
        StringBuilder part_ = new StringBuilder();
        int j_ = _from;
        int k_ = _from;
        while (j_ < len_) {
            char locChar_ = _string.charAt(j_);
            if (StringExpUtil.isTypeLeafChar(locChar_)) {
                part_.append(locChar_);
                j_++;
                continue;
            }
            if (StringUtil.isWhitespace(locChar_)) {
                part_.append(locChar_);
                j_++;
                continue;
            }
            String partStr_ = part_.toString();
            if (locChar_ == DOT_VAR) {
                indexes_.add(j_);
                partsFields_.add(partStr_);
                part_.delete(0,part_.length());
                j_++;
                if (StringExpUtil.nextCharIs(_string,j_,len_,DOT_VAR)) {
                    partsSeps_.add("..");
                    j_++;
                } else {
                    partsSeps_.add(".");
                }
                continue;
            }
            if (locChar_ != PAR_LEFT) {
                k_ = j_;
                partsFields_.add(partStr_);
            }
            break;
        }
        StringList partsFieldsBisFields_ = new StringList();
        int partFieldIndex_ = 0;
        for (String p: partsFields_) {
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
            if (partsFields_.isValidIndex(partFieldIndex_+1)) {
                partsFieldsBisFields_.add(partsSeps_.get(partFieldIndex_));
            }
            partFieldIndex_++;
        }
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        String join_ = StringUtil.join(partsFieldsBisFields_, "");
        StringList inns_ = AnaTemplates.getAllInnerTypes(join_, _page);
        String trim_ = inns_.first().trim();
        int nextOff_ = _from;
        int nb_ = 0;
        String start_;
        if (_page.getAnaGeneType(trim_) != null) {
            for (char c: trim_.toCharArray()) {
                if (c == '.') {
                    nb_++;
                }
            }
            start_ = trim_;
            ContextUtil.appendParts(_from, _from +inns_.first().length(),trim_,partOffsets_, _page);
            nextOff_ += inns_.first().length() + 1;
        } else {
            CustList<PartOffset> currentParts_ = _page.getCurrentParts();
            start_ = ResolvingTypes.resolveCorrectTypeWithoutErrors(_from,inns_.first(), false, currentParts_, _page);
            if (start_.isEmpty()) {
                currentParts_.clear();
            }
            partOffsets_.addAllElts(currentParts_);
            nb_ = 0;
            nextOff_ += inns_.first().length() + 1;
        }
        int iPart_ = 2;
        int lenPart_ = inns_.size();
        while (iPart_ < lenPart_) {
            String fullPart_ = inns_.get(iPart_);
            int locOff_ = StringUtil.getFirstPrintableCharIndex(fullPart_);
            String p_ = fullPart_.trim();
            if (StringUtil.quickEq("..",inns_.get(iPart_-1))) {
                StringList res_;
                res_ = AnaTypeUtil.getEnumOwners(start_, p_, _page);
                if (!res_.onlyOneElt()) {
                    break;
                }
                start_ = StringUtil.concat(res_.first(),"-",p_);
                ContextUtil.appendParts(nextOff_+1+locOff_,nextOff_+1+locOff_+p_.length(),start_,partOffsets_, _page);
                nextOff_ += fullPart_.length() + 1;
                nb_++;
                iPart_+=2;
                continue;
            }
            boolean fieldLoc_ = isField(start_,_ctor, p_, _page);
            if (fieldLoc_) {
                break;
            }
            StringList res_ = AnaTypeUtil.getInners(start_, p_, _page);
            if (!res_.onlyOneElt()) {
                break;
            }
            start_ = res_.first();
            ContextUtil.appendParts(nextOff_+locOff_,nextOff_+locOff_+p_.length(),start_,partOffsets_, _page);
            nextOff_ += fullPart_.length() + 1;
            nb_++;
            iPart_+=2;
        }
        int n_;
        if (indexes_.isValidIndex(nb_)) {
            n_ = indexes_.get(nb_);
        } else {
            n_ = k_;
        }
        if (_page.getAnaGeneType(start_) == null) {
            ConstType type_ = ConstType.WORD;
            VariableInfo infoLoc_ = new VariableInfo();
            infoLoc_.setKind(type_);
            infoLoc_.setFirstChar(_from);
            infoLoc_.setLastChar(_to);
            infoLoc_.setName(_word);
            _d.getVariables().add(infoLoc_);
            return _to;
        }
        if (_string.substring(n_).trim().indexOf('.') != 0) {
            return n_;
        }
        _d.getDelKeyWordStatic().add(_from);
        _d.getDelKeyWordStatic().add(n_);
        _d.getDelKeyWordStaticExtract().add(start_);
        _d.getStaticParts().add(partOffsets_);
        return n_;
    }

    private static boolean isField(String _fromClass, boolean _ctor, String _word, AnalyzedPageEl _page) {
        boolean field_;
        boolean stCtx_ = _page.isStaticContext() || _ctor;
        field_ = true;
        AnaClassArgumentMatching clArg_ = new AnaClassArgumentMatching(_fromClass);
        FieldResult fr_ = OperationNode.resolveDeclaredCustField(stCtx_, clArg_, true, true, _word, true, false, _page);
        if (fr_.getStatus() != SearchingMemberStatus.UNIQ) {
            field_ = false;
        }
        return field_;
    }
}
