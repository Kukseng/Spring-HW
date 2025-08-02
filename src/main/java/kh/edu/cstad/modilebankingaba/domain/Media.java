package kh.edu.cstad.modilebankingaba.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "medias")

public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false, length = 10)
    private String extension;

    @Column(name = "mime_type_file", nullable = false)
    private String mimeTypeFile;

    @Column(nullable = false )
    private Boolean isDelete;

}
