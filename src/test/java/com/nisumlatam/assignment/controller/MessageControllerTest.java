package com.nisumlatam.assignment.controller;

import com.nisumlatam.assignment.domain.Message;
import com.nisumlatam.assignment.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.isEmptyString;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    public void postMessage_shouldReturn500_whenTextNotProvided() throws Exception {
        Message message = new Message("message");
        when(messageService.distributeMessage(anyString())).thenReturn(message);
        this.mockMvc.perform(post("/messages")
                .contentType(MediaType.TEXT_HTML)
                .content(""))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void postMessage_shouldReturnInternalServerErrorMessage_whenTextNotProvided() throws Exception {
        Message message = new Message("message");
        when(messageService.distributeMessage(anyString())).thenReturn(message);
        this.mockMvc.perform(post("/messages")
                .contentType(MediaType.TEXT_HTML)
                .content(""))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal Server Error"));
    }

    @Test
    public void postMessage_shouldReturn200_whenTextProvided() throws Exception {
        Message message = new Message("message");
        when(messageService.distributeMessage(anyString())).thenReturn(message);
        this.mockMvc.perform(post("/messages")
                .contentType(MediaType.TEXT_HTML)
                .content("some message"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyString()));
    }
}