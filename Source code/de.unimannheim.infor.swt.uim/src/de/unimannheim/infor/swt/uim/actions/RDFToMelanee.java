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
import java.io.InputStream;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.eclipse.emf.common.util.URI;
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



public class RDFToMelanee {
  
//	public static String inputFileName = "C:/Users/Dongze/Documents/UIMrdf1.owl";  
	public static String inputFileName = Login.frtextrdffinputx();  
	static String melaneefile=Login.fmtextmelaneex();	
	
	static org.apache.jena.rdf.model.Model modeli = ModelFactory.createDefaultModel();     
	static String  outputpath="Documents/UIMrdf1.owl";
	static String  nsproperties = "http://uim/properties/";
	static String   nrelationship= "http://uim/relationship/";

	static  Model model2 = ModelFactory.createDefaultModel();
	static Property pid=model2.createProperty(nsproperties, "id");
	static Property pname =model2.createProperty(nsproperties, "name");
	static  Property pFQN=model2.createProperty(nsproperties, "FQN");
	static  Property pcontainer=model2.createProperty(nsproperties, "container");
	static Property pp_id=model2.createProperty(nsproperties, "p_id");
	static  Property pnumber=model2.createProperty(nsproperties, "number");
	static  Property ppotincy=model2.createProperty(nsproperties, "potincy");
	static  Property pdirecttype=model2.createProperty(nsproperties, "direct_type");
    static Property plabel=model2.createProperty(nsproperties, "label");
    static   Property pkind=model2.createProperty(nsproperties, "kind");
    static   Property pparticipant_id=model2.createProperty(nsproperties, "participant_id");
    static   Property plower=model2.createProperty(nsproperties, "lower");
    static   Property pupper=model2.createProperty(nsproperties, "upper");
    static    Property prow_name=model2.createProperty(nsproperties, "row_name");
    static    Property pwhole=model2.createProperty(nsproperties, "whole");
    static   Property pdisjoint=model2.createProperty(nsproperties, "disjoint");
    static   Property pcomplete=model2.createProperty(nsproperties, "complete");
    static   Property pparticipant=model2.createProperty(nsproperties, "participant");
    static   Property pinheritanceRelationship_id=model2.createProperty(nsproperties, "inheritanceRelationship_id");
    static   Property pduribility=model2.createProperty(nsproperties, "duribility");
    static   Property ptype=model2.createProperty(nsproperties, "type");
    static   Property pvalue=model2.createProperty(nsproperties, "value");
    static   Property pmutability=model2.createProperty(nsproperties, "mutability");
    static   Property pmethod=model2.createProperty(nsproperties, "method");
    static   Property psignature=model2.createProperty(nsproperties, "signature");
    static   Property pbody=model2.createProperty(nsproperties, "body");
  //  static   Property pkey=model2.createProperty(nsproperties, "key");
    static   Property pbinarlabel=model2.createProperty(nsproperties, "pbinarlabel");
    static   Property pparticipant1=model2.createProperty(nsproperties, "participant2");
    static   Property pparticipant2=model2.createProperty(nsproperties, "articipant2");   
    static   Property proleName1=model2.createProperty(nsproperties, "roleName1");
    static   Property proleName2=model2.createProperty(nsproperties, "roleName2");
    static   Property plower1=model2.createProperty(nsproperties, "lower1");
    static   Property plower2=model2.createProperty(nsproperties, "lower2");
    static   Property pupper1=model2.createProperty(nsproperties, "upper1");
    static   Property pupper2=model2.createProperty(nsproperties, "upper2");    
    static   Property pnavigalbeTo1=model2.createProperty(nsproperties, "navigalbeTo1");
    static   Property pnavigableTo2=model2.createProperty(nsproperties, "navigableTo2");
 
    static   Property rparticipant1=model2.createProperty(nrelationship, "participant1");    
    static   Property rparticpant2=model2.createProperty(nrelationship, "participant2");
    static   Property rparent=model2.createProperty(nrelationship, "parent");
    static   Property inheritance=model2.createProperty(nrelationship, "inheritance");    
    static   Property rparticipant=model2.createProperty(nrelationship, "participant");
    static   Property directtype=model2.createProperty(nrelationship, "direct_type");

