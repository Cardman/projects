package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneConstructor;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.*;
import code.util.*;

public final class ConstructorBlock extends NamedFunctionBlock implements AccessibleBlock,GeneConstructor,ReturnableWithSignature {

    private ConstructorId constIdSameClass;

    private boolean implicitCallSuper;

    private int leftPar;

    public ConstructorBlock(OffsetAccessInfo _access,
                            OffsetStringInfo _retType, OffsetStringInfo _fctName,
                            StringList _paramTypes, Ints _paramTypesOffset,
                            StringList _paramNames, Ints _paramNamesOffset, int _leftPar,OffsetsBlock _offset) {
        super(_access, _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _offset);
        leftPar = _leftPar;
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    @Override
    public ConstructorId getId() {
        RootBlock clBlock_ = (RootBlock) getParent();
        String name_ = clBlock_.getFullName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(name_, pTypes_, isVarargs());
    }

    public ConstructorId getGenericId() {
        RootBlock clBlock_ = (RootBlock) getParent();
        String name_ = clBlock_.getGenericString();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(name_, pTypes_, isVarargs());
    }
    public void setupInstancingStep(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        Block first_ = getFirstChild();
        if (!(first_ instanceof Line)) {
            implicitCallSuper = true;
            return;
        }
        Line l_ = (Line) first_;
        if (l_.isCallInts()) {
            implicitCallSuper = true;
        }
        if (l_.isCallSuper() || l_.isCallInts()) {
            return;
        }
        if (l_.isCallThis()) {
            constIdSameClass = l_.getConstId();
            return;
        }
        implicitCallSuper = true;
    }

    public boolean implicitConstr() {
        return implicitCallSuper;
    }

    public ConstructorId getConstIdSameClass() {
        return constIdSameClass;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.INSTANCE;
    }
    @Override
    public void buildImportedReturnTypes(ContextEl _stds) {
        String void_ = _stds.getStandards().getAliasVoid();
        setImportedReturnType(void_);
    }

    @Override
    public String getName() {
        return EMPTY_STRING;
    }

    @Override
    public RootBlock belong() {
        return (RootBlock) getParent();
    }

    public String getDeclaringType() {
        RootBlock clBlock_ = (RootBlock) getParent();
        return clBlock_.getFullName();
    }

    @Override
    public void setAssignmentAfterCallReadOnly(ContextEl _an, AnalyzingEl _anEl) {
        checkInterfaces(_an);
    }

    private void checkInterfaces(ContextEl _an) {
        Block firstChild_ = getFirstChild();
        StringList ints_ = new StringList();
        StringList filteredCtor_ = _an.getNeedInterfaces();
        boolean checkThis_ = false;
        while (firstChild_ != null) {
            if (!(firstChild_ instanceof Line)) {
                break;
            }
            Line l_ = (Line) firstChild_;
            if (l_.isCallThis()) {
                checkThis_ = true;
                break;
            }
            if (l_.isCallInts()) {
                ints_.add(l_.getCalledInterface());
            }
            firstChild_ = firstChild_.getNextSibling();
        }
        if (!checkThis_) {
            if (!StringList.equalsSet(filteredCtor_, ints_)) {
                for (String n:filteredCtor_) {
                    if (!StringList.contains(ints_,n)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(getFile().getFileName());
                        undef_.setIndexFile(0);
                        //left par of ctor
                        undef_.buildError(_an.getAnalysisMessages().getMustCallIntCtorNeed(),
                                n);
                        _an.addError(undef_);
                    }
                }
                for (String n:ints_) {
                    if (!StringList.contains(filteredCtor_,n)) {
                        FoundErrorInterpret undef_;
                        undef_ = new FoundErrorInterpret();
                        undef_.setFileName(getFile().getFileName());
                        undef_.setIndexFile(0);
                        //constructor ref header len
                        undef_.buildError(_an.getAnalysisMessages().getMustCallIntCtorNotNeed(),
                                n);
                        _an.addError(undef_);
                    }
                }
            }
        } else {
            if (!ints_.isEmpty()) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(getFile().getFileName());
                undef_.setIndexFile(0);
                //first constructor ref header len
                undef_.buildError(_an.getAnalysisMessages().getMustNotCallIntCtorAfterThis());
                _an.addError(undef_);
            }
        }
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_cont,_parts);
        int begName_ = getNameOffset();
        _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
        _parts.add(new PartOffset("</a>",leftPar));
        int len_ = getParametersNamesOffset().size();
        for (int i = 0; i < len_; i++) {
            buildAnnotationsReport(i,_cont,_parts);
            _parts.addAllElts(getPartOffsetsParams().get(i));
            Integer off_ = getParametersNamesOffset().get(i);
            String param_ = getParametersNames().get(i);
            _parts.add(new PartOffset("<a name=\"m"+off_+"\">",off_));
            _parts.add(new PartOffset("</a>",off_+param_.length()));
            _cont.getCoverage().getParamVars().put(param_,off_);
        }
    }
}
