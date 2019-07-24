package code.formathtml;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.UnknownClassName;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BadElRender;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.StdStruct;
import code.formathtml.util.ValueChangeEvent;
import code.sml.DocumentBuilder;
import code.util.*;

final class HtmlRequest {

    private static final String COMMA = ",";
    private static final String EMPTY_STRING = "";
    private static final char EQUALS = '=';
    private static final String GET_LOC_VAR = ";.";
    private static final String LEFT_PAR = "(";
    private static final String RIGHT_PAR = ")";
    private HtmlRequest() {
    }


    static String formatErrorMessage(Configuration _conf,String _textId, boolean _escapeAmp,String _loc, StringMap<String> _files, String _resourcesFolder, CustList<String> _args) {
        String value_ = _textId;
        StringList elts_ = StringList.splitStrings(value_, FormatHtml.COMMA);
        String var_ = elts_.first();
        String fileName_ = ExtractObject.getProperty(_conf, var_);
        if (fileName_ == null) {
            fileName_ = var_;
        }
        StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
        if (_conf.getContext().getException() != null) {
            return EMPTY_STRING;
        }
        String preformatted_ = ExtractFromResources.getFormat(messages_, elts_.last(), _conf, _loc, fileName_);
        if (_conf.getContext().getException() != null) {
            return EMPTY_STRING;
        }
        preformatted_ = DocumentBuilder.transformSpecialChars(preformatted_, _escapeAmp);
        return StringList.simpleStringsFormat(preformatted_, _args);
    }

    static Struct invokeMethodWithNumbers(Configuration _conf, Struct _container, String _command, Argument... _args) {
        String commandExtract_ = _command;
        ImportingPage ip_ = _conf.getLastPage();
        StringList varNames_ = new StringList();
        for (Argument a: _args) {
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(a.getObjectClassName(_conf.getContext()));
            locVar_.setStruct(a.getStruct());
            String locName_ = ip_.getNextTempVar();
            varNames_.add(StringList.concat(locName_,GET_LOC_VAR));
            ip_.putLocalVar(locName_, locVar_);
        }
        Argument arg_ = ElRenderUtil.processEl(StringList.concat(commandExtract_,LEFT_PAR, StringList.join(varNames_, COMMA),RIGHT_PAR), 0, _conf);
        if (_conf.getContext().getException() != null || !_conf.getClasses().getErrorsDet().isEmpty()) {
            return NullStruct.NULL_VALUE;
        }
        for (String n: varNames_) {
            ip_.removeLocalVar(n.substring(0, n.length() - GET_LOC_VAR.length()));
        }
        return arg_.getStruct();
    }

    static Struct invokeMethodWithNumbersBis(Configuration _conf, String _action) {
        Argument arg_ = ElRenderUtil.processEl(_action, 0, _conf);
        if (_conf.getContext().getException() != null || !_conf.getClasses().getErrorsDet().isEmpty()) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();
    }

