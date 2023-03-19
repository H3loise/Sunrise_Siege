package Model;

public class Node {
    Node parent;
    private int col;
    private int row;
    private int gCost;
    private int hCost;
    private int fCost;
    private boolean start;
    private boolean goal;
    private boolean open;
    private boolean solid;
    private boolean checked;

    public Node(int col,int row){
        this.col = col;
        this.row = row;

    }

    public void setAsSolid(){
        solid = true;
    }
    public void setAsStart(){
        start =true;
    }
    public void setAsGoal(){
        goal =true;
    }
    public void setAsOpen(){
        open = true;
    }
    public void setChecked(){
        checked =true;
    }

    public Node getParent() {
        return parent;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getgCost() {
        return gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public int getfCost() {
        return fCost;
    }

    public boolean isStart() {
        return start;
    }

    public boolean isGoal() {
        return goal;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setAsFree(){
        this.solid =false;
    }
    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public void setfCost(int i) {
        this.fCost = i;
    }
    public void setgCost(int i ){
        gCost = i;
    }

    public void setAsNonGoal() {
        this.goal = false;
    }

    public void resetState(){
        open = false;
        checked = false;
        start = false;
        goal = false;
        parent =null;
    }

}
