package cards.gui.animations;

import cards.gui.dialogs.FileConst;
import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.HelpIndexes;
import code.gui.AbsMenuItem;
import code.scripts.confs.HelpScriptConfPages;
import code.scripts.confs.HelpScriptPages;
import code.scripts.imgs.cards.CardsInit;
import code.scripts.pages.cards.HelpCards;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class HelpInitializer implements Runnable {

    private static final String POSITION = "position";

    private static final String TEXTE = "texte";

    private final StringMap<ObjectMap<HelpIndexes,ElementHelp>> trees = new StringMap<ObjectMap<HelpIndexes, ElementHelp>>();
    private final AbsMenuItem generalHelp;
    public HelpInitializer(AbsMenuItem _generalHelp) {
        generalHelp = _generalHelp;
    }
    @Override
    public void run() {
        StringMap<Document> docs_ = HelpScriptPages.docs();
        StringMap<Document> built_ = HelpCards.build();
        StringMap<String> ms_ = CardsInit.ms();
        for (String l:Constants.getAvailableLanguages()) {
            ObjectMap<HelpIndexes,ElementHelp> tree_ = new ObjectMap<HelpIndexes,ElementHelp>();
            Document doc_ = HelpScriptConfPages.infoLg().getVal(l);
            Element element_ = doc_.getDocumentElement();
            CustList<Node> noeudsActuels_ = new CustList<Node>();
            noeudsActuels_.add(element_);
            StringList cheminsActuels_ = new StringList();
            cheminsActuels_.add(StringUtil.concat(FileConst.RESOURCES_HELP,StreamTextFile.SEPARATEUR,l,StreamTextFile.SEPARATEUR,element_.getTagName()));
            HelpIndexes indices_ = new HelpIndexes();
            indices_.add(NumberUtil.parseInt(element_.getAttribute(POSITION)));
            CustList<HelpIndexes> cheminsNumeriquesActuels_ = new CustList<HelpIndexes>();
            cheminsNumeriquesActuels_.add(indices_);
            ElementHelp elementRacine_ = new ElementHelp(element_
                    .getAttribute(TEXTE));
            elementRacine_.ajouterInfo(StringUtil.concat(FileConst.RESOURCES_HELP,StreamTextFile.SEPARATEUR,l, StreamTextFile.SEPARATEUR,
                    element_.getTagName(), FileConst.XML_EXT));
            Document docBase_ = docs_.getVal(StringUtil.concat(FileConst.RESOURCES_HELP,StreamTextFile.SEPARATEUR,l, StreamTextFile.SEPARATEUR,
                    element_.getTagName(), FileConst.XML_EXT));
            PreparedRenderPagesCards prep_ = new PreparedRenderPagesCards(l, docBase_, built_, ms_);
            prep_.run();
            elementRacine_.setMetaDocument(prep_.getMetaDocument());
            elementRacine_.setNavigation(prep_.getNavigation());
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
                        if (e2_.hasAttributes()) {
                            ElementHelp noeud_ = new ElementHelp(e2_
                                    .getAttribute(TEXTE));
                            nouveauxChemins_.add(StringUtil.concat(cheminCourant_, StreamTextFile.SEPARATEUR,
                                    e2_.getTagName()));
                            noeud_.ajouterInfo(StringUtil.concat(cheminCourant_, StreamTextFile.SEPARATEUR,
                                    e2_.getTagName(), FileConst.XML_EXT));
                            Document docGene_ = docs_.getVal(StringUtil.concat(cheminCourant_, StreamTextFile.SEPARATEUR,
                                    e2_.getTagName(), FileConst.XML_EXT));
                            nouveauxElements_.add(e2_);
                            HelpIndexes cheminNumCourantBis_ = new HelpIndexes(
                                    cheminNumCourant_);
                            cheminNumCourantBis_.add(NumberUtil
                                    .parseInt(e2_.getAttribute(
                                            POSITION)));
                            nouveauxCheminsNum_.add(cheminNumCourantBis_);
                            prep_ = new PreparedRenderPagesCards(l, docGene_, built_, ms_);
                            prep_.run();
                            noeud_.setMetaDocument(prep_.getMetaDocument());
                            noeud_.setNavigation(prep_.getNavigation());
                            tree_.put(nouveauxCheminsNum_.last(),
                                    noeud_);
                        }
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
            trees.addEntry(l,tree_);
        }
        generalHelp.setEnabledMenu(true);
    }

    public StringMap<ObjectMap<HelpIndexes, ElementHelp>> getTrees() {
        return trees;
    }
}
