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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Shape;
//import org.junit.Test;
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
//import org.melanee.core.models.plm.PLM.Participation;
import org.melanee.core.models.plm.PLM.Subtype;
import org.melanee.core.models.plm.PLM.Supertype;
//import org.melanee.core.models.plm.PLM.Connection;

public class MySQLUpdateLML {

	static String melaneefile=Login.j1textmelanee();
	static String mysqlhost=Login.textmysqlhostx();
	static String mysqluser=Login.textuserx();
	static String mysqlpassword=Login.textpasswordx();
	static String sqldatabase="jdbc:mysql://"+mysqlhost;
	
	
	static String namedeepmodel="Start2";
	String id;
	String name;
	String FQN;
	static ResultSet res;
	String entityname;
	String message;
	String content;
	String particpant1;
	String particpant2;
	String databasetype;
	String Neo4j;
	String MongoDB;
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
	
	 public static void main(String[] args)
	 {
		
	//	 melaneemodelcreate();
	 }
	 

	public  static void melaneemodelupdate() {


		 Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("lml", new XMIResourceFactoryImpl());
		 Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new EcoreResourceFactoryImpl());
		 ResourceSetImpl resourceSet = new ResourceSetImpl();
		 resourceSet.getPackageRegistry().put(PLMPackage.eNS_URI, PLMPackage.eINSTANCE);
		 resourceSet.getPackageRegistry().put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);	
	//	 resource1 = resourceSet.getResource(URI.createFileURI("D:/eclipselunaNew/workspace/org.uim.models/Example/test.lml"),true);
		  resource1 = resourceSet.getResource(URI.createFileURI(melaneefile),true);
		 EList<EObject> lmlResourcContents = resource1.getContents();
			{	
				if (lmlResourcContents.get(0) != null && lmlResourcContents.get(0) instanceof Domain ) 
		{		   	  
		
		    deepmodel=(DeepModel)lmlResourcContents.get(0).eContents().get(0);	
		    readdeepmodel("deepmodel");
		    save( ); 	
		}
		    
		}
	}

	
