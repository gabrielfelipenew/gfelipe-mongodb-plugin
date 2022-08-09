package com.plugin.mongodb;


//MongoDB libraries
import com.mongodb.client.MongoIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.types.ObjectId;

//Java libraries
import java.util.List;
import java.util.Iterator;
import java.net.Socket;
import java.io.File;
import java.util.Map;
import java.io.*;

import com.dtolabs.rundeck.core.execution.workflow.steps.StepException;
import com.dtolabs.rundeck.core.plugins.Plugin;
import com.dtolabs.rundeck.plugins.descriptions.PluginDescription;
import com.dtolabs.rundeck.plugins.PluginLogger;
import com.dtolabs.rundeck.plugins.step.PluginStepContext;
import com.dtolabs.rundeck.plugins.ServiceNameConstants;
import com.dtolabs.rundeck.plugins.step.StepPlugin;
import com.dtolabs.rundeck.plugins.descriptions.PluginProperty;


//Plugin definition
@Plugin(service=ServiceNameConstants.WorkflowStep,name="mongodb")
@PluginDescription(title="MongoDB", description="My WorkflowStep plugin description")
public class Mongodb implements StepPlugin{

	//properies definition    
	@PluginProperty(name = "key1", title = "Hostname", description = "insert hostname")
                private String mongo_host;
	@PluginProperty(name = "key2", title = "Database", description = "Database name")
        	private String mongo_database;
	@PluginProperty(name = "key3", title = "Collection", description = "Collection name")
                private String mongo_collection;
   

	//Step def.
	public void executeStep(final PluginStepContext context, final Map<String, Object> configuration) {
                    String mongohostname = mongo_host;
	            String mongodatabase = mongo_database;
	            String mongocoll = mongo_collection;
	            String server= String.format("mongodb://%s:27017",mongohostname); 
		    try{
	            	;
			MongoClient mongo = MongoClients.create(server);//establish connecion to database
			MongoDatabase database = mongo.getDatabase(mongodatabase);//get database
	                System.out.println("Displaying the list of all collections in database:"+ mongodatabase+", collection: "+ mongocoll);
                        MongoCollection<Document> collection= database.getCollection(mongocoll);//get collection
			FindIterable<Document> iterDoc = collection.find();
			Iterator it = iterDoc.iterator();
	      		while (it.hasNext()) {
        			System.out.println(it.next());
      			}	 
		  
		
		    }catch(Exception e){System.err.println(e);}
	}                                                                                                                                                                        }
