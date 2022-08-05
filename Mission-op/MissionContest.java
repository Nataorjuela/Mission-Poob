import javax.swing.JOptionPane;
/**
 * Write a description of class MissionContest here.
 * 
 * @author (your name) 
 * @version (a version number or a  date)
 */
public class MissionContest
{
    boolean ok;
    boolean slow;
    int[][] heights;
    public MissionContest(int[][] m)
    {
        int solucion=solve(m);
    }
    
    public int solve (int[][] heights){
    int robadas=0;
    int [] maxf=maximoFilas(heights);
    int[] maxc= maximoColumnas(heights);
    for (int i=0; i<heights.length;i++){
        int contaf=0;
        for (int j=0; j<heights[0].length;j++){
            if (heights[i][j]==maxf[i]){
                contaf+=1;
            }
            if(heights[i][j]!=maxf[i] && heights[i][j]!=maxc[j] && heights[i][j]>1){
                robadas+=heights[i][j]-1;
                heights[i][j]=1;
            }
            if(heights[i][j]==maxf[i] && contaf>1 && heights[i][j]>1){
                robadas+=heights[i][j]-1;
                heights[i][j]=1;
            }
        }
    }
    for (int i=0; i<heights[0].length;i++){
        int contac=0;
        for(int j=0; j<heights.length;j++){
            if(heights[j][i]==maxc[i]){
                contac+=1;
            }   
            if (contac>1 && heights[j][i]!=maxc[i]){
                robadas+=heights[j][i]-1;
                heights[i][j]=1;
            }
        }
    }
    return robadas;
    }
    
    private int[] maximoFilas(int[][] heights){
        int [] maxf=new int [heights.length];
        for (int i=0; i<heights.length;i++){
            int maxi=0;
            for (int j=0; j<heights[0].length;j++){
                if (heights[i][j]>maxi){
                    maxi=heights[i][j];
                }
            }
            maxf[i]=maxi;
        }
        return maxf;
    }
    private int[] maximoColumnas(int[][] heights){
        int [] maxc=new int [heights.length];
        for (int i=0; i<heights[0].length;i++){
            int maxi=0;
            for (int j=0; j<heights.length;j++){
                if (heights[j][i]>maxi){
                    maxi=heights[j][i];
                }
            }
            maxc[i]=maxi;
        }
        return maxc;
    }
    public void simulate(int[][] heights, boolean slow){
        Mission mision= new Mission(heights.length,heights[0].length,heights);       
        int [] maxf=maximoFilas(heights);
        int[] maxc= maximoColumnas(heights);
        int robadas=0;
        mision.lento(slow);
        for (int i=0; i<heights.length;i++){
            int contaf=0;
            for (int j=0; j<heights[0].length;j++){
                if (heights[i][j]==maxf[i]){
                    contaf+=1;
                }
                if(heights[i][j]!=maxf[i] && heights[i][j]!=maxc[j] && heights[i][j]>1){
                    robadas+=heights[i][j]-1;
                    mision.stealc3(i,j);
                }
                if(heights[i][j]==maxf[i] && contaf>1 && heights[i][j]>1){
                    robadas+=heights[i][j]-1;
                    mision.stealc3(i,j);
                }
            }
        }
        for (int i=0; i<heights[0].length;i++){
            int contac=0;
            for(int j=0; j<heights.length;j++){
                if(heights[j][i]==maxc[i]){
                contac+=1;
                }   
                if (contac>1 && heights[j][i]!=maxc[i]){
                    robadas+=heights[j][i]-1;
                    mision.stealc3(i,j);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Valor robado>>"+robadas);
    }
    public void insivible(Mission mision){
          mision.setIsVisible(false);
    }
    public boolean ok(){
    
        return ok;
    }
    
}
