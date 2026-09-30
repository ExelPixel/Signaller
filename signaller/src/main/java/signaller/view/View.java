package signaller.view;

import java.util.HashSet;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import signaller.model.Block;
import signaller.model.Model;
import signaller.model.Point;

public class View {

    private Model model;
    private Pane trackPane;
    private final Group drawingLayer = new Group();
    private final Scale zoom = new Scale(1.0, 1.0, 0.0, 0.0);
    private Point point;
    private Integer step;
    private Block firstBlock;
    private double dragAnchorX;
    private double dragAnchorY;
    private double dragStartTranslateX;
    private double dragStartTranslateY;

    private static final double MIN_ZOOM = 0.2;
    private static final double MAX_ZOOM = 5.0;

    public View(Pane pane, Model model) {
        trackPane = pane;
        this.model = model;
        step = 100;

        Rectangle viewportClip = new Rectangle();
        viewportClip.widthProperty().bind(trackPane.widthProperty());
        viewportClip.heightProperty().bind(trackPane.heightProperty());
        trackPane.setClip(viewportClip);

        drawingLayer.setMouseTransparent(true);
        drawingLayer.getTransforms().add(zoom);
        trackPane.getChildren().add(drawingLayer);
        installNavigationHandlers();
    }

    public void drawBlockset() {
        drawingLayer.getChildren().clear();
        firstBlock = model.getFirstBlock();
        point = new Point((int) trackPane.getWidth()/2, (int) trackPane.getHeight()/2);
        HashSet<Integer> traversedIDs = new HashSet<>();
        drawBlock(firstBlock, "RIGHT", traversedIDs, point);
    }

    private void drawBlock(Block currentBlock, String direction, HashSet<Integer> traversedIDs, Point refPoint) {
        if (!traversedIDs.contains(currentBlock.getId())) {
            Point newPoint = calculateEndXY(refPoint.x(), refPoint.y(), direction);
            Line line = new Line(refPoint.x(), refPoint.y(), newPoint.x(), newPoint.y());
            if (currentBlock.isOccupied() == true) {
                line.setStroke(Color.RED);
            } else {
                line.setStroke(Color.BLACK);
            }
            line.setStrokeWidth(5);
            drawingLayer.getChildren().add(line);
            traversedIDs.add(currentBlock.getId());
            Map<Block, String> neighbors = currentBlock.getNeighbors();
            neighbors.forEach((neighbor, dir) -> drawBlock(neighbor, dir, traversedIDs, newPoint));
        } else {
            assert true;
        }
    }

    private Point calculateEndXY(int x, int y, String direction) {
        switch (direction) {
            case "LEFT":
                return new Point(x - step, y);
            case "UPLEFT":
                return new Point(x - step, y - step);
            case "UP":
                return new Point(x, y - step);
            case "UPRIGHT":
                return new Point(x + step, y - step);
            case "RIGHT":
                return new Point(x + step, y);
            case "DOWNRIGHT":
                return new Point(x + step, y + step);
            case "DOWN":
                return new Point(x, y + step);
            case "DOWNLEFT":
                return new Point(x - step, y + step);
            default:
                throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }

    private void installNavigationHandlers() {
        trackPane.setOnMousePressed(event -> {
            if (event.getButton() != MouseButton.PRIMARY) {
                return;
            }
            dragAnchorX = event.getSceneX();
            dragAnchorY = event.getSceneY();
            dragStartTranslateX = drawingLayer.getTranslateX();
            dragStartTranslateY = drawingLayer.getTranslateY();
            trackPane.setCursor(Cursor.CLOSED_HAND);
        });

        trackPane.setOnMouseDragged(event -> {
            if (!event.isPrimaryButtonDown()) {
                return;
            }
            drawingLayer.setTranslateX(dragStartTranslateX + event.getSceneX() - dragAnchorX);
            drawingLayer.setTranslateY(dragStartTranslateY + event.getSceneY() - dragAnchorY);
        });

        trackPane.setOnMouseReleased(event -> trackPane.setCursor(Cursor.DEFAULT));

        trackPane.setOnScroll(event -> {
            if (event.getDeltaY() == 0) {
                return;
            }

            Point2D cursor = trackPane.sceneToLocal(event.getSceneX(), event.getSceneY());
            double oldScale = zoom.getX();
            double newScale = Math.max(MIN_ZOOM,
                    Math.min(MAX_ZOOM, oldScale * Math.pow(1.0015, event.getDeltaY())));
            double contentX = (cursor.getX() - drawingLayer.getTranslateX()) / oldScale;
            double contentY = (cursor.getY() - drawingLayer.getTranslateY()) / oldScale;

            zoom.setX(newScale);
            zoom.setY(newScale);
            drawingLayer.setTranslateX(cursor.getX() - contentX * newScale);
            drawingLayer.setTranslateY(cursor.getY() - contentY * newScale);
            event.consume();
        });
    }
}
