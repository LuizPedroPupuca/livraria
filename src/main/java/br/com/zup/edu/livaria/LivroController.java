package br.com.zup.edu.livaria;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class LivroController {

    private final LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @PostMapping("/livro")
    public ResponseEntity<?> cadastra(@RequestBody LivroRequest livroRequest, UriComponentsBuilder uriComponentsBuilder){
        if(livroRepository.existsByIsbn(livroRequest.getIsbn())){
            throw new ResponseStatusException(HttpStatus
                    .UNPROCESSABLE_ENTITY, "Já existe ISBN cadastrado");
        }
        Livro livro = livroRequest.toModel();
        livroRepository.save(livro);

        URI location = uriComponentsBuilder.path("/livro/{id}")
                .buildAndExpand(livro.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> errorExceptionHandler(ConstraintViolationException e){
        Map<String, Object> body = Map.of(
                "status", 422,
                "timestamp", LocalDateTime.now(),
                "message", "ISBN já exisente"
        );

        return ResponseEntity.unprocessableEntity().body(body);
    }
}
