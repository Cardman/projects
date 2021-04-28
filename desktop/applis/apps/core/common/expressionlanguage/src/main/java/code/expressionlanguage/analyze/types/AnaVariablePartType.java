package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.core.StringUtil;

final class AnaVariablePartType extends AnaLeafPartType {
    private final int value;
    AnaVariablePartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator, int _value) {
        super(_parent, _index, _indexInType, _type, _previousSeparator);
        value = _value;
    }

    @Override
    void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaVar();
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaVar();
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaVar();
    }

    private void anaVar() {
        String type_ = getTypeName();
        String t_ = StringUtil.removeAllSpaces(type_);
        t_ = StringUtil.concat(AnaInherits.PREFIX_VAR_TYPE,t_);
        setAnalyzedType(t_);
    }

    void processOffsets(AccessedBlock _rooted, AnalyzedPageEl _page) {
        if (!_page.isGettingParts()) {
            return;
        }
        FileBlock refFileName_ = _page.getRefFileName();
        if (refFileName_ != null) {
            setHref(ExportCst.href(((AbsBk)_rooted).getFile(), refFileName_,value));
        } else {
            setHref(ExportCst.href(value));
        }
    }
}
