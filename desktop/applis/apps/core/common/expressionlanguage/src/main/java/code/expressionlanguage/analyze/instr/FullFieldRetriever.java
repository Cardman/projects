package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.FoundVariable;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.SearchingMemberStatus;
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
        if (StringExpUtil.nextPrintCharIs(_to, string.length(), string, DOT_VAR) > -1) {
            return processFieldsStaticAccess(_begin, _word, _to, context);
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

    @Override
    public int tryGetVarField(int _begin, String _word, int _to) {
        add = true;
        FoundVariable foundVar_ = new FoundVariable(_word, context);
        AnaLocalVariable val_ = foundVar_.getVal();
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
            ConstType type_ = ConstType.WORD;
            VariableInfo infoLoc_ = new VariableInfo();
            infoLoc_.setKind(type_);
            infoLoc_.setFirstChar(_begin);
            infoLoc_.setLastChar(_to);
            infoLoc_.setName(_word);
            delimiters.getVariables().add(infoLoc_);
            return _to;
        }
        return _begin;
    }

    private int processFieldsStaticAccess(int _from, String _word, int _to, AnalyzedPageEl _page) {
        int len_ = string.length();
        String glClass_ = _page.getGlobalClass();
        boolean field_ = isField(glClass_, _word, _page);
        if (field_) {
            ConstType type_ = ConstType.WORD;
            VariableInfo infoLoc_ = new VariableInfo();
            infoLoc_.setKind(type_);
            infoLoc_.setFirstChar(_from);
            infoLoc_.setLastChar(_to);
            infoLoc_.setName(_word);
            delimiters.getVariables().add(infoLoc_);
            return _to;
        }
        Ints indexes_ = new Ints();
        StringList partsFields_ = new StringList();
        StringList partsSeps_ = new StringList();
        StringBuilder part_ = new StringBuilder();
        int j_ = _from;
        int k_ = _from;
        while (j_ < len_) {
            char locChar_ = string.charAt(j_);
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
                if (StringExpUtil.nextCharIs(string,j_,len_,DOT_VAR)) {
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
        CustList<AnaResultPartType> partOffsets_ = new CustList<AnaResultPartType>();
        String join_ = StringUtil.join(partsFieldsBisFields_, "");
        StringList inns_ = AnaInherits.getAllInnerTypes(join_, _page);
        String trim_ = inns_.first().trim();
        int nb_ = 0;
        String start_;
        AccessedBlock r_ = _page.getImporting();
        AnaGeneType startType_ = _page.getAnaGeneType(trim_);
        int loc_ = _from + _page.getIndex();
        if (startType_ != null) {
            for (char c: trim_.toCharArray()) {
                if (c == '.') {
                    nb_++;
                }
            }
            start_ = trim_;
            partOffsets_.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(inns_.first(),startType_,start_,r_, loc_,0,_page));
        } else {
            AnaResultPartType resType_ = ResolvingTypes.resolveCorrectTypeWithoutErrors(_from + StringExpUtil.getOffset(inns_.first()), trim_, _page);
            start_ = resType_.getResult();
            if (!resType_.isOk()) {
                start_ = "";
            } else {
                partOffsets_.add(resType_);
            }
            startType_ = _page.getAnaGeneType(start_);
            nb_ = 0;
        }
        int rootOff_ = inns_.first().length() + 1;
        StrTypes operators_ = new StrTypes();
        int iPart_ = 2;
        int lenPart_ = inns_.size();
        while (iPart_ < lenPart_) {
            String fullPart_ = inns_.get(iPart_);
            int locOff_ = StringUtil.getFirstPrintableCharIndex(fullPart_);
            String p_ = fullPart_.trim();
            operators_.addEntry(rootOff_-1,inns_.get(iPart_-1));
            if (StringUtil.quickEq("..",inns_.get(iPart_-1))) {
                OwnerListResultInfo res_;
                res_ = AnaTypeUtil.getEnumOwners(start_, p_, _page);
                if (!res_.onlyOneElt()) {
                    break;
                }
                start_ = res_.firstOwnedName();
                startType_ = res_.firstOwned();
                partOffsets_.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(p_,startType_,start_,r_, loc_,rootOff_+1+locOff_,_page));
                rootOff_ += fullPart_.length() + 1;
                nb_++;
                iPart_+=2;
                continue;
            }
            boolean fieldLoc_ = isField(start_, p_, _page);
            if (fieldLoc_) {
                break;
            }
            OwnerListResultInfo res_ = AnaTypeUtil.getInners(start_, p_, _page);
            if (!res_.onlyOneElt()) {
                break;
            }
            start_ = res_.firstOwnedName();
            startType_ = res_.firstOwned();
            partOffsets_.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(p_,startType_,start_,r_, loc_,rootOff_+locOff_,_page));
            rootOff_ += fullPart_.length() + 1;
            nb_++;
            iPart_+=2;
        }
        int n_;
        if (indexes_.isValidIndex(nb_)) {
            n_ = indexes_.get(nb_);
        } else {
            n_ = k_;
        }
        if (startType_ == null) {
            ConstType type_ = ConstType.WORD;
            VariableInfo infoLoc_ = new VariableInfo();
            infoLoc_.setKind(type_);
            infoLoc_.setFirstChar(_from);
            infoLoc_.setLastChar(_to);
            infoLoc_.setName(_word);
            delimiters.getVariables().add(infoLoc_);
            return _to;
        }
        if (string.substring(n_).trim().indexOf('.') != 0) {
            add = false;
            return n_;
        }
        delimiters.getDelKeyWordStatic().add(_from);
        delimiters.getDelKeyWordStatic().add(n_);
        delimiters.getDelKeyWordStaticExtract().add(start_);
        delimiters.getStaticParts().add(PreLinkagePartTypeUtil.processAccessInnerRootAnalyze(join_,partOffsets_,operators_,r_, loc_,_page));
        return n_;
    }

    private boolean isField(String _fromClass, String _word, AnalyzedPageEl _page) {
        boolean stCtx_ = _page.isStaticContext() || ctorCallFirst;
        AnaClassArgumentMatching clArg_ = new AnaClassArgumentMatching(_fromClass);
        ScopeFilter scope_ = new ScopeFilter(null, true, true, false, _page.getGlobalClass());
        FieldResult fr_ = OperationNode.resolveDeclaredCustField(stCtx_, clArg_, _word, true, false, _page, scope_);
        return fr_.getStatus() == SearchingMemberStatus.UNIQ;
    }
}
