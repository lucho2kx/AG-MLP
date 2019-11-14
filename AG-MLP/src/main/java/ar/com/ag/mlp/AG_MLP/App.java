package ar.com.ag.mlp.AG_MLP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * Hello world!
 *
 */
public class App {
	
	public static final String SEPARATOR="\t";
	public static final String QUOTE="\"";
	
	private static String[] removeTrailingQuotes(String[] fields) {

	      String result[] = new String[fields.length];

	      for (int i=0;i<result.length;i++){
	         result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
	      }
	      return result;
	}
	
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        FileReader fr = null;
		try {
			fr= new FileReader("src/main/resources/dataset-neuroph-procesado.txt");
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (fr != null) {
			CSVReader csvReader= new CSVReader(fr);
			String[] fila = null;
			try {
				while((fila = csvReader.readNext()) != null) {
				    System.out.println(fila[0]);
				}
				csvReader.close();
			} catch (CsvValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
		BufferedReader br = null;
	      
	      try {
	         
	         br =new BufferedReader(new FileReader("src/main/resources/dataset-neuroph-procesado.txt"));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(SEPARATOR);
	            System.out.println(Arrays.toString(fields));
	            
	            fields = removeTrailingQuotes(fields);
	            System.out.println(Arrays.toString(fields));
	            
	            line = br.readLine();
	         }
	         
	      } catch (Exception e) {
	         
	      } finally {
	         if (null != br) {
	            try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
	      }
		
    }
}
