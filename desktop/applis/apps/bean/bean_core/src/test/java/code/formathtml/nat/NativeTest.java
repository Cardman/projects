package code.formathtml.nat;

import code.bean.Bean;
import code.bean.nat.*;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.exec.blocks.NatRendImport;
import code.bean.nat.exec.blocks.RendBlockHelp;
import code.bean.nat.exec.opers.NatStdRefVariableOperation;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.bean.nat.fwd.AdvNatBlockBuilder;
import code.bean.nat.fwd.DefNatBlockBuilder;
import code.bean.nat.fwd.NatRendForwardInfos;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.*;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.sample.BeanOne;
import code.formathtml.sample.BeanTwo;
import code.formathtml.sample.Composite;
import code.formathtml.sample.CustBeanLgNames;
import code.formathtml.sample.MyValidator;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.Struct;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.util.*;
import org.junit.Test;

public final class NativeTest extends EquallableBeanCoreUtil {


    @Test
    public void bases() {
        NativeAnalyzedTestConfiguration conf_ = contextElSec();

        AnalyzingDoc analyzingDoc_ = conf_.getAnalyzingDoc();
        setLocalFiles(conf_, analyzingDoc_);
        IdMap<NatOperationNode, ValidatorInfo> lateValidators_ = new IdMap<NatOperationNode, ValidatorInfo>();
        NatRendForwardInfos.buildExec(analyzingDoc_, conf_.getAnalyzed(), conf_.getConfiguration());
        setFirst(conf_);
        assertNotNull(BeanNatCommonLgNames.getPairStruct(null,conf_.getContext()));
        assertNotNull(BeanNatCommonLgNames.getSimpleItrStruct(null,conf_.getContext()));
        assertNotNull(((BeanNatLgNames)conf_.getContext().getStandards()).getLongsArray(new CustList<Longs>(new Longs(0L))));
        RendStackCall stack_ = new RendStackCall(InitPhase.NOTHING, conf_.getContext());
        stack_.addPage(new ImportingPage());
        RendBlockHelp.processElse(null,null,stack_);
        RendBlockHelp.processElseIf(null, null, null,stack_);
        NatStdRefVariableOperation.getValue(null,null,null);
        PairStruct struct_ = new PairStruct("", NullStruct.NULL_VALUE, NullStruct.NULL_VALUE);
        assertEq("",new VariableWrapperNat(LocalVariable.newLocalVariable(struct_,"")).getClassName(null,null));
        BeanNatLgNames.processString(new Argument(struct_),conf_.getContext());
        NatRendImport.beforeDisp(null, null);
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body>Not empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().setInteger(5);
        assertEq("<html><body>Empty</body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><li>0</li><li>1</li></ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><li>0</li><li>1</li></ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><li>EMPTY</li><li/></ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><li>5</li><li>6</li></ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul>10</ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><a c:command=\"$bean_one.composite.sum(5,5,5)\" href=\"\" n-a=\"0\">5</a></ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><a c:command=\"$bean_one.validateStrings()\" href=\"\" n-a=\"0\">5</a></ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><a c:command=\"composite\" href=\"\" n-a=\"0\">5</a></ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul>10</ul></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul>5</ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><input type=\"submit\" value=\"OK\"/></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><input type=\"submit\" c:groupId=\"5\" value=\"OK\"/></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body>'a'</body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><ul><a c:command=\"$bean_one.composite.sum(5,5)\" href=\"\" n-a=\"0\"/></ul></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><input type=\"radio\" name=\"composite.integer\" value=\"5\" checked=\"checked\"/></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><input value=\"Click\" type=\"submit\"/></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><a title=\"Click\"/></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><img/></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><img/></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><link/></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        assertEq("<html><body><span/></body></html>", getNatRes(folder_, relative_, html_, bean_,files_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td><td>ONE</td><td>1</td></tr><tr><td>ONE</td><td>1</td><td>TWO</td><td>2</td></tr><tr><td>TWO</td><td>2</td><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td><td>TWO</td><td>2</td></tr></table></body></html>", getNatRes(folder_, relative_, html_, bean_));
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
        String xmlConf_ = confCom();
        Navigation n_ = new Navigation();
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(lgNames_, new SampleNativeInit());
        DualAnalyzedContext du_ = n_.loadConfiguration(xmlConf_, "", lgNames_, DefaultFileBuilder.newInstance(lgNames_.getContent()), nat_);
        n_.setFiles(files_);
        lgNames_.setupAll(docs_,n_, n_.getSession(), du_, new DefNatBlockBuilder());
        ContextEl generate_ = du_.getForwards().generate();
        n_.initializeRendSession(generate_, du_.getStds(), new RendStackCall(InitPhase.NOTHING, generate_));
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
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
        String xmlConf_ = confCom();
        Navigation n_ = new Navigation();
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(lgNames_, new SampleNativeInit());
        DualAnalyzedContext du_ = n_.loadConfiguration(xmlConf_, "", lgNames_, DefaultFileBuilder.newInstance(lgNames_.getContent()), nat_);
        n_.setFiles(files_);
        lgNames_.setupAll(docs_,n_, n_.getSession(), du_, new DefNatBlockBuilder());
        ContextEl generate_ = du_.getForwards().generate();
        n_.initializeRendSession(generate_, du_.getStds(), new RendStackCall(InitPhase.NOTHING, generate_));
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
//        DualAnalyzedContext du_ = n_.loadConfiguration(xmlConf_, "", lgNames_, DefaultFileBuilder.newInstance(lgNames_.getContent()), nat_);
//        n_.setFiles(files_);
//        lgNames_.setupAll(docs_,n_, n_.getSession(), du_, new DefNatBlockBuilder());
//        ContextEl generate_ = du_.getForwards().generate(new Options());
//        n_.initializeRendSession(generate_, du_.getStds(), new RendStackCall(InitPhase.NOTHING, generate_));
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
        Configuration conf_ =  EquallableBeanCoreUtil.newConfiguration();
        conf_.setPrefix("c");
        NativeAnalyzedTestConfiguration a_ = buildNat(conf_);
        conf_.setFirstUrl("page2.html");
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanTwo");
        conf_.getBeansInfos().addEntry("bean_two",i_);
        conf_.init(a_.getDual());
        Navigation n_ = new Navigation();
        setSess(conf_, n_);
        n_.setFiles(files_);
        a_.getAdv().setupAll(docs_,n_, n_.getSession(), new DualAnalyzedContext(a_.getForwards(),null,a_.getAdv(),a_.getDual()), new DefNatBlockBuilder());
        RendStackCall build_ = a_.build(InitPhase.NOTHING, a_.getContext());
        n_.initializeRendSession(a_.getContext(), a_.getAdv(), build_);
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
        assertEq("page2.html", n_.getCurrentUrl());
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
        Configuration conf_ =  EquallableBeanCoreUtil.newConfiguration();
        conf_.setPrefix("c");
        NativeAnalyzedTestConfiguration a_ = buildNat(conf_);
        conf_.setFirstUrl("page2.html");
        a_.getDual().getRenderFiles().add("page1.html");
        a_.getDual().getRenderFiles().add("page2.html");
        BeanInfo i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanOne");
        conf_.getBeansInfos().addEntry("bean_one",i_);
        i_ = new BeanInfo();
        i_.setScope("session");
        i_.setClassName("code.formathtml.classes.BeanTwo");
        conf_.getBeansInfos().addEntry("bean_two",i_);
        conf_.init(a_.getDual());
        Navigation n_ = new Navigation();
        setSess(conf_, n_);
        n_.setFiles(files_);
        a_.getAdv().setupAll(docs_,n_, n_.getSession(), new DualAnalyzedContext(a_.getForwards(),null,a_.getAdv(),a_.getDual()), new DefNatBlockBuilder());
        RendStackCall build_ = a_.build(InitPhase.NOTHING, a_.getContext());
        n_.initializeRendSession(a_.getContext(), a_.getAdv(), build_);
        assertEq("<html><body><form action=\"\" name=\"myform\" c:command=\"go\" n-f=\"0\"><input type=\"text\" name=\"bean_two.typedString\" n-i=\"0\" value=\"TYPED_STRING\"/></form></body></html>", n_.getHtmlText());
        assertEq("page2.html", n_.getCurrentUrl());
    }
    private static NativeAnalyzedTestConfiguration buildNat(Configuration _conf) {
        Options opt_ = new Options();
        CustBeanLgNames lgNames_ = new CustBeanLgNames();
        basicStandards(lgNames_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        NatAnalyzedCode page_ = NatAnalyzedCode.setInnerAnalyzing();
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgNames_.getContent());
        Forwards forwards_ = new Forwards(lgNames_, fileBuilder_, opt_);
//        ContextFactory.validatedStds(forwards_, a_, kw_, new CustList<CommentDelimiters>(), _opt, lgNames_.getContent(), page_);
        lgNames_.buildBeans();
        lgNames_.buildOther();
        page_.setStandards(lgNames_.getContent());
        page_.setStds(lgNames_);
        RendBlockHelp.setupOverrides(page_.getStds());
        DualConfigurationContext dual = new DualConfigurationContext();
        return new NativeAnalyzedTestConfiguration(forwards_.generate(),_conf, forwards_, lgNames_, page_, dual);
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
//        assertEq("page2.html", nav_.getCurrentUrl());
//        nav_.getHtmlPage().setUrl(0);
//        form(conf_, nav_);
//        setupBeansAfter(conf_);
//        assertEq("page1.html", nav_.getCurrentUrl());
//        assertEq("bean_one", nav_.getCurrentBeanName());
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
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(bean_, conf_, "bean_one");
//        putBean(beanTwo_, conf_, "bean_two");
        setupNative2(folder_, relative_, conf_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("change", "page1.html");
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        new Composite();
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        conf_.getDual().getRenderFiles().add("page1.html");
        conf_.getDual().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
        assertEq("page2.html", nav_.getCurrentUrl());

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
//        assertEq("page2.html", nav_.getCurrentUrl());
//        nav_.getHtmlPage().setUrl(0);
//        form(conf_, nav_);
//        setupBeansAfter(conf_);
//        assertEq("page1.html", nav_.getCurrentUrl());
//        assertEq("bean_one", nav_.getCurrentBeanName());
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();
        
        conf_.getConfiguration().setFirstUrl("page1.html");
        bean_.getForms().put("key", "sample_value");
        setupNative(folder_, relative_, conf_);
        preinit(conf_);


        RendDocumentBlock rendDocumentBlock_ = buildRendWithTwoNativeBean(html_, htmlTwo_, bean_, beanTwo_, conf_);
        assertEq("<html><body><a href=\"\" c:command=\"go\" n-a=\"0\">Test {0}2</a></body></html>", getSampleRes(conf_, rendDocumentBlock_));
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().getVal("key"));
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
        bean_.getComposite().getStrings().add("FIRST");
        bean_.getComposite().getStrings().add("SECOND");
        bean_.getComposite().setInteger(5);
        bean_.getTree().put("ONE", 1);
        bean_.getTree().put("TWO", 2);
        bean_.getForms().put("key", "sample_value");
        BeanTwo beanTwo_ = new BeanTwo();
        beanTwo_.setTypedString("TITLE");
        NativeAnalyzedTestConfiguration conf_ = contextElSec();
        setupNative(folder_, relative_, conf_);
        preinit(conf_);
        setFiles(files_, conf_);

        RendDocumentBlock rendDocumentBlock_ = buildRendWithTwoNativeBean(html_, htmlTwo_, bean_, beanTwo_, conf_);
        String render_ = getSampleRes(conf_, rendDocumentBlock_);
        assertEq("<html><body><a href=\"\" c:command=\"go\" n-a=\"0\">Test {0}2</a>Description <a c:command=\"$bean_two.go\" href=\"\" n-a=\"1\">two</a></body></html>", render_);
        assertEq(1, beanTwo_.getForms().size());
        assertEq("key", beanTwo_.getForms().getKeys().first());
        assertEq("sample_value", (String)beanTwo_.getForms().getVal("key"));
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
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_one");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_one.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_one.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        conf_.getDual().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
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
        form(conf_, nav_);
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_one", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_one.goToPage\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"text\" name=\"bean_one.selectedString\" c:validator=\"rate_val\" n-i=\"0\" value=\"ONE_TWO\"/><span c:for=\"txt\" c:valueMessage=\"msg_example,err\">ONE_TWO is not a no zero rate</span></form></body></html>", nav_.getHtmlText());
        beanTwo_ = getBean(conf_, "bean_one");
        StringMapObjectSample map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, getBean(conf_, "bean_one").getForms().size());
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
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        conf_.getDual().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
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
        form(conf_, nav_);
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"0\"/><span c:for=\"txt\" c:valueMessage=\"msg_example,err\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = getBeanTwo(conf_, "bean_two");
        StringMapObjectSample map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, getBeanTwo(conf_, "bean_two").getForms().size());
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
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        conf_.getDual().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
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
        form(conf_, nav_);
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"0\"/><span c:for=\"txt\" c:valueMessage=\"msg_example,err\"/><input id=\"txt2\" type=\"text\" name=\"bean_two.typedString\" n-i=\"1\" value=\"TYPED_STRING\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = getBeanTwo(conf_, "bean_two");
        StringMapObjectSample map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, getBeanTwo(conf_, "bean_two").getForms().size());
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
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        putBean(beanOne_, conf_, "bean_one");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        conf_.getDual().getRenderFiles().add("page1.html");
        conf_.getDual().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
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
        form(conf_, nav_);
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input id=\"txt\" type=\"number\" name=\"bean_two.typedInt\" c:validator=\"rate_val\" n-i=\"0\" value=\"0\"/><span c:for=\"txt\" c:valueMessage=\"msg_example,err\"/><input id=\"txt2\" type=\"text\" name=\"bean_one.selectedString\" n-i=\"1\" value=\"ONE\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = getBeanTwo(conf_, "bean_two");
        StringMapObjectSample map_ = beanTwo_.getForms();
        assertEq(0, map_.size());
        assertEq(0, getBeanTwo(conf_, "bean_two").getForms().size());
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
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(bean_, conf_, "bean_one");
        setupNative2(folder_, relative_, conf_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        new Composite();
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        conf_.getDual().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
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
//        assertEq("page2.html", nav_.getCurrentUrl());
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
//        assertEq("page2.html", nav_.getCurrentUrl());
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
//        assertEq("page2.html", nav_.getCurrentUrl());
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
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        conf_.getDual().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
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
        form(conf_, nav_);
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.nullableInt\" n-i=\"0\" value=\"10\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = getBeanTwo(conf_, "bean_two");
        StringMapObjectSample map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
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
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        conf_.getDual().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
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
        form(conf_, nav_);
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"number\" name=\"bean_two.nullableInt\" n-i=\"0\" value=\"\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = getBeanTwo(conf_, "bean_two");
        StringMapObjectSample map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
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
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        conf_.getDual().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
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
        form(conf_, nav_);
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input type=\"checkbox\" name=\"bean_two.checked\" n-i=\"0\" checked=\"checked\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = getBeanTwo(conf_, "bean_two");
        StringMapObjectSample map_ = beanTwo_.getForms();
        assertEq(8, map_.size());
        assertEq(8, getBeanTwo(conf_, "bean_two").getForms().size());
        assertEq("",nav_.getTitle());
        assertEq("",nav_.getReferenceScroll());

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
//        assertEq("page1.html", nav_.getCurrentUrl());
//        assertEq("bean_two", nav_.getCurrentBeanName());
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
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(beanTwo_, conf_, "bean_two");
        setupVal(folder_, relative_, conf_);
        conf_.setNavigation(new StringMap<StringMap<String>>());
        conf_.getNavigation().put("bean_two.go", new StringMap<String>());
        conf_.getNavigation().getVal("bean_two.go").put("no_change", "page2.html");
        Navigation nav_ = newNavigation(conf_);
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        conf_.getDual().getRenderFiles().add("page1.html");
        initSessionNat(conf_,nav_);
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
        form(conf_, nav_);
        setupBeansAfter(conf_);
        assertEq("page1.html", nav_.getCurrentUrl());
        assertEq("bean_two", nav_.getCurrentBeanName());
        assertEq("<html><body><form c:command=\"$bean_two.go\" action=\"\" n-f=\"0\"><input c:className=\"$short\" type=\"number\" name=\"bean_two.typedShort\" n-i=\"0\" value=\"12\"/></form></body></html>", nav_.getHtmlText());
        beanTwo_ = getBeanTwo(conf_, "bean_two");
        StringMapObjectSample map_ = beanTwo_.getForms();
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
//        assertEq("page1.html", nav_.getCurrentUrl());
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
        NativeAnalyzedTestConfiguration conf_ = contextElSec();


        putBean(bean_, conf_, "bean_one");
        setupNative2(folder_, relative_, conf_);


        conf_.setNavigation(new StringMap<StringMap<String>>());
        Navigation nav_ = newNavigation(conf_);
        new Composite();
        nav_.setLanguage(locale_);
        setSess(conf_, nav_);
        nav_.setFiles(files_);
        conf_.getDual().getRenderFiles().add("page2.html");
        initSessionNat(conf_,nav_);
        assertEq("page2.html", nav_.getCurrentUrl());
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
////        assertEq("page2.html", nav_.getCurrentUrl());
////        nav_.getHtmlPage().setUrl(0);
////        form(conf_, nav_);
////        setupBeansAfter(conf_);
//        ((BeanNatLgNames)conf_.getContext().getStandards()).rendRefresh(nav_, conf_.getContext(), conf_.getStackCall());
//        assertEq("page2.html", nav_.getCurrentUrl());
//        assertEq("bean_one", nav_.getCurrentBeanName());
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
//        assertEq("page2.html", nav_.getCurrentUrl());
//        nav_.getHtmlPage().setUrl(0);
//        form(conf_, nav_);
//        setupBeansAfter(conf_);
//        assertEq("page1.html", nav_.getCurrentUrl());
//        assertEq("bean_one", nav_.getCurrentBeanName());
//        assertEq("<html><body>HEAD<a c:command=\"$bean_one.goToNullPage\" href=\"\" n-a=\"0\"/></body></html>", nav_.getHtmlText());
//        assertSame(getBean(conf_, "bean_one").getForms(), getBeanFive(conf_, "bean_two").getForms());
//        assertEq("",nav_.getTitle());
//        assertEq("",nav_.getReferenceScroll());
//
//    }

    private static void setupBeansAfter(NativeAnalyzedTestConfiguration _conf) {
//        cleanBeans(_conf);
//        for (EntryCust<String, Struct> e: _conf.getConfiguration().getBuiltBeans().entryList()) {
//            putBean(((BeanStruct) e.getValue()).getInstance(), e.getKey(), _conf.getAdv().getBeans());
//        }
    }

    private static void initSessionNat(NativeAnalyzedTestConfiguration _conf,Navigation _nav) {
        StringMap<BeanInfo> map_ = new StringMap<BeanInfo>();
        CustBeanLgNames standards_ = _conf.getAdv();
        for (EntryCust<String, Struct> e: _conf.getConfiguration().getBuiltBeans().entryList()) {
            BeanInfo i_ = new BeanInfo();
            i_.setClassName(((BeanStruct)e.getValue()).getBean().getClassName());
            i_.setScope(((BeanStruct)e.getValue()).getBean().getScope());
            map_.addEntry(e.getKey(),i_);
        }
        _nav.getSession().setBeansInfos(map_);
        _nav.setLanguages(new StringList(_nav.getLanguage()));
        _conf.getConfiguration().getFiles().addAllEntries(_nav.getFiles());
        AnalyzingDoc analyzingDoc_ = _conf.getAnalyzingDoc();
        analyzingDoc_.setup(_conf.getConfiguration(), _conf.getDual());
        setupAna(analyzingDoc_, _conf.getAnalyzing());
        BeanNatLgNames.initInstancesPattern(_conf.getConfiguration(), map_);
        _conf.getConfiguration().getBeansInfos().addAllEntries(map_);
        NatAnalyzedCode page_ = _conf.getAnalyzing();

        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (String s: _conf.getDual().getRenderFiles()) {
            String link_ = BeanNatCommonLgNames.getRealFilePath(_conf.getConfiguration().getCurrentLanguage(),s);
            String file_ = _nav.getFiles().getVal(link_);
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(file_);
            Document document_ = res_.getDocument();
            AnaRendDocumentBlock anaDoc_ = AnaRendBlockHelp.newRendDocumentBlock(analyzingDoc_.getPrefix(), document_, file_, link_, analyzingDoc_.getRendKeyWords(), standards_, new AdvNatBlockBuilder(standards_));
            d_.addEntry(link_,anaDoc_);
        }
        analyzingDoc_.setLanguages(_nav.getLanguages());
        _conf.getConfiguration().setCurrentLanguage(_nav.getLanguage());
        for (AnaRendDocumentBlock v : d_.values()) {
            AnaRendBlockHelp.buildFctInstructions(v,analyzingDoc_, page_, map_);
        }
        _conf.setAnalyzed(d_);
        NatRendForwardInfos.buildExec(analyzingDoc_, d_, _conf.getConfiguration());
        RendStackCall build_ = _conf.build(InitPhase.NOTHING, _conf.getContext());
        _conf.getAdv().preInitBeans(_conf.getConfiguration());
//        standards_.beansForTest().clear();
        _nav.initializeRendSession(_conf.getContext(), _conf.getAdv(), build_);
    }

    private String getNatRes(String _folder, String _relative, String _html, BeanOne _bean, StringMap<String> _files) {
        NativeAnalyzedTestConfiguration conf_ = contextElSec();

        conf_.getConfiguration().getFiles().addAllEntries(_files);
        setupNative(_folder, _relative, conf_.getDual());
        putBean(_bean, "bean_one", conf_.getAdv(),conf_.getConfiguration());
        StringMap<BeanInfo> beansInfos_ = conf_.getConfiguration().getBeansInfos();
        addBeanInfo(conf_,"bean_one",new BeanStruct(_bean), beansInfos_);
        AnalyzingDoc analyzingDoc_ = conf_.getAnalyzingDoc();
        setLocalFiles(conf_, analyzingDoc_);
        StringMap<BeanInfo> beansInfosBefore_ = conf_.getAnalyzingDoc().getBeansInfosBefore();
        beansInfosBefore_.addAllEntries(beansInfos_);
        analyzeInner(conf_.getConfiguration(),conf_, beansInfosBefore_, _html);
        IdMap<NatOperationNode, ValidatorInfo> lateValidators_ = new IdMap<NatOperationNode, ValidatorInfo>();
        NatRendForwardInfos.buildExec(analyzingDoc_, conf_.getAnalyzed(), conf_.getConfiguration());
        setFirst(conf_);
        RendStackCall build_ = conf_.build(InitPhase.NOTHING, conf_.getContext());
        return getSampleRes(conf_.getConfiguration(), conf_.getConfiguration().getRenders().getVal("page1.html"), conf_.getAdv(), conf_.getContext(), build_);
    }
    private String getNatRes(String _folder, String _relative, String _html, BeanOne _bean) {
        NativeAnalyzedTestConfiguration conf_ = contextElSec();

        setupNative(_folder, _relative, conf_.getDual());
        putBean(_bean, "bean_one", conf_.getAdv(),conf_.getConfiguration());
        StringMap<BeanInfo> beansInfos_ = conf_.getConfiguration().getBeansInfos();
        addBeanInfo(conf_,"bean_one",new BeanStruct(_bean), beansInfos_);
        AnalyzingDoc analyzingDoc_ = conf_.getAnalyzingDoc();
        setLocalFiles(conf_, analyzingDoc_);
        StringMap<BeanInfo> beansInfosBefore_ = conf_.getAnalyzingDoc().getBeansInfosBefore();
        beansInfosBefore_.addAllEntries(beansInfos_);
        analyzeInner(conf_.getConfiguration(),conf_, beansInfosBefore_, _html);
        IdMap<NatOperationNode, ValidatorInfo> lateValidators_ = new IdMap<NatOperationNode, ValidatorInfo>();
        NatRendForwardInfos.buildExec(analyzingDoc_, conf_.getAnalyzed(), conf_.getConfiguration());
        setFirst(conf_);
        RendStackCall build_ = conf_.build(InitPhase.NOTHING, conf_.getContext());
        return getSampleRes(conf_.getConfiguration(), conf_.getConfiguration().getRenders().getVal("page1.html"), conf_.getAdv(), conf_.getContext(), build_);
    }

    private NativeAnalyzedTestConfiguration contextElSec() {
        Configuration conf_ =  EquallableBeanCoreUtil.newConfiguration();
        conf_.setPrefix("c:");
        return buildNat(conf_);
    }

    private static RendDocumentBlock buildRendWithTwoNativeBean(String _html, String _htmlTwo, BeanOne _bean, BeanTwo _beanTwo, NativeAnalyzedTestConfiguration _conf) {
        Configuration c_ = _conf.getConfiguration();
        StringMap<BeanInfo> beansInfos_ = _conf.getConfiguration().getBeansInfos();
        addBeanInfo(_conf,"bean_one", new BeanStruct(_bean), beansInfos_);
        addBeanInfo(_conf,"bean_two", new BeanStruct(_beanTwo), beansInfos_);
        StringMap<BeanInfo> beansInfosBefore_ = _conf.getAnalyzingDoc().getBeansInfosBefore();
        beansInfosBefore_.addAllEntries(beansInfos_);
        analyzeInner(c_,_conf, beansInfosBefore_, _html,_htmlTwo);
        IdMap<NatOperationNode, ValidatorInfo> lateValidators_ = new IdMap<NatOperationNode, ValidatorInfo>();
        NatRendForwardInfos.buildExec(_conf.getAnalyzingDoc(), _conf.getAnalyzed(), _conf.getConfiguration());
        setFirst(_conf);
        return _conf.getConfiguration().getRenders().getVal("page1.html");
    }

    private static void analyzeInner(Configuration _conf, NativeAnalyzedTestConfiguration _a, StringMap<BeanInfo> _beansInfosBefore, String... _html) {
        StringMap<AnaRendDocumentBlock> d_ = analyze(_conf, _a, _a.getAnalyzingDoc(), _beansInfosBefore, _html);
        _a.setAnalyzed(d_);
    }

    private static void setLocalFiles(NativeAnalyzedTestConfiguration _context, AnalyzingDoc _analyzingDoc) {
        NatAnalyzedCode analyzing_ = _context.getAnalyzing();
        Configuration conf_ = _context.getConfiguration();
        conf_.setCurrentLanguage("en");
        _analyzingDoc.setup(conf_, _context.getDual());
        setInnerLocalFiles(_analyzingDoc, analyzing_);
    }

    private static void setInnerLocalFiles(AnalyzingDoc _analyzingDoc, NatAnalyzedCode _analyzing) {
        _analyzingDoc.setLanguages(new StringList("en"));
        setupAna(_analyzingDoc, _analyzing);
    }
    private static StringMap<AnaRendDocumentBlock> analyze(Configuration _conf, NativeAnalyzedTestConfiguration _a, AnalyzingDoc _analyzingDoc, StringMap<BeanInfo> _beansInfosBefore, String... _html) {
        int c_ = 1;
        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (String h: _html) {
            Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(h).getDocument();
            AnaRendDocumentBlock anaDoc_ = AnaRendBlockHelp.newRendDocumentBlock("c:", doc_, h, "page1.html", _conf.getRendKeyWords(), _a.getAdv(), new AdvNatBlockBuilder(_a.getAdv()));
            d_.addEntry("page"+c_+".html",anaDoc_);
            c_++;
        }
        setLocalFiles(_a, _analyzingDoc);
        for (AnaRendDocumentBlock v: d_.values()) {
            AnaRendBlockHelp.buildFctInstructions(v,_analyzingDoc, _a.getAnalyzing(), _beansInfosBefore);
        }
        return d_;
    }
    protected static void setFirst(NativeAnalyzedTestConfiguration _cont) {
        RendDocumentBlock doc_ = _cont.getConfiguration().getRenders().getVal("page1.html");
        _cont.getConfiguration().setRendDocumentBlock(doc_);
    }

    private static void addBeanInfo(NativeAnalyzedTestConfiguration _conf, String _id, Struct _str, StringMap<BeanInfo> _beansInfos) {
        BeanInfo b_ = new BeanInfo();
        b_.setClassName(_str.getClassName(_conf.getContext()));
        b_.setResolvedClassName(_str.getClassName(_conf.getContext()));
        _beansInfos.addEntry(_id,b_);
        _conf.getConfiguration().getBuiltBeans().addEntry(_id,_str);
    }
    private static void basicStandards(BeanLgNames _lgNames) {
        DefaultInitialization.basicStandards(_lgNames);
    }


    private static void setupNative(String _folder, String _relative, DualConfigurationContext _context) {
        setup(_folder, _relative, _context);
    }

    private static void putBean(Bean _beanTwo, String _key, LgNames _stds, Configuration _configuration) {
        _configuration.getBuiltBeans().put(_key, new BeanStruct(_beanTwo));
//        ((CustBeanLgNames) _stds).beansForTest().put(_key, _beanTwo);
    }

    private static void setupNative(String _folder, String _relative, NativeAnalyzedTestConfiguration _context) {
        setup(_folder, _relative, _context.getDual());
    }

    private static void setupNative2(String _folder, String _relative, NativeAnalyzedTestConfiguration _conf) {
        _conf.getConfiguration().setFirstUrl("page2.html");
        setup(_folder, _relative, _conf.getDual());
    }
    private static void setupVal(String _folder, String _relative, NativeAnalyzedTestConfiguration _conf) {
        _conf.getConfiguration().setFirstUrl("page1.html");
        setup(_folder, _relative, _conf.getDual());
        _conf.getAdv().getValidators().put("rate_val", new MyValidator());
    }

    private static void setSess(Configuration _conf, Navigation _nav) {
        _nav.setSession(_conf);
    }

    private static void setSess(NativeAnalyzedTestConfiguration _conf, Navigation _nav) {
        _nav.setSession(_conf.getConfiguration());
    }

    private static BeanOne getBean(NativeAnalyzedTestConfiguration _conf, String _key) {
        return (BeanOne) ((BeanStruct) _conf.getConfiguration().getBuiltBeans().getVal(_key)).getBean();
//        return (BeanOne) _conf.getAdv().beansForTest().getVal(_key);
    }
//    private static BeanFive getBeanFive(NativeAnalyzedTestConfiguration _conf, String _key) {
//        return (BeanFive)_conf.getAdv().getBeans().getVal(_key);
//    }

    private static BeanTwo getBeanTwo(NativeAnalyzedTestConfiguration _conf, String _key) {
        return (BeanTwo) ((BeanStruct) _conf.getConfiguration().getBuiltBeans().getVal(_key)).getBean();
    }

    private static void putBean(Bean _beanTwo, NativeAnalyzedTestConfiguration _conf, String _key) {
        putBean(_beanTwo, _key, _conf.getAdv(),_conf.getConfiguration());
    }

    private static String getSampleRes(Configuration _conf, RendDocumentBlock _rendDocumentBlock, BeanNatLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
        return getRes(_conf,_rendDocumentBlock, _stds, _ctx, _rendStackCall);
    }

    private static String getSampleRes(NativeAnalyzedTestConfiguration _conf, RendDocumentBlock _rendDocumentBlock) {
        RendStackCall build_ = _conf.build(InitPhase.NOTHING, _conf.getContext());
        return getSampleRes(_conf.getConfiguration(), _rendDocumentBlock, _conf.getAdv(), _conf.getContext(), build_);
    }

    private static void preinit(NativeAnalyzedTestConfiguration _conf) {
        _conf.getAdv().preInitBeans(_conf.getConfiguration());
    }

    private static String getRes(Configuration _conf, RendDocumentBlock _doc, BeanNatLgNames _stds, ContextEl _context, RendStackCall _rendStackCall) {
        return BeanNatCommonLgNames.getRes(_doc, _conf, _stds, _context, _rendStackCall);
    }

    private static Navigation newNavigation(NativeAnalyzedTestConfiguration _conf) {
        Navigation nav_ = new Navigation();
        nav_.setSession(_conf.getConfiguration());

        return nav_;
    }

    private static void setFiles(StringMap<String> _files, NativeAnalyzedTestConfiguration _conf) {
        _conf.getConfiguration().setFiles(_files);
    }

    private static void setup(String _folder, String _relative, DualConfigurationContext _conf) {
        setup(_folder, _conf);
        _conf.getProperties().put("msg_example", _relative);
    }

    private static void setup(String _folder, DualConfigurationContext _conf) {
        _conf.setMessagesFolder(_folder);
        _conf.setProperties(new StringMap<String>());
    }

    private static void setupAna(AnalyzingDoc _analyzingDoc, NatAnalyzedCode _page) {

    }

    private static void form(NativeAnalyzedTestConfiguration _conf, Navigation _nav) {
        _nav.processRendFormRequest((BeanNatLgNames) _conf.getContext().getStandards(), _conf.getContext(), _conf.getStackCall());
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

}
