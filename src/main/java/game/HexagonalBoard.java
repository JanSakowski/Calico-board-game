package game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class HexagonalBoard extends Application {
    private static final double HEX_SIZE = 50;
    private static final double HEX_HEIGHT = Math.sqrt(3) * HEX_SIZE;
    private static final double HEX_WIDTH = 2 * HEX_SIZE;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 5 * HEX_WIDTH + HEX_SIZE, 5 * HEX_HEIGHT + HEX_SIZE);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                double xOffset = row % 2 == 0 ? 0 : HEX_WIDTH / 2;
                double x = col * HEX_WIDTH + xOffset;
                double y = row * HEX_HEIGHT * 0.75;
                Button hexButton = createHexButton(x, y,row,col);
                root.getChildren().add(hexButton);
            }
        }

        primaryStage.setTitle("Hexagonal Pattern");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createHexButton(double x, double y,int row, int col) {
        Button hexButton = new Button();
        hexButton.setLayoutX(x);
        hexButton.setLayoutY(y);

        Polygon hexagon = createHexagon(x, y);
        hexagon.setFill(Color.YELLOW);
        hexagon.setStroke(Color.BLACK);

        hexButton.setBackground(null);
        hexButton.setGraphic(hexagon);

        hexButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button clicked");
            }
        });

        return hexButton;
    }

    private Polygon createHexagon(double x, double y) {
        double[] points = new double[]{
                x + HEX_SIZE, y,
                x + HEX_WIDTH, y + HEX_HEIGHT * 0.25,
                x + HEX_WIDTH, y + HEX_HEIGHT * 0.75,
                x + HEX_SIZE, y + HEX_HEIGHT,
                x, y + HEX_HEIGHT * 0.75,
                x, y + HEX_HEIGHT * 0.25
        };

        return new Polygon(points);
    }

    public static void main(String[] args) {
        launch(args);
    }
}







