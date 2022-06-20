package kr.careeron.assignment.controller.dto;

import kr.careeron.assignment.entity.Category;
import lombok.Data;

@Data
public class MemoDTO {
    String title;
    String content;
    Category category;
}
