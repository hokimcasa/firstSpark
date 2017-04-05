package blog;

import static spark.Spark.get;
import static spark.Spark.post;
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

public class BlogServer_V1 {

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
			
			return dataToJson(model.getAllPosts());
		});

	}

}
