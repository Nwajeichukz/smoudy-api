package com.smoudy.smoudy.controller;

import com.smoudy.smoudy.dto.ItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/api")
public class DataController {
    private final Map<Integer, ItemDto> database = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @GetMapping
    public ResponseEntity<String> welcome() {
        String html = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Welcome</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                        background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%);
                    }
                    .container {
                        text-align: center;
                        background: white;
                        padding: 40px;
                        border-radius: 10px;
                        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                    }
                    h1 {
                        color: #333;
                    }
                    p {
                        color: #666;
                        font-size: 18px;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>Welcome!</h1>
                    <p>Your deployment was successful! 🎉</p>
                    <p>This application is running locally and ready to use.</p>
                </div>
            </body>
            </html>
            """;
        ItemDto itemDto = new ItemDto();
        itemDto.setName("Welcome");
        itemDto.setDescription("Your deployment was successful!");

        ItemDto itemDto1 = new ItemDto("zack", "zack is a boy");
        ItemDto itemDto2 = new ItemDto("zain", "zain is a boy");
        ItemDto itemDto3 = new ItemDto("tommy", "tommy is a boy");
        ItemDto itemDto4 = new ItemDto("alice", "alice is a girl");
        ItemDto itemDto5 = new ItemDto("bob", "bob is a developer");
        ItemDto itemDto6 = new ItemDto("charlie", "charlie loves coding");
        ItemDto itemDto7 = new ItemDto("diana", "diana is a designer");


        database.put(1, itemDto);
        database.put(2, itemDto1);
        database.put(3, itemDto2);
        database.put(4, itemDto3);
        database.put(5, itemDto4);
        database.put(6, itemDto5);
        database.put(7, itemDto6);
        database.put(8, itemDto7);


        return ResponseEntity.ok().header("Content-Type", "text/html").body(html);
    }

}
