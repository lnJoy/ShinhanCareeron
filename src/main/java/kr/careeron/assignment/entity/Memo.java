package kr.careeron.assignment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

    @Column(length = 100)
    String title;

    @Column(columnDefinition = "TEXT")
    String content;

    @Builder
    public Memo(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

}