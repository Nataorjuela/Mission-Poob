import java.util.*;
import javax.swing.JOptionPane;
/**
 * Write a description of class Bodega here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bodega
{
    private int width;
    private int length;
    private int[][]matriz;
    private Camara vistaBodega;
    private boolean slow;
    private boolean isVisible;
    private static final int longi=101;

    /**
     * Constructor for objects of class Bodega
     */
    public Bodega(int width,int length)
    {    
         this.width=width;
         this.length=length;      
         isVisible=false;
         matriz=new int[width][length];
         if (width<101 && width>0 && length<longi && length>0){ 
            for(int i=0;i<width;i++){
                for(int j=0;j<length;j++){
                    Random numero=new Random();
                    matriz[i][j]=numero.nextInt(10);
                }
              }
            }
         else {
                JOptionPane.showMessageDialog(null, "Por favor ingresar un valor entre 1 y 100 para width y length");
                System.exit(0); 
             }
         vistaBodega=new Camara(matriz);
    } 
    /**
     * Constructor 2 for objects of class Bodega
     */
    public Bodega(int width,int length,int[][] heights)
    {    
         this(width,length);
         matriz=heights;
    } 
    public int[][]warehouse(){
       mostrarMatriz();
       vistaBodega.setX(20);
       vistaBodega.makeVisible(matriz);
       return matriz;    
    }
    public void mostrarMatriz(){
        System.out.println("matriz bodega");
        for (int x=0; x < matriz.length; x++) {
         System.out.print("|");
         for (int y=0; y < matriz[x].length; y++) {
             System.out.print (matriz[x][y]);
             if (y!=matriz[x].length-1) System.out.print("\t");
            }
         System.out.println("|");
        }
    }
    /**
      * Este método simula el almacenamiento de una caja en la bodega. 
     * 
     * @param  row   valor entero que representa la fila de la caja que quiere almacenar.
     * @param  column   valor entero que representa la column de la caja que quiere almacenar.
     * 
     */
    public void store(int row,int column){
       if (row<longi && row>0 && column<longi && column>0){ 
        matriz[row-1][column-1]+=1; 
         }
       else {
        JOptionPane.showMessageDialog(null, "ingresa nuevamente los valores en un rango de 1 a 100");
       }
   }
      public void returnn(int row,int column,int valorRobado){
          matriz[row-1][column-1]+=valorRobado;
   }
    /**
     * Este método roba una caja de la bodega en la posicion indicada.
     * @param  row   valor entero que representa la fila de la caja que quiere robar.
     * @param  column   valor entero que representa la column de la caja que quiere robar.
     */
   public int steal(int row,int column){

       int valorRobado=matriz[row-1][column-1]-1;
       matriz[row-1][column-1]=1;
       return valorRobado;
     }
   public int stealc3(int row,int column,int x){
       matriz[row][column]=matriz[row][column]-1;
       return 1;
    }
   public void zoom(String x) {
       vistaBodega.zoom(x);
    }   
   public void makeInvisible(){
       vistaBodega.makeInvisible();
    }
   public int[][] getMatriz(){
    return matriz;
   }
   public void copy(int[][] m){
        for(int i=0;i<m.length;i++){
        for(int k=0;k<m[0].length;k++){
            matriz[i][k]=m[i][k];
            }
        }
    }
   public void setMatriz(int row,int column,int valor){
        matriz[row-1][column-1]=valor;
    }
   public void lento(boolean slow){
        this.slow=slow;
        if (slow==true ){vistaBodega.makeSlow();}
    } 
}
