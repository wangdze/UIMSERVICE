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
import java.io.File;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.RelationshipType;

 
public class  Neo4jNodesCreate {  

    private static enum RelTypes implements RelationshipType{  
        Binaryconnection 
    };  
    // private static final String DB_PATH =Login.tntextneo4joutputx();
  //new
    
    private static  String DB_PATH =Login.tntextneo4joutputx();
  //newe
     
//	 private static final String DB_PATH = "D:/eclipselunaNew/workspace/org.uim.models/Neo4j/db";//"neo4j-db";//  
	 private static final String USERNAME_KEY = "FQN";

     private static Index<Node> nodeIndex;  
     private static GraphDatabaseService graphDb;


     public static void main(String[] args) {  

    	 Neo4jNodesCreate  hwt = new Neo4jNodesCreate();  

     } 
     

//Entity Node create 
    
    public static void Nodeentitycreate(int id,String name,String FQN , String container,int potincy,String directtype,String table )
    { 
    	

      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook();
    
          // START SNIPPET: addUsers
        try ( Transaction tx = graphDb.beginTx() ) {  
        	 nodeIndex = graphDb.index().forNodes( "nodes" );
          	 Node node = graphDb.createNode();
        	 Label mylabel = DynamicLabel.label("entity");
        	 node.addLabel(mylabel);
        	 node.setProperty( "name", name );
        	 node.setProperty( "id", id );
        	 node.setProperty( USERNAME_KEY, FQN );
        	 node.setProperty( "container", container );
        	 node.setProperty( "potincy", potincy );
        	 node.setProperty( "directtype", directtype);
             nodeIndex.add( node, USERNAME_KEY, FQN );
        
//            String userName =FQN;
//            nodesearch(FQN);

          tx.success();//提交  
      } 
        shutdown();
    }  
 
//Entity Node create end
    
//binarynodecreate
      
    public static void Nodebinarycreate(int id,String FQN,String name, String container,int potency,String directtype,String label ,String particpant1,
    		String particpant2, String roleName1 ,String roleName2,String  lower1, String lower2 ,	String upper1 ,String upper2 , 
    		String navigalbeTo1, String navigableTo2,String table )

    { 
    	

      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook();
    
          // START SNIPPET: addUsers
        try ( Transaction tx = graphDb.beginTx() ) {  
        	 nodeIndex = graphDb.index().forNodes( "nodes" );
          	 Node node = graphDb.createNode();
        	 Label mylabel = DynamicLabel.label("binaryconnection");
        	 node.addLabel(mylabel);
        	 node.setProperty( "name", name );
        	 node.setProperty( "id", id );
        	 node.setProperty( USERNAME_KEY, FQN );
        	 node.setProperty( "container", container );
        	 node.setProperty( "potincy", potency );
          	 node.setProperty( "directtype", directtype );
          	 node.setProperty( "label", label );
          	 node.setProperty( "particpant1", particpant1 );
          	 node.setProperty( "particpant2", particpant2 );
             node.setProperty( "roleName1", roleName1 );
           	 node.setProperty( "roleName2", roleName2 );
          	 node.setProperty( "lower1", lower1 );
          	 node.setProperty( "lower2", lower2 );
           	 node.setProperty( "upper1", upper1 );
          	 node.setProperty( "upper2", upper2 );
          	 node.setProperty( "navigalbeTo1", navigalbeTo1 );
          	 node.setProperty( "navigableTo2", navigableTo2 );       	
             nodeIndex.add( node, USERNAME_KEY, FQN );
       
             tx.success();//提交  
      } 
        shutdown();
    }  
 
//BinaryNode create  end
//Package Node create
    
    public static void PackageNodecreate(String id,String FQN,String name, String container,String table )
    { 
     	
//      deleteFileOrDirectory( new File( DB_PATH ) );
      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook();
    
          // START SNIPPET: addUsers
        try ( Transaction tx = graphDb.beginTx() ) {  
        	 nodeIndex = graphDb.index().forNodes( "nodes" );
          	 Node node = graphDb.createNode();
        	 Label mylabel = DynamicLabel.label("package");
        	 node.addLabel(mylabel);
        	 node.setProperty( "name", name );
        	 node.setProperty( "id", id );
        	 node.setProperty( USERNAME_KEY, FQN );
        	 node.setProperty( "container", container );
        	 nodeIndex.add( node, USERNAME_KEY, FQN );
             
          tx.success();//提交  
      } 
        shutdown();
    }  
 
//Package Node create end
//Deepmodel Node create
    
