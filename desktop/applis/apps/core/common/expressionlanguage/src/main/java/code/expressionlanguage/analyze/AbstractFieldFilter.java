package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.common.AnaGeneType;
import code.util.StringList;
import code.util.StringMap;

public interface AbstractFieldFilter {
    void tryAddField(FieldInfo _fi, boolean _accessFromSuper, boolean _superClass, int _anc, boolean _static, boolean _aff, String _name, String _glClass, StringMap<FieldResult> _ancestors, String _cl, AnaGeneType _root, StringList _superTypesBase, StringMap<String> _superTypesBaseMap, AnalyzedPageEl _page);
    FieldInfo getFieldInfo(AnaGeneType _anaGeneType, String _fieldName);
}
