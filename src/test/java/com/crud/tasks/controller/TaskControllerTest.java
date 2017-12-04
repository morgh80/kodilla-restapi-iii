package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskController taskController;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchTasks() throws Exception {
        //Given
        List<TaskDto> taskListDto = new ArrayList<>();
        TaskDto taskDto = new TaskDto((long) 1, "Some task", "Some content");
        taskListDto.add(taskDto);

        List<Task> tasksList = new ArrayList<>();
        Task task = new Task((long) 1, "Some task", "Some content");
        tasksList.add(task);

        when(service.getAllTasks()).thenReturn(tasksList);
        when(taskMapper.mapToTaskDtoList(tasksList)).thenReturn(taskListDto);


        //When & Then
        mockMvc.perform(get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Some task")))
                .andExpect(jsonPath("$[0].content", is("Some content")));
    }

    @Test
    public void shouldGetTaskById() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto((long) 1, "Some task", "Some content");
        Task task = new Task((long) 1, "Some task", "Some content");

        when(service.getTask(1L)).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);


        //When & Then
        mockMvc.perform(get("/v1/task/{taskId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Some task")))
                .andExpect(jsonPath("$.content", is("Some content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto((long) 1, "Some task", "Some content");
        Task task = new Task((long) 1, "Some task", "Some content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        when(service.saveTask(any(Task.class))).thenReturn(task);

        taskController.createTask(taskDto);


        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);


        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Some task")))
                .andExpect(jsonPath("$.content", is("Some content")));




    }
}

