package code.formathtml.nat;

import code.bean.nat.*;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatRendImport;
import code.bean.nat.exec.blocks.RendBlockHelp;
import code.bean.nat.exec.opers.NatStdRefVariableOperation;
import code.bean.nat.fwd.AdvNatBlockBuilder;
import code.bean.nat.fwd.DefNatBlockBuilder;
import code.bean.nat.fwd.NatRendForwardInfos;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.EquallableBeanCoreUtil;
import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.sample.*;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.BeanPageCardsSample;
import code.scripts.pages.cards.PageCardsCommon;
import code.sml.*;
import code.util.*;
import org.junit.Test;

public final class NativeTest extends EquallableBeanCoreUtil {


    @Test
    public void bases() {
        CustBeanLgNames lgNames_ = stds();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        Configuration conf = conf("c:");
        NatAnalyzedCode init = init(lgNames_);
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, conf, lgNames_, init, new NatDualConfigurationContext());

        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        setLocalFiles(analyzingDoc_,init, conf, new NatDualConfigurationContext());
        IdMap<NatOperationNode, ValidatorInfo> lateValidators_ = new IdMap<NatOperationNode, ValidatorInfo>();
        NatRendForwardInfos.buildExec(analyzingDoc_, new StringMap<AnaRendDocumentBlock>(), lgNames_.getRenders());
        setFirst(conf, lgNames_.getRenders());
        assertNotNull(BeanNatCommonLgNames.getPairStruct(null));
        assertNotNull(BeanNatCommonLgNames.getSimpleItrStruct(null));
        assertNotNull(BeanNatCommonLgNames.getSimpleItrStruct(new ArrayStruct(0,"")));
        assertNotNull(BeanNatCommonLgNames.getLongsArray(new CustList<Longs>(new Longs(0L))));
        NatRendStackCall stack_ = new NatRendStackCall();
        stack_.addPage(new NatImportingPage());
        RendBlockHelp.processElse(null,stack_);
        RendBlockHelp.processElseIf(null,stack_);
        NatStdRefVariableOperation.getValue(null);
        PairStruct struct_ = new PairStruct("", NullStruct.NULL_VALUE, NullStruct.NULL_VALUE);
        BeanNatCommonLgNames.processString(new Argument(struct_));
        NatRendImport.beforeDisp(null, null);
        BeanNatCommonLgNames.methName("(0,1)");
        BeanNatCommonLgNames.suff("(0,1)");
        BeanNatCommonLgNames.getString(new StringStruct(""),"",new StringMap<StringMap<String>>(),"_");
        StringMap<StringMap<String>> navigation = new StringMap<StringMap<String>>();
        navigation.addEntry("", new StringMap<String>());
        BeanNatCommonLgNames.getString(new StringStruct(""),"", navigation,"");
        navigation.lastValue().addEntry("","");
        BeanNatCommonLgNames.getString(new StringStruct(""),"", navigation,"");
        BeanNatCommonLgNames.getString(NullStruct.NULL_VALUE,"", new StringMap<StringMap<String>>(),"");
        assertNotNull(new BeanPageCardsSample().self());
        PageCardsCommon.at("","");
        FullDocument fullDocument = DocumentBuilder.newDocumentBuilder().newDocument();
        Element element = fullDocument.createElement("");
        fullDocument.appendChild(element);
        PageCardsCommon.at(element,new CustList<Attr>());
        PageCardsCommon.al(0);
        PageCardsCommon.el(fullDocument,"");
        PageCardsCommon.ad(element,fullDocument.createElement(""));
        PageCardsCommon.tx(fullDocument,"");
        StringMapObjectBase s_ = new StringMapObjectBase();
        s_.put("0",0);
        s_.put("1",false);
        s_.put("2","");
        s_.put("3", Rate.zero());
        s_.put("4", new StringList());
        assertTrue(s_.containsBase("0"));
        assertTrue(s_.containsBase("1"));
        assertTrue(s_.containsBase("2"));
        assertTrue(s_.containsBase("3"));
        assertTrue(s_.containsBase("4"));
        assertFalse(s_.containsBase("5"));
        s_.removeKeyBase("1");
        assertEq(0, s_.getMapBoolean().size());
    }
////    @Test
//    public void process0FailTest() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
//        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"&quot;&quot;\" className='java.lang.String'><li>{s;length()}</li></c:for></ul></body></html>";
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
//        BeanOne bean_ = new BeanOne();
//        bean_.setLanguage("");
//        assertNotNull(bean_.getLanguage());
//        bean_.getComposite().getStrings().add("FIRST");
//        bean_.getComposite().getStrings().add("SECOND");
//        bean_.getComposite().setInteger(5);
//        assertTrue(hasNatErr(folder_, relative_, html_, bean_));
////        assertNull(bean_.db());
//    }

    @Test
    public void process0Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='!composite.strings.isEmpty()'>Not empty</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        assertNotNull(bean_.getLanguage());
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, v_));
    }

    @Test
    public void process0elTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='!composite.strings.isEmpty()'>Not empty</c:if><c:else>Empty</c:else></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, v_));
    }

    @Test
    public void process0el_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='composite.strings.isEmpty()'>Empty</c:if><c:else>Not empty</c:else></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, v_));
    }

    @Test
    public void process0elIfTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='!composite.strings.isEmpty()'>Not empty</c:if><c:elseif condition='!composite.strings.isEmpty()'>Empty</c:elseif></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process0elIf_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='composite.strings.isEmpty()'>Empty</c:if><c:elseif condition='!composite.strings.isEmpty()'>Not empty</c:elseif></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process0elIf__Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='composite.strings.isEmpty()'>Empty</c:if><c:elseif condition='!composite.strings.isEmpty()'>Not empty</c:elseif><c:else>Empty</c:else></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process0elIf___Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='composite.strings.isEmpty()'>Empty</c:if><c:elseif condition='composite.strings.isEmpty()'>Empty</c:elseif><c:else>Not empty</c:else></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process00Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><c:if condition='composite.strings.isEmpty()'>Empty</c:if></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        setInteger(v_);
        assertEq("<html><body>Empty</body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='java.lang.String'><li>{length(s)}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process_1_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='java.lang.String'><li>{length2(s,s,s)}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process1SecTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings2\" className='java.lang.String'><li>{length(s)}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process1Sec2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings2\"><li>{length(s)}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process1IndTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='java.lang.String'><li>{(([s]))}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><li>0</li><li>1</li></ul></body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process1Ind2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='java.lang.String'><li>{([s])}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><li>0</li><li>1</li></ul></body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process1_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\"><li><c:if condition='isEmpty(s)'>EMPTY</c:if></li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><li>EMPTY</li><li/></ul></body></html>", getNatRes(folder_, relative_, html_, v_));
    }

    @Test
    public void process2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='$var'><li>{length(s)}</li></c:for></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, v_));
    }

    @Test
    public void process2_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul>{(composite.sum(composite.integer,composite.integer))}</ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul>10</ul></body></html>", getNatRes(folder_, relative_, html_, v_));
    }

    @Test
    public void process2_3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=<a c:command=''$composite.sum({0},{0},{0})''>{0}</a>";
        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><a c:command=\"$bean_one.composite.sum(5,5,5)\" href=\"\" n-a=\"0\">5</a></ul></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }

    @Test
    public void process2_3_0Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=<a c:command=''$validateStrings()''>{0}</a>";
        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><a c:command=\"$bean_one.validateStrings()\" href=\"\" n-a=\"0\">5</a></ul></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }

    @Test
    public void process2_3_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=<a c:command=''composite''>{0}</a>";
        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><a c:command=\"composite\" href=\"\" n-a=\"0\">5</a></ul></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
    @Test
    public void process__2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean='bean_one'><body><ul>{((composite.sum(composite.integer,composite.integer)))}</ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul>10</ul></body></html>", getNatRes(folder_, relative_, html_, v_));
    }
    @Test
    public void process2__Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one={0}";
        String html_ = "<html c:bean='bean_one'><body><ul><c:message value='msg_example,one'><param value='composite.integer'/></c:message></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul>5</ul></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
    @Test
    public void process2_Sub_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one={0}";
        String html_ = "<html c:bean='bean_one'><body><input type='submit' value='OK'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><input type=\"submit\" value=\"OK\"/></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
    @Test
    public void process2_Id_quote_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one={0}";
        String html_ = "<html c:bean='bean_one'><body><input type='submit' c:groupId=\"{composite.integer}\" value='OK'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><input type=\"submit\" c:groupId=\"5\" value=\"OK\"/></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
    @Test
    public void process2_Id_quote2_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one={0}";
        String html_ = "<html c:bean='bean_one'><body>''a''</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body>'a'</body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
    @Test
    public void process_2_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one={0}";
        String html_ = "<html c:bean='bean_one'><body><ul><a c:command='$composite.sum({composite.integer},{composite.integer})'/></ul></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><ul><a c:command=\"$bean_one.composite.sum(5,5)\" href=\"\" n-a=\"0\"/></ul></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
    @Test
    public void process2_Rd_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one={0}";
        String html_ = "<html c:bean='bean_one'><body><input type='radio' name='composite.integer' c:varValue='composite.integer'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><input type=\"radio\" name=\"composite.integer\" value=\"5\" checked=\"checked\"/></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
    @Test
    public void process2_Sub_Spec_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Click";
        String html_ = "<html c:bean='bean_one'><body><c:submit message='msg_example,one'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><input value=\"Click\" type=\"submit\"/></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
    @Test
    public void process2_Tit_Spec_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Click";
        String html_ = "<html c:bean='bean_one'><body><c:a value='msg_example,one'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><a title=\"Click\"/></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
    @Test
    public void process1ImgTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Click";
        String html_ = "<html c:bean='bean_one'><body><img/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><img/></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }

    @Test
    public void process2ImgTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Click";
        String html_ = "<html c:bean='bean_one'><body><c:img/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><img/></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
    @Test
    public void process2LkTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Click";
        String html_ = "<html c:bean='bean_one'><body><link/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><link/></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
    @Test
    public void process2SpTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Click";
        String html_ = "<html c:bean='bean_one'><body><span/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        assertEq("<html><body><span/></body></html>", getNatRes(folder_, relative_, html_, files_, v_));
    }