// Read package end 
	
	public static void readdeepmodel(String table)  
	  {	 
		
	       melaneefile=Login.j1textmelanee();
	  	  mysqlhost=Login.textmysqlhostx();
	      mysqluser=Login.textuserx();
		  mysqlpassword=Login.textpasswordx();
		  sqldatabase="jdbc:mysql://"+mysqlhost;
		try  
        {  
            Statement stmt=null;  
            ResultSet res=null;  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
            stmt = (Statement)conn.createStatement();  
            res= stmt.executeQuery("SELECT * from   "+table);   
            while (res.next()){
            	
            	String id=res.getString("id");
        		 int p_id=res.getInt("p_id");
//        		   if(p_id==null)
//                     {p_id="null";}
//                      else p_id=p_id;
        		String FQN=res.getString("FQN");
        		   if(FQN==null)
                     {FQN="null";}
                      else FQN=FQN;
       		String name=res.getString("name");
       	    	if(name==null)
                     {name="null";}
                       else name=name;
       		String container=res.getString("container");          
       		   if(container==null)
                    {container="null";}
                     else container=container;
       		
    		readlevel("level",deepmodel,FQN);	
    		readbinaryconnection("binaryconnection");	
    	
//    		readinr();  
//    		readclassification(); 
//    		readconclassification();
//       		resource1.getContents().add(domain);      	
//       		diagramcreate(name) ;
    		
     }  
            
            
  
            while (res.next())   
            {   
              
            }   
        }  
      
        catch(Exception ex)  
        {  
            ex.printStackTrace();  
        }    	
		
	}

	
	public static void readlevel(String table,DeepModel deepmodelp,String DFQN)  
    {  
   
    	try  
        {  
            Statement stmt=null;  
            ResultSet res=null;  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);    
            stmt = (Statement)conn.createStatement();  
            res= stmt.executeQuery("SELECT * from   "+table+" where container='"+DFQN+"'");   

            while (res.next()){
            	
            	 if(table=="level")
    	          {
          	int id=res.getInt("id");
        		   
        		String name=res.getString("name");
       	    	if(name==null)
                     {name="null";}
                       else name=name;
        		String FQN=res.getString("FQN");
        		   if(FQN==null)
                     {FQN="null";}
                      else FQN=FQN;           
       		String container=res.getString("container");          
       		   if(container==null)
                    {container="null";}
                     else container=container;
       			int number=res.getInt("number");   
//       		 levelcreate ( name, id, deepmodelp,FQN);         
       			Level levelx=levelfind(FQN);
       			readentity("entity",FQN,levelx);
       			levelx.setName(name);
       			
       			
      }
     	
     }  
            
            
  
            while (res.next())   
            {   
        
            }   
        }  
      
        catch(Exception ex)  
        {  
            ex.printStackTrace();  
        }    	
    }  
	// entity binaryconnection read..
	
	public static void readentity(String table,String LFQN,Level level)  
     {  
    	try  
        {  
            Statement stmt=null;  
            ResultSet res=null;  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);    
            stmt = (Statement)conn.createStatement();  
            res= stmt.executeQuery("SELECT * from   "+table+" where container ="+"'"+LFQN+"'");   
            while (res.next()){
    
       		if(table=="entity")
            	{
            		int id=res.getInt("id");  
           		    String FQN=res.getString("FQN");

            		String name=res.getString("name");         		        	
            		String container=res.getString("container");
            		int potency=res.getInt("potincy");
            		String directtype=res.getString("direct_type");
            		 if(directtype==null)
  		    	     {directtype="null";}
        		      else directtype=directtype;         		
  
            		 Entity ex= entityfind(FQN);
            		 if(ex==null)
            		 {
            				Entity entityx=PLMFactory.eINSTANCE.createEntityWithLMLVisualizer(); 
            				entityx.setName(name);
            				entityx.setPotency(potency);
            				 readattribute("attribute",entityx,FQN);
            				 readmethod("method", entityx, FQN)  ;
            				 level.getContent().add(entityx);	
            		 }
            		 else {
             			           			 
            		
            		 readattribute("attribute",ex,FQN);
            		 readmethod("method", ex, FQN)  ;
            		 ex.setName(name);
            		 ex.setPotency(potency);
//           		    level.getContent().remove(ex) ;
            	
            		 }
              	 }

     }  
            
           while (res.next())   
            {   
           
            }   
        }  
      
        catch(Exception ex)  
        {  
            ex.printStackTrace();  
        }    	
    }  
	//  binaryconnection
	
	public static void readbinaryconnection(String table)  
    {  
   	try  
       {  
           Statement stmt=null;  
           ResultSet res=null;  
           Class.forName("com.mysql.jdbc.Driver");  
           Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
           stmt = (Statement)conn.createStatement();  
         //  res= stmt.executeQuery("SELECT * from   "+table+" where container ="+"'"+LFQN+"'");   
           res= stmt.executeQuery("SELECT * from  binaryconnection ");             
           while (res.next()){
          	int id1=res.getInt("id");
        
          	    String FQN=res.getString("FQN");
          	    if(FQN==null)
		    	     {FQN="null";}
      		      else FQN=FQN;
           		String name=res.getString("name");
           		 if(name==null)
 		    	     {name="null";}
       		      else name=name;
           		String container=res.getString("container");
           		 if(container==null)
 		    	     {container="null";}
       		      else container=container;
           		String potincy=res.getString("potency");
           		 if(potincy==null)
 		    	     {potincy="null";}
       		      else potincy=potincy;
           		String directtype=res.getString("direct_type");
           		  if(directtype==null)
     		    	   {directtype="directtype";}
     		           else directtype=directtype;
           		String label=res.getString("label");
           		  if(label==null)
        		    	{label="null";}
        		        else label=label;
           		String particpant1=res.getString("particpant1");
//           		   if(particpant1==null)
//     		    	     {particpant1="null";}
//           		      else particpant1=particpant1;
           		String particpant2=res.getString("particpant2");
//           		 if(particpant2==null)
// 		    	     {particpant2="null";}
//       		      else particpant2=particpant2;
           		String roleName1=res.getString("roleName1");
           		  if(roleName1==null)
        			  {roleName1="null";}
        		       else roleName1=roleName1;
           		String roleName2=res.getString("roleName2");
           		 if(roleName2==null)
           			{roleName2="null";}
           		  else roleName2=roleName2;
           		String lower1=res.getString("lower1");
           		  if(lower1==null)
        			   {lower1="null";}
        		       else lower1=lower1;
             		String lower2=res.getString("lower2");
               	  if(lower2==null)
   			      {lower2="null";}
   		           else lower2=lower2;
             		String upper1=res.getString("upper1");
                	 if(upper1==null)
			          {upper1="null";}
		               else upper1=upper1;
             		String upper2=res.getString("upper2");
                	 if(upper2==null)
		               {upper2="null";}
	                    else upper2=upper2;
           		String navigalbeTo1=res.getString("navigalbeTo1");
           	    	if(navigalbeTo1==null)
		                 {navigalbeTo1="null";}
	                      else navigalbeTo1=navigalbeTo1;
             		String navigableTo2=res.getString("navigableTo2");
             		   if(navigableTo2==null)
	                    {navigableTo2="null";}
                       else navigableTo2=navigableTo2; 
       		 
             	
             	
             		  org.melanee.core.models.plm.PLM.Connection connectionx=connectionfind(FQN);
               if(connectionx==null)
               {
            	   Level level=levelfind(container);
            		MelaneeRelationshipset r1= new MelaneeRelationshipset();
      	            r1.binaryconncetionsseting( entityfind(particpant1),  entityfind(particpant2), level,name,FQN);   
               }
               else
               {
            	 
            	   readbinatrribute("attribute",connectionx,FQN);
          		   readbinmethod("method", connectionx, FQN)  ;
          		  connectionx.setName(name);
               }
       	    	
    }  
           
           
 
           while (res.next())   
           {   
     
           }   
       }  
     
       catch(Exception ex)  
       {  
           ex.printStackTrace();  
       }    	
   }  
