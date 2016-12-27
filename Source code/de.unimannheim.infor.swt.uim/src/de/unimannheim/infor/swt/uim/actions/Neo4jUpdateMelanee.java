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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
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
import org.melanee.core.models.plm.PLM.PLMPackage;
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

public class Neo4jUpdateMelanee {
	private static  String DB_PATH =Login.tntextneo4joutputx();
	  static String melaneefile=Login.j1textmelanee();
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


     }
//  @Test
   
   public  static void melaneemodelupdate() {

	     DB_PATH =Login.tntextneo4joutputx();
	     melaneefile=Login.j1textmelanee();
		 Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("lml", new XMIResourceFactoryImpl());
		 Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new EcoreResourceFactoryImpl());
		 ResourceSetImpl resourceSet = new ResourceSetImpl();
		 resourceSet.getPackageRegistry().put(PLMPackage.eNS_URI, PLMPackage.eINSTANCE);
		 resourceSet.getPackageRegistry().put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);	
		 // resource1 = resourceSet.getResource(URI.createFileURI("D:/eclipselunaNew/workspace/org.uim.models/Example/test.lml"),true);
		  resource1 = resourceSet.getResource(URI.createFileURI(melaneefile),true);
		 EList<EObject> lmlResourcContents = resource1.getContents();
			{	
				if (lmlResourcContents.get(0) != null && lmlResourcContents.get(0) instanceof Domain ) 
		{		   	  
		
		    deepmodel=(DeepModel)lmlResourcContents.get(0).eContents().get(0);	
		    GetDeepmodel();
		    save( ); 	
		}
		    
		}
	}
   
   
   public  static void GetDeepmodel()
    {   
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
        		String dpname=(String)deepmodelnode.getProperty("name",null);
        		String container=(String)deepmodelnode.getProperty("container",null);
        		
   	
  	   
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
      		 
      		    Level levelx=levelfind(levelFQN);   		      

              
  		        getlevelchild(levelnode,levelx);  
  		        levelx.setName(levelname);
  		        
    		  } 
  		    
          // This for method is used to get the nodes of level	end
  	      getbinaryconnection(deepmodelnode)  ; 
  	      getInhr(deepmodelnode) ;
  	     deepmodel.setName(dpname);
   		 }
         	  tx.success(); 
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
				   
				          
				          Entity ex= entityfind(FQN);
				        
		            		 if(ex==null)
		            		 {
		            				Entity entityx=PLMFactory.eINSTANCE.createEntityWithLMLVisualizer(); 
		            				entityx.setName(name);
		            				entityx.setPotency(potincy);
		            				getentitychild( entitynode,entityx);
		            				levex.getContent().add(entityx);	
		            		 }
		            		 else {
		             			           			 
		            		
		            		 getentitychild( entitynode,ex);
		            		 ex.setName(name);
		          	            	
		            		 }
		            		 				
			    	}
			    	else{
				    		    }
			    	 
			    }
			    

         	}
		
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
				    	   Attribute attributex = attributefind(FQN); 
				      		 if(attributex==null)
				      		 {
				      			  Attribute attribute1 = PLMFactory.eINSTANCE.createAttributeWithLMLVisualizer();
				        		  attribute1.setName(name);		        		 
				        		  entityx.getFeature().add(attribute1);	
				      		 }
				      		 else
				      		 {
//				      			attributex.setDatatype(type);
				      			attributex.setName(name);
				      			attributex.setValue(value);
				      			 
				      		 }
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
	
	    		     
		    		     Method methodx = methodfind(FQN); 
		        		 if(methodx==null)
		        		 {
		        			 Method method = PLMFactory.eINSTANCE.createMethodWithLMLVisualizer();
		           		  method.setName(name);      
		           		entityx.getFeature().add(method);	
		        		 }
		        		 else
		        		 {
		        			 methodx.setBody(body);
		        			 methodx.setName(name);
		        			 
		        		 }

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
					     		

		    		
					     		
					  		  org.melanee.core.models.plm.PLM.Connection connectionx=connectionfind(FQN);
				               if(connectionx==null)
				               {
//				            	    Level level=levelfind(container);
//				            		MelaneeRelationshipset r1= new MelaneeRelationshipset();
//				      	            r1.binaryconncetionsseting( entityfind(particpant1),  entityfind(particpant2), level,name,FQN);   
						     		Connection binnaryconnection = PLMFactory.eINSTANCE.createConnectionWithLMLVisualizer();
						     		ConnectionEnd p1 = PLMFactory.eINSTANCE.createConnectionEnd();
						     		binnaryconnection.setName(name);
						     	
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
				               else
				               {				            	 
				            	  getbinaryconnectionchild(nodey, connectionx);
				          		  connectionx.setName(name);
				               }
				               
					     		
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
			    	
				    	 
				    	 Attribute attributex = binattributefind(FQN); 
		        		 if(attributex==null)	
		        		 {
		        		  Attribute attribute1 = PLMFactory.eINSTANCE.createAttributeWithLMLVisualizer();
		           		  attribute1.setName(name);		 
		           		  bincx.getFeature().add(attribute1);	
		        		 }
		        		 else 
		        		 {
		        	      attributex.setName(name);
		        		
		        		 }
				    	 
				    
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
		    		   		    		     
		    		     Method methodx=binmethodfind(FQN);    		  
		    	  		  if(methodx==null)	
		    	     		 {
		    	    			  Method method = PLMFactory.eINSTANCE.createMethodWithLMLVisualizer();
		    	        		  method.setName(name);      
		    	        		  bincx.getFeature().add(method);	
		    	     		 }
		    	     		 else 
		    	     		 {	 
		    	     			methodx.setName(name);
		    	     		
		    	     		 }
					
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
				    {
					  
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
				    { 
					  
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
				    { 
					  
				    	if(inhrnode.equals(nodey))
				    	{  
				    		int id=(int)inhrnode.getProperty("id",null);
				    		String FQN=(String)inhrnode.getProperty("FQN",null);
				    		String name=(String)inhrnode.getProperty("name",null);
				    		String container=(String)inhrnode.getProperty("container",null);
				    		String disjoint=(String)inhrnode.getProperty("disjoint",null);
				    		String complete=(String)inhrnode.getProperty("complete",null);
				    	
	                 
	                    
	                      
	                        
	                        Inheritance inh1 = inheritancefind(FQN);
	               		 if(inh1==null)	
	               		 {
	               			 Inheritance inhrx = PLMFactory.eINSTANCE.createInheritanceWithLMLVisualizer();
		                        inhrx.setName(name);	
		                        supertypeset( nodey, inhrx) ;
		                        subertypeset(nodey, inhrx) ;
		                        levelfind((String)levelFQN).getContent().add(inhrx);
	               	      
	               		 }
	               		 else 
	               		 {
	               			 inh1.setName(name);
	               		
	               		 }
	               		 
				    		
				    		
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
	 public static Attribute  attributefind( String aFQN) {
			
		  int levelsize=deepmodel.eContents().size();
			
		  Attribute melaneeattribute=null;
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
				       int entitychildsize=enttiyx.eContents().size();
				       for(int k = 0 ;k< entitychildsize; k++)  
					    {
				    	   String entitychildtype= enttiyx.eContents().get(k).eClass().getName();
				   	    if(entitychildtype.equals("Attribute")){
				   	     Attribute attribute1=(Attribute)enttiyx.eContents().get(k);
				   	  String attributename=attribute1.getName();
				   	 String attributeFQN=deepmodel.getName()+"."+levelx.getName()+"."+enttiyx.getName()+"."+attributename;
			
				   	 if (attributeFQN.equals(aFQN))	
				     { 
				   		melaneeattribute=attribute1;
				     }
				   	    }
					    }
			
			    	
				    	  
				       }
				    }
				 }
			 }
				 return melaneeattribute;  	 	  
	  
	 }
	 
	 public static Attribute  binattributefind( String aFQN) {
			
		  int levelsize=deepmodel.eContents().size();
			
		  Attribute melaneeattribute=null;
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
				       {   org.melanee.core.models.plm.PLM.Connection conn1=( org.melanee.core.models.plm.PLM.Connection)levelx.eContents().get(j);
				       int entitychildsize=conn1.eContents().size();
				       for(int k = 0 ;k< entitychildsize; k++)  
					    {
				    	   String entitychildtype= conn1.eContents().get(k).eClass().getName();
				   	    if(entitychildtype.equals("Attribute")){
				   	     Attribute attribute1=(Attribute)conn1.eContents().get(k);
				   	  String attributename=attribute1.getName();
				   	 String attributeFQN=deepmodel.getName()+"."+levelx.getName()+"."+conn1.getName()+"."+attributename;
				
				   	 if (attributeFQN.equals(aFQN))	
				     { 
				   		melaneeattribute=attribute1;
				     }
				   	    }
					    }
				    	  
				 
			    	
				    	  
				       }
				    }
				 }
			 }
				 return melaneeattribute;  	 	  
	  
	 }
	 
	 public static Method  binmethodfind( String aFQN) {
			
		  int levelsize=deepmodel.eContents().size();
			
		  Method melaneemethod=null;
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
				       {   org.melanee.core.models.plm.PLM.Connection conn1=(org.melanee.core.models.plm.PLM.Connection)levelx.eContents().get(j);
				       int entitychildsize=conn1.eContents().size();
				       for(int k = 0 ;k< entitychildsize; k++)  
					    {
				    	   String entitychildtype= conn1.eContents().get(k).eClass().getName();
				   	    if(entitychildtype.equals("Method")){
				   	     Method method1=(Method)conn1.eContents().get(k);
				   	  String methodname=method1.getName();
				   	 String methodFQN=deepmodel.getName()+"."+levelx.getName()+"."+conn1.getName()+"."+methodname;
				   	 if (methodFQN.equals(aFQN))	
				     { 
				   		melaneemethod=method1;
				     }
				   	    }
					    }
		    	  
				       }
				    }
				 }
			 }
				 return melaneemethod;  	 	  
	  
	 }
	 
	 public static Method  methodfind( String aFQN) {
			
		  int levelsize=deepmodel.eContents().size();
			
		  Method melaneemethod=null;
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
				       int entitychildsize=enttiyx.eContents().size();
				       for(int k = 0 ;k< entitychildsize; k++)  
					    {
				    	   String entitychildtype= enttiyx.eContents().get(k).eClass().getName();
				   	    if(entitychildtype.equals("Method")){
				   	     Method method1=(Method)enttiyx.eContents().get(k);
				   	  String methodname=method1.getName();
				   	 String methodFQN=deepmodel.getName()+"."+levelx.getName()+"."+enttiyx.getName()+"."+methodname;
				    
				   	 if (methodFQN.equals(aFQN))	
				     { 
				   		melaneemethod=method1;
				     }
				   	    }
					    }
		    	  
				       }
				    }
				 }
			 }
				 return melaneemethod;  	 	  
	
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
	 public static Inheritance inheritancefind( String mysqlentityFQN) {
			
		  int levelsize=deepmodel.eContents().size();
			
		  Inheritance melaneeinheritance=null;
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
				       
				       if(entitytype=="Inheritance")
				       {   Inheritance inheritcanex=(Inheritance)levelx.eContents().get(j);
				    	   String entityFQN=deepmodel.getName()+"."+levelx.getName()+"."+inheritcanex.getName();
			 
			    	   if (entityFQN.equals(mysqlentityFQN))
				    	   { 
			    		 
			    		   melaneeinheritance=inheritcanex;
				    		 //  break;				    		  				    		   
				    	   }
				    	  
				       }
				    }
				 }
			 }
				 return melaneeinheritance;  	 	  
	  
	 }
	 
	 public static void  save( ) {
		 try {
			 resource1.save(null);
		
			} catch (IOException e) {
				e.printStackTrace();
			}
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
