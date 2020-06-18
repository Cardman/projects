package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.opers.ExecAffectationOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.opers.ExecStandardInstancingOperation;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.InfoBlock;
import code.expressionlanguage.methods.InnerElementBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.StringList;

public final class ExecInnerElementBlock extends ExecRootBlock implements ExecInnerTypeOrElement, ExecUniqueRootedBlock {

    private final String fieldName;

    private final String value;

    private String importedClassName;
    private String realImportedClassName;

    private CustList<ExecOperationNode> opValue;

    private int fieldNameOffest;

    private int valueOffest;

    private int trOffset;

    private final StringList allSuperTypes = new StringList();

    private String importedDirectSuperClass = "";
    private StringList importedDirectSuperInterfaces = new StringList();
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    public ExecInnerElementBlock(InnerElementBlock _offset) {
        super(_offset);
        fieldName = _offset.getUniqueFieldName();
        value = _offset.getValue();
        fieldNameOffest = _offset.getFieldNameOffset();
        valueOffest = _offset.getValueOffest();
    }

    @Override
    public void buildImportedTypes(InfoBlock _key) {
        importedClassName = _key.getImportedClassName();
        realImportedClassName = _key.getRealImportedClassName();
        partOffsets = _key.getTypePartOffsets();
    }
    @Override
    public String getUniqueFieldName() {
        return fieldName;
    }

    @Override
    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    @Override
    public void setTrOffset(int trOffset) {
        this.trOffset = trOffset;
    }

    public void setOpValue(CustList<ExecOperationNode> opValue) {
        this.opValue = opValue;
    }

    @Override
    public int getFieldNameOffset() {
        return fieldNameOffest;
    }

    @Override
    public String getImportedClassName() {
        return importedClassName;
    }

    @Override
    public String getRealImportedClassName() {
        return realImportedClassName;
    }

    @Override
    public boolean isStaticField() {
        return true;
    }

    @Override
    public boolean isFinalField() {
        return true;
    }

    @Override
    public StringList getFieldName() {
        return new StringList(fieldName);
    }

    @Override
    public boolean isFinalType() {
        return true;
    }

    @Override
    public boolean isAbstractType() {
        return true;
    }

    @Override
    public StringList getAllSuperTypes() {
        return allSuperTypes;
    }

    @Override
    public boolean isStaticType() {
        return false;
    }

    @Override
    public boolean withoutInstance() {
        return true;
    }

    @Override
    public StringList getImportedDirectSuperTypes() {
        StringList l_ = new StringList(importedDirectSuperClass);
        l_.addAllElts(importedDirectSuperInterfaces);
        return l_;
    }

    @Override
    public void buildTypes(StringList _ints,String _sup) {
        importedDirectSuperInterfaces = _ints;
        importedDirectSuperClass = _sup;
    }
    @Override
    public String getImportedDirectGenericSuperClass() {
        return importedDirectSuperClass;
    }

    @Override
    public StringList getImportedDirectGenericSuperInterfaces() {
        return importedDirectSuperInterfaces;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return new ExpressionLanguage(opValue);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (ip_ instanceof StaticInitPageEl) {
            ip_.setGlobalOffset(fieldNameOffest);
            ip_.setOffset(0);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            ElUtil.tryToCalculate(_cont,el_, trOffset);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
        }
        processBlock(_cont);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        processAnnotationReport(_cont,_parts);
        ExecAffectationOperation root_ = (ExecAffectationOperation) opValue.last();
        ExecStandardInstancingOperation inst_ = (ExecStandardInstancingOperation) root_.getFirstChild().getNextSibling();
        String cl_ = inst_.getClassName();
        cl_ = Templates.getIdFromAllTypes(cl_);
        ConstructorId c_ = inst_.getConstId();
        GeneType type_ = _cont.getClassBody(cl_);
        String file_ = ((ExecRootBlock) type_).getFile().getFileName();
        String fileName_ = _cont.getCoverage().getCurrentFileName();
        CustList<ExecConstructorBlock> ctors_ = getConstructorBodiesById(_cont, cl_, c_);
        if (!ctors_.isEmpty()) {
            ExecConstructorBlock ctor_ = ctors_.first();
            String rel_ = ElUtil.relativize(fileName_,file_+".html#m"+ctor_.getNameOffset());
            String tag_ = "<a name=\"m"+fieldNameOffest+"\" title=\""+ ElUtil.transform(cl_ +"."+ c_.getSignature(_cont))+"\" href=\""+rel_+"\">";
            _parts.add(new PartOffset(tag_,fieldNameOffest));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_,fieldNameOffest+fieldName.length()));
        } else {
            String tag_ = "<a name=\"m"+fieldNameOffest+"\">";
            _parts.add(new PartOffset(tag_,fieldNameOffest));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_,fieldNameOffest+fieldName.length()));
        }
        _parts.addAllElts(partOffsets);
        int blOffset_ = valueOffest;
        int endBl_ = valueOffest + value.length();
        ElUtil.buildCoverageReport(_cont,blOffset_,this,opValue,endBl_,_parts,trOffset-1,fieldName,false);
    }
}
