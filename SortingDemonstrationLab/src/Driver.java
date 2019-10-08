import javax.swing.JOptionPane;
import java.util.Random; 
import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) throws InterruptedException {
		int goAgain = 0;
		do {
			JFrame myFrame = new JFrame();
			myFrame.setTitle("GraphicsLab");
			myFrame.setSize(500,500);
			myFrame.setResizable(false);
			myFrame.setVisible(false);
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ColorPanel P = new ColorPanel();
			int numberOfBoxes = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rectangles you want"));
			int SortNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter what sort you want:\n"
																		   + "1.Bubble sort\n"
																		   + "2.Selection sort\n"
																		   + "3.Insertion sort\n"
																		   + "4.Quick sort\n"
																		   + "5.Cocktail sort\n"
																		   + "6.Bitonic sort(Number of Rectangles will be rounded to the nearest power of 2)\n"
																		   + "7.Heap Sort\n"
																		   + "8.Radix Sort\n"
																		   + "9.All sorts except Bitonic\n"));
			if (SortNumber == 6) {
				numberOfBoxes = NearestPowerOfTwo(numberOfBoxes);
			}
			System.out.println("Number Of Boxes " + numberOfBoxes);
			P.SetNumberOfBoxes(numberOfBoxes);
			int Width = (int)Math.ceil((myFrame.getWidth()/(double)numberOfBoxes));
			P.GenerateRandomBoxes(myFrame.getWidth(), myFrame.getHeight(), Width);
			System.out.println(myFrame.getWidth());
			myFrame.setSize((Width*numberOfBoxes)+Width,500);
			System.out.println(myFrame.getWidth());
			myFrame.getContentPane().add(P);
			myFrame.setVisible(true);
			P.setBackground(Color.BLACK);
			if (SortNumber == 1){
				BubbleSort(myFrame, P);
			}
			else if (SortNumber == 2){
				SelectionSort(myFrame, P);
			} 
			else if (SortNumber == 3) {
				InsertionSort(myFrame,P);
			}
			else if (SortNumber == 4) {
				QuickSort(myFrame, P, 0, P.arrRect.length-1);
			}
			else if (SortNumber == 5) {
				cocktailSort(myFrame, P);
			}
			else if (SortNumber == 6) {
				bitonicSort(myFrame, P, 0, P.arrRect.length, true);  
			}
			else if (SortNumber == 9) {
				BubbleSort(myFrame, P);
				Thread.sleep(5000);
				Shuffle(P);
				myFrame.repaint();
				cocktailSort(myFrame, P);
				Thread.sleep(5000);
				Shuffle(P);
				myFrame.repaint();
				SelectionSort(myFrame, P);
				Thread.sleep(5000);
				Shuffle(P);
				myFrame.repaint();
				InsertionSort(myFrame,P);
				Thread.sleep(5000);
				Shuffle(P);
				myFrame.repaint();
				QuickSort(myFrame, P, 0, P.arrRect.length-1);
				Thread.sleep(5000);
				Shuffle(P);
				myFrame.repaint();
				HeapSort(myFrame,P);
				Thread.sleep(5000);
				Shuffle(P);
				myFrame.repaint();
				RadixSort(myFrame,P,P.arrRect.length);
			}
			else if (SortNumber == 7) {
				HeapSort(myFrame, P);
			}
			else if (SortNumber == 8) {
				RadixSort(myFrame,P,P.arrRect.length);
			}
			myFrame.repaint();
			JOptionPane.showMessageDialog(null, "Done Drawing");
			goAgain = JOptionPane.showConfirmDialog(null, "Do you want to go again?");
			myFrame.dispose();
		}while (goAgain == 0);
	}
	public static void Shuffle(ColorPanel P) {
		Random r = new Random(); 
        for (int i = P.arrRect.length-1; i > 0; i--) {  
            int j = r.nextInt(i+1);  
            Rectangle temp = P.arrRect[i]; 
            P.arrRect[i] = P.arrRect[j]; 
            P.arrRect[j] = temp; 
        } 
	}
	public static void SelectionSort(JFrame J, ColorPanel P) throws InterruptedException {
		for (int i = 0; i < P.arrRect.length; i++) {
			int MinimumIndex = i;
			for (int j = i; j < P.arrRect.length; j++) {
				P.arrRect[j].setColor(Color.GREEN);
				if (P.arrRect[MinimumIndex].getHeight() > P.arrRect[j].getHeight()) {
					P.arrRect[MinimumIndex].setColor(Color.RED);
					MinimumIndex = j;
					P.arrRect[MinimumIndex].setColor(Color.BLUE);
				}
				J.repaint();
				Thread.sleep(5);
				if (j != MinimumIndex)
					P.arrRect[j].setColor(Color.RED);
				
			}
			P.arrRect[MinimumIndex].setColor(Color.RED);
			Rectangle Temp = P.arrRect[i];
			P.arrRect[i] = P.arrRect[MinimumIndex];
			P.arrRect[MinimumIndex] = Temp;
			J.repaint();
		    Thread.sleep(50);
		}
	}
	private static void BubbleSort(JFrame J, ColorPanel P) throws InterruptedException
	{
		boolean Match = true;
		while (Match) {
			Match = false;
			for (int j = 0; j < P.arrRect.length-1; j++) {
				P.arrRect[j].setColor(Color.GREEN);
				P.arrRect[j+1].setColor(Color.GREEN);
				if (P.arrRect[j].getHeight() > P.arrRect[j+1].getHeight()) {
					Match = true;
					P.arrRect[j].setColor(Color.BLUE);
					P.arrRect[j+1].setColor(Color.BLUE);
					J.repaint();
					Rectangle Temp = P.arrRect[j];
					P.arrRect[j] = P.arrRect[j+1];
					P.arrRect[j+1] = Temp;
				}
				J.repaint();
				Thread.sleep(10);
				P.arrRect[j].setColor(Color.RED);
				P.arrRect[j+1].setColor(Color.RED);
			}
		}
	}
	private static void InsertionSort(JFrame J, ColorPanel P) throws InterruptedException {
		for (int i = 1; i < P.arrRect.length; i++) {
			
			int pos = i;
			while(pos > 0 && P.arrRect[pos].getHeight() < P.arrRect[pos-1].getHeight()) {
				P.arrRect[pos].setColor(Color.RED);
				Rectangle Temp = P.arrRect[pos];
				P.arrRect[pos] = P.arrRect[pos-1];
				P.arrRect[pos-1] = Temp;
				pos--;
				P.arrRect[pos].setColor(Color.GREEN);
				J.repaint();
				Thread.sleep(10);
			}
			P.arrRect[pos].setColor(Color.RED);
		}
		System.out.println("done");
		J.repaint();
	}
	private static void QuickSort(JFrame J, ColorPanel P, int LowerLimit, int UpperLimit) throws InterruptedException {
		if (P.arrRect == null || P.arrRect.length == 0)
			return;
		if (LowerLimit >= UpperLimit)
			return;
		// pick the pivot
		int middle = LowerLimit + (UpperLimit - LowerLimit) / 2;
		Rectangle pivot = P.arrRect[middle];
		P.arrRect[UpperLimit].setColor(Color.GREEN);
		P.arrRect[LowerLimit].setColor(Color.GREEN);
		// make left < pivot and right > pivot
		int i = LowerLimit, j = UpperLimit;
		int NewMiddle1 = 0, NewMiddle2 = 0;
		boolean LowerMoved = false, UpperMoved = false, MiddleMoved = false;
		while (i <= j) {
			while (P.arrRect[i].getHeight() < pivot.getHeight()) {
				i++;
			}
			while (P.arrRect[j].getHeight() > pivot.getHeight()) {
				j--;
			}
			if (i == LowerLimit) {
				LowerMoved = true;
			}
			else {
				LowerMoved = false;
			}
			if (j == UpperLimit ) {
				UpperMoved = true;
			}
			else {
				UpperMoved = false;
			}
			if (i <= j) {
				if ((LowerMoved || UpperMoved )) {
					P.arrRect[i].setColor(Color.RED);
					P.arrRect[j].setColor(Color.RED);
				}
				
				Rectangle temp = P.arrRect[i];
				P.arrRect[i] = P.arrRect[j];
				P.arrRect[j] = temp;
				
				if ((LowerMoved || UpperMoved )) {
					P.arrRect[LowerLimit].setColor(Color.GREEN);
					P.arrRect[UpperLimit].setColor(Color.GREEN);
				}
				if (P.arrRect.length < 100) {
					Thread.sleep(50);
				}
				else {
					Thread.sleep(10);
				}
				J.repaint();
				i++;
				j--;
			}
		}
		P.arrRect[UpperLimit].setColor(Color.RED);
		P.arrRect[LowerLimit].setColor(Color.RED);
		if (LowerLimit < j)
			QuickSort(J, P , LowerLimit, j);
 
		if (UpperLimit > i)
			QuickSort(J, P, i, UpperLimit);
	}
	public static void cocktailSort(JFrame J, ColorPanel P) throws InterruptedException 
    { 
        boolean swapped = true; 
        int start = 0; 
        int end = P.arrRect.length; 
        while (swapped == true) {  
            swapped = false; 
            for (int i = start; i < end - 1; ++i) { 
            	P.arrRect[i].setColor(Color.GREEN);
				P.arrRect[i+1].setColor(Color.GREEN);
                if (P.arrRect[i].getHeight() > P.arrRect[i + 1].getHeight()) { 
                	P.arrRect[i].setColor(Color.BLUE);
					P.arrRect[i+1].setColor(Color.BLUE);
                    Rectangle temp = P.arrRect[i]; 
                    P.arrRect[i] = P.arrRect[i + 1]; 
                    P.arrRect[i + 1] = temp; 
                    swapped = true;
                    Thread.sleep(10);
                    J.repaint();
                } 
                P.arrRect[i].setColor(Color.RED);
				P.arrRect[i+1].setColor(Color.RED);
            } 
            if (swapped == false) 
                break; 
            swapped = false; 
            end = end - 1; 
            for (int i = end - 1; i >= start; i--) { 
            	P.arrRect[i].setColor(Color.GREEN);
				P.arrRect[i+1].setColor(Color.GREEN);
                if (P.arrRect[i].getHeight() > P.arrRect[i + 1].getHeight()) { 
                	P.arrRect[i].setColor(Color.BLUE);
					P.arrRect[i+1].setColor(Color.BLUE);
                    Rectangle temp = P.arrRect[i]; 
                    P.arrRect[i] = P.arrRect[i + 1]; 
                    P.arrRect[i + 1] = temp; 
                    swapped = true;
                    Thread.sleep(10);
                    J.repaint();
                } 
                P.arrRect[i].setColor(Color.RED);
				P.arrRect[i+1].setColor(Color.RED);
            }  
            start = start + 1;
        } 
    } 
	public static void merge(JFrame J, ColorPanel P, int l, int c, boolean d) throws InterruptedException  
	{  
	    int k,i;  
	    if (c>1)  
	    {  
	        k = c/2;  
	        for (i=l; i<l+k; i++)  {
	        	P.arrRect[i].setColor(Color.GREEN);
	        	P.arrRect[i+k].setColor(Color.GREEN);
	            if (d==(P.arrRect[i].getHeight()>P.arrRect[i+k].getHeight()))  
	    	    {  
	    	        Rectangle temp = P.arrRect[i];  
	    	        P.arrRect[i] = P.arrRect[i+k];  
	    	        P.arrRect[i+k] = temp;  
	    	    }  
	            Thread.sleep(10);
	            J.repaint();
	            P.arrRect[i].setColor(Color.RED);
	        	P.arrRect[i+k].setColor(Color.RED);
	        }
	        merge(J, P, l, k, d);  
	        merge(J, P, l+k, k, d);  
	    }  
	}  
	public static void bitonicSort(JFrame J, ColorPanel P,int l, int c, boolean d) throws InterruptedException  
	{  
	    int k;  
	    if (c>1)  
	    {  
	        k = c/2;  
	        bitonicSort(J, P, l, k, true);  
	        bitonicSort(J, P, l+k, k, false);  
	        merge(J, P,l, c, d);  
	    }  
	}  
	public static int NearestPowerOfTwo(int N) {
		for (int i = 0; i < 200; i++) {
			if (Math.pow(2,i) >= N) {
				return (int) Math.pow(2, i);
			}
		}
		return 128;
	}
	public static void heapify(JFrame J, ColorPanel P, int n, int i) throws InterruptedException 
    { 
        int largest = i;  
        int l = 2*i + 1;   
        int r = 2*i + 2;   
        if (l < n && P.arrRect[l].getHeight() > P.arrRect[largest].getHeight()) 
            largest = l; 
        if (r < n && P.arrRect[r].getHeight() > P.arrRect[largest].getHeight()) 
            largest = r;  
        if (largest != i) 
        { 
        	P.arrRect[i].setColor(Color.GREEN);
        	P.arrRect[largest].setColor(Color.GREEN);
            Rectangle swap = P.arrRect[i]; 
            P.arrRect[i] = P.arrRect[largest]; 
            P.arrRect[largest] = swap; 
            Thread.sleep(10);
            J.repaint();
            P.arrRect[i].setColor(Color.RED);
        	P.arrRect[largest].setColor(Color.RED);
            // Recursively heapify the affected sub-tree 
            heapify(J, P, n, largest); 
        } 
    } 
	public static void HeapSort(JFrame J, ColorPanel P) throws InterruptedException 
    { 
        int n = P.arrRect.length; 
  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(J, P, n, i); 
  
        // One by one extract an element from heap 
        for (int i=n-1; i>=0; i--) 
        { 
            // Move current root to end 
            Rectangle temp = P.arrRect[0]; 
            P.arrRect[0] = P.arrRect[i]; 
            P.arrRect[i] = temp; 
            Thread.sleep(10);
            J.repaint();
            // call max heapify on the reduced heap 
            heapify(J, P, i, 0); 
        } 
    } 
	static Rectangle getMax( ColorPanel P, int n) 
    { 
        Rectangle mx = P.arrRect[0]; 
        for (int i = 1; i < n; i++) 
            if (P.arrRect[i].getHeight() > mx.getHeight()) 
                mx = P.arrRect[i]; 
        return mx; 
    } 
    static void countSort(JFrame J, ColorPanel P, int n, int exp) throws InterruptedException 
    { 
        Rectangle output[] = new Rectangle[n]; // output array 
        int i; 
        int count[] = new int[10]; 
        Arrays.fill(count,0); 
        for (i = 0; i < n; i++) {        
    		count[(P.arrRect[i].getHeight()/exp)%10 ]++;
        } 
        for (i = 1; i < 10; i++) {        
    		count[i] += count[i - 1];
        }
        for (i = n - 1; i >= 0; i--) 
        { 
            output[count[ (P.arrRect[i].getHeight()/exp)%10 ] - 1] = P.arrRect[i]; 
            count[ (P.arrRect[i].getHeight()/exp)%10 ]--; 
        } 
        for (i = 0; i < n; i++) {
        	P.arrRect[i] = output[i];
        	Thread.sleep(10);
    		J.repaint();
        } 
    } 
    static void RadixSort(JFrame J, ColorPanel P, int n) throws InterruptedException 
    {  
        Rectangle m = getMax(P, n); 
        for (int exp = 1; m.getHeight()/exp > 0; exp *= 10) {
        	countSort(J,P, n, exp);
        	Thread.sleep(10);
        	J.repaint();
        } 
    } 


}