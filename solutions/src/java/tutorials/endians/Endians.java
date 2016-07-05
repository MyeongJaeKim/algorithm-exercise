package tutorials.endians;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @see https://algospot.com/judge/problem/read/ENDIANS
 */
public class Endians {

    public static void main(String[] args) throws Exception {
	// For Local
	FileInputStream fis = new FileInputStream("./bin/tutorials/mercifulalgospot/sample_endians.txt");
	System.setIn(fis);
	
	// On Submission
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	int tests = Integer.parseInt(reader.readLine());
	
	for (int idx = 0 ; idx < tests ; idx++) {
	    String read = reader.readLine();
	    int unconverted = 0;
	    
	    // Larger than 32bit integer, pass
	    try {
		unconverted = Integer.parseInt(read);
	    } catch (NumberFormatException e) {
		System.out.println(read);
		continue;
	    }
	    
	    int converted = Integer.reverseBytes(unconverted);
	    
	    // Converting Negative value to Positive
	    if (converted < 0) {
		long unsinged = ((long) converted) & 0xffffffffL;
		System.out.println(Long.toString(unsinged));
	    }
	    else {
	        System.out.println(Integer.toString(converted));
	    }
	}
	
	if (reader != null) {
	    reader.close();
	}
	
	if (fis != null) {
	    fis.close();
	}
    }
}
