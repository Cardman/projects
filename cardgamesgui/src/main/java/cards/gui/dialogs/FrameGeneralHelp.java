package cards.gui.dialogs;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import cards.gui.MainWindow;
import cards.gui.dialogs.events.ListenerClickTree;
import cards.gui.dialogs.help.ComparatorListSizeElement;
import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.HelpIndexes;
import cards.gui.dialogs.help.NodeHelp;
import cards.gui.dialogs.help.beans.GeneralHelpLgNames;
import code.formathtml.DefaultInitialization;
import code.formathtml.util.BeanLgNames;
import code.gui.ChildFrame;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.document.RenderedPage;
import code.gui.events.ClosingChildFrameEvent;
import code.resources.ResourceFiles;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.Node;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.*;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class FrameGeneralHelp extends ChildFrame {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.framegeneralhelp";

//    private static final String TRUMP_SUIT = "trumpSuit";
//
//    private static final String NORMAL_SUIT = "normalSuit";
//
//    private static final String TRUMPS = "trumps";
//
//    private static final String ORDER_TRUMPS = "orderTrumps";
//
//    private static final String ORDER_NO_TRUMPS = "orderNoTrumps";

    private static final String POSITION = "position";

    private static final String TEXTE = "texte";

    private static final String SEARCH_LABEL = "searchLabel";

//    private static final String TYPE = "type";

    private static final String XML_FILE_PATHS = "chemin_acces_aide.xml";
//    private static final String TOUTE_CARTE_BELOTE = "touteCarteBelote";
//    private static final String ORDRE_CARTES_BELOTE = "ordreCartesBelote";
//    private static final String TOUTE_CARTE_TAROT = "touteCarteTarot";
//    private static final String ORDRE_CARTES_TAROT = "ordreCartesTarot";
//    private static final String EMPTY_STRING = "";
    private StringMap<String> messages;

    private ObjectMap<HelpIndexes,ElementHelp> elementsBis = new ObjectMap<HelpIndexes,ElementHelp>();

    private NodeHelp racineBis;

    //private MainWindow window;

    private RenderedPage editor;

    private JTextField field;

    private LabelButton search;

    public FrameGeneralHelp(String _titre, MainWindow _fenetre) {
        super(_fenetre.getLanguageKey());
        setAccessFile(DIALOG_ACCESS);
        setDialogIcon(_fenetre);
        setTitle(_titre);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new ClosingChildFrameEvent(this));
        //window = _fenetre;
    }

    @Override
    public void closeWindow() {
        setVisible(false);
//        editor.clearSession();
    }

