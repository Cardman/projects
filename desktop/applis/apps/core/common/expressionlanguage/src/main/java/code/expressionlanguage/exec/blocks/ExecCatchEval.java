package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.util.CustList;

public final class ExecCatchEval extends ExecAbstractCatchEval {

    private String importedClassName;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    private final String variableName;

    private int variableNameOffset;

    public ExecCatchEval(OffsetsBlock _offset, String _variableName, int _variableNameOffset, String _importedClassName, CustList<PartOffset> _parts) {
        super(_offset);
        variableName = _variableName;
        variableNameOffset = _variableNameOffset;
        importedClassName = _importedClassName;
        partOffsets = _parts;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getCatchVars().removeKey(variableName);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        super.processReport(_cont, _parts);
        _parts.addAllElts(partOffsets);
        String tag_ = "<a name=\"m"+ variableNameOffset +"\">";
        _parts.add(new PartOffset(tag_,variableNameOffset));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,variableNameOffset+variableName.length()));
        _cont.getCoverage().getCatchVars().put(variableName,variableNameOffset);
    }

    public String getVariableName() {
        return variableName;
    }
}
