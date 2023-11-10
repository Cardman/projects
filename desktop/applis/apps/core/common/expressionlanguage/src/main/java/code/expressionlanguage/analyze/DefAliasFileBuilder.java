package code.expressionlanguage.analyze;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.PredefinedClasses;
import code.expressionlanguage.stds.AliasPredefinedTypes;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.StringMap;

public final class DefAliasFileBuilder implements AbsAliasFileBuilder {
    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content) {
        StringMap<String> files_ = new StringMap<String>();
        AliasPredefinedTypes predefTypes_ = _content.getPredefTypes();
        String content_ = PredefinedClasses.getBracedIterableType(_keyWords, predefTypes_);
        String name_;
        name_ = predefTypes_.getAliasIterable();
        files_.put(name_, content_);
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        content_ = PredefinedClasses.getBracedIteratorType(_keyWords, predefTypes_, primTypes_.getAliasPrimBoolean());
        name_ = predefTypes_.getAliasIteratorType();
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIterableTableType(_keyWords, predefTypes_);
        name_ = predefTypes_.getAliasIterableTable();
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIteratorTableType(_keyWords, predefTypes_, primTypes_.getAliasPrimBoolean());
        name_ = predefTypes_.getAliasIteratorTableType();
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedPairType(_keyWords, predefTypes_);
        name_ = predefTypes_.getAliasPairType();
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumType(predefTypes_, _content.getCoreNames(), _keyWords, _content.getCharSeq().getAliasString(), primTypes_.getAliasPrimInteger());
        name_ = predefTypes_.getAliasEnumType();
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumParamType(predefTypes_, _keyWords);
        name_ = predefTypes_.getAliasEnumParam();
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedSeedDoubleGeneratorType(predefTypes_, _keyWords, primTypes_.getAliasPrimDouble());
        name_ = predefTypes_.getAliasSeedDoubleGenerator();
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedSeedGeneratorType(predefTypes_, _keyWords, primTypes_.getAliasPrimLong());
        name_ = predefTypes_.getAliasSeedGenerator();
        files_.put(name_, content_);
        return files_;
    }
}
