/*******************************************************************************
 * Copyright (c) 2016 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dongze Wang - initial API and implementation and initial documentation
 *******************************************************************************/
package de.unimannheim.infor.swt.uim.actions;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.melanee.core.modeleditor.edit.parts.DomainEditPart;
import org.melanee.core.modeleditor.part.PLMDiagramEditorPlugin;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Domain;
import org.melanee.core.models.plm.PLM.Entity;
import org.melanee.core.models.plm.PLM.Inheritance;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.core.models.plm.PLM.Subtype;
import org.melanee.core.models.plm.PLM.Supertype;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.tooling.GlobalGraphOperations;
//import org.junit.Test;
//import org.eclipse.gmf.runtime.notation.Node;

public class Neo4jToMelanee {
	private static  String DB_PATH =Login.tntextneo4joutputx();
	  static String melaneefile=Login.fmtextmelaneex();
	// private static final String DB_PATH = "D:/eclipselunaNew/workspace/org.uim.models/Neo4j/db";//"neo4j-db";//  
	 private static final String USERNAME_KEY = "FQN";
	 private static final String USERNAME_KEY1 = "name";
//	 private static final String USERNAME_KEYENTITY = "username";
//     private static Index<Node> nodeIndex;  
     private static GraphDatabaseService graphDb;
     static ResourceSet resourceSet = new ResourceSetImpl();
	 static Resource resource1 =null;
	 static Domain domain=null;
	 static Diagram diagram=null;
     static DeepModel deepmodel=null;
   
   private static enum RelTypes implements RelationshipType{  
       participant1;
    };  
    private static enum RelTypes2 implements RelationshipType{  
        participant2;
     };  
    private static enum RelTypes1 implements RelationshipType{  

        parent;
     };  
     private static enum RelTypesinh implements RelationshipType{  
         inheritance;
      };  
      private static enum RelTypesinhp implements RelationshipType{  
          participant;
       };  
      private static enum RelTypesdir implements RelationshipType{  
          directType;
       };  
  	
       
       


   public static void main(String args[]  )
   { 

	  GetDeepmodel();

     }
//  @Test
   
