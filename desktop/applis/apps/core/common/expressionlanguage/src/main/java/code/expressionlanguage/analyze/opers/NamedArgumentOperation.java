package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.RecordBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaNamedContent;
import code.util.CustList;
import code.util.core.StringUtil;

public final class NamedArgumentOperation extends AbstractUnaryOperation implements PreAnalyzableOperation {

    private final int offsetTr;
    private final int offsetTrFirst;
    private final AnaNamedContent namedContent;
    private final String name;
    private final String className;
    private final String fieldName;
    private AnaFormattedRootBlock formatted;
    private AnaFormattedRootBlock inferred;
    private RootBlock field;
    private int ref=-1;
    private final CustList<NamedFunctionBlock> customMethod = new CustList<NamedFunctionBlock>();
    private ClassField idField = new ClassField("","");
    private final CustList<AnaResultPartTypeDtoInt> partOffsets = new CustList<AnaResultPartTypeDtoInt>();
    private String importedClassName = "";
    private boolean recordBlock;

    public NamedArgumentOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op, String _na) {
        super(_index, _indexChild, _m, _op);
        namedContent = new AnaNamedContent();
        name = _na.trim();
        int offs_ = StringUtil.getFirstPrintableCharIndex(_na);
        offsetTrFirst = offs_;
        int last_ = name.lastIndexOf('.');
        if (last_ >= 0) {
            className = name.substring(0,last_).trim();
            String substring_ = name.substring(1 + last_);
            offs_ += last_ + 1;
            offs_ += StringUtil.getFirstPrintableCharIndex(substring_);
            fieldName = substring_.trim();
        } else {
            className = "";
            fieldName = name;
        }
        offsetTr = offs_;
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        namedContent.setOffset(offsetTr);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ offsetTrFirst, _page);
        MethodOperation m_ = getParent();
        if (!(m_ instanceof StandardInstancingOperation)) {
            return;
        }
        String typeInfer_ = ((StandardInstancingOperation) m_).getTypeInfer();
        AnaGeneType anaGeneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(typeInfer_));
        if (!(anaGeneType_ instanceof RecordBlock)) {
            return;
        }
        recordBlock = true;
        RootBlock r_ = (RootBlock) anaGeneType_;
        inferred = new AnaFormattedRootBlock(r_,typeInfer_);
        AnaFormattedRootBlock search_;
        if (className.isEmpty()) {
            search_ = new AnaFormattedRootBlock(r_);
        } else {
            ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(0, className, _page);
            partOffsets.add(resolvedIdType_.getDels());
            search_ = AnaInherits.getFormattedOverridingFullTypeByBases(new AnaFormattedRootBlock(r_), resolvedIdType_.getGeneType());
        }
        formatted = search_;
        if (search_ == null) {
            return;
        }
        RootBlock rootBlock_ = search_.getRootBlock();
        for (InfoBlock f: rootBlock_.getFieldsInstBlocks()) {
            int index_ = AnaTypeUtil.getIndex(f,fieldName);
            if (index_ >= 0) {
                field = rootBlock_;
                idField = new ClassField(rootBlock_.getFullName(),fieldName);
                importedClassName = f.getImportedClassName();
                ref = index_;
                break;
            }
        }
    }

    public boolean isRecordBlock() {
        return recordBlock;
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ offsetTr, _page);
        MethodOperation m_ = getParent();
        if (isNotChildOfCall(m_)) {
            FoundErrorInterpret varg_ = new FoundErrorInterpret();
            varg_.setFile(_page.getCurrentFile());
            varg_.setIndexFile(_page);
            //key word len
            varg_.buildError(_page.getAnalysisMessages().getDuplicatedParamName(),
                    name);
            _page.getLocalizer().addError(varg_);
            addErr(varg_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        OperationNode child_ = getFirstChild();
        setResultClass(AnaClassArgumentMatching.copy(child_.getResultClass(), _page.getPrimitiveTypes()));
    }

    public int getIndex() {
        return namedContent.getIndex();
    }

    public void setIndex(int _index) {
        this.namedContent.setIndex(_index);
    }

    public AnaNamedContent getNamedContent() {
        return namedContent;
    }

    public CustList<NamedFunctionBlock> getCustomMethod() {
        return customMethod;
    }

    public ClassField getIdField() {
        return idField;
    }

    public String infer() {
        if (inferred == null) {
            return "";
        }
        return AnaInherits.quickFormat(inferred,format());
    }

    public String format(String _real,RootBlock _root) {
        return AnaInherits.quickFormat(_root, _real,format());
    }

    public String format() {
        if (formatted == null) {
            return "";
        }
        if (importedClassName.isEmpty()) {
            return "";
        }
        return AnaInherits.quickFormat(formatted, importedClassName);
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public CustList<AnaResultPartTypeDtoInt> getPartOffsets() {
        return partOffsets;
    }

    public String getName() {
        return name;
    }

    public int getOffsetTr() {
        return offsetTr;
    }

    public int getRef() {
        return ref;
    }

    public RootBlock getField() {
        return field;
    }

}
