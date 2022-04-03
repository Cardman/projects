package code.formathtml.util;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public abstract class NodeContainer {

    private CustList<Struct> object = new CustList<Struct>();
    private CustList<Struct> allObject = new CustList<Struct>();
    private Struct bean = NullStruct.NULL_VALUE;

    private Struct typedField = NullStruct.NULL_VALUE;
    private String varPrevName;
    private InputInfo varParamName;
    private String varName;
    private String varNameConvert;
    private boolean arrayConverter;

    private final NodeInformations nodeInformation = new NodeInformations();

    private boolean enabled;
    private boolean indexer;

    protected NodeContainer() {
    }
    public boolean eqObj(CustList<Struct> _object) {
        int size_ = _object.size();
        if (allObject.size() != size_) {
            return false;
        }
        for (int i = 0; i < size_; i++) {
            if (!allObject.get(i).sameReference(_object.get(i))) {
                return false;
            }
        }
        return true;
    }

    public Struct getUpdated() {
        return object.first();
    }
    public CustList<Struct> getStructParam() {
        return object.mid(1);
    }

    public void setStruct(CustList<Struct> _struct) {
        object = _struct;
    }

    public void setAllObject(CustList<Struct> _allObject) {
        allObject = _allObject;
    }

    public Struct getTypedStruct() {
        return typedField;
    }
    public void setTypedStruct(Struct _typedField) {
        typedField = _typedField;
    }

    public StringList getValue() {
        return nodeInformation.getValue();
    }


    public final NodeInformations getNodeInformation() {
        return nodeInformation;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _enabled) {
        enabled = _enabled;
    }

    public String getVarPrevName() {
        return varPrevName;
    }

    public void setVarPrevName(String _varPrevName) {
        varPrevName = _varPrevName;
    }

    public  InputInfo getVarParamName() {
        return varParamName;
    }

    public void setVarParamName(InputInfo _varParamName) {
        this.varParamName = _varParamName;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String _varName) {
        varName = _varName;
    }

    public String getVarNameConvert() {
        return varNameConvert;
    }

    public void setVarNameConvert(String _varNameConvert) {
        varNameConvert = _varNameConvert;
    }

    public boolean isArrayConverter() {
        return arrayConverter;
    }

    public void setArrayConverter(boolean _arrayConverter) {
        arrayConverter = _arrayConverter;
    }

    public boolean isIndexer() {
        return indexer;
    }

    public void setIndexer(boolean _indexer) {
        this.indexer = _indexer;
    }

    public Struct getBean() {
        return bean;
    }

    public void setBean(Struct _bean) {
        bean = _bean;
    }
}
