package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;

public class Main extends Application {
    // variable
	//sada
    static int speed = 50;
    static int width = 20;
    static int height = 12;
    static int widthStart = 5;
    static int heightStart = 60;
    static int foodX = 0;
    static int foodY = 0;
    static int cornersize = 50;
    static List<SnakeBody> snake = new ArrayList<>();
    static Dir direction = Dir.right;
    static boolean gameOver = true;
    static boolean isStarted = true;
    static Random rand = new Random();
    static int count = 0;

    public enum Dir {
        left, right, up, down
    }

    public enum State {
        forward, turnRight, turnLeft;
    }

    public static class SnakeBody {
        int x;
        int y;
        State state;
        Dir dir;

        public SnakeBody(int x, int y, State state, Dir dir) {
            this.x = x;
            this.y = y;
            this.state = state;
            this.dir = dir;
        }

    }

    @SuppressWarnings("exports")
    public void start(Stage primaryStage) {
        try {
            VBox root = new VBox();
            Canvas c = new Canvas(width * cornersize + widthStart * 2, height * cornersize + heightStart + widthStart);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            new AnimationTimer() {
                long lastTick = 0;
                int locationChange = 0;

                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        return;
                    }

                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        if (isStarted && !gameOver) {
                            locationChange += 10;
                            move(gc, locationChange);
                            if (locationChange == cornersize) {
                                locationChange = 0;
                            }
                        }
                        if (gameOver && !isStarted) {
                            gameEnd(gc);
                        }
                    }
                }

            }.start();

            Scene scene = new Scene(root, width * cornersize + widthStart * 2,
                    height * cornersize + widthStart + heightStart);
            // control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if (!gameOver) {
                    if (key.getCode() == KeyCode.W) {
                        if (!snake.get(0).dir.equals(Dir.up) && !snake.get(0).dir.equals(Dir.down)) {
                            direction = Dir.up;
                        }
                    }
                    if (key.getCode() == KeyCode.A) {
                        if (!snake.get(0).dir.equals(Dir.left) && !snake.get(0).dir.equals(Dir.right)) {
                            direction = Dir.left;
                        }
                    }
                    if (key.getCode() == KeyCode.S) {
                        if (!snake.get(0).dir.equals(Dir.up) && !snake.get(0).dir.equals(Dir.down)) {
                            direction = Dir.down;
                        }
                    }
                    if (key.getCode() == KeyCode.D) {
                        if (!snake.get(0).dir.equals(Dir.left) && !snake.get(0).dir.equals(Dir.right)) {
                            direction = Dir.right;
                        }
                    }
                }
                if (key.getCode() == KeyCode.ENTER) {
                    newGame(gc);
                    gameOver = false;
                }
                if (key.getCode() == KeyCode.SPACE) {
                	if (!gameOver) {
                        if (snake.size() > 0) {
                            isStarted = true;
                        }
                	}
                }
            });

            gc.setFill(Color.RED);
            gc.setFont(new Font("", 100));
            gc.fillText("Press ENTER to Start", 100, 250);

            primaryStage.setScene(scene);
            primaryStage.setTitle("SNAKE GAME");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("exports")
    public static void gameEnd(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.setFont(new Font("", 100));
        gc.fillText("GAME OVER", 200, 250);
        gc.fillText("Press ENTER to restart", 50, 350);
    }

    @SuppressWarnings("exports")
    public static void newGame(GraphicsContext gc) {

        gc.setFill(Color.rgb(205, 115, 58));
        gc.fillRect(0, 0, width * cornersize + widthStart * 2, height * cornersize + heightStart + widthStart);
        // draw table
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.rgb(251, 193, 78));
                } else {
                    gc.setFill(Color.rgb(252, 178, 79));
                }
                gc.fillRect(widthStart + i * cornersize, heightStart + j * cornersize, cornersize, cornersize);
            }
        }

        // snake
        snake.clear();
        int randlocX = rand.nextInt(width - 4) + 2;
        int randlocY = rand.nextInt(height - 4) + 2;
        snake.add(new SnakeBody(randlocX, randlocY, State.forward, Dir.right));
        snake.add(new SnakeBody(randlocX - 1, randlocY, State.forward, Dir.right));
        snake.add(new SnakeBody(randlocX - 2, randlocY, State.forward, Dir.right));
        newFood();
        gc.setFill(Color.rgb(103, 132, 217));
        head(gc, snake.get(0).x, snake.get(0).y, 00, 0);
        for (int i = 1; i < snake.size() - 1; i++) {
            body(gc, snake.get(i).x, snake.get(i).y, snake.get(i).dir, 0, 0, snake.get(i).state);
        }
        tail(gc, snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y, snake.get(snake.size() - 1).dir, 0, 0);

        isStarted = false;
        direction = Dir.right;
        count = 0;

        gc.setFill(Color.RED);
        gc.setFont(new Font("", 100));
        gc.fillText("Press SPACE", 185 + widthStart, heightStart + 200 + heightStart);
        gc.fillText("to Start", 300 + widthStart, heightStart + 350 + heightStart);
    }

    @SuppressWarnings("exports")
    public static void move(GraphicsContext gc, int locationChange) {

        gc.setFill(Color.rgb(205, 115, 58));
        gc.fillRect(0, 0, width * cornersize + widthStart * 2, height * cornersize + heightStart + widthStart);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + count, 10, 40);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.rgb(251, 193, 78));
                } else {
                    gc.setFill(Color.rgb(252, 178, 79));
                }
                gc.fillRect(widthStart + i * cornersize, heightStart + j * cornersize, cornersize, cornersize);
            }
        }

        for (int i = snake.size() - 1; i >= 0; i--) {
            int locationX = 0;
            int locationY = 0;
            switch (snake.get(i).dir) {
                case up:
                    locationX = 0;
                    locationY = -1 * locationChange;
                    break;
                case down:
                    locationX = 0;
                    locationY = locationChange;
                    break;
                case left:
                    locationX = -1 * locationChange;
                    locationY = 0;
                    break;
                case right:
                    locationX = locationChange;
                    locationY = 0;
                    break;
                default:
                    break;
            }

            if (i == 0) {
                head(gc, snake.get(0).x, snake.get(0).y, locationX, locationY);
                if (snake.get(0).x * cornersize + locationX < 0
                        || (snake.get(0).x + 1) * cornersize + locationX > cornersize * width
                        || snake.get(0).y * cornersize + locationY < 0
                        || (snake.get(0).y + 1) * cornersize + locationY > cornersize * height) {
                    gameOver = true;
                    isStarted = false;
                }
            } else if (i == snake.size() - 1) {
                tail(gc, snake.get(i).x, snake.get(i).y, snake.get(i).dir, locationX, locationY);
            } else {
                body(gc, snake.get(i).x, snake.get(i).y, snake.get(i).dir, locationX, locationY, snake.get(i).state);
            }

            if (locationChange == cornersize) {
                if (i == 0) {
                    switch (snake.get(i).dir) {
                        case up:
                            snake.get(i).y--;
                            if (direction.equals(Dir.left)) {
                                snake.get(i).state = State.turnLeft;
                            }
                            if (direction.equals(Dir.right)) {
                                snake.get(i).state = State.turnRight;
                            }
                            if (snake.get(i).dir.equals(direction)) {
                                snake.get(i).state = State.forward;
                            }
                            break;
                        case down:
                            snake.get(i).y++;
                            if (direction.equals(Dir.right)) {
                                snake.get(i).state = State.turnLeft;
                            }
                            if (direction.equals(Dir.left)) {
                                snake.get(i).state = State.turnRight;
                            }
                            if (snake.get(i).dir.equals(direction)) {
                                snake.get(i).state = State.forward;
                            }
                            break;
                        case left:
                            snake.get(i).x--;
                            if (direction.equals(Dir.down)) {
                                snake.get(i).state = State.turnLeft;
                            }
                            if (direction.equals(Dir.up)) {
                                snake.get(i).state = State.turnRight;
                            }
                            if (snake.get(i).dir.equals(direction)) {
                                snake.get(i).state = State.forward;
                            }
                            break;
                        case right:
                            snake.get(i).x++;
                            if (direction.equals(Dir.up)) {
                                snake.get(i).state = State.turnLeft;
                            }
                            if (direction.equals(Dir.down)) {
                                snake.get(i).state = State.turnRight;
                            }
                            if (snake.get(i).dir.equals(direction)) {
                                snake.get(i).state = State.forward;
                            }
                            break;
                        default:
                            break;
                    }
                    snake.get(i).dir = direction;
                } else {
                    snake.get(i).x = snake.get(i - 1).x;
                    snake.get(i).y = snake.get(i - 1).y;
                    switch (snake.get(i).dir) {
                        case up:
                            if (snake.get(i - 1).dir.equals(Dir.left)) {
                                snake.get(i).state = State.turnLeft;
                            }
                            if (snake.get(i - 1).dir.equals(Dir.right)) {
                                snake.get(i).state = State.turnRight;
                            }
                            if (snake.get(i).dir.equals(snake.get(i - 1).dir)) {
                                snake.get(i).state = State.forward;
                            }
                            break;
                        case down:
                            if (snake.get(i - 1).dir.equals(Dir.right)) {
                                snake.get(i).state = State.turnLeft;
                            }
                            if (snake.get(i - 1).dir.equals(Dir.left)) {
                                snake.get(i).state = State.turnRight;
                            }
                            if (snake.get(i).dir.equals(snake.get(i - 1).dir)) {
                                snake.get(i).state = State.forward;
                            }
                            break;
                        case left:
                            if (snake.get(i - 1).dir.equals(Dir.down)) {
                                snake.get(i).state = State.turnLeft;
                            }
                            if (snake.get(i - 1).dir.equals(Dir.up)) {
                                snake.get(i).state = State.turnRight;
                            }
                            if (snake.get(i).dir.equals(snake.get(i - 1).dir)) {
                                snake.get(i).state = State.forward;
                            }
                            break;
                        case right:
                            if (snake.get(i - 1).dir.equals(Dir.up)) {
                                snake.get(i).state = State.turnLeft;
                            }
                            if (snake.get(i - 1).dir.equals(Dir.down)) {
                                snake.get(i).state = State.turnRight;
                            }
                            if (snake.get(i).dir.equals(snake.get(i - 1).dir)) {
                                snake.get(i).state = State.forward;
                            }
                            break;
                        default:
                            break;
                    }
                    snake.get(i).dir = snake.get(i - 1).dir;
                }
            }
        }
        gc.setFill(Color.RED);
        gc.fillOval(widthStart + foodX * cornersize + 10, heightStart + foodY * cornersize + 10, cornersize - 20,
                cornersize - 20);
        eat(gc);
    }

    @SuppressWarnings("exports")
    public static void head(GraphicsContext gc, int x, int y, int locChangeX, int locChangeY) {
        gc.setFill(Color.rgb(103, 132, 217));

        int angle = 0;

        if (snake.get(0).state.equals(State.forward)) {
            switch (snake.get(0).dir) {
                case up:
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);
                    gc.fillRect(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + cornersize / 2 + locChangeY,
                            cornersize - 10, cornersize / 2);
                    break;
                case down:
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);
                    gc.fillRect(widthStart + x * cornersize + 5 + locChangeX, heightStart + y * cornersize + locChangeY,
                            cornersize - 10, cornersize / 2);
                    break;
                case left:
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);
                    gc.fillRect(widthStart + x * cornersize + cornersize / 2 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY,
                            cornersize / 2, cornersize - 10);
                    break;
                case right:
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);
                    gc.fillRect(widthStart + x * cornersize + locChangeX, heightStart + y * cornersize + 5 + locChangeY,
                            cornersize / 2, cornersize - 10);
                    break;
                default:
                    break;
            }
        }

        if (snake.get(0).state.equals(State.turnLeft)) {
            switch (snake.get(0).dir) {
                case up:
                    if (locChangeY > -20) {
                        locChangeX = -5;
                        angle = 50;
                    } else {
                        angle = 90;
                    }
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);
                    if (locChangeY < -25) {
                        gc.fillRect(widthStart + x * cornersize + 5 + locChangeX,
                                heightStart + y * cornersize + cornersize / 2 + locChangeY,
                                cornersize - 10, -1 * (locChangeY + 25));
                    }
                    gc.fillArc(widthStart + (x - 1) * cornersize + 5, heightStart + (y - 1) * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            270, angle, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + x * cornersize - 5, heightStart + y * cornersize - 5, 5 * 2, 5 * 2, 270, 90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case down:
                    if (locChangeY < 20) {
                        locChangeX = 5;
                        angle = 50;
                    } else {
                        angle = 90;
                    }
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);
                    if (locChangeY > 25) {
                        gc.fillRect(widthStart + x * cornersize + 5 + locChangeX, heightStart + (y + 1) * cornersize,
                                cornersize - 10, locChangeY - 25);
                    }
                    gc.fillArc(widthStart + x * cornersize + 5, heightStart + y * cornersize + 5, cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            90, angle, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + (x + 1) * cornersize - 5, heightStart + (y + 1) * cornersize - 5, 5 * 2,
                            5 * 2, 90, 90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case left:
                    if (locChangeX > -20) {
                        locChangeY = 5;
                        angle = 50;
                    } else {
                        angle = 90;
                    }
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);
                    if (locChangeX < -25) {
                        gc.fillRect(widthStart + x * cornersize + cornersize / 2 + locChangeX,
                                heightStart + y * cornersize + 5 + locChangeY,
                                -1 * (locChangeX + 25), cornersize - 10);
                    }
                    gc.fillArc(widthStart + (x - 1) * cornersize + 5, heightStart + y * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            0, angle, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + x * cornersize - 5, heightStart + (y + 1) * cornersize - 5, 5 * 2, 5 * 2, 0,
                            90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case right:
                    if (locChangeX < 20) {
                        locChangeY = -5;
                        angle = 50;
                    } else {
                        angle = 90;
                    }
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);

                    gc.fillArc(widthStart + x * cornersize + 5, heightStart + (y - 1) * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            180, angle, ArcType.ROUND);
                    if (locChangeX > 25) {
                        gc.fillRect(widthStart + (x + 1) * cornersize, heightStart + y * cornersize + 5 + locChangeY,
                                locChangeX - 25, cornersize - 10);
                    }
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + (x + 1) * cornersize - 5, heightStart + y * cornersize - 5, 5 * 2, 5 * 2,
                            180, 90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                default:
                    break;
            }
        }

        if (snake.get(0).state.equals(State.turnRight)) {
            switch (snake.get(0).dir) {
                case up:
                    if (locChangeY > -20) {
                        locChangeX = 5;
                        angle = 50;
                    } else {
                        angle = 90;
                    }
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);
                    if (locChangeY < -25) {
                        gc.fillRect(widthStart + x * cornersize + 5 + locChangeX,
                                heightStart + y * cornersize + cornersize / 2 + locChangeY,
                                cornersize - 10, -1 * (locChangeY + 25));
                    }
                    gc.fillArc(widthStart + x * cornersize + 5, heightStart + (y - 1) * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            270, -1 * angle, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + (x + 1) * cornersize - 5, heightStart + y * cornersize - 5, 5 * 2, 5 * 2,
                            180, 90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case down:
                    if (locChangeY < 20) {
                        locChangeX = -5;
                        angle = 50;
                    } else {
                        angle = 90;
                    }
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);
                    if (locChangeY > 25) {
                        gc.fillRect(widthStart + x * cornersize + 5 + locChangeX, heightStart + (y + 1) * cornersize,
                                cornersize - 10, locChangeY - 25);
                    }
                    gc.fillArc(widthStart + (x - 1) * cornersize + 5, heightStart + y * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            90, -1 * angle, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + x * cornersize - 5, heightStart + (y + 1) * cornersize - 5, 5 * 2, 5 * 2, 0,
                            90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case left:
                    if (locChangeX > -20) {
                        locChangeY = -5;
                        angle = 50;
                    } else {
                        angle = 90;
                    }
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);
                    if (locChangeX < -25) {
                        gc.fillRect(widthStart + x * cornersize + cornersize / 2 + locChangeX,
                                heightStart + y * cornersize + 5 + locChangeY,
                                -1 * (locChangeX + 25), cornersize - 10);
                    }
                    gc.fillArc(widthStart + (x - 1) * cornersize + 5, heightStart + (y - 1) * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            0, -1 * angle, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + x * cornersize - 5, heightStart + y * cornersize - 5, 5 * 2, 5 * 2, 0, -90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case right:
                    if (locChangeX < 20) {
                        locChangeY = 5;
                        angle = 50;
                    } else {
                        angle = 90;
                    }
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillOval(widthStart + x * cornersize + 5 + locChangeX,
                            heightStart + y * cornersize + 5 + locChangeY, cornersize - 10,
                            cornersize - 10);

                    gc.fillArc(widthStart + x * cornersize + 5, heightStart + y * cornersize + 5, cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            180, -1 * angle, ArcType.ROUND);
                    if (locChangeX > 25) {
                        gc.fillRect(widthStart + (x + 1) * cornersize, heightStart + y * cornersize + 5 + locChangeY,
                                locChangeX - 25, cornersize - 10);
                    }
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + (x + 1) * cornersize - 5, heightStart + (y + 1) * cornersize - 5, 5 * 2,
                            5 * 2, 90, 90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                default:
                    break;
            }
        }
    }

    @SuppressWarnings("exports")
    public static void body(GraphicsContext gc, int x, int y, Dir direct, int locChangeX, int locChangeY, State s) {
        gc.setFill(Color.rgb(103, 132, 217));
        switch (direct) {
            case up:
                gc.fillRect(widthStart + x * cornersize + 5 + locChangeX, heightStart + y * cornersize + locChangeY,
                        cornersize - 10,
                        cornersize);
                break;
            case down:
                gc.fillRect(widthStart + x * cornersize + 5 + locChangeX, heightStart + y * cornersize + locChangeY,
                        cornersize - 10,
                        cornersize);
                break;
            case left:
                gc.fillRect(widthStart + x * cornersize + locChangeX, heightStart + y * cornersize + 5 + locChangeY,
                        cornersize,
                        cornersize - 10);
                break;
            case right:
                gc.fillRect(widthStart + x * cornersize + locChangeX, heightStart + y * cornersize + 5 + locChangeY,
                        cornersize,
                        cornersize - 10);
                break;
            default:
                break;
        }

        if (s.equals(State.turnLeft)) {
            switch (direct) {
                case up:
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillArc(widthStart + (x - 1) * cornersize + 5, heightStart + (y - 1) * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            270, 90, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + x * cornersize - 5, heightStart + y * cornersize - 5, 5 * 2, 5 * 2, 270, 90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case down:
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillArc(widthStart + x * cornersize + 5, heightStart + y * cornersize + 5, cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            90, 90, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + (x + 1) * cornersize - 5, heightStart + (y + 1) * cornersize - 5, 5 * 2,
                            5 * 2, 90, 90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case left:
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillArc(widthStart + (x - 1) * cornersize + 5, heightStart + y * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            0, 90, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + x * cornersize - 5, heightStart + (y + 1) * cornersize - 5, 5 * 2, 5 * 2, 0,
                            90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case right:
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillArc(widthStart + x * cornersize + 5, heightStart + (y - 1) * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            180, 90, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + (x + 1) * cornersize - 5, heightStart + y * cornersize - 5, 5 * 2, 5 * 2,
                            180, 90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                default:
                    break;
            }
        }

        if (s.equals(State.turnRight)) {
            switch (direct) {
                case up:
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillArc(widthStart + x * cornersize + 5, heightStart + (y - 1) * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            270, -1 * 90, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + (x + 1) * cornersize - 5, heightStart + y * cornersize - 5, 5 * 2, 5 * 2,
                            180, 90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case down:
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillArc(widthStart + (x - 1) * cornersize + 5, heightStart + y * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            90, -1 * 90, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + x * cornersize - 5, heightStart + (y + 1) * cornersize - 5, 5 * 2, 5 * 2, 0,
                            90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case left:
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));
                    gc.fillArc(widthStart + (x - 1) * cornersize + 5, heightStart + (y - 1) * cornersize + 5,
                            cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            0, -1 * 90, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + x * cornersize - 5, heightStart + y * cornersize - 5, 5 * 2, 5 * 2, 0, -90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                case right:
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillRect(widthStart + x * cornersize, heightStart + y * cornersize, cornersize, cornersize);
                    gc.setFill(Color.rgb(103, 132, 217));

                    gc.fillArc(widthStart + x * cornersize + 5, heightStart + y * cornersize + 5, cornersize * 2 - 10,
                            cornersize * 2 - 10,
                            180, -1 * 90, ArcType.ROUND);
                    if ((x + y) % 2 == 0) {
                        gc.setFill(Color.rgb(251, 193, 78));
                    } else {
                        gc.setFill(Color.rgb(252, 178, 79));
                    }
                    gc.fillArc(widthStart + (x + 1) * cornersize - 5, heightStart + (y + 1) * cornersize - 5, 5 * 2,
                            5 * 2, 90, 90,
                            ArcType.ROUND);
                    gc.setFill(Color.rgb(103, 132, 217));
                    break;
                default:
                    break;
            }
        }
    }

    @SuppressWarnings("exports")
    public static void tail(GraphicsContext gc, int x, int y, Dir direct, int locChangeX, int locChangeY) {
        gc.setFill(Color.rgb(103, 132, 217));
        switch (direct) {
            case up:
                gc.fillOval(widthStart + x * cornersize + 5 + locChangeX, heightStart + y * cornersize + 5 + locChangeY,
                        cornersize - 10,
                        cornersize - 10);
                gc.fillRect(widthStart + x * cornersize + 5 + locChangeX, heightStart + y * cornersize + locChangeY,
                        cornersize - 10, cornersize / 2);
                break;
            case down:
                gc.fillOval(widthStart + x * cornersize + 5 + locChangeX, heightStart + y * cornersize + 5 + locChangeY,
                        cornersize - 10,
                        cornersize - 10);
                gc.fillRect(widthStart + x * cornersize + 5 + locChangeX,
                        heightStart + y * cornersize + cornersize / 2 + locChangeY,
                        cornersize - 10, cornersize / 2);
                break;
            case left:
                gc.fillOval(widthStart + x * cornersize + 5 + locChangeX, heightStart + y * cornersize + 5 + locChangeY,
                        cornersize - 10,
                        cornersize - 10);
                gc.fillRect(widthStart + x * cornersize + locChangeX, heightStart + y * cornersize + 5 + locChangeY,
                        cornersize / 2, cornersize - 10);
                break;
            case right:
                gc.fillOval(widthStart + x * cornersize + 5 + locChangeX, heightStart + y * cornersize + 5 + locChangeY,
                        cornersize - 10,
                        cornersize - 10);
                gc.fillRect(widthStart + x * cornersize + cornersize / 2 + locChangeX,
                        heightStart + y * cornersize + 5 + locChangeY,
                        cornersize / 2, cornersize - 10);
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("exports")
    public static void eat(GraphicsContext gc) {
        if (snake.get(0).x == foodX && snake.get(0).y == foodY) {
            switch (snake.get(snake.size() - 1).dir) {
                case up:
                    if (snake.get(snake.size() - 1).state.equals(State.forward)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y + 1,
                                State.forward, Dir.up));
                    }
                    if (snake.get(snake.size() - 1).state.equals(State.turnLeft)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x - 1, snake.get(snake.size() - 1).y,
                                State.forward, Dir.right));
                    }
                    if (snake.get(snake.size() - 1).state.equals(State.turnRight)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x + 1, snake.get(snake.size() - 1).y,
                                State.forward, Dir.left));
                    }
                    break;
                case down:
                    if (snake.get(snake.size() - 1).state.equals(State.forward)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y - 1,
                                State.forward, Dir.down));
                    }
                    if (snake.get(snake.size() - 1).state.equals(State.turnLeft)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x + 1, snake.get(snake.size() - 1).y,
                                State.forward, Dir.left));
                    }
                    if (snake.get(snake.size() - 1).state.equals(State.turnRight)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x - 1, snake.get(snake.size() - 1).y,
                                State.forward, Dir.right));
                    }
                    break;
                case left:
                    if (snake.get(snake.size() - 1).state.equals(State.forward)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x + 1, snake.get(snake.size() - 1).y,
                                State.forward, Dir.left));
                    }
                    if (snake.get(snake.size() - 1).state.equals(State.turnLeft)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y + 1,
                                State.forward, Dir.up));
                    }
                    if (snake.get(snake.size() - 1).state.equals(State.turnRight)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y - 1,
                                State.forward, Dir.down));
                    }
                    break;
                case right:
                    if (snake.get(snake.size() - 1).state.equals(State.forward)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x - 1, snake.get(snake.size() - 1).y,
                                State.forward, Dir.right));
                    }
                    if (snake.get(snake.size() - 1).state.equals(State.turnLeft)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y - 1,
                                State.forward, Dir.down));
                    }
                    if (snake.get(snake.size() - 1).state.equals(State.turnRight)) {
                        snake.add(new SnakeBody(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y + 1,
                                State.forward, Dir.up));
                    }
                    break;
                default:
                    break;
            }
            count++;
            newFood();
        }

        for (int i = 3; i < snake.size(); i++) {
            if (snake.get(i).x == snake.get(0).x) {
                if (snake.get(i).y == snake.get(0).y) {
                    gameOver = true;
                    isStarted = false;
                }
            }
        }
    }

    public static void newFood() {
        again: while (true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);
            for (SnakeBody snakeBody : snake) {
                if (snakeBody.x == foodX && snakeBody.y == foodY) {
                    continue again;
                }
            }
            break;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}