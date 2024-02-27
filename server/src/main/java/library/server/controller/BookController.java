package library.server.controller;

import library.server.entity.BookEntity;
import library.server.response.BaseResponse;
import library.server.response.BookListResponse;
import library.server.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
@RequestMapping("api/v1/book")
public class BookController {
    private BookService service;

    private BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(new BookListResponse(service.getAll()));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody BookEntity book, @PathVariable Long id) {
        try {
            service.save(book);
            return ResponseEntity.ok(new BaseResponse(true, "Поле изменено"));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponse(false, "Что-то пошло не так"));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> registration(@RequestBody BookEntity data) {
        try {
            service.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Книга добавлена"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse> update(@RequestBody BookEntity data){
        try {
            service.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Книга обновлена"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }
}
