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
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.bson.BasicBSONObject;
import org.melanee.core.models.plm.PLM.Entity;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

	public class MogoDBCreate {
		
		static String mdlocalhost=Login.tmtexthostx();
		static int mdport=Integer.valueOf(Login.tmtextportx());
		static String tmtextdb=Login.tmtextdbx();
		static String tmtextuser=Login.tmtextuserx();
		static String tmtextpassword=Login.tmtextpasswordx();

		  static DB db =null;
		 
		
		  public static void main(String[] args) {

			  Entitydocumentinsert(123,"FQN123","name123","container123",123,"directtype123");
		
			  }

			  public static void  Packagedocumentinsert(String id,String FQN,String name, String container )
			  {
				
				    try {
			            MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
					    DB db = mongoClient.getDB(tmtextdb );
					    boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());					 
						DBCollection collection = db.getCollection("package");
						DBCursor cursor = collection.find();
						BasicDBObject document = new BasicDBObject();
						document.put("MySQLid", id);
						document.put("FQN",FQN);
						document.put("name", name);
	                    document.put("container", container);
						collection.insert(document);
						
					    } 
				    catch (UnknownHostException e) 
				      {
						e.printStackTrace();
					    } 
				    catch (MongoException e) 
				       {
						e.printStackTrace();
					    }
			  }

			  public static void  Deepmodeldocumentinsert(int id,int p_id,String FQN,String name, String container )
			  {
				     mdlocalhost=Login.tmtexthostx();
					  mdport=Integer.valueOf(Login.tmtextportx());
					  tmtextdb=Login.tmtextdbx();
					  tmtextuser=Login.tmtextuserx();
					  tmtextpassword=Login.tmtextpasswordx(); 
				  try {
						 
				    	clearmongodb();
				    	MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
					    DB db = mongoClient.getDB(tmtextdb );
					    boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());					 
						DBCollection collection = db.getCollection("deepmodel");
						DBCursor cursor = collection.find();
						BasicDBObject document = new BasicDBObject();
						document.put("MySQLid", id);
						document.put("p_id", p_id);
						document.put("FQN",FQN);
						document.put("name", name);
	                    document.put("container", container);
						collection.insert(document);
					    } catch (UnknownHostException e) {
						e.printStackTrace();
					    } catch (MongoException e) {
						e.printStackTrace();
					    }
			  }

			  public static void  Leveldocumentinsert(int id,String FQN,String name, String container,int number)
			  {
				    try {
						 
				    	MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
					    DB db = mongoClient.getDB(tmtextdb );
					    boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());
						DBCollection collection = db.getCollection("level");
						DBCursor cursor = collection.find();
						BasicDBObject document = new BasicDBObject();
						document.put("MySQLid", id);
						document.put("FQN",FQN);
						document.put("name", name);
	                    document.put("container", container);
	                    document.put("number", number);
						collection.insert(document);
					    } 
				    catch (UnknownHostException e) 
				    {
						e.printStackTrace();
					    } 
				    catch (MongoException e) {
						e.printStackTrace();
					    }
			  }
		
	// Level document insert end
		  public static void  Entitydocumentinsert(int id,String FQN,String name, String container,int potincy,String directtype)
		  		  
		  {
			    try {
					 
			    	MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
				    DB db = mongoClient.getDB(tmtextdb );
				    boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());
				 
					DBCollection collection = db.getCollection("entity");
					DBCursor cursor = collection.find();
					BasicDBObject document = new BasicDBObject();
					document.put("MySQLid", id);
					document.put("name",name);
					document.put("FQN",FQN);
                    document.put("container", container);
					document.put("potincy", potincy);
					collection.insert(document);

				    } catch (UnknownHostException e) {
					e.printStackTrace();
				    } catch (MongoException e) {
					e.printStackTrace();
				    }
		  }
//................................................................................................update..........		  
		  
		  public static void entityupdate(String entitychildFQN,String entityfatherFQN)
		  {
			 try {
			  MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
		      DB db = mongoClient.getDB(tmtextdb );
		      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());
		      DBCollection collection = db.getCollection("entity");
		      
				  DBCursor curs = collection.find();
				  while(curs.hasNext())
				  {   DBObject dbObject = curs.next(); 
					  String FQN = ((BasicBSONObject) dbObject).getString("FQN");
					  String exdirecttype = ((BasicBSONObject) dbObject).getString("directtype");
					  if (FQN.equals(entityfatherFQN))
					  { 
						 
						  dbObject.put("directtype",entitychildFQN ); 
						  collection.save(dbObject);
			  }
		 			   
				   }
			  } catch (UnknownHostException e) {
					e.printStackTrace();
				    } catch (MongoException e) {
					e.printStackTrace();
				    }
			}

		  public static void  Attributedocumentinsert(int id,String FQN,String name, String container,String duribility,String type,String value,String mutability)
		  {
			    try {
					 
			    	 MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
				      DB db = mongoClient.getDB(tmtextdb );
				      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());
					DBCollection collection = db.getCollection("attribute");
					DBCursor cursor = collection.find();
					BasicDBObject document = new BasicDBObject();
					document.put("MySQLid", id);
					document.put("FQN",FQN);
					document.put("name", name);
                    document.put("container", container);
                    document.put("duribility", duribility);
                    document.put("type", type);
                    document.put("duribility", duribility);
                    document.put("value", value);
                    document.put("duribility", duribility);
                    document.put("mutability", mutability);
					collection.insert(document);
				    } 
			    catch (UnknownHostException e) 
			    {
					e.printStackTrace();
				    } 
			    catch (MongoException e) {
					e.printStackTrace();
				    }
		  }
		  	
