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
        AliasPredefinedTypes predefTypes = lgCont_.getPredefTypes();
        KeyWords keyWords_ = _keyWords;
        String content_ = PredefinedClasses.getBracedIterableType(keyWords_, predefTypes);
        String name_;
        name_ = predefTypes.getAliasIterable();
        StringList predefinedClasses_ = predefinedClasses;
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        PrimitiveTypes primTypes_ = lgCont_.getPrimTypes();
        content_ = PredefinedClasses.getBracedIteratorType(keyWords_, predefTypes, primTypes_.getAliasPrimBoolean());
        name_ = predefTypes.getAliasIteratorType();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIterableTableType(keyWords_, predefTypes);
        name_ = predefTypes.getAliasIterableTable();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIteratorTableType(keyWords_, predefTypes, primTypes_.getAliasPrimBoolean());
        name_ = predefTypes.getAliasIteratorTableType();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedPairType(keyWords_, predefTypes);
        name_ = predefTypes.getAliasPairType();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumType(predefTypes, lgCont_.getCoreNames(), keyWords_, lgCont_.getCharSeq().getAliasString(), primTypes_.getAliasPrimInteger());
        name_ = predefTypes.getAliasEnumType();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumParamType(predefTypes, keyWords_);
        name_ = predefTypes.getAliasEnumParam();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedSeedDoubleGeneratorType(predefTypes, keyWords_, primTypes_.getAliasPrimDouble());
        name_ = predefTypes.getAliasSeedDoubleGenerator();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedSeedGeneratorType(predefTypes, keyWords_, primTypes_.getAliasPrimLong());
        name_ = predefTypes.getAliasSeedGenerator();
        predefinedClasses_.add(name_);
        files_.put(name_, content_);
        StringList predefinedInterfacesInitOrder_ = predefinedInterfacesInitOrder;
        predefinedInterfacesInitOrder_.add(predefTypes.getAliasIterable());
        predefinedInterfacesInitOrder_.add(predefTypes.getAliasIteratorType());
        predefinedInterfacesInitOrder_.add(predefTypes.getAliasIterableTable());
        predefinedInterfacesInitOrder_.add(predefTypes.getAliasIteratorTableType());
        predefinedInterfacesInitOrder_.add(predefTypes.getAliasPairType());
        predefinedInterfacesInitOrder_.add(predefTypes.getAliasEnumParam());
        predefinedInterfacesInitOrder_.add(predefTypes.getAliasEnumType());
        predefinedInterfacesInitOrder_.add(predefTypes.getAliasSeedDoubleGenerator());
        predefinedInterfacesInitOrder_.add(predefTypes.getAliasSeedGenerator());
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
