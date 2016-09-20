package world;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Classes implementing Reportable can produce a more detailed and 
 * appropriately formatted report than is practicable with toString(). The 
 * report is likely to be written to System.out in many cases but other streams 
 * may be used (e.g. to print to a file). The output destination is set to a 
 * PrintWriter (which may in turn be constructed from a PrintStream).
 * @author Andrew Dallow
 * @version 1.0
 *
 */
public interface Reportable {
	/**
	 * Write the report to the appropriate output stream or file. The 
	 * destination should be set as required and then its print/println 
	 * methods can be used.
	 */
	void report();
	/**
	 * Set output destination to a PrintStream (e.g., System.out).
	 * @param printStream A PrintStream to use.
	 */
	void setDestination(PrintStream printStream);
	/**
	 * Set output destination to a PrintWrinter (e.g., a file).
	 * @param printWriter A PrintWriter to use.
	 */
	void setDestination(PrintWriter printWriter);

}
