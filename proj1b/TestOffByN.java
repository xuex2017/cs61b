import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {


    @Test
    public void testEqualChars(){
        OffByN ob3 = new OffByN(3);
        assertFalse(ob3.equalChars('a','a'));
        assertTrue(ob3.equalChars('a','d'));
        assertTrue(ob3.equalChars('d','a'));
    }

    }

