package fr.apps;

import aiki.db.*;
import aiki.facade.*;
import aiki.game.*;
import aiki.game.player.enums.*;
import aiki.gui.*;
import aiki.main.*;
import aiki.sml.*;
import applications.code.expressionlanguage.adv.*;
import applications.code.gui.*;
import applications.gui.*;
import applications.main.*;
import cards.belote.*;
import cards.belote.enumerations.*;
import cards.belote.sml.*;
import cards.consts.*;
import cards.facade.*;
import cards.gui.dialogs.help.*;
import cards.main.*;
import cards.president.*;
import cards.president.enumerations.*;
import cards.president.sml.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import cards.tarot.sml.*;
import code.converterimages.gui.*;
import code.expressionlanguage.adv.*;
import code.expressionlanguage.gui.unit.*;
import code.expressionlanguage.utilcompo.*;
import code.formathtml.util.*;
import code.gui.*;
import code.gui.files.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.minirts.*;
import code.mock.*;
import code.netw.*;
import code.player.gui.*;
import code.renders.*;
import code.sml.*;
import code.sml.util.*;
import code.stream.*;
import code.stream.core.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class ApplicationsTest extends EquallableApplicationsUtil {
    public static final String TEMP_FOLDER = "/coucou";
    public static final String ARG = "/anti_aerien";
    public static final String LOCALE = "locale";
    public static final String LANGUAGE = "/coucou/langue.xml";
    public static final String EXPORT = "/anti_aerien_2";
    public static final String SAVED_GAME = "/anti_aerien_3";

    @Test
    public void rts1() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        MessagesRts.updateEn(MessagesRts.initAppliTr(p_.lg(StringUtil.EN)));
        FullDocument fullDocument_ = DocumentBuilder.newDocumentBuilder().newDocument();
        fullDocument_.appendChild(fullDocument_.createElement("_"));
        StreamTextFile.saveTextFile(ARG, fullDocument_.export(),p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesRts.APPS_RTS);
        la_.loadLanguage(new String[]{ARG});
        assertEq(2,p_.getFrames().size());
        ((WindowApps)p_.getFrames().get(0)).getButtons().getGroupe().get(0).getActionListeners().get(0).action();
    }
    @Test
    public void rts2() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        FullDocument fullDocument_ = DocumentBuilder.newDocumentBuilder().newDocument();
        fullDocument_.appendChild(fullDocument_.createElement("_"));
        StreamTextFile.saveTextFile(ARG, fullDocument_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_);
        la_.loadLanguage(new String[]{ARG});
        assertEq(0,p_.getFrames().size());
//        ((MockCompoFactory)p_.getCompoFactory()).invoke();
    }

    @Test
    public void rts3() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        MessagesRts.updateEn(MessagesRts.initAppliTr(p_.lg(StringUtil.EN)));
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        FullDocument fullDocument_ = DocumentBuilder.newDocumentBuilder().newDocument();
        fullDocument_.appendChild(fullDocument_.createElement("_"));
        StreamTextFile.saveTextFile(ARG, fullDocument_.export(),p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesRts.APPS_RTS);
        la_.loadLanguage(new String[]{ARG});
        assertEq(2,p_.getFrames().size());
        ((WindowRts)p_.getFrames().get(1)).getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        ((WindowApps)p_.getFrames().get(0)).getButtons().getGroupe().get(0).getActionListeners().get(0).action();
    }
    @Test
    public void noFile1() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(ARG, "",p_.getStreams());
        LaunchingApplications la_ = la(p_);
        la_.loadLanguage(new String[]{ARG});
        assertEq(0,p_.getFrames().size());
