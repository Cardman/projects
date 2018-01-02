package cards.gui.dialogs;
import java.awt.Container;
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
import cards.gui.dialogs.help.ElementHelp;
import cards.gui.dialogs.help.NodeHelp;
import code.gui.ChildFrame;
import code.gui.LabelButton;
import code.gui.SessionEditorPane;
import code.gui.events.ClosingChildFrameEvent;
import code.resources.ResourceFiles;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.Node;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorListSizeElement;
import code.util.consts.Constants;

public final class FrameGeneralHelp extends ChildFrame {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.FrameGeneralHelp";

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

    private ObjectMap<Numbers<Integer>,ElementHelp> elementsBis = new ObjectMap<Numbers<Integer>,ElementHelp>();

    private NodeHelp racineBis;

    //private MainWindow window;

    private SessionEditorPane editor;

    private JTextField field;

    private LabelButton search;

    public FrameGeneralHelp(String _titre, MainWindow _fenetre) {
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

    public void voir() {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = getMessages(FileConst.FOLDER_MESSAGES_GUI);
        elementsBis.clear();
//        try {
        Document doc_ = DocumentBuilder.parseSax(ResourceFiles.ressourceFichier(StringList.concat(FileConst.RESOURCES_HELP,StreamTextFile.SEPARATEUR,Constants.getLanguage(),
                StreamTextFile.SEPARATEUR,XML_FILE_PATHS)));
        Element element_ = doc_.getDocumentElement();
        CustList<Node> noeudsActuels_ = new CustList<Node>();
        noeudsActuels_.add(element_);
        StringList cheminsActuels_ = new StringList();
        cheminsActuels_.add(StringList.concat(FileConst.RESOURCES_HELP,StreamTextFile.SEPARATEUR,Constants.getLanguage(),StreamTextFile.SEPARATEUR,element_.getTagName()));
        Numbers<Integer> indices_ = new Numbers<Integer>();
        indices_.add(Integer.parseInt(element_.getAttribute(POSITION)));
//            CustList<CustList<Integer>> cheminsNumeriques_ = new CustList<CustList<Integer>>();
//            cheminsNumeriques_.add(indices_);
        EqList<Numbers<Integer>> cheminsNumeriquesActuels_ = new EqList<Numbers<Integer>>();
        cheminsNumeriquesActuels_.add(indices_);
        ElementHelp elementRacine_ = new ElementHelp(element_
                .getAttribute(TEXTE));
//            elementRacine_.ajouterInfo(StreamTextFile.ressourceFichier(
//                    FileConst.RESOURCES_HELP+StreamTextFile.SEPARATEUR+Constants.getLanguage(), element_.getNodeName() + FileConst.TXT_EXT));
        elementRacine_.ajouterInfo(StringList.concat(FileConst.RESOURCES_HELP,StreamTextFile.SEPARATEUR,Constants.getLanguage(), StreamTextFile.SEPARATEUR,
                element_.getTagName(), FileConst.XML_EXT));
        elementsBis.put(indices_, elementRacine_);
        while (true) {
            CustList<Node> nouveauxElements_ = new CustList<Node>();
            StringList nouveauxChemins_ = new StringList();
            EqList<Numbers<Integer>> nouveauxCheminsNum_ = new EqList<Numbers<Integer>>();
            int j_ = CustList.FIRST_INDEX;
            for (Node e : noeudsActuels_) {
                String cheminCourant_ = cheminsActuels_.get(j_);
                Numbers<Integer> cheminNumCourant_ = cheminsNumeriquesActuels_
                        .get(j_);
                for (Element e2_ : e.getChildElements()) {
//                        NamedNodeMap attributs_ = e2_.getAttributes();
                    if (e2_.hasAttributes()) {
                        //Node typeNoeud_ = attributs_.getNamedItem(TYPE);
//                            String typeInfo_ = EMPTY_STRING;
//                            if (typeNoeud_ != null) {
//                                typeInfo_ = typeNoeud_.getNodeValue();
//                            }
                        ElementHelp noeud_ = new ElementHelp(e2_
                                .getAttribute(TEXTE));
                        nouveauxChemins_.add(StringList.concat(cheminCourant_, StreamTextFile.SEPARATEUR,
                                e2_.getTagName()));
                        // + infos
//                            noeud_.ajouterInfo(StreamTextFile.ressourceFichier(
//                                    cheminCourant_, e2.getNodeName() + FileConst.TXT_EXT));
                        noeud_.ajouterInfo(StringList.concat(cheminCourant_, StreamTextFile.SEPARATEUR,
                                e2_.getTagName(), FileConst.XML_EXT));
                        nouveauxElements_.add(e2_);
                        Numbers<Integer> cheminNumCourantBis_ = new Numbers<Integer>(
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
//                for (CustList<Integer> c : nouveauxCheminsNum_) {
//                    cheminsNumeriques_.add(c);
//                }
            if (nouveauxElements_.isEmpty()) {
                break;
            }
            noeudsActuels_ = nouveauxElements_;
            cheminsActuels_ = nouveauxChemins_;
            cheminsNumeriquesActuels_ = nouveauxCheminsNum_;
        }

//        } catch (RuntimeException _0) {
//            _0.printStackTrace();
//        }
        setFocusable(true);
        setFocusableWindowState(true);
        EqList<Numbers<Integer>> cles_ = new EqList<Numbers<Integer>>(
                elementsBis.getKeys());
        // Non null pour les valeurs
        cles_.sortElts(new ComparatorListSizeElement());
        racineBis = new NodeHelp(elementsBis.getVal(cles_.first()));
        DefaultMutableTreeNode root_ = new DefaultMutableTreeNode(
                racineBis.nom());
        boolean wasNull_ = editor == null;
        Container container_;
        if (wasNull_) {
            container_ = new Container();
            container_.setLayout(new BoxLayout(container_, BoxLayout.PAGE_AXIS));
        } else {
            container_ = getContentPane();
            container_.removeAll();
        }
        for (Numbers<Integer> chemin_ : cles_) {
            Numbers<Integer> cheminSansNoeud_ = chemin_.mid(CustList.FIRST_INDEX,
                    chemin_.getLastIndex());
            NodeHelp noeudLoc_ = null;
            DefaultMutableTreeNode noeudLocGraphique_ = null;
            try {
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
            } catch (RuntimeException _0) {
                noeudLoc_ = racineBis;
                noeudLocGraphique_ = root_;
            }
            ElementHelp elementLoc_ = elementsBis.getVal(chemin_);
            NodeHelp nouveauNoeud_ = new NodeHelp(elementLoc_);
            noeudLoc_.ajouterInfo(nouveauNoeud_);
            noeudLocGraphique_.add(new DefaultMutableTreeNode(
                    elementLoc_.nom()));
        }
//        arbre_.addTreeSelectionListener(new EcouteCliqueArbre());
//        JTextArea zone_ = new JTextArea(EMPTY_STRING, 50, 100);
//        zone_.setEditable(false);
//        zone_.setFocusable(false);
//        zone_.append(racineBis.infos());
//        JSplitPane separateur_ = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
//                new JScrollPane(arbre_), new JScrollPane(zone_));
        if (wasNull_) {
            editor = new SessionEditorPane();
        }
        JTree arbre_ = new JTree(root_);
        arbre_.setRootVisible(false);
        arbre_.addTreeSelectionListener(new ListenerClickTree(racineBis, editor, arbre_));
//        try {
//            editor.setMainClass(SoftApplication.getMainClass());
//            editor.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
        editor.setLanguage(Constants.getLanguage());
        editor.initialize(racineBis.getFile());
        if (field == null) {
            field = new JTextField(20);
//                search = new LabelButton(MainWindow.OK);
            search = new LabelButton();
            editor.setSearchText(search);
            editor.setField(field);
            editor.addFinder();
        }
        search.setText(messages.getVal(SEARCH_LABEL));
//        } catch (RuntimeException _0) {
//            _0.printStackTrace();
//        }
        //if (wasNull_) {
        JSplitPane separateur_ = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(arbre_), new JScrollPane(editor));
        separateur_.setPreferredSize(new Dimension(600, 550));
        separateur_.setDividerLocation(150);
        container_.add(separateur_);
        //}
        //if (field != null) {
        container_.add(field);
        //}
        //if (search != null) {
        container_.add(search);
        //}
        if (wasNull_) {
            setContentPane(container_);
        }
        pack();
        setVisible(true);
    }
}
