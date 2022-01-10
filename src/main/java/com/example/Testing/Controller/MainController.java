package com.example.Testing.Controller;

import com.example.Testing.Modells.Room;
import com.example.Testing.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class MainController {
    @Autowired
    private RoomRepository roomRepository;


    @GetMapping("/")
    public String homePage(Model model) {
        Iterable<Room> room = roomRepository.findAll();
        model.addAttribute("room", room);
        return "home";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("title", "Добавление Записи");
        return "add";
    }

    @PostMapping("/add")
    public String BlogPostAdd(@RequestParam String room_name, Model model) {
        Room room = new Room(room_name, 0);
        roomRepository.save(room);
        return "redirect:/";
    }

    @GetMapping("/home/{id}")
    public String roomUpdate(@PathVariable(value = "id") long id, Model model) {
        Room room = roomRepository.findById(id).orElseThrow();
        if(room.getLight()==0)
        {
            room.setLight(1);
        }else
        {
            room.setLight(0);
        }
        roomRepository.save(room);
        return "redirect:/";
    }
}