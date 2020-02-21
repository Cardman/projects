package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.BadElError;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BeanCustLgNames;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;

public final class RendRadio extends RendInput {
    private CustList<RendDynOperationNode> opsConverterFieldValue = new CustList<RendDynOperationNode>();
    private String varNameConverterFieldValue = "";
    RendRadio(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list) {
        processAnaInput(_cont,_doc,_read);
        _list.removeAllString(CHECKED);
        _list.removeAllString(ATTRIBUTE_VALUE);
        _list.removeAllString(ATTRIBUTE_NAME);
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_CLASS_NAME));
        _list.removeAllString(NUMBER_INPUT);
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_CONVERT_VALUE));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_CONVERT_FIELD_VALUE));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_CONVERT_FIELD));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VAR_VALUE));
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VALIDATOR));
        _list.removeAllString(ATTRIBUTE_TYPE);
        MethodAccessKind st_ = _doc.getStaticContext();
        String converterFieldValue_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_CONVERT_FIELD_VALUE));
        if (!converterFieldValue_.trim().isEmpty()) {
            String object_ = _cont.getStandards().getAliasObject();
            StringList varNames_ = new StringList();
            String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
            varNames_.add(varLoc_);
            varNameConverterFieldValue = varLoc_;
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(object_);
            _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
            String preRend_ = StringList.concat(converterFieldValue_,"(",BeanCustLgNames.sufficLocal(_cont.getContext(),varLoc_),")");
            opsConverterFieldValue = RenderExpUtil.getAnalyzedOperations(preRend_,0,_cont,Calculation.staticCalculation(st_));
            for (String v:varNames_) {
                _cont.getLocalVarsAna().last().removeKey(v);
            }
            Mapping m_ = new Mapping();
            m_.setArg(opsConverterFieldValue.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasCharSequence());
            if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                BadElError badEl_ = new BadElError();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                _cont.getClasses().addError(badEl_);
            }
        }
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        Element elt_ = (Element) _nextWrite;
        Argument arg_ = processIndexes(_cont, _read, elt_);
        if (_cont.getContext().hasException()) {
            return;
        }
        String name_ = _read.getAttribute(ATTRIBUTE_NAME);
        if (name_.isEmpty()) {
            return;
        }
        Struct res_ = arg_.getStruct();
        if (!opsConverterFieldValue.isEmpty()) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(arg_.getStruct(),_cont.getStandards().getAliasObject());
            _cont.getLastPage().putLocalVar(varNameConverterFieldValue, locVar_);
            Argument argConv_ = RenderExpUtil.calculateReuse(opsConverterFieldValue, _cont);
            _cont.getLastPage().removeLocalVar(varNameConverterFieldValue);
            if (_cont.getContext().hasException()) {
                return;
            }
            res_ = argConv_.getStruct();
        }
        if (res_ == NullStruct.NULL_VALUE) {
            elt_.removeAttribute(CHECKED);
        } else {
            String strObj_ = getStringKey(_cont, res_);
            if (_cont.getContext().hasException()) {
                return;
            }
            if (StringList.quickEq(elt_.getAttribute(ATTRIBUTE_VALUE),strObj_)) {
                elt_.setAttribute(CHECKED, CHECKED);
            } else {
                elt_.removeAttribute(CHECKED);
            }
        }
    }
}
