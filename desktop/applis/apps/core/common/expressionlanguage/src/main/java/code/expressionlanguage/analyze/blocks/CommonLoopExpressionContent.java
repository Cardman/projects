package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;

public abstract class CommonLoopExpressionContent {

    private final String classIndexName;
    private String importedClassIndexName;
    private final int classIndexNameOffset;
    protected CommonLoopExpressionContent(OffsetStringInfo _clName) {
        classIndexName = _clName.getInfo();
        classIndexNameOffset = _clName.getOffset();
    }
    public void resolveIndex(AbsBk _block,AnalyzedPageEl _page) {
        _page.setSumOffset(classIndexNameOffset);
        _page.zeroOffset();
        setImportedClassIndexName(ResolvingTypes.resolveCorrectType(classIndexName, _page).getResult(_page));
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_block.getFile());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _page.addLocError(cast_);
            _block.addErrorBlock(cast_.getBuiltError());
        }
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public void setImportedClassIndexName(String _imp) {
        this.importedClassIndexName = _imp;
    }

    public int getClassIndexNameOffset() {
        return classIndexNameOffset;
    }

    public String getClassIndexName() {
        return classIndexName;
    }
}