   public  static void GetDeepmodel()
    {   
	      DB_PATH =Login.tntextneo4joutputx();
		  melaneefile=Login.fmtextmelaneex();
	   
	   graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
	      registerShutdownHook();
	      try ( Transaction tx = graphDb.beginTx() ) {  
//	  	 if(label=="deepmodel")
//        {

         	for (  Node deepmodelnode : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("deepmodel")))
         	{
         		
        	    int id=(int)deepmodelnode.getProperty("id",null);
        	    int p_id=(int)deepmodelnode.getProperty("p_id",null);
        		String FQN=(String)deepmodelnode.getProperty("FQN",null);
        		String name=(String)deepmodelnode.getProperty("name",null);
        		String container=(String)deepmodelnode.getProperty("container",null);
        		
        	//	resource1 = resourceSet.createResource(URI.createFileURI("D:/eclipselunaNew/workspace/org.uim.models/lml/"+name+"reverse2.lml"));
        		resource1 = resourceSet.createResource(URI.createFileURI(melaneefile+"/"+name+"neo4jreverse.lml"));
        		domain = PLMFactory.eINSTANCE.createDomain();
        	    deepmodel = PLMFactory.eINSTANCE.createDeepModelWithLMLVisualizer();
        	    deepmodel.setName(name);
        		//domain.getOntologies().add(ontology);
        		domain.getDeepModel().add(deepmodel);
        		
//        		readlevel("level",ontology,FQN);		    	  
             
        		
        		
//        		MySQLWrite write =new MySQLWrite();
//  		      //  write.Deepmodelinsert(id,p_id,FQN,name, container,"deepmodel");
       
        // This for method is used to get the nodes of level		
  	   
  	    	for (  Node levelnode : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("level")))
  	      {   
//  		    	Node levelnode = rel.getStartNode();
  		    
//  		    	if (levelnode.equals(levellnodeall))
  		    	 
  		        String levellabel=levelnode.getLabels().toString();
  		        int levelid=(int)levelnode.getProperty("id",null);    	 
      		    String levelFQN=(String)levelnode.getProperty("FQN",null);
      		    String levelname=(String)levelnode.getProperty("name",null);
      		    String levelcontainer=(String)levelnode.getProperty("container",null);
      		 
      		    int number=(int)levelnode.getProperty("number",null);
      		 
      		    Level levex = PLMFactory.eINSTANCE.createLevelWithLMLVisualizer();
      		    levex.setName(levelname);
      		    deepmodel.getContent().add(levex);
   		      
              // readentity("entity",LFQN,leveO0);
  		        getlevelchild(levelnode,levex);
  		    	
    		  } 
  		    
          // This for method is used to get the nodes of level	end
  	     getbinaryconnection(deepmodelnode)  ; 
  	    getInhr(deepmodelnode) ;
  	  getclassification(deepmodelnode)  ;
  	    resource1.getContents().add(domain);  
	    diagramcreate(name) ;
  	
    		 }
         	  tx.success();//戻住 
		  }
 
	      shutdown();
    
 }
	
	 public static void getlevelchild(Node node,Level levex) 

	 {  	 
		 for ( Relationship rel : node.getRelationships( RelTypes1.parent, Direction.INCOMING ) )
         	{
			    Node entitynode =null;
			    entitynode = rel.getStartNode();
			
	
			    for (  Node node1 : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("entity")))
			    { 
			    	if(entitynode.equals(node1))
			    	{
			    		int id=(int)entitynode.getProperty("id",null);
				    	 String entitylabel=entitynode.getLabels().toString();
	
				          String FQN=(String)entitynode.getProperty("FQN",null);
				          String name=(String)entitynode.getProperty("name",null);
				    //	  String container=(String)entitynode.getProperty("container",null);

				     	  int potincy=(int)entitynode.getProperty("potincy",null);
				    	  String directtype=(String)entitynode.getProperty("directtype",null);					    
				    	  Entity entityx=PLMFactory.eINSTANCE.createEntityWithLMLVisualizer(); 
				          entityx.setName(name);
				          //entityx.setPotency(potincy);
	  			
						  
						  levex.getContent().add(entityx);
						  getentitychild( entitynode,entityx);
			    	}
			    	else{
				    		    }
			    	 
			    }
			    

         	}
		
		 // get binaryconnection
		 for ( Relationship rel : node.getRelationships( RelTypes1.parent, Direction.INCOMING ) )
      	{
			    Node bcnode =null;
			    bcnode = rel.getStartNode();
			
		
			    for (  Node nodey : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("binaryconnection")))
			    {
			    	if(bcnode.equals(nodey))
			    	{
			    		int id=(int)node.getProperty("id",null);
			     		String FQN=(String)node.getProperty("FQN",null);
			     		String name=(String)node.getProperty("name",null);
			     		String container=(String)node.getProperty("container",null);
			     		String potincy=(String)node.getProperty("potincy",null);
			     		String direct_type=(String)node.getProperty("direct_type",null);
			     		String label1=(String)node.getProperty("label",null);
			     		String particpant1=(String)node.getProperty("particpant1",null);
			     		String particpant2=(String)node.getProperty("particpant2",null);
			     		String roleName1=(String)node.getProperty("roleName1",null);
			     		String roleName2=(String)node.getProperty("roleName2",null);
			     		String lower1=(String)node.getProperty("lower1",null);
			     		String lower2=(String)node.getProperty("lower2",null);
			     		String upper1=(String)node.getProperty("lower1",null);
			     		String upper2=(String)node.getProperty("upper2",null);
			     		String navigalbeTo1=(String)node.getProperty("navigalbeTo1",null);
			     		String navigableTo2=(String)node.getProperty("navigableTo2",null);
			     //		Entity supertypex=
			     		
			     		
			    	}
			    	else{
				    		    }
			    	 
			    }
			    
      	}
		 //get binaryconncetion
		 
		 // get binaryconnection
		 for ( Relationship rel : node.getRelationships( RelTypes1.parent, Direction.INCOMING ) )
      	{
			    Node bcnode =null;
			    bcnode = rel.getStartNode();
	
			    for (  Node nodey : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("inheritancerelationship")))
			    { 
			    	if(bcnode.equals(nodey))
			    	{   
			    		int id=(int)nodey.getProperty("id",null);
			    		String FQN=(String)nodey.getProperty("FQN",null);
			    		String name=(String)nodey.getProperty("name",null);
			    		String container=(String)nodey.getProperty("container",null);
			    		String disjoint=(String)nodey.getProperty("disjoint",null);
			    		String complete=(String)nodey.getProperty("complete",null);


                       

			    	}
			    	else{
				    		    }
			    	 
			    }
			    
      	}
		 //get binaryconncetion
	
	 }
	 
	 public static void getentitychild(Node node, Entity entityx) 

	 {  	 
		 for ( Relationship rel : node.getRelationships( RelTypes1.parent, Direction.INCOMING ) )
         	{
			  Node entitychildnode = rel.getStartNode();
			  for (  Node nodey : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("attribute")))
			  {
				  if(entitychildnode.equals(nodey))
			    	{
					  int id=(int)entitychildnode.getProperty("id",null);
				    	 String FQN=(String)entitychildnode.getProperty("FQN",null);
				    	 String name=(String)entitychildnode.getProperty("name",null);
				    	 String container=(String)entitychildnode.getProperty("container",null);
				    	 String duribility=(String)entitychildnode.getProperty("duribility",null);
				    	 String type=(String)entitychildnode.getProperty("type",null);
				    	 String mutability=(String)entitychildnode.getProperty("mutability",null);
				    	 String value=(String)entitychildnode.getProperty("value",null);
			    		 Attribute attributex=PLMFactory.eINSTANCE.createAttributeWithLMLVisualizer(); 
			    		 attributex.setName(name);
			    		 entityx.getFeature().add(attributex);
				
			    	}
				  
				  
			  }
			  for (  Node nodey : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("method")))
			  {
				  if(entitychildnode.equals(nodey))
			    	{
					   int id=(int)entitychildnode.getProperty("id",null);
			    	     String FQN=(String)entitychildnode.getProperty("FQN",null);
			    	     String name=(String)entitychildnode.getProperty("name",null);
			    	     String container=(String)entitychildnode.getProperty("container",null);
			    	     String signature=(String)entitychildnode.getProperty("signature",null);
			    	     String body=(String)entitychildnode.getProperty("body",null);
		    		     Method methodx=PLMFactory.eINSTANCE.createMethodWithLMLVisualizer(); 
		    		     methodx.setName(name);
		    		     entityx.getFeature().add(methodx);
				
			    	}				  
				  
			  }
			  
				
         	}
 
	 
	 }	
 
	 public static void getbinaryconnection(Node node) 
	
		 {  	 
			 
			  for ( Relationship reldeepmodel : node.getRelationships( RelTypes1.parent, Direction.INCOMING ) )
			      {
				   Node levelnode = reldeepmodel.getStartNode();
				   String levelFQN=(String)levelnode.getProperty("FQN",null);
				   
				   for ( Relationship rellevel : levelnode.getRelationships( RelTypes1.parent, Direction.INCOMING ) )
				      {
					   Node inhrnode = rellevel.getStartNode();
					   for (  Node nodey : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("binaryconnection")))
					    { 
						  
					    	if(inhrnode.equals(nodey))
					    	{  
					    		int id=(int)nodey.getProperty("id",null);
					     		String FQN=(String)nodey.getProperty("FQN",null);
					     		String name=(String)nodey.getProperty("name",null);
					     		String container=(String)nodey.getProperty("container",null);
					     		int potincy=(int)nodey.getProperty("potincy",null);
					     		String direct_type=(String)nodey.getProperty("direct_type",null);
					     		String label1=(String)nodey.getProperty("label",null);
					     		String particpant1=(String)nodey.getProperty("particpant1",null);
					     		String particpant2=(String)nodey.getProperty("particpant2",null);
					     		String roleName1=(String)nodey.getProperty("roleName1",null);
					     		String roleName2=(String)nodey.getProperty("roleName2",null);
					     		String lower1=(String)nodey.getProperty("lower1",null);
					     		String lower2=(String)nodey.getProperty("lower2",null);
					     		String upper1=(String)nodey.getProperty("lower1",null);
					     		String upper2=(String)nodey.getProperty("upper2",null);
					     		String navigalbeTo1=(String)nodey.getProperty("navigalbeTo1",null);
					     		String navigableTo2=(String)nodey.getProperty("navigableTo2",null);
					     		
					     		Connection binnaryconnection = PLMFactory.eINSTANCE.createConnectionWithLMLVisualizer();
					     		ConnectionEnd p1 = PLMFactory.eINSTANCE.createConnectionEnd();
					     		binnaryconnection.setName(name);
					     		System.out.println("dddddddddddd"+potincy);
					     		binnaryconnection.setPotency(potincy);			     		
					     		p1.setConnection(binnaryconnection);
					     		Entity firstentity=entityfind(particpant1) ;
					     		
					     		p1.setDestination(firstentity);
					     		
					     		ConnectionEnd p2 = PLMFactory.eINSTANCE.createConnectionEnd();
					     		p2.setConnection(binnaryconnection);
					     		Entity secondentity=entityfind(particpant2) ;
					     		p2.setDestination(secondentity);
					     		levelfind((String)levelFQN).getContent().add(binnaryconnection);					     		
					     		getbinaryconnectionchild(nodey, binnaryconnection);
					     		
				    	}
					   
				      }
				   
			      }
			      }
			 
		 
		 }

	public static void getbinaryconnectionchild(Node node, org.melanee.core.models.plm.PLM.Connection bincx) 

	 {  	 
		 for ( Relationship rel : node.getRelationships( RelTypes1.parent, Direction.INCOMING ) )
         	{
			  Node entitychildnode = rel.getStartNode();
			  for (  Node nodey : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("attribute")))
			  {
				  if(entitychildnode.equals(nodey))
			    	{
					  int id=(int)entitychildnode.getProperty("id",null);
				    	 String FQN=(String)entitychildnode.getProperty("FQN",null);
				    	 String name=(String)entitychildnode.getProperty("name",null);
				    	 String container=(String)entitychildnode.getProperty("container",null);
				    	 String duribility=(String)entitychildnode.getProperty("duribility",null);
				    	 String type=(String)entitychildnode.getProperty("type",null);
				    	 String mutability=(String)entitychildnode.getProperty("mutability",null);
				    	 String value=(String)entitychildnode.getProperty("value",null);
			    		 Attribute attributex=PLMFactory.eINSTANCE.createAttributeWithLMLVisualizer(); 
			    		 attributex.setName(name);
			    		 bincx.getFeature().add(attributex);
	
			    	}
				  
				  
			  }
			  for (  Node nodey : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("method")))
			  {
				  if(entitychildnode.equals(nodey))
			    	{
					   int id=(int)entitychildnode.getProperty("id",null);
			    	     String FQN=(String)entitychildnode.getProperty("FQN",null);
			    	     String name=(String)entitychildnode.getProperty("name",null);
			    	     String container=(String)entitychildnode.getProperty("container",null);
			    	     String signature=(String)entitychildnode.getProperty("signature",null);
			    	     String body=(String)entitychildnode.getProperty("body",null);
		    		     Method methodx=PLMFactory.eINSTANCE.createMethodWithLMLVisualizer(); 
		    		     methodx.setName(name);
		    		     bincx.getFeature().add(methodx);
				
			    	}				  
				  
			  }
			  
				
         	}
 
	 
	 }	
	 

	 //Get Inheritance in neo4j
