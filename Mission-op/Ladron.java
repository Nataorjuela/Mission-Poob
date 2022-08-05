import java.util.*; 
import javax.swing.JOptionPane;
/**
 * Write a description of class Ladron here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ladron
{
    private int width;
    private int length;
    private int valorRobado;
    private int[][]matriz;
    private boolean isVisible;
    private Camara vistaLadron;
    private boolean slow;
    private int row;
    private int column;
    private ArrayList<int[]> posicionesRobadas; 
    /**
     * Constructor for objects of class Ladron
     */
    public Ladron(int width,int length)
    {
      this.width=width;
      this.length=length;      
      isVisible=false;
      matriz=new int[width][length];
      for(int i=0;i<width;i++){for(int j=0;j<length;j++){matriz[i][j]=0;}}
      mostrarMatriz();
      vistaLadron=new Camara(matriz);
      posicionesRobadas=new ArrayList<int[]>();
    }
    public void mostrarMatriz(){
        System.out.println("matriz ladron");
        for (int x=0; x <width; x++) {
             System.out.print("|");
             for (int y=0; y < length; y++) {
                 System.out.print (matriz[x][y]);
                 if (y!=matriz[x].length-1) System.out.print("\t");
                }
             System.out.println("|");
        }
    }
    public void layout(){
        vistaLadron.setX(1000);
        vistaLadron.setColor("especial_natalia2");
        vistaLadron.makeVisible(matriz);
        mostrarMatriz();
    }
    /**
     * Este método roba una caja de la bodega en la posicion indicada.
     * @param  row   valor entero que representa la fila de la caja que quiere robar.
     * @param  column   valor entero que representa la column de la caja que quiere robar.
     */
   public void steal(int row,int column,int valorRobado){
       this.valorRobado=valorRobado;
       this.row=row;
       this.column=column;
       int[] robo={row,column};
       posicionesRobadas.add(robo);
       matriz[row-1][column-1]=valorRobado;
   }
   public void stealc3(int row,int column){
       this.row=row;
       this.column=column;
       matriz[row][column]+=1;
   }
   public int returnn(int row,int column){
       int valorRobado=matriz[row-1][column-1];
       matriz[row-1][column-1]=0;
       return valorRobado;
     }
   public void zoom(String x){
       vistaLadron.zoom(x);
    }   
   public void makeInvisible(){
        vistaLadron.makeInvisible();
    }
   // GETS Y SETS
   public ArrayList<int[]> getPosicionesRobadas(){
        return posicionesRobadas;
    }
   public int getRow(){
    return row;
    }
   public int getColumn(){
    return column;
    }
   public int[][] getMatriz(){
       return matriz;
    } 
   public void copy(int[][]m){
       for(int i=0;i<m.length;i++){
        for(int k=0;k<m[0].length;k++){
            matriz[i][k]=m[i][k];
            }
        }
    }
   public int getValorRobado(){
       return valorRobado;
    }
   public void lento(boolean slow){
        this.slow=slow;
        if (slow==true){vistaLadron.makeSlow();}  
    }
}
