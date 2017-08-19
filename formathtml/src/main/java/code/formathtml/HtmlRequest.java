package code.formathtml;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exceptions.SetterException;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.ValueChangeEvent;
import code.serialize.ConverterMethod;
import code.serialize.SerializeXmlObject;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstClasses;
import code.util.exceptions.NullObjectException;
import code.util.opers.BaseListUtil;
import code.xml.XmlParser;

final class HtmlRequest {

    private static final String COMMA = ",";
    private static final String EMPTY_STRING = "";
    private static final String GET_LOC_VAR = ";.";
    private static final String TMP_VAR = "tmpvar";
    private static final char LEFT_PAR_CHAR = '(';
    private static final String LEFT_PAR = EMPTY_STRING+LEFT_PAR_CHAR;
    private static final char RIGHT_PAR_CHAR = ')';
    private static final String RIGHT_PAR = EMPTY_STRING+RIGHT_PAR_CHAR;
    private HtmlRequest() {
    }


    static String formatErrorMessage(Configuration _conf,String _textId, boolean _escapeAmp,String _loc, StringMap<String> _files, String _resourcesFolder, Object... _args) {
        String value_ = _textId;
        StringList elts_ = StringList.splitStrings(value_, FormatHtml.COMMA);
        String var_ = elts_.first();
        String fileName_ = ExtractObject.getProperty(_conf, var_);
        if (fileName_ == null) {
            fileName_ = var_;
        }
        StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
        String preformatted_ = ExtractFromResources.getFormat(messages_, elts_.last(), _conf, _loc, fileName_);
        preformatted_ = XmlParser.transformSpecialChars(preformatted_, _escapeAmp);
        return StringList.simpleFormat(preformatted_, _args);
    }

    static Object invokeMethodWithNumbers(Configuration _conf, Object _container, String _command, Argument... _args) {
        String command_ = _command;
        Object obj_ = _container;
        String commandExtract_ = _command;
        if (_command.contains(FormatHtml.DOT)) {
            command_ = _command.substring(CustList.FIRST_INDEX, _command.lastIndexOf(FormatHtml.DOT));
            obj_ = ElUtil.processEl(command_, 0, _conf.toContextEl()).getObject();
            _conf.getLastPage().addToOffset(command_.length()+FormatHtml.DOT.length());
            commandExtract_ = _command.substring(_command.lastIndexOf(FormatHtml.DOT)+FormatHtml.DOT.length());
        }
        ExtractObject.checkNullPointer(_conf, obj_);
        ImportingPage ip_ = _conf.getLastPage();
        Object current_ = ip_.getGlobalArgument().getObject();
        ip_.setGlobalArgumentObj(obj_);
        StringList varNames_ = new StringList();
        for (Argument a: _args) {
            String tmp_ = TMP_VAR;
            int i_ = CustList.FIRST_INDEX;
            while (ip_.getLocalVars().contains(tmp_+i_)) {
                i_++;
            }
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(ConstClasses.resolve(a.getObjectClassName()));
            locVar_.setStruct(a.getStruct());
            varNames_.add(tmp_+i_+GET_LOC_VAR);
            ip_.getLocalVars().put(tmp_+i_, locVar_);
        }
        Argument arg_ = ElUtil.processEl(commandExtract_+LEFT_PAR+varNames_.join(COMMA)+RIGHT_PAR, 0, _conf.toContextEl());
        ip_.setGlobalArgumentObj(current_);
        for (String n: varNames_) {
            ip_.getLocalVars().removeKey(n.substring(0, n.length() - GET_LOC_VAR.length()));
        }
        return arg_.getObject();
    }

