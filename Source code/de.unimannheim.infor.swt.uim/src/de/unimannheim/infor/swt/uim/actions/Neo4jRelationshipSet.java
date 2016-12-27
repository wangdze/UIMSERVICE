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

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Direction;  
//import org.neo4j.kernel.impl.util.FileUtils;  
//import org.neo4j.kernel.impl.util.StringLogger; 
 

 
public class  Neo4jRelationshipSet {  

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
   //  private static final String DB_PATH =Login.tntextneo4joutputx() +"/db";
    // private static final String DB_PATH =Login.tntextneo4joutputx();
	// private static final String DB_PATH = "D:/eclipselunaNew/workspace/org.uim.models/Neo4j/db";//"neo4j-db";//  
       private static  String DB_PATH =Login.tntextneo4joutputx();
       private static final String USERNAME_KEY = "FQN";
     private static Index<Node> nodeIndex;  
     private static GraphDatabaseService graphDb;
     private static Relationship relationship;

     public static void main(String[] args) {  

     } 
     

public static void relationshipset1(String content1,String content2) { 
    	

     
      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook(graphDb);
    
          // START SNIPPET: addUsers
      Transaction tx = graphDb.beginTx();
        try{  
        	nodeIndex = graphDb.index().forNodes( "nodes" );
            Node firstNode = nodeIndex.get( USERNAME_KEY, content1).getSingle();
            if(firstNode==null)
            		{System.out.println("This node are null="+content1);  }
            Node secondNode = nodeIndex.get( USERNAME_KEY, content2).getSingle();         
            if(secondNode==null)
            {System.out.println("This node are null="+content2);  }
            else{}

            relationship = firstNode.createRelationshipTo(secondNode, RelTypes.participant1);  
        	relationship.setProperty("message", "Uimteamproject");
        	
        	
//            nodesearch(content);
            tx.success();//戻住             
//            graphDb.shutdown();
//            return secondNode;
//          
      } 
//       shutdown();
        finally{  
        	tx.finish();  
            graphDb.shutdown();
    }  
    } 
////Relationship 幹秀撹孔 End


public static void relationshipset2(String content1,String content2) { 
	

	
 
  graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
  registerShutdownHook(graphDb);

      // START SNIPPET: addUsers
  Transaction tx = graphDb.beginTx();
    try{  
    	nodeIndex = graphDb.index().forNodes( "nodes" );
        Node firstNode = nodeIndex.get( USERNAME_KEY, content1).getSingle();
        if(firstNode==null)
		{System.out.println("This node are null="+content1);  }
        Node secondNode = nodeIndex.get( USERNAME_KEY, content2).getSingle();
        if(secondNode==null)
        {System.out.println("This node are null="+content2);  }

        relationship = firstNode.createRelationshipTo(secondNode, RelTypes2.participant2);  
    	relationship.setProperty("message", "Uimteamproject");
    	
//        nodesearch(content);
        tx.success();//戻住             
   
  } 
//   shutdown();
    finally{  
    	tx.finish();  
        graphDb.shutdown();
}  
} 
////Relationship  End
// 1027 2014 inherelationshi

public static void inhrelationshipset(String content1,String content2) { 
	
//  deleteFileOrDirectory( new File( DB_PATH ) );
	
 
  graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
  registerShutdownHook(graphDb);

      // START SNIPPET: addUsers
  Transaction tx = graphDb.beginTx();
    try{  
    	nodeIndex = graphDb.index().forNodes( "nodes" );
        Node firstNode = nodeIndex.get( USERNAME_KEY, content1).getSingle();
        if(firstNode==null)
   		{System.out.println("This node are null="+content1);  }
        Node secondNode = nodeIndex.get( USERNAME_KEY, content2).getSingle();
        if(secondNode==null)
   		{System.out.println("This node are null="+content2);  }

        relationship = firstNode.createRelationshipTo(secondNode, RelTypesinh.inheritance);  
    	relationship.setProperty("message", "Uimteamproject");
    	
    	
//        nodesearch(content);
        tx.success();//戻住             
      
  } 

    finally{  
    	tx.finish();  
        graphDb.shutdown();
}  
} 

public static void inhrpset(String content1,String content2) { 
	
//  deleteFileOrDirectory( new File( DB_PATH ) );
	
 
  graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
  registerShutdownHook(graphDb);

      // START SNIPPET: addUsers
  Transaction tx = graphDb.beginTx();
    try{  
    	nodeIndex = graphDb.index().forNodes( "nodes" );
        Node firstNode = nodeIndex.get( USERNAME_KEY, content1).getSingle();
        if(firstNode==null)
   		{System.out.println("This node are null="+content1);  }
        Node secondNode = nodeIndex.get( USERNAME_KEY, content2).getSingle();
        if(secondNode==null)
   		{System.out.println("This node are null="+content2);  }

        relationship = firstNode.createRelationshipTo(secondNode, RelTypesinhp.participant);  
    	relationship.setProperty("message", "Uimteamproject");
    	
//        nodesearch(content);
        tx.success();//戻住             
      
  } 

    finally{  
    	tx.finish();  
        graphDb.shutdown();
}  
} 

//Relationship End

//1107 2014 generalconnection 

public static void generalrelationshipset(String content1,String content2) { 
	

graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
registerShutdownHook(graphDb);

   // START SNIPPET: addUsers
Transaction tx = graphDb.beginTx();
 try{  
 	nodeIndex = graphDb.index().forNodes( "nodes" );
     Node firstNode = nodeIndex.get( USERNAME_KEY, content1).getSingle();
     Node secondNode = nodeIndex.get( USERNAME_KEY, content2).getSingle();
     relationship = firstNode.createRelationshipTo(secondNode, RelTypesinhp.participant);  
 	relationship.setProperty("message", "Uimteamproject");

     tx.success();//戻住             

} 

 finally{  
 	tx.finish();  
     graphDb.shutdown();
}  
} 
//generalconnection   End


//diecttype relationship 


public static void directtyperelationshipset(String content1,String content2) { 
	

	

graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
registerShutdownHook(graphDb);

   // START SNIPPET: addUsers
Transaction tx = graphDb.beginTx();
 try{  
 	nodeIndex = graphDb.index().forNodes( "nodes" );
     Node firstNode = nodeIndex.get( USERNAME_KEY, content1).getSingle();
     Node secondNode = nodeIndex.get( USERNAME_KEY, content2).getSingle();
     if (secondNode!=null)
     { 
    	 relationship = firstNode.createRelationshipTo(secondNode, RelTypesdir.directType);  
    	  	relationship.setProperty("message", "Uimteamproject");
   
     } 
     else { 
    	 
    	 System.out.println("The program can't found the Node");
     }

     tx.success();//戻住             
  
} 

 finally{  
 	tx.finish();  
     graphDb.shutdown();
}  
} 

// directtype relationship end