    static DeepModel deepmodel=null;
	static org.eclipse.emf.ecore.resource.ResourceSet resourceSetofmelanee = new ResourceSetImpl();
	static  org.eclipse.emf.ecore.resource.Resource melaneeresource =null;
	static Domain domain=null;
	static Diagram diagram=null;
	static String entityfqnsub1=null;
	static String entityfqnsub2=null;
	static String entityfqnsub3=null;
	static String entityfqnsup=null;
	
	
	   public static void main(String[] args){  
	//	   readdeepmodel();
	   }
	//   @Test
	   public static  void readdeepmodel()
	   { 
		   
			  inputFileName = Login.frtextrdffinputx();  
			 melaneefile=Login.fmtextmelaneex();	
		   org.apache.jena.rdf.model.Model modelrdf = ModelFactory.createDefaultModel();  
		 
		    
       InputStream in = FileManager.get().open( inputFileName );  
       if (in == null) {  
           throw new IllegalArgumentException(  
                                    "File: " + inputFileName + " not found");  
       }  
 
       modelrdf.read(in, null);  
       //  StmtIterator iter1 = modelr.listStatements(new SimpleSelector(null, pFQN, (RDFNode)null));  
      ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "deepmodel");
   
      while(iterx.hasNext()){  

    	        org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();
        	    String dname=   rdfresource.getProperty(pname).getObject().toString();
        	    String dFQN=   rdfresource.getProperty(pFQN).getObject().toString();
        	 //   String entityname3=   rdfresource.getProperty(pcontainer).getObject().toString();
          	//	melaneeresource = resourceSetofmelanee.createResource(URI.createFileURI("D:/eclipselunaNew/workspace/org.uim.models/lml/"+dname+"rdfre.lml"));
        	    melaneeresource = resourceSetofmelanee.createResource(URI.createFileURI(melaneefile+"/"+dname+"rdf.lml"));
        	    domain = PLMFactory.eINSTANCE.createDomain();
         		deepmodel = PLMFactory.eINSTANCE.createDeepModelWithLMLVisualizer();
         		deepmodel.setName(dname);
        		domain.getDeepModel().add(deepmodel);
        		readlevel(deepmodel,dFQN,modelrdf);	
        		readbinaryconnection(modelrdf);	
        		readinr(modelrdf);  
        		readclassification(modelrdf);  
        		readconclassification(modelrdf); 
        		melaneeresource.getContents().add(domain);      	
             	diagramcreate(dname);
       
               
  //       }  

       }  
  
		   
	   }
	   
	   public static void readlevel(DeepModel deepmodelp,String DFQN,org.apache.jena.rdf.model.Model modelrdf)
	   {  
		   ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "level");
		   while(iterx.hasNext())
		   {  
			   org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();
			   String lcontainer=   rdfresource.getProperty(pcontainer).getObject().toString();
			   if (lcontainer.equals(DFQN))
			   {
			   String lname=   rdfresource.getProperty(pname).getObject().toString();
       	       String lFQN=   rdfresource.getProperty(pFQN).getObject().toString();
       	    
       	       Level leveOx = PLMFactory.eINSTANCE.createLevelWithLMLVisualizer();
    		   leveOx.setName(lname);	
               deepmodelp.getContent().add(leveOx);
               readentity(lFQN,leveOx,modelrdf);
			   }
		   }
	   }
	   
	   public static void readentity(String lFQN,Level level,org.apache.jena.rdf.model.Model modelrdf)
	   {
		   ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "entity");
		   while(iterx.hasNext())
		   {  
			   org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();
			   String econtainer=   rdfresource.getProperty(pcontainer).getObject().toString(); 
			  
			   if (econtainer.equals(lFQN))
			   {
			     String ename=   rdfresource.getProperty(pname).getObject().toString();
       	         String eFQN=   rdfresource.getProperty(pFQN).getObject().toString();
       	         Entity entityx=PLMFactory.eINSTANCE.createEntityWithLMLVisualizer(); 
       	        
    		     entityx.setName(ename);
    		     readattribute(eFQN,entityx,modelrdf);
    		     readmethod(eFQN,entityx,modelrdf);
    		     level.getContent().add(entityx);	
			   }
       	 
		   }
	   }
	   
		public static void readattribute(String eFQN,Entity entity,org.apache.jena.rdf.model.Model modelrdf)  
		{
			 ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "attribute");
			 while(iterx.hasNext())
			   {  
				   
				   org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();
   			       String acontainer=   rdfresource.getProperty(pcontainer).getObject().toString();   
				   if (acontainer.equals(eFQN))
				   {    
					   String aname=   rdfresource.getProperty(pname).getObject().toString();
		       	       String aFQN=   rdfresource.getProperty(pFQN).getObject().toString();
					   Attribute attribute1 = PLMFactory.eINSTANCE.createAttributeWithLMLVisualizer();
				       attribute1.setName(aname);		 
				      
				       entity.getFeature().add(attribute1);	
				    //   entity.getAllAttributes().add(attribute1)	;
				   }
					   					  				  
		   }
		 }
		public static void readbinattribute(String eFQN,org.melanee.core.models.plm.PLM.Connection bincx,org.apache.jena.rdf.model.Model modelrdf)  
		{
			 ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "attribute");
			 while(iterx.hasNext())
			   {  
				   
				   org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();
   			       String acontainer=   rdfresource.getProperty(pcontainer).getObject().toString();   
				   if (acontainer.equals(eFQN))
				   {    
					   String aname=   rdfresource.getProperty(pname).getObject().toString();
		       	       String aFQN=   rdfresource.getProperty(pFQN).getObject().toString();
					   Attribute attribute1 = PLMFactory.eINSTANCE.createAttributeWithLMLVisualizer();
				       attribute1.setName(aname);		 
				
				       bincx.getFeature().add(attribute1);	
				    //   entity.getAllAttributes().add(attribute1)	;
				   }
					   					  				  
		   }
		 }
		  public static void readmethod(String eFQN,Entity entity,org.apache.jena.rdf.model.Model modelrdf)  
				
		  {
			  ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "method");
			  
			  while(iterx.hasNext())
					 
			  { 
				  org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();						
				  String mcontainer=   rdfresource.getProperty(pcontainer).getObject().toString();   
			
				
				  if (mcontainer.equals(eFQN))
						 
				  {
					
				     String mname=   rdfresource.getProperty(pname).getObject().toString();				     					
					 String mFQN=   rdfresource.getProperty(pFQN).getObject().toString();					
					 Method method = PLMFactory.eINSTANCE.createMethodWithLMLVisualizer();
					 
		    		 method.setName(mname);      
		    		 entity.getFeature().add(method);	
						   }
							   					  				  
					   }				
		        }
		  
		  
		  public static void readbinmethod(String eFQN,org.melanee.core.models.plm.PLM.Connection bincx,org.apache.jena.rdf.model.Model modelrdf)  
			
		  {
			  ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "method");
			  
			  while(iterx.hasNext())
					 
			  { 
				  org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();						
				  String mcontainer=   rdfresource.getProperty(pcontainer).getObject().toString();   
			
				
				  if (mcontainer.equals(eFQN))
						 
				  {
					
				     String mname=   rdfresource.getProperty(pname).getObject().toString();				     					
					 String mFQN=   rdfresource.getProperty(pFQN).getObject().toString();					
					 Method method = PLMFactory.eINSTANCE.createMethodWithLMLVisualizer();					 
		    		 method.setName(mname);      
		    		 bincx.getFeature().add(method);	
						   }
							   					  				  
					   }				
		        }
		  public static void readbinaryconnection(org.apache.jena.rdf.model.Model modelrdf)  
		    {
			  ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "binaryconnection");
				 
			  while(iterx.hasNext())
					 
			  {  
				  org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();					
				  String bname=   rdfresource.getProperty(pname).getObject().toString();
				  String bFQN=   rdfresource.getProperty(pFQN).getObject().toString();
				  String bcontainer=   rdfresource.getProperty(pcontainer).getObject().toString();  
				  String bparticipant1=   rdfresource.getProperty(pparticipant1).getObject().toString();  
				  String bparticipant2=   rdfresource.getProperty(pparticipant2).getObject().toString(); 
				  Level level=levelfind(bcontainer);
				  MelaneeRelationshipset r1= new MelaneeRelationshipset();
				  r1.rdfbinaryconncetionsseting( entityfind(bparticipant1),  entityfind(bparticipant2), level,bname,bFQN, modelrdf); 
			  }
		    }
		  
