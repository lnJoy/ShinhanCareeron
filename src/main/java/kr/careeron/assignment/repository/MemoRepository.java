package kr.careeron.assignment.repository;

import kr.careeron.assignment.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByCategoryNameContains(String categoryName);
    Page<Memo> findByTitleContains(Pageable pageable, String keyword);
}