//  binaryconnection  end
// Inheritance 
	
	public static void readinr()  
    {  
   	try  
       {  
           Statement stmt=null;  
           ResultSet res=null;  
           Class.forName("com.mysql.jdbc.Driver");  
           Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
           stmt = (Statement)conn.createStatement();  
           res= stmt.executeQuery("SELECT * from inheritancerelationship  ");   
           while (res.next()){
        
        	   String id=res.getString("id");
     		   if(id==null)
                    {id="null";}
                   else id=id;
     		String name=res.getString("name");
    	    	if(name==null)
                  {name="null";}
                    else name=name;
     		String FQN=res.getString("FQN");
     		   if(FQN==null)
                  {FQN="null";}
                   else FQN=FQN;           
    		String container=res.getString("container");          
    		   if(container==null)
                 {container="null";}
                  else container=container;
    			String disjoint=res.getString("disjoint");          
     		
     		  String complete=res.getString("complete");          
    	          		 
//    		  readinp( "inheritanceparticipation",FQN);
//    		  levelfind( container) ;

       			 
 	  	  Inheritance inh1 = inheritancefind(FQN);
    		 if(inh1==null)	
    		 {
    	
    			 MelaneeRelationshipset r1= new MelaneeRelationshipset();
    	          r1.inheritanceseting(entityfind(entityfqnsub1), entityfind(entityfqnsub2),entityfind(entityfqnsub3), entityfind(entityfqnsup), levelfind( container),name,complete,disjoint );   
    	      
    		 }
    		 else 
    		 {
    			 inh1.setName(name);
    		
    		 }
    		 
    		 
           
           }  
         while (res.next())   
           {   
             
           }   
       }  
     
       catch(Exception ex)  
       {  
           ex.printStackTrace();  
       }    	
   }  
	
	public static void readinp(String table,String IFQN)  
    {  
   	try  
       {  
           Statement stmt=null;  
           ResultSet res=null;  
           Class.forName("com.mysql.jdbc.Driver");  
           Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
           stmt = (Statement)conn.createStatement();  
           res= stmt.executeQuery("SELECT * from   "+table+" where container ="+"'"+IFQN+"'");   
           while (res.next()){
        
        	   String id=res.getString("id");
  		String name=res.getString("name");
   	    	if(name==null)
                 {name="null";}
                   else name=name;
    		String FQN=res.getString("FQN");
    		   if(FQN==null)
                 {FQN="null";}
                  else FQN=FQN;           
   		String container=res.getString("container");          
   		   if(container==null)
                {container="null";}
                 else container=container;
   			String participant=res.getString("participant");          
    		   if(participant==null)
                 {participant="null";}
                  else participant=participant;
    		  int inheritanceRelationship_id=res.getInt("inheritanceRelationship_id");  
//    		String  inherstring=String.valueOf(inheritanceRelationship_id);
    		

    			  
    		  if(inheritanceRelationship_id==1)
              {entityfqnsup=participant;  }
    		  else if (inheritanceRelationship_id==2)
              {entityfqnsub1=participant;}
    		  else if (inheritanceRelationship_id==3)
              {entityfqnsub2=participant;}
    		  else if (inheritanceRelationship_id==4)
              {entityfqnsub3=participant;}
    		  
    		  

    		  
     
    }  
           
           
 
           while (res.next())   
           {   
             
           }   
       }  
     
       catch(Exception ex)  
       {  
           ex.printStackTrace();  
       }    	
   }  
	
	