//.............................................................................readclassification........
		  public static void readclassification(org.apache.jena.rdf.model.Model modelrdf)  
		    {
			  ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "entity");
				 
			  while(iterx.hasNext())
					 
			  {  
				  org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();					
				  String bname=   rdfresource.getProperty(pname).getObject().toString();
				  String bcontainer=   rdfresource.getProperty(pcontainer).getObject().toString();  
//				  String bparticipant1=   rdfresource.getProperty(pparticipant1).getObject().toString();  
//				  String bparticipant2=   rdfresource.getProperty(pparticipant2).getObject().toString(); 
				  String entityfatherFQN=   rdfresource.getProperty(pFQN).getObject().toString();
				  String  entitychildFQN=null;
				  try{
					  
					   entitychildFQN=   rdfresource.getProperty(pdirecttype).getObject().toString();
				  }
				  catch(Exception e)
				  {
					  
				  }
				
			
				  if(entitychildFQN==null)
		    	     {}
				  else if(entitychildFQN.equals("null")) 
	   		         {
	   		    	  
	   		            }
	   		      else 
	   		         {
	   		    	 
	   		    	  Entity entityfather=entityfind(entityfatherFQN);   		    	
	   		          Entity entitychild=entityfind(entitychildFQN);
	   		          Level levelx=(Level)entitychild.eContainer();	   	
	   		    	  MelaneeRelationshipset r1= new MelaneeRelationshipset();
	   		    	  r1.classificationset(entityfather, entitychild, levelx);

	   		            }
//				  Level level=levelfind(bcontainer);
//				  MelaneeRelationshipset r1= new MelaneeRelationshipset();
//				  r1.binaryconncetionsseting( entityfind(entityfatherFQN),  entityfind(entitychildFQN), level,bname); 
			  }
		    }
		  
		  public static void readconclassification(org.apache.jena.rdf.model.Model modelrdf)  
		    {
			  ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "binaryconnection");
				 
			  while(iterx.hasNext())
					 
			  {  
				  org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();					
				  String bname=   rdfresource.getProperty(pname).getObject().toString();
				  String bcontainer=   rdfresource.getProperty(pcontainer).getObject().toString();  
//				  String bparticipant1=   rdfresource.getProperty(pparticipant1).getObject().toString();  
//				  String bparticipant2=   rdfresource.getProperty(pparticipant2).getObject().toString(); 
				  String confatherfatherFQN=   rdfresource.getProperty(pFQN).getObject().toString();
				  String conchildFQN=null;
				  try{
					  
					
					    conchildFQN=   rdfresource.getProperty(pdirecttype).getObject().toString();
				  }
				  catch(Exception e)
				  {
					  
				  }
				  
				 
				  if(conchildFQN==null)
		    	     {}
				  else if(conchildFQN.equals("null")) 
	   		         {
	   		    	  
	   		            }
	   		      else 
	   		         {
	   		    	 
	   		    	  org.melanee.core.models.plm.PLM.Connection confather=connectionfind(confatherfatherFQN);	   		    	
	   		    	  org.melanee.core.models.plm.PLM.Connection conchild=connectionfind(conchildFQN);
	   		          Level levelx=(Level)conchild.eContainer();  		      
	   		    	  MelaneeRelationshipset r1= new MelaneeRelationshipset();
	   		    	  r1.conclassificationset(confather, conchild, levelx);
	   		    	 
	   		            }
//				  Level level=levelfind(bcontainer);
//				  MelaneeRelationshipset r1= new MelaneeRelationshipset();
//				  r1.binaryconncetionsseting( entityfind(entityfatherFQN),  entityfind(entitychildFQN), level,bname); 
			  }
		    }
 //...............................................................................end............
		  public static void readinr(org.apache.jena.rdf.model.Model modelrdf)  
		    {  
			  
			  ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "inheritancerelationship");
				 
			  while(iterx.hasNext())
					 
			  {  
				  
				  org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();
				  String inhrname=   rdfresource.getProperty(pname).getObject().toString(); 
				  String inhrcontainer=   rdfresource.getProperty(pcontainer).getObject().toString(); 
				  String inhrFQN=   rdfresource.getProperty(pFQN).getObject().toString();	
				  String complete=   rdfresource.getProperty(pcomplete).getObject().toString();
				  String disjoint=   rdfresource.getProperty(pdisjoint).getObject().toString();
				  readinp( inhrFQN,modelrdf);
	    		  //levelfind( inhrcontainer) ;
				  
	       		  MelaneeRelationshipset r1= new MelaneeRelationshipset();
	 	          r1.inheritanceseting(entityfind(entityfqnsub1), entityfind(entityfqnsub2),entityfind(entityfqnsub3), entityfind(entityfqnsup), levelfind( inhrcontainer),inhrname,complete,disjoint );
	 	          
			  }
			
		    }
		  
		  public static void readinp(String inhrFQN,org.apache.jena.rdf.model.Model modelrdf)  
		    {  
			  ResIterator iterx =    modelrdf.listSubjectsWithProperty(plabel , "inheritanceparticipation");
				 
			  while(iterx.hasNext())
					 
			  {  
				  org.apache.jena.rdf.model.Resource  rdfresource=iterx.nextResource();					
				  String inhpcontainer=   rdfresource.getProperty(pcontainer).getObject().toString();   		
				  if (inhpcontainer.equals(inhrFQN))
						 
				  {
					  String inheritanceRelationship_id=   rdfresource.getProperty(pinheritanceRelationship_id).getObject().toString();				     					
					  
					  String participant=   rdfresource.getProperty(pparticipant).getObject().toString();	
					  if(inheritanceRelationship_id.equals("1"))
		              {entityfqnsup=participant;  }
		    		  else if (inheritanceRelationship_id.equals("2"))
		              {entityfqnsub1=participant;}
		    		  else if (inheritanceRelationship_id.equals("3"))
		              {entityfqnsub2=participant;}
		    		  else if (inheritanceRelationship_id.equals("4"))
		              {entityfqnsub3=participant;}
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
	 	 melaneeresource.getContents().add(diagram);
	 	  try {
	 		 melaneeresource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				e.printStackTrace();
			}
	 
//		System.out.println("Melanee model create sucessfully");
		 
		 }	
	   
	   
}
