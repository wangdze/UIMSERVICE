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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.jena.atlas.logging.LogCtl;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmf.runtime.notation.NotationPackage;
//import org.apache.commons.lang.StringUtils;
import org.melanee.core.models.plm.PLM.*;

import com.mysql.jdbc.Statement;

import org.slf4j.*;

public class MelaneeRead {
	
	

	static int iddeepmodel;
	String name;
	String FQN;
	static ResultSet res;
	String entityname;
	String message;
	String content;
	String particpant1;
	String particpant2;
	String databasetype;
	static int instanceid;
	static Entity e1Entity = null;
	static Entity e2Entity = null;
	static Level level1=null;
	static String sentity=null;
	static Inheritance inh1 = null;
	static Subtype subtype1 = null;
	static org.melanee.core.models.plm.PLM.Connection binc1 = null;
	static int packageid=0;
	static int deepmodelid=0;
	static int levelid=0;
	static int entityid=0;
	static int binrid=0;
	static int inhrid=0;
	static int inhpid=0;
	static int genrlid=0;
	static int genrlpatid=0;
	static int attributeentityid=0;
	static int methodentityid=0;
	static int inhers=0;
	static int methodid=0;

	static String melaneefile=Login.j1textmelanee();
	static String mysqlhost=Login.textmysqlhostx();
	static String mysqluser=Login.textuserx();
	static String mysqlpassword=Login.textpasswordx();
	static String sqldatabase="jdbc:mysql://"+mysqlhost;
	
