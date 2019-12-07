import java.util.Random;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.input.*;
import javafx.animation.*;
import javafx.event.*;

import javafx.util.Duration;

public class Main extends Application{
  public static void main(String[] args){launch();}

  public void tick(Ball theBall, Player player1, Player player2, Random random){
     player1.update();
     player2.update();
     theBall.update(random, player1, player2);
  }
  public void render(GraphicsContext gc, Ball theBall, Player player1, Player player2){
     gc.clearRect(0,0,400,300);
     gc.setFill(Color.WHITE);
     gc.fillRect(theBall.x,theBall.y,10,10);
     gc.fillRect(player1.x,player1.y,5,40);
     gc.fillRect(player2.x,player2.y,5,40);
  }

  public void start(Stage stage){
     stage.setTitle("Pong");
     stage.setResizable(false);

     Group root = new Group();
     //creates the group object that will manage our main visual elements.
     Canvas canvas = new Canvas(400,300);
     /*
     / creates the canvas object that will manage our pixel placements on screen.
     /  - the integers in the canvas constructor call represent the canvas' dimension in pixels. form (x , y)
     */
     root.getChildren().add(canvas);
     //adds the canvas to root
     Scene scene = new Scene( root, Color.BLACK );
     /*
     / creates the scene object which will be used to render the root object onto the screen.
     / - The constructor takse a group that it will render onto the screen and a color for the background
     /     in areas of the screen that do not have anything else rendered onto them.
     */
     stage.setScene(scene);

     GraphicsContext gc = canvas.getGraphicsContext2D();
     //creates a graphics context for the canvas. This allows us to draw on the Canvas
     Random random = new Random();

     Ball theBall = new Ball(195,145, random);
     Player player1 = new Player(true, 125);
     Player player2 = new Player(false, 125);
     //tells the stage to use the scene as it's contents.
     scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
      if(key.getCode() == KeyCode.W){ player1.upIsPressed = true; }
      if(key.getCode() == KeyCode.S){ player1.downIsPressed = true; }
      if(key.getCode() == KeyCode.I){ player2.upIsPressed = true; }
      if(key.getCode() == KeyCode.K){ player2.downIsPressed = true; }
     });

     scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
      if(key.getCode() == KeyCode.W){ player1.upIsPressed = false; }
      if(key.getCode() == KeyCode.S){ player1.downIsPressed = false; }
      if(key.getCode() == KeyCode.I){ player2.upIsPressed = false; }
      if(key.getCode() == KeyCode.K){ player2.downIsPressed = false; }
     });

     new AnimationTimer(){
     //begins the Simulation loop.
     //These variables are used by the simulation loop:
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
     //Simulation variables should be initialized here and will last for as long as the simulation is running.

     //
         public void handle(long currentNanoTime){
           //loop variables are initialized here and will only last for one loop.
              long now = System.nanoTime();
           //
              delta += (now - lastTime) / ns;
              lastTime = now;
              while(delta >=1){
                  tick(theBall, player1, player2, random);
                  delta--;
              }

              render(gc, theBall, player1, player2);
              frames++;

              if(System.currentTimeMillis() - timer >= 1000){
                 timer += 1000;
                 System.out.println("FPS: "+ frames);
                 frames = 0;
              }
         }

     }.start();
      stage.show();
  }
}
