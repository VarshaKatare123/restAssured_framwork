package Test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_method.Common_method_Utilities;
import Common_method.common_method_put;
import Request_repository.put_request_respository;
import io.restassured.path.json.JsonPath;

public class put_tc1 {
	@Test
	public static void orchestrator() throws IOException 
	{ 
	  String responsebody = "";	
	  int responsestatuscode = 0;
	  String baseuri = put_request_respository.baseuri();
	  String resource = put_request_respository.resource();
	  String requestbody = put_request_respository.put_request_tc1();
	  for(int i=0; i<5; i++)
	  {
		  responsestatuscode = common_method_put.responsestatuscodeextractor(baseuri, resource, requestbody);
		  if(responsestatuscode == 200)
		  {
			 responsebody = common_method_put.responsebodyextractor(baseuri, resource, requestbody);
			 responsebodyvalidator(responsebody);	
			 break;
		   }
		  else
		  {
			  System.out.println("Correct status code not found in iteration: " + i);
		  }
	  }
	  Common_method_Utilities.evidence_file_creator("put_tc1", requestbody, responsebody);
	  Assert.assertEquals(responsestatuscode, 200);

    }


	private static void responsebodyvalidator(String responsebody) 
	{
		// create jsonPath object to extract responsebody parameters
				JsonPath jsp = new JsonPath(responsebody);

				// extract responsebody parameters
				String res_name = jsp.getString("name");
				String res_job = jsp.getString("job");
				String res_id = jsp.getString("id");
				String res_createdAt = jsp.getString("createdAt");

//				System.out.println("name : " + res_name + "\njob : " + res_job + "\nid : " + res_id + "\ncreatedAt : " + res_createdAt);

				// validate responsebody parameter
				Assert.assertEquals(res_name,"morpheous");
				Assert.assertEquals(res_job, "leader");
				Assert.assertNotEquals(res_id, "assertion error , id parameter is null");



	}
}