	public static void main(String[] args) {
	
		//readdeepmodel();
		}

// outside of main method
 	// read Deepmodel and level
	public static void readdeepmodel(String dbtype)
{
   // 	LogCtl.setCmdLogging();

    	org.apache.jena.rdf.model.Model model1 = ModelFactory.createDefaultModel();
    
    	// jena model create end
     Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("lml", new XMIResourceFactoryImpl());
	 Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new EcoreResourceFactoryImpl());
	 ResourceSetImpl resourceSet = new ResourceSetImpl();
	 resourceSet.getPackageRegistry().put(PLMPackage.eNS_URI, PLMPackage.eINSTANCE);
	 resourceSet.getPackageRegistry().put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);	
     Resource lmlResource = resourceSet.getResource(URI.createFileURI(melaneefile),true);
	 EList<EObject> lmlResourcContents = lmlResource.getContents();
	{	
		if (lmlResourcContents.get(0) != null && lmlResourcContents.get(0) instanceof Domain ) 
{		   	  
    DeepModel deepmodel1= null;
   
    
     deepmodel1=(DeepModel)lmlResourcContents.get(0).eContents().get(0);	
      String dname=deepmodel1.getName();
      String DFQN=deepmodel1.getName();
 	  iddeepmodel=maxid("deepmodel")+1;
 	  MySQLWrite writemysqldm=new MySQLWrite();
 	  Neo4jNodesCreate neo=new Neo4jNodesCreate();
 	  RDFWrite rdfwrite=new RDFWrite(); 
 	  MogoDBCreate mgo=new MogoDBCreate(); 
         if(dbtype.equals("mysql"))
        {
        	 MySQLWrite write =new MySQLWrite();
        	  write.delatetable(); 
            writemysqldm.Deepmodelinsert(iddeepmodel,1,dname,DFQN, "","Deepmodel");	
         }
       
         else if(dbtype.equals("neo4j") ){
        	 
             neo.DeepmodelNodecreate(1,1,dname,DFQN, "","Deepmodel");       	 
         }
         
         else if(dbtype.equals("rdf") ){
        	
             rdfwrite.Deepmodelresourcecreate(1,1,dname,DFQN, "", model1);
         }
         else if(dbtype.equals("mongodb") ){
        	
             mgo.Deepmodeldocumentinsert(1, 1, DFQN, dname, "");
         }
      

       
    
    
//Get level 
   int levelseize=lmlResourcContents.get(0).eAllContents().next().eContents().size();
      for(int i = 0 ;i < levelseize; i++)  
     {  
      String leveltype=lmlResourcContents.get(0).eAllContents().next().eContents().get(i).eClass().getName();
      if(leveltype=="Level")
  {
	    level1=(Level)lmlResourcContents.get(0).eAllContents().next().eContents().get(i);
	    String levelname=level1.getName();
	    String levelFQN=dname+"."+level1.getName();  
	    levelid=levelid+1;
	    String container=dname;
	    int idlevel=maxid("level")+1;
	    if(dbtype.equals("mysql"))
        {   
	    	writemysqldm.Levelinsert(idlevel,levelFQN,levelname, container,0,"level");  	  
        }
	    else if(dbtype.equals("neo4j")) {
	    	neo.LevelNodecreate(levelid,levelFQN,levelname, container,0,"level");
		    Neo4jRelationshipSet nrp= new Neo4jRelationshipSet();
		    nrp.parentsetting(levelFQN, dname);
	    }
	    else if(dbtype.equals("rdf")) {
	    	 rdfwrite.Levelresourcecreate(levelid, levelFQN, levelname, container, "", model1);
	 	    rdfwrite.Parentsetting(levelFQN, DFQN, "level",model1);
	    }
	    else if( dbtype.equals("mongodb")) {
	    	mgo.Leveldocumentinsert(levelid, levelFQN, levelname, container, 0);
	    }
    
	    int childsize=lmlResourcContents.get(0).eAllContents().next().eContents().get(i).eContents().size();
	    readlevelchild ( childsize,i,lmlResourcContents,dname,levelname, levelFQN,writemysqldm,neo,rdfwrite,mgo,model1,dbtype);

	  }   	  
  }
  
      //  start to realtionship 
      for(int i = 0 ;i < levelseize; i++)  
      {  
       String leveltype=lmlResourcContents.get(0).eAllContents().next().eContents().get(i).eClass().getName();
       if(leveltype=="Level")
   {
 	  level1=(Level)lmlResourcContents.get(0).eAllContents().next().eContents().get(i);
 	  String levelname=level1.getName();
 	  String levelFQN=dname+"."+level1.getName();    
 	  String container=dname;

	  int childsize=lmlResourcContents.get(0).eAllContents().next().eContents().get(i).eContents().size();
	  readrealtionship ( childsize,i,lmlResourcContents,dname,levelname, levelFQN,writemysqldm,neo,rdfwrite,mgo,model1,dbtype);

 	  }   	 
       
   }
 	  if(dbtype.equals("rdf"))
	 { 
 		  rdfwrite.writefile(model1);
 	
	 }
      
    
      
      //strat to realtionhsip end
 
}  

} 

	
} 	
//read Deepmodel and level
	
	public static void readlevelchild (int childsize,int i, EList<EObject> lmlResourcContents,String dname,String levelname,String levelFQN,MySQLWrite writemysqldm ,Neo4jNodesCreate neo,RDFWrite rdfwrite,MogoDBCreate mgo, Model model1,String dbtype)

	  {
	//	System.out.println(e1Entity=(Entity)lmlResourcContents.get(0).eAllContents().next().eContents().get(2).eContents().get(1));
		 for(int j = 0 ;j < childsize; j++) 
   	      {  String childtype= lmlResourcContents.get(0).eAllContents().next().eContents().get(i).eContents().get(j).eClass().getName();

   	      if (childtype=="Entity")
   	     { 
   		 e1Entity=(Entity)lmlResourcContents.get(0).eAllContents().next().eContents().get(i).eContents().get(j);
   		 String  entityname=e1Entity.getName();
   		 int identity= maxid("entity")+1;
   		 entityid=entityid+1;
   		 String EntityFQN=dname+"."+levelname+"."+entityname;
//   		 System.out.println("dddd"+EntityFQN) ;
   		 String entitycontainer=levelFQN;
   		 int entitypotincy=e1Entity.getPotency();


   		 if(dbtype.equals("mysql"))
         {
   			 writemysqldm.Enitityinsert (identity,entityname,EntityFQN, entitycontainer,entitypotincy,"null","entity" );
         }
   		 else if(dbtype.equals("neo4j"))
   		 {
   			 neo.Nodeentitycreate (j,entityname,EntityFQN, entitycontainer,entitypotincy,"null","entity" );
   	   		 Neo4jRelationshipSet nrp= new Neo4jRelationshipSet();
   	 		 nrp.parentsetting(EntityFQN, levelFQN);
   		 }
   		else if(dbtype.equals("rdf"))
  		 {
   		
 		 rdfwrite.Entityresourcecreate(j, entityname, EntityFQN, entitycontainer, entitypotincy, "null", model1);
 		 rdfwrite.Parentsetting(EntityFQN,levelFQN ,"entity", model1);
   			
  		 }
 	
   		else if (dbtype.equals("mongodb"))
   		{
   		
 		 mgo.Entitydocumentinsert(j, EntityFQN, entityname, entitycontainer, entitypotincy, "null");
   		}
 		
   		 int entitychildsize= e1Entity.eContents().size();   
   	     readentitychild(lmlResourcContents,writemysqldm,EntityFQN,entitychildsize,e1Entity,neo,rdfwrite,mgo,model1, dbtype);
   	          }
 
   	 }
   	
	  
	  }
