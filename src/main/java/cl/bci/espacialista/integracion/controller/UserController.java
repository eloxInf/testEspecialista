package cl.bci.espacialista.integracion.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bci.espacialista.integracion.dto.RequestUser;
import cl.bci.espacialista.integracion.dto.ResponseGeneric;
import cl.bci.espacialista.integracion.dto.ResponseListUser;
import cl.bci.espacialista.integracion.dto.UserDto;
import cl.bci.espacialista.integracion.dto.ResponseCreateUser;
import cl.bci.espacialista.integracion.errors.EmailExistException;
import cl.bci.espacialista.integracion.errors.GenericException;
import cl.bci.espacialista.integracion.mgr.IUserMgr;
import io.swagger.annotations.Api;


@RestController
@Api(tags = "Apis de Usuario")
@RequestMapping(value = "espacialista/v1")
public class UserController {
	
	
	@Autowired
	private IUserMgr userMrg;
	
	@PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseCreateUser> createUser(@Valid @RequestBody RequestUser userData, BindingResult errors) throws EmailExistException, GenericException {
		
		ResponseCreateUser response = userMrg.createUser(userData, errors);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@GetMapping(value = "/user")
	public ResponseEntity<ResponseListUser> getAllUser(@RequestHeader("Authorization") String token) {
		
		ResponseListUser response = userMrg.getAllUser(token);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/user/{idUser}")
	public ResponseEntity<UserDto> getUser(@RequestHeader("Authorization") String token, @PathVariable String idUser) {
		
		UserDto response = userMrg.getOneUser(token, idUser);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<ResponseGeneric> deleteUser(@RequestHeader("Authorization") String token, @PathVariable String idUser) {
		
		ResponseGeneric response = userMrg.deleteUser(idUser, token);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/user")
	public ResponseEntity<String> updateUser(@RequestHeader("Authorization") String token) {
		
		return new ResponseEntity<>("ok", HttpStatus.CREATED);
	}
	
}
