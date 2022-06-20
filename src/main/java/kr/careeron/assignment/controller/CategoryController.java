package kr.careeron.assignment.controller;

import kr.careeron.assignment.controller.dto.CategoryDTO;
import kr.careeron.assignment.entity.Category;
import kr.careeron.assignment.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public Category createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }
    
    @GetMapping("")
    public Page<Category> getCategories(Pageable pageable, @RequestParam(required = false) String keyword) {
        return categoryService.getCategories(pageable, keyword);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
}
