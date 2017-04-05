package blog;

import static spark.Spark.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import spark.Response;
import user.*;

public class BlogServer {

	private static final int HTTP_BAD_REQUEST = 400;

	
	
	interface Validable {
		boolean isValid();
	}

	static class NewPostPayload {
		private String title;
		private String content;
		private List categories;


		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public List getCategories() {
			return categories;
		}

		public void setCategories(List categories) {
			this.categories = categories;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public boolean isValid() {
			return title != null && !title.isEmpty() && !categories.isEmpty();
		}
	}

	public static class Model {
		private int nextId = 1;
		private Map posts = new HashMap();

		class Post {
			private int id;
			private String title;
			private List categories;
			private String content;

			public Post() {
			}

			public Post(int id, String title, List categories, String content) {
				super();
				this.id = id;
				this.title = title;
				this.categories = categories;
				this.content = content;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public List getCategories() {
				return categories;
			}

			public void setCategories(List categories) {
				this.categories = categories;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}
		}

		public int createPost(String title, String content, List categories) {
			int id = nextId++;
			Post post = new Post();
			post.setId(id);
			post.setTitle(title);
			post.setContent(content);
			post.setCategories(categories);
			posts.put(id, post);
			return id;
		}

		public List getAllPosts() {
			return (List) posts.keySet().stream().sorted().map((id) -> posts.get(id)).collect(Collectors.toList());
		}
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
	public static void main(String[] args) {
		Model model = new Model();
		enableCORS("*", "DELETE,POST,OPTIONS,GET", "accept,content-type,ss,Authorization");
		post("/posts", (req, res) -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				NewPostPayload creation = mapper.readValue(req.body(),NewPostPayload.class);
				if (!creation.isValid()) {
					res.status(HTTP_BAD_REQUEST);
					return "";
				}

				int id = model.createPost(creation.getTitle(), creation.getContent(), creation.getCategories());
				res.status(200);
				res.type("application/json");
				return id;
			} catch (JsonParseException jpe) {
				res.status(HTTP_BAD_REQUEST);
				return "";
			}
		});

		get("/posts", (req, res) -> {
			Set<String> heads = req.headers();
			for(String str:heads){
				System.out.println(str);
			}
			
			res.status(200);
			res.type("application/json");
			String foo = "";
			if(req.queryParamsValues("fname")!=null){
				foo+=req.queryParamsValues("fname")[0]+" ";
			}
			if(req.queryParamsValues("lname")!=null){
				foo+=req.queryParamsValues("lname")[0];
			}
			
			
			return dataToJson(model.getAllPosts()+dataToJson(foo));
		});

		post("/postse", (req, res) -> {
			System.out.println("now is post");
			
			String foo = "";
			Set<String> heads = req.headers();
			for(String str:heads){
				System.out.println(str+" = "+req.headers(str));
			}
			
			System.out.println("body = "+req.body());
			if(req.attribute("fname")!=null){
				foo+="fname = "+req.params("fname");
			}
			if(req.params("lname")!=null){
				foo+="fname = "+req.params("lname");
			}
			if(foo.length()==0){
				return "no input";
			}else{
				return foo;
			}
		});

		get("/postse", (req, res) -> {
			System.out.println("now is get");
			res.status(200);
			res.type("application/json");
			return dataToJson(model.getAllPosts());
		});
		
		get("/postse1", (req, res) -> {
			req.attribute("fff", 000);
			redirect.get("/postse1", "/postse2");
			return "";
		});
		
		get("/postse2", (req, res) -> {
			
			
			return req.attribute("fff");
		});
		
		get("/postse3", (req, res) -> {
			req.session().attribute("qtt", 66666);
			
			return "session attribute set";
		});
		
		get("/postse4", (req, res) -> {
			res.cookie("test", "value");
			res.header("ss", "dd");
			if(req.session().attribute("qtt")!=null){
				return req.session().attribute("qtt");
			}else{
				return "session attribute is no set ";
			}
		});
	
		get("/postse5", (req, res) -> {
			res.cookie("test9", "6666value");
			return "cookie set ";
		});
		
		post("/user/login", (req, res) -> {
			System.out.println("start post");
			try {
				ObjectMapper mapper = new ObjectMapper();
				User userinfo = mapper.readValue(req.body(),User.class);
				if (!userinfo.isValid()) {
					res.status(HTTP_BAD_REQUEST);
					return "";
				}
				
				res.status(200);
				res.header("Access-Control-Allow-Methods", "DELETE,POST,OPTIONS,GET");
//				res.header("Access-Control-Allow-Headers", "ss,content-type,Authorization");
				res.header("Access-Control-Max-Age", "1728000");
				res.header("Access-Control-Expose-Headers", "ss,X-Authorization,Set-Cookie");
				res.header("X-Authorization", "user");
				res.header("ss", "ssss");
				res.cookie("/","sss", "ssssaaaaaaaaa",3600,false,true);
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
	
	private static void enableCORS(final String origin, final String methods, final String headers) {

	    options("/*", (request, response) -> {

	        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
	        if (accessControlRequestHeaders != null) {
	            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
	        }

	        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
	        if (accessControlRequestMethod != null) {
	            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
	        }
	        System.out.println("accessControlRequestHeaders"+ accessControlRequestHeaders);
	        System.out.println("accessControlRequestMethod"+ accessControlRequestMethod);
	        return "OK";
	    });

	    before((request, response) -> {
	        response.header("Access-Control-Allow-Origin", origin);
	        response.header("Access-Control-Request-Method", methods);
	        response.header("Access-Control-Allow-Headers", headers);
	        // Note: this may or may not be necessary in your particular application
	        response.type("application/json");
	    });
	}

}
