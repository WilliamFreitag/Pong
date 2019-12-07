import java.util.Random;

public class Ball extends Coordinate{
  int dx = 0;
  int dy = 0;
  public Ball(int x, int y, Random random){
    this.x = x;
    this.y = y;
    if (random.nextBoolean()){
      this.dx = (1 + random.nextInt(2));
    } else {
      this.dx = (1 + random.nextInt(2)) * -1;
    }
    this.dy = (-3 + random.nextInt(6));
  }
  public void update(Random random, Player player1, Player player2){
    this.handleCollisions(random, player1, player2);
    this.x += this.dx;
    this.y += this.dy;
  }
  public void handleCollisions(Random random, Player player1, Player player2){
     if(this.y <= 0 || this.y >= 290) { this.dy *= -1; }
     if((this.x >= 30 && this.x <= 35) && (this.y <= player1.y + 40 && this.y >= player1.y) ){ this.dx *= -1; }
     if((this.x >= 360 && this.x <= 365) && (this.y <= player2.y + 40 && this.y >= player2.y) ){ this.dx *= -1; }
     if(this.x <= 0 || this.x >= 390) { this.resetBall(random); }
  }
  public void resetBall(Random random){
     this.x = 195;
     this.y = 145;
     if(random.nextBoolean()){
       this.dx = (1 + random.nextInt(2));
     } else {
       this.dx = (1 + random.nextInt(2)) * -1;
     }
     this.dy = (-3 + random.nextInt(6));
  }
}