// Inheritance end 
	//..............................................................................................read classification
	public static void readclassification()  
    {  
   	try  
       {  
           Statement stmt=null;  
           ResultSet res=null;  
           Class.forName("com.mysql.jdbc.Driver");  
           Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
           stmt = (Statement)conn.createStatement();  
           res= stmt.executeQuery("SELECT * from entity  ");   
           while (res.next()){
        
        	   int id=res.getInt("id");  
      		    String FQN=res.getString("FQN");

       		String name=res.getString("name");         		        	
       		String container=res.getString("container");
//       		int potincy=res.getInt("potincy");
//       		 if(potincy==null)
//		    	     {potincy=null;}
//   		      else potincy=potincy;

       		String directtype=res.getString("direct_type");
       		 if(directtype==null)
		    	     {}
   		      else if(directtype.equals("null")) 
   		         {
   		    	  
   		            }
   		      else 
   		         {
   		    	 
   		    	  Entity entityfather=entityfind(FQN);
   		  
   		          Entity entitychild=entityfind(directtype);
   		          Level levelx=(Level)entitychild.eContainer();
   	
   		    	  MelaneeRelationshipset r1= new MelaneeRelationshipset();
   		    	  r1.classificationset(entityfather, entitychild, levelx);
   		    	 
   		            }
       		
 		  
    		  
 	       //   r1.inheritanceseting(entityfind(entityfqnsub1), entityfind(entityfqnsub2), entityfind(entityfqnsup), levelfind( container),name,complete,disjoint );   
    }  
         while (res.next())   
           {   
             
           }   
       }  
     
       catch(Exception ex)  
       {  
           ex.printStackTrace();  
       }    	
   }  
	
	public static void readconclassification()  
    {  
   	try  
       {  
           Statement stmt=null;  
           ResultSet res=null;  
           Class.forName("com.mysql.jdbc.Driver");  
           Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
           stmt = (Statement)conn.createStatement();  
           res= stmt.executeQuery("SELECT * from binaryconnection");   
           while (res.next()){
        
        	   int id=res.getInt("id");  
      		    String FQN=res.getString("FQN");
//       		System.out.println(FQN);
       		String name=res.getString("name");         		        	
       		String container=res.getString("container");
//       		int potincy=res.getInt("potincy");
//       		 if(potincy==null)
//		    	     {potincy=null;}
//   		      else potincy=potincy;

       		String directtype=res.getString("direct_type");
       		 if(directtype==null)
		    	     {}
   		      else if(directtype.equals("null")) 
   		         {
   		    	  
   		            }
   		      else 
   		         {
   		    	 
   		    	  org.melanee.core.models.plm.PLM.Connection confather=connectionfind(FQN);
   		   
   		    	  org.melanee.core.models.plm.PLM.Connection conchild=connectionfind(directtype);
   		          Level levelx=(Level)conchild.eContainer();  		      
   		    	  MelaneeRelationshipset r1= new MelaneeRelationshipset();
   		    	  r1.conclassificationset(confather, conchild, levelx);
   		    	 
   		            }
       		
 		  
    		  
 	       //   r1.inheritanceseting(entityfind(entityfqnsub1), entityfind(entityfqnsub2), entityfind(entityfqnsup), levelfind( container),name,complete,disjoint );   
    }  
         while (res.next())   
           {   
             
           }   
       }  
     
       catch(Exception ex)  
       {  
           ex.printStackTrace();  
       }    	
   }  
