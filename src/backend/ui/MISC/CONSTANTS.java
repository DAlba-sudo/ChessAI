package MISC;

import backend.ui.UIManager;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

public class CONSTANTS {

    public final static Color STND_WHITE = Color.web("ffffff");
    public final static Color STND_BLACK = Color.web("000000");
    public final static Color STND_RED = Color.web("CC0000");

    private final static int STND_HEADER_SIZE = 30;
    private final static String STND_HEADER_FONT = "Helvetica";
    private final static int STND_LABEL_SIZE = 15;
    private final static String STND_LABEL_FONT = "Helvetica";

    public final static Font STND_HEADER = new Font(STND_HEADER_FONT, STND_HEADER_SIZE);
    public final static Font STND_LABEL = new Font(STND_LABEL_FONT, STND_LABEL_SIZE);

    public final static BackgroundFill STND_BACKGROUND_FILL = new BackgroundFill(STND_BLACK, CornerRadii.EMPTY,
            Insets.EMPTY);
    public final static BackgroundFill TF_BACKGROUND_FILL = new BackgroundFill(STND_RED, CornerRadii.EMPTY,
            Insets.EMPTY);

    public final static Insets STND_PADDING = new Insets(10);

    public final static Background STND_BUTTON_BACKGROUND = new Background(STND_BACKGROUND_FILL);
    public final static Background STND_TEXTFIELD_BACKGROUND = new Background(STND_BACKGROUND_FILL);

    private final static BorderStroke TF_ERROR_BORDER_STROKE = new BorderStroke(STND_RED, BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY, BorderStroke.DEFAULT_WIDTHS);

    public final static Border TF_ERROR_BORDER = new Border(TF_ERROR_BORDER_STROKE);
    public final static Border STND_TF_BORDER = UIManager.createTf().getBorder();
}
