package com.TB.TB.controller;

import com.TB.TB.logic.logic.authorization.AuthorizationManagerImpl;
import com.TB.TB.model.dto.LoginResponseDto;
import com.TB.TB.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authorization")
public class AuthorizationController {

	@Autowired
	private final AuthorizationManagerImpl authorizationManager;


	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
			MediaType.APPLICATION_JSON_VALUE)
	public LoginResponseDto addNewUser(@RequestBody UserDto userDto) {
		return authorizationManager.addNewUser(userDto);
	}

	@GetMapping("/info")
	public String isAuthorized() {
		return authorizationManager.isAuthorized();
	}

	@GetMapping("/exit")
	public boolean exit() { return authorizationManager.exit();}

	@ResponseBody
	@PostMapping(value = "/autho", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public LoginResponseDto authorization(@RequestBody UserDto userDto) {
		return authorizationManager.authorization(userDto);
	}

}
