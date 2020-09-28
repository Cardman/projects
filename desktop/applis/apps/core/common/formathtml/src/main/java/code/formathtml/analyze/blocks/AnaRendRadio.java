package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.formathtml.Configuration;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.util.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;

public final class AnaRendRadio extends AnaRendInput {
    private OperationNode rootConverterFieldValue;
    private String varNameConverterFieldValue = EMPTY_STRING;
    AnaRendRadio(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        processAnaInput(_cont, _read, _anaDoc, _page);
        _list.removeAllString(_cont.getRendKeyWords().getAttrChecked());
        _list.removeAllString(_cont.getRendKeyWords().getAttrValue());
        _list.removeAllString(_cont.getRendKeyWords().getAttrName());
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrClassName()));
        _list.removeAllString(_cont.getRendKeyWords().getAttrNi());
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertValue()));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertFieldValue()));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertField()));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrVarValue()));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator()));
        _list.removeAllString(_cont.getRendKeyWords().getAttrType());
        String converterFieldValue_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrConvertFieldValue()));
        if (!converterFieldValue_.trim().isEmpty()) {
            String object_ = _page.getStandards().getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
            varNames_.add(varLoc_);
            varNameConverterFieldValue = varLoc_;
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(object_);
            _page.getInfosVars().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterFieldValue_,AnaRendBlock.LEFT_PAR, varLoc_,AnaRendBlock.RIGHT_PAR);
            int attr_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrConvertFieldValue()));
            rootConverterFieldValue = RenderAnalysis.getRootAnalyzedOperations(preRend_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(rootConverterFieldValue.getResultClass());
            m_.setParam(_page.getStandards().getAliasCharSequence());
            if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(attr_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(rootConverterFieldValue.getResultClass().getNames(),AND_ERR),
                        _page.getStandards().getAliasCharSequence());
                Configuration.addError(badEl_, _anaDoc, _page);
            }
        }
    }

    public OperationNode getRootConverterFieldValue() {
        return rootConverterFieldValue;
    }

    public String getVarNameConverterFieldValue() {
        return varNameConverterFieldValue;
    }
}