    static Struct invokeMethodWithNumbersTer(Configuration _conf, Argument _bean, int _url) {
        StringList varNames_ = _conf.getAnchorsVars().get(_url);
        StringList args_ = _conf.getAnchorsArgs().get(_url);
        ImportingPage ip_ = _conf.getLastPage();
        int s_ = varNames_.size();
        for (int i =0; i< s_; i++) {
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(_conf.getStandards().getAliasPrimInteger());
            locVar_.setStruct(new IntStruct(Numbers.parseInt(args_.get(i))));
            ip_.putLocalVar(varNames_.get(i), locVar_);
        }
        CustList<RendDynOperationNode> exps_ = _conf.getCallsExps().get(_url);
        Argument arg_ = ElRenderUtil.calculateReuse(exps_,_conf,_bean);
        if (_conf.getContext().getException() != null || !_conf.getClasses().getErrorsDet().isEmpty()) {
            return NullStruct.NULL_VALUE;
        }
        for (String n: varNames_) {
            ip_.removeLocalVar(n);
        }
        return arg_.getStruct();
    }
    static void setObject(Configuration _conf, NodeContainer _nodeContainer,
            Struct _attribute,
            Longs _indexes) {
        Struct obj_ = _nodeContainer.getStruct();
        if (obj_ == NullStruct.NULL_VALUE) {
            String err_ = _conf.getStandards().getAliasNullPe();
            ContextEl context_ = _conf.getContext();
            context_.setException(new ErrorStruct(_conf,err_));
            return;
        }
        long index_ = _nodeContainer.getIndex();
        ValueChangeEvent chg_ = calculateChange(_nodeContainer, _attribute, _indexes);
        if (index_ >= 0) {
            boolean key_ = _nodeContainer.isKey();
            ContextEl context_ = _conf.getContext();
            ResultErrorStd res_ = _conf.getAdvStandards().setElementAtIndex(obj_, (int) index_, key_, _attribute, context_);
            if (res_.getError() != null) {
                String err_ = res_.getError();
                context_.setException(new ErrorStruct(_conf,err_));
                return;
            }
        } else {
            LgNames lgNames_ = _conf.getStandards();
            String varMethod_ = _nodeContainer.getNodeInformation().getVarMethod();
            if (!varMethod_.isEmpty()) {
                //use defined class in className attribute
                String className_ = _nodeContainer.getNodeInformation().getInputClass();
                _conf.getLastPage().setProcessingAttribute(StringList.concat(_conf.getPrefix(),FormatHtml.ATTRIBUTE_CLASS_NAME));
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
                String res_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
                if (res_.isEmpty()) {
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(className_);
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().getErrorsDet().add(un_);
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getAdvStandards().getErrorEl()));
                    return;
                }
                className_ = res_;
                ImportingPage ip_ = _conf.getLastPage();
                ip_.setProcessingAttribute(StringList.concat(_conf.getPrefix(),FormatHtml.VAR_METHOD));
                ip_.setLookForAttrValue(true);
                ip_.setOffset(0);
                Struct current_ = ip_.getGlobalArgument().getStruct();
                ip_.setGlobalArgumentStruct(obj_, _conf);
                String tmp_ = ip_.getNextTempVar();
                LocalVariable locVar_ = new LocalVariable();
                locVar_.setClassName(className_);
                locVar_.setStruct(_attribute);
                ip_.putLocalVar(tmp_, locVar_);
                ElRenderUtil.processEl(StringList.concat(varMethod_,LEFT_PAR,tmp_,GET_LOC_VAR,RIGHT_PAR), 0, _conf);
                ip_.removeLocalVar(tmp_);
                ip_.setGlobalArgumentStruct(current_, _conf);
                return;
            }
            ImportingPage ip_ = _conf.getLastPage();
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(lgNames_.getStructClassName(obj_, _conf.getContext()));
            lv_.setStruct(obj_);
            String nameVar_ = ip_.getNextTempVar();
            ip_.putLocalVar(nameVar_, lv_);
            String nameValue_ = ip_.getNextTempVar();
            lv_ = new LocalVariable();
            lv_.setClassName(lgNames_.getStructClassName(_attribute, _conf.getContext()));
            lv_.setStruct(_attribute);
            ip_.putLocalVar(nameValue_, lv_);
            String expressionLeft_ = StringList.concat(nameVar_, GET_LOC_VAR, _nodeContainer.getLastToken());
            String expressionRight_ = StringList.concat(nameValue_, GET_LOC_VAR);
            String full_ = StringList.concat(expressionLeft_,String.valueOf(EQUALS),expressionRight_);
            ElRenderUtil.processEl(full_, 0, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
        }
        if (chg_ != null) {
            String method_ = _nodeContainer.getNodeInformation().getChanging();
            ImportingPage ip_ = _conf.getLastPage();
            ip_.setProcessingAttribute(StringList.concat(_conf.getPrefix(),FormatHtml.ATTRIBUTE_VALUE_CHANGE_EVENT));
            ip_.setLookForAttrValue(false);
            ip_.setOffset(0);
            Struct current_ = ip_.getGlobalArgument().getStruct();
            ip_.setGlobalArgumentStruct(obj_, _conf);
            String tmp_ = ip_.getNextTempVar();
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(_conf.getAdvStandards().getValueChangedEvent());
            locVar_.setStruct(StdStruct.wrapStd(chg_, _conf.getContext()));
            ip_.putLocalVar(tmp_, locVar_);
            StringBuilder str_ = new StringBuilder(method_);
            str_.append(LEFT_PAR);
            str_.append(tmp_);
            str_.append(GET_LOC_VAR);
            str_.append(RIGHT_PAR);
            ElRenderUtil.processEl(str_.toString(), 0, _conf);
            ip_.removeLocalVar(tmp_);
            ip_.setGlobalArgumentStruct(current_, _conf);
        }
    }
    private static ValueChangeEvent calculateChange(NodeContainer _nodeContainer,
            Struct _attribute,
            Longs _indexes) {
        String varMethod_ = _nodeContainer.getNodeInformation().getVarMethod();
        if (!varMethod_.isEmpty()) {
            return null;
        }
        String method_ = _nodeContainer.getNodeInformation().getChanging();
        if (method_.isEmpty()) {
            return null;
        }
        return new ValueChangeEvent(_indexes, _nodeContainer.getTypedStruct(), _attribute);
    }
}