//    @Override
//    public void dispose() {
//        super.dispose();
//        window.clearHelpDialogs();
//        window = null;
//    }

    public void voir(MainWindow _w) {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = getMessages(_w,FileConst.FOLDER_MESSAGES_GUI);
        String lg_ = _w.getLanguageKey();
        elementsBis.clear();
        Document doc_ = DocumentBuilder.parseSax(ResourceFiles.ressourceFichier(StringList.concat(FileConst.RESOURCES_HELP,StreamTextFile.SEPARATEUR,lg_,
                StreamTextFile.SEPARATEUR,XML_FILE_PATHS)));
        Element element_ = doc_.getDocumentElement();
        CustList<Node> noeudsActuels_ = new CustList<Node>();
        noeudsActuels_.add(element_);
        StringList cheminsActuels_ = new StringList();
        cheminsActuels_.add(StringList.concat(FileConst.RESOURCES_HELP,StreamTextFile.SEPARATEUR,lg_,StreamTextFile.SEPARATEUR,element_.getTagName()));
        HelpIndexes indices_ = new HelpIndexes();
        indices_.add(Numbers.parseInt(element_.getAttribute(POSITION)));
        CustList<HelpIndexes> cheminsNumeriquesActuels_ = new CustList<HelpIndexes>();
        cheminsNumeriquesActuels_.add(indices_);
        ElementHelp elementRacine_ = new ElementHelp(element_
                .getAttribute(TEXTE));
        elementRacine_.ajouterInfo(StringList.concat(FileConst.RESOURCES_HELP,StreamTextFile.SEPARATEUR,lg_, StreamTextFile.SEPARATEUR,
                element_.getTagName(), FileConst.XML_EXT));
        elementsBis.put(indices_, elementRacine_);
        while (true) {
            CustList<Node> nouveauxElements_ = new CustList<Node>();
            StringList nouveauxChemins_ = new StringList();
            CustList<HelpIndexes> nouveauxCheminsNum_ = new CustList<HelpIndexes>();
            int j_ = CustList.FIRST_INDEX;
            for (Node e : noeudsActuels_) {
                String cheminCourant_ = cheminsActuels_.get(j_);
                HelpIndexes cheminNumCourant_ = cheminsNumeriquesActuels_
                        .get(j_);
                for (Element e2_ : e.getChildElements()) {
                    if (e2_.hasAttributes()) {
                        ElementHelp noeud_ = new ElementHelp(e2_
                                .getAttribute(TEXTE));
                        nouveauxChemins_.add(StringList.concat(cheminCourant_, StreamTextFile.SEPARATEUR,
                                e2_.getTagName()));
                        noeud_.ajouterInfo(StringList.concat(cheminCourant_, StreamTextFile.SEPARATEUR,
                                e2_.getTagName(), FileConst.XML_EXT));
                        nouveauxElements_.add(e2_);
                        HelpIndexes cheminNumCourantBis_ = new HelpIndexes(
                                cheminNumCourant_);
                        cheminNumCourantBis_.add(Integer
                                .parseInt(e2_.getAttribute(
                                        POSITION)));
                        nouveauxCheminsNum_.add(cheminNumCourantBis_);
                        elementsBis.put(nouveauxCheminsNum_.last(),
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
        setFocusable(true);
        setFocusableWindowState(true);
        CustList<HelpIndexes> cles_ = new CustList<HelpIndexes>(
                elementsBis.getKeys());
        // Non null pour les valeurs
        cles_.sortElts(new ComparatorListSizeElement());
        racineBis = new NodeHelp(elementsBis.getVal(cles_.first()));
        DefaultMutableTreeNode root_ = new DefaultMutableTreeNode(
                racineBis.nom());
        boolean wasNull_ = editor == null;
        Panel container_;
        if (wasNull_) {
            container_ = new Panel();
            container_.setLayout(new BoxLayout(container_.getComponent(), BoxLayout.PAGE_AXIS));
        } else {
            container_ = getPane();
            container_.removeAll();
        }
        for (HelpIndexes chemin_ : cles_) {
            CustList<Integer> cheminSansNoeud_ = chemin_.mid(CustList.FIRST_INDEX,
                    chemin_.getLastIndex());
            NodeHelp noeudLoc_;
            DefaultMutableTreeNode noeudLocGraphique_;
            noeudLoc_ = racineBis.element(cheminSansNoeud_);
            if (cheminSansNoeud_.isEmpty()) {
                noeudLocGraphique_ = root_;
            } else {
                noeudLocGraphique_ = (DefaultMutableTreeNode) root_
                        .getChildAt(cheminSansNoeud_.first());
                int lengthPath_ = cheminSansNoeud_.size();
                for (int indice_ = CustList.SECOND_INDEX; indice_ < lengthPath_; indice_++) {
                    noeudLocGraphique_ = (DefaultMutableTreeNode) noeudLocGraphique_
                            .getChildAt(cheminSansNoeud_.get(indice_));
                }
            }
            ElementHelp elementLoc_ = elementsBis.getVal(chemin_);
            NodeHelp nouveauNoeud_ = new NodeHelp(elementLoc_);
            noeudLoc_.ajouterInfo(nouveauNoeud_);
            noeudLocGraphique_.add(new DefaultMutableTreeNode(
                    elementLoc_.nom()));
        }
        if (wasNull_) {
            editor = new RenderedPage(new JScrollPane());
        }
        JTree arbre_ = new JTree(root_);
        arbre_.setRootVisible(false);
        arbre_.addTreeSelectionListener(new ListenerClickTree(racineBis, editor, arbre_));
        editor.setLanguage(lg_);
        BeanLgNames bean_ = new GeneralHelpLgNames();
        DefaultInitialization.basicStandards(bean_);
        editor.initialize(racineBis.getFile(), bean_);
        if (field == null) {
            field = new JTextField(20);
            search = new LabelButton();
            editor.setSearchText(search);
            editor.setField(field);
            editor.addFinder();
        }
        search.setTextAndSize(messages.getVal(SEARCH_LABEL));
        JSplitPane separateur_ = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(arbre_), editor.getScroll());
        separateur_.setPreferredSize(new Dimension(600, 550));
        separateur_.setDividerLocation(150);
        container_.add(separateur_);
        container_.add(field);
        container_.add(search);
        if (wasNull_) {
            setContentPane(container_);
        }
        pack();
        setVisible(true);
    }
}
