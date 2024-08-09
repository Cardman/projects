package cards.gui.animations;

import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.HelpIndexes;
import cards.gui.dialogs.help.HelpIndexesTree;
import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.analyze.NatConfigurationCore;
import code.gui.EnabledMenu;
import code.gui.MenuItemUtils;
import code.scripts.confs.HelpScriptConfPages;
import code.scripts.confs.HelpScriptPages;
import code.scripts.confs.HelpScriptPagesImgs;
import code.scripts.pages.cards.HelpCards;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.stream.StreamTextFile;
import code.threads.IntCallable;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class HelpInitializer implements IntCallable<HelpIndexesTree> {

    private static final String POSITION = "position";

    private static final String TEXTE = "texte";

    private final EnabledMenu generalHelp;

    public HelpInitializer(EnabledMenu _generalHelp) {
        generalHelp = _generalHelp;
    }
    @Override
    public HelpIndexesTree call() {
//        HelpIndexesTree trees_ = new HelpIndexesTree();
        StringMap<NatConfigurationCore> cf_ = HelpScriptPages.cf();
        StringMap<NatDualConfigurationContext> ct_ = HelpScriptPagesImgs.ct();
        StringMap<Document> built_ = HelpCards.build();
//        StringMap<StringMap<String>> builtMs_ = HelpCards.ms();
//        NavigationCore.adjustMap(builtMs_);
        HelpIndexesTree tree_ = new HelpIndexesTree();
//        for (EntryCust<String, TranslationsLg> l: programInfos.getTranslations().getMapping().entryList()) {
//            HelpIndexesTree tree_ = new HelpIndexesTree();
            Document doc_ = HelpScriptConfPages.info();
            Element element_ = doc_.getDocumentElement();
            CustList<Node> noeudsActuels_ = new CustList<Node>();
            noeudsActuels_.add(element_);
            StringList cheminsActuels_ = new StringList();
            String concat_ = element_.getTagName();
//            String concat_ = StringUtil.concat(FileConst.RESOURCES_HELP, StreamTextFile.SEPARATEUR,
//                    element_.getTagName());
            cheminsActuels_.add(concat_);
            HelpIndexes indices_ = new HelpIndexes();
            indices_.add(NumberUtil.parseInt(element_.getAttribute(POSITION)));
            CustList<HelpIndexes> cheminsNumeriquesActuels_ = new CustList<HelpIndexes>();
            cheminsNumeriquesActuels_.add(indices_);
            ElementHelp elementRacine_ = new ElementHelp(element_
                    .getAttribute(TEXTE),concat_, cf_, ct_,built_);
//            StringMap<String> un_ = builtMs_.getVal(l.getKey());
//            StringMap<int[][]> imgs_ = l.getValue().getMaxiCards();
//            PreparedRenderPagesCards prep_ = new PreparedRenderPagesCards(un_, cf_.getVal(concat_), ct_.getVal(concat_), imgs_, built_.getVal(concat_));
//            prep_.run();
//            elementRacine_.setMetaDocument(prep_.getMetaDocument());
//            elementRacine_.setNavigation(prep_.getNavigation());
            tree_.put(indices_, elementRacine_);
            while (true) {
                CustList<Node> nouveauxElements_ = new CustList<Node>();
                StringList nouveauxChemins_ = new StringList();
                CustList<HelpIndexes> nouveauxCheminsNum_ = new CustList<HelpIndexes>();
                int j_ = IndexConstants.FIRST_INDEX;
                for (Node e : noeudsActuels_) {
                    String cheminCourant_ = cheminsActuels_.get(j_);
                    HelpIndexes cheminNumCourant_ = cheminsNumeriquesActuels_
                            .get(j_);
                    for (Element e2_ : e.getChildElements()) {
                        String concat2_ = StringUtil.concat(cheminCourant_, StreamTextFile.SEPARATEUR,
                                e2_.getTagName());
                        ElementHelp noeud_ = new ElementHelp(e2_
                                .getAttribute(TEXTE),concat2_, cf_, ct_,built_);
                        nouveauxChemins_.add(concat2_);
                        nouveauxElements_.add(e2_);
                        HelpIndexes cheminNumCourantBis_ = new HelpIndexes(
                                cheminNumCourant_);
                        cheminNumCourantBis_.add(NumberUtil
                                .parseInt(e2_.getAttribute(
                                        POSITION)));
                        nouveauxCheminsNum_.add(cheminNumCourantBis_);
//                            prep_ = new PreparedRenderPagesCards(un_, cf_.getVal(concat2_), ct_.getVal(concat2_), imgs_, built_.getVal(concat2_));
//                            prep_.run();
//                            noeud_.setMetaDocument(prep_.getMetaDocument());
//                            noeud_.setNavigation(prep_.getNavigation());
                        tree_.put(nouveauxCheminsNum_.last(),
                                noeud_);
                    }
                    j_++;
                }
                if (nouveauxElements_.isEmpty()) {
                    break;
                }
                noeudsActuels_ = nouveauxElements_;
                cheminsActuels_ = nouveauxChemins_;
                cheminsNumeriquesActuels_ = nouveauxCheminsNum_;
            }
//            trees_.addEntry(l.getKey(),tree_);
//        }
        MenuItemUtils.setEnabledMenu(generalHelp,true);
        return tree_;
    }

}
