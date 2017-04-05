package index;

import static spark.Spark.*;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import Channel.ChannelService;
import member.MemberService;
import user.*;
import util.EnableCORS;

public class MainPoint {

	private static final int HTTP_BAD_REQUEST = 400;
	
	public static void main(String[] args) {
		EnableCORS.enableCORS("http://localhost:3000", "DELETE,POST,OPTIONS,GET,PUT", "accept,content-type,ss,Authorization");
		
		
//=============================			User Start			=========================================		
		
		post("/user/login", (req, res) -> {
			System.out.println("start post /user/login");
			try {
				ObjectMapper mapper = new ObjectMapper();
				User userinfo = mapper.readValue(req.body(),User.class);
							
				if (!userinfo.isValid()) {
					res.status(HTTP_BAD_REQUEST);
					return "";
				}
				
				UserService userService = new UserService();
				
				if(!userService.loginCheck(userinfo)){
					res.status(200);
					res.header("Access-Control-Expose-Headers", "X-Authorization,Set-Cookie");
					res.header("X-Authorization", "not_allowed");
					return "";
				}
				
				res.status(200);
				res.header("Access-Control-Expose-Headers", "X-Authorization,Set-Cookie");
				res.header("X-Authorization", "user");
				res.type("application/json");
				return dataToJson(userService.getOne(userinfo.getId()));
			} catch (JsonParseException jpe) {
				res.status(HTTP_BAD_REQUEST);
				return "";
			}
		});
		
//=============================			User End			=========================================

		

//=============================			Member Start			=========================================			
		get("/member", (req, res) -> {
			System.out.println("start get /member");
			MemberService memberService = new MemberService();
			res.status(200);
			res.type("application/json");
			String resultString = ""; 
			if(memberService.getAll().size()==0){
				resultString = "0";
			}else{
				resultString = dataToJson(memberService.getAll());
			}
			System.out.println(resultString);
			return resultString;
		});
		//¥¼´ú
		get("/member/:id", (req, res) -> {
			System.out.println("start get /member/:id");
			MemberService memberService = new MemberService();
			res.status(200);
			res.type("application/json");
			String id = req.params("id");
			System.out.println(dataToJson(memberService.getOne(id)));
			return dataToJson(memberService.getOne(id));
		});
		
//=============================			Member End			=========================================



//=============================			Channel Start		=========================================
		get("/channel", (req, res) -> {
			System.out.println("start get /channel");
			ChannelService channelService = new ChannelService();
			res.status(200);
			res.type("application/json");
			String resultString = ""; 
			if(channelService.getAll().size()==0){
				resultString = "0";
			}else{
				resultString = dataToJson(channelService.getAll());
			}
			System.out.println(resultString);
			return resultString;
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
