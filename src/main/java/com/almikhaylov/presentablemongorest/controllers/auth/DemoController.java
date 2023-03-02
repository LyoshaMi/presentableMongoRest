package com.almikhaylov.presentablemongorest.controllers.auth;

import com.almikhaylov.presentablemongorest.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo-controller")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user.getUsername());
    }

}
