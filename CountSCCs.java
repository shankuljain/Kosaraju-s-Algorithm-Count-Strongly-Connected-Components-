/* File : CountSCCs.java
 * _________________________
 * This program implement the Kosaraju's Algorithm to count
 * the strongly connected components.(This involves two DFS once
 * on reversed graph and once in original graph but in an magical order.
 * 
 * Strongly component connected is that part of the graph in which
 * each vertex can be reached by any other each vertex.
 * 
 */

/*********************************************************************
 * Since there is huge data. Stackoverflow error occurs because of more
 * recursive calls. so run the program using the -vm arguments to 
 * increase the default stack and heap size to avoid those errors.
 *********************************************************************/

import java.io.*;
import java.util.*;

class CountSCCs {
	
	/* final constants */
	private static final String filename = "SCC.txt";
	private static final int MAX_VERTEX_NO = 875714;
	
/* Method: Main(String [] args) */
/** this method creates a new object of database and initializes
 * the boolean array and an array of integers(magic order).
 */
	public static void main(String [] args) throws IOException{
		db = new VertexDataBase(filename);
		System.out.println("database loaded successfully");
		
		isExplored = new boolean[MAX_VERTEX_NO];
		magicOrder = new int[MAX_VERTEX_NO];
		
		System.out.println("reverse dfs started");
		for(int vertexNo=MAX_VERTEX_NO;vertexNo>0;vertexNo--){
			if(!isExplored[vertexNo-1]){
				reverseDFS(vertexNo);
			}
		}
		System.out.println("reverse dfs completed");
		
/********************************************************************************
 * dfs on original graph is performed in an magical order which is computed
 * by using dfs on reversed graph. It happens in such a way that each external
 * call to  DFS method discovers only one STRONGLY CONNECTED COMPONENT
 ********************************************************************************/
		
		System.out.println("dfs started");
		for(; count>0; count--){
			int vertexNo = magicOrder[count-1];
			if(isExplored[vertexNo-1]){
				sizeOfSCC = 0;
				DFS(vertexNo);
				largestSCCs(sizeOfSCC);
				nSCCs++;
			}
		}
		
		System.out.println("dfs completed");
		System.out.println(nSCCs);
		for(int i=5;i>0;i--){
			System.out.print(largestSCCs[i-1]+" ");
		}
	}
	
/* Method: DFS(int vertexNo) */
/** Each external call to this method discovers exactly one SCC */
	private static void DFS(int vertexNo){
		sizeOfSCC++;
		isExplored[vertexNo-1] = false;
		for(int i : db.getAdjecentVerticesList(vertexNo)){
			if(isExplored[i-1]){
				DFS(i);
			}
		}
	}
	
/* Method: reverseDFS(int vertexNo) */
/** this method is responsble for getting the magical order
 *  which DFS is to be run on original graph so that it discovers 
 *  one SCC at each external call.
 */
	private static void reverseDFS(int vertexNo){
		isExplored[vertexNo-1] = true;
		for(int i : db.getReverseAdjecentVerticesList(vertexNo)){
			if(!isExplored[i-1]){
				reverseDFS(i);
			}
		}
		magicOrder[count++] = vertexNo;
	}
	
/* Method: largestSCCs(int size) */
/** this method keeps track of five largest SCCs size */
	private static void largestSCCs(int size){
		if(size>largestSCCs[0]){
			largestSCCs[0] = size;
			Arrays.sort(largestSCCs);
		}
	}
	
	
/* private ivars */
	private static int sizeOfSCC = 0;
	private static int[] largestSCCs = new int[5];
	private static int nSCCs = 0;  // no of SCCs
	private static int count = 0;  // no of elements in SCC
	private static int[] magicOrder;
	private static VertexDataBase db;
	private static boolean[] isExplored;
}