    public static void DeepmodelNodecreate(int id,int p_id,String FQN,String name, String container,String table )
    { 
    	   
    	  DB_PATH =Login.tntextneo4joutputx();
    	 if(DB_PATH.equals(null))
    	   { 
    		System.out.println("UIM service can not get DB_PATH, please restart the UIM service");   
    	   }
      deleteFileOrDirectory( new File( DB_PATH ) );
      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook();
    
          // START SNIPPET: addUsers
        try ( Transaction tx = graphDb.beginTx() ) {  
        	 nodeIndex = graphDb.index().forNodes( "nodes" );
          	 Node node = graphDb.createNode();
        	 Label mylabel = DynamicLabel.label("deepmodel");
        	 node.addLabel(mylabel);
        	 node.setProperty( "name", name );
        	 node.setProperty( "id", id );
        	 node.setProperty( USERNAME_KEY, FQN );
        	 node.setProperty( "container", container );
        	 node.setProperty( "p_id", p_id );
             nodeIndex.add( node, USERNAME_KEY, FQN );
             
//             System.out.println("the number of deepmodel"+node);
             
          tx.success();//提交  
      } 
        shutdown();
    }  
 
//Deepmodel Node create end
    
//Level Node create
    
    public static void LevelNodecreate(int id,String FQN,String name, String container,int number,String table )
    { 
    	
      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook();
    
          // START SNIPPET: addUsers
        try ( Transaction tx = graphDb.beginTx() ) {  
        	 nodeIndex = graphDb.index().forNodes( "nodes" );
          	 Node node = graphDb.createNode();
        	 Label mylabel = DynamicLabel.label("level");
        	 node.addLabel(mylabel);
        	 node.setProperty( "name", name );
        	 node.setProperty( "id", id );
        	 node.setProperty( USERNAME_KEY, FQN );
        	 node.setProperty( "container", container );
        	 node.setProperty( "number", number );
             nodeIndex.add( node, USERNAME_KEY, FQN );
             
//             System.out.println("the number of level "+node);
          tx.success();//提交  
      } 
        shutdown();
    }  
 
//Level Node create end
    
//Attribute Node create
    
    public static void AttributeNodecreate(int id,String FQN,String name, String container,String duribility,String type,String value,String mutability,String table )
    { 
    	
      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook();
    
          // START SNIPPET: addUsers
        try ( Transaction tx = graphDb.beginTx() ) {  
        	 nodeIndex = graphDb.index().forNodes( "nodes" );
          	 Node node = graphDb.createNode();
        	 Label mylabel = DynamicLabel.label("attribute");
        	 node.addLabel(mylabel);
        	 node.setProperty( "name", name );
        	 node.setProperty( "id", id );
        	 node.setProperty( USERNAME_KEY, FQN ); 	 
        	 node.setProperty( "container", container );
        	 node.setProperty( "duribility", duribility );
        	 if(type==null)
        	 { 	
        		 type="null";
        		 
        	 }
        	 node.setProperty( "type", type );
       
        	 if(value==null)
        	 { 	
        		 value="null";
        		 
        	 }
        	
        	 node.setProperty( "value", value );
        	 node.setProperty( "mutability", mutability );
             nodeIndex.add( node, USERNAME_KEY, FQN );
             
          tx.success();//提交  
      } 
        shutdown();
    }  
 //Attribute Node create end
    
//Method Node create
    
    public static void MethodNodecreate(int id,String FQN,String name, String container,String duribility,String signature,String body,String table )
    { 
    
      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook();
    
          // START SNIPPET: addUsers
        try ( Transaction tx = graphDb.beginTx() ) {  
        	 nodeIndex = graphDb.index().forNodes( "nodes" );
          	 Node node = graphDb.createNode();
        	 Label mylabel = DynamicLabel.label("method");
        	 node.addLabel(mylabel);
        	 node.setProperty( "name", name );
        	 node.setProperty( "id", id );
        	 node.setProperty( USERNAME_KEY, FQN ); 	 
        	 node.setProperty( "container", container );
        	 node.setProperty( "signature", signature );
        	 if(body==null)
        	 { 	
        		 body="null";
        		 
        	 }
        	 node.setProperty( "body", body );
        	
             nodeIndex.add( node, USERNAME_KEY, FQN );
             
          tx.success();//提交  
      } 
        shutdown();
    }  
 //Method Node create end

    private static void shutdown()
    {
        graphDb.shutdown();
    }
    
    private static String idToUserName( final int id )
    {
        return "user" + id + "@neo4j.org";
    }
    
    // create binaryconncetion node
    
//inheritancerelationship Node create
    
