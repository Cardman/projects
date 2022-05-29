package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.MethodHeaderInfo;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaExplicitContent;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class CastFctOperation extends AbstractUnaryOperation{
    private final AnaExplicitContent explicitContent;
    private final CustList<AnaResultPartType> partOffsets = new CustList<AnaResultPartType>();

    private AnaTypeFct function;
    protected CastFctOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        explicitContent = new AnaExplicitContent();
        explicitContent.setOffset(getOperators().firstKey());
        explicitContent.setClassName(getOperators().firstValue());
    }

    protected void analyzeFct(AnalyzedPageEl _page, boolean _implicit) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ explicitContent.getOffset(), _page);
        String extract_ = explicitContent.getClassName().substring(explicitContent.getClassName().indexOf(PAR_LEFT)+1, explicitContent.getClassName().lastIndexOf(PAR_RIGHT));
        StringList types_ = StringExpUtil.getAllSepCommaTypes(extract_);
        if (types_.size() > 3) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFile(_page.getCurrentFile());
            badCall_.setIndexFile(_page);
            //key word len
            badCall_.buildError(_page.getAnalysisMessages().getSplitComaLow(),
                    Long.toString(3),
                    Long.toString(types_.size())
            );
            _page.getLocalizer().addError(badCall_);
            addErr(badCall_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        int leftPar_ = explicitContent.getClassName().indexOf(PAR_LEFT);
        AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(leftPar_ + 1 + StringExpUtil.getOffset(types_.first()), types_.first(), _page);
        String res_ = result_.getResult(_page);
        partOffsets.add(result_);
        explicitContent.setClassName(res_);
        setResultClass(new AnaClassArgumentMatching(explicitContent.getClassName(), _page.getPrimitiveTypes()));
        if (!StringExpUtil.customCast(res_)) {
            return;
        }
        if (AnaTypeUtil.isPrimitive(explicitContent.getClassName(), _page)) {
            getFirstChild().getResultClass().setUnwrapObject(explicitContent.getClassName(), _page.getPrimitiveTypes());
        }
        if (types_.size() == 2 && StringUtil.quickEq(types_.last(), _page.getKeyWords().getKeyWordId())) {
            return;
        }
        ClassMethodIdAncestor uniq_;
        String exp_;
        StringMap<CustList<MethodHeaderInfo>> castMethods_;
        StringMap<CustList<MethodHeaderInfo>> idCastMethods_;
        StringMap<CustList<MethodHeaderInfo>> fromCastMethods_;
        if (_implicit) {
            exp_ = _page.getKeyWords().getKeyWordCast();
            castMethods_ = _page.getImplicitCastMethods();
            idCastMethods_ = _page.getImplicitIdCastMethods();
            fromCastMethods_ = _page.getImplicitFromCastMethods();
        } else {
            exp_ = _page.getKeyWords().getKeyWordExplicit();
            castMethods_ = _page.getExplicitCastMethods();
            idCastMethods_ = _page.getExplicitIdCastMethods();
            fromCastMethods_ = _page.getExplicitFromCastMethods();
        }
        if (types_.size() == 2){
            twoParts(_page, types_, leftPar_, exp_, castMethods_, idCastMethods_, fromCastMethods_);
            return;
        }
        if (_implicit||types_.size() == 3){
            AnaGeneType geneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(explicitContent.getClassName()));
            if (geneType_ == null) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page, leftPar_ +1);
                //_in len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        explicitContent.getClassName());
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
                return;
            }
            String arg_ = types_.get(1);
            int lc_ = leftPar_ + types_.first().length() + 2;
            AnaResultPartType resolvedMid_ = ResolvingTypes.resolveCorrectAccessibleType(lc_ + StringExpUtil.getOffset(arg_),arg_.trim(), explicitContent.getClassName(), _page);
            partOffsets.add(resolvedMid_);
            String midType_ = resolvedMid_.getResult(_page);
            arg_ = types_.last();
            AnaResultPartType resolvedLast_ = ResolvingTypes.resolveCorrectAccessibleType(lc_ +types_.get(1).length()+1 + StringExpUtil.getOffset(arg_),arg_.trim(), explicitContent.getClassName(), _page);
            partOffsets.add(resolvedLast_);
            String lastType_ = resolvedLast_.getResult(_page);
            uniq_ = new ClassMethodIdAncestor(geneType_,new ClassMethodId(explicitContent.getClassName(),new MethodId(MethodAccessKind.STATIC,exp_,new StringList(midType_,lastType_))),0);
            AnaClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
            AnaClassArgumentMatching virtual_ = new AnaClassArgumentMatching(AnaInherits.quickFormat(geneType_, explicitContent.getClassName(), midType_), _page.getPrimitiveTypes());
            CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>(virtual_);
            args_.add(resultClass_);
            AnaClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
            ClassMethodIdReturn resMethod_ = tryGetCast(explicitContent.getClassName(), uniq_, argsClass_, _page, castMethods_, idCastMethods_, fromCastMethods_);
            if (resMethod_ != null) {
                explicitContent.setFormattedTypeOwner(resMethod_.getFormattedType());
                explicitContent.setMemberId(resMethod_.getParametrableContent().getMemberId());
                function = resMethod_.getParametrableContent().getPair();
                setResultClass(virtual_);
                return;
            }
            buildError(argsClass_, _page, exp_);
            return;
        }
        AnaClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>(new AnaClassArgumentMatching(explicitContent.getClassName(), _page.getPrimitiveTypes()));
        args_.add(resultClass_);
        AnaClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
        ClassMethodIdReturn resMethod_ = tryGetCast(explicitContent.getClassName(), null, argsClass_, _page, castMethods_, idCastMethods_, fromCastMethods_);
        if (resMethod_ == null) {
            return;
        }
        explicitContent.setMemberId(resMethod_.getParametrableContent().getMemberId());
        explicitContent.setFormattedTypeOwner(resMethod_.getFormattedType());
        function = resMethod_.getParametrableContent().getPair();
    }

    private void twoParts(AnalyzedPageEl _page, StringList _types, int _leftPar, String _exp, StringMap<CustList<MethodHeaderInfo>> _castMethods, StringMap<CustList<MethodHeaderInfo>> _idCastMethods, StringMap<CustList<MethodHeaderInfo>> _fromCastMethods) {
        ClassMethodIdAncestor uniq_;
        //add a type for full id
        String arg_ = _types.last();
        AnaResultPartType resolved_ = ResolvingTypes.resolveCorrectAccessibleType(_leftPar + _types.first().length() + 2 + StringExpUtil.getOffset(arg_), arg_.trim(), explicitContent.getClassName(), _page);
        partOffsets.add(resolved_);
        String lastType_ = resolved_.getResult(_page);
        AnaGeneType geneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(explicitContent.getClassName()));
        if (geneType_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page, _leftPar +1);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    explicitContent.getClassName());
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            return;
        }
        String gene_ = geneType_.getGenericString();
        uniq_ = new ClassMethodIdAncestor(geneType_,new ClassMethodId(explicitContent.getClassName(),new MethodId(MethodAccessKind.STATIC, _exp,new StringList(gene_,lastType_))),0);
        AnaClassArgumentMatching resultClass_ = getFirstChild().getResultClass();
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>(new AnaClassArgumentMatching(explicitContent.getClassName(), _page.getPrimitiveTypes()));
        args_.add(resultClass_);
        AnaClassArgumentMatching[] argsClass_ = OperationNode.toArgArray(args_);
        ClassMethodIdReturn resMethod_ = tryGetCast(explicitContent.getClassName(), uniq_, argsClass_, _page, _castMethods, _idCastMethods, _fromCastMethods);
        if (resMethod_ != null) {
            explicitContent.setFormattedTypeOwner(resMethod_.getFormattedType());
            explicitContent.setMemberId(resMethod_.getParametrableContent().getMemberId());
            function = resMethod_.getParametrableContent().getPair();
        }
    }

    private void buildError(AnaClassArgumentMatching[] _argsClass, AnalyzedPageEl _page, String _id) {
        StringList classesNames_ = new StringList();
        for (AnaClassArgumentMatching c: _argsClass) {
            classesNames_.add(StringUtil.join(c.getNames(), ExportCst.JOIN_TYPES));
        }
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFile(_page.getCurrentFile());
        undefined_.setIndexFile(_page);
        //_name len
        undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                new MethodId(MethodAccessKind.STATIC, _id, classesNames_).getSignature(_page.getDisplayedStrings()));
        _page.getLocalizer().addError(undefined_);
        addErr(undefined_.getBuiltError());

    }
    public AnaTypeFct getFunction() {
        return function;
    }

    public String getClassName() {
        return explicitContent.getClassName();
    }

    public AnaExplicitContent getExplicitContent() {
        return explicitContent;
    }

    public CustList<AnaResultPartType> getPartOffsets() {
        return partOffsets;
    }

}
