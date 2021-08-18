package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.ExecutingBlocks;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.*;
import code.gui.initialize.AbstractGraphicComboBoxGenerator;
import code.scripts.messages.gui.MessCdmGuiGr;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

import java.awt.event.WindowListener;

public final class GuiAliases {

    private static final String TREE_NODE = "TreeNode";
    private static final String TREE = "Tree";
    private static final String TABLE_GUI = "TableGui";
    private static final String BUTTON = "Button";
    private static final String CONFIRM = "Confirm";
    private static final String FRAME = "Frame";
    private static final String DIALOG = "Dialog";
    private static final String PANEL = "Panel";
    private static final String PROG_BAR = "ProgBar";
    private static final String IMAGE_SET = "ImageSet";
    private static final String ARGS = "Args";
    private static final String KEY_EVENT = "KeyEvent";
    private static final String RADIO = "Radio";
    private static final String CHECK_BOX = "CheckBox";
    private static final String SET_FONT = "SetFont";
    private static final String SPINNER = "Spinner";
    private static final String DISPOSE = "Dispose";
    private static final String GR_LIST = "GrList";
    private static final String GET_FONT = "GetFont";
    private static final String IMAGE = "Image";
    private static final String SLIDER = "Slider";
    private static final String PAINT_ADD = "PaintAdd";
    private static final String PAINT_SET = "PaintSet";
    private static final String COUNT = "Count";
    private static final String PACK = "Pack";
    private static final String COLOR = "Color";
    private static final String WINDOW = "Window";
    private static final String WINDOW_SET = "WindowSet";
    private static final String WINDOW_SET_ADD = "WindowSetAdd";
    private static final String WINDOW_SET_ALL = "WindowSetAll";
    private static final String WINDOW_SET_CONTAINS = "WindowSetContains";
    private static final String WINDOW_SET_REMOVE = "WindowSetRemove";
    private static final String WINDOW_SET_SNAPSHOT = "WindowSetSnapshot";
    private static final String COMP_BACK = "CompBack";
    private static final String PAINT = "Paint";
    private static final String COMP_LOC = "CompLoc";
    private static final String IMAGE_GET = "ImageGet";
    private static final String RENDER = "Render";
    private static final String COMBO = "Combo";
    private static final String ADD_COMPO = "AddCompo";
    private static final String INPUT = "Input";
    private static final String FONT = "Font";
    private static final String COMP_FORE = "CompFore";
    private static final String KEY_TYPED = "KeyTyped";
    private static final String COLOR_RED = "ColorRed";
    private static final String TEXT_AREA = "TextArea";
    private static final String FONT_GET_SIZE = "FontGetSize";
    private static final String DIMENSION = "Dimension";
    private static final String PROG_BAR_OR = "ProgBarOr";
    private static final String FONT_IS_BOLD = "FontIsBold";
    private static final String DIMENSION_GET_HEIGHT = "DimensionGetHeight";
    private static final String PANEL_BORDER_NORTH = "PanelBorderNorth";
    private static final String PANEL_BORDER_SOUTH = "PanelBorderSouth";
    private static final String REMOVE_WINDOW_LISTENER = "RemoveWindowListener";
    private static final String WINDOW_TYPE_RELATIVE = "WindowTypeRelative";
    private static final String PANEL_BORDER_AFTER_LAST = "PanelBorderAfterLast";
    private static final String FONT_GET_NAME = "FontGetName";
    private static final String DIMENSION_GET_WIDTH = "DimensionGetWidth";
    private static final String COMPONENT = "Component";
    private static final String PANEL_BORDER_AFTER_LINE_ENDS = "PanelBorderAfterLineEnds";
    private static final String COMPONENT_SET_AUTOSCROLLS = "ComponentSetAutoscrolls";
    private static final String COMPONENT_IS_AUTOSCROLLS = "ComponentIsAutoscrolls";
    private static final String COMPONENT_GET_WIDTH = "ComponentGetWidth";
    private static final String COMPONENT_GET_HEIGHT = "ComponentGetHeight";
    private static final String PANEL_BORDER_EAST = "PanelBorderEast";
    private static final String PROG_BAR_MAX = "ProgBarMax";
    private static final String GET_WINDOW_LISTENERS = "GetWindowListeners";
    private static final String PANEL_BORDER_BEFORE_FIRST = "PanelBorderBeforeFirst";
    private static final String TEXT_LABEL = "TextLabel";
    private static final String PANEL_BORDER_WEST = "PanelBorderWest";
    private static final String PROG_BAR_MIN = "ProgBarMin";
    private static final String PROG_BAR_VALUE = "ProgBarValue";
    private static final String PANEL_BORDER = "PanelBorder";
    private static final String FONT_IS_ITALIC = "FontIsItalic";
    private static final String IMAGE_LABEL = "ImageLabel";
    private static final String PANEL_BORDER_CENTER = "PanelBorderCenter";
    private static final String FONT_STRING_WIDTH = "FontStringWidth";
    private static final String TABLE_SET_COLUMNS = "TableSetColumns";
    private static final String TREE_IS_ROOT_VISIBLE = "TreeIsRootVisible";
    private static final String TREE_NODE_REMOVE_FROM_PARENT = "TreeNodeRemoveFromParent";
    private static final String TREE_NODE_SET_USER_OBJECT = "TreeNodeSetUserObject";
    private static final String TREE_RELOAD = "TreeReload";
    private static final String TREE_ADD_TREE_LISTENER = "TreeAddTreeListener";
    private static final String TREE_NODE_EQ = "TreeNodeEq";
    private static final String TREE_NODE_NB = "TreeNodeNb";
    private static final String TABLE_IS_MULTIPLE = "TableIsMultiple";
    private static final String TABLE_LISTENER = "TableListener";
    private static final String TABLE_GET_ROW_COUNT = "TableGetRowCount";
    private static final String TREE_NODE_IS_ANCESTOR = "TreeNodeIsAncestor";
    private static final String TREE_NODE_GET_NEXT_SIBLING = "TreeNodeGetNextSibling";
    private static final String TREE_NODE_GET_LAST_CHILD = "TreeNodeGetLastChild";
    private static final String TREE_SET_ROOT_VISIBLE = "TreeSetRootVisible";
    private static final String TABLE_SET_MULTIPLE = "TableSetMultiple";
    private static final String TREE_NODE_GET_PARENT_NODE = "TreeNodeGetParentNode";
    private static final String TREE_NODE_REMOVE = "TreeNodeRemove";
    private static final String TABLE_GET_SELECTED_ROW = "TableGetSelectedRow";
    private static final String TREE_NODE_GET_FIRST_CHILD = "TreeNodeGetFirstChild";
    private static final String TREE_NODE_GET_USER_OBJECT = "TreeNodeGetUserObject";
    private static final String TABLE_GET_SELECTED_ROWS = "TableGetSelectedRows";
    private static final String TABLE_VALUE_TABLE_CHANGED = "TableValueTableChanged";
    private static final String TABLE_GET_COLUMN_COUNT = "TableGetColumnCount";
    private static final String TABLE_GET_ROW_AT_POINT = "TableGetRowAtPoint";
    private static final String TABLE_SET_ROW_COUNT = "TableSetRowCount";
    private static final String TABLE_GET_COLUMN_NAME = "TableGetColumnName";
    private static final String TABLE_GET_VALUE = "TableGetValue";
    private static final String TREE_NODE_IS_DESCENDANT = "TreeNodeIsDescendant";
    private static final String TABLE_SET_VALUE = "TableSetValue";
    private static final String TREE_GET_SELECTED = "TreeGetSelected";
    private static final String TABLE_GET_SELECTED_ROW_COUNT = "TableGetSelectedRowCount";
    private static final String TABLE_GET_COLUMN_AT_POINT = "TableGetColumnAtPoint";
    private static final String CONFIRM_OK = "ConfirmOk";
    private static final String CONFIRM_YES_NO = "ConfirmYesNo";
    private static final String TABLE_REMOVE_INTERVAL = "TableRemoveInterval";
    private static final String CONFIRM_FIELD_OK = "ConfirmFieldOk";
    private static final String DIALOG_SET_MODAL = "DialogSetModal";
    private static final String TABLE_APPLY_CHANGES = "TableApplyChanges";
    private static final String TABLE_IS_REORDER = "TableIsReorder";
    private static final String CONFIRM_FIELD_YES = "ConfirmFieldYes";
    private static final String TABLE_SET_REORDER = "TableSetReorder";
    private static final String CONFIRM_FIELD = "ConfirmField";
    private static final String CONFIRM_FIELD_NO = "ConfirmFieldNo";
    private static final String TABLE_ADD_SELECT = "TableAddSelect";
    private static final String CONFIRM_MESSAGE = "ConfirmMessage";
    private static final String TABLE_ADD_HEADER = "TableAddHeader";
    private static final String CONFIRM_FULL = "ConfirmFull";
    private static final String DIALOG_IS_MODAL = "DialogIsModal";
    private static final String WINDOW_TYPE = "WindowType";
    private static final String TABLE_ADD_INTERVAL = "TableAddInterval";
    private static final String TABLE_MOVE_COLUMN = "TableMoveColumn";
    private static final String CONFIRM_FIELD_CANCEL = "ConfirmFieldCancel";
    private static final String TEXT_AREA_SET_SELECTION_START = "TextAreaSetSelectionStart";
    private static final String TREE_LISTENER = "TreeListener";
    private static final String TREE_NODE_ADD = "TreeNodeAdd";
    private static final String TREE_LISTENER_VALUE_CHANGED = "TreeListenerValueChanged";
    private static final String TREE_NODE_INSERT = "TreeNodeInsert";
    private static final String ACTION_EVENT = "ActionEvent";
    private static final String CHANGE_LISTENER = "ChangeListener";
    private static final String ACTION_LISTENER = "ActionListener";
    private static final String ACTION_PERFORMED = "ActionPerformed";
    private static final String ADD_CHANGE = "AddChange";
    private static final String STATE_CHANGED = "StateChanged";
    private static final String TREE_NODE_GET_PREVIOUS_SIBLING = "TreeNodeGetPreviousSibling";
    private static final String COMPONENT_GET_PREFERRED_SIZE = "ComponentGetPreferredSize";
    private static final String SPLIT_PANE_SET_DIVIDER_LOCATION = "SplitPaneSetDividerLocation";
    private static final String SPLIT_PANE_IS_ONE_TOUCH_EXPANDABLE = "SplitPaneIsOneTouchExpandable";
    private static final String SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE = "SplitPaneSetOneTouchExpandable";
    private static final String SPLIT_PANE_IS_CONTINUOUS_LAYOUT = "SplitPaneIsContinuousLayout";
    private static final String SCROLL_PANE_HORIZONTAL_VALUE = "ScrollPaneHorizontalValue";
    private static final String TREE_NODE_REMOVE_ALL_CHILDREN = "TreeNodeRemoveAllChildren";
    private static final String PANEL_BORDER_BEFORE_LINE_BEGINS = "PanelBorderBeforeLineBegins";
    private static final String COMPONENT_SET_PREFERRED_SIZE = "ComponentSetPreferredSize";
    private static final String SPLIT_PANE_GET_DIVIDER_LOCATION = "SplitPaneGetDividerLocation";
    private static final String SPLIT_PANE_SET_CONTINUOUS_LAYOUT = "SplitPaneSetContinuousLayout";
    private static final String MOUSE_EVENT_IS_CTRL = "MouseEventIsCtrl";
    private static final String MOUSE_RELEASED = "MouseReleased";
    private static final String MOUSE_EVENT_IS_SHIFT = "MouseEventIsShift";
    private static final String SCROLL_PANE_GET_VIEW = "ScrollPaneGetView";
    private static final String PAINT_REFRESH = "PaintRefresh";
    private static final String PAINT_REFRESH_ONE = "PaintRefreshOne";
    private static final String MOUSE_EXITED = "MouseExited";
    private static final String SPLIT_PANE = "SplitPane";
    private static final String SPLIT_PANE_SET_RIGHT = "SplitPaneSetRight";
    private static final String SET_VISIBLE = "SetVisible";
    private static final String REMOVE_COMPO = "RemoveCompo";
    private static final String MOUSE_CLICKED = "MouseClicked";
    private static final String MOUSE_MOVED = "MouseMoved";
    private static final String MOUSE_EVENT = "MouseEvent";
    private static final String MOUSE_EVENT_GET_CLICKS = "MouseEventGetClicks";
    private static final String MOUSE_EVENT_IS_ALT = "MouseEventIsAlt";
    private static final String MOUSE_EVENT_GET_SECOND = "MouseEventGetSecond";
    private static final String SCROLL_PANE = "ScrollPane";
    private static final String SCROLL_PANE_VALIDATE = "ScrollPaneValidate";
    private static final String SPLIT_PANE_SET_DIVIDER_SIZE = "SplitPaneSetDividerSize";
    private static final String SPLIT_PANE_GET_LEFT = "SplitPaneGetLeft";
    private static final String SPLIT_PANE_GET_DIVIDER_SIZE = "SplitPaneGetDividerSize";
    private static final String SPLIT_PANE_VALIDATE = "SplitPaneValidate";
    private static final String MOUSE_ENTERED = "MouseEntered";
    private static final String SCROLL_PANE_SET_VIEW = "ScrollPaneSetView";
    private static final String SPLIT_PANE_SET_LEFT = "SplitPaneSetLeft";
    private static final String MOUSE_PRESSED = "MousePressed";
    private static final String MOUSE_LISTENER = "MouseListener";
    private static final String IS_VISIBLE = "IsVisible";
    private static final String MOUSE_DRAGGED = "MouseDragged";
    private static final String SCROLL_PANE_VERTICAL_VALUE = "ScrollPaneVerticalValue";
    private static final String SPLIT_PANE_GET_RIGHT = "SplitPaneGetRight";
    private static final String MOUSE_EVENT_GET_FIRST = "MouseEventGetFirst";
    private static final String GET_INDEX_COMPO = "GetIndexCompo";
    private static final String IMAGE_FILL_POLYGON = "ImageFillPolygon";
    private static final String COMPONENT_IS_VISIBLE = "ComponentIsVisible";
    private static final String IMAGE_DRAW_RECT = "ImageDrawRect";
    private static final String COMPONENT_SET_VISIBLE = "ComponentSetVisible";
    private static final String COMPONENT_SET_SIZE = "ComponentSetSize";
    private static final String IMAGE_EQ = "ImageEq";
    private static final String IMAGE_GET_COLOR = "ImageGetColor";
    private static final String IMAGE_DRAW_OVAL = "ImageDrawOval";
    private static final String COMPONENT_INVOKE_LATER = "ComponentInvokeLater";
    private static final String IMAGE_GET_HEIGHT = "ImageGetHeight";
    private static final String IMAGE_SET_FONT = "ImageSetFont";
    private static final String IMAGE_DISPOSE = "ImageDispose";
    private static final String IMAGE_GET_FONT = "ImageGetFont";
    private static final String COLOR_IS_TRANSPARENT = "ColorIsTransparent";
    private static final String IMAGE_DRAW_LINE = "ImageDrawLine";
    private static final String IMAGE_FILL_RECT = "ImageFillRect";
    private static final String COLOR_BLUE = "ColorBlue";
    private static final String IMAGE_DRAW_POLYGON = "ImageDrawPolygon";
    private static final String COMPONENT_REPAINT = "ComponentRepaint";
    private static final String IMAGE_IS_WITH_ALPHA = "ImageIsWithAlpha";
    private static final String IMAGE_GET_WIDTH = "ImageGetWidth";
    private static final String COLOR_GREEN = "ColorGreen";
    private static final String COMPONENT_SET_PAINT = "ComponentSetPaint";
    private static final String REMOVE_ALL = "RemoveAll";
    private static final String IMAGE_FILL_OVAL = "ImageFillOval";
    private static final String COLOR_ALPHA = "ColorAlpha";
    private static final String IMAGE_DRAW = "ImageDraw";
    private static final String IMAGE_SET_COLOR = "ImageSetColor";
    private static final String SET_CONTENT = "SetContent";
    private static final String GET_NEXT_COMPO = "GetNextCompo";
    private static final String COMPONENT_GET_PAINT = "ComponentGetPaint";
    private static final String GET_PARENT_COMPO = "GetParentCompo";
    private static final String SET_LABEL_TEXT = "SetLabelText";
    private static final String SET_LABEL_IMAGE = "SetLabelImage";
    private static final String TABBED_PANE_SET_TITLE = "TabbedPaneSetTitle";
    private static final String TABBED_PANE_ADD = "TabbedPaneAdd";
    private static final String TABBED_PANE_NB = "TabbedPaneNb";
    private static final String PANEL_FLOW = "PanelFlow";
    private static final String TABBED_PANE_REMOVE = "TabbedPaneRemove";
    private static final String ADD_WINDOW_LISTENER = "AddWindowListener";
    private static final String PAINT_METHOD = "PaintMethod";
    private static final String PANEL_PAGE_BOX = "PanelPageBox";
    private static final String PANEL_ABSOLUTE = "PanelAbsolute";
    private static final String PANEL_GRID = "PanelGrid";
    private static final String TABBED_PANE_GET = "TabbedPaneGet";
    private static final String TABBED_PANE = "TabbedPane";
    private static final String TABBED_PANE_GET_TITLE = "TabbedPaneGetTitle";
    private static final String PANEL_VALIDATE = "PanelValidate";
    private static final String TABBED_PANE_INDEX = "TabbedPaneIndex";
    private static final String TABBED_PANE_SET = "TabbedPaneSet";
    private static final String TABBED_PANE_SEL_INDEX = "TabbedPaneSelIndex";
    private static final String ADD_LISTENER = "AddListener";
    private static final String REMOVE_LISTENER = "RemoveListener";
    private static final String GET_LISTENERS = "GetListeners";
    private static final String MENU_ITEM_CHECK = "MenuItemCheck";
    private static final String MENU_ADD_SEPARATOR = "MenuAddSeparator";
    private static final String ABS_MENU_ITEM = "AbsMenuItem";
    private static final String MENU_ITEM_CHECK_IS_SELECTED = "MenuItemCheckIsSelected";
    private static final String ABS_MENU_SET_ENABLED = "AbsMenuSetEnabled";
    private static final String MENU_REMOVE = "MenuRemove";
    private static final String ABS_MENU_ITEM_ADD_ACTION = "AbsMenuItemAddAction";
    private static final String ABS_MENU_SET_TEXT = "AbsMenuSetText";
    private static final String ABS_MENU_GET_TEXT = "AbsMenuGetText";
    private static final String MENU_ITEM_CHECK_SET_SELECTED = "MenuItemCheckSetSelected";
    private static final String ABS_MENU_SET_DEEP_ENABLED = "AbsMenuSetDeepEnabled";
    private static final String COMP_TOOL_TIP = "CompToolTip";
    private static final String WINDOW_CLOSED = "WindowClosed";
    private static final String MOUSE_EVENT_IS_RIGHT = "MouseEventIsRight";
    private static final String KEY_PRESSED = "KeyPressed";
    private static final String MOUSE_EVENT_IS_LEFT = "MouseEventIsLeft";
    private static final String WHEEL_LISTENER = "WheelListener";
    private static final String COMP_BOR_LINE = "CompBorLine";
    private static final String KEY_EVENT_CODE = "KeyEventCode";
    private static final String REQUEST_FOCUS = "RequestFocus";
    private static final String COMP_BOR_RAISE = "CompBorRaise";
    private static final String WHEEL_EVENT = "WheelEvent";
    private static final String KEY_EVENT_IS_SHIFT = "KeyEventIsShift";
    private static final String COMP_GET_FIRST_POS = "CompGetFirstPos";
    private static final String COMP_BOR_TITLE = "CompBorTitle";
    private static final String ADD_KEY_LISTENER = "AddKeyListener";
    private static final String REMOVE_KEY_LISTENER = "RemoveKeyListener";
    private static final String GET_KEY_LISTENERS = "GetKeyListeners";
    private static final String KEY_EVENT_IS_ALT = "KeyEventIsAlt";
    private static final String KEY_LISTENER = "KeyListener";
    private static final String KEY_EVENT_CHAR = "KeyEventChar";
    private static final String WINDOW_LISTENER = "WindowListener";
    private static final String WHEEL_MOVE = "WheelMove";
    private static final String COMP_GET_SECOND_POS = "CompGetSecondPos";
    private static final String KEY_EVENT_IS_CTRL = "KeyEventIsCtrl";
    private static final String MOUSE_EVENT_IS_MIDDLE = "MouseEventIsMiddle";
    private static final String WHEEL_ROTATED_CLICKS = "WheelRotatedClicks";
    private static final String COMP_BOR_LOWER = "CompBorLower";
    private static final String WINDOW_OPENED = "WindowOpened";
    private static final String COMP_FOCUSABLE = "CompFocusable";
    private static final String COMP_OPAQUE = "CompOpaque";
    private static final String WINDOW_ICONIFIED = "WindowIconified";
    private static final String KEY_RELEASED = "KeyReleased";
    private static final String ADD_WHEEL_LISTENER = "AddWheelListener";
    private static final String REMOVE_WHEEL_LISTENER = "RemoveWheelListener";
    private static final String GET_WHEEL_LISTENERS = "GetWheelListeners";
    private static final String WINDOW_CLOSING = "WindowClosing";
    private static final String GR_LIST_SET = "GrListSet";
    private static final String WINDOW_ACTIVATED = "WindowActivated";
    private static final String WINDOW_DEACTIVATED = "WindowDeactivated";
    private static final String RENDER_SET_HEIGHT = "RenderSetHeight";
    private static final String LIST_SELECTION = "ListSelection";
    private static final String RENDER_GET_WIDTH = "RenderGetWidth";
    private static final String RENDER_GET_HEIGHT = "RenderGetHeight";
    private static final String GR_LIST_GET_SELECTED_INDEXES = "GrListGetSelectedIndexes";
    private static final String GR_LIST_GET_SELECTIONS = "GrListGetSelections";
    private static final String RENDER_SET_WIDTH = "RenderSetWidth";
    private static final String INPUT_IS_ENABLED = "InputIsEnabled";
    private static final String GR_LIST_UPDATE_GRAPHICS = "GrListUpdateGraphics";
    private static final String GR_LIST_GET_VISIBLE_ROW_COUNT = "GrListGetVisibleRowCount";
    private static final String GR_LIST_CLEAR_SELECTION = "GrListClearSelection";
    private static final String RENDER_GET_PAINT = "RenderGetPaint";
    private static final String WINDOW_EVENT = "WindowEvent";
    private static final String GR_LIST_CLEAR = "GrListClear";
    private static final String GR_LIST_SET_RENDER = "GrListSetRender";
    private static final String GR_LIST_ADD_SELECTION = "GrListAddSelection";
    private static final String GR_LIST_REMOVE_SELECTION = "GrListRemoveSelection";
    private static final String GR_LIST_REMOVE = "GrListRemove";
    private static final String INPUT_SET_ENABLED = "InputSetEnabled";
    private static final String WINDOW_DEICONIFIED = "WindowDeiconified";
    private static final String VALUE_CHANGED = "ValueChanged";
    private static final String GR_LIST_GET_LIST_VIEW = "GrListGetListView";
    private static final String RENDER_SET_PAINT = "RenderSetPaint";
    private static final String GR_LIST_ADD = "GrListAdd";
    private static final String GR_LIST_GET_RENDER = "GrListGetRender";
    private static final String GR_LIST_SET_SELECTED_INDEXES = "GrListSetSelectedIndexes";
    private static final String GR_LIST_SET_VISIBLE_ROW_COUNT = "GrListSetVisibleRowCount";
    private static final String COMBO_GET_SELECTED_ITEM = "ComboGetSelectedItem";
    private static final String COMBO_ADD_ITEM = "ComboAddItem";
    private static final String POPUP_MENU_GET_COMP = "PopupMenuGetComp";
    private static final String COMBO_GET_LISTENERS = "ComboGetListeners";
    private static final String COMBO_GET_SELECTED_INDEX = "ComboGetSelectedIndex";
    private static final String POPUP_MENU = "PopupMenu";
    private static final String POPUP_MENU_ADD_MENU = "PopupMenuAddMenu";
    private static final String POPUP_MENU_NB_MENU = "PopupMenuNbMenu";
    private static final String TEXT_FIELD_ADD_POPUP = "TextFieldAddPopup";
    private static final String POPUP_MENU_NB_COMP = "PopupMenuNbComp";
    private static final String COMBO_REMOVE_ALL_ITEMS = "ComboRemoveAllItems";
    private static final String POPUP_MENU_SHOW = "PopupMenuShow";
    private static final String POPUP_MENU_GET_MENU = "PopupMenuGetMenu";
    private static final String TEXT_FIELD_AUTO = "TextFieldAuto";
    private static final String RADIO_GET_TEXT = "RadioGetText";
    private static final String BUTTON_GROUP_ADD = "ButtonGroupAdd";
    private static final String BUTTON_GROUP = "ButtonGroup";
    private static final String POPUP_MENU_ADD = "PopupMenuAdd";
    private static final String RADIO_IS_SELECTED = "RadioIsSelected";
    private static final String COMBO_ADD_LISTENER = "ComboAddListener";
    private static final String COMBO_REMOVE_LISTENER = "ComboRemoveListener";
    private static final String TEXT_FIELD = "TextField";
    private static final String COMBO_REMOVE_ITEM = "ComboRemoveItem";
    private static final String RADIO_SET_SELECTED = "RadioSetSelected";
    private static final String TEXT_FIELD_GET_TEXT = "TextFieldGetText";
    private static final String POPUP_MENU_REMOVE_MENU = "PopupMenuRemoveMenu";
    private static final String TEXT_FIELD_ADD_DOCUMENT = "TextFieldAddDocument";
    private static final String COMBO_GET_SELECTED_INDEXES = "ComboGetSelectedIndexes";
    private static final String TEXT_FIELD_ADD_ACTION = "TextFieldAddAction";
    private static final String TEXT_AREA_GET_TEXT = "TextAreaGetText";
    private static final String RADIO_SET_TEXT = "RadioSetText";
    private static final String COMBO_SELECT_ITEM = "ComboSelectItem";
    private static final String COMBO_GET_ITEM_COUNT = "ComboGetItemCount";
    private static final String POPUP_MENU_REMOVE_COMP = "PopupMenuRemoveComp";
    private static final String TEXT_FIELD_SET_TEXT = "TextFieldSetText";
    private static final String CHECK_BOX_SET_SELECTED = "CheckBoxSetSelected";
    private static final String CHECK_BOX_ADD_ACTION = "CheckBoxAddAction";
    private static final String TEXT_AREA_SET_SELECTION_END = "TextAreaSetSelectionEnd";
    private static final String SPINNER_GET_VALUE = "SpinnerGetValue";
    private static final String SLIDER_GET_MAX = "SliderGetMax";
    private static final String TEXT_AREA_SELECT = "TextAreaSelect";
    private static final String TEXT_AREA_REPLACE_RANGE = "TextAreaReplaceRange";
    private static final String SPINNER_SET_STEP = "SpinnerSetStep";
    private static final String CHECK_BOX_IS_SELECTED = "CheckBoxIsSelected";
    private static final String TEXT_AREA_REPLACE_SELECTION = "TextAreaReplaceSelection";
    private static final String SPINNER_SET_VALUE = "SpinnerSetValue";
    private static final String SLIDER_GET_VALUE = "SliderGetValue";
    private static final String SLIDER_SET_VALUE = "SliderSetValue";
    private static final String SLIDER_GET_MIN = "SliderGetMin";
    private static final String SLIDER_SET_MIN = "SliderSetMin";
    private static final String TEXT_AREA_SET_TAB_SIZE = "TextAreaSetTabSize";
    private static final String SPINNER_GET_MIN = "SpinnerGetMin";
    private static final String CHECK_BOX_SET_TEXT = "CheckBoxSetText";
    private static final String CHECK_BOX_GET_TEXT = "CheckBoxGetText";
    private static final String TEXT_AREA_INSERT = "TextAreaInsert";
    private static final String TEXT_AREA_SELECT_ALL = "TextAreaSelectAll";
    private static final String TEXT_AREA_APPEND = "TextAreaAppend";
    private static final String SPINNER_GET_MAX = "SpinnerGetMax";
    private static final String SPINNER_SET_MAX = "SpinnerSetMax";
    private static final String TEXT_AREA_GET_TAB_SIZE = "TextAreaGetTabSize";
    private static final String TEXT_AREA_SET_TEXT = "TextAreaSetText";
    private static final String SPINNER_SET_MIN = "SpinnerSetMin";
    private static final String SPINNER_GET_STEP = "SpinnerGetStep";
    private static final String SPINNER_SET_RANGE_VALUE = "SpinnerSetRangeValue";
    private static final String TEXT_AREA_GET_SELECTED_TEXT = "TextAreaGetSelectedText";
    private static final String SLIDER_SET_MAX = "SliderSetMax";
    private static final String SPINNER_SET_RANGE = "SpinnerSetRange";
    private static final String MENU_BAR_GET = "MenuBarGet";
    private static final String MENU_BAR_REMOVE = "MenuBarRemove";
    private static final String ABS_MENU_GET_PARENT = "AbsMenuGetParent";
    private static final String ABS_MENU_IS_ENABLED = "AbsMenuIsEnabled";
    private static final String MENU_BAR_ADD = "MenuBarAdd";
    private static final String GET_MENU_BAR = "GetMenuBar";
    private static final String SLIDER_GET_ORIENTATION = "SliderGetOrientation";
    private static final String MENU_BAR_NB = "MenuBarNb";
    private static final String SET_MENU_BAR = "SetMenuBar";
    private static final String SLIDER_SET_ORIENTATION = "SliderSetOrientation";
    private static final String MENU_BAR = "MenuBar";
    private static final String MENU_NB = "MenuNb";
    private static final String MENU_ADD = "MenuAdd";
    private static final String MENU = "Menu";
    private static final String MENU_ITEM = "MenuItem";
    private static final String ABS_MENU = "AbsMenu";
    private static final String MENU_GET = "MenuGet";
    private String aliasActionListener;
    private String aliasActionPerformed;
    private String aliasActionEvent;
    private String aliasWheelListener;
    private String aliasWheelMove;
    private String aliasWheelEvent;
    private String aliasWheelRotatedClicks;
    private String aliasAddChange;
    private String aliasTreeListener;
    private String aliasTreeListenerValueChanged;
    private String aliasTreeNode;
    private String aliasTreeNodeAdd;
    private String aliasTreeNodeInsert;
    private String aliasTreeNodeRemove;
    private String aliasTreeNodeRemoveFromParent;
    private String aliasTreeNodeRemoveAllChildren;
    private String aliasTreeNodeSetUserObject;
    private String aliasTreeNodeEq;
    private String aliasTreeNodeNb;
    private String aliasTreeNodeGetFirstChild;
    private String aliasTreeNodeGetLastChild;
    private String aliasTreeNodeGetNextSibling;
    private String aliasTreeNodeGetPreviousSibling;
    private String aliasTreeNodeGetParentNode;
    private String aliasTreeNodeGetUserObject;
    private String aliasTreeNodeIsAncestor;
    private String aliasTreeNodeIsDescendant;
    private String aliasTree;
    private String aliasTreeSetRootVisible;
    private String aliasTreeIsRootVisible;
    private String aliasTreeGetSelected;
    private String aliasTreeAddTreeListener;
    private String aliasTreeReload;
    private String aliasTableListener;
    private String aliasTableValueTableChanged;
    private String aliasTableGui;
    private String aliasTableGetSelectedRow;
    private String aliasTableGetSelectedRows;
    private String aliasTableGetSelectedRowCount;
    private String aliasTableGetRowCount;
    private String aliasTableSetRowCount;
    private String aliasTableGetColumnCount;
    private String aliasTableSetColumns;
    private String aliasTableGetColumnName;
    private String aliasTableGetValue;
    private String aliasTableSetValue;
    private String aliasTableGetRowAtPoint;
    private String aliasTableGetColumnAtPoint;
    private String aliasTableIsMultiple;
    private String aliasTableSetMultiple;
    private String aliasTableIsReorder;
    private String aliasTableSetReorder;
    private String aliasTableMoveColumn;
    private String aliasTableAddInterval;
    private String aliasTableRemoveInterval;
    private String aliasTableApplyChanges;
    private String aliasTableAddHeader;
    private String aliasTableAddSelect;
    private String aliasChangeListener;
    private String aliasStateChanged;
    private String aliasMouseListener;
    private String aliasMouseClicked;
    private String aliasMousePressed;
    private String aliasMouseReleased;
    private String aliasMouseEntered;
    private String aliasMouseExited;
    private String aliasMouseDragged;
    private String aliasMouseMoved;
    private String aliasMouseEvent;
    private String aliasMouseEventGetFirst;
    private String aliasMouseEventGetSecond;
    private String aliasMouseEventGetClicks;
    private String aliasMouseEventIsAlt;
    private String aliasMouseEventIsCtrl;
    private String aliasMouseEventIsShift;
    private String aliasMouseEventIsLeft;
    private String aliasMouseEventIsMiddle;
    private String aliasMouseEventIsRight;
    private String aliasWindowListener;
    private String aliasWindowOpened;
    private String aliasWindowClosed;
    private String aliasWindowClosing;
    private String aliasWindowIconified;
    private String aliasWindowDeiconified;
    private String aliasWindowActivated;
    private String aliasWindowDeactivated;
    private String aliasWindowEvent;
    private String aliasListSelection;
    private String aliasValueChanged;
    private String aliasRequestFocus;
    private String aliasCompToolTip;
    private String aliasCompFocusable;
    private String aliasCompOpaque;
    private String aliasCompBack;
    private String aliasCompFore;
    private String aliasCompGetFirstPos;
    private String aliasCompGetSecondPos;
    private String aliasCompLoc;
    private String aliasCompBorLine;
    private String aliasCompBorTitle;
    private String aliasCompBorLower;
    private String aliasCompBorRaise;
    private String aliasAddWheelListener;
    private String aliasRemoveWheelListener;
    private String aliasGetWheelListeners;
    private String aliasAddKeyListener;
    private String aliasRemoveKeyListener;
    private String aliasGetKeyListeners;
    private String aliasKeyListener;
    private String aliasKeyPressed;
    private String aliasKeyTyped;
    private String aliasKeyReleased;
    private String aliasKeyEvent;
    private String aliasKeyEventChar;
    private String aliasKeyEventCode;
    private String aliasKeyEventIsShift;
    private String aliasKeyEventIsAlt;
    private String aliasKeyEventIsCtrl;

    private String aliasConfirm;
    private String aliasConfirmMessage;
    private String aliasConfirmField;
    private String aliasConfirmOk;
    private String aliasConfirmYesNo;
    private String aliasConfirmFull;
    private String aliasConfirmFieldOk;
    private String aliasConfirmFieldCancel;
    private String aliasConfirmFieldYes;
    private String aliasConfirmFieldNo;
    private String aliasFrame;
    private String aliasDialog;
    private String aliasDialogIsModal;
    private String aliasDialogSetModal;
    private String aliasWindowType;
    private String aliasWindowTypeRelative;
    private String aliasRemoveWindowListener;
    private String aliasGetWindowListeners;
    private String aliasPanel;
    private String aliasPanelFlow;
    private String aliasPanelAbsolute;
    private String aliasPanelBorder;
    private String aliasPanelBorderNorth;
    private String aliasPanelBorderSouth;
    private String aliasPanelBorderEast;
    private String aliasPanelBorderWest;
    private String aliasPanelBorderCenter;
    private String aliasPanelBorderBeforeFirst;
    private String aliasPanelBorderBeforeLineBegins;
    private String aliasPanelBorderAfterLast;
    private String aliasPanelBorderAfterLineEnds;
    private String aliasPanelGrid;
    private String aliasPanelPageBox;
    private String aliasPanelValidate;
    private String aliasTabbedPane;
    private String aliasTabbedPaneNb;
    private String aliasTabbedPaneAdd;
    private String aliasTabbedPaneGet;
    private String aliasTabbedPaneGetTitle;
    private String aliasTabbedPaneSet;
    private String aliasTabbedPaneSetTitle;
    private String aliasTabbedPaneRemove;
    private String aliasTabbedPaneIndex;
    private String aliasTabbedPaneSelIndex;
    private String aliasButton;
    private String aliasProgBar;
    private String aliasProgBarValue;
    private String aliasProgBarMax;
    private String aliasProgBarMin;
    private String aliasProgBarOr;
    private String aliasImageLabel;
    private String aliasTextLabel;
    private String aliasScrollPane;
    private String aliasScrollPaneGetView;
    private String aliasScrollPaneSetView;
    private String aliasScrollPaneHorizontalValue;
    private String aliasScrollPaneVerticalValue;
    private String aliasScrollPaneValidate;
    private String aliasSplitPane;
    private String aliasSplitPaneGetDividerLocation;
    private String aliasSplitPaneSetDividerLocation;
    private String aliasSplitPaneGetDividerSize;
    private String aliasSplitPaneSetDividerSize;
    private String aliasSplitPaneIsContinuousLayout;
    private String aliasSplitPaneSetContinuousLayout;
    private String aliasSplitPaneIsOneTouchExpandable;
    private String aliasSplitPaneSetOneTouchExpandable;
    private String aliasSplitPaneGetLeft;
    private String aliasSplitPaneSetLeft;
    private String aliasSplitPaneGetRight;
    private String aliasSplitPaneSetRight;
    private String aliasSplitPaneValidate;
    private String aliasGetFont;
    private String aliasSetFont;
    private String aliasFont;
    private String aliasFontGetName;
    private String aliasFontGetSize;
    private String aliasFontIsBold;
    private String aliasFontIsItalic;
    private String aliasFontStringWidth;
    private String aliasImage;
    private String aliasImageGetWidth;
    private String aliasImageGetHeight;
    private String aliasImageGet;
    private String aliasImageSet;
    private String aliasImageIsWithAlpha;
    private String aliasImageEq;
    private String aliasImageGetColor;
    private String aliasImageSetColor;
    private String aliasImageGetFont;
    private String aliasImageSetFont;
    private String aliasImageDraw;
    private String aliasImageDrawLine;
    private String aliasImageDrawRect;
    private String aliasImageDrawOval;
    private String aliasImageFillRect;
    private String aliasImageFillOval;
    private String aliasImageDrawPolygon;
    private String aliasImageFillPolygon;
    private String aliasImageDispose;
    private String aliasColor;
    private String aliasColorRed;
    private String aliasColorGreen;
    private String aliasColorBlue;
    private String aliasColorAlpha;
    private String aliasColorIsTransparent;
    private String aliasComponentSetPaint;
    private String aliasComponentGetPaint;
    private String aliasComponentRepaint;
    private String aliasComponentSetAutoscrolls;
    private String aliasComponentIsAutoscrolls;
    private String aliasComponentGetWidth;
    private String aliasComponentGetHeight;
    private String aliasComponentGetPreferredSize;
    private String aliasComponentSetPreferredSize;
    private String aliasComponentSetSize;
    private String aliasComponentIsVisible;
    private String aliasComponentSetVisible;
    private String aliasComponentInvokeLater;
    private String aliasComponent;
    private String aliasDimension;
    private String aliasDimensionGetHeight;
    private String aliasDimensionGetWidth;
    private String aliasSetContent;
    private String aliasAddCompo;
    private String aliasGetParentCompo;
    private String aliasGetNextCompo;
    private String aliasGetIndexCompo;
    private String aliasRemoveAll;
    private String aliasAddListener;
    private String aliasRemoveListener;
    private String aliasGetListeners;
    private String aliasAddWindowListener;
    private String aliasSetLabelText;
    private String aliasSetLabelImage;
    private String aliasPaint;
    private String aliasPaintMethod;
    private String aliasPaintAdd;
    private String aliasPaintSet;
    private String aliasPaintRefresh;
    private String aliasPaintRefreshOne;
    private String aliasRemoveCompo;
    private String aliasCount;
    private String aliasIsVisible;
    private String aliasSetVisible;
    private String aliasWindow;
    private String aliasWindowSet;
    private String aliasWindowSetAll;
    private String aliasWindowSetAdd;
    private String aliasWindowSetContains;
    private String aliasWindowSetRemove;
    private String aliasWindowSetSnapshot;
    private String aliasArgs;
    private String aliasPack;
    private String aliasDispose;
    private String aliasInput;
    private String aliasInputIsEnabled;
    private String aliasInputSetEnabled;

    private String aliasRender;
    private String aliasRenderGetHeight;
    private String aliasRenderGetWidth;
    private String aliasRenderGetPaint;
    private String aliasRenderSetHeight;
    private String aliasRenderSetWidth;
    private String aliasRenderSetPaint;
    private String aliasGrList;
    private String aliasGrListAdd;
    private String aliasGrListSet;
    private String aliasGrListGetListView;
    private String aliasGrListGetSelectedIndexes;
    private String aliasGrListSetSelectedIndexes;
    private String aliasGrListClearSelection;
    private String aliasGrListClear;
    private String aliasGrListRemove;
    private String aliasGrListUpdateGraphics;
    private String aliasGrListGetRender;
    private String aliasGrListSetRender;
    private String aliasGrListGetSelections;
    private String aliasGrListAddSelection;
    private String aliasGrListRemoveSelection;
    private String aliasGrListGetVisibleRowCount;
    private String aliasGrListSetVisibleRowCount;

    private String aliasCombo;
    private String aliasComboGetSelectedItem;
    private String aliasComboAddItem;
    private String aliasComboGetItemCount;
    private String aliasComboSelectItem;
    private String aliasComboAddListener;
    private String aliasComboRemoveListener;
    private String aliasComboGetListeners;
    private String aliasComboGetSelectedIndexes;
    private String aliasComboGetSelectedIndex;
    private String aliasComboRemoveAllItems;
    private String aliasComboRemoveItem;

    private String aliasButtonGroup;
    private String aliasButtonGroupAdd;
    private String aliasRadio;
    private String aliasRadioIsSelected;
    private String aliasRadioSetSelected;
    private String aliasRadioGetText;
    private String aliasRadioSetText;
    private String aliasPopupMenu;
    private String aliasPopupMenuAdd;
    private String aliasPopupMenuGetComp;
    private String aliasPopupMenuRemoveComp;
    private String aliasPopupMenuNbComp;
    private String aliasPopupMenuAddMenu;
    private String aliasPopupMenuGetMenu;
    private String aliasPopupMenuRemoveMenu;
    private String aliasPopupMenuNbMenu;
    private String aliasPopupMenuShow;
    private String aliasTextField;
    private String aliasTextFieldGetText;
    private String aliasTextFieldSetText;
    private String aliasTextFieldAuto;
    private String aliasTextFieldAddAction;
    private String aliasTextFieldAddDocument;
    private String aliasTextFieldAddPopup;
    private String aliasTextArea;
    private String aliasTextAreaGetText;
    private String aliasTextAreaSetText;
    private String aliasTextAreaGetTabSize;
    private String aliasTextAreaSetTabSize;
    private String aliasTextAreaAppend;
    private String aliasTextAreaInsert;
    private String aliasTextAreaReplaceRange;
    private String aliasTextAreaReplaceSelection;
    private String aliasTextAreaGetSelectedText;
    private String aliasTextAreaSetSelectionStart;
    private String aliasTextAreaSetSelectionEnd;
    private String aliasTextAreaSelect;
    private String aliasTextAreaSelectAll;
    private String aliasCheckBox;
    private String aliasCheckBoxGetText;
    private String aliasCheckBoxSetText;
    private String aliasCheckBoxIsSelected;
    private String aliasCheckBoxSetSelected;
    private String aliasCheckBoxAddAction;
    private String aliasSpinner;
    private String aliasSpinnerSetRange;
    private String aliasSpinnerSetRangeValue;
    private String aliasSpinnerGetValue;
    private String aliasSpinnerSetValue;
    private String aliasSpinnerGetMax;
    private String aliasSpinnerSetMax;
    private String aliasSpinnerGetMin;
    private String aliasSpinnerSetMin;
    private String aliasSpinnerGetStep;
    private String aliasSpinnerSetStep;
    private String aliasSlider;
    private String aliasSliderGetValue;
    private String aliasSliderSetValue;
    private String aliasSliderGetMax;
    private String aliasSliderSetMax;
    private String aliasSliderGetMin;
    private String aliasSliderSetMin;
    private String aliasSliderGetOrientation;
    private String aliasSliderSetOrientation;
    private String aliasGetMenuBar;
    private String aliasSetMenuBar;
    private String aliasMenuBar;
    private String aliasMenuBarAdd;
    private String aliasMenuBarGet;
    private String aliasMenuBarRemove;
    private String aliasMenuBarNb;
    private String aliasAbsMenu;
    private String aliasAbsMenuGetParent;
    private String aliasAbsMenuIsEnabled;
    private String aliasAbsMenuSetEnabled;
    private String aliasAbsMenuSetDeepEnabled;
    private String aliasAbsMenuGetText;
    private String aliasAbsMenuSetText;
    private String aliasMenu;
    private String aliasMenuAdd;
    private String aliasMenuGet;
    private String aliasMenuRemove;
    private String aliasMenuNb;
    private String aliasMenuAddSeparator;
    private String aliasAbsMenuItem;
    private String aliasAbsMenuItemAddAction;
    private String aliasMenuItem;
    private String aliasMenuItemCheck;
    private String aliasMenuItemCheckIsSelected;
    private String aliasMenuItemCheckSetSelected;
    private final GuiAliasParameters guiAliasParameters = new GuiAliasParameters();
    private final StringMap<String> propertiesGui = MessCdmGuiGr.ms();

    private static StringList newList(Struct _s) {
        if (!(_s instanceof ArrayStruct)) {
            return new StringList();
        }
        StringList l_ = new StringList();
        for (Struct s: ((ArrayStruct)_s).list()) {
            if (!(s instanceof StringStruct)) {
                continue;
            }
            l_.add(((StringStruct)s).getInstance());
        }
        return l_;
    }

    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content, StringList _predefinedClasses, StringList _predefinedInterfacesInitOrder) {
        StringMap<String> stds_ = new StringMap<String>();
        String content_ = res("resources_lg_gui/action_event.txt");
        PrimitiveTypes primTypes_ = _content.getPrimTypes();
        StringMap<PrimitiveType> pr_ = primTypes_.getPrimitiveTypes();
        AliasCore co_ = _content.getCoreNames();
        String public_ = _keyWords.getKeyWordPublic();
        String interface_ = _keyWords.getKeyWordInterface();
        String class_ = _keyWords.getKeyWordClass();
        String abstract_ = _keyWords.getKeyWordAbstract();
        String static_ = _keyWords.getKeyWordStatic();
        String if_ = _keyWords.getKeyWordIf();
        String elseif_ = _keyWords.getKeyWordElseif();
        String while_ = _keyWords.getKeyWordWhile();
        String final_ = _keyWords.getKeyWordFinal();
        String return_ = _keyWords.getKeyWordReturn();
        String continue_ = _keyWords.getKeyWordContinue();
        String break_ = _keyWords.getKeyWordBreak();
        String for_ = _keyWords.getKeyWordFor();
        String new_ = _keyWords.getKeyWordNew();
        String null_ = _keyWords.getKeyWordNull();
        String cast_ = _keyWords.getKeyWordCast();
        String true_ = _keyWords.getKeyWordTrue();
        String false_ = _keyWords.getKeyWordFalse();
        String is_ = _keyWords.getKeyWordInstanceof();
        StringMap<String> map_;
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{ActionListener}", aliasActionListener);
        map_.put("{actionPerformed}", aliasActionPerformed);
        map_.put("{ActionEvent}", aliasActionEvent);
        map_.put("{void}", co_.getAliasVoid());
        map_.put("{e}", guiAliasParameters.getAliasActionListener0ActionPerformed0());
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasActionListener);
        stds_.put(aliasActionListener, content_);
        _predefinedInterfacesInitOrder.add(aliasActionListener);
        content_ = res("resources_lg_gui/change_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{ChangeListener}", aliasChangeListener);
        map_.put("{stateChanged}", aliasStateChanged);
        map_.put("{void}", co_.getAliasVoid());
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasChangeListener);
        stds_.put(aliasChangeListener, content_);
        _predefinedInterfacesInitOrder.add(aliasChangeListener);
        content_ = res("resources_lg_gui/tree_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{TreeListener}", aliasTreeListener);
        map_.put("{valueChanged}", aliasTreeListenerValueChanged);
        map_.put("{TreeNode}", aliasTreeNode);
        map_.put("{void}", co_.getAliasVoid());
        map_.put("{e}", guiAliasParameters.getAliasTreeListener0TreeListenerValueChanged0());
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasTreeListener);
        stds_.put(aliasTreeListener, content_);
        _predefinedInterfacesInitOrder.add(aliasTreeListener);
        content_ = res("resources_lg_gui/table_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{TableListener}", aliasTableListener);
        map_.put("{valueChanged}", aliasTableValueTableChanged);
        map_.put("{int}", primTypes_.getAliasPrimInteger());
        map_.put("{void}", co_.getAliasVoid());
        map_.put("{e}", guiAliasParameters.getAliasTableListener0TableValueTableChanged0());
        map_.put("{f}", guiAliasParameters.getAliasTableListener0TableValueTableChanged1());
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasTableListener);
        stds_.put(aliasTableListener, content_);
        _predefinedInterfacesInitOrder.add(aliasTableListener);
        content_ = res("resources_lg_gui/mouse_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{MouseListener}", aliasMouseListener);
        map_.put("{mouseClicked}", aliasMouseClicked);
        map_.put("{mousePressed}", aliasMousePressed);
        map_.put("{mouseReleased}", aliasMouseReleased);
        map_.put("{mouseEntered}", aliasMouseEntered);
        map_.put("{mouseExited}", aliasMouseExited);
        map_.put("{mouseDragged}", aliasMouseDragged);
        map_.put("{mouseMoved}", aliasMouseMoved);
        map_.put("{MouseEvent}", aliasMouseEvent);
        map_.put("{void}", co_.getAliasVoid());
        map_.put("{a}", guiAliasParameters.getAliasMouseListener0MouseClicked0());
        map_.put("{b}", guiAliasParameters.getAliasMouseListener0MousePressed0());
        map_.put("{c}", guiAliasParameters.getAliasMouseListener0MouseReleased0());
        map_.put("{d}", guiAliasParameters.getAliasMouseListener0MouseEntered0());
        map_.put("{e}", guiAliasParameters.getAliasMouseListener0MouseExited0());
        map_.put("{f}", guiAliasParameters.getAliasMouseListener0MouseDragged0());
        map_.put("{g}", guiAliasParameters.getAliasMouseListener0MouseMoved0());
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasMouseListener);
        stds_.put(aliasMouseListener, content_);
        _predefinedInterfacesInitOrder.add(aliasMouseListener);
        content_ = res("resources_lg_gui/wheel_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{WheelListener}", aliasWheelListener);
        map_.put("{mouseMoveWheel}", aliasWheelMove);
        map_.put("{MouseWheelEvent}", aliasWheelEvent);
        map_.put("{e}", guiAliasParameters.getAliasWheelListener0WheelMove0());
        map_.put("{void}", co_.getAliasVoid());
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasWheelListener);
        stds_.put(aliasWheelListener, content_);
        _predefinedInterfacesInitOrder.add(aliasWheelListener);
        content_ = res("resources_lg_gui/key_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{KeyListener}", aliasKeyListener);
        map_.put("{keyPressed}", aliasKeyPressed);
        map_.put("{keyTyped}", aliasKeyTyped);
        map_.put("{keyReleased}", aliasKeyReleased);
        map_.put("{KeyEvent}", aliasKeyEvent);
        map_.put("{void}", co_.getAliasVoid());
        map_.put("{a}", guiAliasParameters.getAliasKeyListener0KeyPressed0());
        map_.put("{b}", guiAliasParameters.getAliasKeyListener0KeyTyped0());
        map_.put("{c}", guiAliasParameters.getAliasKeyListener0KeyReleased0());
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasKeyListener);
        stds_.put(aliasKeyListener, content_);
        _predefinedInterfacesInitOrder.add(aliasKeyListener);
        content_ = res("resources_lg_gui/window_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{WindowListener}", aliasWindowListener);
        map_.put("{windowOpened}", aliasWindowOpened);
        map_.put("{windowClosing}", aliasWindowClosing);
        map_.put("{windowClosed}", aliasWindowClosed);
        map_.put("{windowIconified}", aliasWindowIconified);
        map_.put("{windowDeiconified}", aliasWindowDeiconified);
        map_.put("{windowActivated}", aliasWindowActivated);
        map_.put("{windowDeactivated}", aliasWindowDeactivated);
        map_.put("{WindowEvent}", aliasWindowEvent);
        map_.put("{void}", co_.getAliasVoid());
        map_.put("{a}", guiAliasParameters.getAliasWindowListener0WindowOpened0());
        map_.put("{b}", guiAliasParameters.getAliasWindowListener0WindowClosing0());
        map_.put("{c}", guiAliasParameters.getAliasWindowListener0WindowClosed0());
        map_.put("{d}", guiAliasParameters.getAliasWindowListener0WindowIconified0());
        map_.put("{e}", guiAliasParameters.getAliasWindowListener0WindowDeiconified0());
        map_.put("{f}", guiAliasParameters.getAliasWindowListener0WindowActivated0());
        map_.put("{g}", guiAliasParameters.getAliasWindowListener0WindowDeactivated0());
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasWindowListener);
        stds_.put(aliasWindowListener, content_);
        _predefinedInterfacesInitOrder.add(aliasWindowListener);
        content_ = res("resources_lg_gui/list_event.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{interface}", interface_);
        map_.put("{ListSelection}", aliasListSelection);
        map_.put("{valueChanged}", aliasValueChanged);
        map_.put("{int}", primTypes_.getAliasPrimInteger());
        map_.put("{void}", co_.getAliasVoid());
        map_.put("{e}", guiAliasParameters.getAliasListSelection0ValueChanged0());
        map_.put("{f}", guiAliasParameters.getAliasListSelection0ValueChanged1());
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasListSelection);
        stds_.put(aliasListSelection, content_);
        _predefinedInterfacesInitOrder.add(aliasListSelection);
        content_ = res("resources_lg_gui/repaint.txt");
        map_ = new StringMap<String>();
        map_.put("{public}", public_);
        map_.put("{abstract}", abstract_);
        map_.put("{final}", final_);
        map_.put("{class}", class_);
        map_.put("{Paint}", aliasPaint);
        map_.put("{paint}", aliasPaintMethod);
        map_.put("{paintPar}", guiAliasParameters.getAliasPaint0PaintMethod0());
        map_.put("{add}", aliasPaintAdd);
        map_.put("{set}", aliasPaintSet);
        map_.put("{setPar0}", guiAliasParameters.getAliasPaint0TabbedPaneSet0());
        map_.put("{setPar1}", guiAliasParameters.getAliasPaint0TabbedPaneSet1());
        map_.put("{setPar2}", guiAliasParameters.getAliasPaint0TabbedPaneSet2());
        map_.put("{addPar00}", guiAliasParameters.getAliasPaint0TabbedPaneAdd0());
        map_.put("{addPar01}", guiAliasParameters.getAliasPaint0TabbedPaneAdd1());
        map_.put("{addPar02}", guiAliasParameters.getAliasPaint0TabbedPaneAdd2());
        map_.put("{addPar10}", guiAliasParameters.getAliasPaint1TabbedPaneAdd0());
        map_.put("{addPar11}", guiAliasParameters.getAliasPaint1TabbedPaneAdd1());
        map_.put("{refreshPar}", guiAliasParameters.getAliasPaint0PaintRefresh0());
        map_.put("{refresh}", aliasPaintRefresh);
        map_.put("{refreshOne}", aliasPaintRefreshOne);
        map_.put("{refreshPar0}", guiAliasParameters.getAliasPaint0PaintRefreshOne0());
        map_.put("{w}", guiAliasParameters.getAliasPaint0PaintRefreshOne1());
        map_.put("{h0}", guiAliasParameters.getAliasPaint0PaintRefreshOne2());
        map_.put("{o0}", guiAliasParameters.getAliasPaint0PaintRefreshOne3());
        map_.put("{f}", guiAliasParameters.getAliasPaint0PaintRefreshOne4());
        map_.put("{s}", guiAliasParameters.getAliasPaint0PaintRefreshOne5());
        map_.put("{static}", static_);
        map_.put("{if}", if_);
        map_.put("{elseif}", elseif_);
        map_.put("{true}", true_);
        map_.put("{false}", false_);
        map_.put("{return}", return_);
        map_.put("{break}", break_);
        map_.put("{continue}", continue_);
        map_.put("{is}", is_);
        map_.put("{while}", while_);
        map_.put("{null}", null_);
        map_.put("{cast}", cast_);
        map_.put("{Component}", aliasComponent);
        map_.put("{Panel}", aliasPanel);
        map_.put("{Fct}", _content.getReflect().getAliasFct());
        map_.put("{void}", co_.getAliasVoid());
        map_.put("{call}", _content.getReflect().getAliasCall());
        map_.put("{getComponent}", aliasGetIndexCompo);
        map_.put("{next}", aliasGetNextCompo);
        map_.put("{getParent}", aliasGetParentCompo);
        map_.put("{getPainting}", aliasComponentGetPaint);
        map_.put("{c}", tr("c", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{p}", tr("p", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{r}", tr("r", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{o}", tr("o", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{la}", tr("la", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{h}", tr("h", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{hf}", tr("hf", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{lf}", tr("lf", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{pf}", tr("pf", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{par}", tr("par", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{pan}", tr("pan", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{fct}", tr("fct", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{nb}", tr("nb", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{img}", tr("img", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{ha}", tr("ha", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{ft}", tr("ft", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{text}", tr("text", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{mw}", tr("mw", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{er}", tr("er", _keyWords, pr_, co_,
                guiAliasParameters.getAliasPaint0PaintMethod0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet0(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet1(),
                guiAliasParameters.getAliasPaint0TabbedPaneSet2(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0TabbedPaneAdd2(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd0(),
                guiAliasParameters.getAliasPaint1TabbedPaneAdd1(),
                guiAliasParameters.getAliasPaint0PaintRefresh0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne0(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne1(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne2(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne3(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne4(),
                guiAliasParameters.getAliasPaint0PaintRefreshOne5()));
        map_.put("{getRender}",aliasGrListGetRender);
        map_.put("{Math}",_content.getMathRef().getAliasMath());
        map_.put("{max}",_content.getMathRef().getAliasMax());
        map_.put("{StringUtil}",_content.getCoreNames().getAliasStringUtil());
        map_.put("{valueOf}",_content.getCoreNames().getAliasStringUtilValueOf());
        map_.put("{widthStr}",aliasFontStringWidth);
        map_.put("{setColor}",aliasImageSetColor);
        map_.put("{fillRect}",aliasImageFillRect);
        map_.put("{drawString}",aliasImageDraw);
        map_.put("{getFont}",aliasGetFont);
        map_.put("{size}",aliasFontGetSize);
        map_.put("{Color}",aliasColor);
        map_.put("{getCompWidth}",aliasComponentGetWidth);
        map_.put("{else}",_keyWords.getKeyWordElse());
        map_.put("{setFont}",aliasImageSetFont);
        map_.put("{getHeight}",aliasRenderGetHeight);
        map_.put("{getWidth}",aliasRenderGetWidth);
        map_.put("{getPaint}",aliasRenderGetPaint);
        map_.put("{Image}",aliasImage);
        map_.put("{ImageLabel}",aliasImageLabel);
        map_.put("{addCompo}",aliasGrListAdd);
        map_.put("{setCompo}",aliasGrListSet);
        map_.put("{getView}",aliasGrListGetListView);
        map_.put("{getSelected}",aliasGrListGetSelectedIndexes);
        map_.put("{updateGraphics}",aliasGrListUpdateGraphics);
        map_.put("{length}",_content.getCharSeq().getAliasLength());
        map_.put("{for}",for_);
        map_.put("{new}",new_);
        map_.put("{var}", _keyWords.getKeyWordVar());
        map_.put("{int}",primTypes_.getAliasPrimInteger());
        map_.put("{GrList}",aliasGrList);
        map_.put("{Object}",co_.getAliasObject());
        map_.put("{dispose}",aliasImageDispose);
        map_.put("{boolean}",primTypes_.getAliasPrimBoolean());
        content_ = StringUtil.formatQuote(content_, map_);
        _predefinedClasses.add(aliasPaint);
        stds_.put(aliasPaint, content_);
        _predefinedInterfacesInitOrder.add(aliasPaint);
        return stds_;
    }

    public String res(String _file) {
        return propertiesGui.getVal(_file);
    }

    public void buildOther(LgNamesContent _content, CustAliases _cust) {
        CustList<CstFieldInfo> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        StandardClass stdcl_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasWindowType, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasPanel);
        method_ = new StandardMethod(aliasSetContent, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowType0SetContent0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasWindowTypeRelative, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowType0WindowTypeRelative0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPack, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVisible, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSetVisible, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowType0ComponentSetVisible0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMenuBar, params_, aliasMenuBar, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasMenuBar);
        method_ = new StandardMethod(aliasSetMenuBar, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowType0SetMenuBar0()));
        methods_.add( method_);
        params_ = new StringList(aliasWindowListener);
        method_ = new StandardMethod(aliasAddWindowListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowType0AddWindowListener0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDispose, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasWindowType, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasConfirm, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.HYPER_ABSTRACT);
        params_ = new StringList(aliasImage,aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmField, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm0ConfirmField0(),guiAliasParameters.getAliasConfirm0ConfirmField1(),guiAliasParameters.getAliasConfirm0ConfirmField2(),guiAliasParameters.getAliasConfirm0ConfirmField3(),guiAliasParameters.getAliasConfirm0ConfirmField4(),guiAliasParameters.getAliasConfirm0ConfirmField5(),guiAliasParameters.getAliasConfirm0ConfirmField6()));
        methods_.add( method_);
        params_ = new StringList(aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmField, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm1ConfirmField0(),guiAliasParameters.getAliasConfirm1ConfirmField1(),guiAliasParameters.getAliasConfirm1ConfirmField2(),guiAliasParameters.getAliasConfirm1ConfirmField3(),guiAliasParameters.getAliasConfirm1ConfirmField4(),guiAliasParameters.getAliasConfirm1ConfirmField5()));
        methods_.add( method_);
        params_ = new StringList(aliasImage,aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmFull, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm0ConfirmFull0(),guiAliasParameters.getAliasConfirm0ConfirmFull1(),guiAliasParameters.getAliasConfirm0ConfirmFull2(),guiAliasParameters.getAliasConfirm0ConfirmFull3(),guiAliasParameters.getAliasConfirm0ConfirmFull4(),guiAliasParameters.getAliasConfirm0ConfirmFull5(),guiAliasParameters.getAliasConfirm0ConfirmFull6()));
        methods_.add( method_);
        params_ = new StringList(aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmFull, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm1ConfirmFull0(),guiAliasParameters.getAliasConfirm1ConfirmFull1(),guiAliasParameters.getAliasConfirm1ConfirmFull2(),guiAliasParameters.getAliasConfirm1ConfirmFull3(),guiAliasParameters.getAliasConfirm1ConfirmFull4(),guiAliasParameters.getAliasConfirm1ConfirmFull5()));
        methods_.add( method_);
        params_ = new StringList(aliasImage,aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmYesNo, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm0ConfirmYesNo0(),guiAliasParameters.getAliasConfirm0ConfirmYesNo1(),guiAliasParameters.getAliasConfirm0ConfirmYesNo2(),guiAliasParameters.getAliasConfirm0ConfirmYesNo3(),guiAliasParameters.getAliasConfirm0ConfirmYesNo4(),guiAliasParameters.getAliasConfirm0ConfirmYesNo5()));
        methods_.add( method_);
        params_ = new StringList(aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmYesNo, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm1ConfirmYesNo0(),guiAliasParameters.getAliasConfirm1ConfirmYesNo1(),guiAliasParameters.getAliasConfirm1ConfirmYesNo2(),guiAliasParameters.getAliasConfirm1ConfirmYesNo3(),guiAliasParameters.getAliasConfirm1ConfirmYesNo4()));
        methods_.add( method_);
        params_ = new StringList(aliasImage,aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmOk, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm0ConfirmOk0(),guiAliasParameters.getAliasConfirm0ConfirmOk1(),guiAliasParameters.getAliasConfirm0ConfirmOk2(),guiAliasParameters.getAliasConfirm0ConfirmOk3(),guiAliasParameters.getAliasConfirm0ConfirmOk4()));
        methods_.add( method_);
        params_ = new StringList(aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmOk, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm1ConfirmOk0(),guiAliasParameters.getAliasConfirm1ConfirmOk1(),guiAliasParameters.getAliasConfirm1ConfirmOk2(),guiAliasParameters.getAliasConfirm1ConfirmOk3()));
        methods_.add( method_);
        params_ = new StringList(aliasImage,aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmMessage, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm0ConfirmMessage0(),guiAliasParameters.getAliasConfirm0ConfirmMessage1(),guiAliasParameters.getAliasConfirm0ConfirmMessage2(),guiAliasParameters.getAliasConfirm0ConfirmMessage3(),guiAliasParameters.getAliasConfirm0ConfirmMessage4()));
        methods_.add( method_);
        params_ = new StringList(aliasWindowType,_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasConfirmMessage, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasConfirm1ConfirmMessage0(),guiAliasParameters.getAliasConfirm1ConfirmMessage1(),guiAliasParameters.getAliasConfirm1ConfirmMessage2(),guiAliasParameters.getAliasConfirm1ConfirmMessage3()));
        methods_.add( method_);
        fields_.add(new CstFieldInfo(aliasConfirmFieldOk,_content.getPrimTypes().getAliasPrimInteger()));
        fields_.add(new CstFieldInfo(aliasConfirmFieldYes,_content.getPrimTypes().getAliasPrimInteger()));
        fields_.add(new CstFieldInfo(aliasConfirmFieldNo,_content.getPrimTypes().getAliasPrimInteger()));
        fields_.add(new CstFieldInfo(aliasConfirmFieldCancel,_content.getPrimTypes().getAliasPrimInteger()));
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasConfirm, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasWindowSet, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasWindow);
        method_ = new StandardMethod(aliasWindowSetAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowSet0TabbedPaneAdd0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasWindowSetAll, params_, aliasWindowSet, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList(aliasWindow);
        method_ = new StandardMethod(aliasWindowSetContains, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowSet0WindowSetContains0()));
        methods_.add( method_);
        params_ = new StringList(aliasWindow);
        method_ = new StandardMethod(aliasWindowSetRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasWindowSet0TreeNodeRemove0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasWindowSetSnapshot, params_, StringExpUtil.getPrettyArrayType(aliasWindowType), false, MethodModifier.FINAL);
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasWindowSet, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasFrame, fields_, constructors_, methods_, aliasWindowType, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasWindow, params_, aliasFrame, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasArgs, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC);
        methods_.add( method_);
        StandardConstructor ctor_;
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasFrame, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasDialog, fields_, constructors_, methods_, aliasWindowType, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDialogIsModal, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasDialogSetModal, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasDialog0DialogSetModal0()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasDialog, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasComponent, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParentCompo, params_, aliasComponent, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetNextCompo, params_, aliasComponent, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetFont, params_, aliasFont, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasFont);
        method_ = new StandardMethod(aliasSetFont, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0SetFont0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompBack, params_, aliasColor, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasCompBack, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompBack0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompFore, params_, aliasColor, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasCompFore, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompFore0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompFocusable, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCompFocusable, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompFocusable0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompOpaque, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCompOpaque, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompOpaque0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompToolTip, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasCompToolTip, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompToolTip0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompGetFirstPos, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompGetSecondPos, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasCompLoc, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompLoc0(),guiAliasParameters.getAliasComponent0CompLoc1()));
        methods_.add( method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasCompBorLine, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompBorLine0()));
        methods_.add( method_);
        params_ = new StringList(aliasColor,_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasCompBorLine, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent1CompBorLine0(),guiAliasParameters.getAliasComponent1CompBorLine1()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasCompBorTitle, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0CompBorTitle0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompBorLower, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCompBorRaise, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);

        params_ = new StringList();
        String type_ = StringUtil.concat(_content.getReflect().getAliasFct(),"<",aliasComponent,",?>");
        method_ = new StandardMethod(aliasComponentGetPaint, params_, type_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(type_);
        method_ = new StandardMethod(aliasComponentSetPaint, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0ComponentSetPaint0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentRepaint, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        type_ = _content.getPrimTypes().getAliasPrimBoolean();
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentIsAutoscrolls, params_, type_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(type_);
        method_ = new StandardMethod(aliasComponentSetAutoscrolls, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0ComponentSetAutoscrolls0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentGetPreferredSize, params_, aliasDimension, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasDimension);
        method_ = new StandardMethod(aliasComponentSetPreferredSize, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0ComponentSetPreferredSize0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentIsVisible, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasComponentSetVisible, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0ComponentSetVisible0()));
        methods_.add( method_);
        params_ = new StringList(_cust.getAliasRunnable());
        method_ = new StandardMethod(aliasComponentInvokeLater, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasComponent0ComponentInvokeLater0()));
        methods_.add( method_);
        params_ = new StringList(aliasKeyListener);
        method_ = new StandardMethod(aliasAddKeyListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0AddKeyListener0()));
        methods_.add( method_);
        params_ = new StringList(aliasKeyListener);
        method_ = new StandardMethod(aliasRemoveKeyListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0RemoveKeyListener0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetKeyListeners, params_, StringExpUtil.getPrettyArrayType(aliasKeyListener), false, MethodModifier.FINAL,new StringList());
        methods_.add( method_);
        params_ = new StringList(aliasWheelListener);
        method_ = new StandardMethod(aliasAddWheelListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0AddWheelListener0()));
        methods_.add( method_);
        params_ = new StringList(aliasWheelListener);
        method_ = new StandardMethod(aliasRemoveWheelListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0RemoveWheelListener0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetWheelListeners, params_, StringExpUtil.getPrettyArrayType(aliasWheelListener), false, MethodModifier.FINAL,new StringList());
        methods_.add( method_);
        params_ = new StringList(aliasMouseListener);
        method_ = new StandardMethod(aliasAddListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0AddListener0()));
        methods_.add( method_);
        params_ = new StringList(aliasMouseListener);
        method_ = new StandardMethod(aliasRemoveListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasComponent0RemoveListener0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetListeners, params_, StringExpUtil.getPrettyArrayType(aliasMouseListener), false, MethodModifier.FINAL,new StringList());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRequestFocus, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentGetHeight, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComponentGetWidth, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasComponent, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasDimension, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDimensionGetHeight, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDimensionGetWidth, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasDimension0Dimension0(),guiAliasParameters.getAliasDimension0Dimension1()));
        constructors_.add(ctor_);
        params_ = new StringList(aliasDimension);
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasDimension1Dimension0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasDimension, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTreeNode, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeAdd0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeInsert, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeInsert0(),guiAliasParameters.getAliasTreeNode0TreeNodeInsert1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTreeNodeRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeRemove0()));
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode1TreeNodeRemove0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeRemoveFromParent, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeRemoveAllChildren, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTreeNodeSetUserObject, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeSetUserObject0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetUserObject, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeNb, params_,_content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetFirstChild, params_,aliasTreeNode, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetLastChild, params_,aliasTreeNode, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetPreviousSibling, params_,aliasTreeNode, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetNextSibling, params_,aliasTreeNode, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeNodeGetParentNode, params_,aliasTreeNode, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeIsAncestor, params_,_content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeIsAncestor0()));
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeIsDescendant, params_,_content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeIsDescendant0()));
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode,aliasTreeNode);
        method_ = new StandardMethod(aliasTreeNodeEq, params_,_content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasTreeNode0TreeNodeEq0(),guiAliasParameters.getAliasTreeNode0TreeNodeEq1()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTreeNode0TreeNode0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasTreeNode, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTree, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasTreeListener);
        method_ = new StandardMethod(aliasTreeAddTreeListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTree0TreeAddTreeListener0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasTreeSetRootVisible, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTree0TreeSetRootVisible0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeIsRootVisible, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeGetSelected, params_, aliasTreeNode, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode);
        method_ = new StandardMethod(aliasTreeGetSelected, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTree0TreeGetSelected0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTreeReload, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasTreeNode);
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTree0Tree0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasTree, std_);


        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTableGui, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasMouseListener);
        method_ = new StandardMethod(aliasTableAddHeader, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableAddHeader0()));
        methods_.add( method_);
        params_ = new StringList(aliasTableListener);
        method_ = new StandardMethod(aliasTableAddSelect, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableAddSelect0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableIsMultiple, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasTableSetMultiple, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableSetMultiple0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableIsReorder, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasTableSetReorder, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableSetReorder0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetColumnName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableGetColumnName0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetColumnCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetColumnAtPoint, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableGetColumnAtPoint0(),guiAliasParameters.getAliasTableGui0TableGetColumnAtPoint1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetRowAtPoint, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableGetRowAtPoint0(),guiAliasParameters.getAliasTableGui0TableGetRowAtPoint1()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetRowCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableSetRowCount, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableSetRowCount0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTableSetColumns, params_, _content.getCoreNames().getAliasVoid(), true, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableSetColumns0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetSelectedRow, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetSelectedRowCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableGetSelectedRows, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimInteger()), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableMoveColumn, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableMoveColumn0(),guiAliasParameters.getAliasTableGui0TableMoveColumn1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableAddInterval, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableAddInterval0(),guiAliasParameters.getAliasTableGui0TableAddInterval1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableRemoveInterval, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TableRemoveInterval0(),guiAliasParameters.getAliasTableGui0TableRemoveInterval1()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTableApplyChanges, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableSetValue, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TreeNodeSetUserObject0(),guiAliasParameters.getAliasTableGui0TreeNodeSetUserObject1(),guiAliasParameters.getAliasTableGui0TreeNodeSetUserObject2()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTableGetValue, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTableGui0TreeNodeGetUserObject0(),guiAliasParameters.getAliasTableGui0TreeNodeGetUserObject1()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,true,new StringList(guiAliasParameters.getAliasTableGui0TableGui0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasTableGui, std_);

        buildEvents(_content);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasPanel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasAddCompo, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanel0TabbedPaneAdd0()));
        methods_.add( method_);
        params_ = new StringList(aliasComponent,_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasAddCompo, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanel1TabbedPaneAdd0(),guiAliasParameters.getAliasPanel1TabbedPaneAdd1()));
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasRemoveCompo, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanel0RemoveCompo0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasRemoveCompo, params_, aliasComponent, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanel1RemoveCompo0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGetIndexCompo, params_, aliasComponent, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanel0TreeNodeGetUserObject0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelFlow, params_, aliasPanel, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelAbsolute, params_, aliasPanel, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelPageBox, params_, aliasPanel, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasPanelGrid, params_, aliasPanel, false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasPanel0PanelGrid0(),guiAliasParameters.getAliasPanel0PanelGrid1()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRemoveAll, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPanelValidate, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasPanel, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasPanelBorder, fields_, constructors_, methods_, aliasPanel, MethodModifier.FINAL);
        params_ = new StringList(aliasComponent,_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasAddCompo, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPanelBorder0TabbedPaneAdd0(),guiAliasParameters.getAliasPanelBorder0TabbedPaneAdd1()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        fields_.add(new CstFieldInfo(aliasPanelBorderNorth,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderSouth,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderWest,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderEast,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderCenter,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderBeforeFirst,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderBeforeLineBegins,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderAfterLineEnds,_content.getCharSeq().getAliasString()));
        fields_.add(new CstFieldInfo(aliasPanelBorderAfterLast,_content.getCharSeq().getAliasString()));
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasPanelBorder, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTabbedPane, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(_content.getCharSeq().getAliasString(),aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TabbedPaneAdd0(),guiAliasParameters.getAliasTabbedPane0TabbedPaneAdd1()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTabbedPaneSelIndex, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneSelIndex, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TabbedPaneSelIndex0()));
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneIndex, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TabbedPaneIndex0()));
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneRemove, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TreeNodeRemove0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneRemove, params_, aliasComponent, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane1TreeNodeRemove0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),aliasComponent);
        method_ = new StandardMethod(aliasTabbedPaneSet, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TreeNodeSetUserObject0(),guiAliasParameters.getAliasTabbedPane0TreeNodeSetUserObject1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTabbedPaneSetTitle, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TabbedPaneSetTitle0(),guiAliasParameters.getAliasTabbedPane0TabbedPaneSetTitle1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneGet, params_, aliasComponent, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TreeNodeGetUserObject0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTabbedPaneGetTitle, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTabbedPane0TabbedPaneGetTitle0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTabbedPaneNb, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRemoveAll, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasTabbedPane, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasScrollPane, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneHorizontalValue, params_,_content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasScrollPaneHorizontalValue, params_,_content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasScrollPane0ScrollPaneHorizontalValue0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneVerticalValue, params_,_content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasScrollPaneVerticalValue, params_,_content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasScrollPane0ScrollPaneVerticalValue0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneGetView, params_,aliasComponent, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasScrollPaneValidate, params_,_content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasScrollPaneSetView, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasScrollPane0ScrollPaneSetView0()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(aliasComponent);
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasScrollPane0ScrollPane0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasScrollPane, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasSplitPane, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetLeft, params_,aliasComponent, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasSplitPaneSetLeft, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetLeft0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetRight, params_,aliasComponent, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasSplitPaneSetRight, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetRight0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetDividerLocation, params_,_content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSplitPaneSetDividerLocation, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetDividerLocation0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneGetDividerSize, params_,_content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSplitPaneSetDividerSize, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetDividerSize0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneIsOneTouchExpandable, params_,_content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSplitPaneSetOneTouchExpandable, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetOneTouchExpandable0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneIsContinuousLayout, params_,_content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasSplitPaneSetContinuousLayout, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSplitPane0SplitPaneSetContinuousLayout0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSplitPaneValidate, params_,_content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),aliasComponent,aliasComponent);
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSplitPane0SplitPane0(),guiAliasParameters.getAliasSplitPane0SplitPane1(),guiAliasParameters.getAliasSplitPane0SplitPane2()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasSplitPane, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasInput, fields_, constructors_, methods_, aliasComponent, StdClassModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasInputIsEnabled, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasInputSetEnabled, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasInput0InputSetEnabled0()));
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasInput, std_);


        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasButton, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasAddListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasButton0AddListener0()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasButton0Button0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasButton, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasProgBar, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasProgBarMin, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasProgBar0ProgBarMin0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarMin, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasProgBarValue, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasProgBar0TreeNodeGetUserObject0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarValue, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasProgBarMax, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasProgBar0ProgBarMax0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarMax, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasProgBarOr, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasProgBar0ProgBarOr0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasProgBarOr, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasProgBar, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTextLabel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasSetLabelText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextLabel0SetLabelText0()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextLabel0TextLabel0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasTextLabel, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasImageLabel, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasImage);
        method_ = new StandardMethod(aliasSetLabelImage, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImageLabel0SetLabelImage0()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(aliasImage);
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasImageLabel0ImageLabel0()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasImageLabel, std_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasFont, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontGetName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontGetSize, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontIsBold, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFontIsItalic, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFontStringWidth, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasFont0FontStringWidth0()));
        methods_.add( method_);
        params_ = new StringList(aliasImage,_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasFontStringWidth, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(guiAliasParameters.getAliasFont1FontStringWidth0(),guiAliasParameters.getAliasFont1FontStringWidth1()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasFont0Font0()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasFont1Font0(),guiAliasParameters.getAliasFont1Font1(),guiAliasParameters.getAliasFont1Font2(),guiAliasParameters.getAliasFont1Font3()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasFont, stdcl_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasColor, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorAlpha, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorBlue, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorRed, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorGreen, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasColorIsTransparent, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasColor0Color0()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasColor1Color0(),guiAliasParameters.getAliasColor1Color1()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasColor2Color0(),guiAliasParameters.getAliasColor2Color1(),guiAliasParameters.getAliasColor2Color2()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasColor3Color0(),guiAliasParameters.getAliasColor3Color1(),guiAliasParameters.getAliasColor3Color2(),guiAliasParameters.getAliasColor3Color3()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasColor, stdcl_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasImage, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageIsWithAlpha, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetHeight, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetWidth, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageGet, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0TreeNodeGetUserObject0(),guiAliasParameters.getAliasImage0TreeNodeGetUserObject1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(), _content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageSet, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0TreeNodeSetUserObject0(),guiAliasParameters.getAliasImage0TreeNodeSetUserObject1(),guiAliasParameters.getAliasImage0TreeNodeSetUserObject2()));
        methods_.add( method_);
        params_ = new StringList(aliasImage,aliasImage);
        method_ = new StandardMethod(aliasImageEq, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC, new StringList(guiAliasParameters.getAliasImage0ImageEq0(),guiAliasParameters.getAliasImage0ImageEq1()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetColor, params_, aliasColor, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasColor);
        method_ = new StandardMethod(aliasImageSetColor, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageSetColor0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageGetFont, params_, aliasFont, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasFont);
        method_ = new StandardMethod(aliasImageSetFont, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0SetFont0()));
        methods_.add( method_);

        params_ = new StringList(aliasImage,_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDraw, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageDraw0(),guiAliasParameters.getAliasImage0ImageDraw1(),guiAliasParameters.getAliasImage0ImageDraw2()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDraw, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage1ImageDraw0(),guiAliasParameters.getAliasImage1ImageDraw1(),guiAliasParameters.getAliasImage1ImageDraw2()));
        methods_.add( method_);

        String arrInt_ = StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimInteger());
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawLine, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageDrawLine0(),guiAliasParameters.getAliasImage0ImageDrawLine1(),guiAliasParameters.getAliasImage0ImageDrawLine2(),guiAliasParameters.getAliasImage0ImageDrawLine3()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawRect, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageDrawRect0(),guiAliasParameters.getAliasImage0ImageDrawRect1(),guiAliasParameters.getAliasImage0ImageDrawRect2(),guiAliasParameters.getAliasImage0ImageDrawRect3()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageDrawOval, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageDrawOval0(),guiAliasParameters.getAliasImage0ImageDrawOval1(),guiAliasParameters.getAliasImage0ImageDrawOval2(),guiAliasParameters.getAliasImage0ImageDrawOval3()));
        methods_.add( method_);
        params_ = new StringList(arrInt_,arrInt_);
        method_ = new StandardMethod(aliasImageDrawPolygon, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageDrawPolygon0(),guiAliasParameters.getAliasImage0ImageDrawPolygon1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageFillRect, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageFillRect0(),guiAliasParameters.getAliasImage0ImageFillRect1(),guiAliasParameters.getAliasImage0ImageFillRect2(),guiAliasParameters.getAliasImage0ImageFillRect3()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasImageFillOval, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageFillOval0(),guiAliasParameters.getAliasImage0ImageFillOval1(),guiAliasParameters.getAliasImage0ImageFillOval2(),guiAliasParameters.getAliasImage0ImageFillOval3()));
        methods_.add( method_);
        params_ = new StringList(arrInt_,arrInt_);
        method_ = new StandardMethod(aliasImageFillPolygon, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasImage0ImageFillPolygon0(),guiAliasParameters.getAliasImage0ImageFillPolygon1()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasImageDispose, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);

        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasImage0Image0(),guiAliasParameters.getAliasImage0Image1(),guiAliasParameters.getAliasImage0Image2()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasImage, stdcl_);
        buildInputs(_content);
        buildMenus(_content);
    }
    private void buildEvents(LgNamesContent _content) {
        CustList<StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        StandardConstructor ctor_;
        CustList<CstFieldInfo> fields_;
        StandardClass stdcl_;
        StringList params_;
        StandardMethod method_;
        StandardClass std_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasActionEvent, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasActionEvent, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasWindowEvent, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasWindowEvent, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasMouseEvent, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsAlt, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsCtrl, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsShift, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsLeft, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsMiddle, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventIsRight, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventGetClicks, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventGetFirst, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMouseEventGetSecond, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),
                _content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),
                _content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),
                _content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasMouseEvent0MouseEvent0(),guiAliasParameters.getAliasMouseEvent0MouseEvent1(),guiAliasParameters.getAliasMouseEvent0MouseEvent2(),guiAliasParameters.getAliasMouseEvent0MouseEvent3(),guiAliasParameters.getAliasMouseEvent0MouseEvent4(),guiAliasParameters.getAliasMouseEvent0MouseEvent5(),guiAliasParameters.getAliasMouseEvent0MouseEvent6(),guiAliasParameters.getAliasMouseEvent0MouseEvent7(),guiAliasParameters.getAliasMouseEvent0MouseEvent8()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasMouseEvent, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasWheelEvent, fields_, constructors_, methods_, aliasMouseEvent, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasWheelRotatedClicks, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),
                _content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),
                _content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),
                _content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasWheelEvent0WheelEvent0(),guiAliasParameters.getAliasWheelEvent0WheelEvent1(),guiAliasParameters.getAliasWheelEvent0WheelEvent2(),guiAliasParameters.getAliasWheelEvent0WheelEvent3(),guiAliasParameters.getAliasWheelEvent0WheelEvent4(),guiAliasParameters.getAliasWheelEvent0WheelEvent5(),guiAliasParameters.getAliasWheelEvent0WheelEvent6(),guiAliasParameters.getAliasWheelEvent0WheelEvent7(),guiAliasParameters.getAliasWheelEvent0WheelEvent8(),guiAliasParameters.getAliasWheelEvent0WheelEvent9()));
        constructors_.add(ctor_);
        std_ = stdcl_;
        _content.getStandards().addEntry(aliasWheelEvent, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasKeyEvent, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventIsAlt, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventIsCtrl, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventIsShift, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventChar, params_, _content.getPrimTypes().getAliasPrimChar(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasKeyEventCode, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(
                _content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),_content.getPrimTypes().getAliasPrimBoolean(),
                _content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasKeyEvent0KeyEvent0(),guiAliasParameters.getAliasKeyEvent0KeyEvent1(),guiAliasParameters.getAliasKeyEvent0KeyEvent2(),guiAliasParameters.getAliasKeyEvent0KeyEvent3(),guiAliasParameters.getAliasKeyEvent0KeyEvent4()));
        constructors_.add(ctor_);

        std_ = stdcl_;
        _content.getStandards().addEntry(aliasKeyEvent, std_);
    }
    private void buildMenus(LgNamesContent _content) {
        CustList<StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        CustList<CstFieldInfo> fields_;
        StandardClass stdcl_;
        StringList params_;
        StandardMethod method_;
        StandardConstructor ctor_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasMenuBar, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasMenu);
        method_ = new StandardMethod(aliasMenuBarAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenuBar0TabbedPaneAdd0()));
        methods_.add( method_);
        params_ = new StringList(aliasMenu);
        method_ = new StandardMethod(aliasMenuBarRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenuBar0TreeNodeRemove0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasMenuBarGet, params_, aliasMenu, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenuBar0TreeNodeGetUserObject0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuBarNb, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        _content.getStandards().addEntry(aliasMenuBar, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAbsMenu, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasAbsMenuGetParent, params_, aliasMenu, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasAbsMenuSetText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasAbsMenu0AbsMenuSetText0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasAbsMenuGetText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasAbsMenuSetEnabled, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasAbsMenu0InputSetEnabled0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasAbsMenuIsEnabled, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasAbsMenuSetDeepEnabled, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasAbsMenu0AbsMenuSetDeepEnabled0()));
        methods_.add( method_);
        _content.getStandards().addEntry(aliasAbsMenu, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasMenu, fields_, constructors_, methods_, aliasAbsMenu, MethodModifier.FINAL);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasMenuAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenu0TabbedPaneAdd0()));
        methods_.add( method_);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasMenuRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenu0TreeNodeRemove0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasMenuGet, params_, aliasAbsMenu, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenu0TreeNodeGetUserObject0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuNb, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuAddSeparator, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasMenu0Menu0()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasMenu, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAbsMenuItem, fields_, constructors_, methods_, aliasAbsMenu, StdClassModifier.ABSTRACT);
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasAbsMenuItemAddAction, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasAbsMenuItem0TabbedPaneAdd0()));
        methods_.add( method_);
        _content.getStandards().addEntry(aliasAbsMenuItem, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasMenuItem, fields_, constructors_, methods_, aliasAbsMenuItem, MethodModifier.FINAL);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasMenuItem0MenuItem0()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasMenuItem, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasMenuItemCheck, fields_, constructors_, methods_, aliasAbsMenuItem, MethodModifier.FINAL);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasMenuItemCheckSetSelected, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasMenuItemCheck0RadioSetSelected0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMenuItemCheckIsSelected, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasMenuItemCheck0MenuItemCheck0()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasMenuItemCheck, stdcl_);

    }
    private void buildInputs(LgNamesContent _content) {
        CustList<StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        CustList<CstFieldInfo> fields_;
        StandardClass stdcl_;
        StringList params_;
        StandardMethod method_;
        StandardConstructor ctor_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasRender, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        String typeHeight_ = StringUtil.concat(_content.getReflect().getAliasFct(),"<",aliasGrList,",",_content.getCoreNames().getAliasObject(),",",_content.getPrimTypes().getAliasPrimInteger(),",",_content.getPrimTypes().getAliasPrimBoolean(),",",_content.getPrimTypes().getAliasPrimInteger(),","+_content.getPrimTypes().getAliasPrimInteger()+">");
        params_ = new StringList();
        method_ = new StandardMethod(aliasRenderGetHeight, params_, typeHeight_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(typeHeight_);
        method_ = new StandardMethod(aliasRenderSetHeight, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRender0RenderSetHeight0()));
        methods_.add( method_);
        String typeWidth_ = StringUtil.concat(_content.getReflect().getAliasFct(),"<",aliasGrList,",",_content.getCoreNames().getAliasObject(),",",_content.getPrimTypes().getAliasPrimInteger(),",",_content.getPrimTypes().getAliasPrimBoolean(),",",_content.getPrimTypes().getAliasPrimInteger(),","+_content.getPrimTypes().getAliasPrimInteger()+">");
        params_ = new StringList();
        method_ = new StandardMethod(aliasRenderGetWidth, params_, typeWidth_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(typeWidth_);
        method_ = new StandardMethod(aliasRenderSetWidth, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRender0RenderSetWidth0()));
        methods_.add( method_);
        String typePaint_ = StringUtil.concat(_content.getReflect().getAliasFct(),"<",aliasGrList,",",_content.getCoreNames().getAliasObject(),",",_content.getPrimTypes().getAliasPrimInteger(),",",_content.getPrimTypes().getAliasPrimBoolean(),",",aliasImage,",?>");
        params_ = new StringList();
        method_ = new StandardMethod(aliasRenderGetPaint, params_, typePaint_, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(typePaint_);
        method_ = new StandardMethod(aliasRenderSetPaint, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRender0ComponentSetPaint0()));
        methods_.add( method_);
        _content.getStandards().addEntry(aliasRender, stdcl_);


        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasGrList, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetRender, params_, aliasRender, false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasRender);
        method_ = new StandardMethod(aliasGrListSetRender, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0GrListSetRender0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetSelections, params_, StringExpUtil.getPrettyArrayType(aliasListSelection), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasGrListAddSelection, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0GrListAddSelection0()));
        methods_.add( method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasGrListRemoveSelection, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0GrListRemoveSelection0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetVisibleRowCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListSetVisibleRowCount, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0GrListSetVisibleRowCount0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasGrListAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0TabbedPaneAdd0(),guiAliasParameters.getAliasGrList0TabbedPaneAdd1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),aliasImageLabel,_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasGrListAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList1TabbedPaneAdd0(),guiAliasParameters.getAliasGrList1TabbedPaneAdd1(),guiAliasParameters.getAliasGrList1TabbedPaneAdd2()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasGrListSet, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList1TreeNodeSetUserObject0(),guiAliasParameters.getAliasGrList1TreeNodeSetUserObject1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),aliasImageLabel,_content.getCoreNames().getAliasObject());
        method_ = new StandardMethod(aliasGrListSet, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0TreeNodeSetUserObject0(),guiAliasParameters.getAliasGrList0TreeNodeSetUserObject1(),guiAliasParameters.getAliasGrList0TreeNodeSetUserObject2()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetListView, params_, StringExpUtil.getPrettyArrayType(_content.getCoreNames().getAliasObject()), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListGetSelectedIndexes, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimInteger()), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListSetSelectedIndexes, params_, _content.getCoreNames().getAliasVoid(), true, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0GrListSetSelectedIndexes0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListClearSelection, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListUpdateGraphics, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasGrListRemove, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasGrList0RemoveCompo0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGrListClear, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasGrList0GrList0()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasGrList, stdcl_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasCombo, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetListeners, params_, StringExpUtil.getPrettyArrayType(aliasListSelection), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasComboAddListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCombo0ComboAddListener0()));
        methods_.add( method_);
        params_ = new StringList(aliasListSelection);
        method_ = new StandardMethod(aliasComboRemoveListener, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCombo0ComboRemoveListener0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasComboAddItem, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCombo0TabbedPaneAdd0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasComboSelectItem, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCombo0ComboSelectItem0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetItemCount, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedIndex, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedIndexes, params_, StringExpUtil.getPrettyArrayType(_content.getPrimTypes().getAliasPrimInteger()), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboGetSelectedItem, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasComboRemoveItem, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCombo0ComboRemoveItem0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasComboRemoveAllItems, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,true,new StringList(guiAliasParameters.getAliasCombo0Combo0()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,true,new StringList(guiAliasParameters.getAliasCombo1Combo0(),guiAliasParameters.getAliasCombo1Combo1()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasCombo, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasButtonGroup, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList(aliasRadio);
        method_ = new StandardMethod(aliasButtonGroupAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasButtonGroup0TabbedPaneAdd0()));
        methods_.add( method_);
        _content.getStandards().addEntry(aliasButtonGroup, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasPopupMenu, fields_, constructors_, methods_, aliasComponent, MethodModifier.FINAL);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasPopupMenuAdd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0TabbedPaneAdd0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasPopupMenuGetComp, params_, aliasComponent, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuGetComp0()));
        methods_.add( method_);
        params_ = new StringList(aliasComponent);
        method_ = new StandardMethod(aliasPopupMenuRemoveComp, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuRemoveComp0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPopupMenuNbComp, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasPopupMenuAddMenu, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuAddMenu0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasPopupMenuGetMenu, params_, aliasAbsMenu, false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuGetMenu0()));
        methods_.add( method_);
        params_ = new StringList(aliasAbsMenu);
        method_ = new StandardMethod(aliasPopupMenuRemoveMenu, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuRemoveMenu0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasPopupMenuNbMenu, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(aliasComponent,_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasPopupMenuShow, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasPopupMenu0PopupMenuShow0(),guiAliasParameters.getAliasPopupMenu0PopupMenuShow1(),guiAliasParameters.getAliasPopupMenu0PopupMenuShow2()));
        methods_.add( method_);
        _content.getStandards().addEntry(aliasPopupMenu, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasRadio, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRadioIsSelected, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasRadioSetSelected, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRadio0RadioSetSelected0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRadioGetText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasRadioSetText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRadio0SetLabelText0()));
        methods_.add( method_);
        params_ = new StringList(aliasChangeListener);
        method_ = new StandardMethod(aliasAddChange, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasRadio0AddChange0()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasRadio0Radio0()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasRadio1Radio0(),guiAliasParameters.getAliasRadio1Radio1()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasRadio, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasCheckBox, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCheckBoxIsSelected, params_, _content.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimBoolean());
        method_ = new StandardMethod(aliasCheckBoxSetSelected, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCheckBox0RadioSetSelected0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCheckBoxGetText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasCheckBoxSetText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasCheckBox0SetLabelText0()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasCheckBox0CheckBox0()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimBoolean());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasCheckBox1CheckBox0(),guiAliasParameters.getAliasCheckBox1CheckBox1()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasCheckBox, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTextField, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextFieldGetText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTextFieldSetText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextField0SetLabelText0()));
        methods_.add( method_);
        params_ = new StringList(aliasActionListener);
        method_ = new StandardMethod(aliasTextFieldAddAction, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextField0TextFieldAddAction0()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextField0TextField0()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextField1TextField0()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextField2TextField0(),guiAliasParameters.getAliasTextField2TextField1()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasTextField, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasTextArea, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTextAreaSetText, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0SetLabelText0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTextAreaAppend, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TabbedPaneAdd0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaInsert, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TreeNodeInsert0(),guiAliasParameters.getAliasTextArea0TreeNodeInsert1()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetSelectedText, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetSelectionStart, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TextAreaSetSelectionStart0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetSelectionEnd, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TextAreaSetSelectionEnd0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaGetTabSize, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSetTabSize, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TextAreaSetTabSize0()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaReplaceRange, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TextAreaReplaceRange0(),guiAliasParameters.getAliasTextArea0TextAreaReplaceRange1(),guiAliasParameters.getAliasTextArea0TextAreaReplaceRange2()));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasTextAreaReplaceSelection, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TextAreaReplaceSelection0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasTextAreaSelect, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasTextArea0TreeGetSelected0(),guiAliasParameters.getAliasTextArea0TreeGetSelected1()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTextAreaSelectAll, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextArea0TextArea0()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextArea1TextArea0(),guiAliasParameters.getAliasTextArea1TextArea1()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getCharSeq().getAliasString(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasTextArea2TextArea0(),guiAliasParameters.getAliasTextArea2TextArea1(),guiAliasParameters.getAliasTextArea2TextArea2()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasTextArea, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasSpinner, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetMax, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetMax, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0SpinnerSetMax0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetMin, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetMin, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0SpinnerSetMin0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetValue, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetValue, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0TreeNodeSetUserObject0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSpinnerGetStep, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetStep, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0SpinnerSetStep0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetRange, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0SpinnerSetRange0(),guiAliasParameters.getAliasSpinner0SpinnerSetRange1()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSpinnerSetRangeValue, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0SpinnerSetRangeValue0(),guiAliasParameters.getAliasSpinner0SpinnerSetRangeValue1(),guiAliasParameters.getAliasSpinner0SpinnerSetRangeValue2()));
        methods_.add( method_);
        params_ = new StringList(aliasChangeListener);
        method_ = new StandardMethod(aliasAddChange, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSpinner0AddChange0()));
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSpinner0Spinner0(),guiAliasParameters.getAliasSpinner0Spinner1(),guiAliasParameters.getAliasSpinner0Spinner2(),guiAliasParameters.getAliasSpinner0Spinner3()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasSpinner, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasSlider, fields_, constructors_, methods_, aliasInput, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetMax, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetMax, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSlider0SpinnerSetMax0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetMin, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetMin, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSlider0SpinnerSetMin0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetValue, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetValue, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSlider0TreeNodeSetUserObject0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSliderGetOrientation, params_, _content.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL);
        methods_.add( method_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasSliderSetOrientation, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSlider0SliderSetOrientation0()));
        methods_.add( method_);
        params_ = new StringList(aliasChangeListener);
        method_ = new StandardMethod(aliasAddChange, params_, _content.getCoreNames().getAliasVoid(), false, MethodModifier.FINAL,new StringList(guiAliasParameters.getAliasSlider0AddChange0()));
        methods_.add( method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSlider0Slider0()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSlider1Slider0(),guiAliasParameters.getAliasSlider1Slider1()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSlider2Slider0(),guiAliasParameters.getAliasSlider2Slider1(),guiAliasParameters.getAliasSlider2Slider2()));
        constructors_.add(ctor_);
        params_ = new StringList(_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger(),_content.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_,false,new StringList(guiAliasParameters.getAliasSlider3Slider0(),guiAliasParameters.getAliasSlider3Slider1(),guiAliasParameters.getAliasSlider3Slider2(),guiAliasParameters.getAliasSlider3Slider3()));
        constructors_.add(ctor_);
        _content.getStandards().addEntry(aliasSlider, stdcl_);
    }

    public Struct getInnerSimpleResult(ClassField _classField,LgNamesContent _content) {
        if (StringUtil.quickEq(_classField.getClassName(),aliasConfirm)) {
            return getResultConfirm(_classField);
        }
        if (StringUtil.quickEq(_classField.getClassName(),aliasPanelBorder)) {
            return getResultPanelBorder(_classField);
        }
        return ValidatorStandard.getSimpleResultBase(_classField, _content.getNbAlias());
    }

    private Struct getResultPanelBorder(ClassField _classField) {
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderNorth)) {
            return new StringStruct(PanelBorderStruct.NORTH);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderSouth)) {
            return new StringStruct(PanelBorderStruct.SOUTH);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderWest)) {
            return new StringStruct(PanelBorderStruct.WEST);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderEast)) {
            return new StringStruct(PanelBorderStruct.EAST);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderCenter)) {
            return new StringStruct(PanelBorderStruct.CENTER);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderBeforeFirst)) {
            return new StringStruct(PanelBorderStruct.BEFORE_FIRST_LINE);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderBeforeLineBegins)) {
            return new StringStruct(PanelBorderStruct.BEFORE_LINE_BEGINS);
        }
        if (StringUtil.quickEq(fieldName_,aliasPanelBorderAfterLineEnds)) {
            return new StringStruct(PanelBorderStruct.AFTER_LINE_ENDS);
        }
        return new StringStruct(PanelBorderStruct.AFTER_LAST_LINE);
    }

    private Struct getResultConfirm(ClassField _classField) {
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,aliasConfirmFieldOk)) {
            return new IntStruct(OtherConfirmDialog.OK_OPTION);
        }
        if (StringUtil.quickEq(fieldName_,aliasConfirmFieldYes)) {
            return new IntStruct(OtherConfirmDialog.YES_OPTION);
        }
        if (StringUtil.quickEq(fieldName_,aliasConfirmFieldNo)) {
            return new IntStruct(OtherConfirmDialog.NO_OPTION);
        }
        return new IntStruct(OtherConfirmDialog.CANCEL_OPTION);
    }
    public Argument defaultInstance(CustAliases _custAliases, ContextEl _cont, String _id, StackCall _stackCall) {
        Argument arg_ = _custAliases.defaultInstance(_cont, _id, _stackCall);
        if (!arg_.isNull() || _cont.callsOrException(_stackCall)) {
            return arg_;
        }
        if (StringUtil.quickEq(_id,aliasFont)) {
            return new Argument(new FontStruct());
        }
        if (StringUtil.quickEq(_id,aliasWindowSet)) {
            WindowSetStruct set_ = new WindowSetStruct(true);
            return new Argument(set_);
        }
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            processFailInit(_cont, _custAliases, _stackCall);
            return new Argument();
        }
        if (StringUtil.quickEq(_id,aliasFrame)) {
            FrameStruct fr_ = new FrameStruct(new OtherFrame());
            ((GuiContextEl)_cont).getGuiInit().getWindows().add(fr_,false);
            return new Argument(fr_);
        }
        if (StringUtil.quickEq(_id,aliasDialog)) {
            DialogStruct di_ = new DialogStruct(new OtherDialog());
            ((GuiContextEl)_cont).getGuiInit().getWindows().add(di_,false);
            return new Argument(di_);
        }
        if (StringUtil.quickEq(_id,aliasPanel)) {
            return new Argument(PanelStruct.newFlow(aliasPanel));
        }
        if (StringUtil.quickEq(_id,aliasTabbedPane)) {
            return new Argument(new TabbedPaneStruct(aliasTabbedPane));
        }
        if (StringUtil.quickEq(_id,aliasScrollPane)) {
            return new Argument(ScrollPaneStruct.newScroll(aliasScrollPane));
        }
        if (StringUtil.quickEq(_id,aliasPanelBorder)) {
            return new Argument(PanelBorderStruct.newBorder(aliasPanelBorder));
        }
        if (StringUtil.quickEq(_id,aliasButton)) {
            return new Argument(new PlainButtonStruct(aliasButton));
        }
        if (StringUtil.quickEq(_id,aliasTextLabel)) {
            return new Argument(new TextLabelStruct(aliasTextLabel));
        }
        if (StringUtil.quickEq(_id,aliasImageLabel)) {
            WindowFull window_ = ((LgNamesGui) _cont.getStandards()).getGuiExecutingBlocks().getWindow();
            return new Argument(new PreparedLabelStruct(window_.getImageFactory(),aliasImageLabel));
        }
        if (StringUtil.quickEq(_id,aliasRender)) {
            return new Argument(new RenderStruct());
        }
        if (StringUtil.quickEq(_id,aliasGrList)) {
            return new Argument(new GraphicListStruct((GuiContextEl)_cont,aliasGrList,true));
        }
        if (StringUtil.quickEq(_id,aliasCombo)) {
            return new Argument(new GraphicComboStruct(aliasCombo));
        }
        return arg_;
    }
    public ResultErrorStd getOtherResult(CustAliases _custAliases, ContextEl _cont,
                                         ConstructorId _method, GuiExecutingBlocks _guiEx, StackCall _stackCall, Struct... _args) {
        String name_ = _method.getName();
        ResultErrorStd r_ = new ResultErrorStd();
        if (StringUtil.quickEq(name_,aliasActionEvent)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new ActionEventStruct(aliasActionEvent));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasWindowSet)) {
            WindowSetStruct set_ = new WindowSetStruct(true);
            r_.setResult(set_);
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasWindowEvent)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new WindowEventStruct(aliasWindowEvent));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasMouseEvent)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            MouseEventStruct res_ = new MouseEventStruct(aliasMouseEvent);
            res_.setFirst(_args[0]);
            res_.setSecond(_args[1]);
            res_.setAlt(_args[2]);
            res_.setCtrl(_args[3]);
            res_.setShift(_args[4]);
            res_.setLeft(_args[5]);
            res_.setMiddle(_args[6]);
            res_.setRight(_args[7]);
            res_.setClicks(_args[8]);
            r_.setResult(res_);
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasWheelEvent)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            MouseWheelEventStruct res_ = new MouseWheelEventStruct(aliasWheelEvent);
            res_.setFirst(_args[0]);
            res_.setSecond(_args[1]);
            res_.setAlt(_args[2]);
            res_.setCtrl(_args[3]);
            res_.setShift(_args[4]);
            res_.setLeft(_args[5]);
            res_.setMiddle(_args[6]);
            res_.setRight(_args[7]);
            res_.setClicks(_args[8]);
            res_.setRotated(_args[9]);
            r_.setResult(res_);
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasKeyEvent)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            KeyEventStruct res_ = new KeyEventStruct(aliasKeyEvent);
            res_.setAlt(_args[0]);
            res_.setCtrl(_args[1]);
            res_.setShift(_args[2]);
            res_.setKeyChar(_args[3]);
            res_.setKeyCode(_args[4]);
            r_.setResult(res_);
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasFrame)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            FrameStruct fr_ = new FrameStruct(new OtherFrame());
            ((GuiContextEl)_cont).getGuiInit().getWindows().add(fr_,false);
            r_.setResult(fr_);
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasDialog)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            DialogStruct di_ = new DialogStruct(new OtherDialog());
            ((GuiContextEl)_cont).getGuiInit().getWindows().add(di_,false);
            r_.setResult(di_);
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasPanel)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(PanelStruct.newFlow(aliasPanel));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasPanelBorder)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(PanelBorderStruct.newBorder(aliasPanelBorder));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasTabbedPane)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new TabbedPaneStruct(aliasTabbedPane));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasScrollPane)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 1) {
                r_.setResult(ScrollPaneStruct.newScroll(_args[0], aliasScrollPane));
            } else {
                r_.setResult(ScrollPaneStruct.newScroll(aliasScrollPane));
            }
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasSplitPane)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (!(_args[1] instanceof CustComponentStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (!(_args[2] instanceof CustComponentStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            CustComponentStruct first_ = (CustComponentStruct) _args[1];
            CustComponentStruct second_ = (CustComponentStruct) _args[2];
            if (first_.getParentComponent() != NullStruct.NULL_VALUE) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (second_.getParentComponent() != NullStruct.NULL_VALUE) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new SplitPaneStruct(aliasSplitPane,_args[0],_args[1],_args[2]));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasProgBar)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new ProgressBarStruct(aliasProgBar));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasButton)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 1) {
                r_.setResult(new PlainButtonStruct(_args[0],aliasButton));
            } else {
                r_.setResult(new PlainButtonStruct(aliasButton));
            }
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasTextLabel)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 1) {
                r_.setResult(new TextLabelStruct(_args[0],aliasTextLabel));
            } else {
                r_.setResult(new TextLabelStruct(aliasTextLabel));
            }
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasImageLabel)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            WindowFull window_ = ((LgNamesGui) _cont.getStandards()).getGuiExecutingBlocks().getWindow();
            if (_method.getParametersTypesLength() == 1) {
                r_.setResult(new PreparedLabelStruct(window_.getImageFactory(),_args[0],aliasImageLabel));
            } else {
                r_.setResult(new PreparedLabelStruct(window_.getImageFactory(),aliasImageLabel));
            }
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasFont)) {
            if (_method.getParametersTypesLength() == 0) {
                r_.setResult(new FontStruct());
                return r_;
            }
            if (_method.getParametersTypesLength() == 1) {
                r_.setResult(new FontStruct(((NumberStruct)_args[0]).intStruct()));
                return r_;
            }
            r_.setResult(new FontStruct(_args[0],BooleanStruct.isTrue(_args[1]),BooleanStruct.isTrue(_args[2]),((NumberStruct)_args[3]).intStruct()));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasColor)) {
            if (_method.getParametersTypesLength() == 1) {
                r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct()));
                return r_;
            }
            if (_method.getParametersTypesLength() == 2) {
                r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct(),BooleanStruct.isTrue(_args[1])));
                return r_;
            }
            if (_method.getParametersTypesLength() == 3) {
                r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct()));
                return r_;
            }
            r_.setResult(new ColorStruct(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct()));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasImage)) {
            r_.setResult(new ImageStruct(_guiEx.getWindow().getImageFactory(),((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),BooleanStruct.isTrue(_args[2])));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasDimension)) {
            if (_method.getParametersTypesLength() == 1) {
                if (!(_args[0] instanceof DimensionStruct)) {
                    r_.setResult(new DimensionStruct(0,0));
                    return r_;
                }
                r_.setResult(new DimensionStruct((DimensionStruct)_args[0]));
                return r_;
            }
            r_.setResult(new DimensionStruct(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct()));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasTreeNode)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 0) {
                r_.setResult(new TreeNodeStruct(_guiEx.getWindow().getCompoFactory().newMutableTreeNode("")));
                return r_;
            }
            r_.setResult(new TreeNodeStruct(_guiEx.getWindow().getCompoFactory().newMutableTreeNode(NumParsers.getString(_args[0]).getInstance())));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasTree)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (!(_args[0] instanceof TreeNodeStruct)) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
                return r_;
            }
            r_.setResult(new TreeStruct(aliasTree, _guiEx.getWindow().getCompoFactory().newTreeGui(((TreeNodeStruct) _args[0]).getTreeNode())));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasTableGui)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new TableStruct(aliasTableGui, _args[0]));
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasRender)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new RenderStruct());
            return r_;
        }
        if (StringUtil.quickEq(name_,aliasGrList)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new GraphicListStruct((GuiContextEl)_cont,aliasGrList,BooleanStruct.isTrue(_args[0])));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasCombo)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            AbstractGraphicComboBoxGenerator geneComboBox_ = _guiEx.getWindow().getFrames().getGeneComboBox();
            if (_method.getParametersTypesLength() == 0) {
                r_.setResult(new GraphicComboStruct(aliasCombo, geneComboBox_.createCombo(_guiEx.getWindow().getImageFactory(),new StringList(),-1)));
                return r_;
            }
            if (_method.getParametersTypesLength() == 1) {
                r_.setResult(new GraphicComboStruct(aliasCombo, geneComboBox_.createCombo(_guiEx.getWindow().getImageFactory(),newList(_args[0]), 0)));
                return r_;
            }
            r_.setResult(new GraphicComboStruct(aliasCombo, geneComboBox_.createCombo(_guiEx.getWindow().getImageFactory(),newList(_args[1]), ((NumberStruct)_args[0]).intStruct())));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasButtonGroup)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new CustButtonGroupStruct());
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasRadio)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 0) {
                r_.setResult(new RadioButtonStruct(aliasRadio));
                return r_;
            }
            if (_method.getParametersTypesLength() == 1) {
                r_.setResult(new RadioButtonStruct(aliasRadio,_args[0]));
                return r_;
            }
            r_.setResult(new RadioButtonStruct(aliasRadio,_args[0],_args[1]));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasCheckBox)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 0) {
                r_.setResult(new CheckBoxStruct(aliasCheckBox));
                return r_;
            }
            if (_method.getParametersTypesLength() == 1) {
                r_.setResult(new CheckBoxStruct(aliasCheckBox,_args[0]));
                return r_;
            }
            r_.setResult(new CheckBoxStruct(aliasCheckBox,_args[0],_args[1]));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasPopupMenu)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new PopupStruct(aliasPopupMenu));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasTextField)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 0) {
                r_.setResult(new TextFieldStruct(aliasTextField));
                return r_;
            }
            if (_method.getParametersTypesLength() == 1) {
                if (!(_args[0] instanceof NumberStruct)) {
                    r_.setResult(new TextFieldStruct(aliasTextField,_args[0]));
                    return r_;
                }
                r_.setResult(new TextFieldStruct(aliasTextField,(NumberStruct) _args[0]));
                return r_;
            }
            r_.setResult(new TextFieldStruct(aliasTextField,_args[0],_args[1]));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasTextArea)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 0) {
                r_.setResult(new TextAreaStruct(aliasTextArea));
                return r_;
            }
            if (_method.getParametersTypesLength() == 1) {
                r_.setResult(new TextAreaStruct(aliasTextArea,_args[0]));
                return r_;
            }
            if (_method.getParametersTypesLength() == 2) {
                r_.setResult(new TextAreaStruct(aliasTextArea,_args[0],_args[1]));
                return r_;
            }
            r_.setResult(new TextAreaStruct(aliasTextArea,_args[0],_args[1],_args[2]));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasSpinner)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new SpinnerStruct(aliasSpinner,_args[0],_args[1],_args[2],_args[3]));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasSlider)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 0) {
                r_.setResult(new SliderStruct(aliasSlider));
                return r_;
            }
            if (_method.getParametersTypesLength() == 1) {
                r_.setResult(new SliderStruct(aliasSlider,_args[0]));
                return r_;
            }
            if (_method.getParametersTypesLength() == 2) {
                r_.setResult(new SliderStruct(aliasSlider,_args[0],_args[1]));
                return r_;
            }
            if (_method.getParametersTypesLength() == 3) {
                r_.setResult(new SliderStruct(aliasSlider,_args[0],_args[1],_args[2]));
                return r_;
            }
            r_.setResult(new SliderStruct(aliasSlider,_args[0],_args[1],_args[2],_args[3]));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasMenuBar)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            r_.setResult(new MenuBarStruct());
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasMenu)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 0) {
                r_.setResult(new MenuStruct());
                return r_;
            }
            r_.setResult(new MenuStruct(_args[0]));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasMenuItem)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 0) {
                r_.setResult(new MenuItemStruct());
                return r_;
            }
            r_.setResult(new MenuItemStruct(_args[0]));
            return r_;
        }
        if (StringUtil.quickEq(name_, aliasMenuItemCheck)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                r_.setResult(NullStruct.NULL_VALUE);
                return r_;
            }
            if (_method.getParametersTypesLength() == 0) {
                r_.setResult(new MenuItemCheckStruct());
                return r_;
            }
            r_.setResult(new MenuItemCheckStruct(_args[0]));
            return r_;
        }
        return _custAliases.getOtherResult(_cont,_method, _stackCall,_args);
    }
    public ResultErrorStd getOtherResult(CustAliases _custAliases, ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, ExecutingBlocks _execBlocks, GuiExecutingBlocks _guiEx, StackCall _stackCall, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        if (StringUtil.quickEq(type_, aliasWindowType)) {
            WindowStruct inst_ = (WindowStruct) _instance;
            if (StringUtil.quickEq(name_, aliasSetMenuBar)) {
                inst_.setMenuBar(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGetMenuBar)) {
                res_.setResult(inst_.getMenuBar());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPack)) {
                inst_.pack();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasWindowTypeRelative)) {
                inst_.setLocationRelativeTo(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasAddWindowListener)) {
                _guiEx.addWindowListener(inst_, _args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRemoveWindowListener)) {
                _guiEx.removeWindowListener(inst_, _args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGetWindowListeners)) {
                CustList<Struct> user_ = new CustList<Struct>();
                for (WindowListener w: inst_.getWindowListeners()) {
                    if (w instanceof Struct) {
                        user_.add((Struct)w);
                    }
                }
                int len_ = user_.size();
                ArrayStruct arr_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(aliasWindowListener));
                for (int i = 0; i< len_; i++) {
                    arr_.set(i, user_.get(i));
                }
                res_.setResult(arr_);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasIsVisible)) {
                res_.setResult(BooleanStruct.of(inst_.isVisible()));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSetVisible)) {
                inst_.setVisible(BooleanStruct.isTrue(_args[0]));
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasDispose)) {
                if (inst_ instanceof FrameStruct && ((FrameStruct)inst_).getCommonFrame().isMainFrame()) {
                    ((GuiContextEl)_cont).disposeAll(_guiEx, _stackCall);
                } else {
                    ((GuiContextEl)_cont).getGuiInit().getWindows().remove(inst_,false);
                    inst_.dispose();
                }
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_args[0] instanceof PanelStruct) {
                inst_.setContentPane(((PanelStruct)_args[0]).getPanel());
            }
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasWindowSet)) {
            if (StringUtil.quickEq(name_,aliasWindowSetAdd)) {
                if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _stackCall.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                WindowSetStruct ins_ = (WindowSetStruct)_instance;
                ins_.add(_args[0],true);
                if (!(_args[0] instanceof WindowStruct)) {
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
                } else {
                    res_.setResult(NullStruct.NULL_VALUE);
                }
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasWindowSetAll)) {
                if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                    processFailInit(_cont, _custAliases, _stackCall);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(((GuiContextEl)_cont).getGuiInit().getWindows());
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasWindowSetRemove)) {
                if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _stackCall.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                WindowSetStruct ins_ = (WindowSetStruct)_instance;
                ins_.remove(_args[0],true);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_,aliasWindowSetContains)) {
                if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _stackCall.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                WindowSetStruct ins_ = (WindowSetStruct)_instance;
                res_.setResult(ins_.contains(_args[0]));
                return res_;
            }
            WindowSetStruct ins_ = (WindowSetStruct)_instance;
            res_.setResult(ins_.toSnapshotArray(_cont, _stackCall));
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasConfirm)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasConfirmField)) {
                if (_method.getConstraints().getParametersTypesLength() == 7) {
                    res_.setResult(_guiEx.showTextField(_args[0], _args[1], _args[2], _args[3], _args[4], _args[5], _args[6]));
                    return res_;
                }
                res_.setResult(_guiEx.showTextField(_args[0], _args[1], _args[2], _args[3], _args[4], _args[5]));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasConfirmFull)) {
                if (_method.getConstraints().getParametersTypesLength() == 7) {
                    res_.setResult(_guiEx.getAnswer(_args[0], _args[1], _args[2], _args[3], _args[4], _args[5], _args[6]));
                    return res_;
                }
                res_.setResult(_guiEx.getAnswer(_args[0], _args[1], _args[2], _args[3], _args[4], _args[5]));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasConfirmYesNo)) {
                if (_method.getConstraints().getParametersTypesLength() == 6) {
                    res_.setResult(_guiEx.getAnswerYesNo(_args[0], _args[1], _args[2], _args[3], _args[4], _args[5]));
                    return res_;
                }
                res_.setResult(_guiEx.getAnswerYesNo(_args[0], _args[1], _args[2], _args[3], _args[4]));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasConfirmOk)) {
                if (_method.getConstraints().getParametersTypesLength() == 5) {
                    res_.setResult(_guiEx.getAnswerOk(_args[0], _args[1], _args[2], _args[3], _args[4]));
                    return res_;
                }
                res_.setResult(_guiEx.getAnswerOk(_args[0], _args[1], _args[2], _args[3]));
                return res_;
            }
            if (_method.getConstraints().getParametersTypesLength() == 5) {
                _guiEx.showMessage(_args[0], _args[1], _args[2], _args[3], _args[4]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            _guiEx.showMessage(_args[0], _args[1], _args[2], _args[3]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasFrame)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasWindow)) {
                res_.setResult(Argument.getNull(_guiEx.getFrame()));
                return res_;
            }
            StringList mainArgs_ = _guiEx.getMainArgs();
            String typeStr_ = _cont.getStandards().getContent().getCharSeq().getAliasString();
            typeStr_ = StringExpUtil.getPrettyArrayType(typeStr_);
            int len_ = mainArgs_.size();
            ArrayStruct result_ = new ArrayStruct(len_, typeStr_);
            for (int i = 0; i < len_; i++) {
                result_.set(i, new StringStruct(mainArgs_.get(i)));
            }
            res_.setResult(result_);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasDialog)) {
            OtherDialog inst_ = ((DialogStruct)_instance).getDialog();
            if (StringUtil.quickEq(name_, aliasDialogSetModal)) {
                inst_.setModal(BooleanStruct.isTrue(_args[0]));
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(BooleanStruct.of(inst_.isModal()));
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasDimension)) {
            DimensionStruct inst_ = (DimensionStruct)_instance;
            if (StringUtil.quickEq(name_, aliasDimensionGetHeight)) {
                res_.setResult(inst_.getHeight());
                return res_;
            }
            res_.setResult(inst_.getWidth());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasComponent)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentInvokeLater)) {
                CustComponentStruct.invokeLater((RunnableContextEl) _cont,_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            CustComponentStruct inst_ = (CustComponentStruct)_instance;
            if (StringUtil.quickEq(name_, aliasAddKeyListener)) {
                inst_.addKeyListener(_args[0]);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRemoveKeyListener)) {
                inst_.removeKeyListener(_args[0]);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGetKeyListeners)) {
                res_.setResult(inst_.getKeyListeners(_cont));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasAddWheelListener)) {
                inst_.addWheel(_args[0]);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRemoveWheelListener)) {
                inst_.removeWheel(_args[0]);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGetWheelListeners)) {
                res_.setResult(inst_.getWheels(_cont));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasAddListener)) {
                inst_.addMouse(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRemoveListener)) {
                inst_.removeMouse(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGetListeners)) {
                res_.setResult(inst_.getMouses(_cont));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompBack)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    inst_.setBackground(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getBackground());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompFore)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    inst_.setForeground(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getForeground());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompFocusable)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    inst_.setFocusable(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.isFocusable());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompOpaque)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    inst_.setOpaque(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.isOpaque());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompToolTip)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    inst_.setToolTipText(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getToolTipText());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompGetFirstPos)) {
                res_.setResult(inst_.getXcoords());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompGetSecondPos)) {
                res_.setResult(inst_.getYcoords());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompLoc)) {
                inst_.setLocation(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompBorLine)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    inst_.setLineBorder(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                inst_.setLineBorder(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompBorTitle)) {
                inst_.setTitledBorder(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompBorLower)) {
                inst_.setLoweredBorder();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCompBorRaise)) {
                inst_.setRaisedBorder();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRequestFocus)) {
                inst_.requestFocus();
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGetFont)) {
                res_.setResult(inst_.getFont());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSetFont)) {
                inst_.setFont(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGetNextCompo)) {
                res_.setResult(inst_.getNext());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentGetPaint)) {
                res_.setResult(inst_.getPaintEvent());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentSetPaint)) {
                inst_.setPaintEvent(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentGetHeight)) {
                res_.setResult(new IntStruct(inst_.getHeight()));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentGetWidth)) {
                res_.setResult(new IntStruct(inst_.getWidth()));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentGetPreferredSize)) {
                res_.setResult(inst_.getPreferredSize());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentSetSize)) {
                inst_.setSize(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentSetPreferredSize)) {
                inst_.setPreferredSize(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentIsVisible)) {
                res_.setResult(inst_.isVisible());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentSetVisible)) {
                inst_.setVisible(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentIsAutoscrolls)) {
                res_.setResult(inst_.isAutoscrolls());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentSetAutoscrolls)) {
                inst_.setAutoscrolls(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComponentRepaint)) {
                Argument arg_ = new Argument(inst_);
                CustList<Argument> args_ = new CustList<Argument>(arg_);
                wrapAndCall(_cont, args_, _guiEx.getPairPaintMethod(), _stackCall);
                return res_;
            }
            res_.setResult(inst_.getParentComponent());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasPanel)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPanelAbsolute)) {
                res_.setResult(PanelStruct.newAbsolute(aliasPanel));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPanelFlow)) {
                res_.setResult(PanelStruct.newFlow(aliasPanel));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPanelGrid)) {
                res_.setResult(PanelStruct.newGrid(aliasPanel,((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct()));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPanelPageBox)) {
                res_.setResult(PanelStruct.newPageBox(aliasPanel));
                return res_;
            }
            PanelStruct strPan_ = (PanelStruct) _instance;
            if (StringUtil.quickEq(name_, aliasCount)) {
                res_.setResult(new IntStruct(strPan_.getComponentCount()));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPanelValidate)) {
                strPan_.validate();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRemoveAll)) {
                strPan_.removeAll();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasAddCompo)) {
                if (!(_args[0] instanceof CustComponentStruct)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (_method.getConstraints().getParametersTypesLength() == 2) {
                    strPan_.add((CustComponentStruct)_args[0],((NumberStruct)_args[1]).intStruct());
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                strPan_.add((CustComponentStruct)_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGetIndexCompo)) {
                res_.setResult(strPan_.getComponent(((NumberStruct)_args[0]).intStruct()));
                return res_;
            }
            if (StringUtil.quickEq(_cont.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),_method.getConstraints().getParametersType(0))) {
                res_.setResult(strPan_.remove(((NumberStruct)_args[0]).intStruct()));
                return res_;
            }
            res_.setResult(strPan_.remove(_args[0]));
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasPanelBorder)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (!(_args[0] instanceof CustComponentStruct)) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            PanelBorderStruct strPan_ = (PanelBorderStruct) _instance;
            strPan_.add((CustComponentStruct)_args[0],_args[1]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasTabbedPane)) {
            TabbedPaneStruct strPan_ = (TabbedPaneStruct) _instance;
            if (StringUtil.quickEq(name_, aliasTabbedPaneNb)) {
                res_.setResult(strPan_.getComponentCount());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRemoveAll)) {
                strPan_.removeAll();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTabbedPaneAdd)) {
                if (!(_args[1] instanceof CustComponentStruct)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                strPan_.add(_args[0],(CustComponentStruct)_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTabbedPaneSet)) {
                if (!(_args[1] instanceof CustComponentStruct)) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                strPan_.setTab(_args[0],(CustComponentStruct)_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTabbedPaneSetTitle)) {
                strPan_.setTitle(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTabbedPaneGet)) {
                res_.setResult(strPan_.getComponent(_args[0]));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTabbedPaneGetTitle)) {
                res_.setResult(strPan_.getTitle(_args[0]));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTabbedPaneSelIndex)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    strPan_.setSelectedIndex(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(strPan_.getSelectedIndex());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTabbedPaneIndex)) {
                if (!(_args[0] instanceof CustComponentStruct)) {
                    res_.setResult(new IntStruct(-1));
                    return res_;
                }
                res_.setResult(strPan_.index((CustComponentStruct)_args[0]));
                return res_;
            }
            if (StringUtil.quickEq(_cont.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),_method.getConstraints().getParametersType(0))) {
                res_.setResult(strPan_.remove(_args[0]));
                return res_;
            }
            if (!(_args[0] instanceof CustComponentStruct)) {
                res_.setResult(new IntStruct(-1));
                return res_;
            }
            res_.setResult(strPan_.remove((CustComponentStruct) _args[0]));
            return res_;
        }
        if (StringUtil.quickEq(type_,aliasScrollPane)) {
            ScrollPaneStruct strPan_ = (ScrollPaneStruct) _instance;
            if (StringUtil.quickEq(name_, aliasScrollPaneHorizontalValue)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    strPan_.setHorizontalValue(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(strPan_.getHorizontalValue());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasScrollPaneVerticalValue)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    strPan_.setVerticalValue(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(strPan_.getVerticalValue());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasScrollPaneGetView)) {
                res_.setResult(strPan_.getView());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasScrollPaneValidate)) {
                strPan_.revalidate();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            strPan_.setViewportView(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_,aliasSplitPane)) {
            SplitPaneStruct strPan_ = (SplitPaneStruct) _instance;
            if (StringUtil.quickEq(name_, aliasScrollPaneValidate)) {
                strPan_.revalidate();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSplitPaneIsOneTouchExpandable)) {
                res_.setResult(strPan_.isOneTouchExpandable());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSplitPaneSetOneTouchExpandable)) {
                strPan_.setOneTouchExpandable(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSplitPaneIsContinuousLayout)) {
                res_.setResult(strPan_.isContinuousLayout());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSplitPaneSetContinuousLayout)) {
                strPan_.setContinuousLayout(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSplitPaneGetLeft)) {
                res_.setResult(strPan_.getLeftComponent());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSplitPaneSetLeft)) {
                strPan_.setLeftComponent(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSplitPaneGetRight)) {
                res_.setResult(strPan_.getRightComponent());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSplitPaneSetRight)) {
                strPan_.setRightComponent(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSplitPaneGetDividerSize)) {
                res_.setResult(strPan_.getDividerSize());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSplitPaneSetDividerSize)) {
                strPan_.setDividerSize(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSplitPaneGetDividerLocation)) {
                res_.setResult(strPan_.getDividerLocation());
                return res_;
            }
            strPan_.setDividerLocation(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasTextLabel)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            TextLabelStruct txt_ = (TextLabelStruct) _instance;
            txt_.setText(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasImageLabel)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            PreparedLabelStruct txt_ = (PreparedLabelStruct) _instance;
            WindowFull window_ = ((LgNamesGui) _cont.getStandards()).getGuiExecutingBlocks().getWindow();
            txt_.setImage(window_.getImageFactory(),_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasMouseEvent)) {
            MouseEventStruct event_ = (MouseEventStruct)_instance;
            if (StringUtil.quickEq(name_, aliasMouseEventIsAlt)) {
                res_.setResult(event_.isAlt());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMouseEventIsCtrl)) {
                res_.setResult(event_.isCtrl());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMouseEventIsShift)) {
                res_.setResult(event_.isShift());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMouseEventIsLeft)) {
                res_.setResult(event_.isLeft());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMouseEventIsMiddle)) {
                res_.setResult(event_.isMiddle());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMouseEventIsRight)) {
                res_.setResult(event_.isRight());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMouseEventGetClicks)) {
                res_.setResult(event_.getClicks());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMouseEventGetFirst)) {
                res_.setResult(event_.getFirst());
                return res_;
            }
            res_.setResult(event_.getSecond());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasWheelEvent)) {
            MouseWheelEventStruct event_ = (MouseWheelEventStruct)_instance;
            res_.setResult(event_.getRotated());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasKeyEvent)) {
            KeyEventStruct event_ = (KeyEventStruct)_instance;
            if (StringUtil.quickEq(name_, aliasKeyEventIsAlt)) {
                res_.setResult(event_.isAlt());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasKeyEventIsCtrl)) {
                res_.setResult(event_.isCtrl());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasKeyEventIsShift)) {
                res_.setResult(event_.isShift());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasKeyEventChar)) {
                res_.setResult(event_.getKeyChar());
                return res_;
            }
            res_.setResult(event_.getKeyCode());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasInput)) {
            InputStruct in_ = (InputStruct) _instance;
            if (StringUtil.quickEq(name_, aliasInputSetEnabled)) {
                in_.setEnabled(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(in_.isEnabled());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasProgBar)) {
            ProgressBarStruct inst_ = (ProgressBarStruct) _instance;
            if (StringUtil.quickEq(name_, aliasProgBarMin)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    inst_.setMinimum(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getMinimum());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasProgBarMax)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    inst_.setMaximum(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getMaximum());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasProgBarValue)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    inst_.setValue(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getValue());
                return res_;
            }
            if (_method.getConstraints().getParametersTypesLength() == 1) {
                inst_.setHorizontal(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.isHorizontal());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasButton)) {
            if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
                processFailInit(_cont, _custAliases, _stackCall);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            PlainButtonStruct pl_ = (PlainButtonStruct) _instance;
            pl_.addActionListener(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasFont)) {
            if (_method.getConstraints().isStaticMethod()) {
                Struct first_ = _args[0];
                if (!(first_ instanceof ImageStruct)) {
                    res_.setResult(new IntStruct(-1));
                    return res_;
                }
                Struct font_ = ((ImageStruct) first_).getFont();
                if (!(font_ instanceof FontStruct)) {
                    res_.setResult(new IntStruct(-1));
                    return res_;
                }
                FontStruct f_ = (FontStruct) font_;
                res_.setResult(_guiEx.stringWidth(f_, _args[1]));
                return res_;
            }
            FontStruct f_ = (FontStruct) _instance;
            if (StringUtil.quickEq(name_, aliasFontGetName)) {
                res_.setResult(f_.getName());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasFontGetSize)) {
                res_.setResult(f_.getSize());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasFontIsBold)) {
                res_.setResult(f_.isBold());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasFontIsItalic)) {
                res_.setResult(f_.isItalic());
                return res_;
            }
            res_.setResult(_guiEx.stringWidth(f_, _args[0]));
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasColor)) {
            ColorStruct c_ = (ColorStruct) _instance;
            if (StringUtil.quickEq(name_, aliasColorAlpha)) {
                res_.setResult(c_.getAlpha());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasColorRed)) {
                res_.setResult(c_.getRed());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasColorGreen)) {
                res_.setResult(c_.getGreen());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasColorBlue)) {
                res_.setResult(c_.getBlue());
                return res_;
            }
            res_.setResult(c_.isTransparent());
            return res_;
        }
        if (StringUtil.quickEq(type_,aliasImage)) {
            if (StringUtil.quickEq(name_, aliasImageEq)) {
                if (!(_args[0] instanceof ImageStruct)) {
                    res_.setResult(BooleanStruct.of(_args[1] == NullStruct.NULL_VALUE));
                    return res_;
                }
                if (!(_args[1] instanceof ImageStruct)) {
                    res_.setResult(BooleanStruct.of(false));
                    return res_;
                }
                ImageStruct first_ = (ImageStruct) _args[0];
                ImageStruct second_ = (ImageStruct) _args[1];
                res_.setResult(BooleanStruct.of(ImageStruct.eq(first_.getImage(),second_.getImage())));
                return res_;
            }
            ImageStruct image_ = (ImageStruct) _instance;
            if (StringUtil.quickEq(name_, aliasImageGetHeight)) {
                res_.setResult(image_.getHeight());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageGetWidth)) {
                res_.setResult(image_.getWidth());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageSet)) {
                if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _stackCall.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.setPixel(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageGet)) {
                res_.setResult(image_.getPixel(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct()));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageIsWithAlpha)) {
                res_.setResult(image_.isWithAlpha());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageGetColor)) {
                res_.setResult(image_.getColor());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageSetColor)) {
                if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _stackCall.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.setColor(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageGetFont)) {
                res_.setResult(image_.getFont());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageSetFont)) {
                if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                    _stackCall.getInitializingTypeInfos().failInitEnums();
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.setFont(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
                _stackCall.getInitializingTypeInfos().failInitEnums();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageDispose)) {
                image_.dispose();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageDraw)) {
                if (StringUtil.quickEq(getAliasImage(),_method.getConstraints().getParametersType(0))) {
                    image_.drawImage(_args[0],((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct());
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                image_.drawString(_args[0],((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageDrawLine)) {
                image_.drawLine(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageDrawRect)) {
                image_.drawRect(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageDrawOval)) {
                image_.drawOval(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageDrawPolygon)) {
                image_.drawPolygon(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageFillRect)) {
                image_.fillRect(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasImageFillOval)) {
                image_.fillOval(((NumberStruct)_args[0]).intStruct(),((NumberStruct)_args[1]).intStruct(),((NumberStruct)_args[2]).intStruct(),((NumberStruct)_args[3]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            image_.fillPolygon(_args[0],_args[1]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasTreeNode)) {
            return treeNode(_cont, _instance, _method, _args);
        }
        if (StringUtil.quickEq(type_, aliasTree)) {
            TreeStruct inst_ = (TreeStruct) _instance;
            if (StringUtil.quickEq(name_, aliasTreeAddTreeListener)) {
                inst_.addTreeSelectionListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTreeGetSelected)) {
                if (_method.getConstraints().getParametersTypesLength() == 1) {
                    inst_.select(_args[0]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(inst_.getLastSelectedPathComponent());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTreeIsRootVisible)) {
                res_.setResult(inst_.isRootVisible());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTreeSetRootVisible)) {
                inst_.setRootVisible(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            inst_.reload();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasTableGui)) {
            TableStruct inst_ = (TableStruct) _instance;
            if (StringUtil.quickEq(name_, aliasTableAddSelect)) {
                inst_.addListSelectionListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableAddHeader)) {
                inst_.addHeaderListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableMoveColumn)) {
                inst_.moveColumn(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableAddInterval)) {
                inst_.addSelectInterval(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableRemoveInterval)) {
                inst_.removeSelectInterval(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableApplyChanges)) {
                inst_.applyChanges();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableGetColumnAtPoint)) {
                res_.setResult(inst_.columnAtPoint(_args[0],_args[1]));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableGetRowAtPoint)) {
                res_.setResult(inst_.rowAtPoint(_args[0],_args[1]));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableGetColumnCount)) {
                res_.setResult(inst_.getColumnCount());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableGetRowCount)) {
                res_.setResult(inst_.getRowCount());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableGetColumnName)) {
                res_.setResult(inst_.getColumnName(_args[0]));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableGetSelectedRow)) {
                res_.setResult(inst_.getSelectedRow());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableGetSelectedRows)) {
                res_.setResult(inst_.getSelectedRows(_cont));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableGetSelectedRowCount)) {
                res_.setResult(inst_.getSelectedRowCount());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableGetValue)) {
                res_.setResult(inst_.getValueAt(_args[0],_args[1]));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableSetValue)) {
                inst_.setValueAt(_args[0],_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableSetColumns)) {
                inst_.setColumnIdentifiers(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableSetMultiple)) {
                inst_.setMultiSelect(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableSetReorder)) {
                inst_.setReorderingAllowed(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableSetRowCount)) {
                inst_.setRowCount(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTableIsMultiple)) {
                res_.setResult(inst_.isMultiSelect());
                return res_;
            }
            res_.setResult(inst_.isReorderingAllowed());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasRender)) {
            RenderStruct image_ = (RenderStruct) _instance;
            if (StringUtil.quickEq(name_, aliasRenderSetPaint)) {
                image_.setPaint(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRenderSetWidth)) {
                image_.setWidth(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRenderSetHeight)) {
                image_.setHeight(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRenderGetPaint)) {
                res_.setResult(image_.getPaint());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRenderGetHeight)) {
                res_.setResult(image_.getHeight());
                return res_;
            }
            res_.setResult(image_.getWidth());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasGrList)) {
            GraphicListStruct inst_ = (GraphicListStruct) _instance;
            if (StringUtil.quickEq(name_, aliasGrListAddSelection)) {
                inst_.addListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListRemoveSelection)) {
                inst_.removeListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListGetSelections)) {
                res_.setResult(inst_.getListeners(_cont));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListSetVisibleRowCount)) {
                inst_.setVisibleRowCount(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListGetVisibleRowCount)) {
                res_.setResult(inst_.getVisibleRowCount());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListSetRender)) {
                inst_.setRender((GuiContextEl) _cont, _args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListGetRender)) {
                res_.setResult(inst_.getRender());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListGetListView)) {
                res_.setResult(inst_.getListView(_cont));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListGetSelectedIndexes)) {
                res_.setResult(inst_.getSelectedIndexes(_cont));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListRemove)){
                inst_.remove(((NumberStruct)_args[0]).intStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListClear)){
                inst_.clear();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListSetSelectedIndexes)) {
                inst_.setSelectedIndexes(_args[0]);
                if (!inst_.isCust()&&_args[0] instanceof ArrayStruct) {
                    Argument arg_ = new Argument(inst_);
                    CustList<Argument> args_ = new CustList<Argument>(arg_);
                    wrapAndCall(_cont, args_, _guiEx.getPairPaintRefresh(), _stackCall);
                }
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListClearSelection)) {
                inst_.clearSelection();
                if (!inst_.isCust()) {
                    Argument arg_ = new Argument(inst_);
                    CustList<Argument> args_ = new CustList<Argument>(arg_);
                    wrapAndCall(_cont, args_, _guiEx.getPairPaintRefresh(), _stackCall);
                }
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListUpdateGraphics)) {
                inst_.updateGraphics();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasGrListSet)) {
                if (_method.getConstraints().getParametersTypesLength() == 3) {
                    inst_.set(((NumberStruct)_args[0]).intStruct(),_args[1],_args[2]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                if (inst_.isCust()) {
                    inst_.set(((NumberStruct)_args[0]).intStruct(),_args[1]);
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                Argument arg_ = new Argument(inst_);
                CustList<Argument> args_ = new CustList<Argument>(arg_);
                args_.add(new Argument(_args[0]));
                args_.add(new Argument(_args[1]));
                wrapAndCall(_cont, args_, _guiEx.getPairPaintSet(), _stackCall);
                return res_;
            }
            if (_method.getConstraints().getParametersTypesLength() == 3) {
                inst_.add(((NumberStruct)_args[0]).intStruct(),_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (inst_.isCust()) {
                inst_.add(((NumberStruct)_args[0]).intStruct(),_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            Argument arg_ = new Argument(inst_);
            CustList<Argument> args_ = new CustList<Argument>(arg_);
            args_.add(new Argument(_args[0]));
            args_.add(new Argument(_args[1]));
            wrapAndCall(_cont, args_, _guiEx.getPairPaintAdd(), _stackCall);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasCombo)) {
            GraphicComboStruct inst_ = (GraphicComboStruct) _instance;
            if (StringUtil.quickEq(name_, aliasComboAddListener)) {
                inst_.addListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComboRemoveListener)) {
                inst_.removeListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComboGetListeners)) {
                res_.setResult(inst_.getListeners(_cont));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComboGetSelectedIndexes)) {
                res_.setResult(inst_.getSelectedIndexes(_cont));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComboGetSelectedIndex)) {
                res_.setResult(inst_.getSelectedIndex());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComboGetSelectedItem)) {
                res_.setResult(inst_.getSelectedItem());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComboAddItem)) {
                inst_.addItem(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComboSelectItem)) {
                inst_.selectItem((NumberStruct) _args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComboRemoveItem)) {
                inst_.removeItem(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasComboRemoveAllItems)) {
                inst_.removeAllItems();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getItemCount());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasRadio)) {
            RadioButtonStruct inst_ = (RadioButtonStruct) _instance;
            if (StringUtil.quickEq(name_, aliasAddChange)) {
                inst_.addChangeListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRadioSetSelected)) {
                inst_.setSelected(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRadioIsSelected)) {
                res_.setResult(inst_.isSelected());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasRadioSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasCheckBox)) {
            CheckBoxStruct inst_ = (CheckBoxStruct) _instance;
            if (StringUtil.quickEq(name_, aliasCheckBoxSetSelected)) {
                inst_.setSelected(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCheckBoxIsSelected)) {
                res_.setResult(inst_.isSelected());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasCheckBoxSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasPopupMenu)) {
            PopupStruct inst_ = (PopupStruct) _instance;
            if (StringUtil.quickEq(name_, aliasPopupMenuAdd)) {
                inst_.add(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPopupMenuGetComp)) {
                res_.setResult(inst_.getCompo(_args[0]));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPopupMenuRemoveComp)) {
                inst_.remove(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPopupMenuNbComp)) {
                res_.setResult(inst_.getCompoCount());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPopupMenuAddMenu)) {
                inst_.addMenu(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPopupMenuGetMenu)) {
                res_.setResult(inst_.getMenu(_args[0]));
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPopupMenuRemoveMenu)) {
                inst_.removeMenu(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasPopupMenuNbMenu)) {
                res_.setResult(inst_.getMenuCount());
                return res_;
            }
            inst_.show(_args[0],_args[1],_args[2]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasTextField)) {
            TextFieldStruct inst_ = (TextFieldStruct) _instance;
            if (StringUtil.quickEq(name_, aliasTextFieldSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextFieldAddAction)) {
                inst_.addActionListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasTextArea)) {
            TextAreaStruct inst_ = (TextAreaStruct) _instance;
            if (StringUtil.quickEq(name_, aliasTextAreaAppend)) {
                inst_.append(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextAreaInsert)) {
                inst_.insert(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextAreaReplaceRange)) {
                inst_.replaceRange(_args[0],_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextAreaReplaceSelection)) {
                inst_.replaceSelection(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextAreaSelect)) {
                inst_.select(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextAreaSelectAll)) {
                inst_.selectAll();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextAreaGetSelectedText)) {
                res_.setResult(inst_.getSelectedText());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextAreaSetSelectionStart)) {
                inst_.setSelectionStart(_args[0]);
                res_.setResult(inst_.getSelectedText());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextAreaSetSelectionEnd)) {
                inst_.setSelectionEnd(_args[0]);
                res_.setResult(inst_.getSelectedText());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextAreaSetTabSize)) {
                inst_.setTabSize(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextAreaGetTabSize)) {
                res_.setResult(inst_.getTabSize());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasTextAreaSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(inst_.getText());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasSpinner)) {
            SpinnerStruct inst_ = (SpinnerStruct) _instance;
            if (StringUtil.quickEq(name_, aliasAddChange)) {
                inst_.addChangeListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSpinnerSetRange)) {
                inst_.setRange(_args[0],_args[1]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSpinnerSetRangeValue)) {
                inst_.setRangeValue(_args[0],_args[1],_args[2]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSpinnerGetMin)) {
                res_.setResult(inst_.getMin());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSpinnerSetMin)) {
                inst_.setMin(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSpinnerGetMax)) {
                res_.setResult(inst_.getMax());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSpinnerSetMax)) {
                inst_.setMax(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSpinnerGetValue)) {
                res_.setResult(inst_.getValue());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSpinnerSetValue)) {
                inst_.setValue(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSpinnerGetStep)) {
                res_.setResult(inst_.getStep());
                return res_;
            }
            inst_.setStep(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasSlider)) {
            SliderStruct inst_ = (SliderStruct) _instance;
            if (StringUtil.quickEq(name_, aliasAddChange)) {
                inst_.addChangeListener(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSliderGetMin)) {
                res_.setResult(inst_.getMin());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSliderSetMin)) {
                inst_.setMin(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSliderGetMax)) {
                res_.setResult(inst_.getMax());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSliderSetMax)) {
                inst_.setMax(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSliderGetValue)) {
                res_.setResult(inst_.getValue());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSliderSetValue)) {
                inst_.setValue(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasSliderGetOrientation)) {
                res_.setResult(inst_.getOrientation());
                return res_;
            }
            inst_.setOrientation(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasMenuBar)) {
            MenuBarStruct inst_ = (MenuBarStruct)_instance;
            if (StringUtil.quickEq(name_, aliasMenuBarAdd)) {
                inst_.add(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMenuBarRemove)) {
                inst_.remove(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMenuBarGet)) {
                res_.setResult(inst_.getMenu(_args[0]));
                return res_;
            }
            res_.setResult(inst_.getMenuCount());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasAbsMenu)) {
            AbsMenuStruct inst_ = (AbsMenuStruct)_instance;
            if (StringUtil.quickEq(name_, aliasAbsMenuGetParent)) {
                res_.setResult(inst_.getParentMenu());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasAbsMenuGetText)) {
                res_.setResult(inst_.getText());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasAbsMenuSetText)) {
                inst_.setText(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasAbsMenuIsEnabled)) {
                res_.setResult(inst_.isEnabled());
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasAbsMenuSetEnabled)) {
                inst_.setEnabled(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            inst_.setDeepEnabled(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasMenu)) {
            MenuStruct inst_ = (MenuStruct) _instance;
            if (StringUtil.quickEq(name_, aliasMenuAdd)) {
                inst_.add(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMenuRemove)) {
                inst_.remove(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMenuAddSeparator)) {
                inst_.addSeparator();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(name_, aliasMenuGet)) {
                res_.setResult(inst_.getMenu(_args[0]));
                return res_;
            }
            res_.setResult(inst_.getMenuCount());
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasAbsMenuItem)) {
            AbsMenuItemStruct inst_ = (AbsMenuItemStruct) _instance;
            inst_.addActionListener(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(type_, aliasMenuItemCheck)) {
            MenuItemCheckStruct inst_ = (MenuItemCheckStruct) _instance;
            if (StringUtil.quickEq(name_, aliasMenuItemCheckIsSelected)) {
                res_.setResult(inst_.isSelected());
                return res_;
            }
            inst_.setSelected(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return _custAliases.getOtherResult(_cont,_instance,_method, _execBlocks, _stackCall, _args);
    }

    private ResultErrorStd treeNode(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct[] _args) {
        String name_ = _method.getConstraints().getName();
        if (StringUtil.quickEq(name_, aliasTreeNodeEq)) {
            ResultErrorStd res_ = new ResultErrorStd();
            res_.setResult(TreeNodeStruct.eq(_args[0],_args[1]));
            return res_;
        }
        TreeNodeStruct inst_ = (TreeNodeStruct)_instance;
        if (StringUtil.quickEq(name_, aliasTreeNodeAdd)) {
            ResultErrorStd res_ = new ResultErrorStd();
            inst_.add(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeInsert)) {
            ResultErrorStd res_ = new ResultErrorStd();
            inst_.insert(_args[0], _args[1]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeRemove)) {
            ResultErrorStd res_ = new ResultErrorStd();
            if (StringUtil.quickEq(_cont.getStandards().getContent().getPrimTypes().getAliasPrimInteger(), _method.getConstraints().getParametersType(0))) {
                inst_.remove(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            inst_.removeNode(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return treeNodeDef(_args, _method, inst_);
    }

    private ResultErrorStd treeNodeDef(Struct[] _args, ClassMethodId _method, TreeNodeStruct _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String name_ = _method.getConstraints().getName();
        if (StringUtil.quickEq(name_, aliasTreeNodeRemoveFromParent)) {
            _inst.removeFromParent();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeRemoveAllChildren)) {
            _inst.removeAllChildren();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeSetUserObject)) {
            _inst.setUserObject(_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeGetUserObject)) {
            res_.setResult(_inst.getUserObject());
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeNb)) {
            res_.setResult(_inst.getChildCount());
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeGetFirstChild)) {
            res_.setResult(_inst.getFirstChild());
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeGetLastChild)) {
            res_.setResult(_inst.getLastChild());
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeGetNextSibling)) {
            res_.setResult(_inst.getNextSibling());
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeGetPreviousSibling)) {
            res_.setResult(_inst.getPreviousSibling());
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeGetParentNode)) {
            res_.setResult(_inst.getParentNode());
            return res_;
        }
        if (StringUtil.quickEq(name_, aliasTreeNodeIsAncestor)) {
            res_.setResult(_inst.isAncestorMethod(_args[0]));
            return res_;
        }
        res_.setResult(_inst.isDescendantMethod(_args[0]));
        return res_;
    }

    protected static void processFailInit(ContextEl _cont, CustAliases _custAliases, StackCall _stackCall) {
        _custAliases.processFailInit(_cont, _stackCall);
    }
    private void wrapAndCall(ContextEl _cont, CustList<Argument> _args, ExecTypeFunction _pair, StackCall _stackCall) {
        ArgumentListCall argList_ = new ArgumentListCall(_args);
        ExecTemplates.wrapAndCall(_pair, new ExecFormattedRootBlock(_pair.getType(),aliasPaint),Argument.createVoid(), _cont, _stackCall, argList_);
    }

    public void otherAliasGui(StringMap<String> _util, StringMap<String> _cust) {
        setAliasTreeNode(LgNamesContent.get(_util, _cust, TREE_NODE));
        setAliasTree(LgNamesContent.get(_util, _cust, TREE));
        setAliasTableGui(LgNamesContent.get(_util, _cust, TABLE_GUI));
        setAliasButton(LgNamesContent.get(_util, _cust, BUTTON));
        setAliasConfirm(LgNamesContent.get(_util, _cust, CONFIRM));
        setAliasFrame(LgNamesContent.get(_util, _cust, FRAME));
        setAliasDialog(LgNamesContent.get(_util, _cust, DIALOG));
        setAliasPanel(LgNamesContent.get(_util, _cust, PANEL));
        setAliasProgBar(LgNamesContent.get(_util, _cust, PROG_BAR));
        setAliasImageSet(LgNamesContent.get(_util, _cust, IMAGE_SET));
        setAliasArgs(LgNamesContent.get(_util, _cust, ARGS));
        setAliasKeyEvent(LgNamesContent.get(_util, _cust, KEY_EVENT));
        setAliasRadio(LgNamesContent.get(_util, _cust, RADIO));
        setAliasCheckBox(LgNamesContent.get(_util, _cust, CHECK_BOX));
        setAliasSetFont(LgNamesContent.get(_util, _cust, SET_FONT));
        setAliasSpinner(LgNamesContent.get(_util, _cust, SPINNER));
        setAliasDispose(LgNamesContent.get(_util, _cust, DISPOSE));
        setAliasGrList(LgNamesContent.get(_util, _cust, GR_LIST));
        setAliasGetFont(LgNamesContent.get(_util, _cust, GET_FONT));
        setAliasImage(LgNamesContent.get(_util, _cust, IMAGE));
        setAliasSlider(LgNamesContent.get(_util, _cust, SLIDER));
        setAliasPaintAdd(LgNamesContent.get(_util, _cust, PAINT_ADD));
        setAliasPaintSet(LgNamesContent.get(_util, _cust, PAINT_SET));
        setAliasCount(LgNamesContent.get(_util, _cust, COUNT));
        setAliasPack(LgNamesContent.get(_util, _cust, PACK));
        setAliasColor(LgNamesContent.get(_util, _cust, COLOR));
        setAliasWindow(LgNamesContent.get(_util, _cust, WINDOW));
        setAliasWindowSet(LgNamesContent.get(_util, _cust, WINDOW_SET));
        setAliasWindowSetAdd(LgNamesContent.get(_util, _cust, WINDOW_SET_ADD));
        setAliasWindowSetAll(LgNamesContent.get(_util, _cust, WINDOW_SET_ALL));
        setAliasWindowSetContains(LgNamesContent.get(_util, _cust, WINDOW_SET_CONTAINS));
        setAliasWindowSetRemove(LgNamesContent.get(_util, _cust, WINDOW_SET_REMOVE));
        setAliasWindowSetSnapshot(LgNamesContent.get(_util, _cust, WINDOW_SET_SNAPSHOT));
        setAliasCompBack(LgNamesContent.get(_util, _cust, COMP_BACK));
        setAliasPaint(LgNamesContent.get(_util, _cust, PAINT));
        setAliasCompLoc(LgNamesContent.get(_util, _cust, COMP_LOC));
        setAliasImageGet(LgNamesContent.get(_util, _cust, IMAGE_GET));
        setAliasRender(LgNamesContent.get(_util, _cust, RENDER));
        setAliasCombo(LgNamesContent.get(_util, _cust, COMBO));
        setAliasAddCompo(LgNamesContent.get(_util, _cust, ADD_COMPO));
        setAliasInput(LgNamesContent.get(_util, _cust, INPUT));
        setAliasFont(LgNamesContent.get(_util, _cust, FONT));
        setAliasCompFore(LgNamesContent.get(_util, _cust, COMP_FORE));
        setAliasKeyTyped(LgNamesContent.get(_util, _cust, KEY_TYPED));
        setAliasColorRed(LgNamesContent.get(_util, _cust, COLOR_RED));
        setAliasTextArea(LgNamesContent.get(_util, _cust, TEXT_AREA));
        setAliasFontGetSize(LgNamesContent.get(_util, _cust, FONT_GET_SIZE));
        setAliasDimension(LgNamesContent.get(_util, _cust, DIMENSION));
        setAliasProgBarOr(LgNamesContent.get(_util, _cust, PROG_BAR_OR));
        setAliasFontIsBold(LgNamesContent.get(_util, _cust, FONT_IS_BOLD));
        setAliasDimensionGetHeight(LgNamesContent.get(_util, _cust, DIMENSION_GET_HEIGHT));
        setAliasPanelBorderNorth(LgNamesContent.get(_util, _cust, PANEL_BORDER_NORTH));
        setAliasPanelBorderSouth(LgNamesContent.get(_util, _cust, PANEL_BORDER_SOUTH));
        setAliasRemoveWindowListener(LgNamesContent.get(_util, _cust, REMOVE_WINDOW_LISTENER));
        setAliasWindowTypeRelative(LgNamesContent.get(_util, _cust, WINDOW_TYPE_RELATIVE));
        setAliasPanelBorderAfterLast(LgNamesContent.get(_util, _cust, PANEL_BORDER_AFTER_LAST));
        setAliasFontGetName(LgNamesContent.get(_util, _cust, FONT_GET_NAME));
        setAliasDimensionGetWidth(LgNamesContent.get(_util, _cust, DIMENSION_GET_WIDTH));
        setAliasComponent(LgNamesContent.get(_util, _cust, COMPONENT));
        setAliasPanelBorderAfterLineEnds(LgNamesContent.get(_util, _cust, PANEL_BORDER_AFTER_LINE_ENDS));
        setAliasComponentSetAutoscrolls(LgNamesContent.get(_util, _cust, COMPONENT_SET_AUTOSCROLLS));
        setAliasComponentIsAutoscrolls(LgNamesContent.get(_util, _cust, COMPONENT_IS_AUTOSCROLLS));
        setAliasComponentGetWidth(LgNamesContent.get(_util, _cust, COMPONENT_GET_WIDTH));
        setAliasComponentGetHeight(LgNamesContent.get(_util, _cust, COMPONENT_GET_HEIGHT));
        setAliasPanelBorderEast(LgNamesContent.get(_util, _cust, PANEL_BORDER_EAST));
        setAliasProgBarMax(LgNamesContent.get(_util, _cust, PROG_BAR_MAX));
        setAliasGetWindowListeners(LgNamesContent.get(_util, _cust, GET_WINDOW_LISTENERS));
        setAliasPanelBorderBeforeFirst(LgNamesContent.get(_util, _cust, PANEL_BORDER_BEFORE_FIRST));
        setAliasTextLabel(LgNamesContent.get(_util, _cust, TEXT_LABEL));
        setAliasPanelBorderWest(LgNamesContent.get(_util, _cust, PANEL_BORDER_WEST));
        setAliasProgBarMin(LgNamesContent.get(_util, _cust, PROG_BAR_MIN));
        setAliasProgBarValue(LgNamesContent.get(_util, _cust, PROG_BAR_VALUE));
        setAliasPanelBorder(LgNamesContent.get(_util, _cust, PANEL_BORDER));
        setAliasFontIsItalic(LgNamesContent.get(_util, _cust, FONT_IS_ITALIC));
        setAliasImageLabel(LgNamesContent.get(_util, _cust, IMAGE_LABEL));
        setAliasPanelBorderCenter(LgNamesContent.get(_util, _cust, PANEL_BORDER_CENTER));
        setAliasFontStringWidth(LgNamesContent.get(_util, _cust, FONT_STRING_WIDTH));
        setAliasTableSetColumns(LgNamesContent.get(_util, _cust, TABLE_SET_COLUMNS));
        setAliasTreeIsRootVisible(LgNamesContent.get(_util, _cust, TREE_IS_ROOT_VISIBLE));
        setAliasTreeNodeRemoveFromParent(LgNamesContent.get(_util, _cust, TREE_NODE_REMOVE_FROM_PARENT));
        setAliasTreeNodeSetUserObject(LgNamesContent.get(_util, _cust, TREE_NODE_SET_USER_OBJECT));
        setAliasTreeReload(LgNamesContent.get(_util, _cust, TREE_RELOAD));
        setAliasTreeAddTreeListener(LgNamesContent.get(_util, _cust, TREE_ADD_TREE_LISTENER));
        setAliasTreeNodeEq(LgNamesContent.get(_util, _cust, TREE_NODE_EQ));
        setAliasTreeNodeNb(LgNamesContent.get(_util, _cust, TREE_NODE_NB));
        setAliasTableIsMultiple(LgNamesContent.get(_util, _cust, TABLE_IS_MULTIPLE));
        setAliasTableListener(LgNamesContent.get(_util, _cust, TABLE_LISTENER));
        setAliasTableGetRowCount(LgNamesContent.get(_util, _cust, TABLE_GET_ROW_COUNT));
        setAliasTreeNodeIsAncestor(LgNamesContent.get(_util, _cust, TREE_NODE_IS_ANCESTOR));
        setAliasTreeNodeGetNextSibling(LgNamesContent.get(_util, _cust, TREE_NODE_GET_NEXT_SIBLING));
        setAliasTreeNodeGetLastChild(LgNamesContent.get(_util, _cust, TREE_NODE_GET_LAST_CHILD));
        setAliasTreeSetRootVisible(LgNamesContent.get(_util, _cust, TREE_SET_ROOT_VISIBLE));
        setAliasTableSetMultiple(LgNamesContent.get(_util, _cust, TABLE_SET_MULTIPLE));
        setAliasTreeNodeGetParentNode(LgNamesContent.get(_util, _cust, TREE_NODE_GET_PARENT_NODE));
        setAliasTreeNodeRemove(LgNamesContent.get(_util, _cust, TREE_NODE_REMOVE));
        setAliasTableGetSelectedRow(LgNamesContent.get(_util, _cust, TABLE_GET_SELECTED_ROW));
        setAliasTreeNodeGetFirstChild(LgNamesContent.get(_util, _cust, TREE_NODE_GET_FIRST_CHILD));
        setAliasTreeNodeGetUserObject(LgNamesContent.get(_util, _cust, TREE_NODE_GET_USER_OBJECT));
        setAliasTableGetSelectedRows(LgNamesContent.get(_util, _cust, TABLE_GET_SELECTED_ROWS));
        setAliasTableValueTableChanged(LgNamesContent.get(_util, _cust, TABLE_VALUE_TABLE_CHANGED));
        setAliasTableGetColumnCount(LgNamesContent.get(_util, _cust, TABLE_GET_COLUMN_COUNT));
        setAliasTableGetRowAtPoint(LgNamesContent.get(_util, _cust, TABLE_GET_ROW_AT_POINT));
        setAliasTableSetRowCount(LgNamesContent.get(_util, _cust, TABLE_SET_ROW_COUNT));
        setAliasTableGetColumnName(LgNamesContent.get(_util, _cust, TABLE_GET_COLUMN_NAME));
        setAliasTableGetValue(LgNamesContent.get(_util, _cust, TABLE_GET_VALUE));
        setAliasTreeNodeIsDescendant(LgNamesContent.get(_util, _cust, TREE_NODE_IS_DESCENDANT));
        setAliasTableSetValue(LgNamesContent.get(_util, _cust, TABLE_SET_VALUE));
        setAliasTreeGetSelected(LgNamesContent.get(_util, _cust, TREE_GET_SELECTED));
        setAliasTableGetSelectedRowCount(LgNamesContent.get(_util, _cust, TABLE_GET_SELECTED_ROW_COUNT));
        setAliasTableGetColumnAtPoint(LgNamesContent.get(_util, _cust, TABLE_GET_COLUMN_AT_POINT));
        setAliasConfirmOk(LgNamesContent.get(_util, _cust, CONFIRM_OK));
        setAliasConfirmYesNo(LgNamesContent.get(_util, _cust, CONFIRM_YES_NO));
        setAliasTableRemoveInterval(LgNamesContent.get(_util, _cust, TABLE_REMOVE_INTERVAL));
        setAliasConfirmFieldOk(LgNamesContent.get(_util, _cust, CONFIRM_FIELD_OK));
        setAliasDialogSetModal(LgNamesContent.get(_util, _cust, DIALOG_SET_MODAL));
        setAliasTableApplyChanges(LgNamesContent.get(_util, _cust, TABLE_APPLY_CHANGES));
        setAliasTableIsReorder(LgNamesContent.get(_util, _cust, TABLE_IS_REORDER));
        setAliasConfirmFieldYes(LgNamesContent.get(_util, _cust, CONFIRM_FIELD_YES));
        setAliasTableSetReorder(LgNamesContent.get(_util, _cust, TABLE_SET_REORDER));
        setAliasConfirmField(LgNamesContent.get(_util, _cust, CONFIRM_FIELD));
        setAliasConfirmFieldNo(LgNamesContent.get(_util, _cust, CONFIRM_FIELD_NO));
        setAliasTableAddSelect(LgNamesContent.get(_util, _cust, TABLE_ADD_SELECT));
        setAliasConfirmMessage(LgNamesContent.get(_util, _cust, CONFIRM_MESSAGE));
        setAliasTableAddHeader(LgNamesContent.get(_util, _cust, TABLE_ADD_HEADER));
        setAliasConfirmFull(LgNamesContent.get(_util, _cust, CONFIRM_FULL));
        setAliasDialogIsModal(LgNamesContent.get(_util, _cust, DIALOG_IS_MODAL));
        setAliasWindowType(LgNamesContent.get(_util, _cust, WINDOW_TYPE));
        setAliasTableAddInterval(LgNamesContent.get(_util, _cust, TABLE_ADD_INTERVAL));
        setAliasTableMoveColumn(LgNamesContent.get(_util, _cust, TABLE_MOVE_COLUMN));
        setAliasConfirmFieldCancel(LgNamesContent.get(_util, _cust, CONFIRM_FIELD_CANCEL));
        setAliasTextAreaSetSelectionStart(LgNamesContent.get(_util, _cust, TEXT_AREA_SET_SELECTION_START));
        setAliasTreeListener(LgNamesContent.get(_util, _cust, TREE_LISTENER));
        setAliasTreeNodeAdd(LgNamesContent.get(_util, _cust, TREE_NODE_ADD));
        setAliasTreeListenerValueChanged(LgNamesContent.get(_util, _cust, TREE_LISTENER_VALUE_CHANGED));
        setAliasTreeNodeInsert(LgNamesContent.get(_util, _cust, TREE_NODE_INSERT));
        setAliasActionEvent(LgNamesContent.get(_util, _cust, ACTION_EVENT));
        setAliasChangeListener(LgNamesContent.get(_util, _cust, CHANGE_LISTENER));
        setAliasActionListener(LgNamesContent.get(_util, _cust, ACTION_LISTENER));
        setAliasActionPerformed(LgNamesContent.get(_util, _cust, ACTION_PERFORMED));
        setAliasAddChange(LgNamesContent.get(_util, _cust, ADD_CHANGE));
        setAliasStateChanged(LgNamesContent.get(_util, _cust, STATE_CHANGED));
        setAliasTreeNodeGetPreviousSibling(LgNamesContent.get(_util, _cust, TREE_NODE_GET_PREVIOUS_SIBLING));
        setAliasComponentGetPreferredSize(LgNamesContent.get(_util, _cust, COMPONENT_GET_PREFERRED_SIZE));
        setAliasSplitPaneSetDividerLocation(LgNamesContent.get(_util, _cust, SPLIT_PANE_SET_DIVIDER_LOCATION));
        setAliasSplitPaneIsOneTouchExpandable(LgNamesContent.get(_util, _cust, SPLIT_PANE_IS_ONE_TOUCH_EXPANDABLE));
        setAliasSplitPaneSetOneTouchExpandable(LgNamesContent.get(_util, _cust, SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE));
        setAliasSplitPaneIsContinuousLayout(LgNamesContent.get(_util, _cust, SPLIT_PANE_IS_CONTINUOUS_LAYOUT));
        setAliasScrollPaneHorizontalValue(LgNamesContent.get(_util, _cust, SCROLL_PANE_HORIZONTAL_VALUE));
        setAliasTreeNodeRemoveAllChildren(LgNamesContent.get(_util, _cust, TREE_NODE_REMOVE_ALL_CHILDREN));
        setAliasPanelBorderBeforeLineBegins(LgNamesContent.get(_util, _cust, PANEL_BORDER_BEFORE_LINE_BEGINS));
        setAliasComponentSetPreferredSize(LgNamesContent.get(_util, _cust, COMPONENT_SET_PREFERRED_SIZE));
        setAliasSplitPaneGetDividerLocation(LgNamesContent.get(_util, _cust, SPLIT_PANE_GET_DIVIDER_LOCATION));
        setAliasSplitPaneSetContinuousLayout(LgNamesContent.get(_util, _cust, SPLIT_PANE_SET_CONTINUOUS_LAYOUT));
        setAliasMouseEventIsCtrl(LgNamesContent.get(_util, _cust, MOUSE_EVENT_IS_CTRL));
        setAliasMouseReleased(LgNamesContent.get(_util, _cust, MOUSE_RELEASED));
        setAliasMouseEventIsShift(LgNamesContent.get(_util, _cust, MOUSE_EVENT_IS_SHIFT));
        setAliasScrollPaneGetView(LgNamesContent.get(_util, _cust, SCROLL_PANE_GET_VIEW));
        setAliasPaintRefresh(LgNamesContent.get(_util, _cust, PAINT_REFRESH));
        setAliasPaintRefreshOne(LgNamesContent.get(_util, _cust, PAINT_REFRESH_ONE));
        setAliasMouseExited(LgNamesContent.get(_util, _cust, MOUSE_EXITED));
        setAliasSplitPane(LgNamesContent.get(_util, _cust, SPLIT_PANE));
        setAliasSplitPaneSetRight(LgNamesContent.get(_util, _cust, SPLIT_PANE_SET_RIGHT));
        setAliasSetVisible(LgNamesContent.get(_util, _cust, SET_VISIBLE));
        setAliasRemoveCompo(LgNamesContent.get(_util, _cust, REMOVE_COMPO));
        setAliasMouseClicked(LgNamesContent.get(_util, _cust, MOUSE_CLICKED));
        setAliasMouseMoved(LgNamesContent.get(_util, _cust, MOUSE_MOVED));
        setAliasMouseEvent(LgNamesContent.get(_util, _cust, MOUSE_EVENT));
        setAliasMouseEventGetClicks(LgNamesContent.get(_util, _cust, MOUSE_EVENT_GET_CLICKS));
        setAliasMouseEventIsAlt(LgNamesContent.get(_util, _cust, MOUSE_EVENT_IS_ALT));
        setAliasMouseEventGetSecond(LgNamesContent.get(_util, _cust, MOUSE_EVENT_GET_SECOND));
        setAliasScrollPane(LgNamesContent.get(_util, _cust, SCROLL_PANE));
        setAliasScrollPaneValidate(LgNamesContent.get(_util, _cust, SCROLL_PANE_VALIDATE));
        setAliasSplitPaneSetDividerSize(LgNamesContent.get(_util, _cust, SPLIT_PANE_SET_DIVIDER_SIZE));
        setAliasSplitPaneGetLeft(LgNamesContent.get(_util, _cust, SPLIT_PANE_GET_LEFT));
        setAliasSplitPaneGetDividerSize(LgNamesContent.get(_util, _cust, SPLIT_PANE_GET_DIVIDER_SIZE));
        setAliasSplitPaneValidate(LgNamesContent.get(_util, _cust, SPLIT_PANE_VALIDATE));
        setAliasMouseEntered(LgNamesContent.get(_util, _cust, MOUSE_ENTERED));
        setAliasScrollPaneSetView(LgNamesContent.get(_util, _cust, SCROLL_PANE_SET_VIEW));
        setAliasSplitPaneSetLeft(LgNamesContent.get(_util, _cust, SPLIT_PANE_SET_LEFT));
        setAliasMousePressed(LgNamesContent.get(_util, _cust, MOUSE_PRESSED));
        setAliasMouseListener(LgNamesContent.get(_util, _cust, MOUSE_LISTENER));
        setAliasIsVisible(LgNamesContent.get(_util, _cust, IS_VISIBLE));
        setAliasMouseDragged(LgNamesContent.get(_util, _cust, MOUSE_DRAGGED));
        setAliasScrollPaneVerticalValue(LgNamesContent.get(_util, _cust, SCROLL_PANE_VERTICAL_VALUE));
        setAliasSplitPaneGetRight(LgNamesContent.get(_util, _cust, SPLIT_PANE_GET_RIGHT));
        setAliasMouseEventGetFirst(LgNamesContent.get(_util, _cust, MOUSE_EVENT_GET_FIRST));
        setAliasGetIndexCompo(LgNamesContent.get(_util, _cust, GET_INDEX_COMPO));
        setAliasImageFillPolygon(LgNamesContent.get(_util, _cust, IMAGE_FILL_POLYGON));
        setAliasComponentIsVisible(LgNamesContent.get(_util, _cust, COMPONENT_IS_VISIBLE));
        setAliasImageDrawRect(LgNamesContent.get(_util, _cust, IMAGE_DRAW_RECT));
        setAliasComponentSetVisible(LgNamesContent.get(_util, _cust, COMPONENT_SET_VISIBLE));
        setAliasComponentSetSize(LgNamesContent.get(_util, _cust, COMPONENT_SET_SIZE));
        setAliasImageEq(LgNamesContent.get(_util, _cust, IMAGE_EQ));
        setAliasImageGetColor(LgNamesContent.get(_util, _cust, IMAGE_GET_COLOR));
        setAliasImageDrawOval(LgNamesContent.get(_util, _cust, IMAGE_DRAW_OVAL));
        setAliasComponentInvokeLater(LgNamesContent.get(_util, _cust, COMPONENT_INVOKE_LATER));
        setAliasImageGetHeight(LgNamesContent.get(_util, _cust, IMAGE_GET_HEIGHT));
        setAliasImageSetFont(LgNamesContent.get(_util, _cust, IMAGE_SET_FONT));
        setAliasImageDispose(LgNamesContent.get(_util, _cust, IMAGE_DISPOSE));
        setAliasImageGetFont(LgNamesContent.get(_util, _cust, IMAGE_GET_FONT));
        setAliasColorIsTransparent(LgNamesContent.get(_util, _cust, COLOR_IS_TRANSPARENT));
        setAliasImageDrawLine(LgNamesContent.get(_util, _cust, IMAGE_DRAW_LINE));
        setAliasImageFillRect(LgNamesContent.get(_util, _cust, IMAGE_FILL_RECT));
        setAliasColorBlue(LgNamesContent.get(_util, _cust, COLOR_BLUE));
        setAliasImageDrawPolygon(LgNamesContent.get(_util, _cust, IMAGE_DRAW_POLYGON));
        setAliasComponentRepaint(LgNamesContent.get(_util, _cust, COMPONENT_REPAINT));
        setAliasImageIsWithAlpha(LgNamesContent.get(_util, _cust, IMAGE_IS_WITH_ALPHA));
        setAliasImageGetWidth(LgNamesContent.get(_util, _cust, IMAGE_GET_WIDTH));
        setAliasColorGreen(LgNamesContent.get(_util, _cust, COLOR_GREEN));
        setAliasComponentSetPaint(LgNamesContent.get(_util, _cust, COMPONENT_SET_PAINT));
        setAliasRemoveAll(LgNamesContent.get(_util, _cust, REMOVE_ALL));
        setAliasImageFillOval(LgNamesContent.get(_util, _cust, IMAGE_FILL_OVAL));
        setAliasColorAlpha(LgNamesContent.get(_util, _cust, COLOR_ALPHA));
        setAliasImageDraw(LgNamesContent.get(_util, _cust, IMAGE_DRAW));
        setAliasImageSetColor(LgNamesContent.get(_util, _cust, IMAGE_SET_COLOR));
        setAliasSetContent(LgNamesContent.get(_util, _cust, SET_CONTENT));
        setAliasGetNextCompo(LgNamesContent.get(_util, _cust, GET_NEXT_COMPO));
        setAliasComponentGetPaint(LgNamesContent.get(_util, _cust, COMPONENT_GET_PAINT));
        setAliasGetParentCompo(LgNamesContent.get(_util, _cust, GET_PARENT_COMPO));
        setAliasSetLabelText(LgNamesContent.get(_util, _cust, SET_LABEL_TEXT));
        setAliasSetLabelImage(LgNamesContent.get(_util, _cust, SET_LABEL_IMAGE));
        setAliasTabbedPaneSetTitle(LgNamesContent.get(_util, _cust, TABBED_PANE_SET_TITLE));
        setAliasTabbedPaneAdd(LgNamesContent.get(_util, _cust, TABBED_PANE_ADD));
        setAliasTabbedPaneNb(LgNamesContent.get(_util, _cust, TABBED_PANE_NB));
        setAliasPanelFlow(LgNamesContent.get(_util, _cust, PANEL_FLOW));
        setAliasTabbedPaneRemove(LgNamesContent.get(_util, _cust, TABBED_PANE_REMOVE));
        setAliasAddWindowListener(LgNamesContent.get(_util, _cust, ADD_WINDOW_LISTENER));
        setAliasPaintMethod(LgNamesContent.get(_util, _cust, PAINT_METHOD));
        setAliasPanelPageBox(LgNamesContent.get(_util, _cust, PANEL_PAGE_BOX));
        setAliasPanelAbsolute(LgNamesContent.get(_util, _cust, PANEL_ABSOLUTE));
        setAliasPanelGrid(LgNamesContent.get(_util, _cust, PANEL_GRID));
        setAliasTabbedPaneGet(LgNamesContent.get(_util, _cust, TABBED_PANE_GET));
        setAliasTabbedPane(LgNamesContent.get(_util, _cust, TABBED_PANE));
        setAliasTabbedPaneGetTitle(LgNamesContent.get(_util, _cust, TABBED_PANE_GET_TITLE));
        setAliasPanelValidate(LgNamesContent.get(_util, _cust, PANEL_VALIDATE));
        setAliasTabbedPaneIndex(LgNamesContent.get(_util, _cust, TABBED_PANE_INDEX));
        setAliasTabbedPaneSet(LgNamesContent.get(_util, _cust, TABBED_PANE_SET));
        setAliasTabbedPaneSelIndex(LgNamesContent.get(_util, _cust, TABBED_PANE_SEL_INDEX));
        setAliasAddListener(LgNamesContent.get(_util, _cust, ADD_LISTENER));
        setAliasRemoveListener(LgNamesContent.get(_util, _cust, REMOVE_LISTENER));
        setAliasGetListeners(LgNamesContent.get(_util, _cust, GET_LISTENERS));
        setAliasMenuItemCheck(LgNamesContent.get(_util, _cust, MENU_ITEM_CHECK));
        setAliasMenuAddSeparator(LgNamesContent.get(_util, _cust, MENU_ADD_SEPARATOR));
        setAliasAbsMenuItem(LgNamesContent.get(_util, _cust, ABS_MENU_ITEM));
        setAliasMenuItemCheckIsSelected(LgNamesContent.get(_util, _cust, MENU_ITEM_CHECK_IS_SELECTED));
        setAliasAbsMenuSetEnabled(LgNamesContent.get(_util, _cust, ABS_MENU_SET_ENABLED));
        setAliasMenuRemove(LgNamesContent.get(_util, _cust, MENU_REMOVE));
        setAliasAbsMenuItemAddAction(LgNamesContent.get(_util, _cust, ABS_MENU_ITEM_ADD_ACTION));
        setAliasAbsMenuSetText(LgNamesContent.get(_util, _cust, ABS_MENU_SET_TEXT));
        setAliasAbsMenuGetText(LgNamesContent.get(_util, _cust, ABS_MENU_GET_TEXT));
        setAliasMenuItemCheckSetSelected(LgNamesContent.get(_util, _cust, MENU_ITEM_CHECK_SET_SELECTED));
        setAliasAbsMenuSetDeepEnabled(LgNamesContent.get(_util, _cust, ABS_MENU_SET_DEEP_ENABLED));
        setAliasCompToolTip(LgNamesContent.get(_util, _cust, COMP_TOOL_TIP));
        setAliasWindowClosed(LgNamesContent.get(_util, _cust, WINDOW_CLOSED));
        setAliasMouseEventIsRight(LgNamesContent.get(_util, _cust, MOUSE_EVENT_IS_RIGHT));
        setAliasKeyPressed(LgNamesContent.get(_util, _cust, KEY_PRESSED));
        setAliasMouseEventIsLeft(LgNamesContent.get(_util, _cust, MOUSE_EVENT_IS_LEFT));
        setAliasWheelListener(LgNamesContent.get(_util, _cust, WHEEL_LISTENER));
        setAliasCompBorLine(LgNamesContent.get(_util, _cust, COMP_BOR_LINE));
        setAliasKeyEventCode(LgNamesContent.get(_util, _cust, KEY_EVENT_CODE));
        setAliasRequestFocus(LgNamesContent.get(_util, _cust, REQUEST_FOCUS));
        setAliasCompBorRaise(LgNamesContent.get(_util, _cust, COMP_BOR_RAISE));
        setAliasWheelEvent(LgNamesContent.get(_util, _cust, WHEEL_EVENT));
        setAliasKeyEventIsShift(LgNamesContent.get(_util, _cust, KEY_EVENT_IS_SHIFT));
        setAliasCompGetFirstPos(LgNamesContent.get(_util, _cust, COMP_GET_FIRST_POS));
        setAliasCompBorTitle(LgNamesContent.get(_util, _cust, COMP_BOR_TITLE));
        setAliasAddKeyListener(LgNamesContent.get(_util, _cust, ADD_KEY_LISTENER));
        setAliasRemoveKeyListener(LgNamesContent.get(_util, _cust, REMOVE_KEY_LISTENER));
        setAliasGetKeyListeners(LgNamesContent.get(_util, _cust, GET_KEY_LISTENERS));
        setAliasKeyEventIsAlt(LgNamesContent.get(_util, _cust, KEY_EVENT_IS_ALT));
        setAliasKeyListener(LgNamesContent.get(_util, _cust, KEY_LISTENER));
        setAliasKeyEventChar(LgNamesContent.get(_util, _cust, KEY_EVENT_CHAR));
        setAliasWindowListener(LgNamesContent.get(_util, _cust, WINDOW_LISTENER));
        setAliasWheelMove(LgNamesContent.get(_util, _cust, WHEEL_MOVE));
        setAliasCompGetSecondPos(LgNamesContent.get(_util, _cust, COMP_GET_SECOND_POS));
        setAliasKeyEventIsCtrl(LgNamesContent.get(_util, _cust, KEY_EVENT_IS_CTRL));
        setAliasMouseEventIsMiddle(LgNamesContent.get(_util, _cust, MOUSE_EVENT_IS_MIDDLE));
        setAliasWheelRotatedClicks(LgNamesContent.get(_util, _cust, WHEEL_ROTATED_CLICKS));
        setAliasCompBorLower(LgNamesContent.get(_util, _cust, COMP_BOR_LOWER));
        setAliasWindowOpened(LgNamesContent.get(_util, _cust, WINDOW_OPENED));
        setAliasCompFocusable(LgNamesContent.get(_util, _cust, COMP_FOCUSABLE));
        setAliasCompOpaque(LgNamesContent.get(_util, _cust, COMP_OPAQUE));
        setAliasWindowIconified(LgNamesContent.get(_util, _cust, WINDOW_ICONIFIED));
        setAliasKeyReleased(LgNamesContent.get(_util, _cust, KEY_RELEASED));
        setAliasAddWheelListener(LgNamesContent.get(_util, _cust, ADD_WHEEL_LISTENER));
        setAliasRemoveWheelListener(LgNamesContent.get(_util, _cust, REMOVE_WHEEL_LISTENER));
        setAliasGetWheelListeners(LgNamesContent.get(_util, _cust, GET_WHEEL_LISTENERS));
        setAliasWindowClosing(LgNamesContent.get(_util, _cust, WINDOW_CLOSING));
        setAliasGrListSet(LgNamesContent.get(_util, _cust, GR_LIST_SET));
        setAliasWindowActivated(LgNamesContent.get(_util, _cust, WINDOW_ACTIVATED));
        setAliasWindowDeactivated(LgNamesContent.get(_util, _cust, WINDOW_DEACTIVATED));
        setAliasRenderSetHeight(LgNamesContent.get(_util, _cust, RENDER_SET_HEIGHT));
        setAliasListSelection(LgNamesContent.get(_util, _cust, LIST_SELECTION));
        setAliasRenderGetWidth(LgNamesContent.get(_util, _cust, RENDER_GET_WIDTH));
        setAliasRenderGetHeight(LgNamesContent.get(_util, _cust, RENDER_GET_HEIGHT));
        setAliasGrListGetSelectedIndexes(LgNamesContent.get(_util, _cust, GR_LIST_GET_SELECTED_INDEXES));
        setAliasGrListGetSelections(LgNamesContent.get(_util, _cust, GR_LIST_GET_SELECTIONS));
        setAliasRenderSetWidth(LgNamesContent.get(_util, _cust, RENDER_SET_WIDTH));
        setAliasInputIsEnabled(LgNamesContent.get(_util, _cust, INPUT_IS_ENABLED));
        setAliasGrListUpdateGraphics(LgNamesContent.get(_util, _cust, GR_LIST_UPDATE_GRAPHICS));
        setAliasGrListGetVisibleRowCount(LgNamesContent.get(_util, _cust, GR_LIST_GET_VISIBLE_ROW_COUNT));
        setAliasGrListClearSelection(LgNamesContent.get(_util, _cust, GR_LIST_CLEAR_SELECTION));
        setAliasRenderGetPaint(LgNamesContent.get(_util, _cust, RENDER_GET_PAINT));
        setAliasWindowEvent(LgNamesContent.get(_util, _cust, WINDOW_EVENT));
        setAliasGrListClear(LgNamesContent.get(_util, _cust, GR_LIST_CLEAR));
        setAliasGrListSetRender(LgNamesContent.get(_util, _cust, GR_LIST_SET_RENDER));
        setAliasGrListAddSelection(LgNamesContent.get(_util, _cust, GR_LIST_ADD_SELECTION));
        setAliasGrListRemoveSelection(LgNamesContent.get(_util, _cust, GR_LIST_REMOVE_SELECTION));
        setAliasGrListRemove(LgNamesContent.get(_util, _cust, GR_LIST_REMOVE));
        setAliasInputSetEnabled(LgNamesContent.get(_util, _cust, INPUT_SET_ENABLED));
        setAliasWindowDeiconified(LgNamesContent.get(_util, _cust, WINDOW_DEICONIFIED));
        setAliasValueChanged(LgNamesContent.get(_util, _cust, VALUE_CHANGED));
        setAliasGrListGetListView(LgNamesContent.get(_util, _cust, GR_LIST_GET_LIST_VIEW));
        setAliasRenderSetPaint(LgNamesContent.get(_util, _cust, RENDER_SET_PAINT));
        setAliasGrListAdd(LgNamesContent.get(_util, _cust, GR_LIST_ADD));
        setAliasGrListGetRender(LgNamesContent.get(_util, _cust, GR_LIST_GET_RENDER));
        setAliasGrListSetSelectedIndexes(LgNamesContent.get(_util, _cust, GR_LIST_SET_SELECTED_INDEXES));
        setAliasGrListSetVisibleRowCount(LgNamesContent.get(_util, _cust, GR_LIST_SET_VISIBLE_ROW_COUNT));
        setAliasComboGetSelectedItem(LgNamesContent.get(_util, _cust, COMBO_GET_SELECTED_ITEM));
        setAliasComboAddItem(LgNamesContent.get(_util, _cust, COMBO_ADD_ITEM));
        setAliasPopupMenuGetComp(LgNamesContent.get(_util, _cust, POPUP_MENU_GET_COMP));
        setAliasComboGetListeners(LgNamesContent.get(_util, _cust, COMBO_GET_LISTENERS));
        setAliasComboGetSelectedIndex(LgNamesContent.get(_util, _cust, COMBO_GET_SELECTED_INDEX));
        setAliasPopupMenu(LgNamesContent.get(_util, _cust, POPUP_MENU));
        setAliasPopupMenuAddMenu(LgNamesContent.get(_util, _cust, POPUP_MENU_ADD_MENU));
        setAliasPopupMenuNbMenu(LgNamesContent.get(_util, _cust, POPUP_MENU_NB_MENU));
        setAliasTextFieldAddPopup(LgNamesContent.get(_util, _cust, TEXT_FIELD_ADD_POPUP));
        setAliasPopupMenuNbComp(LgNamesContent.get(_util, _cust, POPUP_MENU_NB_COMP));
        setAliasComboRemoveAllItems(LgNamesContent.get(_util, _cust, COMBO_REMOVE_ALL_ITEMS));
        setAliasPopupMenuShow(LgNamesContent.get(_util, _cust, POPUP_MENU_SHOW));
        setAliasPopupMenuGetMenu(LgNamesContent.get(_util, _cust, POPUP_MENU_GET_MENU));
        setAliasTextFieldAuto(LgNamesContent.get(_util, _cust, TEXT_FIELD_AUTO));
        setAliasRadioGetText(LgNamesContent.get(_util, _cust, RADIO_GET_TEXT));
        setAliasButtonGroupAdd(LgNamesContent.get(_util, _cust, BUTTON_GROUP_ADD));
        setAliasButtonGroup(LgNamesContent.get(_util, _cust, BUTTON_GROUP));
        setAliasPopupMenuAdd(LgNamesContent.get(_util, _cust, POPUP_MENU_ADD));
        setAliasRadioIsSelected(LgNamesContent.get(_util, _cust, RADIO_IS_SELECTED));
        setAliasComboAddListener(LgNamesContent.get(_util, _cust, COMBO_ADD_LISTENER));
        setAliasComboRemoveListener(LgNamesContent.get(_util, _cust, COMBO_REMOVE_LISTENER));
        setAliasTextField(LgNamesContent.get(_util, _cust, TEXT_FIELD));
        setAliasComboRemoveItem(LgNamesContent.get(_util, _cust, COMBO_REMOVE_ITEM));
        setAliasRadioSetSelected(LgNamesContent.get(_util, _cust, RADIO_SET_SELECTED));
        setAliasTextFieldGetText(LgNamesContent.get(_util, _cust, TEXT_FIELD_GET_TEXT));
        setAliasPopupMenuRemoveMenu(LgNamesContent.get(_util, _cust, POPUP_MENU_REMOVE_MENU));
        setAliasTextFieldAddDocument(LgNamesContent.get(_util, _cust, TEXT_FIELD_ADD_DOCUMENT));
        setAliasComboGetSelectedIndexes(LgNamesContent.get(_util, _cust, COMBO_GET_SELECTED_INDEXES));
        setAliasTextFieldAddAction(LgNamesContent.get(_util, _cust, TEXT_FIELD_ADD_ACTION));
        setAliasTextAreaGetText(LgNamesContent.get(_util, _cust, TEXT_AREA_GET_TEXT));
        setAliasRadioSetText(LgNamesContent.get(_util, _cust, RADIO_SET_TEXT));
        setAliasComboSelectItem(LgNamesContent.get(_util, _cust, COMBO_SELECT_ITEM));
        setAliasComboGetItemCount(LgNamesContent.get(_util, _cust, COMBO_GET_ITEM_COUNT));
        setAliasPopupMenuRemoveComp(LgNamesContent.get(_util, _cust, POPUP_MENU_REMOVE_COMP));
        setAliasTextFieldSetText(LgNamesContent.get(_util, _cust, TEXT_FIELD_SET_TEXT));
        setAliasCheckBoxSetSelected(LgNamesContent.get(_util, _cust, CHECK_BOX_SET_SELECTED));
        setAliasCheckBoxAddAction(LgNamesContent.get(_util, _cust, CHECK_BOX_ADD_ACTION));
        setAliasTextAreaSetSelectionEnd(LgNamesContent.get(_util, _cust, TEXT_AREA_SET_SELECTION_END));
        setAliasSpinnerGetValue(LgNamesContent.get(_util, _cust, SPINNER_GET_VALUE));
        setAliasSliderGetMax(LgNamesContent.get(_util, _cust, SLIDER_GET_MAX));
        setAliasTextAreaSelect(LgNamesContent.get(_util, _cust, TEXT_AREA_SELECT));
        setAliasTextAreaReplaceRange(LgNamesContent.get(_util, _cust, TEXT_AREA_REPLACE_RANGE));
        setAliasSpinnerSetStep(LgNamesContent.get(_util, _cust, SPINNER_SET_STEP));
        setAliasCheckBoxIsSelected(LgNamesContent.get(_util, _cust, CHECK_BOX_IS_SELECTED));
        setAliasTextAreaReplaceSelection(LgNamesContent.get(_util, _cust, TEXT_AREA_REPLACE_SELECTION));
        setAliasSpinnerSetValue(LgNamesContent.get(_util, _cust, SPINNER_SET_VALUE));
        setAliasSliderGetValue(LgNamesContent.get(_util, _cust, SLIDER_GET_VALUE));
        setAliasSliderSetValue(LgNamesContent.get(_util, _cust, SLIDER_SET_VALUE));
        setAliasSliderGetMin(LgNamesContent.get(_util, _cust, SLIDER_GET_MIN));
        setAliasSliderSetMin(LgNamesContent.get(_util, _cust, SLIDER_SET_MIN));
        setAliasTextAreaSetTabSize(LgNamesContent.get(_util, _cust, TEXT_AREA_SET_TAB_SIZE));
        setAliasSpinnerGetMin(LgNamesContent.get(_util, _cust, SPINNER_GET_MIN));
        setAliasCheckBoxSetText(LgNamesContent.get(_util, _cust, CHECK_BOX_SET_TEXT));
        setAliasCheckBoxGetText(LgNamesContent.get(_util, _cust, CHECK_BOX_GET_TEXT));
        setAliasTextAreaInsert(LgNamesContent.get(_util, _cust, TEXT_AREA_INSERT));
        setAliasTextAreaSelectAll(LgNamesContent.get(_util, _cust, TEXT_AREA_SELECT_ALL));
        setAliasTextAreaAppend(LgNamesContent.get(_util, _cust, TEXT_AREA_APPEND));
        setAliasSpinnerGetMax(LgNamesContent.get(_util, _cust, SPINNER_GET_MAX));
        setAliasSpinnerSetMax(LgNamesContent.get(_util, _cust, SPINNER_SET_MAX));
        setAliasTextAreaGetTabSize(LgNamesContent.get(_util, _cust, TEXT_AREA_GET_TAB_SIZE));
        setAliasTextAreaSetText(LgNamesContent.get(_util, _cust, TEXT_AREA_SET_TEXT));
        setAliasSpinnerSetMin(LgNamesContent.get(_util, _cust, SPINNER_SET_MIN));
        setAliasSpinnerGetStep(LgNamesContent.get(_util, _cust, SPINNER_GET_STEP));
        setAliasSpinnerSetRangeValue(LgNamesContent.get(_util, _cust, SPINNER_SET_RANGE_VALUE));
        setAliasTextAreaGetSelectedText(LgNamesContent.get(_util, _cust, TEXT_AREA_GET_SELECTED_TEXT));
        setAliasSliderSetMax(LgNamesContent.get(_util, _cust, SLIDER_SET_MAX));
        setAliasSpinnerSetRange(LgNamesContent.get(_util, _cust, SPINNER_SET_RANGE));
        setAliasMenuBarGet(LgNamesContent.get(_util, _cust, MENU_BAR_GET));
        setAliasMenuBarRemove(LgNamesContent.get(_util, _cust, MENU_BAR_REMOVE));
        setAliasAbsMenuGetParent(LgNamesContent.get(_util, _cust, ABS_MENU_GET_PARENT));
        setAliasAbsMenuIsEnabled(LgNamesContent.get(_util, _cust, ABS_MENU_IS_ENABLED));
        setAliasMenuBarAdd(LgNamesContent.get(_util, _cust, MENU_BAR_ADD));
        setAliasGetMenuBar(LgNamesContent.get(_util, _cust, GET_MENU_BAR));
        setAliasSliderGetOrientation(LgNamesContent.get(_util, _cust, SLIDER_GET_ORIENTATION));
        setAliasMenuBarNb(LgNamesContent.get(_util, _cust, MENU_BAR_NB));
        setAliasSetMenuBar(LgNamesContent.get(_util, _cust, SET_MENU_BAR));
        setAliasSliderSetOrientation(LgNamesContent.get(_util, _cust, SLIDER_SET_ORIENTATION));
        setAliasMenuBar(LgNamesContent.get(_util, _cust, MENU_BAR));
        setAliasMenuNb(LgNamesContent.get(_util, _cust, MENU_NB));
        setAliasMenuAdd(LgNamesContent.get(_util, _cust, MENU_ADD));
        setAliasMenu(LgNamesContent.get(_util, _cust, MENU));
        setAliasMenuItem(LgNamesContent.get(_util, _cust, MENU_ITEM));
        setAliasAbsMenu(LgNamesContent.get(_util, _cust, ABS_MENU));
        setAliasMenuGet(LgNamesContent.get(_util, _cust, MENU_GET));
        guiAliasParameters.build(_util, _cust);
    }

    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames(CustAliases _custAliases,LgNamesContent _content) {
        CustList<CustList<KeyValueMemberName>> list_ = new CustList<CustList<KeyValueMemberName>>();
        list_.add(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(LgNames.ITERATOR,_content.getPredefTypes().getAliasIterator()),
                new KeyValueMemberName(LgNames.HAS_NEXT,_content.getPredefTypes().getAliasHasNext()),
                new KeyValueMemberName(LgNames.NEXT,_content.getPredefTypes().getAliasNext()),
                new KeyValueMemberName(LgNames.ITERATOR_TABLE,_content.getPredefTypes().getAliasIteratorTable()),
                new KeyValueMemberName(LgNames.HAS_NEXT_PAIR,_content.getPredefTypes().getAliasHasNextPair()),
                new KeyValueMemberName(LgNames.NEXT_PAIR,_content.getPredefTypes().getAliasNextPair()),
                new KeyValueMemberName(LgNames.GET_FIRST,_content.getPredefTypes().getAliasGetFirst()),
                new KeyValueMemberName(LgNames.GET_SECOND,_content.getPredefTypes().getAliasGetSecond()),
                new KeyValueMemberName(LgNames.ENUM_ORDINAL,_content.getPredefTypes().getAliasEnumOrdinal()),
                new KeyValueMemberName(LgNames.ENUM_NAME,_content.getPredefTypes().getAliasEnumName()),
                new KeyValueMemberName(LgNames.SEED_GET,_content.getPredefTypes().getAliasSeedGet()),
                new KeyValueMemberName(CustAliases.RUN,_custAliases.getAliasRun()),
                new KeyValueMemberName(ACTION_PERFORMED,getAliasActionPerformed()),
                new KeyValueMemberName(STATE_CHANGED,getAliasStateChanged()),
                new KeyValueMemberName(KEY_TYPED,getAliasKeyTyped()),
                new KeyValueMemberName(KEY_PRESSED,getAliasKeyPressed()),
                new KeyValueMemberName(KEY_RELEASED,getAliasKeyReleased()),
                new KeyValueMemberName(VALUE_CHANGED,getAliasValueChanged()),
                new KeyValueMemberName(TABLE_VALUE_TABLE_CHANGED,getAliasTableValueTableChanged()),
                new KeyValueMemberName(MOUSE_CLICKED,getAliasMouseClicked()),
                new KeyValueMemberName(MOUSE_ENTERED,getAliasMouseEntered()),
                new KeyValueMemberName(MOUSE_EXITED,getAliasMouseExited()),
                new KeyValueMemberName(MOUSE_PRESSED,getAliasMousePressed()),
                new KeyValueMemberName(MOUSE_RELEASED,getAliasMouseReleased()),
                new KeyValueMemberName(MOUSE_DRAGGED,getAliasMouseDragged()),
                new KeyValueMemberName(MOUSE_MOVED,getAliasMouseMoved()),
                new KeyValueMemberName(TREE_LISTENER_VALUE_CHANGED,getAliasTreeListenerValueChanged()),
                new KeyValueMemberName(WHEEL_MOVE,getAliasWheelMove()),
                new KeyValueMemberName(WINDOW_ACTIVATED,getAliasWindowActivated()),
                new KeyValueMemberName(WINDOW_CLOSED,getAliasWindowClosed()),
                new KeyValueMemberName(WINDOW_CLOSING,getAliasWindowClosing()),
                new KeyValueMemberName(WINDOW_DEACTIVATED,getAliasWindowDeactivated()),
                new KeyValueMemberName(WINDOW_DEICONIFIED,getAliasWindowDeiconified()),
                new KeyValueMemberName(WINDOW_ICONIFIED,getAliasWindowIconified()),
                new KeyValueMemberName(WINDOW_OPENED,getAliasWindowOpened())
        ));
        return list_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> f_ = new StringMap<CustList<KeyValueMemberName>>();
        f_.addEntry(getAliasConfirm(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CONFIRM_FIELD_OK,getAliasConfirmFieldOk()),
                new KeyValueMemberName(CONFIRM_FIELD_YES,getAliasConfirmFieldYes()),
                new KeyValueMemberName(CONFIRM_FIELD_NO,getAliasConfirmFieldNo()),
                new KeyValueMemberName(CONFIRM_FIELD_CANCEL,getAliasConfirmFieldCancel())
        ));
        f_.addEntry(getAliasPanelBorder(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PANEL_BORDER_AFTER_LAST,getAliasPanelBorderAfterLast()),
                new KeyValueMemberName(PANEL_BORDER_AFTER_LINE_ENDS,getAliasPanelBorderAfterLineEnds()),
                new KeyValueMemberName(PANEL_BORDER_BEFORE_FIRST,getAliasPanelBorderBeforeFirst()),
                new KeyValueMemberName(PANEL_BORDER_BEFORE_LINE_BEGINS,getAliasPanelBorderBeforeLineBegins()),
                new KeyValueMemberName(PANEL_BORDER_EAST,getAliasPanelBorderEast()),
                new KeyValueMemberName(PANEL_BORDER_WEST,getAliasPanelBorderWest()),
                new KeyValueMemberName(PANEL_BORDER_NORTH,getAliasPanelBorderNorth()),
                new KeyValueMemberName(PANEL_BORDER_SOUTH,getAliasPanelBorderSouth()),
                new KeyValueMemberName(PANEL_BORDER_CENTER,getAliasPanelBorderCenter())
        ));
        return f_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> m_ = new StringMap<CustList<KeyValueMemberName>>();
        allWindowMethods(m_);
        allEventTypes(m_);
        allComponents(m_);
        m_.addEntry(getAliasDimension(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(DIMENSION_GET_HEIGHT,getAliasDimensionGetHeight()),
                new KeyValueMemberName(DIMENSION_GET_WIDTH,getAliasDimensionGetWidth())));
        m_.addEntry(getAliasFont(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FONT_GET_NAME,getAliasFontGetName()),
                new KeyValueMemberName(FONT_GET_SIZE,getAliasFontGetSize()),
                new KeyValueMemberName(FONT_IS_BOLD,getAliasFontIsBold()),
                new KeyValueMemberName(FONT_IS_ITALIC,getAliasFontIsItalic()),
                new KeyValueMemberName(FONT_STRING_WIDTH,getAliasFontStringWidth())));
        m_.addEntry(getAliasButtonGroup(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BUTTON_GROUP_ADD,getAliasButtonGroupAdd()))
        );
        m_.addEntry(getAliasRender(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(RENDER_GET_HEIGHT,getAliasRenderGetHeight()),
                new KeyValueMemberName(RENDER_GET_PAINT,getAliasRenderGetPaint()),
                new KeyValueMemberName(RENDER_GET_WIDTH,getAliasRenderGetWidth()),
                new KeyValueMemberName(RENDER_SET_HEIGHT,getAliasRenderSetHeight()),
                new KeyValueMemberName(RENDER_SET_PAINT,getAliasRenderSetPaint()),
                new KeyValueMemberName(RENDER_SET_WIDTH,getAliasRenderSetWidth())
        ));
        m_.addEntry(getAliasColor(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(COLOR_ALPHA,getAliasColorAlpha()),
                new KeyValueMemberName(COLOR_BLUE,getAliasColorBlue()),
                new KeyValueMemberName(COLOR_RED,getAliasColorRed()),
                new KeyValueMemberName(COLOR_GREEN,getAliasColorGreen()),
                new KeyValueMemberName(COLOR_IS_TRANSPARENT,getAliasColorIsTransparent()))
        );
        m_.addEntry(getAliasImage(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(IMAGE_DRAW,getAliasImageDraw()),
                new KeyValueMemberName(IMAGE_DRAW_LINE,getAliasImageDrawLine()),
                new KeyValueMemberName(IMAGE_DRAW_OVAL,getAliasImageDrawOval()),
                new KeyValueMemberName(IMAGE_DRAW_POLYGON,getAliasImageDrawPolygon()),
                new KeyValueMemberName(IMAGE_DRAW_RECT,getAliasImageDrawRect()),
                new KeyValueMemberName(IMAGE_FILL_OVAL,getAliasImageFillOval()),
                new KeyValueMemberName(IMAGE_FILL_POLYGON,getAliasImageFillPolygon()),
                new KeyValueMemberName(IMAGE_FILL_RECT,getAliasImageFillRect()),
                new KeyValueMemberName(IMAGE_GET,getAliasImageGet()),
                new KeyValueMemberName(IMAGE_SET,getAliasImageSet()),
                new KeyValueMemberName(IMAGE_EQ,getAliasImageEq()),
                new KeyValueMemberName(IMAGE_GET_COLOR,getAliasImageGetColor()),
                new KeyValueMemberName(IMAGE_SET_COLOR,getAliasImageSetColor()),
                new KeyValueMemberName(IMAGE_GET_FONT,getAliasImageGetFont()),
                new KeyValueMemberName(IMAGE_SET_FONT,getAliasImageSetFont()),
                new KeyValueMemberName(IMAGE_GET_HEIGHT,getAliasImageGetHeight()),
                new KeyValueMemberName(IMAGE_GET_WIDTH,getAliasImageGetWidth()),
                new KeyValueMemberName(IMAGE_IS_WITH_ALPHA,getAliasImageIsWithAlpha()),
                new KeyValueMemberName(IMAGE_DISPOSE,getAliasImageDispose()))
        );
        m_.addEntry(getAliasActionListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ACTION_PERFORMED,getAliasActionPerformed()))
        );
        m_.addEntry(getAliasChangeListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(STATE_CHANGED,getAliasStateChanged()))
        );
        m_.addEntry(getAliasWheelListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(WHEEL_MOVE,getAliasWheelMove()))
        );
        m_.addEntry(getAliasMouseListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(MOUSE_CLICKED,getAliasMouseClicked()),
                new KeyValueMemberName(MOUSE_ENTERED,getAliasMouseEntered()),
                new KeyValueMemberName(MOUSE_EXITED,getAliasMouseExited()),
                new KeyValueMemberName(MOUSE_PRESSED,getAliasMousePressed()),
                new KeyValueMemberName(MOUSE_RELEASED,getAliasMouseReleased()))
        );
        m_.addEntry(getAliasKeyListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(KEY_TYPED,getAliasKeyTyped()),
                new KeyValueMemberName(KEY_RELEASED,getAliasKeyReleased()),
                new KeyValueMemberName(KEY_PRESSED,getAliasKeyPressed()))
        );
        m_.addEntry(getAliasWindowListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(WINDOW_ACTIVATED,getAliasWindowActivated()),
                new KeyValueMemberName(WINDOW_ICONIFIED,getAliasWindowIconified()),
                new KeyValueMemberName(WINDOW_DEACTIVATED,getAliasWindowDeactivated()),
                new KeyValueMemberName(WINDOW_DEICONIFIED,getAliasWindowDeiconified()),
                new KeyValueMemberName(WINDOW_OPENED,getAliasWindowOpened()),
                new KeyValueMemberName(WINDOW_CLOSING,getAliasWindowClosing()),
                new KeyValueMemberName(WINDOW_CLOSED,getAliasWindowClosed()))
        );
        m_.addEntry(getAliasListSelection(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(VALUE_CHANGED,getAliasValueChanged()))
        );
        m_.addEntry(getAliasTreeListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TREE_LISTENER_VALUE_CHANGED,getAliasTreeListenerValueChanged()))
        );
        m_.addEntry(getAliasTreeNode(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TREE_NODE_ADD,getAliasTreeNodeAdd()),
                new KeyValueMemberName(TREE_NODE_INSERT,getAliasTreeNodeInsert()),
                new KeyValueMemberName(TREE_NODE_REMOVE,getAliasTreeNodeRemove()),
                new KeyValueMemberName(TREE_NODE_REMOVE_FROM_PARENT,getAliasTreeNodeRemoveFromParent()),
                new KeyValueMemberName(TREE_NODE_REMOVE_ALL_CHILDREN,getAliasTreeNodeRemoveAllChildren()),
                new KeyValueMemberName(TREE_NODE_SET_USER_OBJECT,getAliasTreeNodeSetUserObject()),
                new KeyValueMemberName(TREE_NODE_GET_USER_OBJECT,getAliasTreeNodeGetUserObject()),
                new KeyValueMemberName(TREE_NODE_EQ,getAliasTreeNodeEq()),
                new KeyValueMemberName(TREE_NODE_NB,getAliasTreeNodeNb()),
                new KeyValueMemberName(TREE_NODE_GET_FIRST_CHILD,getAliasTreeNodeGetFirstChild()),
                new KeyValueMemberName(TREE_NODE_GET_LAST_CHILD,getAliasTreeNodeGetLastChild()),
                new KeyValueMemberName(TREE_NODE_GET_NEXT_SIBLING,getAliasTreeNodeGetNextSibling()),
                new KeyValueMemberName(TREE_NODE_GET_PREVIOUS_SIBLING,getAliasTreeNodeGetPreviousSibling()),
                new KeyValueMemberName(TREE_NODE_GET_PARENT_NODE,getAliasTreeNodeGetParentNode()),
                new KeyValueMemberName(TREE_NODE_IS_ANCESTOR,getAliasTreeNodeIsAncestor()),
                new KeyValueMemberName(TREE_NODE_IS_DESCENDANT,getAliasTreeNodeIsDescendant()))
        );
        m_.addEntry(getAliasTableListener(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TABLE_VALUE_TABLE_CHANGED,getAliasTableValueTableChanged()))
        );
        m_.addEntry(getAliasPaint(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PAINT_METHOD,getAliasPaintMethod()),
                new KeyValueMemberName(PAINT_ADD,getAliasPaintAdd()),
                new KeyValueMemberName(PAINT_SET,getAliasPaintSet()),
                new KeyValueMemberName(PAINT_REFRESH,getAliasPaintRefresh()),
                new KeyValueMemberName(PAINT_REFRESH_ONE,getAliasPaintRefreshOne()))
        );
        m_.addEntry(getAliasMenuBar(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(MENU_BAR_ADD,getAliasMenuBarAdd()),
                new KeyValueMemberName(MENU_BAR_GET,getAliasMenuBarGet()),
                new KeyValueMemberName(MENU_BAR_REMOVE,getAliasMenuBarRemove()),
                new KeyValueMemberName(MENU_BAR_NB,getAliasMenuBarNb())
        ));
        m_.addEntry(getAliasAbsMenu(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ABS_MENU_GET_PARENT,getAliasAbsMenuGetParent()),
                new KeyValueMemberName(ABS_MENU_GET_TEXT,getAliasAbsMenuGetText()),
                new KeyValueMemberName(ABS_MENU_IS_ENABLED,getAliasAbsMenuIsEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_DEEP_ENABLED,getAliasAbsMenuSetDeepEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_ENABLED,getAliasAbsMenuSetEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_TEXT,getAliasAbsMenuSetText())
        ));
        m_.addEntry(getAliasMenu(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ABS_MENU_GET_PARENT,getAliasAbsMenuGetParent()),
                new KeyValueMemberName(ABS_MENU_GET_TEXT,getAliasAbsMenuGetText()),
                new KeyValueMemberName(ABS_MENU_IS_ENABLED,getAliasAbsMenuIsEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_DEEP_ENABLED,getAliasAbsMenuSetDeepEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_ENABLED,getAliasAbsMenuSetEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_TEXT,getAliasAbsMenuSetText()),
                new KeyValueMemberName(MENU_ADD,getAliasMenuAdd()),
                new KeyValueMemberName(MENU_GET,getAliasMenuGet()),
                new KeyValueMemberName(MENU_REMOVE,getAliasMenuRemove()),
                new KeyValueMemberName(MENU_NB,getAliasMenuNb()),
                new KeyValueMemberName(MENU_ADD_SEPARATOR,getAliasMenuAddSeparator())
        ));
        m_.addEntry(getAliasAbsMenuItem(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ABS_MENU_GET_PARENT,getAliasAbsMenuGetParent()),
                new KeyValueMemberName(ABS_MENU_GET_TEXT,getAliasAbsMenuGetText()),
                new KeyValueMemberName(ABS_MENU_IS_ENABLED,getAliasAbsMenuIsEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_DEEP_ENABLED,getAliasAbsMenuSetDeepEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_ENABLED,getAliasAbsMenuSetEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_TEXT,getAliasAbsMenuSetText()),
                new KeyValueMemberName(ABS_MENU_ITEM_ADD_ACTION,getAliasAbsMenuItemAddAction())
        ));
        m_.addEntry(getAliasMenuItemCheck(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ABS_MENU_GET_PARENT,getAliasAbsMenuGetParent()),
                new KeyValueMemberName(ABS_MENU_GET_TEXT,getAliasAbsMenuGetText()),
                new KeyValueMemberName(ABS_MENU_IS_ENABLED,getAliasAbsMenuIsEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_DEEP_ENABLED,getAliasAbsMenuSetDeepEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_ENABLED,getAliasAbsMenuSetEnabled()),
                new KeyValueMemberName(ABS_MENU_SET_TEXT,getAliasAbsMenuSetText()),
                new KeyValueMemberName(ABS_MENU_ITEM_ADD_ACTION,getAliasAbsMenuItemAddAction()),
                new KeyValueMemberName(MENU_ITEM_CHECK_IS_SELECTED,getAliasMenuItemCheckIsSelected()),
                new KeyValueMemberName(MENU_ITEM_CHECK_SET_SELECTED,getAliasMenuItemCheckSetSelected())
        ));
        return m_;
    }

    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> m_ = new CustList<CustList<KeyValueMemberName>>();
        m_.addAllElts(guiAliasParameters.allTableTypeMethodParamNames());
        return m_;
    }
    private void allComponents(StringMap<CustList<KeyValueMemberName>> _m) {
        CustList<KeyValueMemberName> names_ = getCompoMethodNames();
        _m.addEntry(getAliasPanel(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(COUNT,getAliasCount()),
                new KeyValueMemberName(GET_INDEX_COMPO,getAliasGetIndexCompo()),
                new KeyValueMemberName(ADD_COMPO,getAliasAddCompo()),
                new KeyValueMemberName(REMOVE_COMPO,getAliasRemoveCompo()),
                new KeyValueMemberName(PANEL_ABSOLUTE,getAliasPanelAbsolute()),
                new KeyValueMemberName(PANEL_FLOW,getAliasPanelFlow()),
                new KeyValueMemberName(PANEL_PAGE_BOX,getAliasPanelPageBox()),
                new KeyValueMemberName(PANEL_GRID,getAliasPanelGrid()),
                new KeyValueMemberName(PANEL_VALIDATE,getAliasPanelValidate()),
                new KeyValueMemberName(REMOVE_ALL,getAliasRemoveAll())),names_));
        _m.addEntry(getAliasTabbedPane(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TABBED_PANE_NB,getAliasTabbedPaneNb()),
                new KeyValueMemberName(TABBED_PANE_ADD,getAliasTabbedPaneAdd()),
                new KeyValueMemberName(TABBED_PANE_REMOVE,getAliasTabbedPaneRemove()),
                new KeyValueMemberName(TABBED_PANE_SEL_INDEX,getAliasTabbedPaneSelIndex()),
                new KeyValueMemberName(TABBED_PANE_INDEX,getAliasTabbedPaneIndex()),
                new KeyValueMemberName(TABBED_PANE_GET,getAliasTabbedPaneGet()),
                new KeyValueMemberName(TABBED_PANE_GET_TITLE,getAliasTabbedPaneGetTitle()),
                new KeyValueMemberName(TABBED_PANE_SET,getAliasTabbedPaneSet()),
                new KeyValueMemberName(TABBED_PANE_SET_TITLE,getAliasTabbedPaneSetTitle()),
                new KeyValueMemberName(REMOVE_ALL,getAliasRemoveAll())),names_));
        _m.addEntry(getAliasPanelBorder(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(COUNT,getAliasCount()),
                new KeyValueMemberName(GET_INDEX_COMPO,getAliasGetIndexCompo()),
                new KeyValueMemberName(ADD_COMPO,getAliasAddCompo()),
                new KeyValueMemberName(REMOVE_COMPO,getAliasRemoveCompo()),
                new KeyValueMemberName(PANEL_ABSOLUTE,getAliasPanelAbsolute()),
                new KeyValueMemberName(PANEL_FLOW,getAliasPanelFlow()),
                new KeyValueMemberName(PANEL_PAGE_BOX,getAliasPanelPageBox()),
                new KeyValueMemberName(PANEL_GRID,getAliasPanelGrid()),
                new KeyValueMemberName(PANEL_VALIDATE,getAliasPanelValidate()),
                new KeyValueMemberName(REMOVE_ALL,getAliasRemoveAll())),names_));
        _m.addEntry(getAliasComponent(), names_);
        _m.addEntry(getAliasTextLabel(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SET_LABEL_TEXT,getAliasSetLabelText())),names_));
        _m.addEntry(getAliasImageLabel(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SET_LABEL_IMAGE,getAliasSetLabelImage())),names_));
        _m.addEntry(getAliasButton(),merge(new CustList<KeyValueMemberName>(),names_));
        _m.addEntry(getAliasProgBar(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PROG_BAR_OR,getAliasProgBarOr()),
                new KeyValueMemberName(PROG_BAR_VALUE,getAliasProgBarValue()),
                new KeyValueMemberName(PROG_BAR_MIN,getAliasProgBarMin()),
                new KeyValueMemberName(PROG_BAR_MAX,getAliasProgBarMax())),names_));
        _m.addEntry(getAliasScrollPane(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SCROLL_PANE_HORIZONTAL_VALUE,getAliasScrollPaneHorizontalValue()),
                new KeyValueMemberName(SCROLL_PANE_VERTICAL_VALUE,getAliasScrollPaneVerticalValue()),
                new KeyValueMemberName(SCROLL_PANE_GET_VIEW,getAliasScrollPaneGetView()),
                new KeyValueMemberName(SCROLL_PANE_SET_VIEW,getAliasScrollPaneSetView()),
                new KeyValueMemberName(SCROLL_PANE_VALIDATE,getAliasScrollPaneValidate())),names_));
        _m.addEntry(getAliasSplitPane(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SPLIT_PANE_GET_DIVIDER_LOCATION,getAliasSplitPaneGetDividerLocation()),
                new KeyValueMemberName(SPLIT_PANE_SET_DIVIDER_LOCATION,getAliasSplitPaneSetDividerLocation()),
                new KeyValueMemberName(SPLIT_PANE_GET_DIVIDER_SIZE,getAliasSplitPaneGetDividerSize()),
                new KeyValueMemberName(SPLIT_PANE_SET_DIVIDER_SIZE,getAliasSplitPaneSetDividerSize()),
                new KeyValueMemberName(SPLIT_PANE_GET_LEFT,getAliasSplitPaneGetLeft()),
                new KeyValueMemberName(SPLIT_PANE_SET_LEFT,getAliasSplitPaneSetLeft()),
                new KeyValueMemberName(SPLIT_PANE_GET_RIGHT,getAliasSplitPaneGetRight()),
                new KeyValueMemberName(SPLIT_PANE_SET_RIGHT,getAliasSplitPaneSetRight()),
                new KeyValueMemberName(SPLIT_PANE_IS_CONTINUOUS_LAYOUT,getAliasSplitPaneIsContinuousLayout()),
                new KeyValueMemberName(SPLIT_PANE_SET_CONTINUOUS_LAYOUT,getAliasSplitPaneSetContinuousLayout()),
                new KeyValueMemberName(SPLIT_PANE_IS_ONE_TOUCH_EXPANDABLE,getAliasSplitPaneIsOneTouchExpandable()),
                new KeyValueMemberName(SPLIT_PANE_SET_ONE_TOUCH_EXPANDABLE,getAliasSplitPaneSetOneTouchExpandable()),
                new KeyValueMemberName(SPLIT_PANE_VALIDATE,getAliasSplitPaneValidate())),names_));
        _m.addEntry(getAliasInput(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(INPUT_IS_ENABLED,getAliasInputIsEnabled()),
                new KeyValueMemberName(INPUT_SET_ENABLED,getAliasInputSetEnabled())),names_));
        _m.addEntry(getAliasCheckBox(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CHECK_BOX_ADD_ACTION,getAliasCheckBoxAddAction()),
                new KeyValueMemberName(CHECK_BOX_GET_TEXT,getAliasCheckBoxGetText()),
                new KeyValueMemberName(CHECK_BOX_SET_TEXT,getAliasCheckBoxSetText()),
                new KeyValueMemberName(CHECK_BOX_IS_SELECTED,getAliasCheckBoxIsSelected()),
                new KeyValueMemberName(CHECK_BOX_SET_SELECTED,getAliasCheckBoxSetSelected())),names_)
        );
        _m.addEntry(getAliasSpinner(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SPINNER_GET_MAX,getAliasSpinnerGetMax()),
                new KeyValueMemberName(SPINNER_GET_MIN,getAliasSpinnerGetMin()),
                new KeyValueMemberName(SPINNER_GET_STEP,getAliasSpinnerGetStep()),
                new KeyValueMemberName(SPINNER_GET_VALUE,getAliasSpinnerGetValue()),
                new KeyValueMemberName(SPINNER_SET_MAX,getAliasSpinnerSetMax()),
                new KeyValueMemberName(SPINNER_SET_MIN,getAliasSpinnerSetMin()),
                new KeyValueMemberName(SPINNER_SET_STEP,getAliasSpinnerSetStep()),
                new KeyValueMemberName(SPINNER_SET_VALUE,getAliasSpinnerSetValue()),
                new KeyValueMemberName(SPINNER_SET_RANGE,getAliasSpinnerSetRange()),
                new KeyValueMemberName(SPINNER_SET_RANGE_VALUE,getAliasSpinnerSetRangeValue()),
                new KeyValueMemberName(ADD_CHANGE,getAliasAddChange())),names_)
        );
        _m.addEntry(getAliasSlider(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SLIDER_GET_MAX,getAliasSliderGetMax()),
                new KeyValueMemberName(SLIDER_GET_MIN,getAliasSliderGetMin()),
                new KeyValueMemberName(SLIDER_GET_ORIENTATION,getAliasSliderGetOrientation()),
                new KeyValueMemberName(SLIDER_GET_VALUE,getAliasSliderGetValue()),
                new KeyValueMemberName(SLIDER_SET_MAX,getAliasSliderSetMax()),
                new KeyValueMemberName(SLIDER_SET_MIN,getAliasSliderSetMin()),
                new KeyValueMemberName(SLIDER_SET_ORIENTATION,getAliasSliderSetOrientation()),
                new KeyValueMemberName(SLIDER_SET_VALUE,getAliasSliderSetValue()),
                new KeyValueMemberName(ADD_CHANGE,getAliasAddChange())),names_)
        );
        _m.addEntry(getAliasRadio(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(RADIO_GET_TEXT,getAliasRadioGetText()),
                new KeyValueMemberName(RADIO_SET_TEXT,getAliasRadioSetText()),
                new KeyValueMemberName(RADIO_IS_SELECTED,getAliasRadioIsSelected()),
                new KeyValueMemberName(RADIO_SET_SELECTED,getAliasRadioSetSelected()),
                new KeyValueMemberName(ADD_CHANGE,getAliasAddChange())),names_)
        );
        _m.addEntry(getAliasTextField(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TEXT_FIELD_ADD_ACTION,getAliasTextFieldAddAction()),
                new KeyValueMemberName(TEXT_FIELD_ADD_POPUP,getAliasTextFieldAddPopup()),
                new KeyValueMemberName(TEXT_FIELD_GET_TEXT,getAliasTextFieldGetText()),
                new KeyValueMemberName(TEXT_FIELD_SET_TEXT,getAliasTextFieldSetText())),names_)
        );
        _m.addEntry(getAliasTextArea(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TEXT_AREA_APPEND,getAliasTextAreaAppend()),
                new KeyValueMemberName(TEXT_AREA_INSERT,getAliasTextAreaInsert()),
                new KeyValueMemberName(TEXT_AREA_REPLACE_RANGE,getAliasTextAreaReplaceRange()),
                new KeyValueMemberName(TEXT_AREA_REPLACE_SELECTION,getAliasTextAreaReplaceSelection()),
                new KeyValueMemberName(TEXT_AREA_GET_SELECTED_TEXT,getAliasTextAreaGetSelectedText()),
                new KeyValueMemberName(TEXT_AREA_SET_SELECTION_START,getAliasTextAreaSetSelectionStart()),
                new KeyValueMemberName(TEXT_AREA_SET_SELECTION_END,getAliasTextAreaSetSelectionEnd()),
                new KeyValueMemberName(TEXT_AREA_GET_TAB_SIZE,getAliasTextAreaGetTabSize()),
                new KeyValueMemberName(TEXT_AREA_SET_TAB_SIZE,getAliasTextAreaSetTabSize()),
                new KeyValueMemberName(TEXT_AREA_GET_TEXT,getAliasTextAreaGetText()),
                new KeyValueMemberName(TEXT_AREA_SET_TEXT,getAliasTextAreaSetText()),
                new KeyValueMemberName(TEXT_AREA_SELECT,getAliasTextAreaSelect()),
                new KeyValueMemberName(TEXT_AREA_SELECT_ALL,getAliasTextAreaSelectAll())),names_)
        );
        _m.addEntry(getAliasCombo(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(COMBO_ADD_ITEM,getAliasComboAddItem()),
                new KeyValueMemberName(COMBO_GET_ITEM_COUNT,getAliasComboGetItemCount()),
                new KeyValueMemberName(COMBO_GET_LISTENERS,getAliasComboGetListeners()),
                new KeyValueMemberName(COMBO_GET_SELECTED_INDEX,getAliasComboGetSelectedIndex()),
                new KeyValueMemberName(COMBO_GET_SELECTED_INDEXES,getAliasComboGetSelectedIndexes()),
                new KeyValueMemberName(COMBO_GET_SELECTED_ITEM,getAliasComboGetSelectedItem()),
                new KeyValueMemberName(COMBO_REMOVE_ALL_ITEMS,getAliasComboRemoveAllItems()),
                new KeyValueMemberName(COMBO_REMOVE_ITEM,getAliasComboRemoveItem()),
                new KeyValueMemberName(COMBO_SELECT_ITEM,getAliasComboSelectItem()),
                new KeyValueMemberName(COMBO_ADD_LISTENER,getAliasComboAddListener()),
                new KeyValueMemberName(COMBO_REMOVE_LISTENER,getAliasComboRemoveListener())),names_)
        );
        _m.addEntry(getAliasGrList(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GR_LIST_ADD,getAliasGrListAdd()),
                new KeyValueMemberName(GR_LIST_CLEAR,getAliasGrListClear()),
                new KeyValueMemberName(GR_LIST_CLEAR_SELECTION,getAliasGrListClearSelection()),
                new KeyValueMemberName(GR_LIST_GET_LIST_VIEW,getAliasGrListGetListView()),
                new KeyValueMemberName(GR_LIST_GET_RENDER,getAliasGrListGetRender()),
                new KeyValueMemberName(GR_LIST_GET_SELECTED_INDEXES,getAliasGrListGetSelectedIndexes()),
                new KeyValueMemberName(GR_LIST_GET_SELECTIONS,getAliasGrListGetSelections()),
                new KeyValueMemberName(GR_LIST_GET_VISIBLE_ROW_COUNT,getAliasGrListGetVisibleRowCount()),
                new KeyValueMemberName(GR_LIST_REMOVE,getAliasGrListRemove()),
                new KeyValueMemberName(GR_LIST_SET,getAliasGrListSet()),
                new KeyValueMemberName(GR_LIST_SET_RENDER,getAliasGrListSetRender()),
                new KeyValueMemberName(GR_LIST_SET_SELECTED_INDEXES,getAliasGrListSetSelectedIndexes()),
                new KeyValueMemberName(GR_LIST_ADD_SELECTION,getAliasGrListAddSelection()),
                new KeyValueMemberName(GR_LIST_REMOVE_SELECTION,getAliasGrListRemoveSelection()),
                new KeyValueMemberName(GR_LIST_SET_VISIBLE_ROW_COUNT,getAliasGrListSetVisibleRowCount()),
                new KeyValueMemberName(GR_LIST_UPDATE_GRAPHICS,getAliasGrListUpdateGraphics())),names_)
        );
        _m.addEntry(getAliasPopupMenu(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(POPUP_MENU_ADD,getAliasPopupMenuAdd()),
                new KeyValueMemberName(POPUP_MENU_ADD_MENU,getAliasPopupMenuAddMenu()),
                new KeyValueMemberName(POPUP_MENU_GET_COMP,getAliasPopupMenuGetComp()),
                new KeyValueMemberName(POPUP_MENU_REMOVE_COMP,getAliasPopupMenuRemoveComp()),
                new KeyValueMemberName(POPUP_MENU_NB_COMP,getAliasPopupMenuNbComp()),
                new KeyValueMemberName(POPUP_MENU_GET_MENU,getAliasPopupMenuGetMenu()),
                new KeyValueMemberName(POPUP_MENU_REMOVE_MENU,getAliasPopupMenuRemoveMenu()),
                new KeyValueMemberName(POPUP_MENU_NB_MENU,getAliasPopupMenuNbMenu()),
                new KeyValueMemberName(POPUP_MENU_SHOW,getAliasPopupMenuShow())),names_)
        );
        _m.addEntry(getAliasTree(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TREE_ADD_TREE_LISTENER,getAliasTreeAddTreeListener()),
                new KeyValueMemberName(TREE_GET_SELECTED,getAliasTreeGetSelected()),
                new KeyValueMemberName(TREE_IS_ROOT_VISIBLE,getAliasTreeIsRootVisible()),
                new KeyValueMemberName(TREE_SET_ROOT_VISIBLE,getAliasTreeSetRootVisible()),
                new KeyValueMemberName(TREE_RELOAD,getAliasTreeReload())),names_)
        );
        _m.addEntry(getAliasTableGui(),merge(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(TABLE_ADD_HEADER,getAliasTableAddHeader()),
                new KeyValueMemberName(TABLE_ADD_SELECT,getAliasTableAddSelect()),
                new KeyValueMemberName(TABLE_APPLY_CHANGES,getAliasTableApplyChanges()),
                new KeyValueMemberName(TABLE_ADD_INTERVAL,getAliasTableAddInterval()),
                new KeyValueMemberName(TABLE_REMOVE_INTERVAL,getAliasTableRemoveInterval()),
                new KeyValueMemberName(TABLE_MOVE_COLUMN,getAliasTableMoveColumn()),
                new KeyValueMemberName(TABLE_GET_COLUMN_AT_POINT,getAliasTableGetColumnAtPoint()),
                new KeyValueMemberName(TABLE_GET_COLUMN_COUNT,getAliasTableGetColumnCount()),
                new KeyValueMemberName(TABLE_GET_COLUMN_NAME,getAliasTableGetColumnName()),
                new KeyValueMemberName(TABLE_GET_ROW_AT_POINT,getAliasTableGetRowAtPoint()),
                new KeyValueMemberName(TABLE_GET_ROW_COUNT,getAliasTableGetRowCount()),
                new KeyValueMemberName(TABLE_GET_SELECTED_ROW,getAliasTableGetSelectedRow()),
                new KeyValueMemberName(TABLE_GET_SELECTED_ROW_COUNT,getAliasTableGetSelectedRowCount()),
                new KeyValueMemberName(TABLE_GET_SELECTED_ROWS,getAliasTableGetSelectedRows()),
                new KeyValueMemberName(TABLE_GET_VALUE,getAliasTableGetValue()),
                new KeyValueMemberName(TABLE_SET_COLUMNS,getAliasTableSetColumns()),
                new KeyValueMemberName(TABLE_SET_MULTIPLE,getAliasTableSetMultiple()),
                new KeyValueMemberName(TABLE_SET_REORDER,getAliasTableSetReorder()),
                new KeyValueMemberName(TABLE_SET_ROW_COUNT,getAliasTableSetRowCount()),
                new KeyValueMemberName(TABLE_SET_VALUE,getAliasTableSetValue()),
                new KeyValueMemberName(TABLE_IS_MULTIPLE,getAliasTableIsMultiple()),
                new KeyValueMemberName(TABLE_IS_REORDER,getAliasTableIsReorder())),names_)
        );
    }

    private static CustList<KeyValueMemberName> merge(CustList<KeyValueMemberName> _one, CustList<KeyValueMemberName> _two) {
        CustList<KeyValueMemberName> all_ = new CustList<KeyValueMemberName>();
        all_.addAllElts(_one);
        all_.addAllElts(_two);
        return all_;
    }
    private CustList<KeyValueMemberName> getCompoMethodNames() {
        return new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_PARENT_COMPO,getAliasGetParentCompo()),
                new KeyValueMemberName(GET_NEXT_COMPO,getAliasGetNextCompo()),
                new KeyValueMemberName(COMPONENT_REPAINT,getAliasComponentRepaint()),
                new KeyValueMemberName(COMPONENT_GET_PAINT,getAliasComponentGetPaint()),
                new KeyValueMemberName(COMPONENT_SET_PAINT,getAliasComponentSetPaint()),
                new KeyValueMemberName(GET_FONT,getAliasGetFont()),
                new KeyValueMemberName(SET_FONT,getAliasSetFont()),
                new KeyValueMemberName(COMPONENT_GET_HEIGHT,getAliasComponentGetHeight()),
                new KeyValueMemberName(COMPONENT_GET_WIDTH,getAliasComponentGetWidth()),
                new KeyValueMemberName(COMPONENT_IS_AUTOSCROLLS,getAliasComponentIsAutoscrolls()),
                new KeyValueMemberName(COMPONENT_SET_AUTOSCROLLS,getAliasComponentSetAutoscrolls()),
                new KeyValueMemberName(COMPONENT_GET_PREFERRED_SIZE,getAliasComponentGetPreferredSize()),
                new KeyValueMemberName(COMPONENT_SET_PREFERRED_SIZE,getAliasComponentSetPreferredSize()),
                new KeyValueMemberName(COMPONENT_SET_SIZE,getAliasComponentSetSize()),
                new KeyValueMemberName(COMPONENT_IS_VISIBLE,getAliasComponentIsVisible()),
                new KeyValueMemberName(COMPONENT_SET_VISIBLE,getAliasComponentSetVisible()),
                new KeyValueMemberName(COMPONENT_INVOKE_LATER,getAliasComponentInvokeLater()),
                new KeyValueMemberName(ADD_KEY_LISTENER,getAliasAddKeyListener()),
                new KeyValueMemberName(REMOVE_KEY_LISTENER,getAliasRemoveKeyListener()),
                new KeyValueMemberName(GET_KEY_LISTENERS,getAliasGetKeyListeners()),
                new KeyValueMemberName(ADD_WHEEL_LISTENER,getAliasAddWheelListener()),
                new KeyValueMemberName(REMOVE_WHEEL_LISTENER,getAliasRemoveWheelListener()),
                new KeyValueMemberName(GET_WHEEL_LISTENERS,getAliasGetWheelListeners()),
                new KeyValueMemberName(ADD_LISTENER,getAliasAddListener()),
                new KeyValueMemberName(REMOVE_LISTENER,getAliasRemoveListener()),
                new KeyValueMemberName(GET_LISTENERS,getAliasGetListeners()),
                new KeyValueMemberName(REQUEST_FOCUS,getAliasRequestFocus()),
                new KeyValueMemberName(COMP_BACK,getAliasCompBack()),
                new KeyValueMemberName(COMP_FOCUSABLE,getAliasCompFocusable()),
                new KeyValueMemberName(COMP_FORE,getAliasCompFore()),
                new KeyValueMemberName(COMP_GET_FIRST_POS,getAliasCompGetFirstPos()),
                new KeyValueMemberName(COMP_GET_SECOND_POS,getAliasCompGetSecondPos()),
                new KeyValueMemberName(COMP_OPAQUE,getAliasCompOpaque()),
                new KeyValueMemberName(COMP_TOOL_TIP,getAliasCompToolTip()),
                new KeyValueMemberName(COMP_LOC,getAliasCompLoc()),
                new KeyValueMemberName(COMP_BOR_LINE,getAliasCompBorLine()),
                new KeyValueMemberName(COMP_BOR_LOWER,getAliasCompBorLower()),
                new KeyValueMemberName(COMP_BOR_RAISE,getAliasCompBorRaise()),
                new KeyValueMemberName(COMP_BOR_TITLE,getAliasCompBorTitle())
        );
    }

    private void allEventTypes(StringMap<CustList<KeyValueMemberName>> _m) {
        _m.addEntry(getAliasMouseEvent(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(MOUSE_EVENT_GET_FIRST,getAliasMouseEventGetFirst()),
                new KeyValueMemberName(MOUSE_EVENT_GET_SECOND,getAliasMouseEventGetSecond()),
                new KeyValueMemberName(MOUSE_EVENT_GET_CLICKS,getAliasMouseEventGetClicks()),
                new KeyValueMemberName(MOUSE_EVENT_IS_ALT,getAliasMouseEventIsAlt()),
                new KeyValueMemberName(MOUSE_EVENT_IS_SHIFT,getAliasMouseEventIsShift()),
                new KeyValueMemberName(MOUSE_EVENT_IS_CTRL,getAliasMouseEventIsCtrl()),
                new KeyValueMemberName(MOUSE_EVENT_IS_LEFT,getAliasMouseEventIsLeft()),
                new KeyValueMemberName(MOUSE_EVENT_IS_RIGHT,getAliasMouseEventIsRight()),
                new KeyValueMemberName(MOUSE_EVENT_IS_MIDDLE,getAliasMouseEventIsMiddle())));
        _m.addEntry(getAliasWheelEvent(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(MOUSE_EVENT_GET_FIRST,getAliasMouseEventGetFirst()),
                new KeyValueMemberName(MOUSE_EVENT_GET_SECOND,getAliasMouseEventGetSecond()),
                new KeyValueMemberName(MOUSE_EVENT_GET_CLICKS,getAliasMouseEventGetClicks()),
                new KeyValueMemberName(MOUSE_EVENT_IS_ALT,getAliasMouseEventIsAlt()),
                new KeyValueMemberName(MOUSE_EVENT_IS_SHIFT,getAliasMouseEventIsShift()),
                new KeyValueMemberName(MOUSE_EVENT_IS_CTRL,getAliasMouseEventIsCtrl()),
                new KeyValueMemberName(MOUSE_EVENT_IS_LEFT,getAliasMouseEventIsLeft()),
                new KeyValueMemberName(MOUSE_EVENT_IS_RIGHT,getAliasMouseEventIsRight()),
                new KeyValueMemberName(MOUSE_EVENT_IS_MIDDLE,getAliasMouseEventIsMiddle()),
                new KeyValueMemberName(WHEEL_ROTATED_CLICKS,getAliasWheelRotatedClicks()))
        );
        _m.addEntry(getAliasKeyEvent(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(KEY_EVENT_CODE,getAliasKeyEventCode()),
                new KeyValueMemberName(KEY_EVENT_CHAR,getAliasKeyEventChar()),
                new KeyValueMemberName(KEY_EVENT_IS_ALT,getAliasKeyEventIsAlt()),
                new KeyValueMemberName(KEY_EVENT_IS_SHIFT,getAliasKeyEventIsShift()),
                new KeyValueMemberName(KEY_EVENT_IS_CTRL,getAliasKeyEventIsCtrl())));
    }

    private void allWindowMethods(StringMap<CustList<KeyValueMemberName>> _m) {
        _m.addEntry(getAliasConfirm(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CONFIRM_FIELD,getAliasConfirmField()),
                new KeyValueMemberName(CONFIRM_FULL,getAliasConfirmFull()),
                new KeyValueMemberName(CONFIRM_MESSAGE,getAliasConfirmMessage()),
                new KeyValueMemberName(CONFIRM_OK,getAliasConfirmOk()),
                new KeyValueMemberName(CONFIRM_YES_NO,getAliasConfirmYesNo())));
        _m.addEntry(getAliasFrame(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PACK,getAliasPack()),
                new KeyValueMemberName(ADD_WINDOW_LISTENER,getAliasAddWindowListener()),
                new KeyValueMemberName(REMOVE_WINDOW_LISTENER,getAliasRemoveWindowListener()),
                new KeyValueMemberName(GET_WINDOW_LISTENERS,getAliasGetWindowListeners()),
                new KeyValueMemberName(DISPOSE,getAliasDispose()),
                new KeyValueMemberName(WINDOW_TYPE_RELATIVE,getAliasWindowTypeRelative()),
                new KeyValueMemberName(IS_VISIBLE,getAliasIsVisible()),
                new KeyValueMemberName(SET_VISIBLE,getAliasSetVisible()),
                new KeyValueMemberName(SET_CONTENT,getAliasSetContent()),
                new KeyValueMemberName(GET_MENU_BAR,getAliasGetMenuBar()),
                new KeyValueMemberName(SET_MENU_BAR,getAliasSetMenuBar()),
                new KeyValueMemberName(WINDOW,getAliasWindow()),
                new KeyValueMemberName(ARGS,getAliasArgs())));
        _m.addEntry(getAliasWindowSet(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(WINDOW_SET_ADD,getAliasWindowSetAdd()),
                new KeyValueMemberName(WINDOW_SET_ALL,getAliasWindowSetAll()),
                new KeyValueMemberName(WINDOW_SET_CONTAINS,getAliasWindowSetContains()),
                new KeyValueMemberName(WINDOW_SET_REMOVE,getAliasWindowSetRemove()),
                new KeyValueMemberName(WINDOW_SET_SNAPSHOT,getAliasWindowSetSnapshot())));
        _m.addEntry(getAliasDialog(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PACK,getAliasPack()),
                new KeyValueMemberName(ADD_WINDOW_LISTENER,getAliasAddWindowListener()),
                new KeyValueMemberName(REMOVE_WINDOW_LISTENER,getAliasRemoveWindowListener()),
                new KeyValueMemberName(GET_WINDOW_LISTENERS,getAliasGetWindowListeners()),
                new KeyValueMemberName(DISPOSE,getAliasDispose()),
                new KeyValueMemberName(WINDOW_TYPE_RELATIVE,getAliasWindowTypeRelative()),
                new KeyValueMemberName(IS_VISIBLE,getAliasIsVisible()),
                new KeyValueMemberName(SET_VISIBLE,getAliasSetVisible()),
                new KeyValueMemberName(SET_CONTENT,getAliasSetContent()),
                new KeyValueMemberName(GET_MENU_BAR,getAliasGetMenuBar()),
                new KeyValueMemberName(SET_MENU_BAR,getAliasSetMenuBar()),
                new KeyValueMemberName(DIALOG_IS_MODAL,getAliasDialogIsModal()),
                new KeyValueMemberName(DIALOG_SET_MODAL,getAliasDialogSetModal())));
        _m.addEntry(getAliasWindowType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PACK,getAliasPack()),
                new KeyValueMemberName(ADD_WINDOW_LISTENER,getAliasAddWindowListener()),
                new KeyValueMemberName(REMOVE_WINDOW_LISTENER,getAliasRemoveWindowListener()),
                new KeyValueMemberName(GET_WINDOW_LISTENERS,getAliasGetWindowListeners()),
                new KeyValueMemberName(DISPOSE,getAliasDispose()),
                new KeyValueMemberName(WINDOW_TYPE_RELATIVE,getAliasWindowTypeRelative()),
                new KeyValueMemberName(IS_VISIBLE,getAliasIsVisible()),
                new KeyValueMemberName(SET_VISIBLE,getAliasSetVisible()),
                new KeyValueMemberName(SET_CONTENT,getAliasSetContent()),
                new KeyValueMemberName(GET_MENU_BAR,getAliasGetMenuBar()),
                new KeyValueMemberName(SET_MENU_BAR,getAliasSetMenuBar())));
    }

    public StringMap<String> allRefTypes() {
        StringMap<String> ref_ = new StringMap<String>();
        ref_.addEntry(WINDOW_SET,getAliasWindowSet());
        ref_.addEntry(FRAME,getAliasFrame());
        ref_.addEntry(CONFIRM,getAliasConfirm());
        ref_.addEntry(DIALOG,getAliasDialog());
        ref_.addEntry(WINDOW_TYPE,getAliasWindowType());
        ref_.addEntry(COMPONENT,getAliasComponent());
        ref_.addEntry(ACTION_EVENT,getAliasActionEvent());
        ref_.addEntry(MOUSE_EVENT,getAliasMouseEvent());
        ref_.addEntry(TABLE_LISTENER,getAliasTableListener());
        ref_.addEntry(TABLE_GUI,getAliasTableGui());
        ref_.addEntry(TREE_LISTENER,getAliasTreeListener());
        ref_.addEntry(TREE,getAliasTree());
        ref_.addEntry(TREE_NODE,getAliasTreeNode());
        ref_.addEntry(KEY_EVENT,getAliasKeyEvent());
        ref_.addEntry(WINDOW_EVENT,getAliasWindowEvent());
        ref_.addEntry(PANEL,getAliasPanel());
        ref_.addEntry(TABBED_PANE,getAliasTabbedPane());
        ref_.addEntry(PANEL_BORDER,getAliasPanelBorder());
        ref_.addEntry(BUTTON,getAliasButton());
        ref_.addEntry(PROG_BAR,getAliasProgBar());
        ref_.addEntry(CHECK_BOX,getAliasCheckBox());
        ref_.addEntry(RADIO,getAliasRadio());
        ref_.addEntry(TEXT_LABEL,getAliasTextLabel());
        ref_.addEntry(IMAGE,getAliasImage());
        ref_.addEntry(IMAGE_LABEL,getAliasImageLabel());
        ref_.addEntry(COLOR,getAliasColor());
        ref_.addEntry(INPUT,getAliasInput());
        ref_.addEntry(FONT,getAliasFont());
        ref_.addEntry(TEXT_AREA,getAliasTextArea());
        ref_.addEntry(TEXT_FIELD,getAliasTextField());
        ref_.addEntry(GR_LIST,getAliasGrList());
        ref_.addEntry(COMBO,getAliasCombo());
        ref_.addEntry(BUTTON_GROUP,getAliasButtonGroup());
        ref_.addEntry(RENDER,getAliasRender());
        ref_.addEntry(POPUP_MENU,getAliasPopupMenu());
        ref_.addEntry(DIMENSION,getAliasDimension());
        ref_.addEntry(KEY_LISTENER,getAliasKeyListener());
        ref_.addEntry(MOUSE_LISTENER,getAliasMouseListener());
        ref_.addEntry(WHEEL_LISTENER,getAliasWheelListener());
        ref_.addEntry(WHEEL_EVENT,getAliasWheelEvent());
        ref_.addEntry(ACTION_LISTENER,getAliasActionListener());
        ref_.addEntry(CHANGE_LISTENER,getAliasChangeListener());
        ref_.addEntry(WINDOW_LISTENER,getAliasWindowListener());
        ref_.addEntry(SCROLL_PANE,getAliasScrollPane());
        ref_.addEntry(SPLIT_PANE,getAliasSplitPane());
        ref_.addEntry(LIST_SELECTION,getAliasListSelection());
        ref_.addEntry(PAINT,getAliasPaint());
        ref_.addEntry(MENU_BAR,getAliasMenuBar());
        ref_.addEntry(ABS_MENU,getAliasAbsMenu());
        ref_.addEntry(MENU,getAliasMenu());
        ref_.addEntry(ABS_MENU_ITEM,getAliasAbsMenuItem());
        ref_.addEntry(MENU_ITEM,getAliasMenuItem());
        ref_.addEntry(MENU_ITEM_CHECK,getAliasMenuItemCheck());
        ref_.addEntry(SPINNER,getAliasSpinner());
        ref_.addEntry(SLIDER,getAliasSlider());
        return ref_;
    }
    protected static String tr(String _var, KeyWords _keyWords, StringMap<PrimitiveType> _primitiveTypes, AliasCore _coreNames, String... _args) {
        CustList<String> allKeysWords_ = _keyWords.allKeyWords().values();
        allKeysWords_.addAllElts(_primitiveTypes.getKeys());
        allKeysWords_.add(_coreNames.getAliasVoid());
        for (String p:_args) {
            allKeysWords_.add(p);
        }
        return ValidatorStandard.tr(allKeysWords_,_var);
    }
    public String getAliasActionListener() {
        return aliasActionListener;
    }

    public void setAliasActionListener(String _v) {
        this.aliasActionListener = _v;
    }

    public String getAliasActionPerformed() {
        return aliasActionPerformed;
    }

    public void setAliasActionPerformed(String _v) {
        this.aliasActionPerformed = _v;
    }

    public String getAliasActionEvent() {
        return aliasActionEvent;
    }

    public void setAliasActionEvent(String _v) {
        this.aliasActionEvent = _v;
    }

    public String getAliasAddChange() {
        return aliasAddChange;
    }

    public void setAliasAddChange(String _v) {
        this.aliasAddChange = _v;
    }

    public String getAliasChangeListener() {
        return aliasChangeListener;
    }

    public void setAliasChangeListener(String _v) {
        this.aliasChangeListener = _v;
    }

    public String getAliasStateChanged() {
        return aliasStateChanged;
    }

    public void setAliasStateChanged(String _v) {
        this.aliasStateChanged = _v;
    }

    public String getAliasTreeListener() {
        return aliasTreeListener;
    }

    public void setAliasTreeListener(String _v) {
        this.aliasTreeListener = _v;
    }

    public String getAliasTreeListenerValueChanged() {
        return aliasTreeListenerValueChanged;
    }

    public void setAliasTreeListenerValueChanged(String _v) {
        this.aliasTreeListenerValueChanged = _v;
    }

    public String getAliasTreeNode() {
        return aliasTreeNode;
    }

    public void setAliasTreeNode(String _v) {
        this.aliasTreeNode = _v;
    }

    public String getAliasTreeNodeAdd() {
        return aliasTreeNodeAdd;
    }

    public void setAliasTreeNodeAdd(String _v) {
        this.aliasTreeNodeAdd = _v;
    }

    public String getAliasTreeNodeInsert() {
        return aliasTreeNodeInsert;
    }

    public void setAliasTreeNodeInsert(String _v) {
        this.aliasTreeNodeInsert = _v;
    }

    public String getAliasTreeNodeRemove() {
        return aliasTreeNodeRemove;
    }

    public void setAliasTreeNodeRemove(String _v) {
        this.aliasTreeNodeRemove = _v;
    }

    public String getAliasTreeNodeRemoveFromParent() {
        return aliasTreeNodeRemoveFromParent;
    }

    public void setAliasTreeNodeRemoveFromParent(String _v) {
        this.aliasTreeNodeRemoveFromParent = _v;
    }

    public String getAliasTreeNodeRemoveAllChildren() {
        return aliasTreeNodeRemoveAllChildren;
    }

    public void setAliasTreeNodeRemoveAllChildren(String _v) {
        this.aliasTreeNodeRemoveAllChildren = _v;
    }

    public String getAliasTreeNodeSetUserObject() {
        return aliasTreeNodeSetUserObject;
    }

    public void setAliasTreeNodeSetUserObject(String _v) {
        this.aliasTreeNodeSetUserObject = _v;
    }

    public String getAliasTreeNodeNb() {
        return aliasTreeNodeNb;
    }

    public void setAliasTreeNodeNb(String _v) {
        this.aliasTreeNodeNb = _v;
    }

    public String getAliasTreeNodeEq() {
        return aliasTreeNodeEq;
    }

    public void setAliasTreeNodeEq(String _v) {
        this.aliasTreeNodeEq = _v;
    }

    public String getAliasTreeNodeGetFirstChild() {
        return aliasTreeNodeGetFirstChild;
    }

    public void setAliasTreeNodeGetFirstChild(String _v) {
        this.aliasTreeNodeGetFirstChild = _v;
    }

    public String getAliasTreeNodeGetLastChild() {
        return aliasTreeNodeGetLastChild;
    }

    public void setAliasTreeNodeGetLastChild(String _v) {
        this.aliasTreeNodeGetLastChild = _v;
    }

    public String getAliasTreeNodeGetNextSibling() {
        return aliasTreeNodeGetNextSibling;
    }

    public void setAliasTreeNodeGetNextSibling(String _v) {
        this.aliasTreeNodeGetNextSibling = _v;
    }

    public String getAliasTreeNodeGetPreviousSibling() {
        return aliasTreeNodeGetPreviousSibling;
    }

    public void setAliasTreeNodeGetPreviousSibling(String _v) {
        this.aliasTreeNodeGetPreviousSibling = _v;
    }

    public String getAliasTreeNodeGetParentNode() {
        return aliasTreeNodeGetParentNode;
    }

    public void setAliasTreeNodeGetParentNode(String _v) {
        this.aliasTreeNodeGetParentNode = _v;
    }

    public String getAliasTreeNodeGetUserObject() {
        return aliasTreeNodeGetUserObject;
    }

    public void setAliasTreeNodeGetUserObject(String _v) {
        this.aliasTreeNodeGetUserObject = _v;
    }

    public String getAliasTreeNodeIsAncestor() {
        return aliasTreeNodeIsAncestor;
    }

    public void setAliasTreeNodeIsAncestor(String _v) {
        this.aliasTreeNodeIsAncestor = _v;
    }

    public String getAliasTreeNodeIsDescendant() {
        return aliasTreeNodeIsDescendant;
    }

    public void setAliasTreeNodeIsDescendant(String _v) {
        this.aliasTreeNodeIsDescendant = _v;
    }

    public String getAliasTree() {
        return aliasTree;
    }

    public void setAliasTree(String _v) {
        this.aliasTree = _v;
    }

    public String getAliasTreeSetRootVisible() {
        return aliasTreeSetRootVisible;
    }

    public void setAliasTreeSetRootVisible(String _v) {
        this.aliasTreeSetRootVisible = _v;
    }

    public String getAliasTreeIsRootVisible() {
        return aliasTreeIsRootVisible;
    }

    public void setAliasTreeIsRootVisible(String _v) {
        this.aliasTreeIsRootVisible = _v;
    }

    public String getAliasTreeGetSelected() {
        return aliasTreeGetSelected;
    }

    public void setAliasTreeGetSelected(String _v) {
        this.aliasTreeGetSelected = _v;
    }

    public String getAliasTreeAddTreeListener() {
        return aliasTreeAddTreeListener;
    }

    public void setAliasTreeAddTreeListener(String _v) {
        this.aliasTreeAddTreeListener = _v;
    }

    public String getAliasTreeReload() {
        return aliasTreeReload;
    }

    public void setAliasTreeReload(String _v) {
        this.aliasTreeReload = _v;
    }

    public String getAliasTableListener() {
        return aliasTableListener;
    }

    public void setAliasTableListener(String _v) {
        this.aliasTableListener = _v;
    }

    public String getAliasTableValueTableChanged() {
        return aliasTableValueTableChanged;
    }

    public void setAliasTableValueTableChanged(String _v) {
        this.aliasTableValueTableChanged = _v;
    }

    public String getAliasTableGui() {
        return aliasTableGui;
    }

    public void setAliasTableGui(String _v) {
        this.aliasTableGui = _v;
    }

    public String getAliasTableGetSelectedRow() {
        return aliasTableGetSelectedRow;
    }

    public void setAliasTableGetSelectedRow(String _v) {
        this.aliasTableGetSelectedRow = _v;
    }

    public String getAliasTableGetSelectedRows() {
        return aliasTableGetSelectedRows;
    }

    public void setAliasTableGetSelectedRows(String _v) {
        this.aliasTableGetSelectedRows = _v;
    }

    public String getAliasTableGetSelectedRowCount() {
        return aliasTableGetSelectedRowCount;
    }

    public void setAliasTableGetSelectedRowCount(String _v) {
        this.aliasTableGetSelectedRowCount = _v;
    }

    public String getAliasTableGetRowCount() {
        return aliasTableGetRowCount;
    }

    public void setAliasTableGetRowCount(String _v) {
        this.aliasTableGetRowCount = _v;
    }

    public String getAliasTableSetRowCount() {
        return aliasTableSetRowCount;
    }

    public void setAliasTableSetRowCount(String _v) {
        this.aliasTableSetRowCount = _v;
    }

    public String getAliasTableGetColumnCount() {
        return aliasTableGetColumnCount;
    }

    public void setAliasTableGetColumnCount(String _v) {
        this.aliasTableGetColumnCount = _v;
    }

    public String getAliasTableSetColumns() {
        return aliasTableSetColumns;
    }

    public void setAliasTableSetColumns(String _v) {
        this.aliasTableSetColumns = _v;
    }

    public String getAliasTableGetColumnName() {
        return aliasTableGetColumnName;
    }

    public void setAliasTableGetColumnName(String _v) {
        this.aliasTableGetColumnName = _v;
    }

    public String getAliasTableGetValue() {
        return aliasTableGetValue;
    }

    public void setAliasTableGetValue(String _v) {
        this.aliasTableGetValue = _v;
    }

    public String getAliasTableSetValue() {
        return aliasTableSetValue;
    }

    public void setAliasTableSetValue(String _v) {
        this.aliasTableSetValue = _v;
    }

    public String getAliasTableGetRowAtPoint() {
        return aliasTableGetRowAtPoint;
    }

    public void setAliasTableGetRowAtPoint(String _v) {
        this.aliasTableGetRowAtPoint = _v;
    }

    public String getAliasTableGetColumnAtPoint() {
        return aliasTableGetColumnAtPoint;
    }

    public void setAliasTableGetColumnAtPoint(String _v) {
        this.aliasTableGetColumnAtPoint = _v;
    }

    public String getAliasTableIsMultiple() {
        return aliasTableIsMultiple;
    }

    public void setAliasTableIsMultiple(String _v) {
        this.aliasTableIsMultiple = _v;
    }

    public String getAliasTableSetMultiple() {
        return aliasTableSetMultiple;
    }

    public void setAliasTableSetMultiple(String _v) {
        this.aliasTableSetMultiple = _v;
    }

    public String getAliasTableIsReorder() {
        return aliasTableIsReorder;
    }

    public void setAliasTableIsReorder(String _v) {
        this.aliasTableIsReorder = _v;
    }

    public String getAliasTableSetReorder() {
        return aliasTableSetReorder;
    }

    public void setAliasTableSetReorder(String _v) {
        this.aliasTableSetReorder = _v;
    }

    public String getAliasTableMoveColumn() {
        return aliasTableMoveColumn;
    }

    public void setAliasTableMoveColumn(String _v) {
        this.aliasTableMoveColumn = _v;
    }

    public String getAliasTableAddInterval() {
        return aliasTableAddInterval;
    }

    public void setAliasTableAddInterval(String _v) {
        this.aliasTableAddInterval = _v;
    }

    public String getAliasTableRemoveInterval() {
        return aliasTableRemoveInterval;
    }

    public void setAliasTableRemoveInterval(String _v) {
        this.aliasTableRemoveInterval = _v;
    }

    public String getAliasTableApplyChanges() {
        return aliasTableApplyChanges;
    }

    public void setAliasTableApplyChanges(String _v) {
        this.aliasTableApplyChanges = _v;
    }

    public String getAliasTableAddHeader() {
        return aliasTableAddHeader;
    }

    public void setAliasTableAddHeader(String _v) {
        this.aliasTableAddHeader = _v;
    }

    public String getAliasTableAddSelect() {
        return aliasTableAddSelect;
    }

    public void setAliasTableAddSelect(String _v) {
        this.aliasTableAddSelect = _v;
    }

    public String getAliasConfirm() {
        return aliasConfirm;
    }

    public void setAliasConfirm(String _v) {
        this.aliasConfirm = _v;
    }

    public String getAliasConfirmMessage() {
        return aliasConfirmMessage;
    }

    public void setAliasConfirmMessage(String _v) {
        this.aliasConfirmMessage = _v;
    }

    public String getAliasConfirmField() {
        return aliasConfirmField;
    }

    public void setAliasConfirmField(String _v) {
        this.aliasConfirmField = _v;
    }

    public String getAliasConfirmOk() {
        return aliasConfirmOk;
    }

    public void setAliasConfirmOk(String _v) {
        this.aliasConfirmOk = _v;
    }

    public String getAliasConfirmYesNo() {
        return aliasConfirmYesNo;
    }

    public void setAliasConfirmYesNo(String _v) {
        this.aliasConfirmYesNo = _v;
    }

    public String getAliasConfirmFull() {
        return aliasConfirmFull;
    }

    public void setAliasConfirmFull(String _v) {
        this.aliasConfirmFull = _v;
    }

    public String getAliasConfirmFieldOk() {
        return aliasConfirmFieldOk;
    }

    public void setAliasConfirmFieldOk(String _v) {
        this.aliasConfirmFieldOk = _v;
    }

    public String getAliasConfirmFieldCancel() {
        return aliasConfirmFieldCancel;
    }

    public void setAliasConfirmFieldCancel(String _v) {
        this.aliasConfirmFieldCancel = _v;
    }

    public String getAliasConfirmFieldYes() {
        return aliasConfirmFieldYes;
    }

    public void setAliasConfirmFieldYes(String _v) {
        this.aliasConfirmFieldYes = _v;
    }

    public String getAliasConfirmFieldNo() {
        return aliasConfirmFieldNo;
    }

    public void setAliasConfirmFieldNo(String _v) {
        this.aliasConfirmFieldNo = _v;
    }

    public String getAliasFrame() {
        return aliasFrame;
    }

    public void setAliasFrame(String _v) {
        this.aliasFrame = _v;
    }

    public String getAliasDialog() {
        return aliasDialog;
    }

    public void setAliasDialog(String _v) {
        this.aliasDialog = _v;
    }

    public String getAliasDialogIsModal() {
        return aliasDialogIsModal;
    }

    public void setAliasDialogIsModal(String _v) {
        this.aliasDialogIsModal = _v;
    }

    public String getAliasDialogSetModal() {
        return aliasDialogSetModal;
    }

    public void setAliasDialogSetModal(String _v) {
        this.aliasDialogSetModal = _v;
    }

    public String getAliasWindowType() {
        return aliasWindowType;
    }

    public void setAliasWindowType(String _v) {
        this.aliasWindowType = _v;
    }

    public String getAliasWindowTypeRelative() {
        return aliasWindowTypeRelative;
    }

    public void setAliasWindowTypeRelative(String _v) {
        this.aliasWindowTypeRelative = _v;
    }

    public String getAliasRemoveWindowListener() {
        return aliasRemoveWindowListener;
    }

    public void setAliasRemoveWindowListener(String _v) {
        this.aliasRemoveWindowListener = _v;
    }

    public String getAliasGetWindowListeners() {
        return aliasGetWindowListeners;
    }

    public void setAliasGetWindowListeners(String _v) {
        this.aliasGetWindowListeners = _v;
    }

    public String getAliasPanel() {
        return aliasPanel;
    }

    public void setAliasPanel(String _v) {
        this.aliasPanel = _v;
    }

    public String getAliasPanelBorder() {
        return aliasPanelBorder;
    }

    public void setAliasPanelBorder(String _v) {
        this.aliasPanelBorder = _v;
    }

    public String getAliasPanelBorderNorth() {
        return aliasPanelBorderNorth;
    }

    public void setAliasPanelBorderNorth(String _v) {
        this.aliasPanelBorderNorth = _v;
    }

    public String getAliasPanelBorderSouth() {
        return aliasPanelBorderSouth;
    }

    public void setAliasPanelBorderSouth(String _v) {
        this.aliasPanelBorderSouth = _v;
    }

    public String getAliasPanelBorderEast() {
        return aliasPanelBorderEast;
    }

    public void setAliasPanelBorderEast(String _v) {
        this.aliasPanelBorderEast = _v;
    }

    public String getAliasPanelBorderWest() {
        return aliasPanelBorderWest;
    }

    public void setAliasPanelBorderWest(String _v) {
        this.aliasPanelBorderWest = _v;
    }

    public String getAliasPanelBorderCenter() {
        return aliasPanelBorderCenter;
    }

    public void setAliasPanelBorderCenter(String _v) {
        this.aliasPanelBorderCenter = _v;
    }

    public String getAliasPanelBorderBeforeFirst() {
        return aliasPanelBorderBeforeFirst;
    }

    public void setAliasPanelBorderBeforeFirst(String _v) {
        this.aliasPanelBorderBeforeFirst = _v;
    }

    public String getAliasPanelBorderBeforeLineBegins() {
        return aliasPanelBorderBeforeLineBegins;
    }

    public void setAliasPanelBorderBeforeLineBegins(String _v) {
        this.aliasPanelBorderBeforeLineBegins = _v;
    }

    public String getAliasPanelBorderAfterLast() {
        return aliasPanelBorderAfterLast;
    }

    public void setAliasPanelBorderAfterLast(String _v) {
        this.aliasPanelBorderAfterLast = _v;
    }

    public String getAliasPanelBorderAfterLineEnds() {
        return aliasPanelBorderAfterLineEnds;
    }

    public void setAliasPanelBorderAfterLineEnds(String _v) {
        this.aliasPanelBorderAfterLineEnds = _v;
    }

    public String getAliasProgBar() {
        return aliasProgBar;
    }

    public void setAliasProgBar(String _v) {
        this.aliasProgBar = _v;
    }

    public String getAliasProgBarValue() {
        return aliasProgBarValue;
    }

    public void setAliasProgBarValue(String _v) {
        this.aliasProgBarValue = _v;
    }

    public String getAliasProgBarMax() {
        return aliasProgBarMax;
    }

    public void setAliasProgBarMax(String _v) {
        this.aliasProgBarMax = _v;
    }

    public String getAliasProgBarMin() {
        return aliasProgBarMin;
    }

    public void setAliasProgBarMin(String _v) {
        this.aliasProgBarMin = _v;
    }

    public String getAliasProgBarOr() {
        return aliasProgBarOr;
    }

    public void setAliasProgBarOr(String _v) {
        this.aliasProgBarOr = _v;
    }

    public String getAliasButton() {
        return aliasButton;
    }

    public void setAliasButton(String _v) {
        this.aliasButton = _v;
    }

    public String getAliasTextLabel() {
        return aliasTextLabel;
    }

    public void setAliasTextLabel(String _v) {
        this.aliasTextLabel = _v;
    }

    public String getAliasImageLabel() {
        return aliasImageLabel;
    }

    public void setAliasImageLabel(String _v) {
        this.aliasImageLabel = _v;
    }

    public String getAliasGetFont() {
        return aliasGetFont;
    }

    public void setAliasGetFont(String _v) {
        this.aliasGetFont = _v;
    }

    public String getAliasSetFont() {
        return aliasSetFont;
    }

    public void setAliasSetFont(String _v) {
        this.aliasSetFont = _v;
    }

    public String getAliasFont() {
        return aliasFont;
    }

    public void setAliasFont(String _v) {
        this.aliasFont = _v;
    }

    public String getAliasFontGetName() {
        return aliasFontGetName;
    }

    public void setAliasFontGetName(String _v) {
        this.aliasFontGetName = _v;
    }

    public String getAliasFontGetSize() {
        return aliasFontGetSize;
    }

    public void setAliasFontGetSize(String _v) {
        this.aliasFontGetSize = _v;
    }

    public String getAliasFontIsBold() {
        return aliasFontIsBold;
    }

    public void setAliasFontIsBold(String _v) {
        this.aliasFontIsBold = _v;
    }

    public String getAliasFontIsItalic() {
        return aliasFontIsItalic;
    }

    public void setAliasFontIsItalic(String _v) {
        this.aliasFontIsItalic = _v;
    }

    public String getAliasFontStringWidth() {
        return aliasFontStringWidth;
    }

    public void setAliasFontStringWidth(String _v) {
        this.aliasFontStringWidth = _v;
    }

    public String getAliasDimension() {
        return aliasDimension;
    }

    public void setAliasDimension(String _v) {
        this.aliasDimension = _v;
    }

    public String getAliasDimensionGetHeight() {
        return aliasDimensionGetHeight;
    }

    public void setAliasDimensionGetHeight(String _v) {
        this.aliasDimensionGetHeight = _v;
    }

    public String getAliasDimensionGetWidth() {
        return aliasDimensionGetWidth;
    }

    public void setAliasDimensionGetWidth(String _v) {
        this.aliasDimensionGetWidth = _v;
    }

    public String getAliasComponent() {
        return aliasComponent;
    }

    public void setAliasComponent(String _v) {
        this.aliasComponent = _v;
    }

    public String getAliasComponentSetAutoscrolls() {
        return aliasComponentSetAutoscrolls;
    }

    public void setAliasComponentSetAutoscrolls(String _v) {
        this.aliasComponentSetAutoscrolls = _v;
    }

    public String getAliasComponentIsAutoscrolls() {
        return aliasComponentIsAutoscrolls;
    }

    public void setAliasComponentIsAutoscrolls(String _v) {
        this.aliasComponentIsAutoscrolls = _v;
    }

    public String getAliasComponentGetWidth() {
        return aliasComponentGetWidth;
    }

    public void setAliasComponentGetWidth(String _v) {
        this.aliasComponentGetWidth = _v;
    }

    public String getAliasComponentGetHeight() {
        return aliasComponentGetHeight;
    }

    public void setAliasComponentGetHeight(String _v) {
        this.aliasComponentGetHeight = _v;
    }

    public String getAliasComponentGetPreferredSize() {
        return aliasComponentGetPreferredSize;
    }

    public void setAliasComponentGetPreferredSize(String _v) {
        this.aliasComponentGetPreferredSize = _v;
    }

    public String getAliasComponentSetPreferredSize() {
        return aliasComponentSetPreferredSize;
    }

    public void setAliasComponentSetPreferredSize(String _v) {
        this.aliasComponentSetPreferredSize = _v;
    }

    public String getAliasComponentSetSize() {
        return aliasComponentSetSize;
    }

    public void setAliasComponentSetSize(String _v) {
        this.aliasComponentSetSize = _v;
    }

    public String getAliasComponentIsVisible() {
        return aliasComponentIsVisible;
    }

    public void setAliasComponentIsVisible(String _v) {
        this.aliasComponentIsVisible = _v;
    }

    public String getAliasComponentSetVisible() {
        return aliasComponentSetVisible;
    }

    public void setAliasComponentSetVisible(String _v) {
        this.aliasComponentSetVisible = _v;
    }

    public String getAliasComponentInvokeLater() {
        return aliasComponentInvokeLater;
    }

    public void setAliasComponentInvokeLater(String _v) {
        this.aliasComponentInvokeLater = _v;
    }

    public String getAliasImage() {
        return aliasImage;
    }

    public void setAliasImage(String _v) {
        this.aliasImage = _v;
    }

    public String getAliasImageGetWidth() {
        return aliasImageGetWidth;
    }

    public void setAliasImageGetWidth(String _v) {
        this.aliasImageGetWidth = _v;
    }

    public String getAliasImageGetHeight() {
        return aliasImageGetHeight;
    }

    public void setAliasImageGetHeight(String _v) {
        this.aliasImageGetHeight = _v;
    }

    public String getAliasImageGet() {
        return aliasImageGet;
    }

    public void setAliasImageGet(String _v) {
        this.aliasImageGet = _v;
    }

    public String getAliasImageSet() {
        return aliasImageSet;
    }

    public void setAliasImageSet(String _v) {
        this.aliasImageSet = _v;
    }

    public String getAliasImageIsWithAlpha() {
        return aliasImageIsWithAlpha;
    }

    public void setAliasImageIsWithAlpha(String _v) {
        this.aliasImageIsWithAlpha = _v;
    }

    public String getAliasImageEq() {
        return aliasImageEq;
    }

    public void setAliasImageEq(String _v) {
        this.aliasImageEq = _v;
    }

    public String getAliasImageGetColor() {
        return aliasImageGetColor;
    }

    public void setAliasImageGetColor(String _v) {
        this.aliasImageGetColor = _v;
    }

    public String getAliasImageSetColor() {
        return aliasImageSetColor;
    }

    public void setAliasImageSetColor(String _v) {
        this.aliasImageSetColor = _v;
    }

    public String getAliasImageGetFont() {
        return aliasImageGetFont;
    }

    public void setAliasImageGetFont(String _v) {
        this.aliasImageGetFont = _v;
    }

    public String getAliasImageSetFont() {
        return aliasImageSetFont;
    }

    public void setAliasImageSetFont(String _v) {
        this.aliasImageSetFont = _v;
    }

    public String getAliasImageDraw() {
        return aliasImageDraw;
    }

    public void setAliasImageDraw(String _v) {
        this.aliasImageDraw = _v;
    }

    public String getAliasImageDrawLine() {
        return aliasImageDrawLine;
    }

    public void setAliasImageDrawLine(String _v) {
        this.aliasImageDrawLine = _v;
    }

    public String getAliasImageDrawRect() {
        return aliasImageDrawRect;
    }

    public void setAliasImageDrawRect(String _v) {
        this.aliasImageDrawRect = _v;
    }

    public String getAliasImageDrawOval() {
        return aliasImageDrawOval;
    }

    public void setAliasImageDrawOval(String _v) {
        this.aliasImageDrawOval = _v;
    }

    public String getAliasImageFillRect() {
        return aliasImageFillRect;
    }

    public void setAliasImageFillRect(String _v) {
        this.aliasImageFillRect = _v;
    }

    public String getAliasImageFillOval() {
        return aliasImageFillOval;
    }

    public void setAliasImageFillOval(String _v) {
        this.aliasImageFillOval = _v;
    }

    public String getAliasImageDrawPolygon() {
        return aliasImageDrawPolygon;
    }

    public void setAliasImageDrawPolygon(String _v) {
        this.aliasImageDrawPolygon = _v;
    }

    public String getAliasImageFillPolygon() {
        return aliasImageFillPolygon;
    }

    public void setAliasImageFillPolygon(String _v) {
        this.aliasImageFillPolygon = _v;
    }

    public String getAliasImageDispose() {
        return aliasImageDispose;
    }

    public void setAliasImageDispose(String _v) {
        this.aliasImageDispose = _v;
    }

    public String getAliasColor() {
        return aliasColor;
    }

    public void setAliasColor(String _v) {
        this.aliasColor = _v;
    }

    public String getAliasColorRed() {
        return aliasColorRed;
    }

    public void setAliasColorRed(String _v) {
        this.aliasColorRed = _v;
    }

    public String getAliasColorGreen() {
        return aliasColorGreen;
    }

    public void setAliasColorGreen(String _v) {
        this.aliasColorGreen = _v;
    }

    public String getAliasColorBlue() {
        return aliasColorBlue;
    }

    public void setAliasColorBlue(String _v) {
        this.aliasColorBlue = _v;
    }

    public String getAliasColorAlpha() {
        return aliasColorAlpha;
    }

    public void setAliasColorAlpha(String _v) {
        this.aliasColorAlpha = _v;
    }

    public String getAliasColorIsTransparent() {
        return aliasColorIsTransparent;
    }

    public void setAliasColorIsTransparent(String _v) {
        this.aliasColorIsTransparent = _v;
    }

    public String getAliasComponentSetPaint() {
        return aliasComponentSetPaint;
    }

    public void setAliasComponentSetPaint(String _v) {
        this.aliasComponentSetPaint = _v;
    }

    public String getAliasComponentGetPaint() {
        return aliasComponentGetPaint;
    }

    public void setAliasComponentGetPaint(String _v) {
        this.aliasComponentGetPaint = _v;
    }

    public String getAliasComponentRepaint() {
        return aliasComponentRepaint;
    }

    public void setAliasComponentRepaint(String _v) {
        this.aliasComponentRepaint = _v;
    }

    public String getAliasSetContent() {
        return aliasSetContent;
    }

    public void setAliasSetContent(String _v) {
        this.aliasSetContent = _v;
    }

    public String getAliasAddCompo() {
        return aliasAddCompo;
    }

    public void setAliasAddCompo(String _v) {
        this.aliasAddCompo = _v;
    }

    public String getAliasRemoveAll() {
        return aliasRemoveAll;
    }

    public void setAliasRemoveAll(String _v) {
        aliasRemoveAll = _v;
    }

    public String getAliasGetParentCompo() {
        return aliasGetParentCompo;
    }

    public void setAliasGetParentCompo(String _v) {
        this.aliasGetParentCompo = _v;
    }

    public String getAliasGetNextCompo() {
        return aliasGetNextCompo;
    }

    public void setAliasGetNextCompo(String _v) {
        this.aliasGetNextCompo = _v;
    }

    public String getAliasGetIndexCompo() {
        return aliasGetIndexCompo;
    }

    public void setAliasGetIndexCompo(String _v) {
        this.aliasGetIndexCompo = _v;
    }

    public String getAliasPanelFlow() {
        return aliasPanelFlow;
    }

    public void setAliasPanelFlow(String _v) {
        this.aliasPanelFlow = _v;
    }

    public String getAliasPanelAbsolute() {
        return aliasPanelAbsolute;
    }

    public void setAliasPanelAbsolute(String _v) {
        this.aliasPanelAbsolute = _v;
    }

    public String getAliasPanelGrid() {
        return aliasPanelGrid;
    }

    public void setAliasPanelGrid(String _v) {
        this.aliasPanelGrid = _v;
    }

    public String getAliasPanelPageBox() {
        return aliasPanelPageBox;
    }

    public void setAliasPanelPageBox(String _v) {
        this.aliasPanelPageBox = _v;
    }

    public String getAliasPanelValidate() {
        return aliasPanelValidate;
    }

    public void setAliasPanelValidate(String _v) {
        this.aliasPanelValidate = _v;
    }

    public String getAliasTabbedPane() {
        return aliasTabbedPane;
    }

    public void setAliasTabbedPane(String _v) {
        this.aliasTabbedPane = _v;
    }

    public String getAliasTabbedPaneNb() {
        return aliasTabbedPaneNb;
    }

    public void setAliasTabbedPaneNb(String _v) {
        this.aliasTabbedPaneNb = _v;
    }

    public String getAliasTabbedPaneAdd() {
        return aliasTabbedPaneAdd;
    }

    public void setAliasTabbedPaneAdd(String _v) {
        this.aliasTabbedPaneAdd = _v;
    }

    public String getAliasTabbedPaneGet() {
        return aliasTabbedPaneGet;
    }

    public void setAliasTabbedPaneGet(String _v) {
        this.aliasTabbedPaneGet = _v;
    }

    public String getAliasTabbedPaneGetTitle() {
        return aliasTabbedPaneGetTitle;
    }

    public void setAliasTabbedPaneGetTitle(String _v) {
        this.aliasTabbedPaneGetTitle = _v;
    }

    public String getAliasTabbedPaneSet() {
        return aliasTabbedPaneSet;
    }

    public void setAliasTabbedPaneSet(String _v) {
        this.aliasTabbedPaneSet = _v;
    }

    public String getAliasTabbedPaneSetTitle() {
        return aliasTabbedPaneSetTitle;
    }

    public void setAliasTabbedPaneSetTitle(String _v) {
        this.aliasTabbedPaneSetTitle = _v;
    }

    public String getAliasTabbedPaneRemove() {
        return aliasTabbedPaneRemove;
    }

    public void setAliasTabbedPaneRemove(String _v) {
        this.aliasTabbedPaneRemove = _v;
    }

    public String getAliasTabbedPaneIndex() {
        return aliasTabbedPaneIndex;
    }

    public void setAliasTabbedPaneIndex(String _v) {
        this.aliasTabbedPaneIndex = _v;
    }

    public String getAliasTabbedPaneSelIndex() {
        return aliasTabbedPaneSelIndex;
    }

    public void setAliasTabbedPaneSelIndex(String _v) {
        this.aliasTabbedPaneSelIndex = _v;
    }

    public String getAliasAddListener() {
        return aliasAddListener;
    }

    public void setAliasAddListener(String _v) {
        this.aliasAddListener = _v;
    }

    public String getAliasRemoveListener() {
        return aliasRemoveListener;
    }

    public void setAliasRemoveListener(String _v) {
        this.aliasRemoveListener = _v;
    }

    public String getAliasGetListeners() {
        return aliasGetListeners;
    }

    public void setAliasGetListeners(String _v) {
        this.aliasGetListeners = _v;
    }

    public String getAliasAddWindowListener() {
        return aliasAddWindowListener;
    }

    public void setAliasAddWindowListener(String _v) {
        this.aliasAddWindowListener = _v;
    }

    public String getAliasSetLabelText() {
        return aliasSetLabelText;
    }

    public void setAliasSetLabelText(String _v) {
        this.aliasSetLabelText = _v;
    }

    public String getAliasSetLabelImage() {
        return aliasSetLabelImage;
    }

    public void setAliasSetLabelImage(String _v) {
        this.aliasSetLabelImage = _v;
    }

    public String getAliasPaint() {
        return aliasPaint;
    }

    public void setAliasPaint(String _v) {
        this.aliasPaint = _v;
    }

    public String getAliasPaintMethod() {
        return aliasPaintMethod;
    }

    public void setAliasPaintMethod(String _v) {
        this.aliasPaintMethod = _v;
    }

    public String getAliasPaintSet() {
        return aliasPaintSet;
    }

    public void setAliasPaintSet(String _v) {
        this.aliasPaintSet = _v;
    }

    public String getAliasPaintAdd() {
        return aliasPaintAdd;
    }

    public void setAliasPaintAdd(String _v) {
        this.aliasPaintAdd = _v;
    }

    public String getAliasPaintRefresh() {
        return aliasPaintRefresh;
    }

    public void setAliasPaintRefresh(String _v) {
        this.aliasPaintRefresh = _v;
    }

    public String getAliasPaintRefreshOne() {
        return aliasPaintRefreshOne;
    }

    public void setAliasPaintRefreshOne(String _v) {
        this.aliasPaintRefreshOne = _v;
    }

    public String getAliasRemoveCompo() {
        return aliasRemoveCompo;
    }

    public void setAliasRemoveCompo(String _v) {
        this.aliasRemoveCompo = _v;
    }

    public String getAliasCount() {
        return aliasCount;
    }

    public void setAliasCount(String _v) {
        this.aliasCount = _v;
    }

    public String getAliasScrollPane() {
        return aliasScrollPane;
    }

    public void setAliasScrollPane(String _v) {
        this.aliasScrollPane = _v;
    }

    public String getAliasScrollPaneGetView() {
        return aliasScrollPaneGetView;
    }

    public void setAliasScrollPaneGetView(String _v) {
        this.aliasScrollPaneGetView = _v;
    }

    public String getAliasScrollPaneSetView() {
        return aliasScrollPaneSetView;
    }

    public void setAliasScrollPaneSetView(String _v) {
        this.aliasScrollPaneSetView = _v;
    }

    public String getAliasScrollPaneHorizontalValue() {
        return aliasScrollPaneHorizontalValue;
    }

    public void setAliasScrollPaneHorizontalValue(String _v) {
        this.aliasScrollPaneHorizontalValue = _v;
    }

    public String getAliasScrollPaneVerticalValue() {
        return aliasScrollPaneVerticalValue;
    }

    public void setAliasScrollPaneVerticalValue(String _v) {
        this.aliasScrollPaneVerticalValue = _v;
    }

    public String getAliasScrollPaneValidate() {
        return aliasScrollPaneValidate;
    }

    public void setAliasScrollPaneValidate(String _v) {
        this.aliasScrollPaneValidate = _v;
    }

    public String getAliasSplitPane() {
        return aliasSplitPane;
    }

    public void setAliasSplitPane(String _v) {
        this.aliasSplitPane = _v;
    }

    public String getAliasSplitPaneGetDividerLocation() {
        return aliasSplitPaneGetDividerLocation;
    }

    public void setAliasSplitPaneGetDividerLocation(String _v) {
        this.aliasSplitPaneGetDividerLocation = _v;
    }

    public String getAliasSplitPaneSetDividerLocation() {
        return aliasSplitPaneSetDividerLocation;
    }

    public void setAliasSplitPaneSetDividerLocation(String _v) {
        this.aliasSplitPaneSetDividerLocation = _v;
    }

    public String getAliasSplitPaneGetDividerSize() {
        return aliasSplitPaneGetDividerSize;
    }

    public void setAliasSplitPaneGetDividerSize(String _v) {
        this.aliasSplitPaneGetDividerSize = _v;
    }

    public String getAliasSplitPaneSetDividerSize() {
        return aliasSplitPaneSetDividerSize;
    }

    public void setAliasSplitPaneSetDividerSize(String _v) {
        this.aliasSplitPaneSetDividerSize = _v;
    }

    public String getAliasSplitPaneIsContinuousLayout() {
        return aliasSplitPaneIsContinuousLayout;
    }

    public void setAliasSplitPaneIsContinuousLayout(String _v) {
        this.aliasSplitPaneIsContinuousLayout = _v;
    }

    public String getAliasSplitPaneSetContinuousLayout() {
        return aliasSplitPaneSetContinuousLayout;
    }

    public void setAliasSplitPaneSetContinuousLayout(String _v) {
        this.aliasSplitPaneSetContinuousLayout = _v;
    }

    public String getAliasSplitPaneIsOneTouchExpandable() {
        return aliasSplitPaneIsOneTouchExpandable;
    }

    public void setAliasSplitPaneIsOneTouchExpandable(String _v) {
        this.aliasSplitPaneIsOneTouchExpandable = _v;
    }

    public String getAliasSplitPaneSetOneTouchExpandable() {
        return aliasSplitPaneSetOneTouchExpandable;
    }

    public void setAliasSplitPaneSetOneTouchExpandable(String _v) {
        this.aliasSplitPaneSetOneTouchExpandable = _v;
    }

    public String getAliasSplitPaneGetLeft() {
        return aliasSplitPaneGetLeft;
    }

    public void setAliasSplitPaneGetLeft(String _v) {
        this.aliasSplitPaneGetLeft = _v;
    }

    public String getAliasSplitPaneSetLeft() {
        return aliasSplitPaneSetLeft;
    }

    public void setAliasSplitPaneSetLeft(String _v) {
        this.aliasSplitPaneSetLeft = _v;
    }

    public String getAliasSplitPaneGetRight() {
        return aliasSplitPaneGetRight;
    }

    public void setAliasSplitPaneGetRight(String _v) {
        this.aliasSplitPaneGetRight = _v;
    }

    public String getAliasSplitPaneSetRight() {
        return aliasSplitPaneSetRight;
    }

    public void setAliasSplitPaneSetRight(String _v) {
        this.aliasSplitPaneSetRight = _v;
    }

    public String getAliasSplitPaneValidate() {
        return aliasSplitPaneValidate;
    }

    public void setAliasSplitPaneValidate(String _v) {
        this.aliasSplitPaneValidate = _v;
    }

    public String getAliasPack() {
        return aliasPack;
    }

    public void setAliasPack(String _v) {
        this.aliasPack = _v;
    }

    public String getAliasDispose() {
        return aliasDispose;
    }

    public void setAliasDispose(String _v) {
        this.aliasDispose = _v;
    }

    public String getAliasIsVisible() {
        return aliasIsVisible;
    }

    public void setAliasIsVisible(String _v) {
        this.aliasIsVisible = _v;
    }

    public String getAliasSetVisible() {
        return aliasSetVisible;
    }

    public void setAliasSetVisible(String _v) {
        this.aliasSetVisible = _v;
    }

    public String getAliasWindow() {
        return aliasWindow;
    }

    public void setAliasWindow(String _v) {
        this.aliasWindow = _v;
    }

    public String getAliasWindowSet() {
        return aliasWindowSet;
    }

    public void setAliasWindowSet(String _v) {
        this.aliasWindowSet = _v;
    }

    public String getAliasWindowSetAll() {
        return aliasWindowSetAll;
    }

    public void setAliasWindowSetAll(String _v) {
        this.aliasWindowSetAll = _v;
    }

    public String getAliasWindowSetAdd() {
        return aliasWindowSetAdd;
    }

    public void setAliasWindowSetAdd(String _v) {
        this.aliasWindowSetAdd = _v;
    }

    public String getAliasWindowSetContains() {
        return aliasWindowSetContains;
    }

    public void setAliasWindowSetContains(String _v) {
        this.aliasWindowSetContains = _v;
    }

    public String getAliasWindowSetRemove() {
        return aliasWindowSetRemove;
    }

    public void setAliasWindowSetRemove(String _v) {
        this.aliasWindowSetRemove = _v;
    }

    public String getAliasWindowSetSnapshot() {
        return aliasWindowSetSnapshot;
    }

    public void setAliasWindowSetSnapshot(String _v) {
        this.aliasWindowSetSnapshot = _v;
    }

    public String getAliasArgs() {
        return aliasArgs;
    }

    public void setAliasArgs(String _v) {
        this.aliasArgs = _v;
    }

    public String getAliasMouseListener() {
        return aliasMouseListener;
    }

    public void setAliasMouseListener(String _v) {
        this.aliasMouseListener = _v;
    }

    public String getAliasMouseClicked() {
        return aliasMouseClicked;
    }

    public void setAliasMouseClicked(String _v) {
        this.aliasMouseClicked = _v;
    }

    public String getAliasMousePressed() {
        return aliasMousePressed;
    }

    public void setAliasMousePressed(String _v) {
        this.aliasMousePressed = _v;
    }

    public String getAliasMouseReleased() {
        return aliasMouseReleased;
    }

    public void setAliasMouseReleased(String _v) {
        this.aliasMouseReleased = _v;
    }

    public String getAliasMouseEntered() {
        return aliasMouseEntered;
    }

    public void setAliasMouseEntered(String _v) {
        this.aliasMouseEntered = _v;
    }

    public String getAliasMouseExited() {
        return aliasMouseExited;
    }

    public void setAliasMouseExited(String _v) {
        this.aliasMouseExited = _v;
    }

    public String getAliasMouseDragged() {
        return aliasMouseDragged;
    }

    public void setAliasMouseDragged(String _v) {
        this.aliasMouseDragged = _v;
    }

    public String getAliasMouseMoved() {
        return aliasMouseMoved;
    }

    public void setAliasMouseMoved(String _v) {
        this.aliasMouseMoved = _v;
    }

    public String getAliasMouseEvent() {
        return aliasMouseEvent;
    }

    public void setAliasMouseEvent(String _v) {
        this.aliasMouseEvent = _v;
    }

    public String getAliasMouseEventGetFirst() {
        return aliasMouseEventGetFirst;
    }

    public void setAliasMouseEventGetFirst(String _v) {
        this.aliasMouseEventGetFirst = _v;
    }

    public String getAliasMouseEventGetSecond() {
        return aliasMouseEventGetSecond;
    }

    public void setAliasMouseEventGetSecond(String _v) {
        this.aliasMouseEventGetSecond = _v;
    }

    public String getAliasMouseEventGetClicks() {
        return aliasMouseEventGetClicks;
    }

    public void setAliasMouseEventGetClicks(String _v) {
        this.aliasMouseEventGetClicks = _v;
    }

    public String getAliasMouseEventIsAlt() {
        return aliasMouseEventIsAlt;
    }

    public void setAliasMouseEventIsAlt(String _v) {
        this.aliasMouseEventIsAlt = _v;
    }

    public String getAliasMouseEventIsCtrl() {
        return aliasMouseEventIsCtrl;
    }

    public void setAliasMouseEventIsCtrl(String _v) {
        this.aliasMouseEventIsCtrl = _v;
    }

    public String getAliasMouseEventIsShift() {
        return aliasMouseEventIsShift;
    }

    public void setAliasMouseEventIsShift(String _v) {
        this.aliasMouseEventIsShift = _v;
    }

    public String getAliasMouseEventIsLeft() {
        return aliasMouseEventIsLeft;
    }

    public void setAliasMouseEventIsLeft(String _v) {
        this.aliasMouseEventIsLeft = _v;
    }

    public String getAliasMouseEventIsMiddle() {
        return aliasMouseEventIsMiddle;
    }

    public void setAliasMouseEventIsMiddle(String _v) {
        this.aliasMouseEventIsMiddle = _v;
    }

    public String getAliasMouseEventIsRight() {
        return aliasMouseEventIsRight;
    }

    public void setAliasMouseEventIsRight(String _v) {
        this.aliasMouseEventIsRight = _v;
    }

    public String getAliasWheelListener() {
        return aliasWheelListener;
    }

    public void setAliasWheelListener(String _v) {
        this.aliasWheelListener = _v;
    }

    public String getAliasWheelMove() {
        return aliasWheelMove;
    }

    public void setAliasWheelMove(String _v) {
        this.aliasWheelMove = _v;
    }

    public String getAliasWheelEvent() {
        return aliasWheelEvent;
    }

    public void setAliasWheelEvent(String _v) {
        this.aliasWheelEvent = _v;
    }

    public String getAliasWheelRotatedClicks() {
        return aliasWheelRotatedClicks;
    }

    public void setAliasWheelRotatedClicks(String _v) {
        this.aliasWheelRotatedClicks = _v;
    }

    public String getAliasRequestFocus() {
        return aliasRequestFocus;
    }

    public void setAliasRequestFocus(String _v) {
        this.aliasRequestFocus = _v;
    }

    public String getAliasCompToolTip() {
        return aliasCompToolTip;
    }

    public void setAliasCompToolTip(String _v) {
        this.aliasCompToolTip = _v;
    }

    public String getAliasCompFocusable() {
        return aliasCompFocusable;
    }

    public void setAliasCompFocusable(String _v) {
        this.aliasCompFocusable = _v;
    }

    public String getAliasCompOpaque() {
        return aliasCompOpaque;
    }

    public void setAliasCompOpaque(String _v) {
        this.aliasCompOpaque = _v;
    }

    public String getAliasCompBack() {
        return aliasCompBack;
    }

    public void setAliasCompBack(String _v) {
        this.aliasCompBack = _v;
    }

    public String getAliasCompFore() {
        return aliasCompFore;
    }

    public void setAliasCompFore(String _v) {
        this.aliasCompFore = _v;
    }

    public String getAliasCompGetFirstPos() {
        return aliasCompGetFirstPos;
    }

    public void setAliasCompGetFirstPos(String _v) {
        this.aliasCompGetFirstPos = _v;
    }

    public String getAliasCompGetSecondPos() {
        return aliasCompGetSecondPos;
    }

    public void setAliasCompGetSecondPos(String _v) {
        this.aliasCompGetSecondPos = _v;
    }

    public String getAliasCompLoc() {
        return aliasCompLoc;
    }

    public void setAliasCompLoc(String _v) {
        this.aliasCompLoc = _v;
    }

    public String getAliasCompBorLine() {
        return aliasCompBorLine;
    }

    public void setAliasCompBorLine(String _v) {
        this.aliasCompBorLine = _v;
    }

    public String getAliasCompBorTitle() {
        return aliasCompBorTitle;
    }

    public void setAliasCompBorTitle(String _v) {
        this.aliasCompBorTitle = _v;
    }

    public String getAliasCompBorLower() {
        return aliasCompBorLower;
    }

    public void setAliasCompBorLower(String _v) {
        this.aliasCompBorLower = _v;
    }

    public String getAliasCompBorRaise() {
        return aliasCompBorRaise;
    }

    public void setAliasCompBorRaise(String _v) {
        this.aliasCompBorRaise = _v;
    }

    public String getAliasAddKeyListener() {
        return aliasAddKeyListener;
    }

    public void setAliasAddKeyListener(String _v) {
        this.aliasAddKeyListener = _v;
    }

    public String getAliasRemoveKeyListener() {
        return aliasRemoveKeyListener;
    }

    public void setAliasRemoveKeyListener(String _v) {
        this.aliasRemoveKeyListener = _v;
    }

    public String getAliasGetKeyListeners() {
        return aliasGetKeyListeners;
    }

    public void setAliasGetKeyListeners(String _v) {
        this.aliasGetKeyListeners = _v;
    }

    public String getAliasAddWheelListener() {
        return aliasAddWheelListener;
    }

    public void setAliasAddWheelListener(String _v) {
        this.aliasAddWheelListener = _v;
    }

    public String getAliasRemoveWheelListener() {
        return aliasRemoveWheelListener;
    }

    public void setAliasRemoveWheelListener(String _v) {
        this.aliasRemoveWheelListener = _v;
    }

    public String getAliasGetWheelListeners() {
        return aliasGetWheelListeners;
    }

    public void setAliasGetWheelListeners(String _v) {
        this.aliasGetWheelListeners = _v;
    }

    public String getAliasKeyListener() {
        return aliasKeyListener;
    }

    public void setAliasKeyListener(String _v) {
        this.aliasKeyListener = _v;
    }

    public String getAliasKeyPressed() {
        return aliasKeyPressed;
    }

    public void setAliasKeyPressed(String _v) {
        this.aliasKeyPressed = _v;
    }

    public String getAliasKeyTyped() {
        return aliasKeyTyped;
    }

    public void setAliasKeyTyped(String _v) {
        this.aliasKeyTyped = _v;
    }

    public String getAliasKeyReleased() {
        return aliasKeyReleased;
    }

    public void setAliasKeyReleased(String _v) {
        this.aliasKeyReleased = _v;
    }

    public String getAliasKeyEvent() {
        return aliasKeyEvent;
    }

    public void setAliasKeyEvent(String _v) {
        this.aliasKeyEvent = _v;
    }

    public String getAliasKeyEventChar() {
        return aliasKeyEventChar;
    }

    public void setAliasKeyEventChar(String _v) {
        this.aliasKeyEventChar = _v;
    }

    public String getAliasKeyEventCode() {
        return aliasKeyEventCode;
    }

    public void setAliasKeyEventCode(String _v) {
        this.aliasKeyEventCode = _v;
    }

    public String getAliasKeyEventIsShift() {
        return aliasKeyEventIsShift;
    }

    public void setAliasKeyEventIsShift(String _v) {
        this.aliasKeyEventIsShift = _v;
    }

    public String getAliasKeyEventIsAlt() {
        return aliasKeyEventIsAlt;
    }

    public void setAliasKeyEventIsAlt(String _v) {
        this.aliasKeyEventIsAlt = _v;
    }

    public String getAliasKeyEventIsCtrl() {
        return aliasKeyEventIsCtrl;
    }

    public void setAliasKeyEventIsCtrl(String _v) {
        this.aliasKeyEventIsCtrl = _v;
    }

    public String getAliasWindowListener() {
        return aliasWindowListener;
    }

    public void setAliasWindowListener(String _v) {
        this.aliasWindowListener = _v;
    }

    public String getAliasWindowOpened() {
        return aliasWindowOpened;
    }

    public void setAliasWindowOpened(String _v) {
        this.aliasWindowOpened = _v;
    }

    public String getAliasWindowClosed() {
        return aliasWindowClosed;
    }

    public void setAliasWindowClosed(String _v) {
        this.aliasWindowClosed = _v;
    }

    public String getAliasWindowClosing() {
        return aliasWindowClosing;
    }

    public void setAliasWindowClosing(String _v) {
        this.aliasWindowClosing = _v;
    }

    public String getAliasWindowIconified() {
        return aliasWindowIconified;
    }

    public void setAliasWindowIconified(String _v) {
        this.aliasWindowIconified = _v;
    }

    public String getAliasWindowDeiconified() {
        return aliasWindowDeiconified;
    }

    public void setAliasWindowDeiconified(String _v) {
        this.aliasWindowDeiconified = _v;
    }

    public String getAliasWindowActivated() {
        return aliasWindowActivated;
    }

    public void setAliasWindowActivated(String _v) {
        this.aliasWindowActivated = _v;
    }

    public String getAliasWindowDeactivated() {
        return aliasWindowDeactivated;
    }

    public void setAliasWindowDeactivated(String _v) {
        this.aliasWindowDeactivated = _v;
    }

    public String getAliasWindowEvent() {
        return aliasWindowEvent;
    }

    public void setAliasWindowEvent(String _v) {
        this.aliasWindowEvent = _v;
    }

    public String getAliasListSelection() {
        return aliasListSelection;
    }

    public void setAliasListSelection(String _v) {
        this.aliasListSelection = _v;
    }

    public String getAliasValueChanged() {
        return aliasValueChanged;
    }

    public void setAliasValueChanged(String _v) {
        this.aliasValueChanged = _v;
    }

    public String getAliasInput() {
        return aliasInput;
    }

    public void setAliasInput(String _v) {
        aliasInput = _v;
    }

    public String getAliasInputIsEnabled() {
        return aliasInputIsEnabled;
    }

    public void setAliasInputIsEnabled(String _v) {
        aliasInputIsEnabled = _v;
    }

    public String getAliasInputSetEnabled() {
        return aliasInputSetEnabled;
    }

    public void setAliasInputSetEnabled(String _v) {
        aliasInputSetEnabled = _v;
    }

    public String getAliasRender() {
        return aliasRender;
    }

    public void setAliasRender(String _v) {
        this.aliasRender = _v;
    }

    public String getAliasRenderGetHeight() {
        return aliasRenderGetHeight;
    }

    public void setAliasRenderGetHeight(String _v) {
        this.aliasRenderGetHeight = _v;
    }

    public String getAliasRenderSetHeight() {
        return aliasRenderSetHeight;
    }

    public void setAliasRenderSetHeight(String _v) {
        this.aliasRenderSetHeight = _v;
    }

    public String getAliasRenderGetWidth() {
        return aliasRenderGetWidth;
    }

    public void setAliasRenderGetWidth(String _v) {
        this.aliasRenderGetWidth = _v;
    }

    public String getAliasRenderSetWidth() {
        return aliasRenderSetWidth;
    }

    public void setAliasRenderSetWidth(String _v) {
        this.aliasRenderSetWidth = _v;
    }

    public String getAliasRenderGetPaint() {
        return aliasRenderGetPaint;
    }

    public void setAliasRenderGetPaint(String _v) {
        this.aliasRenderGetPaint = _v;
    }

    public String getAliasRenderSetPaint() {
        return aliasRenderSetPaint;
    }

    public void setAliasRenderSetPaint(String _v) {
        this.aliasRenderSetPaint = _v;
    }

    public String getAliasGrList() {
        return aliasGrList;
    }

    public void setAliasGrList(String _v) {
        this.aliasGrList = _v;
    }

    public String getAliasGrListAdd() {
        return aliasGrListAdd;
    }

    public void setAliasGrListAdd(String _v) {
        this.aliasGrListAdd = _v;
    }

    public String getAliasGrListSet() {
        return aliasGrListSet;
    }

    public void setAliasGrListSet(String _v) {
        this.aliasGrListSet = _v;
    }

    public String getAliasGrListGetListView() {
        return aliasGrListGetListView;
    }

    public void setAliasGrListGetListView(String _v) {
        this.aliasGrListGetListView = _v;
    }

    public String getAliasGrListGetSelectedIndexes() {
        return aliasGrListGetSelectedIndexes;
    }

    public void setAliasGrListGetSelectedIndexes(String _v) {
        this.aliasGrListGetSelectedIndexes = _v;
    }

    public String getAliasGrListSetSelectedIndexes() {
        return aliasGrListSetSelectedIndexes;
    }

    public void setAliasGrListSetSelectedIndexes(String _v) {
        this.aliasGrListSetSelectedIndexes = _v;
    }

    public String getAliasGrListClearSelection() {
        return aliasGrListClearSelection;
    }

    public void setAliasGrListClearSelection(String _v) {
        this.aliasGrListClearSelection = _v;
    }

    public String getAliasGrListUpdateGraphics() {
        return aliasGrListUpdateGraphics;
    }

    public void setAliasGrListUpdateGraphics(String _v) {
        this.aliasGrListUpdateGraphics = _v;
    }

    public String getAliasGrListGetRender() {
        return aliasGrListGetRender;
    }

    public void setAliasGrListGetRender(String _v) {
        this.aliasGrListGetRender = _v;
    }

    public String getAliasGrListSetRender() {
        return aliasGrListSetRender;
    }

    public void setAliasGrListSetRender(String _v) {
        this.aliasGrListSetRender = _v;
    }

    public String getAliasGrListGetSelections() {
        return aliasGrListGetSelections;
    }

    public void setAliasGrListGetSelections(String _v) {
        this.aliasGrListGetSelections = _v;
    }


    public String getAliasGrListAddSelection() {
        return aliasGrListAddSelection;
    }

    public void setAliasGrListAddSelection(String _v) {
        this.aliasGrListAddSelection = _v;
    }

    public String getAliasGrListRemoveSelection() {
        return aliasGrListRemoveSelection;
    }

    public void setAliasGrListRemoveSelection(String _v) {
        this.aliasGrListRemoveSelection = _v;
    }

    public String getAliasGrListGetVisibleRowCount() {
        return aliasGrListGetVisibleRowCount;
    }

    public void setAliasGrListGetVisibleRowCount(String _v) {
        this.aliasGrListGetVisibleRowCount = _v;
    }

    public String getAliasGrListSetVisibleRowCount() {
        return aliasGrListSetVisibleRowCount;
    }

    public void setAliasGrListSetVisibleRowCount(String _v) {
        this.aliasGrListSetVisibleRowCount = _v;
    }

    public String getAliasGrListClear() {
        return aliasGrListClear;
    }

    public void setAliasGrListClear(String _v) {
        this.aliasGrListClear = _v;
    }

    public String getAliasGrListRemove() {
        return aliasGrListRemove;
    }

    public void setAliasGrListRemove(String _v) {
        this.aliasGrListRemove = _v;
    }

    public String getAliasCombo() {
        return aliasCombo;
    }

    public void setAliasCombo(String _v) {
        this.aliasCombo = _v;
    }

    public String getAliasComboGetSelectedItem() {
        return aliasComboGetSelectedItem;
    }

    public void setAliasComboGetSelectedItem(String _v) {
        this.aliasComboGetSelectedItem = _v;
    }

    public String getAliasComboAddItem() {
        return aliasComboAddItem;
    }

    public void setAliasComboAddItem(String _v) {
        this.aliasComboAddItem = _v;
    }

    public String getAliasComboGetItemCount() {
        return aliasComboGetItemCount;
    }

    public void setAliasComboGetItemCount(String _v) {
        this.aliasComboGetItemCount = _v;
    }

    public String getAliasComboSelectItem() {
        return aliasComboSelectItem;
    }

    public void setAliasComboSelectItem(String _v) {
        this.aliasComboSelectItem = _v;
    }

    public String getAliasComboAddListener() {
        return aliasComboAddListener;
    }

    public void setAliasComboAddListener(String _v) {
        this.aliasComboAddListener = _v;
    }

    public String getAliasComboRemoveListener() {
        return aliasComboRemoveListener;
    }

    public void setAliasComboRemoveListener(String _v) {
        this.aliasComboRemoveListener = _v;
    }

    public String getAliasComboGetListeners() {
        return aliasComboGetListeners;
    }

    public void setAliasComboGetListeners(String _v) {
        this.aliasComboGetListeners = _v;
    }

    public String getAliasComboGetSelectedIndexes() {
        return aliasComboGetSelectedIndexes;
    }

    public void setAliasComboGetSelectedIndexes(String _v) {
        this.aliasComboGetSelectedIndexes = _v;
    }

    public String getAliasComboGetSelectedIndex() {
        return aliasComboGetSelectedIndex;
    }

    public void setAliasComboGetSelectedIndex(String _v) {
        this.aliasComboGetSelectedIndex = _v;
    }

    public String getAliasComboRemoveAllItems() {
        return aliasComboRemoveAllItems;
    }

    public void setAliasComboRemoveAllItems(String _v) {
        this.aliasComboRemoveAllItems = _v;
    }

    public String getAliasComboRemoveItem() {
        return aliasComboRemoveItem;
    }

    public void setAliasComboRemoveItem(String _v) {
        this.aliasComboRemoveItem = _v;
    }

    public String getAliasButtonGroup() {
        return aliasButtonGroup;
    }

    public void setAliasButtonGroup(String _v) {
        this.aliasButtonGroup = _v;
    }

    public String getAliasButtonGroupAdd() {
        return aliasButtonGroupAdd;
    }

    public void setAliasButtonGroupAdd(String _v) {
        this.aliasButtonGroupAdd = _v;
    }

    public String getAliasRadio() {
        return aliasRadio;
    }

    public void setAliasRadio(String _v) {
        this.aliasRadio = _v;
    }

    public String getAliasRadioIsSelected() {
        return aliasRadioIsSelected;
    }

    public void setAliasRadioIsSelected(String _v) {
        this.aliasRadioIsSelected = _v;
    }

    public String getAliasRadioSetSelected() {
        return aliasRadioSetSelected;
    }

    public void setAliasRadioSetSelected(String _v) {
        this.aliasRadioSetSelected = _v;
    }

    public String getAliasRadioGetText() {
        return aliasRadioGetText;
    }

    public void setAliasRadioGetText(String _v) {
        this.aliasRadioGetText = _v;
    }

    public String getAliasRadioSetText() {
        return aliasRadioSetText;
    }

    public void setAliasRadioSetText(String _v) {
        this.aliasRadioSetText = _v;
    }

    public String getAliasPopupMenu() {
        return aliasPopupMenu;
    }

    public void setAliasPopupMenu(String _v) {
        this.aliasPopupMenu = _v;
    }

    public String getAliasPopupMenuAdd() {
        return aliasPopupMenuAdd;
    }

    public void setAliasPopupMenuAdd(String _v) {
        this.aliasPopupMenuAdd = _v;
    }

    public String getAliasPopupMenuGetComp() {
        return aliasPopupMenuGetComp;
    }

    public void setAliasPopupMenuGetComp(String _v) {
        this.aliasPopupMenuGetComp = _v;
    }

    public String getAliasPopupMenuRemoveComp() {
        return aliasPopupMenuRemoveComp;
    }

    public void setAliasPopupMenuRemoveComp(String _v) {
        this.aliasPopupMenuRemoveComp = _v;
    }

    public String getAliasPopupMenuNbComp() {
        return aliasPopupMenuNbComp;
    }

    public void setAliasPopupMenuNbComp(String _v) {
        this.aliasPopupMenuNbComp = _v;
    }

    public String getAliasPopupMenuAddMenu() {
        return aliasPopupMenuAddMenu;
    }

    public void setAliasPopupMenuAddMenu(String _v) {
        this.aliasPopupMenuAddMenu = _v;
    }

    public String getAliasPopupMenuGetMenu() {
        return aliasPopupMenuGetMenu;
    }

    public void setAliasPopupMenuGetMenu(String _v) {
        this.aliasPopupMenuGetMenu = _v;
    }

    public String getAliasPopupMenuRemoveMenu() {
        return aliasPopupMenuRemoveMenu;
    }

    public void setAliasPopupMenuRemoveMenu(String _v) {
        this.aliasPopupMenuRemoveMenu = _v;
    }

    public String getAliasPopupMenuNbMenu() {
        return aliasPopupMenuNbMenu;
    }

    public void setAliasPopupMenuNbMenu(String _v) {
        this.aliasPopupMenuNbMenu = _v;
    }

    public String getAliasPopupMenuShow() {
        return aliasPopupMenuShow;
    }

    public void setAliasPopupMenuShow(String _v) {
        this.aliasPopupMenuShow = _v;
    }

    public String getAliasTextField() {
        return aliasTextField;
    }

    public void setAliasTextField(String _v) {
        this.aliasTextField = _v;
    }

    public String getAliasTextFieldGetText() {
        return aliasTextFieldGetText;
    }

    public void setAliasTextFieldGetText(String _v) {
        this.aliasTextFieldGetText = _v;
    }

    public String getAliasTextFieldSetText() {
        return aliasTextFieldSetText;
    }

    public void setAliasTextFieldSetText(String _v) {
        this.aliasTextFieldSetText = _v;
    }

    public String getAliasTextFieldAuto() {
        return aliasTextFieldAuto;
    }

    public void setAliasTextFieldAuto(String _v) {
        this.aliasTextFieldAuto = _v;
    }

    public String getAliasTextFieldAddAction() {
        return aliasTextFieldAddAction;
    }

    public void setAliasTextFieldAddAction(String _v) {
        this.aliasTextFieldAddAction = _v;
    }

    public String getAliasTextFieldAddDocument() {
        return aliasTextFieldAddDocument;
    }

    public void setAliasTextFieldAddDocument(String _v) {
        this.aliasTextFieldAddDocument = _v;
    }

    public String getAliasTextFieldAddPopup() {
        return aliasTextFieldAddPopup;
    }

    public void setAliasTextFieldAddPopup(String _v) {
        this.aliasTextFieldAddPopup = _v;
    }

    public String getAliasTextArea() {
        return aliasTextArea;
    }

    public void setAliasTextArea(String _v) {
        this.aliasTextArea = _v;
    }

    public String getAliasTextAreaGetText() {
        return aliasTextAreaGetText;
    }

    public void setAliasTextAreaGetText(String _v) {
        this.aliasTextAreaGetText = _v;
    }

    public String getAliasTextAreaSetText() {
        return aliasTextAreaSetText;
    }

    public void setAliasTextAreaSetText(String _v) {
        this.aliasTextAreaSetText = _v;
    }

    public String getAliasTextAreaGetTabSize() {
        return aliasTextAreaGetTabSize;
    }

    public void setAliasTextAreaGetTabSize(String _v) {
        this.aliasTextAreaGetTabSize = _v;
    }

    public String getAliasTextAreaSetTabSize() {
        return aliasTextAreaSetTabSize;
    }

    public void setAliasTextAreaSetTabSize(String _v) {
        this.aliasTextAreaSetTabSize = _v;
    }

    public String getAliasTextAreaAppend() {
        return aliasTextAreaAppend;
    }

    public void setAliasTextAreaAppend(String _v) {
        this.aliasTextAreaAppend = _v;
    }

    public String getAliasTextAreaInsert() {
        return aliasTextAreaInsert;
    }

    public void setAliasTextAreaInsert(String _v) {
        this.aliasTextAreaInsert = _v;
    }

    public String getAliasTextAreaReplaceRange() {
        return aliasTextAreaReplaceRange;
    }

    public void setAliasTextAreaReplaceRange(String _v) {
        this.aliasTextAreaReplaceRange = _v;
    }

    public String getAliasTextAreaReplaceSelection() {
        return aliasTextAreaReplaceSelection;
    }

    public void setAliasTextAreaReplaceSelection(String _v) {
        this.aliasTextAreaReplaceSelection = _v;
    }

    public String getAliasTextAreaGetSelectedText() {
        return aliasTextAreaGetSelectedText;
    }

    public void setAliasTextAreaGetSelectedText(String _v) {
        this.aliasTextAreaGetSelectedText = _v;
    }

    public String getAliasTextAreaSetSelectionStart() {
        return aliasTextAreaSetSelectionStart;
    }

    public void setAliasTextAreaSetSelectionStart(String _v) {
        this.aliasTextAreaSetSelectionStart = _v;
    }

    public String getAliasTextAreaSetSelectionEnd() {
        return aliasTextAreaSetSelectionEnd;
    }

    public void setAliasTextAreaSetSelectionEnd(String _v) {
        this.aliasTextAreaSetSelectionEnd = _v;
    }

    public String getAliasTextAreaSelect() {
        return aliasTextAreaSelect;
    }

    public void setAliasTextAreaSelect(String _v) {
        this.aliasTextAreaSelect = _v;
    }

    public String getAliasTextAreaSelectAll() {
        return aliasTextAreaSelectAll;
    }

    public void setAliasTextAreaSelectAll(String _v) {
        this.aliasTextAreaSelectAll = _v;
    }

    public String getAliasCheckBox() {
        return aliasCheckBox;
    }

    public void setAliasCheckBox(String _v) {
        this.aliasCheckBox = _v;
    }

    public String getAliasCheckBoxGetText() {
        return aliasCheckBoxGetText;
    }

    public void setAliasCheckBoxGetText(String _v) {
        this.aliasCheckBoxGetText = _v;
    }

    public String getAliasCheckBoxSetText() {
        return aliasCheckBoxSetText;
    }

    public void setAliasCheckBoxSetText(String _v) {
        this.aliasCheckBoxSetText = _v;
    }

    public String getAliasCheckBoxIsSelected() {
        return aliasCheckBoxIsSelected;
    }

    public void setAliasCheckBoxIsSelected(String _v) {
        this.aliasCheckBoxIsSelected = _v;
    }

    public String getAliasCheckBoxSetSelected() {
        return aliasCheckBoxSetSelected;
    }

    public void setAliasCheckBoxSetSelected(String _v) {
        this.aliasCheckBoxSetSelected = _v;
    }

    public String getAliasCheckBoxAddAction() {
        return aliasCheckBoxAddAction;
    }

    public void setAliasCheckBoxAddAction(String _v) {
        this.aliasCheckBoxAddAction = _v;
    }

    public String getAliasSpinner() {
        return aliasSpinner;
    }

    public void setAliasSpinner(String _v) {
        this.aliasSpinner = _v;
    }

    public String getAliasSpinnerSetRange() {
        return aliasSpinnerSetRange;
    }

    public void setAliasSpinnerSetRange(String _v) {
        this.aliasSpinnerSetRange = _v;
    }

    public String getAliasSpinnerSetRangeValue() {
        return aliasSpinnerSetRangeValue;
    }

    public void setAliasSpinnerSetRangeValue(String _v) {
        this.aliasSpinnerSetRangeValue = _v;
    }

    public String getAliasSpinnerGetValue() {
        return aliasSpinnerGetValue;
    }

    public void setAliasSpinnerGetValue(String _v) {
        this.aliasSpinnerGetValue = _v;
    }

    public String getAliasSpinnerSetValue() {
        return aliasSpinnerSetValue;
    }

    public void setAliasSpinnerSetValue(String _v) {
        this.aliasSpinnerSetValue = _v;
    }

    public String getAliasSpinnerGetMax() {
        return aliasSpinnerGetMax;
    }

    public void setAliasSpinnerGetMax(String _v) {
        this.aliasSpinnerGetMax = _v;
    }

    public String getAliasSpinnerSetMax() {
        return aliasSpinnerSetMax;
    }

    public void setAliasSpinnerSetMax(String _v) {
        this.aliasSpinnerSetMax = _v;
    }

    public String getAliasSpinnerGetMin() {
        return aliasSpinnerGetMin;
    }

    public void setAliasSpinnerGetMin(String _v) {
        this.aliasSpinnerGetMin = _v;
    }

    public String getAliasSpinnerSetMin() {
        return aliasSpinnerSetMin;
    }

    public void setAliasSpinnerSetMin(String _v) {
        this.aliasSpinnerSetMin = _v;
    }

    public String getAliasSpinnerGetStep() {
        return aliasSpinnerGetStep;
    }

    public void setAliasSpinnerGetStep(String _v) {
        this.aliasSpinnerGetStep = _v;
    }

    public String getAliasSpinnerSetStep() {
        return aliasSpinnerSetStep;
    }

    public void setAliasSpinnerSetStep(String _v) {
        this.aliasSpinnerSetStep = _v;
    }

    public String getAliasSlider() {
        return aliasSlider;
    }

    public void setAliasSlider(String _v) {
        this.aliasSlider = _v;
    }

    public String getAliasSliderGetValue() {
        return aliasSliderGetValue;
    }

    public void setAliasSliderGetValue(String _v) {
        this.aliasSliderGetValue = _v;
    }

    public String getAliasSliderSetValue() {
        return aliasSliderSetValue;
    }

    public void setAliasSliderSetValue(String _v) {
        this.aliasSliderSetValue = _v;
    }

    public String getAliasSliderGetMax() {
        return aliasSliderGetMax;
    }

    public void setAliasSliderGetMax(String _v) {
        this.aliasSliderGetMax = _v;
    }

    public String getAliasSliderSetMax() {
        return aliasSliderSetMax;
    }

    public void setAliasSliderSetMax(String _v) {
        this.aliasSliderSetMax = _v;
    }

    public String getAliasSliderGetMin() {
        return aliasSliderGetMin;
    }

    public void setAliasSliderGetMin(String _v) {
        this.aliasSliderGetMin = _v;
    }

    public String getAliasSliderSetMin() {
        return aliasSliderSetMin;
    }

    public void setAliasSliderSetMin(String _v) {
        this.aliasSliderSetMin = _v;
    }

    public String getAliasSliderGetOrientation() {
        return aliasSliderGetOrientation;
    }

    public void setAliasSliderGetOrientation(String _v) {
        this.aliasSliderGetOrientation = _v;
    }

    public String getAliasSliderSetOrientation() {
        return aliasSliderSetOrientation;
    }

    public void setAliasSliderSetOrientation(String _v) {
        this.aliasSliderSetOrientation = _v;
    }

    public String getAliasGetMenuBar() {
        return aliasGetMenuBar;
    }

    public void setAliasGetMenuBar(String _v) {
        this.aliasGetMenuBar = _v;
    }

    public String getAliasSetMenuBar() {
        return aliasSetMenuBar;
    }

    public void setAliasSetMenuBar(String _v) {
        this.aliasSetMenuBar = _v;
    }

    public String getAliasMenuBar() {
        return aliasMenuBar;
    }

    public void setAliasMenuBar(String _v) {
        this.aliasMenuBar = _v;
    }

    public String getAliasMenuBarAdd() {
        return aliasMenuBarAdd;
    }

    public void setAliasMenuBarAdd(String _v) {
        this.aliasMenuBarAdd = _v;
    }

    public String getAliasMenuBarGet() {
        return aliasMenuBarGet;
    }

    public void setAliasMenuBarGet(String _v) {
        this.aliasMenuBarGet = _v;
    }

    public String getAliasMenuBarRemove() {
        return aliasMenuBarRemove;
    }

    public void setAliasMenuBarRemove(String _v) {
        this.aliasMenuBarRemove = _v;
    }

    public String getAliasMenuBarNb() {
        return aliasMenuBarNb;
    }

    public void setAliasMenuBarNb(String _v) {
        this.aliasMenuBarNb = _v;
    }

    public String getAliasAbsMenu() {
        return aliasAbsMenu;
    }

    public void setAliasAbsMenu(String _v) {
        this.aliasAbsMenu = _v;
    }

    public String getAliasAbsMenuGetParent() {
        return aliasAbsMenuGetParent;
    }

    public void setAliasAbsMenuGetParent(String _v) {
        this.aliasAbsMenuGetParent = _v;
    }

    public String getAliasAbsMenuIsEnabled() {
        return aliasAbsMenuIsEnabled;
    }

    public void setAliasAbsMenuIsEnabled(String _v) {
        this.aliasAbsMenuIsEnabled = _v;
    }

    public String getAliasMenu() {
        return aliasMenu;
    }

    public void setAliasMenu(String _v) {
        this.aliasMenu = _v;
    }

    public String getAliasMenuAdd() {
        return aliasMenuAdd;
    }

    public void setAliasMenuAdd(String _v) {
        this.aliasMenuAdd = _v;
    }

    public String getAliasMenuGet() {
        return aliasMenuGet;
    }

    public void setAliasMenuGet(String _v) {
        this.aliasMenuGet = _v;
    }

    public String getAliasMenuRemove() {
        return aliasMenuRemove;
    }

    public void setAliasMenuRemove(String _v) {
        this.aliasMenuRemove = _v;
    }

    public String getAliasMenuNb() {
        return aliasMenuNb;
    }

    public void setAliasMenuNb(String _v) {
        this.aliasMenuNb = _v;
    }

    public String getAliasAbsMenuSetEnabled() {
        return aliasAbsMenuSetEnabled;
    }

    public void setAliasAbsMenuSetEnabled(String _v) {
        this.aliasAbsMenuSetEnabled = _v;
    }

    public String getAliasAbsMenuSetDeepEnabled() {
        return aliasAbsMenuSetDeepEnabled;
    }

    public void setAliasAbsMenuSetDeepEnabled(String _v) {
        this.aliasAbsMenuSetDeepEnabled = _v;
    }

    public String getAliasAbsMenuGetText() {
        return aliasAbsMenuGetText;
    }

    public void setAliasAbsMenuGetText(String _v) {
        this.aliasAbsMenuGetText = _v;
    }

    public String getAliasAbsMenuSetText() {
        return aliasAbsMenuSetText;
    }

    public void setAliasAbsMenuSetText(String _v) {
        this.aliasAbsMenuSetText = _v;
    }

    public String getAliasMenuAddSeparator() {
        return aliasMenuAddSeparator;
    }

    public void setAliasMenuAddSeparator(String _v) {
        this.aliasMenuAddSeparator = _v;
    }

    public String getAliasAbsMenuItem() {
        return aliasAbsMenuItem;
    }

    public void setAliasAbsMenuItem(String _v) {
        this.aliasAbsMenuItem = _v;
    }

    public String getAliasAbsMenuItemAddAction() {
        return aliasAbsMenuItemAddAction;
    }

    public void setAliasAbsMenuItemAddAction(String _v) {
        this.aliasAbsMenuItemAddAction = _v;
    }

    public String getAliasMenuItem() {
        return aliasMenuItem;
    }

    public void setAliasMenuItem(String _v) {
        this.aliasMenuItem = _v;
    }

    public String getAliasMenuItemCheck() {
        return aliasMenuItemCheck;
    }

    public void setAliasMenuItemCheck(String _v) {
        this.aliasMenuItemCheck = _v;
    }

    public String getAliasMenuItemCheckIsSelected() {
        return aliasMenuItemCheckIsSelected;
    }

    public void setAliasMenuItemCheckIsSelected(String _v) {
        this.aliasMenuItemCheckIsSelected = _v;
    }

    public String getAliasMenuItemCheckSetSelected() {
        return aliasMenuItemCheckSetSelected;
    }

    public void setAliasMenuItemCheckSetSelected(String _v) {
        this.aliasMenuItemCheckSetSelected = _v;
    }

}
