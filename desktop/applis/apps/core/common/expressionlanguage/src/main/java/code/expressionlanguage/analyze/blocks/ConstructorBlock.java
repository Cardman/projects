package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.ParsedFctHeader;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.common.DisplayedStrings;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ConstructorBlock extends NamedFunctionBlock implements ReturnableWithSignature {

    private ConstructorId constIdSameClass;

    private boolean implicitCallSuper;

    private final int leftPar;

    private String ctorName = "";
    private int ctorNumber;

    public ConstructorBlock(ParsedFctHeader _header, OffsetAccessInfo _access,
                            OffsetStringInfo _retType, OffsetStringInfo _fctName,
                            int _leftPar, int _offset) {
        super(_header, _access, _retType, _fctName, _offset);
        leftPar = _leftPar;
    }

    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return getId().getSignature(_page.getDisplayedStrings());
    }

    @Override
    public String getSignature(DisplayedStrings _page) {
        return getId().getSignature(_page);
    }

    public ConstructorId getId() {
        RootBlock clBlock_ = (RootBlock) getParent();
        String name_ = clBlock_.getFullName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new ConstructorId(name_, pTypes_,getParametersRef(), isVarargs());
    }

    public void setupInstancingStep(AnalyzedPageEl _page) {
        _page.setSumOffset(getOffset());
        _page.zeroOffset();
        AbsBk first_ = getFirstChild();
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
    public void buildImportedReturnTypes(AnalyzedPageEl _page) {
        String void_ = _page.getAliasVoid();
        setImportedReturnType(void_);
    }


    public void checkInterfaces(AnalyzedPageEl _page) {
        AbsBk firstChild_ = getFirstChild();
        StringList ints_ = new StringList();
        StringList filteredCtor_ = _page.getNeedInterfaces();
        boolean checkThis_ = false;
        while (firstChild_ != null) {
            if (!(firstChild_ instanceof Line) || ((Line) firstChild_).isCallThis()) {
                if (firstChild_ instanceof Line) {
                    checkThis_ = true;
                }
                break;
            }
            Line l_ = (Line) firstChild_;
            retrieveCall(ints_, l_);
            firstChild_ = firstChild_.getNextSibling();
        }
        lookForErrors(_page, ints_, filteredCtor_, checkThis_);
    }

    private void lookForErrors(AnalyzedPageEl _page, StringList _ints, StringList _filteredCtor, boolean _checkThis) {
        if (_checkThis) {
            if (!_ints.isEmpty()) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFile(getFile());
                undef_.setIndexFile(getNameOffset());
                //first constructor ref header len
                undef_.buildError(_page.getAnalysisMessages().getMustNotCallIntCtorAfterThis());
                _page.addLocError(undef_);
                addNameErrors(undef_);
            }
            return;
        }
        if (!StringUtil.equalsSet(_filteredCtor, _ints)) {
            for (String n: _filteredCtor) {
                if (!StringUtil.contains(_ints,n)) {
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFile(getFile());
                    undef_.setIndexFile(getNameOffset());
                    //left par of ctor
                    undef_.buildError(_page.getAnalysisMessages().getMustCallIntCtorNeed(),
                            n);
                    _page.addLocError(undef_);
                    addNameErrors(undef_);
                }
            }
            for (String n: _ints) {
                if (!StringUtil.contains(_filteredCtor,n)) {
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFile(getFile());
                    undef_.setIndexFile(getNameOffset());
                    //constructor ref header len
                    undef_.buildError(_page.getAnalysisMessages().getMustCallIntCtorNotNeed(),
                            n);
                    _page.addLocError(undef_);
                    addNameErrors(undef_);
                }
            }
        }
    }

    private static void retrieveCall(StringList _ints, Line _l) {
        if (_l.isCallInts()) {
            ConstructorId ctor_ = _l.getConstId();
            if (ctor_ != null) {
                _ints.add(StringExpUtil.getIdFromAllTypes(ctor_.getName()));
            }
        }
    }

    public int getCtorNumber() {
        return ctorNumber;
    }

    public void setCtorNumber(int _ctorNumber) {
        this.ctorNumber = _ctorNumber;
    }

    public String getCtorName() {
        return ctorName;
    }

    public void setCtorName(String _ctorName) {
        ctorName = _ctorName;
    }

    public int getLeftPar() {
        return leftPar;
    }
}
