package index;

import static spark.Spark.*;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import Channel.Channel;
import Channel.ChannelService;
import member.Member;
import member.MemberService;
import user.*;
import util.EnableCORS;

public class MainPoint {

	private static final int HTTP_BAD_REQUEST = 400;
	
	public static void main(String[] args) {
		EnableCORS.enableCORS("http://localhost:3000", "DELETE,POST,OPTIONS,GET,PUT", "accept,content-type,X-userid");
		
		
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
				String resultString = ""; 
				res.status(200);
				res.header("Access-Control-Expose-Headers", "X-Authorization,Set-Cookie");
				res.header("X-Authorization", "user");
				res.type("application/json");
				User user = userService.getOne(userinfo.getId());
				user.setPassword(null);
				resultString = dataToJson(user);
				System.out.println("resultString\n"+resultString);
				return resultString;
			} catch (JsonParseException jpe) {
				res.status(HTTP_BAD_REQUEST);
				return "";
			}
		});
		
		get("/user/:id", (req, res) -> {
			System.out.println("start get /user/:id");
			UserService userService = new UserService();
			String resultString = ""; 
			res.status(200);
			res.type("application/json");
			String id = req.params("id");
			User user = null;
			if(userService.getOne(id)==null){
				user = new User();
				user.setId("0");
			}else{
				user = userService.getOne(id);
			}
			resultString = dataToJson(user);
			System.out.println("resultString\n"+resultString);
			return resultString;
		});
		
//=============================			User End			=========================================

		

//=============================			Member Start			=========================================			
		get("/member", (req, res) -> {
			System.out.println("start get /member");
			MemberService memberService = new MemberService();
			String resultString = ""; 
			res.status(200);
			res.type("application/json");
			if(memberService.getAll().size()==0){
				resultString = "0";
			}else{
				resultString = dataToJson(memberService.getAll());
			}
			System.out.println("resultString\n"+resultString);
			return resultString;
		});
		
		get("/member/:id", (req, res) -> {
			System.out.println("start get /member/:id");
			MemberService memberService = new MemberService();
			String resultString = ""; 
			res.status(200);
			res.type("application/json");
			String id = req.params("id");
			Member member = null;
			if(memberService.getOne(id)==null){
				member = new Member();
				member.setId("0");
			}else{
				member = memberService.getOne(id);
			}
			resultString = dataToJson(member);
			System.out.println("resultString\n"+resultString);
			return resultString;
		});
		
		put("/member/:id", (req, res) -> {
			System.out.println("start "+req.requestMethod()+"/member/:id");
			try {
			ObjectMapper mapper = new ObjectMapper();
			Member member = mapper.readValue(req.body(),Member.class);
						
			if (!member.isValid()) {
				res.status(HTTP_BAD_REQUEST);
				return "0";
			}
			member.setLastUpdate(new Timestamp(System.currentTimeMillis()));
			MemberService memberService = new MemberService();
			String resultString = ""; 
			res.status(200);
			res.type("application/json");
			String id = req.params("id");
			if(memberService.getOne(id)==null){
				resultString = dataToJson("0");
			}else{
				member = memberService.update(member);
				resultString = dataToJson("1");
			}
			System.out.println("resultString\n"+resultString);
			return resultString;
			} catch (JsonParseException jpe) {
				res.status(HTTP_BAD_REQUEST);
				return "0";
			}
		});
		
		post("/member", (req, res) -> {
			System.out.println("start "+req.requestMethod()+"/member");
			try {
			ObjectMapper mapper = new ObjectMapper();
			Member member = mapper.readValue(req.body(),Member.class);
						
			if (!member.isValid()) {
				res.status(HTTP_BAD_REQUEST);
				return "0";
			}
			
			MemberService memberService = new MemberService();
			String resultString = ""; 
			res.status(200);
			res.type("application/json");
			memberService.insert(member);
			
			resultString = dataToJson("1");
			System.out.println("=== memberService.getAll() "+dataToJson(memberService.getAll()));
			System.out.println("resultString\n"+resultString);
			return resultString;
			} catch (JsonParseException jpe) {
				res.status(HTTP_BAD_REQUEST);
				return "0";
			} catch (Exception e) {
				res.status(HTTP_BAD_REQUEST);
				return "0";
			}
		});
//=============================			Member End			=========================================



//=============================			Channel Start		=========================================
		get("/channel", (req, res) -> {
			System.out.println("start get /channel");
			ChannelService channelService = new ChannelService();
			String resultString = ""; 
			res.status(200);
			res.type("application/json");
			if(channelService.getAll().size()==0){
				resultString = "0";
			}else{
				resultString = dataToJson(channelService.getAll());
			}
			System.out.println("resultString\n"+resultString);
			return resultString;
		});
		
		get("/channel/:id", (req, res) -> {
			System.out.println("start get /channel/:id");
			ChannelService channelService = new ChannelService();
			String resultString = ""; 
			res.status(200);
			res.type("application/json");
			String id = req.params("id");
			Channel channel = null;
			if(channelService.getOne(id)==null){
				channel = new Channel();
				channel.setId("0");
			}else{
				channel = channelService.getOne(id);
			}
			resultString = dataToJson(channel);
			System.out.println("resultString\n"+resultString);
			return resultString;
		});
		
		//=============================			channel End			=========================================		
		
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