//。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。Get classification
	 public static void getclassification(Node node) 

	 {  	 
		 
		  for ( Relationship reldeepmodel : node.getRelationships( RelTypes1.parent, Direction.INCOMING ) )
		      {
			   Node levelnode = reldeepmodel.getStartNode();
			   String levelFQN=(String)levelnode.getProperty("FQN",null);
			   
			   for ( Relationship rellevel : levelnode.getRelationships( RelTypes1.parent, Direction.INCOMING ) )
			      {
				   Node entitynodex = rellevel.getStartNode();
				   for (  Node nodey : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("entity")))
				    { //  System.out.println(node1);
					  
				    	if(entitynodex.equals(nodey))
				    	{  
				    		
				    		for ( Relationship getclassification : entitynodex.getRelationships( RelTypesdir.directType, Direction.OUTGOING ) )
						      {
				    			Node entitychild = getclassification.getEndNode();
				    			String entityfatherFQN=(String)nodey.getProperty("FQN",null);
				    			String entitychildFQN=(String)entitychild.getProperty("FQN",null);
				    			Entity firstentity=entityfind(entityfatherFQN) ;
				    			Entity secondentity=entityfind(entitychildFQN) ;
				    			 Level levelx=(Level)secondentity.eContainer();
				    			 MelaneeRelationshipset r1= new MelaneeRelationshipset();
				   		        r1.classificationset(secondentity, firstentity, levelx);
				   		     
						      }
			    		
				    	}
				   
			      }
		//April 04 updatae		   
				   for (  Node nodey : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("binaryconnection")))
				    { //  System.out.println(node1);
					  
				    	if(entitynodex.equals(nodey))
				    	{  
				    		
				    		for ( Relationship getclassification : entitynodex.getRelationships( RelTypesdir.directType, Direction.OUTGOING ) )
						      {
				    			Node conchild = getclassification.getEndNode();
				    			String confatherFQN=(String)nodey.getProperty("FQN",null);
				    			String conchildFQN=(String)conchild.getProperty("FQN",null);
			    			org.melanee.core.models.plm.PLM.Connection firstcon=connectionfind(confatherFQN) ;
			    			org.melanee.core.models.plm.PLM.Connection secondcon=connectionfind(conchildFQN) ;
				    			
				    			
				    			Level levelx=(Level)secondcon.eContainer();
				    			MelaneeRelationshipset r1= new MelaneeRelationshipset();
				   		        r1.conclassificationset(secondcon, firstcon, levelx);
  			
						      }
			    		
				    	}
				   
			      }
 //April 04 updatae  
		      }
		      }	 
	 }

