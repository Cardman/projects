package code.formathtml;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ElUtil;
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
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.xml.XmlParser;

final class HtmlRequest {

    private static final String MOVE = "move";
    private static final String SET_VALUE = "setValue";
    private static final String SET = "set";
    private static final String COMMA = ",";
    private static final String EMPTY_STRING = "";
    private static final char EQUALS = '=';
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

    static Object invokeMethodWithNumbers(Configuration _conf, Struct _container, String _command, Argument... _args) {
        String command_ = _command;
        Struct obj_ = _container;
        String commandExtract_ = _command;
        if (_command.contains(FormatHtml.DOT)) {
            command_ = _command.substring(CustList.FIRST_INDEX, _command.lastIndexOf(FormatHtml.DOT));
            obj_ = ElUtil.processEl(command_, 0, _conf.toContextEl()).getStruct();
            _conf.getLastPage().addToOffset(command_.length()+FormatHtml.DOT.length());
            commandExtract_ = _command.substring(_command.lastIndexOf(FormatHtml.DOT)+FormatHtml.DOT.length());
        }
        ExtractObject.checkNullPointer(_conf, obj_.getInstance());
        ImportingPage ip_ = _conf.getLastPage();
        Struct current_ = ip_.getGlobalArgument().getStruct();
        ip_.setGlobalArgumentStruct(obj_);
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
        ip_.setGlobalArgumentStruct(current_);
        for (String n: varNames_) {
            ip_.getLocalVars().removeKey(n.substring(0, n.length() - GET_LOC_VAR.length()));
        }
        return arg_.getObject();
    }

    static void setObject(Configuration _conf, NodeContainer _nodeContainer,
            Object _attribute,
            Numbers<Long> _indexes) {
        Struct obj_ = _nodeContainer.getStruct();
        if (obj_.isNull()) {
            throw new NullObjectException(_conf.joinPages());
        }
        long index_ = _nodeContainer.getIndex();
        ValueChangeEvent chg_ = calculateChange(_nodeContainer, _attribute, _indexes);
        if (index_ >= 0) {
            try {
                if (_nodeContainer.getLastToken().isEmpty()) {
                    if (obj_.getInstance().getClass().isArray()) {
                        //obj_ is array
                        Array.set(obj_.getInstance(), (int) index_, _attribute);
                    } else {
                        //obj_ is instance of java.util.CustList
                        Method m_ = SerializeXmlObject.getDeclaredMethod(Listable.class, SET, int.class, Object.class);
                        ConverterMethod.invokeMethod(m_, obj_.getInstance(), (int) index_, _attribute);
                    }
                } else {
                    //obj_ is instance of java.util.ListableEntries
                    boolean key_ = _nodeContainer.getLastToken().endsWith(FormatHtml.GET_KEY);
                    if (!key_) {
                        Method m_ = SerializeXmlObject.getDeclaredMethod(ListableEntries.class, SET_VALUE, int.class, Object.class);
                        ConverterMethod.invokeMethod(m_, obj_.getInstance(), (int)index_, _attribute);
                    } else {
                        Method m_ = SerializeXmlObject.getDeclaredMethod(ListableEntries.class, MOVE, Object.class, Object.class);
                        ConverterMethod.invokeMethod(m_, obj_.getInstance(), _nodeContainer.getTypedField(), _attribute);
                    }
                }
            } catch (Throwable _0) {
                _0.printStackTrace();
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
                Struct current_ = ip_.getGlobalArgument().getStruct();
                ip_.setGlobalArgumentStruct(obj_);
                String tmp_ = ip_.getNextTempVar();
                LocalVariable locVar_ = new LocalVariable();
                locVar_.setClassName(ConstClasses.resolve(className_));
                locVar_.setElement(_attribute);
                ip_.getLocalVars().put(tmp_, locVar_);
                ElUtil.processEl(varMethod_+LEFT_PAR+tmp_+GET_LOC_VAR+RIGHT_PAR, 0, _conf.toContextEl());
                ip_.getLocalVars().removeKey(tmp_);
                ip_.setGlobalArgumentStruct(current_);
                return;
            }
            ImportingPage ip_ = _conf.getLastPage();
            try {
                LocalVariable lv_ = new LocalVariable();
                lv_.setClassName(obj_.getClassName());
                lv_.setStruct(obj_);
                String nameVar_ = ip_.getNextTempVar();
                ip_.getLocalVars().put(nameVar_, lv_);
                String nameValue_ = ip_.getNextTempVar();
                lv_ = new LocalVariable();
                lv_.setClassName(_attribute.getClass().getName());
                lv_.setStruct(new Struct(_attribute));
                ip_.getLocalVars().put(nameValue_, lv_);
                String expressionLeft_ = nameVar_ + GET_LOC_VAR + _nodeContainer.getLastToken();
                String expressionRight_ = nameValue_ + GET_LOC_VAR;
                ElUtil.processAffect(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, expressionLeft_, expressionRight_, String.valueOf(EQUALS), _conf.toContextEl(), true);
                ip_.getLocalVars().removeKey(nameVar_);
                ip_.getLocalVars().removeKey(nameValue_);
            } catch (Throwable _0) {
                _conf.getLastPage().setProcessingAttribute(FormatHtml.EMPTY_STRING);
                _conf.getLastPage().setLookForAttrValue(false);
                _conf.getLastPage().setOffset(0);
                throw new SetterException(_nodeContainer.getLastToken()+FormatHtml.RETURN_LINE+_conf.joinPages());
            }
        }
        if (chg_ != null) {
            String method_ = _nodeContainer.getNodeInformation().getChanging();
            ImportingPage ip_ = _conf.getLastPage();
            ip_.setProcessingAttribute(_conf.getPrefix()+FormatHtml.ATTRIBUTE_VALUE_CHANGE_EVENT);
            ip_.setLookForAttrValue(false);
            ip_.setOffset(0);
            Struct current_ = ip_.getGlobalArgument().getStruct();
            ip_.setGlobalArgumentStruct(obj_);
            String tmp_ = ip_.getNextTempVar();
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(ValueChangeEvent.class.getName());
            locVar_.setElement(chg_);
            ip_.getLocalVars().put(tmp_, locVar_);
            ElUtil.processEl(method_+LEFT_PAR+tmp_+GET_LOC_VAR+RIGHT_PAR, 0, _conf.toContextEl());
            ip_.getLocalVars().removeKey(tmp_);
            ip_.setGlobalArgumentStruct(current_);
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
