package uz.dev.springboot.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.dev.springboot.models.TodoItem;
import uz.dev.springboot.repo.TodoRepo;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/todo")
public class TodoController {



    @Autowired
    private TodoRepo todoRepo;

    @GetMapping
    public List<TodoItem> findAll(){
        return todoRepo.findAll();
    }

    @GetMapping("/{id}")
    public TodoItem findOne(@PathVariable Long id){
        return todoRepo.findById(id).orElse(null);
    }

    @PostMapping
    public TodoItem save(@RequestBody TodoItem newTodoItem){
        return todoRepo.save(newTodoItem);
    }

    @PutMapping("/{id}")
    public TodoItem update(@RequestBody TodoItem updateTodoItem, @PathVariable Long id){
        return todoRepo.findById(id)
                .map(item -> {
                    item.setTitle(updateTodoItem.getTitle());
                    item.setBody(updateTodoItem.getBody());
                    item.setCompleted(updateTodoItem.isCompleted());
                    return todoRepo.save(item);
                })
                .orElseGet(() -> {
                    updateTodoItem.set_id(id);
                    return todoRepo.save(updateTodoItem);
                });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        todoRepo.deleteById(id);
    }

}
