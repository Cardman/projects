package aiki.gui.dialogs;

import javax.swing.WindowConstants;

import aiki.sml.Resources;
import aiki.gui.MainWindow;
import code.gui.*;
import code.gui.events.ClosingDialogEvent;
import code.gui.events.CreateServerEvent;
import code.gui.events.JoinServerEvent;
import code.network.enums.IpType;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;

public final class DialogServer extends Dialog implements AbstractDialogServer{
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialogserver";

    private static final String TITLE = "title";
    private static final String IP_SERVER = "ipServer";
    private static final String IP_SERVER_TOOL_TIP = "ipServerToolTip";
    private static final String CREATE_SERVER = "createServer";
    private static final String JOIN_SERVER = "joinServer";
    private static final String CANCEL = "cancel";
    private TextField ipOrHostName;
    private ComboBox<IpType> ipType;
    private boolean create;
    private boolean join;
    private StringMap<String> messages;

//    private Map<String,String> messagesIp;

    private EnumMap<IpType,String> messagesIpEnum;

    public DialogServer() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setDialogServer(MainWindow _fenetre) {
        _fenetre.getDialogServer().init(_fenetre);
    }
    private void init(MainWindow _fenetre) {
        setDialogIcon(_fenetre);
        join = false;
        create = false;
        messages = MainWindow.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _fenetre.getLanguageKey(), getAccessFile());
//        messagesIp = FormatHtml.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), IpType.class);
        messagesIpEnum = new EnumMap<IpType,String>();
//        for (String i: messagesIp.getKeys()) {
//            messagesIpEnum.put(IpType.valueOf(i), messagesIp.getVal(i));
//        }
        for (IpType i: IpType.values()) {
            messagesIpEnum.put(i, i.toString(_fenetre.getLanguageKey()));
        }
        setLocationRelativeTo(_fenetre);
        setResizable(false);
        setTitle(messages.getVal(TITLE));
        ipOrHostName = new TextField();
        Panel pane_ = Panel.newGrid(0, 1);
        Panel panel_ = Panel.newGrid(0, 2);
        TextLabel ipServer_ = new TextLabel(messages.getVal(IP_SERVER));
        ipServer_.setToolTipText(messages.getVal(IP_SERVER_TOOL_TIP));
        panel_.add(ipServer_);
        panel_.add(ipOrHostName);
        EnumList<IpType> list_ = new EnumList<IpType>(IpType.values());
        ipType = new ComboBox<IpType>(_fenetre.getFrames().getGeneComboBox().createCombo(new StringList(), -1));
        ipType.setWithDefaultValue(false);
        ipType.refresh(list_, messagesIpEnum);
        ipType.setSelectedItem(IpType.HOST_NAME);
        panel_.add(ipType.getCombo().self());
        pane_.add(panel_);
        panel_ = Panel.newLineBox();
        LabelButton button_ = new LabelButton(messages.getVal(CREATE_SERVER));
        button_.addMouseListener(new CreateServerEvent(this));
        panel_.add(button_);
        button_ = new LabelButton(messages.getVal(JOIN_SERVER));
        button_.addMouseListener(new JoinServerEvent(this));
        panel_.add(button_);
        button_ = new LabelButton(messages.getVal(CANCEL));
        button_.addMouseListener(new ClosingDialogEvent(this));
        panel_.add(button_);
        pane_.add(panel_);
        setContentPane(pane_);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
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

    public static String getIpOrHostName(DialogServer _dialog) {
        _dialog.setVisible(true);
        return _dialog.ipOrHostName.getText();
    }

    public static IpType getIpType(DialogServer _dialog) {
        return _dialog.ipType.getCurrent();
    }

    public static boolean isCreate(DialogServer _dialog) {
        return _dialog.create;
    }

    public static boolean isChoosen(DialogServer _dialog) {
        return _dialog.create || _dialog.join;
    }

}
