import java.util.*;
import javax.swing.JOptionPane;
import javafx.util.*;
    /**
     * Esta clase simula dos zonas la zona de la bodega y la zona de plan de robo
     * 
     * @author (Macana-Orjuela) 
     * @version (2021)
     */
public class Mission
    {
    // instance variables - replace the example below with your own
    private int width;
    private int length;
    private boolean isVisible;
    private boolean ok;
    private Ladron ladron;
    private Bodega bodega;
    private boolean slow;
    private ArrayList<int[][]> redoLadron;
    private ArrayList<int[][]> redoBodega;
    private ArrayList<int[][]> undoLadron;
    private ArrayList<int[][]> undoBodega;
    /**
     * Constructor for objects of class Mission
     * @param int width ingresar un valor entero para el ancho de la matriz.
     * @param int length ingresar un valor entero para el largo de la matriz.
     */
    public Mission(int width,int length)
    {
      this.width=width;
      this.length=length;      
      isVisible=true;
      redoLadron= new ArrayList<int[][]>(); 
      redoBodega= new ArrayList<int[][]>(); 
      undoLadron= new ArrayList<int[][]>();
      undoBodega= new ArrayList<int[][]>();
      ladron=new Ladron(width,length);
      bodega=new Bodega(width,length); 
      ok=true;
   }
   /**    
     * Constructor for objects of class Mission
     * @param int width ingresar un valor entero para el ancho de la matriz.
     * @param int length ingresar un valor entero para el largo de la matriz.
     * @param int[][] heights ingresar una matriz para la bodega por ejemplo {{1,2},{3,4}}
     */
   public Mission(int width,int length,int[][] heights)
    {
      this(width,length);
      bodega=new Bodega(width,length,heights); 
      
   }
   /**
      * Este método simula el almacenamiento de una caja en la bodega. 
     * 
     * @param  row   valor entero que representa la fila de la caja que quiere almacenar.
     * @param  column   valor entero que representa la column de la caja que quiere almacenar.
     * 
     */
    public void store(int row,int column){
      guardarUndo();
      bodega.store(row,column);
      if (isVisible){makeVisible();}
      ok=true;
   }
   /**
    * Este método simula el almacenamiento de una caja en la bodega.
    * @param tupla con fila y columna por ejemplo: {1,1}
    */
   public void store(int[] crate){
       guardarUndo();
       store(crate[0],crate[1]);
       if (isVisible){makeVisible();}
       ok=true;
    }
   private int[][] obtenerMatriz(int[][] m) {
    int[][] matriz= new int[m.length][m[0].length];
    for (int i=0;i<m.length;i++){
        for (int j=0;j<m[0].length;j++){
        matriz[i][j]=m[i][j];
    }
    }
    return matriz;
    }
   private void guardarUndo(){
       int[][] matrizBodega=obtenerMatriz(bodega.getMatriz());
       int[][] matrizLadron=obtenerMatriz(ladron.getMatriz());
       undoBodega.add(matrizBodega);
       undoLadron.add(matrizLadron);
    }
   /**
    * Este método copia el estado actual de la matriz de la bodega en la zona de plan de robo.
    */
   public void copy(){
       guardarUndo();
       int[][]matriz=bodega.getMatriz();
       ladron.copy(matriz);
       if (isVisible){makeVisible();}
       ok=true;
    }
    /**
     * Este método roba una caja de la bodega en la posicion indicada.
     * @param  row   valor entero que representa la fila de la caja que quiere robar.
     * @param  column   valor entero que representa la column de la caja que quiere robar.
     */
   public void steal(int row,int column){
       guardarUndo();
       int valorRobado=bodega.steal(row,column);
       ladron.steal(row,column,valorRobado);
       if (isVisible){makeVisible();}
       ok=true;
     }
    public void lento(boolean slow){
        this.slow=slow;
        ladron.lento(slow);
        bodega.lento(slow);
    }
   public void stealc3(int row,int column){
       int x=bodega.getMatriz()[row][column];
       for (int i=0;i<x-1;i++){
           int valorRobado=bodega.stealc3(row,column,1);
           ladron.stealc3(row,column);
        }
       if (isVisible){makeVisible();}
   }
   /**
     * Este método roba una caja de la bodega en la posicion indicada.
     * @param create   es una tupla que representa la posicion de la caja que quiere robar.
     */  
    public void steal(int[] create){
       steal(create[0],create[1]);
        }
   /**
      * Este método devuelve la caja robada a la bodega.
      */
   public void returnn(){
       guardarUndo();
       int row=ladron.getRow();
       int column=ladron.getColumn();
       int valorRetornado=ladron.returnn(row,column);
       bodega.returnn(row,column,valorRetornado);
       if (isVisible){makeVisible();}
       ok=true;
    }
   /**
     * Este método Reorganiza las filas de la matriz de manera ascendente.
     *@param from es la tupla desde la cual se quiere hacer el cambio de la matriz ejemplo:{5,8}
     *@param to es la tupla hasta la cual se quiere hace el cambio de la matriz ejemplo:{3,9}}
     */
   public void arrange(int[] from, int[] to){
       guardarUndo();
       int[][] matriz=bodega.getMatriz();
       int aux=matriz[from[0]-1][from[1]-1];
       bodega.setMatriz(from[0],from[1],matriz[to[0]-1][to[1]-1]);
       bodega.setMatriz(to[0],to[1],aux);
       if (isVisible){makeVisible();}
       ok=true; 
   }
   /**
    * Consultar las piezas robadas en el último plan
    * @return valor de la caja robada.
    */
   public int stolen(){
     ok=true;
     return ladron.getValorRobado() ;
   }
   /**
    * Este método Consulta las cajas que hay en bodega , mostrando su  matriz y vistas de las camaras.
    * 
    * @return  la matriz de la zona de bodega.
    */
   public int[][]warehouse(){
       int[][] matriz=bodega.getMatriz();
       bodega.mostrarMatriz();
       ok=true;
       return matriz;    
    }
    /**
    * Este método Consulta las cajas que hay en el plan de robo, mostrando su  matriz y vistas de las camaras.
    * 
    * @return  la matriz de la zona de robo.
    */
   public int[][]layout(){
    int[][] matriz=ladron.getMatriz();
    ladron.mostrarMatriz();
    ok=true;
    return matriz; 
    }
   /**
    * Hace visible el simulador.
    */
   public void makeVisible()
      {
       ladron.layout();
       bodega.warehouse();
       ok=true;
    }
   /**
    * Hace invisible el simulador.
    */
    public void makeInvisible()
    {
         ladron.makeInvisible();
         bodega.makeInvisible();
         ok=true;
     }
   /**
    * Este método termian el simulador.
    */
    public void finish(){
        System.exit(0);
   }
   public ArrayList<int[]> toSteal(){
        System.out.println("Posiciones robadas: ");
        ArrayList<int[]> robadas= ladron.getPosicionesRobadas();
        for (int i=0;i<robadas.size();i++){
            for (int j=0;j<robadas.get(0).length;j++){
                System.out.print(robadas.get(i)[j]+"  ");
            }
            System.out.println();
        }
        if (isVisible){makeVisible();}
        ok=true;
        return robadas;
    }
   /**
     * permite agrandar o achicar las imagenes
     * @param x, String "+" o "-"
     */
   public void zoom(String x){
       bodega.zoom(x);
       ladron.zoom(x);
       
       if (isVisible){makeVisible();}
       ok=true;
    } 
   /**
    * Este método retorna si la acción se pudo realizar o no.
    * 
    */
   public boolean ok(){
       return ok;
    }
   /**
     * permitir deshacer las acciones del simulador
     */
   public void undo(){
       int[][] matrizBodega=obtenerMatriz(bodega.getMatriz());
       int[][] matrizLadron=obtenerMatriz(ladron.getMatriz());
       
       bodega.copy(undoBodega.get(undoBodega.size()-1));
       ladron.copy(undoLadron.get(undoLadron.size()-1));
       
       redoLadron.add(matrizLadron);
       redoBodega.add(matrizBodega);
       
       undoLadron.remove(undoLadron.size()-1);
       undoBodega.remove(undoBodega.size()-1);
       if (isVisible){makeVisible();}
   }  
   /**
    * permitir rehacer las acciones del simulador
    */
   public void redo(){
       int[][] matrizBodega=obtenerMatriz(bodega.getMatriz());
       int[][] matrizLadron=obtenerMatriz(ladron.getMatriz());
       
       bodega.copy(redoBodega.get(redoBodega.size()-1));
       ladron.copy(redoLadron.get(redoBodega.size()-1));
       
       undoLadron.add(matrizLadron);
       undoBodega.add(matrizBodega);
       
       redoLadron.remove(redoLadron.size()-1);
       redoBodega.remove(redoBodega.size()-1);
       
       ladron.mostrarMatriz();
    }
   //metodos para probar las clases con el test 
   public Ladron getLadron(){
    return ladron;
    }
   public Bodega getBodega(){
    return bodega;
    }
   
    public void setIsVisible(boolean x){
        isVisible=x;
    }
}


