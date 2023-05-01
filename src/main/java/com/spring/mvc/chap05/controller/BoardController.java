package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // 목록 조회 요청
    @GetMapping("/list")
    public String list(Page page, Model model) {
        log.info("/board/list : GET");
        log.info("page : {}", page);
        List<BoardListResponseDTO> responseDTOS
                = boardService.getList(page);
        model.addAttribute("bList", responseDTOS);
        return "chap05/list";
    }

    // 글쓰기 화면 조회 요청
    @GetMapping("/write")
    public String write() {
        System.out.println("/board/write : GET");
        return "chap05/write";
    }

    // 글 등록 요청 처리
    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto) {
        System.out.println("/board/write : POST");
        boardService.register(dto);
        return "redirect:/board/list";
    }

    // 글 삭제 요청 처리
    @GetMapping("/delete")
    public String delete(int bno) {
        System.out.println("/board/delete : GET");
        boardService.delete(bno);
        return "redirect:/board/list";
    }

    // 글 상세 조회 요청
    @GetMapping("/detail")
    public String detail(int bno, Model model) {
        System.out.println("/board/detail : GET");
        model.addAttribute("b", boardService.getDetail(bno));
        return "chap05/detail";
    }

    //수정페이지 보여주기
    @GetMapping("/again")
    public String again(int bno, Model model) {
        System.out.println("/board/again : GET");
        BoardDetailResponseDTO board = boardService.getDetail(bno);
        model.addAttribute("a", board);

        return "chap05/again";
    }

    // 수정완료 값 받아오기
    @PostMapping("/again")
    public String again(int bno, BoardWriteRequestDTO dto) {
        System.out.println("/board/again : POST");
        boardService.retrieve(bno, dto);
        return "redirect:/board/detail?bno="+bno;
    }

}