//.....................................................................................Get classifcaion end
	 public static void getInhr(Node node) 
	 { 
		 
		  for ( Relationship reldeepmodel : node.getRelationships( RelTypes1.parent, Direction.INCOMING ) )
		      {
			   Node levelnode = reldeepmodel.getStartNode();
			   String levelFQN=(String)levelnode.getProperty("FQN",null);
			   
			   for ( Relationship rellevel : levelnode.getRelationships( RelTypes1.parent, Direction.INCOMING ) )
			      {
				   Node inhrnode = rellevel.getStartNode();
				   for (  Node nodey : GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label("inheritancerelationship")))
				    { //  System.out.println(node1);
					  
				    	if(inhrnode.equals(nodey))
				    	{  
				    		int id=(int)inhrnode.getProperty("id",null);
				    		String FQN=(String)inhrnode.getProperty("FQN",null);
				    		String name=(String)inhrnode.getProperty("name",null);
				    		String container=(String)inhrnode.getProperty("container",null);
				    		String disjoint=(String)inhrnode.getProperty("disjoint",null);
				    		String complete=(String)inhrnode.getProperty("complete",null);
				    	
	                        Inheritance inhrx = PLMFactory.eINSTANCE.createInheritanceWithLMLVisualizer();
	                        inhrx.setName(name);	
	                    //   System.out.println( "dddddddd"+levelfind(levelFQN));
	                        supertypeset( nodey, inhrx) ;
	                        subertypeset(nodey, inhrx) ;
	                        levelfind((String)levelFQN).getContent().add(inhrx);
	                      
				    		
				    		
				    	}
				   
			      }
			   
		      }
		      }
	 }
	 
	 
	 //Get Inheritance in neo4j
	// get Inheritance 
		 public static Level levelfind(String nero4jFQN) 
		  { 

			 int levelsize=deepmodel.eContents().size();
			Level levelx=null;
			 Inheritance inhrx=null;
			 for(int i = 0 ;i < levelsize; i++)  
			 { 
			
				 String leveltype=deepmodel.eContents().get(i).eClass().getName();
			
				 if(leveltype=="Level")
				 { 
				   Level levely=(Level)deepmodel.eContents().get(i);
					String levelFQN=deepmodel.getName()+"."+(String)levely.getName();
					 if(nero4jFQN.equals(levelFQN)) {
						 levelx=levely;
					
					 }
				 }
				 
			 }
		
			 return levelx;
			 
		  }
		 //get Inheritance end
	 public static void supertypeset(Node node,Inheritance inhrx) 
	 {  String superFQN=null;
		 for ( Relationship rel : node.getRelationships( RelTypesinh.inheritance, Direction.OUTGOING ) )
		 { 			
			 Node ipnode = rel.getEndNode();
			 superFQN=(String)ipnode.getProperty("participant",null);
			
			 Entity superentityx=entityfind(superFQN);
			
			 Supertype supertype = PLMFactory.eINSTANCE.createSupertype();
			 supertype.setSupertype(superentityx);
			 inhrx.getSupertype().add(supertype);
			 
		 }

	 }
	 
	 public static void  subertypeset(Node node,Inheritance inhrx) 
	 {  String suberFQN=null;
		 for ( Relationship rel : node.getRelationships( RelTypesinh.inheritance, Direction.INCOMING ) )
		 { 			
			  Node ipnode = rel.getStartNode(); 
			 
			  suberFQN=(String)ipnode.getProperty("participant",null);
			  
			  Entity suberentityx=entityfind(suberFQN);
		
			  Subtype subtypex = PLMFactory.eINSTANCE.createSubtype();
			  subtypex.setSubtype(suberentityx);
			  inhrx.getSubtype().add(subtypex);
			  		 
		 }
	
	 }
	 
	
	 public static Entity entityfind(String neo4jentityFQN) 
	  { 

		 int levelsize=deepmodel.eContents().size();
		
		 Entity melaneeentity=null;
		 for(int i = 0 ;i < levelsize; i++)  
		 { 
	
			 String leveltype=deepmodel.eContents().get(i).eClass().getName();
		
			 if(leveltype=="Level")
			 { 
				Level levelx=(Level)deepmodel.eContents().get(i);
			    int entitysize=levelx.eContents().size();
			
			    for(int j = 0 ;j < entitysize; j++)  
			    {
			       String entitytype=levelx.eContents().get(j).eClass().getName();
			       
			       if(entitytype=="Entity")
			       {   Entity enttiyx=(Entity)levelx.eContents().get(j);
			    	   String entityFQN=deepmodel.getName()+"."+levelx.getName()+"."+enttiyx.getName();
			    	  
			    	  
			    	   if (entityFQN.equals(neo4jentityFQN))
			    	   { 
			    		   melaneeentity=enttiyx;
			    		
			    		  
			    		 //  break;
			    		  
			    		   
			    	   }
			    	  
			       }
			    }
			 }
			 
		 }
	
		 return melaneeentity;
		 
	  }
   
	 public static org.melanee.core.models.plm.PLM.Connection connectionfind( String mysqlentityFQN) {
			
		  int levelsize=deepmodel.eContents().size();
			
		  org.melanee.core.models.plm.PLM.Connection melaneeconnection=null;
			 for(int i = 0 ;i < levelsize; i++)  
			 { 
		
				 String leveltype=deepmodel.eContents().get(i).eClass().getName();
			
				 if(leveltype=="Level")
				 { 
					Level levelx=(Level)deepmodel.eContents().get(i);
				    int entitysize=levelx.eContents().size();
				
				    for(int j = 0 ;j < entitysize; j++)  
				    {
				       String entitytype=levelx.eContents().get(j).eClass().getName();
				       
				       if(entitytype=="Connection")
				       {   org.melanee.core.models.plm.PLM.Connection connectionx=(org.melanee.core.models.plm.PLM.Connection)levelx.eContents().get(j);
				    	   String entityFQN=deepmodel.getName()+"."+levelx.getName()+"."+connectionx.getName();
				
			    	   if (entityFQN.equals(mysqlentityFQN))
				    	   { 
			    		 
			    		   melaneeconnection=connectionx;
				    		 //  break;				    		  				    		   
				    	   }
				    	  
				       }
				    }
				 }
			 }
				 return melaneeconnection;  	 	  
	  
	 }
	 
	 
	 public static void diagramcreate(String name) 

	 {  	 diagram = ViewService.createDiagram(
				domain,
				DomainEditPart.MODEL_ID,
				PLMDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		        diagram.setName(name+".lml");
		        org.eclipse.gmf.runtime.notation.Node   DeepModelview = ViewService.createNode(diagram, deepmodel,"2005",PLMDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT); 
	 resource1.getContents().add(diagram);
	  try {
			resource1.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}

	System.out.println("Melanee model create sucessfully");
	 
	 }	
   
   
   
   
   private static void registerShutdownHook()
   {
       Runtime.getRuntime().addShutdownHook( new Thread()
       {
           @Override
           public void run()
           {
               shutdown();
           }
       } );
   }
   
   private static void shutdown()
   {
       graphDb.shutdown();
   }
}
