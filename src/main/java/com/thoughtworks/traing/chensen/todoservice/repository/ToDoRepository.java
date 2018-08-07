package com.thoughtworks.traing.chensen.todoservice.repository;

import com.thoughtworks.traing.chensen.todoservice.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<Todo, Integer> {

    public List<Todo> findTodoInfosByCreateByIs(int creatBy);


//    @Value(value = "classpath:static/todo.json")
//    private Resource data;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    public List<Todo> list() {
//        List<Todo> res = new ArrayList<>();
//        res.add(new Todo(1, "todo1"));
//        res.add(new Todo(2, "todo2"));
//        res.add(new Todo(3, "todo3"));
//
//        return  res;
//    }
//
//    public List<Todo> getListFromFile() throws IOException {
//        List<Todo> res = new ArrayList<>();
//        String jsonStr = "";
//        try {
//            InputStreamReader reader = new InputStreamReader(data.getInputStream());
//            BufferedReader br = new BufferedReader(reader);
//            String line = "";
//            while ((line = br.readLine()) != null) {
//                jsonStr += line;
//            }
//
////            JSONObject jsonObject = new JSONObject(jsonStr);
////            JSONArray todos = jsonObject.getJSONArray("todos");
////            for(int i=0; i<todos.length(); i++) {
////                JSONObject jo = (JSONObject) todos.get(i);
////                int id = jo.getInt("id");
////                String content = jo.getString("content");
////                Todo todo = new Todo(id, content);
////                res.add(todo);
////            }
////            System.out.print("todos" + res);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        List<Todo> list = objectMapper.readValue(jsonStr, new TypeReference<List<Todo>>(){});
//
//        return list;
//    }

}
