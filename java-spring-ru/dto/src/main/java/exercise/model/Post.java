package exercise.model;

import exercise.dto.PostDTO;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.GeneratedValue;


@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String title;

    private String body;

    public PostDTO toDTO() {
        var postDTO = new PostDTO();
        postDTO.setId(this.getId());
        postDTO.setTitle(this.getTitle());
        postDTO.setBody(this.getBody());
        return postDTO;
    }
}