//        ((MockCompoFactory)p_.getCompoFactory()).invoke();
    }
    @Test
    public void noFile2() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(ARG, "",p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        p_.setLanguage(EN);
        MessagesApplications.tr(MessagesApplications.initAppliTr(p_.lg(EN)));
        LaunchingApplications la_ = la(p_);
        la_.loadLanguage(new String[]{ARG});
        assertEq(1,p_.getFrames().size());
//        ((MockCompoFactory)p_.getCompoFactory()).invoke();
    }
    @Test
    public void pk() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        p_.setLanguage(EN);
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(p_.getTranslations()));
        MessagesPkGame.enTr(MessagesPkGame.initAppliTr(en_));
        p_.setLanguages(new StringList(EN));
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        DataBase res_ = InitDbValidApp.initDb();
        res_.getMap().initializeLinks();
        res_.getMap().initInteractiveElements();
        res_.getMap().initializeTree();
        res_.getMap().initializeAccessibility();
        FacadeGame f_ = new FacadeGame();
        f_.setData(res_);
        f_.setLanguages(new StringList(EN));
        f_.setSimplyLanguage(EN);
        f_.setSexList(new MockLSexList());
        DefDataBaseStream ins_ = new DefDataBaseStream();
        LoadingGame loadingGame_ = new LoadingGame();
        loadingGame_.setExport(EXPORT);
        ins_.exportRom(p_,f_, loadingGame_);

        Game g_ = new Game(res_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), res_);
        g_.setZippedRom(EXPORT);
        StreamTextFile.saveTextFile(ARG, DocumentWriterAikiCoreUtil.setGame(g_),p_.getStreams());



        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesPkGame.PK);
        la_.loadLanguage(new String[]{ARG});
        ((WindowAiki)p_.getFrames().get(1)).getFacade().setSexList(f_.getSexList());
        ((MockThreadFactory)p_.getThreadFactory()).getAllThreads().get(0).join();
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void pk2() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        p_.setLanguage(EN);
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(p_.getTranslations()));
        MessagesPkGame.enTr(MessagesPkGame.initAppliTr(en_));
        p_.setLanguages(new StringList(EN));
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        DataBase res_ = InitDbValidApp.initDb();
        res_.getMap().initializeLinks();
        res_.getMap().initInteractiveElements();
        res_.getMap().initializeTree();
        res_.getMap().initializeAccessibility();
        FacadeGame f_ = new FacadeGame();
        f_.setData(res_);
        f_.setLanguages(new StringList(EN));
        f_.setSimplyLanguage(EN);
        f_.setSexList(new MockLSexList());
        DefDataBaseStream ins_ = new DefDataBaseStream();
        LoadingGame loadingGame_ = new LoadingGame();
        loadingGame_.setExport(EXPORT);
        loadingGame_.setLastRom(EXPORT);
        loadingGame_.setLastSavedGame(SAVED_GAME);
        ins_.exportRom(p_,f_, loadingGame_);

        Game g_ = new Game(res_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), res_);
        g_.setZippedRom(EXPORT);
        StreamTextFile.saveTextFile(SAVED_GAME, DocumentWriterAikiCoreUtil.setGame(g_),p_.getStreams());

        StreamTextFile.saveTextFile(ARG, DocumentWriterAikiCoreUtil.setLoadingGame(loadingGame_),p_.getStreams());

        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesPkGame.PK);
        la_.loadLanguage(new String[]{ARG});
        ((WindowAiki)p_.getFrames().get(1)).getFacade().setSexList(f_.getSexList());
        ((MockThreadFactory)p_.getThreadFactory()).getAllThreads().get(0).join();
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void rend() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        DefaultBeanAliases.enTr(MessagesRenders.updateEn(FileInfos.enTr(FileInfos.initComments(en_))));
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        StringMap<ContentTime> cont_ = with(p_, with(p_, with(p_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(p_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(p_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(p_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(p_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = p_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,p_.getStreams());
        StreamTextFile.saveTextFile(ARG,"/_.zip\nconf.xml\ninitDb=cl.Init.init\n"+StringUtil.EN, p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, FileInfos.CDM);
        la_.loadLanguage(new String[]{ARG});
        ((MockThreadFactory)p_.getThreadFactory()).getAllThreads().get(0).join();
        assertEq(2,p_.getFrames().size());
    }

    @Test
    public void wu1() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        FileInfos.enTr(MessagesCdmFullGui.updateEn(FileInfos.initComments(en_)));
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        byte[] zipped_ = p_.getZipFact().zipBinFiles(with(p_,  with(p_,with(p_,with(p_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,p_.getStreams());
        StreamTextFile.saveTextFile(ARG,"/_.zip\n"+StringUtil.EN+"\ntabWidth=4", p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, FileInfos.CDM);
        la_.loadLanguage(new String[]{ARG});
        ((MockBaseExecutorService)((WindowUnit)p_.getFrames().last()).getExec()).getTasks().lastValue().attendre();
        assertEq(2,p_.getFrames().size());
    }

    @Test
    public void wu2() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        FileInfos.enTr(MessagesCdmFullGui.updateEn(FileInfos.initComments(en_)));
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        byte[] zipped_ = p_.getZipFact().zipBinFiles(with(p_,  with(p_,with(p_,with(p_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,p_.getStreams());
        StreamTextFile.saveTextFile(ARG,"/_.zip\n"+StringUtil.EN, p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, FileInfos.CDM);
        la_.loadLanguage(new String[]{ARG});
        ((MockBaseExecutorService)((WindowUnit)p_.getFrames().last()).getExec()).getTasks().lastValue().attendre();
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void wf() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        FileInfos.enTr(MessagesCdmFullGui.updateEn(FileInfos.initComments(en_)));
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        byte[] zipped_ = p_.getZipFact().zipBinFiles(with(p_,  with(p_,with(p_,with(p_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,p_.getStreams());
        StreamTextFile.saveTextFile(ARG,"/_.zip\n"+StringUtil.EN+"\nmain=pkg.Sample.m\ncover=\nargs=", p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, FileInfos.CDM);
        la_.loadLanguage(new String[]{ARG});
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void c1() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        MessagesCardGames.enTr(MessagesCardGames.initAppliTr(en_));
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(p_.getTranslations()));
        maxiImgs(p_);
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        RulesBelote rules_ = rulesThreePlayers();
        DealBelote deal_ = dealThreePlayersIa();
//        MockGameBelote mock_ = new MockGameBelote();
//        nextDiscardIa(mock_, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
//        nextSlam(mock_, BoolVal.TRUE);
//        nextCard(mock_, CardBelote.HEART_JACK);
        GameBelote gb_ = edited(deal_, rules_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0, BidBelote.FOLD));
//        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
//        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
//        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
//        gb_.ecarter(mock_);
        StreamTextFile.saveTextFile(ARG, DocumentWriterBeloteUtil.setGameBelote(gb_), p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesCardGames.CARDS);
        la_.loadLanguage(new String[]{ARG});
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void c2() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        MessagesCardGames.enTr(MessagesCardGames.initAppliTr(en_));
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(p_.getTranslations()));
        maxiImgs(p_);
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        RulesPresident r_ = new RulesPresident(4);
        r_.getCommon().setNbDeals(1);
        DealPresident deal_ = mix(3);
        GamePresident gp_ = edited(deal_, r_);
        StreamTextFile.saveTextFile(ARG, DocumentWriterPresidentUtil.setGamePresident(gp_), p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesCardGames.CARDS);
        la_.loadLanguage(new String[]{ARG});
        tryAn((MockThreadFactory) p_.getThreadFactory());
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void c3() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        MessagesCardGames.enTr(MessagesCardGames.initAppliTr(en_));
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(p_.getTranslations()));
        maxiImgs(p_);
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        RulesTarot rules_ = rules();
        DealTarot deal_ = deal1(4);
//        MockGameBelote mock_ = new MockGameBelote();
//        nextDiscardIa(mock_, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8);
//        nextSlam(mock_, BoolVal.TRUE);
//        nextCard(mock_, CardBelote.HEART_JACK);
        GameTarot gb_ = edited(deal_, rules_);
//        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0, BidBelote.FOLD));
//        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
//        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
//        gb_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
//        gb_.ecarter(mock_);
        StreamTextFile.saveTextFile(ARG, DocumentWriterTarotUtil.setGameTarot(gb_), p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesCardGames.CARDS);
        la_.loadLanguage(new String[]{ARG});
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void imgs() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        MessagesConverter.updateEn(MessagesConverter.initAppliTr(en_));
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        updateBase(p_.currentLg());
        p_.getFileCoreStream().newFile("/from/one").mkdirs();
        p_.getFileCoreStream().newFile("/from/one/sub1").mkdirs();
        p_.getFileCoreStream().newFile("/from/one/sub2").mkdirs();
        p_.getFileCoreStream().newFile("/from/two").mkdirs();
        p_.getFileCoreStream().newFile("/from/two/sub1").mkdirs();
        p_.getFileCoreStream().newFile("/from/two/sub2").mkdirs();
        StreamBinaryFile.writeFile("/from/one/sub1/1",new byte[0],p_.getStreams());
        StreamBinaryFile.writeFile("/from/one/sub1/2",toBinary(new int[][]{new int[]{1,2},new int[]{3,4}}),p_.getStreams());
        StreamBinaryFile.writeFile("/from/one/sub2/1",new byte[0],p_.getStreams());
        StreamBinaryFile.writeFile("/from/one/sub2/2",toBinary(new int[][]{new int[]{1,2},new int[]{3,4},new int[]{5,6}}),p_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub1/1",new byte[0],p_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub1/2",toBinary(new int[][]{new int[]{1,2,3},new int[]{4,5,6}}),p_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub2/1",new byte[0],p_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub2/2",toBinary(new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,9}}),p_.getStreams());
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element eltExp_ = d_.createElement(DocumentImagesUtil.ROOT_CONF);
        eltExp_.setAttribute(DocumentImagesUtil.INFO_IMP,"/from");
        eltExp_.setAttribute(DocumentImagesUtil.INFO_EXP,"/to");
        d_.appendChild(eltExp_);
        StreamTextFile.saveTextFile(ARG,d_.export(),p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesConverter.APPS_CONVERTER);
        la_.loadLanguage(new String[]{ARG});
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void sound() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        MessagesSongs.updateEn(MessagesSongs.initAppliTr(p_.lg(EN)));
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(WindowPlayer.CST_SRC,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(WindowPlayer.CST_SRC,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile(ARG, StringUtil.encode(d_.export()),p_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'e', 1),p_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesSongs.SONGS_APP);
        la_.loadLanguage(new String[]{ARG});
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void rec() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        MessagesSongs.updateEn(MessagesSongs.initAppliTr(p_.lg(EN)));
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(WindowPlayer.CST_SRC,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(WindowPlayer.CST_SRC,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile(ARG, StringUtil.encode(d_.export()),p_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'e', 1),p_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        AbsButton main_ = p_.getCompoFactory().newPlainButton();
        LaunchingApplications la_ = la(p_, MessagesSongs.SONGS_APP);
        la_.loadLanguage(new String[]{});
        RecordEvent.rec((WindowApps) p_.getFrames().get(0), main_, new LanguagesButtonsPair(null,main_,null)).action();
        assertEq(2,p_.getFrames().size());
        p_.getFrames().get(0).getCommonFrame().getWindowListenersDef().get(0).windowClosing();
    }
    @Test
    public void ide() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        TranslationsLg en_ = p_.lg(EN);
        FileInfos.enTr(MessagesIde.updateEn(FileInfos.initComments(en_)));
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(WindowPlayer.CST_SRC,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(WindowPlayer.CST_SRC,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile(ARG, StringUtil.encode(d_.export()),p_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'e', 1),p_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        AbsButton main_ = p_.getCompoFactory().newPlainButton();
        LaunchingApplications la_ = la(p_, FileInfos.CDM);
        la_.loadLanguage(new String[]{});
        new LaunchingCdmEditor(la_.getFactories()).launch("",main_,new LanguagesButtonsPair(null,main_,null));
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void rtsBut() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        MessagesRts.updateEn(MessagesRts.initAppliTr(p_.lg(StringUtil.EN)));
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        FullDocument fullDocument_ = DocumentBuilder.newDocumentBuilder().newDocument();
        fullDocument_.appendChild(fullDocument_.createElement("_"));
        StreamTextFile.saveTextFile(ARG, fullDocument_.export(),p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesRts.APPS_RTS);
        la_.loadLanguage(new String[]{});
        ((WindowApps)p_.getFrames().get(0)).getButtonDemo().getActionListeners().get(0).action();
        p_.getFrames().get(1).getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        ((WindowApps)p_.getFrames().get(0)).getButtonDemo().getActionListeners().get(0).action();
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void pkBut() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        p_.setLanguage(EN);
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(p_.getTranslations()));
        MessagesPkGame.enTr(MessagesPkGame.initAppliTr(en_));
        p_.setLanguages(new StringList(EN));
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        DataBase res_ = InitDbValidApp.initDb();
        res_.getMap().initializeLinks();
        res_.getMap().initInteractiveElements();
        res_.getMap().initializeTree();
        res_.getMap().initializeAccessibility();
        FacadeGame f_ = new FacadeGame();
        f_.setData(res_);
        f_.setLanguages(new StringList(EN));
        f_.setSimplyLanguage(EN);
        f_.setSexList(new MockLSexList());
        DefDataBaseStream ins_ = new DefDataBaseStream();
        LoadingGame loadingGame_ = new LoadingGame();
        loadingGame_.setExport(EXPORT);
        ins_.exportRom(p_,f_, loadingGame_);

        Game g_ = new Game(res_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), res_);
        g_.setZippedRom(EXPORT);
        StreamTextFile.saveTextFile(ARG, DocumentWriterAikiCoreUtil.setGame(g_),p_.getStreams());



        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesPkGame.PK);
        la_.loadLanguage(new String[]{});
        ((WindowApps)p_.getFrames().get(0)).getButtonPokemon().getActionListeners().get(0).action();
        ((WindowAiki)p_.getFrames().get(1)).getFacade().setSexList(f_.getSexList());
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void rendBut() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        DefaultBeanAliases.enTr(MessagesRenders.updateEn(FileInfos.enTr(FileInfos.initComments(en_))));
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        String xmlConf_ = "<cfg>\n" +
                "\t<java.lang.String field='firstUrl' value='page.html'/>\n" +
                "\t<java.lang.String field='prefix' value='c'/>\n" +
                "\t<sm field='navigation'>\n" +
                "\t\t<java.lang.String key='' value='bean_one.method'/>\n" +
                "\t\t<sm>\n" +
                "\t\t\t<java.lang.String key='' value='res'/>\n" +
                "\t\t\t<java.lang.String value='page2.html'/>\n" +
                "\t\t</sm>\n" +
                "\t</sm>\n" +
                "\t<n field=\"context\">\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<sm field='styleValues' value='StyleValueRgb=___'/>\n" +
                "\t</n>\n" +
                "\t<java.lang.Integer field='tabWidth' value='4'/>\n" +
                "\t<java.lang.String field='messagesFolder' value='messages'/>\n" +
                "\t<java.lang.String field='filesConfName' value='conf_cl.txt'/>\n" +
                "\t<sm field='beans'>\n" +
                "\t\t<java.lang.String key='' value='bean_one'/>\n" +
                "\t\t<b>\n" +
                "\t\t\t<java.lang.String field='scope' value='session'/>\n" +
                "\t\t\t<java.lang.String field='className' value='pkg.BeanOne'/>\n" +
                "\t\t</b>\n" +
                "\t</sm>\n" +
                "\t<sm field='properties'>\n" +
                "\t\t<java.lang.String key='' value='msg_cust'/>\n" +
                "\t\t<java.lang.String value='sample/file'/>\n" +
                "\t</sm>\n" +
                "\t<sl field='addedFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sl field='renderFiles'>\n" +
                "\t\t<str value='page.html'/>\n" +
                "\t</sl>\n" +
                "\t<sm field='lateValidators'>\n" +
                "\t\t<str key='' value='my_val'/>\n" +
                "\t\t<str value='pkg.MyVal'/>\n" +
                "\t</sm>\n" +
                "</cfg>";
        StringMap<ContentTime> cont_ = with(p_, with(p_, with(p_, init(), "conf.txt", "content"), "src/"), "src/folder/");
        StringMap<ContentTime> contConf_ = with(p_,cont_,"conf_cl.txt","src/folder/file.txt");
        StringMap<ContentTime> contRend_ = with(p_,contConf_,"page.html","<html><body>_</body></html>");
        StringMap<ContentTime> content_ = with(p_, contRend_, "src/folder/file.txt", "public class cl.Init{public static Object init(String[] names, String[] contents){return new String[0];}}");
        StringMap<ContentTime> contents_ = with(p_, content_, "conf.xml", xmlConf_);
        byte[] zipped_ = p_.getZipFact().zipBinFiles(contents_);
        StreamBinaryFile.writeFile("/_.zip",zipped_,p_.getStreams());
        StreamTextFile.saveTextFile(ARG,"/_.zip\nconf.xml\ninitDb=cl.Init.init\n"+StringUtil.EN, p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, FileInfos.CDM);
        la_.loadLanguage(new String[]{});
        ((WindowApps)p_.getFrames().get(0)).getButtonRenders().getActionListeners().get(0).action();
        assertEq(2,p_.getFrames().size());
    }

    @Test
    public void wuBut() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        FileInfos.enTr(MessagesCdmFullGui.updateEn(FileInfos.initComments(en_)));
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        byte[] zipped_ = p_.getZipFact().zipBinFiles(with(p_,  with(p_,with(p_,with(p_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,p_.getStreams());
        StreamTextFile.saveTextFile(ARG,"/_.zip\n"+StringUtil.EN+"\ntabWidth=4", p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, FileInfos.CDM);
        la_.loadLanguage(new String[]{});
        ((WindowApps)p_.getFrames().get(0)).getButtonTests().getActionListeners().get(0).action();
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void wfBut() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        FileInfos.enTr(MessagesCdmFullGui.updateEn(FileInfos.initComments(en_)));
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        byte[] zipped_ = p_.getZipFact().zipBinFiles(with(p_,  with(p_,with(p_,with(p_, init(), "conf.txt", "content"),"src/"),"src/folder/"),"src/folder/file.txt","public class pkg.Sample{public static void m(){}@Test public void err(){Assert.assert(0,1);}@Test public void success(){Assert.assert(1,1);}}"));
        StreamBinaryFile.writeFile("/_.zip",zipped_,p_.getStreams());
        StreamTextFile.saveTextFile(ARG,"/_.zip\n"+StringUtil.EN+"\nmain=pkg.Sample.m\ncover=\nargs=", p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, FileInfos.CDM);
        la_.loadLanguage(new String[]{});
        ((WindowApps)p_.getFrames().get(0)).getButtonApps().getActionListeners().get(0).action();
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void cBut() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        MessagesCardGames.enTr(MessagesCardGames.initAppliTr(en_));
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(p_.getTranslations()));
        maxiImgs(p_);
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesCardGames.CARDS);
        la_.loadLanguage(new String[]{});
        ((WindowApps)p_.getFrames().get(0)).getButtonCards().getActionListeners().get(0).action();
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void imgsBut() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        MessagesConverter.updateEn(MessagesConverter.initAppliTr(en_));
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        updateBase(p_.currentLg());
        p_.getFileCoreStream().newFile("/from/one").mkdirs();
        p_.getFileCoreStream().newFile("/from/one/sub1").mkdirs();
        p_.getFileCoreStream().newFile("/from/one/sub2").mkdirs();
        p_.getFileCoreStream().newFile("/from/two").mkdirs();
        p_.getFileCoreStream().newFile("/from/two/sub1").mkdirs();
        p_.getFileCoreStream().newFile("/from/two/sub2").mkdirs();
        StreamBinaryFile.writeFile("/from/one/sub1/1",new byte[0],p_.getStreams());
        StreamBinaryFile.writeFile("/from/one/sub1/2",toBinary(new int[][]{new int[]{1,2},new int[]{3,4}}),p_.getStreams());
        StreamBinaryFile.writeFile("/from/one/sub2/1",new byte[0],p_.getStreams());
        StreamBinaryFile.writeFile("/from/one/sub2/2",toBinary(new int[][]{new int[]{1,2},new int[]{3,4},new int[]{5,6}}),p_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub1/1",new byte[0],p_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub1/2",toBinary(new int[][]{new int[]{1,2,3},new int[]{4,5,6}}),p_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub2/1",new byte[0],p_.getStreams());
        StreamBinaryFile.writeFile("/from/two/sub2/2",toBinary(new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,9}}),p_.getStreams());
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element eltExp_ = d_.createElement(DocumentImagesUtil.ROOT_CONF);
        eltExp_.setAttribute(DocumentImagesUtil.INFO_IMP,"/from");
        eltExp_.setAttribute(DocumentImagesUtil.INFO_EXP,"/to");
        d_.appendChild(eltExp_);
        StreamTextFile.saveTextFile(ARG,d_.export(),p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesConverter.APPS_CONVERTER);
        la_.loadLanguage(new String[]{});
        ((WindowApps)p_.getFrames().get(0)).getButtonConverter().getActionListeners().get(0).action();
        assertEq(2,p_.getFrames().size());
    }
    @Test
    public void soundBut() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        MessagesSongs.updateEn(MessagesSongs.initAppliTr(p_.lg(EN)));
        FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element r_ = d_.createElement(WindowPlayer.SMIL);
        Element s1_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s1_.setAttribute(WindowPlayer.CST_SRC,"file1");
        r_.appendChild(s1_);
        Element s2_ = d_.createElement(WindowPlayer.CST_MEDIA);
        s2_.setAttribute(WindowPlayer.CST_SRC,"file2");
        r_.appendChild(s2_);
        d_.appendChild(r_);
        StreamBinaryFile.writeFile(ARG, StringUtil.encode(d_.export()),p_.getStreams());
        StreamBinaryFile.writeFile("file1",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'e', 1),p_.getStreams());
        StreamBinaryFile.writeFile("file2",wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 2),p_.getStreams());
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesSongs.SONGS_APP);
        la_.loadLanguage(new String[]{});
        ((WindowApps)p_.getFrames().get(0)).getButtonPlayer().getActionListeners().get(0).action();
        assertEq(2,p_.getFrames().size());

    }
    @Test
    public void netBut() {
        MockProgramInfos p_ = build();
        TranslationsLg en_ = p_.lg(EN);
        MessagesCardGames.enTr(MessagesCardGames.initAppliTr(en_));
        MessagesPkGame.enTr(MessagesPkGame.initAppliTr(en_));
        MessagesNetWork.enTr(MessagesNetWork.initAppliTr(en_));
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        updateBase(p_.currentLg());
        p_.setLanguages(new StringList(EN));
        p_.setLanguage(EN);
        FullDocument lg_ = DocumentBuilder.newDocumentBuilder().newDocument();
        Element elt_ = lg_.createElement("_");
        Element locale_ = lg_.createElement(LOCALE);
        locale_.setAttribute(LOCALE, EN);
        elt_.appendChild(locale_);
        lg_.appendChild(elt_);
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        StreamTextFile.saveTextFile(LANGUAGE, lg_.export(),p_.getStreams());
        MessagesApplications.tr(MessagesApplications.initAppliTr(p_.currentLg()));
        MessagesCardGames.sys(MessagesCardGames.initAppliFilesTr(p_.getTranslations()));
        LaunchingApplications la_ = la(p_);
        la_.loadLanguage(new String[]{});
        ((WindowApps)p_.getFrames().get(0)).getButtonNet().getActionListeners().get(0).action();
        assertEq(2,p_.getFrames().size());

    }
    @Test
    public void rtsLg1() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        MessagesRts.updateEn(MessagesRts.initAppliTr(p_.lg(StringUtil.EN)));
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        FullDocument fullDocument_ = DocumentBuilder.newDocumentBuilder().newDocument();
        fullDocument_.appendChild(fullDocument_.createElement("_"));
        StreamTextFile.saveTextFile(ARG, fullDocument_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_, MessagesRts.APPS_RTS);
        la_.loadLanguage(new String[]{ARG});
        la_.getLanguageFrame().changeLanguage(EN);
        assertEq(2,p_.getFrames().size());
        la_.getLanguageFrame().getLanguage();
//        ((MockCompoFactory)p_.getCompoFactory()).invoke();
    }
    @Test
    public void rtsLg2() {
        MockProgramInfos p_ = build();
        p_.setLanguages(new StringList(EN));
        p_.getFileCoreStream().newFile(TEMP_FOLDER).mkdirs();
        FullDocument fullDocument_ = DocumentBuilder.newDocumentBuilder().newDocument();
        fullDocument_.appendChild(fullDocument_.createElement("_"));
        StreamTextFile.saveTextFile(ARG, fullDocument_.export(),p_.getStreams());
        LaunchingApplications la_ = la(p_);
        la_.loadLanguage(new String[]{ARG});
        la_.getLanguageFrame().getCommonFrame().getWindowListenersDef().get(0).windowClosing();
        assertEq(0,p_.getFrames().size());
//        ((MockCompoFactory)p_.getCompoFactory()).invoke();
    }
    public static byte[] wrapInts(int... _files) {
        return Ints.newList(_files).toArrByte();
    }
    private static byte[] toBinary(int[][] _img) {
        AbstractImage img_ =  new MockImage(_img);
        Bytes bs_ = Bytes.newList((byte)0x89,(byte)0x50,(byte)0x4E,(byte)0x47,(byte)0x0D,(byte)0x0A,(byte)0x1A,(byte)0x0A);
        bs_.addAllElts(Bytes.newList(img_.writeImg("")));
        return bs_.toArrByte();
    }
    private static void maxiImgs(MockProgramInfos _pr) {
        StringMap<int[][]> mini_ = def();
        _pr.getTranslations().getMapping().getVal(EN).getMaxiCards().addAllEntries(mini_);
        _pr.getTranslations().getMapping().getVal(EN).getMiniCardsDef().addAllEntries(mini_);
//        _pr.getTranslations().getMapping().getVal(FR).getMaxiCards().addAllEntries(mini_);
//        _pr.getTranslations().getMapping().getVal(FR).getMiniCardsDef().addAllEntries(mini_);
    }

    public static StringMap<int[][]> def(){
        StringMap<int[][]> out_ = new StringMap<int[][]>(new CollCapacity(78));
        defTrs(out_);
        out_.addEntry(""+ CouleurValeur.HEART_KING,new int[1][1]);
        out_.addEntry(""+CouleurValeur.HEART_QUEEN,new int[1][1]);
        out_.addEntry(""+CouleurValeur.HEART_KNIGHT,new int[1][1]);
        out_.addEntry(""+CouleurValeur.HEART_JACK,new int[1][1]);
        defHeart(out_);
        out_.addEntry(""+CouleurValeur.SPADE_KING,new int[1][1]);
        out_.addEntry(""+CouleurValeur.SPADE_QUEEN,new int[1][1]);
        out_.addEntry(""+CouleurValeur.SPADE_KNIGHT,new int[1][1]);
        out_.addEntry(""+CouleurValeur.SPADE_JACK,new int[1][1]);
        defSpade(out_);
        out_.addEntry(""+CouleurValeur.DIAMOND_KING,new int[1][1]);
        out_.addEntry(""+CouleurValeur.DIAMOND_QUEEN,new int[1][1]);
        out_.addEntry(""+CouleurValeur.DIAMOND_KNIGHT,new int[1][1]);
        out_.addEntry(""+CouleurValeur.DIAMOND_JACK,new int[1][1]);
        defDiamond(out_);
        out_.addEntry(""+CouleurValeur.CLUB_KING,new int[1][1]);
        out_.addEntry(""+CouleurValeur.CLUB_QUEEN,new int[1][1]);
        out_.addEntry(""+CouleurValeur.CLUB_KNIGHT,new int[1][1]);
        out_.addEntry(""+CouleurValeur.CLUB_JACK,new int[1][1]);
        defClub(out_);
        return out_;
    }

    private static void defTrs(StringMap<int[][]> _out) {
        _out.addEntry(""+CouleurValeur.EXCUSE,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_21,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_20,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_19,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_18,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_17,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_16,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_15,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_14,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_13,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_12,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_11,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_10,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_9,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_8,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_7,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_6,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_5,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_4,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_3,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_2,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_1,new int[1][1]);
    }

    private static void defHeart(StringMap<int[][]> _out) {
        _out.addEntry(""+CouleurValeur.HEART_10,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_9,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_8,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_7,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_6,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_5,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_4,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_3,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_2,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_1,new int[1][1]);
    }

    private static void defSpade(StringMap<int[][]> _out) {
        _out.addEntry(""+CouleurValeur.SPADE_10,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_9,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_8,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_7,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_6,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_5,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_4,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_3,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_2,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_1,new int[1][1]);
    }

    private static void defDiamond(StringMap<int[][]> _out) {
        _out.addEntry(""+CouleurValeur.DIAMOND_10,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_9,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_8,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_7,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_6,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_5,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_4,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_3,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_2,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_1,new int[1][1]);
    }

    private static void defClub(StringMap<int[][]> _out) {
        _out.addEntry(""+CouleurValeur.CLUB_10,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_9,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_8,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_7,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_6,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_5,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_4,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_3,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_2,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_1,new int[1][1]);
    }
    private GameBelote edited(DealBelote _deal, RulesBelote _rules) {
        GameBelote g_ = new GameBelote(GameType.EDIT, _deal, _rules);
        g_.setNombre();
        return g_;
    }
    private GamePresident edited(DealPresident _deal, RulesPresident _rules) {
        GamePresident g_ = new GamePresident(GameType.EDIT, _deal, _rules,Bytes.newList());
        g_.setNombre();
        return g_;
    }

    private GameTarot edited(DealTarot _deal, RulesTarot _rules) {
        GameTarot g_ = new GameTarot(GameType.EDIT, _deal, _rules);
        g_.setNombre();
        return g_;
    }
    private BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
    }
//    private void nextBid(MockGameBelote _m, BidBeloteSuit _bid) {
//        _m.getBids().add(_bid);
//        //        return _sort.getNextPlayer((byte) _pl);
//    }
//
//    private void nextSlam(MockGameBelote _m, BoolVal _bid) {
//        _m.getSlams().add(_bid);
//    }
//    private void nextDiscard(MockGameBelote _m, CardBelote _bid) {
//        _m.getDiscard().add(_bid);
//    }
//    private void nextDiscardIa(MockGameBelote _m, CardBelote... _bid) {
//        _m.getDiscardIa().add(create(_bid));
//    }
//
//    private void nextCard(MockGameBelote _m, CardBelote _bid) {
//        _m.getCards().add(_bid);
//        //        return _sort.getNextPlayer((byte) _pl);
//    }

    private DealPresident mix(int _d) {
        return new DealPresident(deal1(), (byte) _d);
    }
    private RulesBelote rulesThreePlayers() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }

    private DealBelote dealThreePlayersIa() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.CLUB_10,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9));
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        return db_;
    }

    static CustList<HandPresident> deal1() {
        CustList<HandPresident> hs_ = new CustList<HandPresident>();
        HandPresident h_;
        h_ = new HandPresident();
        h_.ajouter(CardPresident.CLUB_3);
        h_.ajouter(CardPresident.CLUB_4);
        h_.ajouter(CardPresident.DIAMOND_4);
        h_.ajouter(CardPresident.SPADE_7);
        h_.ajouter(CardPresident.DIAMOND_8);
        h_.ajouter(CardPresident.HEART_8);
        h_.ajouter(CardPresident.CLUB_9);
        h_.ajouter(CardPresident.SPADE_10);
        h_.ajouter(CardPresident.CLUB_JACK);
        h_.ajouter(CardPresident.SPADE_JACK);
        h_.ajouter(CardPresident.SPADE_KING);
        h_.ajouter(CardPresident.DIAMOND_1);
        h_.ajouter(CardPresident.HEART_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.SPADE_3);
        h_.ajouter(CardPresident.SPADE_4);
        h_.ajouter(CardPresident.HEART_4);
        h_.ajouter(CardPresident.CLUB_7);
        h_.ajouter(CardPresident.SPADE_8);
        h_.ajouter(CardPresident.CLUB_8);
        h_.ajouter(CardPresident.SPADE_9);
        h_.ajouter(CardPresident.CLUB_10);
        h_.ajouter(CardPresident.HEART_JACK);
        h_.ajouter(CardPresident.DIAMOND_JACK);
        h_.ajouter(CardPresident.CLUB_KING);
        h_.ajouter(CardPresident.HEART_1);
        h_.ajouter(CardPresident.DIAMOND_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.HEART_3);
        h_.ajouter(CardPresident.SPADE_5);
        h_.ajouter(CardPresident.HEART_5);
        h_.ajouter(CardPresident.SPADE_6);
        h_.ajouter(CardPresident.CLUB_6);
        h_.ajouter(CardPresident.HEART_7);
        h_.ajouter(CardPresident.HEART_9);
        h_.ajouter(CardPresident.HEART_10);
        h_.ajouter(CardPresident.SPADE_QUEEN);
        h_.ajouter(CardPresident.CLUB_QUEEN);
        h_.ajouter(CardPresident.HEART_KING);
        h_.ajouter(CardPresident.SPADE_2);
        h_.ajouter(CardPresident.CLUB_2);
        hs_.add(h_);
        h_ = new HandPresident();
        h_.ajouter(CardPresident.DIAMOND_3);
        h_.ajouter(CardPresident.CLUB_5);
        h_.ajouter(CardPresident.DIAMOND_5);
        h_.ajouter(CardPresident.HEART_6);
        h_.ajouter(CardPresident.DIAMOND_6);
        h_.ajouter(CardPresident.DIAMOND_7);
        h_.ajouter(CardPresident.DIAMOND_9);
        h_.ajouter(CardPresident.DIAMOND_10);
        h_.ajouter(CardPresident.HEART_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_QUEEN);
        h_.ajouter(CardPresident.DIAMOND_KING);
        h_.ajouter(CardPresident.SPADE_1);
        h_.ajouter(CardPresident.CLUB_1);
        hs_.add(h_);
        return hs_;
    }

    private static DealTarot deal1(int _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hands_.add(hand_);
        return new DealTarot(hands_, (byte) _dealer);
    }
    private RulesTarot rules() {
        RulesTarot rules_ = new RulesTarot();
        rules_.getCommon().setNbDeals(1);
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        return rules_;
    }
    private HandBelote create(CardBelote... _cb) {
        return HandBelote.create(_cb);
    }
    public static AbstractThread tryAn(MockThreadFactory _g) {
//        assertEq(1, _g.getAllThreads().size());
        AbstractThread th_ = _g.getAllThreads().get(0);
        _g.getAllThreads().remove(0);
        th_.join();
//        checkNoAnim(_g);
        return th_;
    }

    public static StringMap<ContentTime> init() {
        return new StringMap<ContentTime>();
    }
    public static StringMap<ContentTime> with(AbstractLightProgramInfos _light, StringMap<ContentTime> _all, String _name, String _content) {
        _all.put(_name,new ContentTime(StringUtil.encode(_content),_light.getThreadFactory().millis()));
        return _all;
    }
    public static StringMap<ContentTime> with(AbstractLightProgramInfos _light, StringMap<ContentTime> _all, String _name) {
        _all.put(_name,new ContentTime(null,_light.getThreadFactory().millis()));
        return _all;
    }
    public static void updateBase(TranslationsLg _en) {
        StringMap<TranslationsFile> en_ = MessagesGuiFct.initAppliTr(_en).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_DIAL, MessagesFileDialog.en());
        en_.addEntry(MessagesGuiFct.CONFIRM, MessagesConfirmDialog.en());
        en_.addEntry(MessagesGuiFct.FOLDER_OPEN_DIAL, MessagesFolderOpenDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_TAB,MessagesFileTable.en());
    }

    private LaunchingApplications la(MockProgramInfos _p, String _app) {
        TranslationsAppli tmpFold_ = new TranslationsAppli();
        TranslationsFile tf_ = new TranslationsFile();
        tf_.add(Translations.TEMP_FOLDER,TEMP_FOLDER);
        tmpFold_.getMapping().addEntry(Translations.FILES_PATH, tf_);
        _p.getTranslations().getFiles().addEntry(_app, tmpFold_);
        MessagesApplications.tr(MessagesApplications.initAppliTr(_p.currentLg()));
        return la(_p);
    }

    private LaunchingApplications la(MockProgramInfos _p) {
        TranslationsAppli tmpFold_ = MessagesApplications.initAppliFilesTr(_p.getTranslations());
        MessagesApplications.sys(tmpFold_);
        return new LaunchingApplications(new WithAppFactories(_p, new AppFactories(new AikiFactory(_p, new MockBaseExecutorServiceParam<AikiNatLgNamesNavigation>(), new MockBaseExecutorServiceParam<DataBase>()), new CardFactories(_p, new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(), new MockBaseExecutorServiceParam<HelpIndexesTree>()), new CdmFactory(_p, new MockInterceptor()), TEMP_FOLDER)));
    }

}
