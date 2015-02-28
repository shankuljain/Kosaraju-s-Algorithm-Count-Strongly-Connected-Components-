/* 
 * this class creates the database of the graph
 * File contains the data in the directed edge representation.
 * Each row of file contains two integers, first integer represent
 * the tail and the other represents the head of the edge.
 */

import java.util.*;
import java.io.*;

class VertexDataBase{

/* Constructor: VertexDataBase(String filename) */
/**
 * It initializes the vertexDataBase and reads the data from the specified file.
 * It throws an exception if file is not found or an error occurs while reading 
 * the file.
 * 
 * Two hashmaps has been used to keep track of the graph and the graph with the reverse edges
 * Although it is space consuming but it finishes the need of calculatin the vertices with reverse
 * edges.
 */
	public VertexDataBase(String filename) throws IOException{
		Scanner s = new Scanner(new FileReader(filename));
		while(true){
			int vertexNo = 0;
			int otherVertexNo = 0;
			
			try{
				vertexNo = s.nextInt();
				otherVertexNo = s.nextInt();
			}
			catch(NoSuchElementException e){
				break;
			}
			
			
			Vertex v = inventory.get(vertexNo);
			if(v == null){
				v = new Vertex(vertexNo,otherVertexNo);
				inventory.put(vertexNo, v);
			}else{
				v.addAdjecentVertex(otherVertexNo);
			}
			
			v = reverseInventory.get(otherVertexNo);
			if(v == null){
				v = new Vertex(otherVertexNo,vertexNo);
				reverseInventory.put(otherVertexNo, v);
			}
			else{
				v.addAdjecentVertex(vertexNo);
			}
			
			s.nextLine();
			System.out.println("Vertex : "+vertexNo+" loaded");
		}
	}
		
/* Method: getReverseAdjecentVerticesList(int vertexNo) */
/**
 * This method retuns the adjecent vertices list of a vertex in reversed graph
 */
	public ArrayList<Integer> getReverseAdjecentVerticesList(int vertexNo){
		Vertex v = reverseInventory.get(vertexNo);
		if(v!=null){
			return v.getAdjecentVerticesList();
		}else{
			return new ArrayList<Integer>();
		}
		
	}
	
/* Method : getAdjecentVerticesList(int vertexNo) */
/** this method return the adjecent vertices list of given vertex in original graph */
	public ArrayList<Integer> getAdjecentVerticesList(int vertexNo){
		Vertex v = inventory.get(vertexNo);
		if(v!=null){
			return v.getAdjecentVerticesList();
		}else{
			return new ArrayList<Integer>();
		}
	}
	
/* private ivars */
	private Map<Integer,Vertex> inventory = new HashMap<Integer,Vertex>();
	private Map<Integer,Vertex> reverseInventory = new HashMap<Integer,Vertex>();
}