package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.util.core.StringUtil;

final class AnaVariablePartType extends AnaLeafPartType {
    private final int value;
    private FileBlock currentFile;
    private FileBlock refFileName;
    AnaVariablePartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator, int _value) {
        super(_parent, _index, _indexInType, _type, _previousSeparator);
        value = _value;
    }

    @Override
    void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaVar(_loc);
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaVar(_loc);
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaVar(_loc);
    }

    private void anaVar(int _page) {
        setLoc(_page);
        String type_ = getTypeName();
        String t_ = StringUtil.removeAllSpaces(type_);
        t_ = StringUtil.concat(AnaInherits.PREFIX_VAR_TYPE,t_);
        setAnalyzedType(t_);
    }

    void processOffsets(AccessedBlock _rooted, AnalyzedPageEl _page) {
        FileBlock refFileName_ = _page.getRefFileName();
        refFileName = refFileName_;
        if (refFileName_ != null) {
            currentFile = ((AbsBk)_rooted).getFile();
        }
    }

    FileBlock getRefFileName() {
        return refFileName;
    }

    FileBlock getCurrentFile() {
        return currentFile;
    }

    int getValue() {
        return value;
    }
}
