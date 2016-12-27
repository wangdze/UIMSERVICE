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
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.melanee.core.modeleditor.edit.parts.DomainEditPart;
import org.melanee.core.modeleditor.part.PLMDiagramEditorPlugin;
import org.melanee.core.models.plm.PLM.Attribute;
import org.melanee.core.models.plm.PLM.DeepModel;
import org.melanee.core.models.plm.PLM.Domain;
import org.melanee.core.models.plm.PLM.Entity;
import org.melanee.core.models.plm.PLM.Inheritance;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.Method;
import org.melanee.core.models.plm.PLM.PLMFactory;
import org.melanee.core.models.plm.PLM.PLMPackage;
//import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBUpdateMelanee {
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
	  static String melaneefile=Login.j1textmelanee();
	
	
	public static void main( String args[] ){

	   }
	

	 public  static void melaneemodelupdate() {


		   org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("lml", new XMIResourceFactoryImpl());
		   org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(org.eclipse.emf.ecore.resource.Resource.Factory.Registry.DEFAULT_EXTENSION, new EcoreResourceFactoryImpl());
		   ResourceSetImpl resourceSet = new ResourceSetImpl();
			 resourceSet.getPackageRegistry().put(PLMPackage.eNS_URI, PLMPackage.eINSTANCE);
			 resourceSet.getPackageRegistry().put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);	
			 // melaneeresource = resourceSet.getResource(URI.createFileURI("D:/eclipselunaNew/workspace/org.uim.models/Example/test.lml"),true);
			 resource1 = resourceSet.getResource(URI.createFileURI(melaneefile),true);
			 EList<EObject> lmlResourcContents = resource1.getContents();
				{	
					if (lmlResourcContents.get(0) != null && lmlResourcContents.get(0) instanceof Domain ) 
			{		   	  
			
			    deepmodel=(DeepModel)lmlResourcContents.get(0).eContents().get(0);	
			    ReadMongoDB();
			    save( ); 	
			}
			    
			}
		}
	
	public  static void ReadMongoDB(){
	      try{   

	      MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
	      DB db = mongoClient.getDB(tmtextdb );
	      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray()); 
	
		//  boolean auth = db.authenticate("root", "12345678".toCharArray()); 
	
		  DBObject allQuery = new BasicDBObject(); 
          DBCollection collection = db.getCollection("deepmodel"); 
	
		  DBCursor curs = collection.find();
		    while(curs.hasNext()) 
		     {
		    	    DBObject dbObject = curs.next();
		      	    String id = ((BasicBSONObject) dbObject).getString("MySQLid");  
		      	    int p_id = ((BasicBSONObject) dbObject).getInt("p_id");
		    	    String FQN = ((BasicBSONObject) dbObject).getString("FQN");    
		    	    String name = ((BasicBSONObject) dbObject).getString("name");  
		    	    String container = ((BasicBSONObject) dbObject).getString("container");       

		    	    

		     		
	    		
		    		readlevel(FQN,deepmodel,db);	
		    		readbinaryconnection(db);	
		    		readinr(db);  
		    		deepmodel.setName(name);

		    	    
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
					

					
					Level levelx=levelfind(lFQN);
					readentity(lFQN,levelx,db);
	       			levelx.setName(name);
					
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
		          	           

		      	 Entity ex= entityfind(eFQN);
        		 if(ex==null)
        		 {
        				Entity entityx=PLMFactory.eINSTANCE.createEntityWithLMLVisualizer(); 
        				entityx.setName(ename);
        				entityx.setPotency(potincy);
        				 readattribute(eFQN,entityx,db);
        				 readmethod(eFQN,entityx,db);
        				 levelx.getContent().add(entityx);	
        		 }
        		 else {
         			           			 
        		
        	     readattribute(eFQN,ex,db);
        		 readmethod(eFQN,ex,db);
        		 ex.setName(ename);
//        		 ex.setPotency(potincy);

        	
        		 }
          	 
					
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
					
//				    Attribute attribute1 = PLMFactory.eINSTANCE.createAttributeWithLMLVisualizer();
//				    attribute1.setName(name);		 
//				    entityx.getFeature().add(attribute1);
					   Attribute attributex = attributefind(FQN); 
			      		 if(attributex==null)
			      		 {
			      			  Attribute attribute1 = PLMFactory.eINSTANCE.createAttributeWithLMLVisualizer();
			        		  attribute1.setName(name);		        		 
			        		  entityx.getFeature().add(attribute1);	
			      		 }
			      		 else
			      		 {

			      			attributex.setName(name);
			      			//attributex.setValue(value);
			      			 
			      		 }

					
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
					
//                    Method method = PLMFactory.eINSTANCE.createMethodWithLMLVisualizer();					 
//		    		method.setName(name);      
//		    		entityx.getFeature().add(method);	
					  Method methodx = methodfind(FQN); 
		        		 if(methodx==null)
		        		 {
		        		   Method method = PLMFactory.eINSTANCE.createMethodWithLMLVisualizer();
		           		   method.setName(name);      
		           		   entityx.getFeature().add(method);	
		        		 }
		        		 else
		        		 {
//		        			 methodx.setBody(body);
		        			 methodx.setName(name);
		        			 
		        		 }
					
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
					
//                  Method method = PLMFactory.eINSTANCE.createMethodWithLMLVisualizer();					 
//		    		method.setName(name);      
//		    		bincx.getFeature().add(method);	
		    		
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
//		    Level level=levelfind(container);
//		    MelaneeRelationshipset r1= new MelaneeRelationshipset();
//			r1.mongobinaryconncetionsseting( entityfind(participant1),  entityfind(participant2), level,name,FQN,db); 
			  org.melanee.core.models.plm.PLM.Connection connectionx=connectionfind(FQN);
              if(connectionx==null)
              {
           	   Level level=levelfind(container);
//   		    MelaneeRelationshipset r1= new MelaneeRelationshipset();
//   			r1.mongobinaryconncetionsseting( entityfind(participant1),  entityfind(participant2), level,name,FQN,db); 
              }
              else
              {
           	      readbinattribute(FQN, connectionx, db);
      	          readbinmethod(FQN, connectionx, db);         		   
         		  connectionx.setName(name);
              }
			   
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

			
				
	 	  
	 	  	  Inheritance inh1 = inheritancefind(inhrFQN);
	    		 if(inh1==null)	
	    		 {
	    			 readinp( inhrFQN,db);
	    			 MelaneeRelationshipset r1= new MelaneeRelationshipset();
	 	 	        r1.inheritanceseting(entityfind(entityfqnsub1), entityfind(entityfqnsub2),entityfind(entityfqnsub3) ,entityfind(entityfqnsup), levelfind( container),name,complete,disjoint );   
	    	      
	    		 }
	    		 else 
	    		 {
	    			 inh1.setName(name);
	    		
	    		 }
	    		 
			  
			   
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
			//	System.out.println("levelFQN是  "+levelFQN+"   mysqlFQN是  "+mysqlFQN);
				 if(mysqlFQN.equals(levelFQN)) {
					 levelx=levely;
				//	 System.out.println("levelx是什么"+levelx);
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
			//	 System.out.println("测试deepmode后去方法"+deepmodel.eContents().get(1).eContents().get(1)) ;
				 String leveltype=deepmodel.eContents().get(i).eClass().getName();
			
				 if(leveltype=="Level")
				 { 
					Level levelx=(Level)deepmodel.eContents().get(i);
				    int entitysize=levelx.eContents().size();
				//    System.out.println("查找的entitysize是"+entitysize);
				    for(int j = 0 ;j < entitysize; j++)  
				    {
				       String entitytype=levelx.eContents().get(j).eClass().getName();
				       
				       if(entitytype=="Entity")
				       {   Entity enttiyx=(Entity)levelx.eContents().get(j);
				    	   String entityFQN=deepmodel.getName()+"."+levelx.getName()+"."+enttiyx.getName();
				//    	   System.out.println("entityFQN"+entityFQN+"mysqlentityFQNs是"+mysqlentityFQN); 
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
			//	 System.out.println("测试deepmode后去方法"+deepmodel.eContents().get(1).eContents().get(1)) ;
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
	 
	 public static Method  methodfind( String aFQN) {
			
		  int levelsize=deepmodel.eContents().size();
			
		  Method melaneemethod=null;
			 for(int i = 0 ;i < levelsize; i++)  
			 { 
			//	 System.out.println("测试deepmode后去方法"+deepmodel.eContents().get(1).eContents().get(1)) ;
				 String leveltype=deepmodel.eContents().get(i).eClass().getName();
			
				 if(leveltype=="Level")
				 { 
					Level levelx=(Level)deepmodel.eContents().get(i);
				    int entitysize=levelx.eContents().size();
				//    System.out.println("查找的entitysize是"+entitysize);
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


	public static Attribute  binattributefind( String aFQN) {
			
		  int levelsize=deepmodel.eContents().size();
			
		  Attribute melaneeattribute=null;
			 for(int i = 0 ;i < levelsize; i++)  
			 { 
			//	 System.out.println("测试deepmode后去方法"+deepmodel.eContents().get(1).eContents().get(1)) ;
				 String leveltype=deepmodel.eContents().get(i).eClass().getName();
			
				 if(leveltype=="Level")
				 { 
					Level levelx=(Level)deepmodel.eContents().get(i);
				    int entitysize=levelx.eContents().size();
				//    System.out.println("查找的entitysize是"+entitysize);
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
				    	  
				//    	   System.out.println("entityFQN"+entityFQN+"mysqlentityFQNs是"+mysqlentityFQN); 
			    	
				    	  
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
	// Melanee diagram create
	  public static void  save( ) {
			 try {
				 resource1.save(null);
			
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
}