// attribute document insert end
		  
      public static void  Methoddocumentinsert(int id,String FQN,String name, String container,String duribility,String signature,String body)
		  {
			    try {
					 
			    	 MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
				      DB db = mongoClient.getDB(tmtextdb );
				      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());
					DBCollection collection = db.getCollection("method");
					DBCursor cursor = collection.find();
					BasicDBObject document = new BasicDBObject();
					document.put("MySQLid", id);
					document.put("FQN",FQN);
					document.put("name", name);
                    document.put("container", container);
                    document.put("duribility", duribility);
                    document.put("signature", signature);
                    if(body==null)
               	 { 	
               		 body="null";
               		 
               	 }
                    document.put("body", body);
   					collection.insert(document);
				    } 
			    catch (UnknownHostException e) 
			    {
					e.printStackTrace();
				    } 
			    catch (MongoException e) {
					e.printStackTrace();
				    }
		  }
		// Binary document insert
	  public static void  Binarydocumentinsert(int id,String FQN,String name, String container,int potency,String directtype,String label ,String participant1,
	    		String participant2, String roleName1 ,String roleName2,String  lower1, String lower2 ,	String upper1 ,String upper2 , 
	    		String navigalbeTo1, String navigableTo2)
	  {
		    try {
				 
		    	 MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
			      DB db = mongoClient.getDB(tmtextdb );
			      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());
				DBCollection collection = db.getCollection("binaryconnection");
				DBCursor cursor = collection.find();
				BasicDBObject document = new BasicDBObject();
				document.put("MySQLid", id);
				document.put("FQN",FQN);
				document.put("name", name);        
	            document.put("potincy", potency);
	            document.put("container", container);
//	            document.put("directtype", directtype);
	            document.put("label", label);
	            document.put("participant1", participant1);
	            document.put("participant2", participant2);
	            document.put("roleName1", roleName1);
	            document.put("roleName2", roleName2);
	            document.put("lower1", lower1);
	            document.put("lower2", lower2);
	            document.put("upper1", upper1);
	            document.put("upper2", upper2);
	            document.put("navigalbeTo1", navigalbeTo1);
	            document.put("navigableTo2", navigableTo2);
				collection.insert(document);
			    } 
		    catch (UnknownHostException e) 
		    {
				e.printStackTrace();
			    } 
		    catch (MongoException e) {
				e.printStackTrace();
			    }
	  }
	//...................................................................binaryconnetcion classfication......update..........		  
	  
	  public static void Binaryconnectionupdate(String coninstanceFQN,String conFQN){
		  
		  try {
		  MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
	      DB db = mongoClient.getDB(tmtextdb );
	      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());
	  	DBCollection collection = db.getCollection("binaryconnection");
	      
			  DBCursor curs = collection.find();
			  while(curs.hasNext())
			  {   DBObject dbObject = curs.next(); 
				  String FQN = ((BasicBSONObject) dbObject).getString("FQN");
				  String exdirecttype = ((BasicBSONObject) dbObject).getString("directtype");
				  if (FQN.equals(coninstanceFQN))
				  { 
					 
					  dbObject.put("directtype",conFQN ); 
					  collection.save(dbObject);
		  }
	 			   
			   }
		  } catch (UnknownHostException e) {
				e.printStackTrace();
			    } catch (MongoException e) {
				e.printStackTrace();
			    }
		}
		
	  	
//Binary document update end

		// inheritancerelationship document insert
		  public static void  Inrdocumentinsert(int id,String FQN,String name, String container,String disjoint,String complete )
		  {
			    try {
					 
			    	 MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
				      DB db = mongoClient.getDB(tmtextdb );
				      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray()); 
					DBCollection collection = db.getCollection("inheritancerelationship");
					DBCursor cursor = collection.find();
					BasicDBObject document = new BasicDBObject();
					document.put("MySQLid", id);
					document.put("FQN",FQN);
					document.put("name", name);
                    document.put("container", container);
                    document.put("disjoint", disjoint);
                    document.put("complete", complete);
   					collection.insert(document);
				    } 
			    catch (UnknownHostException e) 
			    {
					e.printStackTrace();
				    } 
			    catch (MongoException e) {
					e.printStackTrace();
				    }
		  }
		  	
