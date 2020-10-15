package code.expressionlanguage.analyze;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.PredefinedClasses;
import code.expressionlanguage.stds.AliasPredefinedTypes;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.StringList;
import code.util.StringMap;

public class DefaultFileBuilder implements AbstractFileBuilder {
    private LgNamesContent content;
    private StringList predefinedClasses;
    private StringList predefinedInterfacesInitOrder;
    private DefaultAliasGroups defaultAliasGroups;

    public DefaultFileBuilder(LgNamesContent _content, DefaultAliasGroups _defaultAliasGroups) {
        content = _content;
        defaultAliasGroups = _defaultAliasGroups;
        predefinedClasses = new StringList();
        predefinedInterfacesInitOrder =  new StringList();
    }

    public static DefaultFileBuilder newInstance(LgNamesContent _content) {
        return new DefaultFileBuilder(_content, new DefaultAliasGroups(_content));
    }
    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords) {
        StringMap<String> files_ = new StringMap<String>();
        LgNamesContent lgCont_ = getContent();
        AliasPredefinedTypes predefTypes_ = lgCont_.getPredefTypes();
        String content_ = PredefinedClasses.getBracedIterableType(_keyWords, predefTypes_);
        String name_;
        name_ = predefTypes_.getAliasIterable();
        StringList predefinedClasses_ = predefinedClasses;
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        PrimitiveTypes primTypes_ = lgCont_.getPrimTypes();
        content_ = PredefinedClasses.getBracedIteratorType(_keyWords, predefTypes_, primTypes_.getAliasPrimBoolean());
        name_ = predefTypes_.getAliasIteratorType();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIterableTableType(_keyWords, predefTypes_);
        name_ = predefTypes_.getAliasIterableTable();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIteratorTableType(_keyWords, predefTypes_, primTypes_.getAliasPrimBoolean());
        name_ = predefTypes_.getAliasIteratorTableType();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedPairType(_keyWords, predefTypes_);
        name_ = predefTypes_.getAliasPairType();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumType(predefTypes_, lgCont_.getCoreNames(), _keyWords, lgCont_.getCharSeq().getAliasString(), primTypes_.getAliasPrimInteger());
        name_ = predefTypes_.getAliasEnumType();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumParamType(predefTypes_, _keyWords);
        name_ = predefTypes_.getAliasEnumParam();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedSeedDoubleGeneratorType(predefTypes_, _keyWords, primTypes_.getAliasPrimDouble());
        name_ = predefTypes_.getAliasSeedDoubleGenerator();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedSeedGeneratorType(predefTypes_, _keyWords, primTypes_.getAliasPrimLong());
        name_ = predefTypes_.getAliasSeedGenerator();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        StringList predefinedInterfacesInitOrder_ = predefinedInterfacesInitOrder;
        predefinedInterfacesInitOrder_.add(predefTypes_.getAliasIterable());
        predefinedInterfacesInitOrder_.add(predefTypes_.getAliasIteratorType());
        predefinedInterfacesInitOrder_.add(predefTypes_.getAliasIterableTable());
        predefinedInterfacesInitOrder_.add(predefTypes_.getAliasIteratorTableType());
        predefinedInterfacesInitOrder_.add(predefTypes_.getAliasPairType());
        predefinedInterfacesInitOrder_.add(predefTypes_.getAliasEnumParam());
        predefinedInterfacesInitOrder_.add(predefTypes_.getAliasEnumType());
        predefinedInterfacesInitOrder_.add(predefTypes_.getAliasSeedDoubleGenerator());
        predefinedInterfacesInitOrder_.add(predefTypes_.getAliasSeedGenerator());
        return files_;
    }

    public DefaultAliasGroups getDefaultAliasGroups() {
        return defaultAliasGroups;
    }

    public LgNamesContent getContent() {
        return content;
    }

    public StringList getPredefinedInterfacesInitOrder() {
        return predefinedInterfacesInitOrder;
    }

    public StringList getPredefinedClasses() {
        return predefinedClasses;
    }
}
