package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.util.CustList;
import code.util.StringList;

public final class ExecCaseCondition extends ExecSwitchPartBlock {

    private final String value;
    private CustList<ExecOperationNode> opValue;

    private boolean builtEnum;

    private String typeEnum;

    private int valueOffset;

    public ExecCaseCondition(OffsetsBlock _offset, String _value, int _valueOffset, boolean _builtEnum, String _typeEnum, CustList<ExecOperationNode> _opValue) {
        super(_offset);
        value = _value;
        builtEnum = _builtEnum;
        typeEnum = _typeEnum;
        valueOffset = _valueOffset;
        opValue = _opValue;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        ExecBracedBlock parent_ = getParent();
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverSwitchs(parent_,this);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = valueOffset;
        _parts.add(new PartOffset(tag_,off_));
        if (builtEnum) {
            GeneType type_ = _cont.getClassBody(typeEnum);
            int delta_ = -1;
            for (ExecBlock b: getDirectChildren((ExecBlock) type_)) {
                if (!(b instanceof ExecInnerTypeOrElement)) {
                    continue;
                }
                ExecInnerTypeOrElement f_ = (ExecInnerTypeOrElement)b;
                if (!StringList.quickEq(f_.getUniqueFieldName(),value)) {
                    continue;
                }
                delta_ = f_.getFieldNameOffset();
            }
            String file_ = ((ExecRootBlock) type_).getFile().getRenderFileName();
            String currentFileName_ = _cont.getCoverage().getCurrentFileName();
            String rel_ = ElUtil.relativize(currentFileName_,file_+"#m"+delta_);
            tag_ = "<a title=\""+ElUtil.transform(typeEnum +"."+ value)+"\" href=\""+rel_+"\">";
            _parts.add(new PartOffset(tag_,off_));
            tag_ = "</a>";
            _parts.add(new PartOffset(tag_,off_+value.length()));
        } else {
            int offsetEndBlock_ = off_ +value.length();
            ElUtil.buildCoverageReport(_cont,off_,this,opValue,offsetEndBlock_,_parts);
        }
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+value.length()));
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        rw_.setBlock(getFirstChild());
        ip_.getLastStack().setCurrentVisitedBlock(this);
    }

    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    public String getValue() {
        return value;
    }
}
