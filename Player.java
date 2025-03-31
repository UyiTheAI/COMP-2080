public abstract class Player{
   String name;
   PlayerColor color;

   public Player(String namePlayer, PlayerColor playerColor){
    this.name = namePlayer;
    this.color = playerColor;
   }

   protected abstract void makeMove(Board board);
}