//    @Test
//    public void process1FailTest() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
//        String html_ = "<html c:bean='bean_one'><body><ul><c:for var=\"s\" list=\"composite.strings\" className='java.lang.Integer'><li>{s.length()}</li></c:for></ul></body></html>";
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
//        BeanOne bean_ = new BeanOne();
//        bean_.getComposite().getStrings().add("FIRST");
//        bean_.getComposite().getStrings().add("SECOND");
//        bean_.getComposite().setInteger(5);
//        assertTrue(hasNatErr(folder_, relative_, html_, bean_));
//    }

    @Test
    public void process3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><table><c:for key=\"k\" keyClassName=\"java.lang.String\" value=\"v\" varClassName=\"$int\" map=\"tree\"><tr><td>{k}</td><td>{v}</td></tr></c:for></table></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        v_.getTree().put("ONE", 1);
        v_.getTree().put("TWO", 2);
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", getNatRes(folder_, relative_, html_, v_));
    }

    @Test
    public void process5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><table><c:for key=\"k\" value=\"v\" map=\"tree\" keyClassName='java.lang.Object' varClassName='java.lang.Object'><tr><td>{k}</td><td>{v}</td></tr></c:for></table></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        v_.getTree().put("ONE", 1);
        v_.getTree().put("TWO", 2);
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", getNatRes(folder_, relative_, html_, v_));
    }

    @Test
    public void process6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body><table><c:for key=\"k\" value=\"v\" map=\"tree\" keyClassName='java.lang.Object' varClassName='java.lang.Object'><c:for key=\"l\" value=\"w\" map=\"tree\" keyClassName='java.lang.Object' varClassName='java.lang.Object'><tr><td>{k}</td><td>{v}</td><td>{l}</td><td>{w}</td></tr></c:for></c:for></table></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        BeanOne bean_ = new BeanOne();
        SampleBeanStruct v_ = init(bean_);
        getStrings(v_).add("FIRST");
        getStrings(v_).add("SECOND");
        setInteger(v_);
        v_.getTree().put("ONE", 1);
        v_.getTree().put("TWO", 2);
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td><td>ONE</td><td>1</td></tr><tr><td>ONE</td><td>1</td><td>TWO</td><td>2</td></tr><tr><td>TWO</td><td>2</td><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td><td>TWO</td><td>2</td></tr></table></body></html>", getNatRes(folder_, relative_, html_, v_));
    }

    @Test
    public void process7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"goToNullPage\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><input type='text' name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<Document> docs_ = new StringMap<Document>();
        StringMap<String> files_ = new StringMap<String>();
        CustBeanLgNames lgNames_ = new CustBeanLgNames();
        lgNames_.getValidators().addEntry("my_val",new MyValidator());
        basicStandards(lgNames_);
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        docs_.addEntry("page1.html",DocumentBuilder.parseSax(html_));
        docs_.addEntry("page2.html",DocumentBuilder.parseSax(htmlTwo_));
        Navigation n_ = new Navigation();
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(lgNames_, new SampleNativeInit());
        Configuration session_ = new Configuration();
        NatDualConfigurationContext d_ = nat_.getDualConfigurationContext(session_);
        Forwards forwards_ = nat_.getForwards();
        d_.init(session_);
        n_.setSession(session_);
        n_.setFiles(files_);
        lgNames_.setupAll(docs_,n_, n_.getSession(), new DefNatBlockBuilder(), d_);
        lgNames_.initializeRendSessionDoc(n_);
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
    }

    @Test
    public void process7_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"page3.html\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><input type='text' name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<Document> docs_ = new StringMap<Document>();
        StringMap<String> files_ = new StringMap<String>();
        CustBeanLgNames lgNames_ = new CustBeanLgNames();
        lgNames_.getValidators().addEntry("my_val",new MyValidator());
        basicStandards(lgNames_);
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        docs_.addEntry("page1.html",DocumentBuilder.parseSax(html_));
        docs_.addEntry("page2.html",DocumentBuilder.parseSax(htmlTwo_));
        Navigation n_ = new Navigation();
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(lgNames_, new SampleNativeInit());
        Configuration session_ = new Configuration();
        NatDualConfigurationContext d_ = nat_.getDualConfigurationContext(session_);
        Forwards forwards_ = nat_.getForwards();
        d_.init(session_);
        n_.setSession(session_);
        n_.setFiles(files_);
        lgNames_.setupAll(docs_,n_, n_.getSession(), new DefNatBlockBuilder(), d_);
        lgNames_.initializeRendSessionDoc(n_);
        lgNames_.processRendAnchorRequest(DocumentBuilder.getFirstElementByAttribute(n_.getDocument(), n_.getSession().getRendKeyWords().getAttrNa(), Long.toString(n_.getHtmlPage().getUrl())), n_);
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
    }

    @Test
    public void processOtherTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"page1.html\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><input type='text' name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<Document> docs_ = new StringMap<Document>();
        StringMap<String> files_ = new StringMap<String>();
        CustBeanLgNames lgNames_ = new CustBeanLgNames();
        lgNames_.getValidators().addEntry("my_val",new MyValidator());
        basicStandards(lgNames_);
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        docs_.addEntry("page1.html",DocumentBuilder.parseSax(html_));
        docs_.addEntry("page2.html",DocumentBuilder.parseSax(htmlTwo_));
        Navigation n_ = new Navigation();
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(lgNames_, new SampleNativeInit());
        Configuration session_ = new Configuration();
        NatDualConfigurationContext d_ = nat_.getDualConfigurationContext(session_);
        Forwards forwards_ = nat_.getForwards();
        d_.init(session_);
        n_.setSession(session_);
        n_.setFiles(files_);
        lgNames_.setupAll(docs_,n_, n_.getSession(), new DefNatBlockBuilder(), d_);
        lgNames_.initializeRendSessionDoc(n_);
        lgNames_.processRendAnchorRequest(DocumentBuilder.getFirstElementByAttribute(n_.getDocument(), n_.getSession().getRendKeyWords().getAttrNa(), Long.toString(n_.getHtmlPage().getUrl())), n_);
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
    }

    @Test
    public void processOther2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"page1.html\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><input type='text' name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<Document> docs_ = new StringMap<Document>();
        StringMap<String> files_ = new StringMap<String>();
        CustBeanLgNames lgNames_ = new CustBeanLgNames();
        lgNames_.getValidators().addEntry("my_val",new MyValidator());
        basicStandards(lgNames_);
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        docs_.addEntry("page1.html",DocumentBuilder.parseSax(html_));
        docs_.addEntry("page2.html",DocumentBuilder.parseSax(htmlTwo_));
        Navigation n_ = new Navigation();
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(lgNames_, new SampleNativeInit());
        Configuration session_ = new Configuration();
        NatDualConfigurationContext d_ = nat_.getDualConfigurationContext(session_);
        Forwards forwards_ = nat_.getForwards();
        d_.init(session_);
        n_.setSession(session_);
        n_.setFiles(files_);
        n_.getSession().setFirstUrl("page1.html");
        lgNames_.setupAll(docs_,n_, n_.getSession(), new DefNatBlockBuilder(), d_);
        lgNames_.initializeRendSessionDoc(n_);
        n_.getHtmlPage().setUrl(0);
        lgNames_.processRendAnchorRequest(DocumentBuilder.getFirstElementByAttribute(n_.getDocument(), n_.getSession().getRendKeyWords().getAttrNa(), Long.toString(n_.getHtmlPage().getUrl())), n_);
        assertEq("<html><body>HEAD<a c:command=\"page1.html\" href=\"\" n-a=\"0\"/></body></html>", n_.getHtmlText());
    }
    @Test
    public void process_7_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"goToNullPage\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><input type='text' name=\"composite.string\" c:varValue=\"composite.string\"/></form></body></html>";
        StringMap<Document> docs_ = new StringMap<Document>();
        StringMap<String> files_ = new StringMap<String>();
        CustBeanLgNames lgNames_ = new CustBeanLgNames();
        lgNames_.getValidators().addEntry("my_val",new MyValidator());
        basicStandards(lgNames_);
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        docs_.addEntry("page1.html",DocumentBuilder.parseSax(html_));
        docs_.addEntry("page2.html",DocumentBuilder.parseSax(htmlTwo_));
        Navigation n_ = new Navigation();
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(lgNames_, new SampleNativeInit());
        Configuration session_ = new Configuration();
        NatDualConfigurationContext d_ = nat_.getDualConfigurationContext(session_);
        Forwards forwards_ = nat_.getForwards();
        d_.init(session_);
        n_.setSession(session_);
        n_.setFiles(files_);
        lgNames_.setupAll(docs_,n_, n_.getSession(), new DefNatBlockBuilder(), d_);
        lgNames_.initializeRendSessionDoc(n_);
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.composite.string\" n-i=\"0\" value=\"\"/></form></body></html>", n_.getHtmlText());
    }