// .................................................................................read classification..........

	// readattribute
	public static void readattribute(String table,Entity entity,String EFQN)  
    {  
     
    	try  
        {  
            Statement stmt=null;  
            ResultSet res=null;  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);    
            stmt = (Statement)conn.createStatement();  
            res= stmt.executeQuery("SELECT * from   "+table+" where container='"+EFQN+"'");   
            while (res.next()){
            	

        		String id=res.getString("id");
       		   if(id==null)
                      {id="null";}
                     else id=id;
       		String name=res.getString("name");
      	    	if(name==null)
                    {name="null";}
                      else name=name;
       		String FQN=res.getString("FQN");
       		   if(FQN==null)
                    {FQN="null";}
                     else FQN=FQN;           
      		String container=res.getString("container");          

      			String duribility=res.getString("duribility");          
       		   if(duribility==null)
                    {duribility="null";}
                     else duribility=duribility;
       		   
       		String type=res.getString("type");          
    		   if(type==null)
                 {type="null";}
                  else type=type;
    		   String value=res.getString("value");          
       		   if(value==null)
                    {value="null";}
                     else value=value;
       		String mutability=res.getString("mutability");          
    		   if(mutability==null)
                 {mutability="null";}
                  else mutability=mutability;
    		   Attribute attributex = attributefind(FQN); 
      		 if(attributex==null)
      		 {
      			  Attribute attribute1 = PLMFactory.eINSTANCE.createAttributeWithLMLVisualizer();
        		  attribute1.setName(name);		        		 
        		  entity.getFeature().add(attribute1);	
      		 }
      		 else
      		 {
//      			attributex.setDatatype(type);
      			attributex.setName(name);
      			attributex.setValue(value);
      			 
      		 }
    		

     }  
            
            
  
            while (res.next())   
            {   
               
            }   
        }  
      
        catch(Exception ex)  
        {  
            ex.printStackTrace();  
        }    	
    }  
	
	
	public static void readbinatrribute(String table,org.melanee.core.models.plm.PLM.Connection bincx,String EFQN)  
    {  
     
    	try  
        {  
            Statement stmt=null;  
            ResultSet res=null;  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);    
            stmt = (Statement)conn.createStatement();  
            res= stmt.executeQuery("SELECT * from   "+table+" where container='"+EFQN+"'");   
            while (res.next()){
            	

        		String id=res.getString("id");
       		   if(id==null)
                      {id="null";}
                     else id=id;
       		String name=res.getString("name");
      	    	if(name==null)
                    {name="null";}
                      else name=name;
       		String FQN=res.getString("FQN");
       		   if(FQN==null)
                    {FQN="null";}
                     else FQN=FQN;           
      		String container=res.getString("container");          

      			String duribility=res.getString("duribility");          
       		   if(duribility==null)
                    {duribility="null";}
                     else duribility=duribility;
       		   
       		String type=res.getString("type");          
    		   if(type==null)
                 {type="null";}
                  else type=type;
    		   String value=res.getString("value");          
       		   if(value==null)
                    {value="null";}
                     else value=value;
       		String mutability=res.getString("mutability");          
    		   if(mutability==null)
                 {mutability="null";}
                  else mutability=mutability;
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
            
            
  
            while (res.next())   
            {   
               
            }   
        }  
      
        catch(Exception ex)  
        {  
            ex.printStackTrace();  
        }    	
    }  
