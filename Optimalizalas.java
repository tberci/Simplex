package optimalizalas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Optimalizalas {
	private static int sora = 0;
	private static int oszlopa = 0;
	private static boolean stop ;
	
public static int  oszlopok(String fileName) throws FileNotFoundException {
		Scanner sc	= new Scanner(new File(fileName));
       
     
        int number = 0;
        
        while (sc.hasNextLine()) {
            number = sc.nextLine().split(" ").length;
        }
        sc.close();
        return number;
}

public static int  sorok(String fileName) throws IOException {
	
	Scanner sc	= new Scanner(new File(fileName));
	int count = 0;
	while (sc.hasNextLine()) {
	    count++;
	    sc.nextLine();
	}
	sc.close();
	System.out.println(count);
	return count;
}

public static double[][] simplexMatrix(String fileName) throws IOException{
	Scanner in = new Scanner(new File(fileName));
	int sor = sorok(fileName);
	int oszlop = oszlopok(fileName);
	double[][] arr = new double[sor][oszlop];
	//double [] b = bRow(oszlop,arr);
	for(int i=0; i<arr.length; i++) {
	    for(int j=0; j<arr[i].length; j++) {
	        arr[i][j]=in.nextDouble();
	        
	      
	       //System.out.println(arr[i][j]);
	    }
	    
	}
	return arr;

}
private static void displayBoard(double[][] board,String[] xek, String[] uk)
{   

    for(int i = 0; i< xek.length;i++) {
    	 System.out.print("   "+xek[i]+" ");
    }
    System.out.println("");
   
    for (int i = 0; i<board.length;i++) {
       
        
         System.out.print(uk[i]+" ");	
        
        for (int j = 0; j<board[i].length; j++) {
            System.out.print(board[i][j] + "  ");
        }
        System.out.println();
    }
}

public static double[] lastRow(int oszlop,double [][] matrix) {
	double [] row = new double[oszlop];
	
	
	for (int i = 0; i < matrix.length; i++)
    {
        row = matrix[i];
       
         //row[row.length - 1]; 
       
    }    
	
	
	return row ;
	
}



//visszatér a mátrix felcserélt sorával és oszlopával
public static String[] trasponal(double[][] ujmatrix,int sora, int oszlopa, String[] uk, String[] xek) {
	
	String u;
	String xsor;
	
		xsor= xek[oszlopa];
		u = uk[sora];
		uk[sora] = xsor;
		xek[oszlopa] = u;
		
	String[] ux = Arrays.copyOf(xek, xek.length + uk.length-1);
	System.arraycopy(uk, 0, ux, xek.length, uk.length-1);
	
	
	
	if(stop == true) {
		return ux;
	}else {
		for(int i= 0; i<ux.length; i++) {
		System.out.println(ux[i]);
	}
	}
	
	return ux;
}

public static double[][] SimplexMegold(int sor,int oszlop, double [][]matrix) throws IOException {

	
	//System.out.println(oszlop);
	double f = 0;
	double[][] arr = new double[sor][oszlop];
	double[] x = new double[oszlop];
	double pivotelem = 0;
	double pivotOszlop = arr.length-1;
	double[] b = lastRow(oszlop, matrix);
	double[] min = new double[sor];
	//String[][] karakterek = new String[sor][oszlop];
	

	int zpozitiv = 0;
	int zk = b.length -1;
	int n = 0 ;
	
	int znegativ = 0;
	int negatives = 0;
	double elemek = 0; 
	int negativindex = 0;
	int pozitivindex = 0;

	
	//int index = -1;

	int[] pozitivHelye = new int[b.length-1];
	

	System.out.println("negativok az oszlopban"+negatives);
	//csak a Z B elõtti része kerüljön bele.
	
		//uk

	//System.out.println("negativok az oszlopban"+negatives +"helye : "+negindex);
	//csak a Z B elõtti része kerüljön bele.
	
	int rnd = new Random().nextInt(pozitivHelye.length);
	
	 	for(int k = 0; k < b.length-1;k++) {
	 		
	 		
		 		
	 			if(b[k] >= 0 ) {
					pozitivindex = k;
					pozitivHelye[k] = k;
					
					zpozitiv ++;
					//n = (int) (Math.random() *(zk-zpozitiv) + zpozitiv);
					n= pozitivHelye[rnd];
						
				
				//System.out.println("Z sora "+b[k]);
				
	 		//ha alsó sorban van negatív akkor azt nem válassza.
	 			}else if(b[k] < 0 ) {
					negativindex = k;
					
					znegativ ++;
					n= pozitivindex;
				 } 
	 			
	 			
				 if(znegativ == b.length-1){
					 System.out.println("az optimum :"+ b[b.length-1]*-1);
					 stop = true;
					 return matrix;
				 }
				
				
				/*else if(b[k] > 0 && negatives != arr.length-1) {
				
				   //ne legyen 0 az n értéke.
				   n = (int) (Math.random() *(zk) + 1);
				 
			   }
			*/
		}	
	 		
	 		
	 	
	 	for(int i= 0; i< matrix.length-1; i++) {
	 		
	 			if(matrix[i][n] < 0) {
	 				negatives ++;
	 			}
	 		
	 	}
	 	System.out.println("az oszlop negativok száma "+negatives);
	 	
	 	if(negatives == matrix.length-1) {
	 		n= pozitivHelye[rnd];
	 	}
	int valasztott = n;
	
	System.out.println("a választott Z oszlopa "+ valasztott);
	
			System.out.println("negativ helye "+negativindex);
			System.out.println("pozitiv helye "+pozitivindex);
	 		System.out.println( zk); 
			System.out.println("A nem negatív zkk száma: "+ zpozitiv);
			System.out.println("negatívak száma : "+znegativ);
			
	
	//length -1 hogy a -Z sor ne kerüljön bele.
	
			System.out.println("Pivotelem választása : \n");
	for(int i = 0; i< matrix.length-1; i++) {
		x = matrix[i];
		
		//B oszlop.
	    f = x[x.length-1];
		//System.out.println(b);
	    
		for(int j =0; j< matrix[i].length; j++) {
			//mátrix értékei B nélkül. 
			//ha j 0 akkor negatívba megy j-1 miatt.
			//System.out.println("az x ek : "+n);
			
			if(matrix[i][n] < 0) {
				negatives++;
			}
			//System.out.println(arr.length-1);
		
		//System.out.println(min[r]);
		
	 }
		//elemek az osztás eredményét tárolják.
		
		elemek = f / matrix[i][n];
		
		//megkeresi az osztott elemek közül a minimumot.
		min[i] = elemek;
		double minimum = min[0];
		for(int r = 0 ;r < min.length;r++) {
		if ( min[r] > 0 && min[r] < minimum) {
			minimum = min[r];
        }
		if(minimum < 0) {
			minimum = min[r+1];
		}
		
		}
		//System.out.println("");
	System.out.println(f + "  /  "+ matrix[i][n] +" = " +f / matrix[i][n] );
		//Ha megtalálta a minimumot akkor az X-ek közül kiválasztja azt az elmet amellyel osztva a minimum kijön és az lesz a pivotelem.
		
		if(f/matrix[i][n] == minimum) {
			pivotelem = matrix[i][n];
			sora = i;
		
			oszlopa = n;
			
			
			//System.out.println(sora +"aaaaaaa "+ oszlopa);
			//System.out.println(uk[sora]+ "  " +xek[oszlopa]);
		}
	
	} 
	
	
	
	double[][]ujmatrix = new double[matrix.length][matrix[0].length];

	System.out.println("Oszlopban negativok száma " + negatives + "A teljes oszlop mérete :"+pivotOszlop );
	System.out.println("\n A pivot elem :" +pivotelem);
	System.out.println("sora :"+ sora + "oszlopa "+ oszlopa);
	
	ujmatrix=Pivotalas(sora, oszlopa, pivotelem, matrix);
	
	
	return ujmatrix;
}


public static double[][] Pivotalas(int sora, int oszlopa,double pivotelem,double [][] matrix){
	
	double pivot= 0;
	
	double[][] ujmatrix = new double[matrix.length][matrix[0].length];
	
	for(int i = 0; i< matrix.length; i++) {
		for(int j = 0; j< matrix[i].length; j++) {
			
			if(matrix[i][j] == pivotelem) {
			
			   pivot = 1 / pivotelem;
			    matrix[sora][oszlopa] = pivot;
			 
			}
			ujmatrix[i][j] = matrix[i][j];
			//System.out.println(ujmatrix[i][j]);
		}
	}
	
	
	for(int i = 0; i< matrix.length; i++) {
		//a pivotelem sora ne szerepeljen a téglalap módszerben.
		if(i == sora) {
			continue;
		}
		
		for(int j = 0; j<matrix[i].length; j++) {
			
			
			
			//System.out.println("pivot = "+matrix[sora][oszlopa]);
			//téglalap módszer
			//a pivotelem o ne szerepeljen a téglalap módszerben.
			if(j == oszlopa  ) {
				continue;
			}
			//System.out.println("aaa"+  matrix[i][j]);
			double szam = ujmatrix[i][j] -(matrix[i][oszlopa]*matrix[sora][j])/ pivotelem;
			
			System.out.println(ujmatrix[i][j] +" - (" +  matrix[i][oszlopa]+ "* "+ matrix[sora][j]  +") /"+  pivotelem +" = "+ szam );
			ujmatrix[i][j] = szam;
			//System.out.println("aa"+ matrix[i][j]);
	
			//System.out.println(matrix[i][oszlopa] +"   "+  matrix[sora][j]);
		}
		//oszlop osztása pivotelemmel
			/*if(oszlopa == ujmatrix[sora][oszlopa]) {
				ujmatrix[sora][oszlopa] = pivot;
			}else {*/
		if(ujmatrix[i][oszlopa] == 0) {
			ujmatrix[i][oszlopa] = 0;
		}else {
			ujmatrix[i][oszlopa] = ujmatrix[i][oszlopa] / (pivotelem*-1);
		}
			
			//}
	}
	
	
	System.out.println("");
	//System.out.println("sor : "+ sora+ "oszlopa :"+ oszlopa);
	
		for(int j = 0; j< ujmatrix[0].length; j++) {
			//sor osztása pivotelemmel
			if(ujmatrix[sora][j] == ujmatrix[sora][oszlopa]) {
				ujmatrix[sora][oszlopa] = pivot;
		
			}else {
			//System.out.println("sora : "+ujmatrix[sora][j] );
			ujmatrix[sora][j] = ujmatrix[sora][j] / pivotelem;
		}
	
		
			}
	
		/*for(int i = 0; i< ujmatrix.length;i ++) {
			System.out.println();
			for(int j = 0; j< ujmatrix[i].length; j++) {
				
					System.out.print(ujmatrix[i][j]+"   ");
				
			}
		}*/
		System.out.println("");
		
		
		return ujmatrix;
	
	
	
	
}

public static void main(String[] args) throws IOException {
	
	    Scanner sc = new Scanner(System.in); 
	    System.out.println("Adja meg az LP feladatot tartalmazó fájl névét (.txt nélkül)");
	    String x = sc.nextLine();
	    //System.out.println("Adja meg az LP feladat iterációinak számát");
	  //  int szam = sc.nextInt();
	    sc.close();
	    
	    //Az LP feladat elérési útvonala
		String path = System.getProperty("user.home") +"/Desktop/optimalizalas"+ File.separator + x + ".txt";
		
		//input mátrix (nxm dimenziós mátrix)
		double[][] matrix = simplexMatrix(path);
		
//input mátrix sorai
		int sor = sorok(path);
		System.out.println("sor"+sor);
		//input mátrix oszlopai
		int oszlop = oszlopok(path);
		System.out.println("oszlop"+oszlop);
		
		
		
		String[] uk = new String[matrix.length];
		String[] xek = new String[matrix[0].length-1];
		String[] a = null;
		for(int i = 0;i< uk.length;i++) {
			
			uk[i]= "u"+(i+1);
			uk[uk.length-1] = "-z";
			
		
	}
		for(int i = 0;i< xek.length;i++) {
		
			xek[i]= "x"+(i+1);
		
		
		
		//System.out.println(xek[i]);
	}
		
		while(!stop) {
			displayBoard(matrix, xek, uk);
			matrix = SimplexMegold(sor, oszlop, matrix);
			trasponal(matrix, sora, oszlopa, uk, xek);
			
			if(stop) {
				a = trasponal(matrix, sora, oszlopa, uk, xek);
			}
		}
		
		
		/*for(int i = 0; i<xek.length; i++) {
			System.out.println("xek "+a[i]);
		}
		
		for(int i= xek.length; i<a.length; i++) {
			System.out.println("uk :"+a[i]);
		}
		*/
		System.out.println("");
		double f = 0;
		double[] asd = new double[matrix[0].length];
		for(int i = 0; i< matrix.length-1; i++) {
			asd = matrix[i];
			
			f= asd[asd.length-1];	
			if(uk[i].contains("x")) {
				System.out.println("x"+(i+1)+" ="+f);
			}
			
		}
	
	}
	
}