// inheritancerelationship document insert end
		  
// inheritanceparticipation document insert
		  public static void  Inpdocumentinsert(int id,String FQN,String name, String container,String participant,int inheritanceRelationship_id)
		  {
			    try {
					 
			    
			    	 MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
				      DB db = mongoClient.getDB(tmtextdb );
				      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());
				  	DBCollection collection = db.getCollection("inheritanceparticipation");
					DBCursor cursor = collection.find();
					BasicDBObject document = new BasicDBObject();
					document.put("MySQLid", id);
					document.put("FQN",FQN);
					document.put("name", name);
                    document.put("container", container);
                    document.put("participant", participant);
                    document.put("inheritanceRelationship_id", inheritanceRelationship_id);
   					collection.insert(document);
				    } 
			    catch (UnknownHostException e) 
			    {
					e.printStackTrace();
				    } 
			    catch (MongoException e) {
					e.printStackTrace();
				    }
		  }
		  	
// inheritanceparticipation document insert end

		  
		// method document insert
	
		  	
// method document insert end

			// Gerneraldocumentinsert document insert
		  public static void  Generaldocumentinsert(String id,String FQN,String name, String container,int potincy,String directtype,String label,
				  String kind)
		  {
			    try {
					 
			    	 MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
				      DB db = mongoClient.getDB(tmtextdb );
				      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());
					DBCollection collection = db.getCollection("generalconnection");
					DBCursor cursor = collection.find();
					BasicDBObject document = new BasicDBObject();
					document.put("MySQLid", id);
					document.put("FQN",FQN);
					document.put("name", name);        
                    document.put("potincy", potincy);
                    document.put("container", container);
                    document.put("directtype", directtype);
                    document.put("label", label);
                    document.put("kind", kind);
   					collection.insert(document);
   					
				    } 
			    catch (UnknownHostException e) 
			    {
					e.printStackTrace();
				    } 
			    catch (MongoException e) {
					e.printStackTrace();
				    }
		  }
		  	
// Gerneral document insert end
		  
// Participationdocumentinsert document insert
 public static void  Participationdocumentinsert( String id,String name,String FQN,String container,int participant_id,String lower,String upper,
				  String row_name,String whole,String generalConnection)
		  {
			    try {
					 
			    	 MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
				      DB db = mongoClient.getDB(tmtextdb );
				      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());	 
					DBCollection collection = db.getCollection("participation");
					DBCursor cursor = collection.find();
					BasicDBObject document = new BasicDBObject();
					document.put("MySQLid", id);
					document.put("FQN",FQN);
					document.put("name", name);        
                    document.put("container", container);
                    document.put("participant_id", participant_id);
                    document.put("lower", lower);
                    document.put("upper", upper);
                    document.put("row_name", row_name);
                    document.put("whole", whole);
                    document.put("generalConnection", generalConnection);
   					collection.insert(document);
				    } 
			    catch (UnknownHostException e) 
			    {
					e.printStackTrace();
				    } 
			    catch (MongoException e) {
					e.printStackTrace();
				    }
		  }
		  	
// Participationdocumentinsert document insert end
		  
		 
		  
		  
//clear the collection 11042014
	  public static void  clearmongodb()
		  
	  {
		    try {
				 
		    	 MongoClient mongoClient = new MongoClient( mdlocalhost , mdport );
			      DB db = mongoClient.getDB(tmtextdb );
			      boolean auth = db.authenticate(tmtextuser, tmtextpassword.toCharArray());
			 
				DBCollection collection1 = db.getCollection("entity");
				collection1.drop();
				DBCollection collection2 = db.getCollection("attribute");
				collection2.drop();
				DBCollection collection3 = db.getCollection("deepmodel");
				collection3.drop();
				DBCollection collection4 = db.getCollection("level");
				collection4.drop();
				DBCollection collection5 = db.getCollection("binaryconnection");
				collection5.drop();
				DBCollection collection6 = db.getCollection("inheritanceparticipation");
				collection6.drop();
				DBCollection collection7 = db.getCollection("inheritancerelationship");
				collection7.drop();
				DBCollection collection8 = db.getCollection("method");
				collection8.drop();
				DBCollection collection9 = db.getCollection("package");
				collection9.drop();
				DBCollection collection10 = db.getCollection("generalconnection");
				collection10.drop();
				DBCollection collection11 = db.getCollection("participation");
				collection11.drop();
				
				
//				DBCursor cursor = collection.find();			     
//				while(cursor.hasNext()) {
//			             System.out.println(cursor.next());
//			        }			 
//				collection.drop();


			    } catch (UnknownHostException e) {
				e.printStackTrace();
			    } catch (MongoException e) {
				e.printStackTrace();
			    }
	  }
	}
	
	////clear the collection 11042014 end