    private static void shutdown()
    {
        graphDb.shutdown();
    }
    
     // parent relationshp setting
    
public static void parentsetting(String content1,String content2) { 
	
	DB_PATH =Login.tntextneo4joutputx();
	
	 if(DB_PATH.equals(null))
	   { 
		System.out.println("UIM service can not get DB_PATH, please restart the UIM service");   
	   }	
	 
   
      graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
      registerShutdownHook(graphDb);
    
          // START SNIPPET: addUsers
      Transaction tx = graphDb.beginTx();
        try{  
        	nodeIndex = graphDb.index().forNodes( "nodes" );
//            String userName =content1;
            Node firstNode = nodeIndex.get( USERNAME_KEY, content1).getSingle();
         
            if(firstNode==null)
       		{System.out.println("The firstnode is null:"+content1);  }
            Node secondNode = nodeIndex.get( USERNAME_KEY, content2).getSingle();
            if(secondNode==null)
       		{System.out.println("The second is null:"+content2);  }
            relationship = firstNode.createRelationshipTo(secondNode, RelTypes1.parent);  
        	relationship.setProperty("message", "Uimteamproject");
        	
//            nodesearch(content);
            tx.success();//戻住             
         
      } 
        
//       shutdown();       
        finally{  
        	tx.finish();  
            graphDb.shutdown();
    }  
    } 

    
//。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。Classifcation set
public static void classificationset(String content1,String content2) { 
	

	
	 
	  graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
	  registerShutdownHook(graphDb);

	      // START SNIPPET: addUsers
	  Transaction tx = graphDb.beginTx();
	    try{  
	    	nodeIndex = graphDb.index().forNodes( "nodes" );
	        Node firstNode = nodeIndex.get( USERNAME_KEY, content1).getSingle();
	        if(firstNode==null)
			{System.out.println("This node are null="+content1);  }
	        Node secondNode = nodeIndex.get( USERNAME_KEY, content2).getSingle();
	        if(secondNode==null)
	        {System.out.println("This node are null="+content2);  }

	        relationship = firstNode.createRelationshipTo(secondNode, RelTypesdir.directType);  
	    	relationship.setProperty("message", "Uimteamproject");
	    	
//	        nodesearch(content);
	        tx.success();//戻住             
	   
	  } 
//      shutdown();       
       finally{  
       	tx.finish();  
           graphDb.shutdown();
   }  
   } 

//。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。Classifcation set

    private static void registerShutdownHook( final GraphDatabaseService graphDb )
    {

        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                graphDb.shutdown();
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
