package aiki.gui.dialogs;

import javax.swing.WindowConstants;

import aiki.sml.Resources;
import aiki.gui.WindowAiki;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.gui.events.CreateServerEvent;
import code.gui.events.JoinServerEvent;
import code.gui.initialize.AbsFrameFactory;
import code.network.enums.IpType;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;

public final class DialogServerAiki implements AbstractDialogServer, AbsCloseableDialog{
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialogserver";

    private static final String TITLE = "title";
    private static final String IP_SERVER = "ipServer";
    private static final String IP_SERVER_TOOL_TIP = "ipServerToolTip";
    private static final String CREATE_SERVER = "createServer";
    private static final String JOIN_SERVER = "joinServer";
    private static final String CANCEL = "cancel";
    private final AbsDialog absDialog;
    private TextField ipOrHostName;
    private ComboBox<IpType> ipType;
    private boolean create;
    private boolean join;
    private StringMap<String> messages;

//    private Map<String,String> messagesIp;

    private EnumMap<IpType,String> messagesIpEnum;

    public DialogServerAiki(AbsFrameFactory _frameFactory) {
        absDialog = _frameFactory.newDialog();
        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogServer(WindowAiki _fenetre) {
        _fenetre.getDialogServer().init(_fenetre);
    }
    private void init(WindowAiki _fenetre) {
        absDialog.setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        join = false;
        create = false;
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _fenetre.getLanguageKey(), absDialog.getAccessFile());
//        messagesIp = FormatHtml.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), IpType.class);
        messagesIpEnum = new EnumMap<IpType,String>();
//        for (String i: messagesIp.getKeys()) {
//            messagesIpEnum.put(IpType.valueOf(i), messagesIp.getVal(i));
//        }
        for (IpType i: IpType.values()) {
            messagesIpEnum.put(i, i.toString(_fenetre.getLanguageKey()));
        }
        absDialog.setLocationRelativeTo(_fenetre);
        absDialog.setResizable(false);
        absDialog.setTitle(messages.getVal(TITLE));
        ipOrHostName = new TextField();
        Panel pane_ = Panel.newGrid(0, 1);
        Panel panel_ = Panel.newGrid(0, 2);
        TextLabel ipServer_ = new TextLabel(messages.getVal(IP_SERVER));
        ipServer_.setToolTipText(messages.getVal(IP_SERVER_TOOL_TIP));
        panel_.add(ipServer_);
        panel_.add(ipOrHostName);
        EnumList<IpType> list_ = new EnumList<IpType>(IpType.values());
        ipType = new ComboBox<IpType>(_fenetre.getFrames().getGeneComboBox().createCombo(_fenetre.getImageFactory(),new StringList(), -1, _fenetre.getCompoFactory()));
        ipType.setWithDefaultValue(false);
        ipType.refresh(list_, messagesIpEnum);
        ipType.setSelectedItem(IpType.HOST_NAME);
        panel_.add(ipType.self());
        pane_.add(panel_);
        panel_ = Panel.newLineBox();
        LabelButton button_ = new LabelButton(messages.getVal(CREATE_SERVER));
        button_.addMouseList(new CreateServerEvent(this));
        panel_.add(button_);
        button_ = new LabelButton(messages.getVal(JOIN_SERVER));
        button_.addMouseList(new JoinServerEvent(this));
        panel_.add(button_);
        button_ = new LabelButton(messages.getVal(CANCEL));
        button_.addMouseList(new ClosingDialogEvent(this));
        panel_.add(button_);
        pane_.add(panel_);
        absDialog.setContentPane(pane_);
        absDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
    }

    @Override
    public void createServerChoice() {
        join = false;
        create = true;
        closeWindow();
    }

    @Override
    public void joinServerChoice() {
        join = true;
        create = false;
        closeWindow();
    }

    @Override
    public void closeWindow() {
        absDialog.closeWindow();
    }

    public static String getIpOrHostName(DialogServerAiki _dialog) {
        _dialog.absDialog.setVisible(true);
        return _dialog.ipOrHostName.getText();
    }

    public static IpType getIpType(DialogServerAiki _dialog) {
        return _dialog.ipType.getCurrent();
    }

    public static boolean isCreate(DialogServerAiki _dialog) {
        return _dialog.create;
    }

    public static boolean isChoosen(DialogServerAiki _dialog) {
        return _dialog.create || _dialog.join;
    }

}
