import java.util.*;
/**
 * Write a description of class Camara here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Camara
{
   private int width;
   private int length;
   
   private Rectangle[][] side;
   private Rectangle[][] front;
   private Rectangle[][] top;
   private int distanceX;
   private int distanceY;
   private int[][] m;
   private static final int longi=101;
   private static Rectangle fondo1;
    private static Rectangle fondo2;
   private String color; 
   private int delta;
   private int tamaño;
   private boolean slow;
   /**
    * Constructor for objects of class Camara
    */
   public Camara(int[][] m)
   {
     this.m=m; 
     delta=25;
     tamaño=20;
     width=m.length;
     length=m[0].length;
     top=new Rectangle[width][length];
     side=new Rectangle[width][longi-1];
     front=new Rectangle[width][longi-1];
     fondo1= new Rectangle();
     fondo2= new Rectangle();
     fondo1.changeSize(800,800);
     fondo2.changeSize(800,800);     
     fondo1.changeColor("especial_natalia");
     fondo2.changeColor("especial_diego2");
     fondo2.moveHorizontal(900);
     //fondo1.makeVisible();
     //fondo2.makeVisible();
     distanceX=20;
     color="especial_diego";
   }
   public void makeSlow(){
       System.out.println("llegue hasta aca");
       this.slow=true;
       
    }
   public void setX(int valor){
       distanceX=valor;
   }
   public void setColor(String c){
       color=c;
    }
   private void side(){
       int[] maximos=new int[length];
       int max=0;
       int maxi=0;
       distanceX+=(delta*length)+10;
       for(int i=0;i<length;i++){
           for(int j=0;j<width;j++){
               if(max<m[i][j]){
                   max=m[i][j];
               }
           }
           maximos[i]=max;
           if(maxi<max){
               maxi=max;
           }
           max=0;
       }
       int distanceY=delta*maxi;
       for(int k=0;k<length;k++){
          for(int h=0;h<maximos[k];h++){
              int r1 = (int) (Math.random()*0+maximos[k]);
              
              side[k][h]=new Rectangle();
              side[k][h].changeColor(color);
              if (h==0){
                  side[k][h].tipos("heavy");
                };
              if (h==maximos[k]-1){
                  side[k][h].tipos("delicate");
              };
              if (r1==k){side[k][h].tipos("oro");}
              if (slow==true){side[k][h].lento();}
              side[k][h].changeSize(tamaño,tamaño);
              
              side[k][h].makeVisible();
              side[k][h].moveVertical(distanceY);
              distanceY=distanceY-delta; 
              side[k][h].moveHorizontal(distanceX); 
           }
          distanceX+=delta; 
          distanceY=delta*maxi;
        }
   }
    private void front(){
        
        int[] maximos=new int[length];
        int max=0;
        int maxi=0;        
        for(int i=0;i<length;i++){
            for(int j=0;j<width;j++){
                if(max<m[j][i]){
                    max=m[j][i];
                }
            }
            maximos[i]=max;
            if(maxi<max){
                maxi=max;
            }
            max=0;
        }
        int distanceY=delta*maxi;
        for(int k=0;k<length;k++){
            
           for(int h=0;h<maximos[k];h++){
               int r1 = (int) (Math.random()*0+maximos[k]);
               
               front[k][h]=new Rectangle();
               if (slow==true){front[k][h].lento();}
               front[k][h].changeSize(tamaño,tamaño);
               front[k][h].changeColor(color);
               if (h==0){
                  front[k][h].tipos("heavy");
                };
               if (h==maximos[k]-1){
                  front[k][h].tipos("delicate");
               };
               if (r1==k){front[k][h].tipos("oro");}
               front[k][h].makeVisible();
               front[k][h].moveVertical(distanceY);
               distanceY=distanceY-delta; 
               front[k][h].moveHorizontal(distanceX);
            }
           distanceX+=delta; 
           distanceY=delta*maxi;
         }  
   }   
   private void top(){
       distanceX+=(delta*length)+10;
       int aux=distanceX;
       int distanceY=20;
       for(int i=0;i<length;i++){
           
        for(int j=0;j<width;j++){
            
            top[i][j]=new Rectangle();
            if (slow==true){top[i][j].lento();}
            
            if(m[i][j]==0){top[i][j].makeInvisible();}
            else{
                top[i][j].changeSize(tamaño,tamaño);
                top[i][j].changeColor("white");
                top[i][j].makeVisible();
            }
            top[i][j].moveVertical(distanceY);
            top[i][j].moveHorizontal(distanceX);
            distanceX+=delta;
          }
        distanceY+=delta;
        distanceX=aux;       
       }
    }
   //METODOS PARA DAR VISIBILIDAD A LAS VISTAS.
   public void zoom(String x){
       if (x=="+"){
           delta+=5;
           tamaño+=5;
       }
       else if (x=="-"){
           delta+=5;
           tamaño-=5;
        }
    }
   public void makeInvisible(){
       makeInVisibleBox(top);
       makeInVisibleBox(side);
       makeInVisibleBox(front);
   }
   public void makeVisible(int[][] m){
       makeInvisible();
       this.m=m;
       front();
       side();
       top();
   }
   private void makeInVisibleBox(Rectangle[][] cajas){
       for(int i=0;i<cajas.length;i++){
           for (int j=0;j<cajas[i].length;j++){
               if(cajas[i][j]!=null){cajas[i][j].makeInvisible();}
           }
       }
    }
}
