/*****************************************************************/
/* Copyright 2013 Code Strategies                                */
/* This code may be freely used and distributed in any project.  */
/* However, please do not remove this credit if you publish this */
/* code in paper or electronic form, such as on a web site.      */
/*****************************************************************/

package uk.ac.shef.wit.simmetrics;

/* tridy souvisejici s pripojenim */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/* tridy souvisejici se simmetrics */
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.BlockDistance;
import uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation;
import uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.EuclideanDistance;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaccardSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Jaro;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
import uk.ac.shef.wit.simmetrics.similaritymetrics.MongeElkan;
import uk.ac.shef.wit.simmetrics.similaritymetrics.NeedlemanWunch;
import uk.ac.shef.wit.simmetrics.similaritymetrics.OverlapCoefficient;
import uk.ac.shef.wit.simmetrics.similaritymetrics.QGramsDistance;
import uk.ac.shef.wit.simmetrics.similaritymetrics.SmithWaterman;
import uk.ac.shef.wit.simmetrics.similaritymetrics.SmithWatermanGotoh;
import uk.ac.shef.wit.simmetrics.similaritymetrics.SmithWatermanGotohWindowedAffine;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Soundex;

//import com.mysql.jdbc.Driver;

public class MySqlJdbcTest {
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		String qstr = null;
		ResultSet rs = null;	
		Integer rsx = null;
		
		String table = "DS004";
		
		try {
		//new com.mysql.jdbc.Driver();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
// conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase?user=testuser&password=testpassword");
			String connectionUrl = "jdbc:mysql://localhost:3306/dq?useUnicode=yes&characterEncoding=UTF-8";
			String connectionUser = "crm";
			String connectionPassword = "";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT klic, string_1, string_2 FROM "+table);
			while (rs.next()) {
				String id = rs.getString("klic");
				String str1 = rs.getString("string_1");
				String str2 = rs.getString("string_2");
				
				/* upravy pred aplikaci metrik */
				str1 = str1.toUpperCase();
				str2 = str2.toUpperCase();
				
				str1 = str1.replace('Ã', 'E');
				str1 = str1.replace('ä', 'S');
				str1 = str1.replace('»', 'C');
				str1 = str1.replace('ÿ', 'R');
				str1 = str1.replace('é', 'Z');
				str1 = str1.replace('›', 'Y');
				str1 = str1.replace('¡', 'A');
				str1 = str1.replace('…', 'E');
				str1 = str1.replace('”', 'O');
				str1 = str1.replace('ç', 'T');
				str1 = str1.replace('œ', 'D');
				str1 = str1.replace('Ÿ', 'U');
				str1 = str1.replace('⁄', 'U');
				str1 = str1.replace('‹', 'U');
				str1 = str1.replace('ƒ', 'A');
				str1 = str1.replace('“', 'N');
				str1 = str1.replace('≈', 'L');
				str1 = str1.replace('Õ', 'I');
				str1 = str1.replace('º', 'L');
				str1 = str1.replace('¿', 'R');
				str1 = str1.replace('£', 'L');
				str1 = str1.replace('÷', 'O');
				str1 = str1.replace('•', 'A');
				str1 = str1.replace('€', 'U');
				str1 = str1.replace('Ø', 'Z');
				 
				
				

				str2 = str2.replace('Ã', 'E');
				str2 = str2.replace('ä', 'S');
				str2 = str2.replace('»', 'C');
				str2 = str2.replace('ÿ', 'R');
				str2 = str2.replace('é', 'Z');
				str2 = str2.replace('›', 'Y');
				str2 = str2.replace('¡', 'A');
				str2 = str2.replace('…', 'E');
				str2 = str2.replace('”', 'O');
				str2 = str2.replace('ç', 'T');
				str2 = str2.replace('œ', 'D');
				str2 = str2.replace('Ÿ', 'U');
				str2 = str2.replace('⁄', 'U');
				str2 = str2.replace('‹', 'U');
				str2 = str2.replace('ƒ', 'A');
				str2 = str2.replace('“', 'N');
				str2 = str2.replace('≈', 'L');
				str2 = str2.replace('Õ', 'I');
				str2 = str2.replace('º', 'L');
				str2 = str2.replace('¿', 'R');
				str2 = str2.replace('£', 'L');
				str2 = str2.replace('÷', 'O');
				str2 = str2.replace('•', 'A');
				str2 = str2.replace('€', 'U');
				str2 = str2.replace('Ø', 'Z');
				
				/*
				System.out.println(str1 + " " + str2);
				
				System.out.println("ID: " + id + ", string_1: " + string_1
						+ ", string_2: " + string_2);
				*/
				
				/*System.out.println(getMatchLikelyhood(str1, str2));*/
	            
	            AbstractStringMetric metric = new ChapmanLengthDeviation();
	            float result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M01="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	           
	            metric = new Jaro(); 
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M02="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new EuclideanDistance();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M03="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new Levenshtein();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M04="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new MongeElkan();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M05="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new NeedlemanWunch();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M06="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new QGramsDistance();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M07="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new SmithWatermanGotoh();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M08="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new SmithWatermanGotohWindowedAffine();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M09="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new SmithWaterman();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M10="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new Soundex();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M11="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new BlockDistance();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M12="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new CosineSimilarity();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M13="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
				
	            metric = new JaccardSimilarity();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M14="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
	            
	            metric = new JaroWinkler();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M15="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
				
	            metric = new OverlapCoefficient();
	            result = metric.getSimilarity(str1, str2);
	            outputResult(id, str1, str2, result, metric);
	            
	            stmt = conn.createStatement();
	            qstr = "update "+table+" set M16="+result+" where klic="+id;
	            rsx = stmt.executeUpdate(qstr);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	/*
	private static float getMatchLikelyhood(final String str1, final String str2) {
		AbstractStringMetric metric;
		float avg = 0F, result = 0F;
		metric = new SmithWaterman();
		result = metric.getSimilarity(str1, str2);
		avg += result;
		metric = new SmithWatermanGotoh();
		result = metric.getSimilarity(str1, str2);
		avg += result;
		metric = new SmithWatermanGotohWindowedAffine();
		result = metric.getSimilarity(str1, str2);
		avg += result;
		metric = new MongeElkan();
		result = metric.getSimilarity(str1, str2);
		avg += result;
		return (avg / 4.0F) * 100.0F;
	}
	 */
    /**
     * outputs the result of the metric test.
     * @param str2 
     * @param str1 
     * @param id 
     *
     * @param result the float result of the metric test
     * @param metric the metric itself to provide its description in the output
     * @param str1 the first string with which to compare
     * @param str2 the second string to compare with the first
     */
    private static void outputResult(String id, String str1, String str2, final float result, final AbstractStringMetric metric) {
        System.out.println(id +"|"+str1+"|"+str2+"|"+ metric.getShortDescriptionString() + "|" + (result * 100 / 1));

    }
}