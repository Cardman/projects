package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AnalyzingDoc;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;

public final class RendRadio extends RendInput {
    private CustList<RendDynOperationNode> opsConverterFieldValue = new CustList<RendDynOperationNode>();
    private String varNameConverterFieldValue = EMPTY_STRING;
    RendRadio(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        processAnaInput(_cont,_doc,_read, _anaDoc, _page);
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
            String varLoc_ = RendBlock.lookForVar(varNames_, _page);
            varNames_.add(varLoc_);
            varNameConverterFieldValue = varLoc_;
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(object_);
            _page.getInfosVars().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterFieldValue_,RendBlock.LEFT_PAR, varLoc_,RendBlock.RIGHT_PAR);
            int attr_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrConvertFieldValue()));
            opsConverterFieldValue = RenderExpUtil.getAnalyzedOperations(preRend_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(_page.getCurrentRoot().getResultClass());
            m_.setParam(_page.getStandards().getAliasCharSequence());
            if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(attr_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(opsConverterFieldValue.last().getResultClass().getNames(),AND_ERR),
                        _page.getStandards().getAliasCharSequence());
                Configuration.addError(badEl_, _anaDoc, _page);
            }
        }
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        Element elt_ = (Element) _nextWrite;
        Argument arg_ = processIndexes(_cont, _read, elt_);
        ContextEl context_ = _cont.getContext();
        if (context_.hasException()) {
            return;
        }
        Struct res_ = arg_.getStruct();
        if (!opsConverterFieldValue.isEmpty()) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(arg_.getStruct(), context_.getStandards().getAliasObject());
            _cont.getLastPage().putLocalVar(varNameConverterFieldValue, locVar_);
            Argument argConv_ = RenderExpUtil.calculateReuse(opsConverterFieldValue, _cont);
            _cont.getLastPage().removeLocalVar(varNameConverterFieldValue);
            if (context_.hasException()) {
                return;
            }
            res_ = argConv_.getStruct();
        }
        if (res_ == NullStruct.NULL_VALUE) {
            elt_.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
        } else {
            String strObj_ = getStringKey(_cont, res_);
            if (context_.hasException()) {
                return;
            }
            if (StringList.quickEq(elt_.getAttribute(_cont.getRendKeyWords().getAttrValue()),strObj_)) {
                elt_.setAttribute(_cont.getRendKeyWords().getAttrChecked(), _cont.getRendKeyWords().getAttrChecked());
            } else {
                elt_.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
            }
        }
    }
}
