public class Player extends Coordinate{
  int dy = 0;
  Boolean upIsPressed = false;
  Boolean downIsPressed = false;
  public Player(Boolean isPlayer1, int y){
    if(isPlayer1){
      this.x = 30;
    } else {
      this.x = 370;
    }
    this.y = y;
  }

  public void update(){
    if(upIsPressed){
      this.dy -=2;
      if(this.dy < -10){ this.dy = -10; }
    } else if(downIsPressed){
      this.dy +=2;
      if(this.dy > 10) { this.dy = 10; }
    } else {
      if(this.dy > 0) { this.dy -= 2; }
      if(this.dy < 0) { this.dy += 2; }
    }
    this.y += this.dy;
    this.handleCollisions();
  }

  public void handleCollisions(){
     if(this.y <= 0){
       this.y = 0;
     }
     if(this.y >= 260){
       this.y = 260;
     }
  }
}
