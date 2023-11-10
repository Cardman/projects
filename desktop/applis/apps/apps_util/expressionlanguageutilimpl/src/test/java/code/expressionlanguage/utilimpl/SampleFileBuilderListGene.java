package code.expressionlanguage.utilimpl;

import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.analyze.DefAliasFileBuilder;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.AbsAdvContextGenerator;
import code.expressionlanguage.utilcompo.CustAliases;
import code.mock.MockAliasFileBuilder;
import code.mock.MockContextGenerator;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.StringMap;

public final class SampleFileBuilderListGene implements AbsFileBuilderListGene {
    public static final String LR = "\n";
    public static final String SPACES_4 = "    ";
    @Override
    public CustList<AbsAliasFileBuilder> build(LgNamesGui _stds) {
        CustList<AbsAliasFileBuilder> builders_ = new CustList<AbsAliasFileBuilder>();
        builders_.add(new DefAliasFileBuilder());
        StringMap<String> ls_ = new StringMap<String>();
        ls_.addEntry("_", annotations(_stds.getContent(), _stds)+"public interface $core.Runnable{public void run();}");
        builders_.add(new MockAliasFileBuilder(ls_));
        return builders_;
    }
    public String annotations(LgNamesContent _content, LgNamesGui _stds) {
        String pub_ = "public";
        String cl_ = "class";
        String i_ = _content.getPrimTypes().getAliasPrimInteger();
        String l_ = _content.getPrimTypes().getAliasPrimLong();
        String m_ = _content.getReflect().getAliasMethod();
        String str_ = _content.getCharSeq().getAliasString();
        CustAliases c_ = _stds.getExecContent().getCustAliases();
        return pub_ +" "+ cl_ +" "+ c_.getAliasInfoTest() +"{"+LR
                +SPACES_4+ pub_ +" "+ i_ +" "+ c_.getAliasInfoTestCount() +";"+LR
                +SPACES_4+ pub_ +" "+SampleAtIntLgNames.THREAD+" "+ c_.getAliasInfoTestDone() +"=new"+"();"+LR
                +SPACES_4+ pub_ +" "+ i_ +" "+ c_.getAliasInfoTestNbThreads() +";"+LR
                +SPACES_4+ pub_ +" "+ l_ +" "+ c_.getAliasInfoTestCalls() +";"+LR
                +SPACES_4+ pub_ +" "+ m_ +" "+ c_.getAliasInfoTestCurrentMethod() +";"+LR
                +SPACES_4+ pub_ +" "+ str_ +" "+ c_.getAliasInfoTestCurrentParams() +";"+LR
                +"}"+LR;
    }

    @Override
    public AbsAdvContextGenerator gene(AbstractAtomicBoolean _st) {
        return new MockContextGenerator(_st);
    }
}