// readattribute
	public static void readmethod(String table,Entity entity,String EFQN)  
    {  
     
    	try  
        {  
            Statement stmt=null;  
            ResultSet res=null;  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);    
            stmt = (Statement)conn.createStatement();  
            res= stmt.executeQuery("SELECT * from   "+table+" where container='"+EFQN+"'");   
            while (res.next()){
            	

        		String id=res.getString("id");
       		   if(id==null)
                      {id="null";}
                     else id=id;
       		String name=res.getString("name");
      	    	if(name==null)
                    {name="null";}
                      else name=name;
       		String FQN=res.getString("FQN");
       		   if(FQN==null)
                    {FQN="null";}
                     else FQN=FQN;           
      		String container=res.getString("container");          
      		   if(container==null)
                   {container="null";}
                    else container=container;
      			String duribility=res.getString("duribility");          
       		   if(duribility==null)
                    {duribility="null";}
                     else duribility=duribility;
       		   
       		String signature=res.getString("signature");          
    		   if(signature==null)
                 {signature="null";}
                  else signature=signature;
    		   String body=res.getString("body");          
       		   if(body==null)
                    {body="null";}
                     else body=body;
       		    		  
    		 
    		  
    		   Method methodx = methodfind(FQN); 
        		 if(methodx==null)
        		 {
        			 Method method = PLMFactory.eINSTANCE.createMethodWithLMLVisualizer();
           		  method.setName(name);      
           		  entity.getFeature().add(method);	
        		 }
        		 else
        		 {
        			 methodx.setBody(body);
        			 methodx.setName(name);
        			 
        		 }

     }  
            

            while (res.next())   
            {   
               
            }   
        }  
      
        catch(Exception ex)  
        {  
            ex.printStackTrace();  
        }    	
    }  
	
	public static void readbinmethod(String table,org.melanee.core.models.plm.PLM.Connection bincx,String EFQN)  
    {  
     
    	try  
        {  
            Statement stmt=null;  
            ResultSet res=null;  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);    
            stmt = (Statement)conn.createStatement();  
            res= stmt.executeQuery("SELECT * from   "+table+" where container='"+EFQN+"'");   
            while (res.next()){
            	

        		String id=res.getString("id");
       		   if(id==null)
                      {id="null";}
                     else id=id;
       		String name=res.getString("name");
      	    	if(name==null)
                    {name="null";}
                      else name=name;
       		String FQN=res.getString("FQN");
       		   if(FQN==null)
                    {FQN="null";}
                     else FQN=FQN;           
      		String container=res.getString("container");          
      		   if(container==null)
                   {container="null";}
                    else container=container;
      			String duribility=res.getString("duribility");          
       		   if(duribility==null)
                    {duribility="null";}
                     else duribility=duribility;
       		   
       		String signature=res.getString("signature");          
    		   if(signature==null)
                 {signature="null";}
                  else signature=signature;
    		   String body=res.getString("body");          
       		   if(body==null)
                    {body="null";}
                     else body=body;
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
            
            
  
            while (res.next())   
            {   
               
            }   
        }  
      
        catch(Exception ex)  
        {  
            ex.printStackTrace();  
        }    	
    }  
	
// Method read end
	
	 public static void levelcreate (String name, int i,DeepModel deepmodelp,String LFQN) {
		
	  
	    Level leveOx = PLMFactory.eINSTANCE.createLevelWithLMLVisualizer();
		leveOx.setName(name);	
       	deepmodelp.getContent().add(leveOx);
       	readentity("entity",LFQN,leveOx);

  
		    }
	 public static void entitycreate (String name,Level level,String EFQN)
	  {
		Entity entityx=PLMFactory.eINSTANCE.createEntityWithLMLVisualizer(); 
		entityx.setName(name);
		 readattribute("attribute",entityx,EFQN);
		 readmethod("method", entityx, EFQN)  ;
		 level.getContent().add(entityx);		
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
	 
	 public static void  save( ) {
		 try {
			 resource1.save(null);
		
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	 
	 
}


