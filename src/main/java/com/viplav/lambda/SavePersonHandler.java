package com.viplav.lambda;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.viplav.beans.PersonRequest;
import com.viplav.beans.PersonResponse;

public class SavePersonHandler implements RequestHandler<PersonRequest, PersonResponse>{
    private DynamoDB dynamoDb;
	private String DYNAMODB_TABLE_NAME = "Person";
	private Regions REGION = Regions.US_WEST_2;
	@Override
	public PersonResponse handleRequest(PersonRequest input, Context context) {
		this.initDynamoDbClient();
		persistData(input);
		PersonResponse response = new PersonResponse();
		response.setMessage("Saved successfully!!!!!!");
		return response;
	}
	
    private PutItemOutcome persistData(PersonRequest personRequest) 
     	throws ConditionalCheckFailedException
    {
    	Map<String, String> itemMap = new HashMap<String, String>();
    	itemMap.put("firstName", personRequest.getFirstName());
    	itemMap.put("lastName", personRequest.getLastName());
    	Item item = new Item();
    	item.asMap().put("firstName", personRequest.getFirstName());
    	item.asMap().put("lastName", personRequest.getLastName());
    	 return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME).putItem(item);
    }
	
	@SuppressWarnings("deprecation")
	private void initDynamoDbClient() {
		AmazonDynamoDBClient client =  (AmazonDynamoDBClient) AmazonDynamoDBClientBuilder.defaultClient();
		
//        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);

		
	}

}