//    @Test
//    public void process7_Test() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
//        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"goToNullPage\" href=\"\"/></body></html>";
//        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><input type='text' name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
//        StringMap<Document> docs_ = new StringMap<Document>();
//        StringMap<String> files_ = new StringMap<String>();
//        CustBeanLgNames lgNames_ = new CustBeanLgNames();
//        lgNames_.getValidators().addEntry("my_val",new MyValidator());
//        basicStandards(lgNames_);
//        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
//        files_.put("page1.html", html_);
//        files_.put("page2.html", htmlTwo_);
//        docs_.addEntry("page1.html",DocumentBuilder.parseSax(html_));
//        docs_.addEntry("page2.html",DocumentBuilder.parseSax(htmlTwo_));
//        String xmlConf_ = confCom();
//        Navigation n_ = new Navigation();
//        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(lgNames_, null);
//        DualAnalyzedContext du_ = n_.loadConfiguration(xmlConf_, "", lgNames_, null, nat_);
//        n_.setFiles(files_);
//        lgNames_.setupAll(docs_,n_, n_.getSession(), du_, new DefNatBlockBuilder());
//        ContextEl generate_ = du_.getForwards().generate(new Options());
//        n_.initializeRendSession(generate_, du_.getStds(), new NatRendStackCall(InitPhase.NOTHING, generate_));
//        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
//    }
    @Test
    public void processNatTest() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"goToNullPage\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><input type='text' name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<Document> docs_ = new StringMap<Document>();
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        docs_.addEntry("page1.html",DocumentBuilder.parseSax(html_));
        docs_.addEntry("page2.html",DocumentBuilder.parseSax(htmlTwo_));
        Configuration conf_ = conf("c");
        CustBeanLgNames lgNames_ = stds();
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        init(lgNames_);
        conf_.setFirstUrl("page2.html");
        dual_.getRenderFiles().add("page1.html");
        dual_.getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanTwo");
        conf_.getBeansInfos().addEntry("bean_two",i_);
        dual_.init(conf_);
        Navigation n_ = new Navigation();
        setSess(conf_, n_);
        n_.setFiles(files_);
        lgNames_.setupAll(docs_,n_, n_.getSession(), new DefNatBlockBuilder(), dual_);
        lgNames_.initializeRendSessionDoc(n_);
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
        assertEq("page2.html", lgNames_.getCurrentUrl());
    }
    @Test
    public void processNat_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"goToNullPage\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><input type='text' name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<Document> docs_ = new StringMap<Document>();
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        docs_.addEntry("page1.html",DocumentBuilder.parseSax(html_));
        docs_.addEntry("page2.html",DocumentBuilder.parseSax(htmlTwo_));
        Configuration conf_ = conf("c");
        CustBeanLgNames lgNames_ = stds();
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        init(lgNames_);
        conf_.setFirstUrl("page2.html");
        dual_.getRenderFiles().add("page1.html");
        dual_.getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanTwo");
        conf_.getBeansInfos().addEntry("bean_two",i_);
        dual_.init(conf_);
        Navigation n_ = new Navigation();
        setSess(conf_, n_);
        n_.setFiles(files_);
        lgNames_.setupAll(docs_,n_, n_.getSession(), new DefNatBlockBuilder(), dual_);
        lgNames_.initializeRendSessionDoc(n_);
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
        assertEq("page2.html", lgNames_.getCurrentUrl());
    }

    private static CustBeanLgNames stds() {
        CustBeanLgNames lgNames_ = new CustBeanLgNames();
        basicStandards(lgNames_);
        return lgNames_;
    }

    private static NatAnalyzedCode init(CustBeanLgNames lgNames_) {
        lgNames_.buildBeans();
        lgNames_.buildOther();
        return page(lgNames_);
    }

    private static NatAnalyzedCode page(CustBeanLgNames lgNames_) {
        NatAnalyzedCode page_ = NatAnalyzedCode.setInnerAnalyzing();
        page_.setStds(lgNames_);
        RendBlockHelp.setupOverrides(page_.getStds());
        return page_;
    }

//    @Test
//    public void processNav1Test() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
//        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"$goToNullPage\" href=\"\"/></body></html>";
//        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"$go\"></form></body></html>";
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
//        files_.put("page1.html", html_);
//        files_.put("page2.html", htmlTwo_);
//        BeanOne bean_ = new BeanOne();
//        BeanFive beanTwo_ = new BeanFive();
//        NativeAnalyzedTestConfiguration conf_ = contextElSec();
//
//
//        putBean(bean_, "bean_one", conf_.getAdv().getBeans());
//        putBean(beanTwo_, "bean_two", conf_.getAdv().getBeans());
//        setupNative2(folder_, relative_, conf_);
//
//
//        conf_.setNavigation(new StringMap<StringMap<String>>());
//        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
//        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
//        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
//        Navigation nav_ = newNavigation(conf_);
//        ((CustBeanLgNames)conf_.getAdv()).setDataBase(new Composite());
//        nav_.setLanguage(locale_);
//        setSess(conf_, nav_);
//        nav_.setFiles(files_);
//        conf_.getDual().getRenderFiles().add("page1.html");
//        conf_.getDual().getRenderFiles().add("page2.html");
//        initSessionNat(conf_,nav_);
//        assertEq("page2.html", lgNames_.getCurrentUrl());
//        nav_.getHtmlPage().setUrl(0);
//        form(conf_, nav_);
//        setupBeansAfter(conf_);
//        assertEq("page1.html", lgNames_.getCurrentUrl());
//        assertEq("bean_one", lgNames_.getCurrentBeanName());
//        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
//        assertSame(getBean(conf_, "bean_one").getForms(), getBeanFive(conf_, "bean_two").getForms());
//        assertEq("",nav_.getTitle());
//        assertEq("",nav_.getReferenceScroll());
//
//    }

    @Test
    public void processNav_1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"$goToNullPage\" href=\"\"/></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"$goToNullPage\" href=\"\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
