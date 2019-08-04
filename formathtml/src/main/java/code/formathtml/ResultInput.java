package code.formathtml;

import code.expressionlanguage.errors.custom.BadElError;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exec.*;
import code.formathtml.util.BeanCustLgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class ResultInput {
    static final String TAG_PARAM = "param";
    static final String ATTRIBUTE_VALUE_SUBMIT = "message";
    static final String ATTRIBUTE_VALUE = "value";
    static final String ATTRIBUTE_QUOTED = "quoted";
    static final String ATTRIBUTE_ESCAPED = "escaped";
    static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";
    static final String ATTRIBUTE_CLASS_NAME = "className";
    static final String ATTRIBUTE_INDEX_CLASS_NAME = "indexClassName";
    static final String ATTRIBUTE_FROM = "from";
    static final String ATTRIBUTE_INIT = "init";
    static final String ATTRIBUTE_STEP = "step";
    static final String ATTRIBUTE_LABEL = "label";
    static final String ATTRIBUTE_NAME = "name";
    static final String ATTRIBUTE_PREPARE_BEAN = "prepare";
    static final String ATTRIBUTE_FORM = "form";
    static final String ATTRIBUTE_LIST = "list";
    static final String ATTRIBUTE_MAP = "map";
    static final String ATTRIBUTE_ID = "id";
    static final String ATTRIBUTE_GROUP_ID = "groupId";
    static final String ATTRIBUTE_MULTIPLE = "multiple";
    static final String ATTRIBUTE_KEY = "key";
    static final String ATTRIBUTE_VAR = "var";
    static final String ATTRIBUTE_HREF = "href";
    static final String ATTRIBUTE_COMMAND = "command";
    static final String ATTRIBUTE_ACTION = "action";
    static final String ATTRIBUTE_TO = "to";
    static final String ATTRIBUTE_EQ = "eq";
    static final String TAG_OPTION = "option";
    static final String SELECTED = "selected";
    static final String VAR_METHOD = "varMethod";
    static final String BEAN_ATTRIBUTE = "bean";
    static final String ATTRIBUTE_VALUE_CHANGE_EVENT = "valueChangeEvent";
    static final String CHECKED = "checked";
    static final String ATTRIBUTE_CONDITION = "condition";
    static final String KEY_CLASS_NAME_ATTRIBUTE = "keyClassName";
    static final String VAR_CLASS_NAME_ATTRIBUTE = "varClassName";
    static final String ATTRIBUTE_TYPE = "type";
    static final String CALL_METHOD = "$";
    static final String COMMA = ",";
    static final String SUBMIT_TYPE = "submit";
    static final String BODY_TAG = "body";
    static final String INPUT_TAG = "input";
    static final String EMPTY_STRING = "";
    static final char RIGHT_EL = '}';
    static final char LEFT_EL = '{';
    static final char QUOTE = 39;
    static final String TMP_BLOCK_TAG = "tmp";
    static final String LT_END_TAG = "</";
    static final char GT_TAG = '>';
    static final char LT_BEGIN_TAG = '<';
    static final String TAG_A = "a";
    static final String NUMBER_FORM = "n-f";
    static final String NUMBER_ANCHOR = "n-a";
    static final String NUMBER_INPUT = "n-i";
    static final String DOT = ".";
    static final String TMP_LOC = "tmpLoc";

    static final String CHECKBOX = "checkbox";

    static final String TEXT = "text";

    static final String RANGE = "range";

    static final String RADIO = "radio";

    static final String NUMBER = "number";
    static final String TEXT_AREA = "textarea";
    static final String SELECT_TAG = "select";
    static final String ATTRIBUTE_VALIDATOR = "validator";
    private static final String ATTRIBUTE_VAR_VALUE = "varValue";
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private String varName = "";
    private ClassField idField;
    public void build(Configuration _cont, RendDocumentBlock _doc, Element _read,String _varValue) {
        String name_ = _read.getAttribute(ATTRIBUTE_NAME);
        boolean st_ = _doc.isStaticContext();
        if (!name_.isEmpty()) {
            opsRead = RenderExpUtil.getAnalyzedOperations(name_, 0, _cont, Calculation.staticCalculation(st_));
            RendDynOperationNode last_ = opsRead.last();
            RendDynOperationNode res_;
            if (last_ instanceof RendIdOperation) {
                res_ = RendAffectationOperation.getIdOp((RendMethodOperation) last_);
            } else {
                res_ = last_;
            }
            RendSettableElResult settable_ = RendAffectationOperation.castDottedTo(res_);
            if (!(settable_ instanceof RendSettableFieldOperation)) {
                BadElError badEl_ = new BadElError();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                _cont.getClasses().addError(badEl_);
            } else {
                FieldInfo infoField_ = ((RendSettableFieldOperation) settable_).getFieldMetaInfo();
                if (infoField_.isStaticField()) {
                    BadElError badEl_ = new BadElError();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                    _cont.getClasses().addError(badEl_);
                }
                idField = infoField_.getClassField();
                String cl_ = ((RendSettableFieldOperation) settable_).getResultClass().getSingleNameOrEmpty();
                ClassArgumentMatching pr_;
                if (((RendSettableFieldOperation) settable_).isIntermediateDottedOperation()) {
                    pr_ = ((RendSettableFieldOperation) settable_).getPrevious();
                } else {
                    pr_ = new ClassArgumentMatching(_cont.getGlobalClass());
                }
                StringList varNames_ = new StringList();
                String varPrevLoc_ = RendBlock.lookForVar(_cont, varNames_);
                varNames_.add(varPrevLoc_);
                String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
                varNames_.add(varLoc_);
                varName = StringList.concat(varPrevLoc_,",",varLoc_);
                LocalVariable lv_ = new LocalVariable();
                String clPrev_ = pr_.getSingleNameOrEmpty();
                lv_.setClassName(clPrev_);
                _cont.getLocalVarsAna().last().addEntry(varPrevLoc_,lv_);
                lv_ = new LocalVariable();
                lv_.setClassName(cl_);
                _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);
                String accessClass_ = StringList.concat(_cont.getKeyWords().getKeyWordClasschoice(),"(",idField.getClassName(),")");
                String assessField_ = StringList.concat("(",BeanCustLgNames.sufficLocal(_cont.getContext(),varPrevLoc_),").",accessClass_,idField.getFieldName());
                String preRend_ = StringList.concat(assessField_,"=",BeanCustLgNames.sufficLocal(_cont.getContext(),varLoc_));
                opsWrite = RenderExpUtil.getAnalyzedOperations(preRend_,0,_cont,Calculation.staticCalculation(st_));
                for (String v:varNames_) {
                    _cont.getLocalVarsAna().last().removeKey(v);
                }
            }
        }
        if (_read.hasAttribute(_varValue)) {
            String value_ = _read.getAttribute(_varValue);
            opsValue = RenderExpUtil.getAnalyzedOperations(value_, 0, _cont, Calculation.staticCalculation(st_));
        }
    }

    public CustList<RendDynOperationNode> getOpsRead() {
        return opsRead;
    }

    public CustList<RendDynOperationNode> getOpsValue() {
        return opsValue;
    }

    public CustList<RendDynOperationNode> getOpsWrite() {
        return opsWrite;
    }

    public String getVarName() {
        return varName;
    }

    public ClassField getIdField() {
        return idField;
    }
}
