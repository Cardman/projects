package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.util.core.StringUtil;

final class AnaVariablePartType extends AnaLeafPartType {
    private final int value;
    private FileBlock currentFile;
    private FileBlock refFileName;
    AnaVariablePartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator, int _value, AnalysisMessages _messages) {
        super(_parent, _index, _indexInType, _type, _previousSeparator, _messages);
        value = _value;
    }

    @Override
    void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaVar(_loc,_page);
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaVar(_loc,_page);
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaVar(_loc,_page);
    }

    private void anaVar(int _loc, AnalyzedPageEl _page) {
        setLoc(_loc);
        refFileName = _page.getRefFileName();
        String type_ = getTypeName();
        String t_ = StringUtil.removeAllSpaces(type_);
        t_ = StringUtil.concat(AnaInherits.PREFIX_VAR_TYPE,t_);
        setAnalyzedType(t_);
    }

    void processOffsets(FileBlock _rooted) {
        if (refFileName != null) {
            currentFile = _rooted;
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