//        BeanFive beanTwo_ = new BeanFive();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_one", init(bean_), lgNames_);
//        putBean(beanTwo_, conf_, "bean_two");
        setupNative2(folder_, relative_, config_, dual_);


        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
//        new Composite();
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        dual_.getRenderFiles().add("page2.html");
        initSessionNat(nav_, lgNames_, dual_);
        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
        assertEq("page2.html", lgNames_.getCurrentUrl());

    }


//    @Test
//    public void processNav1_Test() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
//        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"$goToNullPage\" href=\"\"/></body></html>";
//        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"$go\"></form></body></html>";
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
//        files_.put("page1.html", html_);
//        files_.put("page2.html", htmlTwo_);
//        BeanOne bean_ = new BeanOne();
//        BeanFive beanTwo_ = new BeanFive();
//        NativeAnalyzedTestConfiguration conf_ = contextElSec();
//
//
//        StringMap<String> beans_ = new StringMap<String>();
//        beans_.addEntry("bean_one","code.formathtml.classes.BeanOne");
//        beans_.addEntry("bean_two","code.formathtml.classes.BeanFive");
////        putBean(bean_, "bean_one", beans_);
////        putBean(beanTwo_, "bean_two", beans_);
//        setupNative2(folder_, relative_, conf_);
//
//
//        conf_.setNavigation(new StringMap<StringMap<String>>());
//        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
//        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
//        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
//        Navigation nav_ = newNavigation(conf_);
//        ((CustBeanLgNames)conf_.getAdv()).setDataBase(new Composite());
//        nav_.setLanguage(locale_);
//        setSess(conf_, nav_);
//        nav_.setFiles(files_);
//        conf_.getDual().getRenderFiles().add("page1.html");
//        conf_.getDual().getRenderFiles().add("page2.html");
//        initSessionDoc(conf_,nav_, beans_);
//        assertEq("page2.html", lgNames_.getCurrentUrl());
//        nav_.getHtmlPage().setUrl(0);
//        form(conf_, nav_);
//        setupBeansAfter(conf_);
//        assertEq("page1.html", lgNames_.getCurrentUrl());
//        assertEq("bean_one", lgNames_.getCurrentBeanName());
//        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
//        assertSame(getBean(conf_, "bean_one").getForms(), getBeanFive(conf_, "bean_two").getForms());
//        assertEq("",nav_.getTitle());
//        assertEq("",nav_.getReferenceScroll());
//
//    }


    @Test
    public void processNav2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:import page=\"page2.html\"><a/><c:package name=\"code.formathtml.classes\"><a/><c:class name=\"BeanTwo\"><a/><c:field prepare=\"$intern.typedString=message\"><a/></c:field></c:class></c:package></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setBaseForms(new StringMapObjectBase());
        SampleBeanStruct v1_ = init(bean_);
        v1_.getTree().put("ONE", 1);
        v1_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setBaseForms(new StringMapObjectBase());
        SampleBeanStruct v2_ = init(beanTwo_);
        v2_.setTypedString("TITLE");
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        NatAnalyzedCode init_ = init(lgNames_);

        config_.setFirstUrl("page1.html");
        v1_.getForms().put("key", "sample_value");
        setupNative(folder_, relative_, dual_);
        preinit(lgNames_, config_);


        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(lgNames_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithTwoNativeBean(html_, htmlTwo_, v1_, v2_, config_, analyzingDoc_, lgNames_, init_, dual_);
        assertEq("<html><body><a href=\"\" c:command=\"go\" n-a=\"0\">Test {0}2</a></body></html>", getSampleRes(rendDocumentBlock_, config_, lgNames_));
//        assertEq(1, beanTwo_.getForms().size());
//        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", v2_.getForms().getValStr("key"));
    }

    @Test
    public void processNav3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a c:command=\"$go\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:import page=\"page2.html\" keepfields=\"y\"><c:package name=\"code.formathtml.classes\"><c:class name=\"BeanTwo\"><c:field prepare=\"$intern.typedString=message\"></c:field></c:class></c:package><c:form form=\"key\"/></c:import></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><a href=\"DELETE\" c:command=\"go\">{typedString}</a><c:message value='msg_example,two'/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        bean_.setBaseForms(new StringMapObjectBase());
        SampleBeanStruct v1_ = init(bean_);
        v1_.getTree().put("ONE", 1);
        v1_.getTree().put("TWO", 2);
        v1_.getForms().put("key", "sample_value");
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setBaseForms(new StringMapObjectBase());
        SampleBeanStruct v2_ = init(beanTwo_);
        v2_.setTypedString("TITLE");
        CustBeanLgNames lgNames_ = stds();
        NatAnalyzedCode init_ = init(lgNames_);
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        setupNative(folder_, relative_, dual_);
        Configuration configuration_ = conf("c:");
        preinit(lgNames_, configuration_);
        setFiles(files_, configuration_);

        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(lgNames_);
        RendDocumentBlock rendDocumentBlock_ = buildRendWithTwoNativeBean(html_, htmlTwo_, v1_, v2_, configuration_, analyzingDoc_, lgNames_, init_, dual_);
        String render_ = getSampleRes(rendDocumentBlock_, configuration_, lgNames_);
        assertEq("<html><body><a href=\"\" c:command=\"go\" n-a=\"0\">Test {0}2</a>Description <a c:command=\"$bean_two.go\" href=\"\" n-a=\"1\">two</a></body></html>", render_);
