package kr.careeron.assignment.service;

import kr.careeron.assignment.controller.dto.MemoDTO;
import kr.careeron.assignment.entity.Category;
import kr.careeron.assignment.entity.Memo;
import kr.careeron.assignment.repository.CategoryRepository;
import kr.careeron.assignment.repository.MemoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Memo createMemo(MemoDTO memoDTO) {
        Optional<Category> findOne = categoryRepository.findByName(memoDTO.getCategory().getName());
        if(!findOne.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다.");
        }
        Memo memo = Memo.builder()
                .title(memoDTO.getTitle())
                .content(memoDTO.getContent())
                .category(findOne.get())
                .build();
        memo = memoRepository.save(memo);

        return memo;
    }

    public Page<Memo> getMemos(Pageable pageable, String keyword) {
        if(keyword == null) {
            return memoRepository.findAll(pageable);
        } else {
            return memoRepository.findByTitleContains(pageable, keyword);
        }
    }

    public List<Memo> getMemosByCategoryName(String categoryName) {
        Optional<Category> findOne = categoryRepository.findByName(categoryName);
        if(!findOne.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다.");
        }
        return memoRepository.findByCategoryNameContains(findOne.get().getName());
    }

    public Memo getMemoById(Long id) {
        return memoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 존재하지 않습니다.")
        );
    }

    public Memo updateMemoById(Long id, MemoDTO memoDTO) {
        Optional<Memo> findOne = memoRepository.findById(id);
        if(!findOne.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 존재하지 않습니다.");
        }
        Memo memo = Memo.builder()
                .title(memoDTO.getTitle())
                .content(memoDTO.getContent())
                .category(memoDTO.getCategory())
                .build();
        memo = memoRepository.save(memo);

        return memo;
    }

    public void deleteMemoById(Long id) {
        memoRepository.deleteById(id);
    }
}
