package code.expressionlanguage.inherits;

import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultConstantsCalculator;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.sample.CustLgNames;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.SortConstants;
import org.junit.Test;


public final class PrimitiveTypeUtilTest extends ProcessMethodCommon {

    private static final String CUST_CLASS = "pkg.CustClass";
    private static final String ARR_ARR_CUST_CLASS = "[[pkg.CustClass";
    private static final String ARR_CUST_CLASS = "[pkg.CustClass";




















    @Test
    public void cmpTypes1Test() {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        validatedStds(a_,page_,forwards_,kw_,opt_,lgName_);
        assertTrue(page_.isEmptyStdError());
        String int_ = page_.getAliasInteger();
        String nb_ = page_.getAliasNumber();
        assertEq(SortConstants.SWAP_SORT, cmpTypes(page_, int_, nb_));
    }

    private static int cmpTypes(AnalyzedPageEl _context, String _int, String _nb) {
        return AnaTypeUtil.cmpTypes(_nb,_int, _context);
    }

    @Test
    public void getSubclasses1Test() {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        validatedStds(a_,page_,forwards_,kw_,opt_,lgName_);
        assertTrue(page_.isEmptyStdError());
        StringList classes_ = new StringList(page_.getAliasInteger(), page_.getAliasNumber());
        StringList sub_ = getSubclasses(page_, classes_);
        assertEq(1, sub_.size());
        assertEq(page_.getAliasInteger(), sub_.get(0));
    }

    @Test
    public void getSubclasses2Test() {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        validatedStds(a_,page_,forwards_,kw_,opt_,lgName_);
        assertTrue(page_.isEmptyStdError());
        StringList classes_ = new StringList(page_.getAliasString(), page_.getAliasNumber());
        StringList sub_ = getSubclasses(page_, classes_);
        assertEq(2, sub_.size());
        assertEq(page_.getAliasString(), sub_.get(0));
        assertEq(page_.getAliasNumber(), sub_.get(1));
    }

    @Test
    public void getSubclasses3Test() {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        new DefaultConstantsCalculator(lgName_.getNbAlias());
        opt_.setTabWidth(tabWidth_);
        opt_.setStack(IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        Forwards forwards_ = new Forwards(lgName_, fileBuilder_, opt_);
        page_.setLogErr(forwards_.getGenerator());
        validatedStds(a_,page_,forwards_,kw_,opt_,lgName_);
        assertTrue(page_.isEmptyStdError());
        StringList classes_ = new StringList(page_.getAliasVoid(), page_.getAliasVoid());
        StringList sub_ = getSubclasses(page_, classes_);
        assertTrue(sub_.onlyOneElt());
        assertEq(page_.getAliasVoid(), sub_.get(0));
    }

    private static StringList getSubclasses(AnalyzedPageEl _context, StringList _classes) {
        return AnaTypeUtil.getSubclasses(_classes, _context);
    }

}
