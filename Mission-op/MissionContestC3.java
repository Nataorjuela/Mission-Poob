import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MissionContestC3.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MissionContestC3
{
    /**
     * Default constructor for test class MissionContestC3 
     */
    private MissionContest mision;
    private int[][] m;
    public MissionContestC3()
    {
        int[][] m={{1,2,3},{4,5,6},{7,8,9}};
        mision= new MissionContest(m);
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }
    @Test
    public void segunMODeberiaResolver(){
        int[][] m={{1,2,3},{4,5,6},{7,8,9}};
        assertEquals(mision.solve(m),8);
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
