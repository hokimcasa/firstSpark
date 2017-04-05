package index;

import static spark.Spark.*;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import user.*;
import util.EnableCORS;

public class BlogServer {

	private static final int HTTP_BAD_REQUEST = 400;
	
	public static void main(String[] args) {
		EnableCORS.enableCORS("http://localhost:3000", "DELETE,POST,OPTIONS,GET,PUT", "accept,content-type,ss,Authorization");
		
		
		
		
		post("/user/login", (req, res) -> {
			System.out.println("start post");
			try {
				ObjectMapper mapper = new ObjectMapper();
				User userinfo = mapper.readValue(req.body(),User.class);
				UserData userdata = new UserData();
				if (!userinfo.isValid()) {
					res.status(HTTP_BAD_REQUEST);
					return "";
				}
				System.out.println(userinfo.getName());
				System.out.println(userdata.getUserList().contains(userinfo));
				if(!userdata.getUserList().contains(userinfo)){
					res.status(200);
					res.header("Access-Control-Expose-Headers", "X-Authorization,Set-Cookie");
					res.header("X-Authorization", "not_allowed");
					return "";
				}
				
				res.status(200);
				res.header("Access-Control-Expose-Headers", "X-Authorization,Set-Cookie");
				res.header("X-Authorization", "user");
				res.type("application/json");
				
				System.out.println();
				return dataToJson(userinfo);
			} catch (JsonParseException jpe) {
				res.status(HTTP_BAD_REQUEST);
				return "";
			}
		});
		
		get("/user/login", (req, res) -> {
			res.header("Access-Control-Allow-Methods", "DELETE,POST,OPTIONS,GET");
			res.header("Access-Control-Allow-Headers", "accept, content-type");
			res.header("Access-Control-Max-Age", "1728000");
			res.type("application/json");
			return dataToJson("has sans");
			
		});
	}
	
	public static String dataToJson(Object data) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			StringWriter sw = new StringWriter();
			mapper.writeValue(sw, data);
			return sw.toString();
		} catch (IOException e) {
			throw new RuntimeException("IOException from a StringWiter");
		}
	}

}