// get relationship
	
	public static void readrealtionship (int childsize,int i, EList<EObject> lmlResourcContents,String dname,String levelname,String levelFQN,MySQLWrite writemysqldm ,Neo4jNodesCreate neo,RDFWrite rdfwrite,MogoDBCreate mgo,Model model1,String dbtype)
	  
	  {
	//	System.out.println(e1Entity=(Entity)lmlResourcContents.get(0).eAllContents().next().eContents().get(2).eContents().get(1));
		 for(int j = 0 ;j < childsize; j++) 
 	      {  String childtype= lmlResourcContents.get(0).eAllContents().next().eContents().get(i).eContents().get(j).eClass().getName();

 	      
 	   if (childtype=="Inheritance") 
 	    {
 		Inheritance inh1=(Inheritance)lmlResourcContents.get(0).eAllContents().next().eContents().get(i).eContents().get(j);
 		Entity supentity0=(Entity)inh1.getSupertype().get(0).getSupertype(); 
 		Entity subentity1=(Entity)inh1.getSubtype().get(0).getSubtype();
 		Entity subentity2=(Entity)inh1.getSubtype().get(1).getSubtype();	
// 		if
// 		Entity subentity3=(Entity)inh1.getSubtype().get(2).getSubtype();
 	
 	    int idinhr= maxid("inheritancerelationship")+1;
 	    inhrid=inhrid+1;
 	    String inhrname=inh1.getName();
 	    String container=levelFQN;
 	    String inhrFQN=levelFQN+"."+inhrname;
 	    Boolean disjoint1=inh1.getDisjoint();
 	    String disjoint=String.valueOf(disjoint1);
 	    Boolean complete1=inh1.getComplete();	
 	    String complete=String.valueOf(complete1);
 	   if(dbtype.equals("mysql"))
       {
 		  writemysqldm.Inrinsert( idinhr, inhrFQN, inhrname,  container, disjoint, complete, "inheritancerelationship"); 
       }
 	  else if(dbtype.equals("neo4j"))
		 {
 		  neo.InrNodecreate(inhrid, inhrFQN, inhrname,  container, disjoint, complete, "inheritancerelationship");
 	      Neo4jRelationshipSet nrp= new Neo4jRelationshipSet();
 		  nrp.parentsetting(inhrFQN,levelFQN );	
		 }
 	 else if(dbtype.equals("rdf"))
	 {
 		 rdfwrite.inhrresourcecreate(inhrid, inhrname, inhrFQN, container, disjoint, complete, model1);
 	    rdfwrite.Parentsetting(inhrFQN, levelFQN, "inheritancerelationship", model1);
	 }
 	 else if(dbtype.equals("mongodb"))
	 {
 		  mgo.Inrdocumentinsert(inhrid, inhrFQN, inhrname, container, disjoint, complete);
	 }
  
 	// 0304 2015 update   
 	  String ip1name="IPsup";
	    int ip1id=maxid("inheritanceparticipation")+1;
	    inhpid=inhpid+1;
	    String inhpcontainer=inhrFQN;
	    String inhpip1FQN=inhrFQN+"."+"IPsup";
 	    String participantIP1=levelFQN+"."+supentity0.getName();
    	int inheritanceRelationship_idip1=1;
    	 Neo4jRelationshipSet nrp= new Neo4jRelationshipSet();   	
    	
    	 if(dbtype.equals("mysql"))
       {
    	 writemysqldm.Inpinsert(ip1id,inhpip1FQN,ip1name, inhpcontainer,participantIP1,inheritanceRelationship_idip1,"inheritanceparticipation");
       }
    	 else if(dbtype.equals("neo4j"))
		 {
    	  neo.InpNodecreate(inhpid,inhpip1FQN,ip1name, inhpcontainer,participantIP1,inheritanceRelationship_idip1,"inheritanceparticipation");
    	  nrp.inhrelationshipset(inhrFQN,inhpip1FQN);
    	  nrp.inhrpset(inhpip1FQN, participantIP1);
		 }
    	else if(dbtype.equals("rdf"))
 	     {
    	  rdfwrite.inhpresourcecreate(inhpid, ip1name, inhpip1FQN, inhpcontainer, participantIP1, inheritanceRelationship_idip1, model1);
    	  rdfwrite.inhrrelationshipsetting(inhrFQN, inhpip1FQN,"IP1", model1);	    
 	      rdfwrite.inhprelationshipsetting(inhpip1FQN, participantIP1, model1);

    	  
    	  
 	      }
    	 else if(dbtype.equals("mongodb"))
  	 {
    	  mgo.Inpdocumentinsert(inhpid, inhpip1FQN, ip1name, inhpcontainer, participantIP1,1); 
  	 }
 	   
 	   
 	   int subentitysize=inh1.getSubtype().size();
 	  for(int k = 0 ;k < subentitysize; k++) 
 	 {
 		 Entity subentity=(Entity)inh1.getSubtype().get(k).getSubtype();
 	
 		String subipname="IPsub";
  	    int subipid=maxid("inheritanceparticipation")+1;
  	    inhpid=inhpid+1;
  	    String subipcontainer=inhrFQN;
  	    int l=k+1;
  	    String subipFQN=inhrFQN+"."+"IPsub"+l;
    	String subipparticipant=levelFQN+"."+subentity.getName();
       	int subipinheritanceRelationship_id=k+2;
        if(dbtype.equals("mysql"))
        {
        writemysqldm.Inpinsert(subipid,subipFQN,subipname, subipcontainer,subipparticipant,subipinheritanceRelationship_id,"inheritanceparticipation");
       
        }
    	 else if(dbtype.equals("neo4j"))
   		 {
         	 neo.InpNodecreate(subipid,subipFQN,subipname, subipcontainer,subipparticipant,subipinheritanceRelationship_id,"inheritanceparticipation");     
     	     nrp.inhrelationshipset(subipFQN, inhrFQN);     	     
     	     nrp.inhrpset(subipFQN, subipparticipant);
      	    
   		 }
         	else if(dbtype.equals("rdf"))
      	     {
         	  rdfwrite.inhpresourcecreate(subipid, subipname, subipFQN, subipcontainer, subipparticipant, subipinheritanceRelationship_id, model1);
       
         	  rdfwrite.inhrrelationshipsetting(subipFQN, inhrFQN, "IP23",model1);
         	  rdfwrite.inhprelationshipsetting(subipFQN, subipparticipant, model1);
      	     }
         	 else if(dbtype.equals("mongodb"))
       	 {
         	  mgo.Inpdocumentinsert(subipid, subipFQN, subipname, subipcontainer, subipparticipant,subipinheritanceRelationship_id); 
       	 }
 	 }
 	   
	
 	   } 
  // the following codes is used to set realaitonship  

	     
	    
	  
 	 
 	 else if (childtype=="Connection") 
	    { 
 		org.melanee.core.models.plm.PLM.Connection binc1=(org.melanee.core.models.plm.PLM.Connection)lmlResourcContents.get(0).eAllContents().next().eContents().get(i).eContents().get(j);  
 		
// 		Entity bentity1=(Entity)binc1.getParticipation().get(0).getDestination();
// 		Entity bentity2=(Entity)binc1.getParticipation().get(1).getDestination();
 		Entity bentity1=(Entity)binc1.getConnectionEnd().get(0).getDestination();
 		Entity bentity2=(Entity)binc1.getConnectionEnd().get(1).getDestination();
 		binrid=binrid+1;
 		int binid=maxid("binaryconnection")+1;
 		String biname=binc1.getName();
 		String binFQN=levelFQN+"."+binc1.getName();
 		String bincontainer=levelFQN;
 		int potency=binc1.getPotency();
 	
 		//Entity edirecttype=(Entity)binc1.getDirectTypes().get(0);
 		String directtype=null;
 		if(directtype==null)
	     {directtype="null";}
	      else directtype=directtype;
 		String label=null;
 	    if(label==null)
	        {label="null";}
	       else label=label;
 		String particpant1=levelFQN+"."+bentity1.getName();
 		String particpant2=levelFQN+"."+bentity2.getName();
 	    String roleName1=null;
   	 if(roleName1==null)
        {roleName1="null";}
          else roleName1=roleName1;
 	    String roleName2=null;
  	 if(roleName2==null)
       {roleName2="null";}
         else roleName2=roleName2;
   	String lower1=null;
  	 if(lower1==null)
       {lower1="null";}
         else lower1=lower1;
 	    String lower2=null;
  	 if(lower2==null)
       {lower2="null";}
         else lower2=label;
 	    String upper1=null;
  	 if(upper1==null)
       {upper1="null";}
         else upper1=label;
 	    String upper2=null;
  	 if(upper2==null)
       {upper2="null";}
         else upper2=label;
 	    String navigalbeTo1=null;
  	 if(navigalbeTo1==null)
       {navigalbeTo1="null";}
         else navigalbeTo1=label;
 	    String navigableTo2=null;
  	 if(navigableTo2==null)
       {navigableTo2="null";}
         else navigableTo2=label;
  	if(dbtype.equals("mysql"))
    {
  		writemysqldm.Binaryconnectioninsert(binid, binFQN, biname,bincontainer, potency,directtype,label ,particpant1,
	    		 particpant2,roleName1,roleName2,lower1,lower2,upper1,upper2 , navigalbeTo1, navigableTo2,"binaryconnection" );
    }
  	 else if(dbtype.equals("neo4j"))
	 {
  		 neo.Nodebinarycreate(binrid, binFQN, biname,bincontainer, potency,directtype,label ,particpant1,
  		  		 particpant2,roleName1,roleName2,lower1,lower2,upper1,upper2 , navigalbeTo1, navigableTo2,"binaryconnection" );
  		 	     Neo4jRelationshipSet nrp= new Neo4jRelationshipSet();
  		 	     nrp.parentsetting(binFQN, levelFQN);
  		 	     nrp.relationshipset1(binFQN, particpant1);
  		 	     nrp.relationshipset2(binFQN, particpant2);
	 }
  	 else if(dbtype.equals("rdf"))
     {
  		  
  	     rdfwrite.Binaryresourcecreate(binid, binFQN, biname, bincontainer, potency, directtype, label, particpant1, particpant2, roleName1, roleName2, lower1, lower2, upper1, upper2, navigalbeTo1, navigableTo2, model1);
  	     rdfwrite.Binaryrelationshipsetting(binFQN, particpant1, particpant2, model1);
  	     rdfwrite.Parentsetting(binFQN,levelFQN ,"binaryconnection", model1);
     }
 	   
  	 else if(dbtype.equals("mongodb"))
	   {
  	
	     mgo.Binarydocumentinsert(binrid, binFQN, biname, bincontainer, potency, directtype, label, particpant1, particpant2, roleName1, roleName2, lower1, lower2, upper1, upper2, navigalbeTo1, navigableTo2);
	   }
 	     
     	 int binchildsize= binc1.eContents().size();   
	     readbinaryconnectionchild(lmlResourcContents,writemysqldm,binFQN,binchildsize,binc1,neo,rdfwrite,mgo,model1, dbtype);    
 	    
	    }
 	
 	 else if (childtype=="Classification") 
	 {
	//	System.out.println(childtype);
		Classification classficationx=(Classification)lmlResourcContents.get(0).eAllContents().next().eContents().get(i).eContents().get(j);
	if(classficationx.eCrossReferences().get(0).eClass().getName().equals("Connection")) 
	{
		org.melanee.core.models.plm.PLM.Connection conchild= (org.melanee.core.models.plm.PLM.Connection)classficationx.eCrossReferences().get(0);	
		org.melanee.core.models.plm.PLM.Connection confather= (org.melanee.core.models.plm.PLM.Connection)classficationx.eCrossReferences().get(1);		
		Level  levelechild=(Level)conchild.eContainer();
		Level  levelefather=(Level)confather.eContainer();
		String confatherFQN=dname+"."+levelefather.getName()+"."+confather.getName();
		String conchildFQN=dname+"."+levelechild.getName()+"."+conchild.getName();	
		
		
		if(dbtype.equals("mysql"))
	    {
			writemysqldm.Binaryconnectionupdate ( conchildFQN,  confatherFQN );
	    }
		else if(dbtype.equals("neo4j"))
		  {
		      Neo4jRelationshipSet nrp= new Neo4jRelationshipSet();
	 		  nrp.classificationset(conchildFQN,confatherFQN);
		  }
		else if(dbtype.equals("mongodb"))
		  {
			mgo.Binaryconnectionupdate(conchildFQN, confatherFQN);
		  }
		else if(dbtype.equals("rdf"))
		  {
			RDFWrite rdfx= new RDFWrite();
			rdfx.conclassificationset(conchildFQN, confatherFQN, model1);
		  }
		
	}
	else if (classficationx.eCrossReferences().get(0).eClass().getName().equals("Entity"))
	{
		Entity entitychilid= (Entity)classficationx.eCrossReferences().get(0);		
		Entity entityfather= (Entity)classficationx.eCrossReferences().get(1);
		Level  levelefather=(Level)entityfather.eContainer();
	//	DeepModel dpee0=(DeepModel)levele0.eContainer();
		Level  levelechild=(Level)entitychilid.eContainer();
	//	DeepModel dpee1=(DeepModel)levele1.eContainer();
				
		String entityfatherFQN=dname+"."+levelefather.getName()+"."+entityfather.getName();
		String entitychildFQN=dname+"."+levelechild.getName()+"."+entitychilid.getName();		
		
		
		if(dbtype.equals("mysql"))
	    {
			writemysqldm.Enitityupdate ( entitychildFQN,  entityfatherFQN );
	    }
		else if(dbtype.equals("neo4j"))
		  {
		      Neo4jRelationshipSet nrp= new Neo4jRelationshipSet();
	 		  nrp.classificationset(entityfatherFQN,entitychildFQN);
		  }
		else if(dbtype.equals("mongodb"))
		{
			mgo.entityupdate(entityfatherFQN, entitychildFQN);
		
			
		}
		else if(dbtype.equals("rdf"))
		{
			RDFWrite rdfx= new RDFWrite();			
			rdfx.classificationset(entitychildFQN, entityfatherFQN, model1);
		}
	}
	 }
 	   
 	 }
		 
	  }
