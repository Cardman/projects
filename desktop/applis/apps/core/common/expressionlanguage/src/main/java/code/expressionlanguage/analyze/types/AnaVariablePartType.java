package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
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
        analyzeLine(null, _local,_rooted, null);
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        String type_ = getTypeName();
        String t_ = StringUtil.removeAllSpaces(type_);
        t_ = StringUtil.concat(AnaInherits.PREFIX_VAR_TYPE,t_);
        setAnalyzedType(t_);
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page) {
        String type_ = getTypeName();
        String t_ = StringUtil.removeAllSpaces(type_);
        t_ = StringUtil.concat(AnaInherits.PREFIX_VAR_TYPE,t_);
        setAnalyzedType(t_);
    }

    void processOffsets(AccessedBlock _rooted, AnalyzedPageEl _page) {
        if (!_page.isGettingParts()) {
            return;
        }
        String curr_ = ((Block)_rooted).getFile().getRenderFileName();
        String rel_ = "";
        if (!_page.getRefFileName().isEmpty()) {
            rel_ = LinkageUtil.relativize(curr_, _page.getRefFileName());
        }
        setHref(rel_+"#m"+value);
    }
}
