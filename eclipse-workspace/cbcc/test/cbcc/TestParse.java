package cbcc;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestParse {



	@Test
	void test() {
		char [] buffer = new char[1024];
		buffer[0] = '0';
		buffer[1] = '1';
		assertEquals(1024, buffer.length);
		
		//fail("Not yet implemented");
	}

}
