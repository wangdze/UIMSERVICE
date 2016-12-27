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

import org.melanee.core.models.plm.PLM.Classification;
import org.melanee.core.models.plm.PLM.Connection;
import org.melanee.core.models.plm.PLM.ConnectionEnd;
import org.melanee.core.models.plm.PLM.Entity;
import org.melanee.core.models.plm.PLM.Inheritance;
import org.melanee.core.models.plm.PLM.Level;
import org.melanee.core.models.plm.PLM.PLMFactory;
//import org.melanee.core.models.plm.PLM.Participation;
import org.melanee.core.models.plm.PLM.Subtype;
import org.melanee.core.models.plm.PLM.Supertype;

import com.mongodb.DB;

public class MelaneeRelationshipset {
	
	
	
	
//  创建binaryconncetion
	
public static void binaryconncetionsseting(Entity firstnode,Entity secondnode,Level blevel,String bname,String BFQN)  
{ 
	       Connection binnaryconnection = PLMFactory.eINSTANCE.createConnectionWithLMLVisualizer();
	       ConnectionEnd p1 = PLMFactory.eINSTANCE.createConnectionEnd();
	        binnaryconnection.setName(bname);
         	p1.setConnection(binnaryconnection);
         	p1.setDestination(firstnode);
	
        	ConnectionEnd p2 = PLMFactory.eINSTANCE.createConnectionEnd();
         	p2.setConnection(binnaryconnection);
	       p2.setDestination(secondnode);
	        blevel.getContent().add(binnaryconnection);
	        MySQLToMelanee.readbinatrribute("attribute",binnaryconnection,BFQN);
	        MySQLToMelanee.readbinmethod("method", binnaryconnection, BFQN)  ;
	  
	        
//创建binaryconncetion
	 }  

public static void rdfbinaryconncetionsseting(Entity firstnode,Entity secondnode,Level blevel,String bname,String BFQN,org.apache.jena.rdf.model.Model modelrdf)  
{ 
	       Connection binnaryconnection = PLMFactory.eINSTANCE.createConnectionWithLMLVisualizer();
	       ConnectionEnd p1 = PLMFactory.eINSTANCE.createConnectionEnd();
	        binnaryconnection.setName(bname);
         	p1.setConnection(binnaryconnection);
         	p1.setDestination(firstnode);	
        	ConnectionEnd p2 = PLMFactory.eINSTANCE.createConnectionEnd();
         	p2.setConnection(binnaryconnection);
	       p2.setDestination(secondnode);
	        blevel.getContent().add(binnaryconnection);
	        RDFToMelanee.readbinattribute(BFQN, binnaryconnection, modelrdf);
	        RDFToMelanee.readbinmethod(BFQN, binnaryconnection, modelrdf);
	        
//创建binaryconncetion
	 }  
public static void mongobinaryconncetionsseting(Entity firstnode,Entity secondnode,Level blevel,String bname,String BFQN,DB db)  
{ 
	       Connection binnaryconnection = PLMFactory.eINSTANCE.createConnectionWithLMLVisualizer();
	       ConnectionEnd p1 = PLMFactory.eINSTANCE.createConnectionEnd();
	        binnaryconnection.setName(bname);
         	p1.setConnection(binnaryconnection);
         	p1.setDestination(firstnode);
	
        	ConnectionEnd p2 = PLMFactory.eINSTANCE.createConnectionEnd();
         	p2.setConnection(binnaryconnection);
	       p2.setDestination(secondnode);
	        blevel.getContent().add(binnaryconnection);
	        MongoDBToMelanee.readbinattribute(BFQN, binnaryconnection, db);
	        MongoDBToMelanee.readbinmethod(BFQN, binnaryconnection, db);
	        
//创建binaryconncetion
	 }  
//Create inheritance 
public static void inheritanceseting(Entity subnode1,Entity subnode2,Entity subnode3,Entity supnode,Level ilevel,String name,String complete,String disjoint)  
       { 
	
        Inheritance ihnr = PLMFactory.eINSTANCE.createInheritanceWithLMLVisualizer();
        ihnr.setName(name);
          if(complete==null)
    	      { ihnr.setComplete(null);}
            else if(complete.equals("true"))
    	      {ihnr.setComplete(true);}
              else if(complete.equals("false"))
    	      {ihnr.setComplete(false);}
                if(disjoint==null)
    	          { ihnr.setDisjoint(null);}
                  else if(disjoint.equals("true"))
    	           {ihnr.setDisjoint(true);}
                    else if(disjoint.equals("false"))
     	             {ihnr.setDisjoint(false);}
 
              Subtype subtype = PLMFactory.eINSTANCE.createSubtype();
              subtype.setSubtype(subnode1);
              ihnr.getSubtype().add(subtype);
  
              Subtype subtype1 = PLMFactory.eINSTANCE.createSubtype();
              subtype1.setSubtype(subnode2);
              ihnr.getSubtype().add(subtype1);

              Supertype supertype = PLMFactory.eINSTANCE.createSupertype();
              supertype.setSupertype(supnode);
              ihnr.getSupertype().add(supertype);
              ilevel.getContent().add(ihnr);
          
              
             if( subnode3!=null)
            	
             {
            	 Subtype subtype3 = PLMFactory.eINSTANCE.createSubtype();
              	  subtype3.setSubtype(subnode3);
                    ihnr.getSubtype().add(subtype3);
              }
 
}
//Create inheritance end	


    public static void classificationset(Entity entitychild,Entity entityfather,Level levelx)  
          {
    	   Classification classficationx= PLMFactory.eINSTANCE.createClassificationWithLMLVisualizer();
    	   classficationx.setInstance(entitychild);
    	   classficationx.setType(entityfather);;	
 		   levelx.getContent().add(classficationx);
               }
    public static void conclassificationset( org.melanee.core.models.plm.PLM.Connection  binchild,org.melanee.core.models.plm.PLM.Connection binfather, Level levelx)  
    {
	   Classification classficationx= PLMFactory.eINSTANCE.createClassificationWithLMLVisualizer();
	   classficationx.setInstance(binchild);
	   classficationx.setType(binfather);;	
	   levelx.getContent().add(classficationx);
         }

    }