/*
 * This class keeps track of the data of a single vertex and its directed edges
 */

import java.util.*;

class Vertex{
	
/*Constructor : Vertex(int vertexNo, int adjecentVertexNo */
/** initializes the vertex */
	public Vertex(int vertexNo, int adjecentVertexNo){
		myVertexNo = vertexNo;
		adjecentVerticesList.add(adjecentVertexNo);
	}
	
/* Method: getMyVertexNo() */	
/** this method returns the vertexNo of current vertex */
	public Integer getMyVertexNo(){
		return myVertexNo;
	}
	
/* Method: addAdjecentVertex(int adjecentVertexNo) */
/** this method adds a vertex to adjecentVerticesList of the current vertex */
	public void addAdjecentVertex(int adjecentVertexNo){
		adjecentVerticesList.add(adjecentVertexNo);
	}
	
/* Method: getAdjecentVerticesList */	
/** this method returns the arraylist of adjecentverices */
	public ArrayList<Integer> getAdjecentVerticesList(){
		return adjecentVerticesList;
	}
	
/* private instant variables */
	private int myVertexNo;
	private ArrayList<Integer> adjecentVerticesList = new ArrayList<Integer>();
}