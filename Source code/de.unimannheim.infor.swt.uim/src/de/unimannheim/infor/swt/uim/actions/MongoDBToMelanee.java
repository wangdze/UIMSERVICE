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

import org.bson.BasicBSONObject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.melanee.core.modeleditor.edit.parts.DomainEditPart;
import org.melanee.core.modeleditor.part.PLMDiagramEditorPlugin;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Domain;
import org.melanee.core.models.plm.PLM.Entity;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.core.models.plm.PLM.PLMFactory;
//import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBToMelanee {
	static int i=0;
	static int ids=0;
	static String entityfqnsub1=null;
	static String entityfqnsub2=null;
	static String entityfqnsub3=null;
	static String entityfqnsup=null;

	static DeepModel deepmodel=null;
	static ResourceSet resourceSet = new ResourceSetImpl();
	static Resource resource1 =null;
	static Domain domain=null;
	static Diagram diagram=null;
	
	static String mdlocalhost=Login.tmtexthostx();
	static int mdport=Integer.valueOf(Login.tmtextportx());
	static String tmtextdb=Login.tmtextdbx();
	static String tmtextuser=Login.tmtextuserx();
	static String tmtextpassword=Login.tmtextpasswordx();
	  static String melaneefile=Login.fmtextmelaneex();
	
	
	public static void main( String args[] ){
//		   WriteMySQL write =new WriteMySQL();
//	       write.delatetable(); 
//		   ReadMongoDB("package");
//		   ReadMongoDB("deepmodel");
//		   ReadMongoDB("level");
//		   ReadMongoDB("entity");
//		   ReadMongoDB("attribute");
//		   ReadMongoDB("binaryconnection");
//		   ReadMongoDB("inheritancerelationship");
//		   ReadMongoDB("inheritanceparticipation");
//		   ReadMongoDB("method");
//		   ReadMongoDB("generalconnection");
//		   ReadMongoDB("participation");
	   }
	
