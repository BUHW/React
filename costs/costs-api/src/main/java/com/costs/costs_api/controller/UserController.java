package com.costs.costs_api.controller;

import com.costs.costs_api.domain.login.RegisterRequestDTO;
import com.costs.costs_api.domain.login.ResponseDTO;
import com.costs.costs_api.domain.user.User;
import com.costs.costs_api.infra.security.TokenService;
import com.costs.costs_api.repositories.UserRepository;
import com.costs.costs_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    // Como todas essas dependências têm interfaces, você poderia ter passado as interfaces no construtor,
    //  e não as implementações. Isso facilita a troca de implementações e testes de unidade (Inversão de Controle)
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService){
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/register")
    // O ideal é sempre o parametro do generic
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        // está regra deveria estar dentro do service. O controller serve apenas para receber a requisição e enviar a resposta
        Optional<User> user = this.userRepository.findByLogin(body.login());

        if (user.isEmpty()){
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setLogin(body.login());
            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getLogin(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    // Optional deveria ter sido tratado dentro do método getById do userService,
    //  e não deveria ter sido passado para o controller
    public Optional<User> getById(@PathVariable UUID id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable UUID id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.ok("Usuário excluído com sucesso!");
    }

}
