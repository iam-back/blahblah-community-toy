package controller.api;

import data.dto.BoardDTO;
import data.dto.ReplyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserRestController {

    @GetMapping("/info/board/{userId}")
    public ResponseEntity<List<BoardDTO>> getBoardInfo(@PathVariable int userId){

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/info/reply/{userId}")
    public ResponseEntity<List<ReplyDTO>> getReplyInfo(@PathVariable int userId){



        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