//	@Test
	public  static void ReadMongoDB(){
		
	  mdlocalhost=Login.tmtexthostx();
		 mdport=Integer.valueOf(Login.tmtextportx());
		 tmtextdb=Login.tmtextdbx();
		 tmtextuser=Login.tmtextuserx();
		 tmtextpassword=Login.tmtextpasswordx();
		  melaneefile=Login.fmtextmelaneex();
	      try{   

	      MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
	      DB db = mongoClient.getDB(tmtextdb );
	      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray()); 
	//	  System.out.println("Connect to database successfully");
		//  boolean auth = db.authenticate("root", "12345678".toCharArray()); 
	//	  System.out.println("Authentication: "+auth);
		  DBObject allQuery = new BasicDBObject(); 
          DBCollection collection = db.getCollection("deepmodel"); 
	//	  System.out.println("collection: "+"deepmodel");
		  DBCursor curs = collection.find();
		    while(curs.hasNext()) 
		     {
		    	    DBObject dbObject = curs.next();
		      	    String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
		      	    int p_id = ((BasicBSONObject) dbObject).getInt("p_id");
		    	    String FQN = ((BasicBSONObject) dbObject).getString("FQN");    
		    	    String name = ((BasicBSONObject) dbObject).getString("name");  
		    	    String container = ((BasicBSONObject) dbObject).getString("container");       
//		            WriteMySQL write =new WriteMySQL();
//		            write.Deepmodelinsert(id,p_id,FQN,name, container,"deepmodel");
		    	    
		    //	    resource1 = resourceSet.createResource(URI.createFileURI("D:/eclipselunaNew/workspace/org.uim.models/lml/"+name+"mongore.lml"));
		    	    resource1 = resourceSet.createResource(URI.createFileURI(melaneefile+"/"+name+"mongodbreverse.lml"));
		    	    domain = PLMFactory.eINSTANCE.createDomain();
		     		deepmodel = PLMFactory.eINSTANCE.createDeepModelWithLMLVisualizer();
		     		deepmodel.setName(name);
		    		domain.getDeepModel().add(deepmodel);		    		
		    		readlevel(FQN,deepmodel,db);	
		    		readbinaryconnection(db);	
		    		readinr(db);  
		    		readclassification( db);
		    		readconclassification( db);
		       		resource1.getContents().add(domain);      	
		       		diagramcreate(name) ;
		    	    
		        }
	      }
		     catch(Exception e)
		      {
			     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			  }
		   }
	
	public static void readlevel(String dFQN,DeepModel deepmodelp,DB db){
		  
		DBCollection collection = db.getCollection("level"); 
		  DBCursor curs = collection.find();
		  while(curs.hasNext())
		  {   DBObject dbObject = curs.next(); 
			  String container = ((BasicBSONObject) dbObject).getString("container");
			  if (container.equals(dFQN))
			  { 		     
					String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
					String lFQN = ((BasicBSONObject) dbObject).getString("FQN");    
					String name = ((BasicBSONObject) dbObject).getString("name");  					
					int number = ((BasicBSONObject) dbObject).getInt("number"); 
					Level levelx = PLMFactory.eINSTANCE.createLevelWithLMLVisualizer();
					levelx.setName(name);	
		            deepmodelp.getContent().add(levelx);
		            readentity(lFQN,levelx,db);

					
			  }
 			   
		   }
	}
	
	public static void readentity(String lFQN,Level levelx,DB db){
		  
		DBCollection collection = db.getCollection("entity"); 
		  DBCursor curs = collection.find();
		  while(curs.hasNext())
		  {   DBObject dbObject = curs.next(); 
			  String container = ((BasicBSONObject) dbObject).getString("container");
			  if (container.equals(lFQN))
			  { 		     
				   String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
		           String eFQN = ((BasicBSONObject) dbObject).getString("FQN");    
		           String ename = ((BasicBSONObject) dbObject).getString("name");  
		           int potincy = ((BasicBSONObject) dbObject).getInt("potincy");  
		           String directtype = ((BasicBSONObject) dbObject).getString("directtype"); 
		          	           
		           Entity entityx=PLMFactory.eINSTANCE.createEntityWithLMLVisualizer();
	    		   entityx.setName(ename);
	    		   readattribute(eFQN,entityx,db);
	    		   readmethod(eFQN,entityx,db);
	    		   levelx.getContent().add(entityx);	
              
					
			  }
 			   
		   }
	}
	public static void readattribute(String eFQN,Entity entityx,DB db){
		  
		  DBCollection collection = db.getCollection("attribute"); 
		  DBCursor curs = collection.find();
		  while(curs.hasNext())
		  {   DBObject dbObject = curs.next(); 
			  String container = ((BasicBSONObject) dbObject).getString("container");
			  if (container.equals(eFQN))
			  { 		     				 
					String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
					String FQN = ((BasicBSONObject) dbObject).getString("FQN");    
					String name = ((BasicBSONObject) dbObject).getString("name"); 
					String duribility = ((BasicBSONObject) dbObject).getString("duribility");
					String type = ((BasicBSONObject) dbObject).getString("type"); 
					String value = ((BasicBSONObject) dbObject).getString("value"); 
					String mutability = ((BasicBSONObject) dbObject).getString("mutability");
					
				    Attribute attribute1 = PLMFactory.eINSTANCE.createAttributeWithLMLVisualizer();
				    attribute1.setName(name);		 
				    entityx.getFeature().add(attribute1);

					
			  }
 			   
		   }
	}
	public static void readbinattribute(String eFQN,org.melanee.core.models.plm.PLM.Connection bincx,DB db){
		  
		  DBCollection collection = db.getCollection("attribute"); 
		  DBCursor curs = collection.find();
		  while(curs.hasNext())
		  {   DBObject dbObject = curs.next(); 
			  String container = ((BasicBSONObject) dbObject).getString("container");
			  if (container.equals(eFQN))
			  { 		     				 
					String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
					String FQN = ((BasicBSONObject) dbObject).getString("FQN");    
					String name = ((BasicBSONObject) dbObject).getString("name"); 
					String duribility = ((BasicBSONObject) dbObject).getString("duribility");
					String type = ((BasicBSONObject) dbObject).getString("type"); 
					String value = ((BasicBSONObject) dbObject).getString("value"); 
					String mutability = ((BasicBSONObject) dbObject).getString("mutability");
					
				    Attribute attribute1 = PLMFactory.eINSTANCE.createAttributeWithLMLVisualizer();
				    attribute1.setName(name);		 
				    bincx.getFeature().add(attribute1);

					
			  }
			   
		   }
	}
	
	public static void readmethod(String eFQN,Entity entityx,DB db){
		  
		  DBCollection collection = db.getCollection("method"); 
		  DBCursor curs = collection.find();
		  while(curs.hasNext())
		  {   DBObject dbObject = curs.next(); 
			  String container = ((BasicBSONObject) dbObject).getString("container");
			  if (container.equals(eFQN))
			  { 		     
				    String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
					String FQN = ((BasicBSONObject) dbObject).getString("FQN");    
					String name = ((BasicBSONObject) dbObject).getString("name");  
					String duribility = ((BasicBSONObject) dbObject).getString("duribility");
					String signature = ((BasicBSONObject) dbObject).getString("signature"); 
					String body = ((BasicBSONObject) dbObject).getString("body"); 
					
                    Method method = PLMFactory.eINSTANCE.createMethodWithLMLVisualizer();					 
		    		method.setName(name);      
		    		entityx.getFeature().add(method);	

					
			  }
 			   
		   }
	}
	public static void readbinmethod(String eFQN,org.melanee.core.models.plm.PLM.Connection bincx,DB db){
		  
		  DBCollection collection = db.getCollection("method"); 
		  DBCursor curs = collection.find();
		  while(curs.hasNext())
		  {   DBObject dbObject = curs.next(); 
			  String container = ((BasicBSONObject) dbObject).getString("container");
			  if (container.equals(eFQN))
			  { 		     
				    String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
					String FQN = ((BasicBSONObject) dbObject).getString("FQN");    
					String name = ((BasicBSONObject) dbObject).getString("name");  
					String duribility = ((BasicBSONObject) dbObject).getString("duribility");
					String signature = ((BasicBSONObject) dbObject).getString("signature"); 
					String body = ((BasicBSONObject) dbObject).getString("body"); 
					
                  Method method = PLMFactory.eINSTANCE.createMethodWithLMLVisualizer();					 
		    		method.setName(name);      
		    		bincx.getFeature().add(method);	

					
			  }
			   
		   }
	}
	public static void readbinaryconnection(DB db) 
	{ 
		DBCollection collection = db.getCollection("binaryconnection"); 
		  DBCursor curs = collection.find();
		  while(curs.hasNext())
		  {  
			DBObject dbObject = curs.next();
			String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
			String FQN = ((BasicBSONObject) dbObject).getString("FQN");    
			String name = ((BasicBSONObject) dbObject).getString("name");  
			String container = ((BasicBSONObject) dbObject).getString("container");    
			String duribility = ((BasicBSONObject) dbObject).getString("duribility");
			String type = ((BasicBSONObject) dbObject).getString("type"); 
			String value = ((BasicBSONObject) dbObject).getString("value"); 
			String mutability = ((BasicBSONObject) dbObject).getString("mutability"); 
			String potincy = ((BasicBSONObject) dbObject).getString("potincy");    
			String direct_type = ((BasicBSONObject) dbObject).getString("direct_type");  
			String label = ((BasicBSONObject) dbObject).getString("label");    
			String participant1 = ((BasicBSONObject) dbObject).getString("participant1");
			String participant2 = ((BasicBSONObject) dbObject).getString("participant2"); 
			String roleName1 = ((BasicBSONObject) dbObject).getString("roleName1"); 
			String roleName2 = ((BasicBSONObject) dbObject).getString("roleName2");
			String lower1 = ((BasicBSONObject) dbObject).getString("lower1");    
			String lower2 = ((BasicBSONObject) dbObject).getString("lower2");  
			String upper1 = ((BasicBSONObject) dbObject).getString("upper1");    
			String upper2 = ((BasicBSONObject) dbObject).getString("upper2");
			String navigalbeTo1 = ((BasicBSONObject) dbObject).getString("navigalbeTo1"); 
			String navigableTo2 = ((BasicBSONObject) dbObject).getString("navigableTo2"); 
		    Level level=levelfind(container);
		    MelaneeRelationshipset r1= new MelaneeRelationshipset();
			r1.mongobinaryconncetionsseting( entityfind(participant1),  entityfind(participant2), level,name,FQN,db); 
		    
			  
			   
		   }
	}
	public static void readinr(DB db) 
	{ 
		DBCollection collection = db.getCollection("inheritancerelationship"); 
		  DBCursor curs = collection.find();
		  while(curs.hasNext())
		  {  
			    DBObject dbObject = curs.next();
				String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
				String inhrFQN = ((BasicBSONObject) dbObject).getString("FQN");    
				String name = ((BasicBSONObject) dbObject).getString("name");  
				String container = ((BasicBSONObject) dbObject).getString("container");    
				String disjoint = ((BasicBSONObject) dbObject).getString("disjoint");
				String complete = ((BasicBSONObject) dbObject).getString("complete"); 
				readinp( inhrFQN,db);
				MelaneeRelationshipset r1= new MelaneeRelationshipset();
	 	        r1.inheritanceseting(entityfind(entityfqnsub1), entityfind(entityfqnsub2),entityfind(entityfqnsub3) ,entityfind(entityfqnsup), levelfind( container),name,complete,disjoint );   
	 	  
		    
			  
			   
		   }
	}
	
	public static void readinp(String inhrFQN,DB db) 
	{ 
		DBCollection collection = db.getCollection("inheritanceparticipation"); 
		  DBCursor curs = collection.find();
		  while(curs.hasNext())
		  {   		     
            DBObject dbObject = curs.next();
			String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
			String FQN = ((BasicBSONObject) dbObject).getString("FQN");    
			String name = ((BasicBSONObject) dbObject).getString("name");  
			String container = ((BasicBSONObject) dbObject).getString("container");    
			String participant = ((BasicBSONObject) dbObject).getString("participant");
			int inheritanceRelationship_id = ((BasicBSONObject) dbObject).getInt("inheritanceRelationship_id"); 
			String sinherid=String.valueOf(inheritanceRelationship_id);
			if(sinherid.equals("1"))
            {entityfqnsup=participant;  }
    		else if (sinherid.equals("2"))
            {entityfqnsub1=participant;}
    		 else if (sinherid.equals("3"))
             {entityfqnsub2=participant; }		
    		 else if (sinherid.equals("4"))
             {entityfqnsub3=participant; 
             }	
			   
		  
		  }
	}
	//。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。Classification.....
	public static void readclassification(DB db){
		  
		DBCollection collection = db.getCollection("entity"); 
		  DBCursor curs = collection.find();
		  while(curs.hasNext())
		  {   DBObject dbObject = curs.next(); 
			  String container = ((BasicBSONObject) dbObject).getString("container");
			  		     
				   String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
		           String eFQN = ((BasicBSONObject) dbObject).getString("FQN");    
		           String ename = ((BasicBSONObject) dbObject).getString("name");  
		           int potincy = ((BasicBSONObject) dbObject).getInt("potincy");  
		           String directtype = ((BasicBSONObject) dbObject).getString("directtype"); 
		           
		           if(directtype==null)
		    	     {}
 		      else if(directtype.equals("null")) 
 		         {
 		    	
 		            }
 		      else 
 		         {
 		    	 
 		    	  Entity entityfather=entityfind(eFQN);
 		//    	 System.out.println("FQN頁。。。。。。"+FQN+"directtype頁  。。。    "+directtype);
 		          Entity entitychild=entityfind(directtype);
 		          Level levelcf=(Level)entitychild.eContainer();
 	
 		    	  MelaneeRelationshipset r1= new MelaneeRelationshipset();
 		    	  r1.classificationset(entityfather,entitychild , levelcf);
 		    	 
 		           
					
			  }
 			   
		   }
	}
	
	
	public static void readconclassification(DB db){
		  
		DBCollection collection = db.getCollection("binaryconnection"); 
		  DBCursor curs = collection.find();
		  while(curs.hasNext())
		  {   DBObject dbObject = curs.next(); 
			  String container = ((BasicBSONObject) dbObject).getString("container");
			  		     
				   String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
		           String eFQN = ((BasicBSONObject) dbObject).getString("FQN");    
		           String ename = ((BasicBSONObject) dbObject).getString("name");  
		           int potincy = ((BasicBSONObject) dbObject).getInt("potincy");  
		           String directtype = ((BasicBSONObject) dbObject).getString("directtype"); 
		           
		           if(directtype==null)
		    	     {}
 		      else if(directtype.equals("null")) 
 		         {
 		    	
 		            }
 		      else 
 		         {
	    	      org.melanee.core.models.plm.PLM.Connection confather=connectionfind(eFQN);
  		    	  org.melanee.core.models.plm.PLM.Connection conchild=connectionfind(directtype);
  		          Level levelx=(Level)conchild.eContainer();  		      
  		    	  MelaneeRelationshipset r1= new MelaneeRelationshipset();
  		    	  r1.conclassificationset(confather, conchild, levelx);
					
			  }
 			   
		   }
	}
	 public static Level levelfind(String mysqlFQN) 
	  { 

		 int levelsize=deepmodel.eContents().size();
		 Level levelx=null;
		
		 for(int i = 0 ;i < levelsize; i++)  
		 { 
		
			 String leveltype=deepmodel.eContents().get(i).eClass().getName();
			 
			 if(leveltype=="Level")
			 { 
			   Level levely=(Level)deepmodel.eContents().get(i);
				String levelFQN=deepmodel.getName()+"."+(String)levely.getName();
		
				 if(mysqlFQN.equals(levelFQN)) {
					 levelx=levely;
			
				 }
			 }
			 
		 }
	
		 return levelx;
		 
	  }		  

	  public static Entity  entityfind( String mysqlentityFQN) {
			
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
			
			    	   if (entityFQN.equals(mysqlentityFQN))
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
	 
	// Melanee diagram create
  public static void diagramcreate(String name) 

	 {  	 diagram = ViewService.createDiagram(
				domain,
				DomainEditPart.MODEL_ID,
				PLMDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		        diagram.setName(name+".lml");
	  Node DeepModelview = ViewService.createNode(diagram, deepmodel,"2005",PLMDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT); 
	  resource1.getContents().add(diagram);
	  try {
		  resource1.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}

//	System.out.println("Melanee model create sucessfully");
	 
	 }	
}