// get relationship end
	public static void readentitychild (EList<EObject> lmlResourcContents,MySQLWrite writemysqldm,String EntityFQN, int entitychildsize,Entity e1Entity,Neo4jNodesCreate neo,  RDFWrite rdfwrite,MogoDBCreate mgo,Model model1,String dbtype  )
	  
	  {
		 for(int k = 0 ;k < entitychildsize; k++)  
		 {
			 e1Entity.eContents().get(k);
			 String entitychildtype= e1Entity.eContents().get(k).eClass().getName();
	    if(entitychildtype.equals("Attribute")){
                 Attribute attribute1=(Attribute)e1Entity.eContents().get(k);
				
				String attributename=attribute1.getName();
				int id = maxid ( "attribute")+1;
				String attributeFQN=EntityFQN+"."+attributename;
				int duribilityint=attribute1.getDurability();
				attributeentityid=attributeentityid+1;
				String duribility=String.valueOf(duribilityint);
				String value=attribute1.getValue();
				int Mutabilityt=attribute1.getMutability();
				String mutability=String.valueOf(Mutabilityt);
				String type=attribute1.getDatatype();	
				 if(dbtype.equals("mysql"))
			        {   
					 writemysqldm.Attributeinsert (id,attributeFQN,attributename, EntityFQN, duribility, type, value, mutability, "attribute" );
			        }
				
				 else if(dbtype.equals("neo4j"))
				 { 
					 neo.AttributeNodecreate (attributeentityid,attributeFQN,attributename, EntityFQN, duribility, type, value, mutability, "attribute" );
					 Neo4jRelationshipSet nrp= new Neo4jRelationshipSet();
					 nrp.parentsetting(attributeFQN, EntityFQN);
				 }
				 else if(dbtype.equals("rdf"))
				 { 
					 rdfwrite.attributeresourcecreate(attributeentityid, attributename, attributeFQN, EntityFQN, duribility, type, value, mutability, model1);
					 rdfwrite.Parentsetting(attributeFQN, EntityFQN, "attribute", model1);
				 }
			    
				 else if(dbtype.equals("mongodb"))
				 { 
				
					 mgo.Attributedocumentinsert(attributeentityid, attributeFQN, attributename, EntityFQN, duribility, type, value, mutability);
				 }
				
			}
	 else if(entitychildtype.equals("Method")){
				  Method method1=(Method)e1Entity.eContents().get(k);
				  int id = maxid ( "method")+1;
				  methodid=methodid+1;
				  String name=method1.getName();
				  String methodFQN=EntityFQN+"."+name;
				  String container=EntityFQN;
				  int duribility1=method1.getDurability();
				  String duribility=String.valueOf(duribility1);
				  String signature="";
				  String body=method1.getBody();
				  if(dbtype.equals("mysql"))
			        {  
					  writemysqldm.Methodinsert( id, methodFQN, name, container, duribility, signature, body,"method" );
			        }
				  else if(dbtype.equals("neo4j"))
					 { 
					  neo.MethodNodecreate( methodid, methodFQN, name, container, duribility, signature, body,"method" );
					  Neo4jRelationshipSet nrp= new Neo4jRelationshipSet();
					  nrp.parentsetting(methodFQN, EntityFQN);
					 }
				  else if(dbtype.equals("rdf"))
				    { 
					  rdfwrite.methodresourcecreate(methodid, name, methodFQN, container, duribility, signature, body, model1);
					  rdfwrite.Parentsetting(methodFQN, EntityFQN, "method", model1);
					  
				    }
				  else if(dbtype.equals("mongodb"))
				    { 
					  mgo.Methoddocumentinsert(methodid, methodFQN, name, container, duribility, signature, body);
				    }			
				 
		          }
			}		
			 
		 }
	//	System.out.println(e1Entity=(Entity)lmlResourcContents.get(0).eAllContents().next().eContents().get(2).eContents().get(1));
	//。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。binaryconnection child..
	public static void readbinaryconnectionchild (EList<EObject> lmlResourcContents,MySQLWrite writemysqldm,String binFQN, int entitychildsize,org.melanee.core.models.plm.PLM.Connection bincx,Neo4jNodesCreate neo,  RDFWrite rdfwrite,MogoDBCreate mgo,Model model1,String dbtype  )
	  
	  {
		 for(int k = 0 ;k < entitychildsize; k++)  
		 {
			 bincx.eContents().get(k);
			 String entitychildtype= bincx.eContents().get(k).eClass().getName();
	    if(entitychildtype.equals("Attribute")){
               Attribute attribute1=(Attribute)bincx.eContents().get(k);
				
				String attributename=attribute1.getName();
				int id = maxid ( "attribute")+1;
				String attributeFQN=binFQN+"."+attributename;
				int duribilityint=attribute1.getDurability();
				attributeentityid=attributeentityid+1;
				String duribility=String.valueOf(duribilityint);
				String value=attribute1.getValue();
				int Mutabilityt=attribute1.getMutability();
				String mutability=String.valueOf(Mutabilityt);
				String type=attribute1.getDatatype();	
				 if(dbtype.equals("mysql"))
			        {   
					 writemysqldm.Attributeinsert (id,attributeFQN,attributename, binFQN, duribility, type, value, mutability, "attribute" );
			        }
				
				 else if(dbtype.equals("neo4j"))
				 { 
					 neo.AttributeNodecreate (attributeentityid,attributeFQN,attributename, binFQN, duribility, type, value, mutability, "attribute" );
					 Neo4jRelationshipSet nrp= new Neo4jRelationshipSet();
					 nrp.parentsetting(attributeFQN, binFQN);
				 }
				 else if(dbtype.equals("rdf"))
				 { 
					 rdfwrite.attributeresourcecreate(attributeentityid, attributename, attributeFQN, binFQN, duribility, type, value, mutability, model1);
					 rdfwrite.Parentsetting(attributeFQN, binFQN, "binattribute", model1);
				 }
			    
				 else if(dbtype.equals("mongodb"))
				 { 
				
					 mgo.Attributedocumentinsert(attributeentityid, attributeFQN, attributename, binFQN, duribility, type, value, mutability);
				 }
				
			}
	 else if(entitychildtype.equals("Method")){
				  Method method1=(Method)bincx.eContents().get(k);
				  int id = maxid ( "method")+1;
				  methodid=methodid+1;
				  String name=method1.getName();
				  String methodFQN=binFQN+"."+name;
				  String container=binFQN;
				  int duribility1=method1.getDurability();
				  String duribility=String.valueOf(duribility1);
				  String signature="";
				  String body=method1.getBody();
				  if(dbtype.equals("mysql"))
			        {  
					  writemysqldm.Methodinsert( id, methodFQN, name, container, duribility, signature, body,"method" );
			        }
				  else if(dbtype.equals("neo4j"))
					 { 
					  neo.MethodNodecreate( methodid, methodFQN, name, container, duribility, signature, body,"method" );
					  Neo4jRelationshipSet nrp= new Neo4jRelationshipSet();
					  nrp.parentsetting(methodFQN, binFQN);
					 }
				  else if(dbtype.equals("rdf"))
				    { 
					  rdfwrite.methodresourcecreate(methodid, name, methodFQN, container, duribility, signature, body, model1);
					  rdfwrite.Parentsetting(methodFQN, binFQN, "binmethod", model1);
					  
				    }
				  else if(dbtype.equals("mongodb"))
				    { 
					  mgo.Methoddocumentinsert(methodid, methodFQN, name, container, duribility, signature, body);
				    }			
				 
		          }
			}		
			 
		 }
	//..............................................................................binaryconnection end
	// get maxid
	public static int  maxid ( String table)
		    {  
		     
		    	try  
		        {  
		            Statement stmt=null;  
		            ResultSet res=null;  
		            Class.forName("com.mysql.jdbc.Driver");  
		            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword); 
		            stmt = (Statement)conn.createStatement();  
		            res= stmt.executeQuery("select max(id) from  "+table);  
		            res.next();
		            instanceid=res.getInt(1);
                
		    }  
		  
		    catch(Exception ex)  
		    {  
		        ex.printStackTrace();  
		    }  

		    	 return instanceid; 
		} 
	// get maxid
}