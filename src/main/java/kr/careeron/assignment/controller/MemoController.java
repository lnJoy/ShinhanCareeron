package kr.careeron.assignment.controller;

import kr.careeron.assignment.controller.dto.MemoDTO;
import kr.careeron.assignment.entity.Memo;
import kr.careeron.assignment.service.MemoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memos")
@AllArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @PostMapping("")
    public Memo createMemo(@RequestBody MemoDTO memoDTO) {
        return memoService.createMemo(memoDTO);
    }
    
    @GetMapping("")
    public Page<Memo> getMemos(Pageable pageable, @RequestParam(required = false) String keyword) {
        return memoService.getMemos(pageable, keyword);
    }

    @GetMapping("/category/{categoryName}")
    public List<Memo> getMemosByCategoryName(@PathVariable String categoryName) {
        return memoService.getMemosByCategoryName(categoryName);
    }

    @GetMapping("/{id}")
    public Memo getMemoById(@PathVariable Long id) {
        return memoService.getMemoById(id);
    }

    @PutMapping("/{id}")
    public Memo updateMemo(@PathVariable Long id, @RequestBody MemoDTO memoDTO) {
        return memoService.updateMemoById(id, memoDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMemo(@PathVariable Long id) {
        memoService.deleteMemoById(id);
        return true;
    }
}
