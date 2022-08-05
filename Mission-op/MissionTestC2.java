import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MissionTest.
 *
 * @author  (Macana-Orjuela)
 * @version (12 Marzo 2021)
 */ 
public class MissionTestC2
{
    
    private Mission mision;
    private int[][]m;
    /**
     * Default constructor for test class MissionTest
     */
    public MissionTestC2()
    {
        int[][] m={{1,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}}; 
        mision= new Mission(5,5,m);
        mision.setIsVisible(false);
    }
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }
    @Test
    public void segunMODeberiaCrear(){
        Bodega bodega=mision.getBodega();
        int[][] matriz=bodega.getMatriz();
        int[][] m={{1,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        assertEquals(matriz,m);
    }
    @Test
    public void segunMOStoreDeberiaFuncionar(){
        mision.store(1,1);
        //Ladron ladron=mision.getLadron();
        Bodega bodega=mision.getBodega();
        int[][] matriz={{2,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        assertEquals(matriz,bodega.getMatriz());
    }
    @Test
    public void segunMOStoreNODeberiaFuncionar(){
        mision.store(0,0);
        //Ladron ladron=mision.getLadron();
        Bodega bodega=mision.getBodega();
        int[][] matriz={{2,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        assertEquals(matriz,bodega.getMatriz());
    }
    @Test
    public void segunMOCopyDeberiaFuncionar(){
        mision.copy();
        Ladron ladron=mision.getLadron();
        Bodega bodega=mision.getBodega();
        int[][] matriz1=ladron.getMatriz();
        int[][] matriz2=bodega.getMatriz();
        assertEquals(matriz1,matriz2);
    }
    @Test
    public void segunMOStealDeberiaFuncionar(){
        mision.steal(1,2);
        Ladron ladron=mision.getLadron();
        Bodega bodega=mision.getBodega();
        int[][] matriz=ladron.getMatriz();
        int[][] matriz2={{0,3,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        assertEquals(matriz,matriz2);
    }
    @Test
    public void segunMOStealNODeberiaFuncionar(){
        mision.steal(1,2);
        Ladron ladron=mision.getLadron();
        Bodega bodega=mision.getBodega();
        int[][] matriz=ladron.getMatriz();
        int[][] matriz2={{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        assertEquals(matriz,matriz2);
    }
    @Test
    public void segunMOReturnnDeberiaFuncionar(){
        mision.steal(1,2);
        mision.returnn();
        Ladron ladron=mision.getLadron();
        Bodega bodega=mision.getBodega();
        int[][] matriz=bodega.getMatriz();
        int[][] matriz2={{1,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        assertEquals(matriz,matriz2);
    }
    @Test
    public void segunMOReturnnNODeberiaFuncionar(){
        mision.steal(1,2);
        mision.returnn();
        Ladron ladron=mision.getLadron();
        Bodega bodega=mision.getBodega();
        int[][] matriz=bodega.getMatriz();
        int[][] matriz2={{1,3,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        assertEquals(matriz,matriz2);
    }
    
    @Test
    public void segunMOArrageDeberiaFuncionar(){
        int [] from= {1,2};
        int [] to= {2,2};
        mision.arrange(from,to);
        Bodega bodega=mision.getBodega();
        int[][] matriz=bodega.getMatriz();
        int[][] matriz2={{1,1,0,5,2},{2,4,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        assertEquals(matriz,matriz2);
    }
    @Test
    public void segunMOArrageNODeberiaFuncionar(){
        int [] from= {1,2};
        int [] to= {2,2};
        mision.arrange(from,to);
        Bodega bodega=mision.getBodega();
        int[][] matriz=bodega.getMatriz();
        int[][] matriz2={{1,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        assertEquals(matriz,matriz2);
    }
    @Test
    public void segunMOWarehouseDeberiaFuncionar(){
        int[][] matriz=mision.warehouse();
        int[][] matriz2={{1,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        assertEquals(matriz,matriz2);
    }
    @Test
    public void segunMOWarehouseNODeberiaFuncionar(){
        int[][] matriz=mision.warehouse();
        int[][] matriz2={{0,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        assertEquals(matriz,matriz2);
    }
    @Test
    public void segunMOlayoutDeberiaFuncionar(){
        int[][] matriz=mision.layout();
        int[][] matriz2={{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        assertEquals(matriz,matriz2);
    }
    @Test
    public void segunMOlayoutNODeberiaFuncionar(){
        int[][] matriz=mision.layout();
        int[][] matriz2={{1,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        assertEquals(matriz,matriz2);
    }
    @Test
    public void segunMOundoDeberiaFuncionar(){
        mision.copy();
        mision.undo();
        Ladron ladron=mision.getLadron();
        int[][] matriz1=ladron.getMatriz();
        int[][] matriz2={{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        assertEquals(matriz1,matriz2);
    }
    
    @Test
    public void segunMORedoDeberiaFuncionar(){
        mision.copy();
        mision.undo();
        mision.redo();
        Ladron ladron=mision.getLadron();
        int[][] matriz1=ladron.getMatriz();
        int[][] matriz2={{1,4,0,5,2},{2,1,2,0,1},{0,2,3,4,4},{0,3,0,3,1},{1,2,2,1,1}};
        assertEquals(matriz1,matriz2);
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