//        assertEq(1, beanTwo_.getForms().size());
//        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", v2_.getForms().getValStr("key"));
    }

    @Test
    public void processNav4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "err={0} is not a no zero rate";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body><form c:command=\"$goToPage\"><input id=\"txt\" type=\"text\" name=\"selectedString\" c:varValue=\"selectedString\" c:validator=\"rate_val\"/><span c:for=\"txt\" c:valueMessage='msg_example,err'/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanOne beanTwo_ = new BeanOne();
        CustBeanLgNames lgNames_ = stds();
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        Configuration config_ = conf("c:");


        putBean("bean_one", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_one.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_one.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("ONE_TWO");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
        assertEq("page1.html", lgNames_.getCurrentUrl());
        assertEq("bean_one", lgNames_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_one.goToPage\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"text\" name=\"bean_one.selectedString\" c:validator=\"rate_val\" n-i=\"0\" value=\"ONE_TWO\"/><span c:for=\"txt\" c:valueMessage=\"msg_example,err\">ONE_TWO is not a no zero rate</span></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBean(conf_, "bean_one");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(0, map_.size());
//        assertEq(0, getBean(conf_, "bean_one").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "err=err";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input id=\"txt\" type=\"number\" name=\"typedInt\" c:varValue=\"typedInt\" c:validator=\"rate_val\"/><span c:for=\"txt\" c:valueMessage='msg_example,err'/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        lgNames_.getValidators().addEntry("rate_val",new MyValidator());
        dual_.getRenderFiles().add("page1.html");
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("ONE_TWO");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
        assertEq("page1.html", lgNames_.getCurrentUrl());
        assertEq("bean_two", lgNames_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"ONE_TWO\"/><span c:for=\"txt\" c:valueMessage=\"msg_example,err\">err</span></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(0, map_.size());
//        assertEq(0, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "err=err";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input id=\"txt\" type=\"number\" name=\"typedInt\" c:varValue=\"typedInt\" c:validator=\"rate_val\"/><span c:for=\"txt\" c:valueMessage='msg_example,err'/><input id=\"txt2\" type=\"text\" name=\"typedString\" c:varValue=\"typedString\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        lgNames_.getValidators().addEntry("rate_val",new MyValidator());
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("ONE_TWO");
        ni_.setValue(values_);
        nc_ = containers_.getVal(1L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("TYPED_STRING");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
        assertEq("page1.html", lgNames_.getCurrentUrl());
        assertEq("bean_two", lgNames_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"ONE_TWO\"/><span c:for=\"txt\" c:valueMessage=\"msg_example,err\">err</span><input id=\"txt2\" type=\"text\" name=\"bean_two.typedString\" n-i=\"1\" value=\"TYPED_STRING\"/></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(0, map_.size());
//        assertEq(0, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "err=Err";
        String html_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input id=\"txt\" type=\"number\" name=\"typedInt\" c:varValue=\"typedInt\" c:validator=\"rate_val\"/><span c:for=\"txt\" c:valueMessage='msg_example,err'/><c:import page='page2.html'/></form></body></html>";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body><input id=\"txt2\" type=\"text\" name=\"selectedString\" c:varValue=\"selectedString\"/></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        files_.put("page2.html", htmlTwo_);
        BeanOne beanOne_ = new BeanOne();
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        putBean("bean_one", init(beanOne_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        dual_.getRenderFiles().add("page2.html");
        lgNames_.getValidators().addEntry("rate_val",new MyValidator());
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("ONE_TWO");
        ni_.setValue(values_);
        nc_ = containers_.getVal(1L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("ONE");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
        assertEq("page1.html", lgNames_.getCurrentUrl());
        assertEq("bean_two", lgNames_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"ONE_TWO\"/><span c:for=\"txt\" c:valueMessage=\"msg_example,err\">Err</span><input id=\"txt2\" type=\"text\" name=\"bean_one.selectedString\" n-i=\"1\" value=\"ONE\"/></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(0, map_.size());
//        assertEq(0, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><c:select id=\"combo\" default=\"\" name=\"selectedString\" varValue=\"selectedString\" map=\"map\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_one", init(bean_), lgNames_);
        setupNative2(folder_, relative_, config_, dual_);


        dual_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(config_);
//        new Composite();
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page2.html");
        initSessionNat(nav_, lgNames_, dual_);
        assertEq("page2.html", lgNames_.getCurrentUrl());
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><select name=\"bean_one.selectedString\" n-i=\"0\"><option value=\"ONE\" selected=\"selected\">1</option><option value=\"TWO\">2</option></select></form></body></html>", nav_.getHtmlText());

    }

//    @Test
//    public void processNav9Test() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
//        String htmlTwo_ = "<html c:bean=\"bean_one\"><body>{invokeMethod(0)}</body></html>";
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
//        files_.put("page2.html", htmlTwo_);
//        BeanOne bean_ = new BeanOne();
//        NativeAnalyzedTestConfiguration conf_ = contextElSec();
//
//
//        putBean(bean_, "bean_one", conf_.getAdv().getBeans());
//        setupNative2(folder_, relative_, conf_);
//
//
//        conf_.setNavigation(new StringMap<StringMap<String>>());
//        Navigation nav_ = newNavigation(conf_);
//        ((CustBeanLgNames)conf_.getAdv()).setDataBase(new Composite());
//        nav_.setLanguage(locale_);
//        setSess(conf_, nav_);
//        nav_.setFiles(files_);
//        conf_.getDual().getRenderFiles().add("page2.html");
//        initSessionNat(conf_,nav_);
//        assertEq("page2.html", lgNames_.getCurrentUrl());
//        assertEq("<html><body>returned value</body></html>", nav_.getHtmlText());
//
//    }

//    @Test
//    public void processNav10Test() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
//        String htmlTwo_ = "<html c:bean=\"bean_one\"><body>{invokeMethod(0)}</body></html>";
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
//        files_.put("page2.html", htmlTwo_);
//        BeanOne bean_ = new BeanOne();
//        NativeAnalyzedTestConfiguration conf_ = contextElSec();
//
//
//        putBean(bean_, "bean_one", conf_.getAdv().getBeans());
//        setupNative2(folder_, relative_, conf_);
//
//
//        conf_.setNavigation(new StringMap<StringMap<String>>());
//        Navigation nav_ = newNavigation(conf_);
//        ((CustBeanLgNames)conf_.getAdv()).setDataBase(new Composite());
//        nav_.setLanguage(locale_);
//        setSess(conf_, nav_);
//        nav_.setFiles(files_);
//        conf_.getDual().getRenderFiles().add("page2.html");
//        initSessionNat(conf_,nav_);
//        assertEq("page2.html", lgNames_.getCurrentUrl());
//        assertEq("<html><body>returned value</body></html>", nav_.getHtmlText());
//
//    }

//    @Test
//    public void processNav11Test() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
//        String htmlTwo_ = "<html c:bean=\"bean_one\"><body>{getList(5)}</body></html>";
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
//        files_.put("page2.html", htmlTwo_);
//        BeanOne bean_ = new BeanOne();
//        NativeAnalyzedTestConfiguration conf_ = contextElSec();
//
//
//        putBean(bean_, "bean_one", conf_.getAdv().getBeans());
//        setupNative2(folder_, relative_, conf_);
//
//
//        conf_.setNavigation(new StringMap<StringMap<String>>());
//        Navigation nav_ = newNavigation(conf_);
//        ((CustBeanLgNames)conf_.getAdv()).setDataBase(new Composite());
//        nav_.setLanguage(locale_);
//        setSess(conf_, nav_);
//        nav_.setFiles(files_);
//        conf_.getDual().getRenderFiles().add("page2.html");
//        initSessionNat(conf_,nav_);
//        assertEq("page2.html", lgNames_.getCurrentUrl());
//        assertEq("<html><body>5</body></html>", nav_.getHtmlText());
//
//    }

    @Test
    public void processNav12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input type=\"number\" name=\"nullableInt\" c:varValue=\"nullableInt\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("10");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
        assertEq("page1.html", lgNames_.getCurrentUrl());
        assertEq("bean_two", lgNames_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.nullableInt\" n-i=\"0\" value=\"10\"/></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(8, map_.size());
//        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav12_est() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input type=\"number\" name=\"nullableInt\" c:varValue=\"nullableInt\"/><input type=\"number\" name=\"nullableInt\" c:varValue=\"nullableInt\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("10");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
//        assertEq("page1.html", lgNames_.getCurrentUrl());
//        assertEq("bean_two", lgNames_.getCurrentBeanName());
//        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.nullableInt\" n-i=\"0\" value=\"10\"/></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(8, map_.size());
//        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav_12_est() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input type=\"number\" name=\"nullableInt\" c:varValue=\"nullableInt\"/><input type=\"number\" name=\"nullableInt2\" c:varValue=\"nullableInt2\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("10");
        ni_.setValue(values_);
        nc_ = containers_.getVal(1L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("10");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
//        assertEq("page1.html", lgNames_.getCurrentUrl());
//        assertEq("bean_two", lgNames_.getCurrentBeanName());
//        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.nullableInt\" n-i=\"0\" value=\"10\"/></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(8, map_.size());
//        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }
    @Test
    public void processNav13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input type=\"number\" name=\"nullableInt\" c:varValue=\"nullableInt\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
        assertEq("page1.html", lgNames_.getCurrentUrl());
        assertEq("bean_two", lgNames_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.nullableInt\" n-i=\"0\" value=\"0\"/></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(8, map_.size());
//        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input type=\"checkbox\" name=\"checked\" c:varValue=\"checked\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("on");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
        assertEq("page1.html", lgNames_.getCurrentUrl());
        assertEq("bean_two", lgNames_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"checkbox\" name=\"bean_two.checked\" n-i=\"0\" checked=\"checked\"/></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(8, map_.size());
//        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input type=\"number\" name=\"nullableInt\" c:varValue=\"nullableInt\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
        assertEq("page1.html", lgNames_.getCurrentUrl());
        assertEq("bean_two", lgNames_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.nullableInt\" n-i=\"0\" value=\"0\"/></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(8, map_.size());
//        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());
        init(beanTwo_).getClassName(null);
//        assertNotNull(new SampleBeanStruct(beanTwo_).getInstance());
        assertEq("0",new LgIntStruct(LgInt.zero(),"").getDisplayedString().getInstance());
    }
//    @Test
//    public void processNav15Test() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
//        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input c:className='code.formathtml.classes.Rate' type=\"text\" name=\"rate\" c:varValue=\"rate\"/></form></body></html>";
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
//        files_.put("page1.html", htmlTwo_);
//        BeanTwo beanTwo_ = new BeanTwo();
//        NativeAnalyzedTestConfiguration conf_ = contextElSec();
//
//
//        putBean(beanTwo_, "bean_two", conf_.getAdv().getBeans());
//        setupVal(folder_, relative_, conf_);
//        conf_.setNavigation(new StringMap<StringMap<String>>());
//        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
//        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
//        Navigation nav_ = newNavigation(conf_);
//        nav_.setLanguage(locale_);
//        setSess(conf_, nav_);
//        nav_.setFiles(files_);
//        conf_.getDual().getRenderFiles().add("page1.html");
//        initSessionNat(conf_,nav_);
//        HtmlPage htmlPage_ = nav_.getHtmlPage();
//        LongMap<LongTreeMap<NodeContainer>> containersMap_;
//        containersMap_ = htmlPage_.getContainers();
//        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
//        NodeContainer nc_;
//        NodeInformations ni_;
//        StringList values_;
//        nc_ = containers_.getVal(0L);
//        nc_.setEnabled(true);
//        ni_ = nc_.getNodeInformation();
//        values_ = new StringList();
//        values_.add("1/2");
//        ni_.setValue(values_);
//        nav_.getHtmlPage().setUrl(0);
//        form(conf_, nav_);
//        setupBeansAfter(conf_);
//        assertEq("page1.html", lgNames_.getCurrentUrl());
//        assertEq("bean_two", lgNames_.getCurrentBeanName());
//        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input c:className=\"code.formathtml.classes.Rate\" type=\"text\" name=\"bean_two.rate\" n-i=\"0\" value=\"1/2\"/></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObject map_ = beanTwo_.getForms();
//        assertEq(8, map_.size());
//        assertEq(8, getBean(conf_, "bean_two").getForms().size());
//        assertEq("",nav_.getTitle());
//        assertEq("",nav_.getReferenceScroll());
//
//    }

    @Test
    public void processNav16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input c:className='$short' type=\"number\" name=\"typedShort\" c:varValue=\"typedShort\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(true);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("12");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
        assertEq("page1.html", lgNames_.getCurrentUrl());
        assertEq("bean_two", lgNames_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input c:className=\"$short\" type=\"number\" name=\"bean_two.typedShort\" n-i=\"0\" value=\"12\"/></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(8, map_.size());
//        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

    @Test
    public void processNav16_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form c:command=\"$go\"><input c:className='$short' type=\"number\" name=\"typedShort\" c:varValue=\"typedShort\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        NatRendStackCall rendStackCall_ = initSessionNat(nav_, lgNames_, dual_);
        HtmlPage htmlPage_ = nav_.getHtmlPage();
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = htmlPage_.getContainers();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(0L);
        NodeContainer nc_;
        NodeInformations ni_;
        StringList values_;
        nc_ = containers_.getVal(0L);
        nc_.setEnabled(false);
        ni_ = nc_.getNodeInformation();
        values_ = new StringList();
        values_.add("12");
        ni_.setValue(values_);
        nav_.getHtmlPage().setUrl(0);
        form(lgNames_, nav_);
        assertEq("page1.html", lgNames_.getCurrentUrl());
        assertEq("bean_two", lgNames_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input c:className=\"$short\" type=\"number\" name=\"bean_two.typedShort\" n-i=\"0\" value=\"0\"/></form></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(8, map_.size());
//        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }


    @Test
    public void processNav_16_Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><a c:command=\"$go2({typedShort})\">Lk</a></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", htmlTwo_);
        BeanTwo beanTwo_ = new BeanTwo();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_two", init(beanTwo_), lgNames_);
        setupVal(folder_, relative_, config_, dual_, lgNames_);
        dual_.setNavigation(new StringMap<StringMap<String>>());
        dual_.getNavigation().put("bean_two.go2()", new StringMap<String>());
        dual_.getNavigation().getVal("bean_two.go2()").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(config_);
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page1.html");
        initSessionNat(nav_, lgNames_, dual_);
        nav_.getHtmlPage().setUrl(0);
        lgNames_.processRendAnchorRequest(DocumentBuilder.getFirstElementByAttribute(nav_.getDocument(), nav_.getSession().getRendKeyWords().getAttrNa(), Long.toString(nav_.getHtmlPage().getUrl())), nav_);
        assertEq("page1.html", lgNames_.getCurrentUrl());
        assertEq("bean_two", lgNames_.getCurrentBeanName());
        assertEq("<html><body><a c:command=\"$bean_two.go2(0)\" href=\"\" n-a=\"0\">Lk</a></body></html>", nav_.getHtmlText());
//        beanTwo_ = getBeanTwo(conf_, "bean_two");
//        StringMapObjectSample map_ = beanTwo_.getForms();
//        assertEq(8, map_.size());
//        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

    }

//    @Test
//    public void processNav17Test() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
//        String htmlTwo_ = "<html c:bean=\"bean_two\"><body>{$new $int[]{}}{$this}</body></html>";
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
//        files_.put("page1.html", htmlTwo_);
//        BeanTwo beanTwo_ = new BeanTwo();
//        NativeAnalyzedTestConfiguration conf_ = contextElSec();
//
//
//        putBean(beanTwo_, "bean_two", conf_.getAdv().getBeans());
//        setupVal(folder_, relative_, conf_);
//        conf_.setNavigation(new StringMap<StringMap<String>>());
//        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
//        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
//        Navigation nav_ = newNavigation(conf_);
//        nav_.setLanguage(locale_);
//        setSess(conf_, nav_);
//        nav_.setFiles(files_);
//        conf_.getDual().getRenderFiles().add("page1.html");
//        initSessionNat(conf_,nav_);
//        assertEq("page1.html", lgNames_.getCurrentUrl());
//
//    }

    @Test
    public void processNav18Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String htmlTwo_ = "<html c:bean=\"bean_one\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"go\"><c:select id=\"combo\" default=\"\" name=\"chosenNumber\" varValue=\"chosenNumber\" map=\"map\"/></form></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableBeanCoreUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page2.html", htmlTwo_);
        BeanOne bean_ = new BeanOne();
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();
        //NativeAnalyzedTestConfiguration conf_ = new NativeAnalyzedTestConfiguration(generate_, config_, lgNames_, init(lgNames_), dual_);


        putBean("bean_one", init(bean_), lgNames_);
        setupNative2(folder_, relative_, config_, dual_);


        dual_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(config_);
//        new Composite();
        nav_.setLanguage(locale_);
        nav_.setFiles(files_);
        dual_.getRenderFiles().add("page2.html");
        initSessionNat(nav_, lgNames_, dual_);
        assertEq("page2.html", lgNames_.getCurrentUrl());
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><select name=\"bean_one.chosenNumber\" n-i=\"0\"><option value=\"ONE\">1</option><option value=\"TWO\">2</option></select></form></body></html>", nav_.getHtmlText());

    }
//    @Test
//    public void refreshTest() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
//        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"$goToNullPage\" href=\"\"/></body></html>";
//        String htmlTwo_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"$goToNullPage\" href=\"\"/></body></html>";
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
//        files_.put("page1.html", html_);
//        files_.put("page2.html", htmlTwo_);
//        BeanOne bean_ = new BeanOne();
////        BeanFive beanTwo_ = new BeanFive();
//        NativeAnalyzedTestConfiguration conf_ = contextElSec();
//
//
//        putBean(bean_, conf_, "bean_one");
////        putBean(beanTwo_, conf_, "bean_two");
//        setupNative2(folder_, relative_, conf_);
//
//
//        conf_.setNavigation(new StringMap<StringMap<String>>());
//        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
//        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
//        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
//        Navigation nav_ = newNavigation(conf_);
//        ((CustBeanLgNames)conf_.getAdv()).setDataBase(new Composite());
//        nav_.setLanguage(locale_);
//        setSess(conf_, nav_);
//        nav_.setFiles(files_);
//        conf_.getDual().getRenderFiles().add("page1.html");
//        conf_.getDual().getRenderFiles().add("page2.html");
//        initSessionNat(conf_,nav_);
////        assertEq("page2.html", lgNames_.getCurrentUrl());
////        nav_.getHtmlPage().setUrl(0);
////        form(conf_, nav_);
////        setupBeansAfter(conf_);
//        ((BeanNatLgNames)conf_.getContext().getStandards()).rendRefresh(nav_, conf_.getContext(), conf_.getStackCall());
//        assertEq("page2.html", lgNames_.getCurrentUrl());
//        assertEq("bean_one", lgNames_.getCurrentBeanName());
//        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
////        assertSame(getBean(conf_, "bean_one").getForms(), getBeanFive(conf_, "bean_two").getForms());
//        assertEq("",nav_.getTitle());
//        assertEq("",nav_.getReferenceScroll());
//
//    }
//    @Test
//    public void processNavNullDbTest() {
//        String locale_ = "en";
//        String folder_ = "messages";
//        String relative_ = "sample/file";
//        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
//        String html_ = "<html c:bean=\"bean_one\"><body>HEAD<a c:command=\"$goToNullPage\" href=\"\"/></body></html>";
//        String htmlTwo_ = "<html c:bean=\"bean_two\"><body><form action=\"DELETE\" name=\"myform\" c:command=\"$go\"></form></body></html>";
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
//        files_.put("page1.html", html_);
//        files_.put("page2.html", htmlTwo_);
//        BeanOne bean_ = new BeanOne();
//        BeanFive beanTwo_ = new BeanFive();
//        NativeAnalyzedTestConfiguration conf_ = contextElSec();
//
//
//        putBean(bean_, "bean_one", conf_.getAdv().getBeans());
//        putBean(beanTwo_, "bean_two", conf_.getAdv().getBeans());
//        setupNative2(folder_, relative_, conf_);
//
//
//        conf_.setNavigation(new StringMap<StringMap<String>>());
//        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
//        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
//        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
//        Navigation nav_ = newNavigation(conf_);
//        ((CustBeanLgNames)conf_.getAdv()).setDataBase(null);
//        nav_.setLanguage(locale_);
//        setSess(conf_, nav_);
//        nav_.setFiles(files_);
//        conf_.getDual().getRenderFiles().add("page1.html");
//        conf_.getDual().getRenderFiles().add("page2.html");
//        initSessionNat(conf_,nav_);
//        assertEq("page2.html", lgNames_.getCurrentUrl());
//        nav_.getHtmlPage().setUrl(0);
//        form(conf_, nav_);
//        setupBeansAfter(conf_);
//        assertEq("page1.html", lgNames_.getCurrentUrl());
//        assertEq("bean_one", lgNames_.getCurrentBeanName());
//        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
//        assertSame(getBean(conf_, "bean_one").getForms(), getBeanFive(conf_, "bean_two").getForms());
//        assertEq("",nav_.getTitle());
//        assertEq("",nav_.getReferenceScroll());
//
//    }

    private static NatRendStackCall initSessionNat(Navigation _nav, CustBeanLgNames _adv, NatDualConfigurationContext _dual) {
        StringMap<BeanInfo> map_ = new StringMap<BeanInfo>();
        Configuration configuration_ = _nav.getSession();
        for (EntryCust<String, Struct> e: _adv.getBeansStruct().entryList()) {
            BeanInfo i_ = new BeanInfo();
            i_.setClassName(((SampleBeanStruct)e.getValue()).getBean().getClassName());
//            i_.setScope(((SampleBeanStruct)e.getValue()).getBean().getScope());
            map_.addEntry(e.getKey(),i_);
        }
        _nav.getSession().setBeansInfos(map_);
        _nav.setLanguages(new StringList(_nav.getLanguage()));
        configuration_.getFiles().addAllEntries(_nav.getFiles());
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(_adv);
        analyzingDoc_.setup(configuration_, _dual.getProperties(), _dual.getMessagesFolder());
        NatAnalyzedCode init_ = init(_adv);
        setupAna(analyzingDoc_, init_);
        BeanNatCommonLgNames.initInstancesPattern(configuration_, map_);
        configuration_.getBeansInfos().addAllEntries(map_);

        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (String s: _dual.getRenderFiles()) {
            String link_ = BeanNatCommonLgNames.getRealFilePath(configuration_.getCurrentLanguage(),s);
            String file_ = _nav.getFiles().getVal(link_);
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(file_);
            Document document_ = res_.getDocument();
            AnaRendDocumentBlock anaDoc_ = AnaRendBlockHelp.newRendDocumentBlock(analyzingDoc_.getPrefix(), document_, file_, link_, analyzingDoc_.getRendKeyWords(), _adv, new AdvNatBlockBuilder(_adv));
            d_.addEntry(link_,anaDoc_);
        }
        analyzingDoc_.setLanguages(_nav.getLanguages());
        configuration_.setCurrentLanguage(_nav.getLanguage());
        for (AnaRendDocumentBlock v : d_.values()) {
            AnaRendBlockHelp.buildFctInstructions(v,analyzingDoc_, init_, map_);
        }
//        _conf.setAnalyzed(d_);
        NatRendForwardInfos.buildExec(analyzingDoc_, d_, _adv.getRenders());
        preinit(_adv, configuration_);
//        standards_.beansForTest().clear();
        _adv.initializeRendSessionDoc(_nav);
        return null;
    }

    private String getNatRes(String _folder, String _relative, String _html, StringMap<String> _files, SampleBeanStruct _v) {
        CustBeanLgNames lgNames_ = stds();
        Configuration config_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        NatAnalyzedCode initNat_ = init(lgNames_);
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();

        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(lgNames_);
        config_.getFiles().addAllEntries(_files);
        setupNative2(_folder, _relative, dual_);
        putBean("bean_one", _v, lgNames_);
        StringMap<BeanInfo> beansInfos_ = config_.getBeansInfos();
        addBeanInfo(lgNames_,"bean_one", _v, beansInfos_, config_);
        setLocalFiles(analyzingDoc_, initNat_, config_, dual_);
        StringMap<BeanInfo> beansInfosBefore_ = analyzingDoc_.getBeansInfosBefore();
        beansInfosBefore_.addAllEntries(beansInfos_);
        StringMap<AnaRendDocumentBlock> d_ = analyze(config_, analyzingDoc_, beansInfosBefore_, lgNames_, initNat_, config_, dual_, _html);
        IdMap<NatOperationNode, ValidatorInfo> lateValidators_ = new IdMap<NatOperationNode, ValidatorInfo>();
        NatRendForwardInfos.buildExec(analyzingDoc_, d_, lgNames_.getRenders());
        setFirst(config_, lgNames_.getRenders());
        NatRendStackCall build_ = new NatRendStackCall();
        return getSampleRes(config_, lgNames_.getRenders().getVal("page1.html"), lgNames_, build_);
    }
    private String getNatRes(String _folder, String _relative, String _html, SampleBeanStruct _v) {
        CustBeanLgNames lgNames_ = stds();
        Configuration con_ = conf("c:");
        NatDualConfigurationContext dual_ = new NatDualConfigurationContext();
        NatAnalyzedCode init_ = init(lgNames_);
        ContextEl generate_ = new Forwards(lgNames_, null, new Options()).generate();

        setupNative2(_folder, _relative, dual_);
        putBean("bean_one", _v, lgNames_);
        StringMap<BeanInfo> beansInfos_ = con_.getBeansInfos();
        addBeanInfo(lgNames_,"bean_one", _v, beansInfos_, con_);
        AnalyzingDoc analyzingDo_ = new AnalyzingDoc();
        analyzingDo_.setContent(lgNames_);
        setLocalFiles(analyzingDo_, init_, con_, dual_);
        StringMap<BeanInfo> beansInfosBefore_ = analyzingDo_.getBeansInfosBefore();
        beansInfosBefore_.addAllEntries(beansInfos_);
        StringMap<AnaRendDocumentBlock> d_ = analyze(con_, analyzingDo_, beansInfosBefore_, lgNames_, init_, con_, dual_, _html);
        IdMap<NatOperationNode, ValidatorInfo> lateValidators_ = new IdMap<NatOperationNode, ValidatorInfo>();
        NatRendForwardInfos.buildExec(analyzingDo_, d_, lgNames_.getRenders());
        setFirst(con_, lgNames_.getRenders());
        NatRendStackCall build_ = new NatRendStackCall();
        return getSampleRes(con_, lgNames_.getRenders().getVal("page1.html"), lgNames_, build_);
    }

    private Configuration conf(String _prefix) {
        Configuration conf_ =  EquallableBeanCoreUtil.newConfiguration();
        conf_.setPrefix(_prefix);
        return conf_;
    }

    private static RendDocumentBlock buildRendWithTwoNativeBean(String _html, String _htmlTwo, SampleBeanStruct _v1, SampleBeanStruct _v2, Configuration _configuration, AnalyzingDoc _analyzingDoc, CustBeanLgNames _adv, NatAnalyzedCode _analyzing, NatDualConfigurationContext _dual) {
        StringMap<BeanInfo> beansInfos_ = _configuration.getBeansInfos();
        addBeanInfo(_adv,"bean_one", _v1, beansInfos_, _configuration);
        addBeanInfo(_adv,"bean_two", _v2, beansInfos_, _configuration);
        StringMap<BeanInfo> beansInfosBefore_ = _analyzingDoc.getBeansInfosBefore();
        beansInfosBefore_.addAllEntries(beansInfos_);
        StringMap<AnaRendDocumentBlock> d_ = analyze(_configuration, _analyzingDoc, beansInfosBefore_, _adv, _analyzing, _configuration, _dual, _html, _htmlTwo);
        IdMap<NatOperationNode, ValidatorInfo> lateValidators_ = new IdMap<NatOperationNode, ValidatorInfo>();
        NatRendForwardInfos.buildExec(_analyzingDoc, d_, _adv.getRenders());
        setFirst(_configuration, _adv.getRenders());
        return _adv.getRenders().getVal("page1.html");
    }

    private static void setLocalFiles(AnalyzingDoc _analyzingDoc, NatAnalyzedCode _analyzing, Configuration _configuration, NatDualConfigurationContext _dual) {
        _configuration.setCurrentLanguage("en");
        _analyzingDoc.setup(_configuration, _dual.getProperties(), _dual.getMessagesFolder());
        setInnerLocalFiles(_analyzingDoc, _analyzing);
    }

    private static void setInnerLocalFiles(AnalyzingDoc _analyzingDoc, NatAnalyzedCode _analyzing) {
        _analyzingDoc.setLanguages(new StringList("en"));
        setupAna(_analyzingDoc, _analyzing);
    }
    private static StringMap<AnaRendDocumentBlock> analyze(Configuration _conf, AnalyzingDoc _analyzingDoc, StringMap<BeanInfo> _beansInfosBefore, CustBeanLgNames _adv, NatAnalyzedCode _analyzing, Configuration _configuration, NatDualConfigurationContext _dual, String... _html) {
        int c_ = 1;
        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (String h: _html) {
            Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(h).getDocument();
            AnaRendDocumentBlock anaDoc_ = AnaRendBlockHelp.newRendDocumentBlock("c:", doc_, h, "page1.html", _conf.getRendKeyWords(), _adv, new AdvNatBlockBuilder(_adv));
            d_.addEntry("page"+c_+".html",anaDoc_);
            c_++;
        }
        setLocalFiles(_analyzingDoc, _analyzing, _configuration, _dual);
        for (AnaRendDocumentBlock v: d_.values()) {
            AnaRendBlockHelp.buildFctInstructions(v,_analyzingDoc, _analyzing, _beansInfosBefore);
        }
        return d_;
    }
    protected static RendDocumentBlock setFirst(Configuration _configuration, StringMap<RendDocumentBlock> _renders) {
        return _renders.getVal("page1.html");
    }

    private static void addBeanInfo(CustBeanLgNames _adv,String _id, Struct _str, StringMap<BeanInfo> _beansInfos, Configuration _configuration) {
        BeanInfo b_ = new BeanInfo();
        b_.setClassName(_str.getClassName(null));
        b_.setResolvedClassName(_str.getClassName(null));
        _beansInfos.addEntry(_id,b_);
        _adv.getBeansStruct().addEntry(_id,_str);
    }
    private static void basicStandards(BeanLgNames _lgNames) {
        DefaultInitialization.basicStandards(_lgNames);
    }


    private static void setupNative2(String _folder, String _relative, NatDualConfigurationContext _context) {
        setupNative(_folder, _relative, _context);
    }

    private static void putBean(String _key, BeanNatCommonLgNames _stds, SampleBeanStruct _v) {
        _stds.getBeansStruct().put(_key, _v);
//        ((CustBeanLgNames) _stds).beansForTest().put(_key, _beanTwo);
    }

    private static void setupNative(String _folder, String _relative, NatDualConfigurationContext _dual) {
        setup(_folder, _relative, _dual);
    }

    private static void setupNative2(String _folder, String _relative, Configuration _configuration, NatDualConfigurationContext _dual) {
        _configuration.setFirstUrl("page2.html");
        setupNative(_folder, _relative, _dual);
    }
    private static void setupVal(String _folder, String _relative, Configuration _configuration, NatDualConfigurationContext _dual, CustBeanLgNames _adv) {
        _configuration.setFirstUrl("page1.html");
        setupNative(_folder, _relative, _dual);
        _adv.getValidators().put("rate_val", new MyValidator());
    }

    private static void setSess(Configuration _conf, Navigation _nav) {
        _nav.setSession(_conf);
    }

    //    private static BeanOne getBean(NativeAnalyzedTestConfiguration _conf, String _key) {
//        return (BeanOne) ((SampleBeanStruct) _conf.getConfiguration().getBuiltBeans().getVal(_key)).getBean();
////        return (BeanOne) _conf.getAdv().beansForTest().getVal(_key);
//    }
//    private static BeanFive getBeanFive(NativeAnalyzedTestConfiguration _conf, String _key) {
//        return (BeanFive)_conf.getAdv().getBeans().getVal(_key);
//    }

//    private static BeanTwo getBeanTwo(NativeAnalyzedTestConfiguration _conf, String _key) {
//        return (BeanTwo) ((SampleBeanStruct) _conf.getConfiguration().getBuiltBeans().getVal(_key)).getBean();
//    }

    private static void putBean(String _key, SampleBeanStruct _v, CustBeanLgNames _adv) {
        putBean(_key, _adv, _v);
    }

    private static String getSampleRes(Configuration _conf, RendDocumentBlock _rendDocumentBlock, BeanNatCommonLgNames _stds, NatRendStackCall _rendStackCall) {
        return getRes(_conf,_rendDocumentBlock, _stds, _rendStackCall);
    }

    private static String getSampleRes(RendDocumentBlock _rendDocumentBlock, Configuration _configuration, CustBeanLgNames _adv) {
        NatRendStackCall build_ = new NatRendStackCall();
        return getSampleRes(_configuration, _rendDocumentBlock, _adv, build_);
    }

    private static void preinit(CustBeanLgNames _adv, Configuration _configuration) {
        _adv.preInitBeans(_configuration);
    }

    private static String getRes(Configuration _conf, RendDocumentBlock _doc, BeanNatCommonLgNames _stds, NatRendStackCall _rendStackCall) {
        return _stds.getRes(_doc, _conf, _rendStackCall);
    }

    private static Navigation newNavigation(Configuration _configuration) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_configuration);

        return nav_;
    }

    private static void setFiles(StringMap<String> _files, Configuration _configuration) {
        _configuration.setFiles(_files);
    }

    private static void setup(String _folder, String _relative, NatDualConfigurationContext _conf) {
        setup(_folder, _conf);
        _conf.getProperties().put("msg_example", _relative);
    }

    private static void setup(String _folder, NatDualConfigurationContext _conf) {
        _conf.setMessagesFolder(_folder);
        _conf.setProperties(new StringMap<String>());
    }

    private static void setupAna(AnalyzingDoc _analyzingDoc, NatAnalyzedCode _page) {

    }

    private static void form(CustBeanLgNames _stds, Navigation _nav) {
        _stds.processRendFormRequest(_nav, DocumentBuilder.getFirstElementByAttribute(_nav.getDocument(), _nav.getSession().getRendKeyWords().getAttrNf(), Long.toString(_nav.getHtmlPage().getUrl())));
    }

    private static String confCom() {
        return "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page2.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<java.lang.String field='dataBaseClassName' value='java.lang.Object'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='code.formathtml.classes.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t\t<java.lang.String key='' value='bean_two'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='code.formathtml.classes.BeanTwo'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page1.html'/>\n" +
                "\t\t<str value='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page1.html'/>\n" +
                "\t\t<str value='page2.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='validators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='code.formathtml.classes.MyValidator'/>\n" +
                "\t</sm>\n" +
                "\t<i field='inex'/>\n" +
                "</cfg>\n" +
                "\n";
    }

    private SampleBeanStruct init(BeanOne _b) {
        _b.getBaseForms().getBeansOthers().put("other",new BeanThree());
        _b.getBaseForms().put("typedShort",0);
        return new SampleBeanStruct(_b);
    }

    private SampleBeanStruct init(BeanTwo _b) {
        _b.getBaseForms().getBeansOthers().put("other",new BeanThree());
        _b.getBaseForms().put("typedShort",0);
        return new SampleBeanStruct(_b);
    }

    private StringList getStrings(SampleBeanStruct v_) {
        return ((BeanThree)v_.getComposite()).getStrings();
    }

    private void setInteger(SampleBeanStruct v_) {
        ((BeanThree)v_.getComposite()).setInteger(5);
    }

}