    public static void InrNodecreate(int id,String FQN,String name, String container,String disjoint,String complete,String table )
    { 
    	
      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook();
    
          // START SNIPPET: addUsers
        try ( Transaction tx = graphDb.beginTx() ) {  
        	 nodeIndex = graphDb.index().forNodes( "nodes" );
          	 Node node = graphDb.createNode();
        	 Label mylabel = DynamicLabel.label("inheritancerelationship");
        	 node.addLabel(mylabel);
        	 node.setProperty( "name", name );
        	 node.setProperty( "id", id );
        	 node.setProperty( USERNAME_KEY, FQN );
        	 node.setProperty( "container", container );
        	 node.setProperty( "disjoint", disjoint );
        	 node.setProperty( "complete", complete );
             nodeIndex.add( node, USERNAME_KEY, FQN );
             
          tx.success();//提交  
      } 
        shutdown();
    }  
 
//inheritancerelationship Node create end
    
//inheritancerelationship Node create
    
    public static void InpNodecreate(int id,String FQN,String name, String container,String participant,int inheritanceRelationship_id,String table )
    { 
    	
      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook();
    
          // START SNIPPET: addUsers
        try ( Transaction tx = graphDb.beginTx() ) {  
        	 nodeIndex = graphDb.index().forNodes( "nodes" );
          	 Node node = graphDb.createNode();
        	 Label mylabel = DynamicLabel.label("inheritanceparticipation");
        	 node.addLabel(mylabel);
        	 node.setProperty( "name", name );
        	 node.setProperty( "id", id );
        	 node.setProperty( USERNAME_KEY, FQN );
        	 node.setProperty( "container", container );
        	 node.setProperty( "participant", participant );
        	 node.setProperty( "inheritanceRelationship_id", inheritanceRelationship_id );
             nodeIndex.add( node, USERNAME_KEY, FQN );

          tx.success();//提交  
      } 
        shutdown();
    }  
 
//inheritancerelationship Node create end

    
//generalconnection  Node create
    
    public static void Nodegeneralcreate(String id,String FQN,String name, String container,int potincy,String directtype,String label,
  		  String kind,String table)
    { 
    	
      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook();
    
          // START SNIPPET: addUsers
        try ( Transaction tx = graphDb.beginTx() ) {  
        	 nodeIndex = graphDb.index().forNodes( "nodes" );
          	 Node node = graphDb.createNode();
        	 Label mylabel = DynamicLabel.label("generalconnection");
        	 node.addLabel(mylabel);
        	 node.setProperty( "name", name );
        	 node.setProperty( "id", id );
        	 node.setProperty( USERNAME_KEY, FQN );
        	 node.setProperty( "container", container );
        	 node.setProperty( "potincy", potincy );
        	 node.setProperty( "label", label );
        	 node.setProperty( "directtype", directtype );
        	 node.setProperty( "kind", kind );
             nodeIndex.add( node, USERNAME_KEY, FQN );
             
          tx.success();//提交  
      } 
        shutdown();
    }  
 
//generalconnection  Node create end
    
//participation  Node create
    
    public static void Nodeparticipationcreate( String id,String name,String FQN,String container,int participant_id,String lower,String upper,
  		  String row_name,String whole,String generalConnection,String table)
    { 
    	
      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook();
    
          // START SNIPPET: addUsers
        try ( Transaction tx = graphDb.beginTx() ) {  
        	 nodeIndex = graphDb.index().forNodes( "nodes" );
          	 Node node = graphDb.createNode();
        	 Label mylabel = DynamicLabel.label("participation");
        	 node.addLabel(mylabel);
        	 node.setProperty( "name", name );
        	 node.setProperty( "id", id );
        	 node.setProperty( USERNAME_KEY, FQN );
        	 node.setProperty( "container", container );
        	 node.setProperty( "participant_id", participant_id );
        	 node.setProperty( "lower", lower );
        	 node.setProperty( "upper", upper );
        	 node.setProperty( "row_name", row_name );
        	 node.setProperty( "whole", whole );
        	 node.setProperty( "generalConnection",generalConnection );
             nodeIndex.add( node, USERNAME_KEY, FQN );
             
          tx.success();//提交  
      } 
        shutdown();
    }  
 
//generalconnection  Node create end 
    
    
    
//  nodesearchinex  测试index创建是否成功
    public static Node nodesearch(String entityname) { 
       	
 
     	    Node foundUser = nodeIndex.get( USERNAME_KEY, entityname ).getSingle();
            System.out.println( "Mapping类The username of user index创建成功 " + "name" + " is "
                  + foundUser.getProperty( USERNAME_KEY ) );
            return foundUser;

    } 

//  nodesearchinex end
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

    
//    deleteFileOrDirectory
    private static void deleteFileOrDirectory( File file )
    {
        if ( file.exists() )
        {
            if ( file.isDirectory() )
            {
                for ( File child : file.listFiles() )
                {
                    deleteFileOrDirectory( child );
                }
            }
            file.delete();
        }
    }
}
