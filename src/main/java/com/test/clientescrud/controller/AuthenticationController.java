package com.test.clientescrud.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.clientescrud.models.ERole;
import com.test.clientescrud.models.Role;
import com.test.clientescrud.models.Usuarios;
import com.test.clientescrud.payload.request.LoginRequest;
import com.test.clientescrud.payload.request.SignupRequest;
import com.test.clientescrud.payload.response.JWTAuthReponse;
import com.test.clientescrud.payload.response.MessageResponse;
import com.test.clientescrud.repository.IRole;
import com.test.clientescrud.repository.IUser;
import com.test.clientescrud.security.JwtTokenProvider;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IUser usuarioRepo;
	
	@Autowired
	private IRole rolRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/iniciar")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		//obtenemos el token del jwtTokenProvider
		String token = jwtTokenProvider.generarToken(authentication);
		
		return ResponseEntity.ok(new JWTAuthReponse(token));
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (usuarioRepo.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Nombre de usuario ya existe!"));
		}

		if (usuarioRepo.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email ya existe!"));
		}

		Usuarios user = new Usuarios(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 passwordEncoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = rolRepo.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Rol no Encontrado Vacio"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = rolRepo.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Rol no Encontrado. A"));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = rolRepo.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Rol no Encontrado. M"));
					roles.add(modRole);

					break;
				default:
					Role userRole = rolRepo.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Rol no Encontrado. U"));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		usuarioRepo.save(user);

		return ResponseEntity.ok(new MessageResponse("Registro Exitoso!"));
	}
}