    static void setObject(Configuration _conf, NodeContainer _nodeContainer,
            Object _attribute,
            Numbers<Long> _indexes) {
        Object obj_ = _nodeContainer.getObject();
        if (obj_ == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        Class<?> class_ = obj_.getClass();
        long index_ = _nodeContainer.getIndex();
        ValueChangeEvent chg_ = calculateChange(_nodeContainer, _attribute, _indexes);
        if (index_ >= 0) {
            try {
                if (_nodeContainer.getLastToken().isEmpty()) {
                    if (obj_.getClass().isArray()) {
                        //obj_ is array
                        Array.set(obj_, (int) index_, _attribute);
                    } else {
                        //obj_ is instance of java.util.CustList
                        BaseListUtil.set(obj_, (int) index_, _attribute);
                    }
                } else {
                    //obj_ is instance of java.util.ListableEntries
                    boolean key_ = _nodeContainer.getLastToken().endsWith(FormatHtml.GET_KEY);
                    BaseListUtil.set(obj_, key_, (int) index_, _nodeContainer.getTypedField(), _attribute);
                }
            } catch (VirtualMachineError _0) {
                throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
            } catch (RuntimeException _0) {
                throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
            }
        } else {
            String varMethod_ = _nodeContainer.getNodeInformation().getVarMethod();
            if (!varMethod_.isEmpty()) {
                //use defined class in className attribute
                String className_ = _nodeContainer.getNodeInformation().getInputClass();
                _conf.getLastPage().setProcessingAttribute(_conf.getPrefix()+FormatHtml.ATTRIBUTE_CLASS_NAME);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
                ExtractObject.classForName(_conf, 0, className_);
                ImportingPage ip_ = _conf.getLastPage();
                ip_.setProcessingAttribute(_conf.getPrefix()+FormatHtml.VAR_METHOD);
                ip_.setLookForAttrValue(true);
                ip_.setOffset(0);
                Object current_ = ip_.getGlobalArgument().getObject();
                ip_.setGlobalArgumentObj(obj_);
                String tmp_ = TMP_VAR;
                int i_ = CustList.FIRST_INDEX;
                while (ip_.getLocalVars().contains(tmp_+i_)) {
                    i_++;
                }
                LocalVariable locVar_ = new LocalVariable();
                locVar_.setClassName(ConstClasses.resolve(className_));
                locVar_.setElement(_attribute);
                ip_.getLocalVars().put(tmp_+i_, locVar_);
                ElUtil.processEl(varMethod_+LEFT_PAR+tmp_+i_+GET_LOC_VAR+RIGHT_PAR, 0, _conf.toContextEl());
                ip_.getLocalVars().removeKey(tmp_+i_);
                ip_.setGlobalArgumentObj(current_);
                return;
            }
            try {
                Field field_ = SerializeXmlObject.getDeclaredField(class_, _nodeContainer.getLastToken());
                ContextEl context_ = _conf.toContextEl();
                context_.getAccessValue().setAccess(field_, context_);
                ConverterMethod.setField(field_, obj_, _attribute);
            } catch (RuntimeException _0) {
                _conf.getLastPage().setProcessingAttribute(FormatHtml.EMPTY_STRING);
                _conf.getLastPage().setLookForAttrValue(false);
                _conf.getLastPage().setOffset(0);
                throw new SetterException(_nodeContainer.getLastToken()+FormatHtml.RETURN_LINE+_conf.joinPages());
            } catch (ExceptionInInitializerError _0) {
                _conf.getLastPage().setProcessingAttribute(FormatHtml.EMPTY_STRING);
                _conf.getLastPage().setLookForAttrValue(false);
                _conf.getLastPage().setOffset(0);
                throw new ErrorCausingException(_nodeContainer.getLastToken()+FormatHtml.RETURN_LINE+_conf.joinPages(), new Struct(_0));
            }
        }
        if (chg_ != null) {
            String method_ = _nodeContainer.getNodeInformation().getChanging();
            ImportingPage ip_ = _conf.getLastPage();
            ip_.setProcessingAttribute(_conf.getPrefix()+FormatHtml.ATTRIBUTE_VALUE_CHANGE_EVENT);
            ip_.setLookForAttrValue(false);
            ip_.setOffset(0);
            Object current_ = ip_.getGlobalArgument().getObject();
            ip_.setGlobalArgumentObj(obj_);
            String tmp_ = TMP_VAR;
            int i_ = CustList.FIRST_INDEX;
            while (ip_.getLocalVars().contains(tmp_+i_)) {
                i_++;
            }
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(ValueChangeEvent.class.getName());
            locVar_.setElement(chg_);
            ip_.getLocalVars().put(tmp_+i_, locVar_);
            ElUtil.processEl(method_+LEFT_PAR+tmp_+i_+GET_LOC_VAR+RIGHT_PAR, 0, _conf.toContextEl());
            ip_.getLocalVars().removeKey(tmp_+i_);
            ip_.setGlobalArgumentObj(current_);
        }
    }
    private static ValueChangeEvent calculateChange(NodeContainer _nodeContainer,
            Object _attribute,
            Numbers<Long> _indexes) {
        String varMethod_ = _nodeContainer.getNodeInformation().getVarMethod();
        if (!varMethod_.isEmpty()) {
            return null;
        }
        String method_ = _nodeContainer.getNodeInformation().getChanging();
        if (method_.isEmpty()) {
            return null;
        }
        return new ValueChangeEvent(_indexes, _nodeContainer.getTypedField(), _attribute);
    }
}
