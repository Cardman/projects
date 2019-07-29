package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.NodeContainer;
import code.sml.DocumentBuilder;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

final class RendRequestUtil {

    private static final String EMPTY_STRING = "";
    private RendRequestUtil() {
    }


    static String formatErrorMessage(Configuration _conf,String _textId, boolean _escapeAmp,String _loc, StringMap<String> _files, String _resourcesFolder, CustList<String> _args) {
        String value_ = _textId;
        StringList elts_ = StringList.splitStrings(value_, FormatHtml.COMMA);
        String var_ = elts_.first();
        String fileName_ = ExtractObject.getProperty(_conf, var_);
        if (fileName_ == null) {
            fileName_ = var_;
        }
        StringMap<String> messages_ = RendExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
        if (_conf.getContext().getException() != null) {
            return EMPTY_STRING;
        }
        String preformatted_ = RendExtractFromResources.getFormat(messages_, elts_.last(), _conf, _loc, fileName_);
        if (_conf.getContext().getException() != null) {
            return EMPTY_STRING;
        }
        preformatted_ = DocumentBuilder.transformSpecialChars(preformatted_, _escapeAmp);
        return StringList.simpleStringsFormat(preformatted_, _args);
    }

    static Struct invokeMethodWithNumbersBis(Configuration _conf, String _action) {
        Argument arg_ = RenderExpUtil.processEl(_action, 0, _conf);
        if (!_conf.getClasses().getErrorsDet().isEmpty() || _conf.getContext().getException() != null) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();
    }

    static Struct redirect(Configuration _conf, Argument _bean, int _url) {
        if (_conf.getHtmlPage().getConstAnchors().get(_url)) {
            String action_ = _conf.getHtmlPage().getAnchorsNames().get(_url);
            if (action_.indexOf('(') == CustList.INDEX_NOT_FOUND_ELT) {
                action_ = StringList.concat(action_,"()");
            }
            return invokeMethodWithNumbersBis(_conf,action_);
        }
        StringList varNames_ = _conf.getHtmlPage().getAnchorsVars().get(_url);
        StringList args_ = _conf.getHtmlPage().getAnchorsArgs().get(_url);
        ImportingPage ip_ = _conf.getLastPage();
        int s_ = varNames_.size();
        for (int i =0; i< s_; i++) {
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(_conf.getStandards().getAliasPrimInteger());
            locVar_.setStruct(new IntStruct(Numbers.parseInt(args_.get(i))));
            ip_.putLocalVar(varNames_.get(i), locVar_);
        }
        CustList<RendDynOperationNode> exps_ = _conf.getHtmlPage().getCallsExps().get(_url);
        Argument arg_ = RenderExpUtil.calculateReuse(exps_,_conf,_bean);
        for (String n: varNames_) {
            ip_.removeLocalVar(n);
        }
        if (_conf.getContext().getException() != null) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();
    }
    static void setRendObject(Configuration _conf, NodeContainer _nodeContainer,
                          Struct _attribute) {
        Struct obj_ = _nodeContainer.getStruct();
        String attrName_ = _nodeContainer.getVarName();
        String prev_ = _nodeContainer.getVarPrevName();
        CustList<RendDynOperationNode> wr_ = _nodeContainer.getOpsWrite();
        ImportingPage ip_ = _conf.getLastPage();
        LocalVariable lv_ = new LocalVariable();
        BeanLgNames stds_ = _conf.getAdvStandards();
        lv_.setClassName(stds_.getStructClassName(obj_, _conf.getContext()));
        lv_.setStruct(obj_);
        ip_.putLocalVar(prev_, lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(stds_.getStructClassName(_attribute, _conf.getContext()));
        lv_.setStruct(_attribute);
        ip_.putLocalVar(attrName_, lv_);
        RenderExpUtil.calculateReuse(wr_,_conf);
        ip_.removeLocalVar(prev_);
        ip_.removeLocalVar(attrName_);
    }
}
